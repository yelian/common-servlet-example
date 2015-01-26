package me.choujiang;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AwardedPersonServlet  extends HttpServlet{

	/**
	 * 
	 */
	
	private static final String BASE_PATH = "D:\\AWARD\\";
	private static final long serialVersionUID = -3135623727255811200L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException{
		req.setCharacterEncoding("UTF-8");
		String awarded = req.getParameter("awarded");
		System.out.println(awarded);
		String[] awardedInfo = awarded.split(":");
		String current = awardedInfo[0];
		String awardeds = awardedInfo[1];
		
		Resource.setCurrentaward(awardeds);
		Resource.getAwarded().put(current, awardeds);
		
		String path = BASE_PATH + "第"+current+"次抽奖名单.txt";
		File file = new File(path);
		System.out.println(file.getAbsolutePath());
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			PrintWriter pw = new PrintWriter(file);
			for(String a: awardeds.split(";")){
				for(Map<String, String> map: Resource.allList){
					if(map.containsValue(a)){
						pw.write(map.get("id") + "@" + map.get("name")+"\r\n");
						break;
					}
				}
				//pw.write(a+"\r\n");
			}
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Resource.setSTART(false);
		//req.setAttribute("isStart", isStart);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException{
		this.doGet(req, resp);
	}
}