<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script>
											window.jQuery
													|| document
															.write('<script src="${pageContext.request.contextPath}/resources/assets/jquery/jquery-2.0.3.min.js"><\/script>')
										</script>

<!--page specific css styles-->
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


	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<c:url var="getItemsByCatId" value="/getItemsByCatId" />


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
						<i class="fa fa-file-o"></i>Item Supplement
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
								<i class="fa fa-bars"></i> Add Item Sup
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showItemSupList">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>




						<div class="box-content">
							<form action="${pageContext.request.contextPath}/addItemSupProcess" class="form-horizontal"
								method="post" id="validation-form">

	                    <input type="hidden" name="id" id="id" value="${itemSupp.id}"/>
							  <c:choose>
							  <c:when test="${isEdit==1}">
							  <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Category</label>
									<div class="col-sm-9 col-lg-3 controls">
									<select name="cat_id" id="cat_id" class="form-control" placeholder="Select Category"disabled="disabled">
											<option value="-1">Select Category</option>
										 <c:forEach items="${mCategoryList}" var="mCategoryList">
										            	  <option value="${mCategoryList.catId}"><c:out value="${mCategoryList.catName}"></c:out></option>
										</c:forEach> 
												
								</select>	
									</div>
								</div>
                         
                              <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Item</label>
									<div class="col-sm-9 col-lg-3 controls">
									<select name="item_id" id="item_id" class="form-control" placeholder="Select Item" disabled="disabled">
											<option value="-1">Select Item</option>
											
												
								</select>	
									</div>
								</div>
								</c:when>
								<c:otherwise>
								<div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Category</label>
									<div class="col-sm-9 col-lg-3 controls">
									<select name="cat_id" id="cat_id" data-rule-required="true" class="form-control" placeholder="Select Category">
											<option value="-1">Select Category</option>
										 <c:forEach items="${mCategoryList}" var="mCategoryList">
										            	  <option value="${mCategoryList.catId}"><c:out value="${mCategoryList.catName}"></c:out></option>
										</c:forEach> 
												
								</select>	
									</div>
								</div>
                         
                              <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Item</label>
									<div class="col-sm-9 col-lg-3 controls">
									<select name="item_id" id="item_id" data-rule-required="true" class="form-control" placeholder="Select Item">
											<option value="-1">Select Item</option>
											
												
								</select>	
									</div>
								</div>
								
								</c:otherwise>
								</c:choose>
								
								<hr>
								<input type="hidden" name="sel_item_id" id="sel_item_id" value="${itemSupp.itemId}"/>
								 <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Item Name</label>
									<div class="col-sm-9 col-lg-8 controls">
										<input type="text" name="item_name" id="item_name"
										   class="form-control"
											 value="${itemSupp.itemName}" disabled="disabled"/>
									</div>
							  </div>
							  <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">HSN Code</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="item_hsncd" id="item_hsncd"
											placeholder="HSN Code" class="form-control"
											data-rule-required="true" value="${itemSupp.itemHsncd}"/>
									</div>
							  </div>
							  <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">UOM</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="item_uom" id="item_uom"
											placeholder="Item UOM" class="form-control"
											data-rule-required="true" value="${itemSupp.itemUom}"/>
									</div>
							  </div>
							  <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Actual Weight</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="actual_weight" id="actual_weight"
											placeholder="Actual Weight" class="form-control"
											data-rule-required="true" value="${itemSupp.actualWeight}"/>
									</div>
							  </div>
							   <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Base Weight</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="base_weight" id="base_weight"
											placeholder="Base Weight" class="form-control"
											data-rule-required="true" value="${itemSupp.baseWeight}"/>
									</div>
							  </div>
							  <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Input Per Qty</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="input_per_qty" id="input_per_qty"
											placeholder="Input Per Qty" class="form-control"
											data-rule-required="true" value="${itemSupp.inputPerQty}"/>
									</div>
							  </div>
							      <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Is Gate Sale?</label>
									<div class="col-sm-9 col-lg-3 controls">
												<c:choose>
												<c:when test="${itemSupp.isGateSale==0}">
												<label class="radio-inline"> <input type="radio"
													name="is_gate_sale" id="optionsRadios1" value="0" checked>
													No
												</label> <label class="radio-inline"> <input type="radio"
													name="is_gate_sale" id="optionsRadios1" value="1" />
													Yes
												</label>
												</c:when>
												
												<c:when test="${itemSupp.isGateSale==1}">
												<label class="radio-inline"> <input type="radio"
													name="is_gate_sale" id="optionsRadios1" value="0" >
													No
												</label> <label class="radio-inline"> <input type="radio"
													name="is_gate_sale" id="optionsRadios1" value="1" checked/>
													Yes
												</label>
												</c:when>
												<c:otherwise>
												<label class="radio-inline"> <input type="radio"
													name="is_gate_sale" id="optionsRadios1" value="0" checked>
													No
												</label> <label class="radio-inline"> <input type="radio"
													name="is_gate_sale" id="optionsRadios1" value="1" />
													Yes
												</label>
												</c:otherwise>
												</c:choose>
									</div>
							  </div>
							    <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Is Gate Sale Discount?</label>
									<div class="col-sm-9 col-lg-3 controls">
												<c:choose>
												<c:when test="${itemSupp.isGateSaleDisc==0}">
												<label class="radio-inline"> <input type="radio"
													name="is_gate_sale_disc" id="optionsRadios1" value="0" checked>
													No
												</label> <label class="radio-inline"> <input type="radio"
													name="is_gate_sale_disc" id="optionsRadios1" value="1" />
													Yes
												</label>
												</c:when>
												
												<c:when test="${itemSupp.isGateSaleDisc==1}">
												<label class="radio-inline"> <input type="radio"
													name="is_gate_sale_disc" id="optionsRadios1" value="0" >
													No
												</label> <label class="radio-inline"> <input type="radio"
													name="is_gate_sale_disc" id="optionsRadios1" value="1" checked/>
													Yes
												</label>
												</c:when>
												<c:otherwise>
												<label class="radio-inline"> <input type="radio"
													name="is_gate_sale_disc" id="optionsRadios1" value="0" checked>
													No
												</label> <label class="radio-inline"> <input type="radio"
													name="is_gate_sale_disc" id="optionsRadios1" value="1" />
													Yes
												</label>
												</c:otherwise>
												</c:choose>
									</div>
							  </div>
							    <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Is Allow BirthDay?</label>
									<div class="col-sm-9 col-lg-3 controls">
												<c:choose>
												<c:when test="${itemSupp.isAllowBday==0}">
												<label class="radio-inline"> <input type="radio"
													name="is_allow_bday" id="optionsRadios1" value="0" checked>
													No
												</label> <label class="radio-inline"> <input type="radio"
													name="is_allow_bday" id="optionsRadios1" value="1" />
													Yes
												</label>
												</c:when>
												
												<c:when test="${itemSupp.isAllowBday==1}">
												<label class="radio-inline"> <input type="radio"
													name="is_allow_bday" id="optionsRadios1" value="0" >
													No
												</label> <label class="radio-inline"> <input type="radio"
													name="is_allow_bday" id="optionsRadios1" value="1" checked/>
													Yes
												</label>
												</c:when>
												<c:otherwise>
												<label class="radio-inline"> <input type="radio"
													name="is_allow_bday" id="optionsRadios1" value="0" checked>
													No
												</label> <label class="radio-inline"> <input type="radio"
													name="is_allow_bday" id="optionsRadios1" value="1" />
													Yes
												</label>
												</c:otherwise>
												</c:choose>
									</div>
							  </div>
								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Submit">
<!-- 										<button type="button" class="btn">Cancel</button>
 -->									</div>
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


</body>
<script type="text/javascript">
$(document).ready(function() { 
	$('#cat_id').change(
			function() {
				
				$.getJSON('${getItemsByCatId}', {
					cat_id : $(this).val(),
					ajax : 'true'
				}, function(data) {
					var html = '<option value="-1"selected >Select Item</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].id + '">'
								+ data[i].itemName + '</option>';
					}
					html += '</option>';
					$('#item_id').html(html);

				});
			});
});
</script>
<script type="text/javascript">
$(document).ready(function() { 
	$('#item_id').change(
			function() {
				document.getElementById('sel_item_id').value=$(this).val();
				
				document.getElementById('item_name').value=$('#item_id option:selected').text();

			});
});
</script>
</html>