package me.choujiang;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadPageServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		try {
			req.getRequestDispatcher("page/upload.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		this.doGet(req, resp);
	}
}
