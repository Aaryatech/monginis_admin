<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body>
	<c:url var="getAllItemsForStation" value="/getAllItemsForStation" />

	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>




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
						<i class="fa fa-file-o"></i>Station
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
								<i class="fa fa-bars"></i> Add Station
							</h3>
							<div class="box-tool"></div>

						</div>




						<div class="box-content">
							<form action="${pageContext.request.contextPath}/insertStation"
								class="form-horizontal" method="post" id="validation-form">
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Select
										Item</label>
									<div class="col-sm-9 col-lg-10 controls">
										<select data-placeholder="Select Item" multiple="multiple"
											onchange="getAllItems()" class="form-control chosen"
											name="itemIdlist" id="itemIdList">
											<option value="-1">All</option>

											<c:choose>
												<c:when test="${isEdit==1}">

													<c:forEach items="${selectedItems}" var="item">
														<option value="${item.id}" selected>${item.itemName}</option>
													</c:forEach>
													<c:forEach items="${nonSelectedItems}" var="item">
														<option value="${item.id}">${item.itemName}</option>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<c:forEach items="${itemsList}" var="itemList"
														varStatus="count">
														<option value="${itemList.id}"><c:out
																value="${itemList.itemName}" />
														</option>

													</c:forEach>
												</c:otherwise>
											</c:choose>
										</select>

									</div>
								</div>


								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label"
										for="payment_desc">Station No. </label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="stationNo" id="stationNo"
											placeholder="Station No." class="form-control"
											value="${editStation.stationNo}" data-rule-required="true" />
									</div>
								</div>
								<input type="hidden" value="${editStation.stationId}"
									id="stationId" name="stationId">



								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
										<input type="submit" class="btn btn-primary" value="Submit">
										<!-- <button type="button" class="btn">Cancel</button> -->
									</div>
								</div>
							</form>
						</div>


						<div class="box-content">
						<form action="${pageContext.request.contextPath}/addStation"
								class="form-horizontal" method="GET" id="validation-form">
							<div class="row">


								<div class="form-group">




									<label class="col-sm-1 col-lg-2 control-label" for="payment_desc">Bill Date</label>
									<div class="col-sm-3 col-lg-2 control-label controls date_select">
										<input class="form-control date-picker" id="Bill_date"
											name="Bill_date" size="30" type="text"  value="${Bill_date}"  />
									</div>
									
								<input type="submit" class="btn btn-primary" value="Submit">
								</div>

							</div>
							</form>
						</div>


						<div class="box-content">

							
							<div class="clearfix"></div>
							<div id="table-scroll" class="table-scroll">

								<div class="table-wrap" style="overflow: auto;">

									<table id="table1" class="table table-advance">
										<thead>
											<tr class="bgpink">
												<th width="138" style="width: 18px" align="left">SR No</th>
												<th class="col-md-2">Station No.</th>
												<th class="col-md-10">Items</th>
												<th class="col-md-10">Total Order</th>
												<th class="col-md-2" width="90px">Action</th>
											</tr>
										</thead>
										<tbody style="padding-top: 100px">

											<c:forEach items="${stationList}" var="station"
												varStatus="count">

												<tr>
													<td><c:out value="${count.index+1}"></c:out></td>
													<td align="left"><c:out value="${station.stationNo}" /></td>
													<td align="left"><c:out value="${station.itemId}" /></td>
													<td align="left"><c:out value="${station.exInt1}" /></td>
													<td align="left"><a
														href="editStation/${station.stationId}"><span
															class="glyphicon glyphicon-edit"></span></a>&nbsp; <a
														href="deleteStation/${station.stationId}"
														onClick="return confirm('Are you sure want to delete this record');"><span
															class="glyphicon glyphicon-remove"></span></a></td>



												</tr>

											</c:forEach>

										</tbody>

									</table>
								</div>
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
		function getAllItems() {

			var selected = $('#itemIdList').val();

			if (selected == -1) {
				$.getJSON('${getAllItemsForStation}', {

					ajax : 'true'
				}, function(data) {

					var html = '<option value="">Items</option>';

					var len = data.length;

					$('#itemIdList').find('option').remove().end()

					for (var i = 0; i < len; i++) {

						$("#itemIdList").append(
								$("<option selected></option>").attr("value",
										data[i].id).text(data[i].itemName));
					}

					$("#itemIdList").trigger("chosen:updated");
				});
			}

		}

		/* function selectAllItem() {

			$.getJSON('${getAllItemsForStation}', {

				ajax : 'true'
			}, function(data) {
				var html = '<option value="">Item</option>';

				var len = data.length;

				$('#ItemIdList').find('option').remove().end()

				$("#ItemIdList").append(
						$("<option></option>").attr("value", 0).text(
								"Select Item"));

				for (var i = 0; i < len; i++) {
					$("#ItemIdList").append(
							$("<option></option>")
									.attr("value", data[i].menuId).text(
											data[i].menuTitle));
				}
				$("#menu").trigger("chosen:updated");
			});
		} */
	</script>







</body>
</html>