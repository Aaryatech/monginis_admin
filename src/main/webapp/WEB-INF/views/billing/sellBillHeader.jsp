<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
	<c:url var="getSellBillHeader" value="/getSellBillHeader" />

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
						<i class="fa fa-file-o"></i>Sell Bill Header
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
								<i class="fa fa-bars"></i> Search Sell Bill List
							</h3>
							<div class="box-tool">
								<a href=""></a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							<!-- <div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> <a data-action="close" href="#"><i
									class="fa fa-times"></i></a>
							</div> -->
						</div>


						<div class="box-content">
     
      						<div class="form-group">

									<label for="textfield2" class="col-xs-3 col-lg-2 control-label">Select
										Franchisee </label>
									<div class="col-sm-9 col-lg-6 controls">

										<select class="form-control chosen" multiple="multiple"
											tabindex="6" name="fr_id" id="fr_id" required>

											<option value="" disabled="disabled">Select Franchisee</option>
											<c:forEach items="${allFrIdNameList}" var="allFrIdNameList"
												varStatus="count">
												<option value="${allFrIdNameList.frId}">${allFrIdNameList.frName}</option>
											</c:forEach>

										</select>
									</div>


								</div>
               <br><br>
								<div class="form-group">
									<label class="col-sm-2 col-lg-2 control-label">From
										Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="datepicker" size="16" placeholder="From Date"
											 type="text" name="from_Date" data-rule-required="true" />
									</div>
									<label class="col-sm-2 col-lg-1 control-label">To Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="datepicker2" size="16" placeholder="To Date"
											 type="text" name="to_Date"  required />

									</div>

								</div>
	            <br><br>
								<div align="center" class="form-group">
									<div
										class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
										<input type="button" class="btn btn-primary" value="Search" id="callSubmit"
											onclick="searchSellBill()">
									</div>
								</div>


								<div align="center" id="loader" style="display: none">

									<span>
										<h4>
											<font color="#343690">Loading</font>
										</h4>
									</span> <span class="l-1"></span> <span class="l-2"></span> <span
										class="l-3"></span> <span class="l-4"></span> <span
										class="l-5"></span> <span class="l-6"></span>
								</div>

								<!-- </form>
 -->
								<!-- <tion="getBillListProcess" class="form-horizontal"
								method="post" id="validation-form"> -->
								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i> Sell Bill List
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
										</div>
									</div>

									<div class="box-content">

										<div class="clearfix"></div>
										
										<div id="table-scroll" class="table-scroll">
							 
									<div id="faux-table" class="faux-table" aria="hidden">
									<table id="table2" class="main-table">
											<thead>
												<tr class="bgpink">
										<th width="138" style="width: 18px" align="left">Sr
															No</th>
														<th width="138" align="left">Bill No</th>
														<th width="159" align="left">Invoice No</th>
														<th width="159" align="left">Bill date</th>
														<th width="190" align="left">Franchisee Name</th>
														<th width="159" align="left">Grand Total</th>
														<th width="190" align="left">Payable Amount</th>
														<th width="180" align="left">Paid Amount</th>
														<th width="105" align="left">Pay Mode</th>
														<th width="159" align="left">Action</th>
												</tr>
												</thead>
												</table>
									
									</div>
									<div class="table-wrap">
									
										<table  class="table table-advance" id="table_grid" name="table_grid">
											<thead>
												<tr class="bgpink">
										<th width="138" style="width: 18px" align="left">Sr
															No</th>
														<th width="138" align="left">Bill No</th>
														<th width="159" align="left">Invoice No</th>
														<th width="159" align="left">Bill date</th>
														<th width="190" align="left">Franchisee Name</th>
														<th width="159" align="left">Grand Total</th>
														<th width="190" align="left">Payable Amount</th>
														<th width="180" align="left">Paid Amount</th>
														<th width="105" align="left">Pay Mode</th>
														<th width="159" align="left">Action</th>
												</tr>
												</thead>
										
										<!-- <div class="table-responsive" style="border: 0">
											<table width="100%" class="table table-advance" id="table_grid" name="table_grid">
												<thead>
													<tr>
														<th width="138" style="width: 18px" align="left">Sr
															No</th>
														<th width="138" align="left">Bill No</th>
														<th width="159" align="left">Invoice No</th>
														<th width="159" align="left">Bill date</th>
														<th width="190" align="left">Franchisee Name</th>
														<th width="159" align="left">Grand Total</th>
														<th width="190" align="left">Payable Amount</th>
														<th width="180" align="left">Paid Amount</th>
														<th width="105" align="left">Pay Mode</th>
														<th width="159" align="left">Action</th>
														
													</tr>
												</thead> -->
												<tbody>
											
												
												</tbody>
												
											</table>
										</div>
									</div>


								</div>
							<!-- </form> -->
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

