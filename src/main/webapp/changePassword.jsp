<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<%@ page import="org.springframework.security.ui.AbstractProcessingFilter" %>
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.AuthenticationException" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Admin</title>
<jsp:include page="/common/JsCss.jsp" />
</head>
<body id="login" onload="document.f.username.focus();">
<div id="login-wrapper" class="png_bg">
  <div id="login-top">
    <h1>Admin</h1>
    <!-- Logo (221px width) -->
    <a href="#"><img id="logo" src="/resources/images/logo.png" alt="Simpla Admin logo" /></a> </div>
  <!-- End #logn-top -->
  <div id="login-content">
    <s:form name="f" action="changePassword.action" method="post" >
	    <c:if test="${not empty havefielderror}">
	      <div id="errormessage" class="notification error png_bg">
	        <div><s:fielderror /></div>
	      </div>
	    </c:if>
	    <c:if test="${empty havefielderror}">
	    	<c:if test="${not empty changepasswordok}">
		      <div id="errormessage" class="notification success png_bg">
		        <div>更新成功</div>
		      </div>
			</c:if>
	    </c:if>
      <p>
        <label>用户名：</label>
        <s:textfield id="username" name="username" cssClass="text-input"/>
      </p>
      <div class="clear"></div>
      <p>
        <label>原始密码：</label>
        <s:password id="oldPassword" name="oldPassword" cssClass="text-input"/>
      </p>
      <div class="clear"></div>
      <p>
        <label>新密码：</label>
        <s:password id="newPassword" name="newPassword" cssClass="text-input"/>
      </p>
      <div class="clear"></div>
      <p>
        <label>密码确认：</label>
        <s:password id="newRePassword" name="newRePassword" cssClass="text-input"/>
      </p>
      <div class="clear"></div>
      <div class="clear"></div>
      <p>
      	<input class="button" type="submit" value="更新" />
      	<input onclick="javascript:window.location.href='${basePath }/test/toLoginPage.action'" class="button" type="button" value="后退" style="margin-right:10px;margin-left:10px"/>
      </p>
    </s:form>
  </div>
</div>
</body>
</html>
