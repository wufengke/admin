package com.cyou.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
	private static Properties props = new Properties();
	private static InputStream in = null;
	static {
		try{
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/config.properties");
			props.load(in);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String getProperty(String key){
		return props.getProperty(key);
	}
}
