<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  
 <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
 <jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
 <style>
 .form-elegant .font-small {
    font-size: 0.8rem; }

.form-elegant .z-depth-1a {
    -webkit-box-shadow: 0 2px 5px 0 rgba(55, 161, 255, 0.26), 0 4px 12px 0 rgba(121, 155, 254, 0.25);
    box-shadow: 0 2px 5px 0 rgba(55, 161, 255, 0.26), 0 4px 12px 0 rgba(121, 155, 254, 0.25); }

.form-elegant .z-depth-1-half,
.form-elegant .btn:hover {
    -webkit-box-shadow: 0 5px 11px 0 rgba(85, 182, 255, 0.28), 0 4px 15px 0 rgba(36, 133, 255, 0.15);
    box-shadow: 0 5px 11px 0 rgba(85, 182, 255, 0.28), 0 4px 15px 0 rgba(36, 133, 255, 0.15); }

.form-elegant .modal-header {
    border-bottom: none; }

.modal-dialog .form-elegant .btn .fab {
    color: #2196f3!important; }

.form-elegant .modal-body, .form-elegant .modal-footer {
    font-weight: 400; }
    
  body {
  box-sizing: border-box;
  padding: 0 20px 20px;
  background: #fafafa;
  position: relative;
}

.mdl-data-table {
  margin-bottom: 12px;
}

.mdl-data-table td {
  padding-bottom: 12px;
  vertical-align: middle;
}

.mdl-data-table_full {
  max-width: 100%;
  width: 100%;
}

.mdl-data-table .controls {
  text-align: center;
}

.app-page {  
  margin: 0 auto;
}

  
 </style>
<body>


 
<c:url var="showDetailsForLayering" value="/showDetailsForLayering"/>


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
					<c:choose>
					<c:when test="${type==1}">
					<i class="fa fa-file-o"></i>Generate Mixing For Production
					</c:when>
						<c:when test="${type==2}">
					<i class="fa fa-file-o"></i>Generate Mixing For Cream Preparation
					</c:when>
						<c:when test="${type==3}">
					<i class="fa fa-file-o"></i>Generate Mixing For Layering
					</c:when>
					<c:otherwise>
						<i class="fa fa-file-o"></i>Production Headers
					</c:otherwise>
					</c:choose>
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
								<i class="fa fa-bars"></i> View Production
							</h3>
							<div class="box-tool">
								<a href=""></a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							<!-- <div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> <a data-action="close" href="#"><i
									class="fa fa-times"></i></a>
							</div> -->
						</div>


						<div class="box-content">
							<form action="${pageContext.request.contextPath}/generateMixingForProduction/${type}" class="form-horizontal"
								id="validation-form" method="get">

                            
								<input type="hidden" name="mode_add" id="mode_add"
									value="add_att">

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">From
										Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="from_date" autocomplete="off"
											size="16" type="text" name="from_date" value="${fromDate}"
											required  />
									</div>
									<!-- </div>


								<div class="form-group"> -->
									<label class="col-sm-3 col-lg-2 control-label">To Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="to_date" autocomplete="off" size="16"
											type="text"  name="to_date" required value="${toDate}"
											/>
									</div>

									<div
										class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
										<input type="submit" value="Submit" class="btn btn-primary">
									</div>

								</div>


								<div class="clearfix"></div>
<div id="table-scroll" class="table-scroll">
							 
									<div id="faux-table" class="faux-table" aria="hidden">
									<table id="table2" class="main-table">
											<thead>
												<tr class="bgpink">
										<th width="180" style="width: 90px">Prod ID</th>
												<th width="200" align="left">Production Date</th>
												<th width="358" align="left">Category</th>
												<th width="194" align="left">Status</th>
												<th width="102" align="left">IsPlanned</th>
												<th width="88" align="left">Action</th>
												</tr>
												</thead>
												</table>
									
									</div>
									<div class="table-wrap">
									
										<table id="table1" class="table table-advance">
											<thead>
												<tr class="bgpink">
												<th width="180" style="width: 90px">Prod ID</th>
												<th width="200" align="left">Production Date</th>
												<th width="358" align="left">Category</th>
												<th width="194" align="left">Status</th>
												<th width="102" align="left">IsPlanned</th>
												<th width="88" align="left">Action</th>
												</tr>
												</thead>
							<!-- 	<div class="table-responsive" style="border: 0">
									<table width="100%" class="table table-advance" id="table1">
										<thead>
											<tr>
												<th width="180" style="width: 90px">Prod ID</th>
												<th width="200" align="left">Production Date</th>
												<th width="358" align="left">Category</th>
												<th width="194" align="left">Status</th>
												<th width="102" align="left">IsPlanned</th>
												<th width="88" align="left">Action</th>
											</tr>
										</thead> -->
										<tbody>

											<c:forEach items="${planHeader}" var="planHeader">

												<tr>

													<td align="left"><c:out
															value="${planHeader.productionHeaderId}" /></td>
													<td align="left"><c:out
															value="${planHeader.productionDate}" /></td>
													<td align="left"><c:out value="${planHeader.catName}" /></td>

													<c:choose>
														<c:when test="${planHeader.productionStatus==1}">
															<td align="left"><c:out value="Planning"></c:out></td>

														</c:when>
														<c:when test="${planHeader.productionStatus==2}">
															<td align="left"><c:out value="Added From Order"></c:out></td>

														</c:when>
														<c:when test="${planHeader.productionStatus==3}">
															<td align="left"><c:out value="Production Started"></c:out></td>

														</c:when>
														<c:when test="${planHeader.productionStatus==4}">
															<td align="left"><c:out value="Production Completed"></c:out></td>

														</c:when>
														<c:when test="${planHeader.productionStatus==5}">
															<td align="left"><c:out value="Closed"></c:out></td>

														</c:when>
														<c:otherwise>
															<td align="left"><c:out value=""></c:out></td>

														</c:otherwise>

													</c:choose>


													<c:choose>
														<c:when test="${planHeader.productionStatus==1}">
															<td align="left"><c:out value="Yes"></c:out></td>

														</c:when>
														<c:otherwise>
															<td align="left"><c:out value="No"></c:out></td>

														</c:otherwise>

													</c:choose>


													<td align="left">
														<c:choose>
																<c:when test="${type==1}">
																	<a
																		href="${pageContext.request.contextPath}/addToMixing/${planHeader.productionHeaderId}"><span
																		class="glyphicon glyphicon-info-sign"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
																</c:when>
																<c:when test="${type==2}">
																	<div class="text-center">
  <a href="" onclick="showDetailsForLayering(${planHeader.productionHeaderId}/BMS)" class="btn btn-default btn-rounded" data-toggle="modal" data-target="#elegantModalForm">Action</a>
</div>
															    </c:when>
																<c:when test="${type==3}">
																	-
																</c:when>
																<c:otherwise>
																--
																</c:otherwise>
														</c:choose>


														</td>
												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>

							</div>
							</form>
						</div>
					</div>

				</div>

	</div>
	
			<!-- Modal -->
<div class="modal fade" id="elegantModalForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
  aria-hidden="true">
  <div class="modal-dialog" role="document" style="width:80%;height:50%">
    <!--Content-->
    <div class="modal-content form-elegant">
      <!--Header-->
      <div class="modal-header text-center">
        <h3 class="modal-title w-100 dark-grey-text font-weight-bold my-3" id="myModalLabel"><strong>Add Bom</strong></h3>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div> <div class="modal-body mx-6" style="margin: 20px;">
      <div class="table-responsive" style="border: 0">
									<table width="80%" class="table table-advance" id="modeltable">
										<thead>
											<tr>
											<th width="17" style="width: 18px"><input type="checkbox" /></th>
												<th width="17" style="width: 18px">Sr No</th>
												<th width="120" align="left">Product Name</th>
												<th width="100" align="left">Product Type</th>
												<th width="120" align="left">Qty</th>

												<th width="120" align="left">Edit Qty</th>

												<th width="100" align="left">UOM</th>
											</tr>
										</thead>
										<tbody>
											
										</tbody>
									</table>
									
								</div>
						</div>			
      <!--Body-->
      <div class="modal-body mx-4" >
        <!--Body-->
       

        <div class="text-center mb-1">
          <button type="button" class="btn btn-primary">Add Bom</button>
        </div>
    

       
      </div>
      <!--Footer-->
    
    </div>
    <!--/.Content-->
  </div>
</div>
<!-- Modal -->


	<!-- END Main Content -->
	<footer>
	<p>2019 © MONGINIS.</p>
	</footer>

	<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
		class="fa fa-chevron-up"></i></a>
	</div>
	<!-- END Content -->
	</div>
	<!-- END Container -->
    <script type="text/javascript">
    function showDetailsForLayering(prodHeaderId,dept)
    {
    	$.getJSON('${showDetailsForLayering}', {
    		
    		prodHeaderId:prodHeaderId,
    		toDept:JSON.stringify(dept),
    		ajax : 'true',
    	},  function(data) { 
    		
    		
    	});
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