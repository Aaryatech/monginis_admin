<html style="overflow-x: hidden;">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body>
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>


	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/tableSearch.css">


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
						<i class="fa fa-file-o"></i>Employee
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
								<i class="fa fa-bars"></i>Employee List
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showAddHrEmp"></a> <a
									data-action="collapse" href="#"><i class="fa fa-chevron-up"></i></a>
							</div>

						</div>

						<div class="box-content">
							
							
							<div class="box">
							

								<div class="box-content">

									<%-- <jsp:include page="/WEB-INF/views/include/tableSearch.jsp"></jsp:include> --%>

									<div class="col-md-9"></div>
									<label for="search" class="col-md-3" id="search"> <i
										class="fa fa-search" style="font-size: 20px"></i> <input
										type="text" id="myInput"
										style="border-radius: 25px; box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2);"
										onkeyup="myFunction()" placeholder="Search.."
										title="Type in a name">
									</label>

									<div class="clearfix"></div>
									<div id="table-scroll" class="table-scroll">

										<div id="faux-table" class="faux-table" aria="hidden" >
											<table id="table2" class="main-table">
												<thead>
													<tr class="bgpink">
														<th width="45" style="width: 18px">Sr.No.</th>
														<th width="100" align="left" style="width: 80">Photo</th>
														<th width="100" align="left">DSC</th>
														<th width="100" align="left">EMP Code</th>
														<th width="100" align="left">Name</th>
														<th width="100" align="left">Department</th>
														<th width="100" align="left">Designation</th>
														<th width="100" align="left">Company</th>

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
														<th width="100" align="left" style="width: 80">Photo</th>
														<th width="100" align="left">DSC</th>
														<th width="100" align="left">EMP Code</th>
														<th width="100" align="left">Name</th>
														<th width="100" align="left">Department</th>
														<th width="100" align="left">Designation</th>
														<th width="100" align="left">Company</th>

														<th width="81" align="left">Action</th>
													</tr>
												</thead>

												<tbody>
													<c:forEach items="${empList}" var="empList"
														varStatus="count">
														<tr>

															<td width="45" style="width: 18px"><c:out value="${count.index+1}" /></td>

															<td width="100" align="left"><img
																src="${url}${empList.empPhoto}" width="80" height="60" /></td>

															<td width="100" align="left"><c:out value="${empList.empDsc}"></c:out></td>

															<td width="100" align="left"><c:out value="${empList.empCode}"></c:out></td>

															<td width="100" align="left"><c:out
																	value="${empList.empFname} ${empList.empMname} ${empList.empSname}"></c:out></td>
															<td width="100" align="left"><c:out value="${empList.deptName}"></c:out></td>

															<td width="100" align="left"><c:out value="${empList.catName}"></c:out></td>

															<td width="100" align="left"><c:out
																	value="${empList.companyName}"></c:out></td>


															<td width="81" align="left"><a
																href="${pageContext.request.contextPath}/updateHrEmpAcc/${empList.empId}"><span
																	class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp; <a
																href="${pageContext.request.contextPath}/deleteHrEmpFromList/${empList.empId}"
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
			</div>
				<!-- END Main Content -->
				<footer>
					<p>2017 © MONGINIS.</p>
				</footer>

				<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
					class="fa fa-chevron-up"></i></a>
			
			<!-- END Content -->
		</div>
		</div>
		<!-- END Container -->



		<!-- Search -->
		<script>
			function myFunction() {
				var input, filter, table, tr, td, i;
				input = document.getElementById("myInput");
				filter = input.value.toUpperCase();

				if (!document.getElementById('table1')) {

					table = document.getElementById("table_grid");
				} else {
					table = document.getElementById("table1");
				}
				tr = table.getElementsByTagName("tr");
				//td = table.getElementsByTagName("td");
				//alert("td value = "+td.length);
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
