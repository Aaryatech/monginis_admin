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
<title>Material Receipt Directore</title>
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
					<i class="fa fa-file-o"></i>Material Receipt Directore
				</h1>
				<!-- <h4>Bill for franchises</h4> -->
			</div>
		</div>
		<!-- END Page Title -->

		
		<!-- BEGIN Main Content -->
		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>Material Receipt Directore
				</h3>

			</div>

				<div class=" box-content">
					 
		<div class="box">
			<form id=""
				action="${pageContext.request.contextPath}/ "
				method="post">
				
						<div class="box-content">
							
								<div class="col-md-2">Inward no.</div>
									<div class="col-md-2"><input type="text" id="inward_no." name="inward_no." value="00" class="form-control" readonly>
								</div>
								<div class="col-md-2">Inward date</div>
									<div class="col-md-2">
									<input class="form-control" id="dp1" size="16"
											type="text" name="inward_date" value="" placeholder="Inward date" readonly />
									</div>
								<div class="col-md-2">Time</div>
									<div class="col-md-2"><input type="text" id="time" name="time" value="00:00" class="form-control" readonly>
								</div>
				 
				 
						</div><br>
						<div class="box-content">
						
								<div class="col-md-2">Supplier </div>
									<div class="col-md-3"><input type="text" id="supp_id" name="supp_id" value="Akshay Kasar" 
									class="form-control" readonly>
								</div>
								
								<div class="col-md-2">Transporter</div>
										<div class="col-md-3"><input type="text" id="transporter" name="transporter" value="xyz" class="form-control" readonly>
									</div>
							</div><br>
							
							
			
				
						<div class="box-content">
								
									<div class="col-md-2">Po No.</div>
								<div class="col-md-3">
									<input type="text" name="po_no"id="po_no" class="form-control" readonly>
								</div>
								
								<div class="col-md-2" >PO Date</div>
										<div class="col-md-3">
											<input type="text" name="po_date"id="po_date" class="form-control" readonly>
										</div>
										
				 
						</div> <br/>
						
						
								<div class="box-content">
									
									<div class="col-md-2" >MRN Type</div>
									<div class="col-md-3">
										
									<input type="text" id="mrn_type" name="mrn_type" value="mrn_type" class="form-control" readonly>
									</div>
								
								</div><br><br>
								
								
								<div class="box-content">
								 <div class="form-group">
									
									<div class="col-md-2">Image</div>
									<div class="col-md-2">
										<div class="fileupload fileupload-new"
											data-provides="fileupload">
											<div class="fileupload-new img-thumbnail"
												style="width: 150px; height: 150px;">
												<img
													src="http://www.placehold.it/150x150/EFEFEF/AAAAAA&amp;text=no+image"
													alt="" />
											</div>
											<div
												class="fileupload-preview fileupload-exists img-thumbnail"
												style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
											<div>
												<span class="btn btn-default btn-file"><span
													class="fileupload-new">Select image</span> <span
													class="fileupload-exists">Change</span> <input type="file"
													class="file-input" name="image1" id="image2"
													 /></span> <a href="#"
													class="btn btn-default fileupload-exists"
													data-dismiss="fileupload">Remove</a>
											</div>
										</div>

									</div>
									
									<div class="col-md-2">Image</div>
									<div class="col-md-2">
										<div class="fileupload fileupload-new"
											data-provides="fileupload">
											<div class="fileupload-new img-thumbnail"
												style="width: 200px; height: 150px;">
												<img
													src="http://www.placehold.it/150x150/EFEFEF/AAAAAA&amp;text=no+image"
													alt="" />
											</div>
											<div
												class="fileupload-preview fileupload-exists img-thumbnail"
												style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
											<div>
												<span class="btn btn-default btn-file"><span
													class="fileupload-new">Select image</span> <span
													class="fileupload-exists">Change</span> <input type="file"
													class="file-input" name="image2" id="image2"
													 /></span> <a href="#"
													class="btn btn-default fileupload-exists"
													data-dismiss="fileupload">Remove</a>
											</div>
										</div>

									</div>
									
								</div>
								</div>
							
							
			
				<div class=" box-content">
					<div class="row">
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Item</th>
										<th>Stock Quantity</th>
										<th>PO Quantity</th>
										<th>PO Rate</th>

									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
					</div>

		</div>
		
				 	
									<div class="box-content">
									
										<div class="col-md-2">Issue</div>
											<div class="col-md-2" style="text-align: center">
												<select name="issue" id="issue" class="form-control" tabindex="6" required="required"  >
													<option value="">Select Issue</option>
													<option value="1">Not Applicable</option>
													<option value="2">On Your Side</option>
													<option value="3">On Our Side</option>
												</select>
											</div>
										
									
									</div><br/><br/>
			
			<div class="row">
						<div class="col-md-12" style="text-align: center">
							<input type="submit" class="btn btn-info" value="Accept">
							<input type="button" class="btn btn-info" value="Reject">
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
		
		
		
		
</body>
</html>