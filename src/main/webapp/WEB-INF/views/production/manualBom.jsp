<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<c:url var="getRmSubCategory" value="/getRmSubCategory" />
	
	<c:url var="insertSfItemDetail" value="/insertSfItemDetail" />
	<c:url var="getRmCategory" value="/getRmCategory" />

	<c:url var="getItemDetail" value="/getItemDetail" />
		<c:url var="getSingleItem" value="/getSingleItem" />
	
		<c:url var="getMaterial" value="/getMaterial" />
	
		<c:url var="itemForEdit" value="/itemForEdit" />

		<c:url var="getMatUom" value="/getMatUom" />



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
						<i class="fa fa-file-o"></i>Manual BOM Request
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
								<i class="fa fa-bars"></i>Add Manual Bom - <b>-${sfName}</b>
							</h3>
							<div class="box-tool">
								<a href="">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>

						</div>
						<div class="box-content">
							<form action="" method="post"
								class="form-horizontal" id="validation-form" method="post">

								<div class="form-group">
								
								<h4 align="center">Prod Header Id : 114  Prod Date :22-11-2017</h4>

									<label class="col-sm-3 col-lg-2 control-label">
										From Dept</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="from_dept" id="from_dept"
											class="form-control"
											placeholder="Material Type" data-rule-required="true">
											<option value="0">Select From Dept</option>
											<option value="1">Prod</option>
											<option value="2">Mixing</option>
										</select>
									</div>
								
									
									<label class="col-sm-3 col-lg-2 control-label">
										To Dept</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="to_dept" id="to_dept"
											class="form-control"
											placeholder="Material Type" data-rule-required="true">
											<option value="0">Select To Dept</option>
											<option value="1">Kitchen</option>
											<option value="2">Prod</option>
										</select>
									</div>
										
								</div>
								
								
								<div class="form-group">

									<label class="col-sm-3 col-lg-2 control-label">
										Material Type</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="material_type" id="material_type"
											class="form-control"
											placeholder="Material Type" data-rule-required="true">
											<option value="0">Select Material Type</option>
											<option value="1">RM</option>
											<option value="2">SF</option>
										</select>
									</div>

									<label class=" col-sm-3 col-lg-2 control-label">
										Material Name</label>
									<div class="col-sm-6 col-lg-4 controls"
										id="chooseRM">
										<select name="rm_material_name" id="rm_material_name"
											class="form-control" placeholder="Material Name"
											data-rule-required="true">
											<option value="-1">Select Material</option>
											
										</select>
									</div>
									
								</div>

								<div class="form-group">

									<label class="col-sm-3 col-lg-2 control-label">UOM</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="uom" id="uom"  class="form-control"
											placeholder="UOM" data-rule-required="true" />
											
									</div>
									
									<label class="col-sm-3 col-lg-2 control-label">RM Req Qty</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="qty" id="qty" class="form-control"
											placeholder="Qty" data-rule-required="true"
											onKeyPress="return isNumberCommaDot(event)" />
									</div>

								</div>
								
								

								<div class="row">
									<div class="col-md-12" style="text-align: center">
										<input type="button" class="btn btn-info" value="Submit"
											onclick="submitItem()">

									</div>
								</div>

								<div class="clearfix"></div>
								<div class="table-responsive" style="border: 0">
									<table width="100%" class="table table-advance" id="table1">
										<thead>
											<tr>

												<th width="140" style="width: 30px" align="left">Sr No</th>
												<th width="138" align="left">Material Name</th>
												<th width="120" align="left">Material Type</th>
												<th width="120" align="left">Qty</th>
												<th width="120" align="left">Weight</th>
												<th width="120" align="left">Action</th>

											</tr>

										</thead>

										<tbody>

											<c:forEach items="${sfDetailList}" var="sfDetailList"
												varStatus="count">

												<c:choose>
												
												<c:when test="${sfDetailList.delStatus == '0'}">
												<tr>
												
												<td><c:out value="${count.index+1}" /></td>

													<td align="left"><c:out
															value="${sfDetailList.rmName}" /></td>
															
															<c:choose>
															<c:when test="${sfDetailList.rmType == 1}">
															<td align="left"><c:out
															value="RM" /></td>
															</c:when>
															
															<c:when test="${sfDetailList.rmType == 2}">
															<td align="left"><c:out
															value="SF" /></td>
															</c:when>
															</c:choose>
															<td align="left"><c:out
															value="${sfDetailList.rmQty}" /></td>
															
															<td align="left"><c:out
															value="${sfDetailList.rmWeight}" /></td>

													<td>
													
													<input type="button" id="delete" onClick="deleteSfDetail(${count.index})" value="Delete"> <input type="button" id="edit" onClick="editSfDetail(${count.index})" value="Edit">
													
													</td>
													</tr>
												</c:when>
												</c:choose>
													
													<%-- <a
														href="${pageContext.request.contextPath}/delete/${sfDetailList.rmId}"
														class="action_btn"> <abbr title="Delete"><i
																class="fa fa-list"></i></abbr></a> --%>
												
											</c:forEach>

										</tbody>
									</table>
								</div>
								
								<input type="button" class="btn btn-info" value="Submit Items"
											onclick="insertItemDetail()">

							</form>
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
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/common.js"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>


	
<script type="text/javascript">

function insertItemDetail(){
	
	
	$.getJSON('${insertSfItemDetail}',
			{
				ajax : 'true',
}
);
	

}


</script>

	<script type="text/javascript">
$(document).ready(function() { 
	$('#material_type').change(
			function() {
				

	$.getJSON('${getMaterial}', {
		material_type : $(this).val(),
					
					ajax : 'true',
				},  function(data) {
					var html = '<option value="" selected >Select Raw Material</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].id + '">'
								+ data[i].name + '</option>';
					}
					html += '</option>';
					$('#rm_material_name').html(html);
					
				});
			});
			

});
</script>

<script type="text/javascript">
$(document).ready(function() { 
	$('#rm_material_name').change(
			function() {
				alert("mat name changed");
			alert($(this).val());	

	$.getJSON('${getMatUom}', {
		rm_material_name : $(this).val(),
					
					ajax : 'true',
				},  function(data) {
					
	alert(data);
	alert(data.uom);
	var uom=data.uom;

	document.getElementById("uom").setAttribute('value',data.uom);

				})
					
					})
					
				
			});
			


</script>
	
	
</body>
</html>