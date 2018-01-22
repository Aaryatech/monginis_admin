<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Dashboard - MONGINIS Admin</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<!--base css styles-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/data-tables/bootstrap3/dataTables.bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-fileupload/bootstrap-fileupload.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/chosen-bootstrap/chosen.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-timepicker/compiled/timepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/clockface/css/clockface.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/css/datepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/daterangepicker.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<!--page specific css styles-->

<!--flaty css styles-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flaty.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flaty-responsive.css">

<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/img/favicon.png">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/common.js"></script>
</head>
<body onload="searchOrder(${isDelete})">


	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>


	<div class="container" id="main-container">

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
		<div id="main-content">
			<!-- BEGIN Page Title -->
			<div class="page-title">
				<div>
					<h1>
						<i class="fa fa-file-o"></i> Regular Special Cake Orders
					</h1>

				</div>
			</div>
			<!-- END Page Title -->



			<!-- BEGIN Main Content -->
			<div class="row">
				<div class="col-md-12">
					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-bars"></i>Search Order
							</h3>
							<div class="box-tool">
								<a href="">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							<!-- <div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> <a data-action="close" href="#"><i
									class="fa fa-times"></i></a>
							</div> -->
						</div>


						<div class="box-content">
							<form action="regularSpCkOrderProcess" class="form-horizontal"
								method="post" id="validation-form">




								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Franchisee
									</label>
									<div class="col-sm-9 col-lg-10 controls">

										<select data-placeholder="Select Franchisee"
											class="form-control chosen" multiple="multiple" tabindex="6"
											name="fr_id[]" id="fr_id">

											<option value="0">All</option>
											<c:forEach items="${franchiseeList}" var="franchiseeList">
												<option value="${franchiseeList.frId}">${franchiseeList.frName}</option>


											</c:forEach>
											<c:forEach items="${frIdList}" var="franchiseeList">
												<option value="${franchiseeList.frId}"selected>${franchiseeList.frName}</option>


											</c:forEach>

										</select>
									</div>
								</div>

								<%-- <div class="form-group">
										<label for="textfield2"
											class="col-xs-3 col-lg-2 control-label">Items</label>
										<div class="col-sm-9 col-lg-10 controls">
											<select class="form-control input-sm" name="item_name" id="item_name">
												<option value="1" selected>Savouries</option>
												
												<c:forEach items="${menuList}" var="menuList">
											<option value="${menuList.menuId}">${menuList.menuTitle}</option>
											
											</c:forEach>
											
											</select>
										</div>
									</div> --%>




								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Production
										Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input value="${todayDate}" class="form-control date-picker" id="dp2" size="16"
											type="text" name="prod_date" id="prod_date" required />
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
										<button type="submit" class="btn btn-primary">
											<i class="fa fa-check"></i> Search
										</button>
										<!--<button type="button" class="btn">Cancel</button>-->
									</div>
								</div>

								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i> Order List
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
										</div>
									</div>

									<div class="box-content">
						<c:set var="dis" value="none"/>
										<div class="clearfix"></div>
										<div class="table-responsive" style="border: 0">
											<table width="100%" class="table table-advance" id="table1">
												<thead>
													<tr>
														<th width="20" align="left">Sr No</th>
														<th width="138" align="left">Franchise Name</th>
													
														<th width="159" align="left"><span
															style="width: 130px;">Name</span></th>
													
														<th width="130" align="left">Event</th>
														<th width="88" align="left">Delivery Date</th>
														<th width="105" align="left">Rate</th>
														<th width="75" align="left">Quantity</th>
														<th width="91" align="left">Total</th>
														  <th width="87" align="left">View</th>  
														<th width="87" align="left">PDF</th>
														<th width="87" align="left">Action</th>
														
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${regularSpCkOrderList}" var="spCakeOrderList" varStatus="count">
														<c:set var="dis" value="block"/>
														<tr>
														<td><c:out value="${count.index+1}"/></td>
															<td align="left"><c:out
																	value="${spCakeOrderList.frName}"></c:out></td>
														
															<td align="left"><c:out
																	value="${spCakeOrderList.itemName}"></c:out></td>
														
															<td align="left"><c:out
																	value="${spCakeOrderList.rspEvents}"></c:out></td>

															<td align="left"><c:out
																	value="${spCakeOrderList.rspDeliveryDt}"></c:out></td>

															<td align="left"><c:out
																	value="${spCakeOrderList.rate}"></c:out></td>

															<td align="left"><c:out
																	value="${spCakeOrderList.qty}"></c:out></td>
															<c:set var="qty"
																value="${spCakeOrderList.qty}" />
															<c:set var="rate"
																value="${spCakeOrderList.rate}" />
																

															<td align="left"><c:out value="${rate * qty}"></c:out></td>

															  <td align="left"><a href="${pageContext.request.contextPath}/showHtmlViewRegSpcakeOrder/${spCakeOrderList.rspId}" target="blank"><i class="fa fa-file-text-o" style="font-size:24px;"></i></a></td>  

															 <td align="left"><a href="${pageContext.request.contextPath}/showRegSpcakeOrderPdf/${spCakeOrderList.rspId}" target="blank"><i class="fa fa-file-pdf-o" style="font-size:24px;"></i></a></td>  

															 <td align="left"><a href="${pageContext.request.contextPath}/deleteRegSpOrder/${spCakeOrderList.rspId}"><i class="glyphicon glyphicon-remove" style="font-size:24px;"></i></a></td>  




														</tr>

													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<div class="form-group" style="display: <c:out value="${dis}" />;" id="range">
								<div class="col-sm-2  controls">
											<input type="text" class="form-control"  id="from"  placeholder="to no"  >
											</div>
											<div class="col-sm-2  controls">
											<input type="text"  class="form-control" id="to" placeholder="from no" >
											</div>
											<div class="col-sm-3  controls">
											<input type="button" id="from" class="btn btn-primary" value="EXPORT TO PDF IN RANGE" onclick="inRangePdf();">
											</div>
											<div class="col-sm-3  controls">
											 <input type="button" id="expExcel" class="btn btn-primary" value="EXPORT TO Excel" onclick="exportToExcel();" >
											</div>
											</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- END Main Content -->
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
	<script>
		window.jQuery
				|| document
						.write('<script src="${pageContext.request.contextPath}/resources/assets/jquery/jquery-2.0.3.min.js"><\/script>')
	</script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/jquery-cookie/jquery.cookie.js"></script>

	<!--page specific plugin scripts-->
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.resize.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.pie.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.stack.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.crosshair.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.tooltip.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/sparkline/jquery.sparkline.min.js"></script>


	<!--page specific plugin scripts-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>





	<!--flaty scripts-->
	<script src="${pageContext.request.contextPath}/resources/js/flaty.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/flaty-demo-codes.js"></script>
	<!--page specific plugin scripts-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-fileupload/bootstrap-fileupload.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/chosen-bootstrap/chosen.jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/clockface/js/clockface.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/date.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/daterangepicker.js"></script>
		
		<script>
		function inRangePdf()
		{
			var to=document.getElementById("to").value;
			
			var from=document.getElementById("from").value;
			
			if(from==null ||from=="")
			{
			alert("Enter to from");
			}
			else if(to==null ||to=="")
				{
				alert("Enter to no");
				}
			else{

				window.open("${pageContext.request.contextPath}/showRegSpcakeOrderPdfInRange/"+from+"/"+to);
				
			}
		}
											
	</script>
	<script type="text/javascript">

function searchOrder(isDelete){
	if(isDelete==1)
		{
    var form = document.getElementById("validation-form")
    form.action ="${pageContext.request.contextPath}/regularSpCkOrderProcess";
    form.submit();
		}
}

function exportToExcel()
{
	 
	window.open("${pageContext.request.contextPath}/exportToExcel");
			document.getElementById("expExcel").disabled=true;
}
</script>
</body>
</html>