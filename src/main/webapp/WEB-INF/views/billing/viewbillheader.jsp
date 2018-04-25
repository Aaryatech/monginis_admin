<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body>

	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
	<c:url var="callGetBillListProcess" value="/getBillListProcess" />

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
						<i class="fa fa-file-o"></i>View Your Bills
					</h1>

				</div>
			</div>
			<!-- END Page Title -->

			<c:set var="isEdit" value="0">
			</c:set>
			<c:set var="isView" value="0">
			</c:set>
			<c:set var="isDelete" value="0">
			</c:set>
			
			<input type="hidden" id="modList" value="${sessionScope.newModuleList}">

			<c:forEach items="${sessionScope.newModuleList}" var="modules">
				<c:forEach items="${modules.subModuleJsonList}" var="subModule">

					<c:choose>
						<c:when test="${subModule.subModuleMapping eq 'showBillList'}">

							<c:choose>
								<c:when test="${subModule.editReject=='visible'}">
									<c:set var="isEdit" value="1">
									</c:set>
								</c:when>
								<c:otherwise>
									<c:set var="isEdit" value="0">
									</c:set>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${subModule.view=='visible'}">
									<c:set var="isView" value="1">
									</c:set>
								</c:when>
								<c:otherwise>
									<c:set var="isView" value="0">
									</c:set>
								</c:otherwise>
							</c:choose>


							<c:choose>
								<c:when test="${subModule.deleteRejectApprove=='visible'}">
									<c:set var="isDelete" value="1">
									</c:set>
								</c:when>
								<c:otherwise>
									<c:set var="isDelete" value="0">
									</c:set>
								</c:otherwise>
							</c:choose>
						</c:when>
					</c:choose>
				</c:forEach>
			</c:forEach>
			
			<input type="hidden" id="isDelete" value="${isDelete}">
						<input type="hidden" id="isEdit" value="${isEdit}">
			

			<!-- BEGIN Main Content -->
			<div class="row">
				<div class="col-md-12">
					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-bars"></i> Search Bill List
							</h3>
							<div class="box-tool">
								<!-- <a href="">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> -->
							</div>
							<!-- <div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> <a data-action="close" href="#"><i
									class="fa fa-times"></i></a>
							</div> -->
						</div>


						<div class="box-content">
							<form class="form-horizontal" method="get" id="validation-form">

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">From
										Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="dp1" size="16"
											value="${todaysDate}" type="text" name="from_date" required />
									</div>




									<label class="col-sm-3 col-lg-2 control-label">To Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="dp2" size="16"
											value="${todaysDate}" type="text" name="to_date" required />

									</div>

								</div>



								<div class="form-group">

									<label for="textfield2" class="col-xs-3 col-lg-2 control-label">Select
										Franchise </label>
									<div class="col-sm-9 col-lg-10 controls">

										<select class="form-control chosen" multiple="multiple"
											tabindex="6" name="fr_id" id="fr_id">

											<option value="-1">All</option>
											<c:forEach items="${allFrIdNameList}" var="allFrIdNameList"
												varStatus="count">
												<option value="${allFrIdNameList.frId}">${allFrIdNameList.frName}</option>

											</c:forEach>

										</select>
									</div>


									<h4 align="center">OR</h4>


									<label for="textfield2" class="col-xs-3 col-lg-2 control-label">Select
										Route </label>
									<div class="col-sm-9 col-lg-10 controls">


										<select class="form-control chosen" tabindex="6"
											name="route_id" id="route_id">

											<option value="0">Select Route</option>
											<c:forEach items="${routeList}" var="route" varStatus="count">
												<option value="${route.routeId}"> ${route.routeName}</option>

											</c:forEach>

										</select>
									</div>
								</div>









								<div align="center" class="form-group">
									<div
										class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
										<input type="button" class="btn btn-primary" value="Search"
											id="callSubmit" onclick="callSearch()">


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
											<i class="fa fa-table"></i> Bill List Header
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
															<th class="col-sm-1" align="left">Sr No</th>
															<th class="col-md-2" align="left">Invoice No</th>
															<th class="col-md-2" align="left">Date</th>
															<th class="col-md-2" align="left">Franchise Name</th>
															<th class="col-md-1" align="left">Taxable Amt</th>
															<th class="col-md-1" align="left">Total tax</th>
															<th class="col-md-1" align="left">Total</th>
															<th class="col-md-1" align="left">Status</th>
															<th class="col-md-2" align="left">Action</th>
														</tr>
													</thead>
												</table>

											</div>
											<div class="table-wrap">

												<table id="table1" class="table table-advance">
													<thead>
														<tr class="bgpink">
															<th class="col-sm-1" align="left">Sr No</th>
															<th class="col-md-2" align="left">Invoice No</th>
															<th class="col-md-2" align="left">Date</th>
															<th class="col-md-2" align="left">Franchise Name</th>
															<th class="col-md-1" align="left">Taxable Amt</th>
															<th class="col-md-1" align="left">Total tax</th>
															<th class="col-md-1" align="left">Total</th>
															<th class="col-md-1" align="left">Status</th>
															<th class="col-md-1" align="left">Action</th>
														</tr>
													</thead>
													<tbody>
														<!-- <div class="table-responsive" style="border: 0">
											<table width="100%" class="table table-advance" id="table1">
												<thead>
													<tr>
														<th class="col-sm-1" align="left">Sr No</th>
														<th class="col-md-1" align="left">Invoice No</th>
														<th class="col-md-1" align="left">Date</th>
														<th class="col-md-2" align="left">Franchise Name</th>
														<th class="col-md-1" align="left">Taxable Amt</th>
														<th class="col-md-1" align="left">Total tax</th>
														<th class="col-md-1" align="left">Total</th>
														<th class="col-md-1" align="left">Status</th>
														<th class="col-md-1" align="center">Action</th>
													</tr>
												</thead>
												<tbody> -->
														<c:forEach items="${billHeadersList}"
															var="billHeadersList" varStatus="count">

															<tr>
																<td class="col-sm-1"><c:out
																		value="${count.index+1}" /></td>
																<td class="col-md-1" align="left"><c:out
																		value="${billHeadersList.invoiceNo}" /></td>

																<td class="col-md-1" align="left"><c:out
																		value="${billHeadersList.billDate}" /></td>

																<td class="col-md-2" align="left"><c:out
																		value="${billHeadersList.frName}" /></td>
																<td class="col-md-1" align="left"><c:out
																		value="${billHeadersList.taxableAmt}" /></td>
																<td class="col-md-1" align="left"><c:out
																		value="${billHeadersList.totalTax}" /></td>
																<td align="left"><c:out
																		value="${billHeadersList.grandTotal}" /></td>
																<td align="left"><c:out
																		value="${billHeadersList.status}" /></td>


																<c:choose>
																	<c:when test="${isEdit==1 and isDelete==1}">
																		<td align="left"><a
																			href="${pageContext.request.contextPath}/updateBillDetails/${billHeadersList.billNo}/${billHeadersList.frName}"><abbr
																				title='Update Bill'></abbr> <i
																				class='fa fa-edit  fa-lg'></i></a>&nbsp; <a
																			href="${pageContext.request.contextPath}/viewBillDetails/${billHeadersList.billNo}/${billHeadersList.frName}"><abbr
																				title='View Bill'></abbr> <i
																				class='fa fa-info  fa-lg'></i></a>&nbsp; <a
																			href="${pageContext.request.contextPath}/deleteBill/${billHeadersList.billNo}/${billHeadersList.frName}"><abbr
																				title='Delete Bill'></abbr> <i
																				class='fa fa-trash-o  fa-lg'></i></a></td>

																	</c:when>

																	<c:when test="${isEdit==1 and isDelete==0}">
																		<td align="left"><a
																			href="${pageContext.request.contextPath}/updateBillDetails/${billHeadersList.billNo}/${billHeadersList.frName}"><abbr
																				title='Update Bill'></abbr> <i
																				class='fa fa-edit  fa-lg'></i></a>&nbsp; <a
																			href="${pageContext.request.contextPath}/viewBillDetails/${billHeadersList.billNo}/${billHeadersList.frName}"><abbr
																				title='View Bill'></abbr> <i
																				class='fa fa-info  fa-lg'></i></a>&nbsp; <a
																			href="${pageContext.request.contextPath}/deleteBill/${billHeadersList.billNo}/${billHeadersList.frName}" class="disableClick"><abbr
																				title='Delete Bill'></abbr> <i
																				class='fa fa-trash-o  fa-lg'></i></a></td>

																	</c:when>

																	<c:when test="${isEdit==0 and isDelete==1}">
																		<td align="left"><a
																			href="${pageContext.request.contextPath}/updateBillDetails/${billHeadersList.billNo}/${billHeadersList.frName}" class="disableClick"><abbr
																				title='Update Bill'></abbr> <i
																				class='fa fa-edit  fa-lg'></i></a>&nbsp; <a
																			href="${pageContext.request.contextPath}/viewBillDetails/${billHeadersList.billNo}/${billHeadersList.frName}"><abbr
																				title='View Bill'></abbr> <i
																				class='fa fa-info  fa-lg'></i></a>&nbsp; <a
																			href="${pageContext.request.contextPath}/deleteBill/${billHeadersList.billNo}/${billHeadersList.frName}"><abbr
																				title='Delete Bill'></abbr> <i
																				class='fa fa-trash-o  fa-lg'></i></a></td>

																	</c:when>

																	<c:otherwise>

																		<td align="left"><a
																			href="${pageContext.request.contextPath}/updateBillDetails/${billHeadersList.billNo}/${billHeadersList.frName}" class="disableClick"><abbr
																				title='Update Bill'></abbr> <i
																				class='fa fa-edit  fa-lg'></i></a>&nbsp; <a
																			href="${pageContext.request.contextPath}/viewBillDetails/${billHeadersList.billNo}/${billHeadersList.frName}"><abbr
																				title='View Bill'></abbr> <i
																				class='fa fa-info  fa-lg'></i></a>&nbsp; <a
																			href="${pageContext.request.contextPath}/deleteBill/${billHeadersList.billNo}/${billHeadersList.frName}" class="disableClick"><abbr
																				title='Delete Bill'></abbr> <i
																				class='fa fa-trash-o  fa-lg'></i></a></td>

																	</c:otherwise>
																</c:choose>



