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
 <c:url var="editMachine" value="/editMachine"></c:url>
 
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
								<i class="fa fa-table"></i>Insert New Machine
							</h3>
							
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showMachineList">Machine List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						
						
						<div class="box-content">

							<form id="submitMaterialStore" action="${pageContext.request.contextPath}/insertMachine" method="post"
							enctype="multipart/form-data">
							<input type="hidden" id="machineId" name="machineId"   class="form-control"   >
							<div class="box-content">
								<div class="col-md-2">Machine Name* </div>
								<div class="col-md-3">
								<input type="text" id="machineName" name="machineName" placeholder="Machine Name"  class="form-control"  required>
								
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
								
								<div class="col-md-2">Machine No*</div>
									<div class="col-md-3">
									<input class="form-control" id="machineNo" placeholder="Machine No" size="16"
											type="text" name="machineNo"  required/>
									</div>
								 	
							</div><br>
							
							<div class="box-content">
							
									<div class="col-md-2">Color Of Machine* </div>
									<div class="col-md-3">
									<input class="form-control" id="color" placeholder="Color Of Vehicle" size="16"
											type="text" name="color"  required />
									</div>
									
									<div class="col-md-2">Variable part*</div>
									<div class="col-md-3">
									<input class="form-control" id="variablePart" placeholder="Variable part" size="16"
											type="text" name="variablePart" required />
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
							
									<div class="col-md-2">Last Cleaning Date* </div>
									<div class="col-md-3">
									<input class="form-control date-picker" id="lastCleanDate" placeholder="Last Cleaning Date" size="16"
											type="text" name="lastCleanDate"  required />
									</div>
						
									<div class="col-md-2">Next Cleaning Date* </div>
									<div class="col-md-3">
									<input class="form-control" id="nextCleanDate" placeholder="Next Cleaning Date" size="16"
											type="text" name="nextCleanDate"  readonly />
									</div>
							
							</div><br>
							
							<div class="box-content">
							
									<div class="col-md-2">Cleaning Frequency(Days)* </div>
									<div class="col-md-3">
									<input class="form-control" id="cleaningFrq" onchange="nextCleaning();" placeholder="Cleaning Frequency" size="16"
											type="text" name="cleaningFrq"  required />
									</div>
						
									<div class="col-md-2">Alert Frequency(Days)* </div>
									<div class="col-md-3">
									<input class="form-control" id="alertFrq" placeholder="Alert Frequency" size="16"
											type="text" name="alertFrq" onchange="alertDate();" required />
									</div>
							
							</div><br>
							
							
							
							<div class="box-content">
							
									<div class="col-md-2">Next Alert Date* </div>
									<div class="col-md-3">
									<input class="form-control" id="nextAlertDate" placeholder="Next Alert Date" size="16"
											type="text" name="nextAlertDate"  readonly />
									</div>
									
									<div class="col-md-2">Last AMC Date* </div>
									<div class="col-md-3">
									<input class="form-control date-picker" id="lastAmcDate" placeholder="Last AMC Date" size="16"
											type="text" name="lastAmcDate"  required />
									</div>
						 
							
							</div><br>
							<div class="box-content">
							
									
						
									<div class="col-md-2">Next AMC Date* </div>
									<div class="col-md-3">
									<input class="form-control date-picker" id="nextAmcDate" placeholder="Next AMC Date" size="16"
											type="text" name="nextAmcDate"  required />
									</div>
									<div class="col-md-2">Alert AMC Date* </div>
									<div class="col-md-3">
									<input class="form-control date-picker" id="alertAmcDate" placeholder="Alert AMC Date" size="16"
											type="text" name="alertAmcDate"  required />
									</div>
							
							</div><br>
							  
								
							<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Add New Machine" onclick="validation()" >
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
								<i class="fa fa-table"></i>Machine List
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
										<th>Machine Name.</th> 
										<th>Company Name</th>
										<th>Type</th>
										<th>Variant</th>
										<th>Dealer</th> 
										<th>Machine No.</th> 
										<th>Color</th>
										<th>Variable Part</th> 
										<th>Purchase Date</th>
										<th>Registration Date</th>
										<th>Last Cleaning Date</th>
										<th>Next Cleaning Date</th>
										<th>Action</th>

									</tr>
								</thead>
								<tbody>
								<c:set var = "srNo" value="0"/>
									<c:forEach items="${machineList}" var="machineList"
													varStatus="count">
													
													 
													<tr>
														<td ><c:out value="${count.index+1}" /></td>
 														<c:set var = "srNo" value="${count.index}"/> 
														<td align="left" ><c:out value="${machineList.machineName}" /></td> 
																
																<c:forEach items="${makeList}" var="makeList">
																<c:choose>
																<c:when test="${makeList.makeId==machineList.makeId}">
																<td align="left" ><c:out value="${makeList.makeName}" /></td> 
																</c:when> 
																</c:choose> 
																</c:forEach>
																
																<c:forEach items="${typeList}" var="typeList">
																<c:choose>
																<c:when test="${typeList.vehiTypeId==machineList.typeId}">
																<td align="left" ><c:out value="${typeList.vehTypeName}" /></td> 
																</c:when> 
																</c:choose> 
																</c:forEach>
														  
																<c:forEach items="${variantList}" var="variantList">
																<c:choose>
																<c:when test="${variantList.variantId==machineList.variantId}">
																<td align="left" ><c:out value="${variantList.variantName}" /></td> 
																</c:when> 
																</c:choose> 
																</c:forEach>
																
																<c:forEach items="${dealerList}" var="dealerList">
																<c:choose>
																<c:when test="${dealerList.dealerId==machineList.dealerId}">
																<td align="left" ><c:out value="${dealerList.dealerName}" /></td> 
																</c:when> 
																</c:choose> 
																</c:forEach>
															   
														 <td align="left" ><c:out value="${machineList.machineNo}" /></td> 
														 <td align="left" ><c:out value="${machineList.color}" /></td> 
														 <td align="left" ><c:out value="${machineList.variablePart}" /></td> 
														 <td align="left" ><c:out value="${machineList.purchaseDate}" /></td> 
														 <td align="left" ><c:out value="${machineList.registrationDate}" /></td> 
														 <td align="left" ><c:out value="${machineList.lastCleaningDate}" /></td> 
														 <td align="left" ><c:out value="${machineList.nextCleaningDate}" /></td>  
														
													<td> <span class="glyphicon glyphicon-edit"  onclick="edit(${machineList.machineId})"> </span> 
						<a href="${pageContext.request.contextPath}/deleteMachine/${machineList.machineId}" onClick="return confirm('Are you sure want to delete this record');"   >
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
		
		
		function edit(machineId) {

	         
			 
			//alert("driverId"+driverId);
			$('#loader').show();

			$
					.getJSON(
							'${editMachine}',

							{
								 
								machineId : machineId, 
								ajax : 'true'

							},
							function(data) { 
								           			
								document.getElementById("machineId").value=data.machineId;
								document.getElementById("machineName").value=data.machineName; 
								document.getElementById("makeId").value=data.makeId;
								$('#makeId').trigger("chosen:updated");
								document.getElementById("machineNo").value=data.machineNo; 
								document.getElementById("color").value=data.color;
								document.getElementById("variablePart").value=data.variablePart;
								document.getElementById("regDate").value=data.registrationDate;
								document.getElementById("purDate").value=data.purchaseDate;
								document.getElementById("cleaningFrq").value=data.cleaningFrq;
								document.getElementById("alertFrq").value=data.alertFreq; 
								document.getElementById("lastCleanDate").value=data.lastCleaningDate;
								document.getElementById("nextCleanDate").value=data.nextCleaningDate;
								document.getElementById("nextAlertDate").value=data.nextAlertDate; 
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
										
										if(data.typeId==data1[i].vehiTypeId)
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
									
									typeId : data.typeId,
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
									
									typeId : data.typeId,
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
		document.getElementById("machineId").value="";
								document.getElementById("machineName").value=""; 
								document.getElementById("makeId").value="";
								$('#makeId').trigger("chosen:updated");
								document.getElementById("machineNo").value="";
								document.getElementById("variablePart").value="";
								document.getElementById("color").value="";
								document.getElementById("purDate").value="";
								document.getElementById("regDate").value="";
								document.getElementById("cleaningFrq").value="";
								document.getElementById("alertFrq").value="";
								document.getElementById("lastCleanDate").value=""; 
								document.getElementById("nextCleanDate").value="";
								document.getElementById("nextAlertDate").value=""; 
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
		 
		 
		function nextCleaning() {
			/* var d = new Date(); */
			var selectedDate = $("#lastCleanDate").val().split("-");
			var d = new Date(selectedDate[2],selectedDate[1] - 1, selectedDate[0]);
			var days = $("#cleaningFrq").val();
			var todayTimeStamp1 = +d; // Unix timestamp in milliseconds
			d.setDate(d.getDate());
			var todayTimeStamp = +d; // Unix timestamp in milliseconds
			var oneDayTimeStamp = 1000 * 60 * 60 * 24; // Milliseconds in a day
			 
			var diff = todayTimeStamp + (oneDayTimeStamp*days);
			var yesterdayDate = new Date(diff);  

			var tommarowString =  yesterdayDate.getDate()+ '-' + (yesterdayDate.getMonth() + 1) + '-' +yesterdayDate.getFullYear();
			document.getElementById("nextCleanDate").value=tommarowString; 
		}
		 
		 function alertDate() {
			/* var d = new Date(); */
			var selectedDate = $("#nextCleanDate").val().split("-");
			var d = new Date(selectedDate[2],selectedDate[1] - 1, selectedDate[0]);
			var days = $("#alertFrq").val();
			var todayTimeStamp1 = +d; // Unix timestamp in milliseconds
			d.setDate(d.getDate());
			var todayTimeStamp = +d; // Unix timestamp in milliseconds
			var oneDayTimeStamp = 1000 * 60 * 60 * 24; // Milliseconds in a day
			 
			var diff = todayTimeStamp - (oneDayTimeStamp*days);
			var yesterdayDate = new Date(diff);  

			var tommarowString =  yesterdayDate.getDate()+ '-' + (yesterdayDate.getMonth() + 1) + '-' +yesterdayDate.getFullYear();
			document.getElementById("nextAlertDate").value=tommarowString; 
		} 
	</script>
 
</body>
</html>