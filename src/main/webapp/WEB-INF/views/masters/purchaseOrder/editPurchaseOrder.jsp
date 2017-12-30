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
<title>Edit Purchase Order</title>
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
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
</head>
<body>


	<c:url var="addItemToListInOldItemList" value="/addItemToListInOldItemList"></c:url>
		<c:url var="updateRmQtyInEdit" value="/updateRmQtyInEdit"></c:url>
		<c:url var="deleteItem" value="/deleteItem"></c:url>
		<c:url var="getRmCategory" value="/getRmCategory" />
		<c:url var="getRmListByCatId" value="/getRmListByCatId" />
						<c:url var="getRmRateAndTax" value="/getRmRateAndTax" />
		

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>


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
					<i class="fa fa-file-o"></i>Edit Purchase Order
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
					<i class="fa fa-bars"></i>Edit Direct PO
				</h3>

			</div>

				<div class=" box-content">
					 
		<div class="box">
			<form id="submitPurchaseOrder"
				action="${pageContext.request.contextPath}/submitEditPurchaseOrder"
				method="post">
			<div class="box-content">
				<div class="col-md-2">PO No.  </div>
				<div class="col-md-4"><input type="text" id="po_no" name="po_no" value="${purchaseOrderHeader.poNo}" class="form-control" readonly>
				</div>
				<div class="col-md-2">PO Date</div> 
				<div class="col-md-3">
				<input type="text" id="po_date" name="po_date" value="${purchaseOrderHeader.poDate}" class="form-control" readonly>
				<input type="hidden" id="status" name="status" value="${purchaseOrderHeader.poStatus}" class="form-control">
				
				</div>
				</div><br/>
				<div class="box-content">
				<div class="col-md-2" >Supplier</div>
									<div class="col-md-4">
									
									
				
										
										
										<c:forEach items="${supplierList}" var="supplierList" varStatus="count">
												<c:choose>
													<c:when test="${purchaseOrderHeader.suppId==supplierList.suppId}">
														<input type="text"  value="${supplierList.suppName}" class="form-control" readonly>
														<input type="hidden" name="supp_id" id="supp_id" value="${purchaseOrderHeader.suppId}">
														
													</c:when>
												 </c:choose>
											</c:forEach>
										
										
											 
						

										
									</div>
									<div class="col-md-2">Quotation Ref. No.  </div>
				<div class="col-md-3">
					<input type="text" name="quotation_ref_no" id="quotation_ref_no" value="${purchaseOrderHeader.quotationRefNo}" class="form-control">
				</div>
				 
			</div><br/>
			<div class="box-content">
				<div class="col-md-2">Kind Attention</div>
				<div class="col-md-4">
					<input type="text" name="kind_attn" id="kind_attn" value="${purchaseOrderHeader.kindAttn}" class="form-control">
				</div>
				<div class="col-md-2">Delivery At</div>
				<div class="col-md-3">
					<input type="text" name="delv_at" id="delv_at" value="${purchaseOrderHeader.delvAtRem}" class="form-control">
				</div>
				</div><br/>
			<div class="box-content">
				<div class="col-md-2" >Taxation</div>
									<div class="col-md-4">
										<select name="taxation" id="taxation" class="form-control" tabindex="6">
										<c:choose>
											<c:when test="${purchaseOrderHeader.taxationRem==1}">
											<c:set var="tax" value="Inclusive"></c:set>
											</c:when>
											
											<c:when test="${purchaseOrderHeader.taxationRem==2}">
											<c:set var="tax" value="Extra"></c:set>
											</c:when>
										
										</c:choose>
										<option selected value="${purchaseOrderHeader.taxationRem}">${tax}</option>
										<option value="1">Inclusive</option>
										<option value="2">Extra</option>
											
						

										</select>
									</div>
									<div class="col-md-2">Delivery Date  </div>
				<div class="col-md-3">
					<input type="text" name="delv_date" id="delv_date" class="form-control date-picker" 
					value="${purchaseOrderHeader.delvDateRem}">
				</div>
				 
			</div> <br/>
			<div class="box-content">
			<div class="col-md-2" >PO Type</div>
									<div class="col-md-4">
										<select name="po_type" id="po_type" class="form-control" tabindex="6">
										<c:choose>
											<c:when test="${purchaseOrderHeader.poType==1}">
											<c:set var="poType" value="Regular"></c:set>
											</c:when>
											
											<c:when test="${purchaseOrderHeader.poType==2}">
											<c:set var="poType" value="Open"></c:set>
											</c:when>
										
										</c:choose>
										<option selected value="${purchaseOrderHeader.poType}">${poType}</option>
										<option value="1">Regular</option>
										<option value="2">Open</option>
											
						

										</select>
									</div>
									<div class="col-md-2" >Quotation Ref. Date</div>
									<div class="col-md-3">
										 <input type="text" name="quotation_date" id="quotation_date" value="${purchaseOrderHeader.quotationRefDate}" class="form-control date-picker">
									</div>
									</div><br><br>
									<hr/>
									
									<div class="box-content">
										<div class="col-md-2" >Rm Group</div>
											<div class="col-md-4">
											<select name="rm_group" id="rm_group" class="form-control" tabindex="6" onchange="getCat()">
											<option value="-1" disabled="disabled" selected="selected">Select RM Group</option>
											 <c:forEach items="${RawmaterialList}" var="RawmaterialList"
											varStatus="count">
							  					 <option value="${RawmaterialList.grpId}"><c:out value="${RawmaterialList.grpName}"/></option>
 													 
												</c:forEach>
						

										</select>
									</div>
									<div class="col-md-2">Quantity </div>
										<div class="col-md-3">
										<input type="text" placeholder="Enetr RM Quantity" name="rm_qty" id="rm_qty" class="form-control">
									</div>
				 
								</div><br/>
								
								<div class="box-content">
			
			<div class="col-md-2">RM Category </div>
								<div class="col-md-4">
										<select name="rm_cat" id="rm_cat" class="form-control" tabindex="6" onchange="getRm()">
										<option value="-1"disabled="disabled" selected="selected">Select RM Category</option>
											 
										</select>
				</div>
				
				
				<div class="col-md-2">Discount % </div>
				<div class="col-md-3">
					<input type="text" placeholder="Enter Discount %" name="disc_per" id="disc_per" value="0" class="form-control">
				</div>
				
									
				 
			</div><br>
									
							<div class="box-content">
			
								<div class="col-md-2" >Item</div>
									<div class="col-md-4">
										<select name="rm_id" id="rm_id" class="form-control"placeholder="Select RM " tabindex="6">
										<option value="-1" disabled="disabled" selected="selected">Select Raw Material</option>
											 
						

										</select>
									</div>	
									
				<div class="col-md-1"></div>
				<div class="col-md-3">
				<input type="button" class="btn btn-info pull-right" onclick="addItem()" value="Add Item"> 
					 
			</div>
					</div><br/>
			
			
			<div class="box-content">
			
					</div><br/>
					
					<div align="center" id="loader" style="display: none">

							<span>
								<h4>
									<font color="#343690">Loading</font>
								</h4>
							</span> <span class="l-1"></span> <span class="l-2"></span> <span
						class="l-3"></span> <span class="l-4"></span> <span class="l-5"></span>
					<span class="l-6"></span>
				</div>
			
			
			
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
										<th>Action</th>

									</tr>
								</thead>
								<tbody>
								
								<c:forEach items="${purchaseOrderDetailedList}" var="purchaseOrderDetailedList"
														varStatus="count">


														<tr>
															<td><c:out value="${count.index+1}" /></td>
															<c:set var="srNo" value="${srNo+1}" />
															<td><c:out value="${purchaseOrderDetailedList.rmName}" /></td>


															<td><input type="text" id="poQty${count.index}" onkeyup="changeQty(${count.index});" value="${purchaseOrderDetailedList.poQty}" class="form-control" disabled="true"></td>
															<input type="hidden" id="poRate${count.index}" value="${purchaseOrderDetailedList.poRate}" />
															
															<td><c:out value="${purchaseOrderDetailedList.poRate}" /></td>
															<td><c:out value="${purchaseOrderDetailedList.discPer}" /></td>
															<td><input type="text" id="poValue${count.index}"  value="${purchaseOrderDetailedList.poQty*purchaseOrderDetailedList.poRate}" class="form-control" disabled="true"></td>
															
															
															<td><c:out value="${purchaseOrderDetailedList.schDays}" /></td>
															<td><c:out value="${purchaseOrderDetailedList.rmRemark}" /></td>
															<td align="left"><span class='glyphicon glyphicon-edit' onclick="edit(${count.index})" id="edit${count.index}"></span>
																
		                                                    <span  style="visibility: hidden;" class="glyphicon glyphicon-ok" onclick="submit(${count.index})" id="ok${count.index}"></span>
		                                                    <span class="glyphicon glyphicon-remove" onclick="del(${count.index})" id="del${count.index}"></span>
     														 </td>
												  	 

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
										<select name="pay_terms" id="pay_terms" class="form-control" tabindex="6">
										<option value="-1">Select Pay Terms</option>
											 <c:forEach items="${paymentTermsList}" var="paymentTermsList"
							varStatus="count">
							   <option value="${paymentTermsList.payId}"><c:out value="${paymentTermsList.payDesc}"/></option>
 													 
												</c:forEach>
						

										</select>
									</div>
									<div class="col-md-2">PO Validity </div>
				<div class="col-md-3">
					<input type="text" name="po_validity" id="po_validity" class="form-control">
				</div>
				</div><br/>
				 	<div class="box-content">
				<div class="col-md-2" >Transportation</div>
									<div class="col-md-3">
										<select name="transportation" id="transportation" class="form-control" tabindex="6">
										
										<c:forEach items="${transporterList}" var="transporterList" varStatus="count">
												<c:choose>
													<c:when test="${transporterList.tranId==purchaseOrderHeader.tranId}">
										
														<option selected value="${transporterList.tranId}">${transporterList.tranName}</option>
													</c:when>
										
												</c:choose> 
										</c:forEach> 
										
										
										
											 <c:forEach items="${transporterList}" var="transporterList" varStatus="count">
											 
							   <option value="${transporterList.tranId}"><c:out value="${transporterList.tranName}"/></option>
 													 
												</c:forEach>
						

										</select>
									</div>
									<div class="col-md-2" >Freight</div>
									<div class="col-md-3">
										<select name="freight" id="freight" class="form-control" tabindex="6">
										<option value="-1">Select Freight</option>
										<option value="1">Not Applicable</option>
										<option value="2">On Your Side</option>
										<option value="3">On Our Side</option>
										 </select>
									</div>
									</div><br/>
									<div class="box-content">
								<div class="col-md-2" >Insurance</div>
									<div class="col-md-3">
										<select name="insurance" id="insurance" class="form-control" tabindex="6">
										<option value="-1">Select Insurance Terms</option>
										<option value="1">Not Applicable</option>
										<option value="2">On Your Side</option>
										<option value="3">On Our Side</option>
										</select>
									</div>
									<div class="col-md-2" >Sp.Instrucion</div>
									<div class="col-md-3">
					<input type="text" name="sp_instruction" id="sp_instruction" class="form-control" placeholder="Enter Special Instruction">
				</div>
									</div><br/><br/>
			
			<div class="row">
						<div class="col-md-12" style="text-align: center">
							<input type="submit" class="btn btn-info" value="Submit">


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

 

	<script type="text/javascript">
		function addItem() {

		 

			  
				var taxation = $("#taxation").val();
				var kind_attn = $("#kind_attn").val();
				var rm_qty = $("#rm_qty").val();
				var supp_id = $("#supp_id").val();
				var po_type = $("#po_type").val();
				var delv_date = $("#delv_date").val();
				var delv_at = $("#delv_at").val();
				var quotation_ref_no = $("#quotation_ref_no").val();
				var po_no = $("#po_no").val();
				var po_date = $("#po_date").val();
				var rm_id = $("#rm_id").val();
				var disc_per = $("#disc_per").val();
				$('#loader').show();
				
				$
				.getJSON(
						'${getRmRateAndTax}',

						{
							 
							rm_id : rm_id,
							po_date : po_date,
							po_no : po_no,
							quotation_ref_no : quotation_ref_no,
							delv_at : delv_at,
							delv_date : delv_date,
							po_type : po_type,
							supp_id : supp_id,
							rm_qty : rm_qty,
							kind_attn : kind_attn,
							taxation : taxation,
							disc_per : disc_per,
							ajax : 'true'

						},
				 
				   
			 
				function(data) {
				if(data==0){
					alert("Item rate  is not verified !!");
					$('#loader').hide();
				}
				else{
					
					$
					.getJSON(
							'${addItemToListInOldItemList}',

							{
								 
								rm_id : rm_id,
								po_date : po_date,
								po_no : po_no,
								quotation_ref_no : quotation_ref_no,
								delv_at : delv_at,
								delv_date : delv_date,
								po_type : po_type,
								supp_id : supp_id,
								rm_qty : rm_qty,
								kind_attn : kind_attn,
								taxation : taxation,
								disc_per : disc_per,
								ajax : 'true'

							},
							function(data) {

								$('#table_grid td').remove();
								$('#loader').hide();

								if (data == "") {
									alert("No records found !!");

								}
							 

							  $.each(
											data,
											function(key, itemList) {
											

												var tr = $('<tr></tr>');

											
												
												if(itemList.delStatus==0)
												{
													tr.append($('<td></td>').html(key+1));

												  	tr.append($('<td></td>').html(itemList.rmName));

												  	tr.append($('<td></td>').html('<input type="text" id="poQty'+key+'" onkeyup="changeQty('+key+');"value="'+itemList.poQty+'" class="form-control" disabled="true">'));

												  	tr.append($('<td></td>').html(itemList.poRate+'<input type="hidden" id="poRate'+key+'" value='+itemList.poRate+' readonly>'));

												  	tr.append($('<td></td>').html(itemList.discPer));
												  	
												  	//tr.append($('<td></td>').html(itemList.poTaxable));
												  	tr.append($('<td></td>').html('<input type="text" value="'+itemList.poTaxable+'" id="poValue'+key+'" class="form-control" disabled="true">'));

												  	tr.append($('<td></td>').html(itemList.schDays));
												  	
												  	tr.append($('<td></td>').html(itemList.rmRemark));
												  	tr.append($('<td></td>').html('  <span class="glyphicon glyphicon-edit" id="edit'+key+'" onclick="edit('+key+');"></span> <span style="visibility: hidden;" class="glyphicon glyphicon-ok" onclick="submit('+key+');" id="ok'+key+'"></span><span class="glyphicon glyphicon-remove"  onclick="del('+key+')" id="del'+key+'"></span> '));
												  	 



 

													$('#table_grid tbody').append(tr);
												}
											  	

												 

											})  
							});
				
				}
				
		});
			 
		}
	</script>

	<script type="text/javascript">
	 
				function getCat() {
					
					$.getJSON('${getRmCategory}', {
						grpId : $('#rm_group').val(),
						ajax : 'true'
					}, function(data) {
						var html = '<option value="" disabled="disabled" selected >Select Category</option>';
						
						var len = data.length;
						for ( var i = 0; i < len; i++) {
							html += '<option value="' + data[i].catId + '">'
									+ data[i].catName + '</option>';
						}
						html += '</option>';
						$('#rm_cat').html(html);
						$('#rm_cat').formcontrol('refresh');

					});
				} 
	 
				function getRm() {
					
					$.getJSON('${getRmListByCatId}', {
						catId : $('#rm_cat').val(),
						ajax : 'true'
					}, function(data) {
						var html = '<option value="" disabled="disabled" selected >Select Category</option>';
						
						var len = data.length;
						for ( var i = 0; i < len; i++) {
							html += '<option value="' + data[i].rmId + '">'
									+ data[i].rmName + '</option>';
						}
						html += '</option>';
						$('#rm_id').html(html);
						$('#rm_id').formcontrol('refresh');

					});
				} 
		
	</script>

	<script type="text/javascript">
		function edit(key)
		{
			alert(key);
			document.getElementById("poQty"+key).disabled = false;
			document.getElementById("edit"+key).style.visibility="hidden";
			document.getElementById("ok"+key).style.visibility="visible";
			
			//style="visibility: hidden;"
		}
		function changeQty(key)
		{
			
			var qty=document.getElementById("poQty"+key).value;
			alert(qty);
			var rate=document.getElementById("poRate"+key).value;
			alert("rate"+rate);
			 document.getElementById("poValue"+key).value=qty*rate;
			
		}
		function submit(key)
		{
			alert("key"+key);
			var qty=document.getElementById("poQty"+key).value;
			document.getElementById("poQty"+key).disabled = true;
			document.getElementById("edit"+key).style.visibility="visible";
			document.getElementById("ok"+key).style.visibility="hidden";
			alert(qty);
			$
			.getJSON(
					'${updateRmQtyInEdit}',

					{
						 
						index : key,
						updateQty : qty,
					
						ajax : 'true'

					},
					function(data) {
						
					});
			
			
		}
		
		function del(key)
		{
			alert("key1"+key);
			var key=key;
			$('#loader').show();
			$
			.getJSON(
					'${deleteItem}',

					{
						 
						index : key,
						
					
						ajax : 'true'

					},
					function(data) {
						
						$('#table_grid td').remove();
						$('#loader').hide();

						if (data == "") {
							alert("No records found !!");

						}
					 

					  $.each(
									data,
									function(key, itemList) {
									

										var tr = $('<tr></tr>');

									
										
										if(itemList.delStatus==0)
											{
											
											tr.append($('<td></td>').html(key+1));

										  	tr.append($('<td></td>').html(itemList.rmName));

										  	tr.append($('<td></td>').html('<input type="text" id="poQty'+key+'" onkeyup="changeQty('+key+');"value="'+itemList.poQty+'" class="form-control" disabled="true">'));

										  	tr.append($('<td></td>').html(itemList.poRate+'<input type="hidden" id="poRate'+key+'" value='+itemList.poRate+' readonly>'));

										  	tr.append($('<td></td>').html(itemList.discPer));
										  	
										  	//tr.append($('<td></td>').html(itemList.poTaxable));
										  	tr.append($('<td></td>').html('<input type="text" value="'+itemList.poTaxable+'" id="poValue'+key+'" class="form-control" disabled="true">'));

										  	tr.append($('<td></td>').html(itemList.schDays));
										  	
										  	tr.append($('<td></td>').html(itemList.rmRemark));
										  	tr.append($('<td></td>').html('  <span class="glyphicon glyphicon-edit" id="edit'+key+'" onclick="edit('+key+');"> </span><span style="visibility: hidden;" class="glyphicon glyphicon-ok" onclick="submit('+key+');" id="ok'+key+'"></span> <span class="glyphicon glyphicon-remove"  onclick="del('+key+')" id="del'+key+'"></span>'));
										  	 





											$('#table_grid tbody').append(tr);
											}
									  	

										 

									})
						
					});
			
			
		}
		
	
		
	</script>

<!--   <script>
    /*
//  jquery equivalent
jQuery(document).ready(function() {
   jQuery(".main-table").clone(true).appendTo('#table-scroll .faux-table').addClass('clone');
   jQuery(".main-table.clone").clone(true).appendTo('#table-scroll .faux-table').addClass('clone2');
 });
*/
(function() {
  var fauxTable = document.getElementById("faux-table");
  var mainTable = document.getElementById("table_grid");
  var clonedElement = table_grid.cloneNode(true);
  var clonedElement2 = table_grid.cloneNode(true);
  clonedElement.id = "";
  clonedElement2.id = "";
  fauxTable.appendChild(clonedElement);
  fauxTable.appendChild(clonedElement2);
})();

</script> -->


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
		
		
		
		
</body>
</html>