<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tableSearch.css">


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
						<i class="fa fa-file-o"></i>Special Cake
					</h1>
				</div>
			</div>
			<!-- END Page Title -->


			<!-- BEGIN Main Content -->
			<div class="row">
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-12">
							<div class="box">


								<div class="row">
									<div class="col-md-12">

										<div class="box">
											<div class="box-title">
												<h3>
													<i class="fa fa-table"></i> Special Cake List
												</h3>
												<div class="box-tool">
													<a data-action="collapse" href="#"><i
														class="fa fa-chevron-up"></i></a>
													<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
												</div>
											</div>
											`
											
						<div class="box-content">
<div class="col-md-9" ></div> 
					<label for="search" class="col-md-3" id="search">
    <i class="fa fa-search" style="font-size:20px"></i>
									<input type="text"  id="myInput" onkeyup="myFunction()" placeholder="Search by code.." title="Type in a name">
										</label>  

							<div class="clearfix"></div>
							
							
							
							
							
								<div id="table-scroll" class="table-scroll">
							 
									<div id="faux-table" class="faux-table" aria="hidden">
									<table id="table2" class="main-table">
											<thead>
												<tr class="bgpink">
														<th width="138" style="width: 18px" align="left">Sr No</th>
																<th width="364" align="left">Image</th>
																<th width="230" align="center">Code</th>
																<th width="230" align="center">Name</th>
																<th width="106" align="left">Type</th>
																<th width="206" align="left">Min Weight</th>
																<th width="206" align="left">Max Weight</th>
																<th width="206" align="left">Book Before</th>
																<th width="90" align="left">Action</th>
												</tr>
												</thead>
												</table>
									
									</div>
									<div class="table-wrap">
									
										<table id="table1" class="table table-advance">
											<thead>
												<tr class="bgpink">
													<th width="138" style="width: 18px" align="left">Sr No</th>
																<th width="364" align="left">Image</th>
																<th width="230" align="center">Code</th>
																<th width="230" align="center">Name</th>
																<th width="106" align="left">Type</th>
																<th width="206" align="left">Weight</th>
																<th width="206" align="left">Weight</th>
																<th width="206" align="left">Before</th>
																<th width="90" align="left">Action</th>
												</tr>
												</thead>
												<tbody>
					<c:forEach items="${specialCakeList}" var="specialCake" varStatus="count">


																<tr>
																	<td><c:out value="${count.index+1}"/></td>
																	<td align="left">
																			 <img src="${url}${specialCake.spImage}" width="120" height="100" 	
																			 onerror="this.src='resources/img/No_Image_Available.jpg';"/> 
																	</td>
																	<td align="left"><c:out
																			value="${specialCake.spCode}  "></c:out></td>
																	<td align="left"><c:out
																			value="${specialCake.spName}  "></c:out></td>
																	<c:choose>
																			<c:when test="${specialCake.spType==1}">
																			<td align="left"><c:out value="Chocolate"></c:out></td>
																				
																			</c:when>
																			<c:when test="${specialCake.spType==2}">
																			<td align="left"><c:out value="FC"></c:out></td>
																				
																			</c:when>
																			
																			
																			<c:otherwise>
																				<td align="left"><c:out value="ALL"></c:out></td>
																				
																			</c:otherwise>
																		</c:choose>
																			<td align="left"><c:out
																			value="${specialCake.spMinwt}  "></c:out></td>
																				<td align="left"><c:out
																			value="${specialCake.spMaxwt}  "></c:out></td>
																				<td align="left"><c:out
																			value="${specialCake.spBookb4}  "></c:out></td>
																			
																	<td align="left"><a
																		href="updateSpCake/${specialCake.spId}"><span
																			class="glyphicon glyphicon-edit"></span></a>
																			<a href="viewSpCakeDetailed/${specialCake.spId}" class="action_btn" >
					                                                  	<abbr title="Detailed"><i class="fa fa-list"></i></abbr></a>
                                                                         <a href="deleteSpecialCake/${specialCake.spId}"
																		onClick="return confirm('Are you sure want to delete this record');"><span
																			class="glyphicon glyphicon-remove"></span></a></td>
																</tr>
															</c:forEach>


							</tbody>

						</table>
					</div>
				</div>
				
						</div>

											<%-- <div class="box-content">

												<div class="clearfix"></div>
												<div class="table-responsive" style="border: 0">
													<table width="100%" class="table table-advance" id="table1">
														<thead>
															<tr>
																<th width="17" style="width: 18px">#</th>
																<th width="364" align="left">Image</th>
																<th width="282" align="left">Code</th>
																<th width="218" align="left">Name</th>
																<th width="106" align="left">Type</th>
																<th width="206" align="left">Min Weight</th>
																<th width="206" align="left">Max Weight</th>
																<th width="206" align="left">Book Before</th>
																<th width="66" align="left">Action</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${specialCakeList}" var="specialCake" varStatus="count">


																<tr>
																	<td><c:out value="${count.index+1}"/></td>
																	<td align="left">
																			 <img src="${url}${specialCake.spImage}" width="120" height="100" 	
																			 onerror="this.src='resources/img/No_Image_Available.jpg';"/> 
																	</td>
																	<td align="left"><c:out
																			value="${specialCake.spCode}  "></c:out></td>
																	<td align="left"><c:out
																			value="${specialCake.spName}  "></c:out></td>
																	<c:choose>
																			<c:when test="${specialCake.spType==1}">
																			<td align="left"><c:out value="Chocolate"></c:out></td>
																				
																			</c:when>
																			<c:when test="${specialCake.spType==2}">
																			<td align="left"><c:out value="FC"></c:out></td>
																				
																			</c:when>
																			
																			
																			<c:otherwise>
																				<td align="left"><c:out value="ALL"></c:out></td>
																				
																			</c:otherwise>
																		</c:choose>
																			<td align="left"><c:out
																			value="${specialCake.spMinwt}  "></c:out></td>
																				<td align="left"><c:out
																			value="${specialCake.spMaxwt}  "></c:out></td>
																				<td align="left"><c:out
																			value="${specialCake.spBookb4}  "></c:out></td>
																			
																	<td align="left"><a
																		href="updateSpCake/${specialCake.spId}"><span
																			class="glyphicon glyphicon-edit"></span></a>
																			<a href="viewSpCakeDetailed/${specialCake.spId}" class="action_btn" >
					                                                  	<abbr title="Detailed"><i class="fa fa-list"></i></abbr></a>
                                                                         <a href="deleteSpecialCake/${specialCake.spId}"
																		onClick="return confirm('Are you sure want to delete this record');"><span
																			class="glyphicon glyphicon-remove"></span></a></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
												<!-- <div class="form-group">
													<div
														class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
														
														
													</div>
												</div> -->
												</form>
											</div> --%>
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
<script>
function myFunction() {
  var input, filter, table, tr, td, i;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("table1");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[2];
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
</html>