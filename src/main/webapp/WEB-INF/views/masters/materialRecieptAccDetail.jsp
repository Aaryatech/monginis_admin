<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Material Receipt Account</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<!--base css styles-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/font-awesome/css/font-awesome.min.css">

<!--page specific css styles-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/chosen-bootstrap/chosen.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/jquery-tags-input/jquery.tagsinput.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/jquery-pwstrength/jquery.pwstrength.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-fileupload/bootstrap-fileupload.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-duallistbox/duallistbox/bootstrap-duallistbox.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/dropzone/downloads/css/dropzone.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-colorpicker/css/colorpicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-timepicker/compiled/timepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/clockface/css/clockface.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/css/datepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/daterangepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-switch/static/stylesheets/bootstrap-switch.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-wysihtml5/bootstrap-wysihtml5.css" />
<style>
@media only screen and (min-width: 1200px) {
	.franchisee_label, .menu_label {
		width: 22%;
	}
	.franchisee_select, .menu_select {
		width: 76%;
	}
	.date_label {
		width: 40%;
	}
	.date_select {
		width: 50%;
		padding-right: 0px;
	}
}
</style>
<!--flaty css styles-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flaty.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/loader.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flaty-responsive.css">

<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/img/favicon.png">
</head>
<body>



	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<c:url var="updatedetailed" value="/updatedetailed"></c:url>
