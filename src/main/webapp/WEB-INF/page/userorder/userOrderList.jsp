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
<s:if test="#request.tab == null || #request.tab == 4">
	<s:set value="'default-tab'" name="tab4default" scope="request" />
</s:if>
<s:if test="#request.tab == 0">
	<s:set value="'default-tab'" name="tab0default" scope="request" />
</s:if>
<s:if test="#request.tab == 1">
	<s:set value="'default-tab'" name="tab1default" scope="request" />
</s:if>
<s:if test="#request.tab == 2">
	<s:set value="'default-tab'" name="tab2default" scope="request" />
</s:if>
<s:if test="#request.tab == 3">
	<s:set value="'default-tab'" name="tab3default" scope="request" />
</s:if>

<div id="body-wrapper">
  <jsp:include page="/menu.jsp" />
  <div id="main-content">
    <h2>用户订单管理 --> 用户订单列表</h2>
    <p id="page-intro"></p>
    <div class="clear"></div>
    <div class="content-box">
      <div class="content-box-header">
      <s:form action="userOrderList.action" namespace="userorder" method="post" >
      		<s:hidden id="status" name="status" value="%{status}"></s:hidden>
	        <ul class="content-box-tabs">
	          	<li><a href="4" onclick="check(this);"  class="<s:property value="#request.tab4default" />">全部</a></li>
	          	<li><a href="0" onclick="check(this);" class="<s:property value="#request.tab0default" />">未付款</a></li>
	          	<li><a href="1" onclick="check(this);" class="<s:property value="#request.tab1default" />">已付款</a></li>
	          	<li><a href="2" onclick="check(this);" class="<s:property value="#request.tab2default" />">已取消/已关闭</a></li>
	          	<li><a href="3" onclick="check(this);" class="<s:property value="#request.tab3default" />">退款</a></li>
	        </ul>
        </s:form>
        <div class="clear"></div>
      </div>
      <div class="content-box-content">
       	 <div>
          <c:if test="${havefielderror=='yes'}">
          	<c:if test="${empty tab}">
	          <div class="notification attention png_bg"> <a href="#" class="close"><img src="/resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
	            <div><s:fielderror /></div>
	          </div>
	         </c:if>
          </c:if>
          <s:hidden value="%{status}" id="status"></s:hidden>
          <display:table id="order" name="pageList" requestURI="userOrderList.action"    
    		size="${pageList.fullListSize }" pagesize="${pageList.objectsPerPage }" partialList="true"
    		export="true" decorator="">   
		    <display:column property="id" title="ID" style="width:5%"/>   
		    <display:column property="orderId" title="订单号"/>
		    <display:column property="realName" title="真实姓名"/> 
		    <display:column property="courseTitle" title="课程标题"/> 
		    <display:column property="amount" title="订单金额"/> 
		    <display:column property="phone" title="订单电话"/> 
		    <display:column property="createTime" title="创建时间"/> 
		    <display:column property="updateTime" title="更新时间"/> 
		    <display:column title="订单状态"  > 
			    <c:if test="${order.status==0}">
			    	未付款
			    </c:if>
			    <c:if test="${order.status==1}">
			    	已付款
			    </c:if>
			    <c:if test="${order.status==2}">
			    	已取消/已关闭
			    </c:if>
			    <c:if test="${order.status==3}">
			    	已退款
			    </c:if>
		    </display:column>
		    <display:column title="操作" media="html" >
     			<a href="lookUserOrderDetail?orderId=${order.orderId}&page=${pageList.pageNumber }&tab=${requestScope.tab}">查看订单详情</a>
     		</display:column>
		  </display:table> 
        </div>
      </div>
    </div>
   
    <div class="clear"></div>
    <jsp:include page="/foot.jsp" />
    <script type="text/javascript">
    	function check(self){
			var status = $(self).attr("href");
			$("#status").val(status);
			$("form").submit();
        }
    </script>
  </div>
</div>
</body>
</html>