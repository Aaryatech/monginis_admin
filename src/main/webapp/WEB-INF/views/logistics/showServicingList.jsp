<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<c:url var="getServicingWithDate" value="/getServicingWithDate"></c:url>
	<c:url var="getAllTypeList" value="/getAllTypeList"></c:url>
	<c:url var="machineListOrVehicleList" value="/machineListOrVehicleList"></c:url>
	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>


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
						<i class="fa fa-file-o"></i> Servicing List
					</h1>
					
				</div>
				
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					<div class="box" id="todayslist">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i> Servicing List
							</h3>
							<div class="box-tool">
								<a  onclick="showdatewisetable()">Show Datewise Record</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
						</div>
						
						<div class=" box-content">
					<div class="row">
					
					
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid1">
								<thead>
									<tr>
										<th>Sr.No.</th>
										
										<th>Bill No</th>
										<th>Bill Date</th>
										<th>Type</th>
										<th>Vehicle No. Or Machine Name</th>
										<th>Type</th>
										<th>Taxable Amt</th>
										<th>Tax Amt</th>
										<th>Total</th>
										<th>Status</th>
										<th>Action</th>
										
									</tr>
								</thead>
								
								<tbody>
									<c:forEach items="${servHeaderList}" var="servHeaderList"
													varStatus="count">
													
													
													<c:choose>
													<c:when test="${servHeaderList.isApproved==0}">
													<c:set var = "status" value='Pending'/>
													</c:when>
													
													<c:when test="${servHeaderList.isApproved==1}">
													  <c:set var = "status" value="Approved"/>
													
													</c:when> 
												</c:choose>
												
												<c:choose>
													<c:when test="${servHeaderList.servType2==1}">
													<c:set var = "type" value='Vehicle'/>
													</c:when>
													
													<c:when test="${servHeaderList.servType2==2}">
													  <c:set var = "type" value="Machine"/>
													
													</c:when> 
												</c:choose>
												
												 

													<tr>
														<td><c:out value="${count.index+1}" /></td>
 
														 
																<td align="left"><c:out value="${servHeaderList.billNo}" /></td>
																<td align="left"><c:out value="${servHeaderList.billDate}" /></td>
																<td align="left"><c:out value="${type}" /></td>
																<td align="left"><c:out value="${servHeaderList.vehNo}" /></td>
																<c:forEach items="${mechTypeList}" var="mechTypeList">
																 
																	<c:choose>
																		<c:when test="${mechTypeList.typeId==servHeaderList.typeId}">
																		<td align="left" ><c:out value="${mechTypeList.typeName}" /></td>
																		</c:when>
																		 
																	</c:choose>
																</c:forEach>
																<td align="left"><c:out value="${servHeaderList.taxableAmt}" /></td>
																<td align="left"><c:out value="${servHeaderList.taxAmt}" /></td>
																<td align="left"><c:out value="${servHeaderList.total}" /></td>
																<td align="left"><c:out value="${status}" /></td>
																
													   
													 	 
																
						<td><a href="${pageContext.request.contextPath}/viewServicingDetail/${servHeaderList.servId}/${flag}" class="action_btn" >
						<abbr title="detailed"><i class="fa fa-list"></i></abbr></a></td>
						
																</tr>
												</c:forEach>
										
									
								</tbody>
							</table>
						</div>
					</div>

		</div>
					</div>
						
						
						
					<div class="box" id="datewise_table" style="display: none">
					
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i> Search Bill of Material List Date Wise
							</h3>
							<div class="box-tool">
								<a  onclick="showdatewisetable()">Pending List</a>  <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						<div class=" box-content">
						<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">From Date:</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="from_date" size="16"
											 type="text" name="from_date" required />
									
										</div>
										<div class="col-md-1"></div> 
										<label class="col-sm-3 col-lg-2 control-label">To Date:</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="to_date" size="16"
											 type="text" name="to_date" required />
									<input class="form-control" id="flag" value="${flag}" size="16"
											 type="hidden" name="flag"  />
										</div>
										
										
										</div><br>
										
										<div class="box-content">
							
								<div class="col-md-2">Select Vehicle Or Machine*</div>
									<div class="col-md-3">
									
									<select name="type" id="type" class="form-control chosen" tabindex="6" required>
									 	<option value="">Select Vehicle Or Machine</option> 
											  <option value="1">Vehicle</option>
											   <option value="2">Machine</option>
										</select>
									
									</div> 
									
									<div class="col-md-1">Or</div> 
									<div class="col-md-2">Select Type*</div>
									<div class="col-md-3">
									
									<select name="typeId" id="typeId" class="form-control chosen" tabindex="6" required>
											 <option value="" >Select Type</option>
											<c:forEach items="${mechTypeList}" var="mechTypeList"> 
													<option value="${mechTypeList.typeId}">${mechTypeList.typeName}</option>				 
											</c:forEach>
										</select>
									
									</div> 
									 
									
							</div><br>
							
							<div class="box-content">
							
								 <div class="col-md-2">Select Name or No*</div>
									<div class="col-md-3">
									
									<select name="vehId" id="vehId" class="form-control chosen" tabindex="6" required>
									 	 
										</select>
									
									</div> 
								
									
							</div><br>
						
								</div>
								
								
								
								
								
								<div class=" box-content">
								<div class="form-group">
								
								<div align="center" class="form-group">
									<div class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
										
				
										<input type="button" class="btn btn-primary" value="View All" id="searchmixall"
											onclick="searchbomall()">
								<input type="button" class="btn btn-primary" value="Pdf" onclick="getPdf()" >
									</div><br>
									
									<div align="center" id="loader" style="display: none">

									<span>
										<h4>
											<font color="#343690">Loading</font>
										</h4>
									</span> <span class="l-1"></span> <span class="l-2"></span> <span
										class="l-3"></span> <span class="l-4"></span> <span
										class="l-5"></span> <span class="l-6"></span>
								</div>	
									
									
								</div>
								</div>
								</div>
								
							

						<div class=" box-content">
					<div class="row">
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th>Sr.No.</th>
										
										<th>Bill No</th>
										<th>Bill Date</th>
										<th>Type</th>
										<th>Vehicle/Machine</th>
										<th>Type</th>
										<th>Taxable Amt</th>
										<th>Tax Amt</th>
										<th>Total</th>
										<th>Status</th>
										<th>Action</th>
										
									</tr>
								</thead>
								
								<tbody>
									
										
									
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
		
		<script type="text/javascript">
		
		$(document).ready(function() { 
			$('#type').change(
					function() {
						//alert("typeId"+$(this).val());
						var typeId=$(this).val();
					    
						$.getJSON('${machineListOrVehicleList}', {
							
							typeId : $(this).val(),
							ajax : 'true'
						},
								function(data) {
							 
							var html = '<option value="0">All</option>';
							
							var len = data.length;
							for ( var i = 0; i < len; i++) {
								html += '<option value="' + data[i].mechId + '">'
										+ data[i].mechName + '</option>';
							}
							html += '</option>';
							$('#vehId').html(html);
							$("#vehId").trigger("chosen:updated"); 
							
							 
							
								});
					 
						 
				})
				 		 
		});
	
		function searchbomall() {
			var from_date = $("#from_date").val();
			var to_date = $("#to_date").val();
			var type = $("#type").val();
			var vehId = $("#vehId").val();
			var typeId = $("#typeId").val();
			var abc = $("#flag").val();
			 valid=0;
			if(from_date=="")
				{
				alert("Enter From Date");
				valid=1;
				} 
			else if(to_date=="")
				{
				 alert("Enter To Date");
				 valid=1;
				}
			else if(type=="" && typeId=="")
				{
					alert("Select Machine/Vehicle Or Type");
					valid=1;
				}
			else if(type!=="" && vehId=="")
			{
				alert("Select Name or No");
				valid=1;
			}
				
			if(valid==0)
				{ 
			$('#loader').show();

			$
					.getJSON(
							'${getServicingWithDate}',

							{
								 
								from_date : from_date,
								to_date : to_date,
								type : type,
								vehId : vehId,
								typeId : typeId,
								ajax : 'true'

							},
							function(data) {

								$('#table_grid td').remove();
								$('#loader').hide();

								if (data == "") {
									alert("No records found !!");

								}
								
								 $.getJSON('${getAllTypeList}', {
										 
										ajax : 'true'
									},
											function(data1) {
										 
										var len = data1.length;
											
							 
 
							  $.each( data,
											function(key, itemList) {
												 
												 var stats;
												 var type;
												 var type2;
												 
												 if(itemList.isApproved==0)
													 {
													 stats="Pending";
													 }
												 else
													 {
													 stats="Approved";
													 }
												 
												 if(itemList.servType2==1)
												 {
													 type2="Vehicle";
												 }
											 	else
												 {
											 		type2="Machine";
												 }
												 
												
												 
												var tr = $('<tr></tr>');
											  	tr.append($('<td></td>').html(key+1)); 
											  	tr.append($('<td></td>').html(itemList.billNo));
											  	tr.append($('<td></td>').html(itemList.billDate)); 
											  	tr.append($('<td></td>').html(type2)); 
											  	tr.append($('<td></td>').html(itemList.vehNo));
											  	for ( var i = 0; i < len; i++) {
													if(data1[i].typeId==itemList.typeId)
														{
														tr.append($('<td></td>').html(data1[i].typeName));
														break;
														}
														
												}
											  	
											  	tr.append($('<td style="text-align:right"></td>').html((itemList.taxableAmt).toFixed(2)));
											  	tr.append($('<td style="text-align:right"></td>').html((itemList.taxAmt).toFixed(2)));
											  	tr.append($('<td style="text-align:right"></td>').html((itemList.total).toFixed(2)));
											  	tr.append($('<td></td>').html(stats));
											  	tr.append($('<td ></td>').html("<a href='${pageContext.request.contextPath}/viewServicingDetail/"+itemList.servId+"/"+abc+"' class='action_btn'> <abbr title='detailed'> <i class='fa fa-list' ></i></abbr> "));
												
												$('#table_grid tbody').append(tr); 
											 
											}) 
									});
							});
				 } 
		 
	}
	</script>
	
	<script type="text/javascript">
	var flag=0
	function showdatewisetable() {
		
		if(flag==0)
		{
			$('#todayslist').hide();
			$("#datewise_table").show();
			flag=1;
		}
		else if(flag==1)
		{
			$('#todayslist').show();
			$("#datewise_table").hide();
			flag=0;
		}
	 
}
		
	function getPdf()
	{
	    var from_date = $("#from_date").val();
	    
	    var to_date = $("#to_date").val();
	    var type=$("#type").val();
	    var typeId=$("#typeId").val();
	    var vehId = $("#vehId").val();
		var valid=0;
		
			if(from_date=="")
				{
				alert("Enter Valid From Date");
				valid=1;
				
				}
			else if(to_date=="")
				{
				alert("Enter Valid To Date");
				valid=1;
				}
			else if(type=="" && typeId=="")
			{
				alert("Select Machine/Vehicle Or Type");
				valid=1;
			}
			else if(type=="" && typeId!="")
			{
				type=-1;
				vehId=0;
				valid=0;
			}
			else if(type!="" && typeId=="")
			{
				typeId=-1;
				valid=0;
			}
		
			if(valid==0)
			{
		    	window.open('${pageContext.request.contextPath}/Logistics?url=pdf/vehOrMachInvoiceBill/'+from_date+'/'+to_date+'/'+type+'/'+typeId+'/'+vehId+'/');
			}
	    }
	</script>
</body>
</html>