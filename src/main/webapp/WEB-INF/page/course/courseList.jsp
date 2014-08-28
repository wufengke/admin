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
	return confirm("确定下线吗？");
}
function onlineConfirm() {
	return confirm("确定上线吗？");
}
function preonlineConfirm() {
	return confirm("确定要置为预上线吗？");
}
function preConfirm() {
	return confirm("确定返回上一步吗？");
}
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
    <h2>课程管理 --> 课程列表</h2>
    <p id="page-intro"></p>
    <div class="clear"></div>
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <ul class="content-box-tabs">
          	<li><a href="#tab1" class="<s:property value="#request.tab1default" />">列表</a></li>
            <li><a href="#tab2" class="<s:property value="#request.tab2default" />">添加</a></li>
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
         	<input class="button" type="submit" value="查询" />
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
		    		在线
		    	</c:if>
		    	<c:if test="${courseList.status == 0}">
		    		预上线
		    	</c:if>
		    	<c:if test="${courseList.status == 2}">
		    		下线
		    	</c:if>
		    </display:column>
		    <display:column titleKey="Language#operate" media="html" >
     			<a href="deleteCourse.action?id=${courseList.id}&page=${pageList.pageNumber }" onclick="javascript:return deleteConfirm();" >删除</a>
     			<c:if test="${courseList.status == 1}">
		    			<a href='courseOperation.action?id=${courseList.id}&page=${pageList.pageNumber }&status=2'  onclick="javascript:return offlineConfirm();" >下线</a>
		    			<a href='courseOperation.action?id=${courseList.id}&page=${pageList.pageNumber }&status=0'  onclick="javascript:return preonlineConfirm();" >预上线</a>
		    	</c:if>
		    	<c:if test="${courseList.status == 0}">
			    		<a href='courseOperation.action?id=${courseList.id}&page=${pageList.pageNumber }&status=1'  onclick="javascript:return onlineConfirm();" >上线</a>
		    	</c:if>
		    	<c:if test="${courseList.status == 2}">
			    		<a href='courseOperation.action?id=${courseList.id}&page=${pageList.pageNumber }&status=0'  onclick="javascript:return onlineConfirm();" >预上线</a>
		    	</c:if>
		    	<a href="editCourse.action?courseId=${courseList.courseId}&page=${pageList.pageNumber }">编辑</a>
     		</display:column>
		  </display:table> 
        </div>
          <div class="tab-content <s:property value="#request.tab2default" />" id="tab2">
	          <s:form action="addCourseStep1.action" method="post" namespace="/course" enctype ="multipart/form-data" name="step1" id="step1">
	           	<s:hidden name="courseId" value="%{courseId}"></s:hidden>
	            <fieldset>
	            <p>
	              <label>课程标题</label>
	              <s:textfield id="courseTitle" name="courseTitle" cssClass="text-input large-input" maxlength="255" />
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="courseTitle" />
	              <br />
	              <small>课程标题</small> </p>
	            <p>
	              <label>课程简介</label>
	              <s:textfield id="courseBrief" name="courseBrief" cssClass="text-input large-input"  maxlength="128" />
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="courseBrief" />
	              <br />
	              <small>课程简介</small> </p>
	            <p>
	              <label>课程描述</label>
	              <s:textfield id="courseDesc" name="courseDesc" cssClass="text-input medium-input"  maxlength="32" />
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="courseDesc" />
	              <br />
	              <small>适合的年级（例如：适合三-六年级学生）</small> 
	            </p>
	            <p>
	              <label>所属老师</label>
	              <s:select list="#request.teacherList" headerKey=" " headerValue="%{choose}" listValue="realName" listKey="userId" name="userId" id="userId" cssClass="text-input small-input">
	              </s:select>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="userId" />
	              <br />
	              <small>所属老师</small> 
	            </p>
	            
	            <p>
	              <label>开课时间</label>
	                <s:textfield name="courseTime" cssClass="text-input small-input"  readonly="true" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2008-03-08 11:30:00',maxDate:'2100-03-10 20:59:30'})" />
	              <br />
	              <small>开课时间一期的开课时间</small>
	            </p>  
	            <p>
	              <label>开课时间描述</label>
	                <s:textfield name="courseTimeDesc" cssClass="text-input small-input" maxlength="32"/>
	              <br />
	              <small>用户在首页显示课程时间（例如：8月15日）</small>
	            </p>  
	            
	            <p>
	              <label>课程总人数</label>
	              <s:textfield id="totalCount" name="totalCount" cssClass="text-input small-input"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="totalCount" />
	              <br />
	              <small>课程总人数</small> 
	            </p>
	            <p>
	              <label>已报名人数</label>
	              <s:textfield id="count" name="count" cssClass="text-input small-input"  maxlength="32"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="count" />
	              <br />
	              <small>已报名人数</small> 
	            </p>
	            <p>
	              <label>课次</label>
	              <s:textfield id="lessonTimes" name="lessonTimes" cssClass="text-input small-input"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="lessonTimes" />
	              <br />
	              <small>课次</small> 
	            </p>
	            <p>
	              <label>排序</label>
	              <s:textfield id="rank" name="rank" cssClass="text-input small-input"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="rank" />
	              <br />
	              <small>课程在首页的排序位置（数字，如：1,2,3....）</small> 
	            </p>
	            <p>
	              <label>课程原价</label>
	              <s:textfield id="originalPrice" name="originalPrice" cssClass="text-input small-input"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="originalPrice" />
	              <br />
	              <small>课程原价</small> 
	            </p>
	            <p>
	              <label>课程现价</label>
	              <s:textfield id="price" name="price" cssClass="text-input small-input"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="price" />
	              <br />
	              <small>课程现价</small> 
	            </p>
	            <p>
	              <label>是否参与首页轮播</label>
	              <s:select list="@com.cyou.common.util.IsRoll@values()" listValue="statusDesc" listKey="ordinal()" name="isRoll" id="isRoll" value="%{isRoll}" cssClass="text-input small-input">
	              </s:select>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="isRoll" />
	              <br />
	              <small>是否参与首页轮播</small>
	            </p>           
	            <p>
	              <s:fielderror />  
  
                 <label>首页的轮播图</label> <s:file name ="myFile" label ="大图（216）"/>  
  
                  <label>中图（首页的课程图）</label><s:file name ="myFile" label ="中图（172）"/>  
  
                  <label>小图（订单里的图）</label><s:file name ="myFile" label ="小图（50）"/>  
                  
	              <br />
	              <small>图片</small>
	            </p> 
	            <p>
	              <label>轮播图两边的颜色</label>
	              <s:textfield id="bigImageSideColor" name="bigImageSideColor" cssClass="text-input small-input"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="bigImageSideColor" />
	              <br />
	              <small>首页轮播图不是全部左右铺满屏幕的，两边需要填充颜色代码（如：#ffffff代表白色）</small> 
	            </p>          
	            <p>
	              <input class="button" type="submit" value="下一步" />
	            </p>
	            </fieldset>
	            <div class="clear"></div>
	          </s:form>
	          <!-- step2 form -->
	          <s:form action="addCourseStep2" method="post" namespace="/course" name="step2" id="step2" cssStyle="display:none;">
	            <s:hidden name="courseId" value="%{courseId}"></s:hidden>
	            <fieldset>
	            <p>
	              <label>课时</label>
	              <s:textfield id="lessionTimes" name="lessionTimes" cssClass="text-input small-input" />
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="lessionTimes" />
	              <br />
	              <small>课时</small> </p>
	            <p>
	              <label>评论数</label>
	              <s:textfield id="comments" name="comments" cssClass="text-input small-input"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="comments" />
	              <br />
	              <small>评论数（整数，如1,2,3...）</small> 
	            </p>
	            <p>
	              <label>课程时间表</label>
	              <s:textarea id="lessionSchedule" name="lessionSchedule" cssClass="text-input large-input" cols="200" rows="10"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="lessionSchedule" />
	              <br />
	              <small>课程时间表（JSON格式的时间表，如：）</small> 
	            </p>
	            <p>
	              <label>课程简介</label>
	              <s:textfield id="courseDetailBrief" name="courseDetailBrief" cssClass="text-input large-input" maxlength="255"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="courseDetailBrief" />
	              <br />
	              <small>课程详情页简介</small>
	            </p>  
	            
	            <p>
	              <label>适合学生</label>
	              <s:textfield id="courseDetailDesc" name="courseDetailDesc" cssClass="text-input large-input" maxlength="255"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="courseDetailDesc" />
	              <br />
	              <small>适合学生</small> 
	            </p>
	            <p>
	              <label>课程大纲</label>
	              <s:textarea id="courseDetailOutline" name="courseDetailOutline" cssClass="text-input large-input" cols="200" rows="10"/>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="courseDetailOutline" />
	              <br />
	              <small>课程大纲</small> 
	            </p>
	            <p>
	              <label>课程摘要</label>
	              <script id="editor"  name="courseDetailSummary" type="text/plain">
	              	<s:property value="%{courseDetailSummary}"/>
	              </script>
	              <s:fielderror cssClass="input-notification error png_bg" fieldName="courseDetailSummary" />
	              <br />
	              <small>课程摘要</small> 
	            </p>
	            <p>
	             <a href='viewAddCourseStep1.action?courseId=${requestScope.courseId}'  onclick="javascript:return preConfirm()" >返回上一步</a>
	             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	              <input class="button" type="submit" value="下一步" />
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
