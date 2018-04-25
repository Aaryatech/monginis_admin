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
						<i class="fa fa-file-o"></i> Item Ledger
					</h1>
				</div>
			</div>
			<!-- END Page Title -->
			
			
			<c:set var="isEdit" value="0">
					</c:set>
					<c:set var="isView" value="0">
					</c:set>
					<c:set var="isDelete" value="0">
					</c:set>

					<c:forEach items="${sessionScope.newModuleList}" var="modules">
						<c:forEach items="${modules.subModuleJsonList}" var="subModule">

							<c:choose>
								<c:when test="${subModule.subModuleMapping eq 'itemList'}">

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
										<c:when test="${subModule.view=='visible'}">
											<c:set var="isView" value="1">
											</c:set>
										</c:when>
										<c:otherwise>
											<c:set var="isView" value="0">
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
			

			<div class="row hidden-xs">
				<div class="col-md-12">
					<div class="box box-green">
						<div class="box-title">
							<h3>
								<i class="fa fa-bars"></i> Search Items
							</h3>
							<div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
						</div>
						<div class="box-content">

							<form name="searchItem" id="searchItem" class="form-horizontal"
								method="post" action="searchItem">
								<input type="hidden" name="mod_ser" id="mod_ser"
									value="search_result">

								<div class="row">

									<div class="col-md-6 ">
										<!-- BEGIN Right Side -->
										<div class="form-group">

											<label for="textfield2"
												class="col-xs-3 col-lg-2 control-label">Items</label>
											<div class="col-sm-9 col-lg-10 controls">
												<select class="form-control input-sm" name="catId"
													id="catId">


													<c:forEach items="${mCategoryList}" var="mCategoryList">

														<c:set var="mCatId" value="${mCategoryList.catId}" />
														<c:set var="catId" value="${catId}" />
														<c:choose>
															<c:when test="${mCatId==catId}">
																<option selected value="${mCategoryList.catId}"><c:out value="${mCategoryList.catName}"></c:out></option>

															</c:when>

															<c:otherwise>

																<option value="${mCategoryList.catId}"><c:out value="${mCategoryList.catName}"></c:out></option>

															</c:otherwise>
														</c:choose>





														<%-- <option value="${mCategoryList.catId}"><c:out value="${mCategoryList.catName}"></c:out></option> --%>
													</c:forEach>


												</select>
											</div>
										</div>


										<div class="form-group">
											<div
												class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
												<button type="submit" class="btn btn-primary">
													<i class="fa fa-check"></i> Search
												</button>
											</div>
										</div>
										<!-- END Right Side -->
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- BEGIN Main Content -->
			<div class="row">
				<div class="col-md-12">

					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i> Items List
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
									<input type="text"  id="myInput" onkeyup="myFunction()" placeholder="Search items by Name or Code" title="Type in a name">
										</label>  

							<div class="clearfix"></div>
							
								<div id="table-scroll" class="table-scroll">
							 
									<div id="faux-table" class="faux-table" aria="hidden">
									<table id="table2" class="main-table">
											<thead>
												<tr class="bgpink">
														<th class="col-md-1">Sr No</th>
											<th class="col-md-2">Item Id</th>
											<th class="col-md-3">Item Name</th>
<!-- 											<th class="col-md-2">Image</th>
 -->											<th class="col-md-2">Rate</th>
											<th class="col-md-2">MRP</th>
											<th class="col-md-1">Action</th>
												</tr>
												</thead>
												</table>
									
									</div>
									<div class="table-wrap">
									
										<table id="table1" class="table table-advance">
											<thead>
												<tr class="bgpink">
													<th class="col-md-1">Sr No</th>
											<th class="col-md-2">Item Id</th>
											<th class="col-md-3">Item Name</th>
