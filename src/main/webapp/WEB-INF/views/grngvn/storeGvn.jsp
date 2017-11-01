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

<link rel="stylesheet"
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

	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap/css/lightbox.css">


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
	<c:url var="insertStoreGvnProcessAgree"
		value="/insertStoreGvnProcessAgree" />


	<c:url var="insertStoreGvnProcessDisAgree"
		value="/insertStoreGvnProcessDisAgree" />
		
		
		
		<c:url var="getDateForGvnStore"
		value="/getDateForGvnStore" />
		
		
		
		
	<%-- 	<c:url var="showGateGvnDetails"
		value="/showGateGvnDetails" /> --%>
		
		
		


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
						<i class="fa fa-file-o"></i>GVN
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
								<i class="fa fa-bars"></i>Search Store GVN
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
								action="${pageContext.request.contextPath}/showStoreGvnDetails"
								class="form-horizontal" method="get" id="validation-form">




								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">From
										Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="from_date" size="16"
											type="text" name="from_date" value="${fromDate}" required 
											onblur="getDate()"/>
									</div>
								</div>


								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">To Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="to_date" size="16"
											type="text" value="${toDate}" name="to_date" required 
											onblur="getDate()" />
									</div>
								</div>


								<div class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
									<input type="submit" value="Submit"/>



								</div>


							</form>



							<form
								action="${pageContext.request.contextPath}/insertStoreGvnByCheckBoxes"
								class="form-horizontal" method="get" id="validation-form">




								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i> GVN List
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
														<th width="100" align="left">GVN Quantity</th>
														<th width="100" align="left">PHOTO 1</th>
														<th width="100" align="left">PHOTO 2</th>
														<th width="100" align="left">Status</th>
														<th width="100" align="left">Action</th>
													</tr>


												</thead>
												<tbody>
													<c:forEach items="${gvnList}" var="gvnList"
														varStatus="count">

														<tr>
															<c:choose>
																<c:when test="${gvnList.grnGvnStatus==2}">
																	<td><input type="checkbox" name="select_to_agree"
																		 id="${gvnList.grnGvnId}"
																		value="${gvnList.grnGvnId}"></></td>

																</c:when>
																<c:when test="${gvnList.grnGvnStatus==4}">
																	<td><input type="checkbox" name="select_to_agree" disabled="disabled"
																		id="${gvnList.grnGvnId}" value="${gvnList.grnGvnId}"></></td>

																</c:when>

																<c:when test="${gvnList.grnGvnStatus==5}">
																	<td><input type="checkbox" name="select_to_agree"
																		id="${gvnList.grnGvnId}" value="${gvnList.grnGvnId}"></></td>


																</c:when>

																<c:otherwise>

																	<td><input type="checkbox" name="select_to_agree"
																		disabled="disabled" id="${gvnList.grnGvnId}"
																		value="${gvnList.grnGvnId}"></></td>


																</c:otherwise>
															</c:choose>
															<td><c:out value="${count.index+1}" /></td>

															<td align="left"><c:out value="${gvnList.billNo}" /></td>

															<td align="left"><c:out value="${gvnList.frName}" /></td>


															<td align="left"><c:out value="${gvnList.itemName}" /></td>


															<td align="left"><c:out value="${gvnList.grnGvnQty}" />
																<input type="hidden"
																name="approve_store_login${gvnList.grnGvnId}"
																id="approve_store_login${gvnList.grnGvnId}"
																value="${gvnList.approvedLoginStore}" /></td>

															<td><a href="${url}${gvnList.gvnPhotoUpload1}"data-lightbox="image-1" >Image 1</a>
																							</td>

															<td><a href="${url}${gvnList.gvnPhotoUpload2}"data-lightbox="image-1" >Image 2</a>

															<c:choose>
																<c:when test="${gvnList.grnGvnStatus==1}">
																	<td align="left"><c:out value="Pending"></c:out></td>

																</c:when>

																<c:when test="${gvnList.grnGvnStatus==2}">
																	<td align="left"><c:out value="approvedByGate"></c:out></td>

																</c:when>

																<c:when test="${gvnList.grnGvnStatus==3}">
																	<td align="left"><c:out value="rejectByGate"></c:out></td>

																</c:when>

																<c:when test="${gvnList.grnGvnStatus==4}">
																	<td align="left"><c:out value="approvedBystore"></c:out></td>

																</c:when>

																<c:when test="${gvnList.grnGvnStatus==5}">
																	<td align="left"><c:out value="rejectByStore"></c:out></td>

																</c:when>

																<c:when test="${gvnList.grnGvnStatus==6}">
																	<td align="left"><c:out value="approvedByAcc"></c:out></td>

																</c:when>

																<c:when test="${gvnList.grnGvnStatus==7}">
																	<td align="left"><c:out value="rejectByAcc"></c:out></td>

																</c:when>

															</c:choose>

															<c:choose>
																<c:when test="${gvnList.grnGvnStatus==2}">

																	<td><input class="btn btn-primary" value="Approve"
																		id="callSubmit" 
																		onclick="insertGrnCall(${gvnList.grnGvnId})">


																	<input class="accordion btn btn-primary"
																		value="DisApprove" />
																		<div class="panel" align="left">
																			Enter Remark
																			<textarea name="store_remark${gvnList.grnGvnId}"
																				id="store_remark${gvnList.grnGvnId}"></textarea>
																			<input class="btn btn-primary" value="Submit"
																				onclick="insertGrnDisAgree(${gvnList.grnGvnId})" />

																		</div> <input class="accordion btn btn-primary" value="Show" />
																		<div class="panel" align="left">
																			Franchisee Remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${gvnList.frGrnGvnRemark}</textarea>
																			Factory remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${gvnList.approvedRemarkGate}</textarea>
																		</div></td>

																</c:when>



																<c:when test="${gvnList.grnGvnStatus==4}">

																	<td><input class="btn btn-primary" value="Approve"
																		id="callSubmit" disabled="disabled"
																		onclick="insertGrnCall(${gvnList.grnGvnId})">





																		<input class="accordion btn btn-primary"
																		value="DisApprove"  />
																		<div class="panel" align="left">
																			Enter Remark
																			<textarea name="store_remark${gvnList.grnGvnId}"
																				id="store_remark${gvnList.grnGvnId}"></textarea>
																			<input class="btn btn-primary" value="Submit"
																				onclick="insertGrnDisAgree(${gvnList.grnGvnId})" />

																		</div> <input class="accordion btn btn-primary" value="Show" />
																		<div class="panel" align="left">
																			Franchisee Remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control" > ${gvnList.frGrnGvnRemark}</textarea>
																			Factory remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${gvnList.approvedRemarkGate}</textarea>
																		</div></td>

																</c:when>


																<c:when test="${gvnList.grnGvnStatus==5}">

																	<td><input class="btn btn-primary" value="Approve"
																		id="callSubmit"
																		onclick="insertGrnCall(${gvnList.grnGvnId})">

																		<input class="accordion btn btn-primary"
																		value="DisApprove" disabled="disabled" />
																		<div class="panel" align="left">
																			Enter Remark
																			<textarea name="store_remark${gvnList.grnGvnId}"
																				id="store_remark${gvnList.grnGvnId}"></textarea>
																			<input class="btn btn-primary" value="Submit"
																				onclick="insertGrnDisAgree(${gvnList.grnGvnId})" />

																		</div> <input class="accordion btn btn-primary" value="Show" />
																		<div class="panel" align="left">
																			Franchisee Remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${gvnList.frGrnGvnRemark}</textarea>
																			Factory remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${gvnList.approvedRemarkGate}</textarea>
																		</div></td>

																</c:when>


																<c:otherwise>

																	<td><input class="btn btn-primary" value="Approve"
																		id="callSubmit" disabled="disabled"
																		onclick="insertGrnCall(${gvnList.grnGvnId})" /> <input
																		class="accordion btn btn-primary" value="DisApprove"
																		disabled="disabled" />
																		<div class="panel" align="left">
																			Enter Remark
																			<textarea name="store_remark${gvnList.grnGvnId}"
																				id="store_remark${gvnList.grnGvnId}"></textarea>
																			<input class="btn btn-primary" value="Submit"
																				onclick="insertGrnDisAgree(${gvnList.grnGvnId})" />

																		</div> <input class="accordion btn btn-primary" value="Show" />
																		<div class="panel" align="left">
																			Franchisee Remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${gvnList.frGrnGvnRemark}</textarea>
																			Factory remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${gvnList.approvedRemarkGate}</textarea>
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
var approve_store_login=$("#approve_store_login"+grnGvnId).val();
var store_remark=$("#store_remark"+grnId).val();

if($("#store_remark"+grnGvnId).val() == ''){
	alert("Please Enter Grn Remark!");
	
}
else{
	
	
	$.getJSON('${insertStoreGvnProcessDisAgree}',
			{
			
			grnId : grnId,
			approveStoreLogin : approve_store_login,
			storeRemark : store_remark,				
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
var approve_store_login=$("#approve_store_login"+grnGvnId).val();
var gate_remark=$("#gate_remark"+grnGvnId).val();


/* alert(grnId);
alert(approve_gate_login); */

	

	$.getJSON('${insertStoreGvnProcessAgree}',
							{
							
							grnId : grnId,
							approveStoreLogin:approve_store_login,
								
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
	
	
	
	
	$.getJSON('${getDateForGvnStore}',
			{
			
			fromDate : fromDate,
			toDate:toDate,
				
				ajax : 'true',
			

}
);

	
}


</script>


</body>
</html>