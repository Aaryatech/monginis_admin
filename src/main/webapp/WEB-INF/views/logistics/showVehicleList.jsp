<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
 <c:url var="typeByMakeId" value="/typeByMakeId"></c:url>
 <c:url var="variantByTypeId" value="/variantByTypeId"></c:url>
 <c:url var="dealerByMakeId" value="/dealerByMakeId"></c:url>
 <c:url var="editVehicle" value="/editVehicle"></c:url>
 
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
						<i class="fa fa-file-o"></i>Logistics
					</h1>
				</div>
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i>Insert New Vehicle
							</h3>
							
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showVehicleList">Vehicle List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						
						
						<div class="box-content">

							<form id="submitMaterialStore" action="${pageContext.request.contextPath}/insertVehicle" method="post"
							enctype="multipart/form-data">
							<input type="hidden" id="vehId" name="vehId"   class="form-control"   >
							<div class="box-content">
								<div class="col-md-2">Vehicle Name* </div>
								<div class="col-md-3">
								<input type="text" id="vehName" name="vehName" placeholder="Vehicle Name"  class="form-control"  required>
								
								</div>
								
							<div class="col-md-2">Company Name* </div> 
								<div class="col-md-3">
							 
                                    <select name="makeId" id="makeId" class="form-control chosen" tabindex="6" required>
											<option value="">Select Company</option>
											<c:forEach items="${makeList}" var="makeList"> 
												<option value="${makeList.makeId}"><c:out value="${makeList.makeName}"></c:out> </option>
											 </c:forEach>
										</select>
								</div>
					
							</div><br>
							
							<div class="box-content">
							
								<div class="col-md-2">Type* </div> 
								<div class="col-md-3">
							 
                                    <select name="typeId" id="typeId" class="form-control chosen"  tabindex="6" required>
											 
										</select>
								</div>
								
								<div class="col-md-2">Variant* </div> 
								<div class="col-md-3">
							 
                                    <select name="variantId" id="variantId" class="form-control chosen" tabindex="6" required>
											 
										</select>
								</div>
								
				 
							</div><br>
							
							<div class="box-content">
							
							<div class="col-md-2">Dealer* </div> 
								<div class="col-md-3">
							 
                                    <select name="dealerId" id="dealerId" class="form-control chosen" tabindex="6" required>
											 
										</select>
								</div>
								
								<div class="col-md-2">Fuel Type* </div> 
								<div class="col-md-3">
							 
                                    <select name="fuelType" id="fuelType" class="form-control chosen" tabindex="6" required>
                                    <option value="">Select Fuel Type</option>
                                    <option value="1">Diesel</option>
                                    <option value="2">Petrol</option>
                                    <option value="3">Gas</option>
											 
										</select>
								</div>
								
							</div><br>
							
							<div class="box-content">
							
							<div class="col-md-2">Engine No*</div>
									<div class="col-md-3">
									<input class="form-control" id="engNo" placeholder="Engine No" size="16"
											type="text" name="engNo"  required/>
									</div>
									
									<div class="col-md-2">Chassis No*</div>
									<div class="col-md-3">
									<input class="form-control" id="chsNo" placeholder="Chassis No" size="16"
											type="text" name="chsNo" required />
									</div>
								
							</div><br>
							
							<div class="box-content">
							
									<div class="col-md-2">Color Of Vehicle* </div>
									<div class="col-md-3">
									<input class="form-control" id="color" placeholder="Color Of Vehicle" size="16"
											type="text" name="color"  required />
									</div>
									
									<div class="col-md-2">Company Avg* </div>
									<div class="col-md-3">
									<input class="form-control" id="cmpnyAvg" placeholder="Company Avg" size="16"
											type="text" name="cmpnyAvg"  required />
									</div>
							
							</div><br> 
							<div class="box-content">
							
									<div class="col-md-2">Stand Avg* </div>
									<div class="col-md-3">
									<input class="form-control" id="standAvg" placeholder="Stand Avg" size="16"
											type="text" name="standAvg"  required />
									</div>
									
									<div class="col-md-2">Mini Avg* </div>
									<div class="col-md-3">
									<input class="form-control" id="miniAvg" placeholder="Mini Avg" size="16"
											type="text" name="miniAvg"  required />
									</div>
							
							</div><br> 
							
							<div class="box-content">
							
									<div class="col-md-2">Purchase Date* </div>
									<div class="col-md-3">
									<input class="form-control date-picker" id="purDate" placeholder="Purchase Date" size="16"
											type="text" name="purDate"  required />
									</div>
						
									<div class="col-md-2">Registration Date* </div>
									<div class="col-md-3">
									<input class="form-control date-picker" id="regDate" placeholder="Registration Date" size="16"
											type="text" name="regDate"  required />
									</div>
							
							</div><br>
							
							<div class="box-content">
							
									<div class="col-md-2">Servicing Frequency(Km)* </div>
									<div class="col-md-3">
									<input class="form-control" id="frqKm" placeholder="Servicing Frequency" size="16"
											type="text" name="frqKm"  required />
									</div>
						
									<div class="col-md-2">Wheel Change Frequency(Km)* </div>
									<div class="col-md-3">
									<input class="form-control" id="wheelChange" placeholder="Wheel Change Frequency" size="16"
											type="text" name="wheelChange"  required />
									</div>
							
							</div><br>
							
							<div class="box-content">
							
									<div class="col-md-2">Battery Change Frequency(Days)* </div>
									<div class="col-md-3">
									<input class="form-control" id="batryChange" placeholder="Battery Change Frequency" size="16"
											type="text" name="batryChange"  required />
									</div>
						
									<div class="col-md-2">Ac Change Frequency(Days)* </div>
									<div class="col-md-3">
									<input class="form-control" id="acChang" placeholder="Ac Change Frequency" size="16"
											type="text" name="acChang"  required />
									</div>
							
							</div><br>
							<div class="box-content">
							
									<div class="col-md-2">Current Running Km* </div>
									<div class="col-md-3">
									<input class="form-control" id="currentRunningKm" placeholder="Current Running Km" size="16"
											type="text" name="currentRunningKm"  required />
									</div>
									
									<div class="col-md-2">Last Servicing Km* </div>
									<div class="col-md-3">
									<input class="form-control" id="lastServicingKm" placeholder="Last Servicing Km" size="16"
											type="text" name="lastServicingKm"  required />
									</div>
							
							</div><br> 
							
							<div class="box-content">
							
									<div class="col-md-2">Next Servicing Km* </div>
									<div class="col-md-3">
									<input class="form-control" id="nextServicingKm" placeholder="Next Servicing Km" size="16"
											type="text" name="nextServicingKm"  required />
									</div>
									
									<div class="col-md-2">Alert Next Servicing Km* </div>
									<div class="col-md-3">
									<input class="form-control" id="alertNextServicingKm" placeholder="Alert Next Servicing Km" size="16"
											type="text" name="alertNextServicingKm"  required />
									</div>
							
							</div><br>
							<div class="box-content">
							
									<div class="col-md-2">Last AMC Date* </div>
									<div class="col-md-3">
									<input class="form-control date-picker" id="lastAmcDate" placeholder="Last AMC Date" size="16"
											type="text" name="lastAmcDate"  required />
									</div>
						
									<div class="col-md-2">Next AMC Date* </div>
									<div class="col-md-3">
									<input class="form-control date-picker" id="nextAmcDate" placeholder="Next AMC Date" size="16"
											type="text" name="nextAmcDate"  required />
									</div>
							
							</div><br>
							<div class="box-content">
							
									<div class="col-md-2">Alert AMC Date* </div>
									<div class="col-md-3">
									<input class="form-control date-picker" id="alertAmcDate" placeholder="Alert AMC Date" size="16"
											type="text" name="alertAmcDate"  required />
									</div>
						 
							
							</div><br><br>
							
							
								
							<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Add New Vehicle" onclick="validation()" >
										<input type="button" class="btn btn-primary" value="Cancel" id="cancel" onclick="cancel1()" disabled>
