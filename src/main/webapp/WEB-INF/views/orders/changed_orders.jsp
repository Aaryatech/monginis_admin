<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<style>
table {
	overflow: hidden;
}

tr:hover {
	background-color: #ffa;
}

td, th {
	position: relative;
}

td:hover::after, th:hover::after {
	content: "";
	position: absolute;
	background-color: #ffa;
	left: 0;
	top: -5000px;
	height: 10000px;
	width: 100%;
	z-index: -1;
}
</style>


<body>


	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<c:url var="getModifiedOrdersData" value="/getModifiedOrdersData" />
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
						<i class="fa fa-file-o"></i>Modified Orders from Admin 
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
						<c:when test="${subModule.subModuleMapping eq 'showOrders'}">

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
								<i class="fa fa-bars"></i>Search Modified Orders
							</h3>
							<div class="box-tool">
								<!-- <a href="">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> -->
							</div>
							
						</div>


						<div class="box-content">
							<form action="searchOrdersProcess" class="form-horizontal"
								method="post" id="validation-form">




								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Franchisee
									</label>
									<div class="col-sm-3 col-lg-4 controls">

										<select data-placeholder="Select Franchisee"
											class="form-control chosen" multiple="multiple" tabindex="6"
											name="fr_id" id="fr_id" onchange="disableRoute()">

											<option value="-1">All</option>
											<c:forEach items="${franchiseeList}" var="franchiseeList">
												<option value="${franchiseeList.frId}">${franchiseeList.frName}</option>


											</c:forEach>

										</select>

									</div>
									<!-- 	</div>
							
                        <div class="form-group"> -->
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


								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Delivery From</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="fromDate" size="16"
											type="text" name="fromDate" value="${date}" required />
									</div>
										<label class="col-sm-3 col-lg-2 control-label">To</label>
									
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="toDate" size="16"
											type="text" name="toDate" value="${date}" required />
									</div>
								</div>



								<div align="center" class="form-group">
									<div
										class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
										<input type="button" class="btn btn-primary" value="Submit"
											onclick="callSearch()">


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
								</div>





								<c:set var="dis" value="none" />


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

									<div class="box-content">

										<div class="clearfix"></div>
										<div id="table-scroll" class="table-scroll">

											<!-- <div id="faux-table" class="faux-table" aria="hidden">
												<table id="table2" class="main-table">
													<thead>
														<tr class="bgpink">
															<th class="col-sm-1"><input type="checkbox" id="all" />
																All</th>
															<th width="148" style="width: 18px" align="left">No</th>
															<th width="198" align="left">Franchisee Name</th>
															<th width="190" align="left">Item Name</th>
															<th width="199" align="left">Category</th>
															<th width="199" align="left">Quantity</th>
															<th width="100" align="right">Action</th>
														</tr>
													</thead>
												</table>

											</div> -->
											<div class="table-wrap">

												<table id="table1" class="table table-advance">
													<thead>
														<tr class="bgpink">
															<th class="col-sm-1"><input type="checkbox" id="all" />
																All</th>
															<th width="148" style="width: 18px" align="left">No</th>
															<th width="198" align="left">Franchisee Name</th>
																<th width="190" align="left">Delivery Date</th>
															<th width="190" align="left">Item Name</th>
															<th width="199" align="left">Action</th>
															 <th width="100" align="right">Orgi Qty-Modif Qty</th>
															 <th width="199" align="left">Change Date</th>
														      <th width="199" align="left">User Name</th>
															
														</tr>
													</thead>



													<tbody>
														<%-- <c:forEach items="${orderList}" var="orderList"
															varStatus="count">
															<c:set var="dis" value="block" />
															<tr>
																<td><c:out value="${count.index+1}" /></td>

																<td align="left"><c:out value="${orderList.frName}" /></td>

																<td align="left"><c:out
																		value="${orderList.itemName}" /></td>


																<td align="left"><c:out
																		value="${orderList.catName}" /></td>


																<td align="left"><c:out value="${orderList.id}" /></td>



															</tr>
														</c:forEach> --%>

													</tbody>
												</table>
											</div>
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
				<p style="text-align: center;">2018 © MONGINIS.</p>
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
		function callSearch() {



			var routeIds = $("#selectRoute").val();
			var frIds = $("#fr_id").val();

			var fromDate = $("#fromDate").val();
			var toDate = $("#toDate").val();
			//alert(date);
			$('#loader').show();

				$
						.getJSON(
								'${getModifiedOrdersData}',
								{
									fr_id_list : JSON.stringify(frIds),
									route_id : routeIds,
									fromDate : fromDate,
									toDate : toDate,
									ajax : 'true'
								},
								function(data) {
									$('#loader').hide();
									var len = data.length;
//alert(JSON.stringify(data));
									$('#table1 td').remove();
									$
											.each(
													data,
													function(key, orders) {
																												
														var tr = $('<tr></tr>');

														tr
																.append($(
																		'<td class="col-sm-1"></td>')
																		.html(
																				"<input type='checkbox' name='selorder' class='selorder' id="+orders.orderId+"   value="+orders.orderId+">"));

														tr
																.append($(
																		'<td></td>')
																		.html(
																				key + 1));

														tr
																.append($(
																		'<td></td>')
																		.html(
																				orders.frName));

														tr
																.append($(
																		'<td></td>')
																		.html(
																				orders.deliveryDate));

														tr
																.append($(
																		'<td></td>')
																		.html(
																				orders.itemName));
														
														tr
														.append($(
																'<td></td>')
																.html(
																		orders.changeName));
														
														tr
														.append($(
																'<td></td>')
																.html(
																		orders.origQty +"-"+orders.changeQty ));
														
														tr
														.append($(
																'<td></td>')
																.html(
																		orders.changeDate));
														
														tr
														.append($(
																'<td></td>')
																.html(
																		orders.userName));
														

														$('#table1 tbody')
																.append(tr);

													})

								});
			
		}
	</script>





	<script type="text/javascript">
		function editQty(orderId) {
			var state = document.getElementById("qty" + orderId).disabled;
			var textId = document.getElementById("qty" + orderId).value;
			//alert(textId);
			//document.getElementById(orderId).disabled=false;
			if (state) {
				$("#edit" + orderId).removeClass("glyphicon glyphicon-edit");
				$("#edit" + orderId).addClass("glyphicon glyphicon-ok");
				document.getElementById("qty" + orderId).disabled = false;

			} else {
				$("#edit" + orderId).removeClass("glyphicon glyphicon-ok");
				$("#edit" + orderId).addClass("glyphicon glyphicon-edit");
				document.getElementById("qty" + orderId).disabled = true;
				$.getJSON('${callChangeQty}', {

					order_id : orderId,
					order_qty : textId,

					ajax : 'true'

				});
			}
		}

		var specialKeys = new Array();
		specialKeys.push(8); //Backspace
		function IsNumeric(e) {
			var keyCode = e.which ? e.which : e.keyCode
			var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys
					.indexOf(keyCode) != -1);
			/*  document.getElementById("error").style.display = ret ? "none" : "inline"; */
			return ret;
		}
	</script>
	<script type="text/javascript">
		function deleteOrder(orderId) {
			var isDelete = document.getElementById("isDelete").value;
			var isEdit = document.getElementById("isEdit").value;

			if (confirm("Do you want to Delete this order?") == true) {
				$
						.getJSON(
								'${callDeleteOrder}',
								{
									order_id : orderId,

									ajax : 'true'

								},
								function(data) {
									document.getElementById("expExcel").disabled = true;
									$('#loader').hide();
									var len = data.length;

									$('#table1 td').remove();

									$
											.each(
													data,
													function(key, orders) {
														document
																.getElementById("expExcel1").disabled = false;

														document
																.getElementById("expExcel").disabled = false;
														document
																.getElementById('range').style.display = 'block';
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
																				orders.frName));

														tr
																.append($(
																		'<td></td>')
																		.html(
																				orders.itemName));

														tr
																.append($(
																		'<td></td>')
																		.html(
																				orders.catName));


														$('#table1 tbody')
																.append(tr);

													})

								});

			}

		}
	</script>

	<script>
		function exportToExcel() {
			var itemIds = $("#item_id").val();

			var array = [];

			var routeIds = $("#selectRoute").val();
			var frIds = $("#fr_id").val();

			var date = $("#date").val();
			$('#loader1').show();

			$
					.getJSON(
							'${callSearchOrdersProcess}',
							{

								fr_id_list : JSON.stringify(frIds),
								item_id_list : JSON.stringify(itemIds),
								route_id : routeIds,
								date : date,

								ajax : 'true'

							},
							function(data) {
								$('#loader1').hide();
								window
										.open("${pageContext.request.contextPath}/exportToExcel");
								document.getElementById("expExcel").disabled = true;
							});

		}
		function exportToExcel1() {

			var itemIds = $("#item_id").val();

			var array = [];

			var routeIds = $("#selectRoute").val();
			var frIds = $("#fr_id").val();

			var date = $("#date").val();
			$('#loader1').show();

			$
					.getJSON(
							'${excelOrderByItem}',
							{

								fr_id_list : JSON.stringify(frIds),
								item_id_list : JSON.stringify(itemIds),
								route_id : routeIds,
								date : date,

								ajax : 'true'

							},
							function(data) {
								$('#loader1').hide();
								window
										.open("${pageContext.request.contextPath}/exportToExcel");
								document.getElementById("expExcel1").disabled = true;
							});

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

	<script>
		function getItemsByMenuId() {
			//alert("hii");

			var menuIds = $("#item_id").val();
			//var frId = $("#fr_id").val();
			if (menuIds == "" || menuIds == null) {

				$('#item').find('option').remove().end()
				$("#item").trigger("chosen:updated");
			} else {
				$.getJSON('${getItemListByMenuId}', {

					menuId : JSON.stringify(menuIds),
					//frId:JSON.stringify(frId),
					ajax : 'true'
				}, function(data) {
					//alert(data);
					var html = '<option value="">Select Item</option>';

					var len = data.length;

					$('#item').find('option').remove().end()

					$("#item").append(
							$("<option></option>").attr("value", -1)
									.text("ALL"));

					for (var i = 0; i < len; i++) {
						$("#item").append(
								$("<option ></option>").attr("value",
										data[i].id).text(data[i].itemName));
					}
					$("#item").trigger("chosen:updated");
				});
			}
		}
	</script>
	<script type="text/javascript">
		function updateDetails() {

			//alert("hii");

			var delDate = $("#delivery_date").val();
			var prodDate = $("#production_date").val();
			if (prodDate == "") {

				alert("Please Select Production Date");
			} else if (delDate == "") {
				alert("Please Select Delivery Date");
			} else {
				var selectedItems = new Array();

				var checkedVals = $('.selorder:checkbox:checked').map(
						function() {
							return this.value;
						}).get();

				if (checkedVals == "") {
					alert("Please select atleast one order!!")
				} else {

					$.getJSON('${updateOrderDetails}', {

						ids : checkedVals.join(","),
						delDate : delDate,
						prodDate : prodDate,
						ajax : 'true'

					}, function(data) {
						//alert(data);

						if (data.error == false) {

							alert("Delivery Date updated Successfully.");
							callSearch();
						} else {
							alert("Delivery Date Not Updated.");
						}
					});
				}
			}
		}
	</script>
	<script type="text/javascript">
		function validate() {

			var menu = $("#item_id").val();
			var selectFr = $("#selectRoute").val();
			var selectItem = $("#item").val();

			var isValid = true;

			if (selectFr == "" || selectFr == null) {
				isValid = false;
				alert("Please Select Franchise");
			} else if (menu == "" || menu == null) {

				isValid = false;
				alert("Please Select Menu");
			} else if (selectItem == "" || selectItem == null) {

				isValid = false;
				alert("Please Select Items");
			}
			return isValid;

		}
	</script>


	</script>

</body>
</html>