<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.ats.adminpanel.commons.Constants"%>
<%@ page import="com.ats.adminpanel.commons.Commons"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Dashboard - Admin</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<!--base css styles-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/font-awesome/css/font-awesome.min.css">

<!--page specific css styles-->

<!--flaty css styles-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flaty.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flaty-responsive.css">

<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/img/favicon.png">
</head>
<body>

 
<c:url var="setSubModId" value="/setSubModId" />

	<!-- BEGIN Navbar -->

	<div id="navbar" class="navbar"
		style="width: 100%; text-align: center; padding: 15px 0px;">
		<button type="button" class="navbar-toggle navbar-btn collapsed"
			data-toggle="collapse" data-target="#sidebar">
			<span class="fa fa-bars"></span>
		</button>

		<!-- BEGIN Navbar Buttons -->
		<ul class="nav flaty-nav pull-right">

			<!-- BEGIN Button User -->
			<li class="user-profile"><a data-toggle="dropdown" href="#"
				class="user-menu dropdown-toggle"> <i class="fa fa-caret-down"></i>
			</a> <!-- BEGIN User Dropdown -->
				<ul class="dropdown-menu dropdown-navbar" id="user_menu">

					</a></li>

		</ul>
		<!-- BEGIN User Dropdown -->
		</li>
		<!-- END Button User -->
		</ul>
		<!-- END Navbar Buttons -->
	</div>
	<!-- END Navbar -->

	<!-- BEGIN Container -->
	<div class="container" id="main-container">
		<!-- BEGIN Sidebar -->
		<div id="sidebar" class="navbar-collapse collapse">

			<!-- BEGIN Navlist -->
			<a class="navbar-brand" href="#"
				style="width: 100%; text-align: center; padding: 15px 0px;"> <img
				src="${pageContext.request.contextPath}/resources/img/monginislogo.png"
				style="position: relative;" alt="">
			</a>
			<div style="clear: both;"></div>
			<ul class="nav nav-list">
				<li class="active"><a href="home"> <i
						class="fa fa-dashboard"></i> <span>Dashboard</span>
				</a></li>

				<c:forEach items="${sessionScope.newModuleList}" var="allModuleList"
									varStatus="count">

				<c:choose>
					<c:when test="${allModuleList.moduleId==sessionScope.sessionModuleId}">
						<li class="active">
					</c:when>

					<c:otherwise>
						<li>
					</c:otherwise>
				</c:choose>



				<a href="#" class="dropdown-toggle"> <i class="fa fa-list"></i>
					<span><c:out value="${allModuleList.moduleName}" /></span> <b class="arrow fa fa-angle-right"></b>
				</a>
				<!-- BEGIN Submenu -->
				<ul class="submenu">
				
				<c:forEach items="${allModuleList.subModuleJsonList}" var="allSubModuleList">
				

					<c:choose>
						<c:when test="${allSubModuleList.subModuleId==sessionScope.sessionSubModuleId}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a onclick="selectSubMod(${allSubModuleList.subModuleId},${allSubModuleList.moduleId})" href="${pageContext.request.contextPath}/<c:out value="${allSubModuleList.subModuleMapping}" />"><c:out value="${allSubModuleList.subModulName}" /></a>
					</li>


					</c:forEach>


			</ul>
				<!-- END Submenu -->
				</li>
				</c:forEach>

 

				 

				<c:choose>
					<c:when test="${Constants.mainAct==11}">
						<li class="active">
					</c:when>

					<c:otherwise>
						<li>
					</c:otherwise>
				</c:choose>
				<a href="#" class="dropdown-toggle"> <i class="fa fa-list"></i>
					<span>Logout</span> <b class="arrow fa fa-angle-right"></b>
				</a>
				<!-- BEGIN Submenu -->
				<ul class="submenu">
					<c:choose>
						<c:when test="${Constants.subAct==111}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="resoucres/index.php/dashboard/logout">Logout</a>
					</li>



				</ul>
				<!-- END Submenu -->
				</li>

			</ul>
			<!-- END Navlist -->

			<!-- BEGIN Sidebar Collapse Button -->
			<div id="sidebar-collapse" class="visible-lg">
				<i class="fa fa-angle-double-left"></i>
			</div>
			<!-- END Sidebar Collapse Button -->
		</div>
		<!-- END Sidebar -->


	</div>
	<!-- END Container -->

	<!--basic scripts-->

	<%-- <script
		src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="${pageContext.request.contextPath}/resources/assets/jquery/jquery-2.0.3.min.js"><\/script>')
	</script>
	<script src="${pageContext.request.contextPath}/resources/assets/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/jquery-cookie/jquery.cookie.js"></script>

	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.resize.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.pie.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.stack.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.crosshair.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.tooltip.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/sparkline/jquery.sparkline.min.js"></script>

	<script src="${pageContext.request.contextPath}/resources/js/flaty.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/flaty-demo-codes.js"></script>
 --%>
 
 <script>
 
 
  

  
 function selectSubMod(subModId, modId){
	 
	 $.getJSON('${setSubModId}', {
		 subModId : subModId,
		 modId : modId,
						ajax : 'true'
					});
 }
 
 </script>
</body>
</html>
