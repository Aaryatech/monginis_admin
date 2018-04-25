<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/tableSearch.css">
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
						<i class="fa fa-file-o"></i>Semi Finished Item
					</h1>

				</div>
			</div>
			<!-- END Page Title -->


			<c:set var="isAdd" value="0">
			</c:set>
			<c:set var="isEdit" value="0">
			</c:set>
			<c:set var="isView" value="0">
			</c:set>
			<c:set var="isDelete" value="0">
			</c:set>

			<c:forEach items="${sessionScope.newModuleList}" var="modules">
				<c:forEach items="${modules.subModuleJsonList}" var="subModule">

					<c:choose>
						<c:when test="${subModule.subModuleMapping eq 'showItemSf'}">

							<c:choose>
								<c:when test="${subModule.editReject=='visible'}">
									<c:set var="isEdit" value="1">
									</c:set>
								</c:when>
								<c:otherwise>
									<c:set var="isEdit" value="0">
									</c:set>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${subModule.view=='visible'}">
									<c:set var="isView" value="1">
									</c:set>
								</c:when>
								<c:otherwise>
									<c:set var="isView" value="0">
									</c:set>
								</c:otherwise>
							</c:choose>


							<c:choose>
								<c:when test="${subModule.deleteRejectApprove=='visible'}">
									<c:set var="isDelete" value="1">
									</c:set>
								</c:when>
								<c:otherwise>
									<c:set var="isDelete" value="0">
									</c:set>
								</c:otherwise>
							</c:choose>

							<c:choose>
								<c:when test="${subModule.addApproveConfig=='visible'}">
									<c:set var="isAdd" value="1">
									</c:set>
								</c:when>
								<c:otherwise>
									<c:set var="isAdd" value="0">
									</c:set>
								</c:otherwise>
							</c:choose>


						</c:when>
					</c:choose>
				</c:forEach>
			</c:forEach>





			<!-- BEGIN Main Content -->
			<div class="row">
				<div class="col-md-12">
					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-bars"></i>Add Item
							</h3>
							<div class="box-tool">
								<!-- <a href="">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> -->
							</div>

						</div>


						<div class="box-content">
							<form action="insertSfItemHeader" method="post"
								class="form-horizontal" id="validation-form" method="post">

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label"> SF Name</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="sf_item_name" id="sf_item_name"
											class="form-control" placeholder="SF Name"
											data-rule-required="true" />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">SF Type
									</label>
									<div class="col-sm-6 col-lg-4 controls">

										<select name="sf_item_type" id="sf_item_type"
											class="form-control" placeholder="SF Type"
											data-rule-required="true">
											<option value="0">Select SF Type</option>
											<c:forEach items="${sfTypeList}" var="sfTypeList"
												varStatus="count">
												<option value="${sfTypeList.id}"><c:out value="${sfTypeList.sfTypeName}"/></option>
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
											<option value="0">Select UOM</option>
											<c:forEach items="${rmUomList}" var="rmUomList"
												varStatus="count">
												<option value="${rmUomList.uomId}"><c:out value="${rmUomList.uom}"/></option>
											</c:forEach>
										</select>
									</div>

									<label class="col-sm-3 col-lg-2 control-label">SF
										Weight </label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="sf_item_weight" id="sf_item_weight"
											class="form-control" placeholder="SF Weight "
											data-rule-required="true" data-rule-number="true" />
									</div>
								</div>

								<div class="form-group">


									<label class="col-sm-3 col-lg-2 control-label">Stock
										Qty</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="sf_stock_qty" id="sf_stock_qty"
											class="form-control" placeholder="Stock Qty"
											data-rule-required="true" data-rule-number="true"
											onKeyPress="return isNumberCommaDot(event)" />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Reorder
										Level Qty </label>

									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="sf_reorder_level_qty"
											id="sf_reorder_level_qty" class="form-control"
											placeholder="Reorder Level Qty " data-rule-required="true"
											data-rule-number="true"
											onKeyPress="return isNumberCommaDot(event)" />
									</div>

								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Min
										Level Qty </label>

									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="sf_min_qty" id="sf_min_qty"
											class="form-control" placeholder="Min Level Qty"
											data-rule-required="true" data-rule-number="true"
											onKeyPress="return isNumberCommaDot(event)" />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Max
										Level Qty </label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="sf_max_qty" id="sf_max_qty"
											class="form-control" placeholder="Max Level Qty"
											data-rule-required="true" data-rule-number="true"
											onKeyPress="return isNumberCommaDot(event)" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Multiplication
										Factor </label>

									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="mul_factor" id="mul_factor"
											class="form-control" placeholder="Multi Factot"
											data-rule-required="true" data-rule-number="true"
											onKeyPress="return isNumberCommaDot(event)" />
									</div>
								</div>

								<div class="row">
									<div class="col-md-12" style="text-align: center">

										<c:choose>
											<c:when test="${isAdd eq '1'}">
												<input type="button" onclick="validateQty()"
													class="btn btn-info" value="Submit">

											</c:when>
											<c:otherwise>
												<input type="button" onclick="validateQty()"
													class="btn btn-info" value="Submit" disabled="disabled">


											</c:otherwise>
										</c:choose>
										<!-- 										<input type="button" onclick="validateQty()" class="btn btn-info" value="Submit">
 -->
									</div>
								</div>
								<div class="col-md-9"></div>
								<label for="search" class="col-md-3" id="search"> <i
									class="fa fa-search" style="font-size: 20px"></i> <input
									type="text" id="myInput" onkeyup="myFunction()"
									placeholder="Search.." title="Type in a name">
								</label>
								<div class="clearfix"></div>
								<div id="table-scroll" class="table-scroll">

									<div id="faux-table" class="faux-table" aria="hidden">
										<table id="table2" class="main-table">
											<thead>
												<tr class="bgpink">
													<th width="140" style="width: 30px" align="left">Sr No</th>
													<th width="138" align="left">SF Name</th>
													<th width="120" align="left">SF Type</th>
													<th width="60" align="left">SF Weight</th>

													<th width="80" align="left">Min Level Qty</th>
													<th width="100" align="left">Reorder Level Qty</th>

													<th width="120" align="left">Action</th>
												</tr>
											</thead>
										</table>

									</div>
									<div class="table-wrap">

										<table id="table1" class="table table-advance">
											<thead>
												<tr class="bgpink">
													<th width="140" style="width: 30px" align="left">No</th>
													<th width="138" align="left">SF Name</th>
													<th width="120" align="left">SF Type</th>
													<th width="60" align="left">SF Weight</th>

													<th width="80" align="left">Min Level Qty</th>
													<th width="100" align="left">Reorder Level Qty</th>

													<th width="120" align="left">Action</th>
												</tr>
											</thead>
											<!-- 
								<div class="table-responsive" style="border: 0">
									<table width="100%" class="table table-advance" id="table1">
										<thead>
											<tr>

												<th width="140" style="width: 30px" align="left">Sr No</th>
												<th width="138" align="left">SF Name</th>
												<th width="120" align="left">SF Type</th>
												<th width="60" align="left">SF Weight</th>

												<th width="80" align="left">Min Level Qty</th>
												<th width="100" align="left">Reorder Level Qty</th>

												<th width="120" align="left">Action</th>
													<th width="140" align="left">GST %</th>

											</tr>

										</thead> -->

											<tbody>

												<c:forEach items="${itemHeaderList}" var="itemHeaderList"
													varStatus="count">

													<tr>
														<td><c:out value="${count.index+1}" /></td>

														<td align="left"><c:out
																value="${itemHeaderList.sfName}" /></td>


														<td align="left"><c:out
																value="${itemHeaderList.sfTypeName}" /></td>

														<td align="center"><c:out
																value="${itemHeaderList.sfWeight}" /></td>

														<td align="center"><c:out
																value="${itemHeaderList.minLevelQty}" /></td>

														<td align="center"><c:out
																value="${itemHeaderList.reorderLevelQty}" /></td>
																
																
																
																
																
																	
												<c:choose>
																	<c:when test="${isEdit==1 and isDelete==1}">
																		
														<td><a
															href="${pageContext.request.contextPath}/showAddSfItemDetail/${itemHeaderList.sfId}/${itemHeaderList.sfName}/${itemHeaderList.sfTypeName}"
															class="btn bnt-primary"> <i class="fa fa-list"></i></a> <a
															href="${pageContext.request.contextPath}/editSfItemHeader/${itemHeaderList.sfId}/${itemHeaderList.sfName}/${itemHeaderList.sfTypeName}"
															class="btn btn-primary"><i class="fa fa-edit"></i></a> <a
															href="${pageContext.request.contextPath}/deleteItemSf/${itemHeaderList.sfId}"
															class="btn bnt-primary"
															onClick="return confirm('Are you sure want to delete this record');"><span
																class="glyphicon glyphicon-trash"></span></a></td>
														
																	</c:when>

																	<c:when test="${isEdit==1 and isDelete==0}">
																		
														<td><a
															href="${pageContext.request.contextPath}/showAddSfItemDetail/${itemHeaderList.sfId}/${itemHeaderList.sfName}/${itemHeaderList.sfTypeName}"
															class="btn bnt-primary"> <i class="fa fa-list"></i></a> <a
															href="${pageContext.request.contextPath}/editSfItemHeader/${itemHeaderList.sfId}/${itemHeaderList.sfName}/${itemHeaderList.sfTypeName}"
															class="btn btn-primary"><i class="fa fa-edit"></i></a> <a
															href="${pageContext.request.contextPath}/deleteItemSf/${itemHeaderList.sfId}"
															 class="disableClick"
															onClick="return confirm('Are you sure want to delete this record');"><span
																class="glyphicon glyphicon-trash"></span></a></td>
														
																	</c:when>

																	<c:when test="${isEdit==0 and isDelete==1}">
																		
														<td><a
															href="${pageContext.request.contextPath}/showAddSfItemDetail/${itemHeaderList.sfId}/${itemHeaderList.sfName}/${itemHeaderList.sfTypeName}"
															 class="disableClick"> <i class="fa fa-list"></i></a> <a
															href="${pageContext.request.contextPath}/editSfItemHeader/${itemHeaderList.sfId}/${itemHeaderList.sfName}/${itemHeaderList.sfTypeName}"
															class="btn btn-primary"><i class="fa fa-edit"></i></a> <a
															href="${pageContext.request.contextPath}/deleteItemSf/${itemHeaderList.sfId}"
															class="btn bnt-primary"
															onClick="return confirm('Are you sure want to delete this record');"><span
																class="glyphicon glyphicon-trash"></span></a></td>
																	</c:when>

																	<c:otherwise>

																		
														<td><a
															href="${pageContext.request.contextPath}/showAddSfItemDetail/${itemHeaderList.sfId}/${itemHeaderList.sfName}/${itemHeaderList.sfTypeName}"
															 class="disableClick"> <i class="fa fa-list"></i></a> <a
															href="${pageContext.request.contextPath}/editSfItemHeader/${itemHeaderList.sfId}/${itemHeaderList.sfName}/${itemHeaderList.sfTypeName}"
															class="btn btn-primary"><i class="fa fa-edit"></i></a> <a
															href="${pageContext.request.contextPath}/deleteItemSf/${itemHeaderList.sfId}"
															 class="disableClick"
															onClick="return confirm('Are you sure want to delete this record');"><span
																class="glyphicon glyphicon-trash"></span></a></td>
																	</c:otherwise>
																</c:choose>
												
																
																
																
