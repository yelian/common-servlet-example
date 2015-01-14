package me.choujiang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class IndexServlet extends HttpServlet{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5007004746394544168L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		try {
			req.setAttribute("personInfo", JSON.toJSONString(initResource(req)));
			req.getRequestDispatcher("page/index.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		this.doGet(req, resp);
	}
	
	private List<Map<String, String>> initResource(HttpServletRequest req) throws IOException{
		BufferedReader allReader = new BufferedReader(new InputStreamReader(req.getServletContext().getResourceAsStream("allPersons.txt")));
		String allLine = null;
		Resource.allList = new ArrayList<Map<String, String>>();
		while((allLine = allReader.readLine()) != null){
			String[] infos = allLine.split("@");
			//������֤�����룬�ֻ����룬���š�
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", infos[0].trim());	//����
			map.put("id", infos[1].trim());		//֤������
			map.put("phone", infos[2]);			//�ֻ�����
			map.put("card", infos[3]);			//����
			map.put("visual", infos[0].trim()+infos[1].trim());		//�齱��ʾ����+֤����
			map.put("award", "#");
			//0��ʾ������Ա��, 1��ʾ����Ա��
			map.put("isBank", "0");
			
			Resource.allList.add(map);
		}
		
		BufferedReader bankReader = new BufferedReader(new InputStreamReader(req.getServletContext().getResourceAsStream("bankPersons.txt")));
		String bankLine = null;
		while((bankLine=bankReader.readLine()) != null){
			String[] bankInfos = bankLine.split("@");
			//����Ա����������֤�����롣
			String id = bankInfos[1].trim();
			for(Map<String, String> map: Resource.allList){
				if(map.containsValue(id)){
					map.put("isBank", "1");
					break;
				}
			}
		}
		return Resource.allList;
	}
}
