<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<body>


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
						<i class="fa fa-file-o"></i>Prod Detail
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
								<i class="fa fa-bars"></i> View Production Detail
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
							<form action="viewProd" class="form-horizontal"
								id="validation-form" method="post">



								<input type="hidden" name="mode_add" id="mode_add"
									value="add_att">

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Prod
										Date </label>
									<div class="col-sm-5 col-lg-3 controls">
										<input " type="text" name="from_date"
											value="${planHeader.productionDate}" />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Time
										Slot</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input type="text" value="${planHeader.timeSlot}"
											name="to_date" />
									</div>

								</div>

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Category
									</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input " type="text" name="from_date"
											value="${planHeader.catName}" required />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Prod ID</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input type="text" value="${planHeader.productionHeaderId}"
											name="to_date" />
									</div>

								</div>

								<div class="clearfix"></div>

								<div class="table-responsive" style="border: 0">
									<table width="100%" class="table table-advance" id="table1">
										<thead>
											<tr>

												<th width="17" style="width: 18px">Iten Name</th>
												<th width="150" align="left">Stock</th>
												<th width="150" align="left">Plan Qty</th>
												<th width="150" align="left">Order Qty</th>

												<th width="150" align="left">Actual Prod</th>
												<th width="150" align="left">Total Qty</th>

												<th width="180" style="width: 18px">Rejected Qty</th>

											</tr>
										</thead>
										<tbody>

											<c:forEach items="${planDetail}" var="planDetail">

												<tr>
													<td align="left"><c:out value="${planDetail.itemName}" /></td>
													
													<td align="left"><input align="left" type="text"  value="${planDetail.openingQty}"
														placeholder="Stock Qty" class="form-control" name="stk_qty"
														data-rule-required="true" style="width: 65px" /></td>

													<td align="left"><input align="left" type="text" name="plan_qty"
														placeholder="Plan Qty" class="form-control" value="${planDetail.planQty}"
														data-rule-required="true" style="width: 65px" /></td>
														
													<td align="left"><input align="left" type="text" name="order_qty"
														placeholder="Order Qty" class="form-control" value="${planDetail.orderQty}"
														data-rule-required="true" style="width: 65px" /></td>
														
													<td align="left"><input align="left" type="text" name="act_prod_qty"
														placeholder="Actual Prod" class="form-control" value="${planDetail.productionQty}"
														data-rule-required="true" style="width: 65px" /></td>
														
													<td align="left"><input align="left" type="text" name="total_qty"
														placeholder="Total Qty" class="form-control" value="total"
														data-rule-required="true" style="width: 65px" /></td>
														
													<td align="left"><input align="left" type="text" name="rej_qty"
														placeholder="Rejected Qty" class="form-control" value="0"
														data-rule-required="true" style="width: 65px" /></td>


													<%-- <td align="left"><a
														href="viewDetails/${planHeader.productionHeaderId}"><span
															class="glyphicon glyphicon-info-sign"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;

													</td> --%>
												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>

								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
										<button type="submit" class="btn btn-primary">
											<i class="fa fa-check"></i> Generate BOM
										</button>
									<!-- </div>
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2"> -->
																		&nbsp;&nbsp;&nbsp;&nbsp;
									
										<button type="submit" class="btn btn-primary">
											<i class="fa fa-check"></i> Mixing
										</button>
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
</body>
</html>