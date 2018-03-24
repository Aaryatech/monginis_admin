<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
	
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
												<select name="item_uom" id="item_uom" class="form-control"placeholder="Item UOM"
												 data-rule-required="true" onchange="uomChanged()">
											<option value="">Select Item UOM</option>
											<c:forEach items="${rmUomList}" var="rmUomList"
													varStatus="count">
													<c:choose>
													<c:when test="${rmUomList.uomId==itemSupp.uomId}">
														<option value="${rmUomList.uomId}" selected><c:out value="${rmUomList.uom}"/></option>
													</c:when>
													<c:otherwise>
														<option value="${rmUomList.uomId}"><c:out value="${rmUomList.uom}"/></option>
													</c:otherwise>
													</c:choose>
												</c:forEach>
										</select>
									</div>
							  </div>
							  <input type="hidden" name="uom" id="uom" value="${itemSupp.itemUom}"/> 
							  <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Actual Weight</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="actual_weight" id="actual_weight"
											placeholder="Actual Weight" class="form-control"
											data-rule-required="true" data-rule-number="true"value="${itemSupp.actualWeight}"/>
									</div>
							  </div>
							   <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Base Weight</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="base_weight" id="base_weight"
											placeholder="Base Weight" class="form-control"
											data-rule-required="true" data-rule-number="true" value="${itemSupp.baseWeight}"/>
									</div>
							  </div>
							  <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Input Per Unit</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="input_per_qty" id="input_per_qty"
											placeholder="Input Per Unit" class="form-control"
											data-rule-required="true"  data-rule-number="true" value="${itemSupp.inputPerQty}"/>
									</div>
							  </div>
							    <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Cut Section</label>
									<div class="col-sm-9 col-lg-3 controls">
										<select name="cut_section" id="cut_section" class="form-control"
												 data-rule-required="true">
											<option value="">Select Cut Section</option>
											
										<c:choose>
										<c:when test="${itemSupp.cutSection==0}">
										<option value="0" selected>Not Applicable</option>
											<option value="1">Single Cut</option>
											<option value="2">Double Cut</option>
										</c:when>
											<c:when test="${itemSupp.cutSection==1}">
											<option value="0" >Not Applicable</option>
											<option value="1"selected>Single Cut</option>
											<option value="2">Double Cut</option>
											</c:when>
											<c:when test="${itemSupp.cutSection==2}">
											<option value="0" >Not Applicable</option>
											<option value="1">Single Cut</option>
											<option value="2"selected>Double Cut</option>
											</c:when>
											<c:otherwise>
										    <option value="0">Not Applicable</option>
											<option value="1">Single Cut</option>
											<option value="2">Double Cut</option>
											</c:otherwise>
										</c:choose>
										</select>
									</div>
							  </div>
						    <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Type Of Tray</label>
									<div class="col-sm-9 col-lg-3 controls">
												<select name="tray_type" id="tray_type" class="form-control"placeholder="Type Of Tray"
												 data-rule-required="true">
											<option value="">Select Type Of Tray</option>
											<c:forEach items="${trayTypes}" var="trayTypes"
													varStatus="count">
													<c:choose>
													<c:when test="${trayTypes.typeId==itemSupp.trayType}">
														<option value="${trayTypes.typeId}" selected><c:out value="${trayTypes.typeName}"/></option>
													</c:when>
													<c:otherwise>
														<option value="${trayTypes.typeId}"><c:out value="${trayTypes.typeName}"/></option>
													</c:otherwise>
													</c:choose>
												</c:forEach>
										</select>
									</div>
							  </div>
							    <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">No. Of Item Per Tray</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="no_of_item" id="no_of_item"
											placeholder="No. Of Item Per Tray" class="form-control"
											data-rule-required="true"  data-rule-number="true" value="${itemSupp.noOfItemPerTray}"/>
									</div>
							  </div> 
							      <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Gate Sale Allowed?</label>
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
							    <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Gate Sale Discount Allowed?</label>
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
							  
							  
							    <div class="col2">
							    
							     <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Short Name</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="short_name" id="short_name"
											placeholder="Short Name" class="form-control"
											data-rule-required="true"  value="${itemSupp.shortName}"/>
									</div>
							  </div> 
									<label class="col-sm-3 col-lg-2 control-label">Allowed For Employee Birthday?</label>
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
<script type="text/javascript">

			function uomChanged() {
				
				document.getElementById('uom').value=$('#item_uom option:selected').text();
				
			}
</script>
</html>
