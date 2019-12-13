<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
<head>
  <link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/tableSearch.css"> 
	
<style type="text/css">
   .table-responsive th {
    padding: 22px 0px;
    vertical-align: top;
    background: #DCDCDC !important;
    color: #696969 !important;
}
</style>
</head>  
<body>


	<c:url var="callspCakeOrderProcess" value="/spCakeAlbumOrderProcess" />

	<c:url var="deleteSpOrder" value="/deleteSpOrder" />

<c:url var="showSpcakeRawMaterialInfo" value="/showSpcakeRawMaterialInfo" />



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
						<i class="fa fa-file-o"></i> Album Special Cake Orders
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

			<input type="hidden" id="modList"
				value="${sessionScope.newModuleList}">

			<c:forEach items="${sessionScope.newModuleList}" var="modules">
				<c:forEach items="${modules.subModuleJsonList}" var="subModule">

					<c:choose>
						<c:when test="${subModule.subModuleMapping eq 'spCakeOrders'}">

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

			<input type="hidden" id="isDelete" value="${isDelete}"> <input
				type="hidden" id="isEdit" value="${isEdit}">

			<!-- BEGIN Main Content -->
			<div class="row">
				<div class="col-md-12">
					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-bars"></i>Search Order
							</h3>
							<div class="box-tool">
								<a href="">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							<!-- <div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> <a data-action="close" href="#"><i
									class="fa fa-times"></i></a>
							</div> -->
						</div>


						<div class="box-content">
							<form class="form-horizontal" method="post" id="validationform">
								<!-- action="spCakeOrderProcess" -->




								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Franchisee
									</label>
									<div class="col-sm-3 col-lg-4 controls">

										<select data-placeholder="Select Franchisee"
											class="form-control chosen" multiple="multiple" tabindex="6"
											name="fr_id" id="fr_id" onchange="disableRoute()">

											<option value="0">All</option>
											<c:forEach items="${franchiseeList}" var="franchiseeList">
												<option value="${franchiseeList.frId}">${franchiseeList.frName}</option>


											</c:forEach>

										</select>
									</div>
									<label class="col-sm-1 col-lg-1 control-label"> <b>OR</b></label><label
										class="col-sm-1 col-lg-1 control-label">Route</label>
									<div class="col-sm-1 col-lg-4 controls">
										<select data-placeholder="Select Route"
											class="form-control chosen" name="selectRoute"
											id="selectRoute" onchange="disableFr()">
											<option value="0">Select Route</option>
											<c:forEach items="${routeList}" var="route" varStatus="count">
												<option value="${route.routeId}"><c:out
														value="${route.routeName}" />
												</option>

											</c:forEach>
										</select>

									</div>
								</div>

								<%-- <div class="form-group">
										<label for="textfield2"
											class="col-xs-3 col-lg-2 control-label">Items</label>
										<div class="col-sm-9 col-lg-10 controls">
											<select class="form-control input-sm" name="item_name" id="item_name">
												<option value="1" selected>Savouries</option>
												
												<c:forEach items="${menuList}" var="menuList">
											<option value="${menuList.menuId}">${menuList.menuTitle}</option>
											
											</c:forEach>
											
											</select>
										</div>
									</div> --%>




								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Production
										Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" value="${todayDate }"
											id="dp2" size="16" type="text" name="prod_date"
											data-rule-required="true" />
									</div>
									
									
									<label class="col-sm-1 col-lg-1 control-label">By</label>
									<div class="col-sm-1 col-lg-3 controls">
										<select data-placeholder="By"
											class="form-control chosen" name="select_way"
											id="select_way" onchange="callSearch()">
											<option selected value="0">Sr. No.</option>
											<option value="1">Alternate</option>
										</select>
								</div> 
								<!-- </div>
								
								



								<div align="center" class="form-group"> -->
									<div
										class="col-md-1">
										<input class="btn btn-primary" value="Search" id="callSubmit"
											onclick="callSearch()">


									</div>
									<div class="col-md-9"></div>
									<label for="search" class="col-md-3" id="search"> <i
										class="fa fa-search" style="font-size: 20px"></i> <input
										type="text" style="border-radius: 25px;" id="myInput"
										onkeyup="myFunction()" style="border-radius: 25px;"
										placeholder="Search by Sr.No or Franhise">
									</label>
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

								<!-- <div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
										<button type="submit" class="btn btn-primary">
											<i class="fa fa-check"></i> Search
										</button>
										<button type="button" class="btn">Cancel</button>
									</div>
								</div>
 -->
								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i> Order List
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
										</div>
									</div>

									<c:set var="dis" value="none" />

									<div class="box-content">

									
										<div class="table-responsive" style="border: 0">
											<table width="100%" class="table table-advance" id="table1" >
												<thead>
													<tr>
												<!-- 	<th width="17" style="width: 18px"><input type="checkbox" id="selCheck" name="selCheck" style="display: none;"/>  </th> -->
														<th width="130" align="left">Sr No</th>
														<th width="87" align="left">Action</th>

														<th width="208" align="left">Franchisee</th>
														<th width="203" align="left">Delivery Date</th>
														<th width="159" align="left"><span
															style="width: 130px;">Category</span></th>
														<th width="159" align="left"><span
															style="width: 130px;">Sp Code</span></th>
														<th width="105" align="left">Weight</th>
														<th width="168" align="left">Flavour</th>
														<th width="140" align="left">Event</th>
														<th width="105" align="left">Rate</th>
														<th width="75" align="left">Add Rate</th>
														<th width="91" align="left">Total</th>
														<th width="87" align="left">View</th>
														<th width="87" align="left">PDF</th>
													

													</tr>
												</thead>
												<tbody>

													<c:forEach items="${spCakeOrderList}" var="spCakeOrder"
														varStatus="count">
														<c:set var="dis" value="block" />
														<tr>

															<%
																System.out.println(" DATA ---------------- ");
															%>

															<%-- <c:set var="codeParts" value="${fn:split(${spCakeOrder.itemId}, '#')}" /> --%>

															<td><c:out value="${count.index+1}" /></td>
															<td align="left"><c:out
																	value="${spCakeOrder.frName}"></c:out></td>
															<td align="left"><c:out value="${spCakeOrder.name}"></c:out></td>

															<td align="left"><c:out
																	value="${spCakeOrder.spName}"></c:out></td>
															<td align="left"><c:out
																	value="${spCakeOrder.spfName}"></c:out></td>

															<td align="left"><c:out
																	value="${spCakeOrder.spEvents}"></c:out></td>

															<td align="left"><c:out
																	value="${spCakeOrder.spDeliveryDate}"></c:out></td>
															<td align="left"><c:out
																	value="${spCakeOrder.spSelectedWeight}"></c:out></td>
															<td align="left"><c:out
																	value="${spCakeOrder.spPrice}"></c:out></td>

															<td align="left"><c:out
																	value="${spCakeOrder.spTotalAddRate}"></c:out></td>

															<c:set var="spAddRate"
																value="${spCakeOrder.spTotalAddRate}" />
															<c:set var="spPrice" value="${spCakeOrder.spPrice}" />


															<td align="left"><c:out
																	value="${spAddRate + spPrice}"></c:out></td>

															<td align="left"><c:out value="PDF"></c:out></td>

															<td align="left"><c:out value="ADMIN PDF"></c:out></td>


														</tr>

													</c:forEach>
												</tbody>

											</table>


										</div>
									</div>
								</div>
								<div class="form-group"
									style="display: <c:out value="${dis}" />;" id="range">
									<div id="range_fields">
										<div class="col-sm-2  controls">
											<input type="text" class="form-control" id="from" name="from"
												placeholder="from no">
										</div>
										<div class="col-sm-2  controls">
											<input type="text" class="form-control" id="to" name="to"
												placeholder="to no">
										</div>
									</div>
									
									<div class="col-sm-3  controls">
										<input type="button" id="from" class="btn btn-primary"
											value="EXPORT TO PDF IN RANGE" onclick="inRangePdf();">
									</div>
									<div class="col-sm-3  controls">
										<%-- <a onclick="exportToExcel()" id="expExcel" href="${pageContext.request.contextPath}/download" disabled="true" class="btn btn-primary">EXPORT TO Excel</a> --%>
										<input type="button" id="expExcel" class="btn btn-primary"
											value="EXPORT TO Excel" onclick="exportToExcel();"
											disabled="disabled">
									</div>
									
									<div class="col-sm-2  controls">
									<a href="#" onclick="getRawMatrialInfo()" data-toggle="modal" data-target="#elegantModalForm"><button type="button" class="btn btn-primary">RM Info</button></a>
										<!-- <input type="button" id="raw" class="btn btn-primary"
											value="RM Info" onclick="getRawMatrialInfo()"> -->
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

	<script>
		function myFunction() {
			var input, filter, table, tr, td, td1, i;
			input = document.getElementById("myInput");
			filter = input.value.toUpperCase();
			table = document.getElementById("table1");
			tr = table.getElementsByTagName("tr");
			for (i = 0; i < tr.length; i++) {
				td = tr[i].getElementsByTagName("td")[0];
				td1 = tr[i].getElementsByTagName("td")[2];

				if (td) {
					if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
						tr[i].style.display = "";
					} else if (td1.innerHTML.toUpperCase().indexOf(filter) > -1) {
						tr[i].style.display = "";
					} else {
						tr[i].style.display = "none";
					}
				}
			}
		}
	</script>
	<script type="text/javascript">
		function callSearch() {

			var isDelete = document.getElementById("isDelete").value;
			var isEdit = document.getElementById("isEdit").value;

			var chkYes = document.getElementById("select_way").value;
			if(chkYes==0)
			document.getElementById("range_fields").style.display = "block";
			else
				document.getElementById("range_fields").style.display = "block";
			var frIds = $("#fr_id").val();
			var array = [];
			var routeIds = $("#selectRoute").val();

			var prodDate = document.getElementById("dp2").value;
			$('#loader').show();

			$
					.getJSON(
							'${callspCakeOrderProcess}',
							{
								fr_id_list : JSON.stringify(frIds),
								prod_date : prodDate,
								route_id : routeIds,
								ajax : 'true',
							},
							function(data) {
								$('#table1 td').remove();
								$('#loader').hide();
								if (data == "") {
									alert("No Orders Found");
									document.getElementById("expExcel").disabled = true;
								}
								
							
								$
										.each(
												data,
												function(key, spCakeOrder) {
													document
															.getElementById("expExcel").disabled = false;
													document
															.getElementById('range').style.display = 'block';
													var len = data.length
													var str = (spCakeOrder.itemId)
															.split('#');

													var tr = $('<tr></tr>');
													/* tr.append($('<td></td>')
															.html("")); */
													
												/* 	if(chkYes==0){
														tr
														.append($(
																'<td></td>')
																.html(
																		'<input type="checkbox" name="selCheck" value='+spCakeOrder.spOrderNo+' id='+key+'/>'));
													}	 */											

													tr.append($('<td></td>')
															.html(key + 1));
													tr
															.append($(
																	'<td></td>')
																	.html(
																			'<a href=# class=action_btn onclick=deleteSpOrder('
																					+ spCakeOrder.spOrderNo
																					+ ');><abbr title=Delete><i class="glyphicon glyphicon-remove"></i></abbr></a>'));

													tr
															.append($(
																	'<td></td>')
																	.html(
																			spCakeOrder.frName));
													tr
															.append($(
																	'<td></td>')
																	.html(
																			spCakeOrder.spDeliveryDate));
													tr
															.append($(
																	'<td></td>')
																	.html(
																			spCakeOrder.spName));
													tr.append($('<td></td>')
															.html(str[1]));
													tr
															.append($(
																	'<td></td>')
																	.html(
																			spCakeOrder.spSelectedWeight));
													tr
															.append($(
																	'<td></td>')
																	.html(
																			spCakeOrder.spfName));
													tr
															.append($(
																	'<td></td>')
																	.html(
																			spCakeOrder.spEvents));
													tr
															.append($(
																	'<td></td>')
																	.html(
																			spCakeOrder.spPrice));
													tr
															.append($(
																	'<td></td>')
																	.html(
																			spCakeOrder.spTotalAddRate));
													var totalValue = parseFloat(spCakeOrder.spTotalAddRate)
															+ parseFloat(spCakeOrder.spPrice);
													tr.append($('<td></td>')
															.html(totalValue));

												/* 	tr
															.append($(
																	'<td></td>')
																	.html(
																			'<a href="${pageContext.request.contextPath}/showHtmlViewSpcakeAlbumOrder/'+spCakeOrder.spOrderNo+'" target="blank"><i class="fa fa-file-text-o" style="font-size:18px;"></i></a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/showSpcakeAlbumOrderPdf/'
																					+ spCakeOrder.spOrderNo
																					+ '/'
																					+ (key + 1)
																					+ '" target="blank"><i class="fa fa-file-pdf-o" style="font-size:18px;"></i></a>'));
											 */		tr
													.append($(
															'<td></td>')
															.html(
																	'<a href="${pageContext.request.contextPath}/showHtmlViewSpcakeAlbumOrder/'+spCakeOrder.spOrderNo+'" target="blank"><i title="view" class="fa fa-file-text-o" style="font-size:24px;"></i></a>'));

											tr
													.append($(
															'<td></td>')
															.html(
																	'<a href="${pageContext.request.contextPath}/showSpcakeAlbumOrderPdf/'
																					+ spCakeOrder.spOrderNo
																					+ '/'
																					+ (key + 1)
																					+ '" target="blank"><i class="fa fa-file-pdf-o" style="font-size:18px;"></i></a>'));

									
													$('#table1 tbody').append(
															tr);

												})

							});

		}

		function inRangePdf() {
			var to = document.getElementById("to").value;

			var from = document.getElementById("from").value;

			if (from == null || from == "") {
				alert("Enter to from");
			} else if (to == null || to == "") {
				alert("Enter to no");
			} else {

				window
						.open("${pageContext.request.contextPath}/showSpcakeAlbumOrderPdfInRange/"
								+ from + "/" + to);

			}
		}
		function getRawMatrialInfo() {
			var from = document.getElementById("from").value;
			var to = document.getElementById("to").value;
			var chkYes = document.getElementById("select_way").value;	
			var prodDate = document.getElementById("dp2").value;
			
			if(chkYes==1){
					if (from == null || from == "") {
						alert("Enter to from");
					} else if (to == null || to == "") {
						alert("Enter to no");
					} 
			}
				//$('#loader').show();
				$.ajax({
				    type: "POST",
		             url: "${pageContext.request.contextPath}/showSpcakeRawMaterialInfo",
		             data: $("#validationform").serialize(),
		             dataType: 'json',
				success: function(data){
					$('#modeltable td').remove();
				//	$('#loader').hide();
					if (data == "") {
						alert("No Orders Found");
					}
					$.each(data,function(key, flvrRawMtrl) {
										var rmType = "";
										if(flvrRawMtrl.rmType==1){
											rmType = 'Raw Material';
										}else if(flvrRawMtrl.rmType==2){
											rmType = 'Semi Finished';
										}
										var tr = $('<tr></tr>');
										 /* tr.append($('<td></td>').html('<input type="checkbox"  value='+key+' name="selCheck"  id='+key+'/>'));  */
                                        tr.append($('<td></td>').html(key + 1));
										tr.append($('<td></td>').html(flvrRawMtrl.rmName));
										tr.append($('<td></td>').html(rmType));
										tr.append($('<td></td>').html(flvrRawMtrl.ttlQty));
										tr.append($('<td></td>').html('<input type="text"  value='+flvrRawMtrl.ttlQty+' id='+0+' style="width:100px;border-radius:25px; font-weight:bold;text-align:center;"   ondrop="return false;" min="0"  onpaste="return false;" class="form-control"/>'));
										$('#modeltable tbody').append(tr);
									})
									document.getElementById("prodDateSpan").innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;"+prodDate;
				}
			}).done(function() {
				setTimeout(function(){
				},500);
			});
		}
	</script>
	<script type="text/javascript">
		function deleteSpOrder(spOrderNo) {

			if (confirm("Do you want to Delete this order?") == true) {
				$
						.getJSON(
								'${deleteSpOrder}',
								{
									sp_order_no : spOrderNo,
									ajax : 'true',
								},
								function(data) {
									$('#table1 td').remove();
									$('#loader').hide();
									if (data == "") {
										alert("No Orders Found");
										document.getElementById("expExcel").disabled = true;
									}
									$
											.each(
													data,
													function(key, spCakeOrder) {
														document
																.getElementById("expExcel").disabled = false;
														document
																.getElementById('range').style.display = 'block';
														var len = data.length

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
																				'<a href=# class=action_btn onclick=deleteSpOrder('
																						+ spCakeOrder.spOrderNo
																						+ ');><abbr title=Delete><i class="glyphicon glyphicon-remove"></i></abbr></a>'));

														tr
																.append($(
																		'<td></td>')
																		.html(
																				spCakeOrder.frName));
														tr
																.append($(
																		'<td></td>')
																		.html(
																				spCakeOrder.spDeliveryDate));
														tr
																.append($(
																		'<td></td>')
																		.html(
																				spCakeOrder.spName));
														tr
																.append($(
																		'<td></td>')
																		.html(
																				spCakeOrder.itemId));
														tr
																.append($(
																		'<td></td>')
																		.html(
																				spCakeOrder.spSelectedWeight));
														tr
																.append($(
																		'<td></td>')
																		.html(
																				spCakeOrder.spfName));
														tr
																.append($(
																		'<td></td>')
																		.html(
																				spCakeOrder.spEvents));
														tr
																.append($(
																		'<td></td>')
																		.html(
																				spCakeOrder.spPrice));
														tr
																.append($(
																		'<td></td>')
																		.html(
																				spCakeOrder.spTotalAddRate));

														var totalValue = parseFloat(spCakeOrder.spTotalAddRate)
																+ parseFloat(spCakeOrder.spPrice);

														tr
																.append($(
																		'<td></td>')
																		.html(
																				totalValue));

														tr
														.append($(
																'<td></td>')
																.html(
																		'<a href="${pageContext.request.contextPath}/showHtmlViewSpcakeAlbumOrder/'+spCakeOrder.spOrderNo+'" target="blank"><i title="view" class="fa fa-file-text-o" style="font-size:24px;"></i></a>'));

												tr
														.append($(
																'<td></td>')
																.html(
																		'<a href="${pageContext.request.contextPath}/showSpcakeAlbumOrderPdf/'
																						+ spCakeOrder.spOrderNo
																						+ '/'
																						+ (key + 1)
																						+ '" target="blank"><i class="fa fa-file-pdf-o" style="font-size:18px;"></i></a>'));

														$('#table1 tbody')
																.append(tr);

													})

								});
			}
		}
	</script>
	<script>
		function exportToExcel() {

			window.open("${pageContext.request.contextPath}/exportToExcel");
			document.getElementById("expExcel").disabled = true;
		}
	</script>
	<script type="text/javascript">
		function disableFr() {

			//alert("Inside Disable Fr ");
			document.getElementById("fr_id").disabled = true;

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
	
	<!-- ------------------------------------------------Model------------------------------------------- -->
	
	<div class="modal fade" id="elegantModalForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
  aria-hidden="true" >
  
  <!--SAVE LOADER-->
    <div id="overlay">
	<div class="clock"></div>
  </div>
  
  <div class="modal-dialog" role="document" style="width:80%;height:50%;">
    <!--Content-->
    <div class="modal-content form-elegant">
      <!--Header-->
       <div class="modal-header text-center">
        <h3 class="modal-title w-100 dark-grey-text font-weight-bold my-3" id="myModalLabel" style="color:#ea4973;"><strong>Raw Material List</strong></h3>     
            <a href="#" class="close" data-dismiss="modal" aria-label="Close" id="closeHrefModel">
                <img src="${pageContext.request.contextPath}/resources/img/close.png" alt="X" class="imageclass"/>
            </a> 
         <div>
      </div> 
      <div class="modal-body mx-6" >
      	<form name="modalfrm" id="modalfrm"  method="post"> 
      			<!-- <label class="col-sm-3 col-lg-3 control-label" style="color:#e20b31;">Production Id :<span id="prodIdSpan"></span></label> -->
      		    <label class="col-sm-3 col-lg-4 control-label" style="color:#e20b31;">Production Date :<span id="prodDateSpan"></span></label>
      		    
     		 <input type="hidden" name="dept" id="dept"  />
     		  <input type="hidden" name="prodHeaderId" id="prodHeaderId"  />
     			<div class="component">
     		
									<table width="80%"  id="modeltable" style="font-size: 13px; font-weight:bold; border: 1px solid;border-color: #91d6b8;" >
										<thead>
											<tr>
											<th width="130" align="left">Sr No</th>
														<!-- <th width="87" align="left">Action</th> -->
														<th>RM Name</th>
														<th>RM Type</th>
														<th>Total Qty.</th>
														<th> Qty.</th>
											</tr>
										</thead>
										<tbody>										
										</tbody>
									</table>
									
								</div>
								<!-- <div class="component" >
									<table width="80%"  id="modeltable2" style="font-size: 13px; font-weight:bold; border: 1px solid;border-color: #91d6b8;" > class="table table-advance"
										<thead>
											<tr>
												<th width="17" style="width: 18px">Sr No</th>
												<th width="120" align="left">Product Name</th>
												<th width="100" align="left">Product Type</th>
												<th width="120" align="left">Qty</th>
												<th width="120" align="left">Edit Qty</th>
												<th width="100" align="left">UOM</th> 
											</tr>
										</thead>
										<tbody>
											
										</tbody>
									</table>
								
								</div> -->
								</form>	
						</div>			
      <!--Body-->
      <div class="modal-body mx-4" >
        <!--Body-->
        <div class="text-center mb-1">
          <button type="button" class="btn btn-primary" id="sbtbtn">Submit</button>
        </div>          
      </div>
      <!--Footer-->   
    </div>
    <!--/.Content-->
  </div>
</div></div>

 <script type="text/javascript">
    	$('#sbtbtn').click(function(){
    		$("#overlay").fadeIn(300);
    		
    		$.ajax({
    			    type: "POST",
		             url: "${pageContext.request.contextPath}/addFlavourRawMaterial",
		             data: $("#modalfrm").serialize(),
		             dataType: 'json',
    			success: function(data){
    				if(data==2)
    					{
    					$('#modeltable td').remove();    					
    					alert("Mixing And Bom Done")
    					$("#overlay").fadeOut(300);
    					$("#closeHrefModel")[0].click()


    					}
    			}
    		}).done(function() {
    			setTimeout(function(){
    				$("#overlay").fadeOut(300);
    			},500);
    		});
    	});	
    	
    </script>
   <!-- <script type="text/javascript">
		function submitBill() {
var form = document.getElementById("validation-form")
form.action = "${pageContext.request.contextPath}/getSpcakeRawMaterialInfo";
form.submit();
		}
		$('#btn_submit')
				.click(
						function() {
							var form = document.getElementById("validation-form")
							form.action = "${pageContext.request.contextPath}/getSpcakeRawMaterialInfo";
							form.submit();
						});	
		
	</script> -->
</body>
</html>