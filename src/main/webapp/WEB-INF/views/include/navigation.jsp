<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.ats.adminpanel.commons.Constants"%>

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

				<c:choose>
					<c:when test="${Constants.mainAct==1}">
						<li class="active">
					</c:when>

					<c:otherwise>
						<li>
					</c:otherwise>
				</c:choose>


				<a href="#" class="dropdown-toggle"> <i class="fa fa-list"></i>
					<span>Orders</span> <b class="arrow fa fa-angle-right"></b>
				</a>
				<!-- BEGIN Submenu -->
				<ul class="submenu">

					<c:choose>
						<c:when test="${Constants.subAct==11}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showOrders">Orders</a>
					</li>


					<c:choose>
						<c:when test="${Constants.subAct==12}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/spCakeOrders">Special
						Cake Orders</a>
					</li>
					
					
					<c:choose>
						<c:when test="${Constants.subAct==13}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
							
							
							
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/regularSpCakeOrderProcess">Regular Special
						Cake Orders</a>
					</li>
					
					
					<c:choose>
						<c:when test="${Constants.subAct==14}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showpushorders">Push Orders</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==15}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showdumporders">Dump Orders</a>
					</li>
					

				</ul>
				<!-- END Submenu -->
				</li>

				<c:choose>
					<c:when test="${Constants.mainAct==2}">
						<li class="active">
					</c:when>

					<c:otherwise>
						<li>
					</c:otherwise>
				</c:choose>
				<a href="#" class="dropdown-toggle"> <i class="fa fa-list"></i>
					<span>Masters</span> <b class="arrow fa fa-angle-right"></b>
				</a>
				<!-- BEGIN Submenu -->
				<ul class="submenu">
					<c:choose>
						<c:when test="${Constants.subAct==21}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/flavoursList">Flavours</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==22}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/addAndShowEvents">Events</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==23}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showRates">Rates</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==24}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showSpMessages">Messages</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==25}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/addroute">Route</a>
					</li>
<c:choose>
						<c:when test="${Constants.subAct==26}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showAddRmTax">Add RM Tax</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==27}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showAddRmUmo">Add RM UOM</a>
					</li>

				</ul>
				<!-- END Submenu -->
				</li>


