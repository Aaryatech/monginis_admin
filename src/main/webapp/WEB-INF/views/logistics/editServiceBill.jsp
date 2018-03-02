<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include> 
	<c:url var="getTypeList" value="/getTypeList"></c:url>
	<c:url var="machineListOrVehicleList" value="/machineListOrVehicleList"></c:url>
<c:url var="groupByTypeId" value="/groupByTypeId"></c:url>
	<c:url var="addSparePartInEditInvoice" value="/addSparePartInEditInvoice"></c:url>
	<c:url var="sparePartByGroupId" value="/sparePartByGroupId"></c:url>
	<c:url var="partDetailById" value="/partDetailById"></c:url>
	<c:url var="deleteSparePartInEditInvoice" value="/deleteSparePartInEditInvoice"></c:url>
	<c:url var="updateNextServicingDueKm" value="/updateNextServicingDueKm"></c:url>
<c:url var="editSparePartInEditInvoice" value="/editSparePartInEditInvoice"></c:url>
<c:url var="changeQtyOfSparePartInEditInvoice" value="/changeQtyOfSparePartInEditInvoice"></c:url>

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
						<i class="fa fa-file-o"></i>Edit Servicing Bill
					</h1>
				</div>
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i>Edit Servicing Bill
							</h3>
							
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/insertSarvicing">Servicing Bill</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						<input class="form-control" id="typeIdName" size="16"
											type="hidden" name="typeIdName"  required />
						<input class="form-control" id="labourGroupId" value="${labourGroupId}" size="16"
											type="hidden" name="labourGroupId"    />
						<div class="box-content">

							<form id="submitMaterialStore" action="${pageContext.request.contextPath}/submitEditInvoice" method="post"
							enctype="multipart/form-data">
							<div class="box-content">
							
								<div class="col-md-2">Select Vehicle Or Machine*</div>
									<div class="col-md-3">
									<c:choose>
										<c:when test="${editServicing.servType2==1}">
											<c:set var="typeName" value="Vehicle"></c:set>
											<c:set var="typeIdValue" value="1"></c:set>
										</c:when>
										<c:when test="${editServicing.servType2==2}">
											<c:set var="typeName" value="Machine"></c:set>
											<c:set var="typeIdValue" value="2"></c:set>
										</c:when> 
									</c:choose>
									<select name="type" id="type" class="form-control chosen" tabindex="6" onchange="typeList();" required>
											 <option value="${typeIdValue}">${typeName}</option>
											  <option value="1">Vehicle</option>
											   <option value="2">Machine</option>
										</select>
									
									</div> 
									
							</div><br>
							<div class="box-content">
							<input type="hidden" id="servId" name="servId" value="${editServicing.servId}" placeholder="Service Advice Remainder" class="form-control"  required>
									
								<div class="col-md-2">Bill No*</div>
								<div class="col-md-3"><input type="text" id="billNo" name="billNo" placeholder="Bill No" value="${editServicing.billNo}" class="form-control" required>
								</div>
								
								<div class="col-md-2">Bill Date* </div>
									<div class="col-md-3">
									<input class="form-control date-picker" id="billDate" placeholder="Servicing Date" value="${editServicing.billDate}" size="16"
											type="text" name="billDate"  required />
									</div>
					
							</div><br>
							
							<div class="box-content">
							
								<div class="col-md-2">Type*</div>
									<div class="col-md-3">
									
									<select name="typeId" id="typeId" class="form-control chosen" onchange="updateNextServicingDueKm()" tabindex="6" required>
											<c:forEach items="${typeList}" var="typeList"> 
												<c:choose> 
														<c:when test="${typeList.typeId==editServicing.typeId}">
															<option value="${typeList.typeId}" selected><c:out value="${typeList.typeName}"></c:out> </option>
												 		</c:when> 
												</c:choose>
									 		</c:forEach>
									 
									<c:forEach items="${typeList}" var="typeList"> 
										<option value="${typeList.typeId}"><c:out value="${typeList.typeName}"></c:out> </option>
									 </c:forEach>
											 
										</select>
									
									</div>
								<div class="col-md-2">Servicing Type*</div>
									<div class="col-md-3">
									<c:choose>
										<c:when test="${editServicing.servType==1}">
											<c:set var="servTypeName" value="Regular"></c:set>
											<c:set var="servTypeValue" value="1"></c:set>
										</c:when>
										<c:when test="${editServicing.servType==2}">
											<c:set var="servTypeName" value="Break Down"></c:set>
											<c:set var="servTypeValue" value="2"></c:set>
										</c:when> 
									</c:choose>
									
									<select name="servType" id="servType" class="form-control chosen" tabindex="6" required>
											<option value="${servTypeValue}">${servTypeName}</option>
											<option value="1">Regular</option>
											 <option value="2">Break Down</option>  
										</select>
									
									</div>
								
				 
							</div><br>
							
							
							<div class="box-content">
							
							<div class="col-md-2">Servicing Date* </div>
									<div class="col-md-3">
									<input class="form-control date-picker" id="servDate" placeholder="Servicing Date" value="${editServicing.servDate}" size="16"
											type="text" name="servDate"  required />
									</div>
									
									<div class="col-md-2">Dealer*</div>
									<div class="col-md-3"> 
									<select name="dealerId" id="dealerId" class="form-control chosen" tabindex="6" required>
									<c:forEach items="${dealerList}" var="dealerList"> 
											<c:choose> 
													<c:when test="${dealerList.dealerId==editServicing.dealerId}">
														<option value="${dealerList.dealerId}" selected><c:out value="${dealerList.dealerName}"></c:out> </option>
											 		</c:when> 
											</c:choose>
									 </c:forEach>
									 
									<c:forEach items="${dealerList}" var="dealerList"> 
										<option value="${dealerList.dealerId}"><c:out value="${dealerList.dealerName}"></c:out> </option>
									 </c:forEach>
									</select>
								</div>
								
							</div><br> 
							
							
							
							<div class="box-content">
							
									<div class="col-md-2">Vehicle Or Machine*</div>
									<div class="col-md-3"> 
									<select name="vehId" id="vehId" class="form-control chosen" tabindex="6" onchange="updateNextServicingDueKm()" required>
									<c:choose>
									<c:when test="${editServicing.servType2==1}">
												<c:forEach items="${vehicleList}" var="vehicleList"> 
														<c:choose> 
																<c:when test="${vehicleList.vehId==editServicing.vehId}">
																	<option value="${vehicleList.vehId}" selected><c:out value="${vehicleList.vehNo}"></c:out> </option>
														 		</c:when> 
														</c:choose>
												 </c:forEach>
									 
													<c:forEach items="${vehicleList}" var="vehicleList"> 
														<option value="${vehicleList.vehId}"><c:out value="${vehicleList.vehNo}"></c:out> </option>
													 </c:forEach>
									
									</c:when> 
									<c:otherwise>
											<c:forEach items="${vehicleList}" var="vehicleList"> 
														<c:choose> 
																<c:when test="${vehicleList.machineId==editServicing.vehId}">
																	<option value="${vehicleList.machineId}" selected><c:out value="${vehicleList.machineName}"></c:out> </option>
														 		</c:when> 
														</c:choose>
												 </c:forEach>
									 
													<c:forEach items="${vehicleList}" var="vehicleList"> 
														<option value="${vehicleList.machineId}"><c:out value="${vehicleList.machineName}"></c:out> </option>
													 </c:forEach> 
									</c:otherwise>
									</c:choose>
													
									</select>
								</div>
									
									<div class="col-md-2">Service Advice Remainder*</div>
										<div class="col-md-3"><input type="text" id="servAdvRem" name="servAdvRem" value="${editServicing.servAdviseRem}" placeholder="Service Advice Remainder" class="form-control"  required>
									</div>
							
							</div><br>
							
							
							
							
							
							<hr/>
							
							<div class="box-content">
									
									<div class="col-md-2" >Group</div>
									<div class="col-md-3">
										<select   class="form-control chosen" name="groupId" tabindex="-1" id="groupId"  >
											<option   value="">Select Group</option> 
											<c:forEach items="${sprGroupList}" var="sprGroupList"> 
											<option value="${sprGroupList.groupId}"><c:out value="${sprGroupList.groupName}"></c:out> </option>
											 </c:forEach>
											</select>
												 
									</div>
									
									<div class="col-md-2" >Select Spare Part</div>
									<div class="col-md-3">
										<select   class="form-control chosen" name="sprId" tabindex="-1" id="sprId"  >
										 
											 
											</select>
									</div> 
								
									 	 
								</div><br> 
								
								<div class="box-content">
							
									<div class="col-md-2">Spare Qty*</div>
										<div class="col-md-3">
										<input type="text" id="spareQty" name="spareQty" onkeyup="calculateDetail()" placeholder="Spare Qty" class="form-control"   >
									</div>
								 
									
									<div class="col-md-2">Spare Rate*</div>
										<div class="col-md-3"><input type="text" id="spareRate" onkeyup="calculateDetail()" name="spareRate" placeholder="Next Due Km" class="form-control"  readonly>
									</div>
							
							</div><br>
							 
							
							<div class="box-content">
							
									<div class="col-md-2">Disc% </div>
										<div class="col-md-3">
										<input type="text" id="discPer" name="discPer"   placeholder="Discount" class="form-control"  readonly>
									</div>
								 
									
									<div class="col-md-2">Extra Charge% </div>
										<div class="col-md-3"><input type="text" id="extraChargePer"   name="extraChargePer" placeholder="Extra Charges" class="form-control"  readonly>
									</div>
							
							</div><br>
							
							
							<div class="box-content">
							
									<div class="col-md-2">Disc Amt </div>
										<div class="col-md-3">
										<input type="text" id="discDetail" name="discDetail" onkeyup="calculateDetail()" placeholder="Discount" class="form-control"  readonly>
									</div>
								 
									
									<div class="col-md-2">Extra Amt </div>
										<div class="col-md-3"><input type="text" id="extraChargeDetail" onkeyup="calculateDetail()" name="extraChargeDetail" placeholder="Extra Charges" class="form-control"  readonly>
									</div>
							
							</div><br>
							
							<div class="box-content">
							
								<div class="col-md-2">Tax%*</div>
										<div class="col-md-3">
										<input type="text" id="taxPer" name="taxPer" placeholder="Tax Perentage" class="form-control"  readonly>
									</div>
							
									
								 
									
									<div class="col-md-2">Tax Amt*</div>
										<div class="col-md-3"><input type="text" id="taxAmtDetail" onkeyup="calculateDetail()" name="taxAmtDetail" placeholder="Tax Amt" class="form-control"  readonly>
									</div>
							
							</div><br>
							
							<div class="box-content">
							
									<div class="col-md-2">Taxable Amt*</div>
										<div class="col-md-3">
										<input type="text" id="taxaleAmtDetail" name="taxaleAmtDetail" onkeyup="calculateDetail()" placeholder="Taxable Amt" class="form-control"  readonly>
									</div>
									
										<div class="col-md-2">Total*</div>
										<div class="col-md-3">
										<input type="text" id="totalDetail" name="totalDetail" placeholder="Total Amt" class="form-control"  readonly>
									</div> 
								  
							</div><br>
							
							<div class="box-content">
							
								
									
										<div class="col-md-2">Servicing Type*</div>
									<div class="col-md-3">
									
									<select name="servTypeDetail" id="servTypeDetail" class="form-control chosen" tabindex="6"  >
											<option value="">Select Servicing Type</option>
											<option value="1">Regular</option>
											 <option value="2">Break Down</option>  
										</select>
									
									</div>
									
									 
							</div><br><br>
							
							<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="button" class="btn btn-primary" value="Add" onclick="addPart()">  
									</div>
								</div><br><br>
							
							
								<div align="center" id="loader" style="display: none">

							<span>
								<h4>
									<font color="#343690">Loading</font>
								</h4>
							</span> <span class="l-1"></span> <span class="l-2"></span> <span
						class="l-3"></span> <span class="l-4"></span> <span class="l-5"></span>
					<span class="l-6"></span>
				</div>
											
							
							
							<div class=" box-content">
					<div class="row">
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Part Name</th> 
										<th>Group</th>
										<th>Spare Qty</th>
										<th>Spare Rate</th>
										<th>Tax</th>
										<th>Taxable Amt</th>
										<th>Discount</th>
										<th>Extra Charges</th>
										<th>Total</th>
										<th>Action</th>

									</tr>
								</thead>
								<tbody>
								
								<c:forEach items="${servDetail}" var="servDetail"
														varStatus="count">


														<tr>
															<td><c:out value="${count.index+1}" /></td>
															<c:set var="srNo" value="${srNo+1}" />
															<td><c:out value="${servDetail.partName}" /></td> 
															<td><c:out value="${servDetail.groupName}" /></td>
															<td><c:out value="${servDetail.sprQty}" /></td> 
															<td><c:out value="${servDetail.sprRate}" /></td>
															<td><c:out value="${servDetail.sprTaxAmt}" /></td>
															<td><c:out value="${servDetail.sprTaxableAmt}" /></td>
															<td><c:out value="${servDetail.disc}" /></td>
															<td><c:out value="${servDetail.extraCharges}" /></td>
															<td><c:out value="${servDetail.total}" /></td>
															<td align="left"><span class='glyphicon glyphicon-edit' onclick="edit(${count.index})" id="edit${count.index}"></span> 
		                                                    <span class="glyphicon glyphicon-remove" onclick="del(${count.index})" id="del${count.index}"></span>
     														 </td>
												  	 

														</tr>
													</c:forEach>

								</tbody>
							</table>
						</div>
					</div>
								</div>
							<hr>	
								<div class="box-content">
							
									<div class="col-md-2">Service Done Remainder*</div>
									<div class="col-md-3"> 
									<input type="text" id="servDoneRem" name="servDoneRem" value="${editServicing.servDoneRem}" class="form-control" placeholder="Service Done Remainder" required>
									</div>
								 
									
									<div class="col-md-2">Total Spare Part</div>
										<div class="col-md-3"><input type="text" value="${editServicing.sprTot}" id="totPart" name="totPart" placeholder="Total Spare Part" class="form-control"  required>
									</div>
							
							</div><br>
							
							
							<div class="box-content">
							
									<div class="col-md-2">Labour Charge*</div>
									<div class="col-md-3"> 
									<input type="text" id="labCharge" name="labCharge" value="${editServicing.labChrge}" onkeyup="calculateHeader()" placeholder="Labour Charge" class="form-control"  required>
									</div>
								 
									
									<div class="col-md-2">Total Discount</div>
										<div class="col-md-3"><input type="text" id="totDisc" value="${editServicing.totalDisc}" name="totDisc" placeholder="Total Spare Part" class="form-control"   readonly>
									</div>
							
							</div><br> 
							
							<div class="box-content">
							
									<div class="col-md-2">Total Extra Charge</div>
									<div class="col-md-3"> 
									<input type="text" id="totExtraCharge" name="totExtraCharge" value="${editServicing.totalExtra}" placeholder="Total Extra Charge" class="form-control"   readonly>
									</div>
								 
									
									<div class="col-md-2">Tax Amt*</div>
										<div class="col-md-3"><input type="text" id="taxAmt" value="${editServicing.taxAmt}" name="taxAmt" placeholder="Tax Amt" class="form-control"  required>
									</div>
							
							</div><br> 
							
							<div class="box-content">
							
									<div class="col-md-2">Taxable Amt*</div>
										<div class="col-md-3">
										<input type="text" id="taxaleAmt" name="taxaleAmt" value="${editServicing.taxableAmt}" placeholder="Taxable Amt" class="form-control"  required>
									</div>
								 
									
									<div class="col-md-2">Total*</div>
										<div class="col-md-3"><input type="text" id="total" value="${editServicing.total}" name="total" placeholder="Total" class="form-control"  required>
									</div>
								 
									 <input type="hidden" id="totalfirst" name="totalfirst" placeholder="Total" class="form-control"  required>
								 
							
							</div><br>
							<c:choose>
								<c:when test="${editServicing.servType2==1}">
									<div class="box-content">
							
									<div class="col-md-2">Servicing Done Km*</div>
										<div class="col-md-3">
										<input type="text" id="servDoneKm" value="${editServicing.servDoneKm}" name="servDoneKm" placeholder="Servicing Done Km" onchange="updateNextServicingDueKm()" onkeypress="return IsNumeric(event);" ondrop="return false;" onpaste="return false;" class="form-control"  required>
									</div>
								 
									
									<div class="col-md-2">Next Due Km*</div>
										<div class="col-md-3"><input type="text" value="${editServicing.nextDueKm}" id="nextDueKm" name="nextDueKm" placeholder="Next Due Km" class="form-control"  required>
									</div>
							
							</div><br>
								</c:when>
							</c:choose>
							
							<div class="box-content">
							 
									<div class="col-md-2" >Select Pdf</div>
												<div class="col-md-4"> 
													<input type="file" id="file" name="attachFile" value="${editServicing.billFile}" size="60" data-rule-required="true"/> 
													<input type="hidden" id="fileName" name="fileName" value="${editServicing.billFile}" size="60"/> 
												</div>
							
							</div><br>
							 
								
							<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Submit" onclick="check();">
