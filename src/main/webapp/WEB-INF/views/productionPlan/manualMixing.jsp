<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
 <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
 <jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
<body>
	 
	<c:url var="getSfDtailed" value="/getSfDtailed" />

	<c:url var="addSfIteminMixing" value="/addSfIteminMixing" />
<c:url var="updateQtyinMixing" value="/updateQtyinMixing" />
 
<c:url var="deleteIteminMixing" value="/deleteIteminMixing" />

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
						<i class="fa fa-file-o"></i>Manual Mixing Request
					</h1>

				</div>
			</div>
			<!-- END Page Title -->



			<!-- BEGIN Main Content -->
			<div class="row">
				<div class="col-md-12">
					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-bars"></i>Add Manual Mixing  <b>-</b>
							</h3>
							<div class="box-tool">
								<a href="">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>

						</div>
						<div class="box-content">
							<form action="${pageContext.request.contextPath}/insertManualMixing" method="post" class="form-horizontal"
								id="validation-form" method="post">

								<div class="form-group">
 
											<h4 align="center">Prod Header Id ${prodHeaderId}  
												Date ${prodDate}</h4> 

									<label class="col-sm-3 col-lg-2 control-label"> Semi Finished Item</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="sfName" id="sfName" class="form-control chosen"
											placeholder="Material Type"  />
											<option value="0">Select SemiFinished Item</option>

											<c:forEach items="${itemSfList}" var="itemSfList">
												<option value="${itemSfList.sfId}">${itemSfList.sfName}</option>
											</c:forEach>

										</select>
									</div>
									
									<label class="col-sm-3 col-lg-2 control-label">Multiplication Factor</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="mulfactor"   onkeyup="getTotal()" id="mulfactor" class="form-control"
											placeholder="Multiplication Factor"  readonly/>

									</div>
 
								</div>
 

								<div class="form-group">

									<label class="col-sm-3 col-lg-2 control-label">Qty</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="qty" id="qty" class="form-control"
											placeholder="Qty" onkeyup="getTotal()"   onkeypress="return IsNumeric(event);" ondrop="return false;" onpaste="return false;"/>

									</div>

									<label class="col-sm-3 col-lg-2 control-label">Total
										Qty</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="total" id="total"
											class="form-control" placeholder="total"
											 
											onkeypress="return IsNumeric(event);" ondrop="return false;" onpaste="return false;" readonly/>
									</div>

								</div>



								<div class="row">
									<div class="col-md-12" style="text-align: center">
										<input type="button" class="btn btn-info" value="Add Item"
											onclick="submitItem()">

									</div>
								</div>

								<div class="clearfix"></div>
								<div class="table-responsive" style="border: 0">
									<table width="100%" class="table table-advance" id="table1">
										<thead>
											<tr>

												<th width="140" style="width: 30px" align="left">Sr No</th>
												<th width="138" align="left">Semi Finished Name</th> 
												<th width="120" align="left">Uom</th>
												<th width="120" align="left">Qty</th>
												<th width="120" align="left">Multiplication Factor</th>
												<th width="120" align="left">Total</th>
												<th width="120" align="left">Action</th>

											</tr>

										</thead>

										<tbody>

											 
										</tbody>
									</table>
								</div>

								<input type="submit" class="btn btn-info" value="Submit List"
									onclick="insertItemDetail()"> <input type="hidden"
									id="prodHeaderId" value="${prodHeaderId}"> <input
									type="hidden" id="prodDate" value="${prodDate}">

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
	</div>
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
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/common.js"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>


	<script type="text/javascript">
function submitItem() {
				
				 
				var materialName=$("#sfName option:selected").html();
				var materialNameId= $('#sfName').val();  
				var qty = $('#qty').val();
				//alert("qty"+qty);
				var mulfactor = $('#mulfactor').val();
				var valid=0;
				
				if(materialNameId==null || materialNameId==0)
					{
					alert("Select Sf Item");
					valid=1;
					}
				else if(qty=="" || qty==0)
					{
					alert("Enter Qty");
					valid=1;
					}
				
				if(valid==0)
				{
				$.getJSON('${addSfIteminMixing}', {
					materialName : materialName,
					materialNameId : materialNameId, 
					mulfactor : mulfactor,
					qty : qty,
					 
					ajax : 'true',

				},function(data) {
					
					 //alert("data"+data);
					$('#table1 td').remove(); 
					document.getElementById("sfName").value = 0;
					document.getElementById("qty").value = "";
					document.getElementById("mulfactor").value = "";
					document.getElementById("total").value = ""; 
					$("#sfName").trigger("chosen:updated");
					$.each(
							data,
							function(key, itemList) {
							
								//alert(itemList);
								var tr = $('<tr></tr>'); 
								tr.append($('<td></td>').html(key+1)); 
								tr.append($('<td></td>').html(itemList.sfName)); 
								tr.append($('<td></td>').html(itemList.uomName));
								tr.append($('<td></td>').html('<input type="text" id="recdQty'+key+'" onkeypress="return IsNumeric(event);" ondrop="return false;" onpaste="return false;" onkeyup="changeQty('+key+');" value="'+itemList.qty+'" class="form-control" disabled="true">'));
								 
							  	tr.append($('<td></td>').html(itemList.mulFactor)); 
							  	tr.append($('<td></td>').html(itemList.totalQty)); 
							  	tr.append($('<td></td>').html('<span class="glyphicon glyphicon-edit" id="edit'+key+'" onclick="edit('+key+');"> </span><span style="visibility: hidden;" class="glyphicon glyphicon-ok" onclick="submit('+key+');" id="ok'+key+'"></span><span class="glyphicon glyphicon-remove"  onclick="del('+key+')" id="del'+key+'"></span>'));
												    
							  	$('#table1 tbody').append(tr);
							  	
							}) 

					});

					}
				}
				
