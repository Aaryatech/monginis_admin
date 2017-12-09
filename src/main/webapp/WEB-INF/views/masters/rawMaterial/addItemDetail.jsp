<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Dashboard - MONGINIS Admin</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<!--base css styles-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/loader.css">

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
<c:url var="getRawMaterialList" value="/getRawMaterialList" />
<c:url var="getBaseQty" value="/getBaseQty" />
<c:url var="insertItemDetail" value="/insertItemDetail"/>

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
						<i class="fa fa-file-o"></i>Item Detail
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
								<i class="fa fa-bars"></i>Add Item Detail
							</h3>
							<div class="box-tool">
								<a href="">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							<!-- <div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> <a data-action="close" href="#"><i
									class="fa fa-times"></i></a>
							</div> -->
						</div>


						<div class="box-content">
							<form action="addItemDetails" method="post" class="form-horizontal" id=
									"validation-form"
										 method="post">
							

								<div class="form-group">
									<label class="col-sm-2 col-lg-2 control-label">Item Name</label>
									<div class="col-sm-9 col-lg-4 controls">
										<select name="item_id" id="item_id" class="form-control"placeholder="Item Name"data-rule-required="true">
											<option value="-1">Select Item</option>
									 	<c:forEach items="${itemsList}" var="itemsList"
													varStatus="count">
												<option value="${itemsList.id}"><c:out value="${itemsList.itemName}"/></option>
												</c:forEach> 
										</select>
									</div>
                                    <label class="col-sm-3 col-lg-1 control-label">Base Qty</label>
								 	<div class="col-sm-6 col-lg-4 controls">
						     	    <input type="text" name="base_qty" id="base_qty" class="form-control"placeholder="Base Qty"data-rule-required="true"/>
									
									</div> 
									
								</div>
								<div class="box"><div class="box-title">
										<h3>
											<i class="fa fa-record"></i> Add Item 
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
										</div>
									</div></div>
								<div class="form-group">
									<label class="col-sm-2 col-lg-2 control-label">RM Type</label>
									<div class="col-sm-6 col-lg-4 controls">
                                    <select name="rm_type" id="rm_type" class="form-control" placeholder="Raw Material Type"data-rule-required="true" >
											<option value="-1">Select RM Type</option>
											<option value="1">Raw Material</option>
											<option value="2">Semi Finished</option>
									
								   </select>									
								   </div>

									<label class="col-sm-3 col-lg-1 control-label">Raw Material</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="rm_id" id="rm_id" class="form-control" placeholder="Raw Material"data-rule-required="true">
											<option value="-1">Select Raw Material</option>
										    
								</select>
									</div>
								</div>
							
								<div class="form-group">
								<!-- <label class="col-sm-3 col-lg-2 control-label">RM Unit</label>
									<div class="col-sm-6 col-lg-4 controls">-->
						     	<input type="hidden" name="rm_unit_id" id="rm_unit_id "class="form-control"placeholder="RM Unit"/>
									
									<!--</div> -->
								<label class="col-sm-3 col-lg-2 control-label">RM Qty</label>
					      	    <div class="col-sm-6 col-lg-4 controls">
							    <input type="text" name="rm_qty" id="rm_qty" class="form-control"placeholder="RM Qty"data-rule-required="true"/>
					     	    </div>
					     	    
					     	    <label class="col-sm-3 col-lg-1 control-label">RM Weight</label>
					      	    <div class="col-sm-5 col-lg-4 controls">
							    <input type="text" name="rm_weight"   id="rm_weight" class="form-control"placeholder="RM Weight(KG)"data-rule-required="true"/>
					     	    </div>
								</div>
				
								
					<div class="row">
						<div class="col-md-12" style="text-align: center">
							<input type="button" class="btn btn-info" value="ADD" name="add" id="add">


						</div>
					</div>
					</form>
					</br>
					<form action="addItemDetail" method="post" class="form-horizontal" id=
									"validation-form"
										 method="post">
						<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i> Item Detail List
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
										</div>
									</div>

									<div class="box-content">

										<div class="clearfix"></div>
										<div class="table-responsive" style="border: 0">
											<table width="100%" class="table table-advance" id="table1">
												<thead>
													<tr>
														<th width="45" style="width: 18px">Sr.No.</th>
														<th width="100" align="left">Item Name</th>
														<th width="100" align="left">RM Type</th>
														<th width="100" align="left">Raw Material</th>
														<th width="100" align="left">RM Unit</th>
														<th width="100" align="left">RM Weight</th>
														<th width="100" align="left">RM Qty</th>
														<th width="81" align="left">Action</th>
													</tr>
												</thead>
												<tbody>
													<%-- <c:forEach items="${routeList}" var="routeList" varStatus="count">
														<tr>
															<td><c:out value="${count.index+1}"/></td>
															<td align="left"><c:out
																	value="${routeList.routeName}"></c:out></td>
															<td align="left"><a
																href="updateRoute/${routeList.routeId }"><span
																	class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;

																<a href="deleteRoute/${routeList.routeId}"
																onClick="return confirm('Are you sure want to delete this record');"><span
																	class="glyphicon glyphicon-remove"></span></a></td>
														</tr>

													</c:forEach> --%>
												</tbody>
											</table>
										</div>
									</div>
								</div>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>

			<script type="text/javascript"
				src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
			<script type="text/javascript"
				src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>
				
