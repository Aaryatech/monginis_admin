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

	<c:url var="getBillList" value="/getSaleReportRoyConsoByCat"></c:url>
	<c:url var="getAllCatByAjax" value="/getAllCatAjax"></c:url>
	<c:url var="getFrListofAllFr" value="/getFrListForDatewiseReport"></c:url>

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
					<i class="fa fa-file-o"></i>Item-wise Royalty Report
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
					<i class="fa fa-bars"></i>Item-wise Royalty Report
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
							<input class="form-control date-picker" id="toDate" name="toDate"
								size="30" type="text" value="${todaysDate}" />
						</div>
					</div>

				</div>


				<br>

				<!-- <div class="col-sm-9 col-lg-5 controls">
 -->
				<div class="row">
					<div class="form-group">
						<label class="col-sm-3 col-lg-2 control-label">Select
							Route</label>
						<div class="col-sm-6 col-lg-4 controls">
							<select data-placeholder="Select Route"
								class="form-control chosen" name="selectRoute" id="selectRoute"
								onchange="disableFr()">
								<option value="0">Select Route</option>
								<c:forEach items="${routeList}" var="route" varStatus="count">
									<option value="${route.routeId}"><c:out
											value="${route.routeName}" />
									</option>

								</c:forEach>
							</select>

						</div>

						<label class="col-sm-3 col-lg-2 control-label"><b>OR</b></label>

					</div>
				</div>

				<br>

				<div class="row">
					<div class="form-group">


						<label class="col-sm-3 col-lg-2 control-label">Select
							Franchisee</label>
						<div class="col-sm-6 col-lg-10">

							<select data-placeholder="Choose Franchisee"
								class="form-control chosen" multiple="multiple" tabindex="-1"
								id="selectFr" name="selectFr"
								onchange="setAllFrSelected(this.value)"
								onchange="disableRoute()">

								<option value="-1"><c:out value="All" /></option>

								<c:forEach items="${unSelectedFrList}" var="fr"
									varStatus="count">
									<option value="${fr.frId}"><c:out value="${fr.frName}" /></option>
								</c:forEach>
							</select>

						</div>
					</div>
				</div>

				<br>

				<div class="row">
					<div class="form-group">


						<label class="col-sm-3 col-lg-2 control-label">Select
							Category</label>
						<div class="col-sm-6 col-lg-10">

							<select data-placeholder="Choose Category"
								class="form-control chosen" multiple="multiple" tabindex="6"
								id="selectCat" name="selectCat"
								onchange="setCatOptions(this.value)">

								<option value="-1"><c:out value="All" /></option>

								<c:forEach items="${catList}" var="cat" varStatus="count">
									<option value="${cat.catId}"><c:out
											value="${cat.catName}" /></option>
								</c:forEach>
							</select>

						</div>
					</div>
				</div>

				<br>


				<div class="row">

					<div class="form-group">


						<label class="col-sm-3 col-lg-2 control-label">By</label>
						<div class="col-sm-3 col-lg-2">

							<select data-placeholder="Get By" class="form-control chosen"
								tabindex="6" id="getBy" name="getBy">

								<option value="1" selected>Taxable Amt</option>
								<option value="2">Grand Total</option>
							</select>
						</div>
						<label class="col-sm-3 col-lg-2 control-label"
							style="text-align: right;">GRN/CRN</label>
						<div class="col-sm-3 col-lg-2">

							<select data-placeholder="GRN/CRN" class="form-control chosen"
								tabindex="6" id="type" name="type">

								<option value="1">GRN</option>
								<option value="2">CRN</option>
							</select>
						</div>
						<button class="btn btn-info" onclick="searchReport()">Search
							Report</button>

						<button class="btn btn-primary" value="PDF" id="PDFButton"
							onclick="genPdf()">PDF</button>
						<%-- <a
							href="${pageContext.request.contextPath}/pdfForReport?url=showSaleRoyaltyByCatPdf"
							target="_blank">PDF</a>
 --%>
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
						<i class="fa fa-list-alt"></i>Royalty Cons Report (r10)
					</h3>

				</div>

				<form id="submitBillForm"
					action="${pageContext.request.contextPath}/submitNewBill"
					method="post">
					<div class=" box-content">
						<div class="row">
							<div class="col-md-12 table-responsive">
								<table class="table-bordered table-striped fill-head "
									style="width: 100%" id="table_grid">
									<thead style="background-color: #f3b5db; color: white;">
										<tr>
											<th>Sr.No.</th>
											<th>Item Name</th>
											<th>Sale Qty</th>
											<th>Sale Value</th>
											<th>GRN Qty</th>
											<th>GRN Value</th>
											<th>GVN Qty</th>
											<th>GVN Value</th>
											<th>Net Qty</th>
											<th>Net Value</th>
											<th>Royalty %</th>
											<th>Royalty Amt</th>
											<th>Return % GRN</th>
											<th>Return % GVN</th>
											<th>Return % SUM</th>
											<th>Contribution %</th>

										</tr>
									</thead>
									<tbody>

									</tbody>
								</table>
							</div>
							<div class="form-group" style="display: none;" id="range">



								<div class="col-sm-3  controls">
									<input type="button" id="expExcel" class="btn btn-primary"
										value="EXPORT TO Excel" onclick="exportToExcel();"
										disabled="disabled">
								</div>
							</div>
						</div>


						<br> <input type="button"
							onclick="tableToExcel('table_grid1', 'name', 'RoyaltySummaryReport.xls')"
							value="Export to Excel">
						<div class="col-md-12 table-responsive">
							<table style="width: 100%; border: 1px;" id="table_grid1"
								border="1">
								<thead style="background-color: #f3b5db;">
									<tr>
										<th>Sr.No.</th>
										<th>Item Name</th>
										<th>Sale Qty</th>
										<th>Sale Value</th>
										<th>GRN Qty</th>
										<th>GRN Value</th>
										<th>GVN Qty</th>
										<th>GVN Value</th>
										<th>Net Qty</th>
										<th>Net Value</th>
										<th>Royalty %</th>
										<th>Royalty Amt</th>
										<th>Return % GRN</th>
										<th>Return % GVN</th>
										<th>Return % SUM</th>
										<th>Contribution %</th>


									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>

					</div>

					<div id="chart_div" style="width: 100%; height: 700px;"></div>
					<div id="PieChart_div" style="width: 100%; height: 700px;"></div>

				</form>
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
				var isValid = validate();

				//document.getElementById('chart').style.display = "block";
				document.getElementById("PieChart_div").style = "display:none";
				document.getElementById("chart_div").style = "display:none";
				if (isValid == true) {
					var selectedFr = $("#selectFr").val();
					var routeId = $("#selectRoute").val();
					var isGraph = 0;

					var selectedCat = $("#selectCat").val();
					var getBy = $("#getBy").val();
					var type = $("#type").val();
					var from_date = $("#fromDate").val();
					var to_date = $("#toDate").val();
					// alert(selectedCat);
					$('#loader').show();

					$
							.getJSON(
									'${getBillList}',

									{
										fr_id_list : JSON.stringify(selectedFr),
										fromDate : from_date,
										toDate : to_date,
										route_id : routeId,
										cat_id_list : JSON
												.stringify(selectedCat),
										is_graph : isGraph,
										getBy : getBy,
										type : type,
										ajax : 'true'

									},
									function(data) {

										$('#table_grid td').remove();
										$('#table_grid1 td').remove();
										$('#loader').hide();
										var royPer = ${royPer};
										
										//alert(royPer);
										
										if (data == "") {
											alert("No records found !!");
											document.getElementById("expExcel").disabled = true;

										}
										
										var finalCatNetQtyTotal = 0.0;
										var finalCatNetValueTotal = 0.0;
										var finalCatRAmtTotal = 0.0;
										var finalCatBillQtyTotal = 0.0;
										var finalCatBillValTotal = 0.0;
										var finalCatGrnQtyTotal = 0.0;
										var finalCatGvnQtyTotal = 0.0;
										var finalCatGvnValTotal = 0.0;
										var finalCatGrnValTotal = 0.0;
										var finalContri = 0.0;
										
										
										var contriTotalNet = 0;
										$.each(data.salesReportRoyalty, function(key, report) {
												
												var billVal=0;
												var grnVal=0;
												var gvnVal=0;
												
												if(getBy==1){
													
													billVal=report.taxableAmt;
													
													if(type==1){
													
														grnVal=report.grnTaxableAmt;
														gvnVal=report.gvnTaxableAmt;
														
													}else{

														grnVal=report.crnGrnTaxableAmt;
														gvnVal=report.crnGvnTaxableAmt;

													}
													
												}else{
												
													billVal=report.grandTotal;
													
													if(type==1){
														
														grnVal=report.grnGrandTotal;
														gvnVal=report.gvnGrandTotal;
														
													}else{

														grnVal=report.crnGrnGrandTotal;
														gvnVal=report.crnGvnGrandTotal;

													}

												}
												
												var netValue = billVal - (grnVal + gvnVal);
												
											contriTotalNet = contriTotalNet
													+ netValue;
										});
										
										

										$
												.each(
														data.categoryList,
														function(key, cat) {
															document
																	.getElementById("expExcel").disabled = false;
															document
																	.getElementById('range').style.display = 'block';

															var tr = $('<tr></tr>');
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					cat.catName));
															//tr.append($('<td></td>').html(key+1));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					""));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					""));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					""));

															tr
																	.append($(
																			'<td></td>')
																			.html(
																					""));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					""));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					""));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					""));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					""));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					""));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					""));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					""));
															
															tr
															.append($(
																	'<td></td>')
																	.html(
																			""));
															
															tr
															.append($(
																	'<td></td>')
																	.html(
																			""));
															
															tr
															.append($(
																	'<td></td>')
																	.html(
																			""));
															
															tr
															.append($(
																	'<td></td>')
																	.html(
																			""));
															
															$(
																	'#table_grid tbody')
																	.append(tr);

															var srNo = 0;
															
															var catNetQtyTotal = 0.0;
															var catNetValueTotal = 0.0;
															var catRAmtTotal = 0.0;
															var catBillQtyTotal = 0.0;
															var catBillValTotal = 0.0;
															var catGrnQtyTotal = 0.0;
															var catGvnQtyTotal = 0.0;
															var catGvnValTotal = 0.0;
															var catGrnValTotal = 0.0;
															
															
															$.each(data.salesReportRoyalty,
																			function(
																					key,
																					report) {
																
																

																if (cat.catId == report.catId) {
																	
																
																					
																					
																					var billQty=report.billQty;
																					var billVal=0;
																					var grnQty=0;
																					var grnVal=0;
																					var gvnQty=0;
																					var gvnVal=0;
																					
																					if(getBy==1){
																						
																						billVal=report.taxableAmt;
																						
																						if(type==1){
																						
																							grnVal=report.grnTaxableAmt;
																							gvnVal=report.gvnTaxableAmt;
																							
																						}else{

																							grnVal=report.crnGrnTaxableAmt;
																							gvnVal=report.crnGvnTaxableAmt;

																						}
																						
																					}else{
																					
																						billVal=report.grandTotal;
																						
																						if(type==1){
																							
																							grnVal=report.grnGrandTotal;
																							gvnVal=report.gvnGrandTotal;
																							
																						}else{

																							grnVal=report.crnGrnGrandTotal;
																							gvnVal=report.crnGvnGrandTotal;

																						}

																					}
																					
																					if(type==1){
																						
																						grnQty=report.grnQty;
																						gvnQty=report.gvnQty;
																						
																					}else{

																						grnQty=report.crnGrnQty;
																						gvnQty=report.crnGvnQty;

																					}
																					
																					
																					if(billVal>0 || grnVal>0 || gvnVal>0){
																					
																					
																					//alert("Hi");
																					srNo = srNo + 1;
																					//var index = key + 1;
																					var tr = $('<tr></tr>');
																					//tr.append($('<td></td>').html(cat.catName));
																					tr
																							.append($(
																									'<td></td>')
																									.html(
																											srNo));
																					tr
																							.append($(
																									'<td></td>')
																									.html(
																											report.item_name));
																					tr
																							.append($(
																									'<td  style="text-align:right;"></td>')
																									.html(addCommas(billQty)));
																					tr
																							.append($(
																									'<td  style="text-align:right;"></td>')
																									.html(
																											addCommas(billVal.toFixed(2))));

																					tr
																							.append($(
																									'<td  style="text-align:right;"></td>')
																									.html(addCommas(grnQty)));
																					tr
																							.append($(
																									'<td  style="text-align:right;"></td>')
																									.html(addCommas(grnVal.toFixed(2))));
																					tr
																							.append($(
																									'<td  style="text-align:right;"></td>')
																									.html(addCommas(gvnQty)));
																					tr
																							.append($(
																									'<td  style="text-align:right;"></td>')
																									.html(addCommas(gvnVal.toFixed(2))));

																					var netQty = billQty - (grnQty + gvnQty);
																					netQty = netQty.toFixed(2);

																					var netValue = billVal - (grnVal + gvnVal);
																					netValue = netValue.toFixed(2);

																					tr
																							.append($(
																									'<td  style="text-align:right;"></td>')
																									.html(
																											addCommas(netQty)));
																					tr
																							.append($(
																									'<td  style="text-align:right;"></td>')
																									.html(
																											addCommas(netValue)));
																					tr
																							.append($(
																									'<td style="text-align:right;"></td>')
																									.html(
																											royPer));

																					rAmt = netValue
																							* royPer
																							/ 100;
																					rAmt = rAmt
																							.toFixed(2);

																					tr
																							.append($(
																									'<td style="text-align:right;"></td>')
																									.html(
																											addCommas(rAmt)));
																					
																					
																					var grnRet=0;
																					var gvnRet=0;
																					var sumRet=0;
																					
																					if(billVal>0){
																						grnRet=(grnVal*100)/billVal;
																						gvnRet=(gvnVal*100)/billVal;
																						sumRet=((grnVal+gvnVal)*100)/billVal;
																					}
																					
																					
																					tr.append($(
																							'<td  style="text-align:right;"></td>')
																							.html(grnRet.toFixed(2)));

																					tr.append($(
																					'<td  style="text-align:right;"></td>')
																					.html(gvnRet.toFixed(2)));

																					tr.append($(
																					'<td  style="text-align:right;"></td>')
																					.html(sumRet.toFixed(2)));
																					
																					
																					var contri = (netValue * 100)/ contriTotalNet;
																					finalContri = finalContri+ contri;
																			
																			
																					tr.append($(
																					'<td  style="text-align:right;"></td>')
																					.html(contri.toFixed(2)));
																					
																					

																					
																					/* CAT TOTAL */
																					
																					catNetQtyTotal=parseFloat(catNetQtyTotal)+parseFloat(netQty);
																					catNetValueTotal=parseFloat(catNetValueTotal)+parseFloat(netValue);
																					catRAmtTotal=parseFloat(catRAmtTotal)+parseFloat(rAmt);
																					catBillQtyTotal=catBillQtyTotal+billQty;
																					catBillValTotal=catBillValTotal+billVal;
																					catGrnQtyTotal=catGrnQtyTotal+grnQty;
																					catGrnValTotal=catGrnValTotal+grnVal;
																					catGvnQtyTotal=catGvnQtyTotal+gvnQty;
																					catGvnValTotal=catGvnValTotal+gvnVal;
																					
																					/* FINAL TOTAL */
																					
																					finalCatNetQtyTotal=finalCatNetQtyTotal+parseFloat(netQty);
																					finalCatNetValueTotal=finalCatNetValueTotal+parseFloat(netValue);
																					finalCatRAmtTotal=finalCatRAmtTotal+parseFloat(rAmt);
																					finalCatBillQtyTotal=finalCatBillQtyTotal+billQty;
																					finalCatBillValTotal=finalCatBillValTotal+billVal;
																					finalCatGrnQtyTotal=finalCatGrnQtyTotal+grnQty;
																					finalCatGvnQtyTotal=finalCatGvnQtyTotal+gvnQty;
																					finalCatGvnValTotal=finalCatGvnValTotal+gvnVal;
																					finalCatGrnValTotal=finalCatGrnValTotal+grnVal;
																					
																					
																					$('#table_grid tbody').append(tr);
																					
																					}

																				}//end of if

																			})
																			
																			/* CAT TOTAL DISPLAY */
																			
																			var tr = $('<tr style="background-color:lightgrey;"></tr>');
															
															
															
														 	tr
																	.append($(
																			'<td></td>')
																			.html('' ));
															tr
																	.append($(
																			'<td></td>')
																			.html(cat.catName));
															tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(addCommas(catBillQtyTotal.toFixed(2))));
															tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(addCommas(catBillValTotal.toFixed(2))));

																tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(addCommas(catGrnQtyTotal.toFixed(2))));
															tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(addCommas(catGrnValTotal.toFixed(2))));
																tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(addCommas(catGvnQtyTotal.toFixed(2))));
															tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(addCommas(catGvnValTotal.toFixed(2))));

																tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(addCommas(catNetQtyTotal.toFixed(2))));
																	tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(addCommas(catNetValueTotal.toFixed(2))));
															tr
																	.append($(
																			'<td style="text-align:right;"></td>')
																			.html(royPer));

															tr
																	.append($(
																			'<td style="text-align:right;"></td>')
																			.html(addCommas(catRAmtTotal.toFixed(2)))); 
															
															
															var grnRetCat=(catGrnValTotal*100)/catBillValTotal;
															var gvnRetCat=(catGvnValTotal*100)/catBillValTotal;
															var sumRetCat=((catGrnValTotal+catGvnValTotal)*100)/catBillValTotal;
															
															tr
															.append($(
																	'<td  style="text-align:right;"></td>')
																	.html(grnRetCat.toFixed(2)));
															
															tr
															.append($(
																	'<td  style="text-align:right;"></td>')
																	.html(gvnRetCat.toFixed(2)));
															
															tr
															.append($(
																	'<td  style="text-align:right;"></td>')
																	.html(sumRetCat.toFixed(2)));
															
															
															tr
															.append($(
																	'<td  style="text-align:right;"></td>')
																	.html(" "));
															
															
															
														
															
															
															$('#table_grid tbody').append(tr);
																			
																			
																			
														})
														
														var tr = $('<tr style="background-color:lightgrey;"></tr>');
															
															
														 	tr
																	.append($(
																			'<td></td>')
																			.html('' ));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					'TOTAL'));
															tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(addCommas(finalCatBillQtyTotal.toFixed(2))));
															tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(addCommas(finalCatBillValTotal.toFixed(2))));

																tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(addCommas(finalCatGrnQtyTotal.toFixed(2))));
															tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(addCommas(finalCatGrnValTotal.toFixed(2))));
																tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(addCommas(finalCatGvnQtyTotal.toFixed(2))));
															tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(addCommas(finalCatGvnValTotal.toFixed(2))));

																tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(addCommas(finalCatNetQtyTotal.toFixed(2))));
																	tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(addCommas(finalCatNetValueTotal.toFixed(2))));
															tr
																	.append($(
																			'<td style="text-align:right;"></td>')
																			.html(royPer));

															tr
																	.append($(
																			'<td style="text-align:right;"></td>')
																			.html(addCommas(finalCatRAmtTotal.toFixed(2)))); 
															
															
															var grnRetTot=(finalCatGrnValTotal*100)/finalCatBillValTotal;
															var gvnRetTot=(finalCatGvnValTotal*100)/finalCatBillValTotal;
															var sumRetTot=((finalCatGrnValTotal+finalCatGvnValTotal)*100)/finalCatBillValTotal;
															
															tr
															.append($(
																	'<td  style="text-align:right;"></td>')
																	.html(grnRetTot.toFixed(2)));
															
															tr
															.append($(
																	'<td  style="text-align:right;"></td>')
																	.html(gvnRetTot.toFixed(2)));
															
															tr
															.append($(
																	'<td  style="text-align:right;"></td>')
																	.html(sumRetTot.toFixed(2)));
															
															tr
															.append($(
																	'<td  style="text-align:right;"></td>')
																	.html(finalContri.toFixed(2)));
															
															
															
															
															$('#table_grid tbody').append(tr);
														
														/* ----------------------------------------------------------------------- */

										var finalNetQtyTotal = 0.0;
										var finalNetValueTotal = 0.0;
										var finalRAmtTotal = 0.0;
										var finalBillQtyTotal = 0.0;
										var finalBillTaxableAmtTotal = 0.0;
										var finalGrnQtyTotal = 0.0;
										var finalGvnQtyTotal = 0.0;
										var finalGvnTaxableAmtTotal = 0.0;
										var finalGrnTaxableAmtTotal = 0.0;
										var finalContriTotal = 0.0;
										
										

										var contriTotalNet = 0;
										$.each(data.salesReportRoyalty, function(key, report) {
												
												var billVal=0;
												var grnVal=0;
												var gvnVal=0;
												
												if(getBy==1){
													
													billVal=report.taxableAmt;
													
													if(type==1){
													
														grnVal=report.grnTaxableAmt;
														gvnVal=report.gvnTaxableAmt;
														
													}else{

														grnVal=report.crnGrnTaxableAmt;
														gvnVal=report.crnGvnTaxableAmt;

													}
													
												}else{
												
													billVal=report.grandTotal;
													
													if(type==1){
														
														grnVal=report.grnGrandTotal;
														gvnVal=report.gvnGrandTotal;
														
													}else{

														grnVal=report.crnGrnGrandTotal;
														gvnVal=report.crnGvnGrandTotal;

													}

												}
												
												var netValue = billVal - (grnVal + gvnVal);
												
											contriTotalNet = contriTotalNet
													+ netValue;
										});
										

										$
												.each(
														data.categoryList,
														function(key, cat) {
															var netQtySum = 0.0;
															var netValueSum = 0.0;
															var rAmtSum = 0.0;
															var billQtySum = 0.0;
															var billTaxableAmtSum = 0.0;
															var grnQtySum = 0.0;
															var gvnQtySum = 0.0;
															var gvnTaxableAmtSum = 0.0;
															var grnTaxableAmtSum = 0.0;
															var tr = $('<tr style="background-color:pink;"></tr>');
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					""));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					cat.catName));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					""));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					""));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					""));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					""));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					""));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					""));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					""));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					""));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					""));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					""));
															
															tr
															.append($(
																	'<td></td>')
																	.html(
																			""));
															
															tr
															.append($(
																	'<td></td>')
																	.html(
																			""));
															
															tr
															.append($(
																	'<td></td>')
																	.html(
																			""));
															
															tr
															.append($(
																	'<td></td>')
																	.html(
																			""));
															
															$(
																	'#table_grid1 tbody')
																	.append(tr);
															
															
															var srNo = 0;
															$
																	.each(
																			cat.subCategory,
																			function(
																					key,
																					subcat) {
																				
																				
																				
																				var tr = $('<tr style="background-color:lightgrey;"></tr>');
																				tr
																						.append($(
																								'<td  style="text-align:right;"></td>')
																								.html(
																										""));
																				tr
																						.append($(
																								'<td></td>')
																								.html(
																										subcat.subCatName));
																				tr
																						.append($(
																								'<td  style="text-align:right;"></td>')
																								.html(
																										""));
																				tr
																						.append($(
																								'<td  style="text-align:right;"></td>')
																								.html(
																										("")));

																				tr
																						.append($(
																								'<td  style="text-align:right;"></td>')
																								.html(
																										""));
																				tr
																						.append($(
																								'<td  style="text-align:right;"></td>')
																								.html(
																										""));
																				tr
																						.append($(
																								'<td  style="text-align:right;"></td>')
																								.html(
																										""));
																				tr
																						.append($(
																								'<td  style="text-align:right;"></td>')
																								.html(
																										""));

																				tr
																						.append($(
																								'<td  style="text-align:right;"></td>')
																								.html(
																										""));
																				tr
																						.append($(
																								'<td  style="text-align:right;"></td>')
																								.html(
																										""));
																				tr
																						.append($(
																								'<td></td>')
																								.html(
																										""));
																				tr
																						.append($(
																								'<td></td>')
																								.html(
																										""));
																				
																				tr
																				.append($(
																						'<td></td>')
																						.html(
																								""));
																				
																				tr
																				.append($(
																						'<td></td>')
																						.html(
																								""));
																				
																				tr
																				.append($(
																						'<td></td>')
																						.html(
																								""));
																				
																				tr
																				.append($(
																						'<td></td>')
																						.html(
																								""));
																				
																				
																				$(
																						'#table_grid1 tbody')
																						.append(
																								tr);
																				
																				
																				var netQtyTotal = 0.0;
																				var netValueTotal = 0.0;
																				var rAmtTotal = 0.0;
																				var billQtyTotal = 0.0;
																				var billTaxableAmtTotal = 0.0;
																				var grnQtyTotal = 0.0;
																				var gvnQtyTotal = 0.0;
																				var gvnTaxableAmtTotal = 0.0;
																				var grnTaxableAmtTotal = 0.0;
																				$
																						.each(
																								data.salesReportRoyalty,
																								function(
																										key,
																										report) {

																									if (subcat.subCatId == report.subCatId) {
																										
																										
																										var billQty=report.billQty;
																										var billVal=0;
																										var grnQty=0;
																										var grnVal=0;
																										var gvnQty=0;
																										var gvnVal=0;
																										
																										if(getBy==1){
																											
																											billVal=report.taxableAmt;
																											
																											if(type==1){
																											
																												grnVal=report.grnTaxableAmt;
																												gvnVal=report.gvnTaxableAmt;
																												
																											}else{

																												grnVal=report.crnGrnTaxableAmt;
																												gvnVal=report.crnGvnTaxableAmt;

																											}
																											
																										}else{
																										
																											billVal=report.grandTotal;
																											
																											if(type==1){
																												
																												grnVal=report.grnGrandTotal;
																												gvnVal=report.gvnGrandTotal;
																												
																											}else{

																												grnVal=report.crnGrnGrandTotal;
																												gvnVal=report.crnGvnGrandTotal;

																											}

																										}
																										
																										if(type==1){
																											
																											grnQty=report.grnQty;
																											gvnQty=report.gvnQty;
																											
																										}else{

																											grnQty=report.crnGrnQty;
																											gvnQty=report.crnGvnQty;

																										}
																										
																										if(billVal>0 || grnVal>0 || gvnVal>0){

																										srNo = srNo + 1;

																										billQtyTotal = billQtyTotal + billQty;
																										billTaxableAmtTotal = billTaxableAmtTotal+billVal;

																										grnQtyTotal = grnQtyTotal+grnQty;
																										gvnQtyTotal = gvnQtyTotal+gvnQty;

																										grnTaxableAmtTotal = grnTaxableAmtTotal+grnVal;
																										gvnTaxableAmtTotal = gvnTaxableAmtTotal+gvnVal;
																										var netQty = billQty - (grnQty + gvnQty);
																										netQtyTotal = netQtyTotal + netQty;

																										var netValue = billVal - (grnVal + gvnVal);
																										netValueTotal = netValueTotal + netValue;
																										rAmt = netValue * royPer/ 100;
																										rAmtTotal = rAmtTotal + rAmt;

																										var tr = $('<tr></tr>');
																										//tr.append($('<td></td>').html(cat.catName));
																										tr
																												.append($(
																														'<td></td>')
																														.html(
																																srNo));
																										tr
																												.append($(
																														'<td></td>')
																														.html(
																																report.item_name));
																										tr
																												.append($(
																														'<td  style="text-align:right;"></td>')
																														.html(addCommas(billQty.toFixed(2))));
																										tr
																												.append($(
																														'<td  style="text-align:right;"></td>')
																														.html(addCommas(billVal.toFixed(2))));

																										tr
																												.append($(
																														'<td  style="text-align:right;"></td>')
																														.html(addCommas(grnQty.toFixed(2))));
																										tr
																												.append($(
																														'<td  style="text-align:right;"></td>')
																														.html(addCommas(grnVal.toFixed(2))));
																										tr
																												.append($(
																														'<td  style="text-align:right;"></td>')
																														.html(addCommas(gvnQty.toFixed(2))));
																										tr
																												.append($(
																														'<td  style="text-align:right;"></td>')
																														.html(addCommas(gvnVal.toFixed(2))));
																										tr
																												.append($(
																														'<td  style="text-align:right;"></td>')
																														.html(
																																addCommas(netQty)));
																										tr
																												.append($(
																														'<td  style="text-align:right;"></td>')
																														.html(
																																addCommas(netValue
																																		.toFixed(2))));
																										tr
																												.append($(
																														'<td style="text-align:right;"></td>')
																														.html(
																																royPer));
																										tr
																												.append($(
																														'<td style="text-align:right;"></td>')
																														.html(
																																addCommas(rAmt
																																		.toFixed(2))));
																										

																										var grnRet=0;
																										var gvnRet=0;
																										var sumRet=0;
																										
																										if(billVal>0){
																											grnRet=(grnVal*100)/billVal;
																											gvnRet=(gvnVal*100)/billVal;
																											sumRet=((grnVal+gvnVal)*100)/billVal;
																											
																										}
																										
																										tr.append($(
																												'<td  style="text-align:right;"></td>')
																												.html(grnRet.toFixed(2)));

																										tr.append($(
																										'<td  style="text-align:right;"></td>')
																										.html(gvnRet.toFixed(2)));

																										tr.append($(
																										'<td  style="text-align:right;"></td>')
																										.html(sumRet.toFixed(2)));
																										
																										
																										var contri = (netValue * 100)/ contriTotalNet;
																										finalContriTotal = finalContriTotal+ contri;
																								
																								
																										tr.append($(
																										'<td  style="text-align:right;"></td>')
																										.html(contri.toFixed(2)));
																										
																										
																										$(
																												'#table_grid1 tbody')
																												.append(
																														tr);
																										}
																									}//end of if

																								})
																				netQtySum = netQtySum
																						+ netQtyTotal;
																				netValueSum = netValueSum
																						+ netValueTotal;
																				rAmtSum = rAmtSum
																						+ rAmtTotal;
																				billQtySum = billQtySum
																						+ billQtyTotal;
																				billTaxableAmtSum = billTaxableAmtSum
																						+ billTaxableAmtTotal;
																				grnQtySum = grnQtySum
																						+ grnQtyTotal;
																				gvnQtySum = gvnQtySum
																						+ gvnQtyTotal;
																				gvnTaxableAmtSum = gvnTaxableAmtSum
																						+ gvnTaxableAmtTotal;
																				grnTaxableAmtSum = grnTaxableAmtSum
																						+ grnTaxableAmtTotal;

																				var tr = $('<tr style="background-color:lightgrey;"></tr>');
																				tr
																						.append($(
																								'<td></td>')
																								.html(
																										" "));
																				tr
																						.append($(
																								'<td></td>')
																								.html(
																										subcat.subCatName
																												+ " Total"));
																				tr
																						.append($(
																								'<td  style="text-align:right;"></td>')
																								.html(
																										addCommas(billQtyTotal
																												.toFixed(2))));
																				tr
																						.append($(
																								'<td  style="text-align:right;"></td>')
																								.html(
																										addCommas((billTaxableAmtTotal
																												.toFixed(2)))));

																				tr
																						.append($(
																								'<td  style="text-align:right;"></td>')
																								.html(
																										addCommas(grnQtyTotal
																												.toFixed(2))));
																				tr
																						.append($(
																								'<td  style="text-align:right;"></td>')
																								.html(
																										addCommas(grnTaxableAmtTotal
																												.toFixed(2))));
																				tr
																						.append($(
																								'<td  style="text-align:right;"></td>')
																								.html(
																										addCommas(gvnQtyTotal
																												.toFixed(2))));
																				tr
																						.append($(
																								'<td  style="text-align:right;"></td>')
																								.html(
																										addCommas(gvnTaxableAmtTotal
																												.toFixed(2))));

																				tr
																						.append($(
																								'<td  style="text-align:right;"></td>')
																								.html(
																										addCommas(netQtyTotal
																												.toFixed(2))));
																				tr
																						.append($(
																								'<td  style="text-align:right;"></td>')
																								.html(
																										addCommas(netValueTotal
																												.toFixed(2))));
																				tr
																						.append($(
																								'<td style="text-align:right;"></td>')
																								.html(
																										royPer
																												.toFixed(2)));
																				tr
																						.append($(
																								'<td style="text-align:right;"></td>')
																								.html(
																										addCommas(rAmtTotal
																												.toFixed(2))));
																				
																				
																				var grnRet=(grnTaxableAmtTotal*100)/billTaxableAmtTotal;
																				var gvnRet=(gvnTaxableAmtTotal*100)/billTaxableAmtTotal;
																				var sumRet=((grnTaxableAmtTotal+gvnTaxableAmtTotal)*100)/billTaxableAmtTotal;
																				
																				if(isNaN(grnRet)){
																					grnRet=0;
																				}

																				if(isNaN(gvnRet)){
																					gvnRet=0;
																				}

																				if(isNaN(sumRet)){
																					sumRet=0;
																				}
																				
																				tr.append($(
																						'<td  style="text-align:right;"></td>')
																						.html(grnRet.toFixed(2)));

																				tr.append($(
																				'<td  style="text-align:right;"></td>')
																				.html(gvnRet.toFixed(2)));

																				tr.append($(
																				'<td  style="text-align:right;"></td>')
																				.html(sumRet.toFixed(2)));
																				
																				tr.append($(
																				'<td  style="text-align:right;"></td>')
																				.html(" "));
																				
																				
																				$(
																						'#table_grid1 tbody')
																						.append(
																								tr);

																			})

															finalNetQtyTotal = finalNetQtyTotal
																	+ netQtySum;
															finalNetValueTotal = finalNetValueTotal
																	+ netValueSum;
															finalRAmtTotal = finalRAmtTotal
																	+ rAmtSum;
															finalBillQtyTotal = finalBillQtyTotal
																	+ billQtySum;
															finalBillTaxableAmtTotal = finalBillTaxableAmtTotal
																	+ billTaxableAmtSum;
															finalGrnQtyTotal = finalGrnQtyTotal
																	+ grnQtySum;
															finalGvnQtyTotal = finalGvnQtyTotal
																	+ gvnQtySum;
															finalGvnTaxableAmtTotal = finalGvnTaxableAmtTotal
																	+ gvnTaxableAmtSum;
															finalGrnTaxableAmtTotal = finalGrnTaxableAmtTotal
																	+ grnTaxableAmtSum;

															var tr = $('<tr style="background-color:lightgrey; font-weight:bold;"></tr>');
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					" "));
															tr
																	.append($(
																			'<td></td>')
																			.html(
																					"Total"));
															tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(
																					addCommas(billQtySum
																							.toFixed(2))));
															tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(
																					addCommas((billTaxableAmtSum
																							.toFixed(2)))));

															tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(
																					addCommas(grnQtySum
																							.toFixed(2))));
															tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(
																					addCommas(grnTaxableAmtSum
																							.toFixed(2))));
															tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(
																					addCommas(gvnQtySum
																							.toFixed(2))));
															tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(
																					addCommas(gvnTaxableAmtSum
																							.toFixed(2))));

															tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(
																					addCommas(netQtySum
																							.toFixed(2))));
															tr
																	.append($(
																			'<td  style="text-align:right;"></td>')
																			.html(
																					addCommas(netValueSum
																							.toFixed(2))));
															tr
																	.append($(
																			'<td style="text-align:right;"></td>')
																			.html(
																					royPer
																							.toFixed(2)));
															tr
																	.append($(
																			'<td style="text-align:right;"></td>')
																			.html(
																					addCommas(rAmtSum
																							.toFixed(2))));
															
															
															var grnRet=(grnTaxableAmtSum*100)/billTaxableAmtSum;
															var gvnRet=(gvnTaxableAmtSum*100)/billTaxableAmtSum;
															var sumRet=((grnTaxableAmtSum+gvnTaxableAmtSum)*100)/billTaxableAmtSum;
															
															tr.append($(
																	'<td  style="text-align:right;"></td>')
																	.html(grnRet.toFixed(2)));

															tr.append($(
															'<td  style="text-align:right;"></td>')
															.html(gvnRet.toFixed(2)));

															tr.append($(
															'<td  style="text-align:right;"></td>')
															.html(sumRet.toFixed(2)));
															
															tr.append($(
															'<td  style="text-align:right;"></td>')
															.html(" "));
															
															
															
															$(
																	'#table_grid1 tbody')
																	.append(tr);
														})

										var tr = $('<tr style="background-color:lightgrey; font-weight:bold;"></tr>');
										tr.append($('<td></td>').html(" "));
										tr.append($('<td></td>').html(
												"All Category Total"));
										tr
												.append($(
														'<td  style="text-align:right;"></td>')
														.html(
																finalBillQtyTotal
																		.toFixed(2)));
										tr
												.append($(
														'<td  style="text-align:right;"></td>')
														.html(
																(finalBillTaxableAmtTotal
																		.toFixed(2))));

										tr
												.append($(
														'<td  style="text-align:right;"></td>')
														.html(
																finalGrnQtyTotal
																		.toFixed(2)));
										tr
												.append($(
														'<td  style="text-align:right;"></td>')
														.html(
																finalGrnTaxableAmtTotal
																		.toFixed(2)));
										tr
												.append($(
														'<td  style="text-align:right;"></td>')
														.html(
																finalGvnQtyTotal
																		.toFixed(2)));
										tr
												.append($(
														'<td  style="text-align:right;"></td>')
														.html(
																finalGvnTaxableAmtTotal
																		.toFixed(2)));

										tr
												.append($(
														'<td  style="text-align:right;"></td>')
														.html(
																finalNetQtyTotal
																		.toFixed(2)));
										tr
												.append($(
														'<td  style="text-align:right;"></td>')
														.html(
																finalNetValueTotal
																		.toFixed(2)));
										tr
												.append($(
														'<td style="text-align:right;"></td>')
														.html(royPer.toFixed(2)));
										tr
												.append($(
														'<td style="text-align:right;"></td>')
														.html(
																finalRAmtTotal
																		.toFixed(2)));
										
										
										var grnRet=(finalGrnTaxableAmtTotal*100)/finalBillTaxableAmtTotal;
										var gvnRet=(finalGvnTaxableAmtTotal*100)/finalBillTaxableAmtTotal;
										var sumRet=((finalGrnTaxableAmtTotal+finalGvnTaxableAmtTotal)*100)/finalBillTaxableAmtTotal;
										
										tr.append($(
												'<td  style="text-align:right;"></td>')
												.html(grnRet.toFixed(2)));

										tr.append($(
										'<td  style="text-align:right;"></td>')
										.html(gvnRet.toFixed(2)));

										tr.append($(
										'<td  style="text-align:right;"></td>')
										.html(sumRet.toFixed(2)));
										
										tr.append($(
										'<td  style="text-align:right;"></td>')
										.html(finalContriTotal.toFixed(2)));
										
										
										
										$('#table_grid1 tbody').append(tr);

									});
				}
			}
		</script>


		<script type="text/javascript">
			function showChart() {

				$("#PieChart_div").empty();
				$("#chart_div").empty();
				//document.getElementById('chart').style.display = "block";
				document.getElementById("table_grid").style = "display:none";
				document.getElementById("PieChart_div").style = "block";
				document.getElementById("chart_div").style = "block";

				var selectedFr = $("#selectFr").val();
				var routeId = $("#selectRoute").val();
				var selectedCat = $("#item_grp1").val();
				var from_date = $("#fromDate").val();
				var to_date = $("#toDate").val();

				var isGraph = 1;
				$('#loader').show();

				$
						.getJSON(
								'${getBillList}',

								{
									fr_id_list : JSON.stringify(selectedFr),
									fromDate : from_date,
									toDate : to_date,
									route_id : routeId,
									cat_id_list : JSON.stringify(selectedCat),
									is_graph : isGraph,

									ajax : 'true'

								},
								function(data) {

									$('#loader').hide();

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
												'Category'); // Implicit domain column.
										dataTable.addColumn('number', 'NetQty'); // Implicit data column.
										dataTable.addColumn('number',
												'NetValue');

										var piedataTable = new google.visualization.DataTable();
										piedataTable.addColumn('string',
												'Category'); // Implicit domain column.
										piedataTable.addColumn('number',
												'NetValue');

										$
												.each(
														data.categoryList,
														function(key, cat) {
															var netQty = 0;
															var netValue = 0;
															$
																	.each(
																			data.salesReportRoyalty,
																			function(
																					key,
																					report) {

																				if (cat.catId === report.catId) {
																					netQty = netQty
																							+ report.tBillQty
																							- (report.tGrnQty + report.tGvnQty);
																					//netQty=netQty.toFixed(2);
																					netValue = netValue
																							+ report.tBillTaxableAmt
																							- (report.tGrnTaxableAmt + report.tGvnTaxableAmt);
																					//netValue=netValue.toFixed(2);
																					var catName = report.cat_name;
																					//alert("CatName"+catName);

																					//alert("netValue"+netValue);
																					//alert("netQty"+netQty);

																					dataTable
																							.addRows([
																									[
																											catName,
																											netQty,
																											netValue ], ]);

																					piedataTable
																							.addRows([
																									[
																											catName,
																											netValue ], ]);
																				}
																			})

														})
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

											materialChart
													.draw(
															dataTable,
															google.charts.Bar
																	.convertOptions(materialOptions));

										}

										var chart = new google.visualization.ColumnChart(
												document
														.getElementById('chart_div'));

										var Piechart = new google.visualization.PieChart(
												document
														.getElementById('PieChart_div'));
										chart
												.draw(
														dataTable,
														{
															width : 1000,
															height : 600,
															title : 'Sales Summary Group By Month'
														});

										Piechart
												.draw(
														piedataTable,
														{
															width : 1000,
															height : 600,
															title : 'Sales Summary Group By Month',
															is3D : true
														});
										// drawMaterialChart();
									}
									;

								});

			}
		</script>


		<script type="text/javascript">
			function validate() {

				var selectedFr = $("#selectFr").val();
				var selectedRoute = $("#selectRoute").val();
				var selectedCat = $("#selectCat").val();

				var isValid = true;

				if ((selectedFr == "" || selectedFr == null)
						&& (selectedRoute == 0)) {

					alert("Please Select Route  Or Franchisee");
					isValid = false;

				} else if (selectedCat == "" || selectedCat == null) {

					alert("Please Select Category");
					isValid = false;

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
				var from_date = $("#fromDate").val();
				var to_date = $("#toDate").val();
				var selectedFr = $("#selectFr").val();
				var routeId = $("#selectRoute").val();
				var isGraph = 0;
				var selectedCat = $("#selectCat").val();
				var getBy= $("#getBy").val();
				var type= $("#type").val();

				window.open('pdfForReport?url=pdf/getSaleReportRoyConsoByCatPdf/'
						+ from_date + '/' + to_date+'/'+selectedFr+'/'+routeId+'/'+selectedCat+'/'+isGraph+'/'+getBy+'/'+type);

			}
			function exportToExcel()
			{
				 
				window.open("${pageContext.request.contextPath}/exportToExcelNew");
						document.getElementById("expExcel").disabled=true;
			}
			
			
		</script>
		<script type="text/javascript">
function tableToExcel(table, name, filename) {
        let uri = 'data:application/vnd.ms-excel;base64,', 
        template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><title></title><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>', 
        base64 = function(s) { return window.btoa(decodeURIComponent(encodeURIComponent(s))) },         format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; })}
        
        if (!table.nodeType) table = document.getElementById(table)
        var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}

        var link = document.createElement('a');
        link.download = filename;
        link.href = uri + base64(format(template, ctx));
        link.click();
}
</script>

		<script type="text/javascript">
			function setCatOptions(catId) {
				
				if (catId == -1) {
					$.getJSON('${getAllCatByAjax}', {
						ajax : 'true'
					}, function(data) {
						
						
						var len = data.length;
						$('#selectCat').find('option').remove().end()

						$("#selectCat").append(
								$("<option ></option>").attr("value", -1).text(
										"Select All"));

						for (var i = 0; i < len; i++) {

							$("#selectCat").append(
									$("<option selected></option>").attr(
											"value", data[i].catId).text(
											data[i].catName));
						}

						$("#selectCat").trigger("chosen:updated"); 
					});
				}
			}
		</script>


		<script>
			function setAllFrSelected(frId) {
				//alert("frId" + frId);
				//alert("hii")
				if (frId == -1) {

					$.getJSON('${getFrListofAllFr}', {

						ajax : 'true'
					}, function(data) {

						var len = data.length;

						//alert(len);

						$('#selectFr').find('option').remove().end()
						$("#selectFr").append(
								$("<option value='-1'>All</option>"));
						for (var i = 0; i < len; i++) {
							$("#selectFr").append(
									$("<option selected ></option>").attr(
											"value", data[i].frId).text(
											data[i].frName));
						}
						$("#selectFr").trigger("chosen:updated");
					});
				}
			}
		</script>

		<script type="text/javascript">
		
		function addCommas(x){

			x=String(x).toString();
			 var afterPoint = '';
			 if(x.indexOf('.') > 0)
			    afterPoint = x.substring(x.indexOf('.'),x.length);
			 x = Math.floor(x);
			 x=x.toString();
			 var lastThree = x.substring(x.length-3);
			 var otherNumbers = x.substring(0,x.length-3);
			 if(otherNumbers != '')
			     lastThree = ',' + lastThree;
			 return otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree + afterPoint;
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