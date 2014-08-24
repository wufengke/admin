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
    <h2><s:text name="applyTeachManage"/> --> <s:text name="applyTeachManage"/></h2>
    <p id="page-intro"></p>
    <div class="clear"></div>
    <div class="content-box">
      <div class="content-box-header">
        <ul class="content-box-tabs">
          	<li><a href="#tab1" class="<s:property value="#request.tab1default" />"><s:text name="detail"/></a></li>
        </ul>
        <div class="clear"></div>
      </div>
      <div class="content-box-content">
          <div class="tab-content <s:property value="#request.tab1default" />" id="tab1">
          <s:form action="updateApplyTeach" method="post" namespace="/applyteach">
          	<s:hidden name="id" value="%{#request.applyTeach.id}"></s:hidden>
            <fieldset>
	            <p style="font-weight:bold;">开课申请详情:</p>
		        <table>
		           	<tbody>
		           		<tr>
							<td><s:text name="realName"/>：</td>
							<td><s:property value="%{#request.applyTeach.realName}"/></td>
				      		<td><s:text name="schoolName"/>：</td>
							<td><s:property value="%{#request.applyTeach.schoolName}"/></td>
				      		<td>性别：</td>
							<td><s:property value="%{#request.applyTeach.gender}"/></td>
				      		<td>年龄：</td>
							<td><s:property value="%{#request.applyTeach.age}"/></td>
						</tr>
		           		<tr>
							<td><s:text name="stage"/>：</td>
							<td><s:property value="%{#request.applyTeach.stage}"/></td>
				      		<td><s:text name="teacherTitle"/>：</td>
							<td><s:property value="%{#request.applyTeach.teacherTitle}"/></td>
				      		<td>教学年限：</td>
							<td><s:property value="%{#request.applyTeach.teachYears}"/></td>
				      		<td><s:text name="phone"/>：</td>
							<td><s:property value="%{#request.applyTeach.phone}"/></td>
						</tr>
		           		<tr>
							<td><s:text name="email"/>：</td>
							<td><s:property value="%{#request.applyTeach.email}"/></td>
				      		<td><s:text name="qq"/>：</td>
							<td><s:property value="%{#request.applyTeach.qq}"/></td>
				      		<td>试讲科目：</td>
							<td><s:property value="%{#request.applyTeach.subject}"/></td>
				      		<td>讲课方式：</td>
							<td><s:property value="%{#request.applyTeach.teachType}"/></td>
						</tr>
		           		<tr>
							<td>试讲课程名：</td>
							<td><s:property value="%{#request.applyTeach.courseName}"/></td>
				      		<td>试讲开始日期：</td>
							<td><s:property value="%{#request.applyTeach.startDate}"/></td>
				      		<td>试讲开始时间：</td>
							<td><s:property value="%{#request.applyTeach.startTime}"/></td>
				      		<td>试讲结束时间：</td>
							<td><s:property value="%{#request.applyTeach.endTime}"/></td>
						</tr>
		           		<tr>
				      		<td><s:text name="courseBrief"/>：</td>
							<td colspan="3"><s:property value="%{#request.applyTeach.courseBrief}"/></td>
				      		<td>个人简介：</td>
							<td colspan="3"><s:property value="%{#request.applyTeach.resume}"/></td>
						</tr>
						<tr>
							<td><s:text name="status"/>：</td>
							<td colspan="7">
								<s:select list="@com.cyou.common.util.ApplyTeachStatus@values()" listValue="statusDesc" listKey="status" name="status" id="status" value="%{#request.applyTeach.status}" cssClass="text-input small-input"></s:select>
							</td>
						</tr>
						<tr>
				      		<td>备注：</td>
							<td colspan="7"><s:textarea name="comments" value="%{#request.applyTeach.comments}" cols="20" rows="2">
							</s:textarea></td>
						</tr>
		           	</tbody>
		        </table>
	            <p>
	            <a href='applyTeachList.action?page=${page}' >后退</a>
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
	 if (!confirm("<s:text name='category_deleteconfirm'/>")) {
            window.event.returnValue = false;
     }
}
</script>
</body>
</html>