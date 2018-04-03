<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<div class="container" id="main-container">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tableSearch.css">

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
						<i class="fa fa-file-o"></i> Item Supplement
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
								<i class="fa fa-table"></i>Item Supplement Ledger
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showAddItemSup">Add New Item Supplement</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
						</div>
                          <div class="box-content">
<div class="col-md-9" ></div> 
					<label for="search" class="col-md-3" id="search">
    <i class="fa fa-search" style="font-size:20px"></i>
									<input type="text"  id="myInput" onkeyup="myFunction()" placeholder="Search items.." title="Type in a name">
										</label>  


							<div class="clearfix"></div>
							
							
							
							
							
								<div id="table-scroll" class="table-scroll">
							 
									<div id="faux-table" class="faux-table" aria="hidden">
									<table id="table2" class="main-table">
											<thead>
												<tr class="bgpink">
														<th width="17" style="width: 18px">#</th>
											<th width="160" align="left">Item Id</th>
											<th width="300" align="left">Item Name</th>
											
											<th width="240" align="center">HSN Code</th>
											<th width="200" align="left">Unit Of Measure</th>
											<th width="193" align="left">Actual Weight</th>
											<th width="167" align="left">Base Weight</th>
											<th width="200" align="left">Input Per Unit</th>
									   <!--     <th width="200" align="left">Gate Sale Allowed?</th>
							               <th width="200" align="left">Gate Sale Disc Allowed?</th>
							               <th width="200" align="left">Allowed For Emp Birthday?</th> -->

											<th width="150" align="left">Action</th>
												</tr>
												</thead>
												</table>
									
									</div>
									<div class="table-wrap">
									
										<table id="table1" class="table table-advance">
											<thead>
												<tr class="bgpink">
												<th width="17" style="width: 18px">#</th>
											<th width="160" align="left">Item Id</th>
											<th width="300" align="left">Item Name</th>
											
											<th width="240" align="center">HSN Code</th>
											<th width="200" align="left">Unit Of Measure</th>
											<th width="193" align="left"> Weight</th>
											<th width="167" align="left"> Weight</th>
											<th width="200" align="left">Unit</th>
									    <!--    <th width="200" align="left">Allowed?</th>
							               <th width="200" align="left">Allowed?</th>
							               <th width="200" align="left">Birthday?</th>
 -->
											<th width="150" align="left">Action</th>
												</tr>
												</thead>
												<tbody>
			 <c:forEach items="${itemsList}" var="itemsList" varStatus="count">
											<tr>
												<td><c:out value="${count.index+1}" /></td>
												<td align="left"><c:out value="${itemsList.itemId}" /></td>
												<td align="left"><c:out value="${itemsList.itemName}" /></td>
												
												<td align="left"><c:out value="${itemsList.itemHsncd}" /></td>
												<td align="left"><c:out value="${itemsList.itemUom}" /></td>
										        <td align="left"><c:out value="${itemsList.actualWeight}" /></td>
												<td align="left"><c:out value="${itemsList.baseWeight}" /></td>
											    <td align="left"><c:out value="${itemsList.inputPerQty}" /></td>
											<%-- 	<c:choose><c:when test="${itemsList.isGateSale==0}">
												<td align="left"><c:out value="NO" /></td>
												</c:when>
												<c:when test="${itemsList.isGateSale==1}">
												<td align="left"><c:out value="YES" /></td>
												</c:when>
												</c:choose>
												
												<c:choose><c:when test="${itemsList.isGateSaleDisc==0}">
												<td align="left"><c:out value="NO" /></td>
												</c:when>
												<c:when test="${itemsList.isGateSaleDisc==1}">
												<td align="left"><c:out value="YES" /></td>
												</c:when>
												</c:choose>
												<c:choose>
												<c:when test="${itemsList.isAllowBday==0}">
												<td align="left"><c:out value="NO" /></td>
												</c:when>
												<c:when test="${itemsList.isAllowBday==1}">
												<td align="left"><c:out value="YES" /></td>
												</c:when>
												</c:choose> --%>
												<td align="left"><a href="updateItemSup/${itemsList.id}"><span
														class="glyphicon glyphicon-edit"></span></a>
                                             </td>
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
											<th width="100" align="left">Item Id</th>
											<th width="150" align="left">Item Name</th>
											
											<th width="100" align="left">HSN Code</th>
											<th width="100" align="left">UOM</th>
											<th width="103" align="left">Actual Weight</th>
											<th width="137" align="left">Base Weight</th>
											<th width="200" align="left">Input Per Unit</th>
									       <th width="200" align="left">Gate Sale Allowed?</th>
							               <th width="200" align="left">Gate Sale Disc Allowed?</th>
							               <th width="200" align="left">Allowed For Emp Birthday?</th>

											<th width="150" align="left">Action</th>
										</tr>
									</thead>
									<tbody>
									 <c:forEach items="${itemsList}" var="itemsList" varStatus="count">
											<tr>
												<td><c:out value="${count.index+1}" /></td>
												<td align="left"><c:out value="${itemsList.itemId}" /></td>
												<td align="left"><c:out value="${itemsList.itemName}" /></td>
												
												<td align="left"><c:out value="${itemsList.itemHsncd}" /></td>
												<td align="left"><c:out value="${itemsList.itemUom}" /></td>
										        <td align="left"><c:out value="${itemsList.actualWeight}" /></td>
												<td align="left"><c:out value="${itemsList.baseWeight}" /></td>
											    <td align="left"><c:out value="${itemsList.inputPerQty}" /></td>
												<c:choose><c:when test="${itemsList.isGateSale==0}">
												<td align="left"><c:out value="NO" /></td>
												</c:when>
												<c:when test="${itemsList.isGateSale==1}">
												<td align="left"><c:out value="YES" /></td>
												</c:when>
												</c:choose>
												
												<c:choose><c:when test="${itemsList.isGateSaleDisc==0}">
												<td align="left"><c:out value="NO" /></td>
												</c:when>
												<c:when test="${itemsList.isGateSaleDisc==1}">
												<td align="left"><c:out value="YES" /></td>
												</c:when>
												</c:choose>
												<c:choose>
												<c:when test="${itemsList.isAllowBday==0}">
												<td align="left"><c:out value="NO" /></td>
												</c:when>
												<c:when test="${itemsList.isAllowBday==1}">
												<td align="left"><c:out value="YES" /></td>
												</c:when>
												</c:choose>
												<td align="left"><a href="updateItemSup/${itemsList.id}"><span
														class="glyphicon glyphicon-edit"></span></a>
                                             </td>
											</tr>

										</c:forEach>

									</tbody>
								</table>
							</div>
						</div> --%>
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