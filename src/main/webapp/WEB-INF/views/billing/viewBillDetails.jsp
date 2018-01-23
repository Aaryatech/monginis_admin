<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<c:url var="getGroup2ByCatId" value="/getGroup2ByCatId" />

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
						<i class="fa fa-file-o"></i> Bill Details
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
								<i class="fa fa-bars"></i>View Bill Details
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showBillList">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							<!-- <div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> <a data-action="close" href="#"><i
									class="fa fa-times"></i></a>
							</div> -->
						</div>



						<div class="box-content">
							<form action="" class="form-horizontal" method="post"
								id="validation-form">


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
										date Name</label>
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
								</div>



								<div class="box-content">

									<div class="clearfix"></div>
									<div class="table-responsive" style="border: 0">
										<table width="100%" class="table table-advance" id="table1">
											<thead>
												<tr>
													<th class="col-sm-1">Sr No</th>
													<th class="col-md-1">Group</th>
													<th class="col-md-2">Item Name</th>
													<th class="col-sm-1">Ord Qty</th>
													<th class="col-sm-1">Bill Qty</th>
													<th class="col-sm-1">Rate</th>
													<th class="col-md-2">Taxable Amt</th>
													<th class="col-md-1">Sgst Rs</th>
													<th class="col-md-1">Cgst Rs</th>
													<th class="col-md-1">Igst Rs</th>
													<th class="col-sm-1">GST%</th>
													<th class="col-md-1">Total</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${billDetails}" var="billDetails"
													varStatus="count">
													<tr>
														<td class="col-sm-1"><c:out value="${count.index+1}" /></td>
														<td class="col-md-1"><c:out
																value="${billDetails.catName}" /></td>
														<td class="col-md-2"><c:out
																value="${billDetails.itemName}" /></td>

														<td class="col-sm-1" align="center"><c:out
																value="${billDetails.orderQty}" /></td>

														<td class="col-sm-1" align="center"><c:out
																value="${billDetails.billQty}" /></td>

														<td class="col-sm-1"><c:out
																value="${billDetails.baseRate}" /></td>
														<td class="col-md-2"><c:out
																value="${billDetails.taxableAmt}" /></td>


														<td class="col-md-1"><c:out
																value="${billDetails.sgstRs}" /></td>
														<td class="col-md-1"><c:out
																value="${billDetails.cgstRs}" /></td>
																<td class="col-md-1"><c:out
																value="${billDetails.igstRs}" /></td>

														<c:set var="sgstPer" value="${billDetails.sgstPer}" />
														<c:set var="cgstPer" value="${billDetails.cgstPer}" />
														<td class="col-md-1"><c:out value="${sgstPer + cgstPer}" /></td>

														<%-- <td align="left"><c:out
																value="${billDetails.totalTax}" /></td> --%>

														<td class="col-md-1"><c:out
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

											</tbody>
										</table>
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