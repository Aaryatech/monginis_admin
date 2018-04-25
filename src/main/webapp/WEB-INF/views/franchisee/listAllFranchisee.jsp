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
						<i class="fa fa-file-o"></i> Franchisee
					</h1>
				</div>
			</div>
			<!-- END Page Title -->


			<c:set var="isEdit" value="0">
			</c:set>

			<c:set var="isDelete" value="0">
			</c:set>

			<c:forEach items="${sessionScope.newModuleList}" var="modules">
				<c:forEach items="${modules.subModuleJsonList}" var="subModule">
					<c:choose>
						<c:when
							test="${subModule.subModuleMapping eq 'listAllFranchisee'}">

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
						</c:when>
					</c:choose>

				</c:forEach>
			</c:forEach>


			<!-- BEGIN Main Content -->
			<div class="row">
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-12">
							<div class="box">
								<div class="box-title">
									<h3>
										<i class="fa fa-bars"></i> Franchisee List
									</h3>
									<div class="box-tool">
										<a href="${pageContext.request.contextPath}/listAllFranchisee">Back
											to List</a> <a data-action="collapse" href="#"><i
											class="fa fa-chevron-up"></i></a>
									</div>
								</div>
								<div class="box-content">
									<div class="col-md-9"></div>
									<label for="search" class="col-md-3" id="search"> <i
										class="fa fa-search" style="font-size: 20px"></i> <input
										type="text" id="myInput" onkeyup="myFunction()"
										placeholder="Search.." title="Type in a name">
									</label>
									<div class="clearfix"></div>
									<div id="table-scroll" class="table-scroll">
										<div id="faux-table" class="faux-table" aria="hidden">
											<table id="table2" class="main-table">
												<thead>
													<tr class="bgpink">
														<th width="158" style="width: 18px" align="left">#</th>
														<th class="col-md-2">Name</th>
														<th class="col-md-2">Image</th>
														<th class="col-md-2">Owner</th>
														<th class="col-md-2">City</th>
														<th class="col-md-2">Mobile No.</th>
														<th class="col-md-2">Route</th>
														<!--  <th width="70" align="left">Rate Type</th> -->
														<!--  <th class="col-md-2">GST Type</th> -->
														<th class="col-md-2">Stock Type</th>
														<th class="col-md-2">Rating</th>
														<!--                 <th class="col-md-2">Status</th>
 -->
														<th class="col-md-2" width="90px">Action</th>
													</tr>
												</thead>
											</table>

										</div>
										<div class="table-wrap">

											<table id="table1" class="table table-advance">
												<thead>
													<tr class="bgpink">
														<th width="138" style="width: 18px" align="left">#</th>
														<th class="col-md-2">Name</th>
														<th class="col-md-2">Image</th>
														<th class="col-md-2">Owner</th>
														<th class="col-md-2">City</th>
														<th class="col-md-2">Mobile No.</th>
														<th class="col-md-2">Route</th>
														<!--    <th class="col-md-2">Rate Type</th>
                                          <th class="col-md-2">GST Type</th> -->
														<th class="col-md-2">Stock Type</th>
														<th class="col-md-2">Rating</th>
														<!--   <th class="col-md-2">Status</th> -->
														<th class="col-md-2" width="90px">Action</th>
													</tr>
												</thead>
												<tbody style="padding-top: 100px">

													<c:forEach items="${franchiseeList}" var="franchiseeList"
														varStatus="count">

														<tr>
															<td><c:out value="${count.index+1}"></c:out></td>
															<td align="left"><c:out
																	value="${franchiseeList.frName}" /></td>
															<td align="left"><img
																src="${url}${franchiseeList.frImage}" height="80"
																width="80"
																onerror="this.src='${pageContext.request.contextPath}/resources/img/No_Image_Available.jpg';" />


															</td>
															<td align="left"><c:out
																	value="${franchiseeList.frOwner}" /></td>
															<td align="left"><c:out
																	value="${franchiseeList.frCity}" /></td>
															<td align="left"><c:out
																	value="${franchiseeList.frMob}" /></td>
															<td align="left"><c:forEach items="${routeList}"
																	var="routeList">

																	<c:choose>
																		<c:when
																			test="${routeList.routeId==franchiseeList.frRouteId}">
																			<c:out value="${routeList.routeName}" />
																		</c:when>

																		<c:otherwise></c:otherwise>
																	</c:choose>
																</c:forEach></td>
															<%--   <td align="left">
               <c:choose>
                    <c:when test="${franchiseeList.frRateCat==1}">
                          <c:out value="Local Rate"/> 
                    </c:when>
                    <c:when test="${franchiseeList.frRateCat==2}">
                          <c:out value="Outstation Rate"/> 
                    </c:when>
                    <c:when test="${franchiseeList.frRateCat==3}">
                          <c:out value="Special Rate"/> 
                    </c:when>
                    <c:otherwise></c:otherwise>
              </c:choose>
             </td> --%>

															<%--    <td align="left">
                 <c:choose>
                    <c:when test="${franchiseeList.frGstType==0}">
                          <c:out value="Non-Registered"/> 
                    </c:when>
                     <c:when test="${franchiseeList.frGstType==2000000}">
                          <c:out value="Composite"/> 
                    </c:when>
                     <c:when test="${franchiseeList.frGstType==10000000}">
                          <c:out value="Above 75 lakh"/> 
                    </c:when>
                <c:otherwise><c:out value="Above 75 lakh"/> </c:otherwise>
              </c:choose>
                
               </td> --%>
															<td align="left"><c:choose>
																	<c:when test="${franchiseeList.stockType==1}">
																		<c:out value="Type1" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==2}">
																		<c:out value="Type2" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==3}">
																		<c:out value="Type3" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==4}">
																		<c:out value="Type4" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==5}">
																		<c:out value="Type5" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==6}">
																		<c:out value="Type6" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==7}">
																		<c:out value="Type7" />
																	</c:when>
																	<c:otherwise></c:otherwise>
																</c:choose></td>
															<td align="left"><c:choose>
																	<c:when test="${franchiseeList.frRate==0}">0.5</c:when>
																	<c:when test="${franchiseeList.frRate==1}">1</c:when>
																	<c:when test="${franchiseeList.frRate==2}">1.5</c:when>
																	<c:when test="${franchiseeList.frRate==3}">2</c:when>
																	<c:when test="${franchiseeList.frRate==4}">2.5</c:when>
																	<c:when test="${franchiseeList.frRate==5}">3</c:when>
																	<c:when test="${franchiseeList.frRate==6}">3.5</c:when>
																	<c:when test="${franchiseeList.frRate==7}">4</c:when>
																	<c:when test="${franchiseeList.frRate==8}">4.5</c:when>
																	<c:when test="${franchiseeList.frRate==9}">5</c:when>
																</c:choose></td>
															<%--   <td align="left">
             <c:choose>
  <c:when test="${franchiseeList.delStatus==0}">
  Active    
  </c:when>
 
  <c:otherwise>
 In-Active
  </c:otherwise>
