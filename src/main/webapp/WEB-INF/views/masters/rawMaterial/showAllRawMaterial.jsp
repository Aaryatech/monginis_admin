<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<style>
  
/* #search {
   
    
  background-position: 10px 10px;
  background-repeat: no-repeat;
  width: 100%;
  font-size: 14px;
  padding: 5px 5px 5px 30px;
  border: 1px solid #ddd;
  margin-bottom: 0px;
   margin-top: 5px;
} */

</style>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tableSearch.css">
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
						<i class="fa fa-file-o"></i>Raw Material Master
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
								<i class="fa fa-bars"></i>View Raw Material
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showAddRawMaterial">Add Raw Material</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							<!-- <div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> <a data-action="close" href="#"><i
									class="fa fa-times"></i></a>
							</div> -->
						</div>


						<div class="box-content">
							
							<div class="row">
							<form action="showRawMaterialDetails" method="post" class="form-horizontal" id=
									"validation-form"
										enctype="multipart/form-data" method="post">
									 
					<div class="colOuter">	
					<div class="col-md-2" > </div>
					<div class="col-md-2" >Group</div>
									<div class="col-md-4">
										<select name="rm_group" id="rm_group" class="form-control" tabindex="6">
										<option value="-1">Select Group</option>
											 <c:forEach items="${groupList}" var="groupList"
							varStatus="count">
							
									<c:choose>
													<c:when test="${groupList.grpId==grpId}">
														<option selected value="${groupList.grpId}"><c:out value="${groupList.grpName}"/></option>
												</c:when>
													<c:otherwise>
  														 <option value="${groupList.grpId}"><c:out value="${groupList.grpName}"/></option>
 													 </c:otherwise>
 													 </c:choose>
												</c:forEach>
						

										</select>
									</div>

					</div>
					 
									<div class="col-md-4">
										<input type="submit" id="search" class="btn btn-info"
											value="Search" />



									</div>
										
								 
					
					</form> 
					<div class="col-md-9" ></div> 
					<label for="search" class="col-md-3" id="search">
    <i class="fa fa-search" style="font-size:20px"></i>
									<input type="text"  id="myInput" onkeyup="myFunction()" placeholder="Search for Raw Material names.." title="Type in a name">
										</label>  
				</div>
				
				

									<div class="clearfix"></div>
									<div class="table-responsive" style="border: 0">
									 
				
									 
										<table width="100%" class="table table-advance" id="table1">
											<thead>
												<tr>

													<th width="100"  align="left">Sr No</th>
													<th width="188" align="left">RM Name</th>
													<th width="120" align="left">Category</th>
													<th width="190" align="center">Sub Category</th>
													<th width="270" align="center">BMS ROL Qty</th>
 
													<th width="270" align="center">Store ROL Qty</th>
											     	<th width="40" align="center">SGST %</th> 
												 	<th width="40" align="center">CGST %</th> 
	                                                <th width="40" align="center">IGST %</th> 
													<th class="col-sm-1"> Is Critical</th>
													<th width="105" align="left"> Action</th>
												 

												</tr>

											</thead>

											<tbody>

												<c:forEach items="${RawmaterialList}" var="RawmaterialList"
													varStatus="count">

													<tr>
														<td><c:out value="${count.index+1}" /></td>

														<td align="left"><c:out
																value="${RawmaterialList.rmName}" /></td>


														<td align="left"><c:out
																value="${RawmaterialList.catName}" /></td>

														<td align="center"><c:out
																value="${RawmaterialList.sunCatName}" /></td>

														<td align="center"><c:out value="${RawmaterialList.bmsRolQty}" /></td>

														<td align="center"><c:out value="${RawmaterialList.storeRolQty}" /></td>

														 <td class="col-sm-1" align="left"><c:out value="${RawmaterialList.sgstPer}" /></td>
														 <td class="col-sm-1" align="left"><c:out value="${RawmaterialList.cgstPer}" /></td>
														 <td class="col-sm-1" align="left"><c:out value="${RawmaterialList.igstPer}" /></td>
														<c:choose>
														<c:when test="${RawmaterialList.rmIsCritical==0}">
														<td class="col-sm-1" align="center"><c:out
																value="Low" /></td>
														</c:when>
														<c:when test="${RawmaterialList.rmIsCritical==1}">
														<td class="col-sm-1" align="center"><c:out
																value="Normal" /></td>
														</c:when>
														<c:when test="${RawmaterialList.rmIsCritical==2}">
														<td class="col-sm-1" align="center"><c:out
																value="High" /></td>
														</c:when>
														</c:choose>
														
					<td><a href="${pageContext.request.contextPath}/getRawMaterialDetails?selectedRmId=${RawmaterialList.rmId}" class="action_btn" >
						<abbr title="Details"><i class="fa fa-edit fa-lg"></i></abbr></a>
						
					<a href="${pageContext.request.contextPath}/deleteRawMaterial/${RawmaterialList.rmId}/${grpId}"
													onClick="return confirm('Are you sure want to delete this record');"><span
														class="glyphicon glyphicon-remove fa-lg"></span></a>	
						
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