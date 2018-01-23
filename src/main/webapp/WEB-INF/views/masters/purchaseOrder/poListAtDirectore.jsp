<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

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
					<i class="fa fa-file-o"></i>Purchase Order At Director
				</h1>
				<!-- <h4>Bill for franchises</h4> -->
			</div>
		</div>
		<!-- END Page Title -->

		<!-- BEGIN Breadcrumb -->
		<div id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i> <a
					href="${pageContext.request.contextPath}/home">Home</a> <span
					class="divider"><i class="fa fa-angle-right"></i></span></li>
				<li class="active">Purchase Order</li>
			</ul>
		</div>
		<!-- END Breadcrumb -->
		
		<!-- BEGIN Main Content -->
		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>PO List
				</h3>

			</div>
			<div class=" box-content">

			<form id="submitBillForm"
				action="${pageContext.request.contextPath}/submitNewBill"
				method="post">
					<div class="row">
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>PO.No.</th>
										<th>PO Date</th>
										<th>Po Type</th>
										<th>Suppiler</th>
										<th>Value</th>
										<th>Status</th>
										<th colspan="2">Action</th>

									</tr>
								</thead>
								<tbody>
								
								
								<c:forEach items="${purchaseorderlist}" var="purchaseorderlist"
													varStatus="count">
											<c:choose>
													<c:when test="${purchaseorderlist.poStatus==1}">
													<c:set var = "color" value="red"/>
													</c:when> 
													<c:otherwise>
													  <c:set var = "color" value="black"/>
													</c:otherwise>
											</c:choose>
											<c:choose>
													<c:when test="${purchaseorderlist.poType==1}">
													<c:set var = "type" value="Inclusive"/>
													</c:when>
													<c:otherwise>
													  <c:set var = "type" value="Open"/>
													</c:otherwise>
											</c:choose>
											
											<c:choose>
													<c:when test="${purchaseorderlist.poStatus==2}">
													<c:set var = "status" value="Pending"/>
													</c:when>
													
												</c:choose>

									

													<tr>
														<td style="color: <c:out value = "${color}"/>"><c:out value="${count.index+1}" /></td>

														
																
																<td align="left" style="color: <c:out value = "${color}"/>"><c:out
																value="${purchaseorderlist.poNo}" /></td>
																
																<td align="left" style="color: <c:out value = "${color}"/>"><c:out
																value="${purchaseorderlist.poDate}" />  </td>
																
																
																<td align="left" style="color: <c:out value = "${color}"/>"><c:out	
																value="${type}" />
																</td>
																
																<c:forEach items="${supplierList}" var="supplierList"
													varStatus="count">
																<c:choose>
													<c:when test="${purchaseorderlist.suppId==supplierList.suppId}">
													<td align="left" style="color: <c:out value = "${color}"/>"><c:out
																value="${supplierList.suppName}" /></td>
													</c:when>
													 </c:choose>
													 </c:forEach>
													 
													 <td align="left" style="color: <c:out value = "${color}"/>"><c:out	
																value="${purchaseorderlist.poTotalValue}" />
																</td>
																
													 <td align="left" style="color: <c:out value = "${color}"/>"><c:out	
																value="${status}" />
																</td>
													  				
																
						<td>
						<a href="poHeaderWithDetailed/${purchaseorderlist.poId}/${flag}" class="action_btn" >
						<abbr title="Detail"><i class="fa fa-list"></i></abbr></a>
						
						
						
						</td>
						
																</tr>
												</c:forEach>

								</tbody>
							</table>
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

	<script type="text/javascript">
			function submitBill() {

				//submitBillForm.submit();
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