<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<style type="text/css">
.bg-overlay {
    background: linear-gradient(rgba(0,0,0,.4), rgba(0,0,0,.4)), url("${pageContext.request.contextPath}/resources/img/cake.jpeg");
  /*  background-repeat: no-repeat; */
    /* background-size: cover;
    background-position: center center; */
    color: black;
    height:auto;
    width:auto;
    padding-top: 0px;
}
</style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.buttonload {
    background-color: transparent; /* Green background */
    border: none;  /*Remove borders */
    color: #ec268f; /* White text */
  /*   padding: 12px 20px; /* Some padding */ */
    font-size: 15px; /* Set a font-size */
    display: none;
}

/* Add a right margin to each icon */
.fa {
    margin-left: 12px;
    margin-right: 8px;
}
</style>
</head>
	<c:url var="getDashboardData" value="/getDashboardData"></c:url>


<body class="container bg-overlay">
	
<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
	<!-- BEGIN Container -->
	 


	<div class="container1" id="main-container">

		<!-- BEGIN Sidebar -->
		<div id="sidebar" class="navbar-collapse collapse">

			<jsp:include page="/WEB-INF/views/include/navigation.jsp"></jsp:include>

			<div id="sidebar-collapse" class="visible-lg">
				<i class="fa fa-angle-double-left"></i>
			</div>
			<!-- END Sidebar Collapse Button -->
		</div>
		<!-- END Sidebar -->


		<!-- BEGIN Content -->
		<div id="main-content" style="background: transparent;">
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
					<li class="active">	
									<label class="col-sm-3 col-lg-2 control-label" style="padding-top: 4px;">Date</label>
									<div class="col-sm-5 col-lg-4 controls">
										<input class="form-control date-picker" id="date"
											type="text" name="date" value="" placeholder="Date" required />
									</div>
									<div class="col-sm-5 col-lg-3 controls"><button type="submit" class="btn btn-primary" onclick="searchData()">
											 Search
										</button>
										
										</div>
										<div class="col-sm-2"> <button class="buttonload" id="loader">
                                   <i class="fa fa-spinner fa-spin"></i>Loading
                                   </button></div>
								</li>
				</ul>
			</div>
			<!-- END Breadcrumb -->


			<!-- BEGIN Tiles -->
			<div class="row">



					<div class="col-md-12">
					<div class="row">
					
	<%-- 	<c:forEach items="${orderCounts}"  var ="orderCounts"> --%>
						<!-- <a href="resoucres/index.php/orders/list_all"> -->
							<div class="col-md-4">
								<div class="tile tile-light-pink" style="height:auto;border:5px solid #decfbc;">
									<!-- <div class="img">
										<i class="fa fa-rupee"></i>
									</div> -->
									<div class="content1" >
							<p class="title" style="font-size: 15px;background-color: #e79938;text-align: center;"><c:out value="Sales"></c:out></p>
									
										<p class="title" style="font-size: 15px;background-color: #374390;" id="daysaletext"><c:out value="Day Sales On "></c:out></p>
									
										<p class="title" style="text-align: center;font-size: 18px;" id="daysalevalue"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									<br>
										<p class="title" style="font-size: 15px;background-color: #374390;"id="monthsaletext"><c:out value="Month to date sales as on "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;" id="monthsalevalue"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
								<br>
										<p class="title" style="font-size: 15px;background-color: #374390;"id="yearsaletext"><c:out value="Year to month sales as on "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"id="yearsalevalue"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									</div>
									</div>

							</div>
							<div class="col-md-4">
								<div class="tile tile-light-pink" style="height:auto;border:5px solid #decfbc;">
									<!-- <div class="img">
										<i class="fa fa-rupee"></i>
									</div> -->
									<div class="content1" >
						<p class="title" style="font-size: 15px;background-color: #e79938;text-align: center;"><c:out value="GRN"></c:out></p>
									
										<p class="title" style="font-size: 15px;background-color: #374390;"id="daygrntext"><c:out value="Day GRN On "></c:out></p>
									
										<p class="title" style="text-align: center;font-size: 18px;"id="daygrnvalue"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									<br>
										<p class="title" style="font-size: 15px;background-color: #374390;"id="monthgrntext"><c:out value="Month to date GRN as on "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"id="monthgrnvalue"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
								<br>
										<p class="title" style="font-size: 15px;background-color: #374390;"id="yeargrntext"><c:out value="Year to month GRN as on "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"id="yeargrnvalue"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									</div>
									</div>

							</div>
							<div class="col-md-4">
								<div class="tile tile-light-pink" style="height:auto;border:5px solid #decfbc;">
								
									<div class="content1" >
									<p class="title" style="font-size: 15px;background-color: #e79938;text-align: center;"><c:out value="GVN"></c:out></p>
										<p class="title" style="font-size: 15px;background-color: #374390;"id="daygvntext"><c:out value="Day GVN On "></c:out></p>
									
										<p class="title" style="text-align: center;font-size: 18px;"id="daygvnvalue"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									<br>
										<p class="title" style="font-size: 15px;background-color: #374390;"id="monthgvntext"><c:out value="Month to Date GVN as on "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"id="monthgvnvalue"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
								<br>
										<p class="title" style="font-size: 15px;background-color: #374390;" id="yeargvntext"><c:out value="Year to Month GVN as on "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"id="yeargvnvalue"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									</div>
									</div>

							</div>
						<%-- </c:forEach> --%>
					
						<!-- 
						<a href="resoucres/index.php/orders/list_all">
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
 -->



					</div>


				</div>
			</div>
	<div id="breadcrumbs">
				<ul class="breadcrumb">
					<li class="active">	
									Month Sales
								</li>
				</ul>
			</div>
