<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
 
  <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
 <jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
<body>


	 


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
						<i class="fa fa-file-o"></i> Add to Bom 
					</h1>
				</div>
			</div>
			<!-- END Page Title -->
			<c:choose>
				<c:when test="${isMix==1}">
					<c:set var="title" value="Production Request For BOM">
					</c:set>
				</c:when>
				<c:otherwise>
					<c:set var="title" value="Mixing Request For BOM">
					</c:set>
				</c:otherwise>
			</c:choose>

			<div class="row">
				<div class="col-md-12">

					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i>${title}
							</h3>
							<div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
								<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
							</div>
						</div>

						<div class="box-content">
							<form action="${pageContext.request.contextPath}/insertBom"
								name="validation-form" id="validation-form">
								<div class="clearfix"></div>
								<input type="hidden" name="isMix" value="${isMix}">
	                            <input type="hidden" name="catId" value="${catId}">
								<div class="table-responsive" style="border: 0">
									<table width="100%" class="table table-advance" id="table1">
										<thead>
											<tr>
												<th width="17" style="width: 18px">Sr No</th>
<!-- 												<th width="100" align="left">Rm Id</th>
 -->												<th width="120" align="left">Rm Name</th>
												<th width="100" align="left">Rm Type</th>
												<th width="120" align="left">Qty</th>

												<th width="120" align="left">Edit Qty</th>

												<th width="100" align="left">Unit</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${planDetailForBom}" var="planDetailForBom"
												varStatus="count">

												<tr>
													<td><c:out value="${count.index+1}" /></td>
													<%-- <td align="left"><c:out
															value="${planDetailForBom.rmId}" /></td> --%>
													<td align="left"><c:out
															value="${planDetailForBom.rmName}" /></td>
													<c:choose>
														<c:when test="${planDetailForBom.rmType==1}">
															<td align="left"><c:out
																	value="RM" /></td>

														</c:when>
														<c:when test="${planDetailForBom.rmType==2}">
															<td align="left"><c:out
																	value="SF" /></td>

														</c:when>

													</c:choose>
													<c:set var="total"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${planDetailForBom.total}" /></c:set>
													<td align="left"><c:out
															value="${total}" /></td>

													<td align="left"><input type="text" id="editQty"
														size="2" class="form-control" name="editQty${count.index}"
														value="${total}"></td>

													<td align="left"><c:out
															value="${planDetailForBom.uom}"></c:out></td>

												</tr>
											</c:forEach>

										</tbody>
									</table>
									
								</div>
									
						<div class="col-md-12" style="text-align: center;">
						
						<input type="submit" value="Submit" class="btn btn-primary" />
							
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