<!-- 										<button type="button" class="btn">Cancel</button>
 -->									</div>
								</div><br><br>
						
							
							

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
		
		var editItem=0;
		function addPart() {

			         
				var sprId = $("#sprId").val();
				var sprName = $("#sprId option:selected").text();
				var groupId = $("#groupId").val();
				var groupName = $("#groupId option:selected").text();
				var vehId = $("#vehId").val();
				var vehName = $("#vehId option:selected").text();
				var spareRate = $("#spareRate").val();
				var spareQty = $("#spareQty").val();
				var taxaleAmtDetail = $("#taxaleAmtDetail").val();
				var taxAmtDetail = $("#taxAmtDetail").val();
				var discDetail = $("#discDetail").val();
				var extraChargeDetail = $("#extraChargeDetail").val();
				var totalDetail = $("#totalDetail").val();
				var servTypeDetail = $("#servTypeDetail").val();
				var dealerId = $("#dealerId").val(); 
				var discPer = $("#discPer").val();
				var extraChargePer = $("#extraChargePer").val(); 
				var taxPer = $("#taxPer").val(); 
				var labourGroupId = $("#labourGroupId").val();
				if(validation()==true){	
				
					if(editItem==0)
						{
						//alert("in if"+editItem);
						$('#loader').show();

						$ .getJSON(
										'${addSparePartInEditInvoice}',

										{
											 
											sprId : sprId,
											sprName : sprName,
											groupId : groupId,
											groupName : groupName,
											vehId : vehId,
											vehName : vehName,
											spareRate : spareRate,
											spareQty : spareQty,
											taxaleAmtDetail : taxaleAmtDetail,
											taxAmtDetail : taxAmtDetail,
											discDetail : discDetail,
											extraChargeDetail : extraChargeDetail,
											totalDetail : totalDetail,
											servTypeDetail : servTypeDetail,
											discPer : discPer,
											extraChargePer : extraChargePer,
											taxPer : taxPer,
											ajax : 'true'

										},
										function(data) {

											$('#table_grid td').remove();
											$('#loader').hide();

											if (data == "") {
												alert("No records found !!");

											}
										 
											
											var taxAmt=0;
											var totSparePart=0;
											var disc=0;
											var extraCharge=0;
											var total=0;
											var labourCharge=0;
		  									var taxaleAmt=0
										  $.each( data, function(key, itemList) {
														
														  if(itemList.delStatus==0)
															{
															  var tr = $('<tr></tr>');
			
															  	tr.append($('<td></td>').html(key+1));
			
															  	tr.append($('<td></td>').html(itemList.partName)); 
															  	tr.append($('<td></td>').html(itemList.groupName));
															  	tr.append($('<td></td>').html(itemList.sprQty));
															  	tr.append($('<td></td>').html(itemList.sprRate));
															  	tr.append($('<td></td>').html(itemList.sprTaxAmt));
															  	taxAmt=taxAmt+itemList.sprTaxAmt;
															  	tr.append($('<td></td>').html(itemList.sprTaxableAmt));
															  	taxaleAmt=taxaleAmt+itemList.sprTaxableAmt;
															  	tr.append($('<td></td>').html(itemList.disc));
															  	disc=disc+itemList.disc;
															  	tr.append($('<td></td>').html(itemList.extraCharges));
															  	extraCharge=extraCharge+itemList.extraCharges;
															  	tr.append($('<td></td>').html(itemList.total));
															  	total=total+itemList.total;
															  	if(itemList.groupId==labourGroupId)
														  		{
														  		 
														  		labourCharge=labourCharge+itemList.total;
														  		}
														  	else
														  		{
														  		 
														  		totSparePart=totSparePart+itemList.total;
														  		}
															  	tr.append($('<td></td>').html('<span class="glyphicon glyphicon-edit" id="edit'+key+'" onclick="edit('+key+');"> </span><span style="visibility: hidden;" class="glyphicon glyphicon-ok" onclick="submit('+key+');" id="ok'+key+'"></span><span class="glyphicon glyphicon-remove"  onclick="del('+key+')" id="del'+key+'"></span>'));
															    $('#table_grid tbody').append(tr);
															} 
														})  
														    
											document.getElementById("total").value=(total).toFixed(2);
											document.getElementById("totDisc").value=(disc).toFixed(2);
											document.getElementById("totExtraCharge").value=(extraCharge).toFixed(2);
											document.getElementById("taxAmt").value=(taxAmt).toFixed(2);
											document.getElementById("taxaleAmt").value=(taxaleAmt).toFixed(2); 
											document.getElementById("totPart").value=(totSparePart).toFixed(2); 
											document.getElementById("totalfirst").value=(total).toFixed(2);
											document.getElementById("labCharge").value=(labourCharge).toFixed(2);
											//null detail
											
											document.getElementById("spareRate").value="";
											document.getElementById("discPer").value="";
											document.getElementById("extraChargePer").value="";
											document.getElementById("taxPer").value="";
											document.getElementById("discDetail").value=""; 
											document.getElementById("extraChargeDetail").value="";
											document.getElementById("taxaleAmtDetail").value="";
											document.getElementById("taxAmtDetail").value="";
											document.getElementById("totalDetail").value="";	
											document.getElementById("spareQty").value=""; 
											document.getElementById("groupId").value="";  
											$('#groupId').trigger("chosen:updated");
											var html = '<option value="">Select Spare Part</option>';
											$('#sprId').html(html);
											$('#sprId').trigger("chosen:updated");
											document.getElementById("servTypeDetail").value="";
											$('#servTypeDetail').trigger("chosen:updated");
										});

						}
					else
						{
						//alert("in else");
						$('#loader').show();

						$ .getJSON(
										'${changeQtyOfSparePartInEditInvoice}',

										{
											 
											sprId : sprId,
											sprName : sprName,
											groupId : groupId,
											groupName : groupName,
											vehId : vehId,
											vehName : vehName,
											spareRate : spareRate,
											spareQty : spareQty,
											taxaleAmtDetail : taxaleAmtDetail,
											taxAmtDetail : taxAmtDetail,
											discDetail : discDetail,
											extraChargeDetail : extraChargeDetail,
											totalDetail : totalDetail,
											servTypeDetail : servTypeDetail, 
											discPer : discPer,
											extraChargePer : extraChargePer,
											taxPer : taxPer,
											ajax : 'true'

										},
										function(data) {

											$('#table_grid td').remove();
											$('#loader').hide();

											if (data == "") {
												alert("No records found !!");

											}
										 
											
											var taxAmt=0;
											var totSparePart=0;
											var disc=0;
											var extraCharge=0;
											var total=0;
											var labourCharge=0;
		  									var taxaleAmt=0
										  $.each( data, function(key, itemList) {
														
														 
														  if(itemList.delStatus==0)
															{
															  var tr = $('<tr></tr>');
			
															  	tr.append($('<td></td>').html(key+1));
			
															  	tr.append($('<td></td>').html(itemList.partName)); 
															  	tr.append($('<td></td>').html(itemList.groupName));
															  	tr.append($('<td></td>').html(itemList.sprQty));
															  	tr.append($('<td></td>').html(itemList.sprRate));
															  	tr.append($('<td></td>').html(itemList.sprTaxAmt));
															  	taxAmt=taxAmt+itemList.sprTaxAmt;
															  	tr.append($('<td></td>').html(itemList.sprTaxableAmt));
															  	taxaleAmt=taxaleAmt+itemList.sprTaxableAmt;
															  	tr.append($('<td></td>').html(itemList.disc));
															  	disc=disc+itemList.disc;
															  	tr.append($('<td></td>').html(itemList.extraCharges));
															  	extraCharge=extraCharge+itemList.extraCharges;
															  	tr.append($('<td></td>').html(itemList.total));
															  	total=total+itemList.total;
															  	if(itemList.groupId==labourGroupId)
														  		{
														  		 
														  		labourCharge=labourCharge+itemList.total;
														  		}
														  	else
														  		{
														  		 
														  		totSparePart=totSparePart+itemList.total;
														  		}
															  	tr.append($('<td></td>').html('<span class="glyphicon glyphicon-edit" id="edit'+key+'" onclick="edit('+key+');"> </span><span style="visibility: hidden;" class="glyphicon glyphicon-ok" onclick="submit('+key+');" id="ok'+key+'"></span><span class="glyphicon glyphicon-remove"  onclick="del('+key+')" id="del'+key+'"></span>'));
															    $('#table_grid tbody').append(tr);
															}
														 
														})  
														    
											document.getElementById("total").value=(total).toFixed(2);
											document.getElementById("totDisc").value=(disc).toFixed(2);
											document.getElementById("totExtraCharge").value=(extraCharge).toFixed(2);
											document.getElementById("taxAmt").value=(taxAmt).toFixed(2);
											document.getElementById("taxaleAmt").value=(taxaleAmt).toFixed(2); 
											document.getElementById("totPart").value=(totSparePart).toFixed(2); 
											document.getElementById("totalfirst").value=(total).toFixed(2);
											document.getElementById("labCharge").value=(labourCharge).toFixed(2);
											editItem=0;
											//null detail
											
											document.getElementById("spareRate").value="";
											document.getElementById("discPer").value="";
											document.getElementById("extraChargePer").value="";
											document.getElementById("taxPer").value="";
											document.getElementById("discDetail").value=""; 
											document.getElementById("extraChargeDetail").value="";
											document.getElementById("taxaleAmtDetail").value="";
											document.getElementById("taxAmtDetail").value="";
											document.getElementById("totalDetail").value="";	
											document.getElementById("spareQty").value=""; 
											document.getElementById("groupId").value="";  
											$('#groupId').trigger("chosen:updated");
											var html = '<option value="">Select Spare Part</option>';
											$('#sprId').html(html);
											$('#sprId').trigger("chosen:updated");
											document.getElementById("servTypeDetail").value="";
											$('#servTypeDetail').trigger("chosen:updated");
										});

						}
				
				
			 
		}
				
				
			
	}
		
		function edit(key)
		{
			//alert(key);
			$('#loader').show();

			$
					.getJSON(
							'${editSparePartInEditInvoice}',

							{
								 
								index : key, 
								ajax : 'true'

							},
							function(data) {

								 
								$('#loader').hide();
 
								document.getElementById("spareRate").value=data.sprRate;
								document.getElementById("discPer").value=data.discPer;
								document.getElementById("extraChargePer").value=data.extraChargesPer;
								document.getElementById("taxPer").value=data.sprTaxAmtPer;
								document.getElementById("discDetail").value=data.disc; 
								document.getElementById("extraChargeDetail").value=data.extraCharges;
								document.getElementById("taxaleAmtDetail").value=data.sprTaxableAmt;
								document.getElementById("taxAmtDetail").value=data.sprTaxAmt;
								document.getElementById("totalDetail").value=data.total;	
								document.getElementById("spareQty").value=data.sprQty; 
								document.getElementById("groupId").value=data.groupId;  
								$('#groupId').trigger("chosen:updated"); 
								document.getElementById("servTypeDetail").value=data.servType;
								$('#servTypeDetail').trigger("chosen:updated");
								 editItem=1;
								//alert("editItem " + editItem);
								$.getJSON('${sparePartByGroupId}', {
									
									groupId : data.groupId,
									ajax : 'true'
								},
										function(data1) {
									 
									var html = '<option value="">Select Part</option>';
									
									var len = data1.length;
									for ( var i = 0; i < len; i++) {
										if(data.sprId==data1[i].sprId)
										{
										 
										html += '<option value="' + data1[i].sprId + '" selected>'
										+ data1[i].sprName + '</option>';
										document.getElementById("sprId").value=data.sprId;
										$('#sprId').trigger("chosen:updated");
										}
									else
										{
										html += '<option value="' + data1[i].sprId + '">'
										+ data1[i].sprName + '</option>';
										}
										
									}
									html += '</option>';
									$('#sprId').html(html);
									$("#sprId").trigger("chosen:updated");
															
										});
							});
		}
		 
		function del(key)
		{
			var labourGroupId = $("#labourGroupId").val();
			var key=key;
			$('#loader').show();
			$
			.getJSON(
					'${deleteSparePartInEditInvoice}',

					{
						 
						index : key,
						ajax : 'true'

					},
					function(data) {
						
						$('#table_grid td').remove(); 
						$('#loader').hide();

						if (data == "") {
							alert("No records found !!");

						}
						var taxAmt=0;
						var totSparePart=0;
						var labourCharge=0;
						var disc=0;
						var extraCharge=0;
						var total=0;
							var taxaleAmt=0;

						 $.each( data, function(key, itemList) {
								
						if(itemList.delStatus==0)
							{
							 var tr = $('<tr></tr>');

							  	tr.append($('<td></td>').html(key+1)); 
							  	tr.append($('<td></td>').html(itemList.partName)); 
							  	tr.append($('<td></td>').html(itemList.groupName));
							  	tr.append($('<td></td>').html(itemList.sprQty));
							  	tr.append($('<td></td>').html(itemList.sprRate));
							  	tr.append($('<td></td>').html(itemList.sprTaxAmt));
							  	taxAmt=taxAmt+itemList.sprTaxAmt;
							  	tr.append($('<td></td>').html(itemList.sprTaxableAmt));
							  	taxaleAmt=taxaleAmt+itemList.sprTaxableAmt;
							  	tr.append($('<td></td>').html(itemList.disc));
							  	disc=disc+itemList.disc;
							  	tr.append($('<td></td>').html(itemList.extraCharges));
							  	extraCharge=extraCharge+itemList.extraCharges;
							  	tr.append($('<td></td>').html(itemList.total));
							  	total=total+itemList.total;
							  	if(itemList.groupId==labourGroupId)
							  		{
							  		 
							  		labourCharge=labourCharge+itemList.total;
							  		}
							  	else
							  		{
							  		 
							  		totSparePart=totSparePart+itemList.total;
							  		}
							  	
							  	tr.append($('<td></td>').html('<span class="glyphicon glyphicon-edit" id="edit'+key+'" onclick="edit('+key+');"> </span><span style="visibility: hidden;" class="glyphicon glyphicon-ok" onclick="submit('+key+');" id="ok'+key+'"></span><span class="glyphicon glyphicon-remove"  onclick="del('+key+')" id="del'+key+'"></span>'));
							    $('#table_grid tbody').append(tr);
							}
							
 
						})  
						    
						document.getElementById("total").value=(total).toFixed(2);
							document.getElementById("totDisc").value=(disc).toFixed(2);
							document.getElementById("totExtraCharge").value=(extraCharge).toFixed(2);
							document.getElementById("taxAmt").value=(taxAmt).toFixed(2);
							document.getElementById("taxaleAmt").value=(taxaleAmt).toFixed(2); 
							document.getElementById("totPart").value=(totSparePart).toFixed(2); 
							document.getElementById("totalfirst").value=(total).toFixed(2);
							document.getElementById("labCharge").value=(labourCharge).toFixed(2);
						
					});
			
			
		}
		
		function updateNextServicingDueKm()
		{
			
			var vehId = $("#vehId").val();
			var typeId = $("#typeId").val();
			var typeIdName = $("#typeId option:selected").text();
			var servDoneKm = parseFloat($("#servDoneKm").val()); 
			$
			.getJSON(
					'${updateNextServicingDueKm}',

					{
						 
						vehId : vehId,
						ajax : 'true'

					},
					function(data) { 
						if(typeId==1)
							document.getElementById("nextDueKm").value=data.freqKm+servDoneKm; 
						else if(typeId==6)
							document.getElementById("nextDueKm").value=data.battaryChangeFreq+servDoneKm; 
						else if(typeId==7)
							document.getElementById("nextDueKm").value=data.acChangeFreq+servDoneKm;
						else 
							document.getElementById("nextDueKm").value=data.wheelChangeFreq+servDoneKm;
						 
						document.getElementById("typeIdName").value=typeIdName;
						
					});
			
			
		}
	</script>
