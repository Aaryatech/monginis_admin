<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>


<c:url var="getRmSubCategory" value="/getRmSubCategory" />
<c:url var="getRmCategory" value="/getRmCategory" />



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
						<i class="fa fa-file-o"></i>Raw Material Master
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
								<i class="fa fa-bars"></i>Add Raw Material
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showRawMaterial">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							<!-- <div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> <a data-action="close" href="#"><i
									class="fa fa-times"></i></a>
							</div> -->
						</div>


						<div class="box-content">
							<form action="addRawMaterial" method="post" class="form-horizontal" id=
									"validation-form"
										enctype="multipart/form-data" method="post">
							

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">RM Code</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="rm_code" class="form-control"placeholder="Raw Material Code"data-rule-required="true" />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">RM Name
									</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="rm_name" class="form-control"placeholder="Raw Material Name"data-rule-required="true" />
									</div>

								</div>
							
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">RM UOM</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="rm_uom" class="form-control"placeholder="RM UOM"data-rule-required="true">
											<option value="">Select RM UOM</option>
											<c:forEach items="${rmUomList}" var="rmUomList"
													varStatus="count">
												<option value="${rmUomList.uomId}"><c:out value="${rmUomList.uom}"/></option>
												</c:forEach>
										</select>
									</div>

									<label class="col-sm-3 col-lg-2 control-label">RM
										Specification </label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="rm_specification" class="form-control"placeholder="Specification "data-rule-required="true"/>
									</div>
								</div>
							
								<div class="form-group">
									

									<label class="col-sm-3 col-lg-2 control-label">Group</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="rm_group" id="rm_group" class="form-control"placeholder="Group"data-rule-required="true">
											<option value="">Select Group </option>
										<c:forEach items="${groupList}" var="groupList"
													varStatus="count">
												<option value="${groupList.grpId}"><c:out value="${groupList.grpName}"/></option>
												</c:forEach>
								</select>
									</div>
									<label class="col-sm-3 col-lg-2 control-label">Category</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="rm_cat" id="rm_cat" class="form-control"placeholder="Category"data-rule-required="true">
											 
										</select>
									</div>
									<!-- <label class="col-sm-3 col-lg-2 control-label">RM Icon</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="file" name="rm_icon" class="form-control"placeholder="Icon"data-rule-required="true" />
									</div> -->
								</div>
							
								<div class="form-group">
									
									<label class="col-sm-3 col-lg-2 control-label">Sub Category</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="rm_sub_cat" id="rm_sub_cat" class="form-control"placeholder="Sub Category"data-rule-required="true">
											 
										</select>
									</div>
									<label class="col-sm-3 col-lg-2 control-label">Weight</label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" name="rm_weight" class="form-control"placeholder="Weight"data-rule-required="true"data-rule-number="true" />
						</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">RM Pack
										Quantity</label>

									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="rm_pack_qty" class="form-control"placeholder="Pack Qty"data-rule-required="true"data-rule-number="true" />
									</div>
								 


								<label class="col-sm-3 col-lg-2 control-label">BMS min
									Quantity</label>
								<div class="col-sm-6 col-lg-4 controls">
									<input type="text" name="rm_min_qty" id="rm_min_qty" class="form-control"placeholder="Min Qty"data-rule-required="true"data-rule-number="true" />
								</div>
						</div>

						<div class="form-group">

							<label class="col-sm-3 col-lg-2 control-label">BMS max
								Quantity </label>

							<div class="col-sm-6 col-lg-4 controls">
								<input type="text" name="rm_max_qty" id="rm_max_qty" class="form-control"placeholder="Max Qty "data-rule-required="true" data-rule-number="true" />
							</div>
						 <label class="col-sm-3 col-lg-2 control-label">BMS ROL Qty</label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" name="rm_rol_qty" id="rm_rol_qty" class="form-control"placeholder="Re Order level Qty"data-rule-required="true"data-rule-number="true" />
						</div>
					

						
					</div>
					<div class="form-group">
					<label class="col-sm-3 col-lg-2 control-label">RM Opening Rate
						</label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" name="rm_op_rate" class="form-control"placeholder="RM Op Rate "data-rule-number="true" data-rule-required="true"/>
						</div>
						<label class="col-sm-3 col-lg-2 control-label">Rate </label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" name="rm_rate" class="form-control" placeholder="Rate"data-rule-required="true"data-rule-number="true" />
						</div>

					</div>

					<div class="form-group">
						
						<label class="col-sm-3 col-lg-2 control-label">RM GST % </label>
						<div class="col-sm-6 col-lg-4 controls">
							<select name="rm_tax_id" class="form-control">
								<option value="">Select RM GST</option>
								<c:forEach items="${rmTaxList}" var="rmTaxList"
													varStatus="count">
												<option value="${rmTaxList.taxId}"><c:out value="${rmTaxList.taxDesc}"/></option>
												</c:forEach>
							</select>
						</div>
						<label class="col-sm-3 col-lg-2 control-label">Store Min
							Qty </label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" name="rm_iss_qty" id="rm_iss_qty" class="form-control" placeholder="Store Min Qty"data-rule-required="true"data-rule-number="true" />
						</div>
						
					</div>
				
					<div class="form-group">
						<label class="col-sm-3 col-lg-2 control-label">Store Max Qty </label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" name="rm_op_qty"  id="rm_op_qty" class="form-control"placeholder="Store Max Qty"data-rule-required="true" data-rule-number="true" />
						</div>

						<label class="col-sm-3 col-lg-2 control-label">Store ROL
							Qty </label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" name="rm_recd_qty"  id="rm_recd_qty" class="form-control" placeholder="Store ROL Qty"data-rule-required="true" data-rule-number="true" />
						</div>
						</div>
						<div class="form-group">
					<label class="col-sm-3 col-lg-2 control-label">HSN Code</label>

						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" name="rm_clo_qty" class="form-control" placeholder="RM HSNCD" data-rule-required="true"data-rule-number="true"  />
						</div>
						<label class="col-sm-3 col-lg-2 control-label">RM Rejected
							Qty </label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" name="rm_rej_qty" class="form-control"placeholder="Raw Rejected Qty "data-rule-required="true"data-rule-number="true" />
						</div>
					</div>
					
					<div class="form-group">

						<label class="col-sm-3 col-lg-2 control-label">RM
							isCritical </label>
						<div class="col-sm-6 col-lg-4 controls">
							  <input type="radio" name="rm_is_critical" value="2" > High
							  <input type="radio" name="rm_is_critical" value="1" checked="true"> Normal
  							<input type="radio" name="rm_is_critical" value="0"> Low
  							
  								</div>
  								</div>
									<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Raw Material Image</label>
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
													class="fileupload-exists">Change</span> <input type="file"
													class="file-input" name="rm_icon" id="rm_icon"
													 /></span> <a href="#"
													class="btn btn-default fileupload-exists"
													data-dismiss="fileupload">Remove</a>
											</div>
										</div>

									</div>
								</div>
							
					<div class="row">
						<div class="col-md-12" style="text-align: center">
							<input type="submit" class="btn btn-info" value="Submit" onclick="return validate()">


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
				

	<script type="text/javascript">
