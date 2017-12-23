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
   
   <!-- <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker.css" rel="stylesheet"> -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>  
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.js"></script>
</head>
<body onload="chkRequest(${isSave})">


	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

<c:url var="searchFrMonthTarget" value="/searchFrMonthTarget"/>

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
						<i class="fa fa-file-o"></i>Franchise
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
								<i class="fa fa-bars"></i> Add Franchise Target
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showAddFrTarget">Back</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>



        <form action="${pageContext.request.contextPath}/addFrTargetProcess" class="form-horizontal" method="post" id="validation-form">
						<div class="box-content">
							  <div class="col2">
									<label class="col-sm-1 col-lg-2 control-label">Franchise</label>
									<div class="col-sm-9 col-lg-3 controls">
									<select name="fr_id" id="fr_id" class="form-control" placeholder="Select Franchise" data-rule-required="true">
											<option value="-1">Select Franchise</option>
										 <c:forEach items="${franchiseeList}" var="franchiseeList">
											<c:choose>
													<c:when test="${franchiseeList.frId==frId}">
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
                          <div class="col2">
                          <label class="col-sm-1 col-lg-2 control-label">Select Year</label>
                          
                        <div class="container text-center">

                          <input class="date-own form-control" style="width: 200px;" type="text" name="year" id="year"placeholder="select Year" value="${thisYear}">

                          <script type="text/javascript">
                           $('.date-own').datepicker({
                               minViewMode: 2,
                               format: 'yyyy'
                              });
                          </script>

                        </div>							
                        </div>
                        <div class="col2">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="button" class="btn btn-primary" value="Search" onclick="searchFrMonthTarget();validation()">
<!-- 										<button type="button" class="btn">Cancel</button>
 -->									</div>
						</div>
						
							<br/>
							
						    <br/>
							
								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i>Franchise Target
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
														<th width="10" style="width: 8px">Sr.No</th>
             											<th width="10" >#</th>
														
														<th width="80" align="left">Month</th>
														<th width="95" align="left">Target</th>
														<th width="95" align="left">Achieved Target</th>
														<th width="95" align="left">Award</th>
														<th width="95" align="left">Remark</th>
													    <th width="80" align="left">Status</th>
													</tr>
												</thead>
												<tbody>
												 <c:forEach items="${months}" var="months" varStatus="count">
														<tr>
														
															<td><c:out value="${count.index+1}"/></td>
															<td align="left"><input type="checkbox" name="chk" id="chk${count.index+1}" value="${count.index+1}"/></td>
															<td align="left"><input type="text" class="form-control" name="month${count.index+1}" id="month${count.index+1}" maxlength="12" size="12" value="${months}" readonly/></td>
															<td align="left"><input type="text" class="form-control" name="target${count.index+1}" id="target${count.index+1}" maxlength="14" size="14"   value="0"/></td>
												        	<td align="left"><input type="text" class="form-control" name="ach_target${count.index+1}" id="ach_target${count.index+1}" maxlength="14" size="14" value="0"readonly/></td>					
												        	<td align="left"><input type="text" class="form-control" name="award${count.index+1}" id="award${count.index+1}"  value="NA"/></td>					
                                                            <td align="left"><input type="text"class="form-control"  name="remark${count.index+1}" id="remark${count.index+1}"  value="NA"/></td>					
									                        <td align="left"><input type="text" class="form-control" name="status${count.index+1}" id="status${count.index+1}" maxlength="15" size="15" value="UnFreezed"readonly/>					
															 <input type="hidden" class="form-control" name="id${count.index+1}" id="id${count.index+1}" maxlength="15" size="15" value="0" /></td>					
															
														</tr>

													</c:forEach>  
              										</tbody>
											</table>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Submit" onclick="return validation()">
<!-- 										<button type="button" class="btn">Cancel</button>
 -->									</div>
								</div>
							
						</div></form>
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
<script type="text/javascript">

	function searchFrMonthTarget() {
		
		var frId = document.getElementById("fr_id").value;
		var year = document.getElementById("year").value;


		
				$.getJSON('${searchFrMonthTarget}', {
					
					frId : frId,
					year:year,
					ajax : 'true'
				}, function(data) {
					var len = data.length;
                    
					$.each(data,function(key, frTarget) {
						
					if(frTarget.status==1)
					{
						 document.getElementById("chk"+frTarget.frTargetMonth).disabled = true;
					 	document.getElementById('target'+frTarget.frTargetMonth).value = frTarget.frTargetAmt;
					 	document.getElementById('target'+frTarget.frTargetMonth).disabled = true;
					 	
					 	if(frTarget.frAchievedSale>frTarget.frTargetAmt)
					 		{
							  document.getElementById('ach_target'+frTarget.frTargetMonth).style.color = "green";
					 		}
						document.getElementById('ach_target'+frTarget.frTargetMonth).value = frTarget.frAchievedSale;
						document.getElementById('ach_target'+frTarget.frTargetMonth).disabled = true;
						document.getElementById('award'+frTarget.frTargetMonth).value = frTarget.frAward;
						document.getElementById('award'+frTarget.frTargetMonth).disabled = true;
						document.getElementById('remark'+frTarget.frTargetMonth).value = frTarget.remark;
						document.getElementById('remark'+frTarget.frTargetMonth).disabled = true;
						document.getElementById('id'+frTarget.frTargetMonth).value = frTarget.frTargetId;

						if(frTarget.status==1)
							{
						document.getElementById('status'+frTarget.frTargetMonth).value ="Freezed";
							}
						else if(frTarget.status==0)
							{
							document.getElementById('status'+frTarget.frTargetMonth).value="Unfreezed";
	
							}
					}
					else if(frTarget.status==0)
					{
						document.getElementById('target'+frTarget.frTargetMonth).value = frTarget.frTargetAmt;
					 	if(frTarget.frAchievedSale>frTarget.frTargetAmt)
					 		{
							  document.getElementById('ach_target'+frTarget.frTargetMonth).style.color = "green";
					 		}
						document.getElementById('ach_target'+frTarget.frTargetMonth).value = frTarget.frAchievedSale;
						document.getElementById('award'+frTarget.frTargetMonth).value = frTarget.frAward;
						document.getElementById('remark'+frTarget.frTargetMonth).value = frTarget.remark;
						document.getElementById('id'+frTarget.frTargetMonth).value = frTarget.frTargetId;

						if(frTarget.status==1)
							{
						document.getElementById('status'+frTarget.frTargetMonth).value ="Freezed";
							}
						else if(frTarget.status==0)
							{
							document.getElementById('status'+frTarget.frTargetMonth).value="Unfreezed";
	
							}	
							
							
							
							
					}
					
					})
					

				});
			}
</script>
<script type="text/javascript">
function validation() {
	
	var frId = $("#fr_id").val();
	var year = $("#year").val();
	
	var isValid = true;
	if (frId==-1) { 
		isValid = false;
		alert("Please Select Franchisee");
	} else if (year.length>4) {
		isValid = false;
		alert("Please Select Valid Year");
	}
	return isValid;
}

</script>
<script type="text/javascript">
function chkRequest(isSave)
{
	if(isSave==1)
		{
		searchFrMonthTarget();
		}
	
}
</script>
</html>
