<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="navbar" class="navbar"  style="width:100%; text-align:center; padding:0px 0px;">
		<button type="button" class="navbar-toggle navbar-btn collapsed"
			data-toggle="collapse" data-target="#sidebar">
			<span class="fa fa-bars"></span>
		</button>

		<ul class="nav flaty-nav pull-right">

			<li class="user-profile">
			<a data-toggle="dropdown" href="#"
				class="user-menu dropdown-toggle"> 
					<span class="hhh" id="user_info"> Admin </span> <i
					class="fa fa-caret-down"></i>
			</a> 
				<ul class="dropdown-menu dropdown-navbar" id="user_menu">				
					<li>
						 <a href="${pageContext.request.contextPath}/logout">Logout <c:out
								value="${sessionScope['userName']}" />
					</a>
					</li>
					</ul>
					</li>
</ul>	
</div>