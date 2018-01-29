<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 


 <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
 <jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
<body>
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
						<i class="fa fa-file-o"></i>Variance Detailed
					</h1>
				</div>
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i>Variance Detailed
							</h3>


						</div>


						<div class="box-content">

							<form id="completproduction"
								action="${pageContext.request.contextPath}/updateOrderQtyinPlan"
								method="post">
								<div class="box-content">
									<div class="col-md-2">Production Date</div>

									<div class="col-md-3">
										<input type="text" id="productionDate" name="productionDate"
											value="${postProdPlanHeader.productionDate}"
											class="form-control" readonly>
									</div>



								</div>
								<br>

								

								<div class="box-content">


									<div class="col-md-2">Time Slot</div>
									<div class="col-md-3">
										<input class="form-control" id="time_slot" size="16"
											type="text" name="time_slot" value="${postProdPlanHeader.timeSlot}"
											readonly />
									</div>

								</div>
								<br>

								<div class="box-content">

									<div class="col-md-2">Cat Id</div>
									<div class="col-md-3">
									<c:forEach items="${categoryList}" var="categoryList">
									 	<c:choose>
									 		<c:when test="${postProdPlanHeader.itemGrp1==categoryList.catId}">
									 			
									 			<input type="text" id="itemGrp1"
												name="itemGrp1" value="${categoryList.catName}"
												class="form-control" readonly>
									 		
									 		</c:when> 
										</c:choose>
									</c:forEach>
										<input type="hidden" id="itemGrp1"
											name="itemGrp1" value="${postProdPlanHeader.itemGrp1}"
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
														<th>Item Name</th>
														<th>Curent Closing</th>
														<th>plan Qty</th>
														<th>production Qty</th>
														<th>Order Qty</th>
														<th>Rejected Qty</th>
														<th>Remaining Production</th>
														


													</tr>
												</thead>
												<tbody>
													<c:set var="srNo" value="0" />
													<c:forEach items="${postProdPlanHeaderDetailed}" var="postProdPlanHeaderDetailed"
														varStatus="count">
														
														<tr>
															<td><c:out value="${count.index+1}" /></td>
															<c:set var="srNo" value="${count.index+1}" />
															
															<c:forEach items="${itemsList}" var="itemsList"  >
																<c:choose>
																	<c:when test="${itemsList.id == postProdPlanHeaderDetailed.itemId }">
																	<td><c:out value="${itemsList.itemName}" /></td>
																	</c:when>
																</c:choose>
															</c:forEach>
															<td><c:out value="${postProdPlanHeaderDetailed.curClosingQty}" /></td><!--  newly Added  -->
															
															<td><c:out value="${postProdPlanHeaderDetailed.planQty}" /></td>
															<td><c:out value="${postProdPlanHeaderDetailed.productionQty}" /></td>
															<td><c:out value="${postProdPlanHeaderDetailed.orderQty}" /></td>
															<td><c:out value="${postProdPlanHeaderDetailed.rejectedQty}" /></td>
															<td><c:out value="${postProdPlanHeaderDetailed.int4}" /></td>
														</tr>
													</c:forEach>
													
													
													
													
										
													<c:forEach items="${getVarianceorderlistforsort}" var="getVarianceorderlistforsort"
														varStatus="count">


														<tr>
															<td><c:out value="${srNo+1}" /></td>
															<c:set var="srNo" value="${srNo+1}" />
															<td><c:out value="${getVarianceorderlistforsort.itemName}" /></td>
															<td><c:out value="${0}" /></td>
															<td><c:out value="${0}" /></td>
															<td><c:out value="${0}" /></td>
															<td><c:out value="${0}" /></td>
															<td><c:out value="${0}" /></td>
															<td><c:out value="${getVarianceorderlistforsort.orderQty}" /></td>
															
															


														</tr>
													</c:forEach>




												</tbody>
											</table>
										</div>
									</div>
								</div>



								
									
									<div class="row">
									<div class="col-md-12" style="text-align: center">

												<input type="submit" class="btn btn-primary"
													value="complet Production">
													<a href="${pageContext.request.contextPath}/insertProductionPlanWithoutCompletProd">
													<input type="button" class="btn btn-primary" value="P 2 Production"></a>
													<a href="${pageContext.request.contextPath}/showVariencePdf"
													target="_blank"><input type="button" class="btn btn-primary" value="PDF"></a> 
									</div>
									</div>
													
											 
											</form>
											
										</div>
				 
								<div class="box-content"></div>
								<br>
								<br>
								<br> 
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



</body>
</html>