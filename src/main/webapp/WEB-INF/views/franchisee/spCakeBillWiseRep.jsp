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
						<i class="fa fa-file-o"></i>Billwise Special Cake Report
					</h1>
				</div>
			</div>
			<!-- END Page Title -->

			<!-- BEGIN Main Content -->
			<div class="row">
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-12">

							<!-- BEGIN Main Content -->
							<div class="box">
								<div class="box-title">
									<h3>
										<i class="fa fa-bars"></i>Billwise Special Cake Report
									</h3>

								</div>

								<div class="box-content">
									<form
										action="${pageContext.request.contextPath}/getSpCakeBillWiseReport"
										method="get">
										<div class="row">


											<div class="form-group">

												<label class="col-sm-3 col-lg-2	 control-label">From
													Date</label>
												<div class="col-sm-6 col-lg-4 controls date_select">
													<input class="form-control date-picker" id="fromDate"
														autocomplete="off" name="fromDate" size="30" type="text"
														value="${fromDate}" />
												</div>


												<label class="col-sm-3 col-lg-2	 control-label">To
													Date</label>
												<div class="col-sm-6 col-lg-4 controls date_select">
													<input class="form-control date-picker" id="toDate"
														autocomplete="off" name="toDate" size="30" type="text"
														value="${toDate}" />
												</div>
											</div>

										</div>


										<br>
										<div class="row">
											<label class="col-sm-3 col-lg-2 control-label"><b></b>Select
												Franchisee</label>
											<div class="col-sm-6 col-lg-4">

												<select data-placeholder="Choose Franchisee"
													class="form-control chosen" multiple="multiple"
													tabindex="6" id="fr_id_list" name="fr_id_list">

													<option value="-1" selected="selected"><c:out
															value="All" /></option>

													<c:forEach items="${frList}" var="fr" varStatus="count">
														<option value="${fr.frId}"><c:out
																value="${fr.frName}" /></option>
													</c:forEach>
												</select>

											</div>

											<div class="form-group">
												<label class="col-sm-3 col-lg-2 control-label">Bill
													No. Wise</label>
												<div class="col-sm-6 col-lg-4 controls">
													<label class="radio-inline"> <input type="radio"
														name="isBill" id="optionsRadios1" value="1" />Yes

													</label> <label class="radio-inline"> <input type="radio"
														name="isBill" id="optionsRadios1" value="0" checked />No

													</label>

												</div>
											</div>
										</div>

										<div class="row">
											<div class="col-sm-6 col-lg-10" style="text-align: center; margin-top: 10px;">
												<button class="btn btn-info" onclick="searchReport()">Search
													Report</button>
												<!-- <button class="btn search_btn" onclick="showChart()">Graph</button> -->


												<!-- <button class="btn btn-primary" value="PDF" id="PDFButton"
								onclick="genPdf()">PDF</button> -->

												<%-- <a href="${pageContext.request.contextPath}/pdfForReport?url=showSaleBillwiseByFrPdf"
								target="_blank">PDF</a> --%>

											</div>
										</div>
									</form>

									<div align="center" id="loader" style="display: none">

										<span>
											<h4>
												<font color="#343690">Loading</font>
											</h4>
										</span> <span class="l-1"></span> <span class="l-2"></span> <span
											class="l-3"></span> <span class="l-4"></span> <span
											class="l-5"></span> <span class="l-6"></span>
									</div>

								</div>
							</div>





							<div class="box">
								<div class="box-title">
									<h3>
										<i class="fa fa-bars"></i> Billwise Special Cake Report
									</h3>
									<div class="box-tool">
										<a href="${pageContext.request.contextPath}/listAllFranchisee">Back
											to List</a> <a data-action="collapse" href="#"><i
											class="fa fa-chevron-up"></i></a>
									</div>
								</div>
								<div class="box-content">
									<div class="col-md-9"></div>
									<label for="search" class="col-md-3" id="search"> <i
										class="fa fa-search" style="font-size: 20px"></i> <input
										type="text" id="myInput" onkeyup="myFunction()"
										placeholder="Search.." title="Type in a name">
									</label>
									<div class="clearfix"></div>
									<div id="table-scroll" class="table-scroll">
										<!-- <input type="button"
											onclick="tableToExcel('table1', 'name', 'Billwise_Special_Cake_Report.xls')"
											value="Export to Excel"> -->
										<div class="table-wrap" style="overflow: auto;">

											<table id="table1" class="table table-advance">
												<thead>
													<tr class="bgpink">
														<th width="158" style="width: 18px" align="left">Sr.
															No.</th>
														<th class="col-md-2">Item Name</th>
														<th class="col-md-2">Invoice No.</th>
														<th class="col-md-2">Invoice Date</th>
														<th class="col-md-2">Sold Qty</th>
														<th class="col-md-2">Sold Amt</th>
													</tr>
												</thead>
												<tbody style="padding-top: 100px">
													<c:forEach items="${frList}" var="frList">
														<c:set value="0" var="sr"></c:set>
														<c:set value="0" var="ttlAmt"></c:set>
														<c:set value="0" var="finalTtlAmt"></c:set>
														<tr>
															<td><c:out value="${frList.frName}"></c:out></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>

														</tr>
														<c:forEach items="${spCakeList}" var="spCakeList"
															varStatus="count">
															<c:if test="${spCakeList.frId==frList.frId}">

																<tr>
																	<td><c:out value="${sr+1}"></c:out></td>
																	<c:set value="${sr+1}" var="sr"></c:set>
																	<td align="left"><c:out
																			value="${spCakeList.itemName}" /></td>
																	<td align="left"><c:out
																			value="${spCakeList.invoiceNo}" /></td>
																	<td align="left"><c:out
																			value="${spCakeList.billDate}" /></td>
																	<td align="left"><c:out value="${spCakeList.qty}" /></td>
																	<td align="right"><fmt:formatNumber type="number"
																			pattern="#" value="${spCakeList.grandTotal}" /></td>
																	<c:set value="${ttlAmt+spCakeList.grandTotal}"
																		var="ttlAmt"></c:set>
																</tr>
															</c:if>
															<c:set value="${finalTtlAmt+spCakeList.grandTotal}"
																var="finalTtlAmt"></c:set>
														</c:forEach>

														<tr>
															<td style="color: red;">Total</td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td style="color: blue;"><fmt:formatNumber
																	type="number" pattern="#" value="${ttlAmt}" /></td>

														</tr>

													</c:forEach>
													<tr>
														<td style="color: red;">Grand Total</td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td style="color: blue;"><fmt:formatNumber
																type="number" pattern="#" value="${finalTtlAmt}" /></td>
													</tr>
												</tbody>

											</table>
										</div>
									</div>

								</div>


							</div>
							<div class="form-group" id="range">



								<div class="col-sm-3  controls">
									<input type="button" id="expExcel" class="btn btn-primary"
										value="EXPORT TO Excel" onclick="exportToExcel();">
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

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>



</body>


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

<script>
	function myFunction() {
		var input, filter, table, tr, td, i;
		input = document.getElementById("myInput");
		filter = input.value.toUpperCase();
		table = document.getElementById("table1");
		tr = table.getElementsByTagName("tr");
		for (i = 0; i < tr.length; i++) {
			td = tr[i].getElementsByTagName("td")[1];
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
<script type="text/javascript">
	function exportToExcel() {
		window.open("${pageContext.request.contextPath}/exportToExcel");
		document.getElementById("expExcel").disabled = true;
	}
</script>

</html>