<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body>

	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<c:url var="getBillList" value="/getSaleReportItemwise"></c:url>

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
					<i class="fa fa-file-o"></i>Tray Target Report
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
				<li class="active">Tray Target Report</li>
			</ul>
		</div>
		<!-- END Breadcrumb -->

		<!-- BEGIN Main Content -->
		<!-- <div class="box">
			<div class="box-content">
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
		</div> -->


		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-list-alt"></i>Tray Target Report
				</h3>

			</div>
			<div class=" box-content">
				<div class="row">
					<div class="col-md-12 table-responsive">
						<table class="table table-bordered table-striped fill-head "
							style="width: 100%" id="table_grid">
							<thead style="background-color: #f3b5db;">
								<tr>
									<th style="text-align: center;" width="10">Sr.No.</th>
									<th style="text-align: center;">Route Name</th>
									<th style="text-align: center;">Franchisee Name</th>
									<th style="text-align: center;">Franchisee Target</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${routeIds}" var="routeIds" varStatus="count">
								
								<c:set var="total" value="${0}"/>
								<c:set var="srno" value="${count.index+1}"/>
								
									<c:forEach items="${route}" var="route" varStatus="count">

										<c:if test="${routeIds==route.routeId}">
										
										<c:set var="total" value="${total + route.frTarget}" />
										
											<tr>
												<td><c:out value="${srno}" /></td>
												<td align="center"><c:out value="${route.routeName}" /></td>
												<td align="center"><c:out value="${route.frName}" /></td>
												<td align="left"><c:out value="${route.frTarget}" /></td>
											</tr>
										</c:if>
									</c:forEach>

									<tr>
										<td></td>
										<td align="center"></td>
										<td align="center">TOTAL</td>
										<td align="left"><c:out value="${total}" /></td>
									</tr>

								</c:forEach>


								<%-- <c:forEach items="${route}" var="route" varStatus="count">

									<tr>
										<td><c:out value="${count.index+1}" /></td>
										<td align="center"><c:out value="${route.routeName}" /></td>
										<td align="center"><c:out value="${route.frName}" /></td>
										<td align="left"><c:out value="${route.frTarget}" /></td>
									</tr>
								</c:forEach> --%>



							</tbody>
						</table>
					</div>
					<div class="col-sm-3  controls">
						<button class="btn btn-primary" value="PDF" id="PDFButton"
							onclick="genPdf()">PDF</button>

						<input type="button" id="expExcel" class="btn btn-primary"
							value="EXPORT TO Excel" onclick="tableToExcel('table_grid', 'name', 'Tray Target Report.xls')">


					</div>
				</div>
			</div>

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
	
		function tableToExcel(table, name, filename) {
			let uri = 'data:application/vnd.ms-excel;base64,', template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><title></title><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>', base64 = function(
					s) {
				return window.btoa(decodeURIComponent(encodeURIComponent(s)))
			}, format = function(s, c) {
				return s.replace(/{(\w+)}/g, function(m, p) {
					return c[p];
				})
			}

			if (!table.nodeType)
				table = document.getElementById(table)
			var ctx = {
				worksheet : name || 'Worksheet',
				table : table.innerHTML
			}

			var link = document.createElement('a');
			link.download = filename;
			link.href = uri + base64(format(template, ctx));
			link.click();
		}
	</script>


	<script type="text/javascript">
		function searchReportExceptTP() {
			//	var isValid = validate();

			var selectedFr = $("#selectFr").val();
			var routeId = $("#selectRoute").val();

			var from_date = $("#fromDate").val();
			var to_date = $("#toDate").val();
			var catId = $("#selectCat").val();//prev--> var catId=-3;
			$('#loader').show();

			$.getJSON('${getBillList}',

			{
				fr_id_list : JSON.stringify(selectedFr),
				fromDate : from_date,
				toDate : to_date,
				route_id : routeId,
				catId : catId,
				ajax : 'true'

			}, function(data) {

				$('#table_grid td').remove();
				$('#loader').hide();

				var totalQty = 0;
				var totalBasicValue = 0;
				var totalCgst = 0;
				var totalSgst = 0;
				var totalIgst = 0;
				var totalGst = 0;
				var totalgrandTotal = 0

				if (data == "") {
					alert("No records found !!");
					document.getElementById("expExcel").disabled = true;

				}

				//	alert("Length " +data.length);
				$.each(data, function(key, report) {
					document.getElementById("expExcel").disabled = false;
					document.getElementById('range').style.display = 'block';
					var index = key + 1;
					//var tr = "<tr>";

					totalQty = totalQty + report.billQtySum;

					totalBasicValue = totalBasicValue + report.taxableAmtSum;
					totalCgst = totalCgst + report.cgstRsSum;
					totalSgst = totalSgst + report.sgstRsSum;
					totalIgst = totalIgst + report.igstRsSum;
					var tr = $('<tr></tr>');
					tr.append($('<td></td>').html(key + 1));
					tr.append($('<td></td>').html(report.itemName));
					tr.append($('<td></td>').html(report.itemHsncd));

					tr.append($('<td style="text-align:right;"></td>').html(
							report.itemTax1 + report.itemTax2));
					tr.append($('<td style="text-align:right;"></td>').html(
							report.billQtySum));

					/* if(report.isSameState==1){
					  	tr.append($('<td></td>').html(report.cgstSum));
					  	tr.append($('<td></td>').html(report.sgstSum));
					  	tr.append($('<td></td>').html(0));
					}
					else{
						tr.append($('<td></td>').html(0));
					  	tr.append($('<td></td>').html(0));
					  	tr.append($('<td></td>').html(report.igstSum));
					} */
					//tr.append($('<td></td>').html(report.igstSum));
					tr.append($('<td style="text-align:right;"></td>').html(
							report.taxableAmtSum.toFixed(2)));
					tr.append($('<td style="text-align:right;"></td>').html(
							report.cgstRsSum.toFixed(2)));
					tr.append($('<td style="text-align:right;"></td>').html(
							report.sgstRsSum.toFixed(2)));
					tr.append($('<td style="text-align:right;"></td>').html(
							report.igstRsSum.toFixed(2)));

					var total = report.sgstRsSum + report.cgstRsSum;
					total = total.toFixed(2);

					totalGst = totalGst + parseFloat(total);

					var grandTotal = report.sgstRsSum + report.cgstRsSum
							+ report.taxableAmtSum

					totalgrandTotal = totalgrandTotal + grandTotal
					grandTotal = grandTotal.toFixed(2);
					tr.append($('<td style="text-align:right;"></td>').html(
							total));
					tr.append($('<td style="text-align:right;"></td>').html(
							grandTotal));
					$('#table_grid tbody').append(tr);

				})

				var tr = $('<tr></tr>');

				tr.append($('<td></td>').html(""));
				tr.append($('<td></td>').html(""));
				tr.append($('<td></td>').html(""));

				tr.append($('<td style="font-weight:bold;"></td>')
						.html("Total"));

				tr.append($('<td style="text-align:right;"></td>').html(
						totalQty.toFixed(2)));
				tr.append($('<td style="text-align:right;"></td>').html(
						totalBasicValue.toFixed(2)));
				tr.append($('<td style="text-align:right;"></td>').html(
						totalCgst.toFixed(2)));
				tr.append($('<td style="text-align:right;"></td>').html(
						totalSgst.toFixed(2)));
				tr.append($('<td style="text-align:right;"></td>').html(
						totalIgst.toFixed(2)));

				tr.append($('<td style="text-align:right;"></td>').html(
						totalGst.toFixed(2)));
				tr.append($('<td style="text-align:right;"></td>').html(
						totalgrandTotal.toFixed(2)));

				$('#table_grid tbody').append(tr);

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

	<script type="text/javascript">
		function genPdf() {
			window
					.open('${pageContext.request.contextPath}/pdf/showRouteTargetReportPdf');
			//document.getElementById("PDFButton").disabled = true;
			

		} //window.open('pdfForReport?url=showSaleBillwiseByFrPdf/'+from_date+'/'+to_date);
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