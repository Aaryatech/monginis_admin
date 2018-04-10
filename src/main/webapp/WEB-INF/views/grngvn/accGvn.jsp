<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body>
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<c:url var="insertAccGvnProcessAgree" value="/insertAccGvnProcessAgree" />
	<c:url var="insertAccGvnProcessDisAgree"
		value="/insertAccGvnProcessDisAgree" />
	<c:url var="getDateForGvnAcc" value="/getDateForGvnAcc" />


	<c:url var="getGvnStatus" value="/getGvnStatus" />

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
						<i class="fa fa-file-o"></i>GVN for Acc
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
								<i class="fa fa-bars"></i> Account GVN List
							</h3>
							<div class="box-tool">
								<a href="">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>

						</div>


						<div class="box-content">
							<form
								action="${pageContext.request.contextPath}/insertAccGvnByCheckBoxes"
								class="form-horizontal" method="post" id="validation-form">
								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i> GVN List  GVN List Date-${grnDate} srNo-${srNo}
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
										</div>
									</div>
									<c:set var="sts" value="${0}"></c:set>
									<c:forEach items="${gvnList}" var="gvnList">
												<c:choose>
														<c:when test="${(gvnList.grnGvnStatus==1) or (gvnList.grnGvnStatus==2) or (gvnList.grnGvnStatus==3) or (gvnList.grnGvnStatus==5) or (gvnList.grnGvnStatus==6)}">
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
														 <th><input type="checkbox" onClick="selectedGvn(this)" disabled/>Select All<br/></th>
														</c:when>
														<c:otherwise>
														<th><input type="checkbox" onClick="selectedGvn(this)" />Select All</th>
														</c:otherwise>
												</c:choose>
														
														
														<th style="width: 18px" align="left">Sr No</th>
														<th >Bill No</th>
														<th >Franchise Name</th>
														<th >Item Name</th>
														<th >GVN Quantity</th>
														<th >Sell Apr Qty</th>

														<th>Edited Qty</th>
														<th>Gvn Amt</th>
														<th >PHOTO 1</th>
														<th >PHOTO 2</th>
														<th >Status</th>
														<th >Action</th>
													</tr>
												</thead>
												<tbody>

													<c:forEach items="${gvnList}" var="gvnList"
														varStatus="count">

														<c:choose>
															<c:when
																test="${gvnList.aprQtyGate!=gvnList.grnGvnQty or gvnList.aprQtyStore!=gvnList.aprQtyGate}">

																<c:set var="color" value="blue"></c:set>
															</c:when>
															<c:otherwise>
																<c:set var="color" value="white"></c:set>
															</c:otherwise>
														</c:choose>

														<tr bgcolor="${color}">


															<c:choose>
																<c:when test="${gvnList.grnGvnStatus==4}">
																	<td><input type="checkbox" name="select_to_agree"
																		id="${gvnList.grnGvnId}" value="${gvnList.grnGvnId}"></></td>

																</c:when>
																<c:when test="${gvnList.grnGvnStatus==7}">
																	<td><input type="checkbox" name="select_to_agree"
																		id="${gvnList.grnGvnId}" value="${gvnList.grnGvnId}"></></td>

																</c:when>

																<c:otherwise>
																	<td><input type="checkbox" name="select_to_agree"
																		disabled="disabled" id="${gvnList.grnGvnId}"
																		value="${gvnList.grnGvnId}" /></td>
																</c:otherwise>
															</c:choose>
															<td><c:out value="${count.index+1}" /></td>
															<td align="left"><c:out value="${gvnList.billNo}" /></td>
															<td align="left"><c:out value="${gvnList.frName}" /></td>
															<td align="left"><c:out value="${gvnList.itemName}" /></td>
															<td align="left"><c:out value="${gvnList.grnGvnQty}" />
																<input type="hidden"
																name="approve_acc_login${gvnList.grnGvnId}"
																id="approve_acc_login${gvnList.grnGvnId}"
																value="${gvnList.approvedLoginAcc}" /></td>

															<td align="left"><c:out
																	value="${gvnList.aprQtyStore}" /> <c:set var="qty"
																	value="0"></c:set> <c:choose>
																	<c:when
																		test="${gvnList.grnGvnStatus==1 or gvnList.grnGvnStatus==2 or gvnList.grnGvnStatus==3}">
																		<c:set var="qty" value="${gvnList.aprQtyGate}" />
																	</c:when>

																	<c:when
																		test="${gvnList.grnGvnStatus==4 or gvnList.grnGvnStatus==5}">
																		<c:set var="qty" value="${gvnList.aprQtyStore}" />
																	</c:when>

																	<c:otherwise>
																		<c:set var="qty" value="${gvnList.aprQtyAcc}"></c:set>
																	</c:otherwise>
																</c:choose>
															<td align="center"><input type="text"
																name="acc_gvn_qty${gvnList.grnGvnId}"
																style="width: 50px" class="form-control"
																onkeyup="calcGvn(${gvnList.baseRate},${gvnList.grnGvnId},
																	${gvnList.sgstPer},${gvnList.cgstPer},${gvnList.grnGvnQty},${qty})"
																id='acc_gvn_qty${gvnList.grnGvnId}' value="${qty}" /></td>

															<td id='gvnAmt${gvnList.grnGvnId}' align="left"><c:out
																	value="${gvnList.grnGvnAmt}"></c:out></td>

															<td><a href="${url}${gvnList.gvnPhotoUpload1}"
																data-lightbox="image-1">Image 1</a></td>

															<td><a href="${url}${gvnList.gvnPhotoUpload2}"
																data-lightbox="image-1">Image 2</a></td>

															<c:choose>
																<c:when test="${gvnList.grnGvnStatus==1}">
																	<td align="left"><c:out value="Pending"></c:out></td>

																</c:when>

																<c:when test="${gvnList.grnGvnStatus==2}">
																	<td align="left"><c:out
																			value="Approved From Dispatch"></c:out></td>

																</c:when>

																<c:when test="${gvnList.grnGvnStatus==3}">
																	<td align="left"><c:out
																			value="Reject From Dispatch"></c:out></td>

																</c:when>

																<c:when test="${gvnList.grnGvnStatus==4}">
																	<td align="left"><c:out
																			value="Approved From Saless"></c:out></td>

																</c:when>

																<c:when test="${gvnList.grnGvnStatus==5}">
																	<td align="left"><c:out value="Reject From Sales"></c:out></td>

																</c:when>

																<c:when test="${gvnList.grnGvnStatus==6}">
																	<td align="left"><c:out
																			value="Approved From Account"></c:out></td>

																</c:when>

																<c:when test="${gvnList.grnGvnStatus==7}">
																	<td align="left"><c:out
																			value="Reject From Account"></c:out></td>

																</c:when>

															</c:choose>
															<c:choose>
																<c:when test="${gvnList.grnGvnStatus==4}">

																	<td>

																		<ul class="table-menu">

																			<li><a href="" id="callSubmit"
																				onclick="insertGrnCall(${gvnList.grnGvnId})"> <i
																					class="fa fa-check"></i></a></li>

																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#"
																						data-toggle="dropdown"><i class="fa fa-times"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">
																							<select name="acc_remark${gvnList.grnGvnId}"
																								id="acc_remark${gvnList.grnGvnId}"
																								class="form-control">
																								<c:forEach items="${remarkList}"
																									var="remarkList">
																									<option value="${remarkList.remark}">${remarkList.remark}</option>
																								</c:forEach>
																							</select> </br> <input class="btn btn-primary" value="Submit"
																								onclick="insertGrnDisAgree(${gvnList.grnGvnId})" />

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
																								class="form-control"><c:out value="${gvnList.frGrnGvnRemark}"></c:out></textarea>

																						</div>
																					</div>
																				</div>
																			</li>

																		</ul>

																	</td>

																</c:when>



																<c:when test="${gvnList.grnGvnStatus==6}">

																	<td>
																		<ul class="table-menu">

																			<li><a href="" id="callSubmit"
																				class="disableClick"
																				onclick="insertGrnCall(${gvnList.grnGvnId})"> <i
																					class="fa fa-check"></i></a></li>

																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#"
																						data-toggle="dropdown"><i class="fa fa-times"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">
																							<select name="acc_remark${gvnList.grnGvnId}"
																								id="acc_remark${gvnList.grnGvnId}"
																								class="form-control">
																								<c:forEach items="${remarkList}"
																									var="remarkList">
																									<option value="${remarkList.remark}">${remarkList.remark}</option>
																								</c:forEach>
																							</select> </br> <input class="btn btn-primary" value="Submit"
																								onclick="insertGrnDisAgree(${gvnList.grnGvnId})" />

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
																								class="form-control"><c:out value="${gvnList.frGrnGvnRemark}"></c:out></textarea>

																						</div>
																					</div>
																				</div>
																			</li>

																		</ul>

																	</td>

																</c:when>


																<c:when test="${gvnList.grnGvnStatus==7}">

																	<td>

																		<ul class="table-menu">

																			<li><a href="" id="callSubmit"
																				onclick="insertGrnCall(${gvnList.grnGvnId})"> <i
																					class="fa fa-check"></i></a></li>

																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#" id="disableMe"
																						data-toggle="dropdown"><i class="fa fa-times"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">
																							<select name="acc_remark${gvnList.grnGvnId}"
																								id="acc_remark${gvnList.grnGvnId}"
																								class="form-control">
																								<c:forEach items="${remarkList}"
																									var="remarkList">
																									<option value="${remarkList.remark}">${remarkList.remark}</option>
																								</c:forEach>
																							</select> </br> <input class="btn btn-primary" value="Submit"
																								onclick="insertGrnDisAgree(${gvnList.grnGvnId})" />

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
																								class="form-control"><c:out value="${gvnList.frGrnGvnRemark}"></c:out></textarea>

																							Account remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control">${gvnList.approvedRemarkAcc}</textarea>
																						</div>
																					</div>
																				</div>
																			</li>

																		</ul>
																	</td>

																</c:when>


																<c:otherwise>


																	<c:choose>

																		<c:when test="${gvnList.grnGvnStatus==3}">

																			<td>


																				<ul class="table-menu">

																					<li><a href="" id="callSubmit"
																						class="disableClick"
																						onclick="insertGrnCall(${gvnList.grnGvnId})">
																							<i class="fa fa-check"></i>
																					</a></li>

																					<li>
																						<div class="dropdown">
																							<a class="dropdown-toggle" href="#"
																								id="disableMe" data-toggle="dropdown"><i
																								class="fa fa-times"></i></a>
																							<div class="dropdown-menu">
																								<div class="form">
																									<select name="acc_remark${gvnList.grnGvnId}"
																										id="acc_remark${gvnList.grnGvnId}"
																										class="form-control">
																										<c:forEach items="${remarkList}"
																											var="remarkList">
																											<option value="${remarkList.remark}">${remarkList.remark}</option>
																										</c:forEach>
																									</select> </br> <input class="btn btn-primary" value="Submit"
																										onclick="insertGrnDisAgree(${gvnList.grnGvnId})" />

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
																										class="form-control"><c:out value="${gvnList.frGrnGvnRemark}"></c:out></textarea>

																									Dispatch remark
																									<textarea name="t1" readonly="readonly"
																										class="form-control">${gvnList.approvedRemarkGate}</textarea>
																								</div>
																							</div>
																						</div>
																					</li>

																				</ul>
																			</td>

																		</c:when>



																		<c:when test="${gvnList.grnGvnStatus==5}">

																			<td>


																				<ul class="table-menu">

																					<li><a href="" id="callSubmit"
																						class="disableClick"
																						onclick="insertGrnCall(${gvnList.grnGvnId})">
																							<i class="fa fa-check"></i>
																					</a></li>

																					<li>
																						<div class="dropdown">
																							<a class="dropdown-toggle" href="#"
																								id="disableMe" data-toggle="dropdown"><i
																								class="fa fa-times"></i></a>
																							<div class="dropdown-menu">
																								<div class="form">
																									<select name="acc_remark${gvnList.grnGvnId}"
																										id="acc_remark${gvnList.grnGvnId}"
																										class="form-control">
																										<c:forEach items="${remarkList}"
																											var="remarkList">
																											<option value="${remarkList.remark}">${remarkList.remark}</option>
																										</c:forEach>
																									</select> </br> <input class="btn btn-primary" value="Submit"
																										onclick="insertGrnDisAgree(${gvnList.grnGvnId})" />

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
																										class="form-control"><c:out value="${gvnList.frGrnGvnRemark}"></c:out></textarea>

																									Store remark
																									<textarea name="t1" readonly="readonly"
																										class="form-control">${gvnList.approvedRemarkStore}</textarea>
																								</div>
																							</div>
																						</div>
																					</li>

																				</ul>

																			</td>


																		</c:when>


																		<c:otherwise>

																			<td>

																				<ul class="table-menu">

																					<li><a href="" id="callSubmit"
																						class="disableClick"
																						onclick="insertGrnCall(${gvnList.grnGvnId})">
																							<i class="fa fa-check"></i>
																					</a></li>

																					<li>
																						<div class="dropdown">
																							<a class="dropdown-toggle" href="#"
																								id="disableMe" data-toggle="dropdown"><i
																								class="fa fa-times"></i></a>
																							<div class="dropdown-menu">
																								<div class="form">
																									<select name="acc_remark${gvnList.grnGvnId}"
																										id="acc_remark${gvnList.grnGvnId}"
																										class="form-control">
																										<c:forEach items="${remarkList}"
																											var="remarkList">
																											<option value="${remarkList.remark}">${remarkList.remark}</option>
																										</c:forEach>
																									</select> </br> <input class="btn btn-primary" value="Submit"
																										onclick="insertGrnDisAgree(${gvnList.grnGvnId})" />

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
																										class="form-control"><c:out value="${gvnList.frGrnGvnRemark}"></c:out></textarea>

																								</div>
																							</div>
																						</div>
																					</li>

																				</ul>

																			</td>


																		</c:otherwise>


																	</c:choose>


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
											<input type="submit" value="Submit" class="btn btn-primary">


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

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap/js/lightbox.js"></script>
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

