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
						<i class="fa fa-file-o"></i>Configure Station
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
								<i class="fa fa-bars"></i>Configure Station
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showAddEmployee"></a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>

						<div class="box-content">
							<form action="${pageContext.request.contextPath}/addStationAllocation" class="form-horizontal"
								method="post" id="validation-form">

                      <input type="hidden" name="allocation_id" id="allocation_id" value="${cnfStation.allocationId}"/>
                      
								
                               <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Station</label>
									<div class="col-sm-1 col-lg-4 controls">
									<select name="st_id" id="st_id" class="form-control" placeholder="Select Station" data-rule-required="true">
											<option value="-1">Select Station</option>
											<c:forEach items="${spStationList}" var="spStationList">
										    	<c:choose>
													<c:when test="${spStationList.stId==cnfStation.stationId}">
												          <option value="${spStationList.stId}" selected><c:out value="${spStationList.stName}"></c:out></option>
													</c:when>
													<c:otherwise>
										            	  <option value="${spStationList.stId}"><c:out value="${spStationList.stName}"></c:out></option>
													</c:otherwise>
												</c:choose>
											</c:forEach> 
								</select>	
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Shift</label>
									<div class="col-sm-2 col-lg-4 controls">
									<select name="shift_id" id="shift_id" class="form-control" placeholder="Select Shift" data-rule-required="true">
											<option value="-1">Select Shift</option>
										  	<c:forEach items="${shiftList}" var="shiftList">
										    	<c:choose>
													<c:when test="${shiftList.shiftId==cnfStation.shiftId}">
												          <option value="${shiftList.shiftId}" selected><c:out value="${shiftList.shiftName}"></c:out></option>
													</c:when>
													<c:otherwise>
										            	  <option value="${shiftList.shiftId}"><c:out value="${shiftList.shiftName}"></c:out></option>
													</c:otherwise>
												</c:choose>
											</c:forEach> 
								</select>	
									</div>
								</div>
							<%-- 	</c:otherwise>
								</c:choose> --%>
								  <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Mistry</label>
									<div class="col-sm-9 col-lg-10 controls">
									<select name="m_id" id="m_id" class="form-control" placeholder="Select Mistry" data-rule-required="true">
											<option value="-1">Select Mistry</option>
										  	<c:forEach items="${employeeList}" var="employeeList">
										    	<c:choose>
													<c:when test="${employeeList.empId==cnfStation.empMistryId}">
												          <option value="${employeeList.empId}" selected><c:out value="${employeeList.empName}"></c:out></option>
													</c:when>
													<c:otherwise>
										            	  <option value="${employeeList.empId}"><c:out value="${employeeList.empName}"></c:out></option>
													</c:otherwise>
												</c:choose>
											</c:forEach> 
								</select>	
									</div>
								</div>  
                                  <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Helper</label>
									<div class="col-sm-9 col-lg-10 controls">
									<select name="h_id" id="h_id" class="form-control" placeholder="Select Helper" required>
											<option value="-1">Select Helper</option>
										    <c:forEach items="${helperList}" var="employeeList">
										    	<c:choose>
													<c:when test="${employeeList.empId==cnfStation.empHelperId}">
												          <option value="${employeeList.empId}" selected><c:out value="${employeeList.empName}"></c:out></option>
													</c:when>
													<c:otherwise>
										            	  <option value="${employeeList.empId}"><c:out value="${employeeList.empName}"></c:out></option>
													</c:otherwise>
												</c:choose>
											</c:forEach> 
								</select>	
									</div>
								</div>  

								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
										<input type="submit" class="btn btn-primary" value="Submit" onclick="return validation()">
									 <a href="${pageContext.request.contextPath}/configureStation">	<button type="button" class="btn">Cancel</button></a>
									</div>
								</div>
							</form>
							<br>
								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i>Configured Stations
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
										</div>
									</div>

									<div class="box-content">

										<div class="clearfix"></div>
										<div class="table-responsive" style="border: 0">
											<table width="100%" class="table table-advance" id="table1">
												<thead>
													<tr>
														<th width="45" style="width: 18px">Sr.No.</th>
														<th width="100" align="left">Shift</th>
														<th width="100" align="left">Station</th>
														<th width="100" align="left">Mistry Name</th>
														<th width="100" align="left">Helper Name</th>
														<th width="81" align="left">Action</th>
													</tr>
												</thead>
												<tbody>
													  <c:forEach items="${stationAllocList}" var="stationAllocList" varStatus="count">
														<tr>
														
															<td><c:out value="${count.index+1}"/></td>
															<td align="left"><c:out
																	value="${stationAllocList.shiftName}"></c:out></td>
															<td align="left"><c:out
																	value="${stationAllocList.stName}"></c:out></td>		
															<td align="left"><c:out
																	value="${stationAllocList.empMistryName}"></c:out></td>
															<td align="left"><c:out
																	value="${stationAllocList.empHelperName}"></c:out></td>
																			
															<td align="left"><a href="${pageContext.request.contextPath}/updateStationAllocation/${stationAllocList.allocationId}"><span
														class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;
                                                        
                                                        <a href="${pageContext.request.contextPath}/deleteStationAllocation/${stationAllocList.allocationId}"
													    onClick="return confirm('Are you sure want to delete this record');"><span
														class="glyphicon glyphicon-remove"></span></a></td>	
														 
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
<script type="text/javascript">
function validation() {
	
	var stId = $("#st_id").val();
	var shiftId = $("#shift_id").val();
	var mId = $("#m_id").val();
	var hId = $("#h_id").val();
	
	var isValid = true;
	if (stId==-1) { 
		isValid = false;
		alert("Please Select Station");
	} else if (shiftId ==-1) {
		isValid = false;
		alert("Please Select Shift");
	}else if (mId ==-1) {
		isValid = false;
		alert("Please Select Mistry");
	}else if (hId==-1) {
		isValid = false;
		alert("Please Select Helper");
	}
	return isValid;
}

</script>
<script type="text/javascript">
function hideStationAndShift(isEdit)
{
	if(isEdit==1)
		{
		$('#st_id').css('pointer-events','none'); 
		$('#shift_id').css('pointer-events','none'); 


		}

}
</script>
</html>
