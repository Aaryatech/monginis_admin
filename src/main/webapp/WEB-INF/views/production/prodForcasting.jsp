<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<style type="text/css">
.buttonload {
	background-color: #59b4da08; /* Green background */
	border: none; /* Remove borders */
	color: #444242; /* White text */
	padding: 12px 24px; /* Some padding */
	font-size: 16px; /* Set a font-size */
}

.alert1 {
	padding: 5px;
	background-color: #ee578f;
	color: white;
	opacity: 1;
	transition: opacity 0.6s;
	margin-bottom: 12px;
}

.alert.success {
	background-color: #4CAF50;
}

.alert1.info {
	background-color: #ee578f;
}

.alert.warning {
	background-color: #ff9800;
}

.closebtn {
	margin-left: 32px;
	color: white;
	font-weight: bold;
	float: right;
	font-size: 22px;
	line-height: 20px;
	cursor: pointer;
	transition: 0.3s;
}

.closebtn:hover {
	color: black;
}
</style>
</head>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body>
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<c:url var="findItemsByCategory" value="/getItemsByCategory"></c:url>
	<c:url var="getItemsProdQty" value="/getItemsProdQty"></c:url>
	<c:url var="getItemsForPlan" value="/getItemsForPlan"></c:url>

	<c:url var="getItemsByCategoryWithTotalLimit"
		value="/getItemsByCategoryWithTotalLimit"></c:url>

	<c:url var="getItemsStockByTypeExcel" value="/getItemsStockByTypeExcel"></c:url>




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
						<i class="fa fa-file-o"></i>Plan Production
					</h1>

<div class="alert1 info" >
  <span class="closebtn">&times;</span>  
  <strong><p style="text-align: center;" id="planData" ></p></strong> 
