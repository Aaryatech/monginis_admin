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
						<i class="fa fa-file-o"></i> Logistics DashBoard
					</h1>
					
				</div>
				
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					<div class="box" id="todayslist">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i> AMC List
							</h3>
							 
						</div>
						
						<div class=" box-content">
					<div class="row">
					
					
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Vehicle/Machine</th> 
										<th>Dealer Name</th>
										<th>Type</th>
										<th>From Date</th>
										<th>To Date</th>
										<th>Alert Date</th>
										<th>Remaining Day</th> 

									</tr>
								</thead>
								<tbody>
								<c:set var = "srNo" value="0"/>
									<c:forEach items="${amcRecordList}" var="amcRecordList"
													varStatus="count">
													
													<c:choose>
														<c:when test="${amcRecordList.remainingDay<0}">
														<c:set var="days" value="0"></c:set>
														<c:set var = "color" value="red"/>
														</c:when>
														<c:otherwise>
														<c:set var="days" value="${amcRecordList.remainingDay}"></c:set>
														<c:set var = "color" value="black"/>
														</c:otherwise>
													</c:choose>
													
													<c:choose>
														<c:when test="${amcRecordList.typeId==1}">
														<c:set var="type" value="Vehicle"></c:set>
														</c:when>
														<c:otherwise>
														<c:set var="type" value="Machine"></c:set>
														</c:otherwise>
													</c:choose>
													 
													<tr>
														<td style="color: <c:out value = "${color}"/>"><c:out value="${count.index+1}" /></td>
 														<c:set var = "srNo" value="${count.index}"/> 
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${amcRecordList.mechName}" /></td> 
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${amcRecordList.dealerName}" /></td> 
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${type}" /></td>
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${amcRecordList.amcFromDate}" /></td>
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${amcRecordList.amcToDate}" /></td>
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${amcRecordList.amcAlertDate}" /></td>
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${days}" /></td>
														 
													 
												</tr>
												</c:forEach>

								</tbody>
							</table>
						</div>
					</div>

		</div>
					</div>
						
						<div class="box" id="todayslist">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i> Driver License Expired Record
							</h3>
							 
						</div>
						
						<div class=" box-content">
					<div class="row">
					
					
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Driver Name</th> 
										<th>Address</th>
										<th>Mobile No</th>
										<th>Joining Date</th>
										<th>License No</th>
										<th>Expired Date</th> 

									</tr>
								</thead>
								<tbody>
								<c:set var = "srNo" value="0"/>
									<c:forEach items="${alertDriverMasterList}" var="alertDriverMasterList"
													varStatus="count">
													
													<c:set var = "color" value="red"/>
													  
													 
													<tr>
														<td style="color: <c:out value = "${color}"/>"><c:out value="${count.index+1}" /></td>
 														<c:set var = "srNo" value="${count.index}"/> 
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${alertDriverMasterList.driverName}" /></td> 
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${alertDriverMasterList.address1}" /></td> 
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${alertDriverMasterList.mobile1}" /></td>
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${alertDriverMasterList.joiningDate}" /></td>
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${alertDriverMasterList.licNo}" /></td>
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${alertDriverMasterList.licExpireDate}" /></td> 
														 
													 
												</tr>
												</c:forEach>

								</tbody>
							</table>
						</div>
					</div>

		</div>
					</div>
						<div class="box" id="todayslist">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i> Vehicle Document List
							</h3>
							 
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
										<th>Expired Date</th>
										<th>Alert Date</th>
										<th>Remaining Day</th> 

									</tr>
								</thead>
								<tbody>
								<c:set var = "srNo" value="0"/>
									<c:forEach items="${alertDocumentList}" var="alertDocumentList"
													varStatus="count">
													
													<c:choose>
														<c:when test="${alertDocumentList.remainingDay<0}">
														<c:set var="days" value="0"></c:set>
														<c:set var = "color" value="red"/>
														</c:when>
														<c:otherwise>
														<c:set var="days" value="${alertDocumentList.remainingDay}"></c:set>
														<c:set var = "color" value="black"/>
														</c:otherwise>
													</c:choose>
													 
													 
													<tr>
														<td style="color: <c:out value = "${color}"/>"><c:out value="${count.index+1}" /></td>
 														<c:set var = "srNo" value="${count.index}"/> 
 														
 														 <c:forEach items="${vehicleList}" var="vehicleList" >
														  <c:choose>
														  	<c:when test="${alertDocumentList.vehId==vehicleList.vehId}">
														  	<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${vehicleList.vehNo}" /></td>
														  	</c:when>
														  </c:choose>
														</c:forEach>
														
														 <c:forEach items="${getAllDocumentList}" var="getAllDocumentList" >
														  <c:choose>
														  	<c:when test="${alertDocumentList.docId==getAllDocumentList.docId}">
														  	<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${getAllDocumentList.docName}" /></td>
														  	</c:when>
														  </c:choose>
														</c:forEach>
														 
														 
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${alertDocumentList.entryDate}" /></td>
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${alertDocumentList.docDate}" /></td>
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${alertDocumentList.docExpireDate}" /></td>
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${alertDocumentList.docExpNotificationDate}" /></td>
														<td align="left" style="color: <c:out value = "${color}"/>"><c:out value="${days}" /></td>
														 
													 
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
		
		 
</body>
</html>