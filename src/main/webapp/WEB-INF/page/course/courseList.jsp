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
<script type="text/javascript" src="/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="/ueditor/ueditor.all.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>
 <script type="text/javascript" src="/resources/scripts/My97DatePicker/WdatePicker.js"></script> 
<jsp:include page="/common/JsCss.jsp" />

</head>
<body>
<script type="text/javascript">
function offlineConfirm() {
	return confirm("<s:text name='offlineConfirm'/>");
}
function onlineConfirm() {
	return confirm("<s:text name='onlineConfirm'/>");
}
function preonlineConfirm() {
	return confirm("<s:text name='preonlineConfirm'/>");
}
function preConfirm() {
	return confirm("<s:text name='preConfirm'/>");
}
function deleteConfirm() {
	return confirm("<s:text name='deleteConfirm'/>");
}
</script>
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
  <jsp:include page="/menu.jsp" />
  <div id="main-content">
    <h2><s:text name="coursemanage"/> --> <s:text name="coursemanage"/></h2>
    <p id="page-intro"></p>
    <div class="clear"></div>
    <div class="content-box">
      <!-- Start Content Box -->
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
          <s:text name="choose" id="choose"/>
          <input type="hidden" id="step" value="${requestScope.step }"/>
		  <s:form id="conditionform" name="conditionform" action="courseList" method="post">
         	<s:text name="status"/>:<s:select list="@com.cyou.common.util.Status@values()" headerKey="-1" headerValue="%{whole}" listValue="statusDesc" listKey="ordinal()" name="statusCondition" id="statusCondition" cssClass="text-input small-input"> </s:select>
         	<input class="button" type="submit" value="<s:text name="querybutton"/>" />
          </s:form>
          <display:table id="courseList" name="pageList" requestURI="courseList.action"    
    		size="${pageList.fullListSize }" pagesize="${pageList.objectsPerPage }" partialList="true"
    		export="true" decorator="">   
		    <display:column property="id"  title="ID" style="width:5%"/>   
		    <display:column property="courseTitle" titleKey="Language#courseTitle" /> 
		    <display:column property="userRealName" titleKey="Language#userRealName" /> 
		    <display:column property="courseTime" titleKey="Language#courseTime"/> 
		    <display:column property="totalCount" titleKey="Language#totalCount" /> 
		    <display:column property="count" titleKey="Language#count" /> 
		    <display:column titleKey="Language#status">
		    	<c:if test="${courseList.status == 1}">
		    		<s:text name="online"></s:text>
		    	</c:if>
		    	<c:if test="${courseList.status == 0}">
		    		<s:text name="preonline"></s:text>
		    	</c:if>
		    	<c:if test="${courseList.status == 2}">
		    		<s:text name="offline"></s:text>
		    	</c:if>
		    </display:column>
		    <display:column titleKey="Language#operate" media="html" >
     			<a href="deleteCourse.action?id=${courseList.id}&page=${pageList.pageNumber }" onclick="javascript:return deleteConfirm();" ><s:text name="delete"/></a>
     			<c:if test="${courseList.status == 1}">
		    			<a href='courseOperation.action?id=${courseList.id}&page=${pageList.pageNumber }&status=2'  onclick="javascript:return offlineConfirm();" ><s:text name="offline"/></a>
		    			<a href='courseOperation.action?id=${courseList.id}&page=${pageList.pageNumber }&status=0'  onclick="javascript:return preonlineConfirm();" ><s:text name="preonline"/></a>
		    	</c:if>
		    	<c:if test="${courseList.status == 0}">
			    		<a href='courseOperation.action?id=${courseList.id}&page=${pageList.pageNumber }&status=1'  onclick="javascript:return onlineConfirm();" ><s:text name="toonline"/></a>
		    	</c:if>
		    	<c:if test="${courseList.status == 2}">
			    		<a href='courseOperation.action?id=${courseList.id}&page=${pageList.pageNumber }&status=0'  onclick="javascript:return onlineConfirm();" ><s:text name="preonline"/></a>
		    	</c:if>
		    	<a href="editCourse.action?id=${courseList.id}&page=${pageList.pageNumber }"><s:text name="edit"/></a>
     		</display:column>
		  </display:table> 
        </div>
          <div class="tab-content <s:property value="#request.tab2default" />" id="tab2">
	          <s:form action="addCourseStep1.action" method="post" namespace="/course" enctype ="multipart/form-data" name="step1" id="step1">
	           	<s:hidden name="courseId" value="%{courseId}"></s:hidden>
	            <fieldset>
	            <p>
	              <label><s:text name="courseTitle"/></label>
	              <s:textfield id="courseTitle" name="courseTitle" cssClass="text-input large-input" maxlength="255" />
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="courseTitle" />
	              <br />
	              <small><s:text name="courseTitleDesc"/></small> </p>
	            <p>
	              <label><s:text name="courseBrief"/></label>
	              <s:textfield id="courseBrief" name="courseBrief" cssClass="text-input large-input"  maxlength="128" />
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="courseBrief" />
	              <br />
	              <small><s:text name="courseBriefDesc"/></small> </p>
	            <p>
	              <label><s:text name="courseDesc"/></label>
	              <s:textfield id="courseDesc" name="courseDesc" cssClass="text-input medium-input"  maxlength="32" />
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="courseDesc" />
	              <br />
	              <small><s:text name="courseDesc"/></small> 
	            </p>
	            <p>
	              <label><s:text name="teacherName"/></label>
	              <s:select list="#request.teacherList" headerKey=" " headerValue="%{choose}" listValue="realName" listKey="userId" name="userId" id="userId" cssClass="text-input small-input">
	              </s:select>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="userId" />
	              <br />
	              <small><s:text name="teacherName"/></small> 
	            </p>
	            
	            <p>
	              <label><s:text name="courseTime"/></label>
	                <s:textfield name="courseTime" cssClass="text-input small-input"  readonly="true" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2008-03-08 11:30:00',maxDate:'2100-03-10 20:59:30'})" />
	              <br />
	              <small><s:text name="courseTime"/></small>
	            </p>  
	            <p>
	              <label><s:text name="courseTimeDesc"/></label>
	                <s:textfield name="courseTimeDesc" cssClass="text-input small-input" maxlength="32"/>
	              <br />
	              <small><s:text name="courseTimeDesc"/></small>
	            </p>  
	            
	            <p>
	              <label><s:text name="totalCount"/></label>
	              <s:textfield id="totalCount" name="totalCount" cssClass="text-input small-input"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="totalCount" />
	              <br />
	              <small><s:text name="totalCount"/></small> 
	            </p>
	            <p>
	              <label><s:text name="count"/></label>
	              <s:textfield id="count" name="count" cssClass="text-input small-input"  maxlength="32"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="count" />
	              <br />
	              <small><s:text name="count"/></small> 
	            </p>
	            <p>
	              <label><s:text name="lessonTimes"/></label>
	              <s:textfield id="lessonTimes" name="lessonTimes" cssClass="text-input small-input"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="lessonTimes" />
	              <br />
	              <small><s:text name="lessonTimes"/></small> 
	            </p>
	            <p>
	              <label><s:text name="rank"/></label>
	              <s:textfield id="rank" name="rank" cssClass="text-input small-input"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="rank" />
	              <br />
	              <small><s:text name="rankDesc"/></small> 
	            </p>
	            <p>
	              <label><s:text name="originalPrice"/></label>
	              <s:textfield id="originalPrice" name="originalPrice" cssClass="text-input small-input"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="originalPrice" />
	              <br />
	              <small><s:text name="originalPrice"/></small> 
	            </p>
	            <p>
	              <label><s:text name="price"/></label>
	              <s:textfield id="price" name="price" cssClass="text-input small-input"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="price" />
	              <br />
	              <small><s:text name="price"/></small> 
	            </p>
	            <p>
	              <label><s:text name="isRoll"/></label>
	              <s:select list="@com.cyou.common.util.IsRoll@values()" listValue="statusDesc" listKey="ordinal()" name="isRoll" id="isRoll" value="%{isRoll}" cssClass="text-input small-input">
	              </s:select>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="isRoll" />
	              <br />
	              <small><s:text name="isRoll"/></small>
	            </p>           
	            <p>
	              <label><s:text name="images"/></label>
	              
	              <s:fielderror />  
  
                  <s:file name ="myFile" label ="大图（216）"/>  
  
                  <s:file name ="myFile" label ="中图（172）"/>  
  
                  <s:file name ="myFile" label ="小图（50）"/>  
                  
	              <br />
	              <small><s:text name="images"/></small>
	            </p> 
	            <p>
	              <label><s:text name="bigImageSideColor"/></label>
	              <s:textfield id="bigImageSideColor" name="bigImageSideColor" cssClass="text-input small-input"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="bigImageSideColor" />
	              <br />
	              <small><s:text name="bigImageSideColor"/></small> 
	            </p>          
	            <p>
	              <input class="button" type="submit" value="<s:text name="next"/>" />
	            </p>
	            </fieldset>
	            <div class="clear"></div>
	          </s:form>
	          <!-- step2 form -->
	          <s:form action="addCourseStep2" method="post" namespace="/course" name="step2" id="step2" cssStyle="display:none;">
	            <s:hidden name="courseId" value="%{courseId}"></s:hidden>
	            <fieldset>
	            <p>
	              <label><s:text name="lessionTimes"/></label>
	              <s:textfield id="lessionTimes" name="lessionTimes" cssClass="text-input small-input" />
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="lessionTimes" />
	              <br />
	              <small><s:text name="lessionTimes"/></small> </p>
	            <p>
	              <label><s:text name="comments"/></label>
	              <s:textfield id="comments" name="comments" cssClass="text-input small-input"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="comments" />
	              <br />
	              <small><s:text name="comments"/></small> 
	            </p>
	            <p>
	              <label><s:text name="lessionSchedule"/></label>
	              <s:textarea id="lessionSchedule" name="lessionSchedule" cssClass="text-input large-input" cols="200" rows="10"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="lessionSchedule" />
	              <br />
	              <small><s:text name="lessionScheduleDesc"/></small> 
	            </p>
	            <p>
	              <label><s:text name="courseDetailBrief"/></label>
	              <s:textfield id="courseDetailBrief" name="courseDetailBrief" cssClass="text-input large-input" maxlength="255"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="courseDetailBrief" />
	              <br />
	              <small><s:text name="courseDetailBrief"/></small>
	            </p>  
	            
	            <p>
	              <label><s:text name="courseDetailDesc"/></label>
	              <s:textfield id="courseDetailDesc" name="courseDetailDesc" cssClass="text-input large-input" maxlength="255"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="courseDetailDesc" />
	              <br />
	              <small><s:text name="courseDetailDesc"/></small> 
	            </p>
	            <p>
	              <label><s:text name="courseDetailOutline"/></label>
	              <s:textarea id="courseDetailOutline" name="courseDetailOutline" cssClass="text-input large-input" cols="200" rows="10"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="courseDetailOutline" />
	              <br />
	              <small><s:text name="courseDetailOutlineDesc"/></small> 
	            </p>
	            <p>
	              <label><s:text name="courseDetailSummary"/></label>
	              <script id="editor"  name="courseDetailSummary" type="text/plain">
	              	<s:property value="%{courseDetailSummary}"/>
	              </script>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="courseDetailSummary" />
	              <br />
	              <small><s:text name="courseDetailSummary"/></small> 
	            </p>
	            <p>
	             <a href='addCourseStep1.action?courseId=${requestScope.courseId}&step=step2'  onclick="javascript:return preConfirm()" ><s:text name="pre"/></a>
	             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	              <input class="button" type="submit" value="<s:text name="next"/>" />
	            </p>
	            </fieldset>
	            <div class="clear"></div>
	          </s:form>
	          <div style="display:none;" id="step3" >
	          	<p>
	          		<label>编辑完成！</label>
	          	</p>
	          	<p>
	          		<a href="/course/courseList.action">返回列表</a>
	          	</p>		
	          </div>
        </div>
      </div>
    </div>
   <script type="text/javascript">
   		$(function(){
			var step = $("#step").val();
			if("step1" == step){
				$("#step1").css("display","block");
				$("#step2").css("display","none");
				$("#step3").css("display","none");
			}else if("step2" == step){
				$("#step1").css("display","none");
				$("#step2").css("display","block");
				$("#step3").css("display","none");
			}else if("step3" == step){
				$("#step1").css("display","none");
				$("#step2").css("display","none");
				$("#step3").css("display","block");
			}else{
				$("#step1").css("display","block");
				$("#step2").css("display","none");
				$("#step3").css("display","none");
			}
   	   	})
   </script>
   <script type="text/javascript">
	   //实例化编辑器
	   //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	   var ue = UE.getEditor('editor',{
	        initialFrameWidth: 1000,
	        initialFrameHeight: 600,
	    });
	    ue.ready(function(){
			ue.setContent('<s:property value="courseDetailSummary"/>');
		});
   </script>
    <div class="clear"></div>
    <jsp:include page="/foot.jsp" />
  </div>
</div>
</body>
</html>
