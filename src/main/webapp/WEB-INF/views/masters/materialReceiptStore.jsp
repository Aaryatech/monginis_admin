<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Material Receipt Store</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<!--base css styles-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/data-tables/bootstrap3/dataTables.bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-fileupload/bootstrap-fileupload.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/chosen-bootstrap/chosen.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-timepicker/compiled/timepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/clockface/css/clockface.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/css/datepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/daterangepicker.css" />



<!--page specific css styles-->

<!--flaty css styles-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flaty.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flaty-responsive.css">

<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/img/favicon.png">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/common.js"></script>
</head>
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
						<i class="fa fa-file-o"></i> Material Receipt Store
					</h1>
				</div>
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i> Material Receipt Store
							</h3>
							
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/mrnentry">MRN ENTRY</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						
						
						<div class="box-content">

							<form id="submitMaterialStore" action="${pageContext.request.contextPath}/submitMaterialStore" method="post">
							<div class="box-content">
								<div class="col-md-2">MRN No.</div>
								<div class="col-md-3"><input type="text" id="mrn_no" name="mrn_no" value="${materialRecNote.mrnId}" class="form-control" readonly>
								</div>
								
							<div class="col-md-2"> Date</div> 
							<div class="col-md-3">
							<input type="text" id="mrn_date" name="mrn_date" value="${materialRecNote.gateEntryDate}" class="form-control" readonly>
							
							</div>
					
							</div><br>
							
							<div class="box-content">
							
								<div class="col-md-2">Inward no.</div>
									<div class="col-md-3"><input type="text" id="inward_no" name="inward_no" value="${materialRecNote.mrnNo}" class="form-control" readonly>
								</div>
								<div class="col-md-2">Inward date</div>
									<div class="col-md-3">
									<input class="form-control" id="inward_date" size="16"
											type="text" name="inward_date" value="${materialRecNote.gateEntryDate}"  readonly />
									</div>
								
				 
							</div><br>
							
							
							<div class="box-content">
							
							<div class="col-md-2" >MRN Type</div>
									<div class="col-md-3">
										<select name="mrntype" id="mrntype" class="form-control" tabindex="-1" required>
									
										<option selected value="">Select MRN Type</option>
											
											<c:forEach items="${mrntype}" var="mrntype">
                                              
                                              
										<option value="${mrntype.grpId}"><c:out value="${mrntype.grpName}"></c:out> </option>
													

											</c:forEach>
										</select>
									</div>
									
									<div class="col-md-2">Supplier </div>
									<div class="col-md-3"><input type="text" id="supp_id" name="supp_id" value="${suppName}" 
									class="form-control" readonly>
								</div>
								
							</div><br>
							
							
							
							<div class="box-content">
							
									<div class="col-md-2" >Vehical No.</div>
									<div class="col-md-3"><input type="text" id="vehical_no" name="vehical_no" value="${materialRecNote.vehicleNo}" class="form-control" readonly>
									</div>
									
									<div class="col-md-2">Transporter</div>
										<div class="col-md-3"><input type="text" id="transporter" name="transporter" value="${transname}" class="form-control" readonly>
									</div>
							
							</div><br>
							
							<div class="box-content">
							
												<c:choose>
													<c:when test="${materialRecNote.status==2}">
													<div class="col-md-2" >Against PO</div>
									<div class="col-md-3">
									<input type="text" id="po_id" name="po_id" value="${materialRecNote.apainstPo}" 
									class="form-control" readonly>
										
										</div>
										
													</c:when>
													
													<c:otherwise>
													  <div class="col-md-2" >Against PO</div>
									<div class="col-md-3">
										<select name="po_id" id="po_id" class="form-control" tabindex="6" required>
											<option value=""> select</option>
											<option value="1">Yes</option>
											<option value="2">No</option>

										</select>
									</div>
													</c:otherwise>
													</c:choose>
							
							
									
									<div class="col-md-2">LR No.</div>
									<div class="col-md-3"><input type="text" id="lr_no" name="lr_no" value="${materialRecNote.lrNo}" 
									class="form-control" readonly>
								</div>
								
							</div><br>
							
							
							
							<div class="box-content">
							
									
									<div class="col-md-2" >PO Reference</div>
									<div class="col-md-3">
									
								
									
											<c:choose>
												<c:when test="${materialRecNote.status==2}">
													<div class="col-md-4">
													<input type="text" id="poref_id" name="poref_id" value="${materialRecNote.poId}" 
													class="form-control" readonly>
										
													</div>
												</c:when>
													
													<c:otherwise>
													 <select name="poref_id" id="poref_id" class="form-control" tabindex="-1"  disabled>
														<option value="">Select Po Ref</option>
											
														<c:forEach items="${purchaseOrderList}" var="purchaseOrderList">
															<c:choose>
															<c:when test="${purchaseOrderList.poStatus==6}">
																<option value="${purchaseOrderList.poId}" style="color:blue"><c:out value="${purchaseOrderList.poNo}"></c:out>
																</option>
															</c:when>
                                              				<c:otherwise>
                                              					<option value="${purchaseOrderList.poId}"><c:out value="${purchaseOrderList.poNo}"></c:out>
																</option>
                                              				</c:otherwise>
                                              					</c:choose>
														</c:forEach>
													</select>
													</c:otherwise>
												</c:choose>
										
									</div>
									
									<div class="col-md-2">PO Date</div>
										<div class="col-md-3">
										<input class="form-control" id="po_date" size="16"
											type="text" name="po_date" value="${materialRecNote.poDate}" placeholder="PO Date"  readonly />
										<input  id="po_no" type="hidden" name="po_no"   />
											
									</div>
							
							</div><br><br>
							
											<c:choose>
													<c:when test="${materialRecNote.status==2}">
													<c:set var = "lable" value="${materialRecNote.approvalRemark}"/>
													</c:when>
													</c:choose>
							 <div class="col1">
									<h4><c:out value = "${lable}"/></h4>
								</div><br><br>
							
							
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
										<th>PO QTY</th>
										<th>STOCK QTY</th>
										<th>REJECTED QTY</th>
										<th>PO Rate</th>

									</tr>
								</thead>
									<tbody>
									 <c:set var = "srNo" value="0"/>
									<c:forEach items="${materialRecNoteDetail}" var="materialRecNoteDetail"
													varStatus="count">
													
													<c:choose>
													<c:when test="${materialRecNoteDetail.status==1}">
													<c:set var = "color" value="blue"/>
													<c:set var = "textdisable" value="disabled"/>
													</c:when>
													
													<c:otherwise>
													  <c:set var = "color" value="black"/>
													  <c:set var = "textdisable" value=""/>
													</c:otherwise>
													</c:choose>
													
		
													
													

													<tr>
														<td style="color: <c:out value = "${color}"/>"><c:out value="${count.index+1}" /></td>
 														<c:set var = "srNo" value="${srNo+1}"/>
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out
																value="${materialRecNoteDetail.rmName}" /></td>


														<td align="left" style="color: <c:out value = "${color}"/>"><c:out
																value="${materialRecNoteDetail.recdQty}" /></td>
																
														 <td align="left" style="color: <c:out value = "${color}"/>"><c:out
																value="${materialRecNoteDetail.poQty}" /></td>
																
																<c:choose>
													<c:when test="${materialRecNoteDetail.status==1}">
														
														<td align="left" style="color: <c:out value = "${color}"/>"><input type="text" name='stockQty<c:out
																value="${materialRecNoteDetail.mrnDetailId}" />' class="form-control" value=
																<c:out
																value="${materialRecNoteDetail.stockQty}" /> readonly></td>
														 <td align="left" style="color: <c:out value = "${color}"/>"><input type="text" name='rejectedQty<c:out
																value="${materialRecNoteDetail.mrnDetailId}" />' class="form-control" value=
																<c:out
																value="${materialRecNoteDetail.rejectedQty}" /> readonly></td>
													
													</c:when>
												
													<c:otherwise>
													<td align="left" style="color: <c:out value = "${color}"/>"><input type="text" name='stockQty<c:out
																value="${materialRecNoteDetail.mrnDetailId}" />' class="form-control"  required="true" data-rule-number="true"></td>
														 <td align="left" style="color: <c:out value = "${color}"/>"><input type="text" name='rejectedQty<c:out
																value="${materialRecNoteDetail.mrnDetailId}" />' class="form-control"  required="true" data-rule-number="true"></td>
													  
													</c:otherwise>
													</c:choose>
													
														 
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out
																value="${materialRecNoteDetail.poRate}" /></td>
 												 
											
												</tr>
												</c:forEach>
								
													<input type="hidden" value='<c:out value="${srNo}" />' id="srNo">
									</tbody>
									</table>
								</div>
								</div>
							</div>
							<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Submit">
