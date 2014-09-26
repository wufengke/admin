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
function resetConfirm() {
	return  confirm("<s:text name='application_reset_confirm'/>");
}
function deleteConfirm() {
	return confirm("<s:text name='application_delete_confirm'/>");
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
<s:elseif test="#request.tab == 3">
	<s:set value="''" name="tab1default" scope="request"/>
	<s:set value="'default-tab'" name="tab3default" scope="request"/>
</s:elseif>

<div id="body-wrapper">
  
  <!-- 左侧部分 -->  
  <jsp:include page="/menu.jsp" />
	
      
  <div id="main-content">
    <!-- Main Content Section with everything -->
    <noscript>
    <!-- Show a notification if the user has disabled javascript -->
    <div class="notification error png_bg">
      <div> Javascript is disabled or is not supported by your browser. Please <a href="http://browsehappy.com/" title="Upgrade to a better browser">upgrade</a> your browser or <a href="http://www.google.com/support/bin/answer.py?answer=23852" title="Enable Javascript in your browser">enable</a> Javascript to navigate the interface properly.
        Download From <a href="http://www.exet.tk">exet.tk</a></div>
    </div>
    </noscript>
    
    <!-- Page Head -->
    <h2><s:text name="merchantmanager"/> --> <s:text name="appmanage"/></h2>
    <p id="page-intro"></p>
    
    <!-- End .shortcut-buttons-set -->
    <div class="clear"></div>
    <!-- End .clear -->
    
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <ul class="content-box-tabs">
	    	<li><a href="#tab1" class="<s:property value="#request.tab1default" />"><s:text name="list"/></a></li>
	    	 <li><a href="#tab2" class="<s:property value="#request.tab2default" />"><s:text name="add"/></a></li>
        </ul>
        <div class="clear"></div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content">

       	<div class="tab-content <s:property value="#request.tab1default" />" id="tab1">
        <!-- This is the target div. id must match the href of this div's tab -->
          <c:if test="${havefielderror=='yes'}">
          	<c:if test="${empty tab}">
	          <div class="notification attention png_bg"> <a href="#" class="close"><img src="/resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
	            <div><s:fielderror /></div>
	          </div>
	         </c:if>
          </c:if>
          <display:table id="applicationList" name="pageList" requestURI="applicationList.action"    
    		size="${pageList.fullListSize }" pagesize="${pageList.objectsPerPage }" partialList="true"
    		export="true" decorator="">   
    		<display:column property="id" title="ID"   /> 
    		<display:column property="appId" titleKey="Language#application_appid"/>   
		    <display:column property="appName" titleKey="Language#application_appname" />   
		    <display:column property="merchantId" titleKey="Language#merchantId" />   
		    <display:column titleKey="Language#application_apptype"> 
		    <c:if test="${applicationList.appType==0}">
		    <s:text name="application_type_offline"></s:text>
		    </c:if>
		    <c:if test="${applicationList.appType==1}">
		    <s:text name="application_type_online"></s:text>
		    </c:if>
		    </display:column> 
		    <display:column titleKey="Language#application_status" > 
		    <c:if test="${applicationList.status==0}">
		    <s:text name="application_status_live"></s:text>
		    </c:if>
		    <c:if test="${applicationList.status==1}">
		    <s:text name="application_status_test"></s:text>
		    </c:if>
		    <c:if test="${applicationList.status==-1}">
		    <s:text name="application_status_disabled"></s:text>
		    </c:if>
		    </display:column>
		    <display:column titleKey="Language#operate" media="html" >
     			<a href="#update${applicationList.id}" rel="modal"  ><s:text name="update"/></a>&nbsp;&nbsp;
     			<a href='resetAppSecret.action?id=${applicationList.id}&page=${pageList.pageNumber }'  onclick="return resetConfirm();"><s:text name="application_reset_appsecret"/></a>
     			<a href='deleteApp.action?id=${applicationList.id}&page=${pageList.pageNumber }'  onclick="return deleteConfirm();"><s:text name="delete"/></a>
     		</display:column>
		  </display:table> 
        </div>
        <!-- End #tab1 -->
		<s:text name="choose" id="choose"></s:text>
         <div class="tab-content <s:property value="#request.tab2default" />" id="tab2">
          <s:fielderror cssClass="input-notification error png_bg" fieldName="message" />
          <s:form action="addApplication" method="post" namespace="/application">
            <fieldset>
            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
            <p>
              <label><s:text name="application_appname"/></label>
              <s:textfield id="name" name="addappname" maxLength="32"  cssClass="text-input small-input"/>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="addappname" />
              <br />
              <small><s:text name="application_appname"/></small> </p>
            <p>
              <label><s:text name="application_apptype"/></label> 
               <input type="radio" name="addapptype" value="0" checked="checked"/><s:text name="application_type_offline"/>
              <input type="radio" name="addapptype" value="1"/><s:text name="application_type_online"/>
              <s:fielderror  cssClass="input-notification error png_bg" fieldName="addapptype" />
              <br />
              <small><s:text name="application_apptype"/></small>  </p> 
            <p>
              <label><s:text name="application_status"/></label> 
               <input type="radio" name="addstatus" value="0" checked="checked"/><s:text name="application_status_live"/>
              <input type="radio" name="addstatus" value="1"/><s:text name="application_status_test"/>
               <input type="radio" name="addstatus" value="-1"/><s:text name="application_status_disabled"/>
              <s:fielderror  cssClass="input-notification error png_bg" fieldName="addstatus" />
              <br />
              <small><s:text name="application_status"/></small>  </p> 
            <p>
              <label><s:text name="cp_user_type"/></label>
              <s:select list="@com.cyou.common.util.CpUserType@values()" listValue="statusDesc" listKey="ordinal()" name="cpUserType" id="cpUserType" cssClass="text-input small-input">
              </s:select>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="cp_user_type" />
              <br />
              <small><s:text name="cp_user_type"/></small>
            </p>  
            <p>
              <label><s:text name="application_merchant"/></label> 
              <s:select list="#request.merchantList" headerKey=" " headerValue="%{choose}" listKey="userId" listValue="userName" id="merchantUserId" 
              name="merchantUserId" value="merchantUserId" cssClass="text-input small-input"></s:select>
              <s:fielderror  cssClass="input-notification error png_bg" fieldName="application_merchant_error" />
              <br />
              <small><s:text name="application_merchant"/></small>  </p> 

            <p>
              <label><s:text name="application_country"/></label> 
              <s:select list="#request.countryList"  headerKey="-1" headerValue="%{choose}" listKey="id" listValue="name" id="countryId" 
              name="countryId" value="countryId" cssClass="text-input small-input" ></s:select>
              <s:fielderror  cssClass="input-notification error png_bg" fieldName="application_country_error" />
              <br />
              <small><s:text name="application_country"/></small>  </p> 
              <p>
              <label><s:text name="application_notifyurl"/></label>
              <s:textfield id="type" name="addnotifyurl" maxLength="1024"  cssClass="text-input medium-input"/>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="addnotifyurl" />
              <br />
              <small><s:text name="application_notifyurl"/></small> </p>
            <p>
              <label><s:text name="application_returnurl"/></label>
              <s:textfield id="returnUrl" name="returnUrl" maxLength="1024"  cssClass="text-input medium-input"/>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="addreturnurl" />
              <br />
              <small><s:text name="application_returnurl"/></small> </p>
            <p>
              <label><s:text name="application_failedurl"/></label>
              <s:textfield id="failedUrl" name="failedUrl" maxLength="1024"  cssClass="text-input medium-input"/>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="addfailedurl" />
              <br />
              <small><s:text name="application_failedurl"/></small> </p>
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
	  <s:form action="updateApplication" method="post" id="updateform%{#application.id}" namespace="/application">
	    <fieldset>
	    <input type="hidden" id="page" name="page" value="${pageList.pageNumber }"/>
	    <input type="hidden" id="id" name="id" value="<s:property value='id'/>"/>
	    <p>
	      <label><s:text name="application_appid"/></label>
              <s:textfield id="appId" name="appId" cssClass="text-input medium-input datepicker"  readonly="true"/>
          <br />
          <small><s:text name="application_appiddesc"/></small> </p>
	    <p>
	      <label><s:text name="application_appname"/></label>
              <s:textfield id="appName" name="appName" cssClass="text-input medium-input datepicker" />
              <s:fielderror  cssClass="input-notification error png_bg" fieldName="application_appnameerror" />
          <br />
          <small><s:text name="application_appname"/></small> </p>
        <p>
            <label><s:text name="application_appsecret"/></label>
              <s:textfield id="appSecret" name="appSecret" cssClass="text-input medium-input datepicker" readonly="true"/>
              <br />
              <small><s:text name="application_appsecretdesc"/></small> </p>
  		<p>
            <label><s:text name="application_merchant"/></label>
              <s:textfield id="merchantId" name="merchantId" cssClass="text-input medium-input datepicker" readonly="true"/>
              <br />
              <small><s:text name="application_merchant"/></small> </p>
        <p>
          <label><s:text name="application_apptype"/></label>
	     <input type="radio" name="appType" value="0" <s:if test="#application.appType == 0"> checked = "checked"</s:if>/>
	     <s:text name="application_type_offline"/>
         <input type="radio" name="appType" value="1" <s:if test="#application.appType == 1"> checked = "checked"</s:if>/>
         <s:text name="application_type_online"/>
            <br />
          <small><s:text name="application_apptype"/></small> </p>
         
        <p>
          <label><s:text name="application_status"/></label>
	     <input type="radio" name="status" value="0" <s:if test="#application.status == 0"> checked = "checked"</s:if>/>
	     <s:text name="application_status_live"/>
         <input type="radio" name="status" value="1" <s:if test="#application.status== 1"> checked = "checked"</s:if>/>
         <s:text name="application_status_test"/>
         <input type="radio" name="status" value="-1" <s:if test="#application.status== -1"> checked = "checked"</s:if>/>
         <s:text name="application_status_disabled"/>
            <br />
          <small><s:text name="application_status"/></small> </p>
          
        <p>
           <label><s:text name="application_country"/></label> 
           <s:select list="#request.countryList" headerKey="-1" headerValue="%{choose}" listValue="name" listKey="id" id="countryId" 
           name="countryId" cssClass="text-input medium-input"></s:select>
           <s:fielderror  cssClass="input-notification error png_bg" fieldName="application_country_error" />
           <br />
           <small><s:text name="application_country"/></small>  </p> 
           <p>
              <label><s:text name="cp_user_type"/></label>
              <s:select list="@com.cyou.common.util.CpUserType@values()" listValue="statusDesc" listKey="ordinal()" name="cpUserType" id="cpUserType" cssClass="text-input medium-input">
              </s:select>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="cp_user_type" />
              <br />
              <small><s:text name="cp_user_type"/></small>
            </p>  
          <p>
	      <label><s:text name="application_notifyurl"/></label>
          <s:textfield id="notifyUrl" name="notifyUrl" maxLength="1024" cssClass="text-input large-input datepicker" />
          <s:fielderror  cssClass="input-notification error png_bg" fieldName="application_notifyurl" />
          <br />
          <small><s:text name="application_notifyurl"/></small> </p>
        <p>
	      <label><s:text name="application_returnurl"/></label>
          <s:textfield id="returnUrl" name="returnUrl" maxLength="1024" cssClass="text-input large-input datepicker" />
          <s:fielderror  cssClass="input-notification error png_bg" fieldName="application_returnurl" />
          <br />
          <small><s:text name="application_returnurl"/></small> </p>
       <p>
	      <label><s:text name="application_failedurl"/></label>
          <s:textfield id="failedUrl" name="failedUrl" maxLength="1024" cssClass="text-input large-input datepicker" />
          <s:fielderror  cssClass="input-notification error png_bg" fieldName="application_failedurl" />
          <br />
          <small><s:text name="application_failedurl"/></small> </p>
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
