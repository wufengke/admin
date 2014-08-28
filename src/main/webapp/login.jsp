<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Admin</title>
<jsp:include page="/common/JsCss.jsp" />
</head>
<body id="login" onload="document.f.j_username.focus();">
<div id="login-wrapper" class="png_bg">
  <div id="login-top">
    <h1>Admin</h1>
    <a href="#"><img id="logo" src="resources/images/logo.png" alt="Simpla Admin logo" /></a> 
  </div>
  <div id="login-content">
    <form name="f" action="<c:url value='j_spring_security_check.action'/>" method="post" >
	    <c:if test="${not empty param.login_error}">
	      <div id="errormessage" class="notification error png_bg">
	        <div>用户名或者密码错误 </div>
	      </div>
	    </c:if>
      
      <p>
        <label>用户名：</label>
        <input class="text-input" type="text" name='j_username'/>
      </p>
      <div class="clear"></div>
      <p>
        <label>密 码：</label>
        <input class="text-input" type="password" name='j_password' />
      </p>
      <div class="clear"></div>
      <div class="clear"></div>
      <p>
      	<input class="button" type="submit" value="登录" />
      	<input onclick="javascript:window.location.href='${basePath }password/toChangePasswordPage.action'" class="button" type="button" value="修改密码" style="margin-right:10px;margin-left:10px"/>
      </p>
    </form>
  </div>
</div>
</body>
</html>
