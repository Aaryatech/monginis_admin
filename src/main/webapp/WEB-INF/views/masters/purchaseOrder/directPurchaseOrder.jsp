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
<title>Purchase Order</title>
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


	<c:url var="addItemToList" value="/addItemToList"></c:url>
		<c:url var="updateRmQty0" value="/updateRmQty0"></c:url>
		<c:url var="delItem" value="/delItem"></c:url>

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
					<i class="fa fa-file-o"></i>Purchase Order
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
					<i class="fa fa-bars"></i>Direct PO
				</h3>

			</div>

				<div class=" box-content">
					 
		<div class="box">
			<form id="submitPurchaseOrder"
				action="${pageContext.request.contextPath}/submitPurchaseOrder"
				method="post">
			<div class="box-content">
				<div class="col-md-2">PO No.  </div>
				<div class="col-md-4"><input type="text" id="po_no" name="po_no" value="00" class="form-control" readonly>
				</div>
				<div class="col-md-2">PO Date</div> 
				<div class="col-md-3">
				<input type="text" id="po_date" name="po_date" value="${todayDate}" class="form-control" readonly>
				<input type="hidden" id="flag" name="flag" value="1" class="form-control" readonly>
				
				</div>
				</div><br/>
				<div class="box-content">
				<div class="col-md-2" >Supplier</div>
									<div class="col-md-4">
										<select name="supp_id" id="supp_id" class="form-control" tabindex="6" required>
										<option value="">Select Supplier</option>
											 <c:forEach items="${supplierList}" var="supplierList"
							varStatus="count">
							  <option value="${supplierList.suppId}"><c:out value="${supplierList.suppName}"/></option>
 													 
												</c:forEach>
						

										</select>
									</div>
									<div class="col-md-2">Quotation Ref. No.  </div>
				<div class="col-md-3">
					<input type="text" name="quotation_ref_no" id="quotation_ref_no" class="form-control" required>
				</div>
				 
			</div><br/>
			<div class="box-content">
				<div class="col-md-2">Kind Attention</div>
				<div class="col-md-4">
					<input type="text" name="kind_attn" id="kind_attn" class="form-control" required>
				</div>
				<div class="col-md-2">Delivery At</div>
				<div class="col-md-3">
					<input type="text" name="delv_at" id="delv_at" class="form-control" required>
				</div>
				</div><br/>
			<div class="box-content">
				<div class="col-md-2" >Taxation</div>
									<div class="col-md-4">
										<select name="taxation" id="taxation" class="form-control" tabindex="6" required>
										<option value="">Select Taxation Type</option>
										<option value="1">Inclusive</option>
										<option value="2">Extra</option>
											
						

										</select>
									</div>
									<div class="col-md-2">Delivery Date  </div>
				<div class="col-md-3">
					<input type="text" name="delv_date"id="delv_date" class="form-control date-picker" required>
				</div>
				 
			</div> <br/>
			<div class="box-content">
			<div class="col-md-2" >PO Type</div>
									<div class="col-md-4">
										<select name="po_type" id="po_type" class="form-control" tabindex="6" required>
										<option value="">Select PO Type</option>
										<option value="1">Regular</option>
										<option value="2">Open</option>
											
						

										</select>
									</div>
									<div class="col-md-2">Discount % </div>
				<div class="col-md-3">
					<input type="text" name="disc_per" id="disc_per" class="form-control" required>
				</div>
									</div><br/>
			<div class="box-content">
				<div class="col-md-2" >Item</div>
									<div class="col-md-4">
										<select name="rm_id" id="rm_id" class="form-control" tabindex="6" required>
										<option value="">Select Raw Material</option>
											 <c:forEach items="${RawmaterialList}" var="RawmaterialList"
							varStatus="count">
							   <option value="${RawmaterialList.rmId}"><c:out value="${RawmaterialList.rmName}"/></option>
 													 
												</c:forEach>
						

										</select>
									</div>
									<div class="col-md-2">Quantity </div>
				<div class="col-md-2">
					<input type="text" name="rm_qty" id="rm_qty" class="form-control" required>
				</div>
				 <div class="col-md-2">
				<input type="button" class="btn btn-info pull-right" onclick="addItem()" value="Add Item"> 
					 
			</div>
			</div> <br/>
			<div class="box-content">
			<div class="col-md-2" >Quotation Ref. Date</div>
									<div class="col-md-4">
										 <input type="text" name="quotation_date" id="quotation_date" class="form-control date-picker" required>
									</div>
					</div><br/>
			
			
			
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
										<select name="transportation" id="transportation" class="form-control" tabindex="6" required>
										<option value="">Select Pay Terms</option>
											 <c:forEach items="${transporterList}" var="transporterList"
							varStatus="count">
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
				 
		/* 		document.getElementById("disc_per").readOnly = true;
				document.getElementById("taxation").readOnly = true;
				document.getElementById("quotation_ref_no").readOnly = true;
				document.getElementById("delv_at").readOnly = true;
				document.getElementById("delv_date").readOnly = true;
				document.getElementById("po_type").readOnly = true;
				document.getElementById("supp_id").readOnly = true;
				document.getElementById("kind_attn").readOnly = true; */
				
			 
				 
				   
			 
				$('#loader').show();

				$
						.getJSON(
								'${addItemToList}',

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

												
													
													
												  	tr.append($('<td></td>').html(key+1));

												  	tr.append($('<td></td>').html(itemList.rmName));

												  	tr.append($('<td></td>').html('<input type="text" id="poQty'+key+'" onkeyup="changeQty('+key+');"value="'+itemList.poQty+'" class="form-control" disabled="true">'));

												  	tr.append($('<td></td>').html(itemList.poRate+'<input type="hidden" id="poRate'+key+'" value='+itemList.poRate+' readonly>'));

												  	tr.append($('<td></td>').html(itemList.discPer));
												  	
												  	//tr.append($('<td></td>').html(itemList.poTaxable));
												  	tr.append($('<td></td>').html('<input type="text" value="'+itemList.poTaxable+'" id="poValue'+key+'" class="form-control" disabled="true">'));

												  	tr.append($('<td></td>').html(itemList.schDays));
												  	
												  	tr.append($('<td></td>').html(itemList.rmRemark));
												  	tr.append($('<td></td>').html('  <span class="glyphicon glyphicon-edit" id="edit'+key+'" onclick="edit('+key+');"> </span>  <span style="visibility: hidden;" class="glyphicon glyphicon-ok" onclick="submit('+key+');" id="ok'+key+'"></span>    <span class="glyphicon glyphicon-remove" onclick="del('+key+')" id="delete'+key+'"></span>  '));
												  	 



 

													$('#table_grid tbody').append(tr);

													 
 
												})  
								});

			 
		}
	</script>

	<script type="text/javascript">
		function validate() {

			var selectedFr = $("#selectFr").val();
			var selectedMenu = $("#selectMenu").val();

			var isValid = true;

			if (selectedFr == "" || selectedFr == null) {

				isValid = false;
				alert("Please select Franchise");

			} else if (selectedMenu == "" || selectedMenu == null) {

				isValid = false;
				alert("Please select Menu");

			}
			return isValid;

		}
		
	</script>

	<script type="text/javascript">
		function updateTotal(orderId, rate) {
			
			var newQty = $("#billQty" + orderId).val();

			var total = parseFloat(newQty) * parseFloat(rate);


			 $('#billTotal'+orderId).html(total);
		}
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
			//alert(qty);
			var rate=document.getElementById("poRate"+key).value;
			//alert("rate"+rate);
			 document.getElementById("poValue"+key).value=qty*rate;
			
		}
		function submit(key)
		{
			var qty=document.getElementById("poQty"+key).value;
			document.getElementById("poQty"+key).disabled = true;
			document.getElementById("edit"+key).style.visibility="visible";
			document.getElementById("ok"+key).style.visibility="hidden";
			alert(qty);
			$
			.getJSON(
					'${updateRmQty}',

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
			$
			.getJSON(
					'${delItem}',

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