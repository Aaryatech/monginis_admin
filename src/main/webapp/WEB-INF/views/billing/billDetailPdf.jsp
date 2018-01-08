<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script>
	window.jQuery
			|| document
					.write('<script src="${pageContext.request.contextPath}/resources/assets/jquery/jquery-2.0.3.min.js"><\/script>')
</script>

<!--page specific css styles-->
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

	<c:url var="getGroup2ByCatId" value="/getGroup2ByCatId" />

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
						<i class="fa fa-file-o"></i> Bill Details PDF
					</h1>

				</div>
			</div>
			<!-- END Page Title -->



			<!-- BEGIN Main Content -->
			<div class="row">
				<div class="col-md-12">
					<div class="box">
						<!-- <div class="box-title">
							<h3>
								<i class="fa fa-bars"></i>View Bill Details for PDF
							</h3>
							<div class="box-tool">
								<a href="">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							<div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> <a data-action="close" href="#"><i
									class="fa fa-times"></i></a>
							</div>
						</div> -->


						<form action="addItemProcess" class="form-horizontal"
							method="post" id="validation-form" enctype="multipart/form-data">

							<%-- <div class="box-content">
							

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Bill No</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="bill_no" id="bill_no"
											placeholder="Bill No" class="form-control" value="${billNo}"
											data-rule-required="true" readonly="readonly" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label" for="item_name">Bill
										date</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="bill_date" id="bill_date"
											placeholder="Bill Date" class="form-control"
											value="${billDate}" data-rule-required="true"
											data-rule-minlength="3" readonly="readonly" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Franchise
										Name</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="fr_name" id="fr_name"
											placeholder="Franchisee Name" class="form-control"
											data-rule-required="true" readonly="readonly"
											value="${frName}" />
									</div>
								</div> --%>
							<c:forEach items="${billDetails}" var="frDetails"
								varStatus="count">
								<div class="box-content">

									<div class="clearfix"></div>
									<div class="table-responsive" style="border: 0">
										<table width="100%" class="table table-advance" id="table1">
											<thead>
												<tr>
													<!-- <th width="138" align="left">Fr Name</th>
													<th width="120" align="left">Invoice No</th>
													<th width="130" align="left">Fr Address</th>
													</tr> -->

													<h3>FR Name:${frDetails.frName}</h3>
													<h3>Invoice No:${frDetails.invoiceNo}</h3>
													<h3>Address:s${frDetails.frAddress}</h3>
												<tr>
													<th width="140" style="width: 30px" align="left">Sr No</th>
													<th width="138" align="left">Item Name</th>
													<th width="120" align="left">Group</th>
													<th width="130" align="right">Billed Qty</th>
													<th width="100" align="left">Base Rate</th>
													<th width="100" align="left">Amount</th>
													<th width="105" align="left">GST %</th>

													<c:choose>
														<c:when test="${frDetails.isSameState==1}">
															<th>SGST Rs</th>
															<th>CGST Rs</th>
														</c:when>
														<c:otherwise>
															<th>IGST Rs</th>
														</c:otherwise>
													</c:choose>
													<th width="105" align="left">Total Tax</th>
													<th width="130" align="left">Grand Total</th>
												</tr>
											</thead>
											<tbody>



												<%-- <tr>
													
													<td align="left"><c:out
																value="${frDetails.frName}" /></td>


														<td align="left"><c:out
																value="${frDetails.invoiceNo}" /></td>
																
																<td align="left"><c:out
																value="${frDetails.frAddress}" /></td>
																
																</tr> --%>
												<c:forEach items="${frDetails.billDetailsList}"
													var="billDetails">
													<tr>
														<td><c:out value="${count.index+1}" /></td>

														<td align="left"><c:out
																value="${billDetails.itemName}" /></td>


														<td align="left"><c:out
																value="${billDetails.catName}" /></td>


														<td align="center"><c:out
																value="${billDetails.billQty}" /></td>


														<td align="left"><c:out
																value="${billDetails.baseRate}" /></td>
														<td align="left"><c:out
																value="${billDetails.taxableAmt}" /></td>

														<c:choose>
															<c:when test="${frDetails.isSameState==1}">

																<c:set var="sgstPer" value="${billDetails.sgstPer}" />
																<c:set var="cgstPer" value="${billDetails.cgstPer}" />

																<td align="left"><c:out
																		value="${sgstPer + cgstPer}" /></td>

															</c:when>
															<c:otherwise>

																<td align="left"><c:out
																		value="${billDetails.igstPer}" /></td>
															</c:otherwise>
														</c:choose>

														<c:choose>
															<c:when test="${frDetails.isSameState==1}">

																<td align="left"><c:out value="${billDetails.sgstRs}" /></td>

																<td align="left"><c:out value="${billDetails.cgstRs}" /></td>

															</c:when>
															<c:otherwise>

																<td align="left"><c:out
																		value="${billDetails.igstRs}" /></td>
															</c:otherwise>
														</c:choose>


														<td align="left"><c:out
																value="${billDetails.totalTax}" /></td>
														<td align="center"><c:out
																value="${billDetails.grandTotal}" /></td>
														<!-- Total -->


														<!-- <td rowspan="1" align="left"> <input
																type="button" value="View"> <input type="button"
																value="Edit"> <input type="button"
																value="Cancel"></td> -->


														<!-- <td align="left"><label><input type="submit"
																	name="submit_button" id="submit_button"></label></td>  -->


													</tr>

												</c:forEach>
												<!-- </tr>
 -->
												<%--  </c:forEach>
 --%>
											</tbody>
										</table>
									</div>
								</div>
							</c:forEach>
							<a href="${pageContext.request.contextPath}/pdf?url=showBillPdf"
								target="_blank">PDF</a>
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