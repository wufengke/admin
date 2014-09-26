package com.cyou.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static String[] splitStrWithReg(String str,String regex){
		if(null!=str && !"".equals(str)){
			return str.split(",");
		}
		return new String[0];
	}
	/**
	 * java去除字符串中的空格、回车、换行符、制表符
	 * @param src
	 * @return
	 */
	public static String replace(String src) {
		String dest = "";
		if (src!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(src);
			dest = m.replaceAll("");
		}
		return dest;
	}
	public static void main(String[] args) {
		String string = "1000 0";
		Double d = Double.valueOf(replace(string));
		System.out.println(d);
	}
}
