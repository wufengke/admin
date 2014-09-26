/*
 * Created on 2004-6-16 by cheng
 */
package com.cyou.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * @author cheng chengchengji@sohu-inc.com Created on 2004-3-11 at 14:14:10
 */
public class StrMD5 {

//	public static void main(String[] args) {
//		String password = "root";
//		String md5 = new StrMD5(password).getResult();
//		System.out.println(password);
//		System.out.println(md5);
//	}

	private String m_result;
	private String m_key;
	private MessageDigest currentAlgorithm;

	public StrMD5(String key) {
		this.m_key = key;
		try {
			currentAlgorithm = MessageDigest.getInstance("MD5");
			this.m_result = computeDigest(loadBytes(this.m_key)); //+ "\r\n";
		} catch (NoSuchAlgorithmException e) {
			System.out.println("MD5 algorithm not available.");
		}
	}

	public String getResult() {
		return this.m_result;
	}
	static private byte[] loadBytes(String name) {
		byte[] buffer = name.getBytes();
		//System.out.println(name);
		//System.out.println("Begin...");
		return (buffer);
	}

	private String computeDigest(byte[] b) {
		currentAlgorithm.reset();
		currentAlgorithm.update(b);
		byte[] hash = currentAlgorithm.digest();
		String d = "";
		int usbyte = 0; // unsigned byte
		for (int i = 0;
			i < hash.length;
			i += 2) { // format with 2-byte words with spaces.
			usbyte = hash[i] & 0xFF;
			// byte-wise AND converts signed byte to unsigned.
			if (usbyte < 16)
				d += "0" + Integer.toHexString(usbyte);
			// pad on left if single hex digit.
			else
				d += Integer.toHexString(usbyte);
			usbyte = hash[i + 1] & 0xFF;
			// byte-wise AND converts signed byte to unsigned.
			if (usbyte < 16)
				d += "0" + Integer.toHexString(usbyte);
			//+ " "; // pad on left if single hex digit.
			else
				d += Integer.toHexString(usbyte); // + " ";
		}
		//return d.toUpperCase();
		return d.trim().toLowerCase();
	}
	
	public static String getStrMD5(String myStr) throws Exception{
		if(myStr.length()>0){
			StrMD5 myStrMD5 = new StrMD5(myStr);
			return (myStrMD5.getResult());
		} 
		else {
			return null;	
		}
	}


}

