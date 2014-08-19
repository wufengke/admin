package com.cyou.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author maojunfeng
 * 生成激活码
 */
public class CreateActiveCodeUtil {
	public static int gameMode=1;
	/**
	 * date:Apr 19, 2012
	 * author:maojunfeng
	 * return:String
	 * 同步获取激活码，以时间为标准,进行MD5加密 
	 */
	public static synchronized String getActiveCode() {
		try {
			Thread.sleep(25);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat format =new SimpleDateFormat("yyMMddHHmmssS");
		StrMD5 md5 = new StrMD5(format.format(new Date()));
		return md5.getResult();
	}
}
