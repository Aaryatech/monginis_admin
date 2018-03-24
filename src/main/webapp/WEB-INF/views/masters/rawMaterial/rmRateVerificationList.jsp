<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>




	<c:url var="getRmRateVerificationList" value="/getRmRateVerificationList" />
	<c:url var="getUomTax" value="/getUomTax" />


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
								<i class="fa fa-bars"></i>Raw material Rate Verification
							</h3>
							<div class="box-tool">
								<a href="">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							<!-- <div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> <a data-action="close" href="#"><i
									class="fa fa-times"></i></a>
							</div> -->
						</div>


						<div class="box-content">
							<form action="submitRmRateVerification" method="post"
								class="form-horizontal" id="validation-form"
								enctype="multipart/form-data" method="post">

								<div class="form-group">

									<label class="col-sm-3 col-lg-2 control-label">Select
										Supplier</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="supp_id" id="supp_id" class="form-control chosen">
											<option value="-1">Select Supplier</option>
											<option value="0">All</option>
											<c:forEach items="${supplierList}" var="supplierList"
												varStatus="count">
												<option value="${supplierList.suppId}"><c:out value="${supplierList.suppName}"/></option>
											</c:forEach>
										</select>
									</div>
									<label class="col-sm-3 col-lg-2 control-label">Select
										Row Material</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="rm_id" id="rm_id" class="form-control chosen">
											<option value="-1">Select Row Material</option>
											<option value="0">All</option>
											<c:forEach items="${RawmaterialList}" var="RawmaterialList"
												varStatus="count">
												<option value="${RawmaterialList.rmId}"><c:out value="${RawmaterialList.rmName}"/></option>
											</c:forEach>
										</select>
									</div>



								</div>
								 <div class="row">
									<div class="col-md-12" style="text-align: center">
										<input type="button" id="search" class="btn btn-info"
											value="Search" onclick="onSearch()" />



									</div>
								</div>
								<hr>
								
								<div class="box-content">
  
							<div class="clearfix"></div>
							
								<div id="table-scroll" class="table-scroll">
							 
									<div id="faux-table" class="faux-table" aria="hidden">
									<table id="table2" class="main-table">
											<thead>
												<tr class="bgpink">
												<th class="col-md-1">Sr No.</th>
														<th class="col-md-1">Supplier</th>
											<th class="col-md-2">Item</th>
											 <th class="col-md-1">Action</th>
												</tr>
												</thead>
												</table>
									
									</div>
									<div class="table-wrap">
									
										<table id="table1" class="table table-advance">
											<thead>
												<tr class="bgpink">
												<th class="col-md-1">Sr No.</th>
													<th class="col-md-1">Supplier</th>
											<th class="col-md-2">Item</th>
											<th class="col-md-1">Action</th>
										 
												</tr>
												</thead>
												<tbody>
											
											 
							</tbody>

						</table>
					</div>
				</div>
				
						</div>
								<br>
								

 
								<input type="hidden" name="rm_rate_ver_id" id="rm_rate_ver_id">

								 
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

	<script type="text/javascript">
 
function onSearch()
{	
	  
	var selectedRmId = $("#rm_id").val();
	var selectedSuppId = $("#supp_id").val();
 
   
    
	$.getJSON('${getRmRateVerificationList}',{
		
						rm_id : selectedRmId,
						supp_id : selectedSuppId,
					 
						ajax : 'true'

					},
					function(data) { 
						
						$('#table1 td').remove();
						 
						if (data == "") {
							 
							alert("No Record");
						}  
					  $.each(data,function(key, itemList) { 
										 
						  				 
										var tr = $('<tr></tr>');  
									  	tr.append($('<td></td>').html(key+1)); 
									  	tr.append($('<td></td>').html(itemList.suppName)); 
									  	tr.append($('<td></td>').html(itemList.rmName)); 
									  	tr.append($('<td></td>').html('  <a href="${pageContext.request.contextPath}/showRmRateVerificationDetailed/'+itemList.suppId+'/'+itemList.rmId+'" class="action_btn" ><abbr title="Details"><i class="fa fa-list"></i></abbr></a> '));
									  	
										$('#table1 tbody').append(tr);

										 

									})

					});
}

 
</script>
</body>
</html>