</body>

<script type="text/javascript">
$(document).ready(function() { 
	$('#rm_type').change(
			function() {


	$.getJSON('${getRawMaterialList}', {
					rm_type : $(this).val(),
					ajax : 'true',
				},  function(data) {
					var html = '<option value="" selected >Select Raw Material</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].id + '">'
								+ data[i].name + '</option>';
					}
					html += '</option>';
					$('#rm_id').html(html);
				});
			});
			

});
</script>
<script type="text/javascript">
$(document).ready(function() { 
	$('#item_id').change(
			function() {


	$.getJSON('${getBaseQty}', {
					id : $(this).val(),
					ajax : 'true',
				},  function(data) {
					document.getElementById("base_qty").value = data;
				});
			});
			

});
</script>
<script type="text/javascript">
$(document).ready(function() {
	$("#add").click(function() {
		
 	var itemId = $("#item_id").val();
	//alert(itemId);
	var itemName=$('#item_id option:selected').text();
	//alert(selectedValue);

	var baseQty = $("#base_qty").val();
	//alert("baseQty"+baseQty)
	
	var rmType = $("#rm_type").val();

	var rmId = $("#rm_id").val();
	
	var rmWeight = $("#rm_weight").val();
	
	var rmQty = $("#rm_qty").val();
	
	var rmName=$('#rm_id option:selected').text();

	$.getJSON('${insertItemDetail}', {
		
		itemId : itemId,
		itemName:itemName,
		baseQty:baseQty,
		rmType:rmType,
		rmId:rmId,
		rmName:rmName,
		rmQty:rmQty,
		rmWeight:rmWeight,
		
		ajax : 'true',
	},  function(data) { 
 
		 //$('#loader').hide();
		var len = data.length;

		alert(len);

		$('#table1 td').remove();

		$.each(data,function(key, item) {

		var tr = $('<tr></tr>');

	  	tr.append($('<td></td>').html(key+1));

	  	tr.append($('<td></td>').html(item.itemName));

	  	if(item.rmType==1)
	  		{
		  	
	  		 tr.append($('<td></td>').html("Raw Material"));

	  		}
	  	else
	  		{
		  	tr.append($('<td></td>').html("Semi Finished"));

	  		}

	  	tr.append($('<td></td>').html(item.rmName));

	  	tr.append($('<td></td>').html(item.rmUomId));
	  	
	  	tr.append($('<td></td>').html(item.rmWeight));
	  	
	  	tr.append($('<td></td>').html(item.rmQty));
	  	
	 	tr.append($('<td></td>').html());
	  
	  	tr.append($('<td></td>').html());
	  	
		$('#table1 tbody').append(tr);

	 }); 
	
	});
});
});
</script>
</html>