<script type="text/javascript">
function validation()
{
	        
	var groupId = $("#groupId").val();
	var sprId = $("#sprId").val();
	var vehId = $("#vehId").val();
	var typeId = $("#typeId").val();
	var spareRate = $("#spareRate").val();
	var spareQty = $("#spareQty").val();
	var taxaleAmtDetail = $("#taxaleAmtDetail").val();
	var taxAmtDetail = $("#taxAmtDetail").val();
	var discDetail = $("#discDetail").val();
	var extraChargeDetail = $("#extraChargeDetail").val();
	var totalDetail = $("#totalDetail").val();
	var servTypeDetail = $("#servTypeDetail").val(); 
	var dealerId = $("#dealerId").val(); 
	
	var isValid = true;
	 if(isNaN(typeId) || typeId < 0 || typeId=="")
		{
		isValid = false;
		alert("Please Enter Type"); 
		} 
	 else if(isNaN(dealerId) || dealerId < 0 || dealerId=="")
		{
		isValid = false;
		alert("Please Enter Dealer"); 
		} 
	 else if(isNaN(vehId) || vehId < 0 || vehId=="")
		{
		isValid = false;
		alert("Please Enter Vehicle"); 
		} 
	 else if(isNaN(groupId) || groupId < 0 || groupId=="")
		{
		isValid = false;
		alert("Please Enter Group");
		}
		else if(isNaN(sprId) || sprId < 0 || sprId=="")
		{
		isValid = false;
		alert("Please Enter Spare Part");
		}
	else if(isNaN(spareRate) || spareRate < 0 || spareRate=="")
	{
	isValid = false;
	alert("Please Enter Rate"); 
	} 
	else if(isNaN(spareQty) || spareQty < 0 || spareQty=="")
	{
	isValid = false;
	alert("Please Enter Quantity");
	
	} 
	else if(isNaN(taxaleAmtDetail) || taxaleAmtDetail < 0 || taxaleAmtDetail=="")
	{
	isValid = false;
	alert("Please Enter Taxable Amt"); 
	}
	else if(isNaN(taxAmtDetail) || taxAmtDetail < 0 || taxAmtDetail=="")
	{
	isValid = false;
	alert("Please Enter Tax Amt"); 
	}
	else if(isNaN(discDetail) || discDetail < 0 || discDetail=="")
	{
	isValid = false;
	alert("Please Enter Discount");
	}
	else if(isNaN(extraChargeDetail) || extraChargeDetail < 0 || extraChargeDetail=="")
	{
	isValid = false;
	alert("Please Enter Extra Charges");
	}
	else if(isNaN(totalDetail) || totalDetail < 0 || totalDetail=="")
	{
	isValid = false;
	alert("Please Enter Total");
	}
	else if(isNaN(servTypeDetail) || servTypeDetail < 0 || servTypeDetail=="")
	{
	isValid = false;
	alert("Please Enter Service Type");
	} 
	
return isValid;
	
}

