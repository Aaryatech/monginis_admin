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
									<input class="form-control" id="poNo" size="16"
											type="text" name="poNo" value="${materialRecNote.poNo}"  readonly />
									</div>
								
				 
							
									<div class="col-md-2" >Po Date</div>
								 
									<div class="col-md-3"><input type="text" id="poDate" name="poDate" value="${materialRecNote.poDate}" class="form-control" readonly>
									</div>
									 
									</div><br> 
									</c:when>
								</c:choose>
								
									<div class="box-content">
									<div class="col-md-2">Booking Date</div>
									<div class="col-md-3">
									<input class="form-control" id="invBookDate" size="16"
											type="text" name="invBookDate" value="<fmt:formatDate pattern = "dd-MM-yyyy" value = "${materialRecNote.invBookDate}"/>"  readonly />
									</div>
								
				 
							
									<div class="col-md-2" >Invoice No.</div>
									<div class="col-md-3"><input type="text" id="invoiceNumber" name="invoiceNumber" value="${materialRecNote.invoiceNumber}" class="form-control" readonly>
									</div>
									
									 
							
							</div><br>
							<div class="box-content">
									<div class="col-md-2">Invoice Date</div>
									<div class="col-md-3">
									<input class="form-control" id="invDate" size="16"
											type="text" name="invDate" value="<fmt:formatDate pattern = "dd-MM-yyyy" value = "${materialRecNote.invDate}"/>"  readonly />
									</div>
								
				 
							
									<div class="col-md-2" >Basic Value</div>
									<div class="col-md-3"><input type="text" id="basicValue" name="basicValue" value="${materialRecNote.basicValue}" class="form-control" readonly>
									</div>
									
									 
							
							</div><br> 
							<div class="box-content">
									<div class="col-md-2">Disc Per</div>
									<div class="col-md-3">
									<input class="form-control" id="discPer" size="16"
											type="text" name="discPer" value="${materialRecNote.discPer}"  readonly />
									</div>
								
				 
							
									<div class="col-md-2" >Disc Amt</div>
									<div class="col-md-3"><input type="text" id="discAmt" name="discAmt" value="${materialRecNote.discAmt}" class="form-control" readonly>
									</div>
									
									 
							
							</div><br> 
							<div class="box-content">
									<div class="col-md-2">Disc Amt Per Item</div>
									<div class="col-md-3">
									<input class="form-control" id="discAmt2" size="16"
											type="text" name="discAmt2" value="${materialRecNote.discAmt2}"  readonly />
									</div>
								
				 
							<c:set var="freightAmt"><fmt:formatNumber type = "number"  minFractionDigits ="0" maxFractionDigits = "0" value = "${materialRecNote.freightAmt}"/></c:set>
									<div class="col-md-2" >Freight Amt</div>
									<div class="col-md-3"><input type="text" id="freightAmt" name="freightAmt" value="${freightAmt}" class="form-control" readonly>
									</div>
									
									 
							
							</div><br> 
							<div class="box-content">
							<c:set var="insuranceAmt"><fmt:formatNumber type = "number"  minFractionDigits ="0" maxFractionDigits = "0" value = "${materialRecNote.insuranceAmt}"/></c:set>
									<div class="col-md-2">Insurance Amt</div>
									<div class="col-md-3">
									<input class="form-control" id="insuranceAmt" size="16"
											type="text" name="insuranceAmt" value="${insuranceAmt}"  readonly />
									</div>
								
				 
							<div class="col-md-2">Other Discount</div>
									<div class="col-md-3">
									<c:set var="other2"><fmt:formatNumber type = "number"  minFractionDigits ="0" maxFractionDigits = "0" value = "${materialRecNote.other2}"/></c:set>
									<input class="form-control" id="other2" size="16"
											type="text" name="other2" value="${other2}"  readonly />
									</div>
									  
							
							</div><br> 
							<div class="box-content">
									<div class="col-md-2">Other Extra</div>
									<div class="col-md-3">
								<c:set var="other3"><fmt:formatNumber type = "number"  minFractionDigits ="0" maxFractionDigits = "0" value = "${materialRecNote.other3}"/></c:set>
									 <input class="form-control" id="other3" size="16" 
											type="text" name="other3" value="${other3}"  readonly />
									</div>
								
				 
							<div class="col-md-2">Other Extra 2</div>
									<div class="col-md-3">
									<c:set var="other4"><fmt:formatNumber type = "number"  minFractionDigits ="0" maxFractionDigits = "0" value = "${materialRecNote.other4}"/></c:set>
									<input class="form-control" id="other4" size="16"
											type="text" name="other4" value="${other4}"  readonly />
									</div>
									  
							
							</div><br> 
							<div class="box-content">
									 
				  
									<div class="col-md-2" >CGST Amt</div>
									<div class="col-md-3"><input type="text" id="cgst" name="cgst" value="${materialRecNote.cgst}" class="form-control" readonly>
									</div>
									
									<div class="col-md-2">SGST Amt</div>
									<div class="col-md-3">
									<input class="form-control" id="sgst" size="16"
											type="text" name="sgst" value="${materialRecNote.sgst}"  readonly />
									</div>
									 
							
							</div><br> 
							<div class="box-content">
									 
									<div class="col-md-2" >IGST Amt</div>
									<div class="col-md-3"><input type="text" id="cgst" name="cgst" value="${materialRecNote.igst}" class="form-control" readonly>
									</div>
									
									<div class="col-md-2">Cess Amt</div>
									<div class="col-md-3">
									<input class="form-control" id="cess" size="16"
											type="text" name="cess" value="${materialRecNote.cess}"  readonly />
									</div>
									 
							
							</div><br>
							<div class="box-content">
									
								 
									<div class="col-md-2" >Bill Amt</div>
									<div class="col-md-3"><input type="text" id="billAmount" name="billAmount" value="${materialRecNote.billAmount}" class="form-control" readonly>
									</div>
									
									<div class="col-md-2" >Round Off</div>
									<div class="col-md-3"><input type="text" id="roundOff" name="roundOff" value="${materialRecNote.roundOff}" class="form-control" readonly>
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
								<div style="overflow:scroll;height:100%;width:100%;overflow:auto">
									<table width="100%" border="0" class="table table-bordered table-striped fill-head "
										style="width: 100%" id="table_grid">
								<thead>
									<tr>
												<th>Sr.No.</th>
												<th>Item</th>
												<th>Verified Rate</th>
												<th>Received Quantity</th> 
												<th>Value</th>
												<th>Disc Per</th>
												<th>Disc Amt</th>
												<th>Insurance Amt</th>
												<th>Freight Amt</th>
												<th>other(Discount)</th> 
												<th>Taxable Amt</th> 
												<th>CGST Amt</th>
												<th>SGST Amt</th>
												<th>IGST Amt</th>
												<th>Other(Extra)</th> 
												<th>CESS Amt</th>
											 
									  

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
														<td><c:out value="${materialRecNoteDetail.varifiedRate}" /></td>
														<td><c:out value="${materialRecNoteDetail.recdQty}" /></td>
															
														<td><c:out value="${materialRecNoteDetail.value}" /></td>
														<td><c:out value="${materialRecNoteDetail.discPer}" /></td>
														<td><c:out value="${materialRecNoteDetail.discAmt}" /></td> 
														<td><c:out value="${materialRecNoteDetail.insurance_amt}" /></td>
														<td><c:out value="${materialRecNoteDetail.freightAmt}" /></td>
														<c:set var="disc"><fmt:formatNumber type = "number"  minFractionDigits ="2" maxFractionDigits = "2" value = "${materialRecNoteDetail.other1 + materialRecNoteDetail.other2}"/></c:set>
														<td><c:out value="${disc}" /></td> 
														<td><c:out value="${materialRecNoteDetail.amount}" /></td>
														<td><c:out value="${materialRecNoteDetail.cgstRs}" /></td>
														<td><c:out value="${materialRecNoteDetail.sgstRs}" /></td>
														<td><c:out value="${materialRecNoteDetail.igstRs}" /></td>
														<c:set var="extra"><fmt:formatNumber type = "number"  minFractionDigits ="2" maxFractionDigits = "2" value = "${materialRecNoteDetail.other3 + materialRecNoteDetail.other4}"/></c:set>
														<td><c:out value="${extra}" /></td> 
														<td><c:out value="${materialRecNoteDetail.cessRs}" /></td> 
														<%-- <c:set var="total"><fmt:formatNumber type = "number"  minFractionDigits ="2" maxFractionDigits = "2" value = "${materialRecNoteDetail.amount+materialRecNoteDetail.cgstRs+materialRecNoteDetail.sgstRs
 												 +materialRecNoteDetail.igstRs+materialRecNoteDetail.cessRs+extra}"/></c:set>  
 												 <td><c:out value="${total}" /></td>  --%>
											
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