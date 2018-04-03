<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
 	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
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
						<i class="fa fa-file-o"></i>Cake Allocation
					</h1>

				</div>
			</div>
			<!-- END Page Title -->



			<!-- BEGIN Main Content -->
			<div class="row">
				<div class="col-md-12">
					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-bars"></i>Cake Completion Status
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showSpCksAllocToStation">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>


						<div class="box-content">
							<form action="addCkAllocToStation" method="post" class="form-horizontal" id=
									"validation-form"
										 method="post">
							

								<div class="col2">
								 <c:forEach items="${stWiseCkList}" var="stWiseCkList">
									<label class="col-sm-2 col-lg-2 control-label">${stWiseCkList.stName}[${stWiseCkList.compQty}/${stWiseCkList.totQty}]</label>
									</c:forEach>
								</div>
								<br/><br/>
								<div class="box"><div class="box-title">
										<h3>
											<i class="fa fa-record"></i>Cake Allocation To Station
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											
										</div>
									</div></div>
								<div class="form-group">
									<label class="col-sm-2 col-lg-2 control-label">Station</label>
									<div class="col-sm-6 col-lg-10 controls">
                                    <select name="st_id" id="st_id" class="form-control" placeholder="Station" data-rule-required="true" >
											<option value="">Select Station</option>
									 <c:forEach items="${spStationList}" var="spStationList">
								         <option value="${spStationList.stId}"><c:out value="${spStationList.stName}"></c:out></option>
									 
									</c:forEach>
								   </select>									
								   </div>

								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Shift</label>
									<div class="col-sm-2 col-lg-4 controls">
									<select name="shift_id" id="shift_id" class="form-control" placeholder="Select Shift" data-rule-required="true">
											<option value="">Select Shift</option>
										  	<c:forEach items="${shiftList}" var="shiftList">
										    	 <option value="${shiftList.shiftId}"><c:out value="${shiftList.shiftName}"></c:out></option>
											</c:forEach> 
								</select>	
									</div>
								</div>
							  <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Special Cake</label>
									<div class="col-sm-9 col-lg-10 controls">
									<select name="spck_id" id="spck_id[]" class="form-control chosen" placeholder="Select Special Cake" data-rule-required="true" multiple="multiple">
										 <option value="-1">Select Special Cake</option>
									     <c:forEach items="${stationSpCakeList}" var="stationSpCakeList">
										 <option value="${stationSpCakeList.spOrderNo}"><c:out value="${stationSpCakeList.spCode}-${stationSpCakeList.spName}"></c:out></option>
										 </c:forEach>
								</select>	
									</div>
								</div>
								
					<div class="row">
						<div class="col-md-12" style="text-align: center">
							<input type="submit" class="btn btn-info" value="Submit" name="add" id="add">


						</div>
					</div>
					</form>
					
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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>

			<script type="text/javascript"
				src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
			<script type="text/javascript"
				src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>
				
</body>


</html>

