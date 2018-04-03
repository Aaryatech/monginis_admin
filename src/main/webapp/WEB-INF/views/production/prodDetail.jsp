<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.message {
	padding: 6px;
	margin: auto;
	width: 60%;
	list-style: none;
	text-align: center;
	font-size: 14px;
	font-weight: bold;
}

.messagesErr {
	border: 1px solid #e3c1c5;
	color: #e32139;
	background: #ffeff1;
}
</style>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

<body>

	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<c:url var="updatePlanQty" value="/updatePlanQty" />

	<c:url var="goToManualBom" value="/goToManualBom" />

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
						<i class="fa fa-file-o"></i>Production Detail
					</h1>




				</div>
			</div>
			<!-- END Page Title -->



			<!-- BEGIN Main Content -->
			<div class="row">
				<c:choose>
					<c:when test="${not empty msg}">
						<!-- here would be a message with a result of processing -->
						<div class="message messagesErr">${msg}</div>
					</c:when>

					<c:otherwise>
						<div class="col-md-12">
							<div class="box">
								<div class="box-title">
									<h3>
										<i class="fa fa-bars"></i> View Production Detail
									</h3>
									<div class="box-tool">
										<a href="${pageContext.request.contextPath}/showProdHeader">Back
											to List</a> <a data-action="collapse" href="#"><i
											class="fa fa-chevron-up"></i></a>
									</div>





								</div>


								<div class="box-content">
									<form action="${pageContext.request.contextPath}/updateQty"
										class="form-horizontal" id="validation_form" method="post"
										name="form">



										<input type="hidden" name="mode_add" id="mode_add"
											value="add_att">

										<div class="form-group">

											<label class="col-sm-3 col-lg-2 control-label">Prod
												ID</label>
											<div class="col-sm-5 col-lg-3 controls">
												<input disabled type="text"
													value="${planHeader.productionHeaderId}" id="prod_id"
													name="prod_id" class="form-control" />
											</div>

											<label class="col-sm-3 col-lg-2 control-label">Prod
												Date </label>
											<div class="col-sm-5 col-lg-3 controls">
												<input type="text" name="prod_date" id="prod_date"
													class="form-control" value="${planHeader.productionDate}"
													readonly />
											</div>



										</div>

										<input type="hidden" value="${planHeader.isPlanned}"
											name="is_plan" /> <input type="hidden"
											value="${planHeader.productionHeaderId}" name="production_id" />

										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Category
											</label>
											<div class="col-sm-5 col-lg-3 controls">
												<input disabled type="text" name="cat_name" id="cat_name"
													value="${planHeader.catName}" class="form-control" required />
											</div>

											<label class="col-sm-3 col-lg-2 control-label">Time
												Slot</label>
											<div class="col-sm-5 col-lg-3 controls">
												<input disabled type="text" value="${planHeader.timeSlot}"
													name="time_slot" class="form-control" />
											</div>

										</div>
										<c:choose>
											<c:when test="${planHeader.productionStatus==1}">
												<c:set var="sts" value="Planning"></c:set>

											</c:when>
											<c:when test="${planHeader.productionStatus==2}">
												<c:set var="sts" value="Added From Order"></c:set>

											</c:when>
											<c:when test="${planHeader.productionStatus==3}">
												<c:set var="sts" value="Production Started"></c:set>

											</c:when>
											<c:when test="${planHeader.productionStatus==4}">
												<c:set var="sts" value="Production Completed"></c:set>

											</c:when>
											<c:when test="${planHeader.productionStatus==5}">
												<c:set var="sts" value="Closed"></c:set>

											</c:when>
											<c:otherwise>
												<c:out value=""></c:out>

											</c:otherwise>

										</c:choose>
										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Status
											</label>
											<div class="col-sm-5 col-lg-3 controls">
												<input disabled type="text" name="status" id="status"
													value="${sts}" class="form-control" />
											</div>


											<div class="col-sm-5 col-lg-3 controls">

												<input type="button" value="PDF" class="btn btn-primary"
													onclick="genPdf()" />
											</div>
										</div>
										<div class="clearfix"></div>
										<div id="table-scroll" class="table-scroll">

											<div id="faux-table" class="faux-table" aria="hidden" >
												<table id="table2" class="main-table">
													<thead>
														<tr class="bgpink">
															<!-- 		<th width="150">Item Name</th>
															<th width="110" align="left">Current Stock</th>
															<th width="110" align="left">Opening Qty</th>
															<th width="120" align="left">Plan Qty</th>
															<th width="120" align="left">Order Qty</th>

															<th width="150" align="left">Actual Prod</th>
															<th width="120" align="left">Rej Qty</th>
															<th width="150" align="left">Total Qty</th>
															not in use -->


															<th class="col-md-3">Item Name</th>
															<th class="col-md-2">Current Stock</th>
															<th class="col-md-2">Opening Qty</th>
															<th class="col-md-1">Plan Qty</th>
															<th class="col-md-1">Order Qty</th>

															<th class="col-md-2">Actual Prod</th>
															<th class="col-md-1">Rej Qty</th>
															<th class="col-md-2">Total Qty</th>
															<!-- not in use -->

														</tr>
													</thead>
												</table>

											</div>
											<div class="table-wrap">

												<table id="table1" class="table table-advance">
													<thead>
														<tr class="bgpink">
															<th class="col-md-3">Item Name</th>
															<th class="col-md-2">Current Stock</th>
															<th class="col-md-2">Opening Qty</th>
															<th class="col-md-1">Plan Qty</th>
															<th class="col-md-1">Order Qty</th>

															<th class="col-md-2">Actual Prod</th>
															<th class="col-md-1">Rej Qty</th>
															<th class="col-md-2">Total Qty</th>
															<!-- not in use -->

														</tr>
													</thead>
													<!-- 	<div class="table-responsive" style="border: 0">
									<table width="100%" class="table table-advance" id="table1">
										<thead>
											<tr>
												<th width="170" >Item Name</th>
												<th width="120" align="left">Current Stock</th>
												<th width="120" align="left">Opening Qty</th>
												<th width="120" align="left">Plan Qty</th>
												<th width="120" align="left">Order Qty</th>

												<th width="150" align="left">Actual Prod</th>
												<th width="120" style="width: 18px">Rej Qty</th>
												<th width="150" align="left">Total Qty</th> not in use

											</tr>
										</thead> -->
													<tbody>
														<c:forEach items="${planDetail}" var="planDetail"
															varStatus="count">
															<input type="hidden" name="item${count.index}"
																id="item${planDetail.productionDetailId}"
																value="${planDetail.itemId}" />
															<tr>
																<td class="col-md-3"><c:out
																		value="${planDetail.itemName}" /></td>

																<td class="col-md-2"><input align="left" type="text"
																	value="${planDetail.curOpeQty}" placeholder="Stock Qty"
																	class="form-control"
																	name="stk_qty${planDetail.productionDetailId}"
																	id="stk_qty${planDetail.productionDetailId}"
																	data-rule-required="true" style="width: 65px" /></td>
																<td class="col-md-2"><input align="left" type="text"
																	value="${planDetail.openingQty}"
																	placeholder="Opening Qty" class="form-control"
																	name="op_total${planDetail.productionDetailId}"
																	id="op_total${planDetail.productionDetailId}"
																	data-rule-required="true" style="width: 65px" /></td>

																<c:choose>

																	<c:when test="${planHeader.productionStatus==1}">
																		<td class="col-md-1"><input align="left" type="text"
																			name="plan_qty${planDetail.productionDetailId}"
																			id="plan_qty${planDetail.productionDetailId}"
																			placeholder="Plan Qty" class="form-control"
																			value="${planDetail.planQty}"
																			data-rule-required="true" style="width: 65px" /></td>

																	</c:when>
																	<c:otherwise>
																		<td class="col-md-1" ><input align="left" type="text"
																			name="plan_qty${planDetail.productionDetailId}"
																			id="plan_qty${planDetail.productionDetailId}"
																			placeholder="Plan Qty" class="form-control"
																			value="${planDetail.planQty}"
																			data-rule-required="true" style="width: 65px"
																			disabled /></td>
																	</c:otherwise>
																</c:choose>
																<c:choose>
																	<c:when test="${planHeader.productionStatus==2}">
																		<td class="col-md-1" "><input align="left" type="text"
																			name="order_qty${planDetail.productionDetailId}"
																			id="order_qty${planDetail.productionDetailId}"
																			placeholder="Order Qty" class="form-control"
																			value="${planDetail.orderQty}"
																			data-rule-required="true" style="width: 65px" /></td>
																	</c:when>
																	<c:otherwise>
																		<td class="col-md-1" ><input align="left" type="text"
																			name="order_qty${planDetail.productionDetailId}"
																			id="order_qty${planDetail.productionDetailId}"
																			placeholder="Order Qty" class="form-control"
																			value="${planDetail.orderQty}"
																			data-rule-required="true" style="width: 65px"
																			disabled /></td>
																	</c:otherwise>
																</c:choose>
																<c:choose>
																	<c:when test="${planHeader.productionStatus==3}">

																		<c:choose>
																			<c:when test="${planHeader.isPlanned==1}">
																				<td class="col-md-2" ><input align="left"
																					type="text"
																					name="act_prod_qty${planDetail.productionDetailId}"
																					id="act_prod_qty${planDetail.productionDetailId}"
																					placeholder="Actual Prod" class="form-control"
																					value="${planDetail.planQty}"
																					data-rule-required="true" style="width: 65px"
																					onkeypress="return IsNumeric(event);"
																					ondrop="return false;" onpaste="return false;"
																					onkeyup="changeQty(${planDetail.productionDetailId})" /></td>
																			</c:when>
																			<c:when test="${planHeader.isPlanned==0}">
																				<td class="col-md-2" ><input align="left"
																					type="text"
																					name="act_prod_qty${planDetail.productionDetailId}"
																					id="act_prod_qty${planDetail.productionDetailId}"
																					placeholder="Actual Prod" class="form-control"
																					value="${planDetail.orderQty}"
																					data-rule-required="true" style="width: 65px"
																					onkeypress="return IsNumeric(event);"
																					ondrop="return false;" onpaste="return false;"
																					onkeyup="changeQty(${planDetail.productionDetailId})" /></td>
																			</c:when>
																		</c:choose>
																	</c:when>


																	<c:otherwise>
																		<td class="col-md-2" ><input align="left" type="text"
																			name="act_prod_qty${planDetail.productionDetailId}"
																			id="act_prod_qty${planDetail.productionDetailId}"
																			placeholder="Actual Prod" class="form-control"
																			value="${planDetail.productionQty}"
																			data-rule-required="true" style="width: 65px"
																			disabled /></td>
																	</c:otherwise>
																</c:choose>

																<c:choose>
																	<c:when test="${planHeader.productionStatus==3}">
																		<td class="col-md-2"><input align="left" type="text"
																			name="rej_qty${planDetail.productionDetailId}"
																			id="rej_qty${planDetail.productionDetailId}"
																			placeholder="Rejected Qty" class="form-control"
																			value="0" data-rule-required="true"
																			style="width: 65px"
																			onkeyup="changeQty(${planDetail.productionDetailId})" /></td>
																	</c:when>
																	<c:otherwise>
																		<td class="col-md-2"><input align="left" type="text"
																			name="rej_qty${planDetail.productionDetailId}"
																			id="rej_qty${planDetail.productionDetailId}"
																			placeholder="Rejected Qty" class="form-control"
																			value="${planDetail.rejectedQty}"
																			data-rule-required="true" style="width: 65px"
																			disabled
																			onkeyup="changeQty(${planDetail.productionDetailId})" /></td>
																	</c:otherwise>
																</c:choose>

																<c:choose>
																	<c:when test="${planHeader.isPlanned==0}">
																		<td class="col-md-2"><input align="left" type="text"
																			name="total_qty${planDetail.productionDetailId}"
																			id="total_qty${planDetail.productionDetailId}"
																			placeholder="Total Qty" class="form-control"
																			value="${planDetail.openingQty+planDetail.orderQty}"
																			data-rule-required="true" style="width: 65px"
																			disabled /></td>
																	</c:when>
																	<c:when test="${planHeader.isPlanned==1}">
																		<td class="col-md-2"><input align="left" type="text"
																			name="total_qty${planDetail.productionDetailId}"
																			id="total_qty${planDetail.productionDetailId}"
																			placeholder="Total Qty" class="form-control"
																			value="${planDetail.openingQty+planDetail.planQty}"
																			data-rule-required="true" style="width: 65px"
																			disabled /></td>
																	</c:when>
																</c:choose>



																<%-- <td align="left"><a
														href="viewDetails/${planHeader.productionHeaderId}"><span
															class="glyphicon glyphicon-info-sign"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;

													</td> --%>

																<c:if test="${count.last}">
																	<input type="hidden" name="cnt" id="cnt"
																		value="${count.index}" />
																</c:if>
															</tr>
														</c:forEach>

													</tbody>
												</table>
											</div>

											<br>



											<div class="form-group">


												<div
													class="col-sm-1 col-sm-offset-1 col-lg-9 col-lg-offset-1">
													<c:choose>
														<c:when test="${planHeader.isBom==0}">
															<a
																href="${pageContext.request.contextPath}/showBom/${planHeader.productionHeaderId}/1/${planHeader.productionDate}/${planHeader.isPlanned}/${planHeader.catId}">
																<button type="button" class="btn btn-primary">
																	<i class="fa fa-check"></i> Req.To BOM
																</button>
															</a>
														</c:when>

														<c:when test="${planHeader.isBom==1}">
															<button type="button" class="btn btn-primary"
																disabled="disabled">
																<i class="fa fa-check"></i> Req. BOM
															</button>
														</c:when>
														<c:otherwise>
														</c:otherwise>
													</c:choose>
													&nbsp;&nbsp;
													<c:choose>

														<c:when
															test="${planHeader.isMixing==0 && planHeader.productionStatus==1 or planHeader.productionStatus==2 or planHeader.productionStatus==3}">
															<a href="${pageContext.request.contextPath}/addMixing">
																<button type="button" class="btn btn-primary">
																	<i class="fa fa-check"></i> Req. Mixing
																</button>
															</a>

														</c:when>


														<c:when
															test="${planHeader.productionStatus==4 or planHeader.productionStatus==5}">
															<button type="button" class="btn btn-primary"
																disabled="disabled">
																<i class="fa fa-check"></i> Req. Mixing
															</button>

														</c:when>
														<c:when test="${planHeader.isMixing==1}">
															<button type="button" class="btn btn-primary"
																disabled="disabled">
																<i class="fa fa-check"></i> Req. Mixing
															</button>

														</c:when>
														<c:otherwise>

														</c:otherwise>
													</c:choose>
													<input type="hidden" name="productionStatus"
														id="productionStatus"
														value="${planHeader.productionStatus}" /> &nbsp;&nbsp;
													<c:choose>

														<c:when test="${planHeader.productionStatus==1}">
															<button type="submit" class="btn btn-primary"
																id="editPlanQty">
																<i class="fa fa-check"></i> Edit Plan Qty
															</button>

														</c:when>

														<c:when test="${planHeader.productionStatus==2}">
															<button type="submit" class="btn btn-primary"
																id="editOrderQty">
																<i class="fa fa-check"></i> Order Plan Qty
															</button>
														</c:when>

														<c:otherwise>

														</c:otherwise>
													</c:choose>
													&nbsp;&nbsp;
													<c:choose>

														<c:when test="${planHeader.productionStatus==4}">
															<button type="button" class="btn btn-primary"
																disabled="disabled">
																<i class="fa fa-check"></i>Complete Production
															</button>
														</c:when>

														<c:when test="${planHeader.productionStatus==5}">
															<button type="button" class="btn btn-primary"
																disabled="disabled">
																<i class="fa fa-check"></i>Complete Production
															</button>
														</c:when>
														<c:otherwise>
															<button type="button" class="btn btn-primary"
																id="complete_prod">
																<i class="fa fa-check"></i>Complete Production
															</button>
														</c:otherwise>
													</c:choose>
													<c:choose>

														<c:when test="${planHeader.isBom==1}">
															<button type="button" class="btn btn-primary"
																id="man_bom_button" onclick="goToManBom()">
																<i class="fa fa-check"></i>Manual Bom
															</button>
														</c:when>

													</c:choose>
													<c:choose>
														<c:when test="${planHeader.isMixing==1}">
															<a
																href="${pageContext.request.contextPath}/manualMixing/${planHeader.productionHeaderId}/${planHeader.productionDate}/${planHeader.timeSlot}"><input
																type="button" class="btn btn-primary"
																id="man_bom_button" value="Spot Mixing"> </a>
														</c:when>
													</c:choose>
													 <input type="button" id="expExcel" class="btn btn-primary" value="EXPORT TO Excel" onclick="exportToExcel();" >
												</div>

												<!-- 
							<select name="prodUnit" id="prodUnit">
							<option value="1">Shendra</option>
							<option value="2">Chikalthana</option>
							</select> -->

												<div class="form-group">
													<label class="col-sm-3 col-lg-3 control-label">Select
														Unit</label> <br></br>
													<div class="col-sm-5 col-lg-3 controls">

														<select data-placeholder="Choose Franchisee"
															class="form-control chosen" tabindex="6" id="prodUnit"
															name="prodUnit">
															<option value="1">Shendra</option>
															<option value="2">Chikalthana</option>

														</select>
													</div>



												</div>

											</div>

										</div>
									</form>
								</div>
							</div>

						</div>
					</c:otherwise>
				</c:choose>
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

