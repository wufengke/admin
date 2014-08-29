<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" /> 
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" /> 
<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT" />
<title>Admin</title>
<jsp:include page="/common/JsCss.jsp" />
<script type="text/javascript">
function delConfirm(isDisabled) {
	var delFlag = false;
	if(isDisabled) {
		delFlag = confirm("确定将该用户置为正常状态吗？");
	} else {
		delFlag = confirm("确定禁止该用户吗？");
	}
	return delFlag;
}

</script>
</head>
<body>
<s:if test="#request.tab == null">
	<s:set value="'default-tab'" name="tab1default" scope="request" />
</s:if>
<div id="body-wrapper">
  <jsp:include page="/menu.jsp" />
  <div id="main-content">
    <h2>用户管理 --> 注册用户管理</h2>
    <p id="page-intro"></p>
    <div class="clear"></div>
    <div class="content-box">
      <div class="content-box-header">
        <ul class="content-box-tabs">
          	<li><a href="#tab1" class="<s:property value="#request.tab1default" />">列表</a></li>
        </ul>
        <div class="clear"></div>
      </div>
      <div class="content-box-content">
       	 <div class="tab-content <s:property value="#request.tab1default" />" id="tab1">
          <c:if test="${havefielderror=='yes'}">
          	<c:if test="${empty tab}">
	          <div class="notification attention png_bg"> <a href="#" class="close"><img src="/resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
	            <div><s:fielderror /></div>
	          </div>
	         </c:if>
          </c:if>
          <display:table id="users" name="pageList" requestURI="onlineUsersList.action"    
    		size="${pageList.fullListSize }" pagesize="${pageList.objectsPerPage }" partialList="true"
    		export="true" decorator="">   
		    <display:column property="id"  title="ID"   style="width:5%"/>   
		    <display:column property="username" titleKey="Language#username"   /> 
		    <display:column property="nickName" titleKey="Language#nickName"   /> 
		    <display:column property="phone" titleKey="Language#phone"   /> 
 			<display:column property="createTime" titleKey="Language#createTime"/> 
		    <display:column titleKey="Language#status">
			    <c:if test="${users.disabled == true}">已停用</c:if>
			    <c:if test="${users.disabled == false}">正常</c:if>
		    </display:column>   
		    <display:column titleKey="Language#accountType">
			    <c:if test="${users.accountType == 0}">学生</c:if>
			    <c:if test="${users.accountType == 1}">老师</c:if>
		    </display:column>   
		    <display:column titleKey="Language#type">
			    <c:if test="${users.type == 0}">邮箱注册</c:if>
			    <c:if test="${users.type == 1}">手机号注册</c:if>
		    </display:column>   
		    <display:column titleKey="Language#applyStatus">
			    <c:if test="${users.applyStatus == 1}">已申请</c:if>
			    <c:if test="${users.applyStatus == 0}">未申请</c:if>
		    </display:column>   
		    <display:column titleKey="Language#operate" media="html" >
     			<a href='resetUserStatus.action?id=${users.id}&page=${pageList.pageNumber }'  onclick="javascript:return delConfirm(${users.disabled});">更改状态</a>
     			<a href='resetUserAccountType.action?id=${users.id}&page=${pageList.pageNumber }'  onclick="">更改用户账户类型</a>
     		</display:column>
		  </display:table> 
        </div>
        <!-- End #tab1 -->
      </div>
    </div>
    <div class="clear"></div>
    <jsp:include page="/foot.jsp" />
  </div>
</div>
</body>
</html>
