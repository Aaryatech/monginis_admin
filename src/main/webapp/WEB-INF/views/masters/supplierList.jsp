<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tableSearch.css">
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
						<i class="fa fa-file-o"></i>Supplier 
					</h1>
				</div>
			</div>
			<!-- END Page Title -->
				<c:set var="isAdd" value="0">
					</c:set>
				
			
			<c:set var="isEdit" value="0">
					</c:set>
				
					<c:set var="isDelete" value="0">
					</c:set>

					<c:forEach items="${sessionScope.newModuleList}" var="modules">
						<c:forEach items="${modules.subModuleJsonList}" var="subModule">

							<c:choose>
								<c:when test="${subModule.subModuleMapping eq 'showSupplierList'}">

									<c:choose>
										<c:when test="${subModule.editReject=='visible'}">
											<c:set var="isEdit" value="1">
											</c:set>
										</c:when>
										<c:otherwise>
											<c:set var="isEdit" value="0">
											</c:set>
										</c:otherwise>
									</c:choose>

									<c:choose>
										<c:when test="${subModule.deleteRejectApprove=='visible'}">
											<c:set var="isDelete" value="1">
											</c:set>
										</c:when>
										<c:otherwise>
											<c:set var="isDelete" value="0">
											</c:set>
										</c:otherwise>
									</c:choose>
									
									<c:choose>
										<c:when test="${subModule.addApproveConfig=='visible'}">
											<c:set var="isAdd" value="1">
											</c:set>
										</c:when>
										<c:otherwise>
											<c:set var="isAdd" value="0">
											</c:set>
										</c:otherwise>
									</c:choose>
									
								</c:when>
							</c:choose>
						</c:forEach>
					</c:forEach>
			
			
			

			<div class="row">
				<div class="col-md-12">

					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i>Supplier  List
							</h3>
								<div class="box-tool">
								<c:choose>
								<c:when test="${isAdd==1}">
								<a href="${pageContext.request.contextPath}/showAddSupplier">Add Supplier</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
								</c:when>
								<c:otherwise>
								<a href="${pageContext.request.contextPath}/showAddSupplier"  class="disableClick">Add Supplier</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
								</c:otherwise>
								
								</c:choose>
								
							</div>
						</div>

						<div class="box-content">
	<div class="col-md-9" ></div> 
					<label for="search" class="col-md-3" id="search">
    <i class="fa fa-search" style="font-size:20px"></i>
									<input type="text"  id="myInput" onkeyup="myFunction()" placeholder="Search for supplier names.." title="Type in a name">
										</label>  
							<div class="clearfix"></div>

							<div class="table-responsive" style="border: 0">
								<table width="100%" class="table table-advance" id="table1">
									<thead>
										<tr>
											<th width="170" style="width: 18px">Sr.No.</th>
											<th width="190" align="left">Supplier Name</th>
											<th width="190" align="left">Supplier Mobile</th>	
											<th width="200" align="left">City</th>
										     <th width="200" align="left">Credit days</th>
											<th width="88" align="left">Action</th>
										</tr>
									</thead>
									<tbody>
							  <%int count=1; %>
										<c:forEach items="${supplierList}" var="supplierList">
											<tr>
												<td>	<%=count++%>
											<c:out value="${count}" /> 
												</td>
												<td align="left"><c:out
														value="${supplierList.suppName}" /></td>
											
												<td align="left"><c:out
														value="${supplierList.suppMob1}" /></td>
											
												<td align="left"><c:out
														value="${supplierList.suppCity}" /></td>		
										
												<td align="left"><c:out
														value="${supplierList.suppCreditDays}" /></td>	
														
														
															<c:choose>
																	<c:when test="${isEdit==1 and isDelete==1}">
																		<td align="left"><a
													href="${pageContext.request.contextPath}/showSupplierDetails/${supplierList.suppId}"><span
														class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
												
												<a href="${pageContext.request.contextPath}/deleteSupplier/${supplierList.suppId}"
													onClick="return confirm('Are you sure want to delete this record');"><span
														class="glyphicon glyphicon-remove"></span></a></td>
														
																	</c:when>

																	<c:when test="${isEdit==1 and isDelete==0}">
																		<td align="left"><a
													href="${pageContext.request.contextPath}/showSupplierDetails/${supplierList.suppId}"><span
														class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
												
												<a href="${pageContext.request.contextPath}/deleteSupplier/${supplierList.suppId}" class="disableClick"
													onClick="return confirm('Are you sure want to delete this record');"><span
														class="glyphicon glyphicon-remove"></span></a></td>
														
																	</c:when>

																	<c:when test="${isEdit==0 and isDelete==1}">
																		<td align="left"><a
													href="${pageContext.request.contextPath}/showSupplierDetails/${supplierList.suppId}" class="disableClick"><span
														class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
												
												<a href="${pageContext.request.contextPath}/deleteSupplier/${supplierList.suppId}"
													onClick="return confirm('Are you sure want to delete this record');"><span
														class="glyphicon glyphicon-remove"></span></a></td>
																	</c:when>

																	<c:otherwise>

																		<td align="left"><a
													href="${pageContext.request.contextPath}/showSupplierDetails/${supplierList.suppId}" class="disableClick"><span
														class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
												
												<a href="${pageContext.request.contextPath}/deleteSupplier/${supplierList.suppId}" class="disableClick"
													onClick="return confirm('Are you sure want to delete this record');"><span
														class="glyphicon glyphicon-remove"></span></a></td>
														

																	</c:otherwise>
																</c:choose>	
														
																		
												<%-- <td align="left"><a
													href="${pageContext.request.contextPath}/showSupplierDetails/${supplierList.suppId}"><span
														class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
												
												<a href="${pageContext.request.contextPath}/deleteSupplier/${supplierList.suppId}"
													onClick="return confirm('Are you sure want to delete this record');"><span
														class="glyphicon glyphicon-remove"></span></a></td> --%>
											</tr>
										</c:forEach>

									</tbody>
								</table>
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

<script>
function myFunction() {
  var input, filter, table, tr, td, i;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("table1");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1];
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
