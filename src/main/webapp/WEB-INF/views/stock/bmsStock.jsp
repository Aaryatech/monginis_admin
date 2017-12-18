<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<style>
div.panel {
	padding: 0 18px;
	background-color: white;
	max-height: 0;
	overflow: hidden;
	transition: max-height 0.2s ease-out;
}

.disableClick {
	pointer-events: none;
}

#disableMe {
	pointer-events: none;
}
</style>

<!-- <script>
  $( function() {
    $( "#monthPicker" ).datepicker({ dateFormat: 'mm/yy' });
  } );
  
 
  </script> -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Dashboard - MONGINIS Admin</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<!--base css styles-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/loader.css">


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

<script type="text/javascript"
	src="/adminpanel/resources/assets/bootstrap-datepicker/js/bootstrap-monthpicker.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap/css/style.css" />

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
						<i class="fa fa-file-o"></i>BMS
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
								<i class="fa fa-bars"></i>Search BMS Stock
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
							<form
								action="${pageContext.request.contextPath}/showAccountGrnDetails"
								class="form-horizontal" method="get" id="validation-form">




								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Select
										View Option Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<select name="selectStock" class="form-control chosen"
											tabindex="6" id="selectStock" onchange="showDiv(this)"
											required>

											<option value="-1">Select Option</option>
											<option value="1" id="currentStock">Get Current Stock</option>
											<option value="2" id="monthStock">Get Stock Between Month</option>
											<option value="3" id="dateStock">Get Stock Between Dates</option>

										</select>
									</div>
									<input type="button" class="btn btn-primary" value="Search">

								</div>


								<div class="form-group">
									<div>
										<div class="colOuter" style="display: none"
											id=select_month_year>
											<div class="col-md-2">
												<div class="col1title">Select Month From :</div>
											</div>
											<div class="col-md-2" align="left">

												<input type='text' placeholder="Select From Month"
													name="from_stockdate" required class="datepicker" />
											</div>

											<div class="col3"></div>

											<div class="col-md-2">
												<div class="col1title">To :</div>
											</div>
											<div class="col-md-2" align="left">
												<input type='text' placeholder="Select To Month"
													name="to_stockdate" required class="datepicker" />
											</div>

										</div>

										<div class="colOuter" style="display: none" id=select_date>
											<div class="col-md-2">
												<div class="col1title">From Date:</div>
											</div>
											<div class="col-md-2" align="left">

												<input id="fromdatepicker" class="form-control date-picker"
													placeholder="From Date" name="from_datepicker" type="text">

											</div>

											<div class="col3"></div>

											<div class="col-md-2">
												<div class="col1title">To Date:</div>
											</div>
											<div class="col-md-2" align="left">
												<input id="todatepicker" class="form-control date-picker"
													placeholder="To Date" name="to_datepicker" type="text">

											</div>

										</div>


									</div>


								</div>
							</form>

							<form action="${pageContext.request.contextPath}/getBmsStock"
								class="form-horizontal" method="get" id="validation-form">




								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i> BMS Stock List
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
										</div>
									</div>

									<div class="box-content">

										<div class="clearfix"></div>
										<div class="table-responsive" style="border: 0">
											<table width="100%"
												class="table table-advance table-responsive table-position"
												id="table1">
												<thead>
													<tr>

														<th>Sr No</th>
														<th>Material Type</th>
														<th>Material Name</th>
														<th>UOM</th>
														<th>Opening Stock</th>
														<th>Store Issue Qty</th>
														<th>Store Rej Qty</th>
														<th>Mix Rec Qty</th>
														<th>Mix Rec Rej Qty</th>
														<th>Mix Issue Qty</th>
														<th>Mix Return Qty</th>
														<th>Mix Rej Qty</th>
														<th>Prod Issue Qty</th>
														<th>Prod Rej Qty</th>
														<th>Prod Return Qty</th>
														<th>Closing Qty</th>
														<th>Status</th>

													</tr>

												</thead>
												<tbody>
												
												<tr>
												<td>
												<c:out value="${a }"></c:out>
												</td>
												<td>
												<c:out value="${a }"></c:out>
												</td>
												<td>
												<c:out value="${a }"></c:out>
												</td>
												<td>
												<c:out value="${a }"></c:out>
												</td>
												<td>
												<c:out value="${a }"></c:out>
												</td>
												<td>
												<c:out value="${a }"></c:out>
												</td>
												<td>
												<c:out value="${a}"></c:out>
												</td>
												<td>
												<c:out value="${a }"></c:out>
												</td>
												<td>
												<c:out value="${ a}"></c:out>
												</td>
												<td>
												<c:out value="${ a}"></c:out>
												</td>
												<td>
												<c:out value="${a }"></c:out>
												</td>
												<td>
												<c:out value="${a }"></c:out>
												</td>
												<td>
												<c:out value="${a }"></c:out>
												</td>
												<td>
												<c:out value="${a }"></c:out>
												</td>
												<td>
												<c:out value="${a }"></c:out>
												</td>
												
												</tr>

												</tbody>

											</table>
										</div>

										<!-- this is for ajax call<input type="submit" class="btn btn-primary" value="Submit"
										id="callSubmit" onclick="callSubmitGrn(); getGrnId();"> -->


										<div
											class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-2">
											<input type="submit" value="Submit" class="btn btn-primary">

											<input type="submit" value="Day End Process"
												class="btn btn-warning"> <input type="submit"
												value=" Store BOM" class="btn btn-success">


										</div>
										<!-- </form> -->

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
		$("#datepicker").datepicker({
			format : "mm-yyyy",
			startView : "months",
			minViewMode : "months"
		});
	</script>
	<script>
		function showDiv(elem) {
			if (elem.value == 1) {
				document.getElementById('select_month_year').style = "display:none";
				document.getElementById('select_date').style = "display:none";
			} else if (elem.value == 2) {
				document.getElementById('select_month_year').style.display = "block";
				document.getElementById('select_date').style = "display:none";
			} else if (elem.value == 3) {
				document.getElementById('select_date').style.display = "block";
				document.getElementById('select_month_year').style = "display:none";
			}
		}
	</script>
	<script>
		$(document)
				.ready(
						function() {
							$('#txtDate')
									.datepicker(
											{
												changeMonth : true,
												changeYear : true,
												dateFormat : 'mm/yy',

												onClose : function() {
													var iMonth = $(
															"#ui-datepicker-div .ui-datepicker-month :selected")
															.val();
													var iYear = $(
															"#ui-datepicker-div .ui-datepicker-year :selected")
															.val();
													$(this).datepicker(
															'setDate',
															new Date(iYear,
																	iMonth, 1));
												},

												beforeShow : function() {
													$('#ui-datepicker-div')
															.addClass(
																	'hide-calendar');

													if ((selDate = $(this)
															.val()).length > 0) {
														iYear = selDate
																.substring(
																		selDate.length - 4,
																		selDate.length);
														iMonth = jQuery
																.inArray(
																		selDate
																				.substring(
																						0,
																						selDate.length - 5),
																		$(this)
																				.datepicker(
																						'option',
																						'monthNames'));
														$(this).datepicker(
																'option',
																'defaultDate',
																new Date(iYear,
																		iMonth,
																		1));
														$(this).datepicker(
																'setDate',
																new Date(iYear,
																		iMonth,
																		1));
													}
												}
											});
						});
	</script>

</body>
</html>