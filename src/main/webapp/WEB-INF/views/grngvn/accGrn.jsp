<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.re-order {
    background: #f4353f !important;
}
</style>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body onload="placeValue()">

	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
	<c:url var="insertGateGrnProcessAgree"
		value="/insertGateGrnProcessAgree" />

	<c:url var="insertAccGrnProcessAgree" value="/insertAccGrnProcessAgree" />

	<c:url var="insertAccGrnProcessDisAgree"
		value="/insertAccGrnProcessDisAgree" />
		
		
		<c:url var="getStatus"
		value="/getStatus" />
		

	<c:url var="getDateForGrnAcc" value="/getDateForGrnAcc" />

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
						<i class="fa fa-file-o"></i>Account GRN
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
								<i class="fa fa-bars"></i> GRN for Account
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

							<form
								action="${pageContext.request.contextPath}/insertAccGrnByCheckBoxes"
								class="form-horizontal" method="post" id="validation-form">

								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i> GRN List  Date-${grnDate} SrNo- ${srNo}
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
										</div>
									</div>
									<c:set var="sts" value="${0}"></c:set>
									<c:forEach items="${grnList}" var="grnList">
												<c:choose>
														<c:when test="${(grnList.grnGvnStatus==1) or (grnList.grnGvnStatus==3) or (grnList.grnGvnStatus==6) }">
														<c:set var="sts" value="${1}"></c:set>
														</c:when>
												</c:choose>
									</c:forEach>

									<div class="box-content">

										<div class="clearfix"></div>
										<div class="table-responsive" style="border: 0">
											<table width="100%"
												class="table table-advance table-responsive table-position"
												id="table1">
												<thead>
													<tr>
													<c:choose>
														<c:when test="${sts==1}">
														 <th><input type="checkbox" onClick="selectedGrn(this)" disabled/>Select All<br/></th>
														</c:when>
														<c:otherwise>
														<th><input type="checkbox"
													onClick="selectedGrn(this)" />Select All<br/></th>
														</c:otherwise>
												</c:choose>
													
														
														<th>Sr No</th>
														<th>Invoice No</th>
														<th>Franchise Name</th>
														<th>Item Name</th>
														<th>GRN Type</th>
														<th>Quantity</th>
														<th>Dispatch Qty</th>
														<th>Acc Edit</th>
														<th>Status</th>

														<th>GRN Amount</th>

														<th>Action</th>
													</tr>



												</thead>
												<tbody>
													<c:forEach items="${grnList}" var="grnList"
														varStatus="count">

														<c:choose>
															<c:when
																test="${grnList.grnGvnQty!=grnList.grnGvnQtyAuto}">

																<c:set var="color" value="red"></c:set>
															</c:when>
															
															<c:when
																test="${grnList.grnGvnQty!=grnList.aprQtyGate}">

																<c:set var="color" value="blue"></c:set>
															</c:when>
															
															<c:otherwise>
																<c:set var="color" value="white"></c:set>
															</c:otherwise>
														</c:choose>

														<tr bgcolor="${color}">
														
															<c:choose>
																<c:when test="${grnList.grnGvnStatus==2}">
																	<td><input type="checkbox" name="select_to_agree"
																		id="${grnList.grnGvnId}" value="${grnList.grnGvnId}"></></td>

																</c:when>
																<c:when test="${grnList.grnGvnStatus==3}">
																	<td><input type="checkbox" name="select_to_agree"
																		disabled="disabled" id="${grnList.grnGvnId}"
																		value="${grnList.grnGvnId}"></></td>

																</c:when>

																<c:when test="${grnList.grnGvnStatus==1}">
																	<td><input type="checkbox" name="select_to_agree"
																		disabled="disabled" id="${grnList.grnGvnId}"
																		value="${grnList.grnGvnId}"></></td>

																</c:when>


																<c:when test="${grnList.grnGvnStatus==6}">
																	<td><input type="checkbox" name="select_to_agree"
																		disabled="disabled" id="${grnList.grnGvnId}"
																		value="${grnList.grnGvnId}"></></td>

																</c:when>

																<c:when test="${grnList.grnGvnStatus==4}">
																	<td><input type="checkbox" name="select_to_agree"
																		disabled="disabled" id="${grnList.grnGvnId}"
																		value="${grnList.grnGvnId}"></></td>


																</c:when>
																<c:when test="${grnList.grnGvnStatus==5}">
																	<td><input type="checkbox" name="select_to_agree"
																		disabled="disabled" id="${grnList.grnGvnId}"
																		value="${grnList.grnGvnId}"></></td>


																</c:when>

																<c:when test="${grnList.grnGvnStatus==7}">
																	<td><input type="checkbox" name="select_to_agree"
																		id="${grnList.grnGvnId}" value="${grnList.grnGvnId}"></></td>


																</c:when>


																<c:otherwise>

																	<td><input type="checkbox" name="select_to_agree"
																		disabled="disabled" id="${grnList.grnGvnId}"
																		value="${grnList.grnGvnId}" /></td>


																</c:otherwise>
															</c:choose>
															<td><c:out value="${count.index+1}" /></td>

															<td align="left"><c:out value="${grnList.invoiceNo}" /></td>

															<td align="left"><c:out value="${grnList.frName}" /></td>


															<td align="left"><c:out value="${grnList.itemName}" /></td>

															<c:choose>
																<c:when test="${grnList.grnType==0}">
																	<td align="left"><c:out value="GRN 1"></c:out></td>

																</c:when>


																<c:when test="${grnList.grnType==1}">
																	<td align="left"><c:out value="GRN 2"></c:out></td>

																</c:when>


																<c:when test="${grnList.grnType==2}">
																	<td align="left"><c:out value="GRN 3"></c:out></td>

																</c:when>

																<c:when test="${grnList.grnType==4}">
																	<td align="left"><c:out value="GRN 3"></c:out></td>

																</c:when>

															</c:choose>


															<td align="left"><c:out value="${grnList.grnGvnQty}" />
																<input type="hidden"
																name="approve_acc_login${grnList.grnGvnId}"
																id="approve_acc_login${grnList.grnGvnId}"
																value="${grnList.approvedLoginAcc}" /></td>

															<td align="left"><c:out
																	value="${grnList.aprQtyGate}"></c:out></td>
																	
																	<c:set var ="qty" value="0" ></c:set>
															<c:choose>
																<c:when test="${grnList.grnGvnStatus==1 or grnList.grnGvnStatus==2 or grnList.grnGvnStatus==3}">
																<c:set var ="qty" value="${grnList.aprQtyGate}" />
																
																</c:when>
																<c:otherwise>
																
																<c:set var ="qty" value="${grnList.aprQtyAcc}" ></c:set>
																
																</c:otherwise>

															</c:choose>


															<td align="center"><input type="text"
																name="acc_grn_qty${grnList.grnGvnId}"
																class="form-control" id='acc_grn_qty${grnList.grnGvnId}'
																value="${qty}"
																onkeyup="calcGrn(${grnList.grnType},${grnList.baseRate},${grnList.grnGvnId},
																	${grnList.sgstPer},${grnList.cgstPer},${grnList.grnGvnQty},${qty})"/></td>



															<c:choose>
																<c:when test="${grnList.grnGvnStatus==1}">
																	<td align="left"><c:out value="Pending"></c:out></td>

																</c:when>

																<c:when test="${grnList.grnGvnStatus==2}">
																	<td align="left"><c:out value="Approved From Dispatch"></c:out></td>

																</c:when>

																<c:when test="${grnList.grnGvnStatus==3}">
																	<td align="left"><c:out value="Reject From Dispatch"></c:out></td>

																</c:when>

																<c:when test="${grnList.grnGvnStatus==4}">
																	<td align="left"><c:out value="Approved From Sales"></c:out></td>

																</c:when>

																<c:when test="${grnList.grnGvnStatus==5}">
																	<td align="left"><c:out value="Reject From Sales"></c:out></td>

																</c:when>

																<c:when test="${grnList.grnGvnStatus==6}">
																	<td align="left"><c:out value="Approved From Account"></c:out></td>

																</c:when>

																<c:when test="${grnList.grnGvnStatus==7}">
																	<td align="left"><c:out value="Reject From Account"></c:out></td>

																</c:when>

															</c:choose>

															<td id='grnAmt${grnList.grnGvnId}' align="left"><c:out
																	value="${grnList.grnGvnAmt}"></c:out></td>

															<c:choose>
																<c:when test="${grnList.grnGvnStatus==2}">

																	<td>
																		<ul class="table-menu">

																			<li><a href="" id="callSubmit"
																				onclick="insertGrnCall(${grnList.grnGvnId})"><i
																					class="fa fa-check"></i></a></li>
																			<li>

																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#"
																						data-toggle="dropdown"><i class="fa fa-times"></i></a>
																					<div class="dropdown-menu ">
																						<div class="form">

																							<select name="acc_remark${grnList.grnGvnId}"
																								id="acc_remark${grnList.grnGvnId}"
																								class="form-control">
																								<c:forEach items="${remarkList}"
																									var="remarkList">
																									<option value="${remarkList.remark}">${remarkList.remark}</option>
																								</c:forEach>
																							</select> <input class="btn btn-primary" value="Submit"
																								class="form-control"
																								onclick="insertGrnDisAgree(${grnList.grnGvnId})" />
																						</div>
																					</div>
																				</div>
																			</li>

																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#"
																						data-toggle="dropdown"><i
																						class="fa fa-info-circle"></i></a>

																					<div class="dropdown-menu">
																						<div class="form">
																							Franchisee Remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control" ${grnList.frGrnGvnRemark}></textarea>
																							Gate remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control">${grnList.approvedRemarkGate}</textarea>
																							Account remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control">${grnList.approvedRemarkAcc}</textarea>

																						</div>
																					</div>
																				</div>
																			</li>
																		</ul>
																	</td>

																</c:when>


																<c:when test="${grnList.grnGvnStatus==3}">

																	<td>
																		<ul class="table-menu">

																			<li><a href="" id="callSubmit"
																				class="disableClick"
																				onclick="insertGrnCall(${grnList.grnGvnId})"><i
																					class="fa fa-check"></i></a></li>
																			<li>

																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#" id="disableMe"
																						data-toggle="dropdown"><i class="fa fa-times"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">

																							<select name="acc_remark${grnList.grnGvnId}"
																								id="acc_remark${grnList.grnGvnId}"
																								class="form-control">
																								<c:forEach items="${remarkList}"
																									var="remarkList">
																									<option value="${remarkList.remark}">${remarkList.remark}</option>
																								</c:forEach>
																							</select> </br> <input class="btn btn-primary" value="Submit"
																								disabled="disabled"
																								onclick="insertGrnDisAgree(${grnList.grnGvnId})" />
																						</div>
																					</div>
																				</div>
																			</li>

																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#"
																						data-toggle="dropdown"><i
																						class="fa fa-info-circle"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">
																							Franchisee Remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control" ${grnList.frGrnGvnRemark}></textarea>
																							Gate remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control">${grnList.approvedRemarkGate}</textarea>
																							Account remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control">${grnList.approvedRemarkAcc}</textarea>

																						</div>
																					</div>
																				</div>
																			</li>
																		</ul>
																	</td>

																</c:when>


																<c:when test="${grnList.grnGvnStatus==6}">

																	<td>
																		<ul class="table-menu">

																			<li><a href="" id="callSubmit"
																				class="disableClick"
																				onclick="insertGrnCall(${grnList.grnGvnId})"><i
																					class="fa fa-check"></i></a></li>
																			<li>

																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#"
																						data-toggle="dropdown"><i class="fa fa-times"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">

																							<select name="acc_remark${grnList.grnGvnId}"
																								id="acc_remark${grnList.grnGvnId}"
																								class="form-control">
																								<c:forEach items="${remarkList}"
																									var="remarkList">
																									<option value="${remarkList.remark}">${remarkList.remark}</option>
																								</c:forEach>
																							</select><input class="btn btn-primary" value="Submit"
																								onclick="insertGrnDisAgree(${grnList.grnGvnId})" />
																						</div>
																					</div>
																				</div>
																			</li>

																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#"
																						data-toggle="dropdown"><i
																						class="fa fa-info-circle"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">
																							Franchisee Remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control" ${grnList.frGrnGvnRemark}></textarea>
																							Gate remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control">${grnList.approvedRemarkGate}</textarea>
																							Account remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control">${grnList.approvedRemarkAcc}</textarea>

																						</div>
																					</div>
																				</div>
																			</li>
																		</ul>
																	</td>

																</c:when>
																<c:when test="${grnList.grnGvnStatus==7}">

																	<td>
																		<ul class="table-menu">

																			<li><a href="" id="callSubmit"
																				onclick="insertGrnCall(${grnList.grnGvnId})"><i
																					class="fa fa-check"></i></a></li>
																			<li>

																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#" id="disableMe"
																						data-toggle="dropdown"><i class="fa fa-times"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">

																							<select name="acc_remark${grnList.grnGvnId}"
																								id="acc_remark${grnList.grnGvnId}"
																								class="form-control">
																								<c:forEach items="${remarkList}"
																									var="remarkList">
																									<option value="${remarkList.remark}">${remarkList.remark}</option>
																								</c:forEach>
																							</select><input class="btn btn-primary" value="Submit"
																								disabled="disabled"
																								onclick="insertGrnDisAgree(${grnList.grnGvnId})" />
																						</div>
																					</div>
																				</div>
																			</li>

																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#"
																						data-toggle="dropdown"><i
																						class="fa fa-info-circle"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">
																							Franchisee Remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control" ${grnList.frGrnGvnRemark}></textarea>
																							Gate remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control">${grnList.approvedRemarkGate}</textarea>
																							Account remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control">${grnList.approvedRemarkAcc}</textarea>

																						</div>
																					</div>
																				</div>
																			</li>
																		</ul>
																	</td>

																</c:when>


																<c:otherwise>

																	<td><ul class="table-menu">

																			<li><a href="" id="callSubmit"
																				class="disableClick"
																				onclick="insertGrnCall(${grnList.grnGvnId})"><i
																					class="fa fa-check"></i></a></li>
																			<li>

																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#" id="disableMe"
																						data-toggle="dropdown"><i class="fa fa-times"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">

																							<select name="acc_remark${grnList.grnGvnId}"
																								id="acc_remark${grnList.grnGvnId}"
																								class="form-control">
																								<c:forEach items="${remarkList}"
																									var="remarkList">
																									<option value="${remarkList.remark}">${remarkList.remark}</option>
																								</c:forEach>
																							</select><input class="btn btn-primary" value="Submit"
																								disabled="disabled"
																								onclick="insertGrnDisAgree(${grnList.grnGvnId})" />
																						</div>
																					</div>
																				</div>
																			</li>

																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#"
																						data-toggle="dropdown"><i
																						class="fa fa-info-circle"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">
																							Franchisee Remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control" ${grnList.frGrnGvnRemark}></textarea>
																							Gate remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control">${grnList.approvedRemarkGate}</textarea>
																							Account remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control">${grnList.approvedRemarkAcc}</textarea>

																						</div>
																					</div>
																				</div>
																			</li>
																		</ul></td>
																</c:otherwise>
															</c:choose>

														</tr>

													</c:forEach>

												</tbody>

											</table>
										</div>

										<!-- this is for ajax call<input type="submit" class="btn btn-primary" value="Submit"
										id="callSubmit" onclick="callSubmitGrn(); getGrnId();"> -->


										<div
											class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
											<input type="button" value="Submit" onclick="callSubmit()"
												class="btn btn-primary">

										</div>
										<!-- </form> -->

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

	<!-- insertGrnDisAgree -->

	<script type="text/javascript">

