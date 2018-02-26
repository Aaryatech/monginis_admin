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
								<i class="fa fa-table"></i>Insert New Spare Part
							</h3>
							
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showSparePartList">Spare Part List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						
						
						<div class="box-content">

							<form id="submitMaterialStore" action="${pageContext.request.contextPath}/insertSparePart" method="post"
							enctype="multipart/form-data">
							<input type="hidden" id="sprId" name="sprId"   class="form-control"   >
							<div class="box-content">
								<div class="col-md-2">Part Name* </div>
								<div class="col-md-3">
								<input type="text" id="partName" name="partName" placeholder="Part Name"  class="form-control"  required>
								
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
							 
                                    <select name="typeId" id="typeId" class="form-control chosen" tabindex="6" required>
											<option value="">Select Type</option>
											<c:forEach items="${mechTypeList}" var="mechTypeList">
											<option value="${mechTypeList.typeId}">${mechTypeList.typeName}</option>
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
											type="text" name="uom"  required />
								</div>
								
								<div class="col-md-2">Is Critical* </div> 
								<div class="col-md-3">
							 
                                    <select name="critical" id="critical" class="form-control chosen" tabindex="6" required>
                                    <option value="">Select</option>
                                    <option value="1">Yes</option>
                                    <option value="2">No</option> 
											 
										</select>
								</div>
								
							</div><br>
							
							<div class="box-content">
							
								<div class="col-md-2">Date1* </div>
									<div class="col-md-3">
									<input class="form-control date-picker" id="date1" placeholder=" Date1" size="16"
											type="text" name="date1"  required />
									</div>
									
									<div class="col-md-2">Date1 Rate*</div>
									<div class="col-md-3">
									<input class="form-control" id="rate1" placeholder="Date1 Rate" size="16"
											type="text" name="rate1" required />
									</div>
								
							</div><br>
							
							<div class="box-content">
							
									<div class="col-md-2">Date2* </div>
									<div class="col-md-3">
									<input class="form-control date-picker" id="date2" placeholder="Date2" size="16"
											type="text" name="date2"  required />
									</div>
									
									<div class="col-md-2">Date2 Rate*</div>
									<div class="col-md-3">
									<input class="form-control" id="rate2" placeholder="Date2 Rate" size="16"
											type="text" name="rate2" required />
									</div>
							
							</div><br> 
							<div class="box-content">
							
									<div class="col-md-2">Date3* </div>
									<div class="col-md-3">
									<input class="form-control date-picker" id="date3" placeholder="Date3" size="16"
											type="text" name="date3"  required />
									</div>
									
									<div class="col-md-2">Date3 Rate*</div>
									<div class="col-md-3">
									<input class="form-control" id="rate3" placeholder="Date3 Rate" size="16"
											type="text" name="rate3" required />
									</div>
							
							</div><br> 
							
							<div class="box-content">
							
									<div class="col-md-2">Spare Warranty* </div>
									<div class="col-md-3">
									<input class="form-control" id="warnty" placeholder="Spare Warranty" size="16"
											type="text" name="warnty"  required />
									</div>
						
									<div class="col-md-2">Cgst* </div>
									<div class="col-md-3">
									<input class="form-control" id="cgst" placeholder="Cgst" size="16"
											type="text" name="cgst"  required />
									</div>
							
							</div><br>
							
							<div class="box-content">
							
									<div class="col-md-2">Sgst* </div>
									<div class="col-md-3">
									<input class="form-control" id="sgst" placeholder="Sgst" size="16"
											type="text" name="sgst"  required />
									</div>
						
									<div class="col-md-2">Igst* </div>
									<div class="col-md-3">
									<input class="form-control" id="igst" placeholder="Igst" size="16"
											type="text" name="igst"  required />
									</div>
							
							</div><br>
							
							<div class="box-content">
							
									<div class="col-md-2">Discount* </div>
									<div class="col-md-3">
									<input class="form-control" id="disc" placeholder="Discount" size="16"
											type="text" name="disc"  required />
									</div>
						
									<div class="col-md-2">Extra Charges* </div>
									<div class="col-md-3">
									<input class="form-control" id="extra" placeholder="Extra Charges" size="16"
											type="text" name="extra"  required />
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
						
						<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i>Spare Part List
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
										<th>Part Name</th> 
										<th>Company Name</th>
										<th>Type</th>
										<th>Group</th>
										<th>UOM</th>
										<th>Is Critical</th>
										<th>Date </th>
										<th>Date Rate</th>
										<th>Warranty</th> 
										<th>Action</th>

									</tr>
								</thead>
								<tbody>
								<c:set var = "srNo" value="0"/>
									<c:forEach items="${sprPartList}" var="sprPartList"
													varStatus="count">
													
													 
													<tr>
														<td ><c:out value="${count.index+1}" /></td>
 														<c:set var = "srNo" value="${count.index}"/> 
														<td align="left" ><c:out value="${sprPartList.sprName}" /></td> 
																
																<c:forEach items="${makeList}" var="makeList">
																<c:choose>
																<c:when test="${makeList.makeId==sprPartList.makeId}">
																<td align="left" ><c:out value="${makeList.makeName}" /></td> 
																</c:when> 
																</c:choose> 
																</c:forEach>
																
																<c:forEach items="${mechTypeList}" var="mechTypeList">
																	<c:choose>
																		<c:when test="${mechTypeList.typeId==sprPartList.typeId}">
																		<td align="left" ><c:out value="${mechTypeList.typeName}" /></td>
																		</c:when>
																	</c:choose>
																</c:forEach>
																
																<c:forEach items="${sprGroupList}" var="sprGroupList">
																<c:choose>
																<c:when test="${sprGroupList.groupId==sprPartList.groupId}">
																<td align="left" ><c:out value="${sprGroupList.groupName}" /></td> 
																</c:when> 
																</c:choose> 
																</c:forEach>
														   	  
																<td align="left" ><c:out value="${sprPartList.sprUom}" /></td>
																<c:choose>
																	<c:when test="${sprPartList.sprIscritical==1}">
																		<c:set var="critical" value="Yes"></c:set>
																	</c:when>
																	<c:when test="${sprPartList.sprIscritical==2}">
																		<c:set var="critical" value="No"></c:set>
																	</c:when> 
																</c:choose> 
																<td align="left" ><c:out value="${critical}" /></td>
														   	 	<td align="left" ><c:out value="${sprPartList.sprDate1}" /></td>
																<td align="left" ><c:out value="${sprPartList.sprRate1}" /></td>
																<td align="left" ><c:out value="${sprPartList.sprWarrantyPeriod}" /></td>
														 
													<td> <span class="glyphicon glyphicon-edit"  onclick="edit(${sprPartList.sprId})"> </span> 
						<a href="${pageContext.request.contextPath}/deleteSparePart/${sprPartList.sprId}" onClick="return confirm('Are you sure want to delete this record');"   >
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