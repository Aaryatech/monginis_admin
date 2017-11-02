<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
	<c:url var="frItemStockConfigurationProcess"
		value="/frItemStockConfigurationProcess" />

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
						<i class="fa fa-file-o"></i>Franchisee Item Configuration
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


								<div class="row">
									<div class="col-md-12">

										<div class="box">
											<div class="box-title">
												<h3>
													<i class="fa fa-table"></i> Item Configuration
												</h3>
												<div class="box-tool">
													<a data-action="collapse" href="#"><i
														class="fa fa-chevron-up"></i></a>
													<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
												</div>
											</div>
											`


											<div class="box-content">
												<form action="getItemsbyCatIdProcess" name="validation-from"
													id="validation-from" method="get">


													<div class="form-group">
														<label class="col-sm-3 col-lg-2 control-label">Category</label>
														<div class="col-sm-9 col-lg-10 controls">
															<select data-placeholder="Select Category"
																class="form-control chosen" tabindex="6" name="cat_name"
																id="cat_name">

																<%-- <%
																int cId;
																request.setAttribute("cId", "${catId}");
																
																%> --%>
																<c:forEach items="${ItemIdCategory}" var="catIdName"
																	varStatus="count">



																	<c:set var="cId" value="${catId}"></c:set>
																	<c:set var="listCatId" value="${catIdName.catId}"></c:set>
																	
																	<%
																		int cateId = (int) pageContext.getAttribute("cId");
																		int lCatId = (int) pageContext.getAttribute("listCatId");

																	if (cateId == lCatId) {
																	%>
																	<option selected value="${catIdName.catId}"><c:out value="${catIdName.catName}"/></option>
																	
																	<%
																	} else {
																	%>

																	<option value="${catIdName.catId}"><c:out value="${catIdName.catName}"/></option>

																	<%
																		}
																	%>


																</c:forEach>

															</select>
														</div>
													</div>




													<div class="form-group">
														<div
															class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
															<input type="submit" class="btn btn-primary"
																value="Submit">

														</div>
													</div>
												</form>


												<form action="frItemStockConfigurationProcess"
													name="validation-from2" id="validation-from" method="post">


													<c:set var="settingValue" value="${settingValue}" />

													<div class="clearfix"></div>
													<div class="table-responsive" border="1">
														<table width="100%" class="table table-advance"
															id="table1">
															<thead>

																<tr>

																	<th width="17" style="width: 18px">#</th>
																	<th width="200" align="left">Item Name</th>
																	<th width="200" align="left">Category</th>



																	<c:forEach begin="1" end="${settingValue}" var="cc"
																		varStatus="count">

																		<th width="200" align="right">Type ${count.index}</th>

																	</c:forEach>

																	<!-- 																	<th width="200" align="left">Action</th>
 -->
																</tr>

															</thead>

															<tbody>

																<c:forEach items="${itemList}" var="item"
																	varStatus="count">

																	<c:set var="id" value="${item.itemId}" />


																	<tr>

																		<td align="left">${count.index+1}</td>

																		<td align="left"><c:out value="${item.itemName}" /></td>

																		<td align="left"><c:out value="${item.itemName}" /></td>

																		<c:forEach items="${item.stockDetails}" var="stDetails"
																			varStatus="count">
																			<td align="left">
																						<input type="hidden" name="${item.itemId}stockId${count.index}"
																						value="${stDetails.frStockId}"
																						>
																				<div align="center"
																					class="col-sm-9 col-lg-10 controls">
																					Min <input align="left" type="text"
																						name="${item.itemId}min${count.index}"
																						placeholder="Min" class="form-control"
																						data-rule-required="true" style="width: 65px" 
																						value="${stDetails.minQty}"
																						
																						 />
																				</div>

																				<div align="center"
																					class="col-sm-9 col-lg-10 controls">
																					Max <input type="text"
																						name="${item.itemId}max${count.index}"
																						placeholder="Max" class="form-control"
																						data-rule-required="true" style="width: 65px" 
																						value="${stDetails.maxQty}"
																						/>
																				</div>

																			</td>


																		</c:forEach>
																		<td></td>

																	</tr>

																</c:forEach>

															</tbody>
														</table>
													</div>


													<div class="form-group">
														<div
															class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
															<input type="submit" class="btn btn-primary"
																value="Submit" id="callSubmit">
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


	<script type="text/javascript">
		$(document).ready(function() {
			$('#callSubmit').submit(function() {
				$.ajax({
					type : "get",
					url : "frItemStockConfigurationProcess", //this is my servlet
				/*   data: "input=" +$('#ip').val()+"&output="+$('#op').val(), */

				});
			});

		});
	</script>


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
</html>