<c:url var="updateHeader" value="/updateHeader"></c:url>
<c:url var="updateFreightAmt" value="/updateFreightAmt"></c:url>

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
					<i class="fa fa-file-o"></i>Material Receipt Account
				</h1>
				<!-- <h4>Bill for franchises</h4> -->
			</div>
		</div>
		<!-- END Page Title -->


		<!-- BEGIN Main Content -->
		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>Material Receipt Account
				</h3>

			</div>

			<div class=" box-content">

				<div class="box">
					<form id="validation-form" class="form-horizontal"
						action="${pageContext.request.contextPath}/submitMaterialAcc" method="post">

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
							
							<c:forEach items="${supplierDetailsList}" var="supplierDetailsList">
							
							<c:choose>
								<c:when test="${supplierDetailsList.suppId==materialRecNoteHeader.supplierId}">
									
									<input type="text" id="supp_id" name="supp_id"
									value="${supplierDetailsList.suppName}" class="form-control" readonly>
								</c:when>
							
							</c:choose>
							
							</c:forEach>
							
								
							</div>
							
							<div class="col-md-1"></div>
							<div class="col-md-2" >Booking Date</div>
									<div class="col-md-3">
										 <input type="text" name="booking_date" id="booking_date" class="form-control date-picker" required>
									</div>
							
							
						</div>
						<br>
						
						<div class="box-content">
						<div class="col-md-2" >Invoice no.</div>
									<div class="col-md-3">
										 <input type="text" name="invoice_no" id="invoice_no" class="form-control" required>
									</div>
							<div class="col-md-1"></div>
							<div class="col-md-2" >Invoice no. Date</div>
									<div class="col-md-3">
										 <input type="text" name="invoice_date" id="invoice_date" class="form-control date-picker" required>
									</div>
									
						</div>
							<br>




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
						<br>  <br>
				</c:when>
				</c:choose>

						

				

						<div class=" box-content">
							<div class="row">
								<div style="overflow:scroll;height:100%;width:100%;overflow:auto">
									<table width="100%" border="0"class="table table-bordered table-striped fill-head "
										style="width: 100%" id="table_grid">
										<thead>
											<tr>

												

												<th>Sr.No.</th>
												<th>Tax</th> 
												<th>Item</th>
												<th style="width:500%;">PO Rate</th>
												<th>Received Quantity</th>
												<th>Rate</th> 
												<th>Value</th>
												<th style="width:500%;">Disc Per</th>
												<th>Disc Amt</th>
												<th>Cd Per</th>
												<th>Cd Amt</th>
												<th>Division factor</th>
												<th>Insu Amt</th>
												<th>Freight Amt</th>
												<th style="width:200%;">Other1(Discount)</th>
												<th style="width:200%;">Other2(Discount)</th>
												<th style="width:200%;">Other3(Extra Charges)</th>
												<th style="width:200%;">Other4(Extra Charges)</th>
												<th>GST</th>
												<th>CGST</th>
												<th>SGST</th>
												<th>IGST</th>
												<th>CESS</th>
												<th>Taxable Amt</th>
												
												<th>CGST Amt</th>
												<th>SGST Amt</th>
												<th>IGST Amt</th>
												<th>CESS Amt</th>
												

											</tr>
										</thead>
										<tbody>


											<c:forEach items="${materialRecieptAccList}"
												var="materialRecieptAccList" varStatus="count">



												<tr>

													<td><c:out
															value="${count.index+1}" /></td>
															
													<c:choose>
														<c:when test="${materialRecieptAccList.incldTax==1}">
															<c:set var="tax" value="Including Tax"></c:set>
														</c:when>
														<c:otherwise>
															<c:set var="tax" value="No Including"></c:set>
														</c:otherwise>
													</c:choose>
															
													<td><c:out
															value="${tax}" /></td>

													<td><c:out
															value="${materialRecieptAccList.item}" /></td>
															
												<c:choose>
													<c:when test="${materialRecieptAccList.incldTax==0}">
													<td ><input style="width:75px" type="text"
													onchange="changeRate(${count.index})"
													name="poRate${count.index}"  id="poRate${count.index}"
									  class="form-control" pattern="[+-]?([0-9]*[.])?[0-9]+" required></td>
													
													</c:when>
													<c:otherwise>
													<td ><input style="width:75px" type="text"
													name="poRate${count.index}" id="poRate${count.index}"
									value="${materialRecieptAccList.poRate}" class="form-control" readonly></td>
													</c:otherwise>
												
												</c:choose>
													

													<td>
														<c:out value="${materialRecieptAccList.reciedvedQty}" />
													</td>


													<td><c:out value="${materialRecieptAccList.rateCal}" />
													</td>
													
													<td><c:out value="${materialRecieptAccList.value}" />
													</td>
													<td> 
													<input style="width:50px;" onchange="changeRate(${count.index})" type="text" name="discPer${count.index}" id="discPer${count.index}"
									value="${materialRecieptAccList.discPer}" class="form-control" pattern="[+-]?([0-9]*[.])?[0-9]+" required>
													
													</td>
													<td><c:out value="${materialRecieptAccList.discAmt}" />
													</td>
													<td><c:out value="${materialRecieptAccList.cdPer}" />
													</td>
													<td><c:out value="${materialRecieptAccList.cdAmt}" />
													</td>
													<td><c:out value="${materialRecieptAccList.divFactor}" />
													</td>
													<td><c:out value="${materialRecieptAccList.insuAmt}" />
													</td>
													<td><c:out value="${materialRecieptAccList.freightAmt}" />
													</td>
													<td><input style="width:100%;" onchange="changeRate(${count.index})" type="text" name="other1${count.index}" id="other1${count.index}"
									value="${materialRecieptAccList.other1}" class="form-control" pattern="[+-]?([0-9]*[.])?[0-9]+" required> 
													</td>
													<td><input style="width:100%;" onchange="changeRate(${count.index})" type="text" name="other2${count.index}" id="other2${count.index}"
									value="${materialRecieptAccList.other2}" class="form-control" pattern="[+-]?([0-9]*[.])?[0-9]+" required> 
													</td>
													<td><input style="width:100%;" onchange="changeRate(${count.index})" type="text" name="other3${count.index}" id="other3${count.index}"
									value="${materialRecieptAccList.other3}" class="form-control" pattern="[+-]?([0-9]*[.])?[0-9]+" required> 
													</td>
													<td><input style="width:100%;" onchange="changeRate(${count.index})" type="text" name="other4${count.index}" id="other4${count.index}"
									value="${materialRecieptAccList.other4}" class="form-control" pattern="[+-]?([0-9]*[.])?[0-9]+" required> 
													</td>
													<td><c:out value="${materialRecieptAccList.gst}" />
													</td>
													<td><c:out value="${materialRecieptAccList.cgst}" />
													</td>
													<td><c:out value="${materialRecieptAccList.sgst}" />
													</td>
													<td><c:out value="${materialRecieptAccList.igst}" />
													</td>
													<td><c:out value="${materialRecieptAccList.cess}" />
													</td>
													<td><c:out value="${materialRecieptAccList.taxableAmt}" />
													</td>
													
													<td><c:out value="${materialRecieptAccList.cgstAmt}" />
													</td>
													<td><c:out value="${materialRecieptAccList.sgstAmt}" />
													</td>
													<td><c:out value="${materialRecieptAccList.igstAmt}" />
													</td>
													<td><input style="width:50px" onchange="changeRate(${count.index})" type="text" name="cessAmt${count.index}" id="cessAmt${count.index}"
									value="${materialRecieptAccList.cessAmt}" class="form-control" pattern="[+-]?([0-9]*[.])?[0-9]+" required> 
													</td>
													
													<%-- <td>
													<span  class="glyphicon glyphicon-ok" onclick="changeRate(${count.index})" id="ok${count.index}"></span>
													</td> --%>
 
												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>
							</div>

						</div>
						
							<div class="box-content">
								<h5>Summary</h5>
									<div class="col-md-2">value</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="basicValue" id="basicValue" class="form-control"
									value="${materialRecNoteHeader.basicValue}" readonly>
									</div>
									
							
							</div><br>
							<div class="box-content">
								
									<div class="col-md-2">Perticular Disc Total Amt</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="discAmt2" id="discAmt2" class="form-control"
									value="${materialRecNoteHeader.discAmt2}" readonly>
									</div>
									
							
							</div><br>
						
						<div class="box-content"> 
						 <div class="col-md-2">Per Disc</div>
							<div class="col-md-3">
					 
								<input style="text-align:right; width:150px" type="text" onchange="changeFreightAmt();" name="discPer" id="discPer"
									value="${materialRecNoteHeader.discPer}" class="form-control" pattern="[+-]?([0-9]*[.])?[0-9]+" required>
									 
							</div>
							
						   <div class="col-md-2">Cash Disc</div>
							<div class="col-md-3">
					 
								<input style="text-align:right; width:150px" type="text" name="discAmt" id="discAmt"
									value="${materialRecNoteHeader.discAmt}" class="form-control"
									readonly>
									 
							</div>
						</div><br>
						<div class="box-content">
									<div class="col-md-2">Freight Amt</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" onchange="changeFreightAmt();" name="freightAmt" id="freightAmt" class="form-control"
									value="${materialRecNoteHeader.freightAmt}" pattern="[+-]?([0-9]*[.])?[0-9]+" required>
									</div>
									
							
							</div><br>
							<div class="box-content">
									<div class="col-md-2">Insu Amt</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" onchange="changeFreightAmt();" name="insuranceAmt" id="insuranceAmt" class="form-control"
									value="${materialRecNoteHeader.insuranceAmt}" pattern="[+-]?([0-9]*[.])?[0-9]+" required>
									</div>
									
							
							</div><br>
							<div class="box-content">
									<div class="col-md-2">Other1</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="Other1" id="Other1" class="form-control"
									value="${materialRecNoteHeader.other1}" readonly>
									</div>
									
							
							</div><br>
							<div class="box-content">
									<div class="col-md-2">Other2</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="Other2" id="Other2" class="form-control"
									value="${materialRecNoteHeader.other2}" readonly>
									</div>
									
							
							</div><br>
							<div class="box-content">
									<div class="col-md-2">Other3</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="Other3" id="Other3" class="form-control"
									value="${materialRecNoteHeader.other3}" readonly>
									</div>
									
							
							</div><br>
							<div class="box-content">
									<div class="col-md-2">Other4</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="Other4" id="Other4" class="form-control"
									value="${materialRecNoteHeader.other4}" readonly>
									</div>
									
							
							</div><br>
							<div class="box-content">
									<div class="col-md-2">CGST</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="cgst" id="cgst" class="form-control"
									value="${materialRecNoteHeader.cgst}" readonly>
									</div>
									
							
							</div><br>
							<div class="box-content">
									<div class="col-md-2">SGST</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="sgst" id="sgst" class="form-control"
									value="${materialRecNoteHeader.sgst}" readonly>
									</div>
									
							
							</div><br>
							<div class="box-content">
									<div class="col-md-2">IGST</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="igst" id="igst" class="form-control"
									value="${materialRecNoteHeader.igst}" readonly>
									</div>
									
							
							</div><br>
							<div class="box-content">
									<div class="col-md-2">Cess</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="cess" id="cess" class="form-control"
									value="${materialRecNoteHeader.cess}" readonly>
									</div>
									
							
							</div><br>
							
							<div class="box-content">
									<div class="col-md-2">Bill Total</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="billAmount" id="billAmount" class="form-control"
									value="${materialRecNoteHeader.billAmount}" readonly>
									</div>
									
							
							</div><br>
							<div class="box-content">
									<div class="col-md-2">Round Off</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="roundOff" id="roundOff" class="form-control"
									value="${materialRecNoteHeader.roundOff}" readonly>
									</div>
									
							
							</div><br>


				
						<br />
						<br />

						<div class="row">
							<div class="col-md-12" style="text-align: center">
								 <input type="submit" class="btn btn-info"
									value="Submit" >
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
	
	function changeRate(key)
	{
	
		
		var poRate = $("#poRate"+key+"").val();
		
		var discPer = $("#discPer"+key+"").val();
		var cessAmt = $("#cessAmt"+key+"").val();
		var other1 = $("#other1"+key+"").val();
		var other2 = $("#other2"+key+"").val();
		var other3 = $("#other3"+key+"").val();
		var other4 = $("#other4"+key+"").val();
		
		
		$
		.getJSON(
				'${updatedetailed}',

				{
					 
					index : key,
					poRate : poRate,
					discPer : discPer,
					cessAmt : cessAmt,
					other3 : other3,
					other1 : other1,
					other2 : other2,
					other4 : other4,
					ajax : 'true',

				},
				function(data) {
					
					
					$('#table_grid td').remove();
					if (data == "") {
						alert("No records found !!");

					}
					$.each(
							data,
							function(key, itemList) {
								
								
								
									var tax;
									if(itemList.incldTax==1)
										{
										tax="Including Tax";
										}
									else
										{
										tax="No Including";
										}
									var tr = $('<tr></tr>');
									tr.append($('<td></td>').html(key+1));

								  	tr.append($('<td></td>').html(tax));
								  	tr.append($('<td></td>').html(itemList.item));
								  	if(itemList.incldTax==0)
								  		{
								  		tr.append($('<td></td>').html('<input style="width:75px;" type="text" onchange="changeRate('+key+')" id="poRate'+key+'" value="'+itemList.poRate+'" class="form-control" pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));

								  		}
								  	else
								  		{
								  		tr.append($('<td></td>').html('<input style="width:75px;" type="text" onchange="changeRate('+key+')" id="poRate'+key+'" value="'+itemList.poRate+'" class="form-control" readonly>'));
								  		
										}
								  	tr.append($('<td></td>').html(itemList.reciedvedQty));
								  	tr.append($('<td></td>').html(itemList.rateCal));
								  	tr.append($('<td></td>').html(itemList.value));
								  	
								  	tr.append($('<td></td>').html('<input style="width:50px;" type="text" onchange="changeRate('+key+')" id="discPer'+key+'" value="'+itemList.discPer+'"  class="form-control"  pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));


								  	tr.append($('<td></td>').html(itemList.discAmt));
								  	tr.append($('<td></td>').html(itemList.cdPer));
								  	tr.append($('<td></td>').html(itemList.cdAmt));
								  	tr.append($('<td></td>').html(itemList.divFactor));
								  	tr.append($('<td></td>').html(itemList.insuAmt));
								  	tr.append($('<td></td>').html(itemList.freightAmt));
								  	tr.append($('<td></td>').html('<input style="width:50px;" type="text" onchange="changeRate('+key+')" id="other1'+key+'" value="'+itemList.other1+'"  class="form-control"  pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));
								  	tr.append($('<td></td>').html('<input style="width:50px;" type="text" onchange="changeRate('+key+')" id="other2'+key+'" value="'+itemList.other2+'"  class="form-control"  pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));
 									tr.append($('<td></td>').html('<input style="width:50px;" type="text" onchange="changeRate('+key+')" id="other3'+key+'" value="'+itemList.other3+'"  class="form-control"  pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));
 									tr.append($('<td></td>').html('<input style="width:50px;" type="text" onchange="changeRate('+key+')" id="other4'+key+'" value="'+itemList.other4+'"  class="form-control"  pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));

								  	/* tr.append($('<td></td>').html(itemList.other1));
								  	tr.append($('<td></td>').html(itemList.other2));
								  	tr.append($('<td></td>').html(itemList.other3));
								  	tr.append($('<td></td>').html(itemList.other4)); */
								  	
								  	tr.append($('<td></td>').html(itemList.gst));
								  	tr.append($('<td></td>').html(itemList.cgst));
								  	tr.append($('<td></td>').html(itemList.sgst));
								  	tr.append($('<td></td>').html(itemList.igst));
								  	tr.append($('<td></td>').html(itemList.cess));
								  	tr.append($('<td></td>').html(itemList.taxableAmt));
								  	tr.append($('<td></td>').html(itemList.cgstAmt));
								  	tr.append($('<td></td>').html(itemList.sgstAmt));
								  	tr.append($('<td></td>').html(itemList.igstAmt));
								  	/* tr.append($('<td></td>').html(itemList.cessAmt)); */
								  	tr.append($('<td ></td>').html('<input style="width:50px;" type="text" onchange="changeRate('+key+')" id="cessAmt'+key+'" value="'+itemList.cessAmt+'"  class="form-control"  pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));

								  
								  	/* tr.append($('<td></td>').html('<span  class="glyphicon glyphicon-ok" onclick="changeRate('+key+')" id="ok'+key+'"></span>'));
								  	  */





									$('#table_grid tbody').append(tr);
								

								 

							})
							$ .getJSON( '${updateHeader}',

									{
					 
									ajax : 'true',

									},
								function(data)
								{
										
										document.getElementById("basicValue").value=data.basicValue;
										document.getElementById("billAmount").value=data.billAmount;
										document.getElementById("discAmt2").value=data.discAmt2;
										document.getElementById("discAmt").value=data.discAmt;
										document.getElementById("Other1").value=data.other1;
										document.getElementById("Other2").value=data.other2;
										document.getElementById("Other3").value=data.other3;
										document.getElementById("Other4").value=data.other4;
										document.getElementById("cgst").value=data.cgst;
										document.getElementById("sgst").value=data.sgst;
										document.getElementById("igst").value=data.igst;
										document.getElementById("cess").value=data.cess;
										document.getElementById("roundOff").value=data.roundOff;
								});
					
					
					
				});
		
		
	}
	
	
	function changeFreightAmt()
	{
		 
		var discPer = $("#discPer").val();
		
		var freightAmt = $("#freightAmt").val();
		
		var insuranceAmt = $("#insuranceAmt").val();
		
		
		$
		.getJSON(
				'${updateFreightAmt}',

				{
					 
					discPer : discPer,
					freightAmt : freightAmt,
					insuranceAmt : insuranceAmt,
					ajax : 'true',

				},
				function(data) {
					
					$('#table_grid td').remove();
					if (data == "") {
						

					}
					$.each(
							data,
							function(key, itemList) {
								
								
								
						
									var tr = $('<tr></tr>');
									tr.append($('<td></td>').html(key+1));

								  	tr.append($('<td></td>').html(itemList.incldTax));
								  	tr.append($('<td></td>').html(itemList.item));
								  	if(itemList.incldTax==0)
								  		{
								  		tr.append($('<td></td>').html('<input style="width:50px" type="text" onchange="changeRate('+key+')" id="poRate'+key+'" value="'+itemList.poRate+'" class="form-control" pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));

								  		}
								  	else
								  		{
								  		tr.append($('<td></td>').html('<input style="width:50px" type="text" onchange="changeRate('+key+')" id="poRate'+key+'" value="'+itemList.poRate+'" class="form-control" readonly>'));
								  		
										}
								  	tr.append($('<td></td>').html(itemList.reciedvedQty));
								  	tr.append($('<td></td>').html(itemList.rateCal));
								  	tr.append($('<td></td>').html(itemList.value));
								  	
								  	tr.append($('<td ></td>').html('<input style="width:50px;" type="text" onchange="changeRate('+key+')" id="discPer'+key+'" value="'+itemList.discPer+'"  class="form-control" pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));


								  	tr.append($('<td></td>').html(itemList.discAmt));
								  	tr.append($('<td></td>').html(itemList.cdPer));
								  	tr.append($('<td></td>').html(itemList.cdAmt));
								  	tr.append($('<td></td>').html(itemList.divFactor));
								  	tr.append($('<td></td>').html(itemList.insuAmt));
								  	tr.append($('<td></td>').html(itemList.freightAmt));
								  	
								  	tr.append($('<td></td>').html('<input style="width:50px" type="text" onchange="changeRate('+key+')" id="other1'+key+'" value="'+itemList.other1+'"  class="form-control"  pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));
								  	tr.append($('<td></td>').html('<input style="width:50px;" type="text" onchange="changeRate('+key+')" id="other2'+key+'" value="'+itemList.other2+'"  class="form-control"  pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));
 									tr.append($('<td></td>').html('<input style="width:50px;" type="text" onchange="changeRate('+key+')" id="other3'+key+'" value="'+itemList.other3+'"  class="form-control"  pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));
 									tr.append($('<td></td>').html('<input style="width:50px;" type="text" onchange="changeRate('+key+')" id="other4'+key+'" value="'+itemList.other4+'"  class="form-control"  pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));

								  	/* tr.append($('<td></td>').html(itemList.other1));
								  	tr.append($('<td></td>').html(itemList.other2)); */
								
								  	tr.append($('<td></td>').html(itemList.gst));
								  	tr.append($('<td></td>').html(itemList.cgst));
								  	tr.append($('<td></td>').html(itemList.sgst));
								  	tr.append($('<td></td>').html(itemList.igst));
								  	tr.append($('<td></td>').html(itemList.cess));
								  	tr.append($('<td></td>').html(itemList.taxableAmt));
								  	tr.append($('<td></td>').html(itemList.cgstAmt));
								  	tr.append($('<td></td>').html(itemList.sgstAmt));
								  	tr.append($('<td></td>').html(itemList.igstAmt));
								  	/* tr.append($('<td></td>').html(itemList.cessAmt)); */
								  	tr.append($('<td ></td>').html('<input style="width:50px" type="text" onchange="changeRate('+key+')" id="cessAmt'+key+'" value="'+itemList.cessAmt+'"  class="form-control"  pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));

								  	/* tr.append($('<td></td>').html('<span  class="glyphicon glyphicon-ok" onclick="changeRate('+key+')" id="ok'+key+'"></span>'));
								  	 */
									$('#table_grid tbody').append(tr);
								

								 

							})
							$ .getJSON( '${updateHeader}',

									{
					 
									ajax : 'true',

									},
								function(data)
								{ 
										alert("data.igst"+data.igst);
										 
										alert("round off"+data.roundOff); 
										
										document.getElementById("basicValue").value=data.basicValue;
										document.getElementById("billAmount").value=data.billAmount;
										document.getElementById("discAmt2").value=data.discAmt2;
										document.getElementById("discAmt").value=data.discAmt;
										document.getElementById("Other1").value=data.other1;
										document.getElementById("Other2").value=data.other2;
										document.getElementById("Other3").value=data.other3;
										document.getElementById("Other4").value=data.other4;
										document.getElementById("cgst").value=data.cgst;
										document.getElementById("sgst").value=data.sgst;
										document.getElementById("igst").value=data.igst;
										document.getElementById("cess").value=data.cess;
										document.getElementById("roundOff").value=data.roundOff;
								});
					
				});
		
		
	}
	</script>

</body>
</html>