<!-- 										<button type="button" class="btn">Cancel</button>
 -->									</div>
								</div><br><br>
								
						
							
							<div class="box-content">
							
							<div class="col-md-2" >Remark</div>
									
									<div class="col-md-3"><input type="text" id="Remark" name="Remark" value="" 
									class="form-control" >
									</div>
									
							</div><br>
							
							
								
								
							
							
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
		<script>
	$(document).ready(function() { 
	$('#po_id').change(
			function() {
			 
				var po_id = $(this).val();
				if(po_id==1)
				{
					document.getElementById("poref_id").disabled = false;
					document.getElementById("po_date").disabled = false;
					document.getElementById("poref_id").required=true;
					
				 
				 
				}
				
				else
				{
					document.getElementById("poref_id").disabled = true;
					document.getElementById("po_date").disabled = true;
				 
				}
		})
					 
});
	 
	
	$(document).ready(function() { 
		$('#poref_id').change(
				function() {
				 
					$.getJSON('${withPoRef}', {
						
						poref_id : $(this).val(),
						ajax : 'true'
					},
							function(data) {
						if(data=="")
							{
							
							}
						else
							{
							
							$('#table_grid td').remove();
						
							
						
						
						var srNo=document.getElementById("srNo").value;
						 $.each(data,function(key, itemList) {
													
						var tr = $('<tr></tr>');

					  	tr.append($('<td></td>').html(key+1));			  	
					  	tr.append($('<td></td>').html(itemList.rmName));
						tr.append($('<td></td>').html(itemList.recdQty));
						tr.append($('<td></td>').html(itemList.poQty));
						if(itemList.mrnDetailId!=0)
							{
						tr.append($('<td></td>').html("<input type='text' class='form-control' name='stockQty"+itemList.mrnDetailId+"' required>"));
						tr.append($('<td></td>').html("<input type='text' class='form-control' name='rejectedQty"+itemList.mrnDetailId+"' required>"));
							}
						else
							{
							tr.append($('<td></td>').html("0"));
							tr.append($('<td></td>').html("0"));
							}
						tr.append($('<td></td>').html(itemList.poRate));
					$('#table_grid tbody').append(tr);
					
												})
												
							}
												
							});
					
			})
						 
	});
	
	
	
	$(document).ready(function() { 
		$('#poref_id').change(
				function() {
				 
					$.getJSON('${withPoRefDate}', {
						
						 
						ajax : 'true',
					},
							function(data) {
						
						
						
						document.getElementById("po_date").value=data[0].poDate;
						document.getElementById("po_no").value=data[0].poNo;
												
							});
					
			})
						 
	});
	</script> 
	
	
								
							
	
</body>
</html>