package com.cyou.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StrMD5 {
    private String m_result;
    private String m_key;
    private MessageDigest currentAlgorithm;

    public StrMD5(String key) {
        this.m_key = key;
        try {
           currentAlgorithm = MessageDigest.getInstance("MD5");
           this.m_result = computeDigest(loadBytes(this.m_key));
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String getResult() {
        return this.m_result;
    }
    
    static private byte[] loadBytes(String name) {
        byte [] buffer = name.getBytes();
        return buffer;
    }

    private String computeDigest(byte[] b) {
      currentAlgorithm.reset();
      currentAlgorithm.update(b);
      byte[] hash = currentAlgorithm.digest();
      String d = "";
      int usbyte = 0;  // unsigned byte
      for (int i = 0; i < hash.length; i += 2) {   // format with 2-byte words with spaces.
          usbyte = hash[i] & 0xFF;   // byte-wise AND converts signed byte to unsigned.
          if (usbyte < 16) {
        	  d += "0" + Integer.toHexString(usbyte);   // pad on left if single hex digit.
          } else {
        	  d += Integer.toHexString(usbyte);
          }
          usbyte = hash[i+1] & 0xFF;   // byte-wise AND converts signed byte to unsigned.
          if (usbyte < 16) {
        	  d += "0" + Integer.toHexString(usbyte);//+ "  ";   // pad on left if single hex digit.
          } else {
        	  d += Integer.toHexString(usbyte);// + "  ";
          }
      }
      return d.trim().toLowerCase();
   }
}