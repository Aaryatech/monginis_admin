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
								<i class="fa fa-bars"></i>Details of <b> ${rawMaterialDetails.rmName}</b>
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
					
							<form action="${pageContext.request.contextPath}/addRawMaterial" method="post" class="form-horizontal" id=
									"validation-form"
										enctype="multipart/form-data" method="post">
							

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">RM Code</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" value="${rawMaterialDetails.rmCode }" name="rm_code" id="rm_code" class="form-control"placeholder="Raw Material Code"data-rule-required="true"   />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">RM Name
									</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" value="${rawMaterialDetails.rmName }" name="rm_name" id="rm_name" class="form-control"placeholder="Raw Material Name"data-rule-required="true" />
									</div>

								</div>
							<%-- <c:set var="rmUomId"   value="${rawMaterialDetails.rmUom}"/> --%>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">RM UOM</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="rm_uom" id="rm_uom" class="form-control"placeholder="RM UOM"data-rule-required="true">
											<option value="1">Select RM UOM</option>
											<c:forEach items="${rmUomList}" var="rmUomList"
													varStatus="count">
													<c:choose>
													<c:when test="${rmUomIdInt==rmUomList.uomId}">
													<option selected value="${rmUomList.uomId}"><c:out value="${rmUomList.uom}" /></option>
													</c:when>
													<c:otherwise>
  														 <option value="${rmUomList.uomId}"><c:out value="${rmUomList.uom}"/></option>
 													 </c:otherwise>
 													 </c:choose>
												</c:forEach>
										</select>
									</div>

									<label class="col-sm-3 col-lg-2 control-label">RM
										Specification </label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" value="${rawMaterialDetails.rmSpecification }" name="rm_specification" id="rm_specification" class="form-control"placeholder="Specification "data-rule-required="true"/>
									</div>
								</div>
							
								<div class="form-group">
									

									<label class="col-sm-3 col-lg-2 control-label">Group</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="rm_group" id="rm_group" class="form-control"placeholder="Group"data-rule-required="true">
											 <c:forEach items="${groupList}" var="groupList"
							varStatus="count">
									<c:choose>
													<c:when test="${rawMaterialDetails.grpId==groupList.grpId}">
														<option selected value="${groupList.grpId}"><c:out value="${groupList.grpName}"/></option>
												</c:when>
													<c:otherwise>
  														 <option value="${groupList.grpId}"><c:out value="${groupList.grpName}"/></option>
 													 </c:otherwise>
 													 </c:choose>
												</c:forEach>
						

										</select>
									</div>
									<label class="col-sm-3 col-lg-2 control-label">Category</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="rm_cat" id="rm_cat" class="form-control"placeholder="Category"data-rule-required="true">
											  
											  <c:forEach items="${rmItemCategoryList}" var="rmItemCategoryList"
							varStatus="count">
											 	<c:choose>
													<c:when test="${rawMaterialDetails.catId==rmItemCategoryList.catId}">
														<option selected value="${rmItemCategoryList.catId}"><c:out value="${rmItemCategoryList.catName}"/></option>
												</c:when>
													<c:otherwise>
  														 <option value="${rmItemCategoryList.catId}"><c:out value="${rmItemCategoryList.catName}"/></option>
 													 </c:otherwise>
 													 </c:choose>
												</c:forEach>
										</select>
									</div>
								</div>
							
								<div class="form-group">
									
									<label class="col-sm-3 col-lg-2 control-label">Sub Category</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="rm_sub_cat" id="rm_sub_cat" class="form-control"placeholder="Sub Category"data-rule-required="true">
											 
											  <c:forEach items="${rmItemSubCategoryList}" var="rmItemSubCategoryList"
							varStatus="count">
											 	<c:choose>
													<c:when test="${rawMaterialDetails.subCatId==rmItemSubCategoryList.subCatId}">
														<option selected value="${rmItemSubCategoryList.subCatId}"><c:out value="${rmItemSubCategoryList.subCatName}"/></option>
												</c:when>
													<c:otherwise>
  														 <option value="${rmItemSubCategoryList.subCatId}"><c:out value="${rmItemSubCategoryList.subCatName}"/></option>
 													 </c:otherwise>
 													 </c:choose>
												</c:forEach>
											 
										</select>
									</div>
									<label class="col-sm-3 col-lg-2 control-label">Weight</label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" value="${rawMaterialDetails.rmWeight }" name="rm_weight" id="rm_weight" class="form-control"placeholder="Weight"data-rule-required="true" data-rule-number="true"/>
						</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">RM Pack
										Quantity</label>

									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" value="${rawMaterialDetails.rmPackQty }" name="rm_pack_qty" id="rm_pack_qty" class="form-control" placeholder="Pack Qty" data-rule-required="true" data-rule-number="true"/>
									</div>
								 


								<label class="col-sm-3 col-lg-2 control-label">BMS Min
									Quantity</label>
								<div class="col-sm-6 col-lg-4 controls">
									<input type="text" value="${rawMaterialDetails.rmMinQty }" name="rm_min_qty" id="rm_min_qty" class="form-control"placeholder="Min Qty" data-rule-required="true" data-rule-number="true"/>
								</div>
						</div>

						<div class="form-group">

							<label class="col-sm-3 col-lg-2 control-label">BMS Max
								Quantity </label>

							<div class="col-sm-6 col-lg-4 controls">
								<input type="text" value="${rawMaterialDetails.rmMaxQty }" name="rm_max_qty"  id="rm_max_qty" class="form-control"placeholder="Max Qty "data-rule-required="true" data-rule-number="true"/>
							</div>
							<label class="col-sm-3 col-lg-2 control-label">BMS ROL Qty</label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" value="${rawMaterialDetails.rmRolQty }" name="rm_rol_qty" id="rm_rol_qty" class="form-control"placeholder="Re Order level" data-rule-required="true" data-rule-number="true"/>
						</div>
						 
					</div>
					<div class="form-group">
						<label class="col-sm-3 col-lg-2 control-label">RM OP Rate
						</label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" value="${rawMaterialDetails.rmOpRate }" name="rm_op_rate" id="rm_op_rate" class="form-control"placeholder="RM Op Rate "data-rule-required="true"data-rule-number="true"/>
						</div>
						
						<label class="col-sm-3 col-lg-2 control-label">Rate </label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" value="${rawMaterialDetails.rmRate }" name="rm_rate" id="rm_rate" class="form-control" placeholder="Rate"data-rule-required="true"data-rule-number="true"/>
						</div>
					</div>

					<div class="form-group">
							<label class="col-sm-3 col-lg-2 control-label">RM GST % </label>
						<div class="col-sm-6 col-lg-4 controls">
							<select name="rm_tax_id" id="rm_tax_id" class="form-control">
								<option value="1">Select RM GST</option>
								<c:forEach items="${rmTaxList}" var="rmTaxList"
													varStatus="count">
													
													<c:choose>
													<c:when test="${rawMaterialDetails.rmTaxId==rmTaxList.taxId}">
														<option selected value="${rmTaxList.taxId}"><c:out value="${rmTaxList.sgstPer + rmTaxList.cgstPer}"/>%</option>
												</c:when>
													<c:otherwise>
  														<option value="${rmTaxList.taxId}"><c:out value="${rmTaxList.sgstPer + rmTaxList.cgstPer}"/>%</option>
												 </c:otherwise>
 													 </c:choose>
												 
												</c:forEach>
							</select>
						</div>
						<label class="col-sm-3 col-lg-2 control-label">Store Min
							Qty </label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" value="${rawMaterialDetails.rmIssQty }" name="rm_iss_qty" id="rm_iss_qty" class="form-control" placeholder="Store Min Qty"data-rule-required="true" data-rule-number="true"/>
						</div>
						

					</div>
				
					<div class="form-group">
						<label class="col-sm-3 col-lg-2 control-label">Store Max Qty </label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" value="${rawMaterialDetails.rmOpQty }" name="rm_op_qty" id="rm_op_qty" class="form-control"placeholder="Store Max Qty"data-rule-required="true" data-rule-number="true" />
						</div>

						<label class="col-sm-3 col-lg-2 control-label">Store ROL
							Qty </label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" value="${rawMaterialDetails.rmReceivedQty }" name="rm_recd_qty" id="rm_recd_qty" class="form-control" placeholder="Store ROL Qty"data-rule-required="true" data-rule-number="true"/>
						</div>
					<input type="hidden" name="rm_id" id="rm_id"value="${rawMaterialDetails.rmId}"  />
						
					</div>
					
					<div class="form-group">
						<label class="col-sm-3 col-lg-2 control-label">HSN Code</label>

						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" value="${rawMaterialDetails.rmCloQty }" name="rm_clo_qty" id="rm_clo_qty" class="form-control" placeholder="RM HSNCD" data-rule-required="true" data-rule-number="true"/>
						</div>

						<label class="col-sm-3 col-lg-2 control-label">RM Rejected
							Qty </label>
						<div class="col-sm-6 col-lg-4 controls">
							<input type="text" value="${rawMaterialDetails.rmRejQty }"name="rm_rej_qty" id="rm_rej_qty" class="form-control"placeholder="Raw Rejected Qty "data-rule-required="true" data-rule-number="true"/>
						</div>
					</div>
					
					<div class="form-group">

						<label class="col-sm-3 col-lg-2 control-label">RM
							isCritical </label>
						<div class="col-sm-6 col-lg-4 controls">
						
						   <c:choose>
																	<c:when test="${rawMaterialDetails.rmIsCritical==1}">
																		
																		<input type="radio" name="rm_is_critical"id="rm_high"   value="2"> High
							  											<input type="radio" name="rm_is_critical" id="rm_normal" checked="true" value="1"> Normal
  																		<input type="radio" name="rm_is_critical" id="rm_low"    value="0"> Low
																	</c:when>
																		<c:when test="${rawMaterialDetails.rmIsCritical==0}">
																		
																		<input type="radio" name="rm_is_critical"id="rm_high"   value="2"> High
							  											<input type="radio" name="rm_is_critical" id="rm_normal" value="1"> Normal
  																		<input type="radio" name="rm_is_critical" id="rm_low" checked="true"   value="0"> Low
																	</c:when>
																	<c:when test="${rawMaterialDetails.rmIsCritical==2}">
																		
																		<input type="radio" name="rm_is_critical"id="rm_high" checked="true"  value="2"> High
							  											<input type="radio" name="rm_is_critical" id="rm_normal" value="1"> Normal
  																		<input type="radio" name="rm_is_critical" id="rm_low"   value="0"> Low
																	</c:when>
																
													</c:choose>
							  <%-- <input type="radio" name="rm_is_critical"id="rm_high" checked=<c:out value="${high}" /> value="2"> High
							  <input type="radio" name="rm_is_critical" id="rm_normal"checked=<c:out value="${normal}" /> value="1"> Normal
  							<input type="radio" name="rm_is_critical" id="rm_low"   value="0"> Low
  							 --%>
 						 </div>

					<label class="col-sm-3 col-lg-2 control-label">
					 </label>
					 <input type="hidden" value="${rawMaterialDetails.rmIsCritical }"id="temp_is_critical" >
						
				</div>
				
							<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Raw Material Image</label>
														<div class="col-sm-9 col-lg-10 controls">
					
							<div class="fileupload fileupload-new"
											data-provides="fileupload">
											<div class="fileupload-new img-thumbnail"
												style="width: 200px; height: 150px;">
												 <img src="${url}${rawMaterialDetails.rmIcon}" onerror="this.src='${pageContext.request.contextPath}/resources/img/No_Image_Available.jpg';"/>
												</div>
											<div
												class="fileupload-preview fileupload-exists img-thumbnail"
												style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
											<div>
												<span class="btn btn-default btn-file"><span
													class="fileupload-new" >Change image</span> <span
													class="fileupload-exists">Change</span> <input type="file"
													class="file-input" name="rm_icon" id="rm_icon"
													 /></span> <a href="#"
													class="btn btn-default fileupload-exists"
													data-dismiss="fileupload">Previous Image</a>
											</div>
										</div>
										 <div>
            <input type="hidden" name="prevImage" value="${rawMaterialDetails.rmIcon}"></div>
										

					</div>
