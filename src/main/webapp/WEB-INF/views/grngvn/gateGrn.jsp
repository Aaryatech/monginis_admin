<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body>

	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<c:url var="insertGateGrnProcessAgree"
		value="/insertGateGrnProcessAgree" />

	<c:url var="insertGateGrnProcessDisAgree"
		value="/insertGateGrnProcessDisAgree" />

	<c:url var="getDateForGrnGate" value="/getDateForGrnGate" />

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
								<i class="fa fa-bars"></i>GRN for Gate
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
								action="${pageContext.request.contextPath}/insertGateGrnByCheckBoxes"
								class="form-horizontal" method="post" id="validation-form">

								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i> GRN List Date:${grnDate} SrNo:${srNo }
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
												class="table table-advance table-responsive table-position"
												id="table1">
												<thead>
													<tr>
														<th></th>
														<th>Sr No</th>
														<th align="center">Invoice No</th>
														<th>Franchise Name</th>
														<th>Item Name</th>
														<th>TYPE of GRN</th>
														<th>Auto Qty</th>
														<th>Edited Qty</th>
														<th align="center">Received Qty</th>
														<th>Status</th>
														<th width="160px">Action</th>
													</tr>

												</thead>
												<tbody>
													<c:forEach items="${grnList}" var="grnList"
														varStatus="count"> 
														
														 <c:choose> 
														<c:when test="${grnList.grnGvnQtyAuto!=grnList.grnGvnQty}">
														<c:set var="color" value="red"></c:set> 
														</c:when>
														<c:otherwise>
														<c:set var="color" value="white"></c:set>
														</c:otherwise>
														</c:choose> 

													<tr bgcolor="${color}">
 															<c:choose>
																<c:when test="${grnList.grnGvnStatus==2}">
																	<td><input type="checkbox" name="select_to_agree"
																		disabled="disabled" id="${grnList.grnGvnId}"
																		value="${grnList.grnGvnId}"></></td>

																</c:when>
																<c:when test="${grnList.grnGvnStatus==3}">
																	<td><input type="checkbox" name="select_to_agree"
																		id="${grnList.grnGvnId}" value="${grnList.grnGvnId}"></></td>

																</c:when>

																<c:when test="${grnList.grnGvnStatus==1}">
																	<td><input type="checkbox" name="select_to_agree"
																		id="${grnList.grnGvnId}" value="${grnList.grnGvnId}"></></td>


																</c:when>

																<c:otherwise>

																	<td><input type="checkbox" name="select_to_agree"
																		disabled="disabled" id="${grnList.grnGvnId}"
																		value="${grnList.grnGvnId}"></></td>


																</c:otherwise>
															</c:choose>
															<td align="center"><c:out value="${count.index+1}" /></td>

															<td align="center"><c:out
																	value="${grnList.invoiceNo}" /></td>

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

															<td align="center"><c:out
																	value="${grnList.grnGvnQtyAuto}" /></td>

															<td align="center"><c:out
																	value="${grnList.grnGvnQty}" /> <input type="hidden"
																name="approve_gate_login${grnList.grnGvnId}"
																id="approve_gate_login${grnList.grnGvnId}"
																value="${grnList.approvedLoginGate}" /></td>
															
															<c:set var="qty" value="0"></c:set>

															<c:choose>

																<c:when test="${grnList.grnGvnStatus==1}">


																	<c:set var="qty" value="${grnList.grnGvnQty}"></c:set>

																	<%-- <td align="center"><input type="text"
																		name="gate_grn_qty${grnList.grnGvnId}"
																		class="form-control" style="width: 50px"
																		id='gate_grn_qty${grnList.grnGvnId}'
																		value="${grnList.grnGvnQty}" /></td> --%>
																</c:when>

																<c:otherwise>

																	<c:set var="qty" value="${grnList.aprQtyGate}"></c:set>
																	<%-- <td align="center"><input type="text"
																		name="gate_grn_qty${grnList.grnGvnId}"
																		class="form-control" style="width: 50px"
																		id='gate_grn_qty${grnList.grnGvnId}'
																		value="${grnList.aprQtyGate}" /></td> --%>
																</c:otherwise>

															</c:choose>

															<td align="center"><input type="text"
																name="gate_grn_qty${grnList.grnGvnId}"
																class="form-control" style="width: 50px"
																id='gate_grn_qty${grnList.grnGvnId}' onkeyup="checkQty(${grnList.grnGvnId},${grnList.grnGvnQty},${grnList.aprQtyGate},${qty})"
																value="${qty}" /></td>


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

															<c:choose>
																<c:when test="${grnList.grnGvnStatus==2}">

																	<td>


																		<ul class="table-menu">
																			<li><a href="" id="callSubmit"
																				disabled="disabled" class="disableClick"
																				onclick="insertGrnCall(${grnList.grnGvnId})"><i
																					class="fa fa-check"></i></a></li>

																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#"
																						data-toggle="dropdown"><i class="fa fa-times"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">

																							<select name="gate_remark${grnList.grnGvnId}"
																								id="gate_remark${grnList.grnGvnId}"
																								class="form-control">
																								<c:forEach items="${remarkList}"
																									var="remarkList">

																									<option value="${remarkList.remark}">${remarkList.remark}</option>



																								</c:forEach>
																							</select> </br> <input class="btn btn-primary" value="Submit"
																								onclick="insertGrnDisAgree(${grnList.grnGvnId})" />


																							<%-- Enter Remark
																							<textarea name="gate_remark${grnList.grnGvnId}"
																								id="gate_remark${grnList.grnGvnId}"
																								class="form-control"></textarea>
																							</br> <input class="btn btn-primary" value="Submit"
																								onclick="insertGrnDisAgree(${grnList.grnGvnId})" />
 --%>
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
																								class="form-control">${grnList.frGrnGvnRemark}</textarea>
																							Factory remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control">${grnList.approvedRemarkGate}</textarea>
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
																				onclick="insertGrnCall(${grnList.grnGvnId})"><i
																					class="fa fa-check"></i></a></li>

																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#" id="disableMe"
																						data-toggle="dropdown"><i class="fa fa-times"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">

																							<select name="gate_remark${grnList.grnGvnId}"
																								id="gate_remark${grnList.grnGvnId}"
																								class="form-control">
																								<c:forEach items="${remarkList}"
																									var="remarkList">

																									<option value="${remarkList.remark}">${remarkList.remark}</option>



																								</c:forEach>
																							</select> </br> <input class="btn btn-primary" value="Submit"
																								disabled="disabled"
																								onclick="insertGrnDisAgree(${grnList.grnGvnId})" />



																							<%-- Enter Remark
																							<textarea name="gate_remark${grnList.grnGvnId}"
																								id="gate_remark${grnList.grnGvnId}"
																								class="form-control"></textarea>
																							</br> <input class="btn btn-primary" value="Submit"
																								onclick="insertGrnDisAgree(${grnList.grnGvnId})" /> --%>
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
																								class="form-control"> ${grnList.frGrnGvnRemark}</textarea>
																							Factory remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control">${grnList.approvedRemarkGate}</textarea>
																						</div>
																					</div>
																				</div>

																			</li>
																		</ul>
																	</td>

																</c:when>


																<c:when test="${grnList.grnGvnStatus==1}">

																	<td>




																		<ul class="table-menu" style="text-align: left;">

																			<li><a href="" id="callSubmit"
																				onclick="insertGrnCall(${grnList.grnGvnId})"><i
																					class="fa fa-check"></i></a></li>

																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#"
																						data-toggle="dropdown"><i class="fa fa-times"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">


																							<select name="gate_remark${grnList.grnGvnId}"
																								id="gate_remark${grnList.grnGvnId}"
																								class="form-control">

																								<c:forEach items="${remarkList}"
																									var="remarkList">

																									<option value="${remarkList.remark}">${remarkList.remark}</option>

																								</c:forEach>
																							</select> </br> <input class="btn btn-primary" value="Submit"
																								onclick="insertGrnDisAgree(${grnList.grnGvnId})" />


																							<%-- Enter Remark
																							<textarea name="gate_remark${grnList.grnGvnId}"
																								id="gate_remark${grnList.grnGvnId}"
																								class="form-control"></textarea>
																							</br> <input class="btn btn-primary" value="Submit"
																								onclick="insertGrnDisAgree(${grnList.grnGvnId})" /> --%>
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
																								class="form-control">${grnList.frGrnGvnRemark}</textarea>
																							Factory remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control">${grnList.approvedRemarkGate}</textarea>

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
																				disabled="disabled" class="disableClick"
																				onclick="insertGrnCall(${grnList.grnGvnId})"><i
																					class="fa fa-check"></i></a></li>


																			<li>
																				<div class="col1">
																					<a class="dropdown-toggle" href="#" id="disableMe"
																						data-toggle="dropdown"><i class="fa fa-times"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">


																							<select name="gate_remark${grnList.grnGvnId}"
																								id="gate_remark${grnList.grnGvnId}"
																								class="form-control">
																								<c:forEach items="${remarkList}"
																									var="remarkList">

																									<option value="${remarkList.remark}">${remarkList.remark}</option>



																								</c:forEach>
																							</select> </br> <input class="btn btn-primary" value="Submit"
																								disabled
																								onclick="insertGrnDisAgree(${grnList.grnGvnId})" />


																							<%-- Enter Remark
																							<textarea name="gate_remark${grnList.grnGvnId}"
																								id="gate_remark${grnList.grnGvnId}"
																								class="form-control"></textarea>
																							</br> <input class="btn btn-primary" value="Submit"
																								onclick="insertGrnDisAgree(${grnList.grnGvnId})" />
 --%>
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
																								class="form-control">${grnList.frGrnGvnRemark}</textarea>
																							Factory remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control">${grnList.approvedRemarkGate}</textarea>

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
											class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-5">
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
			//alert("HIII");
			
		    var form = document.getElementById("validation-form");
		    form.action ="${pageContext.request.contextPath}/insertGateGrnByCheckBoxes";
		    form.submit();
		}
