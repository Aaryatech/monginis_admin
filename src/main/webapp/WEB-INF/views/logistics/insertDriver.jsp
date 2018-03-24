<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
 <c:url var="editDriver" value="/editDriver"></c:url>
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
								<i class="fa fa-table"></i>Insert New Driver
							</h3>
							
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showDriverList">Driver List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						
						
						<div class="box-content">

							<form id="submitMaterialStore" action="${pageContext.request.contextPath}/insertNewDriver" method="post"
							enctype="multipart/form-data">
							<input type="hidden" id="driverId" name="driverId"   class="form-control"   >
							<div class="box-content">
								<div class="col-md-2">Driver Name* </div>
								<div class="col-md-3">
								<input type="text" id="driver_name" name="driver_name"  placeholder="Driver Name" class="form-control"  required>
								
								</div>
								
							<div class="col-md-2">Address*</div> 
							<div class="col-md-3">
							 
                                    <input type="text" id="add1" name="add1"  placeholder="Address" class="form-control"  required>
							</div>
					
							</div><br>
							
							<div class="box-content">
							
								<div class="col-md-2">Alternet Address</div> 
								<div class="col-md-3">
							 
                                    <input type="text" id="add2" name="add2" placeholder="Alternet Address"  class="form-control"   >
								</div>
								<div class="col-md-2">Mobile No.* </div>
									<div class="col-md-3">
									<input class="form-control" id="mob1" placeholder="Mobile No" size="16"
											type="text" name="mob1" pattern="^\d{10}$" required />
									</div>
								
				 
							</div><br>
							
							
							<div class="box-content">
							
							<div class="col-md-2">Alternet Mobile No.</div>
									<div class="col-md-3">
									<input class="form-control" id="mob2" placeholder="Alternet Mobile No" size="16"
											type="text" name="mob2" pattern="^\d{10}$"   />
									</div>
									
									<div class="col-md-2">Another Mobile No.</div>
									<div class="col-md-3">
									<input class="form-control" id="mob3" placeholder="Another Mobile No" size="16"
											type="text" name="mob3"  pattern="^\d{10}$"  />
									</div>
								
							</div><br>
							
							
							
							<div class="box-content">
							
									<div class="col-md-2">Date Of Birth* </div>
									<div class="col-md-3">
									<input class="form-control date-picker" placeholder="Date Of Birth" id="dob" size="16"
											type="text" name="dob"  required />
									</div>
									
									<div class="col-md-2">Joining Date* </div>
									<div class="col-md-3">
									<input class="form-control date-picker" placeholder="Joining Date" id="joining_date" size="16"
											type="text" name="joining_date"  required />
									</div>
							
							</div><br> 
							
							<div class="box-content">
							
									<div class="col-md-2">License No.* </div>
									<div class="col-md-3">
									<input class="form-control" id="lic_no" placeholder="License No" size="16"
											type="text" name="lic_no"  required />
									</div>
									
									<div class="col-md-2">License Expired Date* </div>
									<div class="col-md-3">
									<input class="form-control date-picker" id="lic_expr_date" placeholder="License Expired Date" size="16"
											type="text" name="lic_expr_date"  required />
									</div>
							
							</div><br> <br>
								
							<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Add New Driver"  >
										<input type="button" class="btn btn-primary" value="Cancel" id="cancel" onclick="cancel1()" disabled>
<!-- 									<button type="button" class="btn">Cancel</button>
 -->									</div>
								</div><br><br>
						
							
							

						</form>
						</div>	
						</div>
						
						<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i>Driver List
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
										<th>Driver Name</th> 
										<th>Joining Date</th>
										<th>Mobile No</th>
										<th>Address</th>
										<th>Action</th>

									</tr>
								</thead>
								<tbody>
								<c:set var = "srNo" value="0"/>
									<c:forEach items="${driverList}" var="driverList"
													varStatus="count">
													
													 
													<tr>
														<td ><c:out value="${count.index+1}" /></td>
 														<c:set var = "srNo" value="${count.index}"/> 
														<td align="left" ><c:out
																value="${driverList.driverName}" /></td> 
														<td align="left" ><c:out
																value="${driverList.joiningDate}" /></td> 
																<td align="left" ><c:out
																value="${driverList.mobile1}" /></td>  
														 <td align="left" ><c:out
																value="${driverList.address1}" /></td> 
														
													<td> <span class="glyphicon glyphicon-edit"  onclick="edit(${driverList.driverId})"> </span> 
						<a href="${pageContext.request.contextPath}/deleteDriver/${driverList.driverId}" onClick="return confirm('Are you sure want to delete this record');"   >
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
		
		
		function edit(driverId) {

			         
			 
				//alert("driverId"+driverId);
				$('#loader').show();

				$
						.getJSON(
								'${editDriver}',

								{
									 
									driverId : driverId, 
									ajax : 'true'

								},
								function(data) { 
									
									document.getElementById("driverId").value=data.driverId;
									document.getElementById("driver_name").value=data.driverName;
									document.getElementById("add1").value=data.address1;
									document.getElementById("add2").value=data.address2;
									document.getElementById("mob1").value=data.mobile1;
									document.getElementById("mob2").value=data.mobile2;
									document.getElementById("mob3").value=data.mobile3;
									document.getElementById("dob").value=data.driverDob;
									document.getElementById("joining_date").value=data.joiningDate;
									document.getElementById("lic_no").value=data.licNo;
									document.getElementById("lic_expr_date").value=data.licExpireDate; 
									document.getElementById("cancel").disabled=false;
								});

			 
				 
			
	}
		
		function cancel1() {

	         //alert("cancel");
	         document.getElementById("cancel").disabled=true; 
	         document.getElementById("driverId").value="";
				document.getElementById("driver_name").value="";
				document.getElementById("add1").value="";
				document.getElementById("add2").value="";
				document.getElementById("mob1").value="";
				document.getElementById("mob2").value="";
				document.getElementById("mob3").value="";
				document.getElementById("dob").value="";
				document.getElementById("joining_date").value="";
				document.getElementById("lic_no").value="";
				document.getElementById("lic_expr_date").value=""; 
		
	}
		 
		 
	</script>
 
</body>
</html>