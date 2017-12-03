<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Franchisee Bill</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<!--base css styles-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/font-awesome/css/font-awesome.min.css">

<!--page specific css styles-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/chosen-bootstrap/chosen.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/jquery-tags-input/jquery.tagsinput.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/jquery-pwstrength/jquery.pwstrength.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-fileupload/bootstrap-fileupload.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-duallistbox/duallistbox/bootstrap-duallistbox.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/dropzone/downloads/css/dropzone.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-colorpicker/css/colorpicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-timepicker/compiled/timepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/clockface/css/clockface.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/css/datepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/daterangepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-switch/static/stylesheets/bootstrap-switch.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-wysihtml5/bootstrap-wysihtml5.css" />
<style>
@media only screen and (min-width: 1200px) {
	.franchisee_label, .menu_label {
		width: 22%;
	}
	.franchisee_select, .menu_select {
		width: 76%;
	}
	.date_label {
		width: 40%;
	}
	.date_select {
		width: 50%;
		padding-right: 0px;
	}
}
</style>
<!--flaty css styles-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flaty.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/loader.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flaty-responsive.css">

<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/img/favicon.png">
</head>
<body>


	<c:url var="getBillList" value="/generateNewBill"></c:url>

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>


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
					<i class="fa fa-file-o"></i>Franchise Bill
				</h1>
				<h4>Bill for franchises</h4>
			</div>
		</div>
		<!-- END Page Title -->

		<!-- BEGIN Breadcrumb -->
		<div id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i> <a
					href="${pageContext.request.contextPath}/home">Home</a> <span
					class="divider"><i class="fa fa-angle-right"></i></span></li>
				<li class="active">Franchise Bill</li>
			</ul>
		</div>
		<!-- END Breadcrumb -->

		<!-- BEGIN Main Content -->
		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>Menu
				</h3>

			</div>

			<div class="box-content">
				<div class="row">
					<div class="form-group col-md-9">
						<label class=" col-md-2 control-label franchisee_label">Select
							Franchise </label>
						<div class=" col-md-7 controls franchisee_select">
							<select data-placeholder="Choose Franchisee"
								class="form-control chosen " multiple="multiple" tabindex="6"
								id="selectFr" name="selectFr">

								<option value="-1"><c:out value="All"/></option>



								<c:forEach items="${unSelectedFrList}" var="fr"
									varStatus="count">
									<option value="${fr.frId}"><c:out value="${fr.frName}"/></option>
								</c:forEach>



							</select>
						</div>
						
						
						
					</div>

					<div class="form-group col-md-3">
						<label class="col-md-5	 control-label date_label">Select
							Date</label>
						<div class="col-md-6 controls date_select">
							<input class="form-control date-picker" id="deliveryDate"
								name="deliveryDate" size="30" type="text" value="${todaysDate}" />
						</div>

					</div>



