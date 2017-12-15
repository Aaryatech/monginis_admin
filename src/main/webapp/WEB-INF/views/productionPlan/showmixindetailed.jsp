<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Mix Request Detailed</title>
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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


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
	<c:url var="gateEntryList" value="/gateEntryList"></c:url>
	<c:url var="withPoRef" value="/withPoRef"></c:url>
	<c:url var="withPoRefDate" value="/withPoRefDate"></c:url>

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
						<i class="fa fa-file-o"></i>Mix Request Detailed
					</h1>
				</div>
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i>Mix Request Detailed
							</h3>


						</div>


						<div class="box-content">

							<form id="completproduction"
								action="${pageContext.request.contextPath}/updateProdctionQty"
								method="post">
								<div class="box-content">
									<div class="col-md-2">Mix Request Date</div>

									<div class="col-md-3">
										<input type="text" id="mix_date" name="mix_date"
											value="<fmt:formatDate pattern = "dd-MM-yyyy" value = "${mixheader.mixDate}" />"
											class="form-control" readonly>
									</div>



								</div>
								<br>

								<div class="box-content">

									<div class="col-md-2">Status</div>
									<div class="col-md-3">
										<input type="text" id="status" name="status"
											value="${mixheader.status}" class="form-control" readonly>
									</div>
								</div>
								<br>

								<div class="box-content">


									<div class="col-md-2">Time Slot</div>
									<div class="col-md-3">
										<input class="form-control" id="time_slot" size="16"
											type="text" name="time_slot" value="${mixheader.timeSlot}"
											readonly />
									</div>

								</div>
								<br>

								<div class="box-content">

									<div class="col-md-2">Production Batch</div>
									<div class="col-md-3">
										<input type="text" id="production_batch"
											name="production_batch" value="${mixheader.productionBatch}"
											class="form-control" readonly>

									</div>
								</div>
								<br>




								<div class=" box-content">
									<div class="row">
										<div class="col-md-12 table-responsive">
											<table class="table table-bordered table-striped fill-head "
												style="width: 100%" id="table_grid">
												<thead>
													<tr>
														<th>Sr.No.</th>
														<th>Sf Name</th>
														<th>Received Qty</th>
														<th>Production Qty</th>
														<th>Mixing Date</th>


													</tr>
												</thead>
												<tbody>
													<c:set var="srNo" value="0" />
													<c:forEach items="${mixwithdetaild}" var="mixwithdetaild"
														varStatus="count">


														<tr>
															<td><c:out value="${count.index+1}" /></td>
															<c:set var="srNo" value="${srNo+1}" />
															<td><c:out value="${mixwithdetaild.sfName}" /></td>


															<td><c:out value="${mixwithdetaild.receivedQty}" /></td>

															<c:choose>
																<c:when test="${mixheader.status==0}">
																	<td><input type="text"
																		name='production_Qty<c:out
																value="${mixwithdetaild.mixing_detailId}" />'
																		class="form-control"
																		value=<c:out
																value="${mixwithdetaild.productionQty}" />></td>
																</c:when>
																<c:otherwise>
																	<td><c:out value="${mixwithdetaild.productionQty}" /></td>
																</c:otherwise>
															</c:choose>



															<td><c:out value="${mixwithdetaild.mixingDate}" /></td>


														</tr>
													</c:forEach>


												</tbody>
											</table>
										</div>
									</div>
								</div>



								<c:choose>
									<c:when test="${mixheader.status==0}">
										<div align="center" class="form-group">
											<div
												class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">

												<input type="submit" class="btn btn-primary"
													value="complet Production">
											</div>
										</div>
									</c:when>
								</c:choose>

								<c:choose>
									<c:when test="${mixheader.isBom==0}">
										<div align="center" class="form-group">
											<div
												class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
												<a
													href="${pageContext.request.contextPath}/showBom/${mixheader.mixId}/0/${date}/0"><i
													class="fa fa-check"></i> Req. BOM</a>

											</div>
										</div>
									</c:when>
								</c:choose>

								<c:choose>

									<c:when test="${mixheader.isBom == 1}">
										<button type="button" class="btn btn-primary"
											id="man_bom_button" onclick="goToManBom()">
											<i class="fa fa-check"></i>Manual Bom for Mixing
										</button>
									</c:when>

								</c:choose>


								<div class="box-content"></div>
								<br>
								<br>
								<br>

<input type="hidden" name="mixId" value="${mixheader.mixId}">
<input type="hidden" name="isMixing" value="1">

							</form>
						</div>
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

<script type="text/javascript">

$('#man_bom_button').click(function(){
    var form = document.getElementById("completproduction")
    form.action ="${pageContext.request.contextPath}/goToManualBom";
    form.submit();
});
</script>

</body>
</html>