<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
 --%>
<!-- <script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script> -->

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body>

	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<c:url var="getMenuListByCatId" value="/getMenu"></c:url>
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
						<i class="fa fa-file-o"></i>Order Dispatch Report
					</h1>
					<h4></h4>
				</div>
			</div>
			<!-- END Page Title -->

			<!-- BEGIN Breadcrumb -->
		
			<!-- END Breadcrumb -->

			<!-- BEGIN Main Content -->
			<div class="box">
				<div class="box-title">
					<h3>
						<i class="fa fa-bars"></i>Order Dispatch Report
					</h3>

				</div>
<form action="${pageContext.request.contextPath}/showOrderDispatchReport" class="form-horizontal"
								method="get" id="validation-form">

				<div class="box-content">
					<div class="row">


						<div class="form-group">
							<label class="col-sm-3 col-lg-1	 control-label">Del Date</label>
							<div class="col-sm-6 col-lg-2 controls date_select">
								<input class="form-control date-picker" id="deliveryDate"
									name="deliveryDate" size="30" type="text" value="${todaysDate}" />
							</div>
							<label class="col-sm-3 col-lg-1 control-label">Category</label>
							<div class="col-sm-6 col-lg-2 controls">
								<select data-placeholder="Select Route"
									class="form-control chosen" name="catId" id="catId"
									onchange="getMenusByCatId(this.value)">
							    <option value=""><c:out value="Select Category"/></option>

								<c:forEach items="${catList}" var="cat" varStatus="count">
								     <c:choose>
                                <c:when test="${cat.catId!=5}">
                                 <c:choose>
                                <c:when test="${cat.catId==catId}">
									<option value="${cat.catId}" selected><c:out value="${cat.catName}"/></option>
								</c:when>
								<c:otherwise>
									<option value="${cat.catId}"><c:out value="${cat.catName}"/></option>
								</c:otherwise>
								</c:choose>
								</c:when>
								</c:choose>
								</c:forEach>
								</select>

							</div>
							<label class="col-sm-3 col-lg-1 control-label">
								Menu</label>
							<div class="col-sm-6 col-lg-2 controls">
								<select data-placeholder="Select Menu"
									class="form-control chosen" name="menuId" id="menuId" multiple="multiple">
							    <option value=""><c:out value="Select Menu"/></option>
                                <c:if test="${flag==1}">	
                                
                                <c:forEach items="${menuList}" var="menu" varStatus="count">
                                   <c:choose>
                                <c:when test="${menu.mainCatId==catId}">
                                 <c:forEach items="${menuIdList}" var="menuId" varStatus="count">
                                <c:choose>
                                <c:when test="${menu.menuId==menuId}">
                                	<option value="${menu.menuId}" selected><c:out value="${menu.menuTitle}"/></option>
                                </c:when>
                                 <c:otherwise>
                                 <option value="${menu.menuId}"><c:out value="${menu.menuTitle}"/></option>
                                 </c:otherwise>
                                </c:choose>
                                </c:forEach>
                                </c:when>
                                </c:choose>
								</c:forEach>
								</c:if>
                                					
								</select>
							</div>
						
									<div class="col-md-1">
										<input type="submit" class="btn btn-primary" value="Submit">
								</div> <c:if test="${flag==1}">	
								<div class="col-md-2">
									<input type="button" id="expExcel" class="btn btn-primary"
										value="Export Excel" onclick="exportToExcel();"
										>
								</div></c:if>
                 </div>

					</div>
				</div></form>
			</div>


			<div class="box">
			<!-- 	<div class="box-title">
					<h3>
						<i class="fa fa-list-alt"></i>Dispatch List
					</h3>

				</div> -->
			


					<div class=" box-content">
						<div id="routeName"></div>
						<div class="row">
							<div class="col-md-12 table-responsive">
								<div
									style="overflow: scroll; height: 100%; width: 100%; overflow: auto">

									<table class="table table-bordered table-striped fill-head "
										style="width: 100%" id="table_grid">
										<thead>
											<tr>
										    <th>Sr.No.</th>
											<th>Item Name</th>
											<th>Op Stock Qty</th>
											<th>Order Qty</th>
											<th>Take From Opening</th>
											<th>Take From Fresh</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${dispatchList}" var="dispatch" varStatus="count">
											<tr>
											 <td>${count.index+1}</td>
											 <td>${dispatch.itemName}</td>
											 <td style="text-align: right;">${dispatch.opTotal}</td>
											 <td style="text-align: right;">${dispatch.orderQty}</td>
											<c:set var="op" value="0"/><c:set var="fresh" value="0"/>
											 <c:choose>
											 <c:when test="${dispatch.orderQty<=dispatch.opTotal && dispatch.orderQty>0}">
											 <c:set var="op" value="${dispatch.orderQty}"/>
											  <c:set var="fresh" value="0"/>
											 </c:when>
											  <c:when test="${dispatch.orderQty>dispatch.opTotal}">
											 <c:set var="fresh" value="${dispatch.orderQty-dispatch.opTotal}"/>
											  <c:set var="op" value="${dispatch.opTotal}"/>
											 </c:when>
										
											 </c:choose>
											 <td style="text-align: right;">${op}</td>
											 <td style="text-align: right;">${fresh}</td>
											</tr>
										</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<c:if test="${flag==1}">
							<div class="form-group" >&nbsp;&nbsp;
							<button class="btn btn-primary" value="PDF" id="PDFButton"
							onclick="genPdf()">PDF</button>
								</div>
							</c:if>
							</div>
						</div>

					</div>

			</div>

			<!-- END Main Content -->

			<footer>
				<p>2019 © Monginis.</p>
			</footer>

			<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
				class="fa fa-chevron-up"></i></a>
		</div>
	</div>