function edit(key)
{
	
	document.getElementById("recdQty"+key).disabled = false;
	document.getElementById("edit"+key).style.visibility="hidden";
	document.getElementById("ok"+key).style.visibility="visible";
	
	//style="visibility: hidden;"
}
function submit(key)
{
	
	var qty=document.getElementById("recdQty"+key).value;
	document.getElementById("recdQty"+key).disabled = true;
	document.getElementById("edit"+key).style.visibility="visible";
	document.getElementById("ok"+key).style.visibility="hidden";
	
	$
	.getJSON(
			'${updateQtyinMixing}',

			{
				 
				index : key,
				updateQty : qty,
			
				ajax : 'true'

			},
			function(data) {
				
				$('#table1 td').remove();
				
				$.each(
						data,
						function(key, itemList) {
						
							//alert(itemList);
							var tr = $('<tr></tr>'); 
							tr.append($('<td></td>').html(key+1)); 
							tr.append($('<td></td>').html(itemList.sfName)); 
							tr.append($('<td></td>').html(itemList.uomName));
							tr.append($('<td></td>').html('<input type="text" id="recdQty'+key+'" onkeypress="return IsNumeric(event);" ondrop="return false;" onpaste="return false;" onkeyup="changeQty('+key+');" value="'+itemList.qty+'" class="form-control" disabled="true">'));
							 
						  	tr.append($('<td></td>').html(itemList.mulFactor)); 
						  	tr.append($('<td></td>').html(itemList.totalQty)); 
						  	tr.append($('<td></td>').html('<span class="glyphicon glyphicon-edit" id="edit'+key+'" onclick="edit('+key+');"> </span><span style="visibility: hidden;" class="glyphicon glyphicon-ok" onclick="submit('+key+');" id="ok'+key+'"></span><span class="glyphicon glyphicon-remove"  onclick="del('+key+')" id="del'+key+'"></span>'));
											    
						  	$('#table1 tbody').append(tr);
						  	
						}) 
				
			});
	
	
}
function del(key)
{
	
	var key=key; 
	//alert("key"+key);
	$
	.getJSON(
			'${deleteIteminMixing}',

			{
				 
				index : key,
				ajax : 'true'

			},
			function(data) {
				
				$('#table1 td').remove(); 

				if (data == "") {
					alert("No records found !!");

				}
			 

			  $.each(
							data,
							function(key, itemList) { 
								var tr = $('<tr></tr>'); 
								tr.append($('<td></td>').html(key+1)); 
								tr.append($('<td></td>').html(itemList.sfName)); 
								tr.append($('<td></td>').html(itemList.uomName));
								tr.append($('<td></td>').html('<input type="text" id="recdQty'+key+'" onkeypress="return IsNumeric(event);" ondrop="return false;" onpaste="return false;" onkeyup="changeQty('+key+');" value="'+itemList.qty+'" class="form-control" disabled="true">'));
								 
							  	tr.append($('<td></td>').html(itemList.mulFactor)); 
							  	tr.append($('<td></td>').html(itemList.totalQty)); 
							  	tr.append($('<td></td>').html('<span class="glyphicon glyphicon-edit" id="edit'+key+'" onclick="edit('+key+');"> </span><span style="visibility: hidden;" class="glyphicon glyphicon-ok" onclick="submit('+key+');" id="ok'+key+'"></span><span class="glyphicon glyphicon-remove"  onclick="del('+key+')" id="del'+key+'"></span>'));
												    
							  	$('#table1 tbody').append(tr);
							  	
							})
				
			});
	
	
}

</script>

 

	<script type="text/javascript">
 function getTotal() { 
	 var mulfactor = $("#mulfactor").val();
	 var qty = $("#qty").val(); 
	 var total=mulfactor*qty;
	 //alert("total"+total);
	 document.getElementById("total").value=total;
	 
}
</script>

	<script type="text/javascript">
$(document).ready(function() { 
	$('#sfName').change(
			function() {

	$.getJSON('${getSfDtailed}', {
		sfName : $(this).val(),
					
					ajax : 'true',
				},  function(data) {
					
	//alert("data"+data);
	document.getElementById("mulfactor").value=data.mulFactor; 
				})
					
					})
					
			});
			
var specialKeys = new Array();
specialKeys.push(8); //Backspace
function IsNumeric(e) {
    var keyCode = e.which ? e.which : e.keyCode
    var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1 || keyCode==9);
   // document.getElementById("error").style.display = ret ? "none" : "inline";
    return ret;
}

</script>


</body>
</html>