<!-- 										<button type="button" class="btn">Cancel</button>
 -->									</div>
								</div><br><br>
						
							
							

						</form>
						</div>	
						</div>
						
						<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i>Vehicle List
							</h3>
							
							<div class="box-tool">
								 <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						
						
						<div class="box-content">

							 
							<div class=" box-content">
					<div class="row">
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>vehicle No.</th> 
										<th>Company Name</th>
										<th>Vehicle Type</th>
										<th>Variant</th>
										<th>Dealer</th>
										<th>Fuel Type</th>
										<th>Engine No.</th>
										<th>Chassi No.</th>
										<th>Color</th>
										<th>Company Avg</th>
										<th>Stand Avg</th>
										<th>Mini Avg</th>
										<th>Purchase Date</th>
										<th>Registration Date</th>
										<th>Action</th>

									</tr>
								</thead>
								<tbody>
								<c:set var = "srNo" value="0"/>
									<c:forEach items="${vehicleList}" var="vehicleList"
													varStatus="count">
													
													 
													<tr>
														<td ><c:out value="${count.index+1}" /></td>
 														<c:set var = "srNo" value="${count.index}"/> 
														<td align="left" ><c:out value="${vehicleList.vehNo}" /></td> 
																
																<c:forEach items="${makeList}" var="makeList">
																<c:choose>
																<c:when test="${makeList.makeId==vehicleList.makeId}">
																<td align="left" ><c:out value="${makeList.makeName}" /></td> 
																</c:when> 
																</c:choose> 
																</c:forEach>
																
																<c:forEach items="${typeList}" var="typeList">
																<c:choose>
																<c:when test="${typeList.vehiTypeId==vehicleList.vehTypeId}">
																<td align="left" ><c:out value="${typeList.vehTypeName}" /></td> 
																</c:when> 
																</c:choose> 
																</c:forEach>
														  
																<c:forEach items="${variantList}" var="variantList">
																<c:choose>
																<c:when test="${variantList.variantId==vehicleList.variantId}">
																<td align="left" ><c:out value="${variantList.variantName}" /></td> 
																</c:when> 
																</c:choose> 
																</c:forEach>
																
																<c:forEach items="${dealerList}" var="dealerList">
																<c:choose>
																<c:when test="${dealerList.dealerId==vehicleList.dealerId}">
																<td align="left" ><c:out value="${dealerList.dealerName}" /></td> 
																</c:when> 
																</c:choose> 
																</c:forEach>
															<c:choose>
																<c:when test="${vehicleList.fuelType==1}">
																<c:set var="fuelType" value="Diesel"></c:set> 
																</c:when>
																<c:when test="${vehicleList.fuelType==2}">
																<c:set var="fuelType" value="Petrol"></c:set> 
																</c:when>
																<c:when test="${vehicleList.fuelType==3}">
																<c:set var="fuelType" value="Gas"></c:set> 
																</c:when>
															</c:choose>
														  
														 <td align="left" ><c:out value="${fuelType}" /></td> 
														 <td align="left" ><c:out value="${vehicleList.vehEngNo}" /></td> 
														 <td align="left" ><c:out value="${vehicleList.vehChesiNo}" /></td> 
														 <td align="left" ><c:out value="${vehicleList.vehColor}" /></td> 
														 <td align="left" ><c:out value="${vehicleList.vehCompAvg}" /></td> 
														 <td align="left" ><c:out value="${vehicleList.vehStandAvg}" /></td> 
														 <td align="left" ><c:out value="${vehicleList.vehMiniAvg}" /></td> 
														 <td align="left" ><c:out value="${vehicleList.purchaseDate}" /></td> 
														 <td align="left" ><c:out value="${vehicleList.regDate}" /></td>
														
													<td> <span class="glyphicon glyphicon-edit"  onclick="edit(${vehicleList.vehId})"> </span> 
						<a href="${pageContext.request.contextPath}/deleteVehicle/${vehicleList.vehId}" onClick="return confirm('Are you sure want to delete this record');"   >
						<span class="glyphicon glyphicon-remove" > </span></a>
						
						</td>
												</tr>
												</c:forEach>

								</tbody>
							</table>
						</div>
					</div>
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
		
		
		function edit(vehId) {

	         
			 
			//alert("driverId"+driverId);
			$('#loader').show();

			$
					.getJSON(
							'${editVehicle}',

							{
								 
								vehId : vehId, 
								ajax : 'true'

							},
							function(data) { 
								           			
								document.getElementById("vehId").value=data.vehId;
								document.getElementById("vehName").value=data.vehNo; 
								document.getElementById("makeId").value=data.makeId;
								$('#makeId').trigger("chosen:updated");
								document.getElementById("engNo").value=data.vehEngNo;
								document.getElementById("chsNo").value=data.vehChesiNo;
								document.getElementById("color").value=data.vehColor;
								document.getElementById("purDate").value=data.purchaseDate;
								document.getElementById("regDate").value=data.regDate;
								document.getElementById("cmpnyAvg").value=data.vehCompAvg;
								document.getElementById("standAvg").value=data.vehStandAvg;
								document.getElementById("miniAvg").value=data.vehMiniAvg;
								document.getElementById("fuelType").value=data.fuelType;
								$('#fuelType').trigger("chosen:updated");
								document.getElementById("frqKm").value=data.freqKm;
								document.getElementById("wheelChange").value=data.wheelChangeFreq;
								document.getElementById("batryChange").value=data.battaryChangeFreq;
								document.getElementById("acChang").value=data.acChangeFreq;
								document.getElementById("currentRunningKm").value=data.currentRunningKm;
								document.getElementById("lastServicingKm").value=data.lastServicingKm;
								document.getElementById("nextServicingKm").value=data.nextServicingKm;
								document.getElementById("alertNextServicingKm").value=data.alertNextServicingKm;
								document.getElementById("lastAmcDate").value=data.lastAmcDate;
								document.getElementById("nextAmcDate").value=data.nextAmcDate;
								document.getElementById("alertAmcDate").value=data.alertAmcDate;
								
								   
								$.getJSON('${typeByMakeId}', {
									
									makeId : data.makeId,
									ajax : 'true'
								},
										function(data1) {
									 
									var html = '<option value="">Select Type</option>';
									
									var len = data1.length;
									for ( var i = 0; i < len; i++) {
										
										if(data.vehTypeId==data1[i].vehiTypeId)
											{
											 
											html += '<option value="' + data1[i].vehiTypeId + '" selected>'
											+ data1[i].vehTypeName + '</option>';
											document.getElementById("typeId").value=data.vehTypeId;
											$('#typeId').trigger("chosen:updated");
											}
										else
											{
											html += '<option value="' + data1[i].vehiTypeId + '">'
											+ data1[i].vehTypeName + '</option>';
											}
										
									}
									html += '</option>';
									$('#typeId').html(html);
									$("#typeId").trigger("chosen:updated");
															
										});
								
								$.getJSON('${dealerByMakeId}', {
									
									typeId : data.vehTypeId,
									makeId : data.makeId,
									ajax : 'true'
								},
										function(data2) {
									 
									var html = '<option value="">Select Type</option>';
									
									var len = data2.length;
									for ( var i = 0; i < len; i++) {
										if(data.dealerId==data2[i].dealerId)
										{
										 
										html += '<option value="' + data2[i].dealerId + '" selected>'
										+ data2[i].dealerName + '</option>';
										document.getElementById("dealerId").value=data.dealerId;
										$('#dealerId').trigger("chosen:updated");
										}
										else
										{
											html += '<option value="' + data2[i].dealerId + '">'
											+ data2[i].dealerName + '</option>';
										}
										
									}
									html += '</option>';
									$('#dealerId').html(html);
									$("#dealerId").trigger("chosen:updated");
															
										});
								
								$.getJSON('${variantByTypeId}', {
									
									typeId : data.vehTypeId,
									makeId : data.makeId,
									ajax : 'true'
								},
										function(data3) {
									 
									var html = '<option value="">Select Type</option>';
									
									var len = data3.length;
									for ( var i = 0; i < len; i++) {
										if(data.variantId==data3[i].variantId)
											{
											 
											html += '<option value="' + data3[i].variantId + '" selected>'
											+ data3[i].variantName + '</option>';
											document.getElementById("variantId").value=data.dealerId;
											$('#variantId').trigger("chosen:updated");
											}
										else
											{
											html += '<option value="' + data3[i].variantId + '">'
											+ data3[i].variantName + '</option>';
											}
										
									}
									html += '</option>';
									$('#variantId').html(html);
									$("#variantId").trigger("chosen:updated");
															
										});
								 
								document.getElementById("cancel").disabled=false;
							});

		 
			 
		
}
	function validation() {

         //alert("hi");
		 var makeId = $("#makeId").val();
		 var typeId = $("#typeId").val();
		 var variantId = $("#variantId").val();
		 var dealerId = $("#dealerId").val();
		 var fuelType = $("#fuelType").val();
		 if(makeId=="")
			 {
			 alert("Select Company ");
			 }
		 else if(typeId=="")
			 {
			 alert("Select Type ");
			 }
		 else if(variantId=="")
			 {
			 alert("Select Variant ");
			 }
		 else if(dealerId=="")
		 {
		 alert("Select Dealer ");
		 }
		 else if(fuelType=="")
		 {
		 alert("Select Fuel Type ");
		 }
		 
	
}
	
	function cancel1() {

         //alert("cancel");
         document.getElementById("cancel").disabled=true; 
		document.getElementById("vehId").value="";
								document.getElementById("vehName").value=""; 
								document.getElementById("makeId").value="";
								$('#makeId').trigger("chosen:updated");
								document.getElementById("engNo").value="";
								document.getElementById("chsNo").value="";
								document.getElementById("color").value="";
								document.getElementById("purDate").value="";
								document.getElementById("regDate").value="";
								document.getElementById("cmpnyAvg").value="";
								document.getElementById("standAvg").value="";
								document.getElementById("miniAvg").value="";
								document.getElementById("fuelType").value="";
								$('#fuelType').trigger("chosen:updated");
								document.getElementById("frqKm").value="";
								document.getElementById("wheelChange").value="";
								document.getElementById("batryChange").value="";
								document.getElementById("acChang").value="";
								document.getElementById("currentRunningKm").value="";
								document.getElementById("lastServicingKm").value="";
								document.getElementById("nextServicingKm").value="";
								document.getElementById("alertNextServicingKm").value="";
								document.getElementById("lastAmcDate").value="";
								document.getElementById("nextAmcDate").value="";
								document.getElementById("alertAmcDate").value="";
		var html = '<option value="">Select Type</option>';
		$('#typeId').html(html);
		$('#typeId').trigger("chosen:updated");
		var html = '<option value="">Select Variant</option>';
		$('#variantId').html(html);
		$('#variantId').trigger("chosen:updated");
		var html = '<option value="">Select Dealer</option>';
		$('#dealerId').html(html);
		$('#dealerId').trigger("chosen:updated");
	
}
		
		$(document).ready(function() { 
			$('#makeId').change(
					function() {
						//alert("makeId"+$(this).val());
						var makeId=$(this).val();
					    
						$.getJSON('${typeByMakeId}', {
							
							makeId : $(this).val(),
							ajax : 'true'
						},
								function(data) {
							 
							var html = '<option value="">Select Type</option>';
							
							var len = data.length;
							for ( var i = 0; i < len; i++) {
								html += '<option value="' + data[i].vehiTypeId + '">'
										+ data[i].vehTypeName + '</option>';
							}
							html += '</option>';
							$('#typeId').html(html);
							$("#typeId").trigger("chosen:updated");
													
								});
					 
						 
				})
				 		 
		});
		
		$(document).ready(function() { 
			$('#typeId').change(
					function() {
						//alert("makeId"+$(this).val());
						var typeId=$(this).val();
						var makeId = $("#makeId").val();
					    
						$.getJSON('${variantByTypeId}', {
							
							typeId : $(this).val(),
							makeId : makeId,
							ajax : 'true'
						},
								function(data) {
							 
							var html = '<option value="">Select Variant</option>';
							
							var len = data.length;
							for ( var i = 0; i < len; i++) {
								html += '<option value="' + data[i].variantId + '">'
										+ data[i].variantName + '</option>';
							}
							html += '</option>';
							$('#variantId').html(html);
							$("#variantId").trigger("chosen:updated");
													
								});
					 
						 
				})
				 		 
		});
		
		$(document).ready(function() { 
			$('#makeId').change(
					function() {
						//alert("makeId"+$(this).val());
						var typeId=$(this).val();
						var makeId = $("#makeId").val();
					    
						$.getJSON('${dealerByMakeId}', {
							
							typeId : $(this).val(),
							makeId : makeId,
							ajax : 'true'
						},
								function(data) {
							 
							var html = '<option value="">Select Dealer</option>';
							
							var len = data.length;
							for ( var i = 0; i < len; i++) {
								html += '<option value="' + data[i].dealerId + '">'
										+ data[i].dealerName + '</option>';
							}
							html += '</option>';
							$('#dealerId').html(html);
							$("#dealerId").trigger("chosen:updated");
													
								});
					 
						 
				})
				 		 
		});
		 
		 
	</script>
 
</body>
</html>