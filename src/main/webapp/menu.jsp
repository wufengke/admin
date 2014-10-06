<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %>
  <div id="sidebar">
    <div id="sidebar-wrapper">
      <h1 id="sidebar-title"><a href="#">Admin</a></h1>
      <a href="javascript:void(0);"><img id="logo" src="/resources/images/logo.png" alt="Simpla Admin logo" /></a>
      <div id="profile-links"> 你好, <sec:authentication property='principal.username' /><br />
        <br />
        <a href="<c:url value='j_spring_security_logout.action'/>" title="退出">退出</a> </div>
		<ul id="main-nav">
	        <!-- Accordion Menu -->
	        <li> 
	        <a href="/login/toIndexPage.action" class="nav-top-item" name="index.jsp">
		         	主页
		         	</a> 
		       <ul>
		          <li><a href="/config/indexPicList.action" name="/config/">首页图片管理</a></li>
		       </ul>
		       
	        </li>
	        <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER,ROLE_ROLE">  
	        <li> <a href="#" class="nav-top-item" name="#">权限管理 </a>
		          <ul> 
		          <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">  
		          	<li><a href="/user/userList.action" name="/user/user">用户管理</a></li>
				  </sec:authorize> 
		          <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_ROLE">  
		          	<li><a href="/role/roleList.action" name="/role/">角色管理</a></li>
				  </sec:authorize> 
		          </ul>
	        </li>
	        </sec:authorize> 
	        <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_COURSE"> 
		        <li> <a href="#" class="nav-top-item" name="#">课程管理 </a>
			          <ul> 
			          	<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_COURSE"> 
			             	<li><a href="/course/courseList.action" name="/course/">课程管理</a></li>
			           	</sec:authorize>
			          </ul>
		        </li>
	       </sec:authorize>  
	        <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_APPLY_TEACH"> 
		        <li> <a href="#" class="nav-top-item" name="#">开课申请管理</a>
			          <ul> 
			          	<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_APPLY_TEACH"> 
			             	<li><a href="/applyteach/applyTeachList.action" name="/applyteach/">开课申请管理</a></li>
			           	</sec:authorize>
			          </ul>
		        </li>
	       </sec:authorize>  
	        <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_ONLINE_USERS"> 
		        <li> <a href="#" class="nav-top-item" name="#">注册用户管理</a>
			          <ul> 
			          	<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_ONLINE_USERS"> 
			             	<li><a href="/user/onlineUsersList.action" name="/user/onlineUsersList">注册用户管理</a></li>
			           	</sec:authorize>
			          </ul>
		        </li>
	       </sec:authorize>  
	        <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER_ORDER"> 
		        <li> <a href="#" class="nav-top-item" name="#">订单管理</a>
			          <ul> 
			          	<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER_ORDER"> 
			             	<li><a href="/userorder/userOrderList.action" name="/userorder/">订单管理</a></li>
			           	</sec:authorize>
			          </ul>
		        </li>
	       </sec:authorize>  
	        <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FEEDBACK"> 
		        <li> <a href="#" class="nav-top-item" name="#">用户反馈管理</a>
			          <ul> 
			          	<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER_ORDER"> 
			             	<li><a href="/feedback/feedbackList.action" name="/feedback/">用户建议</a></li>
			           	</sec:authorize>
			          </ul>
		        </li>
	       </sec:authorize>  
       </ul>
    </div>
  </div>      