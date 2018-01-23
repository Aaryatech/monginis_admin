<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>


<c:url var="getRmSubCategory" value="/getRmSubCategory" />
<c:url var="getRmCategory" value="/getRmCategory" />



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
						<i class="fa fa-file-o"></i>Tax Master
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
								<i class="fa fa-bars"></i>Add Tax
							</h3>
							<div class="box-tool">
								<a href=""></a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
						
						</div>


						<div class="box-content">
							<form action="${pageContext.request.contextPath}/addRmTax" method="post" class="form-horizontal" id="validation-form"
										enctype="multipart/form-data" method="post">
							
									<input type="hidden" name="tax_id" id="tax_id" value="${rmTax.taxId}"/>

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Tax Description</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="tax_desc" id="tax_desc" class="form-control"placeholder="Tax Description"data-rule-required="true" value="${rmTax.taxDesc}"/>
									</div>

									<label class="col-sm-3 col-lg-2 control-label">IGST Percentage(%)
									</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="igst_per" id="igst_per" class="form-control"placeholder="Enter IGST %"data-rule-required="true"  data-rule-number="true" value="${rmTax.igstPer}"/>
									</div>

								</div>
							
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">CGST Percentage(%)
									</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="cgst_per" id="cgst_per" class="form-control"placeholder="Enter CGST %"data-rule-required="true"  data-rule-number="true" value="${rmTax.cgstPer}"/>
									</div>
								 


								<label class="col-sm-3 col-lg-2 control-label">SGST Percentage(%)
									</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="sgst_per" id="sgst_per" class="form-control"placeholder="Enter SGST %"data-rule-required="true"   data-rule-number="true" value="${rmTax.sgstPer}"/>
									</div>
						</div>

						
				
					<div class="row">
						<div class="col-md-12" style="text-align: center">
							<input type="submit" class="btn btn-info" value="Submit">


						</div>
					</div>
					</form>
					<br>
								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i>RM Tax List
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
										</div>
									</div>

									<div class="box-content">

										<div class="clearfix"></div>
										<div class="table-responsive" style="border: 0">
											<table width="100%" class="table table-advance" id="table1">
												<thead>
													<tr>
														<th width="55" style="width: 18px">Sr.No.</th>
														<th width="140" align="center">Tax Description</th>
														<th width="140" align="center">IGST Percentage</th>
														<th width="140" align="center">CGST Percentage</th>
														<th width="140" align="center">SGST Percentage</th>
														<th width="50" align="left">Action</th>
													</tr>
												</thead>
												<tbody>
													  <c:forEach items="${rmTaxList}" var="rmTaxList" varStatus="count">
														<tr>
														
															<td><c:out value="${count.index+1}"/></td>
															<td align="left"><c:out
																	value="${rmTaxList.taxDesc}"></c:out></td>
															<td align="left"><c:out
																	value="${rmTaxList.igstPer}"></c:out></td>
															<td align="left"><c:out
																	value="${rmTaxList.cgstPer}"></c:out></td>
															<td align="left"><c:out
																	value="${rmTaxList.sgstPer}"></c:out></td>		
															<td align="left"><a href="updateRmTax/${rmTaxList.taxId}"><span
														class="glyphicon glyphicon-edit"></span></a>&nbsp;
                                                        
                                                        <a href="deleteRmTax/${rmTaxList.taxId}"
													    onClick="return confirm('Are you sure want to delete this record');"><span
														class="glyphicon glyphicon-remove"></span></a></td>	
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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>

			<script type="text/javascript"
				src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
			<script type="text/javascript"
				src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>
				




</body>
</html>

