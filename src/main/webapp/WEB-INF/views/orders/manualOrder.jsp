<%@page import="com.ats.adminpanel.model.franchisee.SubCategory"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page
	import="java.io.*, java.util.*, java.util.Enumeration, java.text.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Manual Order</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

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

<!--basic scripts-->

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
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
<!-- http://forum.springsource.org/showthread.php?110258-dual-select-dropdown-lists -->
<!-- http://api.jquery.com/jQuery.getJSON/ -->

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>
<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
<style type="text/css">
select {
    width: 180px;
    height: 30px;
}
</style>


</head>
<body>

	<c:url var="setAllItemSelected" value="/setAllItemSelected" />

	<c:url var="findItemsByCatId" value="/getItemsOfMenuId" />
	<c:url var="findAllMenus" value="/getMenuForOrder" />
	<c:url var="insertItem" value="/insertItem" />
	<c:url var="deleteItems" value="/deleteItems" />
	<c:url var="generateManualBill" value="/generateManualBill" />
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
						<i class="fa fa-file-o"></i> Manual Order
					</h1>
				</div>
			</div>
			<!-- END Page Title -->


			<!-- BEGIN Main Content -->
			<div class="row">
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-12">
							<div class="box">
								<div class="box-title">
									<h3>
										<i class="fa fa-bars"></i>  Manual Order
									</h3>
									<div class="box-tool">
										<a href="${pageContext.request.contextPath}/"></a> <a data-action="collapse" href="#"><i
											class="fa fa-chevron-up"></i></a>
									</div>
								</div>


								
								<div class="box-content">
									<form action="${pageContext.request.contextPath}/addManualOrder" class="form-horizontal"
										id="validation-form" method="post">





										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Franchisee</label>
											<div class="col-sm-9 col-lg-5 controls">
												<select data-placeholder="Select Franchisee" name="fr_id"
													class="form-control chosen" tabindex="-1" id="fr_id"
													data-rule-required="true">
													<option value="0">Select Franchisee </option>
														<c:forEach
															items="${allFranchiseeAndMenuList.getAllFranchisee()}"
															var="franchiseeList">
															<option value="${franchiseeList.frId}">${franchiseeList.frName}</option>

														</c:forEach>
												

												</select>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Menu</label>
											<div class="col-sm-9 col-lg-5 controls">
												<select data-placeholder="Select Menu" name="menu"
													class="form-control chosen" tabindex="-1" id="menu"
													data-rule-required="true">
	                                            	<option value="0">Select Menu </option>                                                     


												</select>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Item</label>
											<div class="col-sm-9 col-lg-5 controls">
												<select data-placeholder="Select Item" name="items"
													class="form-control chosen" tabindex="-1" id="items"
													data-rule-required="true">
                                                   	<option value="0">Select Item </option>
												</select>
												
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Qty</label>
											<div class="col-sm-9 col-lg-5 controls">
											<input type="text" name="qty" value="0"	id="qty" class="form-control"/>
												
											</div>
										</div>
										<div class="form-group">
											<div
												class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
												<input type="button" class="btn btn-primary"
													value="Add Item" id="add">
												<!-- <button type="button" class="btn">Cancel</button> -->
											</div>
										</div>
									</form>
                                    						<div align="center" id="loader" style="display: none">

					<span>
						<h4>
							<font color="#343690">Loading</font>
						</h4>
					</span> <span class="l-1"></span> <span class="l-2"></span> <span
						class="l-3"></span> <span class="l-4"></span> <span class="l-5"></span>
					<span class="l-6"></span>
				</div>
								</div>
				
								<br>
									<div class=" box-content">
					<div class="row">
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th style="text-align:center;">Sr.No.</th>
										<th style="text-align:center;">Item Name</th>
										<th style="text-align:center;">Qty</th>
										<th style="text-align:center;">MRP</th>
										<th style="text-align:center;">Rate</th>
										<th style="text-align:center;">Total</th>
										<th style="text-align:center;">Action</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
				
					</div>
						  <div class="row">
						<div class="col-md-12" style="text-align: center">
							<input type="button" class="btn btn-info" value="Submit" name="Submit" id="Submit" onclick="generateBill()" disabled>

						</div>
					</div>

				</div>
								<div align="center" id="loader1" style="display: none">

					<span>
						<h4>
							<font color="#343690">Saving your order,please wait</font>
						</h4>
					</span> <span class="l-1"></span> <span class="l-2"></span> <span
						class="l-3"></span> <span class="l-4"></span> <span class="l-5"></span>
					<span class="l-6"></span>
				</div>	
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END Main Content -->

			<footer>
				<p>2018 © MONGINIS.</p>
			</footer>

			<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
				class="fa fa-chevron-up"></i></a>
		</div>
		<!-- END Content -->
	</div>
	<!-- END Container -->
 <script
	src="${pageContext.request.contextPath}/resources/assets/bootstrap/js/bootstrap.min.js"></script> 
<script>
$(function() {
    $('#typeselector').change(function(){
        $('.formgroup').hide();
        $('#' + $(this).val()).show();
    });
});
</script>
<script>
$('#select_all').click(function() {
	alert("alert");
    $('#items option').prop('selected', true);
    $('#items').chosen('destroy').val(["hola","mundo","cruel"]).chosen();
});
</script>

<script type="text/javascript">
$(document).ready(function() {
	
	
    $('#menu').change(
            function() {
            	
                $.getJSON('${findItemsByCatId}', {
                    menuId : $(this).val(),
                    ajax : 'true'
                }, function(data) {
                    var len = data.length;

					$('#items')
				    .find('option')
				    .remove()
				    .end()
				    $("#items").append(
                                 $("<option></option>").attr(
                                     "value","0").text("Select Item")
                             );
                    for ( var i = 0; i < len; i++) {
                    	 
                                
                        $("#items").append(
                                $("<option></option>").attr(
                                    "value", data[i].id).text(data[i].name)
                            );
                    }

                    $("#items").trigger("chosen:updated");
                });
            });
});
</script>


