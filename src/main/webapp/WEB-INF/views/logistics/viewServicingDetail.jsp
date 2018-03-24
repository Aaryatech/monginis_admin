<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<c:url var="addItemToListInOldItemList" value="/addItemToListInOldItemList"></c:url>
		<c:url var="updateRmQty0" value="/updateRmQty0"></c:url> 


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
					<i class="fa fa-file-o"></i>Invoice Bill Detailed
				</h1>
				<!-- <h4>Bill for franchises</h4> -->
			</div>
		</div>
		<!-- END Page Title -->

	 
		<!-- BEGIN Main Content -->
		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>Invoice Bill Detailed
				</h3>

			</div>

				<div class=" box-content">
					 
		<div class="box">
			<form id="submitPurchaseOrder"
				action="${pageContext.request.contextPath}/requestPOFinalByDirectore"
				method="post" enctype="multipart/form-data" onsubmit="return(validate());">
				<div class="box-content">
				<div class="col-md-2">Vehicle Or Machine </div>
										<c:choose>
													<c:when test="${viewServicingDetail.servType2==1}">
													<c:set var = "type" value='Vehicle'/>
													</c:when>
													
													<c:when test="${viewServicingDetail.servType2==2}">
													  <c:set var = "type" value="Machine"/>
													
													</c:when>  
												</c:choose>
				<div class="col-md-4"><input type="text" id="po_no" name="po_no" value="${type}" class="form-control" readonly>
				</div>
				 
				</div><br/>
				
			<div class="box-content">
												
					<div class="col-md-2">Bill No.  </div>
						<div class="col-md-4"><input type="text" id="po_no" name="po_no" value="${viewServicingDetail.billNo}" class="form-control" readonly>
					</div>
					<div class="col-md-2">Bill Date</div> 
						<div class="col-md-3">
							<input type="text" id="po_date" name="po_date" value="${viewServicingDetail.billDate}" class="form-control" readonly> 
					
					</div>
				</div><br/>
				
				<div class="box-content">
											
									<div class="col-md-2" >Type</div>
									<div class="col-md-4"> 
									<c:forEach items="${mechTypeList}" var="mechTypeList" varStatus="count">
											 	<c:choose>
											 		<c:when test="${mechTypeList.typeId==viewServicingDetail.typeId}">
											 			<input type="text" name="typeId" id="typeId" value="${mechTypeList.typeName}" class="form-control" readonly>
													 </c:when>
												 </c:choose>
											</c:forEach> 
										 
				 	
									</div>
									<div class="col-md-2">Service Type </div>
									<c:choose>
													<c:when test="${viewServicingDetail.servType==1}">
													<c:set var = "servType" value='Regular'/>
													</c:when>
													
													<c:when test="${viewServicingDetail.servType==2}">
													  <c:set var = "servType" value="Breakdown"/> 
													</c:when> 
												 
												</c:choose>
				<div class="col-md-3">
					<input type="text" name="servType" id="servType" value="${servType}" class="form-control" readonly>
				</div>
				 
			</div><br/>
			
			<div class="box-content">
				<div class="col-md-2">Servicing Date</div>
				<div class="col-md-4">
					<input type="text" name="kind_attn" id="kind_attn" value="${viewServicingDetail.servDate}" class="form-control" readonly>
				</div>
				<div class="col-md-2">Dealer</div>
				<div class="col-md-3">
							<c:forEach items="${dealerList}" var="dealerList" varStatus="count">
											 	<c:choose>
											 		<c:when test="${dealerList.dealerId==viewServicingDetail.dealerId}">
											 			<input type="text" name="dealerId" id="dealerId" value="${dealerList.dealerName}" class="form-control" readonly>
													 </c:when>
												 </c:choose>
											</c:forEach> 
				</div>
				</div><br/>
				
							<div class="box-content">
								<div class="col-md-2" >Vehicle No.</div>
													<div class="col-md-4">
													 
													<input type="text" name="vehNo" id="vehNo" value="${viewServicingDetail.vehNo}" class="form-control" readonly>
								
													</div>
													<div class="col-md-2">Service Advice Remainder </div>
								<div class="col-md-3">
									<input type="text" name="servAdviseRem"id="servAdviseRem" class="form-control" 
									value="${viewServicingDetail.servAdviseRem}" readonly>
								</div>
								 
							</div> <br/>
							 
						<div class="box-content">
								<div class="col-md-2" >Labour Charge</div>
									<div class="col-md-4">
										  
										<input type="text" name="labChrge" id="labChrge" class="form-control" 
											value="${viewServicingDetail.labChrge}" readonly>
										
									</div>
								<div class="col-md-2" >Total Discount</div>
									<div class="col-md-3">
										 <input type="text" name="totalDisc" id="totalDisc" value="${viewServicingDetail.totalDisc}" class="form-control" readonly>
									</div>
									
						</div>	<br/>
						
						<div class="box-content">
								<div class="col-md-2" >Total Extra Charge</div>
									<div class="col-md-4">
										  
										<input type="text" name="totalExtra" id="totalExtra" class="form-control" 
											value="${viewServicingDetail.totalExtra}" readonly>
										
									</div>
								 <div class="col-md-2" >Tax Amt</div>
									<div class="col-md-3">
										 <input type="text" name="taxAmt" id="taxAmt" value="${viewServicingDetail.taxAmt}" class="form-control" readonly>
									</div>
									
						</div>	<br/>
						
					 
						
						<div class="box-content">
								<div class="col-md-2" >Taxable Amt</div>
									<div class="col-md-4">
										  
										<input type="text" name="taxableAmt" id="taxableAmt" class="form-control" 
											value="${viewServicingDetail.taxableAmt}" readonly>
										
									</div>
								<div class="col-md-2" >Total</div>
									<div class="col-md-3">
										 <input type="text" name="total" id="total" value="${viewServicingDetail.total}" class="form-control" readonly>
									</div>
									
						</div><br/>
						
						<c:choose>
							<c:when test="${viewServicingDetail.servType2==1}">
								<div class="box-content">
								<div class="col-md-2" >Service Done Remainder</div>
									<div class="col-md-4">
										  
										<input type="text" name="servDoneRem" id="servDoneRem" class="form-control" 
											value="${viewServicingDetail.servDoneRem}" readonly>
										
									</div>
								<div class="col-md-2" >Part Total</div>
									<div class="col-md-3">
										 <input type="text" name="sprTot" id="sprTot" value="${viewServicingDetail.sprTot}" class="form-control" readonly>
									</div>
									
						</div>	<br/>	
							</c:when>
						</c:choose>
						<br>
							
							 
			
				<div class=" box-content">
					<div class="row">
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Part Name</th>
										<th>Group Name</th>
										<th>Qty</th>
										<th>Rate</th>
										<th>Discount</th>
										<th>Extra Charges</th>
										<th>Taxable Amt</th>
										<th>Tax Amt</th>
										<th>Total</th>
										

									</tr>
								</thead>
								<tbody>
								
								<c:forEach items="${viewServicingDetail.servDetail}" var="servDetail"
														varStatus="count">


														<tr>
															<td><c:out value="${count.index+1}" /></td>
															<c:set var="srNo" value="${srNo+1}" />
															
															<c:forEach items="${sprPartList}" var="sprPartList" >
																<c:choose>
																	<c:when test="${servDetail.sprId==sprPartList.sprId}">
																	<td><c:out value="${sprPartList.sprName}" /></td>
																	</c:when>
																</c:choose>
															</c:forEach> 
														
															<c:forEach items="${sprGroupList}" var="sprGroupList" >
																	<c:choose>
																		<c:when test="${sprGroupList.groupId==servDetail.groupId}">
																		<td><c:out value="${sprGroupList.groupName}" /></td>
																		</c:when>
																	</c:choose>
															</c:forEach> 
															<td><c:out value="${servDetail.sprQty}" /></td>
 
															<td><c:out value="${servDetail.sprRate}" /></td>
															<td><c:out value="${servDetail.disc}" /></td>
															<td><c:out value="${servDetail.extraCharges}" /></td>
															
															<td><c:out value="${servDetail.sprTaxableAmt}" /></td>
															<td><c:out value="${servDetail.sprTaxAmt}" /></td>
															<td><c:out value="${servDetail.total}" /></td>
		                                                    

														</tr>
													</c:forEach>
								
								

								</tbody>
							</table>
						</div>
					</div>

 
		</div>
		 
				 	 
									 
									
									<div class="row">
						<div class="col-md-12" style="text-align: center">
							<c:choose>
								<c:when test="${(viewServicingDetail.isApproved==0) and (flag==1)}"> 
									<a href="${pageContext.request.contextPath}/approvedServiceBill/${viewServicingDetail.servId}" ><input type="button" value="Approve" class="btn btn-info">
									</a>
									
									<a href="${pageContext.request.contextPath}/editServiceBill/${viewServicingDetail.servId}" ><input type="button" value="Edit" class="btn btn-info">
									</a>
									 
							</c:when>
							 
						</c:choose>
						<a href="${url}${viewServicingDetail.billFile}" target="blank" ><input type="button" value="View Pdf" class="btn btn-info">
									</a>
							</div>
						</div>
			
			
					
				
			</form>
			</div>
		</div>
	</div>
	</div>
	<!-- END Main Content -->

	<footer>
	<p>2017 © Monginis.</p>
	</footer>

	<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
		class="fa fa-chevron-up"></i></a>

 






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
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/chosen-bootstrap/chosen.jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-inputmask/bootstrap-inputmask.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-tags-input/jquery.tagsinput.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-pwstrength/jquery.pwstrength.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-fileupload/bootstrap-fileupload.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-duallistbox/duallistbox/bootstrap-duallistbox.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/dropzone/downloads/dropzone.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/clockface/js/clockface.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/date.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-switch/static/js/bootstrap-switch.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/ckeditor/ckeditor.js"></script>

	<!--flaty scripts-->
	<script src="${pageContext.request.contextPath}/resources/js/flaty.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/flaty-demo-codes.js"></script>
	<script>	
		function getPdf()
	{
    var poId = $("#poId").val();
    document.getElementById('submit').disabled=false;
    
	    	window.open('${pageContext.request.contextPath}/purchase?url=pdf/poPdf/'+poId);
		 
    }
		
		function validate(){
		    var inp = document.getElementById('file');
		    var fileName = inp.value;
		    var ext = fileName.substring(fileName.lastIndexOf('.') + 1); 
		    if(inp.files.length == 0 || ext!="pdf"){
		        alert("Valid file Required");
		   inp.focus();
		    return false;
		    }
		}
</script>
		
		
		
</body>
</html>