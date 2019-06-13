<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body>
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
	<c:url var="getEmpwiseGPReport" value="/getEmpwiseGPReport"></c:url>
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
						<i class="fa fa-file-o"></i>Employee Wise Gatepass Report
					</h1>
					<h4></h4>
				</div>
			</div>
			<!-- END Page Title -->

			<!-- BEGIN Breadcrumb -->
			<div id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="fa fa-home"></i> <a
						href="${pageContext.request.contextPath}/home">Home</a> <span
						class="divider"><i class="fa fa-angle-right"></i></span></li>
					<li class="active">Bill Report</li>
				</ul>
			</div>
			<!-- END Breadcrumb -->

			<!-- BEGIN Main Content -->
			<div class="box">
				<div class="box-title">
					<h3>
						<i class="fa fa-bars"></i>View Employee Wise Gatepass Report
					</h3>

				</div>

				<div class="box-content">
					<div class="row">


						<div class="form-group">
							<label class="col-sm-3 col-lg-2	 control-label">From Date</label>
							<div class="col-sm-6 col-lg-4 controls date_select">
								<input class="form-control date-picker" id="fromDate"
									disabled="true" name="fromDate" size="30" type="text"
									value="${fromDate}" />
							</div>

							<!-- </div>

					<div class="form-group  "> -->

							<label class="col-sm-3 col-lg-2	 control-label">To Date</label>
							<div class="col-sm-6 col-lg-4 controls date_select">
								<input class="form-control date-picker" id="toDate"
									disabled="true" name="toDate" size="30" type="text"
									value="${toDate}" />
							</div>
						</div>

					</div>


					<br>

					<!-- <div class="col-sm-9 col-lg-5 controls">
 -->
					<div class="row">
						<div class="form-group">

							<div class="col2">
								<label class="col-sm-3 col-lg-2 control-label">Select
									Gatepass Type </label>
								<div class="col-sm-3 col-lg-4 controls">
									<select name="selectType" disabled="true"
										data-placeholder="Select Gatepass Type" id="selectType"
										class="form-control chosen" tabindex="" aria-hidden="true"
										data-rule-required="true">
										<option value="">Select Gatepass Type</option>

										<option ${type == '1'  ? 'Selected': '' } value="1">Visitor</option>

										<option ${type == '2'  ? 'Selected': '' } value="2">Maintenance</option>

										<!-- <option value="1">Visitor</option>
										<option value="2">Maintenance</option> -->


									</select>
								</div>
							</div>

							<div class="col2">
								<label class="col-sm-3 col-lg-2	 control-label">Employee</label>
								<div class="col-sm-6 col-lg-4 controls date_select">
									<input class="form-control date-picker" id="emp"
										disabled="true" name="emp" size="30" type="text"
										value="${emp}" />
								</div>

							</div>

						</div>
					</div>


					<div class="row">
						<br>
						<div class="form-group">

							<div class="col2">
								<label class="col-sm-3 col-lg-2 control-label">Select
									Status</label>
								<div class="col-sm-3 col-lg-4 controls">
									<select data-placeholder="Select Status" disabled="true"
										class="form-control chosen" name="selectStatus"
										id="selectStatus" data-rule-required="true">
										<option value="">Select Status</option>

										<option ${status == '0'  ? 'Selected': '' } value="0">Pending</option>

										<option ${status == '1'  ? 'Selected': '' } value="1">Approved</option>

										<option ${status == '2'  ? 'Selected': '' } value="2">Rejected</option>

										<option ${status == '3'  ? 'Selected': '' } value="3">Closed
											Meeting</option>

										<!-- <option value="0">Pending</option>
										<option value="1">Approved</option>
										<option value="2">Rejected</option>
										<option value="4">Closed Meeting</option> -->
									</select>

								</div>
							</div>




							<div class="col2">
								<div style="text-align: center;">

									<button class="btn search_btn" onclick="exportToExcel()">Excel</button>

									<button class="btn btn-primary" value="PDF" id="PDFButton"
										onclick="exportToPDF()">PDF</button>
								</div>
							</div>


						</div>
					</div>



					<div align="center" id="loader" style="display: none">

						<span>
							<h4>
								<font color="#343690">Loading</font>
							</h4>
						</span> <span class="l-1"></span> <span class="l-2"></span> <span
							class="l-3"></span> <span class="l-4"></span> <span class="l-5"></span>
						<span class="l-6"></span>
					</div>

				</div>
			</div>


			<div class="box">
				<div class="box-title">
					<h3>
						<i class="fa fa-list-alt"></i>Employee Wise Gatepass Detail Report
					</h3>

				</div>

				<form id="submitBillForm"
					action="${pageContext.request.contextPath}/submitNewBill"
					method="post">
					<div class="box-content">
						<div class="row">
							<div class="col-md-12 table-responsive">
								<table class="table table-bordered table-striped fill-head "
									style="width: 100%" id="table_grid">
									<thead>
										<tr>
											<th>Sr.No.</th>
											<th>Date</th>
											<th>Person Name</th>
											<th>Person Company</th>
											<th>Contact Number</th>
											<th>Purpose</th>
											<th>In Time</th>
											<th>Out Time</th>
											<th>Total Time</th>
											<th>Assign Employee</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach items="${reportList}" var="reportList"
											varStatus="count">
											<tr>

												<td><c:out value="${count.index+1}" /></td>


												<td align="left"><c:out
														value="${reportList.visitDateIn}"></c:out></td>



												<td align="left"><c:out
														value="${reportList.personName}"></c:out></td>

												<td align="left"><c:out
														value="${reportList.personCompany}"></c:out></td>

												<td align="left"><c:out value="${reportList.mobileNo}"></c:out></td>

												<td align="left"><c:out
														value="${reportList.visitPurposeText}"></c:out></td>

												<td align="left"><c:out value="${reportList.inTime}"></c:out></td>

												<td align="left"><c:out
														value="${reportList.visitOutTime}"></c:out></td>

												<td align="left"><c:out
														value="${reportList.totalTimeDifference}"></c:out></td>

												<td align="left"><c:out value="${reportList.empName}"></c:out></td>


											</tr>

										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>


						<div id="chart"">
							<br> <br> <br>
							<hr>


							<div id="chart_div" style="width: 100%; height: 100%;"></div>


							<div id="PieChart_div" style="width: 100%; height: 100%;"></div>


						</div>
				</form>
			</div>

			<!-- END Main Content -->

			<footer>
				<p>2017 © Monginis.</p>
			</footer>


			<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
				class="fa fa-chevron-up"></i></a>
		</div>
	</div>

	<script type="text/javascript">
		function validate() {

			var selectedFr = $("#selectFr").val();
			var selectedMenu = $("#selectMenu").val();
			var selectedRoute = $("#selectRoute").val();

			var isValid = true;

			if (selectedFr == "" || selectedFr == null) {

				if (selectedRoute == "" || selectedRoute == null) {
					alert("Please Select atleast one ");
					isValid = false;
				}
				//alert("Please select Franchise/Route");

			} else if (selectedMenu == "" || selectedMenu == null) {

				isValid = false;
				alert("Please select Menu");

			}
			return isValid;

		}
	</script>



	<script type="text/javascript">
		function callDetail(empId) {

			window.open("${pageContext.request.contextPath}/getDetailOfEmp"
					+ empId);

		}
	</script>




	<script>
		$('.datepicker').datepicker({
			format : {
				/*
				 * Say our UI should display a week ahead,
				 * but textbox should store the actual date.
				 * This is useful if we need UI to select local dates,
				 * but store in UTC
				 */
				format : 'mm/dd/yyyy',
				startDate : '-3d'
			}
		});
	</script>


	<script type="text/javascript">
		$(document).ready(function() {
			$('#bootstrap-data-table').DataTable();
		});
	</script>

	<script type="text/javascript">

		function exportToPDF() {

			var type = $("#selectType").val();
			var fromDate = $("#fromDate").val();
			var toDate = $("#toDate").val();
			var status = $("#selectStatus").val();

			window
					.open("${pageContext.request.contextPath}/showEmpwiseGPDetailPdf/"
							+ fromDate + '/' + toDate);

		}

		function exportToExcel() {

			window.open("${pageContext.request.contextPath}/exportToExcel");
			document.getElementById("expExcel").disabled = true;
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
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/chosen-bootstrap/chosen.jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-inputmask/bootstrap-inputmask.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-tags-input/jquery.tagsinput.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-pwstrength/jquery.pwstrength.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-fileupload/bootstrap-fileupload.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-duallistbox/duallistbox/bootstrap-duallistbox.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/dropzone/downloads/dropzone.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/clockface/js/clockface.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/date.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-switch/static/js/bootstrap-switch.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/ckeditor/ckeditor.js"></script>

	<!--flaty scripts-->
	<script src="${pageContext.request.contextPath}/resources/js/flaty.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/flaty-demo-codes.js"></script>
</body>
</html>