<div class="row">



					<div class="col-md-12">
					<div class="row" >
				   <c:forEach var="entry" items="${months}">
					 <c:forEach var="monthData" items="${monthDataList}">
					 <c:choose><c:when test="${monthData.month==entry.key}">
	                  <div class="col-md-4">
								<div class="tile tile-light-pink" style="height:auto;border:5px solid #decfbc;">
								
									<div class="content1" >
									<p class="title" style="font-size: 15px;background-color: #e79938;text-align: center;"><c:out value="${entry.value} ${monthData.year}"></c:out></p>
										<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="Sales "></c:out></p>
									
										<p class="title" style="text-align: right;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value="${monthData.salesAmt}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</p>
								
											<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="GRN "></c:out></p>
								
										<p class="title" style="text-align: left;font-size: 14px;">&nbsp;&nbsp;Grn1: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${monthData.grnAmt1}" />&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn2: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${monthData.grnAmt2}" />&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn3: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${monthData.grnAmt3}" />&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<hr>
										&nbsp;&nbsp;Total GRN: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;font-size: 17px;"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${monthData.grnTotalAmt}" />&nbsp;&nbsp;&nbsp;&nbsp;</span>
										
										</p>			
										<p class="title" style="font-size: 15px;background-color: #374390;" ><c:out value="GVN "></c:out></p>
								
										<p class="title" style="text-align: right;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${monthData.gvnAmt}"/>&nbsp;&nbsp;&nbsp;&nbsp;</p>
								
								        <p class="title" style="font-size: 15px;background-color: #374390;" ><c:out value="Net Sale "></c:out></p>
								
										<p class="title" style="text-align: right;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<b><c:out value="${monthData.netSaleAmt}"></c:out></b>&nbsp;&nbsp;&nbsp;&nbsp;</p>
								
									</div>
									</div>

							</div> 
						</c:when>	</c:choose>
							</c:forEach>
