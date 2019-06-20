<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body onload="funRandomNumberonLoad()">
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
	<c:url var="checkuniqueEmpCodeProcess" value="/checkuniqueEmpCodeProcess"></c:url>

	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/tableSearch.css">


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
								<a href="${pageContext.request.contextPath}/showAddHrEmp"></a> <a
									data-action="collapse" href="#"><i class="fa fa-chevron-up"></i></a>
							</div>

						</div>

						<div class="box-content">
							<form action="${pageContext.request.contextPath}/addHrEmp"
								class="form-horizontal" method="post" id="validation-form"
								enctype="multipart/form-data">

								<input type="hidden" name="emp_id" id="emp_id"
									value="${emp.empId}" /> <input type="hidden" name="oldDsc"
									id="oldDsc" value="${emp.empDsc}" />

								<div>
									<input type="hidden" name="prevPhoto" value="${emp.empPhoto}">
								</div>

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Employee
										Photo</label>
									<div class="col-sm-9 col-lg-10 controls">
										<div class="fileupload fileupload-new"
											data-provides="fileupload">
											<div class="fileupload-new img-thumbnail"
												style="width: 200px; height: 150px;">
												<!-- <img
													src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image"
													alt="" /> -->
												<img src="${url}${emp.empPhoto}"
													onerror="this.src='${pageContext.request.contextPath}/resources/img/No_Image_Available.jpg';" />


											</div>
											<div
												class="fileupload-preview fileupload-exists img-thumbnail"
												style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
											<div>
												<span class="btn btn-default btn-file"><span
													class="fileupload-new">Select image</span> <span
													class="fileupload-exists">Change</span> <input type="file"
													class="file-input" name="photo" id="photo" /></span> <a href="#"
													class="btn btn-default fileupload-exists"
													data-dismiss="fileupload">Remove</a>
											</div>
										</div>

									</div>
								</div>


								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Employee
											Code </label>
										<div class="col-sm-3 col-lg-4 controls">
											<input type="text" name="emp_code" id="emp_code"
												placeholder="Employee Code" class="form-control"
												onchange="checkUniqueEmpCode()" data-rule-required="true"
												value="${emp.empCode}" />
										</div>
									</div>



									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">DSC
											Code </label>
										<div class="col-sm-2 col-lg-3 controls">
											<input type="text" name="dsc" id="dsc" readonly="readonly"
												placeholder="DSC Code" class="form-control"
												data-rule-required="true" value="${emp.empDsc}" />
										</div>

										<div class="col-sm-1 col-lg-1 controls">
											<input type="button" class="btn btn-primary" value="update"
												onclick="funRandomNumber()">
										</div>

									</div>



								</div>

								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Company</label>
										<div class="col-sm-3 col-lg-4 controls">
											<select name="comp_id" id="comp_id" class="form-control"
												data-rule-required="true">
												<option value="">Select Company</option>
												<c:forEach items="${compList}" var="companyList">
													<c:choose>
														<c:when test="${companyList.companyId==emp.companyId}">
															<option value="${companyList.companyId}" selected><c:out
																	value="${companyList.companyName}"></c:out></option>
														</c:when>
														<c:otherwise>
															<option value="${companyList.companyId}"><c:out
																	value="${companyList.companyName}"></c:out></option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</div>

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Department</label>
										<div class="col-sm-3 col-lg-4 controls">
											<select name="dept_id" id="dept_id" class="form-control"
												data-rule-required="true">
												<option value="">Select Department</option>
												<c:forEach items="${deptList}" var="deptList">
													<c:choose>
														<c:when test="${deptList.empDeptId==emp.empDeptId}">
															<option value="${deptList.empDeptId}" selected><c:out
																	value="${deptList.empDeptName}"></c:out></option>
														</c:when>
														<c:otherwise>
															<option value="${deptList.empDeptId}"><c:out
																	value="${deptList.empDeptName}"></c:out></option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</div>




								</div>


								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Designation</label>
										<div class="col-sm-3 col-lg-4 controls">
											<select name="cat_id" id="cat_id" class="form-control"
												data-rule-required="true">
												<option value="">Select Designation</option>
												<c:forEach items="${catList}" var="catList">
													<c:choose>
														<c:when test="${catList.empCatId==emp.empCatId}">
															<option value="${catList.empCatId}" selected><c:out
																	value="${catList.empCatName}"></c:out></option>
														</c:when>
														<c:otherwise>
															<option value="${catList.empCatId}"><c:out
																	value="${catList.empCatName}"></c:out></option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</div>

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Location</label>
										<div class="col-sm-3 col-lg-4 controls">
											<select name="loc_id" id="loc_id" class="form-control"
												data-rule-required="true">
												<option value="">Select Location</option>
												<c:forEach items="${locList}" var="locList">
													<c:choose>
														<c:when test="${locList.locId==emp.locId}">
															<option value="${locList.locId}" selected><c:out
																	value="${locList.locName}"></c:out></option>
														</c:when>
														<c:otherwise>
															<option value="${locList.locId}"><c:out
																	value="${locList.locName}"></c:out></option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</div>

								</div>


								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Type</label>
										<div class="col-sm-3 col-lg-4 controls">
											<select name="type_id" id="type_id" class="form-control"
												data-rule-required="true">
												<option value="">Select Type</option>
												<c:forEach items="${typeList}" var="typeList">
													<c:choose>
														<c:when test="${typeList.empTypeId==emp.empTypeId}">
															<option value="${typeList.empTypeId}" selected><c:out
																	value="${typeList.empTypeName}"></c:out></option>
														</c:when>
														<c:otherwise>
															<option value="${typeList.empTypeId}"><c:out
																	value="${typeList.empTypeName}"></c:out></option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</div>

								</div>



								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">First
											Name </label>
										<div class="col-sm-3 col-lg-4 controls">
											<input type="text" name="f_name" id="f_name"
												placeholder="First Name" class="form-control"
												data-rule-required="true" value="${emp.empFname}" />
										</div>
									</div>

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Middle
											Name </label>
										<div class="col-sm-3 col-lg-4 controls">
											<input type="text" name="m_name" id="m_name"
												placeholder="Middle Name" class="form-control"
												data-rule-required="true" value="${emp.empMname}" />
										</div>
									</div>
								</div>


								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Last
											Name </label>
										<div class="col-sm-3 col-lg-4 controls">
											<input type="text" name="l_name" id="l_name"
												placeholder="Last Name" class="form-control"
												data-rule-required="true" value="${emp.empSname}" />
										</div>
									</div>


								</div>


								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Contact
											Number 1 </label>
										<div class="col-sm-3 col-lg-4 controls">
											<input type="text" name="mobile1" id="mobile1" maxlength="10"
												placeholder="Contact Number" class="form-control"
												pattern="^\d{10}$" data-rule-required="true"
												value="${emp.empMobile1}" />
										</div>
									</div>

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Contact
											Number 2 </label>
										<div class="col-sm-3 col-lg-4 controls">
											<input type="text" name="mobile2" id="mobile2" maxlength="10"
												placeholder="Contact Number" class="form-control"
												pattern="^\d{10}$" data-rule-required="true"
												value="${emp.empMobile2}" />
										</div>
									</div>
								</div>



								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Select
											Blood Group </label>
										<div class="col-sm-3 col-lg-4 controls">
											<select name="bloodGrp" data-placeholder="Please Select"
												id="bloodGrp"
												class="form-control form-control-select2 select2-hidden-accessible"
												tabindex="" aria-hidden="true" data-rule-required="true">
												<option value="">Please Select</option>

												<option ${emp.empBloodgrp == '0'  ? 'Selected': '' }
													value="0">A+</option>
												<option ${emp.empBloodgrp == '1' ? 'Selected': '' }
													value="1">O+</option>
												<option ${emp.empBloodgrp == '2'  ? 'Selected': '' }
													value="2">B+</option>
												<option ${emp.empBloodgrp == '3' ? 'Selected': '' }
													value="3">AB+</option>
												<option ${emp.empBloodgrp == '4'  ? 'Selected': '' }
													value="4">A-</option>
												<option ${emp.empBloodgrp == '5'  ? 'Selected': '' }
													value="5">O-</option>
												<option ${emp.empBloodgrp == '6'  ? 'Selected': '' }
													value="6">B-</option>
												<option ${emp.empBloodgrp == '7'  ? 'Selected': '' }
													value="7">AB-</option>


											</select> <span class="validation-invalid-label" id="error_bloodGrp"
												style="display: none;">This field is required.</span>
										</div>
									</div>


									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Email </label>
										<div class="col-sm-3 col-lg-4 controls">
											<input type="text" name="email" id="email"
												placeholder="Email Address" class="form-control"
												data-rule-required="true" value="${emp.empEmail}" />
										</div>
									</div>
								</div>


								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Gender
										</label>
										<div class="col-sm-3 col-lg-4 controls">


											<c:choose>
												<c:when test="${emp.gender==1}">
													<label class="radio-inline"> <input type="radio"
														name="gender" id="gender" value="1" checked /> Male
													</label>
													<label class="radio-inline"> <input type="radio"
														name="gender" id="gender" value="2"> Female
													</label>
												</c:when>
												<c:otherwise>
													<label class="radio-inline"> <input type="radio"
														name="gender" id="gender" value="1" /> Male
													</label>
													<label class="radio-inline"> <input type="radio"
														name="gender" id="gender" value="2" checked>
														Female
													</label>

												</c:otherwise>

											</c:choose>

										</div>
									</div>

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Date of
											Birth</label>
										<div class="col-sm-3 col-lg-4 controls">
											<input type="text" name="dob" id="dob"
												placeholder="Date of Birth" class="form-control date-picker"
												data-rule-required="true" value="${emp.dob}" />
										</div>
									</div>

								</div>


								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Permanent
											Address </label>
										<div class="col-sm-3 col-lg-4 controls">
											<input type="text" name="perAdd" id="perAdd"
												placeholder="Permanent Address" class="form-control"
												data-rule-required="true" value="${emp.empAddressPerm}" />
										</div>
									</div>

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Temporary
											Address </label>
										<div class="col-sm-3 col-lg-4 controls">
											<input type="text" name="tmpAdd" id="tmpAdd"
												placeholder="Temporary Address" class="form-control"
												data-rule-required="true" value="${emp.empAddressTemp}" />
										</div>
									</div>
								</div>



								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Emergency
											Contact Name 1 </label>
										<div class="col-sm-3 col-lg-4 controls">
											<input type="text" name="emrgPer1" id="emrgPer1"
												placeholder="Emergency Contact Name" class="form-control"
												data-rule-required="true" value="${emp.empEmergencyPerson1}" />
										</div>
									</div>

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Emergency
											Contact Number 1 </label>
										<div class="col-sm-3 col-lg-4 controls">
											<input type="text" name="emrgNo1" id="emrgNo1" maxlength="10"
												placeholder="Emergency Contact Number" class="form-control"
												pattern="^\d{10}$" data-rule-required="true"
												value="${emp.empEmergencyNo1}" />
										</div>
									</div>
								</div>




								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Emergency
											Contact Name 2 </label>
										<div class="col-sm-3 col-lg-4 controls">
											<input type="text" name="emrgPer2" id="emrgPer2"
												placeholder="Emergency Contact Name" class="form-control"
												data-rule-required="true" value="${emp.empEmergencyPerson2}" />
										</div>
									</div>

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Emergency
											Contact Number 2 </label>
										<div class="col-sm-3 col-lg-4 controls">
											<input type="text" name="emrgNo2" id="emrgNo2" maxlength="10"
												placeholder="Emergency Contact Number" class="form-control"
												pattern="^\d{10}$" data-rule-required="true"
												value="${emp.empEmergencyNo2}" />
										</div>
									</div>
								</div>



								<div class="form-group">


									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Terms
											and Conditions </label>
										<div class="col-sm-3 col-lg-10 controls">
											<textarea name="terms" id="terms"
												placeholder="Terms and Conditions" class="form-control"
												data-rule-required="true">${emp.termConditions}</textarea>
										</div>
									</div>

								</div>

								<br>
								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Submit">
										<!-- 										<button type="button" class="btn">Cancel</button>
 -->
									</div>
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

									<%-- <jsp:include page="/WEB-INF/views/include/tableSearch.jsp"></jsp:include> --%>

									<div class="col-md-9"></div>
									<label for="search" class="col-md-3" id="search"> <i
										class="fa fa-search" style="font-size: 20px"></i> <input
										type="text" id="myInput"
										style="border-radius: 25px; box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2);"
										onkeyup="myFunction()" placeholder="Search.."
										title="Type in a name">
									</label>

									<div class="clearfix"></div>
									<div id="table-scroll" class="table-scroll">

										<div id="faux-table" class="faux-table" aria="hidden">
											<table id="table2" class="main-table">
												<thead>
													<tr class="bgpink">
														<th width="45" style="width: 18px">Sr.No.</th>
														<th width="100" align="left">Photo</th>
														<th width="100" align="left">DSC</th>
														<th width="100" align="left">EMP Code</th>
														<th width="100" align="left">Name</th>
														<th width="100" align="left">Department</th>
														<th width="100" align="left">Designation</th>
														<th width="100" align="left">Company</th>

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
														<th width="100" align="left">Photo</th>
														<th width="100" align="left">DSC</th>
														<th width="100" align="left">EMP Code</th>
														<th width="100" align="left">Name</th>
														<th width="100" align="left">Department</th>
														<th width="100" align="left">Designation</th>
														<th width="100" align="left">Company</th>

														<th width="81" align="left">Action</th>
													</tr>
												</thead>

												<tbody>
													<c:forEach items="${empList}" var="empList"
														varStatus="count">
														<tr>

															<td><c:out value="${count.index+1}" /></td>

															<td align="left"><img
																src="${url}${empList.empPhoto}" width="80" height="60" /></td>

															<td align="left"><c:out value="${empList.empDsc}"></c:out></td>

															<td align="left"><c:out value="${empList.empCode}"></c:out></td>

															<td align="left"><c:out
																	value="${empList.empFname} ${empList.empMname} ${empList.empSname}"></c:out></td>
															<td align="left"><c:out value="${empList.deptName}"></c:out></td>

															<td align="left"><c:out value="${empList.catName}"></c:out></td>

															<td align="left"><c:out
																	value="${empList.companyName}"></c:out></td>


															<td align="left"><a
																href="${pageContext.request.contextPath}/updateHrEmp/${empList.empId}"><span
																	class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp; <a
																href="${pageContext.request.contextPath}/deleteHrEmp/${empList.empId}"
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




		<!-- Generate Random Number -->
		<script>
			function funRandomNumber() {

				document.getElementById("dsc").value = Math
						.floor(Math.random() * 90000) + 10000;

			}
		</script>

		<!-- Generate Random Number -->
		<script>
			function funRandomNumberonLoad() {

				var x = sal = $("#oldDsc").val();

				if (x == "") {
					document.getElementById("dsc").value = Math.floor(Math
							.random() * 90000) + 10000;
				}

			}
		</script>

		<!-- Calculate Rate per Hr -->
		<script>
			function calculateRatePerHr() {
				var sal, nHrs;
				sal = $("#grSal").val();
				nHrs = $("#nHrs").val();

				alert("Salary - " + (sal / nHrs));
				document.getElementById("rate").value = (sal / nHrs);
			}
		</script>

		<!-- Check Emp Code for unique -->
		<script>
			function checkUniqueEmpCode() {
				var empCode;
				empCode = $("#emp_code").val();

				
				if (empCode == null) {
					alert("Please Insert Employee Code")
				} else {
					

					$.getJSON('${checkuniqueEmpCodeProcess}',

					{
						code : empCode,
						ajax : 'true'

					}, function(data) {

						 alert(data);
 
						

					});//data function

				}//else

			}
		</script>



		<!-- Search -->
		<script>
			function myFunction() {
				var input, filter, table, tr, td, i;
				input = document.getElementById("myInput");
				filter = input.value.toUpperCase();

				if (!document.getElementById('table1')) {

					table = document.getElementById("table_grid");
				} else {
					table = document.getElementById("table1");
				}
				tr = table.getElementsByTagName("tr");
				//td = table.getElementsByTagName("td");
				//alert("td value = "+td.length);
				for (i = 0; i < tr.length; i++) {
					td = tr[i].getElementsByTagName("td")[4];
					if (td) {
						if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
							tr[i].style.display = "";
						} else {
							tr[i].style.display = "none";
						}
					}
				}
			}
		</script>









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