$(document).ready(function() { 
	$('#type').change(
			function() {
				//alert("typeId"+$(this).val());
				var type=$(this).val();
			    
				$.getJSON('${getTypeList}', {
					
					type : $(this).val(),
					ajax : 'true'
				},
						function(data) {
					 
					var html = '<option value="">Select Type</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].typeId + '">'
								+ data[i].typeName + '</option>';
					}
					html += '</option>';
					$('#typeId').html(html);
					$("#typeId").trigger("chosen:updated"); 
					 		
						});
			 
				 
		})
		 		 
});

$(document).ready(function() { 
	$('#type').change(
			function() {
				//alert("typeId"+$(this).val());
				var typeId=$(this).val();
			    
				$.getJSON('${machineListOrVehicleList}', {
					
					typeId : $(this).val(),
					ajax : 'true'
				},
						function(data) {
					 
					var html = '<option value="">Select Vehicle Or Machine</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].mechId + '">'
								+ data[i].mechName + '</option>';
					}
					html += '</option>';
					$('#vehId').html(html);
					$("#vehId").trigger("chosen:updated"); 
					
					 
					
						});
			 
				 
		})
		 		 
});

$(document).ready(function() { 
	$('#typeId').change(
			function() {
				//alert("typeId"+$(this).val());
				var typeId=$(this).val();
			    
				$.getJSON('${groupByTypeId}', {
					
					typeId : $(this).val(),
					ajax : 'true'
				},
						function(data) {
					 
					var html = '<option value="">Select Group</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].groupId + '">'
								+ data[i].groupName + '</option>';
					}
					html += '</option>';
					$('#groupId').html(html);
					$("#groupId").trigger("chosen:updated");
											
						});
			 
				 
		})
		 		 
});

