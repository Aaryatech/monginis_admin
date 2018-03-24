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
						<i class="fa fa-file-o"></i> Gate Entry Detailed
					</h1>
				</div>
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i> Gate Entry Detailed
							</h3>
							
							 
							
						</div>
						
						
						<div class="box-content">

							<form id="submitMaterialStore" action="${pageContext.request.contextPath}/submitMaterialStore" method="post">
							<div class="box-content">
								<div class="col-md-2">Inward no.</div>
								<div class="col-md-3"><input type="text" id="mrn_no" name="mrn_no" value="${materialRecNote.mrnNo}" class="form-control" readonly>
								</div>
								
							<div class="col-md-2"> Gate Entry Date</div> 
							<div class="col-md-3">
							<input type="text" id="mrn_date" name="mrn_date" value="${materialRecNote.gateEntryDate}" class="form-control" readonly>
							
							</div>
					
							</div><br>
							
							<div class="box-content">
								<div class="col-md-2">Gate Entry Time</div> 
									<div class="col-md-3">
										<input type="text" id="mrn_date" name="mrn_date" value="${materialRecNote.gateEntryTime}" class="form-control" readonly>
							  		</div>
							
								<div class="col-md-2">Lr No.</div>
									<div class="col-md-3"><input type="text" id="transportId" name="transportId" value="${materialRecNote.lrNo}" class="form-control" readonly>
								</div>
								
							</div><br>
							
							
							<div class="box-content">
							
								
								<div class="col-md-2">Transporter</div> 
									<div class="col-md-3">
									<c:forEach items="${transporterList}" var="transporterList">
										<c:choose>
											<c:when test="${transporterList.tranId==materialRecNote.transportId}">
												<input type="text" id="transportId" name="transportId" value="${transporterList.tranName}" class="form-control" readonly>
									
											</c:when>
										</c:choose>
										
									</c:forEach>
										</div>
								
								<div class="col-md-2">Supplier</div>
									<div class="col-md-3">
									<c:forEach items="${supplierDetailsList}" var="supplierDetailsList">
										<c:choose>
											<c:when test="${supplierDetailsList.suppId==materialRecNote.supplierId}">
												<input type="text" id="transportId" name="transportId" value="${supplierDetailsList.suppName}" class="form-control" readonly>
									
											</c:when>
										</c:choose>
										
									</c:forEach>
									 
									</div>
								
				 
							</div><br>
							
							
							
							<div class="box-content">
							<div class="col-md-2">Vehicle No</div>
									<div class="col-md-3">
									<input class="form-control" id="vehicleNo" size="16"
											type="text" name="vehicleNo" value="${materialRecNote.vehicleNo}"  readonly />
									</div>
								
				 
							
									<div class="col-md-2" >No of Item</div>
									<div class="col-md-3"><input type="text" id="vehical_no" name="vehical_no" value="${materialRecNote.noOfItem}" class="form-control" readonly>
									</div>
									
									 
							
							</div><br> 
							<c:choose>
								<c:when test="${materialRecNote.status>0}">
									<div class="box-content">
										<div class="col-md-2">MRN Type</div>
										<div class="col-md-3">
										<input class="form-control" id="vehicleNo" size="16"
												type="text" name="vehicleNo" value="${materialRecNote.mrnType}"  readonly />
										</div>
								
				 
							
									<div class="col-md-2" >Against Po</div>
									<c:choose>
										<c:when test="${materialRecNote.apainstPo==1}">
										<c:set var="Po" value="YES"></c:set>
										</c:when>
										<c:otherwise>
										<c:set var="Po" value="No"></c:set>
										</c:otherwise>
									</c:choose>
									<div class="col-md-3"><input type="text" id="vehical_no" name="vehical_no" value="${Po}" class="form-control" readonly>
									</div> 
									</div><br> 
									
									<c:choose>
									<c:when test="${materialRecNote.apainstPo==1}">
									<div class="box-content">
									<div class="col-md-2">PO NO.</div>
									<div class="col-md-3">
									<input class="form-control" id="vehicleNo" size="16"
											type="text" name="vehicleNo" value="${materialRecNote.poNo}"  readonly />
									</div>
								
				 
							
									<div class="col-md-2" >Po Date</div>
								 
									<div class="col-md-3"><input type="text" id="vehical_no" name="vehical_no" value="${materialRecNote.poDate}" class="form-control" readonly>
									</div>
									 
									</div><br> 
									</c:when>
								</c:choose>
								
								</c:when>
							</c:choose>
							
							<c:choose>
							<c:when test="${materialRecNote.status==1}">
							<c:set var="stats" value="Approved By Store"></c:set>
							</c:when> 
							<c:when test="${materialRecNote.status==2}">
							<c:set var="stats" value="Reject By Director"></c:set>
							</c:when>
							<c:when test="${materialRecNote.status==3}">
							<c:set var="stats" value="Reject To Acc"></c:set>
							</c:when>
							<c:when test="${materialRecNote.status==4}">
							<c:set var="stats" value="Approved To Acc"></c:set>
							</c:when>
							<c:when test="${materialRecNote.status==5}">
							<c:set var="stats" value="Approved By Acc"></c:set>
							</c:when>
							</c:choose>
							
							<div class="box-content">
							<div class="col-md-2">Status</div>
									<div class="col-md-3">
									<input class="form-control" id="stats" size="16"
											type="text" name="stats" value="${stats}"  readonly />
									</div>
								
				  
							</div><br> 
						<div class=" box-content">
								<div class="row">	
							 
									<div class="col-md-2">Image</div>
									<div class="col-md-3">
										<div class="fileupload fileupload-new"
											data-provides="fileupload">
											<div class="fileupload-new img-thumbnail"
												style="width: 200px; height: 150px;">
												<img src="${imageUrl}${materialRecNote.photo1}"
											onerror="this.src='${pageContext.request.contextPath}/resources/img/No_Image_Available.jpg';"
											alt="" />
											</div>
											
										</div>

									</div>
								 
								 
									<div class="col-md-2">Image</div>
									<div class="col-md-3">
										<div class="fileupload fileupload-new"
											data-provides="fileupload">
											<div class="fileupload-new img-thumbnail"
												style="width: 200px; height: 150px;">
												<img src="${imageUrl}${materialRecNote.photo2}"
											onerror="this.src='${pageContext.request.contextPath}/resources/img/No_Image_Available.jpg';"
											alt="" />
											</div>
											
										</div>

									</div>
							 	
							</div>
							</div>
							<div class=" box-content">
								<div class="row">
								<div class="col-md-12 table-responsive">
									<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Item</th>
										<th>IN QTY</th>
										<c:choose>
											<c:when test="${materialRecNote.status>0}">
												<th>Stock Qty</th>
												<th>Rejected Qty</th>
											</c:when>
										</c:choose>
									  

									</tr>
								</thead>
									<tbody>
									 <c:set var = "srNo" value="0"/>
									<c:forEach items="${materialRecNote.materialRecNoteDetails}" var="materialRecNoteDetail"
													varStatus="count">
													
													 
													<tr>
														<td ><c:out value="${count.index+1}" /></td>
 														<c:set var = "srNo" value="${srNo+1}"/>
														<td><c:out
																value="${materialRecNoteDetail.rmName}" /></td>


														<td><c:out value="${materialRecNoteDetail.recdQty}" /></td>
														
																<c:choose>
																	<c:when test="${materialRecNote.status>0}">
																	<td ><c:out value="${materialRecNoteDetail.stockQty}" /></td>
																	<td ><c:out value="${materialRecNoteDetail.rejectedQty}" /></td>
																	</c:when>
															</c:choose>
																
														  
 												 
											
												</tr>
												</c:forEach>
								
													 
									</tbody>
									</table>
								</div>
								</div>
							</div>
							 
							 
							<div class="box-content">
							
							
							</div><br><br><br>
							
							

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
		 
</body>
</html>