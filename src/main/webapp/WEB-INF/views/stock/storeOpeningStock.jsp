<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
 	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>


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
						<i class="fa fa-file-o"></i>Store Stock
					</h1>
				</div>
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i>Store Opening Stock
							</h3>
							<div class="box-tool">
								<a href="">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						<div class="box-content">
								<form action="${pageContext.request.contextPath}/showStoreOpeningStock" name="validation-from"
													id="validation-from" method="get">


													<div class="form-group">
														<label class="col-sm-2 col-lg-2 control-label">Category</label>
														<div class="col-sm-1 col-lg-3 controls">
															<select data-placeholder="Select Category"
																class="form-control chosen" tabindex="6" name="cat_name"
																id="cat_name">
															<c:forEach items="${rmItemGroupList}" var="rmItemGroupList">	<c:choose>
																<c:when test="${subCatId==rmItemGroupList.grpId}">
                                  								<option  value="${rmItemGroupList.grpId}" selected>${rmItemGroupList.grpName}</option>
                                                                </c:when>
                                                                <c:otherwise>
															     <option  value="${rmItemGroupList.grpId}" >${rmItemGroupList.grpName}</option>
                                                                </c:otherwise>
																</c:choose>
																</c:forEach>
															</select>
														</div>
														<!-- </div>

													<div class="form-group"> -->

														<div class="col-md-2">
															<input type="submit" class="btn btn-primary"
																value="Submit">

														</div>
													</div><br><br>
												</form>
												</div>
						
						<div class="box-content">

							<form id="completproduction" action="${pageContext.request.contextPath}/insertStoreOpeningStock" method="post">
							 
							 							 <input type="hidden" name="catId" id="catId" value="${subCatId}" />
							 
							
							<div class=" box-content">
								<div class="row">
								<div class="col-md-12 table-responsive">
									<table class="table table-bordered table-striped fill-head "
								 id="table_grid">
								<thead>
									<tr>
										<th  style="width: 50px">Sr.No.</th>
										<th style="width: 400px">Raw Material Name</th>
										
										<th style="width: 100px"> Uom</th>
										<th style="width: 200px"> Qty</th>
										

									</tr>
								</thead>
									<tbody>
											
											<c:forEach items="${rmList}" var="rmList"
														varStatus="count">

														<tr>
															<td><c:out value="${count.index+1}"></c:out></td> 
															<td><c:out value="${rmList.rmName}"></c:out></td>
														
															<c:forEach items="${uomList}" var="uomList" >
														<c:choose>
														<c:when test="${uomList.uomId == rmList.rmUom}">
															<td><c:out value="${uomList.uom}"></c:out>
															</td>
															</c:when>
															</c:choose>
															</c:forEach>
															
															<td>  <input type='text' class='form-control' value='${rmList.storeOpeningStock}' pattern='[+-]?([0-9]*[.])?[0-9]+' name='stockQty<c:out value="${rmList.rmId}" />' required> 
															</td>
															  

														</tr>
													</c:forEach>
													
													
													
									</tbody>
									</table>
								</div>
								</div>
									<div class="form-group">
												<div class="col-sm-9 col-sm-offset-3 col-lg-11 col-lg-offset-4">
										
													<input type="submit" class="btn btn-primary" value="Submit">
												</div>
									</div>
							</div>
							
							<div class="box-content">
							
							
							</div><br><br><br>
							
							

						</form>
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