$(document).ready(function() { 
	$('#groupId').change(
			function() {
				//alert("makeId"+$(this).val());
				var groupId=$(this).val();
			    
				$.getJSON('${sparePartByGroupId}', {
					
					groupId : $(this).val(),
					ajax : 'true'
				},
						function(data) {
					 
					var html = '<option value="">Select Part</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].sprId + '">'
								+ data[i].sprName + '</option>';
					}
					html += '</option>';
					$('#sprId').html(html);
					$("#sprId").trigger("chosen:updated");
											
						});
			 
				 
		})
		 		 
});

$(document).ready(function() { 
	$('#sprId').change(
			function() {
				var dealerId = $("#dealerId").val();
				var sprId=$(this).val();
			if(dealerId=="")
				{
				alert("Select Dealer");
				}
			 
			else
				{
				$.getJSON('${partDetailById}', {
					
					sprId : $(this).val(),
					dealerId : dealerId,
					ajax : 'true'
				},
						function(data) {
					
					document.getElementById("spareRate").value=data.sprRate1;
					document.getElementById("discPer").value=data.disc;
					document.getElementById("extraChargePer").value=data.extraCharges;
					document.getElementById("taxPer").value=data.cgst+data.igst+data.sgst; 
					document.getElementById("taxaleAmtDetail").value=0;
					document.getElementById("taxAmtDetail").value=0;
					document.getElementById("totalDetail").value="";	
					document.getElementById("spareQty").value="";
						});
				}
				 
		})
		 		 
});