<!-- Purchase Master
 -->			
 <c:choose>
					<c:when test="${Constants.mainAct==17}">
						<li class="active">
					</c:when>

					<c:otherwise>
						<li>
					</c:otherwise>
				</c:choose>
				<a href="#" class="dropdown-toggle"> <i class="fa fa-list"></i>
					<span>Purchase Masters</span> <b class="arrow fa fa-angle-right"></b>
				</a>
				<!-- BEGIN Submenu -->
				<ul class="submenu">
					<c:choose>
						<c:when test="${Constants.subAct==171}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showAddSupplier">Add Supplier</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==172}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showSupplierDetails">View Supplier Details</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==173}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showAddRawMaterial">Add Raw Material</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==174}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showRawMaterial">View Raw Material Details</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==175}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showRmRateVerification">RM Rate Verification</a>
					</li>
                    <c:choose>
						<c:when test="${Constants.subAct==176}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showRmItemCategory">RM Category</a>
					</li>
                   <c:choose>
						<c:when test="${Constants.subAct==177}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showItemCatList">RM Category List</a>
					</li>
                   <c:choose>
						<c:when test="${Constants.subAct==178}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showRmItemSubCategory">RM SubCategory</a>
					</li>
					 <c:choose>
						<c:when test="${Constants.subAct==179}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showItemSubCatList">RM SubCategory List</a>
					</li>
                    <c:choose>
						<c:when test="${Constants.subAct==180}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showAddTransporter">Transporter</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==181}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showTransporterList">Transporter List</a>
					</li>
						<c:choose>
						<c:when test="${Constants.subAct==182}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showAddPaymentTerms">Payment Terms</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==183}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showPaymentTermsList">Payment Terms List</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==184}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/gateEntries">Gate Entry</a>
					</li>

				</ul>
				<!-- END Submenu -->
				</li>
 
 <!-- end Puchase Master -->
 	<c:choose>
					<c:when test="${Constants.mainAct==3}">
						<li class="active">
					</c:when>

					<c:otherwise>
						<li>
					</c:otherwise>
				</c:choose>
				<a href="#" class="dropdown-toggle"> <i class="fa fa-list"></i>
					<span>Special Cake</span> <b class="arrow fa fa-angle-right"></b>
				</a>
				<!-- BEGIN Submenu -->
				<ul class="submenu">
					<c:choose>
						<c:when test="${Constants.subAct==31}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/addSpCake">Add New
						Cake</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==32}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showSpecialCake">Special
						Cakes</a>
					</li>


				</ul>
				<!-- END Submenu -->
				</li>

				<c:choose>
					<c:when test="${Constants.mainAct==4}">
						<li class="active">
					</c:when>

					<c:otherwise>
						<li>
					</c:otherwise>
				</c:choose>
				<a href="#" class="dropdown-toggle"> <i class="fa fa-list"></i>
					<span>Items</span> <b class="arrow fa fa-angle-right"></b>
				</a>
				<!-- BEGIN Submenu -->
				<ul class="submenu">
					<c:choose>
						<c:when test="${Constants.subAct==41}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="resoucres/index.php/items/import_data">Import Items</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==42}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/addItem">Add New</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==43}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/itemList">Items
						List</a>
					</li>

						<c:choose>
						<c:when test="${Constants.subAct==44}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showFrItemConfiguration">Item Configuration</a>
					</li>


				</ul>
				<!-- END Submenu -->
				</li>

				<c:choose>
					<c:when test="${Constants.mainAct==5}">
						<li class="active">
					</c:when>

					<c:otherwise>
						<li>
					</c:otherwise>
				</c:choose>
				<a href="#" class="dropdown-toggle"> <i class="fa fa-list"></i>
					<span> Latest News</span> <b class="arrow fa fa-angle-right"></b>
				</a>
				<!-- BEGIN Submenu -->
				<ul class="submenu">
					<c:choose>
						<c:when test="${Constants.subAct==51}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/addNews">Add New</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==52}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showAllLatestNews">
						Latest News List</a>
					</li>


				</ul>
				<!-- END Submenu -->
				</li>

				<c:choose>
					<c:when test="${Constants.mainAct==6}">
						<li class="active">
					</c:when>

					<c:otherwise>
						<li>
					</c:otherwise>
				</c:choose>
				<a href="#" class="dropdown-toggle"> <i class="fa fa-list"></i>
					<span>Message</span> <b class="arrow fa fa-angle-right"></b>
				</a>
				<!-- BEGIN Submenu -->
				<ul class="submenu">
					<c:choose>
						<c:when test="${Constants.subAct==61}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/addNewMessage">Add
						New</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==62}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showMessages">Messages
						List</a>
					</li>


				</ul>
				<!-- END Submenu -->
				</li>

				<c:choose>
					<c:when test="${Constants.mainAct==7}">
						<li class="active">
					</c:when>

					<c:otherwise>
						<li>
					</c:otherwise>
				</c:choose>
				<a href="#" class="dropdown-toggle"> <i class="fa fa-list"></i>
					<span>Franchisee</span> <b class="arrow fa fa-angle-right"></b>
				</a>
				<!-- BEGIN Submenu -->
				<ul class="submenu">
					<c:choose>
						<c:when test="${Constants.subAct==71}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showAddNewFranchisee">Add
						New</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==72}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/listAllFranchisee">Franchisee
						List</a>
					</li>

					<c:choose>
						<c:when test="${Constants.subAct==73}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/configureFranchisee">Configure
						Franchisee</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==74}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a
						href="${pageContext.request.contextPath}/configureFranchiseesList">
						Configured Franchisee List</a>
					</li>
                   <c:choose>
						<c:when test="${Constants.subAct==75}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a
						href="${pageContext.request.contextPath}/configureSpecialDayCake">
						Configure Special Day Cake</a>
					</li>
                      <c:choose>
						<c:when test="${Constants.subAct==76}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a
						href="${pageContext.request.contextPath}/configureSpecialDayCkList">
						Configured Special Day Cake List</a>
					</li>

				</ul>
				<!-- END Submenu -->
				</li>
				
				
				<!-- Billing Menu -->

	<c:choose>
					<c:when test="${Constants.mainAct==8}">
						<li class="active">
					</c:when>

					<c:otherwise>
						<li>
					</c:otherwise>
				</c:choose>
				<a href="#" class="dropdown-toggle"> <i class="fa fa-list"></i>
					<span>Billing</span> <b class="arrow fa fa-angle-right"></b>
				</a>
				<!-- BEGIN Submenu -->
				<ul class="submenu">
					<%-- <c:choose>
						<c:when test="${Constants.subAct==81}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showAddNewBill">Add
						New Bill</a>
					</li> --%>
					<c:choose>
						<c:when test="${Constants.subAct==82}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showGenerateBill">Generate Bill</a>
					</li>
					
					<c:choose>
						<c:when test="${Constants.subAct==83}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showBillList">Bill List</a>
					</li>
					
						<c:choose>
						<c:when test="${Constants.subAct==84}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/viewBill">Sell Bill List</a>
					</li>

					
					
					<c:choose>
						<c:when test="${Constants.subAct==85}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showInsertCreditNote">Credit Note</a>
					</li>
					



				</ul>
				<!-- END Submenu -->
				</li>

		<!-- new added by sachin -->
		
		
<!-- GRN Menu -->


</li>

