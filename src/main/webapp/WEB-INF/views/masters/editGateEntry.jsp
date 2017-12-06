<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



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
<body onload="startTime()">


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
						<i class="fa fa-file-o"></i>Gate Entry
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
								<i class="fa fa-bars"></i>Edit Gate Entry
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/gateEntries">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>




						<div class="box-content">
							<form action="${pageContext.request.contextPath}/addTransporterProcess" class="form-horizontal"
								method="post" id="validation-form">

                  <div class="form-group">
								<div class="col1">
								 <label class="col-sm-1 col-lg-2 control-label" for="Inward No">InwardNo:</label>
								  <label class="col-sm-1 col-lg-1 control-label" for="Inward No">101</label>
								</div>
                               <div class="col1">
									<label class="col-sm-1 col-lg-1 control-label" for="Date">Date:</label>
									<label class="col-sm-1 col-lg-2 control-label" for="Date">
                                     <jsp:useBean id="now" class="java.util.Date"/>    
                                     <fmt:formatDate value="${now}" pattern="dd-MMM-yyyy"/>
                                   </label>
								</div>
								 <div class="col1">
									<label class="col-sm-1 col-lg-1 control-label" for="Time">Time:</label>
                                    <label class="col-sm-1 col-lg-2 control-label" for="Date"> <div id="txt"></div></label>			
 					            </div>
								<div class="col1">
									

								</div>
                             
                   </div>
                    
                                 <div class="col1">
									<label class="col-sm-1 col-lg-2 control-label" for="Supplier">Supplier</label>
									<div class="col-sm-1 col-lg-3 controls">
										<select data-placeholder="Select Supplier"
											class="form-control chosen" name="supp_id" tabindex="-1"
											id="supp_id" data-rule-required="true">
											<option selected>Select Supplier</option>
											
											<c:forEach items="${supplierList}" var="supplierList">
                                              
                                              
													   <c:choose>
													<c:when test="${supplierList.suppId==gateEntry.suppId}">
														<option selected value="${supplierList.suppId}"><c:out value="${supplierList.suppName}"></c:out> </option>
													</c:when>
													<c:otherwise>
														<option value="${supplierList.suppId}"><c:out value="${supplierList.suppName}"></c:out> </option>
													</c:otherwise>
												</c:choose>

											</c:forEach>
											</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-1 col-lg-2 control-label" for="Vehicle No">Vehicle No</label>
									<div class="col-sm-1 col-lg-3 controls">
										<input type="text" name="vehicle_no" id="vehicle_no"
											placeholder="Vehicle No" class="form-control"
											data-rule-required="true" />
									</div>
								</div>
								<div class="col1">
									<label class="col-sm-1 col-lg-2 control-label" for="LR No">LR No</label>
									<div class="col-sm-1 col-lg-3 controls">
										<input type="text" name="lr_no" id="lr_no"
											placeholder="LR No" class="form-control"
											data-rule-required="true"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-1 col-lg-2 control-label" for="Transporter">Transporter</label>
									<div class="col-sm-1 col-lg-3 controls">
										<select data-placeholder="Select Transporter"
											class="form-control chosen" name="tran_id" tabindex="-1"
											id="tran_id" data-rule-required="true">
											<option selected>Select Transporter</option>
											
											<c:forEach items="${transporterList}" var="transporterList">
                                               <c:choose>
													<c:when test="${transporterList.tranId==gateEntry.tranId}">
														<option selected value="${transporterList.tranId}"><c:out value="${transporterList.tranName}"></c:out> </option>
													</c:when>
													<c:otherwise>
														<option value="${transporterList.tranId}"><c:out value="${transporterList.tranName}"></c:out> </option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
											</select>
									</div>
								</div>	
								 <div class="col1">
									<label class="col-sm-1 col-lg-2 control-label" for="Description of Goods">Description</label>
									<div class="col-sm-1 col-lg-3 controls">
										<textarea type="text" name="description_of_goods" id="description_of_goods"
											placeholder="Description of Goods" class="form-control"
											data-rule-required="true"></textarea>
									</div>
								</div>
								 <div class="form-group">
									<label class="col-sm-1 col-lg-2 control-label" for="No of Items">No of Items</label>
									<div class="col-sm-1 col-lg-3 controls">
										<input type="text" name="no_of_items" id="no_of_items"
											placeholder="No of Items" class="form-control"
											data-rule-required="true" />
									</div>
								</div>
								 <div class="col1">
									<label class="col-sm-1 col-lg-2 control-label" for="Description of Goods">Remark</label>
									<div class="col-sm-1 col-lg-3 controls">
										<textarea type="text" name="remark" id="remark"
											placeholder="Remark" class="form-control"
											data-rule-required="true"></textarea>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-1 col-lg-2 control-label" for="Tot.Qty">Tot.Qty</label>
									<div class="col-sm-1 col-lg-3 controls">
										<input type="text" name="tot_qty" id="tot_qty"
											placeholder="Tot.Qty" class="form-control"
											data-rule-required="true" />
									</div>
								</div>
									<div class="col1">
									<label class="col-sm-2 col-lg-2 control-label">Image</label>
									<div class="col-sm-2 col-lg-4 controls">
										<div class="fileupload fileupload-new"
											data-provides="fileupload">
											<div class="fileupload-new img-thumbnail"
												style="width: 200px; height: 150px;">
												<img
													src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image"
													alt="" />
											</div>
											<div
												class="fileupload-preview fileupload-exists img-thumbnail"
												style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
											<div>
												<span class="btn btn-default btn-file"><span
													class="fileupload-new">Select image</span> <span
													class="fileupload-exists">Change</span> <input type="file"
													class="file-input" name="image1" id="image2"
													 /></span> <a href="#"
													class="btn btn-default fileupload-exists"
													data-dismiss="fileupload">Remove</a>
											</div>
										</div>

									</div>
								</div>
									<div class="form-group">
									<label class="col-sm-2 col-lg-1 control-label">Image</label>
									<div class="col-sm-2 col-lg-4 controls">
										<div class="fileupload fileupload-new"
											data-provides="fileupload">
											<div class="fileupload-new img-thumbnail"
												style="width: 200px; height: 150px;">
												<img
													src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image"
													alt="" />
											</div>
											<div
												class="fileupload-preview fileupload-exists img-thumbnail"
												style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
											<div>
												<span class="btn btn-default btn-file"><span
													class="fileupload-new">Select image</span> <span
													class="fileupload-exists">Change</span> <input type="file"
													class="file-input" name="image2" id="image2"
													 /></span> <a href="#"
													class="btn btn-default fileupload-exists"
													data-dismiss="fileupload">Remove</a>
											</div>
										</div>

									</div>
								</div>	
								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Submit">
<!-- 										<button type="button" class="btn">Cancel</button>
 -->									</div>
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
<script>
function startTime() {
    var today = new Date();
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
    m = checkTime(m);
    s = checkTime(s);
    document.getElementById('txt').innerHTML =
    h + ":" + m + ":" + s;
    var t = setTimeout(startTime, 500);
}
function checkTime(i) {
    if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
    return i;
}
</script>
</body>
</html>