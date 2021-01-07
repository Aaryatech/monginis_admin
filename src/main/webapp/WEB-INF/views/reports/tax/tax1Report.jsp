<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body>

	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

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

		<!-- END Page Title -->

		<!-- END Breadcrumb -->

		<!-- BEGIN Main Content -->
		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>Tax Report
				</h3>

			</div>

			<div class="box-content">
				<form action="${pageContext.request.contextPath}/showBillTaxSlabReport"
					class="form-horizontal" method="get" id="validation-form">
					<div class="row">


						<div class="form-group">
							<label class="col-sm-3 col-lg-2	 control-label">From Date</label>
							<div class="col-sm-6 col-lg-2 controls date_select">
								<input class="form-control date-picker" id="fromDate"
									name="fromDate" size="30" type="text" value="${fromDate}" />
							</div>

							<!-- </div>

					<div class="form-group  "> -->

							<label class="col-sm-3 col-lg-2	 control-label">To Date</label>
							<div class="col-sm-6 col-lg-2 controls date_select">
								<input class="form-control date-picker" id="toDate"
									name="toDate" size="30" type="text" value="${toDate}" />
							</div>
							<!-- </div>

				</div>


				<div class="row">
					<div class="col-md-12" style="text-align: center;"> -->
							<input type="submit" class="btn btn-info" value="Search" />
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

			</div>
		</div>


		<div class="box">


			<div class=" box-content">
				<div class="row">
					<div class="col-md-12 table-responsive">
						<table class="table table-bordered table-striped fill-head "
							style="width: 100%" id="table_grid">
							<thead style="background-color: #f3b5db;">
								<tr>
									<th width="2;">Sr.No.</th>
									<th>Invoice No</th>
									<th>Bill No.</th>
									<th>Bill Date</th>
									<th>Franchise</th>
									<th>GSTIN</th>
									<th>CGST %</th>
									<th>SGST %</th>
									<th>IGST %</th>
									<th>CGST Amt</th>
									<th>SGST Amt</th>
									<th>IGST Amt</th>
									<th>Taxable Amt</th>
									<th>Total Tax</th>
									<th>Grand Total</th>

								</tr>
							</thead>
							<tbody>
								<c:set var="totalCgstAmt" value="0" />
								<c:set var="totalSgstAmt" value="0" />
								<c:set var="totalIgstAmt" value="0" />
								<c:set var="totalTaxableAmt" value="0" />
								<c:set var="totalTax" value="0" />
								<c:set var="totalGrandTotal" value="0" />
								<c:forEach items="${taxReportList}" var="taxList"
									varStatus="count">
									<tr>
										<c:set var="totalCgstAmt"
											value="${totalCgstAmt+taxList.cgstAmt}" />
										<c:set var="totalSgstAmt"
											value="${totalSgstAmt+taxList.sgstAmt}" />
										<c:set var="totalIgstAmt"
											value="${totalIgstAmt+taxList.igstAmt}" />
										<c:set var="totalTaxableAmt"
											value="${totalTaxableAmt+taxList.taxableAmt}" />
										<c:set var="totalTax" value="${totalTax+taxList.totalTax}" />
										<c:set var="totalGrandTotal"
											value="${totalGrandTotal+taxList.grandTotal}" />


										<td><c:out value="${count.index+1}" /></td>
										<td><c:out value="${taxList.invoiceNo}" /></td>
										<td><c:out value="${taxList.billNo}" /></td>
										<td><c:out value="${taxList.billDate}" /></td>
										<td><c:out value="${taxList.frName}" /></td>

										<td><c:out value="${taxList.frGstNo}" /></td>
										<td style="text-align: right;"><c:out
												value="${taxList.cgstPer}" /></td>
										<td style="text-align: right;"><c:out
												value="${taxList.sgstPer}" /></td>
										<td style="text-align: right;"><c:out
												value="${taxList.igstPer}" /></td>

										<td style="text-align: right;"><c:out
												value="${taxList.cgstAmt}" /></td>
										<td style="text-align: right;"><c:out
												value="${taxList.sgstAmt}" /></td>
										<td style="text-align: right;"><c:out
												value="${taxList.igstAmt}" /></td>

										<td style="text-align: right;"><c:out
												value="${taxList.taxableAmt}" /></td>
										<td style="text-align: right;"><c:out
												value="${taxList.totalTax}" /></td>
										<td style="text-align: right;"><c:out
												value="${taxList.grandTotal}" /></td>

									</tr>
								</c:forEach>

								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>

									<td style="text-align: left;">Total</td>


									<td style="text-align: right;"><fmt:formatNumber
											type="number" maxFractionDigits="2" minFractionDigits="2"
											value="${totalCgstAmt}" /></td>
									<td style="text-align: right;"><fmt:formatNumber
											type="number" maxFractionDigits="2" minFractionDigits="2"
											value="${totalSgstAmt}" /></td>
											<td style="text-align: right;"><fmt:formatNumber
											type="number" maxFractionDigits="2" minFractionDigits="2"
											value="${totalIgstAmt}" /></td>

									<td style="text-align: right;"><fmt:formatNumber
											type="number" maxFractionDigits="2" minFractionDigits="2"
											value="${totalTaxableAmt}" /></td>


									<td style="text-align: right;"><fmt:formatNumber
											type="number" maxFractionDigits="2" minFractionDigits="2"
											value="${totalTax}" /></td>


									<td style="text-align: right;"><fmt:formatNumber
											type="number" maxFractionDigits="2" minFractionDigits="2"
											value="${totalGrandTotal}" /></td>

								</tr>


							</tbody>
						</table>
					</div>
					<div class="form-group" id="range">



						<div class="col-sm-3  controls">
							<input type="button" id="expExcel" class="btn btn-primary"
								value="EXPORT TO Excel" onclick="exportToExcel();">
						</div>

						<!-- <div class="col-sm-3  controls">
							<input type="button" id="expExcelTally" class="btn btn-primary"
								value="EXPORT TO Excel For Tally"
								onclick="exportToExcelTally();">
						</div> -->
					</div>
				</div>

			</div>

		</div>
	</div>
	<!-- END Main Content -->

	<footer>
		<p>2018 © Monginis.</p>
	</footer>

	<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
		class="fa fa-chevron-up"></i></a>


	<!-- 	<script type="text/javascript">
		function searchReport() {
		//	var isValid = validate();

				var selectedFr = $("#selectFr").val();
				var routeId=$("#selectRoute").val();
				
				var from_date = $("#fromDate").val();
				var to_date = $("#toDate").val();

				$('#loader').show();

				$
						.getJSON(
								'${getBillList}',

								{
									fr_id_list : JSON.stringify(selectedFr),
									fromDate : from_date,
									toDate : to_date,
									route_id:routeId,
									ajax : 'true'

								},
								function(data) {
									
								
									$('#table_grid td').remove();
									$('#loader').hide();

									if (data == "") {
										alert("No records found !!");
										  document.getElementById("expExcel").disabled=true;
									}

									$
											.each(
													data,
													function(key, report) {
														
														  document.getElementById("expExcel").disabled=false;
															document.getElementById('range').style.display = 'block';
														var index = key + 1;
														//var tr = "<tr>";
														
														
														var tr = $('<tr></tr>');

													  	tr.append($('<td></td>').html(key+1));

													  	tr.append($('<td></td>').html(report.invoiceNo));

													  	tr.append($('<td></td>').html(report.billDate));

													  	tr.append($('<td></td>').html(report.frName));
													  	
													  	
													  	tr.append($('<td></td>').html(report.frCity));

													  	tr.append($('<td></td>').html(report.frGstNo));

													  	tr.append($('<td></td>').html(report.taxableAmt));
													  	
													  	if(report.isSameState==1){
														  	tr.append($('<td></td>').html(report.cgstSum));
														  	tr.append($('<td></td>').html(report.sgstSum));
														  	tr.append($('<td></td>').html(0));
														}
														else{
															tr.append($('<td></td>').html(0));
														  	tr.append($('<td></td>').html(0));
														  	tr.append($('<td></td>').html(report.igstSum));
														}
														tr.append($('<td></td>').html(report.roundOff));
														var total;
														
														if(report.isSameState==1){
															 total=parseFloat(report.taxableAmt)+parseFloat(report.cgstSum+report.sgstSum);
														}
														else{
															
															 total=report.taxableAmt+report.igstSum;
														}

													  	tr.append($('<td></td>').html(total));

														$('#table_grid tbody')
																.append(
																		tr);
														

													})

								});

			
		}
	</script> -->

	<!-- 	<script type="text/javascript">
		function validate() {

			var selectedFr = $("#selectFr").val();
			var selectedMenu = $("#selectMenu").val();
			var selectedRoute = $("#selectRoute").val();


			var isValid = true;

			if (selectedFr == "" || selectedFr == null  ) {
 
				if(selectedRoute=="" || selectedRoute ==null ) {
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
	</script> -->

	<!-- <script type="text/javascript">
		function updateTotal(orderId, rate) {
			
			var newQty = $("#billQty" + orderId).val();

			var total = parseFloat(newQty) * parseFloat(rate);


			 $('#billTotal'+orderId).html(total);
		}
	</script> -->

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

		/* function genPdf()
		 {
		 var from_date = $("#fromDate").val();
		 var to_date = $("#toDate").val();
		 var selectedFr = $("#selectFr").val();
		 var routeId=$("#selectRoute").val();

		 window.open('${pageContext.request.contextPath}/pdfForReport?url=pdf/showSaleReportByDatePdf/'+from_date+'/'+to_date+'/'+selectedFr+'/'+routeId+'/');

		 //window.open("${pageContext.request.contextPath}/pdfForReport?url=showSaleReportByDatePdf/"+from_date+"/"+to_date);
		
		 } */
	</script>

	<script type="text/javascript">
		/* 
		 function disableFr(){

		 //alert("Inside Disable Fr ");
		 document.getElementById("selectFr").disabled = true;

		 }

		 function disableRoute(){

		 //alert("Inside Disable route ");
		 var x=document.getElementById("selectRoute")
		 //alert(x.options.length);
		 var i;
		 for(i=0;i<x;i++){
		 document.getElementById("selectRoute").options[i].disabled;
		 //document.getElementById("pets").options[2].disabled = true;
		 }
		 //document.getElementById("selectRoute").disabled = true;

		 } */

		function exportToExcel() {

			window.open("${pageContext.request.contextPath}/exportToExcelNew");
			document.getElementById("expExcel").disabled = true;
		}
		function exportToExcelTally() {

			window
					.open("${pageContext.request.contextPath}/exportToExcelTally");
			document.getElementById("expExcelTally").disabled = true;
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