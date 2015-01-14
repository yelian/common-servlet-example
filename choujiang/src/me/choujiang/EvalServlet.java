package me.choujiang;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class EvalServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String fileName = (String) req.getSession().getAttribute("fileName");
		//ExcelUtil.getExcelTemplate(fileName);
		ExcelUtil excel = new ExcelUtil();
		Map<String, Object> map = excel.evaluator(fileName, req);
		try {
			Writer writer = resp.getWriter();
			//Map<String, Object> ret = new HashMap<String, Object>();
			//ret.put(arg0, arg1)
			writer.write(JSON.toJSONString(map));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		this.doGet(req, resp);
	}
}
