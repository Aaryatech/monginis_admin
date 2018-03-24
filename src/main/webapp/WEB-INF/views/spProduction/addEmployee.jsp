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
						<i class="fa fa-file-o"></i>Employee
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
								<i class="fa fa-bars"></i> Add Employee
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showAddEmployee"></a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>

						<div class="box-content">
							<form action="${pageContext.request.contextPath}/addEmpProcess" class="form-horizontal"
								method="post" id="validation-form">

                      <input type="hidden" name="emp_id" id="emp_id" value="${emp.empId}"/>
                      
								<div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Employee
										Name</label>
									<div class="col-sm-3 col-lg-4 controls">
										<input type="text" name="emp_name" id="emp_name"
											placeholder="Employee Name" class="form-control"
											data-rule-required="true" value="${emp.empName}"/>
									</div>
								</div>
                               <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Department Name</label>
									<div class="col-sm-3 col-lg-4 controls">
									<select name="dept_id" id="dept_id" class="form-control" placeholder="Department Name" data-rule-required="true">
											<option value="-1">Select Department Name</option>
										    	<c:forEach items="${mDeptList}" var="mDeptList">
										    	<c:choose>
													<c:when test="${mDeptList.deptId==emp.deptId}">
												          <option value="${mDeptList.deptId}" selected><c:out value="${mDeptList.deptName}"></c:out></option>
													</c:when>
													<c:otherwise>
										            	  <option value="${mDeptList.deptId}"><c:out value="${mDeptList.deptName}"></c:out></option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
								</select>	
									</div>
								</div>
								  <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Employee Type</label>
									<div class="col-sm-3 col-lg-4 controls">
									<select name="emp_type" id="emp_type" class="form-control" placeholder="Employee Type" data-rule-required="true">
											<option value="-1">Select Employee Type</option>
											<c:forEach items="${typeList}" var="typeList">
											<c:choose>
													<c:when test="${typeList.typeId==emp.empType}">
												          <option value="${typeList.typeId}" selected><c:out value="${typeList.typeName}"></c:out></option>
													</c:when>
													<c:otherwise>
										            	  <option value="${typeList.typeId}"><c:out value="${typeList.typeName}"></c:out></option>
													</c:otherwise>
												</c:choose>
										</c:forEach>
												
								</select>	
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Emp Family Members
										</label>
									<div class="col-sm-3 col-lg-4 controls">
										<input type="text" name="emp_fam_memb" id="emp_fam_memb"
											placeholder="Employee Family Member" class="form-control"
											data-rule-required="true" value="${emp.empFamMemb}"/>
									</div>
								</div>
								  <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Employee Discount</label>
									<div class="col-sm-3 col-lg-4 controls">
									<select name="disc_id" id="disc_id" class="form-control" placeholder="Employee Discount" data-rule-required="true">
											<option value="-1">Select Employee Discount</option>
											<c:forEach items="${discountList}" var="discountList">
											<c:choose>
													<c:when test="${discountList.discountId==emp.discId}">
												          <option value="${discountList.discountId}" selected><c:out value="${discountList.discountHead}"></c:out></option>
													</c:when>
													<c:otherwise>
										            	  <option value="${discountList.discountId}"><c:out value="${discountList.discountHead}"></c:out></option>
													</c:otherwise>
												</c:choose>
										</c:forEach>
												
								</select>	
									</div>
								</div>
									<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Mobile No.
										</label>
									<div class="col-sm-3 col-lg-4 controls">
										<input type="number" name="emp_mob" id="emp_mob"
											placeholder="Employee Mobile No." class="form-control" pattern="^\d{10}$" required
											data-rule-required="true" value="${emp.empMobile}"/>
									</div>
								</div>
									<div class="col2">
											<label class="col-sm-3 col-lg-2 control-label">Date of Birth</label>
											<div class="col-sm-3 col-lg-4 controls">
												<input class="form-control date-picker" id="emp_dob" size="16"
													type="text" name="emp_dob"
													required placeholder="Date of Birth" value="${emp.empDob}"/>
											</div>
									</div>
									<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Date of Joining</label>
											<div class="col-sm-3 col-lg-4 controls">
												<input class="form-control date-picker" id="emp_doj" size="16"
													type="text" name="emp_doj"
													required placeholder="Date of Joining" value="${emp.empDoj}"/>
											</div>
									</div>
										<div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Monthly Limit
										</label>
									<div class="col-sm-3 col-lg-4 controls">
										<input type="number" name="monthly_limit" id="monthly_limit"
											placeholder="Monthly Limit" class="form-control"
											data-rule-required="true" value="${emp.monthlyLimit}"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Yearly Limit
										</label>
									<div class="col-sm-3 col-lg-4 controls">
										<input type="number" name="yearly_limit" id="yearly_limit"
											placeholder="Yearly Limit" class="form-control"
											data-rule-required="true" value="${emp.yearlyLimit}"/>
									</div>
								</div>
                              <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Is Used?</label>
									<div class="col-sm-3 col-lg-4 controls">
											<c:choose>
												
												<c:when test="${emp.isUsed==0}">
												
												<label class="radio-inline"> <input type="radio"
													name="is_used" id="optionsRadios1" value="1" />
													Yes
												</label>
												<label class="radio-inline"> 
												<input type="radio"
													name="is_used" id="optionsRadios1" value="0" checked>
													No
												</label> 
												</c:when>
												
												<c:when test="${emp.isUsed==1}">
											
												<label class="radio-inline"> <input type="radio"
													name="is_used" id="optionsRadios1" value="1" checked />
													Yes
												</label>
													<label class="radio-inline"> <input type="radio"
													name="is_used" id="optionsRadios1" value="0" />
													No
												</label>
												</c:when>
												<c:otherwise>
												 
												<label class="radio-inline"> <input type="radio"
													name="is_used" id="optionsRadios1" value="1" checked/>
													Yes
												</label>
												<label class="radio-inline"> 
												<input type="radio"
													name="is_used" id="optionsRadios1" value="0" >
													No
												</label>
												</c:otherwise>
												</c:choose>
									</div>
								</div>