$(document).ready(function() { 
	$('#dealerId').change(
			function() {
				document.getElementById("sprId").value="";  
				$('#sprId').trigger("chosen:updated");
				 
		})
		 		 
});

function calculateDetail()
{
	
	var spareQty = parseFloat($("#spareQty").val()); 
	var spareRate = parseFloat($("#spareRate").val());  
	var taxPer = parseFloat($("#taxPer").val());
	var discPer = parseFloat($("#discPer").val());
	var extraChargePer = parseFloat($("#extraChargePer").val()); 
	
	var discDetail=((spareQty*spareRate)*discPer)/100;
	var extraChargeDetail=((spareQty*spareRate)*extraChargePer)/100; 
	var taxaleAmtDetail=(spareQty*spareRate)-discDetail+extraChargeDetail;
	var taxAmtDetail=(taxaleAmtDetail*taxPer)/100;
	var totalDetail=(spareQty*spareRate)-discDetail+extraChargeDetail+taxAmtDetail;
	document.getElementById("taxAmtDetail").value=(taxAmtDetail).toFixed(2);
	document.getElementById("discDetail").value=(discDetail).toFixed(2);
	document.getElementById("extraChargeDetail").value=(extraChargeDetail).toFixed(2);
	document.getElementById("totalDetail").value=(totalDetail).toFixed(2); 
	document.getElementById("taxaleAmtDetail").value=(taxaleAmtDetail).toFixed(2); 
}

/* function calculateHeader()
{
	 
	
	var labCharge = parseFloat($("#labCharge").val());  
	var totalfirst = parseFloat($("#totalfirst").val());  
	document.getElementById("total").value=totalfirst+labCharge; 
} */

var specialKeys = new Array();
specialKeys.push(8); //Backspace
function IsNumeric(e) {
    var keyCode = e.which ? e.which : e.keyCode
    var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1 || keyCode==9);
   // document.getElementById("error").style.display = ret ? "none" : "inline";
    return ret;
}

function check()
{
	 
	var servType = $("#servType").val();
	var dealerId = $("#dealerId").val();
	var file = $("#file").val();
	if(servType=="")
		{
		alert("Select Service Type");
		}
	else if(dealerId=="")
	{
	alert("Select Dealer");
	}
	 
}

  
			 
</script>
	
								
							
	
</body>
</html>