</c:forEach>
						<%-- 	
											
	<div class="col-md-4">
								<div class="tile tile-light-pink" style="height:auto;border:5px solid #decfbc;">
								
									<div class="content1" >
									<p class="title" style="font-size: 15px;background-color: #e79938;text-align: center;"><c:out value="May 2018"></c:out></p>
										<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="Sales "></c:out></p>
									
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									
											<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="GRN "></c:out></p>
								
										<p class="title" style="text-align: left;font-size: 14px;">&nbsp;&nbsp;Grn1: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="6666"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn2: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="66666666666" ></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn3: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="6666"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										
										</p>
										<p class="title" style="font-size: 15px;background-color: #374390;" ><c:out value="GVN "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
								   <p class="title" style="font-size: 15px;background-color: #374390;" ><c:out value="Net Sale "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
								
									</div>
									</div>

							</div>
												
	<div class="col-md-4">
								<div class="tile tile-light-pink" style="height:auto;border:5px solid #decfbc;">
								
									<div class="content1" >
									<p class="title" style="font-size: 15px;background-color: #e79938;text-align: center;"><c:out value="April 2018"></c:out></p>
										<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="Sales "></c:out></p>
									
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									
											<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="GRN "></c:out></p>
								
										<p class="title" style="text-align: left;font-size: 14px;">&nbsp;&nbsp;Grn1: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="6666"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn2: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="66666666666" ></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn3: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="6666"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										
										</p>
										<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="GVN "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									       <p class="title" style="font-size: 15px;background-color: #374390;" ><c:out value="Net Sale "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
								
									</div>
									</div>

							</div>
							
												
	<div class="col-md-4">
								<div class="tile tile-light-pink" style="height:auto;border:5px solid #decfbc;">
								
									<div class="content1" >
									<p class="title" style="font-size: 15px;background-color: #e79938;text-align: center;"><c:out value="April 2018"></c:out></p>
										<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="Sales "></c:out></p>
									
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									
											<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="GRN "></c:out></p>
								
										<p class="title" style="text-align: left;font-size: 14px;">&nbsp;&nbsp;Grn1: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="6666"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn2: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="66666666666" ></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn3: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="6666"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										
										</p>					
										<p class="title" style="font-size: 15px;background-color: #374390;" ><c:out value="GVN "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									   <p class="title" style="font-size: 15px;background-color: #374390;" ><c:out value="Net Sale "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
								
									
									</div>
									</div>

							</div>
												
	<div class="col-md-4">
								<div class="tile tile-light-pink" style="height:auto;border:5px solid #decfbc;">
								
									<div class="content1" >
									<p class="title" style="font-size: 15px;background-color: #e79938;text-align: center;"><c:out value="April 2018"></c:out></p>
										<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="Sales "></c:out></p>
									
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									
											<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="GRN "></c:out></p>
								
										<p class="title" style="text-align: left;font-size: 14px;">&nbsp;&nbsp;Grn1: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="6666"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn2: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="66666666666" ></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn3: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="6666"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										
										</p>
										<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="GVN "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									   <p class="title" style="font-size: 15px;background-color: #374390;" ><c:out value="Net Sale "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
								
									
									</div>
									</div>

							</div>
							
												
	<div class="col-md-4">
								<div class="tile tile-light-pink" style="height:auto;border:5px solid #decfbc;">
								
									<div class="content1" >
									<p class="title" style="font-size: 15px;background-color: #e79938;text-align: center;"><c:out value="April 2018"></c:out></p>
										<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="Sales "></c:out></p>
									
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									
											<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="GRN "></c:out></p>
								
										<p class="title" style="text-align: left;font-size: 14px;">&nbsp;&nbsp;Grn1: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="6666"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn2: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="66666666666" ></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn3: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="6666"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										
										</p>			
										<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="GVN "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									
									   <p class="title" style="font-size: 15px;background-color: #374390;" ><c:out value="Net Sale "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
								
									</div>
									</div>

							</div>
												
	<div class="col-md-4">
								<div class="tile tile-light-pink" style="height:auto;border:5px solid #decfbc;">
								
									<div class="content1" >
									<p class="title" style="font-size: 15px;background-color: #e79938;text-align: center;"><c:out value="April 2018"></c:out></p>
										<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="Sales "></c:out></p>
									
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									
												<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="GRN "></c:out></p>
								
										<p class="title" style="text-align: left;font-size: 14px;">&nbsp;&nbsp;Grn1: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="6666"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn2: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="66666666666" ></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn3: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="6666"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										
										</p>
										<p class="title" style="font-size: 15px;background-color: #374390;" ><c:out value="GVN "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									
									   <p class="title" style="font-size: 15px;background-color: #374390;" ><c:out value="Net Sale "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
								
									</div>
									</div>

							</div>
												
	<div class="col-md-4">
								<div class="tile tile-light-pink" style="height:auto;border:5px solid #decfbc;">
								
									<div class="content1" >
									<p class="title" style="font-size: 15px;background-color: #e79938;text-align: center;"><c:out value="April 2018"></c:out></p>
										<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="Sales "></c:out></p>
									
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									
												<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="GRN "></c:out></p>
								
										<p class="title" style="text-align: left;font-size: 14px;">&nbsp;&nbsp;Grn1: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="6666"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn2: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="66666666666" ></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn3: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="6666"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										
										</p>				
										<p class="title" style="font-size: 15px;background-color: #374390;" ><c:out value="GVN "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									 
									    <p class="title" style="font-size: 15px;background-color: #374390;" ><c:out value="Net Sale "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
								
									
									</div>
									</div>

							</div>
							
												
	<div class="col-md-4">
								<div class="tile tile-light-pink" style="height:auto;border:5px solid #decfbc;">
								
									<div class="content1" >
									<p class="title" style="font-size: 15px;background-color: #e79938;text-align: center;"><c:out value="April 2018"></c:out></p>
										<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="Sales "></c:out></p>
									
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									
												<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="GRN "></c:out></p>
								
										<p class="title" style="text-align: left;font-size: 14px;">&nbsp;&nbsp;Grn1: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="6666"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn2: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="66666666666" ></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn3: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="6666"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										
										</p>				
										<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="GVN "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
								
								        <p class="title" style="font-size: 15px;background-color: #374390;" ><c:out value="Net Sale "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
								
									</div>
									</div>

							</div>
							
												
	<div class="col-md-4">
								<div class="tile tile-light-pink" style="height:auto;border:5px solid #decfbc;">
								
									<div class="content1" >
									<p class="title" style="font-size: 15px;background-color: #e79938;text-align: center;"><c:out value="April 2018"></c:out></p>
										<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="Sales "></c:out></p>
									
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									
												<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="GRN "></c:out></p>
								
										<p class="title" style="text-align: left;font-size: 14px;">&nbsp;&nbsp;Grn1: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="6666"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn2: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="66666666666" ></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn3: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="6666"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										
										</p>					
										<p class="title" style="font-size: 15px;background-color: #374390;" ><c:out value="GVN "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
								       
								          <p class="title" style="font-size: 15px;background-color: #374390;" ><c:out value="Net Sale "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
								
								
									</div>
									</div>

							</div>
							
												
	<div class="col-md-4">
								<div class="tile tile-light-pink" style="height:auto;border:5px solid #decfbc;">
								
									<div class="content1" >
									<p class="title" style="font-size: 15px;background-color: #e79938;text-align: center;"><c:out value="April 2018"></c:out></p>
										<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="Sales "></c:out></p>
									
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
								
												<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="GRN "></c:out></p>
								
										<p class="title" style="text-align: left;font-size: 14px;">&nbsp;&nbsp;Grn1: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="6666"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn2: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="66666666666" ></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn3: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="6666"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										
										</p>					<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="GVN "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									  
									   <p class="title" style="font-size: 15px;background-color: #374390;" ><c:out value="Net Sale "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
								
									
									</div>
									</div>

							</div>
							
											
	<div class="col-md-4">
								<div class="tile tile-light-pink" style="height:auto;border:5px solid #decfbc;">
								
									<div class="content1" >
									<p class="title" style="font-size: 15px;background-color: #e79938;text-align: center;"><c:out value="April 2018"></c:out></p>
										<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="Sales "></c:out></p>
									
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									
										<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="GRN "></c:out></p>
								
										<p class="title" style="text-align: left;font-size: 14px;">&nbsp;&nbsp;Grn1: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="6666"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn2: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="66666666666" ></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										<br>
										&nbsp;&nbsp;Grn3: <i class="fa fa-rupee"></i>&nbsp;&nbsp;<span style=" float: right;font-weight: bold;"><c:out value="6666"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;</span>
										
										</p>
								
										<p class="title" style="font-size: 15px;background-color: #374390;"><c:out value="GVN "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
									
									     <p class="title" style="font-size: 15px;background-color: #374390;" ><c:out value="Net Sale "></c:out></p>
								
										<p class="title" style="text-align: center;font-size: 18px;"><i class="fa fa-rupee"></i>&nbsp;&nbsp;<c:out value=""></c:out></p>
								
									</div>
									</div>

							</div>
							 --%>	
                   </div></div></div>
			<footer>
			<p>2018 © MONGINIS.</p>
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
	<script>window.jQuery || document.write('<script src="${pageContext.request.contextPath}/resources/assets/jquery/jquery-2.0.3.min.js"><\/script>')</script>
	<script src="${pageContext.request.contextPath}/resources/assets/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/jquery-cookie/jquery.cookie.js"></script>

	<!--page specific plugin scripts-->
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.resize.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.pie.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.stack.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.crosshair.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.tooltip.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/sparkline/jquery.sparkline.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	
	<!--flaty scripts-->
	<script src="${pageContext.request.contextPath}/resources/js/flaty.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/flaty-demo-codes.js"></script>
