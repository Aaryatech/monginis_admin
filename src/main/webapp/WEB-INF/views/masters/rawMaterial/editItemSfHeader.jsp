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
	href="${pageContext.request.contextPath}/resources/css/loader.css">

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
	<c:url var="getRmSubCategory" value="/getRmSubCategory" />
	<c:url var="getRmCategory" value="/getRmCategory" />

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
						<i class="fa fa-file-o"></i>Semi Finished Item Edit
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
								<i class="fa fa-bars"></i>Edit Item
							</h3>
							<div class="box-tool">
								<a href="">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>

						</div>


						<div class="box-content">
							<form action="${pageContext.request.contextPath}/editSfHeader" method="post"
								class="form-horizontal" id="validation-form">

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label"> SF Name</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="sf_item_name" id="sf_item_name"
											class="form-control" placeholder="SF Name" value="${sfName}"
											data-rule-required="true" />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">SF Type
									</label>
									<div class="col-sm-6 col-lg-4 controls">

										<select name="sf_item_type" id="sf_item_type"
											class="form-control" placeholder="SF Type"
											data-rule-required="true">
											<option value="1">Select SF Type</option>
											<c:forEach items="${sfTypeList}" var="sfTypeList"
												varStatus="count">
												
												<c:choose>
												<c:when test="${editHeader.sfType eq sfTypeList.id}">
													<option selected value="${sfTypeList.id}"><c:out value="${sfTypeList.sfTypeName}"/></option>

												</c:when>
												<c:otherwise>
													<option value="${sfTypeList.id}"><c:out value="${sfTypeList.sfTypeName}"/></option>
												</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
									</div>

								</div>

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">SF UOM</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="sf_item_uom" id="sf_item_uom"
											class="form-control" placeholder="SF UOM"
											data-rule-required="true">
											<option value="1">Select UOM</option>
											<c:forEach items="${rmUomList}" var="rmUomList"
												varStatus="count">
												<c:choose>
												<c:when test="${editHeader.sfUomId eq rmUomList.uomId}">
													<option selected value="${rmUomList.uomId}"><c:out value="${rmUomList.uom}"/></option>
												</c:when>
												<c:otherwise>
													<option value="${rmUomList.uomId}"><c:out value="${rmUomList.uom}"/></option>

												</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
									</div>

									<label class="col-sm-3 col-lg-2 control-label">SF
										Weight </label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="sf_item_weight" id="sf_item_weight" value="${editHeader.sfWeight}"
											class="form-control" placeholder="Specification "
											data-rule-required="true" />
									</div>
								</div>

								<div class="form-group">


									<label class="col-sm-3 col-lg-2 control-label">Stock
										Qty</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="sf_stock_qty" id="sf_stock_qty"
											class="form-control" placeholder="Weight"
											data-rule-required="true" value="${editHeader.stockQty}"
											onKeyPress="return isNumberCommaDot(event)" />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Reorder
										Level Qty </label>

									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="sf_reorder_level_qty"
											id="sf_reorder_level_qty" class="form-control"
											placeholder="Max Qty " data-rule-required="true"
											value="${editHeader.reorderLevelQty}"
											onKeyPress="return isNumberCommaDot(event)" />
									</div>

								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Min
										Level Qty </label>

									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="sf_min_qty" id="sf_min_qty"
											class="form-control" placeholder="Pack Qty"
											data-rule-required="true"
											value="${editHeader.minLevelQty}"
											onKeyPress="return isNumberCommaDot(event)" />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Max
										Level Qty </label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="sf_max_qty" id="sf_max_qty"
											class="form-control" placeholder="Min Qty"
											data-rule-required="true"
											value="${editHeader.maxLevelQty}"
											onKeyPress="return isNumberCommaDot(event)" />
									</div>
								</div>

								<div class="row">
									<div class="col-md-12" style="text-align: center">
										<input type="submit" class="btn btn-info" value="Submit Edit">

									</div>
								</div>

						<%-- 		<div class="clearfix"></div>
								<div class="table-responsive" style="border: 0">
									<table width="100%" class="table table-advance" id="table1">
										<thead>
											<tr>

												<th width="140" style="width: 30px" align="left">Sr No</th>
												<th width="138" align="left">SF Name</th>
												<th width="120" align="left">SF Type</th>
												<th width="120" align="left">SF Weight</th>

												<th width="120" align="left">Min Level Qty</th>
												<th width="120" align="left">Reorder Level Qty</th>

												<th width="120" align="left">Action</th>
												<!-- 	<th width="140" align="left">GST %</th> -->

											</tr>

										</thead>

										<tbody>

											<c:forEach items="${itemHeaderList}" var="itemHeaderList"
												varStatus="count">

												<tr>
													<td><c:out value="${count.index+1}" /></td>

													<td align="left"><c:out
															value="${itemHeaderList.sfName}" /></td>


													<td align="left"><c:out
															value="${itemHeaderList.sfTypeName}" /></td>

													<td align="left"><c:out
															value="${itemHeaderList.sfWeight}" /></td>

													<td align="left"><c:out
															value="${itemHeaderList.minLevelQty}" /></td>

													<td align="left"><c:out
															value="${itemHeaderList.reorderLevelQty}" /></td>

													<td><a
														href="${pageContext.request.contextPath}/showAddSfItemDetail/${itemHeaderList.sfId}/${itemHeaderList.sfName}/${itemHeaderList.sfTypeName}"
														class="action_btn"> <abbr title="Details"><i
																class="fa fa-list"></i></abbr></a> <a
														href="${pageContext.request.contextPath}/editSfItemDetail/${itemHeaderList.sfId}/${itemHeaderList.sfName}/${itemHeaderList.sfTypeName}"
														class="action_btn"> <abbr title="Details"><i
																class="fa fa-edit"></i></abbr></a></td>

												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div> --%>

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
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/common.js"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>



</body>
</html>
