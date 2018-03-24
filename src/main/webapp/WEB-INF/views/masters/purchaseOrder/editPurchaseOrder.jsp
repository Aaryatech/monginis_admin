<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include> 

	<c:url var="addItemToListInOldItemList" value="/addItemToListInOldItemList"></c:url>
		<c:url var="updateRmQtyInEdit" value="/updateRmQtyInEdit"></c:url>
		<c:url var="deleteItem" value="/deleteItem"></c:url>
		<c:url var="getRmCategory" value="/getRmCategory" />
		<c:url var="getRmListByCatId" value="/getRmListByCatId" />
						<c:url var="getRmRateAndTax" value="/getRmRateAndTax" />
						<c:url var="getUomForRawMaterial" value="/getUomForRawMaterial" /> 

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
				<input type="hidden" id="flag" name="flag" value="${flag}" class="form-control">
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
					<input type="text" name="quotation_ref_no" id="quotation_ref_no" onkeypress="return IsNumeric(event);" ondrop="return false;" onpaste="return false;" value="${purchaseOrderHeader.quotationRefNo}" class="form-control" required>
				</div>
				 
			</div><br/>
			<div class="box-content">
				<div class="col-md-2">Kind Attention</div>
				<div class="col-md-4">
					<input type="text" name="kind_attn" id="kind_attn" value="${purchaseOrderHeader.kindAttn}" class="form-control" required>
				</div>
				<div class="col-md-2">Delivery At</div>
				<div class="col-md-3">
					<input type="text" name="delv_at" id="delv_at" value="${purchaseOrderHeader.delvAtRem}" class="form-control" required>
				</div>
				</div><br/>
			<div class="box-content">
				<div class="col-md-2" >Taxation</div>
									<div class="col-md-4">
										<select name="taxation" id="taxation" class="form-control chosen" tabindex="6">
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
					value="${purchaseOrderHeader.delvDateRem}" required>
				</div>
				 
			</div> <br/>
			<div class="box-content">
			<div class="col-md-2" >PO Type</div>
									<div class="col-md-4">
										<select name="po_type" id="po_type" class="form-control chosen" tabindex="6">
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
										 <input type="text" name="quotation_date" id="quotation_date" value="${purchaseOrderHeader.quotationRefDate}" class="form-control date-picker" required>
									</div>
									</div><br><br>
									<hr/>
									
									<div class="box-content">
										<div class="col-md-2" >Rm Group</div>
											<div class="col-md-3">
											<select name="rm_group" id="rm_group" class="form-control chosen" tabindex="6" onchange="getCat()">
											<option value="-1" disabled="disabled" selected="selected">Select RM Group</option>
											 <c:forEach items="${RawmaterialList}" var="RawmaterialList"
											varStatus="count">
							  					 <option value="${RawmaterialList.grpId}"><c:out value="${RawmaterialList.grpName}"/></option>
 													 
												</c:forEach>
						

										</select>
									</div>
									<div class="col-md-2">RM Category </div>
								<div class="col-md-3">
										<select name="rm_cat" id="rm_cat" class="form-control chosen" tabindex="6" onchange="getRm()">
										<option value="-1"disabled="disabled" selected="selected">Select RM Category</option>
											 
										</select>
								</div>
									
				 
								</div><br/>
								
								<div class="box-content">
								<div class="col-md-2" >Item</div>
									<div class="col-md-3">
										<select name="rm_id" id="rm_id" class="form-control chosen" placeholder="Select RM " tabindex="6">
										<option value="-1" disabled="disabled" selected="selected">Select Raw Material</option> 
										</select>
									</div>	
									<div class="col-md-2">Uom </div>
								<div class="col-md-3">
									<input type="text" placeholder="Uom" name="rm_uom" id="rm_uom" class="form-control" readonly>
								</div>
			
								
				
				
				
				
									
				 
			</div><br>
										
							<div class="box-content">
			
								<div class="col-md-2">Quantity </div>
										<div class="col-md-3">
										<input type="text" placeholder="Enetr RM Quantity" name="rm_qty" id="rm_qty" class="form-control">
									</div>
								
									
									<div class="col-md-2">Discount % </div>
								<div class="col-md-3">
									<input type="text" placeholder="Enter Discount %" name="disc_per" id="disc_per" value="0" class="form-control">
								</div>
					</div><br/>	<br>
					
					 
							<div class="box-content"> 
									<div class="col-md-2"></div>
									<div class="col-md-2"></div> 
									<div class="col-md-2">
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
										<select name="pay_terms" id="pay_terms" class="form-control chosen" tabindex="6">
										<c:forEach items="${supPaymentTerms}" var="supPaymentTerms" varStatus="count">
												<c:choose>
													<c:when test="${supPaymentTerms.payId==purchaseOrderHeader.payId}">
										
														<option selected value="${supPaymentTerms.payId}">${supPaymentTerms.payDesc}</option>
													</c:when>
										
												</c:choose> 
										</c:forEach> 
											 <c:forEach items="${supPaymentTerms}" var="supPaymentTerms"
							varStatus="count">
							   <option value="${supPaymentTerms.payId}"><c:out value="${supPaymentTerms.payDesc}"/></option>
 													 
												</c:forEach>
						

										</select>
									</div>
									<div class="col-md-2">PO Validity In Days</div>
				<div class="col-md-3">
					<input type="text" name="po_validity" id="po_validity" onkeypress="return IsNumeric(event);" ondrop="return false;" onpaste="return false;" value="${purchaseOrderHeader.validity}" class="form-control" required>
				</div>
				</div><br/>
				 	<div class="box-content">
				<div class="col-md-2" >Transportation</div>
									<div class="col-md-3">
										<select name="transportation" id="transportation" class="form-control chosen" tabindex="6">
										
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
									<div class="col-md-2" >Freight</div>
									<div class="col-md-3">
										<select name="freight" id="freight" class="form-control chosen" tabindex="6" required>
										<option value="${purchaseOrderHeader.freidhtRem}">${freightAmt}</option>
										<option value="1">Not Applicable</option>
										<option value="2">On Your Side</option>
										<option value="3">On Our Side</option>
										 </select>
									</div>
									</div><br/>
									
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
									<div class="box-content">
								<div class="col-md-2" >Insurance</div>
									<div class="col-md-3">
										<select name="insurance" id="insurance" class="form-control chosen" tabindex="6" required>
										<option value="${purchaseOrderHeader.insuRem}">${InsuAmt}</option>
										<option value="1">Not Applicable</option>
										<option value="2">On Your Side</option>
										<option value="3">On Our Side</option>
										</select>
									</div>
									<div class="col-md-2" >Sp.Instrucion</div>
									<div class="col-md-3">
					<input type="text" name="sp_instruction" id="sp_instruction" class="form-control" value="${purchaseOrderHeader.spRem}" required >
				</div>
									</div><br/><br/>
			
			<div class="row">
						<div class="col-md-12" style="text-align: center">
							<input type="submit" class="btn btn-info" value="Submit" onclick="check();">


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
				
				
				if(validate()){	
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
					$('#loader').hide();
					alert("Item rate  is not verified !!");
					
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
			 
		}
	</script>
	<script type="text/javascript">
		function validate() {
			

			 
	 
			var rm_qty = $("#rm_qty").val();
			var supp_id = $("#supp_id").val();
  
			var rm_id = $("#rm_id").val();
			var disc_per = $("#disc_per").val();

			var isValid = true;

			if (rm_qty=="") {

				isValid = false;
				alert("Please enter valid qty");

			}
			else if (isNaN(rm_qty) || rm_qty <= 0){
				isValid = false;
				alert("Please enter valid qty");
			}
			else if (rm_id == "" || rm_id == null) {

				isValid = false;
				alert("Please select RM Item");

			}
			
			else if (supp_id == "" || supp_id == null) {

				isValid = false;
				alert("Please select Supplier");

			}
			
			else if (  disc_per=="") {

				isValid = false;
				alert("Please enter valid Discount %");
				

			}
			else if(isNaN(disc_per) || disc_per < 0)
				{
				isValid = false;
				alert("Please enter valid Discount %");
				}
			return isValid;

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
						$('#rm_cat').trigger("chosen:updated");
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
						$('#rm_id').trigger("chosen:updated");
						$('#rm_id').formcontrol('refresh');

					});
				} 
		
	</script>

	<script type="text/javascript">
		function edit(key)
		{
			//alert(key);
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
			//alert("key"+key);
			var qty=document.getElementById("poQty"+key).value;
			document.getElementById("poQty"+key).disabled = true;
			document.getElementById("edit"+key).style.visibility="visible";
			document.getElementById("ok"+key).style.visibility="hidden";
			//alert(qty);
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
			//alert("key1"+key);
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

  <script type="text/javascript">
  $(document).ready(function() { 
		$('#rm_id').change(
				function() {
				var	rm_id =document.getElementById("rm_id").value; 
				
					$.getJSON('${getUomForRawMaterial}', { 
						ajax : 'true'
					}, function(uomlist) {
						
						 var uomlistlength = uomlist.length; 
						 cId=document.getElementById("rm_cat").value; 
									$.getJSON('${getRmListByCatId}', {
										
										catId : cId,
										ajax : 'true'
										
									}, 
									function(data) {
										
										var len = data.length;
										var uom; 
										for ( var i = 0; i < len; i++) { 
												if(data[i].rmId==rm_id)
													{
													uom=data[i].rmUomId;
													break;
													} 
										}
										
										for(var j = 0; j< uomlistlength; j++)
										{
										 
										if(uom==uomlist[j].uomId)
											{ 
											document.getElementById("rm_uom").value=uomlist[j].uom;
											break;
											}
										
										}
										
										 
									});

					});
					
				 
		
				});
	});
        var specialKeys = new Array();
        specialKeys.push(8); //Backspace
        function IsNumeric(e) {
            var keyCode = e.which ? e.which : e.keyCode
            var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1 || keyCode==9);
            //document.getElementById("error").style.display = ret ? "none" : "inline";
            return ret;
        }
        
        function check()
        {
        	
        	var freight = $("#freight").val();
        	var insurance = $("#insurance").val();
        	if(freight=="")
        		{
        		alert("Enter Freight Amt");
        		}
        	else if(insurance=="")
    		{
    		alert("Enter Insurance Amt");
    		}
        }
    </script>


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