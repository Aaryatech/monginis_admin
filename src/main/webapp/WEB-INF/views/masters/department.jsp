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
						<i class="fa fa-file-o"></i>Department
					</h1>

				</div>
			</div>
			<!-- END Page Title -->
			showAddDepartment
				<c:set var="isAdd" value="0">
			</c:set>
			<c:set var="isEdit" value="0">
			</c:set>
			<c:set var="isView" value="0">
			</c:set>
			<c:set var="isDelete" value="0">
			</c:set>

			<c:forEach items="${sessionScope.newModuleList}" var="modules">
				<c:forEach items="${modules.subModuleJsonList}" var="subModule">

					<c:choose>
						<c:when test="${subModule.subModuleMapping eq 'showAddDepartment'}">
							<c:out value="${ subModule}"></c:out>
							<c:choose>
								<c:when test="${subModule.editReject=='visible'}">
									<c:set var="isEdit" value="1">
									</c:set>
								</c:when>
								<c:otherwise>
									<c:set var="isEdit" value="0">
									</c:set>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${subModule.view=='visible'}">
									<c:set var="isView" value="1">
									</c:set>
								</c:when>
								<c:otherwise>
									<c:set var="isView" value="0">
									</c:set>
								</c:otherwise>
							</c:choose>


							<c:choose>
								<c:when test="${subModule.deleteRejectApprove=='visible'}">
									<c:set var="isDelete" value="1">
									</c:set>
								</c:when>
								<c:otherwise>
									<c:set var="isDelete" value="0">
									</c:set>
								</c:otherwise>
							</c:choose>

							<c:choose>
								<c:when test="${subModule.addApproveConfig=='visible'}">
									<c:set var="isAdd" value="1">
									</c:set>
								</c:when>
								<c:otherwise>
									<c:set var="isAdd" value="0">
									</c:set>
								</c:otherwise>
							</c:choose>


						</c:when>
					</c:choose>
				</c:forEach>
			</c:forEach>

			<!-- BEGIN Main Content -->
			<div class="row">
				<div class="col-md-12">
					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-bars"></i> Add Department
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showAddDepartment"></a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>

						<div class="box-content">
							<form action="${pageContext.request.contextPath}/addDeptProcess" class="form-horizontal"
								method="post" id="validation-form">

                      <input type="hidden" name="dept_id" id="dept_id" value="${dept.deptId}"/>
                      
                      	<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Department
										Code</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="dept_code" id="dept_code"
											placeholder="Department Code" class="form-control"
											data-rule-required="true" value="${dept.deptCode}"/>
									</div>
								</div>
                               <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Department
										Name</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="dept_name" id="dept_name"
											placeholder="Department Name" class="form-control"
											data-rule-required="true" value="${dept.deptName}"/>
									</div>
								</div>
                              <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Is Active?</label>
									<div class="col-sm-9 col-lg-10 controls">
											<c:choose>
												
												<c:when test="${dept.isActive==0}">
												<label class="radio-inline"> 
												<input type="radio"
													name="is_active" id="optionsRadios1" value="0" checked>
													No
												</label> 
												<label class="radio-inline"> <input type="radio"
													name="is_active" id="optionsRadios1" value="1" />
													Yes
												</label>
												</c:when>
												
												<c:when test="${dept.isActive==1}">
												<label class="radio-inline"> <input type="radio"
													name="is_active" id="optionsRadios1" value="0" />
													No
												</label>
												<label class="radio-inline"> <input type="radio"
													name="is_active" id="optionsRadios1" value="1" checked />
													Yes
												</label>
												</c:when>
												<c:otherwise>
												<label class="radio-inline"> 
												<input type="radio"
													name="is_active" id="optionsRadios1" value="0" >
													No
												</label> 
												<label class="radio-inline"> <input type="radio"
													name="is_active" id="optionsRadios1" value="1" checked/>
													Yes
												</label>
												
												</c:otherwise>
												</c:choose>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
										<input type="submit" class="btn btn-primary" value="Submit">
<!-- 										<button type="button" class="btn">Cancel</button>
 -->									</div>
								</div>
							</form>
							<br>
								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i>Department List
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
														<th width="100" align="left">Department Code</th>
														<th width="100" align="left">Department Name</th>
														<th width="100" align="left">IsActive</th>
														
														<th width="81" align="left">Action</th>
													</tr>
												</thead>
												<tbody>
													  <c:forEach items="${deptList}" var="deptList" varStatus="count">
														<tr>
														
															<td><c:out value="${count.index+1}"/></td>
															<td align="left"><c:out
																	value="${deptList.deptCode}"></c:out></td>
															<td align="left"><c:out
																	value="${deptList.deptName}"></c:out></td>
															<c:choose>
															<c:when test="${deptList.isActive==0}">
															<td align="left"><c:out
																	value="InActive"></c:out></td>	
															</c:when>		
															<c:when test="${deptList.isActive==1}">
															<td align="left"><c:out
																	value="Active"></c:out></td>	
															</c:when>		
															</c:choose>		
															<td align="left">
															<c:choose>
															<c:when test="${isEdit==1 }">
																<a href="updateDept/${deptList.deptId}"><span
														class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;
															</c:when>
															<c:otherwise>
																<a href="updateDept/${deptList.deptId}" class="disableClick"><span
														class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;
															
															</c:otherwise>
															</c:choose>
															<c:choose>
															<c:when test="${isDelete==1 }">
															
                                                        <a href="deleteDept/${deptList.deptId}"
													    onClick="return confirm('Are you sure want to delete this record');"><span
														class="glyphicon glyphicon-remove"></span></a>
															
															</c:when>
															<c:otherwise>
															
                                                        <a href="deleteDept/${deptList.deptId}"
													    onClick="return confirm('Are you sure want to delete this record');" class="disableClick"><span
														class="glyphicon glyphicon-remove"></span></a>
															
															</c:otherwise>
															</c:choose>
														
                                                        
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