<!-- 											<th class="col-md-2">Image</th>
 -->											<th class="col-md-2">Rate</th>
											<th class="col-md-2">MRP</th>
											<th class="col-md-1">Action</th>
												</tr>
												</thead>
												<tbody>
											
	<c:forEach items="${itemsList}" var="itemsList" varStatus="count">
											<tr>
												<td><c:out value="${count.index+1}" /></td>
												<td align="left"><c:out value="${itemsList.itemId}" /></td>
												<td align="left"><c:out value="${itemsList.itemName}"/></td>
												
											<%-- 	<td align="left">
												<img
													src="${url}${itemsList.itemImage}" width="120" height="100"
													onerror="this.src='${pageContext.request.contextPath}/resources/img/No_Image_Available.jpg';"/>
													
												</td> --%>
												<td align="left"><c:out value="${itemsList.itemRate1}" /></td>
												<td align="left"><c:out value="${itemsList.itemMrp1}" /></td>
												
												
												
												
												
												
												<c:choose>
																	<c:when test="${isEdit==1 and isDelete==1}">
																		<td align="left"><a href="updateItem/${itemsList.id}"><span
														class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;
                                             <a href="showItemDetail/${itemsList.id}"><span
														class="glyphicon glyphicon-list"></span></a>
													&nbsp;&nbsp;
													<a href="deleteItem/${itemsList.id}"
													onClick="return confirm('Are you sure want to delete this record');"><span
														class="glyphicon glyphicon-remove"></span></a></td>
														
																	</c:when>

																	<c:when test="${isEdit==1 and isDelete==0}">
																		<td align="left"><a href="updateItem/${itemsList.id}"><span
														class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;
                                             <a href="showItemDetail/${itemsList.id}"><span
														class="glyphicon glyphicon-list"></span></a>
													&nbsp;&nbsp;
													<a href="deleteItem/${itemsList.id}" class="disableClick"
													onClick="return confirm('Are you sure want to delete this record');"><span
														class="glyphicon glyphicon-remove"></span></a></td>
														
																	</c:when>

																	<c:when test="${isEdit==0 and isDelete==1}">
																		<td align="left"><a href="updateItem/${itemsList.id}" class="disableClick"><span
														class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;
                                             <a href="showItemDetail/${itemsList.id}"><span
														class="glyphicon glyphicon-list"></span></a>
													&nbsp;&nbsp;
													<a href="deleteItem/${itemsList.id}"
													onClick="return confirm('Are you sure want to delete this record');"><span
														class="glyphicon glyphicon-remove"></span></a></td>
														
																	</c:when>

																	<c:otherwise>

																		<td align="left"><a href="updateItem/${itemsList.id}" class="disableClick"><span
														class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;
                                             <a href="showItemDetail/${itemsList.id}"><span
														class="glyphicon glyphicon-list"></span></a>
													&nbsp;&nbsp;
													<a href="deleteItem/${itemsList.id}" 
													onClick="return confirm('Are you sure want to delete this record');"><span
														class="glyphicon glyphicon-remove"></span></a></td>
														

																	</c:otherwise>
																</c:choose>
												
												
												
												
												
												<%-- <td align="left"><a href="updateItem/${itemsList.id}"><span
														class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;
                                             <a href="showItemDetail/${itemsList.id}"><span
														class="glyphicon glyphicon-list"></span></a>
													&nbsp;&nbsp;
													<a href="deleteItem/${itemsList.id}" class="disableClick"
													onClick="return confirm('Are you sure want to delete this record');"><span
														class="glyphicon glyphicon-remove"></span></a></td>
														 --%>
														
											</tr>

										</c:forEach>


							</tbody>

						</table>
					</div>
				</div>
				
						</div>
						
						
						<div class="form-group"  id="range">
								 
											 
											 
											<div class="col-sm-3  controls">
											 <input type="button" id="expExcel" class="btn btn-primary" value="EXPORT TO Excel" onclick="exportToExcel();">
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

<script type="text/javascript">
function exportToExcel()
{
	window.open("${pageContext.request.contextPath}/exportToExcel");
			document.getElementById("expExcel").disabled=true;
}
</script>

</html>