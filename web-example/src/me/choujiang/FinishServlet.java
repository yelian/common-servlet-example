package me.choujiang;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FinishServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3135623727255811200L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		try {
			//TODO
			String path = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/WEB-INF/";
			//System.out.println(path);
			req.setAttribute("path", path);
			Map<String, String> awardMap = Resource.getAwarded();
			Iterator<String> it = awardMap.keySet().iterator();
			while(it.hasNext()){
				String k = it.next();
				String v = awardMap.get(k);
				if("4".equals(k)){
					req.setAttribute("award"+k, v);
				} else {
					String rli = getAwardLI(v);
					req.setAttribute("award"+k, rli);
				}
			}
			req.getRequestDispatcher("page/award.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		this.doGet(req, resp);
	}
	
	public String getAwardLI(String awards){
		if(awards == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(String award: awards.split(";")){
			if(award != null && (!"".equals(award.trim()))){
				sb.append("<li><p>" + award + "</p></li>");
			}
		}
		return sb.toString();
	}
}
