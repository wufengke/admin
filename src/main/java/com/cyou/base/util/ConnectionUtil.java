package com.cyou.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 * @ClassName:	ConnectionUtil.java
 * @Description:	获取数据库连接辅助类
 * @author:			hushasha
 * @Date:				2014-5-7 下午04:50:48
 */
public class ConnectionUtil {
	
	/**
	 * 
	 * @Title:				getStatConnection
	 * @Description:	获取cypay统计库的连接
	 * @param:			@return   
	 * @return:			Connection   
	 * @throws:
	 */
	public static Connection getStatConnection() {
		Connection conn = null;
		String driver = "";
		String url = "";
		String user = "";
		String password = "";
		InputStream in =  null;
		try {
			
			driver = PropertyUtil.getProperty("jdbc.driverClassName");
			url = PropertyUtil.getProperty("stat.jdbc.url");
			user = PropertyUtil.getProperty("stat.jdbc.username");
			password = PropertyUtil.getProperty("stat.jdbc.password");
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return conn;
	}

}
