<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body onload="typeChangeOnload(${sparePart.typeId},${sparePart.groupId})">
	
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
								<i class="fa fa-table"></i>Edit New Spare Part
							</h3>
							
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showAllSparePartList">View All Spare Parts</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						
						
						<div class="box-content">

							<form id="submitMaterialStore" action="${pageContext.request.contextPath}/insertSparePart" method="post"
							enctype="multipart/form-data">
							<input type="hidden" id="sprId" name="sprId"   class="form-control"  value="${sparePart.sprId}" >
							<div class="box-content">
								<div class="col-md-2">Part Name* </div>
								<div class="col-md-3">
								<input type="text" id="partName" name="partName" placeholder="Part Name"  class="form-control" value="${sparePart.sprName}" required>
								
								</div>
								
							<div class="col-md-2">Company Name* </div> 
								<div class="col-md-3">
							 
                                    <select name="makeId" id="makeId" class="form-control chosen" tabindex="6" required>
											<option value="">Select Company</option>
											<c:forEach items="${makeList}" var="makeList"> 
											<c:choose>
											<c:when test="${makeList.makeId==sparePart.makeId}">
												<option value="${makeList.makeId}" selected><c:out value="${makeList.makeName}"></c:out> </option>
											</c:when>
											<c:otherwise>
													<option value="${makeList.makeId}"><c:out value="${makeList.makeName}"></c:out> </option>
											</c:otherwise>
											</c:choose>
											 </c:forEach>
										</select>
								</div>
					
							</div><br>
							
							<div class="box-content">
							
								<div class="col-md-2">Type* </div> 
								<div class="col-md-3">
							 
                                    <select name="typeId" id="typeId" class="form-control chosen" tabindex="6"  onchange="typeChange(this.value)" required>
											<option value="">Select Type</option>
											<c:forEach items="${mechTypeList}" var="mechTypeList">
											<c:choose>
											<c:when test="${mechTypeList.typeId==sparePart.typeId}">
											<option value="${mechTypeList.typeId}" selected>${mechTypeList.typeName}</option>
												</c:when>
												<c:otherwise>
								<option value="${mechTypeList.typeId}">${mechTypeList.typeName}</option>

												</c:otherwise>
												</c:choose>
													</c:forEach>
										</select>
								</div>
								
								<div class="col-md-2">Group* </div> 
								<div class="col-md-3">
							 
                                    <select name="groupId" id="groupId" class="form-control chosen" tabindex="6" required>
											 
										</select>
								</div>
								
				 
							</div><br>
							
							<div class="box-content">
							
							<div class="col-md-2">UOM* </div> 
								<div class="col-md-3">
							 
                                    <input class="form-control" id="uom" placeholder="UOM" size="16"
											type="text" name="uom" value="${sparePart.sprUom}" required />
								</div>
								
								<div class="col-md-2">Is Critical* </div> 
								<div class="col-md-3">
							 
                                   <select name="critical" id="critical" class="form-control chosen" tabindex="6" required>
                                   <option value="">Select</option>
                                   <c:choose>
                                   <c:when test="${sparePart.sprIscritical==1}">
                                   <option value="1"selected>Yes</option>
                                   <option value="2">No</option> 
                                   </c:when>
                                   <c:when test="${sparePart.sprIscritical==2}">                   
                                   <option value="1">Yes</option>
                                   <option value="2" selected>No</option> 
                                   </c:when>
                                   <c:otherwise>
                                   
                                   <option value="1">Yes</option>
                                   <option value="2">No</option> 
                                   </c:otherwise>
										 
                                    </c:choose>	 
									</select>
								</div>
								
							</div><br>
							
							<div class="box-content">
							
								<div class="col-md-2">Date1* </div>
									<div class="col-md-3">
									<input class="form-control date-picker" id="date1" placeholder=" Date1" size="16"
											type="text" name="date1" value="${sparePart.sprDate1}" required />
									</div>
									
									<div class="col-md-2">Date1 Rate*</div>
									<div class="col-md-3">
									<input class="form-control" id="rate1" placeholder="Date1 Rate" autofocus  pattern="[+-]?([0-9]*[.])?[0-9]+" size="16" title="Please Enter Numeric Value"
											type="text" name="rate1" value="${sparePart.sprRate1}" required />
									</div>
								
							</div><br>
							
							<div class="box-content">
							
									<div class="col-md-2">Date2* </div>
									<div class="col-md-3">
									<input class="form-control date-picker" id="date2" placeholder="Date2" size="16"
											type="text" name="date2" value="${sparePart.sprDate2}"  required />
									</div>
									
									<div class="col-md-2">Date2 Rate*</div>
									<div class="col-md-3">
									<input class="form-control" id="rate2" placeholder="Date2 Rate" autofocus pattern="[+-]?([0-9]*[.])?[0-9]+" size="16" title="Please Enter Numeric Value"
											type="text" name="rate2" value="${sparePart.sprRate2}" required />
									</div>
							
							</div><br> 
							<div class="box-content">
							
									<div class="col-md-2">Date3* </div>
									<div class="col-md-3">
									<input class="form-control date-picker" id="date3" placeholder="Date3" size="16"  
											type="text" name="date3"  value="${sparePart.sprDate3}" required />
									</div>
									
									<div class="col-md-2">Date3 Rate*</div>
									<div class="col-md-3">
									<input class="form-control" id="rate3" placeholder="Date3 Rate" autofocus pattern="[+-]?([0-9]*[.])?[0-9]+"  title="Please Enter Numeric Value" size="16"
											type="text" name="rate3" value="${sparePart.sprRate3}" required />
									</div>
							
							</div><br> 
							
							<div class="box-content">
							
									<div class="col-md-2">Spare Warranty* </div>
									<div class="col-md-3">
									<input class="form-control" id="warnty" placeholder="Spare Warranty" autofocus pattern="[+-]?([0-9]*[.])?[0-9]+"  title="Please Enter Numeric Value" size="16"
											type="text" name="warnty"  value="${sparePart.sprWarrantyPeriod}" required />
									</div>
						
									<div class="col-md-2">Cgst* </div>
									<div class="col-md-3">
									<input class="form-control" id="cgst" placeholder="Cgst" autofocus pattern="[+-]?([0-9]*[.])?[0-9]+"  title="Please Enter Numeric Value" size="16"
											type="text" name="cgst"  value="${sparePart.cgst}"   required />
									</div>
							
							</div><br>
							
							<div class="box-content">
							
									<div class="col-md-2">Sgst* </div>
									<div class="col-md-3">
									<input class="form-control" id="sgst" placeholder="Sgst" autofocus pattern="[+-]?([0-9]*[.])?[0-9]+"  title="Please Enter Numeric Value" size="16"
											type="text" name="sgst" value="${sparePart.sgst}" required />
									</div>
						
									<div class="col-md-2">Igst* </div>
									<div class="col-md-3">
									<input class="form-control" id="igst" placeholder="Igst" autofocus  pattern="[+-]?([0-9]*[.])?[0-9]+"  title="Please Enter Numeric Value"  size="16"
											type="text" name="igst" value="${sparePart.igst}" required />
									</div>
							
							</div><br>
							
							<div class="box-content">
							
									<div class="col-md-2">Discount* </div>
									<div class="col-md-3">
									<input class="form-control" id="disc" placeholder="Discount" autofocus pattern="[+-]?([0-9]*[.])?[0-9]+"  title="Please Enter Numeric Value"   size="16"
											type="text" name="disc"   value="${sparePart.disc}" required />
									</div>
						
									<div class="col-md-2">Extra Charges* </div>
									<div class="col-md-3">
									<input class="form-control" id="extra" placeholder="Extra Charges" autofocus pattern="[+-]?([0-9]*[.])?[0-9]+" title="Please Enter Numeric Value" size="16"
											type="text" name="extra" value="${sparePart.extraCharges}" required />
									</div>
							
							</div><br><br>
							
							
								
							<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Submit" onclick="validation()" >
										<input type="button" class="btn btn-primary" value="Cancel" id="cancel" onclick="cancel1()" disabled>
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
			<p>2018 © MONGINIS.</p>
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

	function validation() {

         //alert("hi"); 
		 var makeId = $("#makeId").val();
		 var typeId = $("#typeId").val();
		 var critical = $("#critical").val();
		 var groupId = $("#groupId").val(); 
		 if(makeId=="")
			 {
			 alert("Select Company ");
			 }
		 else if(typeId=="")
			 {
			 alert("Select Type ");
			 } 
		 else if(groupId=="")
		 {
		 alert("Select Group ");
		 }
		 else if(critical=="")
		 {
		 alert("Select is Critical");
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
		
					function typeChange(typeId) {
						//alert("makeId"+$(this).val());
						//var typeId=$(typeId).val();
					    
						$.getJSON('${groupByTypeId}', {
							
							typeId : typeId,
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
					 
				}
					function typeChangeOnload(typeId,groupId) {
						//alert("makeId"+$(this).val());
						//var typeId=$(typeId).val();
					    
						$.getJSON('${groupByTypeId}', {
							
							typeId : typeId,
							ajax : 'true'
						},
								function(data) {
							 
							var html = '<option value="">Select Group</option>';
							
							var len = data.length;
							for ( var i = 0; i < len; i++) {
								if(data[i].groupId ==groupId){
								html += '<option value="' + data[i].groupId + '"selected>'
										+ data[i].groupName + '</option>';
								}
								else
									{
									html += '<option value="' + data[i].groupId + '">'
									+ data[i].groupName + '</option>';
									}
							}
							html += '</option>';
							$('#groupId').html(html);
							$("#groupId").trigger("chosen:updated");
													
								});
					 
				}
	</script>
 
</body>
</html>