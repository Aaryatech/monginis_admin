<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
 <c:url var="getNextServDate" value="/getNextServDate"></c:url>
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
								<i class="fa fa-table"></i>Insert Machine Servicing
							</h3>
							
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showMachineServicingList">Servicing List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						
						
						<div class="box-content">

							<form id="submitMaterialStore" action="${pageContext.request.contextPath}/submitMachineServicing" method="post"
							enctype="multipart/form-data">
							<input type="hidden" id="mechName" name="mechName"   class="form-control"   >
							<input type="hidden" id="dealerName" name="dealerName"   class="form-control"   >
							<div class="box-content">
								<div class="col-md-2">Select Machine* </div>
								<div class="col-md-3">
								 <select name="machineId" id="machineId" class="form-control chosen" tabindex="6" required>
											<option value="">Select Machine</option>
											<c:forEach items="${machineList}" var="machineList"> 
												<option value="${machineList.machineId}"><c:out value="${machineList.machineName}"></c:out> </option>
											 </c:forEach>
										</select>
								</div>
								
							<div class="col-md-2">Servicing Type*</div> 
							<div class="col-md-3">
							 
                                     <select name="servType" id="servType" class="form-control chosen" tabindex="6" required>
											<option value="">Select Serv Type</option>
											 <option value="1">Internal</option>
											 <option value="2">External</option>
										</select>
							</div>
					
							</div><br>
							
							<div class="box-content">
							
								<div class="col-md-2">Paid Type*</div> 
								<div class="col-md-3">
								 
	                                     <select name="paidType" id="paidType" class="form-control chosen" tabindex="6" required>
												<option value="">Select Paid Type</option>
												 <option value="1">Free</option>
												 <option value="2">Amc</option>
												 <option value="3">Paid</option>
											</select>
								</div>
								<div class="col-md-2">Select Dealer* </div>
									<div class="col-md-3">
									 <select name="dealerId" id="dealerId" class="form-control chosen" tabindex="6" required>
											<option value="">Select Dealer</option>
											<c:forEach items="${dealerList}" var="dealerList"> 
												<option value="${dealerList.dealerId}"><c:out value="${dealerList.dealerName}"></c:out> </option>
											 </c:forEach>
										</select>
									</div>
								
				 
							</div><br>
							
							
							<div class="box-content">
							
							<div class="col-md-2">Person Name*</div>
									<div class="col-md-3">
									<input class="form-control" id="persnName" placeholder="Person Name" size="16"
											type="text" name="persnName"  required/>
									</div>
									
									<div class="col-md-2">Date* </div>
									<div class="col-md-3">
									<input class="form-control" placeholder="Date" value="${today}" id="date" size="16"
											type="text" name="date"  readonly />
									</div>
								
							</div><br>
							
							
							
							<div class="box-content">
							
									<div class="col-md-2">Next Servicing Date* </div>
									<div class="col-md-3">
									<input class="form-control" placeholder="Next Servicing Date" id="nextServDate" size="16"
											type="text" name="nextServDate"  readonly />
									</div>
									
									<div class="col-md-2">Alert Servicing Date* </div>
									<div class="col-md-3">
									<input class="form-control" placeholder="Alert Servicing Date" id="alertServDate" size="16"
											type="text" name="alertServDate"  readonly />
									</div>
							
							</div><br> 
							
							<div class="box-content">
							
									<div class="col-md-2">Alert Is Required*</div> 
								<div class="col-md-3">
								 
	                                     <select name="alertRequired" id="alertRequired" class="form-control chosen" tabindex="6" required>
												<option value="">Select</option>
												 <option value="1">Yes</option>
												 <option value="2">No</option> 
											</select>
								</div>
									
									 
							</div><br> <br>
								
							<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Submit" onclick="validation()" >  
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
		$(document).ready(function() { 
			$('#machineId').change(
					function() {
						//alert("mechId"+$(this).val());
						var mechId=$(this).val();  
						var mechName = $("#machineId option:selected").text();
						$.getJSON('${getNextServDate}', {
							
							mechId : $(this).val(), 
							ajax : 'true'
						},
								function(data) {
							  
							 
							document.getElementById("mechName").value=mechName;  
							var selectedDate = $("#date").val().split("-");
							var d = new Date(selectedDate[2],selectedDate[1] - 1, selectedDate[0]);
							var days = data.cleaningFrq;
							var todayTimeStamp1 = +d; // Unix timestamp in milliseconds
							d.setDate(d.getDate());
							var todayTimeStamp = +d; // Unix timestamp in milliseconds
							var oneDayTimeStamp = 1000 * 60 * 60 * 24; // Milliseconds in a day
							 
							var diff = todayTimeStamp + (oneDayTimeStamp*days);
							var yesterdayDate = new Date(diff);  

							var tommarowString =  yesterdayDate.getDate()+ '-' + (yesterdayDate.getMonth() + 1) + '-' +yesterdayDate.getFullYear();
							document.getElementById("nextServDate").value=tommarowString;
							
							
							var selectedDate = $("#nextServDate").val().split("-");
							var d = new Date(selectedDate[2],selectedDate[1] - 1, selectedDate[0]);
							var days = data.alertFreq;
							var todayTimeStamp1 = +d; // Unix timestamp in milliseconds
							d.setDate(d.getDate());
							var todayTimeStamp = +d; // Unix timestamp in milliseconds
							var oneDayTimeStamp = 1000 * 60 * 60 * 24; // Milliseconds in a day
							 
							var diff = todayTimeStamp - (oneDayTimeStamp*days);
							var yesterdayDate = new Date(diff);  

							var tommarowString =  yesterdayDate.getDate()+ '-' + (yesterdayDate.getMonth() + 1) + '-' +yesterdayDate.getFullYear();
							document.getElementById("alertServDate").value=tommarowString;
								});
					 
						 
				})
				 		 
		});
		
		$(document).ready(function() { 
			$('#dealerId').change(
					function() {
						 
						 
						var dealerName = $("#dealerId option:selected").text(); 
							document.getElementById("dealerName").value=dealerName;  
							 
								 
						 
				})
				 		 
		});
		
		function validation() {

			  
			 var machineId = $("#machineId").val(); 
			 var dealerId = $("#dealerId").val(); 
			 var paidType = $("#paidType").val();
			 var servType = $("#servType").val();
			 var alertRequired = $("#alertRequired").val();
			 if(machineId=="")
				 {
				 alert("Select Machine ");
				 }  
			 else if(dealerId=="")
			 {
			 alert("Select Dealer ");
			 }
			 else if(paidType=="")
			 {
			 alert("Select Paid Type ");
			 }
			 else if(servType=="")
			 {
			 alert("Select Serv Type ");
			 }
			 else if(alertRequired=="")
			 {
			 alert("Select Alert Required ");
			 }
		 
		
	}
		
		 
	</script>
 
</body>
</html>