<!-- <div class="col-sm-9 col-lg-5 controls">
 -->
 
 <div class="form-group col-md-5"><label class=" col-md-2 control-label menu_label">
							Route</label>

										<select class="form-control chosen" tabindex="6"
											name="selectRoute" id="selectRoute">

											<option value="0">Select Route</option>
											<c:forEach items="${routeList}" var="route" varStatus="count">
												<option value="${route.routeId}"> ${route.routeName}</option>

											</c:forEach>

										</select>
									</div>

					<div class="form-group  ">
						<label class=" col-md-2 control-label menu_label">Select
							Menu</label>
						<div class=" col-md-3 controls menu_select">

							<select data-placeholder="Choose Menu"
								class="form-control chosen" multiple="multiple" tabindex="6"
								id="selectMenu" name="selectMenu">

								<option value="-1"><c:out value="All"/></option>





								<c:forEach items="${unSelectedMenuList}" var="unSelectedMenu"
									varStatus="count">
									<option value="${unSelectedMenu.menuId}"><c:out value="${unSelectedMenu.menuTitle}"/></option>
								</c:forEach>


							</select>
						</div>
					</div>

				</div>

				<div class="row">
					<div class="col-md-12" style="text-align: center">
						<button class="btn btn-info" onclick="generateNewBill()">Generate
							Bill</button>


					</div>
				</div>


				<div align="center" id="loader" style="display: none">

					<span>
						<h4>
							<font color="#343690">Loading</font>
						</h4>
					</span> <span class="l-1"></span> <span class="l-2"></span> <span
						class="l-3"></span> <span class="l-4"></span> <span class="l-5"></span>
					<span class="l-6"></span>
				</div>

			</div>
		</div>


		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-list-alt"></i>Bill
				</h3>

			</div>

			<form id="submitBillForm"
				action="${pageContext.request.contextPath}/submitNewBill" method="post">
				<div class=" box-content">
					<div class="row">
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Franchise Name</th>
										<th>Menu Name</th>
										<th>Item id</th>
										<th>Item Name</th>
										<th>Order Quantity</th>
										<th>Bill Quantity</th>
										<th>Expiry Date</th>
										<th>Rate</th>
										<th>Total</th>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
							</table>
						</div>
					</div>



					<div class="row">
						<div class="col-md-offset-6 col-md-6">
							<button class="btn btn-info pull-right">Submit & PDF</button>
							
						 <a href="${pageContext.request.contextPath}/pdf?url=showBillPdf">PDF</a> 
							<button class="btn btn-info pull-right"
								style="margin-right: 5px;" onclick="submitBill()">Submit</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- END Main Content -->

	<footer>
	<p>2017 © Monginis.</p>
	</footer>

	<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
		class="fa fa-chevron-up"></i></a>

	<script type="text/javascript">
			function submitBill() {

				//submitBillForm.submit();
			}
		</script>


	<script type="text/javascript">
		function generateNewBill() {

			var isValid = validate();

			if (isValid) {
				var selectedFr = $("#selectFr").val();
				var routeId=$("#selectRoute").val();
				
				var selectedMenu = $("#selectMenu").val();
				var deliveryDate = $("#deliveryDate").val();

				$('#loader').show();

				$
						.getJSON(
								'${getBillList}',

								{
									fr_id_list : JSON.stringify(selectedFr),
									menu_id_list : JSON.stringify(selectedMenu),
									deliveryDate : deliveryDate,
									route_id:routeId,
									ajax : 'true'

								},
								function(data) {

									$('#table_grid td').remove();
									$('#loader').hide();

									if (data == "") {
										alert("No records found !!");

									}

									$
											.each(
													data,
													function(key, bill) {
														
														if(bill.orderQty>0){

														var index = key + 1;

														var tr = "<tr>";

														var index = "<td>&nbsp;&nbsp;&nbsp;"
																+ index
																+ "</td>";

														var frName = "<td>&nbsp;&nbsp;&nbsp;"
																+ bill.frName
																+ "</td>";

														var menuTitle = "<td>&nbsp;&nbsp;&nbsp;"
																+ bill.menuTitle
																+ "</td>";

														var itemId = "<td>&nbsp;&nbsp;&nbsp;"
																+ bill.itemId
																+ "</td>";

														var itemName = "<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
																+ bill.itemName
																+ "</td>";

														var orderQty = "<td align=center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
																+ bill.orderQty
																+ "</td>";

														 var billQty = "<td align=center><input type=number min=0 max=500 class=form-control   onkeyup= updateTotal("
																+ bill.orderId + ","
																+ bill.orderRate + ") onchange= updateTotal("+ bill.orderId+ ","+ bill.orderRate+ ")  id= billQty"+ bill.orderId+ " name=billQty"+bill.orderId+" value = "+ bill.orderQty+ "></td>"; 
																
																//var billQty = "<td align=center><input name=newId id=newId value=21 type=number ></td>";

														
														var expiryDate = "<td></td>";

														var orderRate = "<td align=center id=billRate"+bill.orderId+"  value="
																+ bill.orderRate
																+ ">"
																+ bill.orderRate
																+ "</td>";

														var total = parseFloat(bill.orderRate)
																* parseFloat(bill.orderQty);

														var totaLBill = "<td align=center id=billTotal"+bill.orderId+">"
																+ total
																+ "</td>";

														var trclosed = "</tr>";

														$('#table_grid tbody')
																.append(tr);
														$('#table_grid tbody')
																.append(index);
														$('#table_grid tbody')
																.append(frName);
														$('#table_grid tbody')
																.append(
																		menuTitle);
														$('#table_grid tbody')
																.append(itemId);
														$('#table_grid tbody')
																.append(
																		itemName);
														$('#table_grid tbody')
																.append(
																		orderQty);
														$('#table_grid tbody')
																.append(billQty);
														$('#table_grid tbody')
																.append(
																		expiryDate);
														$('#table_grid tbody')
																.append(
																		orderRate);
														$('#table_grid tbody')
																.append(
																		totaLBill);

														$('#table_grid tbody')
																.append(
																		trclosed);
														
													}

													})

								});

			}
		}
	</script>

	<script type="text/javascript">
		function validate() {

			var selectedFr = $("#selectFr").val();
			var selectedMenu = $("#selectMenu").val();
			var selectedRoute = $("#selectRoute").val();


			var isValid = true;

			if (selectedFr == "" || selectedFr == null  ) {
 
				if(selectedRoute=="" || selectedRoute ==null ) {
					alert("Please Select atleast one ");
					isValid = false;
				}
				//alert("Please select Franchise/Route");
 
			} else if (selectedMenu == "" || selectedMenu == null) {

				isValid = false;
				alert("Please select Menu");

			}
			return isValid;

		}
	</script>

	<script type="text/javascript">
		function updateTotal(orderId, rate) {
			
			var newQty = $("#billQty" + orderId).val();

			var total = parseFloat(newQty) * parseFloat(rate);


			 $('#billTotal'+orderId).html(total);
		}
	</script>



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
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/chosen-bootstrap/chosen.jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-inputmask/bootstrap-inputmask.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-tags-input/jquery.tagsinput.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-pwstrength/jquery.pwstrength.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-fileupload/bootstrap-fileupload.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-duallistbox/duallistbox/bootstrap-duallistbox.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/dropzone/downloads/dropzone.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/clockface/js/clockface.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/date.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-switch/static/js/bootstrap-switch.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/ckeditor/ckeditor.js"></script>

	<!--flaty scripts-->
	<script src="${pageContext.request.contextPath}/resources/js/flaty.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/flaty-demo-codes.js"></script>
</body>
</html>