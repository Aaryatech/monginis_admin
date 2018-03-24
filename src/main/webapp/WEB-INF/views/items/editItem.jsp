<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body onload="calTotalGstOnLoad()">
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
	
	
	<c:url var="getGroup2ByCatId" value="/getGroup2ByCatId" />

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
						<i class="fa fa-file-o"></i> Items
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
								<i class="fa fa-bars"></i> Edit Item
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/itemList">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							<!-- <div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> <a data-action="close" href="#"><i
									class="fa fa-times"></i></a>
							</div> -->
						</div>


						<div class="box-content">
							<form action="updateItemProcess" class="form-horizontal" id="validation-form"
								 method="post" enctype="multipart/form-data">



								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Item Id</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="item_id" value="${item.itemId}"
											id="item_id" placeholder="Item Id" class="form-control"
											data-rule-required="true" /> <input type="hidden"
											name="itemId" value="${item.id}">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label" for="item_name">Item
										Name</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="item_name" id="item_name"
											value="${item.itemName}" placeholder="Item Name"
											class="form-control" data-rule-required="true"
											/>
									</div>
								</div>


								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Group1</label>
									<div class="col-sm-9 col-lg-10 controls">
										<select data-placeholder="Select Group" name="item_grp1"
											class="form-control chosen" tabindex="-1" id="item_grp1"
											data-rule-required="true">


											<c:forEach items="${mCategoryList}" var="mCategoryList">
												<c:choose>
													<c:when test="${mCategoryList.catId==item.itemGrp1}">
														<option selected value="${item.itemGrp1}"><c:out value="${mCategoryList.catName}"></c:out> </option>
													</c:when>
													<c:otherwise>
														<option value="${mCategoryList.catId}"><c:out value="${mCategoryList.catName}"></c:out></option>
													</c:otherwise>
												</c:choose>


											</c:forEach>

										</select>
									</div>
								</div>


								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Group2</label>
									<div class="col-sm-9 col-lg-10 controls">
										<select data-placeholder="Select Group" name="item_grp2"
											class="form-control chosen-select" tabindex="-1"
											id="item_grp2" data-rule-required="true">
											<option selected value="${selectedItemId}"><c:out value="${selectedItem}"></c:out></option>
											
											 <c:forEach items="${subCategoryList}" var="subCategoryList">
													
													
													<option value="${subCategoryList.subCatId}"><c:out value="${subCategoryList.subCatName}"></c:out></option>
												</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Group3</label>
									<div class="col-sm-9 col-lg-10 controls">
										<select data-placeholder="Select Group" name="item_grp3"
											class="form-control chosen" tabindex="-1" id="selS0V"
											>
											
											<c:choose>
													<c:when test="${itemGrp3.equals('1')}">
													<option selected value="1">Small</option>
													<option  value="2">Medium</option>
													<option  value="3">Large</option>
													</c:when>
													
													<c:when test="${itemGrp3.equals('2')}">
													<option  value="1">Small</option>
													<option selected  value="2">Medium</option>
													<option  value="3">Large</option>
													</c:when>
													
													<c:when test="${itemGrp3.equals('3')}">
													<option  value="1">Small</option>
													<option   value="2">Medium</option>
													<option  selected value="3">Large</option>
													</c:when>
													
													</c:choose>
											
											
										</select>
									</div>
								</div>
                         <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Min Quantity</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="min_qty" id="min_qty"
											value="${item.minQty}" placeholder="Minimum Quantity"
											class="form-control" data-rule-required="true" data-rule-number="true" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Local
										Rate</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="item_rate1" id="item_rate1"
											value="${item.itemRate1}" placeholder="Item Rate1"
											class="form-control" data-rule-required="true" data-rule-number="true" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Out Station
										Rate</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="item_rate2" id="item_rate2"
											value="${item.itemRate2}" placeholder="Item Rate2"
											class="form-control" data-rule-required="true" data-rule-number="true"/>
									</div>
								</div>

                                 <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Special
										Rate</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="item_rate3" id="item_rate3"
											value="${item.itemRate3}" placeholder="Item Rate3"
											class="form-control" data-rule-required="true" data-rule-number="true"/>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Local
										Mrp</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="item_mrp1" id="item_mrp1"
											value="${item.itemMrp1}" placeholder="Item Mrp1"
											class="form-control" data-rule-required="true" data-rule-number="true" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Out Station
										Mrp</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="item_mrp2" id="item_mrp2"
											value="${item.itemMrp2}" placeholder="Item Mrp2"
											class="form-control" data-rule-required="true" data-rule-number="true"/>
									</div>
								</div>
                                <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Special
										Mrp</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="item_mrp3" id="item_mrp3"
											value="${item.itemMrp3}" placeholder="Item Mrp3"
											class="form-control" data-rule-required="true" data-rule-number="true"/>
									</div>
								</div>

