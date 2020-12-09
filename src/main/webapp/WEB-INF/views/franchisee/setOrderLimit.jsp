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
					.write(
							'<script src="${pageContext.request.contextPath}/resources/assets/jquery/jquery-2.0.3.min.js"><\/script>')
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

	<c:url var="saveOrderLimitAdmin" value="/saveOrderLimitAdmin" />

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
						<i class="fa fa-file-o"></i> Order Limit
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
										<i class="fa fa-bars"></i> Order Limit
									</h3>
									<div class="box-tool"></div>
								</div>

								<div class="box-content">

									<form
										action="${pageContext.request.contextPath}/saveMenuOrderLimitAdmin"
										class="form-horizontal" id="validation-form" method="post">

										<!-- <div class="table-wrap"> -->

											<table id="table1" class="sachin table table-advance">
												<thead>
													<tr class="bgpink">
														<th class="col-md-2">Menu</th>
														<th class="col-md-2">Sub Category</th>
														<th class="col-md-2">Limit</th>
													</tr>
												</thead>

												<tbody>


													<c:forEach items="${menuIds}" var="menuId"
														varStatus="count">

														<%-- <c:forEach items="${menuList}" var="menuList"
														varStatus="count1">

														<c:if test="${menuId==menuList.menuId}">
															<tr>
																<td>${menuList.menuTitle}</td>
																<td></td>
																<td></td>
															</tr>
														</c:if>

													</c:forEach> --%>


														<c:forEach items="${menuList}" var="menuList"
															varStatus="count1">

															<c:if test="${menuId==menuList.menuId}">

																<tr>

																	<td class="menuTitleCls" style="font-weight: bold;">${menuList.menuTitle}</td>
																	<td class="subCatNameCls">${menuList.subCatName}</td>
																	<td><input type="number"
																		id="${menuList.menuId}#${menuList.subCatId}"
																		name="${menuList.menuId}#${menuList.subCatId}"
																		value="${menuList.qtyLimit}" class="limitCls"></td>

																	<td style="display: none;" class="subCatIdCls">${menuList.subCatId}</td>
																	<td style="display: none;" class="menuIdCls">${menuList.menuId}</td>
																	<td style="display: none;" class="idCls">${menuList.id}</td>
																	<td style="display: none;" class="catIdCls">${menuList.catId}</td>

																</tr>

															</c:if>

														</c:forEach>

														<tr>

															<td>.</td>
															<td></td>
															<td></td>

														</tr>


													</c:forEach>

												</tbody>

											</table>
										<!-- </div> -->



										<div class="form-group" style="text-align: center;">
											<br>

												<input type="submit" class="btn btn-primary"
													onclick="saveLimit()">
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


	<script type="text/javascript">
		function saveLimit() {

			/* var menuList = [];

			$(".idCls")
					.each(
							function(counter) {

								var id = document
										.getElementsByClassName("idCls")[counter].innerHTML;

								var menuId = document
										.getElementsByClassName("menuIdCls")[counter].innerHTML;

								var subCatId = document
										.getElementsByClassName("subCatIdCls")[counter].innerHTML;

								var catId = document
										.getElementsByClassName("catIdCls")[counter].innerHTML;

								var menuTitle = document
										.getElementsByClassName("menuTitleCls")[counter].innerHTML;

								var subCatName = document
										.getElementsByClassName("subCatNameCls")[counter].innerHTML;

								var limit = document
										.getElementsByClassName("limitCls")[counter].value;

								var item = {
									id : id,
									menuId : menuId,
									subCatId : subCatId,
									catId : catId,
									menuTitle : menuTitle,
									subCatName : subCatName,
									qtyLimit : limit
								}

								menuList.push(item);

							});

			alert(JSON.stringify(menuList))

			$.getJSON('${saveOrderLimitAdmin}', {
				jsonStr : JSON.stringify(menuList),
				ajax : 'true'
			}, function(data) {

				alert(JSON.stringify(data))
			});
 */
		}
	</script>

</body>
</html>