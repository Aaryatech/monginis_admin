<%@page import="org.w3c.dom.Document"%>
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

<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>


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


</head>
<body onload="myFunction()">



	<c:url var="findItemsByCatId" value="/getItemsByCatID" />
	<c:url var="findAllMenus" value="/getAllMenu" />
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
										<i class="fa fa-bars"></i> Update Configured Franchisee
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
									<form action="updateFranchiseeProcess" class="form-horizontal"
										id="validation-form" method="post">

										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Franchisee</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="fr_id" id="fr_id" value="${frName}"
													placeholder="Franchisee Name" class="form-control"
													data-rule-required="true" readonly="readonly" /> <input
													type="hidden" id="settingId" name="settingId"
													value="${franchiseeList.settingId}" />
											</div>
										</div>





										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Menu</label>
											<div class="col-sm-9 col-lg-10 controls">
												<input type="text" name="menu" id="menu" value="${menuName}"
													placeholder="Franchisee Name" class="form-control"
													data-rule-required="true" readonly="readonly" />
											</div>
										</div>


										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Items</label>
											<div class="col-sm-9 col-lg-10 controls">
												<select data-placeholder="Select Items"
													class="form-control chosen" multiple="multiple"
													tabindex="6" name="items[]" id="items[]">
													<option value=""> </option>
													<optgroup label="ITEMS">
													</optgroup>
                                                    
                                                	 	<c:choose>
											 <c:when test="${catId==5}">
													
                                                       <c:forEach items="${selectedItems}" var="selectedItem">
															<option selected value="${selectedItem.spId}">${selectedItem.spCode}-${selectedItem.spName}</option>
														</c:forEach>

														<c:forEach items="${remItems}" var="remItem">

															<option value="${remItem.spId}"><c:out value="${remItem.spCode}-${remItem.spName}"></c:out></option>

														</c:forEach>

												</c:when>
												<c:otherwise>
												
												      <c:forEach items="${selectedItems}" var="selectedItem">
															<option selected value="${selectedItem.id}">${selectedItem.itemName}</option>
														</c:forEach>
														<c:forEach items="${remItems}" var="remItem">
															<option value="${remItem.id}"><c:out value="${remItem.itemName}"></c:out></option>
														</c:forEach>
												</c:otherwise> 
												      
											</c:choose>

												</select>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Order Frequency</label>
											<div class="col-sm-9 col-lg-10 controls">
												<select data-placeholder="Select Type" name="typeselector"
													class="form-control chosen" tabindex="-1"
													data-rule-required="true" id="typeselector" onselect="changeSettings()">
													<option value=""> </option>
													<optgroup label="Types">
														<option value=""></option>

														<c:choose>
															<c:when test="${settingType==1}">
																<option selected value="1">Daily</option>
																<option value="2">Date</option>
																<option value="3">Day</option>
															</c:when>

															<c:when test="${settingType==2}">
																<option value="1">Daily</option>
																<option selected value="2">Date</option>
																<option value="3">Day</option>
																
															</c:when>

															<c:when test="${settingType==3}">
																<option value="1">Daily</option>
																<option value="2">Date</option>
																<option selected value="3">Day</option>

																
															</c:when>

														</c:choose>

													</optgroup>

												</select>
											</div>
										</div>
										
											<div id="2" class="formgroup" style="display: none">
											<label class="col-sm-3 col-lg-2 control-label">Date</label>
											<div class="col-sm-9 col-lg-10 controls">
												<c:set var="sel" value=""/>
												<select class="form-control chosen" name="date[]" id="date" multiple="multiple">
													<option value="0">Select Date</option>
													 <c:forEach items="${remDate}" var="remDate">
													 <c:set var="sel" value=""/>
													   <c:forEach items="${date}" var="date">
													   
       													<c:choose>
       													<c:when test="${remDate==date}">
       													 <c:set var="sel" value="selected"/>
       													
       													</c:when>
       												
       													</c:choose>
                                                     </c:forEach>
       													 <option value="${remDate}" <c:out value="${sel}"/>>${remDate}</option>
													
                                                     
                                                     </c:forEach>
                                                    
												</select>	
											</div>
										</div>
										
										<div id="3" class="formgroup" style="display: none">
											<label class="col-sm-3 col-lg-2 control-label">Day</label>
											<div class="col-sm-9 col-lg-10 controls">
													<c:set var="sel1" value=""/>
											
												<select class="form-control chosen" name="day[]" id="day" multiple="multiple">

                                                       <c:forEach items="${allDays}" var="allDays">
													 <c:set var="sel1" value=""/>
                                                     <c:forEach items="${frDay}" var="day">
                                                         
                                                         <c:choose>
       													<c:when test="${allDays.key==day}">
       													 <c:set var="sel1" value="selected"/>
       													
       													</c:when>
       												
       													</c:choose>
                                                    </c:forEach>
                                                              <option value="${allDays.key}" <c:out value="${sel1}"/>>${allDays.value}</option>
                                                    
                                                     </c:forEach>

													<%-- <c:choose>
														<c:when test="${frDay=='1'}">
															<option selected value="1">Sunday</option>
															<option value="2">Monday</option>
															<option value="3">Tuesday</option>
															<option value="4">Wednesday</option>
															<option value="5">Thursday</option>
															<option value="6">Friday</option>
															<option value="7">Saturday</option>
														</c:when>


														<c:when test="${frDay=='2'}">
															<option value="1">Sunday</option>
															<option selected value="2">Monday</option>

															<option value="3">Tuesday</option>
															<option value="4">Wednesday</option>
															<option value="5">Thursday</option>
															<option value="6">Friday</option>
															<option value="7">Saturday</option>
														</c:when>

														<c:when test="${frDay=='3'}">
															<option value="1">Sunday</option>
															<option value="2">Monday</option>


															<option selected value="3">Tuesday</option>

															<option value="4">Wednesday</option>
															<option value="5">Thursday</option>
															<option value="6">Friday</option>
															<option value="7">Saturday</option>
														</c:when>

														<c:when test="${frDay=='4'}">
															<option value="1">Sunday</option>
															<option value="2">Monday</option>
															<option value="3">Tuesday</option>

															<option selected value="4">Wednesday</option>


															<option value="5">Thursday</option>
															<option value="6">Friday</option>
															<option value="7">Saturday</option>
														</c:when>


														<c:when test="${frDay=='5'}">
															<option value="1">Sunday</option>
															<option value="2">Monday</option>
															<option value="3">Tuesday</option>
															<option value="4">Wednesday</option>

															<option selected value="5">Thursday</option>



															<option value="6">Friday</option>
															<option value="7">Saturday</option>
														</c:when>

														<c:when test="${frDay=='6'}">
															<option value="1">Sunday</option>
															<option value="2">Monday</option>
															<option value="3">Tuesday</option>
															<option value="4">Wednesday</option>
															<option value="5">Thursday</option>

															<option selected value="6">Friday</option>



															<option value="7">Saturday</option>
														</c:when>

														
														<c:otherwise>
															<option value="1">Sunday</option>
															<option value="2">Monday</option>
															<option value="3">Tuesday</option>
															<option value="4">Wednesday</option>
															<option value="5">Thursday</option>

															<option value="6">Friday</option>


															<option selected value="7">Saturday</option>
															</c:otherwise>
														


													</c:choose> --%>
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

													<optgroup label="Select From Time">

														<%
															DateFormat tipe = new SimpleDateFormat("hh:mm a");

															Date date = new Date(); // timestamp now
															Calendar cal = Calendar.getInstance(); // get calendar instance
															cal.setTime(date); // set cal to date
															cal.set(Calendar.HOUR_OF_DAY, 23); // set hour to midnight
															cal.set(Calendar.MINUTE, 45); // set minute in hour
															cal.set(Calendar.SECOND, 0); // set second in minute
															cal.set(Calendar.MILLISECOND, 0);
														%>
														<c:forEach var="i" begin="1" end="32">
															<c:set var="time" value="${i}" />
															<c:forEach var="j" begin="1" end="3">


																<%
																	cal.add(Calendar.MINUTE, 15);
																			//out.print(tipe.format(cal.getTime()));
																			String time = tipe.format(cal.getTime());
																%>

																<c:set var="fTime" value="${fromTime}">
																</c:set>
																<%
																	String strFTime = (String) pageContext.getAttribute("fTime");
																			if (strFTime.equalsIgnoreCase(time)) {
																%>

																<option selected value="${fromTime}">${fromTime}</option>

																<%
																	} else {
																%>


																<option value="<%=time%>"><%=time%></option>

																<%
																	}
																%>
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

															Date date1 = new Date(); // timestamp now
															Calendar cal1 = Calendar.getInstance(); // get calendar instance
															cal.setTime(date1); // set cal to date
															cal.set(Calendar.HOUR_OF_DAY, 23); // set hour to midnight
															cal.set(Calendar.MINUTE, 45); // set minute in hour
															cal.set(Calendar.SECOND, 0); // set second in minute
															cal.set(Calendar.MILLISECOND, 0);
														%>
														<c:forEach var="i" begin="1" end="32">
															<c:set var="time" value="${i}" />
															<c:forEach var="j" begin="1" end="3">


																<%
																	cal.add(Calendar.MINUTE, 15);
																			//out.print(tipe.format(cal.getTime()));
																			String time = tipe.format(cal.getTime());
																%>


																<c:set var="tTime" value="${toTime}">
																</c:set>
																<%
																	String strTTime = (String) pageContext.getAttribute("tTime");
																			if (strTTime.equalsIgnoreCase(time)) {
																%>

																<option selected value="${toTime}">${toTime}</option>

																<%
																	} else {
																%>


																<option value="<%=time%>"><%=time%></option>

																<%
																	}
																%>

																<%-- <option value="<%=time%>"><%=time%></option> --%>


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


	<%-- <script>