<div> <input type="hidden" name="prevImage" value="${item.itemImage}"></div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Product Image</label>
									<div class="col-sm-9 col-lg-10 controls">
										<div class="fileupload fileupload-new"
											data-provides="fileupload">
											<div class="fileupload-new img-thumbnail"
												style="width: 200px; height: 150px;">
												<img
													src="${url}${item.itemImage}"  onerror="this.src='${pageContext.request.contextPath}/resources/img/No_Image_Available.jpg';"/>
													

											</div>
											<div
												class="fileupload-preview fileupload-exists img-thumbnail"
												style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
											<div>
												<span class="btn btn-default btn-file"><span
													class="fileupload-new">Select image</span> <span
													class="fileupload-exists">Change</span> <input type="file"
													class="file-input" name="item_image" id="item_image"
													value="${item.itemImage}"/></span> <a
													href="#" class="btn btn-default fileupload-exists"
													data-dismiss="fileupload">Remove</a>
											</div>
										</div>

									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">IGST %</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="item_tax3" id="item_tax3"
											value="${item.itemTax3}" placeholder="IGST"
											class="form-control" data-rule-required="true" data-rule-number="true"value="0.0"onchange="calTotalGst()"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">CGST %</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="item_tax2" id="item_tax2"
											value="${item.itemTax2}" placeholder="CGST"
											class="form-control" data-rule-required="true" data-rule-number="true" value="0.0" onchange="calTotalGst()"/>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">SGST %</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="item_tax1" id="item_tax1"
											value="${item.itemTax1}" placeholder="SGST"
											class="form-control" data-rule-required="true" data-rule-number="true" value="0.0" />
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
									<label class="col-sm-3 col-lg-2 control-label">Is Used?</label>
									<div class="col-sm-9 col-lg-10 controls">
										<select class="form-control input-sm" tabindex="1"
											name="is_used">

											
								
								
								<c:set var= "strIsUsedType" value="${strIsUsed}"></c:set>
													 <% 
													 	String strIsUsedOption=(String) pageContext.getAttribute("strIsUsedType");
													 if(strIsUsedOption.equalsIgnoreCase("1")){
														 												 
													 
													 %>
													 <option selected value="1">Active Daily</option>
													 
													 	<option value="2">Special Days</option>
													
													
													<option value="3">Sp Day Cake</option>
														<option value="4">Inactive</option>

												
													
													<%}else if(strIsUsedOption.equalsIgnoreCase("2")){
														
														
														%>

													<option value="1">Active Daily</option>
														<option selected value="2">Special Days</option>

													
													<option value="3">Sp Day Cake</option>
														<option value="4">Inactive</option>
													
														
														
														
													<%}else if(strIsUsedOption.equalsIgnoreCase("3")){
														
														
														%>

													<option value="1">Active Daily</option>
														

														<option value="2">Special Days</option>
														<option selected value="3">Sp Day Cake</option>
													
														<option value="4">Inactive</option>
													
														
														
														
														<%
													}else{
													
													
														%>
															
															<option value="1">Active Daily</option>
														<option selected value="2">Special Days</option>

														<option value="3">Sp Day Cake</option>
														<option selected value="4">Inactive</option>
													
													
													
													<%} %>
								
								
								</select>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Item
										SortId</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="item_sort_id" id="item_sort_id"
											value="${item.itemSortId}" placeholder="Item Sort Id"
											class="form-control" data-rule-required="true" data-rule-number="true"/>
									</div>
								</div>
								
								<%-- <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">GRN 2
										Applicable ?</label>
									<div class="col-sm-9 col-lg-10 controls">
										<label class="radio-inline"> <input type="radio"
											name="grn_two" id="optionsRadios1" value="${item.grnTwo}"
											checked> Not Applicable
										</label> <label class="radio-inline"> <input type="radio"
											name="grn_two" id="optionsRadios1" value="${item.grnTwo}"
											data-rule-required="false" /> Applicable
										</label>
									</div>
								</div> --%>
								
								
								
								
										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">GRN Type</label>
											<div class="col-sm-9 col-lg-10 controls">
												<label class="radio-inline"> </label>
												<c:choose>
													<c:when test="${strGrnAppl.equals('0')}">

													

														<label class="radio-inline"> <input type="radio"
															name="grn_two" id="optionsRadios2" checked
															value="0" /> GRN1
														</label>
															<label class="radio-inline"> <input type="radio"
															name="grn_two" id="optionsRadios2" value="1" />
															GRN2
														</label>
														
														<label class="radio-inline"> <input type="radio"
															name="grn_two" id="optionsRadios2" value="2" />
															GRN3
														</label>
														
													</c:when>
													
													<c:when test="${strGrnAppl.equals('1')}">

													

														<label class="radio-inline"> <input type="radio"
															name="grn_two" id="optionsRadios2" 
															value="0" /> GRN1
														</label>
															<label class="radio-inline"> <input type="radio"
															name="grn_two" id="optionsRadios2" value="1"checked />
															GRN2
														</label>
														
														<label class="radio-inline"> <input type="radio"
															name="grn_two" id="optionsRadios2" value="2" />
															GRN3
														</label>
														
													</c:when>
													
													
													<c:when test="${strGrnAppl.equals('2')}">

													

														<label class="radio-inline"> <input type="radio"
															name="grn_two" id="optionsRadios2" 
															value="0" /> GRN1
														</label>
															<label class="radio-inline"> <input type="radio"
															name="grn_two" id="optionsRadios2" value="1" />
															GRN2
														</label>
														
														<label class="radio-inline"> <input type="radio"
															name="grn_two" id="optionsRadios2" value="2" checked />
															GRN3
														</label>
														
													</c:when>
													

												</c:choose>

											</div>
										</div>
										
										 <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Item Shelf Life</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="item_shelf_life" id="item_shelf_life"
											value="${item.shelfLife}" placeholder="Item Shelf Life"
											class="form-control" data-rule-required="true" data-rule-number="true" />
									</div>
								</div>
								

								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
										<input type="submit" class="btn btn-primary" value="Submit">
										<!-- <button type="button" class="btn">Cancel</button> -->
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



	<script type="text/javascript">
$(document).ready(function() { 
	$('#item_grp1').change(
			function() {
				$.getJSON('${getGroup2ByCatId}', {
					catId : $(this).val(),
					ajax : 'true'
				}, function(data) {
					var html = '<option value="" selected >select Group 2</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].subCatId + '">'
								+ data[i].subCatName + '</option>';
					}
					html += '</option>';
					$('#item_grp2').html(html);
					$('#item_grp2').formcontrol('refresh');

				});
			});
});
</script>

<script>
function calTotalGst() {
	 var igst=parseFloat($("#item_tax3").val());
	  var cgst=parseFloat($("#item_tax2").val());
	  var sgst=parseFloat(igst-cgst);
	  var totGst=parseFloat(cgst+sgst);

	  document.getElementById("item_tax1")
		.setAttribute('value', sgst);
	  
	  document.getElementById("total_gst_appli")
		.setAttribute('value', totGst);
}
</script>

<script>
function calTotalGstOnLoad() {
   
	  var sgst=parseFloat($("#item_tax1").val());
	  var cgst=parseFloat($("#item_tax2").val());
	  var totalGst=parseFloat(cgst+sgst);
	
	  document.getElementById("total_gst_appli")
		.setAttribute('value', totalGst);
}
</script>
</body>
</html>