function genPdf(){
	window.open('${pageContext.request.contextPath}/showProdByOrderPdf/');
}

</script>

	<script type="text/javascript">



$('#man_bom_button').click(function(){
    var form = document.getElementById("validation_form")
    form.action ="${pageContext.request.contextPath}/goToManualBom";
    form.submit();
});

var specialKeys = new Array();
specialKeys.push(8); //Backspace
function IsNumeric(e) {
    var keyCode = e.which ? e.which : e.keyCode
    var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1);
    //document.getElementById("error").style.display = ret ? "none" : "inline";
    return ret;
}

function changeQty(id)
{
	var opQty=parseFloat(document.getElementById("op_total"+id).value); 
	var actQty=parseFloat(document.getElementById("act_prod_qty"+id).value);
	var rejQty=parseFloat(document.getElementById("rej_qty"+id).value);
	/* alert(opQty);
	alert(actQty);
	alert(rejQty); */
	var total = opQty+actQty-rejQty;
 document.getElementById("total_qty"+id).value=total;
 /* alert(total); */
	
	}

</script>
	<script type="text/javascript">

$('#complete_prod').click(function(){
	document.getElementById("prodUnit").style.display="block";
	var prodUnit=document.getElementById("prodUnit").value;
	alert(prodUnit);
	
    var form = document.getElementById("validation_form")
   form.action ="${pageContext.request.contextPath}/completeProd";
    form.submit();
});

function exportToExcel()
{
	 
	window.open("${pageContext.request.contextPath}/exportToExcel");
			document.getElementById("expExcel").disabled=true;
}
</script>
</body>
</html>