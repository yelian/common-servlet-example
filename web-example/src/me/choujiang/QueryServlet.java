package me.choujiang;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QueryServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3135623727255811200L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		boolean isStart = Resource.isSTART();
		req.setAttribute("start", isStart);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		this.doGet(req, resp);
	}
}
