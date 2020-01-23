<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body>
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
	<c:url var="getGrnGvnByDatewise" value="/getSalesReportComparion"></c:url>
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
						<i class="fa fa-file-o"></i>Sales Comparison Report
					</h1>
					<h4></h4>
				</div>
			</div>
			<!-- END Page Title -->

			<!-- BEGIN Breadcrumb -->
			<%-- <div id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="fa fa-home"></i> <a
						href="${pageContext.request.contextPath}/home">Home</a> <span
						class="divider"><i class="fa fa-angle-right"></i></span></li>
					<li class="active">Sales Compare Report</li>
				</ul>
			</div> --%>
			<!-- END Breadcrumb -->

			<!-- BEGIN Main Content -->
			<div class="box">
				<div class="box-title">
					<h3>
						<i class="fa fa-bars"></i>View Sales Comparison Report
					</h3>

				</div>

				<div class="box-content">
					<div class="row">


						<div class="form-group">
							<label class="col-sm-3 col-lg-2	 control-label">Select
								First Month</label>
							<div class="col-sm-6 col-lg-2 controls date_select">
								<select class="form-control chosen" name="sel_month"
									id="sel_month">

									<option value="13">Jan ${prevYear}</option>
									<option value="20">Feb ${prevYear}</option>
									<option value="30">Mar ${prevYear}</option>
									<option value="40">Apr ${prevYear}</option>
									<option value="50">May ${prevYear}</option>
									<option value="60">Jun ${prevYear}</option>
									<option value="70">Jul ${prevYear}</option>
									<option value="80">Aug ${prevYear}</option>
									<option value="90">Sep ${prevYear}</option>
									<option value="100">Oct ${prevYear}</option>
									<option value="110">Nov ${prevYear}</option>
									<option value="120">Dec ${prevYear}</option>

									<option value="1">Jan ${year}</option>
									<option value="2">Feb ${year}</option>
									<option value="3">Mar ${year}</option>
									<option value="4">Apr ${year}</option>
									<option value="5">May ${year}</option>
									<option value="6">Jun ${year}</option>
									<option value="7">Jul ${year}</option>
									<option value="8">Aug ${year}</option>
									<option value="9">Sep ${year}</option>
									<option value="10">Oct ${year}</option>
									<option value="11">Nov ${year}</option>
									<option value="12">Dec ${year}</option>




								</select>
							</div>


							<label class="col-sm-3 col-lg-2	 control-label">Select
								Second Month</label>
							<div class="col-sm-6 col-lg-2 controls date_select">
								<select class="form-control chosen" name="sel_month_next"
									id="sel_month_next">

									<option value="13">Jan ${prevYear}</option>
									<option value="20">Feb ${prevYear}</option>
									<option value="30">Mar ${prevYear}</option>
									<option value="40">Apr ${prevYear}</option>
									<option value="50">May ${prevYear}</option>
									<option value="60">Jun ${prevYear}</option>
									<option value="70">Jul ${prevYear}</option>
									<option value="80">Aug ${prevYear}</option>
									<option value="90">Sep ${prevYear}</option>
									<option value="100">Oct ${prevYear}</option>
									<option value="110">Nov ${prevYear}</option>
									<option value="120">Dec ${prevYear}</option>

									<option value="1">Jan ${year}</option>
									<option value="2">Feb ${year}</option>
									<option value="3">Mar ${year}</option>
									<option value="4">Apr ${year}</option>
									<option value="5">May ${year}</option>
									<option value="6">Jun ${year}</option>
									<option value="7">Jul ${year}</option>
									<option value="8">Aug ${year}</option>
									<option value="9">Sep ${year}</option>
									<option value="10">Oct ${year}</option>
									<option value="11">Nov ${year}</option>
									<option value="12">Dec ${year}</option>




								</select>
							</div>





							<div class="col-sm-6 col-lg-3 controls date_select">
								<button class="btn btn-info" onclick="searchReport()">Search</button>
								<button class="btn btn-primary" value="PDF" id="PDFButton"
									onclick="genPdf()" disabled="disabled">PDF</button>
							</div>
						</div>
					</div>

					<br>

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
						<i class="fa fa-list-alt"></i>Sales Comparison Report
					</h3>

				</div>

				<form id="submitBillForm" method="post">
					<div class=" box-content">
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>

										<th rowspan="1"></th>
										<th colspan="2"></th>
										<th colspan="2"></th>
										<th colspan="2"></th>
										<th rowspan="1"></th>
										<th rowspan="1"></th>
										<th rowspan="1"></th>
										<th rowspan="1">11.11%</th>
										<th rowspan="1">14.9%</th>
										<th rowspan="1">17.6%</th>
									</tr>
									<tr>

										<th rowspan="1"></th>
										<th colspan="2"></th>
										<th colspan="2"></th>
										<th colspan="2"></th>
										<th rowspan="1"></th>
										<th rowspan="1"></th>
										<th rowspan="1"></th>
										<th colspan="3" style="text-align: center;" id="actualMonth">First
											Month</th>

									</tr>
									<tr>

										<th rowspan="2">Party Name</th>
										<th colspan="2">First Month Sale Value</th>
										<th colspan="2">Second Month Sale Value</th>
										<th colspan="2">Sale Difference</th>
										<th rowspan="2">Percent(%)</th>
										<th rowspan="2">Route</th>
										<th rowspan="2">Avg Per Day Sale First Month</th>
										<th colspan="3">Franchise Margin On Billing Not On Mrp</th>
									</tr>
									<tr>
										<th colspan="2" align="center" id="prevMonth"></th>
										<th colspan="2" align="center" id="currMonth"></th>
										<th colspan="2" align="left" id="diff"></th>
										<th rowspan="1">10% First Month</th>
										<th rowspan="1">13% First Month</th>
										<th rowspan="1">15% First Month</th>
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
							<div align="center" id="showchart" style="display: none"></div>
						</div>
					</div>
					<!-- 				</div>
				
				<div id="chart" style="display: none"><br><br><br>
	<hr><div  >
	 
			<div  id="chart_div" style="width:60%; height:300; float:left;" ></div> 
		 
			<div   id="PieChart_div" style="width:40%%; height:300; float: right;" ></div> 
			</div>
			
				 
				</div> -->


					<div id="chart">
						<br> <br> <br>
						<hr>


						<div id="chart_div" style="width: 100%; height: 100%;"></div>


						<div id="PieChart_div" style="width: 100%; height: 100%;"></div>


					</div>
				</form>
			</div>
		</div>
		<!-- END Main Content -->

		<footer>
			<p>2018 © Monginis.</p>
		</footer>


		<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
			class="fa fa-chevron-up"></i></a>
	</div>
	</div>
	<script type="text/javascript">
		function dropdown(monthfield, yearfield) {
			var monthtext = [ '01', '02', '03', '04', '05', '06', '07', '08',
					'09', '10', '11', '12' ];
			var today = new Date()
			var monthfield = document.getElementById(monthfield)
			var yearfield = document.getElementById(yearfield)
			var thisyear = today.getFullYear()
			var thismonth = today.getMonth()

			var id = 0;
			for (var y = 0; y <= 20; y++) {
				for (var m = (monthtext.length - 1); m >= 0; m--, id++) {
					if (m <= thismonth) {
						yearfield.options[id] = new Option(thisyear + "-"
								+ monthtext[m], thisyear + "" + monthtext[m])
					} else {
					}
				}
				thisyear -= 1
				thismonth = 12;
			}
			yearfield.options[0] = new Option(today.getFullYear(), today
					.getFullYear(), true, true) //select today's year
		}

		function searchReport() {
			//	var isValid = validate();

			var month = $("#sel_month").val();
			var month_next = $("#sel_month_next").val();

			$('#loader').show();

			$
					.getJSON(
							'${getGrnGvnByDatewise}',

							{

								month : month,
								month_next : month_next,
								ajax : 'true'

							},
							function(data) {

								$('#table_grid td').remove();
								$('#loader').hide();
								document.getElementById("PDFButton").disabled = false;

								document.getElementById('range').style.display = 'block';
								document.getElementById("expExcel").disabled = false;

								if (data == "") {
									alert("No records found !!");
									document.getElementById("PDFButton").disabled = true;
									document.getElementById("expExcel").disabled = true;
								}

								document.getElementById('prevMonth').innerHTML = data.prevMonth;
								document.getElementById('currMonth').innerHTML = data.currMonth;
								document.getElementById('diff').innerHTML = data.prevMonth
										+ " " + data.currMonth + "  diff";
								document.getElementById('actualMonth').innerHTML = data.prevMonth;
								$
										.each(
												data.routeList,
												function(key, route) {
													var prevMonthRouteSale = 0;
													var currMonthRouteSale = 0;
													$
															.each(
																	data.saleCompFinal,
																	function(
																			key,
																			report) {
																		if (route.routeId == report.routeId) {
																			//  document.getElementById("expExcel").disabled=false;
																			//	document.getElementById('range').style.display = 'block';

																			var index = key + 1;
																			//var tr = "<tr>";
																			var tr = $('<tr></tr>');
																			//tr.append($('<td></td>').html(key+1));

																			tr
																					.append($(
																							'<td style="text-align:left;"></td>')
																							.html(
																									report.frName));
																			tr
																					.append($(
																							'<td colspan="2" style="text-align:right;"></td>')
																							.html(
																									(report.perMonthSale)
																											.toFixed(2)));
																			tr
																					.append($(
																							'<td colspan="2" style="text-align:right;"></td>')
																							.html(
																									(report.prevMonthSale)
																											.toFixed(2)));
																			tr
																					.append($(
																							'<td colspan="2" style="text-align:right;"></td>')
																							.html(
																									(report.lastMonthDiff)
																											.toFixed(2)));
																			tr
																					.append($(
																							'<td style="text-align:right;"></td>')
																							.html(
																									(report.monthDiffInPer)
																											.toFixed(2)));
																			tr
																					.append($(
																							'<td style="text-align:left;"></td>')
																							.html(
																									report.routeName));
																			tr
																					.append($(
																							'<td style="text-align:right;"></td>')
																							.html(
																									(report.perMonthSale / 30)
																											.toFixed(2)));
																			tr
																					.append($(
																							'<td style="text-align:right;"></td>')
																							.html(
																									(report.perMonthSale * 11.11 / 100)
																											.toFixed(2)));
																			tr
																					.append($(
																							'<td style="text-align:right;"></td>')
																							.html(
																									(report.perMonthSale * 14.9 / 100)
																											.toFixed(2)));
																			tr
																					.append($(
																							'<td style="text-align:right;"></td>')
																							.html(
																									(report.perMonthSale * 17.6 / 100)
																											.toFixed(2)));
																			$(
																					'#table_grid tbody')
																					.append(
																							tr);
																			prevMonthRouteSale = prevMonthRouteSale
																					+ report.prevMonthSale;
																			currMonthRouteSale = currMonthRouteSale
																					+ report.perMonthSale;
																		}

																	})
													var tr = $('<tr "></tr>');

													tr
															.append($(
																	'<td style="text-align:left; color:blue;"></td>')
																	.html(
																			"Route Total"));
													tr
															.append($(
																	'<td colspan="2" style="text-align:right; color:blue;" ></td>')
																	.html(
																			(prevMonthRouteSale)
																					.toFixed(2)));
													tr
															.append($(
																	'<td colspan="2" style="text-align:right; color:blue;"></td>')
																	.html(
																			(currMonthRouteSale)
																					.toFixed(2)));
													tr
															.append($(
																	'<td colspan="2"></td>')
																	.html(""));

													tr.append($('<td ></td>')
															.html(""));
													tr.append($('<td></td>')
															.html(""));
													tr.append($('<td></td>')
															.html(""));
													tr.append($('<td></td>')
															.html(""));
													tr.append($('<td></td>')
															.html(""));
													tr.append($('<td></td>')
															.html(""));
													$('#table_grid tbody')
															.append(tr);
												});
							});

		}
	</script>

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
		function showChart() {
			$("#PieChart_div").empty();
			$("#chart_div").empty();
			document.getElementById('chart').style.display = "block";
			document.getElementById("table_grid").style = "display:none";

			document.getElementById('chart').style.display = "display:none";
			document.getElementById("table_grid").style = "block";
			var isGrn = $("#isGrn").val();
			//alert("isGrn " +isGrn);

			//report 1
			var selectedFr = $("#selectFr").val();
			var routeId = $("#selectRoute").val();

			var from_date = $("#fromDate").val();
			var to_date = $("#toDate").val();

			$('#loader').show();

			$
					.getJSON(
							'${getGrnGvnByDatewise}',

							{
								fr_id_list : JSON.stringify(selectedFr),
								from_date : from_date,
								to_date : to_date,
								route_id : routeId,
								is_grn : isGrn,
								ajax : 'true'

							},
							function(data) {

								//alert(data);
								if (data == "") {
									alert("No records found !!");
									$('#loader').hide();

								}
								var i = 0;
								$('#loader').hide();
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
									dataTable.addColumn('string', 'Date'); // Implicit domain column.
									dataTable.addColumn('number',
											'Requested Value'); // Implicit data column.
									dataTable.addColumn('number',
											'Approved Value');

									var piedataTable = new google.visualization.DataTable();
									piedataTable.addColumn('string', 'Date'); // Implicit domain column.
									piedataTable.addColumn('number',
											'Approved Value');

									$.each(data, function(key, report) {

										// alert("In Data")
										var reqValue = report.totalAmt;
										var aprValue = report.aprGrandTotal;

										//var total=report.taxableAmt+report.sgstSum+report.cgstSum;
										//alert("total ==="+total);
										//alert("base Value "+baseValue);

										var date = report.grngvnDate;
										//alert("frNAme "+frName);
										//var date= item.billDate+'\nTax : ' + item.tax_per + '%';

										dataTable.addRows([

										[ date, reqValue, aprValue ],

										]);

										piedataTable.addRows([

										[ date, aprValue ],

										]);
									}) // end of  $.each(data,function(key, report) {-- function

									// Instantiate and draw the chart.

									var materialOptions = {

										width : 500,
										chart : {
											title : 'Date wise Grn Gvn Report',
											subtitle : 'Requested and Approved Value',

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
													label : 'Req Value'
												}, // Left y-axis.
												brightness : {
													side : 'right',
													label : 'Apr  Value'
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
										title : 'Date wise Grn Gvn Report'
									});

									Piechart.draw(piedataTable, {
										title : 'Date wise Grn Gvn Report',
										is3D : true
									});
									// drawMaterialChart();
								}
								;

							});

		}

		function genPdf() {

			window
					.open('${pageContext.request.contextPath}/showSalesComparePdf/');

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