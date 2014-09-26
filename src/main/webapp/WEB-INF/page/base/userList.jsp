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
		delFlag = confirm("确定开启用户吗？");
	} else {
		delFlag = confirm("确定关闭该用户吗？");
	}
	return delFlag;
}

</script>
</head>
<body>
<s:if test="#request.tab == null">
	<s:set value="'default-tab'" name="tab1default" scope="request" />
	<s:set value="''" name="tab2default" scope="request"/>
</s:if>
<s:elseif test="#request.tab == 2">
	<s:set value="''" name="tab1default" scope="request"/>
	<s:set value="'default-tab'" name="tab2default" scope="request"/>
</s:elseif>
<div id="body-wrapper">
  <jsp:include page="/menu.jsp" />
  <div id="main-content">
    <h2>权限管理 --> 用户管理</h2>
    <p id="page-intro"></p>
    <div class="clear"></div>
    <div class="content-box">
      <div class="content-box-header">
        <ul class="content-box-tabs">
          	<li><a href="#tab1" class="<s:property value="#request.tab1default" />">列表</a></li>
            <li><a href="#tab2" class="<s:property value="#request.tab2default" />">添加</a></li>
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
		  <s:form id="conditionform" name="conditionform" action="userList" method="post">
         	<s:text name="username"/>:<s:textfield id="conditionUsername" name="conditionUsername" cssClass="text-input" ></s:textfield>
         	<s:text name="status"/>:<s:select list="@com.cyou.common.util.Status@values()" headerKey="-1" headerValue="%{whole}" listValue="statusDesc" listKey="ordinal()" name="disabled" id="disabled" cssClass="text-input small-input"> </s:select>
         	<input class="button" type="submit" value="查询" />
          </s:form>
          <display:table id="userList" name="pageList" requestURI="userList.action"    
    		size="${pageList.fullListSize }" pagesize="${pageList.objectsPerPage }" partialList="true"
    		export="true" decorator="">   
		    <display:column property="id"  title="ID"   style="width:5%"/>   
		    <display:column property="username" title="用户名"   /> 
		    <display:column property="name" title="姓名"   /> 
		    <display:column property="email" title="邮箱"   /> 
		    <display:column property="tel" title="电话"/> 
		    <display:column property="description" title="账号描述"  />   
		    <display:column title="是否已停权">
			    <c:if test="${userList.disabled == true}">是</c:if>
			    <c:if test="${userList.disabled == false}">否</c:if>
		    </display:column>   
		    <display:column title="操作" media="html" >
     			<a href="#update${userList.id}" rel="modal"  >更新</a>
     			<a href='deleteUser.action?id=${userList.id}&page=${pageList.pageNumber }'  onclick="javascript:return delConfirm(${userList.disabled});">启停权</a>
     			<a href='userRole.action?id=${userList.id}&page=${pageList.pageNumber }' >用户角色</a>
     		</display:column>
		  </display:table> 
        </div>
        <!-- End #tab1 -->

          <div class="tab-content <s:property value="#request.tab2default" />" id="tab2">
          <s:form action="addUser" method="post" namespace="/user">
            <fieldset>
            <p>
              <label>用户名</label>
              <s:textfield id="addusername" name="addusername" cssClass="text-input small-input"/>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="addusername" />
              <br />
              <small>用户名</small> </p>
            <p>
              <label>密码</label>
              <s:password id="addpassword" name="addpassword" cssClass="text-input small-input"/>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="addpassword" />
              <br />
              <small>密码</small> </p>
            <p>
              <label>真实姓名</label>
              <s:textfield id="addname" name="addname" cssClass="text-input small-input"/>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="addname" />
              <br />
              <small>真实姓名</small> </p>
            <p>
              <label>电子邮箱</label>
              <s:textfield id="addemail" name="addemail" cssClass="text-input small-input datepicker"/>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="addemail" />
              <br />
              <small>电子邮箱</small></p>
            <p>
              <label>电话</label>
              <s:textfield id="addtel" name="addtel" cssClass="text-input small-input datepicker"/>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="addtel" />
              <br />
              <small>电话</small> 
              </p>
            <p>
              <label>地址</label>
              <s:textfield id="addaddress" name="addaddress" cssClass="text-input medium-input datepicker"/>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="addaddress" />
              <br />
              <small>地址</small> </p>
            <p>
              <label>用户描述</label>
              <s:textfield cssClass="text-input medium-input datepicker" id="adddescription" name="adddescription" />
              <s:fielderror  cssClass="input-notification error png_bg" fieldName="adddescription" />
              <br />
              <small>用户描述</small> </p>
            <p>
              <input class="button" type="submit" value="添加" />
            </p>
            </fieldset>
            <div class="clear"></div>
          </s:form>
        </div>
      </div>
    </div>
    <div class="clear"></div>
    <jsp:include page="/foot.jsp" />
  </div>
  <!-- End #main-content -->
  <s:iterator value="#request.pageList.list" id="application">		
	<div id="update<s:property value='id'/>" style="display: none">
	  <!-- Messages are shown when a link with these attributes are clicked: href="#update" rel="modal"  -->
	  <s:form action="updateUser" method="post" id="updateform%{#application.id}" namespace="/user">
	    <fieldset>
	    <input type="hidden" id="page" name="page" value="${pageList.pageNumber }"/>
	    <input type="hidden" id="id" name="id" value="<s:property value='id'/>"/>
	    <p>
              <label>用户名</label>
              <s:textfield id="username" name="username" cssClass="text-input medium-input" readonly="true"/>
              <br />
              <small>用户名</small> </p>
            <p>
              <label>密码</label>
              <s:password id="password" name="password" cssClass="text-input medium-input"/>
              <s:fielderror  cssClass="input-notification error png_bg" fieldName="password" />
              <br />
              <small>密码</small> </p>
            <p>
              <label>真实姓名</label>
              <s:textfield id="name" name="name" cssClass="text-input medium-input"/>
              <br />
              <small>真实姓名</small> </p>
            <p>
              <label>电子邮箱</label>
              <s:textfield id="email" name="email" cssClass="text-input medium-input datepicker"/>
              <br />
              <small>电子邮箱</small> </p>
            <p>
              <label>电话</label>
              <s:textfield id="tel" name="tel" cssClass="text-input medium-input datepicker"/>
              <br />
              <small>电话</small> </p>
            <p>
              <label>地址</label>
              <s:textfield id="address" name="address" cssClass="text-input medium-input datepicker"/>
              <br />
              <small>地址</small> </p>
            <p>
              <label>用户描述</label>
              <s:textfield cssClass="text-input medium-input datepicker" id="description" name="description" />
              <br />
              <small>用户描述</small> </p>
	    </fieldset>
	    <fieldset>
	    <input class="button" type="submit" value="更新" />
	    </fieldset>
	  </s:form>
	</div>
	</s:iterator>
</div>
</body>
</html>
