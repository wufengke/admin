package com.cyou.common.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class LanguageFilter implements Filter  
{
 private static Logger logger = Logger.getLogger(LanguageFilter.class);
 public void destroy() {
 }

 public void doFilter(ServletRequest arg0, ServletResponse arg1,
   FilterChain arg2) throws IOException, ServletException {
  
	  HttpServletRequest request = (HttpServletRequest)arg0;
	  HttpServletResponse response=(HttpServletResponse)arg1;
	  request.setCharacterEncoding("utf-8");
	  HttpSession session = request.getSession();
	  
	  String request_locale = request.getParameter("request_locale");
	  logger.debug("---------request_locale:"+request_locale);
	  Locale locale = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
	  if(locale == null)
	  {
	   Cookie[] allcookies = request.getCookies();
	   if(allcookies!=null)
	   {
	    for(int i=0;i<allcookies.length;i++)
	    {
	     if(allcookies[i].getName().equalsIgnoreCase("CYOU_LANG"))
	     {
	      if(allcookies[i].getValue().equalsIgnoreCase("zh_CN"))
	       locale = Locale.CHINA;
	      else if(allcookies[i].getValue().equalsIgnoreCase("zh_TW"))
	       locale = Locale.TAIWAN;
	      else if(allcookies[i].getValue().equalsIgnoreCase("en_US"))
	       locale = Locale.US;
	     }
	    }
	   }
	  }
	  if(locale==null)
	  {
	   locale = request.getLocale();
	  }
	  logger.debug(request.getLocale());
	  
	  session.setAttribute("WW_TRANS_I18N_LOCALE", locale);
	  logger.debug("-----------language:"+locale);
     
     arg2.doFilter(request, response);
  
 }

 public void init(FilterConfig arg0) throws ServletException {
  // TODO Auto-generated method stub
  
 }
}
