<%@page import="com.ats.adminpanel.model.franchisee.SubCategory"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page
	import="java.io.*, java.util.*, java.util.Enumeration, java.text.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Configure Franchisee</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

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

<!--basic scripts-->

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
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
<!-- http://forum.springsource.org/showthread.php?110258-dual-select-dropdown-lists -->
<!-- http://api.jquery.com/jQuery.getJSON/ -->

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>
<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
<style type="text/css">
select {
    width: 180px;
    height: 30px;
}
</style>


</head>
<body>

	<c:url var="setAllItemSelected" value="/setAllItemSelected" />

	<c:url var="findItemsByCatId" value="/getCommonByMenuId" />
	<c:url var="findAllMenus" value="/getAllMenus" />
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
						<i class="fa fa-file-o"></i> Franchisee
					</h1>
				</div>
			</div>
			<!-- END Page Title -->


			<!-- BEGIN Main Content -->
			<div class="row">
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-12">
							<div class="box">
								<div class="box-title">
									<h3>
										<i class="fa fa-bars"></i> Configure Franchisee
									</h3>
									<div class="box-tool">
										<a href="${pageContext.request.contextPath}/configureFranchiseesList">Back to
											List</a> <a data-action="collapse" href="#"><i
											class="fa fa-chevron-up"></i></a>
									</div>
								</div>


								<c:set var="allFranchiseeAndMenuList"
									value="${allFranchiseeAndMenuList}" />
								<div class="box-content">
									<form action="addFranchiseeProcess" class="form-horizontal"
										id="validation-form" method="post">





										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Franchisee</label>
											<div class="col-sm-9 col-lg-10 controls">
												<select data-placeholder="Select Franchisee" name="fr_id"
													class="form-control chosen" tabindex="-1" id="fr_id"
													data-rule-required="true">
													<option value=""> </option>
													<optgroup label="All Franchisee">
														<option value=""></option>
														<c:forEach
															items="${allFranchiseeAndMenuList.getAllFranchisee()}"
															var="franchiseeList">
															<option value="${franchiseeList.frId}">${franchiseeList.frName}</option>

														</c:forEach>
													</optgroup>

												</select>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Menu</label>
											<div class="col-sm-9 col-lg-10 controls">
												<select data-placeholder="Select Menu" name="menu"
													class="form-control chosen" tabindex="-1" id="menu"
													data-rule-required="true">
	                                             <optgroup label="All Menus">                                                     

													</optgroup>

												</select>
											</div>
										</div>

											<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Items</label>
											<div class="col-sm-9 col-lg-10 controls">
												<select data-placeholder="Select Items" name="items[]"
													class="form-control chosen" tabindex="-1" id="items" multiple="multiple"
													data-rule-required="true">
                                                   
												</select>
												
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Order Frequency</label>
											<div class="col-sm-9 col-lg-10 controls">
												<select data-placeholder="Select Type" name="typeselector"
													class="form-control chosen" tabindex="-1"
													data-rule-required="true" id="typeselector">
													<option value=""> </option>
													<optgroup label="Types">
														<option value=""></option>
														<option value="1">Daily Basis</option>
														<option value="2">Date Basis</option>
														<option value="3">Day Basis</option>
													</optgroup>

												</select>
											</div>
										</div>

										<div id="2" class="formgroup" style="display: none">
											<label class="col-sm-3 col-lg-2 control-label">Date</label>
											<div class="col-sm-9 col-lg-10 controls">
											<select class="form-control chosen" name="date[]" id="date" multiple="multiple">
													<option value="0">Select Date</option>
													 <c:forEach var = "i" begin = "1" end = "32">
       													<option value="${i}">${i}</option>
                                                     </c:forEach>
												</select>
											</div>
										</div>

										<div id="3" class="formgroup" style="display: none">
											<label class="col-sm-3 col-lg-2 control-label">Day</label>
											<div class="col-sm-9 col-lg-10 controls">
												<select class="form-control chosen" name="day[]" id="day" multiple="multiple">
													<option value="">Select Day</option>
													<option value="1">Sunday</option>
													<option value="2">Monday</option>
													<option value="3">Tuesday</option>
													<option value="4">Wednesday</option>
													<option value="5">Thursday</option>
													<option value="6">Friday</option>
													<option value="7">Saturday</option>
												</select>
											</div>

										</div>

										&nbsp;

										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">From
												Time</label>
											<div class="col-sm-9 col-lg-10 controls">
												<select data-placeholder="From Time" name="frm_time"
													class="form-control chosen" tabindex="-1" id="frm_time"
													data-rule-required="true">
													<option value=""> </option>
													<optgroup label="Select From Time">
														<option value=""></option>



														<%
	                     DateFormat tipe = new SimpleDateFormat("hh:mm a");

	                     Date date = new Date();                      // timestamp now
	                     Calendar cal = Calendar.getInstance();       // get calendar instance
	                     cal.setTime(date);                           // set cal to date
	                     cal.set(Calendar.HOUR_OF_DAY, 23);            // set hour to midnight
	                     cal.set(Calendar.MINUTE,45);                 // set minute in hour
	                     cal.set(Calendar.SECOND, 0);                 // set second in minute
                       	cal.set(Calendar.MILLISECOND, 0);  

                        %>
														<c:forEach var="i" begin="1" end="32">
															<c:set var="time" value="${i}" />
															<c:forEach var="j" begin="1" end="3">


																<% 
                                    cal.add(Calendar.MINUTE, 15);
                                   //out.print(tipe.format(cal.getTime()));
                                   String time=tipe.format(cal.getTime());
                           

                              %>
             			<option value="<%=time%>"><%=time%></option>

															</c:forEach>

														</c:forEach>
													</optgroup>

												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">To
												Time</label>
											<div class="col-sm-9 col-lg-10 controls">
												<select data-placeholder="To Time" name="to_time"
													class="form-control chosen" tabindex="-1" id="to_time"
													data-rule-required="true">
													<optgroup label="Select To Time">
														<option value=""></option>

														<%
	                     DateFormat tipe1 = new SimpleDateFormat("hh:mm a");

	                     Date date1 = new Date();                      // timestamp now
	                     Calendar cal1 = Calendar.getInstance();       // get calendar instance
	                     cal.setTime(date1);                           // set cal to date
	                     cal.set(Calendar.HOUR_OF_DAY, 23);            // set hour to midnight
	                     cal.set(Calendar.MINUTE,45);                 // set minute in hour
	                     cal.set(Calendar.SECOND, 0);                 // set second in minute
                       	cal.set(Calendar.MILLISECOND, 0);  

                        %>
														<c:forEach var="i" begin="1" end="32">
															<c:set var="time" value="${i}" />
															<c:forEach var="j" begin="1" end="3">


																<% 
                                    cal.add(Calendar.MINUTE, 15);
                                   //out.print(tipe.format(cal.getTime()));
                                   String time=tipe.format(cal.getTime());
                           

                              %>




																<option value="<%=time%>"><%=time%></option>


															</c:forEach>

														</c:forEach>
													</optgroup>

												</select>
											</div>
										</div>

										<div class="form-group">
											<div
												class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
												<input type="submit" class="btn btn-primary"
													value="Configure">
												<button type="button" class="btn">Cancel</button>
											</div>
										</div>
									</form>

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
 <script
	src="${pageContext.request.contextPath}/resources/assets/bootstrap/js/bootstrap.min.js"></script> 
