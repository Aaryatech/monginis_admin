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
	href="resources/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="resources/assets/font-awesome/css/font-awesome.min.css">

<!--page specific css styles-->

<!--flaty css styles-->
<link rel="stylesheet" href="resources/css/flaty.css">
<link rel="stylesheet" href="resources/css/flaty-responsive.css">

<link rel="shortcut icon" href="resources/img/favicon.png">
</head>
<body>


	<!-- BEGIN Navbar -->

	<div id="navbar" class="navbar">
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
					</a> --%> <a href="logout">Logout <c:out
								value="${sessionScope['userName']}" />
					</a>

					</li>

					<!--                        <li>
                            <a href="#">
                                <i class="fa fa-user"></i>
                                Edit Profile
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <i class="fa fa-question"></i>
                                Help
                            </a>
                        </li>-->

					<!--<li class="divider visible-xs"></li>

                        <li class="visible-xs">
                            <a href="#">
                                <i class="fa fa-tasks"></i>
                                Tasks
                                <span class="badge badge-warning">4</span>
                            </a>
                        </li>
                        <li class="visible-xs">
                            <a href="#">
                                <i class="fa fa-bell"></i>
                                Notifications
                                <span class="badge badge-important">8</span>
                            </a>
                        </li>
                        <li class="visible-xs">
                            <a href="#">
                                <i class="fa fa-envelope"></i>
                                Messages
                                <span class="badge badge-success">5</span>
                            </a>
                        </li>

                        <li class="divider"></li>

                        <li>
                            <a href="#">
                                <i class="fa fa-off"></i>
                                Logout
                            </a>
                        </li>-->
				</ul> <!-- BEGIN User Dropdown --></li>
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
				src="resources/img/monginislogo.png" style="position: relative;"
				alt="">
			</a>
			<div style="clear: both;"></div>
			<ul class="nav nav-list">
				<!-- BEGIN Search Form -->
				<!--<li>
                        <form target="#" method="GET" class="search-form">
                            <span class="search-pan">
                                <button type="submit">
                                    <i class="fa fa-search"></i>
                                </button>
                                <input type="text" name="search" placeholder="Search ..." autocomplete="off" />
                            </span>
                        </form>
                    </li>-->
				<!-- END Search Form -->
				<li class="active"><a href="home"> <i
						class="fa fa-dashboard"></i> <span>Dashboard</span>
				</a></li>

				<li><a href="#" class="dropdown-toggle"> <i
						class="fa fa-list"></i> <span>Orders</span> <b
						class="arrow fa fa-angle-right"></b>
				</a> <!-- BEGIN Submenu -->
					<ul class="submenu">
						<li><a href="resoucres/index.php/orders/list_all">Orders</a></li>
						<li><a href="resoucres/index.php/orders/special">Special
								Cake Orders</a></li>

					</ul> <!-- END Submenu --></li>

				<li><a href="#" class="dropdown-toggle"> <i
						class="fa fa-list"></i> <span>Masters</span> <b
						class="arrow fa fa-angle-right"></b>
				</a> <!-- BEGIN Submenu -->
					<ul class="submenu">
						<li><a href="resoucres/index.php/flavours/list_all">Flavours</a></li>
						<li><a href="resoucres/index.php/events/list_all">Events</a></li>
						<li><a href="resoucres/index.php/rates/list_all">Rates</a></li>
						<li><a href="resoucres/index.php/messages/list_all">Messages</a></li>
						<li><a href="resoucres/index.php/route/list_all">Route</a></li>


					</ul> <!-- END Submenu --></li>
				<li><a href="#" class="dropdown-toggle"> <i
						class="fa fa-list"></i> <span>Special Cake</span> <b
						class="arrow fa fa-angle-right"></b>
				</a> <!-- BEGIN Submenu -->
					<ul class="submenu">
						<li><a href="resoucres/index.php/special_cake/add">Add
								New</a></li>
						<li><a href="resoucres/index.php/special_cake/list_all">Special
								Cakes</a></li>


					</ul> <!-- END Submenu --></li>

				<li><a href="#" class="dropdown-toggle"> <i
						class="fa fa-list"></i> <span>Items</span> <b
						class="arrow fa fa-angle-right"></b>
				</a> <!-- BEGIN Submenu -->
					<ul class="submenu">
						<li><a href="resoucres/index.php/items/import_data">Import
								Items</a></li>
						<li><a href="resoucres/index.php/items/add">Add New</a></li>
						<li><a href="resoucres/index.php/items/list_all">Items
								List</a></li>


					</ul> <!-- END Submenu --></li>

				<li><a href="#" class="dropdown-toggle"> <i
						class="fa fa-list"></i> <span> Latest News</span> <b
						class="arrow fa fa-angle-right"></b>
				</a> <!-- BEGIN Submenu -->
					<ul class="submenu">
						<li><a href="resoucres/index.php/scheduler/add">Add New</a></li>
						<li><a href="resoucres/index.php/scheduler/list_all">
								Latest News List</a></li>


					</ul> <!-- END Submenu --></li>

				<li><a href="#" class="dropdown-toggle"> <i
						class="fa fa-list"></i> <span>Message</span> <b
						class="arrow fa fa-angle-right"></b>
				</a> <!-- BEGIN Submenu -->
					<ul class="submenu">
						<li><a href="resoucres/index.php/t_message/add">Add New</a></li>
						<li><a href="resoucres/index.php/t_message/list_all">Messages
								List</a></li>


					</ul> <!-- END Submenu --></li>

				<li><a href="#" class="dropdown-toggle"> <i
						class="fa fa-list"></i> <span>Franchisee</span> <b
						class="arrow fa fa-angle-right"></b>
				</a> <!-- BEGIN Submenu -->
					<ul class="submenu">
						<li><a href="AddNewFranchisee">Add New</a></li>
						<li><a href="resoucres/index.php/franchisee/list_all">Franchisee
								List</a></li>


					</ul> <!-- END Submenu --></li>

				<li><a href="#" class="dropdown-toggle"> <i
						class="fa fa-list"></i> <span>Admin Users</span> <b
						class="arrow fa fa-angle-right"></b>
				</a> <!-- BEGIN Submenu -->
					<ul class="submenu">
						<li><a href="resoucres/index.php/admin_users/add">Add New</a></li>
						<li><a href="resoucres/index.php/admin_users/list_all">Users
								List</a></li>


					</ul> <!-- END Submenu --></li>

				<li><a href="#" class="dropdown-toggle"> <i
						class="fa fa-list"></i> <span>Setiings</span> <b
						class="arrow fa fa-angle-right"></b>
				</a> <!-- BEGIN Submenu -->
					<ul class="submenu">
						<li><a href="resoucres/index.php/special_items/list_all">Pack
								Product Scheduling</a></li>
						<li><a href="resoucres/index.php/admin_setting/time_setting">Time
								Seting</a></li>
						<li><a href="resoucres/index.php/admin_setting/special_day">Special
								Day</a></li>


					</ul> <!-- END Submenu --></li>

				<li><a href="#" class="dropdown-toggle"> <i
						class="fa fa-list"></i> <span>Logout</span> <b
						class="arrow fa fa-angle-right"></b>
				</a> <!-- BEGIN Submenu -->
					<ul class="submenu">
						<li><a href="resoucres/index.php/dashboard/logout">Logout</a></li>



					</ul> <!-- END Submenu --></li>

			</ul>
			<!-- END Navlist -->

			<!-- BEGIN Sidebar Collapse Button -->
			<div id="sidebar-collapse" class="visible-lg">
				<i class="fa fa-angle-double-left"></i>
			</div>
			<!-- END Sidebar Collapse Button -->
		</div>
		<!-- END Sidebar -->

		<!-- BEGIN Content -->
		<div id="main-content">
			<!-- BEGIN Page Title -->
			<div class="page-title">
				<div>
					<h1>
						<i class="fa fa-file-o"></i> Dashboard
					</h1>
					<!--<h4>Overview, stats, chat and more</h4>-->
				</div>
			</div>
			<!-- END Page Title -->

			<!-- BEGIN Breadcrumb -->
			<div id="breadcrumbs">
				<ul class="breadcrumb">
					<li class="active"><i class="fa fa-home"></i> Home</li>
				</ul>
			</div>
			<!-- END Breadcrumb -->


			<!-- BEGIN Tiles -->
			<div class="row">


				<div class="col-md-12">
					<div class="row">
						<a href="resoucres/index.php/orders/list_all">
							<div class="col-md-3">
								<div class="tile tile-orange">
									<div class="img">
										<i class="fa fa-comments"></i>
									</div>
									<div class="content">
										<p class="big">1727</p>
										<p class="title">Savouries</p>
									</div>
								</div>
							</div>
						</a> <a href="resoucres/index.php/orders/list_all">
							<div class="col-md-3">
								<div class="tile tile-orange">
									<div class="img">
										<i class="fa fa-comments"></i>
									</div>
									<div class="content">
										<p class="big">17</p>
										<p class="title">Cakes & Pastries</p>
									</div>
								</div>
							</div>
						</a> <a href="resoucres/index.php/orders/special">
							<div class="col-md-3">
								<div class="tile tile-orange">
									<div class="img">
										<i class="fa fa-comments"></i>
									</div>
									<div class="content">
										<p class="big">26</p>
										<p class="title">Special Cake</p>
									</div>
								</div>
							</div>
						</a> <a href="resoucres/index.php/orders/list_all">
							<div class="col-md-3">
								<div class="tile tile-orange">
									<div class="img">
										<i class="fa fa-comments"></i>
									</div>
									<div class="content">
										<p class="big">119</p>
										<p class="title">Trading & Packing</p>
									</div>
								</div>
							</div>
						</a>
					</div>


				</div>

				<div class="col-md-12">
					<div class="row">
						<a href="resoucres/index.php/orders/list_all">
							<div class="col-md-3">
								<div class="tile tile-orange">
									<div class="img">
										<i class="fa fa-comments"></i>
									</div>
									<div class="content">
										<p class="big">0</p>
										<p class="title">Pack Product Booking</p>
									</div>
								</div>
							</div>
						</a> <a href="resoucres/index.php/orders/list_all">
							<div class="col-md-3">
								<div class="tile tile-orange">
									<div class="img">
										<i class="fa fa-comments"></i>
									</div>
									<div class="content">
										<p class="big">26</p>
										<p class="title">Special Day Cakes</p>
									</div>
								</div>
							</div>
						</a>




					</div>


				</div>
			</div>


			<footer>
			<p>2017 © MONGINIS.</p>
			</footer>

			<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
				class="fa fa-chevron-up"></i></a>

		</div>
		<!-- END Content -->
	</div>
	<!-- END Container -->
	<!--basic scripts-->
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	<script>window.jQuery || document.write('<script src="resources/assets/jquery/jquery-2.0.3.min.js"><\/script>')</script>
	<script src="resources/assets/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="resources/assets/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="resources/assets/jquery-cookie/jquery.cookie.js"></script>

	<!--page specific plugin scripts-->
	<script src="resources/assets/flot/jquery.flot.js"></script>
	<script src="resources/assets/flot/jquery.flot.resize.js"></script>
	<script src="resources/assets/flot/jquery.flot.pie.js"></script>
	<script src="resources/assets/flot/jquery.flot.stack.js"></script>
	<script src="resources/assets/flot/jquery.flot.crosshair.js"></script>
	<script src="resources/assets/flot/jquery.flot.tooltip.min.js"></script>
	<script src="resources/assets/sparkline/jquery.sparkline.min.js"></script>

	<!--flaty scripts-->
	<script src="resources/js/flaty.js"></script>
	<script src="resources/js/flaty-demo-codes.js"></script>

</body>
</html>
