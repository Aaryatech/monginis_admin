<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/flaty.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/flaty-responsive.css">

<link rel="icon" href="${pageContext.request.contextPath}/resources/img/monginislogo.png" type="image/x-icon" >
</head>
<body>


	<!-- BEGIN Navbar -->

	<div id="navbar" class="navbar"  style="width:100%; text-align:center; padding:0px 0px;">
		<button type="button" class="navbar-toggle navbar-btn collapsed"
			data-toggle="collapse" data-target="#sidebar">
			<span class="fa fa-bars"></span>
		</button>

		<!-- BEGIN Navbar Buttons -->
		<ul class="nav flaty-nav pull-right">

			<!-- BEGIN Button User -->
			<li class="user-profile"><a data-toggle="dropdown" href="#"
				class="user-menu dropdown-toggle"> <!--<img class="nav-user-photo" src="resoucres/img/demo/avatar/avatar1.jpg" alt="Penny's Photo" />-->
					<span class="hhh" id="user_info"> Admin </span> <i
					class="fa fa-caret-down"></i>
			</a> <!-- BEGIN User Dropdown -->
				<ul class="dropdown-menu dropdown-navbar" id="user_menu">
					<!--<li class="nav-header">
                            <i class="fa fa-clock-o"></i>
                            Logined From 20:45
                        </li>-->

					<li>
						<%-- <c:url value="/logout" var="logoutUser" />
					
					<a href=""> <i
							class="fa fa-off"></i> Logout
					</a> --%> <a href="${pageContext.request.contextPath}/logout">Logout <c:out
								value="${sessionScope['userName']}" />
					</a>

					</li>
</ul>
					
			
	</div>
	<!-- END Navbar -->

	
	
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

	page specific plugin scripts
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.resize.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.pie.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.stack.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.crosshair.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.tooltip.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/sparkline/jquery.sparkline.min.js"></script>

	flaty scripts
	<script src="${pageContext.request.contextPath}/resources/js/flaty.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/flaty-demo-codes.js"></script>
 --%>
</body>
</html>
