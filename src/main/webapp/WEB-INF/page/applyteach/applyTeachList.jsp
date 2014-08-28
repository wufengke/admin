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
<!--根据参数设置tab1和tab2的样式  -->
<s:if test="#request.tab == null">
	<s:set value="'default-tab'" name="tab1default" scope="request" />
</s:if>


<div id="body-wrapper">
  <jsp:include page="/menu.jsp" />
  <div id="main-content">
    <h2>开课申请管理 --> 开课申请列表</h2>
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
          <display:table id="applyTeach" name="pageList" requestURI="applyTeachList.action"    
    		size="${pageList.fullListSize }" pagesize="${pageList.objectsPerPage }" partialList="true"
    		export="true" decorator="">   
		    <display:column property="id" title="ID" style="width:5%"/>   
		    <display:column property="realName" titleKey="Language#realName"/> 
		    <display:column property="schoolName" titleKey="Language#schoolName"/> 
		    <display:column property="stage" titleKey="Language#stage"/> 
		    <display:column property="teacherTitle" titleKey="Language#teacherTitle"/> 
		    <display:column property="phone" titleKey="Language#phone"/> 
			<display:column titleKey="Language#status"  > 
			    <c:if test="${applyTeach.status==0}">
			    	待审
			    </c:if>
			    <c:if test="${applyTeach.status==1}">
			    	通过
			    </c:if>
			    <c:if test="${applyTeach.status==-1}">
			    	拒绝
			    </c:if>
		    </display:column>
		    <display:column titleKey="Language#operate" media="html" >
     			<a href="lookApplyTeachDetail?id=${applyTeach.id}&page=${pageList.pageNumber }">查看详情</a>
     			<c:if test="${applyTeach.status==0}">
		    		<a href="resetApplyTeachStatus.action?id=${applyTeach.id}&page=${pageList.pageNumber }&status=-1">拒绝</a>
		    		<a href="resetApplyTeachStatus.action?id=${applyTeach.id}&page=${pageList.pageNumber }&status=1">通过</a>
			    </c:if>
			    <c:if test="${applyTeach.status==1}">
			    		<a href="resetApplyTeachStatus.action?id=${applyTeach.id}&page=${pageList.pageNumber }&status=-1">拒绝</a>
		    		<a href="resetApplyTeach.action?id=${applyTeach.id}&page=${pageList.pageNumber }&status=0">待审</a>
			    
			    </c:if>
			    <c:if test="${applyTeach.status==-1}">
			   	<a href="resetApplyTeachStatus?id=${applyTeach.id}&page=${pageList.pageNumber }&status=0">待审</a>
		    		<a href="resetApplyTeachStatus.action?id=${applyTeach.id}&page=${pageList.pageNumber }&status=1">通过</a>
			    
			    </c:if>
     		</display:column>
		  </display:table> 
        </div>
      </div>
    </div>
   
    <div class="clear"></div>
    <jsp:include page="/foot.jsp" />
  </div>
</div>
<script type="text/javascript">
function delConfirm() {
	 if (!confirm("确定删除吗？")) {
            window.event.returnValue = false;
        }
}
</script>
</body>
</html>