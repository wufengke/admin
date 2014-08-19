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
<!--根据参数设置tab1和tab2的样式  -->
<s:if test="#request.tab == null">
	<s:set value="'default-tab'" name="tab1default" scope="request" />
	<s:set value="''" name="tab2default" scope="request"/>
</s:if>
<s:elseif test="#request.tab == 2">
	<s:set value="''" name="tab1default" scope="request"/>
	<s:set value="'default-tab'" name="tab2default" scope="request"/>
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
    <h2><s:text name="categorymanager"/> --> <s:text name="categorymanage"/></h2>
    <p id="page-intro"></p>
    
    <!-- End .shortcut-buttons-set -->
    <div class="clear"></div>
    <!-- End .clear -->
    
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <ul class="content-box-tabs">
          	<li><a href="#tab1" class="<s:property value="#request.tab1default" />"><s:text name="list"/></a></li>
	    	<!-- href must be unique and match the id of target div -->
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
          <display:table id="categoryList" name="pageList" requestURI="categoryList.action"    
    		size="${pageList.fullListSize }" pagesize="${pageList.objectsPerPage }" partialList="true"
    		export="true" decorator="">   
		    <display:column property="id"  title="ID"   style="width:5%"/>   
		    <display:column property="categoryType" titleKey="Language#categorytype"   /> 
		    <display:column property="categoryName" titleKey="Language#categoryname"   /> 
			 <display:column titleKey="Language#status"  > 
		    <c:if test="${categoryList.status==0}">
		    <s:text name="disable"></s:text>
		    </c:if>
		    <c:if test="${categoryList.status==1}">
		    <s:text name="enable"></s:text>
		    </c:if>
		    </display:column>
		     
		    <display:column titleKey="Language#operate" media="html" >
     			<a href="#update${categoryList.id}" rel="modal"  ><s:text name="categoryupdate"/></a>
     			<a href='deleteCategory.action?id=${categoryList.id}&page=${pageList.pageNumber }' onclick="delConfirm()"><s:text name="categorydelete"/></a>
     		</display:column>
		  </display:table> 
        </div>
        <!-- End #tab1 -->

          <div class="tab-content <s:property value="#request.tab2default" />" id="tab2">
          <s:form action="addCategory" method="post" namespace="/category">
            <fieldset>
            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
            <p>
              <label><s:text name="categorytype"/></label>
              <s:textfield id="categoryType" name="categoryType" cssClass="text-input small-input"/>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="addcategoryType" />
              <br />
              <small><s:text name="categorytype"/></small> </p>
            <p>
              <label><s:text name="categoryname"/></label>
              <s:textfield id="categoryName" name="categoryName" cssClass="text-input small-input"/>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="addcategoryName" />
              <br />
              <small><s:text name="categoryname"/></small> </p>
        
            <p>
              <input class="button" type="submit" value="<s:text name="add"/>" />
            </p>
            </fieldset>
            <div class="clear"></div>
            <!-- End .clear -->
          </s:form>
        </div>
        <!-- End #tab2 -->
      </div>
      <!-- End .content-box-content -->
    </div>
    <!-- End .content-box -->
   
    <div class="clear"></div>
    <jsp:include page="/foot.jsp" />
  </div>
  <!-- End #main-content -->
  <s:iterator value="#request.pageList.list" id="category">		
	<div id="update<s:property value='id'/>" style="display: none">
	  <!-- Messages are shown when a link with these attributes are clicked: href="#update" rel="modal"  -->
	  <s:form action="updateCategory" method="post" id="updateform%{#category.id}" namespace="/category">
	    <fieldset>
	    <input type="hidden" id="page" name="page" value="${pageList.pageNumber }"/>
	    <input type="hidden" id="id" name="id" value="<s:property value='id'/>"/>
	    <p>
              <label><s:text name="categorytype"/></label>
              <s:textfield id="categoryType" name="categoryType" cssClass="text-input small-input"/>
              <br />
              <small><s:text name="categorytype"/></small> </p>
            <p>
              <label><s:text name="categoryname"/></label>
              <s:textfield id="categoryName" name="categoryName" cssClass="text-input small-input"/>
              <br />
              <small><s:text name="categoryname"/></small> </p>
           <p>
              <label><s:text name="status"/></label>
              <s:select list="@com.cyou.common.util.Status@values()" listValue="statusDesc" listKey="ordinal()" name="status" id="status" value="status" cssClass="text-input small-input">
              </s:select>
              <s:fielderror cssClass="input-notification error png_bg" fieldName="status" />
              <br />
              <small><s:text name="status"/></small> </p>
              
	    </fieldset>
	    <fieldset>
	    <input class="button" type="submit" value="<s:text name="update"/>" />
	    </fieldset>
	  </s:form>
	</div>
	</s:iterator>
  <!-- End #messages -->
  
  
</div>
<script type="text/javascript">
function delConfirm() {
	 if (!confirm("<s:text name='category_deleteconfirm'/>")) {
            window.event.returnValue = false;
        }
}
</script>
</body>
<!-- Download From www.exet.tk-->
</html>