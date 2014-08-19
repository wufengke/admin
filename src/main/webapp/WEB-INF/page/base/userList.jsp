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
<script type="text/javascript">
function delConfirm(isDisabled) {
	var delFlag = false;
	if(isDisabled) {
		delFlag = confirm("<s:text name='user_enabledconfirm'/>");
	} else {
		delFlag = confirm("<s:text name='user_disabledconfirm'/>");
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
    <h2><s:text name="authoritymanage"/> --> <s:text name="usermanage"/></h2>
    <p id="page-intro"></p>
    <div class="clear"></div>
    <div class="content-box">
      <div class="content-box-header">
        <ul class="content-box-tabs">
          	<li><a href="#tab1" class="<s:property value="#request.tab1default" />"><s:text name="list"/></a></li>
            <li><a href="#tab2" class="<s:property value="#request.tab2default" />"><s:text name="add"/></a></li>
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
          <s:text name="whole" id="whole"/>
		  <s:form id="conditionform" name="conditionform" action="userList" method="post">
         	<s:text name="username"/>:<s:textfield id="conditionUsername" name="conditionUsername" cssClass="text-input" ></s:textfield>
         	<s:text name="status"/>:<s:select list="@com.cyou.common.util.Status@values()" headerKey="-1" headerValue="%{whole}" listValue="statusDesc" listKey="ordinal()" name="disabled" id="disabled" cssClass="text-input small-input"> </s:select>
         	<input class="button" type="submit" value="<s:text name="querybutton"/>" />
          </s:form>
          <display:table id="userList" name="pageList" requestURI="userList.action"    
    		size="${pageList.fullListSize }" pagesize="${pageList.objectsPerPage }" partialList="true"
    		export="true" decorator="">   
		    <display:column property="id"  title="ID"   style="width:5%"/>   
		    <display:column property="username" titleKey="Language#username"   /> 
		    <display:column property="name" titleKey="Language#realname"   /> 
		    <display:column property="email" titleKey="Language#email"   /> 
		    <display:column property="tel" titleKey="Language#tel"  sortable="false"/> 
		    <display:column property="description" titleKey="Language#userdescription"  />   
		    <display:column titleKey="Language#userdisabled">
			    <c:if test="${userList.disabled == true}"><s:text name="yes"/></c:if>
			    <c:if test="${userList.disabled == false}"><s:text name="no"/></c:if>
		    </display:column>   
		    <display:column titleKey="Language#operate" media="html" >
     			<a href="#update${userList.id}" rel="modal"  ><s:text name="update"/></a>
     			<a href='deleteUser.action?id=${userList.id}&page=${pageList.pageNumber }'  onclick="javascript:return delConfirm(${userList.disabled});"><s:text name="useraccountstatus"/></a>
     			<a href='userRole.action?id=${userList.id}&page=${pageList.pageNumber }' ><s:text name="userrole"/></a>
     		</display:column>
		  </display:table> 
        </div>
        <!-- End #tab1 -->

          <div class="tab-content <s:property value="#request.tab2default" />" id="tab2">
          <s:form action="addUser" method="post" namespace="/user">
            <fieldset>
            <p>
              <label><s:text name="username"/></label>
              <s:textfield id="addusername" name="addusername" cssClass="text-input small-input"/>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="addusername" />
              <br />
              <small><s:text name="username"/></small> </p>
            <p>
              <label><s:text name="password"/></label>
              <s:password id="addpassword" name="addpassword" cssClass="text-input small-input"/>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="addpassword" />
              <br />
              <small><s:text name="password"/></small> </p>
            <p>
              <label><s:text name="realname"/></label>
              <s:textfield id="addname" name="addname" cssClass="text-input small-input"/>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="addname" />
              <br />
              <small><s:text name="password"/></small> </p>
            <p>
              <label><s:text name="email"/></label>
              <s:textfield id="addemail" name="addemail" cssClass="text-input small-input datepicker"/>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="addemail" />
              <br />
              <small><s:text name="email"/></small></p>
            <p>
              <label><s:text name="tel"/></label>
              <s:textfield id="addtel" name="addtel" cssClass="text-input small-input datepicker"/>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="addtel" />
              <br />
              <small><s:text name="tel"/></small> 
              </p>
            <p>
              <label><s:text name="address"/></label>
              <s:textfield id="addaddress" name="addaddress" cssClass="text-input medium-input datepicker"/>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="addaddress" />
              <br />
              <small><s:text name="address"/></small> </p>
            <p>
              <label><s:text name="userdescription"/></label>
              <s:textfield cssClass="text-input medium-input datepicker" id="adddescription" name="adddescription" />
              <s:fielderror  cssClass="input-notification error png_bg" fieldName="adddescription" />
              <br />
              <small><s:text name="userdescription"/></small> </p>
            <p>
              <input class="button" type="submit" value="<s:text name="add"/>" />
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
              <label><s:text name="username"/></label>
              <s:textfield id="username" name="username" cssClass="text-input medium-input" readonly="true"/>
              <br />
              <small><s:text name="user_usernamedesc"/></small> </p>
            <p>
              <label><s:text name="password"/></label>
              <s:password id="password" name="password" cssClass="text-input medium-input"/>
              <s:fielderror  cssClass="input-notification error png_bg" fieldName="password" />
              <br />
              <small><s:text name="password"/></small> </p>
            <p>
              <label><s:text name="realname"/></label>
              <s:textfield id="name" name="name" cssClass="text-input medium-input"/>
              <br />
              <small><s:text name="realname"/></small> </p>
            <p>
              <label><s:text name="email"/></label>
              <s:textfield id="email" name="email" cssClass="text-input medium-input datepicker"/>
              <br />
              <small><s:text name="email"/></small> </p>
            <p>
              <label><s:text name="tel"/></label>
              <s:textfield id="tel" name="tel" cssClass="text-input medium-input datepicker"/>
              <br />
              <small><s:text name="tel"/></small> </p>
            <p>
              <label><s:text name="address"/></label>
              <s:textfield id="address" name="address" cssClass="text-input medium-input datepicker"/>
              <br />
              <small><s:text name="address"/></small> </p>
            <p>
              <label><s:text name="userdescription"/></label>
              <s:textfield cssClass="text-input medium-input datepicker" id="description" name="description" />
              <br />
              <small><s:text name="userdescription"/></small> </p>
	    </fieldset>
	    <fieldset>
	    <input class="button" type="submit" value="<s:text name="update"/>" />
	    </fieldset>
	  </s:form>
	</div>
	</s:iterator>
  
</div>
</body>
</html>
