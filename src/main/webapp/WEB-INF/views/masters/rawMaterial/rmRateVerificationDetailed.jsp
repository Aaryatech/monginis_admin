<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>




	<c:url var="getRmRateVerification" value="/getRmRateVerification" />
	<c:url var="getUomTax" value="/getUomTax" />


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
						<i class="fa fa-file-o"></i>Raw Material Master
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
								<i class="fa fa-bars"></i>Raw material Rate Verification
							</h3>
							<div class="box-tool">
								<a href="">Back to List</a> <a data-action="collapse" href="${pageContext.request.contextPath}/showRmRateVerificationList"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							<!-- <div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> <a data-action="close" href="#"><i
									class="fa fa-times"></i></a>
							</div> -->
						</div>


						<div class="box-content">
							<form action="${pageContext.request.contextPath}/submitRmRateVerificationDetailed" method="post"
								class="form-horizontal" id="validation-form"
								enctype="multipart/form-data" method="post">

								<div class="form-group">

									<label class="col-sm-3 col-lg-2 control-label">Select
										Supplier</label>
									<div class="col-sm-6 col-lg-4 controls"> 
									<c:forEach items="${supplierList}" var="supplierList">
										<c:choose>
											<c:when test="${rmRateVerification.suppId==supplierList.suppId}"> 
											<input type="text" name="supp_id" id="supp_id" value="${supplierList.suppName}" class="form-control" readonly/>
											</c:when>
										
										</c:choose>
									</c:forEach>
										
									</div>
									<label class="col-sm-3 col-lg-2 control-label">Select
										Row Material</label>
									<div class="col-sm-6 col-lg-4 controls">
											<c:forEach items="${RawmaterialList}" var="RawmaterialList">
										<c:choose>
											<c:when test="${rmRateVerification.rmId==RawmaterialList.rmId}"> 
											<input type="text" name="supp_id" id="supp_id" value="${RawmaterialList.rmName}" class="form-control" readonly/>
											</c:when>
										
										</c:choose>
									</c:forEach>
									</div>



								</div>
								 
								<hr>



								<div class="form-group">

									<label class="col-sm-3 col-lg-2 control-label">Select
										Rate Date </label>
									<div class="col-sm-6 col-lg-4 controls">
										<input class="form-control date-picker" id="dp2" size="16"
											type="text" name="curr_rate_date" data-rule-required="true" />
									</div>

 
								</div>


								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label"> </label>
									<div class="col-sm-6 col-lg-4 controls" align="center">
										<label class=" "><b>Tax Rate Extra</b></label>
									</div>

									<!-- <label class="col-sm-3 col-lg-2 control-label">  </label> -->
									<div class="col-sm-6 col-lg-4 controls" align="center">
										<label class=" "><b>Tax Rate Include </b></label>
									</div>
								</div>

								<div class="form-group">

									<label class="col-sm-3 col-lg-2 control-label">Current
										Rate </label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="curr_rate_tax_extra"
											id="curr_rate_tax_extra" class="form-control"
											placeholder="Tax Rate Extra" data-rule-required="true"
											onKeyPress="return isNumberCommaDot(event)" />
									</div>


									<!-- <label class="col-sm-3 col-lg-2 control-label"></label> -->
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="curr_rate_tax_incl"
											id="curr_rate_tax_incl" class="form-control"
											placeholder="Tax Rate Inclusive" data-rule-required="true"
											onKeyPress="return isNumberCommaDot(event)" />
									</div>

								</div>

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label" id="date1">${rmRateVerification.rateDate}
									</label> <input type="hidden" name="rate_date" id="rate_date" value="${rmRateVerification.rateDate}">
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="rate_tax_extra" id="rate_tax_extra"
											class="form-control" value="${rmRateVerification.rateTaxExtra}" disabled="disabled" />
											<input type="hidden" name="tax_extra" id="tax_extra" value="${rmRateVerification.rateTaxExtra}">
									</div>

									<!-- <label class="col-sm-3 col-lg-2 control-label"> </label> -->
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="rate_tax_incl" id="rate_tax_incl"
											class="form-control" value="${rmRateVerification.rateTaxIncl}" disabled="disabled" />
											<input type="hidden" name="tax_incl" id="tax_incl" value="${rmRateVerification.rateTaxIncl}">
									</div>

								</div>

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label" id="date2">${rmRateVerification.date1}
									</label> <input type="hidden" name="rate_date1" id="rate_date1">
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" value="${rmRateVerification.rateTaxExtra1}" name="rate_tax_extra1" id="rate_tax_extra1"
											class="form-control" value="" disabled="disabled" />
											<input type="hidden" name="tax_extra1" id="tax_extra1">
									</div>

									<!-- <label class="col-sm-3 col-lg-2 control-label">Last Date 1 Tax Rate Extra</label> -->
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="rate_tax_incl1" id="rate_tax_incl1"
											class="form-control" value="${rmRateVerification.rateTaxIncl1}" disabled="disabled" />
											<input type="hidden" name="tax_incl1" id="tax_incl1">
									</div>
								</div>

								<div class="form-group">

									<label class="col-sm-3 col-lg-2 control-label" id="date3">${rmRateVerification.date2}
									</label> <input type="hidden" name="rate_date2" id="rate_date2">
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" value="${rmRateVerification.rateTaxExtra2}" name="rate_tax_extra2" id="rate_tax_extra2"
											class="form-control" disabled="disabled" />
									</div>


									<!-- <label class="col-sm-3 col-lg-2 control-label">Last Date 2</label> -->
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" value="${rmRateVerification.rateTaxIncl2}" name="rate_tax_incl2" id="rate_tax_incl2"
											class="form-control" disabled="disabled" />
									</div>
								</div>
								<input type="hidden" name="rm_rate_ver_id" id="rm_rate_ver_id">

								<div class="row">
									<div class="col-md-12" style="text-align: center">
										<input type="submit" class="btn btn-primary" value="Submit">
										<input type="button" id="search" class="btn btn-info"
											value="Edit" onclick="onEdit()" />
											<input type="button" id="cancel" class="btn btn-info"
											value="Cancel" onclick="addRate()" disabled/>


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

	<script type="text/javascript">
function onEdit()
{	
	

	document.getElementById("dp2").value=document.getElementById("rate_date").value; 
	document.getElementById("curr_rate_tax_extra").value=document.getElementById("tax_extra").value; 
	document.getElementById("curr_rate_tax_incl").value=document.getElementById("tax_incl").value;
	document.getElementById("dp2").disabled=true;
	document.getElementById("cancel").disabled=false;
	
	 
}

function addRate()
{	
	

	document.getElementById("dp2").value="";
	document.getElementById("curr_rate_tax_extra").value="";
	document.getElementById("curr_rate_tax_incl").value="";
	document.getElementById("dp2").disabled=false;
	document.getElementById("cancel").disabled=true;
	
	 
}
</script>
</body>
</html>