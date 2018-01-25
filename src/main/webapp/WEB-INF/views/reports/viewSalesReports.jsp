

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>

	<c:url var="getGroup2ByCatId" value="/getGroup2ByCatId" />


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
						<i class="fa fa-file-o"></i>Sales Report
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
								<i class="fa fa-bars"></i> Report  Url's
							</h3>
							
							
						</div>




						<div class="box-content">
							<form action="" class="form-horizontal"
								method="post" id="validation-form" enctype="multipart/form-data">

                          	<div class="box-content">

										<div class="clearfix"></div>
										<div class="table-responsive" style="border: 0">
											<table width="100%" class="table table-advance" id="table1">
												<thead>
													<tr>
														<th width="10" style="width: 8px">Sr.No</th>
             											<th width="500" >Report Name</th>
													
													</tr>
												</thead>
												<tbody>
														<tr>
														
															<td><c:out value="1"/></td>
														   <td align="left"><a href="${pageContext.request.contextPath}/showSaleReportByDate" >Sale Report By Date</a><br>
														   	</td>
														     
														      
														</tr>
                                                          <tr>
														
															<td><c:out value="2"/></td>
														   <td align="left"><a href="${pageContext.request.contextPath}/showSaleReportByFr">Sale Report By Franchise</a><br>
														   	</td>
														     
														      
														</tr>
														 <tr>
														
															<td><c:out value="3"/></td>
														   <td align="left"><a href="${pageContext.request.contextPath}/showSaleReportGrpByDate">Sale Report Group By Date</a><br>
														   	</td>
														     
														      
														</tr>
                                                             <tr>
														
															<td><c:out value="4"/></td>
														   <td align="left"><a href="${pageContext.request.contextPath}/showSaleReportByMonth">Sale Report By Month</a><br>
	                                                      </td>
														     
														      
														</tr>
														 <tr>
														
															<td><c:out value="5"/></td>
														   <td align="left"><a href="${pageContext.request.contextPath}/showSaleRoyaltyByCat">Sale Royalty By Category</a><br>
 </td>
														     
														      
														</tr>
														 <tr>
														
															<td><c:out value="6"/></td>
														   <td align="left"><a href="${pageContext.request.contextPath}/showSaleRoyaltyByFr">Sale Royalty By Franchise</a><br>
</td>
														     
														      
														</tr>
														 <tr>
														
															<td><c:out value="7"/></td>
														   <td align="left"><a href="${pageContext.request.contextPath}/showSaleReportItemwise">Sale Report Item wise</a><br>
</td>
														     
														      
														</tr>
														 <tr>
														
															<td><c:out value="8"/></td>
														   <td align="left"><a href="${pageContext.request.contextPath}/showSaleReportBillwiseAllFr">Sale Report Bill wise All Franchise</a><br>
</td>
														     
														      
														</tr>
															 <tr>
														
															<td><c:out value="9"/></td>
														   <td align="left"><a href="${pageContext.request.contextPath}/showSaleReportRoyConsoByCat">Sale Report Royalty Consolidated By Category</a>
</td>
														     
														      
														</tr>
              										</tbody>
											</table>
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



</body>
</html>