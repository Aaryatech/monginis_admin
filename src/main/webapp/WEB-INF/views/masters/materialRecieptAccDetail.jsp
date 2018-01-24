<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
 	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
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
					<form onsubmit="return confirm('Do you really want to submit the form?');" id="validation-form" class="form-horizontal"
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
						
				</c:when>
				</c:choose>

						
					<br>  <br>
				

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
												<th>In Qty</th>
												<th>Stock Qty</th>
												<th>Rejected Qty</th>
												<th>Varified Rate</th>
												<th style="width:500%;">PO Rate</th> 
												<th>Rate</th> 
												<th>Value</th>
												<th style="width:500%;">Disc Per</th>
												<th>Disc Rs</th>
												<th>Cd %</th>
												<th>Cd Rs</th>
												<th>Division factor</th>
												<th>Insu Rs</th>
												<th>Freight Rs</th>
												<th style="width:200%;">Other1 Rs(Discount)</th>
												<th style="width:200%;">Other2 Rs(Discount)</th>
												<th style="width:200%;">Other3 Rs(Extra Charges)</th>
												<th style="width:200%;">Other4 Rs(Extra Charges)</th>
												<th>GST</th>
												<th>CGST</th>
												<th>SGST</th>
												<th>IGST</th>
												<th>CESS</th>
												<th>Taxable Rs</th>
												
												<th>CGST Rs</th>
												<th>SGST Rs</th>
												<th>IGST Rs</th>
												<th>CESS Rs</th>
												

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

													<td style="width:75px"><c:out
															value="${materialRecieptAccList.item}" /> 
													</td>
															
													<td><input style="width:100px" type="text"
													onchange="changeRate(${count.index})"
													name="recQty${count.index}"  id="recQty${count.index}"
									  class="form-control" value="${materialRecieptAccList.reciedvedQty}" pattern="[+-]?([0-9]*[.])?[0-9]+" required></td>
														 
														 
															
													<td><c:out
															value="${materialRecieptAccList.stockQty}" /></td>
													<td><c:out
															value="${materialRecieptAccList.rejectedQty}" /></td>
													 
													<td ><input style="width:100px" type="text"
													onchange="changeRate(${count.index})"
													name="poRate${count.index}"  id="poRate${count.index}"
									  class="form-control" value="${materialRecieptAccList.varifiedRate}" pattern="[+-]?([0-9]*[.])?[0-9]+" required></td>
													
													 
													<td ><c:out
															value="${materialRecieptAccList.poRate}" />
									  				</td>
													 
													<td><c:out value="${materialRecieptAccList.rateCal}" />
													</td>
													
													<td><c:out value="${materialRecieptAccList.value}" />
													</td>
													<td> 
													<input style="width:100px;" onchange="changeRate(${count.index})" type="text" name="discPer${count.index}" id="discPer${count.index}"
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
													<td><input style="width:100px;" onchange="changeRate(${count.index})" type="text" name="other1${count.index}" id="other1${count.index}"
									value="${materialRecieptAccList.other1}" class="form-control" pattern="[+-]?([0-9]*[.])?[0-9]+" required> 
													</td>
													<td><c:out value="${materialRecieptAccList.other2}" />  
													</td>
													<td><input style="width:100px;" onchange="changeRate(${count.index})" type="text" name="other3${count.index}" id="other3${count.index}"
									value="${materialRecieptAccList.other3}" class="form-control" pattern="[+-]?([0-9]*[.])?[0-9]+" required> 
													</td>
													<td><c:out value="${materialRecieptAccList.other4}" /> 
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
													<td><input style="width:100px" onchange="changeRate(${count.index})" type="text" name="cessAmt${count.index}" id="cessAmt${count.index}"
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
							 
								<div class="col-md-3"></div>
								<div class="col-md-4"></div>
									<div class="col-md-2">value</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="basicValue" id="basicValue" class="form-control"
									value="${materialRecNoteHeader.basicValue}" readonly>
									</div>
									
							
							</div><br>
							<div class="box-content">
								<div class="col-md-3"></div>
								<div class="col-md-4"></div>
									<div class="col-md-2">Perticular Disc Total Amt</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="discAmt2" id="discAmt2" class="form-control"
									value="${materialRecNoteHeader.discAmt2}" readonly>
									</div>
									
							
							</div><br>
						
						<div class="box-content"> 
							<div class="col-md-3"></div>
								<div class="col-md-4"></div>
						 	<div class="col-md-2">Per Disc</div>
							<div class="col-md-3">
					 
								<input style="text-align:right; width:150px" type="text" onchange="changeFreightAmt();" name="discPer" id="discPer"
									value="${materialRecNoteHeader.discPer}" class="form-control" pattern="[+-]?([0-9]*[.])?[0-9]+" required>
									 
							</div>
						</div><br>
							<div class="box-content">
								<div class="col-md-3"></div>
								<div class="col-md-4"></div> 
						   <div class="col-md-2">Cash Disc</div>
							<div class="col-md-3">
					 
								<input style="text-align:right; width:150px" type="text" name="discAmt" id="discAmt"
									value="${materialRecNoteHeader.discAmt}" class="form-control"
									readonly>
									 
							</div>
						</div><br>
						<div class="box-content">
								<div class="col-md-3"></div>
								<div class="col-md-4"></div>
									<div class="col-md-2">Freight Amt</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" onchange="changeFreightAmt();" name="freightAmt" id="freightAmt" class="form-control"
									value="${materialRecNoteHeader.freightAmt}" pattern="[+-]?([0-9]*[.])?[0-9]+" required>
									</div>
									
							
							</div><br>
							<div class="box-content">
								<div class="col-md-3"></div>
								<div class="col-md-4"></div>
									<div class="col-md-2">Insu Amt</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" onchange="changeFreightAmt();" name="insuranceAmt" id="insuranceAmt" class="form-control"
									value="${materialRecNoteHeader.insuranceAmt}" pattern="[+-]?([0-9]*[.])?[0-9]+" required>
									</div>
									
							
							</div><br>
							<div class="box-content">
								<div class="col-md-3"></div>
								<div class="col-md-4"></div>
									<div class="col-md-2">Other1 Rs</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="Other1" id="other1" class="form-control"
									value="${materialRecNoteHeader.other1}" readonly>
									</div>
									
							
							</div><br>
							<div class="box-content">
								<div class="col-md-3"></div>
								<div class="col-md-4"></div>
									<div class="col-md-2">Other2 Rs</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="other2" onchange="changeFreightAmt();" id="other2" class="form-control"
									value="${materialRecNoteHeader.other2}" pattern="[+-]?([0-9]*[.])?[0-9]+" required>
									</div>
									
							
							</div><br>
							<div class="box-content">
								<div class="col-md-3"></div>
								<div class="col-md-4"></div>
									<div class="col-md-2">Other3 Rs</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="Other3" id="other3" class="form-control"
									value="${materialRecNoteHeader.other3}" readonly>
									</div>
									
							
							</div><br>
							<div class="box-content">
								<div class="col-md-3"></div>
								<div class="col-md-4"></div>
									<div class="col-md-2">Other4 Rs</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="other4" onchange="changeFreightAmt();" id="other4" class="form-control"
									value="${materialRecNoteHeader.other4}" pattern="[+-]?([0-9]*[.])?[0-9]+" required>
									</div>
									
							
							</div><br>
							<div class="box-content">
								<div class="col-md-3"></div>
								<div class="col-md-4"></div>
									<div class="col-md-2">CGST Rs</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="cgst" id="cgst" class="form-control"
									value="${materialRecNoteHeader.cgst}" readonly>
									</div>
									
							
							</div><br>
							<div class="box-content">
								<div class="col-md-3"></div>
								<div class="col-md-4"></div>
									<div class="col-md-2">SGST Rs</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="sgst" id="sgst" class="form-control"
									value="${materialRecNoteHeader.sgst}" readonly>
									</div>
									
							
							</div><br>
							<div class="box-content">
								<div class="col-md-3"></div>
								<div class="col-md-4"></div>
									<div class="col-md-2">IGST Rs</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="igst" id="igst" class="form-control"
									value="${materialRecNoteHeader.igst}" readonly>
									</div>
									
							
							</div><br>
							<div class="box-content">
								<div class="col-md-3"></div>
								<div class="col-md-4"></div>
									<div class="col-md-2">Cess Rs</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="cess" id="cess" class="form-control"
									value="${materialRecNoteHeader.cess}" readonly>
									</div>
									
							
							</div><br>
							
							<div class="box-content">
								<div class="col-md-3"></div>
								<div class="col-md-4"></div>
									<div class="col-md-2">Bill Total</div>
									<div class="col-md-3">
										<input style="text-align:right; width:150px" type="text" name="billAmount" id="billAmount" class="form-control"
									value="${materialRecNoteHeader.billAmount}" readonly>
									</div>
									
							
							</div><br>
							<div class="box-content">
								<div class="col-md-3"></div>
								<div class="col-md-4"></div>
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
								 <input type="submit" class="btn btn-info" value="Submit" >
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
	
		
		var varifiedRate = $("#poRate"+key+"").val();  
		var recQty = $("#recQty"+key+"").val(); 
		var discPer = $("#discPer"+key+"").val(); 
		var cessAmt = $("#cessAmt"+key+"").val(); 
		var other1 = $("#other1"+key+"").val(); 
		var other3 = $("#other3"+key+"").val();  
		var freightAmt = $("#freightAmt").val(); 
		var insuranceAmt = $("#insuranceAmt").val();
		var other2 = $("#other2").val(); 
		var other4 = $("#other4").val(); 
		
		if (recQty=="" || isNaN(recQty)) {

			 
			alert("Please Enter Valid Received Qty Of Item "+(key+1));
			document.getElementById("recQty"+key+"").value="0";
			recQty=0;

		}
		if (varifiedRate=="" || isNaN(varifiedRate)) {

			 
			alert("Please Enter Valid VarifiedRate Of Item "+(key+1));
			document.getElementById("poRate"+key+"").value="0";
			varifiedRate=0;

		}
		 if (discPer=="" || isNaN(discPer)) {

			 
			alert("Please Enter Valid Discount % Of Item "+(key+1));
			document.getElementById("discPer"+key+"").value="0";
			discPer=0;

		}
		if (cessAmt=="" || isNaN(cessAmt)) {

			 
			alert("Please Enter Valid Cess Amt Of Item "+(key+1));
			document.getElementById("cessAmt"+key+"").value="0";
			cessAmt=0;

		}
		if (other1=="" || isNaN(other1)) { 
			
			alert("Please Enter Valid Other1 Amt Of Item "+(key+1));
			document.getElementById("other1"+key+"").value="0";
			other1=0;

		}
		if (other3=="" || isNaN(other3)) {

			 
			alert("Please Enter Valid Other3 Amt Of Item "+(key+1));
			document.getElementById("other3"+key+"").value="0";
			other3=0;

		}
		
		
		$
		.getJSON(
				'${updatedetailed}',

				{
					 
					index : key,
					varifiedRate : varifiedRate,
					recQty : recQty,
					discPer : discPer,
					cessAmt : cessAmt,
					other3 : other3,
					other1 : other1,
					freightAmt : freightAmt,
					insuranceAmt : insuranceAmt,
					other4 : other4,
					other2 : other2,
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
								  	tr.append($('<td></td>').html('<input style="width:100px;" type="text" onchange="changeRate('+key+')" id="recQty'+key+'" value="'+itemList.reciedvedQty+'" class="form-control" pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));
								  	 
								  	tr.append($('<td></td>').html(itemList.stockQty));
								  	tr.append($('<td></td>').html(itemList.rejectedQty));
								  	
								  	tr.append($('<td></td>').html('<input style="width:100px;" type="text" onchange="changeRate('+key+')" id="poRate'+key+'" value="'+itemList.varifiedRate+'" class="form-control" pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));
								  	tr.append($('<td></td>').html(itemList.poRate));
										 
								  	
								  	tr.append($('<td></td>').html(itemList.rateCal));
								  	tr.append($('<td></td>').html(itemList.value));
								  	
								  	tr.append($('<td></td>').html('<input style="width:100px;" type="text" onchange="changeRate('+key+')" id="discPer'+key+'" value="'+itemList.discPer+'"  class="form-control"  pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));


								  	tr.append($('<td></td>').html(itemList.discAmt));
								  	tr.append($('<td></td>').html(itemList.cdPer));
								  	tr.append($('<td></td>').html(itemList.cdAmt));
								  	tr.append($('<td></td>').html(itemList.divFactor));
								  	tr.append($('<td></td>').html(itemList.insuAmt));
								  	tr.append($('<td></td>').html(itemList.freightAmt));
								  	tr.append($('<td></td>').html('<input style="width:100px;" type="text" onchange="changeRate('+key+')" id="other1'+key+'" value="'+itemList.other1+'"  class="form-control"  pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));
								  	tr.append($('<td></td>').html(itemList.other2));
								  	tr.append($('<td></td>').html('<input style="width:100px;" type="text" onchange="changeRate('+key+')" id="other3'+key+'" value="'+itemList.other3+'"  class="form-control"  pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));
								  	tr.append($('<td></td>').html(itemList.other4)); 
								  	tr.append($('<td></td>').html(itemList.gst));
								  	tr.append($('<td></td>').html(itemList.cgst));
								  	tr.append($('<td></td>').html(itemList.sgst));
								  	tr.append($('<td></td>').html(itemList.igst));
								  	tr.append($('<td></td>').html(itemList.cess));
								  	tr.append($('<td></td>').html(itemList.taxableAmt));
								  	tr.append($('<td></td>').html(itemList.cgstAmt));
								  	tr.append($('<td></td>').html(itemList.sgstAmt));
								  	tr.append($('<td></td>').html(itemList.igstAmt)); 
								  	tr.append($('<td ></td>').html('<input style="width:100px;" type="text" onchange="changeRate('+key+')" id="cessAmt'+key+'" value="'+itemList.cessAmt+'"  class="form-control"  pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));

								   
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
										document.getElementById("other1").value=data.other1;
										document.getElementById("other2").value=data.other2;
										document.getElementById("other3").value=data.other3;
										document.getElementById("other4").value=data.other4;
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
		var other2 = $("#other2").val(); 
		var other4 = $("#other4").val(); 
		 

		if (discPer=="" || isNaN(discPer)) {

			isValid = false;
			alert("Please Enter Valid Discount %");
			document.getElementById("discPer").value="0";
			discPer=0;

		}
		if (freightAmt=="" || isNaN(freightAmt)) {

			isValid = false;
			alert("Please Enter Valid Freight Amt");
			document.getElementById("freightAmt").value="0";
			freightAmt=0;

		}
		if (insuranceAmt=="" || isNaN(insuranceAmt)) {

			isValid = false;
			alert("Please Enter Valid Insurance Amt");
			document.getElementById("insuranceAmt").value="0";
			insuranceAmt=0;

		}
		if (other2=="" || isNaN(other2)) {

			isValid = false;
			alert("Please Enter Valid Other2 Amt");
			document.getElementById("other2").value="0";
			other2=0;

		}
		if (other4=="" || isNaN(other4)) {

			isValid = false;
			alert("Please Enter Valid Other4 Amt");
			document.getElementById("other4").value="0";
			other4=0;

		}
		 
		
	 
		
		$
		.getJSON(
				'${updateFreightAmt}',

				{
					 
					discPer : discPer,
					freightAmt : freightAmt,
					insuranceAmt : insuranceAmt,
					other4 : other4,
					other2 : other2,
					ajax : 'true',

				},
				function(data) {
					
					$('#table_grid td').remove();
					if (data == "") {
						

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
								  	  
								  	tr.append($('<td></td>').html('<input style="width:100px;" type="text" onchange="changeRate('+key+')" id="recQty'+key+'" value="'+itemList.reciedvedQty+'" class="form-control" pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));
								  	
								  	tr.append($('<td></td>').html(itemList.stockQty));
								  	tr.append($('<td></td>').html(itemList.rejectedQty));
								  	
								  	tr.append($('<td></td>').html('<input style="width:100px;" type="text" onchange="changeRate('+key+')" id="poRate'+key+'" value="'+itemList.varifiedRate+'" class="form-control" pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));
								  	tr.append($('<td></td>').html(itemList.poRate));
								  	tr.append($('<td></td>').html(itemList.rateCal));
								  	tr.append($('<td></td>').html(itemList.value));
								  	
								  	tr.append($('<td ></td>').html('<input style="width:100px;" type="text" onchange="changeRate('+key+')" id="discPer'+key+'" value="'+itemList.discPer+'"  class="form-control" pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));


								  	tr.append($('<td></td>').html(itemList.discAmt));
								  	tr.append($('<td></td>').html(itemList.cdPer));
								  	tr.append($('<td></td>').html(itemList.cdAmt));
								  	tr.append($('<td></td>').html(itemList.divFactor));
								  	tr.append($('<td></td>').html(itemList.insuAmt));
								  	tr.append($('<td></td>').html(itemList.freightAmt));
								  	
								  	tr.append($('<td></td>').html('<input style="width:100px" type="text" onchange="changeRate('+key+')" id="other1'+key+'" value="'+itemList.other1+'"  class="form-control"  pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));
								  	tr.append($('<td></td>').html(itemList.other2));
								  	tr.append($('<td></td>').html('<input style="width:100px;" type="text" onchange="changeRate('+key+')" id="other3'+key+'" value="'+itemList.other3+'"  class="form-control"  pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));
								  	tr.append($('<td></td>').html(itemList.other4));
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
								  	tr.append($('<td ></td>').html('<input style="width:100px" type="text" onchange="changeRate('+key+')" id="cessAmt'+key+'" value="'+itemList.cessAmt+'"  class="form-control"  pattern="[+-]?([0-9]*[.])?[0-9]+" required>'));

								   
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
										document.getElementById("other1").value=data.other1;
										document.getElementById("other2").value=data.other2;
										document.getElementById("other3").value=data.other3;
										document.getElementById("other4").value=data.other4;
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