</c:choose>
              </td> --%>







															<c:choose>
																<c:when test="${isEdit==1 and isDelete==1}">

																	<td align="left"><a
																		href="updateFranchisee/${franchiseeList.frId}"><span
																			class="glyphicon glyphicon-edit"></span></a>&nbsp; <a
																		href="deleteFranchisee/${franchiseeList.frId}"
																		onClick="return confirm('Are you sure want to delete this record');"><span
																			class="glyphicon glyphicon-remove"></span></a></td>
																</c:when>

																<c:when test="${isEdit==1 and isDelete==0}">

																	<td align="left"><a
																		href="updateFranchisee/${franchiseeList.frId}"><span
																			class="glyphicon glyphicon-edit"></span></a>&nbsp; <a
																		href="deleteFranchisee/${franchiseeList.frId}"
																		class="disableClick"
																		onClick="return confirm('Are you sure want to delete this record');"><span
																			class="glyphicon glyphicon-remove"></span></a></td>
																</c:when>

																<c:when test="${isEdit==0 and isDelete==1}">

																	<td align="left"><a
																		href="updateFranchisee/${franchiseeList.frId}"
																		class="disableClick"><span
																			class="glyphicon glyphicon-edit"></span></a>&nbsp; <a
																		href="deleteFranchisee/${franchiseeList.frId}"
																		onClick="return confirm('Are you sure want to delete this record');"><span
																			class="glyphicon glyphicon-remove"></span></a></td>
																</c:when>

																<c:otherwise>


																	<td align="left"><a
																		href="updateFranchisee/${franchiseeList.frId}"
																		class="disableClick"><span
																			class="glyphicon glyphicon-edit"></span></a>&nbsp; <a
																		href="deleteFranchisee/${franchiseeList.frId}"
																		class="disableClick"
																		onClick="return confirm('Are you sure want to delete this record');"><span
																			class="glyphicon glyphicon-remove"></span></a></td>

																</c:otherwise>
															</c:choose>

															<%-- <td align="left"><a
																href="updateFranchisee/${franchiseeList.frId}"><span
																	class="glyphicon glyphicon-edit"></span></a>&nbsp; <a
																href="deleteFranchisee/${franchiseeList.frId}"
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
							<div class="form-group" id="range">



								<div class="col-sm-3  controls">
									<input type="button" id="expExcel" class="btn btn-primary"
										value="EXPORT TO Excel" onclick="exportToExcel();">
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

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>



</body>

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
<script type="text/javascript">
function exportToExcel()
{
	window.open("${pageContext.request.contextPath}/exportToExcel");
			document.getElementById("expExcel").disabled=true;
}
</script>

</html>