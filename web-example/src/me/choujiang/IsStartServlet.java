package me.choujiang;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IsStartServlet  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3135623727255811200L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		boolean isStart = Resource.isSTART();
		try {
			resp.getWriter().write(isStart+"");
			//System.out.println("get start sigal:" + isStart);
			if(isStart){
				Resource.setSTART(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//req.setAttribute("isStart", isStart);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		this.doGet(req, resp);
	}
}