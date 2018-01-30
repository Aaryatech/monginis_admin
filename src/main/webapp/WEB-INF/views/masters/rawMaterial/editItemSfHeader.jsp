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
						<i class="fa fa-file-o"></i>Semi Finished Item Edit
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
								<i class="fa fa-bars"></i>Edit Item
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showItemSf">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>

						</div>


						<div class="box-content">
							<form action="${pageContext.request.contextPath}/editSfHeader" method="post"
								class="form-horizontal" id="validation-form">

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label"> SF Name</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="sf_item_name" id="sf_item_name"
											class="form-control" placeholder="SF Name" value="${sfName}"
											data-rule-required="true" />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">SF Type
									</label>
									<div class="col-sm-6 col-lg-4 controls">

										<select name="sf_item_type" id="sf_item_type"
											class="form-control" placeholder="SF Type"
											data-rule-required="true">
											<option value="1">Select SF Type</option>
											<c:forEach items="${sfTypeList}" var="sfTypeList"
												varStatus="count">
												
												<c:choose>
												<c:when test="${editHeader.sfType eq sfTypeList.id}">
													<option selected value="${sfTypeList.id}"><c:out value="${sfTypeList.sfTypeName}"/></option>

												</c:when>
												<c:otherwise>
													<option value="${sfTypeList.id}"><c:out value="${sfTypeList.sfTypeName}"/></option>
												</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
									</div>

								</div>

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">SF UOM</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="sf_item_uom" id="sf_item_uom"
											class="form-control" placeholder="SF UOM"
											data-rule-required="true">
											<option value="1">Select UOM</option>
											<c:forEach items="${rmUomList}" var="rmUomList"
												varStatus="count">
												<c:choose>
												<c:when test="${editHeader.sfUomId eq rmUomList.uomId}">
													<option selected value="${rmUomList.uomId}"><c:out value="${rmUomList.uom}"/></option>
												</c:when>
												<c:otherwise>
													<option value="${rmUomList.uomId}"><c:out value="${rmUomList.uom}"/></option>

												</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
									</div>

									<label class="col-sm-3 col-lg-2 control-label">SF
										Weight </label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="sf_item_weight" id="sf_item_weight" value="${editHeader.sfWeight}"
											class="form-control" placeholder="Specification "
											data-rule-required="true"  data-rule-number="true"/>
									</div>
								</div>

								<div class="form-group">


									<label class="col-sm-3 col-lg-2 control-label">Stock
										Qty</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="sf_stock_qty" id="sf_stock_qty"
											class="form-control" placeholder="Weight"
											data-rule-required="true" data-rule-number="true"  value="${editHeader.stockQty}"
											onKeyPress="return isNumberCommaDot(event)" />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Reorder
										Level Qty </label>

									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="sf_reorder_level_qty"
											id="sf_reorder_level_qty" class="form-control"
											placeholder="Max Qty " data-rule-required="true"
											data-rule-number="true"
											value="${editHeader.reorderLevelQty}"
											onKeyPress="return isNumberCommaDot(event)" />
									</div>

								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Min
										Level Qty </label>

									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="sf_min_qty" id="sf_min_qty"
											class="form-control" placeholder="Pack Qty"
											data-rule-required="true" data-rule-number="true"
											value="${editHeader.minLevelQty}"
											onKeyPress="return isNumberCommaDot(event)" />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Max
										Level Qty </label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="sf_max_qty" id="sf_max_qty"
											class="form-control" placeholder="Min Qty"
											data-rule-required="true" data-rule-number="true"
											value="${editHeader.maxLevelQty}"
											onKeyPress="return isNumberCommaDot(event)" />
									</div>
								</div>
								<div class="form-group">
								<label class="col-sm-3 col-lg-2 control-label">Multiplication
										Factor</label>
								<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="mul_factor" id="mul_factor"
											class="form-control" placeholder="Multiplication Factot"
											value="${editHeader.mulFactor}"
											data-rule-required="true" data-rule-number="true"
											onKeyPress="return isNumberCommaDot(event)" />
									</div>
									</div>
									
								<div class="row">
									<div class="col-md-12" style="text-align: center">
										<input type="button" onclick="validateQty()" class="btn btn-info" value="Update">

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
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/date.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/common.js"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>

<script type="text/javascript">
	function validateQty() {
		
		var min = parseFloat(document.getElementById("sf_min_qty").value);
		var max = parseFloat(document.getElementById("sf_max_qty").value);
		var reOrder = parseFloat(document.getElementById("sf_reorder_level_qty").value);
		var sfName = document.getElementById("sf_item_name").value;
		var sfType = document.getElementById("sf_item_type").value;
		var uom = document.getElementById("sf_item_uom").value;
		var weight = parseFloat(document.getElementById("sf_item_weight").value);
		var stockQty = parseFloat(document.getElementById("sf_stock_qty").value);
		
				var mulFactor = document.getElementById("mul_factor").value;

		/* alert("min"+min);
		alert("max"+max);
		alert("reOrder"+reOrder);
		alert("sfName"+sfName);
		alert("sfType"+sfType);
		alert("uom"+uom);
		alert("weight"+weight);
		alert("stockQty"+stockQty);
		alert("mulFactor"+mulFactor); */
		
		
		var valid=true;
		
		if(max <= min ){
			alert("Enter Max Qty  greater than Min Qty");
			valid=false;
		}
		else if(reOrder<=min || reOrder>=max ){
			alert("Enter Reorder Qty between Min and Max Qty");
			valid=false;
		}
		
		else if(sfName==null || sfName==""){
			alert("Please Enter SF Name");
			valid=false;
		}
		else if(sfType==0){
			alert("Please Select Sf Type");
			valid=false;
		}
		else if(uom==0){
			alert("Please Select Unit of Measure");
			valid=false;
		}
		else if(weight<0 || isNaN(weight)){
			alert("Please Enter valid Weight")
			valid=false;
		}
		else if(isNaN(min)){
			alert("Please Enter valid Min Qty");
			valid=false;
		}
		else if(max<=0 || isNaN(max)){
			alert("Please Enter valid Max Qty");
			valid=false;
		}
		else if(stockQty<=0 || isNaN(stockQty)){
			alert("Please Enter valid Stock Qty");
			valid=false;
		}
		else if(reOrder<=0 || isNaN(reOrder)){
			alert("Please Enter valid Reorder Qty");
			valid=false;
		}
		
		else if(mulFactor<=0 || isNaN(mulFactor)){
			alert("Please Enter valid Multiplication Qty");
			valid=false;
		}
		
		
		if(valid){
			 var form = document.getElementById("validation-form")
			    form.action ="${pageContext.request.contextPath}/editSfHeader";
			    form.submit();
		}

		
	}
	</script>

</body>
</html>

