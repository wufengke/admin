package com.cyou.core.util;

import java.util.Random;

public class RandomNumUtil {

	public static String generateString(int length){
	    String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        StringBuffer sb = new StringBuffer();
	        Random random = new Random();
	        for (int i = 0; i < length; i++) {
	                sb.append(allChar.charAt(random.nextInt(allChar.length())));
	        }
	        return sb.toString();
	}
	
}
