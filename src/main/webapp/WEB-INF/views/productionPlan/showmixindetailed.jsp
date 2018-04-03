<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
	
	<c:url var="gateEntryList" value="/gateEntryList"></c:url>
	<c:url var="withPoRef" value="/withPoRef"></c:url>
	<c:url var="withPoRefDate" value="/withPoRefDate"></c:url> 
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
									<c:set var="mdate" value="${mixheader.mixDate}">
									
									</c:set>

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
									<c:choose>
										<c:when test="${mixheader.status==0}">
											<c:set var="sts" value="Pending"></c:set>
										</c:when>
										<c:when test="${mixheader.status==1}">
											<c:set var="sts" value="Start Production"></c:set>
										</c:when>
										<c:when test="${mixheader.status==2}">
											<c:set var="sts" value="Production Completed"></c:set>
										</c:when>
									</c:choose>
									
									
									
									<input type="text" id="status" name="status"
											value="${sts}" class="form-control" readonly>
										<input type="hidden" id="status" name="status"
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
														<th>Original Qty</th>
														<th>Auto Order Qty</th>
														<th>Received Qty</th>
														<th>Production Qty</th>
														<th>Rejected Qty</th> 
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
															<td style="text-align:right;"><c:out value="${mixwithdetaild.originalQty}" /></td>
															<td style="text-align:right;">  <c:out value="${mixwithdetaild.autoOrderQty}" /></td>

															<td style="text-align:right;"><c:out value="${mixwithdetaild.receivedQty}" /></td>

															<c:choose>
																<c:when test="${(mixheader.status!=2) and (deptId==15)}">
																	<td style="text-align:right;"><input type="text"
																		name='production_Qty<c:out
																		value="${mixwithdetaild.mixing_detailId}" />'
																		class="form-control"
																		value=<c:out value="${mixwithdetaild.receivedQty}" /> pattern="[+-]?([0-9]*[.])?[0-9]+" required></td>
																<td style="text-align:right;"><input type="text" 
																		name='rejected_Qty<c:out  
																		value="${mixwithdetaild.mixing_detailId}" />'
																		class="form-control"
																		value=<c:out value="${mixwithdetaild.rejectedQty}" /> pattern="[+-]?([0-9]*[.])?[0-9]+" required></td>
																
																</c:when>
																<c:otherwise>
																	<td style="text-align:right;"><c:out value="${mixwithdetaild.productionQty}" /></td>
																	<td style="text-align:right;"><c:out value="${mixwithdetaild.rejectedQty}" /></td>
																</c:otherwise>
															</c:choose>
															 
															<td style="text-align:right;"><fmt:formatDate pattern = "dd-MM-yyyy" value = "${mixwithdetaild.mixingDate}" /></td>


														</tr>
													</c:forEach>


												</tbody>
											</table>
										</div>
									</div>
								</div>

							<div class=" box-content">
								<div class="form-group">
									<div align="center" class="form-group">
										<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
											
								
						
											<c:choose>
												<c:when test="${(mixheader.status!=2) and (deptId==15)}">
													<c:choose>
														<c:when test="${mixheader.isBom==0}"> <!-- /showBom/${mixheader.mixId}/0/${date}/0/0 <--last 0 is cat id for mixing -->
															<a href="${pageContext.request.contextPath}/showBom/${mixheader.mixId}/0/${date}/0/0"><input type="button" class="btn btn-primary"
													value="Request to Bom"> </a>
 
														</c:when>
													</c:choose>
												</c:when>
											</c:choose>

											<c:choose> 
												<c:when test="${mixheader.isBom == 1}">
									  				<button type="button" class="btn btn-primary" 
									  				id="man_bom_button" onclick="goToManBom()">
											 Manual Bom for Mixing
													</button>
										 
												</c:when> 
											</c:choose>
											
											<c:choose>
												<c:when test="${(mixheader.status!=2) and (deptId==15)}">
										 			<input type="submit" class="btn btn-primary"
													value="Complete Production">
											    </c:when>
											</c:choose>
										</div>
								</div>
							</div>
						</div>

<input type="hidden" name="mixId" value="${mixheader.mixId}">
<input type="hidden" name="isMixing" value="1">


	<div style="text-align: center;">
								<a href="${pageContext.request.contextPath}/showMixReqPdf"
								target="_blank"><input type="button" value="PDF" class="btn btn-primary"/></a>
								</div>

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