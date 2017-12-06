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


<c:url var="getSupplierDetails" value="/getSupplierDetails" />

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
						<i class="fa fa-file-o"></i>Supplier Master
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
								<i class="fa fa-bars"></i>Supplier Detail
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
					
						<div class="row">
					<div class="col-md-12" style="text-align: center" align="center">
					<label class="col-sm-3 col-lg-2 control-label">Supplier Name</label>
					<div class="col-sm-6 col-lg-4 controls">
						 <select  name="supplier" id="supplier"  class="form-control chosen ">
										<option value="-1">Select Suppiler </option>
										<c:forEach items="${supplierList}" var="supplierList"
													varStatus="count">
												<option value="${supplierList.suppId}"><c:out value="${supplierList.suppName}"/></option>
												</c:forEach>
								</select>
</div>

					</div>
				</div>
				 
				<hr> 
								<form  method="post" class="form-horizontal" id=
									"validation-form"
										enctype="multipart/form-data" method="post">
							
						<div class="form-group">
										<label class="col-sm-3 col-lg-2 control-label">Supplier Name</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" id="supp_name" name="supp_name" class="form-control"placeholder="Enter Supplier Name "data-rule-required="true" disabled="true"/>
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Supplier GSTIN </label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_gstin" id="supp_gstin" class="form-control" placeholder="Enter GSTIN " data-rule-required="true"disabled="true"/>
									</div>
								 
								</div>
							 
								
								
								<div class="form-group">
										<label class="col-sm-3 col-lg-2 control-label">Address</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_addr" id="supp_addr" class="form-control" placeholder="Address" data-rule-required="true"disabled="true"/>
									</div>

									<label class="col-sm-3 col-lg-2 control-label">City </label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_city" id="supp_city" class="form-control"  placeholder="Enter City "data-rule-required="true"disabled="true"/>
									</div>
								 
								</div>
							
								
								<div class="form-group">
										<label class="col-sm-3 col-lg-2 control-label">State</label>
									<div class="col-sm-6 col-lg-4 controls">
										 <!-- <select  name="supp_state"  class="form-control"> 
										<option value="1">Maharashtra</option>
								</select>-->
								<input type="text" name="supp_state" id="supp_state" class="form-control"  placeholder="Enter State "data-rule-required="true"disabled="true"/>
									
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Country </label>
									<div class="col-sm-6 col-lg-4 controls">
										  <!--<select  name="supp_country"  class="form-control">
										<option value="1">India</option>
								</select>-->
								<input type="text" name="supp_country" id="supp_country" class="form-control"  placeholder="Enter County "data-rule-required="true" disabled="true"/>
								
									</div>
								 
								</div>
								 	<br />
							 
								
								
								<div class="form-group">
										<label class="col-sm-3 col-lg-2 control-label">Mobile 1</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_mob1" id="supp_mob1" class="form-control" placeholder="Emter Mobile 1 " data-rule-required="true" data-rule-minlength="10"disabled="true"
													data-rule-maxlength="10"
													onKeyPress="return isNumberCommaDot(event)" />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Email 1</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="email" name="supp_email1" id="supp_email1" class="form-control"  placeholder="Emter Email 1 "data-rule-required="true"disabled="true"
													data-rule-email="true"  />
									</div>
								 
								</div>
							 
								
							 <div class="form-group">
										<label class="col-sm-3 col-lg-2 control-label">Mobile 2</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_mob2" id="supp_mob2" class="form-control" placeholder="Emter Mobile 2 " data-rule-required="true" data-rule-minlength="10"disabled="true"
													data-rule-maxlength="10"
													onKeyPress="return isNumberCommaDot(event)"  />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Email 2</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="email" name="supp_email2" id="supp_email2" class="form-control"placeholder="Emter Email 2 "data-rule-required="true"disabled="true"
													data-rule-email="true"  />
									</div>
								 
								</div>
								 <div class="form-group">
										<label class="col-sm-3 col-lg-2 control-label">Mobile 3</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_mob3" id="supp_mob3" class="form-control" placeholder="Emter Mobile 3 "data-rule-required="true" data-rule-minlength="10"disabled="true"
													data-rule-maxlength="10"
													onKeyPress="return isNumberCommaDot(event)"   />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Email 3</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="email" name="supp_email3" id="supp_email3" class="form-control"placeholder="Emter Email 3 " data-rule-required="true"disabled="true"
													data-rule-email="true" />
									</div>
								 
								</div>
							 
									<div class="form-group">
										<label class="col-sm-3 col-lg-2 control-label">Phone 1</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_phone1" id="supp_phone1" class="form-control" placeholder="Emter Phone 1 "data-rule-required="true" data-rule-minlength="10"disabled="true"
													data-rule-maxlength="11"
													onKeyPress="return isNumberCommaDot(event)" />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Email 4</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="email" name="supp_email4" id="supp_email4" class="form-control" placeholder="Emter Email 4 "data-rule-required="true"disabled="true"
													data-rule-email="true" />
									</div>
								 
								</div>
								 
								<div class="form-group">
										<label class="col-sm-3 col-lg-2 control-label">Phone 2</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_phone2" id="supp_phone2" class="form-control" placeholder="Emter Phone 2 "data-rule-required="true" data-rule-minlength="10"disabled="true"
													data-rule-maxlength="11"
													onKeyPress="return isNumberCommaDot(event)" />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Email 5</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="email" name="supp_email5" id="supp_email5" class="form-control"placeholder="Emter Email 5 "data-rule-required="true"disabled="true"
													data-rule-email="true"  />
									</div>
								 
								</div>
							 	<br />
								<br />
							
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Supplier Contact Person</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_c_person" id="supp_c_person" class="form-control" placeholder="Supplier contact person" data-rule-required="true"disabled="true"/>
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Supplier Pan No
									</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_panno" id="supp_panno" class="form-control"  placeholder="Supplier Pan No"data-rule-required="true"disabled="true"/>
									</div>

								</div>
								 
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Supplier FDA Lic </label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_fdalic" id="supp_fdalic" class="form-control" placeholder="Supplier FDA Lic"data-rule-required="true"disabled="true" />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Supplier Pay Id</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_pay_id" id="supp_pay_id" class="form-control" placeholder=" Supplier Pay Id" onKeyPress="return isNumberCommaDot(event)" data-rule-required="true"disabled="true"/>
									</div>
								</div>
							 
								<div class="form-group" >
									<label class="col-sm-3 col-lg-2 control-label">Supplier Credit days</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="supp_credit_days" id="supp_credit_days" class="form-control"  placeholder="Supplier Credit days" onKeyPress="return isNumberCommaDot(event)" data-rule-required="true"disabled="true"/>
									</div>
										<input type="hidden" name="supp_id" id="supp_id" />
									
							</div>
								<br />
								
								 <div class="row">
					<div class="col-md-12" style="text-align: center">
						<input type="button" class="btn btn-success" value="Submit" id="btn_submit" disabled="true"">
						<input type="button" class="btn btn-info" value="Edit" id="edit" onclick="editClick()">
						<input type="button" class="btn btn-danger" value="Delete" id="delete" onclick="deleteClick()">
					 


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
$(document).ready(function() { 
	$('#supplier').change(
			function() {
				$.getJSON('${getSupplierDetails}', {
					selectedSupplier : $(this).val(),
					ajax : 'true'
				}, function(data) {
				 
					document.getElementById("supp_name").value=data.suppName;
					document.getElementById("supp_city").value=data.suppCity;
					document.getElementById("supp_state").value=data.suppState;
					document.getElementById("supp_country").value=data.suppCountry;
					document.getElementById("supp_addr").value=data.suppAddr;
					document.getElementById("supp_gstin").value=data.suppGstin;
					document.getElementById("supp_mob1").value=data.suppMob1;
					document.getElementById("supp_mob2").value=data.suppMob2;
					document.getElementById("supp_mob3").value=data.suppMob3;
					document.getElementById("supp_phone1").value=data.suppPhone1;
					document.getElementById("supp_phone2").value=data.suppPhone2;
					document.getElementById("supp_email1").value=data.suppEmail1;
					document.getElementById("supp_email2").value=data.suppEmail2;
					document.getElementById("supp_email3").value=data.suppEmail3;
					document.getElementById("supp_email4").value=data.suppEmail4;
					document.getElementById("supp_email5").value=data.suppEmail5;
					document.getElementById("supp_c_person").value=data.suppCPerson;
					document.getElementById("supp_fdalic").value=data.suppFdaLic;
					document.getElementById("supp_pay_id").value=data.suppPayId;
					document.getElementById("supp_credit_days").value=data.suppCreditDays;
					document.getElementById("supp_panno").value=data.suppPanNo;
					document.getElementById("supp_id").value=data.suppId;
					//$('#supp_name').html('value='+data.suppName);
					//$('#supp_name').formcontrol('refresh');

				});
			});
});

