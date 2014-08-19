package com.cyou.core.util;

public class BrowserUtil {
	
	public static String getBrowserType(String UserAgent){
		if(UserAgent.toLowerCase().indexOf("firefox")!=-1){
			return "firefox";
		}else if(UserAgent.toLowerCase().indexOf("msie")!=-1){
			return "IE";
		}else{
			return "other";
		}
		
	}
}
