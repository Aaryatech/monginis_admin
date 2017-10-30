<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<style>
div.panel {
	padding: 0 18px;
	background-color: white;
	max-height: 0;
	overflow: hidden;
	transition: max-height 0.2s ease-out;
}
</style>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Dashboard - MONGINIS Admin</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<!--base css styles-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/loader.css">

<%-- <link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/data-tables/bootstrap3/dataTables.bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-fileupload/bootstrap-fileupload.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/chosen-bootstrap/chosen.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-timepicker/compiled/timepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/clockface/css/clockface.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/css/datepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/daterangepicker.css" />
 --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/chosen-bootstrap/chosen.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/jquery-tags-input/jquery.tagsinput.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/jquery-pwstrength/jquery.pwstrength.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-fileupload/bootstrap-fileupload.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-duallistbox/duallistbox/bootstrap-duallistbox.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/dropzone/downloads/css/dropzone.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-colorpicker/css/colorpicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-timepicker/compiled/timepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/clockface/css/clockface.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/css/datepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/daterangepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-switch/static/stylesheets/bootstrap-switch.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-wysihtml5/bootstrap-wysihtml5.css" />


<!--page specific css styles-->

<!--flaty css styles-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flaty.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flaty-responsive.css">

<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/img/favicon.png">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/common.js"></script>
</head>
<body>
	<c:url var="insertGateGrnProcessAgree"
		value="/insertGateGrnProcessAgree" />
		<c:url var="insertAccGrnProcessAgree" value="/insertAccGrnProcessAgree" />

		<c:url var="insertAccGrnProcessDisAgree" value="/insertAccGrnProcessDisAgree" />

	

	<%-- 	<c:url var="getGrnId" value="/getGrnId" />
 --%>


	<%-- <c:url var="callSearchOrdersProcess"
		value="/searchOrdersProcess" />
 --%>

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>


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
								<i class="fa fa-bars"></i>Search GRN
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
										<input class="form-control date-picker" id="dp1" size="16"
											type="text" name="from_date" value="${fromDate}" required />
									</div>
								</div>


								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">To Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="dp2" size="16"
											type="text" value="${toDate}" name="to_date" required />
									</div>
								</div>


								<div class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
									<input type="submit" value="Submit">



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
												class="table table-advance table-responsive" id="table1">
												<thead>
													<tr>
														<th width="30"></th>
														<th width="50" style="width: 18px" align="left">Sr No</th>
														<th width="100" align="left">Bill No</th>
														<th width="120" align="left">Franchise Name</th>
														<th width="120" align="left">Item Name</th>
														<th width="100" align="left">TYPE of GRN</th>
														<th width="100" align="left">Quantity</th>
														<th width="100" align="left">Status</th>
														<th width="100" align="left">Is Edit</th>
														<th width="100" align="left">GRN Amount</th>

														<th width="100">Action</th>
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
																		disabled="disabled" id="${grnList.grnGvnId}"
																		value="${grnList.grnGvnId}"></></td>


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

																	<td><input class="btn btn-primary" value="Approve"
																		id="callSubmit"
																		onclick="insertGrnCall(${grnList.grnGvnId})">





																		<input class="accordion btn btn-primary"
																		value="DisApprove" />
																		<div class="panel" align="left">
																			Enter Remark
																			<textarea name="acc_remark${grnList.grnGvnId}"
																				id="acc_remark${grnList.grnGvnId}"></textarea>
																			<input class="btn btn-primary" value="Submit"
																				onclick="insertGrnDisAgree(${grnList.grnGvnId})" />

																		</div> <input class="accordion btn btn-primary" value="Show" />
																		<div class="panel" align="left">
																			Franchisee Remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${grnList.frGrnGvnRemark}></textarea>
																			Gate remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${grnList.approvedRemarkGate}</textarea>
																				Account remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${grnList.approvedRemarkAcc}</textarea>
																				
																		</div></td>

																</c:when>



																<c:when test="${grnList.grnGvnStatus==3}">

																	<td><input class="btn btn-primary" value="Approve"
																		id="callSubmit"
																		onclick="insertGrnCall(${grnList.grnGvnId})">





																		<input class="accordion btn btn-primary"
																		value="DisApprove" />
																		<div class="panel" align="left">
																			Enter Remark
																			<textarea name="acc_remark${grnList.grnGvnId}"
																				id="acc_remark${grnList.grnGvnId}"></textarea>
																			<input class="btn btn-primary" value="Submit"
																				onclick="insertGrnDisAgree(${grnList.grnGvnId})" />

																		</div> <input class="accordion btn btn-primary" value="Show" />
																		<div class="panel" align="left">
																			Franchisee Remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${grnList.frGrnGvnRemark}></textarea>
																			Gate remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${grnList.approvedRemarkGate}</textarea>
																				
																				Account remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${grnList.approvedRemarkAcc}</textarea>
																		</div></td>

																</c:when>




																<c:when test="${grnList.grnGvnStatus==6}">

																	<td><input class="btn btn-primary" value="Approve"
																		id="callSubmit" disabled="disabled"
																		onclick="insertGrnCall(${grnList.grnGvnId})" /> <input
																		class="accordion btn btn-primary" value="DisApprove" />
																		<div class="panel" align="left">
																			Enter Remark
																			<textarea name="acc_remark${grnList.grnGvnId}"
																				id="acc_remark${grnList.grnGvnId}"></textarea>
																			<input class="btn btn-primary" value="Submit"
																				onclick="insertGrnDisAgree(${grnList.grnGvnId})" />

																		</div> <input class="accordion btn btn-primary" value="Show" />
																		<div class="panel" align="left">
																			Franchisee Remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${grnList.frGrnGvnRemark}></textarea>
																			Gate remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${grnList.approvedRemarkGate}</textarea>
																				Account remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${grnList.approvedRemarkAcc}</textarea>
																		</div></td>

																</c:when>
																<c:when test="${grnList.grnGvnStatus==7}">

																	<td><input class="btn btn-primary" value="Approve"
																		id="callSubmit" disabled="disabled"
																		onclick="insertGrnCall(${grnList.grnGvnId})" /> <input
																		class="accordion btn btn-primary" value="DisApprove" />
																		<div class="panel" align="left">
																			Enter Remark
																			<textarea name="acc_remark${grnList.grnGvnId}"
																				id="acc_remark${grnList.grnGvnId}"></textarea>
																			<input class="btn btn-primary" value="Submit"
																				onclick="insertGrnDisAgree(${grnList.grnGvnId})" />

																		</div> <input class="accordion btn btn-primary" value="Show" />
																		<div class="panel" align="left">
																			Franchisee Remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${grnList.frGrnGvnRemark}></textarea>
																			Gate remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${grnList.approvedRemarkGate}</textarea>
																				
																				Account remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${grnList.approvedRemarkAcc}</textarea>
																		</div></td>

																</c:when>



																<c:otherwise>

																	<td><input class="btn btn-primary" value="Approve"
																		id="callSubmit" disabled="disabled"
																		onclick="insertGrnCall(${grnList.grnGvnId})" /> <input
																		class="accordion btn btn-primary" value="DisApprove"
																		disabled="disabled" />
																		<div class="panel" align="left">
																			Enter Remark
																			<textarea name="acc_remark${grnList.grnGvnId}"
																				id="acc_remark${grnList.grnGvnId}"></textarea>
																			<input class="btn btn-primary" value="Submit"
																				onclick="insertGrnDisAgree(${grnList.grnGvnId})" />

																		</div> <input class="accordion btn btn-primary" value="Show" />
																		<div class="panel" align="left">
																			Franchisee Remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${grnList.frGrnGvnRemark}></textarea>
																			Gate remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${grnList.approvedRemarkGate}</textarea>
																				
																				Account remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${grnList.approvedRemarkAcc}</textarea>
																		</div></td>
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
											<input type="submit" value="Submit">

											<button type="button" onClick="firstCall();">FirstCall</button>

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
alert(acc_remark);

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

}
	




callRefreshDisAgree();
/* callSecondRefresh();
callThirdRefresh();
callfourthRefresh(); */
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



</body>
</html>