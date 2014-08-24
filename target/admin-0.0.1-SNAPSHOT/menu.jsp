<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %>
  <div id="sidebar">
    <div id="sidebar-wrapper">
      <h1 id="sidebar-title"><a href="#">Admin</a></h1>
      <a href="http://www.changyou.com"><img id="logo" src="/resources/images/logo.png" alt="Simpla Admin logo" /></a>
      <div id="profile-links"> <s:text name="hello"/>, <sec:authentication property='principal.username' /><br />
        <br />
        <a href="<c:url value='j_spring_security_logout.action'/>" title="<s:text name="logout"/></a>"><s:text name="logout"/></a> </div>
		<ul id="main-nav">
	        <!-- Accordion Menu -->
	        <li> <a href="/login/toIndexPage.action" class="nav-top-item no-submenu" name="index.jsp">
		         	<s:text name="homepage"/> 
		         	</a> 
	        </li>
	        <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_ROLE,ROLE_ROLE">  
	        <li> <a href="#" class="nav-top-item" name="#">
		          <!-- Add the class "current" to current menu item -->
		          <s:text name="authoritymanage"/> </a>
		          <ul> 
		          <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">  
		          	<li><a href="/user/userList.action" name="/user/userList"><s:text name="usermanage"/></a></li>
				  </sec:authorize> 
		          <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_ROLE">  
		          	<li><a href="/role/roleList.action" name="/role/"><s:text name="rolemanage"/></a></li>
				  </sec:authorize> 
		          </ul>
	        </li>
	        </sec:authorize> 
	        <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_COURSE"> 
		        <li> <a href="#" class="nav-top-item" name="#">
			          <s:text name="coursemanage"/> </a>
			          <ul> 
			          	<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_COURSE"> 
			             	<li><a href="/course/courseList.action" name="/course/"><s:text name="coursemanage"/></a></li>
			           	</sec:authorize>
			          </ul>
		        </li>
	       </sec:authorize>  
	        <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_APPLY_TEACH"> 
		        <li> <a href="#" class="nav-top-item" name="#"><s:text name="applyTeachManage"/> </a>
			          <ul> 
			          	<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_APPLY_TEACH"> 
			             	<li><a href="/applyteach/applyTeachList.action" name="/applyteach/"><s:text name="applyTeachManage"/></a></li>
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
       </ul>
    </div>
  </div>      