function callSubmit(){
		//	alert("HIII");
			
		    var form = document.getElementById("validation-form");
		    form.action ="${pageContext.request.contextPath}/insertAccGrnByCheckBoxes";
		    form.submit();
		}
</script>
	<script type="text/javascript">



function insertGrnDisAgree(grnGvnId){

var grnId=grnGvnId;
var approve_acc_login=$("#approve_acc_login"+grnGvnId).val();
var acc_remark=$("#acc_remark"+grnGvnId).val();

var acc_grn_qty=$("#acc_grn_qty"+grnGvnId).val();


if($("#acc_remark"+grnGvnId).val() == ''){
	alert("Please Enter Grn Remark!");
}
else{
	$.getJSON('${insertAccGrnProcessDisAgree}',
			{
			
			grnId : grnId,
			approveAccLogin : approve_acc_login,
			accRemark : acc_remark,	
			acc_grn_qty:acc_grn_qty,
				ajax : 'true',

			}
);
	
callRefreshDisAgree();
}
}


</script>

	<script type="text/javascript">
function callRefreshDisAgree(){
	
		alert("DisApproved Successfully");
		window.location.reload();
	//document.getElementById("validation-form").reload();
	}

</script>



	<!-- insertGrnDisAgree -->


	<!-- insertGrnAgree -->

	<script type="text/javascript">


