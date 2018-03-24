<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<c:url var="getBillList" value="/generateNewBill"></c:url>
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
					<i class="fa fa-file-o"></i> Tray Invoice
				</h1>
				<h4>Bill for Trays</h4>
			</div>
		</div>
		<!-- END Page Title -->

		<!-- BEGIN Breadcrumb -->
		<div id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i> <a
					href="${pageContext.request.contextPath}/home">Home</a> <span
					class="divider"><i class="fa fa-angle-right"></i></span></li>
				<li class="active">Tray Bill</li>
			</ul>
		</div>
		<!-- END Breadcrumb -->

		<!-- BEGIN Main Content -->

		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-list-alt"></i>Tray Bill
				</h3>

			</div>

			<form id="insertTrayBills"
				action="${pageContext.request.contextPath}/insertTrayBills"
				method="post">
				<div class=" box-content">
					<div class="row">
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th>Select Bill</th>
										<th>Sr.</th>
										<th>Id</th>
										<th>Date</th>
										<th>Franchisee Name</th>
										<th>Small Tray </th>
										<th>Big Tray </th>
										<th>Lead Tray </th>
										<th>Extra Tray </th>
										<th>Taxable Amt</th>
										<th>Tax Amt</th>
										<th>Grand Total</th>
									</tr>
								</thead>
								<tbody>
                                 <c:forEach items="${trayMgtDetailsList}" var="trayMgtDetailsList" varStatus="count">
			      								<tr>
			      								 <td align="left"><input type="checkbox" name="tranDetailId${trayMgtDetailsList.tranDetailId}" id="tranDetailId${trayMgtDetailsList.tranDetailId}" value="${trayMgtDetailsList.tranDetailId}"></td>		
			      								
												<td><c:out value="${count.index+1}" /></td>
												<td align="left"><c:out value="${trayMgtDetailsList.tranDetailId}" /></td>
												<td align="left"><c:out value="${trayMgtDetailsList.outtrayDate}" /></td>
												<td align="left"><c:out value="${trayMgtDetailsList.frName}"/></td>
												<td align="left"><c:out value="${trayMgtDetailsList.balanceSmall} * ${smallPrice}"/><c:out value="=${trayMgtDetailsList.balanceSmall*smallPrice}"/></td>
											      <c:set var = "smallPr"  value = "${trayMgtDetailsList.balanceSmall*smallPrice}"/>

												<td align="left"><c:out value="${trayMgtDetailsList.balanceBig} * ${bigPrice}"/><c:out value="=${trayMgtDetailsList.balanceBig*bigPrice}"/></td>
											     <c:set var = "bigPr"  value = "${trayMgtDetailsList.balanceBig*bigPrice}"/>
											    
											    <td align="left"><c:out value="${trayMgtDetailsList.balanceLead} * ${leadPrice}"/><c:out value="=${trayMgtDetailsList.balanceLead*leadPrice}"/></td>
											 	<c:set var = "leadPr"  value = "${trayMgtDetailsList.balanceLead*leadPrice}"/>
											 
											    <td align="left"><c:out value="${trayMgtDetailsList.balanceExtra} * ${extraPrice}"/><c:out value="=${trayMgtDetailsList.balanceExtra*extraPrice}"/></td>
												<c:set var = "extraPr"  value = "${trayMgtDetailsList.balanceExtra*extraPrice}"/>
												
												 <td style="text-align: right;"><c:out value="${smallPr+bigPr+leadPr+extraPr}"/></td>
												 <c:set var = "taxableAmount"  value = "${smallPr+bigPr+leadPr+extraPr}"/>
												 
												 <td style="text-align: right;"><c:out value="${(taxableAmount*gstPer)/100}(${gstPer}%)"/></td>
												 	 <c:set var = "TaxAmount"  value = "${(taxableAmount*gstPer)/100}"/>
												 
												 <td style="text-align: right;"><c:out value="${taxableAmount+TaxAmount}"/></td>
												
												
												</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</div>

<br>

					<div class="row">
						<div class="col-md-offset-5 col-md-4">
<!-- 							<button class="btn btn-info pull-right">Submit & PDF</button>
 -->
							<%-- <a href="${pageContext.request.contextPath}/pdf?url=showBillPdf"
								target="_blank">PDF</a> --%>
							<input type="submit" name="Submit Bill" value="Submit Bill" class="btn btn-info"/>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- END Main Content -->

	<footer>
	<p>2017 © Monginis.</p>
	</footer>

	<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
		class="fa fa-chevron-up"></i></a>

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