function calcGvn(baseRate,grnId,sgstPer,cgstPer,gvnQty,curQty){
	
	
//alert("HII");
	
	var grandTotal;
	var aprTotalTax;
	
	var aprTaxableAmt;
	
	checkQty(grnId,gvnQty,curQty);//Calling another function 

	
	var gvnQty=$("#acc_gvn_qty"+grnId).val();
	//alert(gvnQty);
	
	//var gvnAmt=parseFloat(acc_gvn_qty)*parseFloat(baseRate);
	//alert(gvnAmt);
	//$("#gvn_amt"+itemId).html(gvnAmt.toFixed(2));

	 var taxableAmt=baseRate*gvnQty;
		
		var totalTax=(taxableAmt*(sgstPer+cgstPer))/100;
		
		var grandTotal=taxableAmt+totalTax;	
		
		
		document.getElementById('gvnAmt'+grnId).innerText=grandTotal.toFixed(2);

}

</script>

	<script type="text/javascript">


function insertGrnDisAgree(grnGvnId){
//alert("second function called ");
var grnId=grnGvnId;
var approve_acc_login=$("#approve_acc_login"+grnGvnId).val();
var acc_remark=$("#acc_remark"+grnId).val();
var acc_gvn_qty=$("#acc_gvn_qty"+grnGvnId).val();


if($("#acc_remark"+grnGvnId).val() == ''){
	alert("Please Enter Grn Remark!");
	
}
else{
	
	
	$.getJSON('${insertAccGvnProcessDisAgree}',
			{
			
			grnId : grnId,
			approveAccLogin : approve_acc_login,
			accRemark : acc_remark,	
			acc_gvn_qty : acc_gvn_qty,

				ajax : 'true',
			

			}
);


	




callRefreshDisAgree();
/* callSecondRefresh();
callThirdRefresh();
callfourthRefresh(); */
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
var acc_gvn_qty=$("#acc_gvn_qty"+grnGvnId).val();


/* alert(grnId);
alert(approve_gate_login); */

	

	$.getJSON('${insertAccGvnProcessAgree}',
							{
							
							grnId : grnId,
							approveAccLogin:approve_acc_login,
							acc_gvn_qty : acc_gvn_qty,

								
								ajax : 'true',
							
	 complete: function() {
	       //alert("ajax completed");
	       
 	  }
}
);

callRefresh();
/* callSecondRefresh();
callThirdRefresh();
callfourthRefresh(); */
}


</script>

	<script type="text/javascript">
function callRefresh(){
	alert("Approved Successfully");
		window.location.reload();
	//document.getElementById("validation-form").reload();
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

function showGateGvnDetails(){
	
	alert("hi");
		var fromDate=$("#from_date").val();
	
		var toDate=$("#to_date").val();
		
		alert(fromDate);
		alert(toDate);
		
		$.getJSON('${showGateGvnDetails}',
				{
				
				from_date :fromDate,
				to_date	: toDate,
					
					ajax : 'true',
				
				});


}

</script>



	<script type="text/javascript">

function getDate(){
	
	
	var fromDate=$("#from_date").val();
	var toDate=$("#to_date").val();
	
	
	
	
	$.getJSON('${getDateForGvnAcc}',
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

	<script type="text/javascript">
function checkQty(grnId,gvnQty,qty){
	//alert("JJJ");
	var entered=$("#acc_gvn_qty"+grnId).val();
	//alert("received = " +entered);
	if(entered>gvnQty){
		alert("Can not Enter Qty Greater than Gvn Qty ");
		document.getElementById("acc_gvn_qty"+grnId).value=qty;
	}
}
</script>

	<script>
	
		function selectedGvn(source) {
			checkboxes = document.getElementsByName('select_to_agree');
			$.getJSON('${getGvnStatus}', {
				
				ajax : 'true'
			}, function(data) {
				
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