<script>
function genPdf() {

	window.open('${pageContext.request.contextPath}/showOrderDispatchReportPdf',"_blank");

}
function getMenusByCatId(catId)
{
		if (catId == "" || catId == null) {

		} else {
			$.getJSON('${getMenuListByCatId}', {
				selectedCat :catId,
				ajax : 'true'
			}, function(data) {
				//alert(data);
				var html = '<option value="">Select Menu</option>';

				var len = data.length;

				$('#menuId').find('option').remove().end()

				$("#menuId").append(
						$("<option></option>").attr("value", "")
								.text("Select Menu"));

				for (var i = 0; i < len; i++) {
					$("#menuId").append(
							$("<option ></option>").attr("value",
									data[i].menuId).text(data[i].menuTitle));
				}
				$("#menuId").trigger("chosen:updated");
			});
		}

	
}
</script>
	<script>
		$('.datepicker').datepicker({
			format : {
				/*
				 * Say our UI should display a week ahead,
				 * but textbox should store the actual date.
				 * This is useful if we need UI to select local dates,
				 * but store in UTC
				 */
				format : 'mm/dd/yyyy',
				startDate : '-3d'
			}
		});
	</script>



	<script type="text/javascript">
	
		function exportToExcel() {

			window.open("${pageContext.request.contextPath}/exportToExcel");
			document.getElementById("expExcel").disabled = true;
		}
	</script>
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
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/chosen-bootstrap/chosen.jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-inputmask/bootstrap-inputmask.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-tags-input/jquery.tagsinput.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-pwstrength/jquery.pwstrength.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-fileupload/bootstrap-fileupload.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-duallistbox/duallistbox/bootstrap-duallistbox.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/dropzone/downloads/dropzone.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/clockface/js/clockface.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/date.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-switch/static/js/bootstrap-switch.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/ckeditor/ckeditor.js"></script>

	<!--flaty scripts-->
	<script src="${pageContext.request.contextPath}/resources/js/flaty.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/flaty-demo-codes.js"></script>
</body>
</html>