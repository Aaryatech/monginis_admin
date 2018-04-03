<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
 
 	
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
 
<body>
<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
 	 
	<c:url var="getTrayManagementReport" value="/getTrayManagementReport"></c:url> 


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
						<i class="fa fa-file-o"></i>Tray Management Report
					</h1>
					
				</div>
				
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					 
					
					<div class="box" id="datewise">
					
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i>Tray Management Report
							</h3>
							<div class="box-tool">
								 
							</div>
						</div>
						<div class=" box-content">
						<div class="form-group">
									<div class="col-md-2">From Date:</div>
									<div class="col-md-3">
										<input class="form-control date-picker" id="from_date" size="16"
											 type="text" name="from_date" required />
									
										</div>
										
										<div class="col-md-2">To Date:</div>
									<div class="col-md-3">
										<input class="form-control date-picker" id="to_date" size="16"
											 type="text" name="to_date" required />
									
										</div>
										
										
										</div><br>
						
								</div>
								<div class=" box-content">
								    <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Select Vehicle</label>
									<div class="col-sm-5 col-lg-3 controls">
										<select name="vehId" class="form-control chosen" tabindex="-1" id="vehId"  data-rule-required="true">
													<option value="0" selected>All</option>
													
													<c:forEach items="${vehicleList}" var="vehicleList"> 
													  <option value="${vehicleList.vehId}"><c:out value="${vehicleList.vehNo}"></c:out> </option>
													 </c:forEach> 
												</select>
									
										</div>
										 
										</div><br>
						
								</div>
								<div class=" box-content">
								    <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Select Route</label>
									<div class="col-sm-5 col-lg-3 controls">
										<select name="routeId" class="form-control chosen" tabindex="-1" id="routeId"  data-rule-required="true">
													<option value="0" selected>All</option>
													
													<c:forEach items="${routeList}" var="routeList"> 
													  <option value="${routeList.routeId}"><c:out value="${routeList.routeName}"></c:out> </option>
													 </c:forEach> 
												</select>
									
										</div>
										 
										</div><br>
						
								</div>
								<div class=" box-content">
								<div class="form-group">
							 
								<div align="center" class="form-group">
									<div class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
										
				
										<input type="button" class="btn btn-primary" value="View All" id="searchmixall"
											onclick="searchdatewise()">
											 <input type="button" class="btn btn-primary" value="Pdf"  onclick="getPdf()"> 
								 

									</div><br>
									
									<div align="center" id="loader" style="display: none">

									<span>
										<h4>
											<font color="#343690">Loading</font>
										</h4>
									</span> <span class="l-1"></span> <span class="l-2"></span> <span
										class="l-3"></span> <span class="l-4"></span> <span
										class="l-5"></span> <span class="l-6"></span>
								</div>	
									
									
								</div>
								</div>
								</div>
								
							

						<div class=" box-content">
					<div class="row">
					
					
						<div class="col-md-12 table-responsive">
							<div style="overflow:scroll;height:100%;width:100%;overflow:auto">
									<table width="100%" height="100%" border="0"class="table table-bordered table-striped fill-head "
										style="width: 100%" id="table_grid2">
								<thead>
									<tr>
										<th style="text-align:center;">Sr.No.</th> 
										<th style="text-align:center;">Date</th> 
										<th style="text-align:center;">Route</th>
										<th style="text-align:center;">Vehicle No</th>
										<th style="text-align:center;">Driver Name</th>
										<th style="text-align:center;">Running Km</th>
										<th style="text-align:center;">Diesel</th>
										<th style="text-align:center;">AVG</th>
										  
										
									</tr>
								</thead>
								
								<tbody>
									 
										
									
								</tbody>
							</table>
							
							 
											
							 
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
		</div>
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
	 
	</script>
		<script type="text/javascript">
		
		function searchdatewise() {
			var fromDate = $("#from_date").val();
			var toDate = $("#to_date").val();
			var routeId = $("#routeId").val();
			var vehId = $("#vehId").val();
		 
			 
			$('#loader').show();

			$
					.getJSON(
							'${getTrayManagementReport}',

							{
								 
								fromDate : fromDate,
								toDate : toDate,
								routeId : routeId,
								vehId : vehId,
								ajax : 'true'

							},
							function(data) {

								$('#table_grid2 td').remove();
								$('#loader').hide();

								if (data == "") {
									alert("No records found !!");
									 
								}
							 

							  $.each( data, function(key, itemList) {
								   
												 
												var tr = $('<tr></tr>');
											  	tr.append($('<td></td>').html(key+1)); 
											  	tr.append($('<td></td>').html(itemList.tranDate)); 
											  	tr.append($('<td></td>').html(itemList.routeName));
											  	tr.append($('<td></td>').html(itemList.vehNo));
											  	tr.append($('<td></td>').html(itemList.driverName)); 
											  	
											  	tr.append($('<td style="text-align:right"></td>').html((itemList.vehRunningKm).toFixed(2))); 
											  	tr.append($('<td style="text-align:right"></td>').html((itemList.diesel).toFixed(2)));
											  	var avg = 0;
											  	if(itemList.diesel==0)
											  		avg=0;
											  	else
											  		avg=(itemList.vehRunningKm/itemList.diesel).toFixed(2)
											  	tr.append($('<td style="text-align:right"></td>').html(avg));
											  	 
												$('#table_grid2 tbody').append(tr);
												 

											})  
							});
		 
		 
	}
	</script>
	<script type="text/javascript">
 
 
function getPdf()
{
	var fromDate = $("#from_date").val();
	var toDate = $("#to_date").val();
	var routeId = $("#routeId").val();
	var vehId = $("#vehId").val();
	var valid=0;
	
		if(fromDate=="")
			{
			alert("Enter Valid From Date");
			valid=1;
			
			}
		else if(toDate=="")
			{
			alert("Enter Valid To Date");
			valid=1;
			}
	 
		if(valid==0)
		{
	    	window.open('${pageContext.request.contextPath}/Logistics?url=pdf/trayManagementPdf/'+fromDate+'/'+toDate+'/'+routeId+'/'+vehId+'/');
		}
   
    }
</script>
	
</body>
</html>