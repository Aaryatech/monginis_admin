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
	<c:url var="getSupCompReport" value="/getSupCompReport"></c:url>
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
						<i class="fa fa-bars"></i>View Supervisor Comparison Report
					</h3>

				</div>

				<div class="box-content">
					<div class="row">


						<div class="form-group">
							<label class="col-sm-3 col-lg-2	 control-label">From Date</label>
							<div class="col-sm-6 col-lg-4 controls date_select">
								<input class="form-control date-picker" id="fromDate"
									name="fromDate" size="30" type="text" value="${todaysDate}" />
							</div>

							<!-- </div>

					<div class="form-group  "> -->

							<label class="col-sm-3 col-lg-2	 control-label">To Date</label>
							<div class="col-sm-6 col-lg-4 controls date_select">
								<input class="form-control date-picker" id="toDate"
									name="toDate" size="30" type="text" value="${todaysDate}" />
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
									Department</label>
								<div class="col-sm-3 col-lg-4 controls">

									<select data-placeholder="Choose Department"
										class="form-control chosen" multiple="multiple" tabindex="6"
										id="selectDept" name="selectDept">
										<option value="-1"><c:out value="All" /></option>

										<c:forEach items="${deptList}" var="dept" varStatus="count">
											<option value="${dept.empDeptId}"><c:out
													value="${dept.empDeptName}" /></option>
										</c:forEach>
									</select>

								</div>
							</div>


							<div class="col2">

								<!-- <div class="col-sm-12" style="text-align: center;"> -->

								<div style="text-align: center;">
									<button class="btn btn-info" onclick="searchReport()">Search
										Report</button>

									<button class="btn search_btn" onclick="showChart()" id="chartButton" disabled="disabled" >Graph</button>

									<button class="btn btn-primary" value="PDF" id="PDFButton"
										 disabled="disabled" onclick="exportToPDF()">PDF</button>
								</div>
								<!-- </div> -->
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
						<i class="fa fa-list-alt"></i>Supervisor Comparison Report
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
											<th>Supervisor Name</th>
											<th>Count</th>
											<th>Action</th>

										</tr>
									</thead>
									<tbody>

									</tbody>
								</table>
								<div class="form-group" style="display: none;" id="range">



									<div class="col-sm-3  controls">
										<input type="button" id="expExcel" class="btn btn-primary"
											value="EXPORT TO Excel" onclick="exportToExcel();"
											disabled="disabled">
									</div>
								</div>
								<div align="center" id="showchart" style="display: none">
								</div>
							</div>
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
		function searchReport() {
			//alert("hi");
			//	var isValid = validate();
			document.getElementById('chart').style.display = "display:none";
			document.getElementById("table_grid").style = "block";

			var deptId = $("#selectDept").val();
			var from_date = $("#fromDate").val();
			var to_date = $("#toDate").val();

			if (deptId == null) {
				alert("Please Select Department");
			} else {
				$('#loader').show();

				$
						.getJSON(
								'${getSupCompReport}',

								{
									dept_id_list : JSON.stringify(deptId),
									fromDate : from_date,
									toDate : to_date,
									ajax : 'true'

								},
								function(data) {

									$('#table_grid td').remove();
									$('#loader').hide();

									if (data == "") {
										alert("No records found !!");
										document.getElementById("expExcel").disabled = true;
									}

									//PDFButton
									document.getElementById("PDFButton").disabled = false;
									//ChartButton
									document.getElementById("chartButton").disabled = false;
									

									$
											.each(
													data,
													function(key, report) {

														var acButton = '<a href="#" class="action_btn" onclick="callDetail('
																+ report.empId
																+ ')" style="color:black"><i class="fa fa-list"></i></a>'

														document
																.getElementById("expExcel").disabled = false;
														document
																.getElementById('range').style.display = 'block';

														var index = key + 1;
														//var tr = "<tr>";
														var tr = $('<tr></tr>');
														tr
																.append($(
																		'<td></td>')
																		.html(
																				key + 1));
														tr
																.append($(
																		'<td></td>')
																		.html(
																				report.empName));
														tr
																.append($(
																		'<td></td>')
																		.html(
																				report.count));
														tr
																.append($(
																		'<td></td>')
																		.html(
																				acButton));

														$('#table_grid tbody')
																.append(tr);

													})

								});

			}
		}
	</script>





	<script type="text/javascript">
		function callDetail(empId) {

			var fromDate = $("#fromDate").val();
			var toDate = $("#toDate").val();

			window.open("${pageContext.request.contextPath}/getDetailOfSup/"
					+ fromDate + '/' + toDate + '/' + empId);

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
		function showChart() {

			$("#PieChart_div").empty();
			$("#chart_div").empty();
			document.getElementById('chart').style.display = "block";
			document.getElementById("table_grid").style = "display:none";

			var deptId = $("#selectDept").val();

			var from_date = $("#fromDate").val();
			var to_date = $("#toDate").val();

			//document.getElementById('btn_pdf').style.display = "block";
			$
					.getJSON(
							'${getSupCompReport}',

							{
								dept_id_list : JSON.stringify(deptId),
								fromDate : from_date,
								toDate : to_date,
								ajax : 'true'

							},
							function(data) {

								//alert(data);
								if (data == "") {
									alert("No records found !!");

								}
								var i = 0;

								google.charts.load('current', {
									'packages' : [ 'corechart', 'bar' ]
								});
								google.charts.setOnLoadCallback(drawStuff);

								function drawStuff() {

									// alert("Inside DrawStuff");

									var chartDiv = document
											.getElementById('chart_div');
									document.getElementById("chart_div").style.border = "thin dotted red";

									var PiechartDiv = document
											.getElementById('PieChart_div');
									document.getElementById("PieChart_div").style.border = "thin dotted red";

									var dataTable = new google.visualization.DataTable();
									dataTable.addColumn('string',
											'Supervisor Name'); // Implicit domain column.
									dataTable.addColumn('number', 'Count'); // Implicit data column.
									//dataTable.addColumn('number', 'Total');

									var piedataTable = new google.visualization.DataTable();
									piedataTable.addColumn('string',
											'Supervisor Name'); // Implicit domain column.
									piedataTable.addColumn('number', 'Count');

									$.each(data, function(key, report) {

										// alert("In Data")
										var count = report.count;

										var total = 0;

										var empName = report.empName;
										//alert("frNAme "+frName);
										//var date= item.billDate+'\nTax : ' + item.tax_per + '%';

										dataTable.addRows([

										[ empName, count ], ]);

										piedataTable.addRows([

										[ empName, count ],

										]);
									}) // end of  $.each(data,function(key, report) {-- function

									// Instantiate and draw the chart.

									var materialOptions = {

										width : 500,
										chart : {
											title : 'Date wise Tax Graph',
											subtitle : 'Total tax & Taxable Amount per day',

										},
										series : {
											0 : {
												axis : 'distance'
											}, // Bind series 0 to an axis named 'distance'.
											1 : {
												axis : 'brightness'
											}
										// Bind series 1 to an axis named 'brightness'.
										},
										axes : {
											y : {
												distance : {
													label : 'Total Tax'
												}, // Left y-axis.
												brightness : {
													side : 'right',
													label : 'Taxable Amount'
												}
											// Right y-axis.
											}
										}
									};

									function drawMaterialChart() {
										var materialChart = new google.charts.Bar(
												chartDiv);

										// alert("mater chart "+materialChart);
										materialChart
												.draw(
														dataTable,
														google.charts.Bar
																.convertOptions(materialOptions));
										// button.innerText = 'Change to Classic';
										// button.onclick = drawClassicChart;
									}

									var chart = new google.visualization.ColumnChart(
											document
													.getElementById('chart_div'));

									var Piechart = new google.visualization.PieChart(
											document
													.getElementById('PieChart_div'));
									chart.draw(dataTable, {
										title : 'Supervisor Comparison Summary'
									});

									Piechart
											.draw(
													piedataTable,
													{
														title : 'Supervisor Comparison Summary',
														is3D : true
													});
									// drawMaterialChart();
								}
								;

							});

		}

		function exportToPDF() {

			var type = $("#selectType").val();
			var fromDate = $("#fromDate").val();
			var toDate = $("#toDate").val();
			var status = $("#selectStatus").val();

			window.open("${pageContext.request.contextPath}/showSupCompPdf/"
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