package me.choujiang;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StartServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3135623727255811200L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		Resource.setSTART(true);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		this.doGet(req, resp);
	}
}
