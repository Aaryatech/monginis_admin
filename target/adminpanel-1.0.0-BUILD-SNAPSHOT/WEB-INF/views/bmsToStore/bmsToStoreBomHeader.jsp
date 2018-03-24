<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
 <jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
<body>


	<c:url var="getBomListBmsToStoreWithDate" value="/getBomListBmsToStoreWithDate"></c:url>
	 
	 


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
						<i class="fa fa-file-o"></i> Search Bill Of Material Department Wise
					</h1>
					
				</div>
				
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					<div class="box" id="todayslist">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i> Search Bill Of Material Department Wise
							</h3>
							<div class="box-tool">
								<input type="button" class="btn btn-primary" value="Datewise record" onclick="showdatewisetable()"> <a data-action="collapse" href="#"><i
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
										
										<th>Department Name</th>
										<th>Request Date</th>
										
										<th>Status</th>
										<th>Action</th>
										
									</tr>
								</thead>
								
								<tbody>
									<c:forEach items="${getbomList}" var="getbomList"
													varStatus="count">
													
													
													<c:choose>
													<c:when test="${getbomList.status==0}">
													<c:set var = "status" value='Pending'/>
													</c:when>
													
													<c:when test="${getbomList.status==1}">
													  <c:set var = "status" value="Approved"/>
													
													</c:when>
													
													<c:when test="${getbomList.status==2}">
													  <c:set var = "status" value="Rejected"/>
													
													</c:when>
												</c:choose>

													<tr>
														<td><c:out value="${count.index+1}" /></td>

														
																
																<td align="left"><c:out
																value="${getbomList.fromDeptName}" /></td>
																
													  <td align="left"><fmt:formatDate pattern = "dd-MM-yyyy" value="${getbomList.reqDate}" />   </td>
													
													<%-- <td align="left"><c:out
																 />  </td> --%>
															
															
																
																
																<td align="left"><c:out	
																value="${status}" />
																</td>
																
																
						<td><a href="${pageContext.request.contextPath}/viewDetailBOMBmsToStoreRequest?reqId=${getbomList.reqId}&settingvalue=${settingvalue}" class="action_btn" >
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
								<input type="button" class="btn btn-primary" value="Pending List" onclick="showdatewisetable()"> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						<div class=" box-content">
						<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">From Date:</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="from_date" size="16"
											 type="text" name="from_date" required />
										<input class="form-control " id="settingvalue" size="16"
											 type="hidden" name="settingvalue" value="${settingvalue}" readonly/>
											 <input class="form-control " id="deptId" size="16"
											 type="hidden" name="deptId" value="${deptId}" readonly/>
									
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
								
								<div align="center" class="form-group">
									<div class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
										
				
										<input class="btn btn-primary" value="View All" id="searchmixall"
											onclick="searchbomall()">

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
										
										<th>Department Name</th>
										<th>Request Date</th>
										
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
	
		function searchbomall() {
			var from_date = $("#from_date").val();
			var to_date = $("#to_date").val();
			var deptId = $("#deptId").val();
			var settingvalue = $("#settingvalue").val();
			
			
			$('#loader').show();

			$
					.getJSON(
							'${getBomListBmsToStoreWithDate}',

							{
								 
								from_date : from_date,
								to_date : to_date,
								deptId : deptId,
								settingvalue : settingvalue,
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
												var stats;
												var bgcolor;
												//alert("all request");
												
											if(itemList.status==0)
												{
												stats="Pending";
												var tr = $('<tr></tr>');
											  	tr.append($('<td></td>').html(key+1));

											  	tr.append($('<td></td>').html(itemList.fromDeptName));
											  	

											  	tr.append($('<td></td>').html(itemList.reqDate));
											  
											  	tr.append($('<td></td>').html(stats));
											  	tr.append($('<td ></td>').html("<a href='${pageContext.request.contextPath}/viewDetailBOMBmsToStoreRequest?reqId="+itemList.reqId+"&settingvalue="+settingvalue+"' class='action_btn'> <abbr title='detailed'> <i class='fa fa-list' ></i></abbr> "));
												
												$('#table_grid tbody').append(tr);
												
												}
											else if(itemList.status==1)
												{
												stats="Approved";
												var tr = $('<tr></tr>');
											  	tr.append($('<td style="color:blue"></td>').html(key+1));

											  	tr.append($('<td style="color:blue"></td>').html(itemList.fromDeptName));
											  	

											  	tr.append($('<td style="color:blue"></td>').html(itemList.reqDate));
											  
											  	tr.append($('<td style="color:blue"></td>').html(stats));
											  	tr.append($('<td ></td>').html("<a href='${pageContext.request.contextPath}/viewDetailBOMBmsToStoreRequest?reqId="+itemList.reqId+"&settingvalue="+settingvalue+"' class='action_btn'> <abbr title='detailed'> <i class='fa fa-list' ></i></abbr> "));
												
												$('#table_grid tbody').append(tr);
												}
											else if(itemList.status==2)
												{
												stats="Rejected";
												var tr = $('<tr></tr>');
											  	tr.append($('<td style="color:red"></td>').html(key+1));

											  	tr.append($('<td style="color:red"></td>').html(itemList.fromDeptName));
											  	

											  	tr.append($('<td style="color:red"></td>').html(itemList.reqDate));
											  
											  	tr.append($('<td style="color:red"></td>').html(stats));
											  	tr.append($('<td></td>').html("<a href='${pageContext.request.contextPath}/viewDetailBOMBmsToStoreRequest?reqId="+itemList.reqId+"&settingvalue="+settingvalue+"' class='action_btn'> <abbr title='detailed'> <i class='fa fa-list' ></i></abbr> "));
												
												$('#table_grid tbody').append(tr);
												}
											else if(itemList.status==3)
											{
											stats="Approved-Rejected";
											var tr = $('<tr></tr>');
										  	tr.append($('<td style="color:green"></td>').html(key+1));

										  	tr.append($('<td style="color:green"></td>').html(itemList.fromDeptName));
										  	

										  	tr.append($('<td style="color:green"></td>').html(itemList.reqDate));
										  
										  	tr.append($('<td style="color:green"></td>').html(stats));
										  	tr.append($('<td></td>').html("<a href='${pageContext.request.contextPath}/viewDetailBOMBmsToStoreRequest?reqId="+itemList.reqId+"&settingvalue="+settingvalue+"' class='action_btn'> <abbr title='detailed'> <i class='fa fa-list' ></i></abbr> "));
											
											$('#table_grid tbody').append(tr);
											}
										
											
												
												

												 

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
		
	</script>
</body>
</html>