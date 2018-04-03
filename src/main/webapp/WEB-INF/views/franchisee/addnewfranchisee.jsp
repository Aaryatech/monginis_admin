<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

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
						<i class="fa fa-file-o"></i> Franchisee
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
										<i class="fa fa-bars"></i> Add Franchisee
									</h3>
									<div class="box-tool">
										<a href="${pageContext.request.contextPath}/listAllFranchisee">Back to
											List</a> <a data-action="collapse" href="#"><i
											class="fa fa-chevron-up"></i></a>
									</div>
								</div>

								<div class="box-content">
									<form action="addNewFrProcess" class="form-horizontal" id=
									"validation-form"
										enctype="multipart/form-data" method="post">

										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label" for="fr_code">Code</label>
											<div class="col-sm-6 col-lg-4 controls">
												<input type="text" name="fr_code" id="fr_code"
													class="form-control" value="${frCode}"  required/>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Opening
												Date</label>
											<div class="col-sm-5 col-lg-4 controls">
												<input class="form-control date-picker" id="fr_opening_date" size="16"
													type="text" name="fr_opening_date"
													required placeholder="Opening Date"/>
											</div>
										</div>


										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label" for="fr_name">Franchisee
												Name</label>
											<div class="col-sm-6 col-lg-4 controls">
												<input type="text" name="fr_name" id="fr_name" data-rule-required="true"
													class="form-control" placeholder="Name"required/>
											</div>
										</div>

                                    <div class="form-group">
	                             	<label class="col-sm-3 col-lg-2 control-label" for="fr_name">Franchisee
												Address</label>
	                                 <div class="col-sm-6 col-lg-4 controls"><textarea rows="4" cols="25" name="fr_addr" id="fr_addr" placeholder="Address"	class="form-control" form="validation-form"required></textarea></div>
	                                 </div>
	                                 
		                             <div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Target Per Year</label>
											<div class="col-sm-9 col-lg-4 controls">
												<input type="text" name="fr_target" id="fr_target" placeholder="Target Per Year"
													class="form-control" data-rule-required="true"
													data-rule-number="true"
													onKeyPress="return isNumberCommaDot(event)"/>
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
											<label class="col-sm-3 col-lg-2 control-label">Route</label>
											<div class="col-sm-9 col-lg-10 controls">
												<select class="form-control input-sm" name="fr_route_id"
													id="fr_route_id" data-rule-required="true">

								<c:forEach items="${routeList}" var ="routeList">
								<option value="${routeList.routeId}"><c:out value="${routeList.routeName}"/></option>
								
								
								</c:forEach>
									
													
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Rate Type</label>
											<div class="col-sm-9 col-lg-10 controls">
												<select class="form-control input-sm" name="fr_rate_cat"
													id="fr_rate_cat" data-rule-required="true">

													<option value="1">Local Rate</option>
													<option value="2">OutStation Rate</option>
													<option value="3">Special Rate</option>

												</select>
											</div>
										</div>


										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Rating</label>
											<div class="col-sm-9 col-lg-10 controls">
												<select class="form-control input-sm" name="fr_rate"
													id="fr_rate" data-rule-required="true">
													<option value="0">Rating 0.5</option>
													<option value="1">Rating 1</option>
													<option value="2">Rating 1.5</option>
													<option value="3">Rating 2</option>
													<option value="4">Rating 2.5</option>
													<option value="5">Rating 3</option>
													<option value="6">Rating 3.5</option>
													<option value="7">Rating 4</option>
													<option value="8">Rating 4.5</option>
													<option value="9">Rating 5</option>

												</select>
											</div>
										</div>


										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">City</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="fr_city" id="fr_city"
													placeholder="City" class="form-control"
													data-rule-required="true" />
											</div>
										</div>


										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Pastries</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="kg_1" id="kg_1" placeholder="Qty"
													class="form-control" data-rule-required="true" data-rule-number="true"
													onKeyPress="return isNumberCommaDot(event)" />
											</div>
										</div>

                                             <div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">1/2 Kg
												Cake</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="kg_2" id="kg_2" placeholder="Qty"
													value="" class="form-control"
													data-rule-required="true" data-rule-number="true"
													onKeyPress="return isNumberCommaDot(event)" />
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">1 Kg
												Cake</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="kg_3" id="kg_3" placeholder="Qty"
													class="form-control" data-rule-required="true" data-rule-number="true"
													onKeyPress="return isNumberCommaDot(event)" />
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Above
												1 Kg Cake</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="kg_4" id="kg_4" placeholder="Qty"
													class="form-control" data-rule-required="true"
													data-rule-number="true"
													onKeyPress="return isNumberCommaDot(event)" />
											</div>
										</div>


										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label"
												for="fr_password">Password</label>
											<div class="col-sm-6 col-lg-4 controls">
												<input type="password" name="fr_password" id="fr_password"
													class="form-control" data-rule-required="true"
													data-rule-minlength="6" placeholder="Password"/>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label"
												for="fr_confirm_password">Confirm Password</label>
											<div class="col-sm-6 col-lg-4 controls">
												<input type="password" name="fr_confirm_password"
													id="fr_confirm_password" class="form-control"
													data-rule-required="true" data-rule-minlength="6"
													data-rule-equalTo="#fr_password" placeholder="Confirm Password"/>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label" for="fr_email">Email
												Address:</label>
											<div class="col-sm-6 col-lg-4 controls">
												<input type="email" name="fr_email" id="fr_email"
													class="form-control" data-rule-required="true"
													data-rule-email="true" placeholder="Enter Email Id"/>
											</div>
										</div>


										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Mobile</label>
											<div class="col-sm-9 col-lg-4 controls">
												<input type="text" name="fr_mob" id="fr_mob"
													placeholder="Mobile" class="form-control"
													data-rule-required="true" data-rule-minlength="10"
													data-rule-maxlength="10"
													onKeyPress="return isNumberCommaDot(event)" />
											</div>
										</div>


										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Owner
												Name</label>
											<div class="col-sm-9 col-lg-4 controls">
												<input type="text" name="fr_owner" id="fr_owner"
													placeholder="Owner Name" class="form-control"
													data-rule-required="true" />
											</div>
										</div>
	                                    <div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Owner's Birthdate</label>
											<div class="col-sm-5 col-lg-4 controls">
												<input class="form-control date-picker" id="fr_birth_date" size="16"
													type="text" name="fr_birth_date"
													required placeholder="Birthdate"/>
											</div>
										</div>
                                          <div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">FDA License Date</label>
											<div class="col-sm-5 col-lg-4 controls">
												<input class="form-control date-picker" id="fba_license_date" size="16"
													type="text" name="fba_license_date"
													required placeholder="FDA License Date"/>
											</div>
										</div>
										 <div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Agreement Date</label>
											<div class="col-sm-5 col-lg-4 controls">
												<input class="form-control date-picker" id="fr_agreement_date" size="16"
													type="text" name="fr_agreement_date"
													required placeholder="Agreement Date"/>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">GST Type</label>
											<div class="col-sm-9 col-lg-4 controls">
												<select class="form-control input-sm" name="fr_gst_type"
													id="fr_gst_type" data-rule-required="true" onChange="changetextbox();">
												   <option value="">Select GST Type</option>	
						                                                   
                                           <c:forEach items="${frItemStockConfigures}" var="frItemStockConfigures">
                                       
                                               <option value="${frItemStockConfigures.settingValue}"><c:out value="${frItemStockConfigures.settingKey}" /></option>
                                            </c:forEach>
                                          
												</select>
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">GST NO.</label>
											<div class="col-sm-9 col-lg-4 controls">
												<input type="text" name="fr_gst_no" id="fr_gst_no" placeholder="GST Number"
													class="form-control" data-rule-required="true"
													
													onKeyPress="return isNumberCommaDot(event)"/>
											</div>
										</div>
										
										
										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Stock Type</label>
											<div class="col-sm-9 col-lg-4 controls">
												<select class="form-control input-sm" name="stock_type"
													id="stock_type" data-rule-required="true">
															 <c:forEach var = "i" begin = "1" end = "${settingValue}">
                                                          <option value="${i}">    Type <c:out value = "${i}"/></option>
                                                             </c:forEach>
												</select>
											</div>
										</div>
											<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Is Same State?</label>
											<div class="col-sm-9 col-lg-10 controls">
												 <label class="radio-inline"> <input type="radio"
													name="is_same_state" id="optionsRadios1" value="1" checked/> YES
												</label>
												<label class="radio-inline"> <input type="radio"
													name="is_same_state" id="optionsRadios1" value="0">
													NO
												</label>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">GRN 2
												Applicable ?</label>
											<div class="col-sm-9 col-lg-10 controls">
												 <label class="radio-inline"> <input type="radio"
													name="grn_two" id="optionsRadios1" value="1" checked/> Applicable
												</label>
												<label class="radio-inline"> <input type="radio"
													name="grn_two" id="optionsRadios1" value="0">
													Not Applicable
												</label>
											</div>
										</div>
										
										
										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Status</label>
											<div class="col-sm-9 col-lg-10 controls">
												<label class="radio-inline"> <input type="radio"
													name="fr_status" id="optionsRadios1" value="0" checked>
													Active
												</label> <label class="radio-inline"> <input type="radio"
													name="fr_status" id="optionsRadios1" value="1" />
													In-active
												</label>
											</div>
										</div>


										<div class="form-group">
											<div
												class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
												<input type="submit" class="btn btn-primary" value="Submit">
												<!-- <button type="button" class="btn">Cancel</button> -->
											</div>
										</div>
										
										
										
									</form>
								</div>
							</div>
						</div>
					</div></div></div>
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
			<script src="${pageContext.request.contextPath}/resources/assets/bootstrap/js/bootstrap.min.js"></script>
			<script
				src="${pageContext.request.contextPath}/resources/assets/jquery-slimscroll/jquery.slimscroll.min.js"></script>
			<script src="${pageContext.request.contextPath}/resources/assets/jquery-cookie/jquery.cookie.js"></script>

			<!--page specific plugin scripts-->
			<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.js"></script>
			<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.resize.js"></script>
			<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.pie.js"></script>
			<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.stack.js"></script>
			<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.crosshair.js"></script>
			<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.tooltip.min.js"></script>
			<script src="${pageContext.request.contextPath}/resources/assets/sparkline/jquery.sparkline.min.js"></script>

			<!--flaty scripts-->
			<script src="${pageContext.request.contextPath}/resources/js/flaty.js"></script>
			<script src="${pageContext.request.contextPath}/resources/js/flaty-demo-codes.js"></script>
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
				src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
			<script type="text/javascript"
				src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>
				
<script type="text/javascript">
function changetextbox()
{
    if (document.getElementById("fr_gst_type").value === "0") {
        document.getElementById("fr_gst_no").disabled = true;
    } else  if (document.getElementById("fr_gst_type").value === "2000000") {
        document.getElementById("fr_gst_no").disabled = false;
    }
    else
    	{
    	document.getElementById("fr_gst_no").disabled = false;
    	}
}
</script>
				
</body>
</html>