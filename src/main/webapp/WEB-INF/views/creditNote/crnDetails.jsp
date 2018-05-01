<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body onload="placeValue()">

	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
	<c:url var="insertGateGrnProcessAgree"
		value="/insertGateGrnProcessAgree" />

	<c:url var="insertAccGrnProcessAgree" value="/insertAccGrnProcessAgree" />

	<c:url var="insertAccGrnProcessDisAgree"
		value="/insertAccGrnProcessDisAgree" />

	<c:url var="getDateForGrnAcc" value="/getDateForGrnAcc" />

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
						<i class="fa fa-file-o"></i>CRN LIST
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
								<i class="fa fa-bars"></i> Credit Note
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showCreditNotes">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							<!-- <div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> <a data-action="close" href="#"><i
									class="fa fa-times"></i></a>
							</div> -->
						</div>


						<div class="box-content">

							<form
								action=""
								class="form-horizontal" method="post" id="validation-form">

								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i> Credit Note Details List for Credit Note Id : ${creditNoteHeaders.crnId}
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
										</div>
									</div>

									<div class="box-content">
<div class="col-md-9" ></div> 
					<label for="search" class="col-md-3" id="search">
    <i class="fa fa-search" style="font-size:20px"></i>
									<input type="text"  id="myInput" onkeyup="myFunction()" placeholder="Search items by name.." title="Type in a name">
										</label> <br>
										<h5> Credit Note No : ${creditNoteHeaders.crnId} </h5>
										<h5> Franchisee Name : ${creditNoteHeaders.frName} </h5> 
										<h5> Date : ${creditNoteHeaders.crnDate} </h5> 
										<h5> Taxable Amount : ${creditNoteHeaders.crnTaxableAmt} </h5> 
										<h5> Tax Amount : ${creditNoteHeaders.crnTotalTax} </h5> 
										<h5> Total : ${creditNoteHeaders.crnGrandTotal} </h5> 
										<div class="clearfix"></div>
										<div class="table-responsive" style="border: 0">
											<table width="100%"
												class="table table-advance table-responsive table-position"
												id="table1">
												<thead>
													<tr>

														<th>Sr No</th>
														<th>GrnGvn Sr No</th>
														<th>GrnGvn Date</th>
														<th>Invoice No</th>
														<th>Item Name</th>
														<th>Type</th>
														<th>Quantity</th>
														<th>Taxable Amt</th>
														<th>Tax Amt</th>
														<th>Amount</th>
													</tr>

												</thead>
												<tbody>
													<c:forEach items="${crnDetailList}" var="crnDetail"
														varStatus="count">

														<tr>

															<td><c:out value="${count.index+1}" /></td>

															<td align="left"><c:out
																	value="${crnDetail.grngvnSrno}" /></td>

															<td align="left"><c:out
																	value="${crnDetail.grnGvnDate}" /></td>


															<td align="left"><c:out
																	value="${crnDetail.refInvoiceNo}" /></td>
																	
															<td align="left"><c:out
																	value="${crnDetail.itemName}" /></td>
															
															
															<c:set var="type" value="aa"></c:set>

															<c:choose>
																<c:when test="${crnDetail.isGrn==1}">

																	<c:choose>
																		<c:when test="${crnDetail.grnType==0}">
																			<c:set var="type" value="GRN -(1)"></c:set>
																		</c:when>

																		<c:when test="${crnDetail.grnType==1}">
																			<c:set var="type" value="GRN -(2)"></c:set>
																		</c:when>

																		<c:when test="${crnDetail.grnType==2}">
																			<c:set var="type" value="GRN -(3)"></c:set>
																		</c:when>

																		<c:when test="${crnDetail.grnType==4}">
																			<c:set var="type" value="GRN -(3)"></c:set>
																		</c:when>

																	</c:choose>

																</c:when>
																<c:when test="${crnDetail.isGrn==0}">

																	<c:set var="type" value="GVN"></c:set>

																</c:when>
															</c:choose>

															<td align="left"><c:out value="${type}"></c:out></td>

															<td align="left"><c:out value="${crnDetail.grnGvnQty}" />
																</td>

															<td align="left"><c:out
																	value="${crnDetail.taxableAmt}"></c:out></td>

															<td align="left"><c:out
																	value="${crnDetail.totalTax}"></c:out></td>

															<td align="left"><c:out
																	value="${crnDetail.grnGvnAmt}"></c:out></td>
														</tr>

													</c:forEach>

												</tbody>

											</table>
										</div>

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

<script>
function myFunction() {
  var input, filter, table, tr, td, i;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("table1");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[4];
    if (td) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}
</script>


</body>
</html>