<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body>
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>


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
						<i class="fa fa-file-o"></i>Employee Account
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
								<i class="fa fa-bars"></i> Add Account Details
							</h3>
							<div class="box-tool">
										<a href="${pageContext.request.contextPath}/showHrEmpList">Back
											to List</a> <a data-action="collapse" href="#"><i
											class="fa fa-chevron-up"></i></a>
									</div>

						</div>

						<div class="box-content">
							<form action="${pageContext.request.contextPath}/addHrEmpAcc"
								class="form-horizontal" method="post" id="validation-form"
								enctype="multipart/form-data">

								<input type="hidden" name="emp_id" id="emp_id"
									value="${emp.empId}" />

								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Employee
											Name </label>
										<div class="col-sm-3 col-lg-10 controls">
											<input type="text" name="f_name" id="f_name"
												readonly="readonly" placeholder="First Name"
												class="form-control" data-rule-required="true"
												value="${emp.empFname} ${emp.empMname} ${emp.empSname}" />
										</div>
									</div>


								</div>




								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Joining
											Date </label>
										<div class="col-sm-3 col-lg-4 controls">
											<input type="text" name="jDate" id="jDate"
												placeholder="Joining Date" class="form-control date-picker"
												data-rule-required="true" value="${emp.empJoiningDate}" />
										</div>
									</div>

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Salary</label>
										<div class="col-sm-3 col-lg-4 controls">
											<select name="sal_id" id="sal_id" class="form-control"
												data-rule-required="true">
												<option value="">Select Salary</option>
												<c:forEach items="${salList}" var="salList">
													<c:choose>
														<c:when test="${salList.salaryId==emp.salaryId}">
															<option value="${salList.salaryId}" selected><c:out
																	value="${salList.salaryBracket}"></c:out></option>
														</c:when>
														<c:otherwise>
															<option value="${salList.salaryId}"><c:out
																	value="${salList.salaryBracket}"></c:out></option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</div>

								</div>

								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Gross
											Salary </label>
										<div class="col-sm-3 col-lg-4 controls">
											<input type="text" name="grSal" id="grSal"
												placeholder="Gross Salary" class="form-control"
												onchange="calculateRatePerHr()" pattern="[0-9]+([.][0-9]+)?"
												data-rule-required="true" value="${emp.grossSalary}" />
										</div>
									</div>

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">No of
											Hours </label>
										<div class="col-sm-3 col-lg-4 controls">
											<input type="text" name="nHrs" id="nHrs"
												onchange="calculateRatePerHr()" placeholder="No of Hours"
												class="form-control" data-rule-required="true"
												value="${noOfHrs}" />
										</div>
									</div>
								</div>




								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Rate
											Per Hour </label>
										<div class="col-sm-3 col-lg-4 controls">
											<input type="text" name="rate" id="rate"
												placeholder="Rate Per Hour" class="form-control"
												readonly="readonly" pattern="[0-9]+([.][0-9]+)?"
												data-rule-required="true" value="${emp.empRatePerhr}" />
										</div>
									</div>


								</div>



								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Previous
											Experience (years) </label>
										<div class="col-sm-3 col-lg-4 controls">
											<input type="text" name="yrExp" id="yrExp" pattern="[0-9.]+"
												placeholder="Previous Experience " class="form-control"
												data-rule-required="true" value="${emp.empPrevExpYrs}" />
										</div>
									</div>

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Previous
											Experience (months) </label>
										<div class="col-sm-3 col-lg-4 controls">
											<input type="text" name="monExp" id="monExp"
												pattern="[0-9.]+" placeholder="Previous Experience"
												class="form-control" data-rule-required="true"
												value="${emp.empPrevExpMonths}" />
										</div>
									</div>
								</div>




								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Leaving
											Date </label>
										<div class="col-sm-3 col-lg-4 controls">
											<input class="form-control date-picker" type="text"
												name="lvDate" id="lvDate" placeholder="Leaving Date"
												data-rule-required="true" value="${emp.empLeavingDate}" />
										</div>
									</div>

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Leaving
											Reason </label>
										<div class="col-sm-3 col-lg-4 controls">
											<input type="text" name="lvReason" id="lvReason"
												placeholder="Leaving Reason" class="form-control"
												data-rule-required="true" value="${emp.empLeavingReason}" />
										</div>
									</div>


								</div>







								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Lock
											Period </label>
										<div class="col-sm-3 col-lg-4 controls">
											<input type="text" name="lock" id="lock" pattern="[0-9.]+"
												placeholder="Lock Period" class="form-control"
												data-rule-required="true" value="${emp.lockPeriod}" />
										</div>
									</div>



								</div>




								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">PF
											Applicable </label>
										<div class="col-sm-3 col-lg-4 controls">

											<c:choose>
												<c:when test="${emp.pf==1}">
													<label class="radio-inline"> <input type="radio"
														name="pf" id="pf" value="1" checked /> YES
													</label>
													<label class="radio-inline"> <input type="radio"
														name="pf" id="pf" value="0"> NO
													</label>
												</c:when>
												<c:otherwise>
													<label class="radio-inline"> <input type="radio"
														name="pf" id="pf" value="1" /> YES
													</label>
													<label class="radio-inline"> <input type="radio"
														name="pf" id="pf" value="0" checked> NO
													</label>

												</c:otherwise>

											</c:choose>




										</div>
									</div>

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">ESIC
											Applicable </label>
										<div class="col-sm-3 col-lg-4 controls">

											<c:choose>
												<c:when test="${emp.esic==1}">
													<label class="radio-inline"> <input type="radio"
														name="esic" id="esic" value="1" checked /> YES
													</label>
													<label class="radio-inline"> <input type="radio"
														name="esic" id="esic" value="0"> NO
													</label>
												</c:when>
												<c:otherwise>
													<label class="radio-inline"> <input type="radio"
														name="esic" id="esic" value="1" /> YES
													</label>
													<label class="radio-inline"> <input type="radio"
														name="esic" id="esic" value="0" checked> NO
													</label>

												</c:otherwise>

											</c:choose>


										</div>
									</div>

								</div>


								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Bonus
											Applicable </label>
										<div class="col-sm-3 col-lg-4 controls">

											<c:choose>
												<c:when test="${emp.bonus==1}">
													<label class="radio-inline"> <input type="radio"
														name="bonus" id="bonus" value="1" checked /> YES
													</label>
													<label class="radio-inline"> <input type="radio"
														name="bonus" id="bonus" value="0"> NO
													</label>
												</c:when>
												<c:otherwise>
													<label class="radio-inline"> <input type="radio"
														name="bonus" id="bonus" value="1" /> YES
													</label>
													<label class="radio-inline"> <input type="radio"
														name="bonus" id="bonus" value="0" checked> NO
													</label>

												</c:otherwise>

											</c:choose>

										</div>
									</div>

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Casual
											Leave Applicable </label>
										<div class="col-sm-3 col-lg-4 controls">

											<c:choose>
												<c:when test="${emp.cl==1}">
													<label class="radio-inline"> <input type="radio"
														name="cl" id="cl" value="1" checked /> YES
													</label>
													<label class="radio-inline"> <input type="radio"
														name="cl" id="cl" value="0"> NO
													</label>
												</c:when>
												<c:otherwise>
													<label class="radio-inline"> <input type="radio"
														name="cl" id="cl" value="1" /> YES
													</label>
													<label class="radio-inline"> <input type="radio"
														name="cl" id="cl" value="0" checked> NO
													</label>

												</c:otherwise>

											</c:choose>

										</div>
									</div>

								</div>

								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Sick
											Leave Applicable </label>
										<div class="col-sm-3 col-lg-4 controls">


											<c:choose>
												<c:when test="${emp.sl==1}">
													<label class="radio-inline"> <input type="radio"
														name="sl" id="sl" value="1" checked /> YES
													</label>
													<label class="radio-inline"> <input type="radio"
														name="sl" id="sl" value="0"> NO
													</label>
												</c:when>
												<c:otherwise>
													<label class="radio-inline"> <input type="radio"
														name="sl" id="sl" value="1" /> YES
													</label>
													<label class="radio-inline"> <input type="radio"
														name="sl" id="sl" value="0" checked> NO
													</label>

												</c:otherwise>

											</c:choose>

										</div>
									</div>

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Paid
											Leave Applicable </label>
										<div class="col-sm-3 col-lg-4 controls">

											<c:choose>
												<c:when test="${emp.pl==1}">
													<label class="radio-inline"> <input type="radio"
														name="pl" id="pl" value="1" checked /> YES
													</label>
													<label class="radio-inline"> <input type="radio"
														name="pl" id="pl" value="0"> NO
													</label>
												</c:when>
												<c:otherwise>
													<label class="radio-inline"> <input type="radio"
														name="pl" id="pl" value="1" /> YES
													</label>
													<label class="radio-inline"> <input type="radio"
														name="pl" id="pl" value="0" checked> NO
													</label>

												</c:otherwise>

											</c:choose>

										</div>
									</div>

								</div>


								<div>
									<input type="hidden" name="scan1" value="${emp.scanCopy1}">
									<input type="hidden" name="scan2" value="${emp.scanCopy2}">
								</div>


								<div class="form-group">


									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Scan
											Copy 1</label>
										<div class="col-sm-9 col-lg-4 controls">
											<div class="fileupload fileupload-new"
												data-provides="fileupload">
												<div class="fileupload-new img-thumbnail"
													style="width: 200px; height: 150px;">
													<!-- <img
													src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image"
													alt="" /> -->
													<img src="${url}${emp.scanCopy1}"
														onerror="this.src='${pageContext.request.contextPath}/resources/img/No_Image_Available.jpg';" />


												</div>
												<div
													class="fileupload-preview fileupload-exists img-thumbnail"
													style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
												<div>
													<span class="btn btn-default btn-file"><span
														class="fileupload-new">Select File</span> <span
														class="fileupload-exists">Change</span> <input type="file"
														class="file-input" name="file1" id="file1" /></span> <a href="#"
														class="btn btn-default fileupload-exists"
														data-dismiss="fileupload">Remove</a>
												</div>
											</div>

										</div>
									</div>


									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Scan
											Copy 2</label>
										<div class="col-sm-9 col-lg-4 controls">
											<div class="fileupload fileupload-new"
												data-provides="fileupload">
												<div class="fileupload-new img-thumbnail"
													style="width: 200px; height: 150px;">
													<!-- <img
													src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image"
													alt="" /> -->
													<img src="${url}${emp.scanCopy2}"
														onerror="this.src='${pageContext.request.contextPath}/resources/img/No_Image_Available.jpg';" />


												</div>
												<div
													class="fileupload-preview fileupload-exists img-thumbnail"
													style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
												<div>
													<span class="btn btn-default btn-file"><span
														class="fileupload-new">Select File</span> <span
														class="fileupload-exists">Change</span> <input type="file"
														class="file-input" name="file2" id="file2" /></span> <a href="#"
														class="btn btn-default fileupload-exists"
														data-dismiss="fileupload">Remove</a>
												</div>
											</div>

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



		<script>
			function calculateRatePerHr() {
				var sal, nHrs;
				sal = $("#grSal").val();
				nHrs = $("#nHrs").val();

				var noOfHrs = sal / nHrs;

				var result = 0;
			

				if (parseFloat(nHrs) > 0) {
					
					result = noOfHrs.toFixed(2);
				}
				

				document.getElementById("rate").value = result;
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