<script type="text/javascript">
	function searchSellBill()
	{ 
		$('#table_grid td').remove();
		
		var isValid = validate();
		
		if (isValid) {
			
			var fromDate = document.getElementById("datepicker").value;
			var toDate = document.getElementById("datepicker2").value;
			var frIds=$("#fr_id").val();
			$('#loader').show();
			
			$.getJSON('${getSellBillHeader}',{
				
								fr_id_list : JSON.stringify(frIds),
								fromDate : fromDate,
								toDate : toDate,
								ajax : 'true',

							},
							function(data) {

								$('#loader').hide();
								if (data == "") {
									alert("No records found !!");

								}
								$.each(data,function(key, sellBillData) {

													var index = key + 1;

													var tr = "<tr>";

													var indexfortable="<td>&nbsp;&nbsp;&nbsp;"
														+  index
														+ "</td>";
													var sellBillNo = "<td>&nbsp;&nbsp;&nbsp;"
															+ sellBillData.sellBillNo
															+ "</td>";
															var invoiceNo = "<td>&nbsp;&nbsp;&nbsp;"
																+ sellBillData.invoiceNo
																+ "</td>";
																var billDate = "<td>&nbsp;&nbsp;&nbsp;"
																	+ sellBillData.billDate
																	+ "</td>";
																	var frName = "<td>&nbsp;&nbsp;&nbsp;"
																		+ sellBillData.frName
																		+ "</td>";

																	var grandTotal = "<td>&nbsp;&nbsp;&nbsp;"
																		+ sellBillData.grandTotal
																		+ "</td>";

																		var PayableAmt = "<td>&nbsp;&nbsp;&nbsp;"
						 													+ sellBillData.payableAmt
						 													+ "</td>";
																			
						  													var paidAmt = "<td>&nbsp;&nbsp;&nbsp;"
																				+ sellBillData.paidAmt
																				+ "</td>";

																			var paymentMode = "<td>&nbsp;&nbsp;&nbsp;"
																				+ sellBillData.paymentMode
																				+ "</td>";
																				
																				var viewBill = '<td>&nbsp;&nbsp;&nbsp;'
																				+'<a href="${pageContext.request.contextPath}/viewBillDetails?sellBillNo='+ sellBillData.sellBillNo+'&billDate='+sellBillData.billDate+'&frName='+sellBillData.frName+'" class="action_btn" name='+'><abbr title="Details"><i class="fa fa-list"></i></abbr></a>'
																				+ "</td>";
													var trclosed = "</tr>";

													$('#table_grid tbody')
															.append(tr);
													
													$('#table_grid tbody')
													.append(indexfortable);
													$('#table_grid tbody')
															.append(sellBillNo);
													$('#table_grid tbody')
													.append(invoiceNo);
													$('#table_grid tbody')
													.append(billDate);
													$('#table_grid tbody')
													.append(frName);
													$('#table_grid tbody')
													.append(grandTotal);
													$('#table_grid tbody')
													.append(PayableAmt);
													$('#table_grid tbody')
													.append(paidAmt);
													
													$('#table_grid tbody')
													.append(paymentMode);
													
													$('#table_grid tbody')
													.append(viewBill);
													
													$('#table_grid tbody')
													.append(trclosed);
												
												})
										
							});

		}
	}
	</script>
	<script type="text/javascript">
	function validate() {
	
		var frId =$("#fr_id").val();
		var fromDate =$("#datepicker").val();
		var toDate =$("#datepicker2").val();
		

		var isValid = true;

		if(frId==""||frId==null){
			isValid = false;
			alert("Please select Franchise");
		}else
	 if (fromDate == "" || fromDate == null) {

			isValid = false;
			alert("Please select From Date");
		}
	 else if (toDate == "" || toDate == null) {

			isValid = false;
			alert("Please select To Date");
		}
		return isValid;

	}
</script>

</body>
</html>