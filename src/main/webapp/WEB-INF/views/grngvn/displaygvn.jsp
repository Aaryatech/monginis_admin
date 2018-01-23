<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
	<c:url var="getGvnList" value="/getGvnList" />

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
						<i class="fa fa-file-o"></i>GVN Details
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
								<i class="fa fa-bars"></i> Search GVN List
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
							<!-- <form class="form-horizontal" method="get" id="validation-form"> -->
							<div class="form-group">

									<label for="textfield2" class="col-xs-3 col-lg-2 control-label">Select
										Franchisee </label>
									<div class="col-sm-9 col-lg-10 controls">

										<select class="form-control chosen" multiple="multiple"
											tabindex="6" name="fr_id" id="fr_id">

											<c:forEach items="${allFrIdNameList}" var="allFrIdNameList"
												varStatus="count">
												<option value="${allFrIdNameList.frId}">${allFrIdNameList.frName}</option>

											</c:forEach>

										</select>
									</div>


								</div>

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">From
										Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="datepicker" size="16"
											 type="text" name="from_Date" required />
									</div>




									<label class="col-sm-3 col-lg-2 control-label">To Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="datepicker2" size="16"
											 type="text" name="to_Date" required />

									</div>

								</div>
	
								<div align="center" class="form-group">
									<div
										class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
										<input class="btn btn-primary" value="Search..." id="callSubmit"
											onclick="searchGVN()">


									</div>
								</div>


								<div align="center" id="loader" style="display: none">

									<span>
										<h4>
											<font color="#343690">Loading</font>
										</h4>
									</span> <span class="l-1"></span> <span class="l-2"></span> <span
										class="l-3"></span> <span class="l-4"></span> <span
										class="l-5"></span> <span class="l-6"></span>
								</div>

								<!-- </form>
 -->
								<!-- <tion="getBillListProcess" class="form-horizontal"
								method="post" id="validation-form"> -->
								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i> GVN List
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
										</div>
									</div>

									<div class="box-content">

										<div class="clearfix"></div>
										<div class="table-responsive" style="border: 0">
											<table width="100%" class="table table-advance" id="table_grid" name="table_grid">
												<thead>
													<tr>
														<!-- <th width="138" style="width: 18px" align="left">Sr
															No</th> -->
														<th width="138" align="left">Bill No</th>
														<th width="159" align="left">GVN Date</th>
														<th width="159" align="left">Franchisee Name</th>
														<th width="159" align="left">Item Name</th>
														<th width="159" align="left">RATE</th>
														<th width="91" align="left">Quantity</th>
														<th width="105" align="left">Amount</th>
														<th width="105" align="left">Status</th>
														<th width="105" align="left">Photo 1</th>
														<th width="105" align="left">Photo 2</th>
														<th width="105" align="left">Remark</th>
														

													</tr>
												</thead>
												<tbody>
												
												</tbody>
												
											</table>
										</div>
									</div>


								</div>
							<!-- </form> -->
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
	
		
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/assets/bootstrap/js/lightbox.js"></script>
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
	
		function searchGVN() {
			
			$('#table_grid td').remove();
		
			
			var isValid = validate();
			
			if (isValid) {
				
				var fromDate = document.getElementById("datepicker").value;
				var toDate = document.getElementById("datepicker2").value;
				var frIds=$("#fr_id").val();
				$('#loader').show();
				
				$.getJSON('${getGvnList}',{
					
									fr_id_list : JSON.stringify(frIds),
									fromDate : fromDate,
									toDate : toDate,
									ajax : 'true'

								},
								function(data) {
									alert(data);
									//$('#table_grid td').remove();
									
									$('#loader').hide();

									if (data == "") {
										alert("No records found !!");

									}

									
									$.each(data,function(key, gvndata) {

										var index = key + 1;

										var tr = "<tr>";

										

										var billNo = "<td>&nbsp;&nbsp;&nbsp;"
												+ gvndata.billNo
												+ "</td>";
												var grnGvnDate = "<td>&nbsp;&nbsp;&nbsp;"
													+ gvndata.grnGvnDate
													+ "</td>";
													var frName = "<td>&nbsp;&nbsp;&nbsp;"
														+ gvndata.frName
														+ "</td>";
													var itemName = "<td>&nbsp;&nbsp;&nbsp;"
														+ gvndata.itemName
														+ "</td>";

														var itemRate = "<td>&nbsp;&nbsp;&nbsp;"
															+ gvndata.itemRate
															+ "</td>";

															var grnGvnQty = "<td>&nbsp;&nbsp;&nbsp;"
																+ gvndata.grnGvnQty
																+ "</td>";

																var grnGvnAmt = "<td>&nbsp;&nbsp;&nbsp;"
																	+ gvndata.grnGvnAmt
																	+ "</td>";

																	var grnGvnStatus = "<td>&nbsp;&nbsp;&nbsp;"
																		+ gvndata.grnGvnStatus
																		+ "</td>";

																		var gvnPhotoUpload1 =  '<td> &nbsp;&nbsp;<a href="'
																			+gvndata.gvnPhotoUpload1+'" data-lightbox="image-1" >Image 1</a>'
																			+ '</td>';

																			var gvnPhotoUpload2 =  '<td> &nbsp;&nbsp;<a href="'
																			+gvndata.gvnPhotoUpload2+'" data-lightbox="image-1" >Image 2</a>'
																				+ '</td>';

																				var frGrnGvnRemark = "<td>&nbsp;&nbsp;&nbsp;"
																					+ gvndata.frGrnGvnRemark
																					+ "</td>";



										

										var trclosed = "</tr>";

										$('#table_grid tbody')
												.append(tr);
										$('#table_grid tbody')
												.append(billNo);
										$('#table_grid tbody')
										.append(grnGvnDate);
										$('#table_grid tbody')
										.append(frName);
										$('#table_grid tbody')
										.append(itemName);
										$('#table_grid tbody')
										.append(itemRate);
										$('#table_grid tbody')
										.append(grnGvnQty);
										
										$('#table_grid tbody')
										.append(grnGvnAmt);
										
										$('#table_grid tbody')
										.append(grnGvnStatus);
										$('#table_grid tbody')
										.append(gvnPhotoUpload1);
										$('#table_grid tbody')
										.append(gvnPhotoUpload2);
										$('#table_grid tbody')
										.append(frGrnGvnRemark);
										
										$('#table_grid tbody')
										.append(trclosed);
										

									})
										

				});

}
}

	</script>
	
	
	
		<script type="text/javascript">
		function validate() {
		
		
			var fromDate =$("#datepicker").val();
			var toDate =$("#datepicker2").val();
			

			var isValid = true;

		 if (fromDate == "" || fromDate == null) {

				isValid = false;
				alert("Please select From Date");
			}
		 else if (toDate == "" || toDate == null) {

				isValid = false;
				alert("Please select To Date");
			}
			return isValid;

		}
	</script>




</body>
</html>