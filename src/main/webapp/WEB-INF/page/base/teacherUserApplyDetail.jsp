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
<div id="body-wrapper">
  <jsp:include page="/menu.jsp" />
  <div id="main-content">
    <h2>注册用户管理--> 老师用户申请</h2>
    <p id="page-intro"></p>
    <div class="clear"></div>
    <div class="content-box">
      <div class="content-box-header">
        <ul class="content-box-tabs">
          	<li><a href="#tab1" class="<s:property value="#request.tab1default" />">用户资料详情</a></li>
        </ul>
        <div class="clear"></div>
      </div>
      <div class="content-box-content">
          <div class="tab-content <s:property value="#request.tab1default" />" id="tab1">
          <s:form action="updateTeacherApply" method="get" namespace="/user">
          	<s:hidden name="userId" value="%{#request.user.userId}"></s:hidden>
            <fieldset>
	            <p style="font-weight:bold;">用户资料详情:</p>
		        <table>
		           	<tbody>
		           		<tr>
							<td>真实姓名：</td>
							<td><s:property value="%{#request.user.realName}"/></td>
				      		<td>学校名称：</td>
							<td><s:property value="%{#request.user.schoolName}"/></td>
				      		<td>性别：</td>
							<td><s:property value="%{#request.user.sex}"/></td>
				      		<td>身份证号：</td>
							<td><s:property value="%{#request.user.idCardNo}"/></td>
						</tr>
		           		<tr>
							<td>教学阶段：</td>
							<td><s:property value="%{#request.user.stage}"/></td>
				      		<td>教师职称：</td>
							<td><s:property value="%{#request.user.teacherTitle}"/></td>
				      		<td>教学年限：</td>
							<td><s:property value="%{#request.user.teachYears}"/></td>
				      		<td>手机号码：</td>
							<td><s:property value="%{#request.user.phone}"/></td>
						</tr>
		           		<tr>
		           			<td>省份：</td>
							<td><s:select list="#request.provinceList" headerKey="-1" headerValue="未填写" listKey="provinceId" listValue="provinceName" name="provinceId"></s:select></td>
				      		<td>城市：</td>
							<td><s:select  list="#request.cityList" headerKey="-1" headerValue="未填写" listKey="cityId" listValue="cityName" name="cityId"></s:select></td>
							<td>创建时间：</td>
							<td><s:property value="%{#request.user.createTime}"/></td>
				      		<td>更新时间：</td>
							<td><s:property value="%{#request.user.updateTime}"/></td>
						</tr>
		           		<tr>
				      		<td>个人简介：</td>
							<td colspan="7"><s:property value="%{#request.user.teacherBrief}"/></td>
						</tr>
						<tr>
							<td>审核状态：</td>
							<td colspan="7">
								<s:select list="@com.cyou.common.util.ApplyTeachStatus@values()" listValue="statusDesc" listKey="status" name="status" id="status" value="%{#request.user.status}" cssClass="text-input small-input"></s:select>
							</td>
						</tr>
						<tr>
				      		<td>备注：</td>
							<td colspan="7"><s:textarea name="comments" value="%{#request.user.comments}" cols="20" rows="2">
							</s:textarea></td>
						</tr>
		           	</tbody>
		        </table>
	            <p>
	            <a href='onlineUsersList.action?page=${page}' >返回</a>
	             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	              <input class="button" type="submit" value="确定" />
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