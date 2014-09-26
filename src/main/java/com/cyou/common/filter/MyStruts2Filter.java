package com.cyou.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

public class MyStruts2Filter extends StrutsPrepareAndExecuteFilter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		String url = req.getRequestURI();
		if(url.contains("/ueditor/jsp/")){
			chain.doFilter(request, response);
		}else {
			super.doFilter(request, response, chain);
		}
	}

}
