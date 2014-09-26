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
<div id="body-wrapper">
  <jsp:include page="/menu.jsp" />
  <div id="main-content">
    <h2>用户订单管理 --> 订单详情</h2>
    <p id="page-intro"></p>
    <div class="clear"></div>
    <div class="content-box">
      <div class="content-box-header">
        <ul class="content-box-tabs">
          	<li><a href="#tab1" class="default-tab">订单详情</a></li>
        </ul>
        <div class="clear"></div>
      </div>
      <div class="content-box-content">
          <div>
            <fieldset>
	            <p style="font-weight:bold;">订单详情:</p>
		        <table>
		           	<tbody>
		           		<tr>
							<td>订单号：</td>
							<td><s:property value="%{userOrder.orderId}"/></td>
							<td>订单金额：</td>
							<td><s:property value="%{userOrder.amount}"/></td>
				      		<td>创建时间：</td>
							<td><s:property value="%{userOrder.createTime}"/></td>
				      		<td>更新时间：</td>
							<td><s:property value="%{userOrder.updateTime}"/></td>
						</tr>
		           		<tr>
		           			<td>课程ID：</td>
							<td><s:property value="%{userOrder.courseId}"/></td>
							<td>课程标题：</td>
							<td><s:property value="%{userOrder.courseTitle}"/></td>
				      		<td>开课时间表：</td>
							<td><s:property value="%{userOrder.lessionSchedule}"/></td>
				      		<td>课程时间序号：</td>
							<td><s:property value="%{userOrder.lessionRank}"/></td>
						</tr>
		           		<tr>
				      		<td>购买人电话：</td>
							<td><s:property value="%{userOrder.phone}"/></td>
				      		<td>购买人用户ID：</td>
							<td><s:property value="%{userOrder.userId}"/></td>
							<td>购买人姓名：</td>
							<td><s:property value="%{userOrder.realName}"/></td>
							<td>订单状态：</td>
							<td>
								<c:if test="${userOrder.status==0}">
							    	未付款
							    </c:if>
							    <c:if test="${userOrder.status==1}">
							    	已付款
							    </c:if>
							    <c:if test="${userOrder.status==2}">
							    	已取消/以关闭
							    </c:if>
							    <c:if test="${userOrder.status==3}">
							    	已退款
							    </c:if>
						    </td>
						</tr>
		           	</tbody>
		        </table>
	            <p>
	           		 <a href='userOrderList.action?page=${page}&tab=${tab}' >后退</a>
	            </p>
            </fieldset>
            <div class="clear"></div>
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