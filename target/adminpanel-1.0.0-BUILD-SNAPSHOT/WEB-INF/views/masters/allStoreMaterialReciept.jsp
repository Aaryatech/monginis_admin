<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
 
<c:url var="allRecordwithDate" value="/allRecordwithDate"></c:url>

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
					<i class="fa fa-file-o"></i>show All Store Material Reciept
				</h1>
				
				<!-- <h4>Bill for franchises</h4> -->
			</div>
		</div>
		<!-- END Page Title -->

		
		<!-- BEGIN Main Content -->
		<div class="box" id="pending">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>show All Store Material Reciept
					
				</h3>
				<div class="box-tool">
				<a  onclick="showdatewisetable()">Show Datewise All Record</a> 
				</div>

			</div> 
				<div class=" box-content">
					<div class="row">
					<div class="clearfix"></div>
						<div class=" table-responsive">
							<table class="table table-advance "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th>Sr.No.</th>
										
										<th>MRN No.</th>
										<th>Supplier Name</th>
										<th>lrNo</th>
										<th>Vehical</th>
										<th>Transport</th>
										<th>Status</th>
										<th>Action</th>

									</tr>
								</thead>
								
								<tbody>
								<c:forEach items="${materialRecNoteList}" var="materialRecNoteList"
													varStatus="count">
											<c:choose>
													<c:when test="${materialRecNoteList.status==2}">
													<c:set var="status" value="Rejected By Director"></c:set>
													<c:set var = "color" value="red"/>
													</c:when>
													
													
													<c:otherwise>
													<c:set var="status" value="Pending"></c:set>
													  <c:set var = "color" value="black"/>
													</c:otherwise>
													</c:choose>

									

													<tr>
														<td style="color: <c:out value = "${color}"/>"><c:out value="${count.index+1}" /></td>

														
																
																<td align="left" style="color: <c:out value = "${color}"/>"><c:out
																value="${materialRecNoteList.mrnNo}" /></td>
																
																<c:forEach items="${supplierDetailsList}" var="supplierDetailsList"
													varStatus="count">
																<c:choose>
													<c:when test="${materialRecNoteList.supplierId==supplierDetailsList.suppId}">
													<td align="left" style="color: <c:out value = "${color}"/>"><c:out
																value="${supplierDetailsList.suppName}" /></td>
													</c:when>
													 </c:choose>
													 </c:forEach>
													  
													<td align="left" style="color: <c:out value = "${color}"/>"><c:out
																value="${materialRecNoteList.lrNo}" />  </td>
															
															<td align="left" style="color: <c:out value = "${color}"/>"><c:out	
																value="${materialRecNoteList.vehicleNo}" />
																</td>
													 
																<c:forEach items="${transportlist}" var="transportlist"
													varStatus="count">
																<c:choose>
													<c:when test="${materialRecNoteList.transportId==transportlist.tranId}">
													<td align="left" style="color: <c:out value = "${color}"/>"><c:out
																value="${transportlist.tranName}" /></td>
													</c:when>
													 </c:choose>
													 </c:forEach>
																<td align="left" style="color: <c:out value = "${color}"/>"><c:out	
																value="${status}" />
																</td>
																
						<td><a href="${pageContext.request.contextPath}/showStoreMaterialReciept?mrnId=${materialRecNoteList.mrnId}" class="action_btn" >
						<abbr title="Details"><i class="fa fa-list"></i></abbr></a>
						<a href="${pageContext.request.contextPath}/editGateEntry?mrnId=${materialRecNoteList.mrnId}&flag=1"
									><span class="glyphicon glyphicon-edit"><abbr title='Edit'></abbr></span> </a>
						</td>
						
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
					<i class="fa fa-bars"></i>Date Wise
				</h3>
				<div class="box-tool">
				<a  onclick="showdatewisetable()">Pending List</a> 
				 
								
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
								
								<div align="center" class="form-group">
									<!-- <div class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0"> -->
										<input type="button" class="btn btn-primary" value="All Record" id="searchmixall"
											onclick="pending()"> 

									<!-- </div><br> -->
									
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
						<div class="table-responsive">
						 <input type="hidden" name="flag" id="flag" value="${flag}">
							<table class="table table-advance "
								style="width: 100%" id="table_grid1">
								<thead>
								
									<tr>
										<th>Sr.No.</th>
										
										<th>MRN No.</th>
										<th>Gate Entry Date</th>
										<th>Store Approved Date</th>
										<th>Supplier Name</th>
										<th>lrNo</th>
										<th>Vehical No.</th> 
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
	<!-- END Main Content -->

	<footer>
	<p>2017 © Monginis.</p>
	</footer>

	<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
		class="fa fa-chevron-up"></i></a>

 

	

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
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/chosen-bootstrap/chosen.jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-inputmask/bootstrap-inputmask.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-tags-input/jquery.tagsinput.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-pwstrength/jquery.pwstrength.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-fileupload/bootstrap-fileupload.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-duallistbox/duallistbox/bootstrap-duallistbox.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/dropzone/downloads/dropzone.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/clockface/js/clockface.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/date.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-switch/static/js/bootstrap-switch.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/ckeditor/ckeditor.js"></script>

	<!--flaty scripts-->
	<script src="${pageContext.request.contextPath}/resources/js/flaty.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/flaty-demo-codes.js"></script>
	<script type="text/javascript">
	var div=0
	function showdatewisetable() {
		
		if(div==0)
		{
			$('#pending').hide();
			$("#datewise_table").show();
			div=1;
		}
		else if(div==1)
		{
			$('#pending').show();
			$("#datewise_table").hide();
			div=0;
		}
	 
}
	function pending()
	{
		var from_date=document.getElementById("from_date").value; 
		var to_date=document.getElementById("to_date").value; 
		var flag = 1;
		//alert("from_date"+from_date);
		//alert("to_date"+to_date);
		//alert("flag"+flag);
		 
		$('#loader').show();
		$
		.getJSON(
				'${allRecordwithDate}',

				{
					 
					from_date : from_date,
					to_date : to_date,
					flag : flag,
					ajax : 'true'

				},
				function(data) {
					
					//alert("data"+data);
					$('#table_grid1 td').remove();
					$('#loader').hide();

					if (data == "") {
						 
						alert("No Record");
					}  
				  $.each(data,function(key, itemList) { 
									 
					  				var status;
					  				if(itemList.status==0)
					  					{
					  					status="Pending By Store"
					  					}
					  				else if(itemList.status==1)
					  					{
					  					status="Approved By Store"
					  					}
					  				else if(itemList.status==2)
				  					{
				  					status="Rejected By Directore"
				  					}
					  				else if(itemList.status==3)
				  					{
				  					status="Reject To Account"
				  					}
					  				else if(itemList.status==4)
				  					{
				  					status="Approved By Dierctor"
				  					}
					  				else if(itemList.status==5)
				  					{
				  					status="Approved By Account"
				  					}
					  
					  
									var tr = $('<tr></tr>');  
								  	tr.append($('<td></td>').html(key+1)); 
								  	tr.append($('<td></td>').html(itemList.mrnNo)); 
								  	tr.append($('<td></td>').html(itemList.gateEntryDate));
								  	tr.append($('<td></td>').html(itemList.mrnStoreDate));
								  	tr.append($('<td></td>').html(itemList.suppName));
								  	tr.append($('<td></td>').html(itemList.lrNo));
								  	tr.append($('<td></td>').html(itemList.vehicleNo)); 
								  	tr.append($('<td></td>').html(status));
								  	if(itemList.status==0 || itemList.status==2)
								  		{
								  		tr.append($('<td></td>').html('  <a href="${pageContext.request.contextPath}/showStoreMaterialReciept?mrnId='+itemList.mrnId+'" class="action_btn" > <abbr title="Details"><i class="fa fa-list"></i></abbr></a> <a href="${pageContext.request.contextPath}/editGateEntry?mrnId='+itemList.mrnId+'&flag=1" class="action_btn" ><span class="glyphicon glyphicon-edit" > </span></a> '));
									  	
								  		}
								  	else
								  		{
								  		tr.append($('<td></td>').html('  <a href="${pageContext.request.contextPath}/allRecordwithDateDetailed?mrnId='+itemList.mrnId+'" class="action_btn" ><abbr title="Details"><i class="fa fa-list"></i></abbr></a> '));
									  	
								  		}
								  	
									$('#table_grid1 tbody').append(tr);

									 

								})
								
				
					
				});
		
		
	}
	</script>
		
		
		
</body>
</html>