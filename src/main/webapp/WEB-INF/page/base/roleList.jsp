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
    <h2>权限管理 --> 角色管理</h2>
    <p id="page-intro"></p>
    <div class="clear"></div>
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <ul class="content-box-tabs">
          <li><a href="#tab1" class="<s:property value="#request.tab1default" />">列表</a></li>
          <li><a href="#tab2" class="<s:property value="#request.tab2default" />">添加</a></li>
        </ul>
        <div class="clear"></div>
      </div>
      <div class="content-box-content">
       	<div class="tab-content <s:property value="#request.tab1default" />"  id="tab1">
          <c:if test="${havefielderror=='yes'}">
          	<c:if test="${empty tab}">
	          <div class="notification attention png_bg"> <a href="#" class="close"><img src="/resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
	            <div><s:fielderror /></div>
	          </div>
	         </c:if>
          </c:if>
          <display:table id="roleList" name="pageList" requestURI="roleList.action"    
    		size="${pageList.fullListSize }" pagesize="${pageList.objectsPerPage }" partialList="true"
    		export="true" decorator="">   
		    <display:column property="id"  title="ID"   style="width:5%"/>   
		    <display:column property="name" titleKey="Language#role_name"   /> 
		    <display:column property="description" titleKey="Language#role_description"  />    
		    <display:column titleKey="Language#operate" media="html" >
     			<a href="#update${roleList.id}" rel="modal"  >更新</a>
     			<a href="/role/deleteRole.action?id=${roleList.id}" onclick="return delconfirm();">删除</a>
     		</display:column>
		  </display:table> 
        </div>
      <div class="tab-content <s:property value="#request.tab2default" />" id="tab2">
          <s:form action="addRole" method="post" namespace="/role">
            <fieldset>
            <p>
              <label>角色名</label>
              <s:textfield id="addname" name="addname" cssClass="text-input small-input"/>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="addname" />
              <br />
              <small>角色名</small> </p>
        
            <p>
              <label>角色描述</label>
              <s:textfield id="adddescription" name="adddescription" cssClass="text-input small-input"/>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="adddescription" />
              <br />
              <small>角色描述</small> </p>
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
  <s:iterator value="#request.pageList.list" id="application">		
	<div id="update<s:property value='id'/>" style="display: none">
	  <!-- Messages are shown when a link with these attributes are clicked: href="#update" rel="modal"  -->
	  <s:form action="updateRole" method="post" id="updateform%{#application.id}" namespace="/role">
	    <fieldset>
	    <input type="hidden" id="page" name="page" value="${pageList.pageNumber }"/>
	    <input type="hidden" id="id" name="id" value="<s:property value='id'/>"/>
            <p>
              <label>角色名</label>
               <s:textfield maxLength="200" cssClass="text-input medium-input datepicker" id="name" name="name" readonly="true"/>
              <br />
               <small>角色名</small> </p>
            <p>
              <label>角色描述</label>
              <s:textfield maxLength="200" cssClass="text-input medium-input datepicker" id="description" name="description" />
              <br />
              <small>角色描述</small> </p>
	    </fieldset>
	    <fieldset>
	    <input class="button" type="submit" value="更新" />
	    </fieldset>
	  </s:form>
	</div>
	</s:iterator>
</div>
<script type="text/javascript">
	function delconfirm(){
		return confirm("确定删除该角色吗？");
	}
</script>
</body>
</html>