<script>
$(function() {
    $('#typeselector').change(function(){
        $('.formgroup').hide();
        $('#' + $(this).val()).show();
    });
});
</script>
<script>
$('#select_all').click(function() {
	alert("alert");
    $('#items option').prop('selected', true);
    $('#items').chosen('destroy').val(["hola","mundo","cruel"]).chosen();
});
</script>

<script type="text/javascript">
$(document).ready(function() {
	
	
    $('#menu').change(
            function() {
            	
                $.getJSON('${findItemsByCatId}', {
                    menuId : $(this).val(),
                    ajax : 'true'
                }, function(data) {
                
                    var len = data.length;

					$('#items')
				    .find('option')
				    .remove()
				    .end()
				 $("#items").append($("<option></option>").attr( "value",-1).text("ALL"));
                    for ( var i = 0; i < len; i++) {
                            
                                
                        $("#items").append(
                                $("<option></option>").attr(
                                    "value", data[i].id).text(data[i].name)
                            );
                    }

                    $("#items").trigger("chosen:updated");
                });
            });
});
</script>


<script type="text/javascript">
$(document).ready(function() { 
	$('#fr_id').change(
			function() {
				$.getJSON('${findAllMenus}', {
					fr_id : $(this).val(),
					ajax : 'true'
				}, function(data) {
					var html = '<option value="">Menu</option>';
				
					var len = data.length;
					
					$('#menu')
				    .find('option')
				    .remove()
				    .end()
				    
				 $("#menu").append(
                                $("<option></option>").attr(
                                    "value", 0).text("Select Menu")
                            );
					
					for ( var i = 0; i < len; i++) {
                        $("#menu").append(
                                $("<option></option>").attr(
                                    "value", data[i].menuId).text(data[i].menuTitle)
                            );
					}
					   $("#menu").trigger("chosen:updated");
				});
			});
});
</script>

<script type="text/javascript">
$(document).ready(function() { // if all label selected set all items selected
	
$('#items').change(
		function () {
			 var selected=$('#items').val();
			 var menu=$('#menu').val();
	
        if(selected==-1){
			$.getJSON('${findItemsByCatId}', {
				 menuId : menu,
				ajax : 'true'
			}, function(data) {
				var html = '<option value="">Items</option>';
			
				var len = data.length;
				
				$('#items')
			    .find('option')
			    .remove()
			    .end()
			
				for ( var i = 0; i < len; i++) {
    
                   $("#items").append(
                           $("<option selected></option>").attr(
                               "value", data[i].id).text(data[i].name)
                       );
				}
		
				   $("#items").trigger("chosen:updated");
			});
  }
});
});



</script>
<script>
$( "#date" ).datepicker({ 
    // Add this line

    stepMonths: 0,
});
</script>
</body>
</html>