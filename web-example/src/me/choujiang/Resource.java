package me.choujiang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Resource {
	
	//��ʼ�齱��־��ǰ̨���ݴ˱�־��ʼ���г齱��
	private static boolean START = false;
	
	//�����齱��־��ǰ̨���ݴ˱�־�������г齱��
	private static boolean STOP = false;
	
	//��ǰ�齱���
	private static String CURRENTAWARD = "";
	
	public static List<Map<String, String>> allList = null;
	
	public static boolean isSTOP() {
		return STOP;
	}

	public static void setSTOP(boolean sTOP) {
		STOP = sTOP;
	}

	//�齱���
	private static final Map<String, String> AWARDED = new HashMap<String, String>();

	public static void setSTART(boolean sTART) {
		START = sTART;
	}

	public static boolean isSTART() {
		return START;
	}

	public static Map<String, String> getAwarded() {
		return AWARDED;
	}

	public static String getCurrentaward() {
		return CURRENTAWARD;
	}
	
	public static void setCurrentaward(String award) {
		CURRENTAWARD = award;
	}
}
