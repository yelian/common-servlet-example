package me.excel.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.excel.common.ExcelUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		try {
			req.setCharacterEncoding("UTF-8");
			
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(req);
			Iterator<FileItem> itr = items.iterator();
			while(itr.hasNext()){
				FileItem item = itr.next();
				String fileName = item.getName();
				System.out.println(fileName);
				InputStream is = item.getInputStream();
				ExcelUtil excel = new ExcelUtil();
				String tables = excel.mapping2Table(is, fileName);
				req.getSession(true).setAttribute("table", tables);
				req.getSession().setAttribute("fileName", fileName);
				req.setAttribute("table", tables);
				req.getRequestDispatcher("page/show.jsp").forward(req, resp);
				//ExcelUtil.storeExcelTemplate(fileName);
				//item.getInputStream();
				is.close();
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		this.doGet(req, resp);
	}
}
