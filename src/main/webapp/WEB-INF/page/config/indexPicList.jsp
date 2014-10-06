<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" /> 
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" /> 
<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT" />
<title>Admin</title>
<jsp:include page="/common/JsCss.jsp" />
</head>
<body>
<script type="text/javascript">
function deleteConfirm() {
	return confirm("确定删除吗？");
}
</script>
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
        <!-- Main Content Section with everything -->
	    <noscript>
	    <!-- Show a notification if the user has disabled javascript -->
	    <div class="notification error png_bg">
	      <div> Javascript is disabled or is not supported by your browser. Please <a href="http://browsehappy.com/" title="Upgrade to a better browser">upgrade</a> your browser or <a href="http://www.google.com/support/bin/answer.py?answer=23852" title="Enable Javascript in your browser">enable</a> Javascript to navigate the interface properly.
	        Download From <a href="http://www.exet.tk">exet.tk</a></div>
	    </div>
	    </noscript>
	    <!-- Page Head -->
        <h2>首页图片管理 --> 图片管理</h2>
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
	          <display:table id="indexPicList" name="pageList" requestURI="indexPicList.action"    
	    		size="${pageList.fullListSize }" pagesize="${pageList.objectsPerPage }" partialList="true"
	    		export="true" decorator="">   
			    <display:column property="id"  title="ID"   style="width:5%"/>   
			    <display:column property="title" title="标题"   /> 
			    <display:column property="description" title="简介"   /> 
			    <display:column property="rank" title="顺序"   /> 
				<display:column title="可用"  > 
			    <c:if test="${indexPicList.status==0}">
			    <s:text name="disable"></s:text>
			    </c:if>
			    <c:if test="${indexPicList.status==1}">
			    <s:text name="enable"></s:text>
			    </c:if>
			    </display:column>
			     
			    <display:column title="" media="html" >
			        <a href='resetIndexPicState.action?id=${indexPicList.id}&page=${pageList.pageNumber }'  >更改可用状态</a>
	     			<a href="#update${indexPicList.id}" rel="modal"  >编辑</a>
	     			<a href='deleteIndexPic.action?id=${indexPicList.id}&page=${pageList.pageNumber }' onclick="javascript:return delConfirm();">删除</a>
	     		</display:column>
			  </display:table> 
	        </div>
	        <!-- End #tab1 -->
	
	          <div class="tab-content <s:property value="#request.tab2default" />" id="tab2">
	          <s:form action="addIndexPic.action" enctype ="multipart/form-data" method="post" namespace="/config">
	            <fieldset>
	            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
	            <p>
	              <label>标题</label>
	              <s:textfield id="title" name="title" cssClass="text-input small-input"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="addtitle" />
	              <br />
	              <small>图片标题</small>
	              </p>
	            <p>
	              <label>描述信息</label>
	              <s:textfield id="description" name="description" cssClass="text-input small-input"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="adddescription" />
	              <br />
	              <small>图片描述信息</small> </p>
	            <p>
	              <label>图片</label>
	              <s:file id="file" name="file" cssClass="text-input small-input"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="addfile" />
	              <br />
	              <small>上传要显示图片，推荐大小为1920*440"</small> </p> 
	            <p>
	              <label>顺序</label>
	              <s:textfield id="rank" name="rank" cssClass="text-input small-input"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="addrank" />
	              <br />
	              <small>图片显示顺序(由小到大显示)</small> </p>
	        	<p>
	              <label>是否可用</label>
	              <s:radio name="status" list="%{#{'1':'可用','0':'不可用'}}" value="1" ></s:radio> 
	              <br />
	              </p>
	            <p>
	              <input class="button" type="submit" value="添加" />
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
	  <s:iterator value="#request.pageList.list" id="indexPic">		
		<div id="update<s:property value='id'/>" style="display: none">
		  <!-- Messages are shown when a link with these attributes are clicked: href="#update" rel="modal"  -->
		  <s:form action="updateIndexPic.action" method="post" enctype ="multipart/form-data" id="updateform%{#category.id}" namespace="/config">
		    <fieldset>
		        <input type="hidden" id="page" name="page" value="${pageList.pageNumber }"/>
	            <input type="hidden" id="id" name="id" value="<s:property value='id'/>"/>
	            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
	            <p>
	              <label>标题</label>
	              <s:textfield id="title" name="title" cssClass="text-input small-input"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="addtitle" />
	              <br />
	              <small>图片标题</small>
	              </p>
	            <p>
	              <label>描述信息</label>
	              <s:textfield id="description" name="description" cssClass="text-input small-input"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="adddescription" />
	              <br />
	              <small>图片描述信息</small> </p>
	            <p>
	              <label>图片</label>
	              <s:file id="file" name="file" cssClass="text-input small-input"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="addfile" />
	              <br />
	              <small>上传要显示图片，推荐大小为1920*440"</small> </p> 
	            <p>
	              <label>顺序</label>
	              <s:textfield id="rank" name="rank" cssClass="text-input small-input"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="addrank" />
	              <br />
	              <small>图片显示顺序(由小到大显示)</small> </p>
	        	<p>
	              <label>是否可用</label>
	              <s:radio name="status" list="%{#{'1':'可用','0':'不可用'}}" value="1" ></s:radio> 
	              <br />
	              </p>
	            <p>
	              <input class="button" type="submit" value="更新" />
	            </p>
	            </fieldset>
		  </s:form>
		</div>
		</s:iterator>
	  <!-- End #messages -->
</div>
</body>
</html>