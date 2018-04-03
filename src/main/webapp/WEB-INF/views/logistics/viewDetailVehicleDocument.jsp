<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
 <c:url var="groupByTypeId" value="/groupByTypeId"></c:url>
 <c:url var="editSparePart" value="/editSparePart"></c:url> 
 
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
								<i class="fa fa-table"></i>View Vehicle Document
							</h3>
							
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showVehicleDocumentList">Vehicle Document List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						
						
						<div class="box-content">

							<form id="submitMaterialStore" action="${pageContext.request.contextPath}/submitVehicleDocument" method="post"
							enctype="multipart/form-data">
							<input type="hidden" id="vehDocId" name="vehDocId" value="${vehDocId}"  class="form-control"   >
							<div class="box-content">
								<div class="col-md-2">Vehicle No* </div>
								<div class="col-md-3">
								   
											 
												<c:forEach items="${vehicleList}" var="vehicleList"> 
													<c:choose>
														<c:when test="${vehicleList.vehId==vehicleDocument.vehId}">
														${vehicleList.vehNo}
														</c:when>
													</c:choose>
												
											 </c:forEach>
											 
											
										 
								</div>
								
							<div class="col-md-2">Select Document* </div> 
								<div class="col-md-3"> 
											<c:forEach items="${documentList}" var="documentList"> 
													<c:choose>
														<c:when test="${documentList.docId==vehicleDocument.docId}">
														${documentList.docName}
														</c:when>
													</c:choose>
												
											 </c:forEach>
										 
								</div>
					
							</div><br>
							
							<div class="box-content">
							
								<div class="col-md-2">Entry Date </div>
									<div class="col-md-3">
									${vehicleDocument.entryDate}
									</div>
								
								<div class="col-md-2">Document Date</div> 
								<div class="col-md-3">
							 
                                  ${vehicleDocument.docDate}
								</div>
								
				 
							</div><br>
							
							<div class="box-content">
							
							<div class="col-md-2">Document Expire Date</div> 
								<div class="col-md-3">
							 
                                   ${vehicleDocument.docExpireDate}
								</div>
								
								<div class="col-md-2">Expire Notification Date</div> 
								<div class="col-md-3">
							 
                                   ${vehicleDocument.docExpNotificationDate}
								</div>
								
							</div><br>
							
							<div class="box-content">
							 
									
									<div class="col-md-2">Current Km*</div>
									<div class="col-md-3">
									${vehicleDocument.currentKm}
									</div>
								
							</div><br><br>
							
							     
							 
								
							<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
								<!-- 		<input type="submit" class="btn btn-primary" value="Submit" onclick="validation()" > -->
										<a href="${imageUrl}${vehicleDocument.docPath}" target="blank" ><input type="button" value="View Document File" class="btn btn-info">
										</a>
										 </div>
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
		
		
		function edit(sprId) {

	         
			 
			//alert("driverId"+driverId);
			$('#loader').show();

			$
					.getJSON(
							'${editSparePart}',

							{
								 
								sprId : sprId, 
								ajax : 'true'

							},
							function(data) { 
								           			
								document.getElementById("sprId").value=data.sprId;
								document.getElementById("partName").value=data.sprName; 
								document.getElementById("makeId").value=data.makeId;
								$('#makeId').trigger("chosen:updated");
								document.getElementById("typeId").value=data.typeId;
								$('#typeId').trigger("chosen:updated");
								document.getElementById("uom").value=data.sprUom;
								document.getElementById("critical").value=data.sprIscritical;
								$('#critical').trigger("chosen:updated");
								document.getElementById("date1").value=data.sprDate1;
								document.getElementById("rate1").value=data.sprRate1;
								document.getElementById("date2").value=data.sprDate2;
								document.getElementById("rate2").value=data.sprRate2;
								document.getElementById("date3").value=data.sprDate3;
								document.getElementById("rate3").value=data.sprRate3; 
								document.getElementById("warnty").value=data.sprWarrantyPeriod;
								document.getElementById("cgst").value=data.cgst;
								document.getElementById("sgst").value=data.sgst;
								document.getElementById("igst").value=data.igst;
								document.getElementById("disc").value=data.disc;
								document.getElementById("extra").value=data.extraCharges;
								
								
								$.getJSON('${groupByTypeId}', {
									
									typeId : data.typeId,
									ajax : 'true'
								},
										function(data1) {
									 
									var html = '<option value="">Select Group</option>';
									
									var len = data1.length;
									for ( var i = 0; i < len; i++) {
										
										if(data.groupId==data1[i].groupId)
											{
											 
											html += '<option value="' + data1[i].groupId + '" selected>'
											+ data1[i].groupName + '</option>';
											document.getElementById("groupId").value=data.groupId;
											$('#groupId').trigger("chosen:updated");
											}
										else
											{
											html += '<option value="' + data1[i].groupId + '">'
											+ data1[i].groupName + '</option>';
											}
										
									}
									html += '</option>';
									$('#groupId').html(html);
									$("#groupId").trigger("chosen:updated");
															
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
         document.getElementById("sprId").value="";
			document.getElementById("partName").value=""; 
			document.getElementById("makeId").value="";
			$('#makeId').trigger("chosen:updated");
			document.getElementById("typeId").value="";
			$('#typeId').trigger("chosen:updated");
			document.getElementById("uom").value="";
			document.getElementById("critical").value="";
			$('#critical').trigger("chosen:updated");
			document.getElementById("date1").value="";
			document.getElementById("rate1").value="";
			document.getElementById("date2").value="";
			document.getElementById("rate2").value="";
			document.getElementById("date3").value="";
			document.getElementById("rate3").value=""; 
			document.getElementById("warnty").value="";
			document.getElementById("cgst").value="";
			document.getElementById("sgst").value="";
			document.getElementById("igst").value="";
			document.getElementById("disc").value="";
			document.getElementById("extra").value="";
		var html = '<option value="">Select Group</option>';
		$('#groupId').html(html);
		$('#groupId').trigger("chosen:updated"); 
	
}
		
		$(document).ready(function() { 
			$('#typeId').change(
					function() {
						//alert("makeId"+$(this).val());
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
		
		 
		
		 
		 
		 
	</script>
 
</body> 
</html>