</div>
				</div>
			</div>
			<!-- END Page Title -->



			<!-- BEGIN Main Content -->
			<div class="row">
				<div class="col-md-12">
					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-bars"></i> Plan Production for Cakes
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
							<form class="form-horizontal" id="validation-form">



								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Category</label>

									<div class="col-sm-9 col-lg-10 controls">
										<select class="form-control chosen" name="catId" id="catId">

											<c:forEach items="${catList}" var="catList">

												<option value="${catList.catId}">${catList.catName}
												</option>

											</c:forEach>


										</select>
									</div>

								</div>



								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-5 col-lg-offset-2">

										<input type="button" class="btn btn-info" name="submit"
											value="submit" onclick="searchItemsByCategory()" /> <input
											type="button" class="btn btn-info" name="submit"
											value="Limit" onclick="searchItemsAndStockTotalByCategory()" />

										<input type="button" class="btn btn-info" name="Excel"
											value="Excel" onclick="generateExcel()" />






										<button class="buttonload" id="loader1" style="display: none;">
											<i class="fa fa-spinner fa-spin"></i>Loading
										</button>
									</div>
								</div>
								<input type="hidden" id="selectedCatId" name="selectedCatId" />


								<div align="center" id="loader" style="display: none">

									<span>
										<h4>
											<font color="#343690">Loading</font>
										</h4>
									</span> <span class="l-1"></span> <span class="l-2"></span> <span
										class="l-3"></span> <span class="l-4"></span> <span
										class="l-5"></span> <span class="l-6"></span>
								</div>


							</form>
							<form
								action="${pageContext.request.contextPath}/submitProductionPlan"
								method="post">

								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i> Production List
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

													</thead>
												</table>

											</div>
											<div class="table-wrap">

												<table id="table1" class="table table-advance">
													<!-- //removed text -->
													<!-- 	<div class="table-responsive" style="border: 0">
											<table width="100%" class="table table-advance" id="table1"> -->
													<thead>
														<tr>
															<th width="20" align="left">No</th>
															<th width="180" align="left">Item Name</th>
															<!-- 														<th width="30" align="left">Cur Closing</th>
 -->
															<th width="50" align="left" id="thLimit"
																style="display: none;">Limit</th>

															<th width="90" align="left">Cur Opening</th>

															<%-- <th width="90" align="left">
										 					<div>
 									                     	<input class="form-control date-picker" id="datepicker1" size="16" type="text" name="datepicker1" value="" placeholder="Date1"  disabled/>
 								                     	  <a href="${pageContext.request.contextPath}/"> <span class="	glyphicon glyphicon-circle-arrow-right"></span></a>
								                     	  
                                                              </div>
														</th>
                                                       <th width="5" align="left"><i class="glyphicon glyphicon-circle-arrow-right  fa-2x" onclick=" return getProdQty(1,5)"></i>
														 </th> --%>

															<th width="100" align="left">
																<div>
																	<input class="form-control date-picker"
																		id="datepicker2" size="16" type="text"
																		name="datepicker2" value="" placeholder="Order Date"
																		onblur=" return getProdQty(2,2)" />
																</div>
															</th>
															<th width="5" align="left"><i
																class="	glyphicon glyphicon-circle-arrow-right  fa-2x"
																onclick=" return getProdQty(2,5)"></i></th>

															<th width="100" align="left">
																<div>
																	<input class="form-control date-picker"
																		id="datepicker3" size="16" type="text"
																		name="datepicker3" value="" placeholder="Date3"
																		onblur=" return getProdQty(3,3)" />
																</div>
															</th>
															<th width="5" align="left"><i
																class="	glyphicon glyphicon-circle-arrow-right  fa-2x"
																onclick=" return getProdQty(3,5)"></i></th>

															<!-- <th width="100" align="left">
															<div>
									                     	<input class="form-control date-picker" id="datepicker4" size="16" type="text" name="datepicker4" value="" placeholder="Date4"  onblur=" return getProdQty(4,4)"/>
								                     	    </div>
														</th> 
													 <th width="5" align="left">  <i class="	glyphicon glyphicon-circle-arrow-right  fa-2x" onclick=" return getProdQty(4,5)"></i>
														 </th> -->

															<!-- 	<th width="120" align="left">
															<div>
									                     	<input class="form-control date-picker" id="datepicker5" size="16" type="text" name="datepicker5" value="" placeholder="Date5"  onblur=" return getProdQty(5)"/>
								                     	    </div>
														</th> -->

															<th width="100" align="left">
																<div>
																	<input class="form-control date-picker"
																		id="datepicker5" size="16" type="text"
																		name="datepicker5" value="" placeholder="Date5"
																		onblur=" return getProdQty(5,5)" />
																</div>
															</th>




														</tr>
													</thead>
													<tbody>

													</tbody>
												</table>
											</div>
											<!-- //added div -->
										</div>
									</div>
								</div>
								<br>

								<div align="center" class="form-group">
									<div class="col-sm-5 col-lg-10 controls">

										Select Time Slot <select data-placeholder="Choose Time Slot"
											tabindex="-1" name="selectTime" id="selectTime"
											data-rule-required="true">


											<c:forEach items="${productionTimeSlot}" var="productionTime"
												varStatus="count">
												<option value="${productionTime}"><c:out
														value="Time Slot ${productionTime}" /></option>
											</c:forEach>


										</select>
									</div>
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="button" class="btn btn-info" name="plandetail"
											id="plandetail" value="PLAN DETAILS"
											onclick="searchPlanDetails()" />
										<!-- </div>
									<div
										class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0"> -->
										<input type="submit" class="btn btn-primary" value="Submit"
											id="callSubmit">


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
		function searchItemsByCategory() {

			var catId = $("#catId").val();
			document.getElementById("selectedCatId").value = catId;
			var d = new Date();
			var todayTimeStamp1 = +d; // Unix timestamp in milliseconds
			d.setDate(d.getDate() + 2);
			var todayTimeStamp = +d; // Unix timestamp in milliseconds
			var oneDayTimeStamp = 1000 * 60 * 60 * 24; // Milliseconds in a day
			var diff = todayTimeStamp - oneDayTimeStamp;
			var diff1 = todayTimeStamp1 - oneDayTimeStamp;
			var yesterdayDate = new Date(diff);
			var yesterdayDate1 = new Date(diff1);
			var todaysDate = new Date(todayTimeStamp);

			var tommarowString = yesterdayDate.getDate() + '-'
					+ (yesterdayDate.getMonth() + 1) + '-'
					+ yesterdayDate.getFullYear();
			var yesterdayString = (yesterdayDate1.getDate()) + '-'
					+ (yesterdayDate1.getMonth() + 1) + '-'
					+ yesterdayDate1.getFullYear();

			$("#datepicker1").val(yesterdayString);
			$("#datepicker5").val(tommarowString);

			//alert(catId);

			$('#loader').show();
			$('#thLimit').hide();
			$('#table1 td').remove();
			
			$("#datepicker2").val('');
			$("#datepicker3").val('');

			$
					.getJSON(
							'${findItemsByCategory}',
							{

								catId : catId,
								ajax : 'true'

							},
							function(data) {

								$('#table1 td').remove();
								document.getElementById("plandetail").disabled = false;

								$('#loader').hide();
								if (data == "") {
									alert("No records found !!");
									document.getElementById("callSubmit").disabled = true;
									document.getElementById("plandetail").disabled = true;
								}

								$
										.each(
												data,
												function(key, item) {

													var index = key + 1;

													var tr = "<tr>";

													var index = "<td>&nbsp;&nbsp;&nbsp;"
															+ index + "</td>";

													var itemName = "<td>"
															+ item.name
															+ "</td>";

													/* 															var curClosing = "<td align=center colspan='2'><input type=text  class=form-control  id= curClos"+ item.id+ " name=curClos"+item.id+" value ="+item.curClosingQty+"></td>"; 
													 */
													var curOpening = "<td align=center colspan='1'><input type=text  class=form-control  id= curOpe"+ item.id+ " name=curOpe"+item.id+" value ="+item.curOpeQty+" disabled></td>";

													/* var qty1 = "<td align=center colspan='2'><input type=text  class=form-control  id= qty1"+ item.id+ " name=qty1"+item.id+" value = "+item.qty+ " disabled></td>"; 
													 */

													var qty2 = "<td align=center colspan='2'><input type=text  class=form-control  id= qty2"+ item.id+ " name=qty2"+item.id+" value = "+0+ " disabled></td>";

													var qty3 = "<td align=center colspan='2'><input type=text  class=form-control  id= qty3"+ item.id+ " name=qty3"+item.id+" value = "+0+ " disabled></td>";

													/*  var qty4 = "<td align=center colspan='2'><input type=text  class=form-control  id= qty4"+ item.id+ " name=qty4"+item.id+" value = "+0+ " disabled></td>";
													 */
													/* 													var qty5 = "<td align=center><input type=text min=0 max=500 class=form-control  id= qty5"+ item.id+ " name=qty5"+item.id+" value = "+0+ " disabled></td>";
													 */
													var qty5 = "<td align=center colspan='2'><input type=number  class=form-control  id= qty5"+ item.id+ " name=qty5"+item.id+" value = "+0+ "></td>";

													var trclosed = "</tr>";

													$('#table1 tbody').append(
															tr);
													$('#table1 tbody').append(
															index);
													$('#table1 tbody').append(
															itemName);

													$('#table1 tbody').append(
															curOpening);

													/* $('#table1 tbody')
															.append(
																	qty1); */

													$('#table1 tbody').append(
															qty2);

													$('#table1 tbody').append(
															qty3);
													/*  $('#table1 tbody')
														.append(qty4);  */
													$('#table1 tbody').append(
															qty5);

													$('#table1 tbody').append(
															trclosed);

												})

							});

		}
	</script>



	<!-- ----------------------TOTAL-------------------------------------- -->


	<script type="text/javascript">
		function searchItemsAndStockTotalByCategory() {

			var catId = $("#catId").val();
			document.getElementById("selectedCatId").value = catId;
			var d = new Date();
			var todayTimeStamp1 = +d; // Unix timestamp in milliseconds
			d.setDate(d.getDate() + 2);
			var todayTimeStamp = +d; // Unix timestamp in milliseconds
			var oneDayTimeStamp = 1000 * 60 * 60 * 24; // Milliseconds in a day
			var diff = todayTimeStamp - oneDayTimeStamp;
			var diff1 = todayTimeStamp1 - oneDayTimeStamp;
			var yesterdayDate = new Date(diff);
			var yesterdayDate1 = new Date(diff1);
			var todaysDate = new Date(todayTimeStamp);

			var tommarowString = yesterdayDate.getDate() + '-'
					+ (yesterdayDate.getMonth() + 1) + '-'
					+ yesterdayDate.getFullYear();
			var yesterdayString = (yesterdayDate1.getDate()) + '-'
					+ (yesterdayDate1.getMonth() + 1) + '-'
					+ yesterdayDate1.getFullYear();

			$("#datepicker1").val(yesterdayString);
			$("#datepicker5").val(tommarowString);

			//alert(catId);

			$('#loader').show();
			$('#thLimit').show();
			$('#table1 td').remove();
			
			$("#datepicker2").val('');
			$("#datepicker3").val('');
			
			

			$
					.getJSON(
							'${getItemsByCategoryWithTotalLimit}',
							{

								catId : catId,
								ajax : 'true'

							},
							function(data) {

								$('#table1 td').remove();
								document.getElementById("plandetail").disabled = false;

								$('#loader').hide();
								if (data == "") {
									alert("No records found !!");
									document.getElementById("callSubmit").disabled = true;
									document.getElementById("plandetail").disabled = true;
								}

								$
										.each(
												data,
												function(key, item) {
													

													var index = key + 1;

													var tr = "<tr>";

													var index = "<td>&nbsp;&nbsp;&nbsp;"
															+ index + "</td>";

													var itemName = "<td>"
															+ item.name
															+ "</td>";

													var totalLimit = "<td align=center colspan='1'><input type=text  class=form-control  value ="+item.totalLimit+" disabled></td>";

													/* 															var curClosing = "<td align=center colspan='2'><input type=text  class=form-control  id= curClos"+ item.id+ " name=curClos"+item.id+" value ="+item.curClosingQty+"></td>"; 
													 */
													var curOpening = "<td align=center colspan='1'><input type=text  class=form-control  id= curOpe"+ item.id+ " name=curOpe"+item.id+" value ="+item.curOpeQty+" disabled></td>";

													/* var qty1 = "<td align=center colspan='2'><input type=text  class=form-control  id= qty1"+ item.id+ " name=qty1"+item.id+" value = "+item.qty+ " disabled></td>"; 
													 */

													var qty2 = "<td align=center colspan='2'><input type=text  class=form-control  id= qty2"+ item.id+ " name=qty2"+item.id+" value = "+0+ " disabled></td>";

													var qty3 = "<td align=center colspan='2'><input type=text  class=form-control  id= qty3"+ item.id+ " name=qty3"+item.id+" value = "+0+ " disabled></td>";

													/*  var qty4 = "<td align=center colspan='2'><input type=text  class=form-control  id= qty4"+ item.id+ " name=qty4"+item.id+" value = "+0+ " disabled></td>";
													 */
													/* 													var qty5 = "<td align=center><input type=text min=0 max=500 class=form-control  id= qty5"+ item.id+ " name=qty5"+item.id+" value = "+0+ " disabled></td>";
													 */

													if ((item.totalLimit
															- item.curOpeQty < 0)) {

														var qty5 = "<td align=center colspan='2'><input type=number  class=form-control  id= qty5"
																+ item.id
																+ " name=qty5"
																+ item.id
																+ " value = "
																+ 0
																+ "></td>";

													} else {

														var qty5 = "<td align=center colspan='2'><input type=number  class=form-control  id= qty5"
																+ item.id
																+ " name=qty5"
																+ item.id
																+ " value = "
																+ (item.totalLimit - item.curOpeQty)
																+ "></td>";

													}

													var trclosed = "</tr>";

													$('#table1 tbody').append(
															tr);
													$('#table1 tbody').append(
															index);
													$('#table1 tbody').append(
															itemName);

													$('#table1 tbody').append(
															totalLimit);

													$('#table1 tbody').append(
															curOpening);

													/* $('#table1 tbody')
															.append(
																	qty1); */

													$('#table1 tbody').append(
															qty2);

													$('#table1 tbody').append(
															qty3);
													/*  $('#table1 tbody')
														.append(qty4);  */
													$('#table1 tbody').append(
															qty5);

													$('#table1 tbody').append(
															trclosed);

												})

							});

		}
	</script>

	<!-- ------------------------------------------------------------------ -->





	<script>
		function searchPlanDetails() {
			var catId = $("#selectedCatId").val();

			$.getJSON('${getItemsForPlan}', {

				catId : catId,
				ajax : 'true'

			}, function(data) {
				var a = [];
				var total = 0;
				$.each(data.catList, function(key, subCat) {
					var qty = 0;
					$.each(data.itemList, function(key, item) {
						if (item.itemGrp2 == subCat.subCatId) {
							var actQty = parseInt($("#qty5" + item.id).val());
							qty = qty + actQty;
							total = total + actQty;
						}

					});
					a.push(subCat.subCatName + ":[ " + qty + "  ]   ");

				});

				document.getElementById("planData").innerHTML = a
						+ "|| TOTAL:- " + total;

			});

		}
	</script>
	<script type="text/javascript">
		function getProdQty(token, id) {

			var prodDate = document.getElementById("datepicker" + token).value;

			var selectedCatId = document.getElementById("selectedCatId").value;

			//  alert("Your typed in " + prodDate);

			if (id == 5) {
				//alert("5"+window.getComputedStyle(thLimit)['display']);

				if (window.getComputedStyle(thLimit)['display'] == "none") {
					//alert("hide");

					$
							.getJSON(
									'${getItemsProdQty}',
									{

										prodDate : prodDate,
										catId : selectedCatId,
										id : token,
										ajax : 'true'

									},
									function(data) {

										var len = data.length;
										$.each(data.itemList, function(key,
												item) {
											document.getElementById('qty' + id
													+ '' + item.id).value = 0;

										})

										var prodQtyListLength = data.getProductionItemQtyList.length;
										if (prodQtyListLength > 0) {
											$
													.each(
															data.getProductionItemQtyList,
															function(key, prod) {

																$
																		.each(
																				data.itemList,
																				function(
																						key,
																						item) {

																					if (prod.itemId == item.id) {
																						document
																								.getElementById('qty'
																										+ id
																										+ ''
																										+ prod.itemId).value = prod.qty;
																					}

																				})

															})
										} else {
											$
													.each(
															data.itemList,
															function(key, item) {
																document
																		.getElementById('qty'
																				+ id
																				+ ''
																				+ item.id).value = 0;

															})

										}

									});

				}

			}else{
				
				$
				.getJSON(
						'${getItemsProdQty}',
						{

							prodDate : prodDate,
							catId : selectedCatId,
							id : token,
							ajax : 'true'

						},
						function(data) {

							var len = data.length;
							$.each(data.itemList, function(key,
									item) {
								document.getElementById('qty' + id
										+ '' + item.id).value = 0;

							})

							var prodQtyListLength = data.getProductionItemQtyList.length;
							if (prodQtyListLength > 0) {
								$
										.each(
												data.getProductionItemQtyList,
												function(key, prod) {

													$
															.each(
																	data.itemList,
																	function(
																			key,
																			item) {

																		if (prod.itemId == item.id) {
																			document
																					.getElementById('qty'
																							+ id
																							+ ''
																							+ prod.itemId).value = prod.qty;
																		}

																	})

												})
							} else {
								$
										.each(
												data.itemList,
												function(key, item) {
													document
															.getElementById('qty'
																	+ id
																	+ ''
																	+ item.id).value = 0;

												})

							}

						});
				
			}

		}
	</script>



	<script>
		function generateExcel() {
			var catId = $("#catId").val();

			//alert("cat id  - " + catId);

			$('#loader').show();

			$.getJSON('${getItemsStockByTypeExcel}', {
				catId : catId,
				ajax : 'true'
			}, function(data) {

				$('#loader').hide();

				//alert("Excel Ready");
				exportToExcel();

			});
		}

		function exportToExcel() {
			//alert("Export Excel");
			window.open("${pageContext.request.contextPath}/exportToExcel");
		}
	</script>

	<script>
		var close = document.getElementsByClassName("closebtn");
		var i;

		for (i = 0; i < close.length; i++) {
			close[i].onclick = function() {
				var div = this.parentElement;
				div.style.opacity = "0";
				setTimeout(function() {
					div.style.display = "none";
				}, 600);
			}
		}
	</script>
</body>

</html>