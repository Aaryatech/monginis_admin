<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
 <c:url var="editDealer" value="/editDealer"></c:url>
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
								<i class="fa fa-table"></i>Insert New Dealer
							</h3>
							
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showDealarList">Dealer List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						
						
						<div class="box-content">

							<form id="submitMaterialStore" action="${pageContext.request.contextPath}/insertNewDealer" method="post"
							enctype="multipart/form-data">
							<input type="hidden" id="dealerId" name="dealerId"   class="form-control"   >
							<div class="box-content">
								<div class="col-md-2">Dealer Name* </div>
								<div class="col-md-3">
								<input type="text" id="dealerName" name="dealerName"  placeholder="Dealer Name" class="form-control"  required>
								
								</div>
								
							<div class="col-md-2">Mobile No.* </div>
									<div class="col-md-3">
									<input class="form-control" id="mob1" size="16"
											type="text" name="mob1" pattern="^\d{10}$" placeholder="Mobile No" required />
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
								<div class="col-md-2">City* </div>
									<div class="col-md-3">
									<input class="form-control" id="city" placeholder="City" size="16"
											type="text" name="city"   required />
									</div>
								
				 
							</div><br>
							
							
							<div class="box-content">
							
							<div class="col-md-2">Email* </div>
									<div class="col-md-3">
									<input class="form-control" id="email" placeholder="Email" size="16"
											type="email" name="email"   data-rule-email="true" required/>
									</div>
									
									<div class="col-md-2">GSTN No* </div>
									<div class="col-md-3">
									<input class="form-control" id="gstnNo" placeholder="GSTN No" size="16"
											type="text" name="gstnNo"  required/>
									</div>
								
							</div><br>
							
							<div class="box-content">
							
							<div class="col-md-2">Is Same State* </div>
									<div class="col-md-3">
									  <select name="isSameState" id="isSameState" class="form-control chosen" tabindex="6" required>
									  <option value="">Select</option>
											<option value="1">Yes</option>
											 <option value="2">No</option>
										</select>
									</div>
									
									
								
							</div><br>
							
							
							
							<div class="box-content">
							
									<div class="col-md-2">Contact Person Name* </div>
									<div class="col-md-3">
									<input class="form-control" id="cntprn" placeholder="Contact Person Name" size="16"
											type="text" name="cntprn"  required/>
									</div>
									
										<div class="col-md-2">Person Mobile No.* </div>
									<div class="col-md-3">
									<input class="form-control" id="mob2" size="16" placeholder="Person Mobile No"
											type="text" name="mob2" pattern="^\d{10}$" required />
									</div>
							
							</div><br> 
							
							<div class="box-content">
							
									<div class="col-md-2">Person Email* </div>
									<div class="col-md-3">
									<input class="form-control" id="email2" size="16" placeholder="Person Email"
											type="email" name="email2" data-rule-email="true" required />
									</div>
									
									 
							
							</div><br><br>
								
							<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Add New Dealer"  onclick="validation()">
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
								<i class="fa fa-table"></i>Dealer List
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
										<th>Dealer Name</th> 
										<th>Company Name</th>
										<th>City</th>
										<th>Mobile No.</th>
										<th>Action</th>

									</tr>
								</thead>
								<tbody>
								<c:set var = "srNo" value="0"/>
									<c:forEach items="${dealerList}" var="dealerList"
													varStatus="count">
													
													 
													<tr>
														<td ><c:out value="${count.index+1}" /></td>
 														<c:set var = "srNo" value="${count.index}"/> 
														<td align="left" ><c:out
																value="${dealerList.dealerName}" /></td> 
																
																<c:forEach items="${makeList}" var="makeList">
																<c:choose>
																	<c:when test="${dealerList.makeId==makeList.makeId}">
																	<td align="left" ><c:out value="${makeList.makeName}" /></td>
																	</c:when>
																</c:choose> 
																</c:forEach>
														 
																<td align="left" ><c:out
																value="${dealerList.city}" /></td>  
														 <td align="left" ><c:out
																value="${dealerList.dealerMobileNo}" /></td> 
														
													<td> <span class="glyphicon glyphicon-edit"  onclick="edit(${dealerList.dealerId})"> </span> 
						<a href="${pageContext.request.contextPath}/deleteDealer/${dealerList.dealerId}" onClick="return confirm('Are you sure want to delete this record');"   >
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
		
		
		function edit(dealerId) {

			         
			 
				//alert("driverId"+driverId);
				$('#loader').show();

				$
						.getJSON(
								'${editDealer}',

								{
									 
									dealerId : dealerId, 
									ajax : 'true'

								},
								function(data) { 
									
									document.getElementById("dealerId").value=data.dealerId;
									document.getElementById("dealerName").value=data.dealerName; 
									document.getElementById("mob1").value=data.dealerMobileNo;
									document.getElementById("makeId").value=data.makeId;
									$('#makeId').trigger("chosen:updated");
									document.getElementById("city").value=data.city;
									document.getElementById("email").value=data.dealerEmail;
									document.getElementById("cntprn").value=data.contactPerson;
									document.getElementById("mob2").value=data.personMobileNo;
									document.getElementById("email2").value=data.contactPersonEmail; 
									document.getElementById("gstnNo").value=data.gstnNo;
									document.getElementById("isSameState").value=data.isSameState;
									$('#isSameState').trigger("chosen:updated");
									document.getElementById("cancel").disabled=false;
								});

			 
				 
			
	}
		function validation() {

	         //alert("hi");
			 var makeId = $("#makeId").val();
			 var isSameState = $("#isSameState").val();
			 if(makeId=="")
				 {
				 alert("Select Company ");
				 }
			 else if(isSameState=="")
			 {
			 alert("Select Is Same State ");
			 }
			 
		
}
		
		function cancel1() {

	         //alert("cancel");
			document.getElementById("cancel").disabled=true; 
			document.getElementById("dealerId").value="";
			document.getElementById("dealerName").value=""; 
			document.getElementById("mob1").value="";
			document.getElementById("makeId").value="";
			$('#makeId').trigger("chosen:updated");
			document.getElementById("city").value="";
			document.getElementById("email").value="";
			document.getElementById("cntprn").value="";
			document.getElementById("mob2").value="";
			document.getElementById("email2").value=""; 
			document.getElementById("gstnNo").value="";
			document.getElementById("isSameState").value="";
			$('#isSameState').trigger("chosen:updated");
		
}
		 
		 
		 
	</script>
 
</body>
</html>