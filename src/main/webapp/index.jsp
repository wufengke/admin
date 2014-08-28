<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" /> 
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" /> 
<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT" />
<title>Admin</title>
<jsp:include page="/common/JsCss.jsp" />
</head>
<body>
<div id="body-wrapper">
  <jsp:include page="menu.jsp"></jsp:include>
  <div id="main-content">
    <h2>主页</h2>
    <p id="page-intro">
     <c:if test="${empty havefielderror}">
      <c:if test="${not empty flushSuccess}">
       <div class="notification success png_bg"> <a href="#" class="close"><img src="/resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
         <div>刷新成功</div>
       </div>
      </c:if>
     </c:if>
    </p>
    <div class="clear"></div>
     <jsp:include page="/foot.jsp" />
  </div>
</div>
</body>
</html>