<%-- 
														<td><a
															href="${pageContext.request.contextPath}/showAddSfItemDetail/${itemHeaderList.sfId}/${itemHeaderList.sfName}/${itemHeaderList.sfTypeName}"
															class="btn bnt-primary"> <i class="fa fa-list"></i></a> <a
															href="${pageContext.request.contextPath}/editSfItemHeader/${itemHeaderList.sfId}/${itemHeaderList.sfName}/${itemHeaderList.sfTypeName}"
															class="btn btn-primary"><i class="fa fa-edit"></i></a> <a
															href="${pageContext.request.contextPath}/deleteItemSf/${itemHeaderList.sfId}"
															class="btn bnt-primary"
															onClick="return confirm('Are you sure want to delete this record');"><span
																class="glyphicon glyphicon-trash"></span></a></td> --%>

													</tr>
												</c:forEach>

											</tbody>
										</table>
									</div>
								</div>

							</form>
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
		
		var min = document.getElementById("sf_min_qty").value;
		var max = document.getElementById("sf_max_qty").value;
		var reOrder = document.getElementById("sf_reorder_level_qty").value;
		var sfName = document.getElementById("sf_item_name").value;
		//alert( "name= "+sfName);
		var sfType = document.getElementById("sf_item_type").value;
		//alert("type ="+sfType);
		var uom = document.getElementById("sf_item_uom").value;
		//alert("uom = "+uom);
		var weight = document.getElementById("sf_item_weight").value;
		//alert("weight="+weight);
		var stockQty = document.getElementById("sf_stock_qty").value;
		//alert("stock qty "+stockQty);
		
				var mulFactor = document.getElementById("mul_factor").value;

		//alert("Max = "+max);
		//alert("Min = "+min);
		
		
		var valid=true;
		
		if(parseInt(max) === parseInt(min)){
			alert("Enter Max Qty  greater than Min Qty");
			valid=false;
		}else if(parseInt(min) > parseInt(max)){
			alert("Enter Max Qty  greater than Min Qty");
			valid=false;
		}
		else if(parseInt(reOrder)<=parseInt(min) || parseInt(reOrder)>=parseInt(max)){
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
			    form.action ="${pageContext.request.contextPath}/insertSfItemHeader";
			    form.submit();
		}

		
	}
	</script>
	<script>
function myFunction() {
  var input, filter, table, tr, td, i;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("table1");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1];
    if (td) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}
</script>
</body>
</html>

