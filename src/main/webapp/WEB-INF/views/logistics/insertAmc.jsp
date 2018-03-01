<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
 <c:url var="machineListOrVehicleList" value="/machineListOrVehicleList"></c:url>
 <c:url var="getDealerIdBymechId" value="/getDealerIdBymechId"></c:url>
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
								<i class="fa fa-table"></i>Insert AMC
							</h3>
							
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showAmcList">Show AMC List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						
						
						<div class="box-content">

							<form id="submitMaterialStore" action="${pageContext.request.contextPath}/submitAmc" method="post"
							enctype="multipart/form-data">
							<input type="hidden" id="machineId" name="machineId"   class="form-control"   >
							<div class="box-content">
								<div class="col-md-2">Select Type* </div>
								<div class="col-md-3">
								 <select name="typeId" id="typeId" class="form-control chosen" tabindex="6" required>
											<option value="">Select Option</option>
											<option value="1">Vehicle</option>
											<option value="2">Machine</option>
										</select>
								</div>
								
							<div class="col-md-2">Select Machine Or Vehicle* </div> 
								<div class="col-md-3">
							 
                                    <select name="mechId" id="mechId" class="form-control chosen" tabindex="6" required>
											 
										</select>
								</div>
					
							</div><br>
							
							<div class="box-content"> 
							<div class="col-md-2">Select Dealer* </div> 
								<div class="col-md-3">
							 
                                    <select name="dealerId" id="dealerId" class="form-control chosen" tabindex="6" required>
                                    <option value="">Select Dealer</option>
                                    <c:forEach items="${dealerList}" var="dealerList">
                                    	<option value="${dealerList.dealerId}">${dealerList.dealerName}</option> 
                                    </c:forEach>
											 
										</select>
										
										<input class="form-control" id="mechName" placeholder="AMC From Date" size="16"
											type="hidden" name="mechName"  required />
											<input class="form-control" id="dealerName" placeholder="AMC From Date" size="16"
											type="hidden" name="dealerName"  required />
								</div>
								
				 
							</div><br>
							 
							<div class="box-content">
							
									<div class="col-md-2">AMC From Date* </div>
									<div class="col-md-3">
									<input class="form-control date-picker" id="fromDate" placeholder="AMC From Date" size="16"
											type="text" name="fromDate"  required />
									</div>
						
									<div class="col-md-2">AMC To Date* </div>
									<div class="col-md-3">
									<input class="form-control date-picker" id="toDate" onchange="nulldays();"  placeholder="AMC To Date" size="16"
											type="text" name="toDate"  required />
									</div>
							
							</div><br>
							 
							
							<div class="box-content">
							
									<div class="col-md-2">AMC Alert Frequency(Days)* </div>
									<div class="col-md-3">
									<input class="form-control" id="amcAlertFrq" onchange="addDays();" placeholder="AMC Alert Frequency" size="16"
											type="text" name="amcAlertFrq"  required />
									</div>
						
									<div class="col-md-2">AMC Alert Date* </div>
									<div class="col-md-3">
									<input class="form-control" id="alertDate" placeholder="AMC Alert Date" size="16"
											type="text" name="alertDate"  readonly />
									</div>
							
							</div><br>
							
							<div class="box-content">
							
									<div class="col-md-2">Bill No* </div>
									<div class="col-md-3">
									<input class="form-control" id="billNo" placeholder="Bill No" size="16"
											type="text" name="billNo"  required />
									</div>
									
									<div class="col-md-2">Taxable Amt* </div>
									<div class="col-md-3">
									<input class="form-control" id="taxableAmt" placeholder="Taxable Amt" size="16"
											type="text" name="taxableAmt"  required />
									</div>
						 
							
							</div><br> 
							  
							
							<div class="box-content">
							
									<div class="col-md-2">Tax Amt* </div>
									<div class="col-md-3">
									<input class="form-control" id="taxAmt" placeholder="Tax Amt" size="16"
											type="text" name="taxAmt"  required />
									</div>
									
									<div class="col-md-2">Total* </div>
									<div class="col-md-3">
									<input class="form-control" id="total" placeholder="Total" size="16"
											type="text" name="total"  required />
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
		
		 
	function validation() {

       
		 var typeId = $("#typeId").val(); 
		 var dealerId = $("#dealerId").val(); 
		 var mechId = $("#mechId").val(); 
		 if(typeId=="")
			 {
			 alert("Select Type ");
			 } 
		 else if(mechId=="")
		 {
		 alert("Select Select Machine Or Vehicle ");
		 }
		 else if(dealerId=="")
		 {
		 alert("Select Dealer ");
		 }
	 
	
}
	
	  
		$(document).ready(function() { 
			$('#typeId').change(
					function() {
						//alert("makeId"+$(this).val());
						var typeId=$(this).val(); 
					    
						$.getJSON('${machineListOrVehicleList}', {
							
							typeId : $(this).val(), 
							ajax : 'true'
						},
								function(data) {
							 
							var html = '<option value="">Select Option</option>';
							
							var len = data.length;
							for ( var i = 0; i < len; i++) {
								html += '<option value="' + data[i].mechId + '">'
										+ data[i].mechName + '</option>';
							}
							html += '</option>';
							$('#mechId').html(html);
							$("#mechId").trigger("chosen:updated");
													
								});
					 
						 
				})
				 		 
		});
		
		$(document).ready(function() { 
			$('#mechId').change(
					function() {
						//alert("mechId"+$(this).val());
						var mechId=$(this).val();  
						var mechName = $("#mechId option:selected").text();
						$.getJSON('${getDealerIdBymechId}', {
							
							mechId : $(this).val(), 
							ajax : 'true'
						},
								function(data) {
							 //alert(data);
							document.getElementById("dealerId").value=data;
							$("#dealerId").trigger("chosen:updated");
							var dealerName = $("#dealerId option:selected").text();
							document.getElementById("mechName").value=mechName;		
							document.getElementById("dealerName").value=dealerName;	
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
		 
		/* function addDays()
		{
			alert("dd");
			var selectedDate = $("#toDate").val();
			var days = $("#amcAlertFrq").val();
			alert(selectedDate);
			alert(days);
			var returnDate = new Date(
					selectedDate.getFullYear(),
					selectedDate.getMonth(),
					selectedDate.getDate()-days,
					selectedDate.getHours(),
					selectedDate.getMinutes(),
					selectedDate.getSeconds());
			alert("returnDate"+returnDate);
			document.getElementById("alertDate").value=returnDate; 
		} */
		
		function addDays() {
			/* var d = new Date(); */
			var selectedDate = $("#toDate").val().split("-");
			var d = new Date(selectedDate[2],selectedDate[1] - 1, selectedDate[0]);
			var days = $("#amcAlertFrq").val();
			var todayTimeStamp1 = +d; // Unix timestamp in milliseconds
			d.setDate(d.getDate());
			var todayTimeStamp = +d; // Unix timestamp in milliseconds
			var oneDayTimeStamp = 1000 * 60 * 60 * 24; // Milliseconds in a day
			 
			var diff = todayTimeStamp - (oneDayTimeStamp*days);
			var yesterdayDate = new Date(diff);  

			var tommarowString =  yesterdayDate.getDate()+ '-' + (yesterdayDate.getMonth() + 1) + '-' +yesterdayDate.getFullYear();
			document.getElementById("alertDate").value=tommarowString; 
		}
		
		function nulldays() {
			alert("null");
			document.getElementById("amcAlertFrq").value=""; 
		}
		 
	</script>
 
</body>
</html>