<%-- 
																<td align="left"><a
																	href="${pageContext.request.contextPath}/updateBillDetails/${billHeadersList.billNo}/${billHeadersList.frName}"><abbr
																		title='Update Bill'></abbr> <i
																		class='fa fa-edit  fa-lg'></i></a>&nbsp; <a
																	href="${pageContext.request.contextPath}/viewBillDetails/${billHeadersList.billNo}/${billHeadersList.frName}"><abbr
																		title='View Bill'></abbr> <i class='fa fa-info  fa-lg'></i></a>&nbsp;

																	<a
																	href="${pageContext.request.contextPath}/deleteBill/${billHeadersList.billNo}/${billHeadersList.frName}"><abbr
																		title='Delete Bill'></abbr> <i
																		class='fa fa-trash-o  fa-lg'></i></a></td> --%>

																<!-- <td rowspan="1" align="left"> <input
																type="button" value="View"> <input type="button"
																value="Edit"> <input type="button"
																value="Cancel"></td>
 -->


																<!-- <td align="left"><label><input type="submit"
																	name="submit_button" id="submit_button"></label></td>  -->


															</tr>
														</c:forEach>

													</tbody>
												</table>
											</div>
										</div>


									</div>
							</form>
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
		$(document).ready(function() {
			$('#callSubmit2').submit(function() {
				alert("searching");
				$.ajax({
					type : "get",
					url : "/getBillListProcess", //this is my servlet
					/*   data: "input=" +$('#ip').val()+"&output="+$('#op').val(), */
					success : function(data) {
						alert("success");

					}

				});
			});

		});
	</script>

	<script type="text/javascript">
		function callSearch() {
			
			var isDelete=document.getElementById("isDelete").value;
			var isEdit=document.getElementById("isEdit").value;
			
			//alert("isDelete =" +isDelete);
			//alert("isEdit =" +isEdit);

			/* var abc =${sessionScope.newModuleList[0].subModuleJsonList};
			
			$.each(abc,function(index,value){
				alert(index +": " +value);
			}); */
			 //alert("abc " +abc);
			//var ar[]= document.getElementById("modList").value;
			//alert(ar);

			//var frId = document.getElementById("fr_id").value;
			var array = [];
			var frIds = $("#fr_id").val();
			var fromDate = document.getElementById("dp1").value;
			var toDate = document.getElementById("dp2").value;

			var routeId = document.getElementById("route_id").value;

			$('#loader').show();

			$
					.getJSON(
							'${callGetBillListProcess}',
							{
								fr_id_list : JSON.stringify(frIds),
								from_date : fromDate,
								to_date : toDate,
								route_id : routeId,
								ajax : 'true'
							},
							function(data) {
								$('#table1 td').remove();
								$('#loader').hide();
								if (data == "") {
									alert("No Bill Found");
								}

								$
										.each(
												data,
												function(key, bill) {
													

													var tr = $('<tr></tr>');

													tr
															.append($(
																	'<td class="col-sm-1"></td>')
																	.html(
																			key + 1));

													tr
															.append($(
																	'<td class="col-md-1"></td>')
																	.html(
																			bill.invoiceNo));

													tr
															.append($(
																	'<td class="col-md-1"></td>')
																	.html(
																			bill.billDate));

													tr
															.append($(
																	'<td class="col-md-2"></td>')
																	.html(
																			bill.frName));

													tr
															.append($(
																	'<td class="col-md-1"></td>')
																	.html(
																			bill.taxableAmt));

													tr
															.append($(
																	'<td class="col-md-1"></td>')
																	.html(
																			bill.totalTax));

													tr
															.append($(
																	'<td class="col-md-1"></td>')
																	.html(
																			bill.grandTotal));

													if (bill.status == 1) {
														tr
																.append($(
																		'<td class="col-md-1"></td>')
																		.html(
																				"Pending"));

													} else if (bill.status == 2) {
														tr
																.append($(
																		'<td class="col-md-1"></td>')
																		.html(
																				"Received"));
													} else if (bill.status == 3) {
														tr
																.append($(
																		'<td class="col-md-1"></td>')
																		.html(
																				"GVN Applied"));
													} else if (bill.status == 4) {
														tr
																.append($(
																		'<td class="col-md-1"></td>')
																		.html(
																				"GVN Approved"));
													} else if (bill.status == 5) {
														tr
																.append($(
																		'<td class="col-md-1"></td>')
																		.html(
																				"GRN Applied"));
													} else if (bill.status == 6) {
														tr
																.append($(
																		'<td class="col-md-1"></td>')
																		.html(
																				"GRN Approved"));
													} else if (bill.status == 7) {
														tr
																.append($(
																		'<td class="col-md-1"></td>')
																		.html(
																				"Closed"));
													}

													/* 												  	tr.append($('<td></td>').html(bill.remark));
													 */

													 
												 if(isDelete==1 && isEdit==1){
														// alert("in first");
														 tr
															.append($(
																	'<td class="col-md-1"></td>')
																	.html(
																			"<a href='${pageContext.request.contextPath}/updateBillDetails/"
																					+ bill.billNo
																					+ "/"
																					+ bill.frName
																					+ "'<abbr title='Update Bill'></abbr><i class='fa fa-edit  fa-lg'></i></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/viewBillDetails/"
																					+ bill.billNo
																					+ "/"
																					+ bill.frName
																					+ "'<abbr title='View Bill'></abbr><i class='fa fa-info  fa-lg'></i></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/deleteBill/"
																					+ bill.billNo
																					+ "/"
																					+ bill.frName
																					+ "'<abbr title='Delete Bill'></abbr><i class='fa fa-trash-o  fa-lg'></i></a>"));

														 
													 }else if(isDelete==1 && isEdit==0){
														 //alert("in second");
														 tr
															.append($(
																	'<td class="col-md-1"></td>')
																	.html(
																			"<a href=''javascript: void(0)'/"
																					+ bill.billNo
																					+ "/"
																					+ bill.frName
																					+ "class='disableClick''<abbr title='Update Bill'></abbr><i class='fa fa-edit  fa-lg'></i></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/viewBillDetails/"
																					+ bill.billNo
																					+ "/"
																					+ bill.frName
																					+ "'<abbr title='View Bill'></abbr><i class='fa fa-info  fa-lg'></i></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/deleteBill/"
																					+ bill.billNo
																					+ "/"
																					+ bill.frName
																					+ "'<abbr title='Delete Bill'></abbr><i class='fa fa-trash-o  fa-lg'></i></a>"));

														 
													 }else if(isDelete==0 && isEdit==1){
														// alert("in third");
														 tr
															.append($(
																	'<td class="col-md-1"></td>')
																	.html(
																			"<a href='${pageContext.request.contextPath}/updateBillDetails/"
																					+ bill.billNo
																					+ "/"
																					+ bill.frName
																					+ "'<abbr title='Update Bill'></abbr><i class='fa fa-edit  fa-lg'></i></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/viewBillDetails/"
																					+ bill.billNo
																					+ "/"
																					+ bill.frName
																					+ "'<abbr title='View Bill'></abbr><i class='fa fa-info  fa-lg'></i></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=''javascript: void(0)'"
																					+ bill.billNo
																					+ "/"
																					+ bill.frName
																					+ "class='disableClick''<abbr title='Delete Bill'></abbr><i class='fa fa-trash-o  fa-lg'></i></a>"));

														 
													 }else{
														// alert("in fourth");
														 
														 tr
															.append($(
																	'<td class="col-md-1"></td>')
																	.html(
																			"<a href='javascript: void(0)/"
																					+ bill.billNo
																					+ "/"
																					+ bill.frName
																					+ "class='disableClick''<abbr title='Update Bill'></abbr><i class='fa fa-edit  fa-lg'></i></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript: void(0)'"
																					+ bill.billNo
																					+ "/"
																					+ bill.frName
																					+ "'<abbr title='View Bill'></abbr><i class='fa fa-info  fa-lg'></i></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript: void(0)'"
																					+ bill.billNo
																					+ "/"
																					+ bill.frName
																					+ "class='disableClick''<abbr title='Delete Bill'></abbr><i class='fa fa-trash-o  fa-lg'></i></a>"));
														 
														 
														 /* tr
															.append($(
																	'<td class="col-md-1"></td>')
																	.html(
																			"<a href='javascript: void(0)' class='disableClick''<abbr title='Update Bill'></abbr><i class='fa fa-edit  fa-lg'></i></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript: void(0)'></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript. void(0)'
																					'<abbr title='Delete Bill'></abbr><i class='fa fa-trash-o  fa-lg'></i></a>")); */
													 }  
													 
													 
													/*  tr
															.append($(
																	'<td class="col-md-1"></td>')
																	.html(
																			"<a href='${pageContext.request.contextPath}/updateBillDetails/"
																					+ bill.billNo
																					+ "/"
																					+ bill.frName
																					+ "class='disableClick''<abbr title='Update Bill'></abbr><i class='fa fa-edit  fa-lg'></i></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/viewBillDetails/"
																					+ bill.billNo
																					+ "/"
																					+ bill.frName
																					+ "'<abbr title='View Bill'></abbr><i class='fa fa-info  fa-lg'></i></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/deleteBill/"
																					+ bill.billNo
																					+ "/"
																					+ bill.frName
																					+ "class='disableClick''<abbr title='Delete Bill'></abbr><i class='fa fa-trash-o  fa-lg'></i></a>"));
 */
													//tr.append($('<td></td>').html("<a href='${pageContext.request.contextPath}/updateBillDetails/"+bill.billNo+"/"+bill.frName+"'><input type='button' name='update' value='Update'/></a><a href='${pageContext.request.contextPath}/viewBillDetails/"+bill.billNo+"/"+bill.frName+"'><input type='button' name='view' value='View'/></a><a href='${pageContext.request.contextPath}/deleteBill/"+bill.billNo+"/"+bill.frName+"'><input type='button' name='deleteBill' value='Delete'/></a>"));

													//tr.append($('<td></td>').html("<input type=button id=edit onClick=editQty("+orders.orderId+"); Value=Edit> <input type=button id=delete"+orders.orderId+" onClick=deleteOrder("+orders.orderId+"); Value=Delete>"));

													$('#table1 tbody').append(
															tr);

													/* var index = key + 1;
													
													var tr = "<tr>";

													var index = "<td>&nbsp;&nbsp;&nbsp;"
															+ index + "</td>";
															
													var billNo = "<td>&nbsp;&nbsp;&nbsp;"
															+ bill.billNo
															+ "</td>";
															
													var billDate = "<td>&nbsp;&nbsp;&nbsp;"
															+ bill.billDate
															+ "</td>";
															
													var frName = "<td>&nbsp;&nbsp;&nbsp;&nbsp;"
															+ bill.frName
															+ "</td>";
															
													var taxableAmt = "<td>&nbsp;&nbsp;&nbsp;&nbsp;"
															+ bill.taxableAmt
															+ "</td>";
															
													
													var totalTax = "<td>&nbsp;&nbsp;&nbsp;&nbsp;"
															+ bill.totalTax
															+ "</td>";
															
													var grandTotal = "<td>&nbsp;&nbsp;&nbsp;&nbsp;"
															+ bill.grandTotal
															+ "</td>"; 
															
															
													if (bill.status == 1) {
														var status = "<td>&nbsp;&nbsp;&nbsp;&nbsp;Pending</td>";

													} else if (bill.status == 2) {
														var status = "<td>&nbsp;&nbsp;&nbsp;&nbsp;Received</td>";

													} else if (bill.status == 3) {
														var status = "<td>&nbsp;&nbsp;&nbsp;&nbsp;GVN Applied/td>";

													} else if (bill.status == 4) {
														var status = "<td>&nbsp;&nbsp;&nbsp;&nbsp;GVN Approved</td>";

													} else if (bill.status == 5) {
														var status = "<td>&nbsp;&nbsp;&nbsp;&nbsp;GRN Applied</td>";

													} else if (bill.status == 6) {
														var status = "<td>&nbsp;&nbsp;&nbsp;&nbsp;GRN Approved</td>";

													} else if (bill.status == 7) {
														var status = "<td>&nbsp;&nbsp;&nbsp;&nbsp;Closed</td>";

													}
													
													var remark = "<td>&nbsp;&nbsp;&nbsp;&nbsp;"
															+ bill.remark
															+ "</td>";
															
													var action="<td>"+ "<a href='${pageContext.request.contextPath}/updateBillDetails/"+bill.billNo+"/"+bill.frName+"'><input type='button' name='update' value='Update'/></a><a href='${pageContext.request.contextPath}/viewBillDetails/"+bill.billNo+"/"+bill.frName+"'><input type='button' name='view' value='View'/></a><a href='${pageContext.request.contextPath}/deleteBill/"+bill.billNo+"/"+bill.frName+"'><input type='button' name='deleteBill' value='Delete'/></a>"+"</td>";

													var trclosed = "</tr>";

													$('#table1 tbody').append(tr);
													$('#table1 tbody').append(index);
													$('#table1 tbody').append(billNo);
													$('#table1 tbody').append(billDate);
													$('#table1 tbody').append(frName);
													$('#table1 tbody').append(taxableAmt);
													
													$('#table1 tbody').append(totalTax);
													$('#table1 tbody').append(grandTotal);
													$('#table1 tbody').append(status);
													$('#table1 tbody').append(remark);
													$('#table1 tbody').append(action);

													$('#table1 tbody').append(trclosed);
													 */
												})

							});

		}
	</script>



</body>
</html>