function insertGrnCall(grnGvnId){
//alert("second function called ");
var grnId=grnGvnId;
var approve_acc_login=$("#approve_acc_login"+grnGvnId).val();
var acc_remark=$("#acc_remark"+grnGvnId).val();
var acc_grn_qty=$("#acc_grn_qty"+grnGvnId).val();

	$.getJSON('${insertAccGrnProcessAgree}',
							{
							
							grnId : grnId,
							approveAccLogin:approve_acc_login,
							acc_grn_qty:acc_grn_qty,
								
								ajax : 'true',
							
	 complete: function() {
	       //alert("ajax completed");
	       
 	  }
}
);

callRefresh();

}


</script>

	<script type="text/javascript">
function callRefresh(){
	alert("Approved Successfully");
		window.location.reload();
	}

</script>

	<script type="text/javascript">

function callSecondRefresh(){

	window.location.reload();
}


</script>

	<script type="text/javascript">
function callThirdRefresh(){
	window.location.reload();
}
</script>

	<script type="text/javascript">

function callfourthRefresh(){
	window.reload();
}

</script>
	<!-- insertGrnAgree -->

	<script>
var acc = document.getElementsByClassName("accordion");
var i;

for (i = 0; i < acc.length; i++) {
  acc[i].onclick = function() {
    this.classList.toggle("active");
    var panel = this.nextElementSibling;
    if (panel.style.maxHeight){
      panel.style.maxHeight = null;
    } else {
      panel.style.maxHeight = panel.scrollHeight + "px";
    } 
  }
}
</script>

	<script type="text/javascript">