function editClick()
{

	document.getElementById("edit").disabled = true;
	document.getElementById("btn_submit").disabled = false;
	document.getElementById("supp_panno").disabled = false;
	document.getElementById("supp_credit_days").disabled = false;
	document.getElementById("supp_pay_id").disabled = false;
	document.getElementById("supp_fdalic").disabled = false;
	document.getElementById("supp_c_person").disabled = false;
	document.getElementById("supp_email5").disabled = false;
	document.getElementById("supp_email4").disabled = false;
	document.getElementById("supp_email3").disabled = false;
	document.getElementById("supp_email2").disabled = false;
	document.getElementById("supp_email1").disabled = false;
	document.getElementById("supp_phone2").disabled = false;
	document.getElementById("supp_phone1").disabled = false;
	document.getElementById("supp_mob3").disabled = false;
	document.getElementById("supp_mob2").disabled = false;
	document.getElementById("supp_mob1").disabled = false;
	document.getElementById("supp_gstin").disabled = false;
	document.getElementById("supp_addr").disabled = false;
	document.getElementById("supp_country").disabled = false;
	document.getElementById("supp_state").disabled = false;
	document.getElementById("supp_city").disabled = false;
	document.getElementById("supp_name").disabled = false;
	
	}
	
$('#btn_submit').click(function(){
    var form = document.getElementById("validation-form")
    form.action ="addSupplier";
    form.submit();
});

$('#delete').click(function(){
    var form = document.getElementById("validation-form")
    form.action = "deleteSupplier";
    var r = confirm("You want to delete Supplier!");
    if (r == true) {
        form.submit();
    }

});
</script>

</body>
</html>