</script>
	<script type="text/javascript">


function insertGrnDisAgree(grnGvnId){
//alert("second function called ");
var grnId=grnGvnId;
var approve_gate_login=$("#approve_gate_login"+grnGvnId).val();
var gate_remark=$("#gate_remark"+grnId).val();

var gate_grn_qty=$("#gate_grn_qty"+grnGvnId).val();

if($("#gate_remark"+grnGvnId).val() == ''){
	alert("Please  Grn Remark!");
	
}
else{
	
	
	$.getJSON('${insertGateGrnProcessDisAgree}',
			{
			
			grnId : grnId,
			approveGateLogin : approve_gate_login,
			gateRemark : gate_remark,
		 gate_grn_qty:gate_grn_qty,

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
var approve_gate_login=$("#approve_gate_login"+grnGvnId).val();
var gate_remark=$("#gate_remark"+grnGvnId).val();
var gate_grn_qty=$("#gate_grn_qty"+grnGvnId).val();

/* alert(grnId);
alert(approve_gate_login); */

	

	$.getJSON('${insertGateGrnProcessAgree}',
							{
							
							grnId : grnId,
							approveGateLogin:approve_gate_login,
							gate_grn_qty:gate_grn_qty,
								
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
	
	
	
	$.getJSON('${getDateForGrnGate}',
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

function checkQty(grnId,grnQty,aprQty,qty){
	//alert("JJJ");
	var entered=$("#gate_grn_qty"+grnId).val();
	alert("received = " +entered);
	if(entered>grnQty){
		alert("Can not Enter Qty Greater than auto Qty ");
		document.getElementById("gate_grn_qty"+grnId).value=qty;
	}
}

</script>


</body>
</html>