function getDate(){
	
	var fromDate=$("#from_date").val();
	var toDate=$("#to_date").val();
	$.getJSON('${getDateForGrnAcc}',
			{
			
			fromDate : fromDate,
			toDate:toDate,
				
				ajax : 'true',
}
);
}

</script>
	<script>
	$(document).ready(function(){
	    //Handles menu drop down
	    $('.dropdown-menu').find('.form').click(function (e) {
	        e.stopPropagation();
	    });
	});
</script>

	<script>
function calcGrn(grnType,baseRate,grnId,sgstPer,cgstPer,grnQty,curQty){
	
	var grandTotal;
	var aprTotalTax;
	var grnRate;
	var aprTaxableAmt;
	
	checkQty(grnId,grnQty,curQty);//Calling another function 
	var acc_grn_qty=$("#acc_grn_qty"+grnId).val();

	if(grnType==0){
		grnRate=baseRate*75/100;
	}
	
	if(grnType==1){
		grnRate=baseRate*90/100;
	}
	
	if(grnType==2 || grnType==4){
		grnRate=baseRate;
	}
	
	aprTaxableAmt = grnRate * acc_grn_qty;
	aprTotalTax = ((aprTaxableAmt) * (sgstPer + cgstPer))/ 100;
	grandTotal = aprTaxableAmt + aprTotalTax;
	document.getElementById('grnAmt'+grnId).innerText=grandTotal.toFixed(2);
}

</script>
<script type="text/javascript">
function checkQty(grnId,grnQty,qty){
	var entered=$("#acc_grn_qty"+grnId).val();
	if(entered>grnQty){
		alert("Can not Enter Qty Greater than Grn Qty ");
		document.getElementById("acc_grn_qty"+grnId).value=qty;
	}
}
</script>

<script>
	
		function selectedGrn(source) {
			checkboxes = document.getElementsByName('select_to_agree');
			$.getJSON('${getStatus}', {
				
				ajax : 'true'
			}, function(data) {
				//alert(data);
				//alert(data[0]);
				
				for(var i=0;i<data.length;i++){
					checkboxes[data[i]].checked = source.checked;
				}
				
			});	
				
		/* checkboxes = document.getElementsByName('select_to_agree');
			
			for (var i = 0, n = checkboxes.length; i < n; i++) {
				checkboxes[i].checked = source.checked;
				
			} */
			
			
		}
		
	</script>



</body>
</html>