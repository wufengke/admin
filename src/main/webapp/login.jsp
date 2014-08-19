<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Changyou Admin</title>
<jsp:include page="/common/JsCss.jsp" />
</head>
<body id="login" onload="document.f.j_username.focus();">
<div id="login-wrapper" class="png_bg">
  <div id="login-top">
    <h1>Simpla Admin</h1>
    <!-- Logo (221px width) -->
    <a href="#"><img id="logo" src="resources/images/logo.png" alt="Simpla Admin logo" /></a> 
    <div ><a href="/language/chooseLanguage.action?request_locale=en_US">English </a>|<a href="/language/chooseLanguage.action?request_locale=zh_CN"> 简体中文</a></div></div>
  <!-- End #logn-top -->
  <div id="login-content">
    <form name="f" action="<c:url value='j_spring_security_check.action'/>" method="post" >
	    <c:if test="${not empty param.login_error}">
	      <div id="errormessage" class="notification error png_bg">
	        <div> <s:text name="wrong"/> </div>
	      </div>
	    </c:if>
      
      <p>
        <label><s:text name="username"/>：</label>
        <input class="text-input" type="text" name='j_username'/>
      </p>
      <div class="clear"></div>
      <p>
        <label><s:text name="password"/>：</label>
        <input class="text-input" type="password" name='j_password' />
      </p>
      <div class="clear"></div>
      <div class="clear"></div>
      <p>
      	<input class="button" type="submit" value="<s:text name="login"/>" />
      	<input onclick="javascript:window.location.href='${basePath }password/toChangePasswordPage.action'" class="button" type="button" value="<s:text name="updatePassword"/>" style="margin-right:10px;margin-left:10px"/>
      </p>
    </form>
  </div>
</div>
</body>
</html>
