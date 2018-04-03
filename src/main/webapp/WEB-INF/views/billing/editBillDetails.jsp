<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>


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
								<i class="fa fa-bars"></i>Update Bill Details
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
								action="${pageContext.request.contextPath}/updateBillDetailsProcess"
								class="form-horizontal" method="post" id="validation-form"
								enctype="multipart/form-data">


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
											readonly="readonly" />
									</div>
								</div>



								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Franchisee
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

													<th width="140" style="width: 30px" align="left">Sr No</th>
													<th width="138" align="left">Item Name</th>
													<th width="120" align="left">Group</th>
													<th width="130" align="left">Order Qty</th>
													<th width="130" align="right">Billed Qty</th>
													<th width="100" align="left">MRP</th>
													<th width="100" align="left">Rate</th>
													<th width="105" align="left">SGST %</th>
													<th width="105" align="left">CGST %</th>
													<th width="105" align="left">Grand Total</th>
													<th width="130" align="left">Tax Amount</th>
													<th width="130" align="left">Taxable Amount</th>
													<th width="159" align="left">Remark</th>

												</tr>

											</thead>

											<tbody>

												<c:forEach items="${billDetails}" var="billDetails"
													varStatus="count">

													<tr>
													
														<td><c:out value="${count.index+1}"/></td>

														<td align="left"><c:out
																value="${billDetails.itemName}"/></td>


														<td align="left"><c:out	value="${billDetails.catName}"/></td>

														<td align="center"><c:out value="${billDetails.orderQty}"/></td>

														<td align="left"><input type="text"class="form-control"
															data-rule-number="true"
															name="billQty${billDetails.billDetailNo}"
															id="billQty${billDetails.billDetailNo}" style="width: 60px"
															value="${billDetails.billQty}" onkeyup="changeValues(${billDetails.baseRate},${billDetails.billDetailNo})"/></td>

														<td align="left"><c:out value="${billDetails.mrp}"/></td>

														<td align="left"><c:out value="${billDetails.rate}"/></td>

													

														<td align="left" id="sgstPer${billDetails.billDetailNo}"><c:out value="${billDetails.sgstPer}"/></td>
														<td align="left" id="cgstPer${billDetails.billDetailNo}"><c:out value="${billDetails.cgstPer}"/></td>

														<c:set var="billQty" value="${billDetails.billQty}"/>
														<c:set var="rate" value="${billDetails.rate}"/>

														<td align="left" id="grandTotal${billDetails.billDetailNo}"><c:out value="${billDetails.grandTotal}"/></td>

														<td align="left" id="totalTax${billDetails.billDetailNo}"  ><c:out
																value="${billDetails.totalTax}"/></td>

														<td align="center" id="taxableAmt${billDetails.billDetailNo}"  ><c:out
																value="${billDetails.taxableAmt}"/></td>

														<td align="left"><c:out value="${billDetails.remark}" /></td>

													</tr>
													
												</c:forEach>

											</tbody>
										</table>
									</div>
								</div>




								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<button type="submit" class="btn btn-primary">
											<i class="fa fa-check"></i> Update
										</button>
										<!--<button type="button" class="btn">Cancel</button>-->
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
		function changeValues(baseRate,detailNo) {
			
			var billQty=$("#billQty"+detailNo).val();
			
			var sgstPer=$("#sgstPer"+detailNo).text();
			var cgstPer=$("#cgstPer"+detailNo).text();
			
			var taxableAmt=parseFloat(billQty)*parseFloat(baseRate);
			
			var sgstRs=parseFloat(taxableAmt)*parseFloat(sgstPer)/100;
			var cgstRs=parseFloat(taxableAmt)*parseFloat(cgstPer)/100;
			
			var totalTax=parseFloat(sgstRs)+parseFloat(cgstRs);
			
			var grandTotal=parseFloat(totalTax)+parseFloat(taxableAmt);
			
			$("#taxableAmt"+detailNo).html(taxableAmt.toFixed(2));
			$("#totalTax"+detailNo).html(totalTax.toFixed(2));
			$("#grandTotal"+detailNo).html(grandTotal.toFixed(2));
		}
	</script>






</body>
</html>