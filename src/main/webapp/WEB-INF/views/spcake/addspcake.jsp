<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

</head> --%>
	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

<body>

	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include> 



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
						<i class="fa fa-file-o"></i>Special Cake
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
										<i class="fa fa-bars"></i> Add Special Cake
										
									</h3>
                         <div class="box-tool">
								<a href="${pageContext.request.contextPath}/showSpecialCake">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
								</div>
								<div class="box-content">
									<form action="addSpCakeProcess" class="form-horizontal"
									id="validation-form"
										enctype="multipart/form-data" method="post">



										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Code</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="spc_code" id="spc_code"
													placeholder="Code" class="form-control"
													data-rule-required="true" />
											</div>
										</div>


										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Name </label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="spc_name" id="spc_name" 
													class="form-control" placeholder="Name" data-rule-required="true"  />
											</div>

										</div>
											<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Image</label>
											<div class="col-sm-9 col-lg-10 controls">
												<div class="fileupload fileupload-new"
													data-provides="fileupload">
													<div class="fileupload-new img-thumbnail"
														style="width: 200px; height: 150px;">
														<img
															src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image"
															alt="" />
													</div>
													<div
														class="fileupload-preview fileupload-exists img-thumbnail"
														style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
													<div>
														<span class="btn btn-default btn-file"><span
															class="fileupload-new">Select image</span> <span
															class="fileupload-exists">Change</span> <input
															type="file" class="file-input" name="sp_image"
															id="fr_image" /></span> <a href="#"
															class="btn btn-default fileupload-exists"
															data-dismiss="fileupload">Remove</a>
													</div>
												</div>

											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Description</label>
											<div class="col-sm-9 col-lg-10 controls">
												<textarea class="form-control" name="sp_desc" data-rule-required="true"
													cols="" rows="" id="sp_desc" placeholder="Description"></textarea>

											</div>

										</div>
										
										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Type</label>

											<div class="col-sm-9 col-lg-10 controls">
												<select class="form-control input-sm" name="spc_type" id=""spc_type"">

													<option name="Chocolate" value="1">Chocolate</option>

													<option name="FC" value="2">FC</option>

													<option name="All" value="0">All</option>
												</select>
											</div>
											
										</div>
											<!-- <div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="spc_type" id="spc_type"
													class="form-control" />
											</div> -->



										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Min
												Weight</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="min_weight" id="min_weight"
													
													placeholder="Min Weight" data-rule-required="true"
													class="form-control" data-rule-number="true"
													 />
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Max
												Weight</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="max_weight" id="max_weight"
													max_weight""
													placeholder="Max Weight" data-rule-required="true"
													class="form-control" data-rule-number="true"
													 />
											</div>
										</div>


										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Book
												Before</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="book_before" id="book_before"
													placeholder="Book Before" class="form-control"
													data-rule-required="true"
													data-rule-number="true" />
											</div>
										</div>

										
                                      <div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Local Rate
											</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="sp_rate1" id="sp_rate1"
													 placeholder="Special Cake Rate 1" class="form-control"
													 data-rule-required="true"
													data-rule-number="true" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Out Station Rate
											</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="sp_rate2" id="sp_rate2"
													 placeholder="Special Cake Rate 2" class="form-control"
													 data-rule-required="true"
													data-rule-number="true" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Special Rate
											</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="sp_rate3" id="sp_rate3"
									                placeholder="Special Cake Rate 3" class="form-control"
									                data-rule-required="true"
													data-rule-number="true" />
											</div>
										</div>
										  <div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Local MRP
											</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="mrp_rate1" id="mrp_rate1"
													 placeholder="MRP Rate 1" class="form-control"
													 data-rule-required="true"
													data-rule-number="true" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Out Station MRP
											</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="mrp_rate2" id="mrp_rate2"
													 placeholder="MRP Rate 2" class="form-control"
													 data-rule-required="true"
													data-rule-number="true" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Special MRP
											</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="mrp_rate3" id="mrp_rate3"
									                placeholder="MRP Rate 3" class="form-control"
													data-rule-number="true" data-rule-required="true" />
											</div>
										</div>
									
                                       <div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Minimum Order Qty
											</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="order_qty" id="order_qty"
											        placeholder="Order Quantity" class="form-control"
													data-rule-required="true" data-rule-number="true" />
											</div> 
										</div>
                                       <div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Order Discount %
											</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="order_disc" id="order_disc"
											        placeholder="Order Discount" class="form-control"
													data-rule-number="true" data-rule-required="true" />
											</div>
										</div>
                                       <div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">IGST %
											</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="tax_3" id="tax_3"
													tax_3"" placeholder="IGST" class="form-control"
													data-rule-required="true" data-rule-number="true" value="0.0" onchange="calTotalGst()"/>
											</div>
										</div>
										 <div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">CGST %
											</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="tax_1" id="tax_1"
													 placeholder="CGST" class="form-control"
													data-rule-required="true"
													data-rule-number="true" value="0.0" onchange="calTotalGst()"/>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">SGST %
											</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="tax_2" id="tax_2"
													 placeholder="SGST" class="form-control"	
													data-rule-required="true" data-rule-number="true"
													value="0.0" />
													
											</div>
										</div>

                                      
                                 <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Total GST Applicable %</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="total_gst_appli" id="total_gst_appli"
											placeholder="Total GST Applicable" class="form-control"
											data-rule-required="true" data-rule-number="true" disabled/>
									</div>
								</div>
							
										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Events</label>
											<div class="col-sm-9 col-lg-10 controls">
												<select data-placeholder="Select Events"
													class="form-control chosen" multiple="multiple"
													tabindex="6" name="spe_id_list[]" id="spe_id_list"data-rule-required="true">
													<option value=""> </option>
													<optgroup label="EVENTS">


														<c:forEach items="${eventList}" var="eventList">

															<option value="${eventList.speId}">${eventList.speName}</option>
														</c:forEach>
													</optgroup>

												</select>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">ERP
												Link Code </label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="erplinkcode" id="erplinkcode"
													placeholder="ERP Link Code" class="form-control"
													data-rule-required="true"/>

											</div>
										</div>

                                         <div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Is Customer Choice Cake?</label>
											<div class="col-sm-9 col-lg-10 controls">
												<label class="radio-inline"> <input type="radio"
													name="is_cust_choice_ck" id="is_cust_choice_ck" value="0"
													checked>No
												</label> <label class="radio-inline"> <input type="radio"
													name="is_cust_choice_ck" id="is_cust_choice_ck" value="1">
													Yes
												</label>
											</div>
										</div>
                                       <div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Is Addon Rate Appli?</label>
											<div class="col-sm-9 col-lg-10 controls">
												<label class="radio-inline"> <input type="radio"
													name="is_addon_rate_appli" id="is_addon_rate_appli" value="0"
													checked>No
												</label> <label class="radio-inline"> <input type="radio"
													name="is_addon_rate_appli" id="is_addon_rate_appli" value="1">
													Yes
												</label>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Type 2
												Applicable ?</label>
											<div class="col-sm-9 col-lg-10 controls">
												<label class="radio-inline"> <input type="radio"
													name="type_2_applicable" id="type_2_applicable" value="0"
													checked>No
												</label> <label class="radio-inline"> <input type="radio"
													name="type_2_applicable" id="type_2_applicable" value="1">
													Yes
												</label>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Is
												Used?</label>
											<div class="col-sm-9 col-lg-10 controls">
												<label class="radio-inline"> <input type="radio"
													name="is_used" id="is_used" value="0" checked > No
												</label> <label class="radio-inline"> <input type="radio"
													name="is_used" id="is_used" value="1" /> Yes
												</label>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Allow
												to upload Photo</label>
											<div class="col-sm-9 col-lg-10 controls">
												<label class="radio-inline"> <input type="radio"
													name="allowphupload" id="allowphupload" value="0" checked>
													No
												</label> <label class="radio-inline"> <input type="radio"
													name="allowphupload"  id="allowphupload" value="1" />
													Yes
												</label>
											</div>
										</div>

                                       	<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Is Slot Used?</label>
											<div class="col-sm-9 col-lg-10 controls">
												<label class="radio-inline"> <input type="radio"
													name="isSlotUsed" id="isSlotUsed" value="0" checked>
													No
												</label> <label class="radio-inline"> <input type="radio"
													name="isSlotUsed"  id="isSlotUsed" value="1" />
													Yes
												</label>
											</div>
										</div>
                                       

										<div class="form-group">
											<div
												class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
												<button type="submit" class="btn btn-primary" style="width: 70px" onclick="return validation()">
														 Save
														</button>
										
												<button type="button" class="btn">Cancel</button>

												<!--<button type="button" class="btn">Cancel</button>-->
											</div>
										</div>
									</form>
								</div>
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
	<%-- <script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/date.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/daterangepicker.js"></script> --%>
	
</body>
		
<script>
    function calTotalGst() {
   
	  var igst=parseFloat($("#tax_3").val());
	  var cgst=parseFloat($("#tax_1").val());
	  var sgst=parseFloat(igst-cgst);
	  var totGst=parseFloat(cgst+sgst);
	  document.getElementById("tax_2").setAttribute('value',sgst);
	  document.getElementById("total_gst_appli").setAttribute('value', totGst);
}
   
</script>	

</html>