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
	href="resources/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="resources/assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="resources/assets/data-tables/bootstrap3/dataTables.bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="resources/assets/bootstrap-fileupload/bootstrap-fileupload.css" />
<link rel="stylesheet" type="text/css"
	href="resources/assets/chosen-bootstrap/chosen.min.css" />
<link rel="stylesheet" type="text/css"
	href="resources/assets/bootstrap-timepicker/compiled/timepicker.css" />
<link rel="stylesheet" type="text/css"
	href="resources/assets/clockface/css/clockface.css" />
<link rel="stylesheet" type="text/css"
	href="resources/assets/bootstrap-datepicker/css/datepicker.css" />
<link rel="stylesheet" type="text/css"
	href="resources/assets/bootstrap-daterangepicker/daterangepicker.css" />



<!--page specific css styles-->

<!--flaty css styles-->
<link rel="stylesheet" href="resources/css/flaty.css">
<link rel="stylesheet" href="resources/css/flaty-responsive.css">

<link rel="shortcut icon" href="resources/img/favicon.png">

<script type="text/javascript" src="resources/js/common.js"></script>
</head>
<body>


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

								</div>
								<div class="box-content">
									<form action="addSpCake"
										class="form-horizontal" enctype="multipart/form-data">
										<input type="hidden" name="mode_add" id="mode_add"
											value="add_att">

										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Code</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="spc_code" id="spc_code"
													placeholder="Code" class="form-control"
													data-rule-required="true" data-rule-minlength="3" />
											</div>
										</div>

										<div class="form-group">
											<label for="minlengthfield"
												class="col-sm-3 col-lg-2 control-label">Minlength</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" class="form-control"
													placeholder="At least 3 characters" name="minlengthfield"
													id="minlengthfield" data-rule-minlength="3"
													data-rule-required="true">




												<div class="form-group">
													<label class="col-sm-3 col-lg-2 control-label">Name
													</label>
													<div class="col-sm-9 col-lg-10 controls">
														<input type="text" name="spc_name" id="spc_name"
															placeholder="Name" class="form-control"
															data-rule-required="true" />


													</div>
												</div>


												<div class="form-group">
													<label class="col-sm-3 col-lg-2 control-label">Type</label>
													<div class="col-sm-9 col-lg-10 controls">
														<select class="form-control input-sm" name="spc_type"
															id="spc_type">

															<option name="Chocolate" value="Chocolate">Chocolate</option>

															<option name="FC" value="FC">FC</option>

															<option name="All" value="All">All</option>
														</select>
													</div>
												</div>




												<div class="form-group">
													<label class="col-sm-3 col-lg-2 control-label">Min
														Weight</label>
													<div class="col-sm-9 col-lg-10 controls">
														<input type="text" name="min_weight" id=""
															min_weight""
													placeholder="Min Weight"
															class="form-control" data-rule-required="true" />
													</div>
												</div>

												<div class="form-group">
													<label class="col-sm-3 col-lg-2 control-label">Max
														Weight</label>
													<div class="col-sm-9 col-lg-10 controls">
														<input type="text" name="max_weight" id=""
															max_weight""
													placeholder="Max Weight"
															class="form-control" data-rule-required="true"/>
													</div>
												</div>


												<div class="form-group">
													<label class="col-sm-3 col-lg-2 control-label">Book
														Before</label>
													<div class="col-sm-9 col-lg-10 controls">
														<input type="text" name="book_before" id="book_before"
															placeholder="Book Before" class="form-control"
															data-rule-required="true" />
													</div>
												</div>

												<div class="form-group">
													<label class="col-sm-3 col-lg-2 control-label">Rates</label>
													<div class="col-sm-9 col-lg-10 controls">
														<select class="form-control input-sm" name="spc_rates"
															id="spc_rates">
															<option value="1000330">A--Fresh Cream - 670.00</option>

															<option value="1000332">B- Fresh Cream - 700.00</option>

															<option value="1000334">C- Fresh Cream - 730.00</option>

															<option value="1000337">D- Fresh Cream - 750.00</option>

															<option value="1000338">E- Fresh Cream - 800.00</option>

															<option value="1000340">F- Fresh Cream - 850.00</option>

															<option value="1000342">G- Fresh Cream - 875.00</option>

															<option value="1000344">H - 900.00</option>

															<option value="1000345">I - 925.00</option>

															<option value="1000346">Photo Cake - 850.00</option>

															<option value="1000348">Regular Chocolate  - 570.00</option>

															<option value="1000347">Taluka Photo Cake  - 450.00</option>

														</select>
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
																	type="file" class="file-input" name="fr_image"
																	id="fr_image" /></span> <a href="#"
																	class="btn btn-default fileupload-exists"
																	data-dismiss="fileupload">Remove</a>
															</div>
														</div>

													</div>
												</div>



												<div class="form-group">
													<label class="col-sm-3 col-lg-2 control-label">Tax
														1 </label>
													<div class="col-sm-9 col-lg-10 controls">
														<input type="text" name="tax_1" id=""
															tax_1"" placeholder="Tax 1" class="form-control"
															data-rule-required="true" />
													</div>
												</div>

												<div class="form-group">
													<label class="col-sm-3 col-lg-2 control-label">Tax
														2 </label>
													<div class="col-sm-9 col-lg-10 controls">
														<input type="text" name="tax_2" id=""
															tax_2"" placeholder="Tax 2" class="form-control"
															data-rule-required="true"
															 />
													</div>
												</div>

												<div class="form-group">
													<label class="col-sm-3 col-lg-2 control-label">Tax
														3 </label>
													<div class="col-sm-9 col-lg-10 controls">
														<input type="text" name="tax_3" id=""
															tax_3"" placeholder="Tax 3" class="form-control" data-rule-required="true"
															 />
													</div>
												</div>


												<div class="form-group">
													<label class="col-sm-3 col-lg-2 control-label">Rates</label>
													<div class="col-sm-9 col-lg-10 controls">
														<select class="form-control input-sm" name="spc_events"
															id="spc_events">
															<optgroup label="EVENTS">

																<option>Anniversary</option>

																<option>Birthday</option>

																<option>Christmas</option>

																<option>Diwali</option>

																<option>Doctors Day</option>

																<option>Fathers Day</option>

																<option>Krishna Janmasthami</option>

																<option>Mothers Day</option>

																<option>New year</option>

																<option>No Message</option>

																<option>Rakshabandhan</option>

																<option>Teachers Day</option>

																<option>Valentines Day</option>

															</optgroup>

														</select>
													</div>
												</div>

												<div class="form-group">
													<label class="col-sm-3 col-lg-2 control-label">ERP
														Link Code </label>
													<div class="col-sm-9 col-lg-10 controls">
														<input type="text" name="erplinkcode" id="erplinkcode"
															placeholder="ERP Link Code" class="form-control" data-rule-required="true"
															 />
													</div>
												</div>


												<div class="form-group">
													<label class="col-sm-3 col-lg-2 control-label">Type
														2 Applicable ?</label>
													<div class="col-sm-9 col-lg-10 controls">
														<label class="radio-inline"> <input type="radio"
															name="type_2_applicable" id="type_2_applicable" value="0"
															checked="true">No
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
															name="is_used" id="is_used" value="0" checked> No
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
															name="allowphupload" id="" allowphupload"" value="0"
															checked> No
														</label> <label class="radio-inline"> <input type="radio"
															name="" allowphupload"" id="" allowphupload"" value="1" />
															Yes
														</label>
													</div>
												</div>


												<div class="form-group">
													<div
														class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
														<!-- <button type="submit" class="btn btn-primary">
															<i class="fa fa-check"></i> Save
														</button> -->
														<input type="submit" class="btn btn-primary" value="Submit">
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
	<script>window.jQuery|| document.write('<script src="resources/assets/jquery/jquery-2.0.3.min.js"><\/script>')
	</script>
	<script src="resources/assets/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="resources/assets/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="resources/assets/jquery-cookie/jquery.cookie.js"></script>

	<!--page specific plugin scripts-->
	<script src="resources/assets/flot/jquery.flot.js"></script>
	<script src="resources/assets/flot/jquery.flot.resize.js"></script>
	<script src="resources/assets/flot/jquery.flot.pie.js"></script>
	<script src="resources/assets/flot/jquery.flot.stack.js"></script>
	<script src="resources/assets/flot/jquery.flot.crosshair.js"></script>
	<script src="resources/assets/flot/jquery.flot.tooltip.min.js"></script>
	<script src="resources/assets/sparkline/jquery.sparkline.min.js"></script>


