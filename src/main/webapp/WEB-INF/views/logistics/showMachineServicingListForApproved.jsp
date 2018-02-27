<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<c:url var="getMachineServicingWithDate" value="/getMachineServicingWithDate"></c:url>
	<c:url var="getBomAllListWithDate" value="/getBomAllListWithDate"></c:url>
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
						<i class="fa fa-file-o"></i> Machine Servicing List
					</h1>
					
				</div>
				
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					<div class="box" id="todayslist">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i> Machine Servicing List
							</h3>
							<div class="box-tool">
								<a  onclick="showdatewisetable()">Show Datewise Record</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
						</div>
						
						<div class=" box-content">
						<form id="submitMaterialStore" action="${pageContext.request.contextPath}/approvedMachineServicing" method="post"
							enctype="multipart/form-data">
					<div class="row">
					
					
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid1">
								<thead>
									<tr>
									
									<th align="left"><input type="checkbox"
													onClick="selectAll(this)"
													  /> Select All</th>
										<th>Sr.No.</th> 
										<th>Machine Name</th>
										<th>Dealer Name</th> 
										<th>Person Name</th>
										<th>Servicing Date</th>
										<th>Next Date Date</th> 
										<th>Alert Date Date</th>
										
									</tr>
								</thead>
								
								<tbody>
									<c:forEach items="${getMachineServicingList}" var="getMachineServicingList"
													varStatus="count">
													
													
													 

													<tr>
													 <td><input type="checkbox" name="select_to_approve"
																id="select_to_approve" 
																value="${getMachineServicingList.mechServId}" ></td>
																 
														<td><c:out value="${count.index+1}" /></td>
 
																<td align="left"><c:out value="${getMachineServicingList.machineName}" /></td>
																<td align="left"><c:out value="${getMachineServicingList.dealerName}" /></td>
																<td align="left"><c:out value="${getMachineServicingList.prsnName}" /></td>
																<td align="left"><c:out value="${getMachineServicingList.date}" /></td>
																<td align="left"><c:out value="${getMachineServicingList.nextServDate}" /></td>
													  <td align="left"><c:out value="${getMachineServicingList.alertServDate}" /></td> 
																</tr>
												</c:forEach>
										
									
								</tbody>
							</table>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12" style="text-align: center">
							<input type="submit" class="btn btn-info" value="Submit" name="Submit" id="Submit">

						</div>
					</div>
				</form>
		</div>
		
					</div>
						
						
						
					<div class="box" id="datewise_table" style="display: none">
					
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i> Search Machine Servicing Date Wise
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
										
										<label class="col-sm-3 col-lg-2 control-label">To Date:</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="to_date" size="16"
											 type="text" name="to_date" required />
									
										</div>
										
										
										</div><br>
						
								</div>
								
								<div class=" box-content">
						<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Select Machine:</label>
									<div class="col-sm-5 col-lg-3 controls">
										 <select name="machineId" id="machineId" class="form-control chosen" tabindex="6" required>
                                    <option value="0">All</option>
                                    <c:forEach items="${machineMasterList}" var="machineMasterList">
                                    	<option value="${machineMasterList.machineId}">${machineMasterList.machineName}</option> 
                                    </c:forEach>
											 
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
										<input type="button" class="btn btn-primary" value="Veiw Pdf" id="searchmixall"
											onclick="getPdf();">

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
										<th>Machine Name</th>
										<th>Dealer Name</th> 
										<th>Person Name</th>
										<th>Servicing Date</th>
										<th>Next Date Date</th> 
										<th>Alert Date Date</th>
										<th>Status</th>
										
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
	
		function searchbomall() {
			var from_date = $("#from_date").val();
			var to_date = $("#to_date").val();
			var machineId = $("#machineId").val();
			
			$('#loader').show();

			$
					.getJSON(
							'${getMachineServicingWithDate}',

							{
								 
								from_date : from_date,
								to_date : to_date,
								machineId : machineId,
								ajax : 'true'

							},
							function(data) {

								$('#table_grid td').remove();
								$('#loader').hide();

								if (data == "") {
									alert("No records found !!");

								}
							 

							  $.each(
											data,
											function(key, itemList) {
												var approve;
												 if(itemList.isApproved==0) 
													 approve="Pending";
												 else
													 approve="Approved";
											var tr = $('<tr></tr>');
										  	tr.append($('<td></td>').html(key+1)); 
										  	tr.append($('<td></td>').html(itemList.machineName)); 
										  	tr.append($('<td></td>').html(itemList.dealerName)); 
										  	tr.append($('<td></td>').html(itemList.prsnName));
										  	tr.append($('<td></td>').html(itemList.date));
										  	tr.append($('<td></td>').html(itemList.nextServDate));
										  	tr.append($('<td></td>').html(itemList.alertServDate));
										  	tr.append($('<td></td>').html(approve));
										  	 $('#table_grid tbody').append(tr);
											 
											

											})  
							});

		 
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
	    var machineId=$("#machineId").val();
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
			else if(machineId==null)
			{
			alert("Select Machine");
			valid=1;
			}
		
			if(valid==0)
			{
		    	window.open('${pageContext.request.contextPath}/Logistics?url=pdf/machineServicingPdf/'+from_date+'/'+to_date+'/'+machineId+'/');
			}
	    }
		
	</script>
</body>
</html>