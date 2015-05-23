package me.jsoup.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JsonpServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		String re = req.getParameter("jsonp");
		System.out.println(re);
		String s = "onCustomerLoaded";
		try {
			OutputStream os = resp.getOutputStream();
			os.write((s + "("+"[{\"id\":\"1\",\"name\":\"测试1\"},{\"id\":\"2\",\"name\":\"测试2\"}]"+")").getBytes("utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		this.doGet(req, resp);
	}
}
