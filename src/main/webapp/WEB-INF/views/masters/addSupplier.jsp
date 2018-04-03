<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>




<c:url var="getSupplierDetails" value="/getSupplierDetails" />



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
						<i class="fa fa-file-o"></i>Supplier Master
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
								<i class="fa fa-bars"></i>Add Supplier 
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showSupplierList">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							<!-- <div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> <a data-action="close" href="#"><i
									class="fa fa-times"></i></a>
							</div> -->
						</div>


					<div class="box-content">
							<form action="addSupplier" method="post" class="form-horizontal" id=
									"validation-form"
										enctype="multipart/form-data" method="post">
							
						<div class="form-group">
										<label class="col-sm-3 col-lg-2 control-label">Supplier Name*</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_name" class="form-control"placeholder="Enter Supplier Name "data-rule-required="true" />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Supplier GSTIN* </label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_gstin" class="form-control" placeholder="Enter GSTIN " data-rule-required="true"/>
									</div>
								 
								</div>
							 
								
								
								<div class="form-group">
										<label class="col-sm-3 col-lg-2 control-label">Address*</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_addr" class="form-control" placeholder="Address" data-rule-required="true"/>
									</div>

									<label class="col-sm-3 col-lg-2 control-label">City*</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_city" class="form-control"  placeholder="Enter City "data-rule-required="true"/>
									</div>
								 
								</div>
							
								<div class="form-group">
										<label class="col-sm-3 col-lg-2 control-label">State*</label>
									<div class="col-sm-6 col-lg-4 controls">
										 <!-- <select  name="supp_state"  class="form-control"> 
										<option value="1">Maharashtra</option>
								</select>-->
								<input type="text" name="supp_state" class="form-control"  placeholder="Enter State "data-rule-required="true" value="Maharashtra"/>
									
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Country*</label>
									<div class="col-sm-6 col-lg-4 controls">
										  <!--<select  name="supp_country"  class="form-control">
										<option value="1">India</option>
								</select>-->
								<input type="text" name="supp_country" class="form-control"  placeholder="Enter County "data-rule-required="true" value="India"/>
								
									</div>
								 
								</div>
								 	<br />
							 
								
								<div class="form-group">
										<label class="col-sm-3 col-lg-2 control-label">Mobile 1*</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_mob1" class="form-control" placeholder="Enter Mobile 1 " data-rule-required="true" 
													 pattern="^\d{10}$" required
													onKeyPress="return isNumberCommaDot(event)" />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Email 1*</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="email" name="supp_email1" class="form-control"  placeholder="Enter Email 1 "data-rule-required="true"
													data-rule-email="true" />
									</div>
								 
								</div>
							 
								
							 <div class="form-group">
										<label class="col-sm-3 col-lg-2 control-label">Mobile 2*</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_mob2" class="form-control" placeholder="Enter Mobile 2 " data-rule-required="true"
														 pattern="^\d{10}$" required
													onKeyPress="return isNumberCommaDot(event)"  />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Email 2</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="email" name="supp_email2" class="form-control"placeholder="Enter Email 2 "
													data-rule-email="true" />
									</div>
								 
								</div>
								 <div class="form-group">
										<label class="col-sm-3 col-lg-2 control-label">Mobile 3</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_mob3" class="form-control" placeholder="Enter Mobile 3 "pattern="^\d{10}$" 
													onKeyPress="return isNumberCommaDot(event)"   />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Email 3</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="email" name="supp_email3" class="form-control"placeholder="Enter Email 3 " 
													data-rule-email="true"/>
									</div>
								 
								</div>
							 
									<div class="form-group">
										<label class="col-sm-3 col-lg-2 control-label">Phone 1</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="number" name="supp_phone1" class="form-control" placeholder="Enter Phone 1 " data-rule-minlength="10"
													data-rule-maxlength="11"
													onKeyPress="return isNumberCommaDot(event)" />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Email 4</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="email" name="supp_email4" class="form-control" placeholder="Enter Email 4 "
													data-rule-email="true" />
									</div>
								 
								</div>
								 
								<div class="form-group">
										<label class="col-sm-3 col-lg-2 control-label">Phone 2</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="number" name="supp_phone2" class="form-control" placeholder="Enter Phone 2 " data-rule-minlength="10"
													data-rule-maxlength="11"
													onKeyPress="return isNumberCommaDot(event)" />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Email 5</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="email" name="supp_email5" class="form-control"placeholder="Enter Email 5 "
													data-rule-email="true"/>
									</div>
								 
								</div>
							 	<br />
								<br />
							
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Supplier Contact Person*</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_c_person" class="form-control" placeholder="Supplier contact person" data-rule-required="true"/>
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Supplier Pan No*
									</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_panno" class="form-control"  placeholder="Supplier Pan No"data-rule-required="true"/>
									</div>

								</div>
								 
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Supplier FDA Lic*</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_fdalic" class="form-control" placeholder="Supplier FDA Lic"data-rule-required="true" />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Supplier Pay Id*</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_pay_id" class="form-control" placeholder=" Supplier Pay Id" data-rule-number="true"  data-rule-required="true"/>
									</div>
								</div>
							 
								<div class="form-group" >
									<label class="col-sm-3 col-lg-2 control-label">Supplier Credit days*</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_credit_days" class="form-control"  placeholder="Supplier Credit days" onKeyPress="return isNumberCommaDot(event)"data-rule-number="true" data-rule-required="true"/>
									</div>
										
							</div>
								<br />
								 
								
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
</html>