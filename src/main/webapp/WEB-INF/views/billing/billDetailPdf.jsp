<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

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
						<i class="fa fa-file-o"></i> Bill Details PDF
					</h1>

				</div>
			</div>
			<!-- END Page Title -->



			<!-- BEGIN Main Content -->
			<div class="row">
				<div class="col-md-12">
					<div class="box">
						<!-- <div class="box-title">
							<h3>
								<i class="fa fa-bars"></i>View Bill Details for PDF
							</h3>
							<div class="box-tool">
								<a href="">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							<div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> <a data-action="close" href="#"><i
									class="fa fa-times"></i></a>
							</div>
						</div> -->


			
							<c:forEach items="${billDetails}" var="frDetails"
								varStatus="count">
								<div class="box-content">

									<div class="clearfix"></div>
									<div class="table-responsive" style="border: 0">
										<table width="100%" class="table table-advance" id="table1">
											<thead>
												<tr>
													<!-- <th width="138" align="left">Fr Name</th>
													<th width="120" align="left">Invoice No</th>
													<th width="130" align="left">Fr Address</th>
													</tr> -->

													<h4>FR Name:${frDetails.frName}</h4>
													<h4>Invoice No:${frDetails.invoiceNo}</h4>
													<h4>Address:${frDetails.frAddress}</h4>
												<tr>
													<th width="138" align="left">Item Name</th>
													<th width="120" align="left">Group</th>
													<th width="130" align="right">Billed Qty</th>
													<th width="100" align="left">Base Rate</th>
													<th width="100" align="left">Amount</th>
													<th width="105" align="left">GST %</th>

													<c:choose>
														<c:when test="${frDetails.isSameState==1}">
															<th>SGST Rs</th>
															<th>CGST Rs</th>
														</c:when>
														<c:otherwise>
															<th>IGST Rs</th>
														</c:otherwise>
													</c:choose>
													<th width="105" align="left">Total Tax</th>
													<th width="130" align="left">Grand Total</th>
												</tr>
											</thead>
											<tbody>



												<%-- <tr>
													
													<td align="left"><c:out
																value="${frDetails.frName}" /></td>


														<td align="left"><c:out
																value="${frDetails.invoiceNo}" /></td>
																
																<td align="left"><c:out
																value="${frDetails.frAddress}" /></td>
																
																</tr> --%>
												<c:forEach items="${frDetails.billDetailsList}"
													var="billDetails">
													<tr>

														<td align="left"><c:out
																value="${billDetails.itemName}" /></td>


														<td align="left"><c:out
																value="${billDetails.catName}" /></td>


														<td align="center"><c:out
																value="${billDetails.billQty}" /></td>


														<td align="left"><c:out
																value="${billDetails.baseRate}" /></td>
														<td align="left"><c:out
																value="${billDetails.taxableAmt}" /></td>

														<c:choose>
															<c:when test="${frDetails.isSameState==1}">

																<c:set var="sgstPer" value="${billDetails.sgstPer}" />
																<c:set var="cgstPer" value="${billDetails.cgstPer}" />

																<td align="left"><c:out
																		value="${sgstPer + cgstPer}" /></td>

															</c:when>
															<c:otherwise>

																<td align="left"><c:out
																		value="${billDetails.igstPer}" /></td>
															</c:otherwise>
														</c:choose>

														<c:choose>
															<c:when test="${frDetails.isSameState==1}">

																<td align="left"><c:out value="${billDetails.sgstRs}" /></td>

																<td align="left"><c:out value="${billDetails.cgstRs}" /></td>

															</c:when>
															<c:otherwise>

																<td align="left"><c:out
																		value="${billDetails.igstRs}" /></td>
															</c:otherwise>
														</c:choose>


														<td align="left"><c:out
																value="${billDetails.totalTax}" /></td>
														<td align="center"><c:out
																value="${billDetails.grandTotal}" /></td>
														<!-- Total -->


														<!-- <td rowspan="1" align="left"> <input
																type="button" value="View"> <input type="button"
																value="Edit"> <input type="button"
																value="Cancel"></td> -->


														<!-- <td align="left"><label><input type="submit"
																	name="submit_button" id="submit_button"></label></td>  -->


													</tr>

												</c:forEach>
												<!-- </tr>
 -->
												<%--  </c:forEach>
 --%>
											</tbody>
										</table>
									</div>
								</div>
							</c:forEach>
							<div
										class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-6">
										
										<!-- <button class="btn btn-primary" value="PDF" id="PDFButton" onclick="genPdf()">PDF</button> -->
										
										<a href='${pageContext.request.contextPath}/pdf?url=showBillPdf'
								target="_blank">PDF</a>

									</div>
							
						<!-- </form> -->
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

<script type="text/javascript">

function genPdf()
{
	window.open('${pageContext.request.contextPath}/pdf?url=showBillPdf');
}


</script>


</body>
</html>