</div>
				<br/>
					<br/>
					<div class="row">
							<div class="col-md-12" style="text-align: center">
						<input type="submit" class="btn btn-success" value="Submit" id="btn_submit" onclick="return validate()">
						<!-- <input type="button" class="btn btn-info" value="Edit" id="edit" onclick="editClick()"> -->
<!-- 						<input type="button" class="btn btn-danger" value="Delete" id="delete" onclick="deleteClick()">
 -->					
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
/* 
$('#btn_submit').click(function(){
    var form = document.getElementById("validation-form")
    form.action ="addRawMaterial";
    form.submit();
});
 */
$('#delete').click(function(){
    var form = document.getElementById("validation-form")
    form.action = "deleteRawMaterial";
    var r = confirm("You want to delete this Raw MMaterial!");
    if (r == true) {
        form.submit();
    }

});

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
	if (bmsMinQty>=bmsMaxQty) { 
		isValid = false;
		alert("BMS maximum qty is always greater than minimum Qty");
	} else if (bmsRolQty>=bmsMaxQty) {
		isValid = false;
		alert("BMS reorder level qty is between minimum qty & maximum qty");
	} else if (bmsRolQty<=bmsMinQty) {
		isValid = false;
		alert("BMS reorder level qty is between minimum qty & maximum qty");
	}
	
	return isValid;
}
function validationForStore() {
	
	var storeIssueQty=parseFloat($("#rm_iss_qty").val());
	var storeOpQty=parseFloat($("#rm_op_qty").val());
	var storeRecQty=parseFloat($("#rm_recd_qty").val());

	var isValid = true;
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
	
	return isValid;
}

function validate() {
    return validation() && validationForStore();
}</script> 
</body>
</html>