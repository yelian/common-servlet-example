package me.excel.common;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelUtil {
	public void createXLS(String fileName) throws IOException{
		Workbook wb = new HSSFWorkbook();
		FileOutputStream out = new FileOutputStream(fileName);
		wb.write(out);
		out.close();
	}
	
	public void createXLSX(String fileName) throws IOException{
		Workbook wb = new XSSFWorkbook();
		FileOutputStream out = new FileOutputStream(fileName);
		wb.write(out);
		out.close();
	}
	
	//@Test
	public void readXLS(String fileName){
		try{
			Workbook wb = WorkbookFactory.create(new FileInputStream(fileName));
			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> it = sheet.iterator();
			int countMergeRegion = sheet.getNumMergedRegions();
			String info = "";
			for(int x=0; x<countMergeRegion; x++){
				CellRangeAddress rangeAddress =  sheet.getMergedRegion(x);
				info += "(" + rangeAddress.getFirstColumn() + "," + rangeAddress.getFirstRow() + ")";
				info += "(" + rangeAddress.getLastColumn() + "," + rangeAddress.getLastRow() + ")" + "\n";
 			}	
			System.out.println(info + "-------------------------------");
			String formula = "";
			while(it.hasNext()){
				Row row = it.next();
				Iterator<Cell> itc = row.cellIterator();
				while(itc.hasNext()){
					Cell cell = itc.next();
					//cell.get
					String m = cell.getColumnIndex() + ";" + cell.getRowIndex() + ";" + getCellValue(cell) + "|";
					if(cell.isPartOfArrayFormulaGroup()){
						formula = cell.getCellFormula() + "||" + cell.getCachedFormulaResultType() + "||";
					}
					System.out.print(m);
				}
				System.out.println();
			}
			System.out.println(formula);
			
			System.out.println("----------------------------------");
			System.out.println(sheet.getLeftCol() + ":" + sheet.getLastRowNum());
			System.out.println("----------------------------------");
			System.out.println(getMaxColumnNum(sheet));
			System.out.println("==================================================");
			System.out.println(mapping2Table(wb));
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//获取excel�?��列数
	public int getMaxColumnNum(Sheet sheet){
		int max = 0;
		for(Row row: sheet){
			int r1 = row.getPhysicalNumberOfCells();
			max = max > r1 ? max : r1;
		}
		return max;
	}
	
	public String mapping2Table(InputStream is, String fileName){
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(is);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileOutputStream template = null;
		try {
			//TODO 模版文件文件名：
			template = new FileOutputStream(fileName);
			wb.write(template);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(template != null){
				try {
					template.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return this.mapping2Table(wb);
	}
	
	public String mapping2Table(Workbook wb){
		//TODO
		Sheet sheet = wb.getSheetAt(0);
		int totalMergeRegion = sheet.getNumMergedRegions();
		List<CellRangeAddress> rangeAddresses = null;
		
		//获取合并单元格列�?
		for(int x=0; x<totalMergeRegion; x++){
			CellRangeAddress rangeAddress = sheet.getMergedRegion(x);
			if(rangeAddresses == null){
				rangeAddresses = new ArrayList<CellRangeAddress>();
			}
			rangeAddresses.add(rangeAddress);
		}
		
		StringBuilder sb = new StringBuilder("<tr>");
		int maxColumnNum = getMaxColumnNum(sheet);
		int currentRowIndex = 0;
		for(Row row: sheet){
			int currentColumnIndex = 0;
			for(Cell cell: row){
				 cell.getCellType();
				 if(rangeAddresses == null){
					 buildTD(sb, currentRowIndex, currentColumnIndex, cell);
				 } else {
					 CellRangeInfo info = getCellPosition(cell, rangeAddresses);
					 switch(info.getPosition()){
					 case ExcelConstant.CELL_NOT_IN_RANGE:
						 //sb.append("<td>");
						 buildTD(sb, currentRowIndex, currentColumnIndex, cell);
						 break;
					 case ExcelConstant.CELL_IN_RANGE_START:
						 CellRangeAddress address = info.getRangeAddress();
						 int rangeX = address.getLastRow() - address.getFirstRow() + 1;
						 int rangeY = address.getLastColumn() - address.getFirstColumn() + 1;
						 sb.append("<td ");
						 if(rangeX > 1){
							 sb.append("rowspan=\""+rangeX+"\" ");
						 }
						 if(rangeY > 1){
							 sb.append("colspan=\""+rangeY+"\" ");
						 }
						 sb.append(">");
						 sb.append(getCellValue(cell)).append("</td>");
						 break;
					 }
				 }
				 
				 currentColumnIndex++;
			}
			while(currentColumnIndex < maxColumnNum){
				//sb.append("<td>empty</td>");
				buildTD(sb, currentRowIndex, currentColumnIndex, null);
				currentColumnIndex++;
			}
			sb.append("</tr>\n<tr>");
			currentRowIndex++;
		}
		
		return sb.substring(0, sb.length()-4);
	}
	
	public StringBuilder buildTD(StringBuilder sb, int currentRowIndex, int currentColumnIndex, Cell cell){
		String cellHiddenName = ExcelUtil.getCellName(currentRowIndex, currentColumnIndex);
		if(cell == null){
			sb.append("<td>")
			 .append("<input type=\"text\" class=\"easyui-numberbox\" value=\"100\" data-options=\"min:0,precision:2\" style=\"width:100%\"")
			 .append(" name=\""+cellHiddenName+"\"></input>");
		} else {
			switch(cell.getCellType()){
				 case Cell.CELL_TYPE_BLANK:
	//				 sb.append("<td ").append("name=\""+cellHiddenName+"\">").append("${").append(cellHiddenName).append("}");
					 sb.append("<td>")
					 .append("<input type=\"text\" class=\"easyui-numberbox\" value=\"100\" data-options=\"min:0,precision:2\" style=\"width:100%\"")
					 .append(" name=\""+cellHiddenName+"\"></input>");
					 cell.setCellValue("#"+cellHiddenName+"#");
					 break;
				 case Cell.CELL_TYPE_STRING:
					 String v = cell.getStringCellValue();
					 if(v == null || "".equals(v.trim())){
						 sb.append("<td>")
						 .append("<input type=\"text\" class=\"easyui-numberbox\" value=\"100\" data-options=\"min:0,precision:2\" style=\"width:100%\"")
						 .append(" name=\""+cellHiddenName+"\"></input>");
						 cell.setCellValue("#"+cellHiddenName+"#");
					 } else {
						 sb.append("<td>").append(v);
					 }
					 break;
				 case Cell.CELL_TYPE_NUMERIC:
					 double v1 = cell.getNumericCellValue();
					 if(v1 == 0){
						 sb.append("<td>")
						 .append("<input type=\"text\" class=\"easyui-numberbox\" value=\"100\" data-options=\"min:0,precision:2\" style=\"width:100%\"")
						 .append(" name=\""+cellHiddenName+"\"></input>");
						 cell.setCellValue("#"+cellHiddenName+"#");
					 } else {
						 sb.append("<td>").append(v1);
					 }
					 break;
				 case Cell.CELL_TYPE_FORMULA:
					 sb.append("<td ").append("name=\""+cellHiddenName+"\">").append("${").append(cellHiddenName).append("}");;
					 break;
			 }
		 }
		 sb.append("</td>");
		 return sb;
	}
	
	public CellRangeInfo getCellPosition(Cell cell, List<CellRangeAddress> rangeAddresses){
		int rowIndex = cell.getRowIndex();
		int colIndex = cell.getColumnIndex();
		CellRangeInfo info = new CellRangeInfo();
		int position = ExcelConstant.CELL_NOT_IN_RANGE;
		if(rangeAddresses != null){
			for(CellRangeAddress cra: rangeAddresses){
				if(cra.isInRange(rowIndex, colIndex)){
					position = ExcelConstant.CELL_IN_RANGE_NOTSE;
					info.setRangeAddress(cra);
				};
				if(position == ExcelConstant.CELL_IN_RANGE_NOTSE){
					if(cra.getFirstColumn() == colIndex && cra.getFirstRow() == rowIndex){
						position = ExcelConstant.CELL_IN_RANGE_START;
					} else if(cra.getLastColumn() == colIndex && cra.getLastRow() == rowIndex){
						position = ExcelConstant.CELL_IN_RANGE_END;
					}
				}
				if(position != ExcelConstant.CELL_NOT_IN_RANGE){
					info.setPosition(position);
					break;
				}
			}
		}
		return info;
	}
	
	private static String getCellName(int rowIndex, int colIndex){
		return "R"+rowIndex+"_C"+colIndex;
	}
	
	public static CellReference getCellReference(String name){
		CellReference cellReference = null;
		if(name != null){
			String[] indexes = name.split("_");
			if(indexes != null && indexes.length == 2){
				int rIndex = -1;
				int cIndex = -1;
				for(String index: indexes){
					if(index.startsWith("R")){
						rIndex = Integer.parseInt(index.substring(1));
					} else if(index.startsWith("C")){
						cIndex = Integer.parseInt(index.substring(1));
					}
				}
				cellReference = new CellReference(rIndex, cIndex);
			}
		}
		return cellReference;
	}
	
	public void evaluator(){
		try{
			Workbook wb = WorkbookFactory.create(new FileInputStream("D:/workspace/metogo/example/src/excel/template.xls")); 
			Sheet sheet = wb.getSheetAt(0);
			int rIndex = 0;
			for(Row row: sheet){
				int cIndex = 0;
				for(Cell cell: row){
					if(cIndex == 2){
						if(rIndex == 130){
							cell.setCellValue(130);
						} else if(rIndex == 131) {
							cell.setCellValue(131);
						} else if(rIndex == 133){
							cell.setCellValue(133);
							//break;
						}
					}
					cIndex++;
				}
				rIndex++;
			}
			
			CellReference cellReference = new CellReference("C136"); 
			Row row = sheet.getRow(cellReference.getRow());
			Cell cell = row.getCell(cellReference.getCol()); 
			CellValue cellValue = wb.getCreationHelper().createFormulaEvaluator().evaluate(cell);
			switch (cellValue.getCellType()) {
			    case Cell.CELL_TYPE_BOOLEAN:
			        System.out.println(cellValue.getBooleanValue());
			        break;
			    case Cell.CELL_TYPE_NUMERIC:
			        System.out.println(cellValue.getNumberValue());
			        break;
			    case Cell.CELL_TYPE_STRING:
			        System.out.println(cellValue.getStringValue());
			        break;
			    case Cell.CELL_TYPE_BLANK:
			        break;
			    case Cell.CELL_TYPE_ERROR:
			        break;		
			    // CELL_TYPE_FORMULA will never happen
			    case Cell.CELL_TYPE_FORMULA: 
			        break;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public Map<String, Object> evaluator(String fileName, HttpServletRequest req){
		Map<String, Object> ret = new HashMap<String, Object>();
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(new File(fileName));
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheet = wb.getSheetAt(0);
		String info = req.getParameter("info");
		if(info != null && !"".equals(info.trim())){
			String[] _infos = info.split("&");
			for(String _info: _infos){
				String[] kv = _info.split("=");
				String k = kv[0];
				String v = kv[1];
				if(k.startsWith("R") && k.contains("_C")){
					CellReference reference = getCellReference(k);
					Cell cell = sheet.getRow(reference.getRow()).getCell(reference.getCol());
					if(cell == null){
						cell = sheet.getRow(reference.getRow()).createCell(reference.getCol());
					}
					cell.setCellValue(Double.parseDouble(v));
					//ret.put(k, v);
				}
			}
		}
		FormulaEvaluator eval = wb.getCreationHelper().createFormulaEvaluator();
		for(Row row: sheet){
			for(Cell cell: row){
				if(Cell.CELL_TYPE_FORMULA == cell.getCellType()){
					cell.setCellFormula(cell.getCellFormula());
					CellValue cellValue = eval.evaluate(cell);
					ret.put(getCellName(cell.getRowIndex(), cell.getColumnIndex()), cellValue.getNumberValue());
				}
			}
		}
		return ret;
	}
	
	public static String getCellValue(Cell cell){
		switch(cell.getCellType()) {
			case Cell.CELL_TYPE_BLANK :
				return "";
			case Cell.CELL_TYPE_BOOLEAN:
				return cell.getBooleanCellValue()+"";
			case Cell.CELL_TYPE_ERROR:
				return "error";
			case Cell.CELL_TYPE_FORMULA:
				return cell.getCellFormula() + ";" + cell.getCachedFormulaResultType();
			case Cell.CELL_TYPE_NUMERIC:
				return cell.getNumericCellValue() + "";
			case Cell.CELL_TYPE_STRING:
				return cell.getStringCellValue();
			default:
				return "";
		}		
	}
	
	class CellRangeInfo{
		private int position;
		private CellRangeAddress rangeAddress;
		public int getPosition() {
			return position;
		}
		public void setPosition(int position) {
			this.position = position;
		}
		public CellRangeAddress getRangeAddress() {
			return rangeAddress;
		}
		public void setRangeAddress(CellRangeAddress rangeAddress) {
			this.rangeAddress = rangeAddress;
		}		
	}
	
	private static Map<String, ThreadLocal<Workbook>> restoredExcelTemplate = new HashMap<String, ThreadLocal<Workbook>>();
	public static void storeExcelTemplate(String fileName){
		String uuid = UUID.randomUUID().toString();
		//TODO将模版保存到数据库
		restoredExcelTemplate.put(uuid, loadExcelTemplate2Cache(fileName));
	}
	
	public static Workbook getExcelTemplate(String fileName){
		return restoredExcelTemplate.get(fileName).get();
	}
	
	public static void initExcelTemplate(){
		//TODO
		//系统初始化时，加载excel模版
	}
	
	private static ThreadLocal<Workbook> loadExcelTemplate2Cache(final String fileName){
		return new ThreadLocal<Workbook>(){
			@Override
			protected Workbook initialValue() {
				super.initialValue();
				Workbook wb = null;
				try {
					wb = WorkbookFactory.create(new File(fileName));
				} catch (InvalidFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return wb;
			}
		};
	}
	
}
