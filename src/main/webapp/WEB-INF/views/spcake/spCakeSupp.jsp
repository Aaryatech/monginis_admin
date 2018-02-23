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
						<i class="fa fa-file-o"></i>Special Cake Supplement
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
								<i class="fa fa-bars"></i> Add Special Cake Supplement
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showSpCkSupList">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>




						<div class="box-content">
							<form action="${pageContext.request.contextPath}/addSpCakeSupProcess" class="form-horizontal"
								method="post" id="validation-form">

	                    <input type="hidden" name="id" id="id" value="${spCkSupp.id}"/>
					
                              <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Special Cake</label>
									<div class="col-sm-9 col-lg-3 controls">
									<select name="sp_id" id="sp_id" data-rule-required="true" class="form-control" placeholder="Select Special Cake">
											<option value="">Select Special Cake</option>
											<c:forEach items="${spList}" var="spCakeList"
													varStatus="count">
													<c:choose>
													<c:when test="${spCakeList.spId==spCkSupp.spId}">
														<option value="${spCakeList.spId}" selected><c:out value="${spCakeList.spCode}-${spCakeList.spName}"/></option>
													</c:when>
													<c:otherwise>
														<option value="${spCakeList.spId}"><c:out value="${spCakeList.spCode}-${spCakeList.spName}"/></option>
													</c:otherwise>
													</c:choose>
												</c:forEach>
												
								</select>	
									</div>
								</div>
								
								
							  <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">HSN Code</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="spck_hsncd" id="spck_hsncd"
											placeholder="HSN Code" class="form-control"
											data-rule-required="true" value="${spCkSupp.spHsncd}"/>
									</div>
							  </div>
							  <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">UOM</label>
									<div class="col-sm-9 col-lg-3 controls">
												<select name="spck_uom" id="spck_uom" class="form-control"placeholder="Special Cake UOM"
												 data-rule-required="true" onchange="uomChanged()">
											<option value="">Select Special Cake UOM</option>
											<c:forEach items="${rmUomList}" var="rmUomList"
													varStatus="count">
													<c:choose>
													<c:when test="${rmUomList.uomId==spCkSupp.uomId}">
														<option value="${rmUomList.uomId}" selected><c:out value="${rmUomList.uom}"/></option>
													</c:when>
													<c:otherwise>
														<option value="${rmUomList.uomId}"><c:out value="${rmUomList.uom}"/></option>
													</c:otherwise>
													</c:choose>
												</c:forEach>
										</select>
									</div>
							  </div>
							  <input type="hidden" name="sp_uom_name" id="sp_uom_name" value="${spCkSupp.spUom}"/> 
							  <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Cess(%)</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="sp_cess" id="sp_cess"
											placeholder="Cess %" class="form-control"
											data-rule-required="true" data-rule-number="true"value="${spCkSupp.spCess}"/>
									</div>
							  </div>
							  
							    <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Cut Section</label>
									<div class="col-sm-9 col-lg-3 controls">
										<select name="cut_section" id="cut_section" class="form-control"
												 data-rule-required="true">
											<option value="">Select Cut Section</option>
											
										<c:choose>
										<c:when test="${spCkSupp.cutSection==0}">
										<option value="0" selected>Not Applicable</option>
											<option value="1">Single Cut</option>
											<option value="2">Double Cut</option>
										</c:when>
											<c:when test="${spCkSupp.cutSection==1}">
											<option value="0" >Not Applicable</option>
											<option value="1"selected>Single Cut</option>
											<option value="2">Double Cut</option>
											</c:when>
											<c:when test="${spCkSupp.cutSection==2}">
											<option value="0" >Not Applicable</option>
											<option value="1">Single Cut</option>
											<option value="2"selected>Double Cut</option>
											</c:when>
											<c:otherwise>
										    <option value="0">Not Applicable</option>
											<option value="1">Single Cut</option>
											<option value="2">Double Cut</option>
											</c:otherwise>
										</c:choose>
										</select>
									</div>
							  </div>
					
								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Submit">
     							</div>
								</div>
							</form>
					         		<br>
								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i>Special Cake Supplement List
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
										</div>
									</div>

									<div class="box-content">
                            <jsp:include page="/WEB-INF/views/include/tableSearch.jsp"></jsp:include>

										<div class="clearfix"></div>
							<div id="table-scroll" class="table-scroll">
								 		
								<div id="faux-table" class="faux-table" aria="hidden">
									<table id="table2" class="main-table">
											<thead>
												<tr class="bgpink">
												<th width="45" style="width: 18px">Sr.No.</th>
														<th width="100" align="left">Special Cake</th>
														<th width="100" align="left">HSN Code</th>
														<th width="100" align="left">CESS(%)</th>
														<th width="100" align="left">UOM</th>
														<th width="81" align="left">Action</th>
												</tr>
												</thead>
												</table>
									
									</div>
									<div class="table-wrap">
									
										<table id="table1" class="table table-advance">
											<thead>
												<tr class="bgpink">
													<th width="45" style="width: 18px">Sr.No.</th>
														<th width="100" align="left">Special Cake</th>
														<th width="100" align="left">HSN Code</th>
														<th width="100" align="left">CESS(%)</th>
														<th width="100" align="left">UOM</th>
														<th width="81" align="left">Action</th>
												</tr>
												</thead> 
                          
												<tbody>
													  <c:forEach items="${spSuppList}" var="spSuppList" varStatus="count">
														<tr>
														
															<td><c:out value="${count.index+1}"/></td>
															<td align="left"><c:out
																	value="${spSuppList.spName}"></c:out></td>
															
															<td align="left"><c:out
																	value="${spSuppList.spHsncd}"></c:out></td>
															<td align="left"><c:out
																	value="${spSuppList.spCess}"></c:out></td>
															<td align="left"><c:out
																	value="${spSuppList.spUom}"></c:out></td>
														
															<td align="left"><a href="${pageContext.request.contextPath}/updateSpSupp/${spSuppList.id}"><span
														class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;
                                                        
                                                       <%--  <a href="${pageContext.request.contextPath}/deleteSpSupp/${spSuppList.id}"
													    onClick="return confirm('Are you sure want to delete this record');"><span
														class="glyphicon glyphicon-remove"></span></a> --%></td>	
														 
														</tr>

													</c:forEach> 
												</tbody>
											</table>
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


<script type="text/javascript">

			function uomChanged() {
				
				document.getElementById('sp_uom_name').value=$('#spck_uom option:selected').text();
				
			}
</script>
</html>