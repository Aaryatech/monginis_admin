<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 



 
<style>
#disableMe {
	pointer-events: none;
}
</style>
 <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
 <jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

 
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
						<i class="fa fa-file-o"></i>Bill of Material Request Detailed
					</h1>
				</div>
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i>Bill of Material Request Detailed
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/getBomListBmsToStore">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>

						</div>


						<div class="box-content">

							<form id="completproduction"
								action="${pageContext.request.contextPath}/approvedBomFromStore"
								method="post">
								<div class="box-content">
									<div class="col-md-3">Bill Of Material Request Date</div>

									<div class="col-md-3">
										<input type="text" id="mix_date" name="mix_date"
											value="<fmt:formatDate pattern = "dd-MM-yyyy" value = "${billOfMaterialHeader.reqDate}" />"
											class="form-control" readonly>
									</div>



								</div>
								<br>

								<div class="box-content">

									<div class="col-md-3">Status</div>
									<div class="col-md-3">
									<c:choose>
									<c:when test="${billOfMaterialHeader.status==0}">
									<c:set var="sts" value="Pending"></c:set>
									</c:when>
									<c:when test="${billOfMaterialHeader.status==1}">
										<c:set var="sts" value="Approved"></c:set>
									</c:when>
									<c:when test="${billOfMaterialHeader.status==2}">
										<c:set var="sts" value="Rejected"></c:set>
									</c:when>
									<c:when test="${billOfMaterialHeader.status==3}">
										<c:set var="sts" value="Approved Rejected"></c:set>
									</c:when>
									<c:when test="${billOfMaterialHeader.status==4}">
										<c:set var="sts" value="Request Closed"></c:set>
									</c:when>
									</c:choose>
										<input type="text" id="statuss" name="status"
											value="${sts}" class="form-control"
											readonly>
									</div>
								</div>
								<br>

								<div class="box-content">


									<div class="col-md-3">From Department</div>
									<div class="col-md-3">
										<input class="form-control" id="time_slot" size="16"
											type="text" name="time_slot"
											value="${billOfMaterialHeader.fromDeptName}" readonly />
									</div>


								</div>
								<br>
								<div class="box-content">


									<div class="col-md-3">To Department</div>
									<div class="col-md-3">
										<input class="form-control" id="time_slot" size="16"
											type="text" name="time_slot"
											value="${billOfMaterialHeader.toDeptName}" readonly />
									</div>
									<input class="form-control " id="settingvalue" size="16"
											 type="hidden" name="settingvalue" value="${settingvalue}" readonly/>


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
														<th>Name</th>
														<th>request Qty</th>
														<th>issue Qty</th>
														<c:choose>
															<c:when test="${billOfMaterialHeader.status!=0}">
																<th>Return Qty</th>
																<th>Reject Qty</th>
															</c:when>
														</c:choose>



													</tr>
												</thead>
												<tbody class="abc">
													<c:set var="srNo" value="0" />
													<c:forEach items="${bomwithdetaild}" var="bomwithdetaild"
														varStatus="count">

														<tr>
															<td><c:out value="${count.index+1}" /></td>
															<c:set var="srNo" value="${srNo+1}" />
															<td><c:out value="${bomwithdetaild.rmName}" /></td>

															<td><c:out value="${bomwithdetaild.rmReqQty}" /></td>

															<c:choose>
																<c:when test="${billOfMaterialHeader.status==0}">
																	<td><input type="text"
																		name='issue_qty<c:out
																value="${bomwithdetaild.reqDetailId}" />'
																		class="form-control"
																		value=<c:out
																value="${bomwithdetaild.rmIssueQty}" />></td>
																</c:when>
																<c:otherwise>
																	<td><c:out value="${bomwithdetaild.rmIssueQty}" /></td>
																</c:otherwise>
															</c:choose>

															<c:choose>
																<c:when test="${billOfMaterialHeader.status!=0}">

																	<td><c:out value="${bomwithdetaild.returnQty}" /></td>

																	<td><c:out value="${bomwithdetaild.rejectedQty}" /></td>


																</c:when>
															</c:choose>
													</c:forEach>


												</tbody>
											</table>
										</div>
									</div>
								</div>



								<c:choose>
									<%-- <c:when test="${billOfMaterialHeader.status==0 && deptId==billOfMaterialHeader.toDeptId}"> --%>
									<c:when test="${billOfMaterialHeader.status==0}">
										<div align="center" class="form-group">
											<div
												class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
                                          <c:choose><c:when test="${flag==1}">
												<input type="submit" class="btn btn-primary"
													value="Approved">
													</c:when>
										</c:choose> 
											</div>
										</div>
									</c:when>
									
									<%-- <c:when test="${billOfMaterialHeader.status==1 && deptId==billOfMaterialHeader.fromDeptId}"> --%>
									<c:when test="${billOfMaterialHeader.status==1}">
										<div align="center" class="form-group">
											<div
												class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">

												<a href="${pageContext.request.contextPath}/rejectiontoStore?reqId=${billOfMaterialHeader.reqId}&settingvalue=${settingvalue}"> 
													<input type="button" class="btn btn-primary" value="For Rejection And return"></a> 

											</div>
										</div>


									</c:when>




									<%-- <c:when test="${billOfMaterialHeader.status ==2 && deptId==billOfMaterialHeader.toDeptId}"> --%>
									<c:when test="${billOfMaterialHeader.status ==2}">
										<div align="center" class="form-group">
											<div
												class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">

												<%-- <a
													href="${pageContext.request.contextPath}/rejectiontoBms?reqId=${billOfMaterialHeader.reqId}"><i
													class="fa fa-check"></i>For The Rejection And Return</a> --%> 
													<a href="${pageContext.request.contextPath}/approveRejectedByStore?reqId=${billOfMaterialHeader.reqId}"><i
													class="fa fa-check"></i>Approve Rejected</a>

											</div>
										</div>


									</c:when>



									<%-- <c:when test="${billOfMaterialHeader.status eq '3'}">
										<div align="center" class="form-group">
											<div
												class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">

												<a
													href="${pageContext.request.contextPath}/rejectiontoBms?reqId=${billOfMaterialHeader.reqId}"
													id="disableMe"><i class="fa fa-check"></i>For The
													Rejection And Return</a> <a
													href="${pageContext.request.contextPath}/approveRejected?reqId=${billOfMaterialHeader.reqId}"
													id="disableMe"><i class="fa fa-check"></i>Approve
													Rejected</a>

											</div>
										</div>


									</c:when> --%>

								</c:choose>




								<div class="box-content"></div>
								<br>
								<br>
								<br>



							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- END Main Content -->
		<footer>
		<p>2019 © MONGINIS.</p>
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