<br>
								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Submit">
<!-- 										<button type="button" class="btn">Cancel</button>
 -->									</div>
								</div>
							</form>
							<br>
								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i>Employee List
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
										</div>
									</div>

									<div class="box-content">
<jsp:include page="/WEB-INF/views/include/tableSearch.jsp"></jsp:include>

										<div class="clearfix"></div>
							<div id="table-scroll" class="table-scroll">
								 		
								<div id="faux-table" class="faux-table" aria="hidden">
									<table id="table2" class="main-table">
											<thead>
												<tr class="bgpink">
												<th width="45" style="width: 18px">Sr.No.</th>
														<th width="100" align="left">Employee Name</th>
														<th width="100" align="left">Department Name</th>
														<th width="100" align="left">Mobile No.</th>
														<th width="100" align="left">Monthly Limit</th>
														<th width="100" align="left">Yearly Limit</th>
													
														
														<th width="81" align="left">Action</th>
												</tr>
												</thead>
												</table>
									
									</div>
									<div class="table-wrap">
									
										<table id="table1" class="table table-advance">
											<thead>
												<tr class="bgpink">
												<th width="45" style="width: 18px">Sr.No.</th>
														<th width="100" align="left">Employee Name</th>
														<th width="100" align="left">Department Name</th>
														<th width="100" align="left">Mobile No.</th>
														<th width="100" align="left">Monthly Limit</th>
														<th width="100" align="left">Yearly Limit</th>
													
														
														<th width="81" align="left">Action</th>
												</tr>
												</thead> 
                             <!--        <div class="table-responsive" style="border: 0">
											<table width="100%" class="table table-advance" id="table1">
												<thead>
													<tr>
														<th width="45" style="width: 18px">Sr.No.</th>
														<th width="100" align="left">Employee Name</th>
														<th width="100" align="left">Department Name</th>
														<th width="100" align="left">Mobile No.</th>
														<th width="100" align="left">Monthly Limit</th>
														<th width="100" align="left">Yearly Limit</th>
													
														
														<th width="81" align="left">Action</th>
													</tr>
												</thead>  -->
												<tbody>
													  <c:forEach items="${employeeList}" var="employeeList" varStatus="count">
														<tr>
														
															<td><c:out value="${count.index+1}"/></td>
															<td align="left"><c:out
																	value="${employeeList.empName}"></c:out></td>
															
															<td align="left"><c:out
																	value="${employeeList.deptName}"></c:out></td>
															<td align="left"><c:out
																	value="${employeeList.empMobile}"></c:out></td>
															<td align="left"><c:out
																	value="${employeeList.monthlyLimit}"></c:out></td>
															<td align="left"><c:out
																	value="${employeeList.yearlyLimit}"></c:out></td>
															<%-- <c:choose>
															
															<c:when test="${employeeList.isUsed==0}">
															<td align="left"><c:out
																	value="NO"></c:out></td>
															</c:when>
															<c:when test="${employeeList.isUsed==1}">
																<td align="left"><c:out
																	value="YES"></c:out></td>
															</c:when>
															</c:choose>		 --%>	
															<td align="left"><a href="${pageContext.request.contextPath}/updateEmp/${employeeList.empId}"><span
														class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;
                                                        
                                                        <a href="${pageContext.request.contextPath}/deleteEmp/${employeeList.empId}"
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
</html>