<!--page specific plugin scripts-->
        <script type="text/javascript" src="resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
        <script type="text/javascript" src="resources/assets/jquery-validation/dist/additional-methods.min.js"></script>


	<!--flaty scripts-->
	<script src="resources/js/flaty.js"></script>
	<script src="resources/js/flaty-demo-codes.js"></script>
	<!--page specific plugin scripts-->
	<script type="text/javascript"
		src="resources/assets/bootstrap-fileupload/bootstrap-fileupload.min.js"></script>
	<script type="text/javascript"
		src="resources/assets/chosen-bootstrap/chosen.jquery.min.js"></script>
	<script type="text/javascript"
		src="resources/assets/clockface/js/clockface.js"></script>
	<script type="text/javascript"
		src="resources/assets/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
	<script type="text/javascript"
		src="resources/assets/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
	<script type="text/javascript"
		src="resources/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript"
		src="resources/assets/bootstrap-daterangepicker/date.js"></script>
	<script type="text/javascript"
		src="resources/assets/bootstrap-daterangepicker/daterangepicker.js"></script>
		
		
		
		  <script type="text/javascript" src="resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
        <script type="text/javascript" src="resources/assets/jquery-validation/dist/additional-methods.min.js"></script>
        
        <!--basic scripts-->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="assets/jquery/jquery-2.0.3.min.js"><\/script>')</script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <script src="assets/jquery-cookie/jquery.cookie.js"></script>




        <!--page specific plugin scripts-->
        <script type="text/javascript" src="resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
        <script type="text/javascript" src="resources/assets/jquery-validation/dist/additional-methods.min.js"></script>

        <!--flaty scripts-->
        <script src="resources/js/flaty.js"></script>
        <script src="resources/js/flaty-demo-codes.js"></script>
        
        
</body>
</html>