<c:choose>
					<c:when test="${Constants.mainAct==12}">
						<li class="active">
					</c:when>

					<c:otherwise>
						<li>
					</c:otherwise>
				</c:choose>
				<a href="#" class="dropdown-toggle"> <i class="fa fa-list"></i>
					<span>Stock</span> <b class="arrow fa fa-angle-right"></b>
				</a>
				<!-- BEGIN Submenu -->
				<ul class="submenu">

					<c:choose>
						<c:when test="${Constants.subAct==121}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showFrOpeningStock">Franchise
						Opening Stock</a>
					</li>

					<c:choose>
						<c:when test="${Constants.subAct==122}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showBillList">Bill
						List</a>
					</li>

				</ul>
				<!-- END Submenu -->
				</li>








	<c:choose>
					<c:when test="${Constants.mainAct==9}">
						<li class="active">
					</c:when>

					<c:otherwise>
						<li>
					</c:otherwise>
				</c:choose>
				<a href="#" class="dropdown-toggle"> <i class="fa fa-list"></i>
					<span>GRN</span> <b class="arrow fa fa-angle-right"></b>
				</a>
				<!-- BEGIN Submenu -->
				<ul class="submenu">
					<%-- <c:choose>
						<c:when test="${Constants.subAct==81}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showAddNewBill">Add
						New Bill</a>
					</li> --%>
					<c:choose>
						<c:when test="${Constants.subAct==91}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showGateGrn">Gate GRN</a>
					</li>
					
					<c:choose>
						<c:when test="${Constants.subAct==92}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showAccountGrn">Account GRN</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==93}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/displayGrn">View GRN</a>
					</li>

				</ul>
				<!-- END Submenu -->
				</li>




<!-- end of new added by sachin -->

<!--start GVN menu  -->


<c:choose>
					<c:when test="${Constants.mainAct==12}">
						<li class="active">
					</c:when>

					<c:otherwise>
						<li>
					</c:otherwise>
				</c:choose>
				<a href="#" class="dropdown-toggle"> <i class="fa fa-list"></i>
					<span>GVN</span> <b class="arrow fa fa-angle-right"></b>
				</a>
				<!-- BEGIN Submenu -->
				<ul class="submenu">
					<%-- <c:choose>
						<c:when test="${Constants.subAct==81}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showAddNewBill">Add
						New Bill</a>
					</li> --%>
					<c:choose>
						<c:when test="${Constants.subAct==121}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showGateGvn">Gate GVN</a>
					</li>
					
					<c:choose>
						<c:when test="${Constants.subAct==122}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showStoreGvn">Store GVN</a>

<c:choose>
						<c:when test="${Constants.subAct==123}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showAccountGvn">Account GVN</a>

					</li>
					<c:choose>
						<c:when test="${Constants.subAct==124}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/displayGvn">View GvN</a>
					</li>

				</ul>
				<!-- END Submenu -->
				</li>




<!-- end of new added by sachin -->


<c:choose>
					<c:when test="${Constants.mainAct==16}">
						<li class="active">
					</c:when>

					<c:otherwise>
						<li>
					</c:otherwise>
				</c:choose>
				<a href="#" class="dropdown-toggle"> <i class="fa fa-list"></i>
					<span>Production</span> <b class="arrow fa fa-angle-right"></b>
				</a>
				<!-- BEGIN Submenu -->
				<ul class="submenu">
					
					<c:choose>
						<c:when test="${Constants.subAct==161}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/showproduction">Add to Production</a>
					</li>
					
					<c:choose>
						<c:when test="${Constants.subAct==162}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/addForecasting">Add Forecasting</a>
					</li>
					
</ul>
				<!-- END Submenu -->
				</li>










				<c:choose>
					<c:when test="${Constants.mainAct==13}">
						<li class="active">
					</c:when>

					<c:otherwise>
						<li>
					</c:otherwise>
				</c:choose>
				<a href="#" class="dropdown-toggle"> <i class="fa fa-list"></i>
					<span>Admin Users</span> <b class="arrow fa fa-angle-right"></b>
				</a>
				<!-- BEGIN Submenu -->
				<ul class="submenu">
					<c:choose>
						<c:when test="${Constants.subAct==131}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="resoucres/index.php/admin_users/add">Add New</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==132}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="resoucres/index.php/admin_users/list_all">Users List</a>
					</li>


				</ul>
				<!-- END Submenu -->
				</li>

				<c:choose>
					<c:when test="${Constants.mainAct==10}">
						<li class="active">
					</c:when>

					<c:otherwise>
						<li>
					</c:otherwise>
				</c:choose>
				<a href="#" class="dropdown-toggle"> <i class="fa fa-list"></i>
					<span>Setiings</span> <b class="arrow fa fa-angle-right"></b>
				</a>
				<!-- BEGIN Submenu -->
				<ul class="submenu">
					<c:choose>
						<c:when test="${Constants.subAct==101}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="resoucres/index.php/special_items/list_all">Pack
						Product Scheduling</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==102}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="resoucres/index.php/admin_setting/time_setting">Time
						Seting</a>
					</li>
					<c:choose>
						<c:when test="${Constants.subAct==103}">
							<li class="active">
						</c:when>
						<c:otherwise>
							<li>
						</c:otherwise>
					</c:choose>
					<a href="resoucres/index.php/admin_setting/special_day">Special
						Day</a>
					</li>


				</ul>
				<!-- END Submenu -->
				</li>

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
</body>
</html>
