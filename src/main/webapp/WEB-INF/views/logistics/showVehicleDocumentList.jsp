<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<c:url var="getServicingWithDate" value="/getServicingWithDate"></c:url>
	<c:url var="getBomAllListWithDate" value="/getBomAllListWithDate"></c:url>
	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>


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
						<i class="fa fa-file-o"></i> Logistics
					</h1>
					
				</div>
				
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					<div class="box" id="todayslist">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i> Vehicle Document List
							</h3>
							<div class="box-tool">
							<a href="${pageContext.request.contextPath}/insertVehicleDocument">Insert Vehicle Document</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
								<!-- <a  onclick="showdatewisetable()">Show Datewise Record</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> -->
							</div>
						</div>
						
						<div class=" box-content">
					<div class="row">
					
					
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid1">
								<thead>
									<tr>
										<th>Sr.No.</th>
										
										<th>Vehicle No</th>
										<th>Document</th>
										<th>Entry Date</th>
										<th>Document Date</th>
										<th>Document Expire Date</th>
										<th>Expire Notification Date</th>
										<th>Current Km</th> 
										<th>Action</th>
										
									</tr>
								</thead>
								
								<tbody>
									<c:forEach items="${vehicleDocument}" var="vehicleDocument"
													varStatus="count">
												 
													<tr>
														<td><c:out value="${count.index+1}" /></td>
 
														 <c:forEach items="${vehicleList}" var="vehicleList" >
														  <c:choose>
														  	<c:when test="${vehicleDocument.vehId==vehicleList.vehId}">
														  	<td align="left"><c:out value="${vehicleList.vehNo}" /></td>
														  	</c:when>
														  </c:choose>
														</c:forEach>
														
														 <c:forEach items="${documentList}" var="documentList" >
														  <c:choose>
														  	<c:when test="${vehicleDocument.docId==documentList.docId}">
														  	<td align="left"><c:out value="${documentList.docName}" /></td>
														  	</c:when>
														  </c:choose>
														</c:forEach>
																
																<td align="left"><c:out value="${vehicleDocument.entryDate}" /></td>
																<td align="left"><c:out value="${vehicleDocument.docDate}" /></td>
																<td align="left"><c:out value="${vehicleDocument.docExpireDate}" /></td>
																<td align="left"><c:out value="${vehicleDocument.docExpNotificationDate}" /></td> 
																<td align="left"><c:out value="${vehicleDocument.currentKm}" /></td> 
																
													   
													 	 
																
						<td><a href="${pageContext.request.contextPath}/editVehicleDocument/${vehicleDocument.vehDocId}" class="action_btn" >
						<span class="glyphicon glyphicon-edit"></span></a>
						<a href="${pageContext.request.contextPath}/viewDetailVehicleDocument/${vehicleDocument.vehDocId}" class="action_btn" >
						<abbr title="Details"><i class="fa fa-list"></i></abbr></a></td>
						
																</tr>
												</c:forEach>
										
									
								</tbody>
							</table>
						</div>
					</div>

		</div>
					</div>
						
						
						
					<div class="box" id="datewise_table" style="display: none">
					
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i> Search Vehicle Document Date Wise
							</h3>
							<div class="box-tool">
							<a href="${pageContext.request.contextPath}/insertVehicleDocument">Insert Vehicle Document</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
								<a  onclick="showdatewisetable()">Pending List</a>  <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						<div class=" box-content">
						<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">From Date:</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="from_date" size="16"
											 type="text" name="from_date" required />
									
										</div>
										
										<label class="col-sm-3 col-lg-2 control-label">To Date:</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="to_date" size="16"
											 type="text" name="to_date" required />
									<input class="form-control" id="flag" value="${flag}" size="16"
											 type="hidden" name="flag"  />
										</div>
										
										
										</div><br>
						
								</div>
								
								
								
								
								
								<div class=" box-content">
								<div class="form-group">
								
								<div align="center" class="form-group">
									<div class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
										
				
										<input class="btn btn-primary" value="View All" id="searchmixall"
											onclick="searchbomall()">

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
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th>Sr.No.</th>
										
										<th>Vehicle No</th>
										<th>Document</th>
										<th>Entry Date</th>
										<th>Document Date</th>
										<th>Document Expire Date</th>
										<th>Expire Notification Date</th>
										<th>Current Km</th> 
										<th>Action</th>
										
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
	
		function searchbomall() {
			var from_date = $("#from_date").val();
			var to_date = $("#to_date").val();
			var flag = $("#flag").val();
			
			$('#loader').show();

			$
					.getJSON(
							'${getServicingWithDate}',

							{
								 
								from_date : from_date,
								to_date : to_date,
								ajax : 'true'

							},
							function(data) {

								$('#table_grid td').remove();
								$('#loader').hide();

								if (data == "") {
									alert("No records found !!");

								}
							 
 
							  $.each( data,
											function(key, itemList) {
												
												 
											 
												 var stats;
												 var type;
												 
												 if(itemList.isApproved==0)
													 {
													 stats="Pending";
													 }
												 else
													 {
													 stats="Approved";
													 }
												 
												 if(itemList.typeId==1)
												 {
													 type="Servicing";
												 }
											 	else if(itemList.typeId==2)
												 {
											 		type="Wheel";
												 }
											 	else if(itemList.typeId==3)
												 {
											 		type="Battary";
												 }
											 	else if(itemList.typeId==4)
												 {
											 		type="AC";
												 }
												 
												var tr = $('<tr></tr>');
											  	tr.append($('<td></td>').html(key+1)); 
											  	tr.append($('<td></td>').html(itemList.billNo));
											  	tr.append($('<td></td>').html(itemList.billDate)); 
											  	tr.append($('<td></td>').html(itemList.vehNo));
											  	tr.append($('<td></td>').html(type));
											  	tr.append($('<td></td>').html(itemList.taxableAmt));
											  	tr.append($('<td></td>').html(itemList.taxAmt));
											  	tr.append($('<td></td>').html(itemList.total));
											  	tr.append($('<td></td>').html(stats));
											  	tr.append($('<td ></td>').html("<a href='${pageContext.request.contextPath}/viewServicingDetail/"+itemList.servId+"/"+flag+"' class='action_btn'> <abbr title='detailed'> <i class='fa fa-list' ></i></abbr> "));
												
												$('#table_grid tbody').append(tr); 
											 
											})  
							});

		 
	}
	</script>
	
	<script type="text/javascript">
	var flag=0
	function showdatewisetable() {
		
		if(flag==0)
		{
			$('#todayslist').hide();
			$("#datewise_table").show();
			flag=1;
		}
		else if(flag==1)
		{
			$('#todayslist').show();
			$("#datewise_table").hide();
			flag=0;
		}
	 
}
		
	</script>
</body>
</html>