$(document).ready(function() { 
	$('#rm_group').change(
			function() {
				$.getJSON('${getRmCategory}', {
					grpId : $(this).val(),
					ajax : 'true'
				}, function(data) {
					var html = '<option value="" selected >Select Category</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].catId + '">'
								+ data[i].catName + '</option>';
					}
					html += '</option>';
					$('#rm_cat').html(html);
					$('#rm_cat').formcontrol('refresh');

				});
			});
});
$(document).ready(function() { 
	$('#rm_cat').change(
			function() {
				$.getJSON('${getRmSubCategory}', {
					catId : $(this).val(),
					ajax : 'true'
				}, function(data) {
					var html = '<option value="" selected >Select Category</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].subCatId + '">'
								+ data[i].subCatName + '</option>';
					}
					html += '</option>';
					$('#rm_sub_cat').html(html);
					$('#rm_sub_cat').formcontrol('refresh');

				});
			});
});
</script>
<script type="text/javascript">
function validation() {
	
	var bmsMinQty=parseFloat($("#rm_min_qty").val());
	var bmsMaxQty=parseFloat($("#rm_max_qty").val());
	var bmsRolQty=parseFloat($("#rm_rol_qty").val());

	var isValid = true;
	  if(bmsMinQty!=0 || bmsMaxQty!=0 || bmsRolQty!=0){

	if (bmsMinQty>=bmsMaxQty) { 
		isValid = false;
		alert("BMS maximum qty is always greater than minimum Qty ");
	} else if (bmsRolQty>=bmsMaxQty) {
		isValid = false;
		alert("BMS reorder level qty is between minimum qty & maximum qty");
	} else if (bmsRolQty<=bmsMinQty) {
		isValid = false;
		alert("BMS reorder level qty is between minimum qty & maximum qty");
	}
	  }
	return isValid;
}
function validationForStore() {
	
	var storeIssueQty=parseFloat($("#rm_iss_qty").val());
	var storeOpQty=parseFloat($("#rm_op_qty").val());
	var storeRecQty=parseFloat($("#rm_recd_qty").val());

	var isValid = true;
    if(storeIssueQty!=0 || storeOpQty!=0 || storeRecQty!=0){

	if (storeIssueQty>=storeOpQty) { 
		isValid = false;
		alert("Store maximum qty is always greater than minimum Qty");
	} else if (storeRecQty>=storeOpQty) {
		isValid = false;
		alert("Store reorder level qty is between minimum qty & maximum qty");
	} else if (storeRecQty<=storeIssueQty) {
		isValid = false;
		alert("Store reorder level qty is between minimum qty & maximum qty");
	}
    }
	return isValid;
}
function validate() {
    return validation() && validationForStore();
}
</script> 

</body>
</html>

