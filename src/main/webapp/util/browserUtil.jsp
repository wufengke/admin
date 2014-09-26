<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cyou.core.util.BrowserUtil"  %>
<% 
	String type = BrowserUtil.getBrowserType(request.getHeader("USER-AGENT"));
	out.print("your browser is "+type );
%>