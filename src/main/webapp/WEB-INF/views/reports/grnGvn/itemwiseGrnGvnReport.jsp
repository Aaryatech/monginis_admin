<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body>

	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<c:url var="getBillList" value="/getSaleBillwise"></c:url>
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
						<i class="fa fa-file-o"></i>Item Wise GRN GVN Report
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
					<li class="active">Item Wise GRN GVN Report</li>
				</ul>
			</div>
			<!-- END Breadcrumb -->

			<!-- BEGIN Main Content -->
			<div class="box">
				<div class="box-title">
					<h3>
						<i class="fa fa-bars"></i>Item Wise GRN GVN Report
					</h3>

				</div>

				<div class="box-content">
					<form id="submitBillForm"
						action="${pageContext.request.contextPath}/itemwiseGrnGvnReport"
						method="get">
						<div class="row">


							<div class="form-group">
								<label class="col-sm-3 col-lg-2	 control-label">From
									Date</label>
								<div class="col-sm-6 col-lg-4 controls date_select">
									<input class="form-control date-picker" id="fromDate"
										name="fromDate" size="30" type="text" value="${fromDate}"
										placeholder="From Date" />
								</div>

								<!-- </div>

					<div class="form-group  "> -->

								<label class="col-sm-3 col-lg-2	 control-label">To Date</label>
								<div class="col-sm-6 col-lg-4 controls date_select">
									<input class="form-control date-picker" id="toDate"
										name="toDate" size="30" type="text" value="${toDate}"
										placeholder="To Date" />
								</div>
							</div>

						</div>


						<br>


						<div class="row">
							<div class="form-group">
								<label class="col-sm-3 col-lg-2 control-label">Select </label>
								<div class="col-sm-6 col-lg-4 controls">
									<select data-placeholder="Select Route"
										class="form-control chosen" name="isGrn" id="isGrn">

										<c:choose>
											<c:when test="${grn==0}">
												<option value="-1">All</option>
												<option value="0" selected>GVN</option>
												<option value="1">GRN</option>
											</c:when>
											<c:when test="${grn==1}">
												<option value="-1">All</option>
												<option value="0">GVN</option>
												<option value="1" selected>GRN</option>
											</c:when>
											<c:otherwise>
												<option value="-1" selected>All</option>
												<option value="0">GVN</option>
												<option value="1">GRN</option>
											</c:otherwise>
										</c:choose>


									</select>

								</div>

							</div>
						</div>

						<br>
						<div class="row">
							<div class="col-md-12" style="text-align: center;">
								<input type="submit" class="btn btn-primary" value="Search">
								<input class="btn btn-primary" value="PDF" id="PDFButton"
									type="button" onclick="genPdf()"> &nbsp;
								<c:if test="${list.size()>0}">
									<input type="button" id="expExcel" class="btn btn-primary"
										value="EXPORT TO Excel" onclick="exportToExcel();">
								</c:if>

							</div>
						</div>
					</form>

					<div align="center" id="loader" style="display: none">

						<span>
							<h4>
								<font color="#343690">Loading</font>
							</h4>
						</span> <span class="l-1"></span> <span class="l-2"></span> <span
							class="l-3"></span> <span class="l-4"></span> <span class="l-5"></span>
						<span class="l-6"></span>
					</div>

					<div class="box-content">
						<div class="row">
							<div class="col-md-12 table-responsive">
								<table class="table table-bordered table-striped fill-head "
									style="width: 100%" id="table_grid">
									<thead>
										<tr>
											<th width="7%">Sr.No.</th>
											<th>Item Name</th>
											<th width="10%">Requested QTY</th>
											<th width="10%">Approved QTY</th>
											<th width="10%">Taxable AMT</th>
											<th width="10%">Tax AMT</th>
											<th width="10%">Total AMT</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach items="${list}" var="list" varStatus="count">
											<tr>
												<td><c:out value="${count.index+1}" /></td>
												<td><c:out value="${list.itemName}" /></td>
												<td style="text-align: right;"><fmt:formatNumber
														type="number" maxFractionDigits="2" minFractionDigits="2"
														groupingUsed="false" value="${list.grnGvnQty}" /></td>
												<td style="text-align: right;"><fmt:formatNumber
														type="number" maxFractionDigits="2" minFractionDigits="2"
														groupingUsed="false" value="${list.aprQtyAcc}" /></td>
												<td style="text-align: right;"><fmt:formatNumber
														type="number" maxFractionDigits="2" minFractionDigits="2"
														groupingUsed="false" value="${list.aprTaxableAmt}" /></td>
												<td style="text-align: right;"><fmt:formatNumber
														type="number" maxFractionDigits="2" minFractionDigits="2"
														groupingUsed="false" value="${list.aprTotalTax}" /></td>
												<td style="text-align: right;"><fmt:formatNumber
														type="number" maxFractionDigits="2" minFractionDigits="2"
														groupingUsed="false" value="${list.aprGrandTotal}" /></td>
											</tr>

										</c:forEach>
									</tbody>
								</table>
							</div>

						</div>

					</div>

				</div>
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
			//	var isValid = validate();

			var selectedFr = $("#selectFr").val();
			var routeId = $("#selectRoute").val();

			var from_date = $("#fromDate").val();
			var to_date = $("#toDate").val();

			$('#loader').show();

			$.getJSON('${getBillList}',

			{
				fr_id_list : JSON.stringify(selectedFr),
				fromDate : from_date,
				toDate : to_date,
				route_id : routeId,
				ajax : 'true'

			}, function(data) {

				$('#table_grid td').remove();
				$('#loader').hide();

				if (data == "") {
					alert("No records found !!");
					document.getElementById("expExcel").disabled = true;
				}

				$.each(data, function(key, report) {

					document.getElementById("expExcel").disabled = false;
					document.getElementById('range').style.display = 'block';
					var index = key + 1;
					//var tr = "<tr>";

					var tr = $('<tr></tr>');

					tr.append($('<td></td>').html(key + 1));

					tr.append($('<td></td>').html(report.invoiceNo));

					tr.append($('<td></td>').html(report.billDate));

					tr.append($('<td></td>').html(report.frName));

					tr.append($('<td></td>').html(report.frCity));

					tr.append($('<td></td>').html(report.frGstNo));

					tr.append($('<td></td>').html(report.taxableAmt));

					if (report.isSameState == 1) {
						tr.append($('<td></td>').html(report.cgstSum));
						tr.append($('<td></td>').html(report.sgstSum));
						tr.append($('<td></td>').html(0));
					} else {
						tr.append($('<td></td>').html(0));
						tr.append($('<td></td>').html(0));
						tr.append($('<td></td>').html(report.igstSum));
					}
					tr.append($('<td></td>').html(report.roundOff));
					var total;

					if (report.isSameState == 1) {
						total = parseFloat(report.taxableAmt)
								+ parseFloat(report.cgstSum + report.sgstSum);
					} else {

						total = report.taxableAmt + report.igstSum;
					}

					tr.append($('<td></td>').html(total));

					$('#table_grid tbody').append(tr);

				})

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
		function updateTotal(orderId, rate) {

			var newQty = $("#billQty" + orderId).val();

			var total = parseFloat(newQty) * parseFloat(rate);

			$('#billTotal' + orderId).html(total);
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

		function genPdf() {
			var fromDate = $("#fromDate").val();
			var toDate = $("#toDate").val();
			var isGrn = $("#isGrn").val();

			window
					.open('${pageContext.request.contextPath}/pdfForDisReport?url=pdf/showItemwiseGrnReportPdf/'
							+ fromDate + '/' + toDate + '/' + isGrn);

			//window.open("${pageContext.request.contextPath}/pdfForReport?url=showSaleReportByDatePdf/"+from_date+"/"+to_date);

		}
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