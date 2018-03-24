<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
	<c:url var="insertGateGrnProcessAgree"
		value="/insertGateGrnProcessAgree" />
		
	<c:url var="insertAccGrnProcessAgree" value="/insertAccGrnProcessAgree" />

	<c:url var="insertAccGrnProcessDisAgree"
		value="/insertAccGrnProcessDisAgree" />

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
						<i class="fa fa-file-o"></i>GRN
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
								<i class="fa fa-bars"></i>Search GRN for Account
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
								action="${pageContext.request.contextPath}/showAccountGrnDetails"
								class="form-horizontal" method="get" id="validation-form">




								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">From
										Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="from_date"
											size="16" type="text" name="from_date" value="${fromDate}"
											required onblur="getDate()" />
									</div>
									<!-- </div>


								<div class="form-group"> -->
									<label class="col-sm-3 col-lg-2 control-label">To Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="to_date" size="16"
											type="text" value="${toDate}" name="to_date" required
											onblur="getDate()" />
									</div>

									<div
										class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
										<input type="submit" value="Submit" class="btn btn-primary">
									</div>

								</div>

							</form>

							<form
								action="${pageContext.request.contextPath}/insertAccGrnByCheckBoxes"
								class="form-horizontal" method="get" id="validation-form">




								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i> GRN List
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
										</div>
									</div>

									<div class="box-content">

										<div class="clearfix"></div>
										<div class="table-responsive" style="border: 0">
											<table width="100%"
												class="table table-advance table-responsive table-position" id="table1">
												<thead>
													<tr>
														<th ></th>
														<th>Sr No</th>
														<th>Bill No</th>
														<th>Franchise Name</th>
														<th>Item Name</th>
														<th>TYPE of GRN</th>
														<th>Quantity</th>
														<th>Status</th>
														<th>Is Edit</th>
														<th>GRN Amount</th>

														<th>Action</th>
													</tr>



												</thead>
												<tbody>
													<c:forEach items="${grnList}" var="grnList"
														varStatus="count">

														<tr>
															<c:choose>
																<c:when test="${grnList.grnGvnStatus==2}">
																	<td><input type="checkbox" name="select_to_agree"
																		id="${grnList.grnGvnId}" value="${grnList.grnGvnId}"></></td>

																</c:when>
																<c:when test="${grnList.grnGvnStatus==3}">
																	<td><input type="checkbox" name="select_to_agree"
																		id="${grnList.grnGvnId}" value="${grnList.grnGvnId}"></></td>

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
																		value="${grnList.grnGvnId}"></></td>


																</c:otherwise>
															</c:choose>
															<td><c:out value="${count.index+1}" /></td>

															<td align="left"><c:out value="${grnList.billNo}" /></td>

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
																	<td align="left"><c:out value="GRN 4"></c:out></td>

																</c:when>

															</c:choose>


															<td align="left"><c:out value="${grnList.grnGvnQty}" />
																<input type="hidden"
																name="approve_acc_login${grnList.grnGvnId}"
																id="approve_acc_login${grnList.grnGvnId}"
																value="${grnList.approvedLoginAcc}" /></td>


															<c:choose>
																<c:when test="${grnList.grnGvnStatus==1}">
																	<td align="left"><c:out value="Pending"></c:out></td>

																</c:when>

																<c:when test="${grnList.grnGvnStatus==2}">
																	<td align="left"><c:out value="approvedByGate"></c:out></td>

																</c:when>

																<c:when test="${grnList.grnGvnStatus==3}">
																	<td align="left"><c:out value="rejectByGate"></c:out></td>

																</c:when>

																<c:when test="${grnList.grnGvnStatus==4}">
																	<td align="left"><c:out value="approvedBystore"></c:out></td>

																</c:when>

																<c:when test="${grnList.grnGvnStatus==5}">
																	<td align="left"><c:out value="rejectByStore"></c:out></td>

																</c:when>

																<c:when test="${grnList.grnGvnStatus==6}">
																	<td align="left"><c:out value="approvedByAcc"></c:out></td>

																</c:when>

																<c:when test="${grnList.grnGvnStatus==7}">
																	<td align="left"><c:out value="rejectByAcc"></c:out></td>

																</c:when>

															</c:choose>

															<td align="left"><c:out value="${grnList.isGrnEdit}"></c:out></td>
															<td align="left"><c:out value="${grnList.grnGvnAmt}"></c:out></td>



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
																					<div class="dropdown-menu">
																						<div class="form">

																							<select name="acc_remark${grnList.grnGvnId}"
																								id="acc_remark${grnList.grnGvnId}"
																								class="form-control">
																								<c:forEach items="${remarkList}"
																									var="remarkList">
																									<option value="${remarkList.remark}">${remarkList.remark}</option>
																								</c:forEach>
																							</select> 
																							</br>
																							<input class="btn btn-primary"
																								value="Submit"
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

																			<li><a href="" id="callSubmit" class="disableClick"
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
																							</select> </br><input class="btn btn-primary"
																								value="Submit" disabled="disabled"
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

																			<li><a href="" id="callSubmit" class="disableClick"
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
																							</select> </br><input class="btn btn-primary"
																								value="Submit"
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

																			<li><a href="" id="callSubmit" class="disableClick"
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
																							</select> </br><input class="btn btn-primary"
																								value="Submit" disabled="disabled"
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

																			<li><a href="" id="callSubmit" class="disableClick"
																				onclick="insertGrnCall(${grnList.grnGvnId})"><i
																					class="fa fa-check"></i></a></li>
																			<li>

																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#"  id="disableMe"
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
																							</select> </br ><input class="btn btn-primary"
																								value="Submit" disabled="disabled"
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

	<!-- <script type="text/javascript">
		/* $(document).ready(function() { */
			/* $('#callSubmit').submit(function() { */
				function callSubmitGrn(){
					//var selectedGrn = $("#selectFr").val();
					
					
					
					
				
			
			//alert("hello"),
				 $.ajax({
					type : "get",
					
					
					
					url : "insertGateGrnProcess" //this is my servlet
				/*  data: "input=" +$('#ip').val()+"&output="+$('#op').val(), */

				});
			
 
		};
	</script>
 -->


	<!-- insertGrnDisAgree -->


	<script type="text/javascript">


function insertGrnDisAgree(grnGvnId){
//alert("second function called ");
var grnId=grnGvnId;
var approve_acc_login=$("#approve_acc_login"+grnGvnId).val();
var acc_remark=$("#acc_remark"+grnGvnId).val();

if($("#acc_remark"+grnGvnId).val() == ''){
	alert("Please Enter Grn Remark!");
	
}
else{
	
	
	$.getJSON('${insertAccGrnProcessDisAgree}',
			{
			
			grnId : grnId,
			approveAccLogin : approve_acc_login,
			accRemark : acc_remark,				
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


/* alert(grnId);
alert(approve_gate_login); */

	

	$.getJSON('${insertAccGrnProcessAgree}',
							{
							
							grnId : grnId,
							approveAccLogin:approve_acc_login,
								
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




</body>
</html>