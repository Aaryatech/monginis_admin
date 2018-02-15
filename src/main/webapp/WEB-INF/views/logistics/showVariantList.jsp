<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
 <c:url var="typeByMakeId" value="/typeByMakeId"></c:url>
 <c:url var="editVariant" value="/editVariant"></c:url>
 
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
								<i class="fa fa-table"></i>Insert New Variant
							</h3>
							
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showVariantList">Variant List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						
						
						<div class="box-content">

							<form id="submitMaterialStore" action="${pageContext.request.contextPath}/insertVariant" method="post"
							enctype="multipart/form-data">
							<input type="hidden" id="variantId" name="variantId"   class="form-control"   >
							<div class="box-content">
								<div class="col-md-2">Variant Name* </div>
								<div class="col-md-3">
								<input type="text" id="variantName" name="variantName"  placeholder="Variant Name" class="form-control"  required>
								
								</div>
								 
							</div><br>
							
							<div class="box-content">
							
								<div class="col-md-2">Company Name* </div> 
								<div class="col-md-3">
							 
                                    <select name="makeId" id="makeId" class="form-control chosen" tabindex="6" required>
											<option value="">Select Company</option>
											<c:forEach items="${makeList}" var="makeList"> 
												<option value="${makeList.makeId}"><c:out value="${makeList.makeName}"></c:out> </option>
											 </c:forEach>
										</select>
								</div>
								<div class="col-md-1"></div>
								<div class="col-md-1">Type* </div> 
								<div class="col-md-3">
							 
                                    <select name="typeId" id="typeId" class="form-control chosen" tabindex="6" required>
											 
										</select>
								</div>
								
				 
							</div><br><br>
							 
							 
								
							<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Add New Variant"  onclick="validation()">
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
								<i class="fa fa-table"></i>Variant List
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
										<th>Variant Name</th> 
										<th>Company Name</th>
										<th>Type</th> 
										<th>Action</th>

									</tr>
								</thead>
								<tbody>
								<c:set var = "srNo" value="0"/>
									<c:forEach items="${variantList}" var="variantList"
													varStatus="count">
													
													 
													<tr>
														<td ><c:out value="${count.index+1}" /></td>
 														<c:set var = "srNo" value="${count.index}"/> 
														<td align="left" ><c:out
																value="${variantList.variantName}" /></td> 
																
																<c:forEach items="${makeList}" var="makeList">
																<c:choose>
																	<c:when test="${variantList.makeId==makeList.makeId}">
																	<td align="left" ><c:out value="${makeList.makeName}" /></td>
																	</c:when>
																</c:choose> 
																</c:forEach>
																
																<c:forEach items="${vehicalTypeList}" var="vehicalTypeList">
																<c:choose>
																	<c:when test="${variantList.vehTypeId==vehicalTypeList.vehiTypeId}">
																	<td align="left" ><c:out value="${vehicalTypeList.vehTypeName}" /></td>
																	</c:when>
																</c:choose> 
																</c:forEach>
														  
														  
														
													<td> <span class="glyphicon glyphicon-edit"  onclick="edit(${variantList.variantId})"> </span> 
						<a href="${pageContext.request.contextPath}/deleteVariant/${variantList.variantId}" onClick="return confirm('Are you sure want to delete this record');"   >
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
		
		
		function edit(variantId) {

			         
			 
				//alert("driverId"+driverId);
				$('#loader').show();

				$
						.getJSON(
								'${editVariant}',

								{
									 
									variantId : variantId, 
									ajax : 'true'

								},
								function(data) { 
									
									document.getElementById("variantId").value=data.variantId;
									document.getElementById("variantName").value=data.variantName; 
									document.getElementById("makeId").value=data.makeId;
									$('#makeId').trigger("chosen:updated");
									
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
									 
									document.getElementById("cancel").disabled=false;
								});

			 
				 
			
	}
		function validation() {

	         //alert("hi");
			 var makeId = $("#makeId").val();
			 var typeId = $("#typeId").val();
			 if(makeId=="")
				 {
				 alert("Select Company ");
				 }
			 else if(typeId=="")
				 {
				 alert("Select Type ");
				 }
			 
		
}
		
		function cancel1() {

	         //alert("cancel");
			document.getElementById("cancel").disabled=true; 
			document.getElementById("variantId").value="";
			document.getElementById("variantName").value="";  
			document.getElementById("makeId").value="";
			$('#makeId').trigger("chosen:updated"); 
			document.getElementById("typeId").value="";
			var html = '<option value="">Select Type</option>';
			$('#typeId').html(html);
			$('#typeId').trigger("chosen:updated");
		
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
		 
	</script>
 
</body>
</html>