<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
 --%>
<!-- <script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
 -->
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body>
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
	<c:url var="getSpCakeList" value="/getSpCakeAlbumListAjax"></c:url>
	<c:url var="excelForFrBill" value="/excelForTspCake" />
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
						<i class="fa fa-file-o"></i> Album SP Cake Order-App
					</h1>
					<h4></h4>
				</div>
			</div>
			<!-- END Page Title -->

			<!-- BEGIN Breadcrumb -->
			<%-- 	<div id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="fa fa-home"></i> <a
						href="${pageContext.request.contextPath}/home">Home</a> <span
						class="divider"><i class="fa fa-angle-right"></i></span></li>
					<li class="active">SP Cake Report</li>
				</ul>
			</div> --%>
			<!-- END Breadcrumb -->

			<!-- BEGIN Main Content -->
			<div class="box">
				<div class="box-title">
					<h3>
						<i class="fa fa-bars"></i>Album SP Cake Order-App
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


					<br>
					<div class="row">

						<div class="form-group">
							<label class="col-sm-3 col-lg-2 control-label">Menu</label>
							<div class="col-sm-4 col-lg-4 controls">
								<select data-placeholder="Select Menu" name="menuIdList"
									class="form-control chosen" tabindex="-1" id="menuIdList"
									multiple="multiple" data-rule-required="true">
									<option value=""></option>

									<option value=""></option>
									<option value="-1">All</option>
									<c:forEach items="${allMenus}" var="allMenus">
										<option value="${allMenus.menuId}">${allMenus.menuTitle}</option>

									</c:forEach>

								</select>
							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-3 col-lg-2 control-label">Select</label>
							<div class="col-sm-4 col-lg-4 controls">
								<select data-placeholder="Select " name="seqenceList"
									class="form-control chosen" tabindex="-1" id="seqenceList"
									data-rule-required="true">
									<option value=""></option>
									<option value="1">Order Sequence</option>
									<option value="2">Production Sequence</option>
									<option value="3">Marzipan Sequence</option>


								</select>
							</div>
						</div>

						<div class="col-md-3" style="text-align: center;">
							<button class="btn btn-info" onclick="searchReport()">Search
								Report</button>
							<!-- <button class="btn search_btn" onclick="showChart()">Graph</button> -->


							<!-- <button class="btn btn-primary" value="PDF" id="PDFButton"
								onclick="genPdf()">PDF</button> -->

							<%-- <a href="${pageContext.request.contextPath}/pdfForReport?url=showSaleBillwiseByFrPdf"
								target="_blank">PDF</a> --%>

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
						<i class="fa fa-list-alt"></i>SP Cake Order-App
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
											<th class="col-sm-1"><input type="checkbox"
												onClick="selectBillNo(this)" /> All<br /></th>
											<th>Sr.No.</th>
											<th>Sequence In Route</th>
											<th>Route Name</th>
											<th>Franchise</th>
											<th>Cake Code</th>
											<th>Weight</th>
											<th>Flavour</th>
											<th>Delivery At</th>

										</tr>
									</thead>
									<tbody>

									</tbody>
								</table>
								<div class="form-group" style="display: none;" id="range">



									<input type="button" id="expExcel" class="btn btn-primary"
										value="EXPORT TO Excel" onclick="createExel();">
								</div>
							</div>
							<div align="center" id="showchart" style="display: none"></div>
						</div>
					</div>
				</form>
			</div>



		</div>

	</div>

	<!-- END Main Content -->

	<footer>
		<p>2019 © Monginis.</p>
	</footer>


	<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
		class="fa fa-chevron-up"></i></a>


	<script type="text/javascript">
		function searchReport() {
			//	var isValid = validate();
			//alert("Hii");

			var from_date = $("#fromDate").val();
			var to_date = $("#toDate").val();
			var menuIdList = $("#menuIdList").val();
			var seqenceList = $("#seqenceList").val();
			//alert("menuIdList" + menuIdList);

			$('#loader').show();

			$
					.getJSON(
							'${getSpCakeList}',

							{

								fromDate : from_date,
								toDate : to_date,
								menuIdList : JSON.stringify(menuIdList),
								seqenceList : seqenceList,

								ajax : 'true'

							},
							function(data) {

								//alert("Hii");

								$('#table_grid td').remove();
								$('#loader').hide();

								if (data == "") {
									alert("No records found !!");
									document.getElementById("expExcel").disabled = true;
								}

								$
										.each(
												data,
												function(key, report) {

													document
															.getElementById("expExcel").disabled = false;
													document
															.getElementById('range').style.display = 'block';

													var index = key + 1;
													var colorName = " ";
													if (report.status == 1) {
														colorName = "red";
													} else if (report.status == 2) {
														colorName = "green";
													}
													//var tr = "<tr>";
													var tr = $('<tr></tr>');

													tr
															.append($(
																	'<td class="col-sm-1"></td>')
																	.html(
																			"<input type='checkbox' name='select_to_print' value="+report.tSpCakeSupNo+">"));

													tr
															.append($(
																	'<td style="text-align:left;  color:'+colorName+';"></td>')
																	.html(
																			report.srNo));

													tr
															.append($(
																	'<td style="text-align:left;  color:'+colorName+';"></td>')
																	.html(
																			report.noInRoute));

													tr
															.append($(
																	'<td style="text-align:left;  color:'+colorName+';"></td>')
																	.html(
																			report.routeName));

													tr
															.append($(
																	'<td style="text-align:left;  color:'+colorName+';"></td>')
																	.html(
																			report.frName));

													tr
															.append($(
																	'<td style="text-align:left;  color:'+colorName+';"></td>')
																	.html(
																			report.spCode));

													tr
															.append($(
																	'<td style="text-align:left;  color:'+colorName+';"></td>')
																	.html(
																			report.inputKgFr));

													tr
															.append($(
																	'<td style="text-align:left;  color:'+colorName+';"></td>')
																	.html(
																			report.spfName));

													tr
															.append($(
																	'<td style="text-align:left;  color:'+colorName+';"></td>')
																	.html(
																			report.spDeliveryPlace));

													$('#table_grid tbody')
															.append(tr);

												})

							});

		}
	</script>

	<script type="text/javascript">
		function createExel() {
			var select_to_print = document.forms[0];
			//alert("Hii");
			var txt = "";
			var i;
			var flag = 0;
			var all = 0;

			for (i = 0; i < select_to_print.length; i++) {
				if (select_to_print[i].checked
						&& select_to_print[i].value != "on") {
					txt = txt + select_to_print[i].value + ",";
					flag = 1;
				}
			}

			if (flag == 1) {

				window
						.open("${pageContext.request.contextPath}/excelForTspCake/"
								+ txt);
			} else {
				alert("Please select minimum 1 CRN Note ");
			}

		}
	</script>
	<!-- 
	<script type="text/javascript">
		function createExel() {

			var fromDate = document.getElementById("fromDate").value;
			var toDate = document.getElementById("toDate").value;
			var select_to_print = document.forms[0];
			alert(JSON.stringify(select_to_print));
			var txt = "";
			var i;
			var flag = 0;
			var all = 0;
			for (i = 0; i < select_to_print.length; i++) {
				if (select_to_print[i].checked
						&& select_to_print[i].value != "on") {
					txt = txt + select_to_print[i].value + ",";
					flag = 1;
				}
			}

			if (flag == 1) {
				$.getJSON('${excelForFrBill}', {
					checkboxes : txt,
					all : all,
					fromDate : fromDate,
					toDate : toDate,
					ajax : 'true'
				}, function(data) {

					alert("Excel Ready");
					exportToExcel();

				});
			} else {
				alert("Select Minimum 1 Bill ");
			}

		}

		function exportToExcel() {
			//alert("Export Excel");
			window.open("${pageContext.request.contextPath}/exportToExcel");
			document.getElementById("expExcel2").disabled = true;

		}
	</script>
 -->

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
		function disableFr() {

			//alert("Inside Disable Fr ");
			document.getElementById("selectFr").disabled = true;

		}

		function disableRoute() {

			//alert("Inside Disable route ");
			var x = document.getElementById("selectRoute")
			//alert(x.options.length);
			var i;
			for (i = 0; i < x; i++) {
				document.getElementById("selectRoute").options[i].disabled;
				//document.getElementById("pets").options[2].disabled = true;
			}
			//document.getElementById("selectRoute").disabled = true;

		}
	</script>
	<script>
		function selectBillNo(source) {
			checkboxes = document.getElementsByName('select_to_print');

			for (var i = 0, n = checkboxes.length; i < n; i++) {
				checkboxes[i].checked = source.checked;
			}

		}
	</script>

	<script type="text/javascript">
		function genPdf() {
			var fromDate = $("#fromDate").val();
			var toDate = $("#toDate").val();

			/* 	window
						.open('${pageContext.request.contextPath}/pdfForReport?url=pdf/getSpCakeCountPdf/'
								+ fromDate + '/' + toDate); */

			window.open("${pageContext.request.contextPath}/getSpCakeCountPdf/"
					+ fromDate + "/" + toDate);

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