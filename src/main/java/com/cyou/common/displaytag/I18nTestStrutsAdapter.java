package com.cyou.common.displaytag;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.apache.log4j.Logger;
import org.apache.struts.Globals;
import org.displaytag.localization.I18nResourceProvider;
import org.displaytag.localization.LocaleResolver;


public class I18nTestStrutsAdapter implements I18nResourceProvider,
		LocaleResolver {
	private static Logger log = Logger.getLogger(I18nTestStrutsAdapter.class);
	private static Map<String,String> i18nEnMap=new HashMap<String, String>(); 
	private static Map<String,String> i18nZHMap=new HashMap<String, String>(); 
	/**
	    * prefix/suffix for missing entries.
	    */
	   public static final String UNDEFINED_KEY = "???"; //$NON-NLS-1$

	public String getResource(String resourceKey, String defaultValue, Tag tag, PageContext pageContext) {
		 String titlkey = (resourceKey != null) ? resourceKey : defaultValue;  
	        String[] temp = titlkey.split("#");  
            String bundle = temp[0];  
            String key = temp[1];  
	        String title = null;
	        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
	        HttpSession session = request.getSession();
	        String local=((Locale) session.getAttribute("WW_TRANS_I18N_LOCALE")).toString();
	        
	        title = I18nTestStrutsAdapter.getConstantByName(key,bundle,local);
	        if (title == null && resourceKey != null) {  
	          title = UNDEFINED_KEY + resourceKey + UNDEFINED_KEY;  
	        }  
	        return title;  
	}
	
	public static String getConstantByName(String key,String bundle,String local){
		if(local.equals("en_US") ){
			if(i18nEnMap.size()<=0){
				getProperties(bundle,local);
			}
			return i18nEnMap.get(key);
		}else if(local.equals("zh_CN")){
			if(i18nZHMap.size()<=0){
				getProperties(bundle,local);
			}
			return i18nZHMap.get(key);
		}
		return null;
	}
	
	private static void getProperties(String bundle,String local){
		try{
			ResourceBundle rb=ResourceBundle.getBundle(bundle+"_"+local);
			Enumeration<String> e = rb.getKeys();
			while (e.hasMoreElements()) {
				String key = e.nextElement();
				String value = rb.getString(key);
				if(local.equals("en_US")){
					i18nEnMap.put(key, value);
				}else if(local.equals("zh_CN")){
					i18nZHMap.put(key, value);
				}
			}
		}catch(Exception e){
			log.error("获取配置文件参数异常");
		}
		
//		
//		
//		try{
//			ResourceBundle rb=ResourceBundle.getBundle(bundle+"_"+local);
//			
//			Enumeration<String> e = rb.getKeys();
//			while (e.hasMoreElements()) {
//				if(key.equals(e.nextElement())){
//					return rb.getString(key);
//				}
//				
//				if(local.equals("EN") && i18nEnMap.size()>0){
//					i18nEnMap.put(key, value)
//				}else if(local.equals("ZH")){
//					i18nZHMap
//				}
//			}
//		}catch(Exception e){
//			log.error("获取配置文件参数异常");
//		}
//		return null;
	}
	
	

	public Locale resolveLocale(HttpServletRequest request) {  
		Locale userLocale = null;  
	       HttpSession session = request.getSession();  
	  
	       if (session != null) {  
	           userLocale = (Locale) session.getAttribute(Globals.LOCALE_KEY);  
	        }  
	  
	        if (userLocale == null) {  
	            userLocale = request.getLocale();  
	        }  
	  
	        return userLocale;  
 } 
	
}
