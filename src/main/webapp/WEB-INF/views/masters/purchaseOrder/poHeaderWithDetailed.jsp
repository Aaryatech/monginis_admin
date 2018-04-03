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
					<i class="fa fa-file-o"></i>Purchase Order Detailed
				</h1>
				<!-- <h4>Bill for franchises</h4> -->
			</div>
		</div>
		<!-- END Page Title -->

		<!-- BEGIN Breadcrumb -->
		<div id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i> <a
					href="${pageContext.request.contextPath}/home">Home</a> <span
					class="divider"><i class="fa fa-angle-right"></i></span></li>
				<li class="active">Purchase Order</li>
			</ul>
		</div>
		<!-- END Breadcrumb -->
		
		<!-- BEGIN Main Content -->
		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>Purchase Order Detailed
				</h3>

			</div>

				<div class=" box-content">
					 
		<div class="box">
			<form id="submitPurchaseOrder"
				action="${pageContext.request.contextPath}/requestPOFinalByDirectore"
				method="post" enctype="multipart/form-data" onsubmit="return(validate());">
			<div class="box-content">
				<div class="col-md-2">PO No.  </div>
				<div class="col-md-4"><input type="text" id="po_no" name="po_no" value="${purchaseOrderHeader.poNo}" class="form-control" readonly>
				</div>
				<div class="col-md-2">PO Date</div> 
				<div class="col-md-3">
				<input type="text" id="po_date" name="po_date" value="${purchaseOrderHeader.poDate}" class="form-control" readonly>
				<input type="hidden" id="flag" name="flag" value="0" class="form-control">
				
				</div>
				</div><br/>
				<div class="box-content">
				<div class="col-md-2" >Supplier</div>
									<div class="col-md-4">
									
										<c:forEach items="${supplierList}" var="supplierList" varStatus="count">
												<c:choose>
													<c:when test="${purchaseOrderHeader.suppId==supplierList.suppId}">
													<input type="text" id="Suppid" name="Suppid" value="${supplierList.suppName}" 
								class="form-control" readonly>
													
													</c:when>
												 </c:choose>
											</c:forEach>
									
									</div>
									<div class="col-md-2">Quotation Ref. No.  </div>
				<div class="col-md-3">
					<input type="text" name="quotation_ref_no" id="quotation_ref_no" value="${purchaseOrderHeader.quotationRefNo}" class="form-control" readonly>
				</div>
				 
			</div><br/>
			<div class="box-content">
				<div class="col-md-2">Kind Attention</div>
				<div class="col-md-4">
					<input type="text" name="kind_attn" id="kind_attn" value="${purchaseOrderHeader.kindAttn}" class="form-control" readonly>
				</div>
				<div class="col-md-2">Delivery At</div>
				<div class="col-md-3">
					<input type="text" name="delv_at" id="delv_at" value="${purchaseOrderHeader.delvAtRem}" class="form-control" readonly>
				</div>
				</div><br/>
			<div class="box-content">
				<div class="col-md-2" >Taxation</div>
									<div class="col-md-4">
									<c:choose>
										<c:when test="${purchaseOrderHeader.taxationRem==1}">
											<c:set var="taxation" value="Inclusive"> </c:set>
										</c:when>
										<c:when test="${purchaseOrderHeader.taxationRem==2}">
											<c:set var="taxation" value="Extra"> </c:set>
										</c:when>
									</c:choose>
									
									<input type="text" name="taxation" id="taxation" value="${taxation}" class="form-control" readonly>
				
									</div>
									<div class="col-md-2">Delivery Date  </div>
				<div class="col-md-3">
					<input type="text" name="delv_date"id="delv_date" class="form-control" 
					value="${purchaseOrderHeader.delvDateRem}" readonly>
				</div>
				 
			</div> <br/>
			<div class="box-content">
								<div class="col-md-2" >PO Type</div>
									<div class="col-md-4">
										 <c:choose>
											<c:when test="${purchaseOrderHeader.poType==1}">
												<c:set var="poType" value="Regular"></c:set>
											</c:when>
											
											<c:when test="${purchaseOrderHeader.poType==2}">
												<c:set var="poType" value="Open"></c:set>
											</c:when>
										
										</c:choose>
										
										<input type="text" name="po_type" id="po_type" class="form-control" 
											value="${poType}" readonly>
										
									</div>
								<div class="col-md-2" >Quotation Ref. Date</div>
									<div class="col-md-3">
										 <input type="text" name="quotation_date" id="quotation_date" value="${purchaseOrderHeader.quotationRefDate}" class="form-control" readonly>
									</div>
									
						</div>	<br/>		
							
							 <c:choose>
									<c:when test="${(purchaseOrderHeader.poStatus==2) and (flag==2)}">
										<div class="box-content">
											<div class="col-md-2" >Select Pdf</div>
												<div class="col-md-4"> 
													<input type="file" id="file" name="attachFile" size="60" data-rule-required="true"/> 
												</div> 
									</div><br/>
									</c:when>  
							</c:choose>
			
			
				<div class=" box-content">
					<div class="row">
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Product</th>
										<th>Quantity</th>
										<th>Rate</th>
										<th>Discount %</th>
										<th>Value</th>
										<th>Schedule Days</th>
										<th>RM Remark</th>
										

									</tr>
								</thead>
								<tbody>
								
								<c:forEach items="${purchaseOrderHeader.purchaseOrderDetail}" var="purchaseOrderDetailedList"
														varStatus="count">


														<tr>
															<td><c:out value="${count.index+1}" /></td>
															<c:set var="srNo" value="${srNo+1}" />
															<td><c:out value="${purchaseOrderDetailedList.rmName}" /></td>
															<td><c:out value="${purchaseOrderDetailedList.poQty}" /></td>


															<td><c:out value="${purchaseOrderDetailedList.poRate}" /></td>
															<td><c:out value="${purchaseOrderDetailedList.discPer}" /></td>
															<td><c:out value="${purchaseOrderDetailedList.poQty*purchaseOrderDetailedList.poRate}" /></td>
															
															<td><c:out value="${purchaseOrderDetailedList.schDays}" /></td>
															<td><c:out value="${purchaseOrderDetailedList.rmRemark}" /></td>
															
		                                                    

														</tr>
													</c:forEach>
								
								

								</tbody>
							</table>
						</div>
					</div>

 
		</div>
		<div class="box-content">
				<div class="col-md-2" >Payment Terms</div>
									<div class="col-md-3">
									<c:forEach items="${supPaymentTerms}" var="supPaymentTerms" varStatus="count">
											 	<c:choose>
											 		<c:when test="${supPaymentTerms.payId==purchaseOrderHeader.payId}">
											 			<input type="text" name="supPaymentTerms" id="supPaymentTerms" class="form-control" value="${supPaymentTerms.payDesc}" readonly>
													 </c:when>
												 </c:choose>
											</c:forEach>
									
									<%-- <input type="text" name="pay_terms" id="pay_terms" value="${purchaseOrderHeader.payId}" class="form-control" readonly>
				 --%>
								
									</div>
									<div class="col-md-2">PO Validity </div>
				<div class="col-md-3">
					<input type="text" name="po_validity" id="po_validity" class="form-control" value="${purchaseOrderHeader.validity}" readonly>
				</div>
				</div><br/>
				 	<div class="box-content">
							<div class="col-md-2" >Transportation</div>
									<div class="col-md-3">
									
					
											 <c:forEach items="${transporterList}" var="transporterList" varStatus="count">
											 	<c:choose>
											 		<c:when test="${transporterList.tranId==purchaseOrderHeader.tranId}">
											 			<input type="text" name="transportation" id="transportation" class="form-control" value="${transporterList.tranName}" readonly>
													 </c:when>
												 </c:choose>
											</c:forEach>
							 		</div>
							 		
									<div class="col-md-2" >Freight</div>
									<div class="col-md-3">
									<c:choose>
										<c:when test="${purchaseOrderHeader.freidhtRem==1}">
										<c:set var="freightAmt" value="Not Applicable"></c:set>
										</c:when>
										<c:when test="${purchaseOrderHeader.freidhtRem==2}">
										<c:set var="freightAmt" value="On Your Side"></c:set>
										</c:when>
										<c:when test="${purchaseOrderHeader.freidhtRem==3}">
										<c:set var="freightAmt" value="On Our Side"></c:set>
										</c:when>
									</c:choose>
										<input type="text" name="transportation" id="transportation" class="form-control" value="${freightAmt}" readonly>
									</div>
									</div><br/>
									
									<div class="box-content">
										<div class="col-md-2" >Insurance</div>
											<div class="col-md-3">
											<c:choose>
										<c:when test="${purchaseOrderHeader.insuRem==1}">
										<c:set var="InsuAmt" value="Not Applicable"></c:set>
										</c:when>
										<c:when test="${purchaseOrderHeader.insuRem==2}">
										<c:set var="InsuAmt" value="On Your Side"></c:set>
										</c:when>
										<c:when test="${purchaseOrderHeader.insuRem==3}">
										<c:set var="InsuAmt" value="On Our Side"></c:set>
										</c:when>
									</c:choose>
											<input type="text" name="insurance" id="insurance" class="form-control" value="${InsuAmt}" readonly>
									
					
									</div>
									<div class="col-md-2" >Sp.Instrucion</div>
									<div class="col-md-3">
					<input type="text" name="sp_instruction" id="sp_instruction" class="form-control" value="${purchaseOrderHeader.spRem}" readonly>
				</div>
									</div><br/><br/>
									
									<div class="row">
						<div class="col-md-12" style="text-align: center">
							<c:choose>
								<c:when test="${(purchaseOrderHeader.poStatus==0) and (flag==0)}"> 
									<a href="${pageContext.request.contextPath}/editPurchaseOrder/${purchaseOrderHeader.poId}/${flag}" ><input type="button" value="Edit" class="btn btn-info">
									</a>
								
								<a href="${pageContext.request.contextPath}/requestPOStoreToPurchase/${purchaseOrderHeader.poId}" ><input type="button" value="Request To Purchase" class="btn btn-info">
								</a>
							</c:when>
							 <c:when test="${(purchaseOrderHeader.poStatus==3) and (flag==0)}">
							 	<a href="${pageContext.request.contextPath}/editPurchaseOrder/${purchaseOrderHeader.poId}/${flag}" ><input type="button" value="Edit" class="btn btn-info">
								</a>
								
								<a href="${pageContext.request.contextPath}/requestPOStoreToPurchase/${purchaseOrderHeader.poId}" ><input type="button" value="Request To Purchase" class="btn btn-info">
								</a>
          						 
						</c:when>
						<c:when test="${(purchaseOrderHeader.poStatus==4) and (flag==1)}"> 
						<a href="${pageContext.request.contextPath}/editPurchaseOrder/${purchaseOrderHeader.poId}/${flag}" ><input type="button" value="Edit" class="btn btn-info">
								</a>
									<a href="${pageContext.request.contextPath}/requestPOPurachaseToDirectore/${purchaseOrderHeader.poId}" ><input type="button" value="Request To Directore" class="btn btn-info">
									</a>
								
								<a href="${pageContext.request.contextPath}/rejectPOPurachaseToStore/${purchaseOrderHeader.poId}"
						onClick="return confirm('You Want To Reject This Record To Store');" ><input type="button" value="Reject To Store" class="btn btn-info">
								</a>
							</c:when>
							
							 <c:when test="${(purchaseOrderHeader.poStatus==1) and (flag==1)}">
							 <a href="${pageContext.request.contextPath}/editPurchaseOrder/${purchaseOrderHeader.poId}/${flag}" ><input type="button" value="Edit" class="btn btn-info">
								</a>
							 	<a href="${pageContext.request.contextPath}/requestPOPurachaseToDirectore/${purchaseOrderHeader.poId}" ><input type="button" value="Request To Directore" class="btn btn-info">
									</a>
								
								<a href="${pageContext.request.contextPath}/rejectPOPurachaseToStore/${purchaseOrderHeader.poId}"
						onClick="return confirm('You Want To Reject This Record To Store');" ><input type="button" value="Reject To Store" class="btn btn-info">
								</a>
          						 
						</c:when>
						
						<c:when test="${(purchaseOrderHeader.poStatus==2) and (flag==2)}"> 
						<a href="${pageContext.request.contextPath}/editPurchaseOrder/${purchaseOrderHeader.poId}/${flag}" ><input type="button" value="Edit" class="btn btn-info">
								</a>
								<input type="hidden" id="poId" name="poId" value="${purchaseOrderHeader.poId}">
									 <input type="submit" id="submit" value="Approve" class="btn btn-info" disabled>
									 
								<a <%-- href="${pageContext.request.contextPath}/purchase?url=poPdf" --%> target="_blank"><input type="button" value="Pdf" class="btn btn-info" onclick="getPdf()">
									</a>
								
								<a href="${pageContext.request.contextPath}/rejectPODirectoreToPurchase/${purchaseOrderHeader.poId}"
						onClick="return confirm('You Want To Reject This Record To Purchase');" ><input type="button" value="Reject To Purchase" class="btn btn-info">
								</a>
							</c:when>
						
						
						</c:choose>
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