function preferedTest() {
    var prefer = document.forms[0].test.value;
/*     alert("You selected " + prefer);
*/    



   if(prefer=="menu1"){
   <%
   stArray=new String[1];
   %>
   testSelect = document.getElementById('testSelect');
   var i;
   for(i = testSelect.options.length - 1 ; i >= 0 ; i--)
   {
       testSelect.remove(i);
   }
   testSelect.options[testSelect.options.length] = new Option('1 Submenu1', 'Value1'); 
   testSelect.options[testSelect.options.length] = new Option('1 Submenu2', 'Value2');

}else if(prefer=="menu2"){
     <%
        stArray=new String[2];
        %>
        testSelect = document.getElementById('testSelect');
        var i;
        for(i = testSelect.options.length - 1 ; i >= 0 ; i--)
        {
            testSelect.remove(i);
        }        testSelect.options[testSelect.options.length] = new Option('2 Submenu 1', 'Value1');
        testSelect.options[testSelect.options.length] = new Option('2 Submenu 2', 'Value2');

}else{
    
    <%
   stArray=new String[2];
   %>
   testSelect = document.getElementById('testSelect');
   var i;
   for(i = testSelect.options.length - 1 ; i >= 0 ; i--)
   {
       testSelect.remove(i);
   }        testSelect.options[testSelect.options.length] = new Option('3 Submenu 1', 'Value1');
   testSelect.options[testSelect.options.length] = new Option('3 Submenu 2', 'Value2');
}

}