<script type="text/javascript">
function searchData(){
	
	$('#loader').show();
	 document.getElementById('daysalevalue').innerHTML = ' <i class="fa fa-rupee"></i>&nbsp;&nbsp;'+0;
	 document.getElementById('daygrnvalue').innerHTML = ' <i class="fa fa-rupee"></i>&nbsp;&nbsp;'+0;
	 document.getElementById('daygvnvalue').innerHTML = ' <i class="fa fa-rupee"></i>&nbsp;&nbsp;'+0;
	 document.getElementById('monthsalevalue').innerHTML = ' <i class="fa fa-rupee"></i>&nbsp;&nbsp;'+0;
	 document.getElementById('monthgrnvalue').innerHTML = ' <i class="fa fa-rupee"></i>&nbsp;&nbsp;'+0;
	 document.getElementById('monthgvnvalue').innerHTML = ' <i class="fa fa-rupee"></i>&nbsp;&nbsp;'+0;
	 document.getElementById('yearsalevalue').innerHTML = ' <i class="fa fa-rupee"></i>&nbsp;&nbsp;'+0;
	 document.getElementById('yeargrnvalue').innerHTML = ' <i class="fa fa-rupee"></i>&nbsp;&nbsp;'+0;
	 document.getElementById('yeargvnvalue').innerHTML = ' <i class="fa fa-rupee"></i>&nbsp;&nbsp;'+0;
	
	 var date = $("#date").val();
			$.getJSON(
					'${getDashboardData}',
					{
						date : date,
						ajax : 'true'

					},
					function(data) {
						$('#loader').hide();

							 if (data == "") {
									alert("No records found !!");

								}
							 document.getElementById('daysaletext').innerHTML = 'Day Sales On '+date;
							 document.getElementById('daygrntext').innerHTML = 'Day GRN On '+date;
							 document.getElementById('daygvntext').innerHTML = 'Day GVN On '+date;
							 document.getElementById('monthsaletext').innerHTML = ' Month to date Sales as on '+date;
							 document.getElementById('monthgrntext').innerHTML = ' Month to date GRN as on '+date;
							 document.getElementById('monthgvntext').innerHTML = ' Month to date GVN as on '+date;
							 
							 document.getElementById('yearsaletext').innerHTML = ' Year to month Sales as on '+date;
							 document.getElementById('yeargrntext').innerHTML = ' Year to month GRN as on '+date;
							 document.getElementById('yeargvntext').innerHTML = ' Year to month GVN as on '+date;
                              if(data.daySale!=null){
							 document.getElementById('daysalevalue').innerHTML = ' <i class="fa fa-rupee"></i>&nbsp;&nbsp;'+data.daySale.grandTotal;
                              }
                              if(data.dayGrn!=null){
							 document.getElementById('daygrnvalue').innerHTML = ' <i class="fa fa-rupee"></i>&nbsp;&nbsp;'+data.dayGrn.crnGrandTotal;
                              }
                              if(data.dayGvn!=null){
							 document.getElementById('daygvnvalue').innerHTML = ' <i class="fa fa-rupee"></i>&nbsp;&nbsp;'+data.dayGvn.crnGrandTotal;
                              }
                              if(data.monthSale!=null){
							 document.getElementById('monthsalevalue').innerHTML = ' <i class="fa fa-rupee"></i>&nbsp;&nbsp;'+data.monthSale.grandTotal;
                              }
                              if(data.monthGrn!=null){
							 document.getElementById('monthgrnvalue').innerHTML = ' <i class="fa fa-rupee"></i>&nbsp;&nbsp;'+data.monthGrn.crnGrandTotal;
                              }
                              if(data.monthGvn!=null){
							 document.getElementById('monthgvnvalue').innerHTML = ' <i class="fa fa-rupee"></i>&nbsp;&nbsp;'+data.monthGvn.crnGrandTotal;
                              }
                              if(data.yearSale!=null){
							 document.getElementById('yearsalevalue').innerHTML = ' <i class="fa fa-rupee"></i>&nbsp;&nbsp;'+data.yearSale.grandTotal;
                              }
                              if(data.yearGrn!=null){
							 document.getElementById('yeargrnvalue').innerHTML = ' <i class="fa fa-rupee"></i>&nbsp;&nbsp;'+data.yearGrn.crnGrandTotal;
                              }
                              if(data.yearGvn!=null){
							 document.getElementById('yeargvnvalue').innerHTML = ' <i class="fa fa-rupee"></i>&nbsp;&nbsp;'+data.yearGvn.crnGrandTotal;
                              }
					});
}
</script>

</body>
</html>
