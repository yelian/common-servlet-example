package me.choujiang;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StopServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3135623727255811200L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		String flag = req.getParameter("type");
		resp.setHeader("Content-Type", "application/json;charset=utf-8");
		if("stop".equals(flag)){
			boolean isStarted = Resource.isSTART();
			if(isStarted){
				Resource.setSTART(false);
				Resource.setSTOP(true);
			}
			try {
				resp.getWriter().write("{\"stop\":\"" + isStarted+"\"}");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if("result".equals(flag)){
			try {
				resp.getWriter().write("{\"result\":\"" + Resource.getCurrentaward()+"\"}");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		this.doGet(req, resp);
	}
}
