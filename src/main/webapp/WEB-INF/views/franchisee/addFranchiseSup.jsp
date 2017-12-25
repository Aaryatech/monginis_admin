<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Dashboard - MONGINIS Admin</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<!--base css styles-->
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

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script>
											window.jQuery
													|| document
															.write('<script src="${pageContext.request.contextPath}/resources/assets/jquery/jquery-2.0.3.min.js"><\/script>')
										</script>

<!--page specific css styles-->
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
<body onload="disableFranchise(${isEdit})">


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
						<i class="fa fa-file-o"></i>Franchise Supplement
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
								<i class="fa fa-bars"></i> Add Franchise Sup
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showAddFrSup">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>




						<div class="box-content">
							<form action="${pageContext.request.contextPath}/addFrSupProcess" class="form-horizontal"
								method="post" id="validation-form">

	                    <input type="hidden" name="id" id="id" value="${frSup.id}"/>
							 
							  <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Franchise</label>
									<div class="col-sm-9 col-lg-3 controls">
									<select name="fr_id" id="fr_id" class="form-control" placeholder="Select Franchise" data-rule-required="true">
											<option value="-1">Select Franchise</option>
										 <c:forEach items="${franchiseeList}" var="franchiseeList">
											<c:choose>
													<c:when test="${franchiseeList.frId==frSup.frId}">
												          <option value="${franchiseeList.frId}" selected><c:out value="${franchiseeList.frName}"></c:out></option>
													</c:when>
													<c:otherwise>
										            	  <option value="${franchiseeList.frId}"><c:out value="${franchiseeList.frName}"></c:out></option>
													</c:otherwise>
												</c:choose>
										</c:forEach>
												
								</select>	
									</div>
								</div>
                         
							  <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">PAN No.</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="pan_no" id="pan_no"
											placeholder="PAN No" class="form-control"
											data-rule-required="true" value="${frSup.frPanNo}"/>
									</div>
							  </div>
							  <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">State</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="fr_state" id="fr_state"
											placeholder="State" class="form-control"
											data-rule-required="true" value="${frSup.frState}"/>
									</div>
							  </div>
							   <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Country</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="fr_country" id="fr_country"
											placeholder="Country" class="form-control"
											data-rule-required="true" value="${frSup.frCountry}"/>
									</div>
							  </div>
							  <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Password1</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="pass1" id="pass1"
											placeholder="User1 Password" class="form-control"
											data-rule-required="true" value="${frSup.pass1}"/>
									</div>
							  </div>
							    <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Password2</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="pass2" id="pass2"
											placeholder="User2 Password" class="form-control"
											data-rule-required="true" value="${frSup.pass2}"/>
									</div>
							  </div>
							    <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Password3</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="pass3" id="pass3"
											placeholder="User3 Password" class="form-control"
											data-rule-required="true" value="${frSup.pass3}"/>
									</div>
							  </div>
								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Submit">
<!-- 										<button type="button" class="btn">Cancel</button>
 -->									</div>
								</div>
							</form>
							<br>
								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i>Franchise List
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
											<table width="100%" class="table table-advance" id="table1">
												<thead>
													<tr>
														<th width="45" style="width: 18px">Sr.No.</th>
														<th width="100" align="left">Franchise Name</th>
														<th width="100" align="left">PAN No.</th>
														<th width="100" align="left">State</th>
														<th width="100" align="left">Country</th>
													<th width="100" align="left">Pass1</th>
													<th width="100" align="left">Pass2</th>
													<th width="100" align="left">Pass3</th>
												
														<th width="81" align="left">Action</th>
													</tr>
												</thead>
												<tbody>
												 <c:forEach items="${frSupList}" var="frSupList" varStatus="count">
														<tr>
														
															<td><c:out value="${count.index+1}"/></td>
															<td align="left"><c:out
																	value="${frSupList.frName}"></c:out></td>
															<td align="left"><c:out
																	value="${frSupList.frPanNo}"></c:out></td>	
												        	<td align="left"><c:out
																	value="${frSupList.frState}"></c:out></td>		
															<td align="left"><c:out
																	value="${frSupList.frCountry}"></c:out></td>							
															<td align="left"><c:out
																	value="${frSupList.pass1}"></c:out></td>
															<td align="left"><c:out
																	value="${frSupList.pass2}"></c:out></td>
															<td align="left"><c:out
																	value="${frSupList.pass3}"></c:out></td>
															
																	
															<td align="left"><a href="${pageContext.request.contextPath}/updateFranchiseSup/${frSupList.id}"><span
														class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;
                                                        </td>		
																												
       													
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


</body>
<script>
function disableFranchise(isEdit) {
	if(isEdit==1)
		{
		$("#fr_id option:not(:selected)").prop("disabled", true);
		}

}
</script>
</html>
