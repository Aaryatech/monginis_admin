<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
 	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
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
					<i class="fa fa-file-o"></i>Material Receipt Director
				</h1>
				<!-- <h4>Bill for franchises</h4> -->
			</div>
		</div>
		<!-- END Page Title -->


		<!-- BEGIN Main Content -->
		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>Material Receipt Director
				</h3>
				<div class="box-tool">
								<a href="${pageContext.request.contextPath}/allDirectorMaterialReceiptNote">All Director Material Reciept</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>

			</div>

			<div class=" box-content">

				<div class="box">
					<form id="validation-form" class="form-horizontal"
						enctype="multipart/form-data" method="post">

						<div class="box-content">

							<div class="col-md-2">Inward No.</div>
							<div class="col-md-3">
								<input type="text" id="inward_no." name="inward_no."
									value="${materialRecNoteHeader.mrnNo}" class="form-control"
									readonly>
							</div>
							<div class="col-md-1"></div>
							<div class="col-md-2">Inward Date & Time</div>
							<div class="col-md-3">
								<input class="form-control" id="dp1" size="16" type="text"
									name="inward_date" value=""
									placeholder="${materialRecNoteHeader.gateEntryDate}   ${materialRecNoteHeader.gateEntryTime}"
									readonly />
							</div>



						</div>
						<br>
						<div class="box-content">

							<div class="col-md-2">Supplier</div>
							<div class="col-md-3">
								<input type="text" id="supp_id" name="supp_id"
									value="${suppName1}" class="form-control" readonly>
							</div>
							<div class="col-md-1"></div>
							<div class="col-md-2">Transporter</div>
							<div class="col-md-3">
								<input type="text" id="transporter" name="transporter"
									value="${transportName }" class="form-control" readonly>
							</div>
						</div>
						<br>



					
						
						<c:forEach items="${rmItemGroupList}" var="rmItemGroupList"
							varStatus="count">
							<c:choose>
								<c:when
									test="${materialRecNoteHeader.mrnType==rmItemGroupList.grpId}">
									<c:set var="mrnName" value="${rmItemGroupList.grpName}" />
								</c:when>
							</c:choose>
						</c:forEach>

						<div class="box-content">

							<div class="col-md-2">MRN Type</div>
							<div class="col-md-3">

								<input type="text" id="mrn_type" name="mrn_type"
									value='<c:out value = "${mrnName}"/>' class="form-control"
									readonly>
							</div>
							<div class="col-md-1"></div>
							<div class="col-md-2">Store Remark</div>
							<div class="col-md-3">

								${materialRecNoteHeader.storesRemark}
							</div>
							</div><br>
							
							<c:choose>
						<c:when test="${materialRecNoteHeader.poId!=0}">
						 
						<div class="box-content">

							<div class="col-md-2">Po No.</div>
							<div class="col-md-3">
								<input type="text" name="po_no" id="po_no" class="form-control"
									value="${materialRecNoteHeader.poNo}" readonly>
							</div>
							<div class="col-md-1"></div>
							<div class="col-md-2">PO Date</div>
							<div class="col-md-3">
					 
								<input type="text" name="po_date" id="po_date"
									value="${materialRecNoteHeader.poDate}" class="form-control"
									readonly>
									 
							</div>


						</div>
						<br>
						
						<div class="box-content">
							<c:choose>
							
							
							 	<c:when test="${(purchaseOrderHeader.poType==1) and (materialRecNoteHeader.poId!=0) and (purchaseOrderHeader.poStatus!=7)}">
							 	
							 		<div class="col-md-2">Po Status</div>
									<div class="col-md-3">
									<select name="po_sts" id="po_sts" class="form-control chosen" tabindex="6" required>
									<option  value="">Select Transporter</option>
									<option  value="1">Partially Closed</option>
									<option  value="2">Closed</option>
									</select>

										
							</div>
							 	</c:when>
							
							
							</c:choose>
							</div><br>
						
						</c:when>
					
					</c:choose>
						<br>
						<br>


						<div class="box-content">


							<div class="col-md-2">Images</div>
							<div class="col-md-2"></div>
							<div class="col-md-2">
								<div class="fileupload fileupload-new"
									data-provides="fileupload">
									<div class="fileupload-new img-thumbnail"
										style="width: 150px; height: 150px;">
										<img src="${imageUrl}${materialRecNoteHeader.photo1}"
											onerror="this.src='${pageContext.request.contextPath}/resources/img/No_Image_Available.jpg';"
											alt="" />
									</div>
									<div class="fileupload-preview fileupload-exists img-thumbnail"
										style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
								</div>

							</div>


							<div class="col-md-2">
								<div class="fileupload fileupload-new"
									data-provides="fileupload">
									<div class="fileupload-new img-thumbnail"
										style="width: 200px; height: 150px;">
										<img src="${imageUrl}${materialRecNoteHeader.photo2}"
											onerror="this.src='${pageContext.request.contextPath}/resources/img/No_Image_Available.jpg';"
											alt="" />
									</div>
									<div class="fileupload-preview fileupload-exists img-thumbnail"
										style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>

								</div>

							</div>


						</div>

						<c:choose>
							<c:when test="${materialRecNoteHeader.status==1}">
								<c:set var="disabledFlag" value="" />
							</c:when>
							<c:otherwise>
								<c:set var="disabledFlag" value="disabled" />
							</c:otherwise>
						</c:choose>

						<div class=" box-content">
							<div class="row">
								<div class="col-md-12 table-responsive">
									<table class="table table-bordered table-striped fill-head "
										style="width: 100%" id="table_grid">
										<thead>
											<tr>

												<th align="left"><input type="checkbox"
													onClick="selectAll(this)"
													<c:out value = "${disabledFlag}"/> /> Select All</th>
												<th>Sr.No.</th>
												<th>Item</th>
												<th>In Quantity</th>
												<th>Stock Quantity</th>
												<th>PO Quantity</th>
												<th>PO Rate</th>
												<th>Varified Rate</th>
												<th>Value</th>

											</tr>
										</thead>
										<tbody>


											<c:forEach items="${materialRecNoteDetail}"
												var="materialRecNoteDetail" varStatus="count">



												<tr>

													<c:choose>
														<c:when test="${materialRecNoteDetail.status==1}">
															<td><input type="checkbox" name="select_to_approve"
																id="select_to_approve"
																value="${materialRecNoteDetail.mrnDetailId}" checked
																readonly="readonly"></td>
															<c:set var="color" value="black" />
														</c:when>
														 
														<c:otherwise>
															<td><input type="checkbox" name="select_to_approve"
																id="select_to_approve"
																value="${materialRecNoteDetail.mrnDetailId}"
																<c:out value = "${disabledFlag}"/>></td>
															<c:set var="color" value="black" />
														</c:otherwise>
													</c:choose>





													<td style="color: <c:out value = "${color}"/>"><c:out
															value="${count.index+1}" /></td>

													<td align="left" style="color: <c:out value = "${color}"/>"><c:out
															value="${materialRecNoteDetail.rmName}" /></td>
															
													<td align="left" style="color: <c:out value = "${color}"/>"><c:out
															value="${materialRecNoteDetail.recdQty}" /></td>

													<td align="left" style="color: <c:out value = "${color}"/>"><c:out
															value="${materialRecNoteDetail.stockQty}" /></td>



													<td align="left" style="color: <c:out value = "${color}"/>"><c:out
															value="${materialRecNoteDetail.poQty}" /></td>

													<td align="left" style="color: <c:out value = "${color}"/>">
														<c:out value="${materialRecNoteDetail.poRate}" />
													</td>
													
													<td align="left" style="color: <c:out value = "${color}"/>">
														<c:out value="${materialRecNoteDetail.varifiedRate}" />
													</td>

													<td align="left" style="color: <c:out value = "${color}"/>"><c:out
															value="${materialRecNoteDetail.varifiedRate*materialRecNoteDetail.stockQty}" />
													</td>








												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>
							</div>

						</div>

									<%-- <c:choose>
										<c:when test="${materialRecNoteHeader.approvalRemark==null}">
										<c:set var="sts" value="None"></c:set>
										</c:when>
										<c:otherwise>
										<c:set var="sts" value="${materialRecNoteHeader.approvalRemark}"></c:set>
										</c:otherwise>
									</c:choose> --%>

						<div class="box-content">

							<div class="col-md-2">* Issue</div>
							<div class="col-md-4" style="text-align: left;">
								<select name="issue" id="issue" class="form-control chosen"
									tabindex="1" required data-rule-required="true">
									<option value="">None</option>
									<c:forEach items="${allRemarksList}" var="allRemarksList"
										varStatus="count">
										<option value="${allRemarksList.remark}"><c:out value="${allRemarksList.remark}"/></option>
									</c:forEach>

								</select>
							</div>


						</div>
						<br />
						<br />

						<div class="row">
							<div class="col-md-12" style="text-align: center">
								<input type="button" class="btn btn-info" id="btnRejetToStore"
									value="Reject To Store" <c:out value = "${disabledFlag}"/>
									onclick="rejectToStore()"> <input type="button"
									class="btn btn-info" id="btnRejectToAcc"
									value="Reject To Account" <c:out value = "${disabledFlag}"/>
									onclick="rejectToAcc()"> <input type="button"
									class="btn btn-info" id="btnApprov" value="Approve"
									onclick="approve()" <c:out value = "${disabledFlag}"/>><br />
								<br /> <a href='${pageContext.request.contextPath}/allDirectorMaterialReceiptNote'><input type="button" class="btn btn-info"
									value="Back to List" >
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
	
		function selectAll(source) {
			checkboxes = document.getElementsByName('select_to_approve');
			
			for (var i = 0, n = checkboxes.length; i < n; i++) {
				checkboxes[i].checked = source.checked;
			}
			
		}
		
		function rejectToStore(){
			 var form = document.getElementById("validation-form");
			 form.action ="submitMaterialReceiptDirector/2";
			    form.submit();
		}
		
		function rejectToAcc(){
			 var form = document.getElementById("validation-form");
			 form.action ="submitMaterialReceiptDirector/3";
			    form.submit();
			    
		}
		
		function approve(){
			 var form = document.getElementById("validation-form");
			 form.action ="submitMaterialReceiptDirector/4";
			    form.submit();
		}
		 
 
   
	 
		
	</script>

</body>
</html>