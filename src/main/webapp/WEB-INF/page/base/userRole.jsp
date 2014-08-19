<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" /> 
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" /> 
<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT" />
<title>Simpla Admin</title>
<jsp:include page="/common/JsCss.jsp" />
</head>
<body>
<div id="body-wrapper">
  
  <jsp:include page="/menu.jsp" />
  <div id="main-content">
    <noscript>
    <div class="notification error png_bg">
      <div> Javascript is disabled or is not supported by your browser. Please <a href="http://browsehappy.com/" title="Upgrade to a better browser">upgrade</a> your browser or <a href="http://www.google.com/support/bin/answer.py?answer=23852" title="Enable Javascript in your browser">enable</a> Javascript to navigate the interface properly.
        Download From <a href="http://www.exet.tk">exet.tk</a></div>
    </div>
    </noscript>
    <h2><s:text name="authoritymanage"/> --> <s:text name="usermanage"/> --><s:text name="userrole"/></h2>
    <p id="page-intro"></p>
    <div class="clear"></div>
    <div class="content-box">
      <div class="content-box-header">
        <ul class="content-box-tabs">
	          <li><a href="#tab1" class="default-tab"><s:text name="authoritylist"/></a></li>
        </ul>
        <div class="clear"></div>
      </div>
      <div class="content-box-content">
        <div class="tab-content default-tab" id="tab1">
          <c:if test="${not empty havefielderror}">
	          <div class="notification attention png_bg"> <a href="#" class="close"><img src="/resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
	            <div><s:fielderror /></div>
	          </div>
          </c:if>
          <c:if test="${empty havefielderror}">
	          <c:if test="${not empty addUserRoleSuccess}">
		          <div class="notification success png_bg"> <a href="#" class="close"><img src="/resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
		            <div><s:text name="updatesuccess"/></div>
		          </div>
	          </c:if>
          </c:if>
          <s:form action="addUserRole" method="post" namespace="/user">
          	<input type="hidden" id="userid" name="userid" value="${user.id }"/>
            <fieldset>
            <p>
              <label><s:text name="username"/>：${user.username}</label><label><s:text name="realname"/>：${user.name}</label>
            </p>
           	<table>
            	<s:iterator value="#request.user.roles" id="haverole">	
            		<tr>
            			<td style="width:5%"><input type="checkbox" name="addrole" value="<s:property value='id'/>" checked="checked" /></td>
            			<td align="left"><s:property value='name'/></td>
            			<td align="left"><s:property value='description'/></td>
            		</tr>
            	</s:iterator>
            	<s:iterator value="#request.roles" id="norole">	
            		<tr>
            			<td style="width:5%"><input type="checkbox" name="addrole" value="<s:property value='id' />" /></td>
            			<td align="left"><s:property value='name'/></td>
            			<td align="left"><s:property value='description'/></td>
            		</tr>
            	</s:iterator>
           	</table>
            <p>
              <input onclick="javascript:window.location.href='${basePath }user/userList.action?page=${page}'" class="button" type="button" value="<s:text name="back"/>"/>
              <input class="button" type="submit" value="<s:text name="update"/>" />
            </p>
            </fieldset>
            <div class="clear"></div>
            <!-- End .clear -->
          </s:form>
        </div>
      </div>
    </div>
    <div class="clear"></div>
    <jsp:include page="/foot.jsp" />
  </div>
</div>
</body>
</html>
