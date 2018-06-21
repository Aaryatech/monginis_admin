<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
						<i class="fa fa-file-o"></i> Spare Part List
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
								<i class="fa fa-table"></i>  Spare Part List
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
									<input type="text"  id="myInput" onkeyup="myFunction()" placeholder="Search...." title="Type in a name">
										</label>  

							<div class="clearfix"></div>
							
								<div id="table-scroll" class="table-scroll">
							 
									<div id="faux-table" class="faux-table" aria="hidden">
									<table id="table2" class="main-table">
											<thead>
												<tr class="bgpink">
										<th>Sr.No.</th>
										<th>Part Name</th> 
										<th>Company</th>
										<th>Type</th>
										<th>Group</th>
										<th>UOM</th>
										<th>Is Critical</th>
										<th>Date </th>
										<th>Date Rate</th>
										<th>Warranty</th> 
										<th>Action</th>

									</tr>
												</thead>
												</table>
									
									</div>
									<div class="table-wrap">
									
										<table id="table1" class="table table-advance">
											<thead>
												<tr class="bgpink">
										<th>Sr.No.</th>
										<th>Part Name</th> 
										<th>Company Name</th>
										<th>Type</th>
										<th>Group</th>
										<th>UOM</th>
										<th>Is Critical</th>
										<th>Date </th>
										<th>Date Rate</th>
										<th>Warranty</th> 
										<th>Action</th>

									</tr>
												</thead>
												<tbody>
											<c:set var = "srNo" value="0"/>
									<c:forEach items="${sprPartList}" var="sprPartList"
													varStatus="count">
													
													 
													<tr>
														<td ><c:out value="${count.index+1}" /></td>
 														<c:set var = "srNo" value="${count.index}"/> 
														<td align="left" ><c:out value="${sprPartList.sprName}" /></td> 
																
																<c:forEach items="${makeList}" var="makeList">
																<c:choose>
																<c:when test="${makeList.makeId==sprPartList.makeId}">
																<td align="left" ><c:out value="${makeList.makeName}" /></td> 
																</c:when> 
																</c:choose> 
																</c:forEach>
																
																<c:forEach items="${mechTypeList}" var="mechTypeList">
																	<c:choose>
																		<c:when test="${mechTypeList.typeId==sprPartList.typeId}">
																		<td align="left" ><c:out value="${mechTypeList.typeName}" /></td>
																		</c:when>
																	</c:choose>
																</c:forEach>
																
																<c:forEach items="${sprGroupList}" var="sprGroupList">
																<c:choose>
																<c:when test="${sprGroupList.groupId==sprPartList.groupId}">
																<td align="left" ><c:out value="${sprGroupList.groupName}" /></td> 
																</c:when> 
																</c:choose> 
																</c:forEach>
														   	  
																<td align="left" ><c:out value="${sprPartList.sprUom}" /></td>
																<c:choose>
																	<c:when test="${sprPartList.sprIscritical==1}">
																		<c:set var="critical" value="Yes"></c:set>
																	</c:when>
																	<c:when test="${sprPartList.sprIscritical==2}">
																		<c:set var="critical" value="No"></c:set>
																	</c:when> 
																</c:choose> 
																<td align="left" ><c:out value="${critical}" /></td>
														   	 	<td align="left" ><c:out value="${sprPartList.sprDate1}" /></td>
																<td align="left" ><c:out value="${sprPartList.sprRate1}" /></td>
																<td align="left" ><c:out value="${sprPartList.sprWarrantyPeriod}" /></td>
														 
													<td>  <a href="${pageContext.request.contextPath}/editSpareParts/${sprPartList.sprId}"><span class="glyphicon glyphicon-edit" > </span></a>  
						<a href="${pageContext.request.contextPath}/deleteSparePart/${sprPartList.sprId}" onClick="return confirm('Are you sure want to delete this record');"   >
						<span class="glyphicon glyphicon-remove" > </span></a>
						
						</td>
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



			<!-- END Main Content -->
			<footer>
			<p>2018 © MONGINIS.</p>
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
  var input, filter, table, tr, td,td1, i;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("table1");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[2];
    td1 = tr[i].getElementsByTagName("td")[1];
    if (td || td1) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      }else if (td1.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      }  else {
        tr[i].style.display = "none";
      }
    }       
  }//end of for
  
 
  
}
</script>


</html>