<script type="text/javascript">
$(document).ready(function() { 
	$('#fr_id').change(
			
			function() {
				$.getJSON('${findAllMenus}', {
					fr_id : $(this).val(),
					ajax : 'true'
				}, function(data) {
					var html = '<option value="0">Menu</option>';
				
					var len = data.length;
					
					$('#menu')
				    .find('option')
				    .remove()
				    .end()
				    
				 $("#menu").append(
                                $("<option></option>").attr(
                                    "value", "0").text("Select Menu")
                            );
					
					for ( var i = 0; i < len; i++) {
                        $("#menu").append(
                                $("<option></option>").attr(
                                    "value", data[i].menuId).text(data[i].menuTitle)
                            );
					}
					   $("#menu").trigger("chosen:updated");
				});
			});
});
</script>
<script type="text/javascript">

$(document).ready(function() {
	$("#add").click(function() {
		
	  var isValid = validation();
	if (isValid) {  
		
 	var frId = $('#fr_id option:selected').val();
	var menuId = $('#menu option:selected').val();
	var itemId=$('#items option:selected').val();
	var qty=$("#qty").val();add
	 $('#loader').show();

	$.getJSON('${insertItem}', {
		frId : frId,
		menuId:menuId,
		itemId:itemId,
		qty:qty,
		ajax : 'true',
		
	},  function(data) { 
 
		 $('#loader').hide();
		var len = data.length;
		document.getElementById("Submit").disabled = false;

		$('#table_grid td').remove();

		$.each(data,function(key, item) {

		var tr = $('<tr></tr>');

	  	tr.append($('<td></td>').html(key+1));

	  	tr.append($('<td></td>').html(item.itemName));

	  	tr.append($('<td style="text-align:right;"></td>').html(item.orderQty));
	  	
	  	tr.append($('<td style="text-align:right;"></td>').html(item.orderMrp));
	  	
	  	tr.append($('<td style="text-align:right;"></td>').html(item.orderRate));
	  	var total=item.orderQty*item.orderRate;
	  	tr.append($('<td style="text-align:right;"></td>').html(total.toFixed(2)));
	  	
	 	tr.append($('<td style="text-align:center;"></td>').html("<a href='#' class='action_btn' onclick=deleteItem("+key+")><i class='fa fa-trash-o  fa-lg'></i></a>"));
	  
		$('#table_grid tbody').append(tr);
	 
	 }); 
	
	});
	document.getElementById("fr_id").selectedIndex = "0";
	$("#fr_id").trigger("chosen:updated");
	document.getElementById("menu").selectedIndex = "0";
	$('#menu')
    .find('option')
    .remove()
    .end()
	$("#menu").trigger("chosen:updated");
	document.getElementById("items").selectedIndex = "0";
	$('#items')
    .find('option')
    .remove()
    .end()
	$("#items").trigger("chosen:updated");
	document.getElementById("qty").value =0;


	}
});

});
</script>
<script type="text/javascript">
function validation() {
	
	var frId = $('#fr_id').val();
	var menuId = $('#menu').val();
	var itemId=$('#items').val();
	var qty=$("#qty").val();

	var isValid = true;
	if (frId == ""||frId==0) { 
		isValid = false;
		alert("Please Select Franchisee");
	} else if (menuId == ""||menuId ==0) {
		isValid = false;
		alert("Please Select Menu ");
	}else if (itemId == ""||itemId=='0' ) {
		isValid = false;
		alert("Please Select Item");
	}else if (qty == ""||isNaN(qty) || qty < 1) {
		isValid = false;
		alert("Please Enter Valid Item Qty.");
	}
	return isValid;
}0
</script>
<script type="text/javascript">
function deleteItem(key){
	var isDel=confirm('Are you sure want to delete this record');
	if(isDel==true)
	{
	$.getJSON('${deleteItems}', {
		
		key:key,
	
		ajax : 'true',

	}, function(data) {
		
		var len = data.length;
        if(len==0)
        	{
    		document.getElementById("Submit").disabled = true;

        	}
		$('#table_grid td').remove();

		$.each(data,function(key, item) {
			
			var tr = $('<tr></tr>');

		  	tr.append($('<td></td>').html(key+1));

		  	tr.append($('<td></td>').html(item.itemName));

		  	tr.append($('<td style="text-align:right;"></td>').html(item.orderQty));
		  	
		  	tr.append($('<td style="text-align:right;"></td>').html(item.orderMrp));
		  	
		  	tr.append($('<td style="text-align:right;"></td>').html(item.orderRate));
		  	var total=item.orderQty*item.orderRate;
		  	tr.append($('<td style="text-align:right;"></td>').html(total.toFixed(2)));
		  	
		 	tr.append($('<td style="text-align:center;"></td>').html("<a href='#' class='action_btn' onclick=deleteItem("+key+")><abbr title='Delete'><i class='fa fa-trash-o  fa-lg'></i></abbr></a>"));
		  
			$('#table_grid tbody').append(tr);
			
		});
	});
	}
}
</script>
<script type="text/javascript">
function generateBill()
{
	$('#loader1').show();

	$.getJSON(
					'${generateManualBill}',
					{
						ajax : 'true'
					},
					function(data) {
						$('#table_grid td').remove();
						if(data.length!=0)
							{
                       alert("Orders Inserted Successfully");
               		document.getElementById("Submit").disabled = true;

							}
						$('#loader1').hide();
						
					});
	
}


</script>
</body>
</html>