/* function preferedMenu() {
    var prefer = document.forms[0].menu.value;
/*     alert("You selected " + prefer);
 */ 

 
 var i;
 for(i = subCat.options.length - 1 ; i >= 0 ; i--)
 {
	 subCat.remove(i);
 }   
 var myList = new Array();
 
/*  <c:forEach items="${allFranchiseeAndMenuList.getSubCategories()}" var="sub" varStatus="loop">
    myList[${loop.index}] = "${sub}";
    
 </c:forEach> */
 
 
 /* <c:forEach
	items="${allFranchiseeAndMenuList.allMenu()}"
	var="Menu">
	alert("You selected " + var); */
</c:forEach> */


}


</script> --%>
	<script>
		$(function() {
			$('#typeselector').change(function() {
				$('.formgroup').hide();
				$('#' + $(this).val()).show();
			});
		});
	</script>


<script>
		$(function myFunction() {
			
		var	type =document.forms[0].typeselector.value
		
				$('.formgroup').hide();
				$('#' + type).show();
		
		});
	</script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#menu')
									.change(
											function() {
												$
														.getJSON(
																'${findItemsByCatId}',
																{
																	mainCatId : $(
																			this)
																			.val(),
																	ajax : 'true'
																},
																function(data) {
																	var html = '<option value="">Items</option>';

																	var len = data.length;
																	for (var i = 0; i < len; i++) {
																		html += '<option value="' + data[i].itemId + '">'
																				+ data[i].itemName
																				+ '</option>';
																	}
																	html += '</option>';
																	$('#items')
																			.html(
																					html);
																	$('#items')
																			.formcontrol(
																					'refresh');

																});
											});
						});
	</script>



	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$
									.getJSON(
											'${findAllMenus}',
											{
												ajax : 'true'
											},
											function(data) {
												var html = '<option value="">Menu</option>';
												var len = data.length;
												for (var i = 0; i < len; i++) {
													html += '<option value="' + data[i].mainCatId + '">'
															+ data[i].menuTitle
															+ '</option>';
												}
												html += '</option>';
												$('#menu').html(html);
												$('#menu').form
														- control('refresh');

											});

						});
	</script>


</body>
</html>