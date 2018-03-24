<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

<c:url var="searchPo" value="/searchPo"></c:url>
<c:url var="supplier" value="/supplier"></c:url>
<c:url var="dateWisePo" value="/dateWisePo"></c:url>

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
					<i class="fa fa-file-o"></i>Purchase Order
				</h1>
				<!-- <h4>Bill for franchises</h4> -->
			</div>
		</div>
		<!-- END Page Title -->

		<!-- BEGIN Breadcrumb -->
		<div id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i> <a
					href="${pageContext.request.contextPath}/home">Home</a> <span
					class="divider"><i class="fa fa-angle-right"></i></span></li>
				<li class="active">Purchase Order</li>
			</ul>
		</div>
		<!-- END Breadcrumb -->
		
		<!-- BEGIN Main Content -->
		<div class="box" id="polist">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>PO List
				</h3>
				<div class="box-tool">
								<a  onclick="showdatewisetable()">Show Datewise Record</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>

			</div>
			<div class=" box-content">
			<div class="box">
			<div class="col-md-2">
			<a href="showDirectPurchaseOrder" class="btn btn-info btn">
          <span class="glyphicon glyphicon-pencil"></span> Add New 
        </a>
				<!-- <button class="btn btn-info pull-left" style="margin-right: 5px;" >Add
					New</button> -->
					
			</div>
			<div class="col-md-2">Search</div>
					<div class="col-md-3">
				<input type="text" onkeyup="search()" id="search" name="search" class="form-control" >
					
				</div>

<br/>

		</div>

			<form id="submitBillForm"
				action="${pageContext.request.contextPath}/submitNewBill"
				method="post">
				<div class=" box-content">
					<div class="row">
						<div class="col-md-12 table-responsive">
						 <input type="hidden" name="flag" id="flag" value="${flag}">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
								
									<tr>
										<th>Sr.No.</th>
										<th>PO.No.</th>
										<th>PO Date</th>
										<th>Po Type</th>
										<th>Suppiler</th>
										<th>Value</th>
										<th>Status</th>
										<th colspan="2">Action</th>

									</tr>
								</thead>
								<tbody>
								
								
								<c:forEach items="${purchaseorderlist}" var="purchaseorderlist"
													varStatus="count">
											<c:choose>
													<c:when test="${purchaseorderlist.poStatus==3}">
													<c:set var = "color" value="red"/>
													</c:when>
													<c:otherwise>
													  <c:set var = "color" value="black"/>
													</c:otherwise>
											</c:choose>
											<c:choose>
													<c:when test="${purchaseorderlist.poType==1}">
													<c:set var = "type" value="Inclusive"/>
													</c:when>
													<c:otherwise>
													  <c:set var = "type" value="Open"/>
													</c:otherwise>
											</c:choose>
											
											<c:choose>
													<c:when test="${purchaseorderlist.poStatus==0}">
													<c:set var = "status" value="Pending"/>
													</c:when>
													<c:when test="${purchaseorderlist.poStatus==1}">
													 <c:set var = "status" value="Requested"/>
													</c:when>
													<c:when test="${purchaseorderlist.poStatus==3}">
													 <c:set var = "status" value="Rejected"/>
													</c:when>
											</c:choose>

									

													<tr>
														<td style="color: <c:out value = "${color}"/>"><c:out value="${count.index+1}" /></td>

														
																
																<td align="left" style="color: <c:out value = "${color}"/>"><c:out
																value="${purchaseorderlist.poNo}" /></td>
																 
																<td align="left" style="color: <c:out value = "${color}"/>"><c:out
																value="${purchaseorderlist.poDate}" />  </td>
																 
																
																<td align="left" style="color: <c:out value = "${color}"/>"><c:out	
																value="${type}" />
																</td>
																
																<c:forEach items="${supplierList}" var="supplierList"
													varStatus="count">
																<c:choose>
													<c:when test="${purchaseorderlist.suppId==supplierList.suppId}">
													<td align="left" style="color: <c:out value = "${color}"/>"><c:out
																value="${supplierList.suppName}" /></td>
													</c:when>
													 </c:choose>
													 </c:forEach>
													 
													 <td align="left" style="color: <c:out value = "${color}"/>"><c:out	
																value="${purchaseorderlist.poTotalValue}" />
																</td>
																
													<td align="left" style="color: <c:out value = "${color}"/>"><c:out	
																value="${status}" />
																</td>
															
															
																
																
				
																
																
						<td>
						
						<a href="poHeaderWithDetailed/${purchaseorderlist.poId}/${flag}" class="action_btn" >
						<abbr title="Detail"><i class="fa fa-list"></i></abbr></a>
						
						<c:choose>
						<c:when test="${purchaseorderlist.poStatus==0}"> 
							
          					<a href="deletePoRecord/${purchaseorderlist.poId}"
						onClick="return confirm('Are you sure want to delete this record');"><abbr title='Delete'></abbr><span
																			class="glyphicon glyphicon-remove"></span></a>
						
						</c:when>
						<c:when test="${purchaseorderlist.poStatus==3}">		
          					<a href="deletePoRecord/${purchaseorderlist.poId}"
						onClick="return confirm('Are you sure want to delete this record');"><abbr title='Delete'></abbr><span
																			class="glyphicon glyphicon-remove"></span></a>
						</c:when>
						</c:choose>
						
						
						
						
						
						
						
						
						</td>
						
																</tr>
												</c:forEach>

								</tbody>
							</table>
						</div>
					</div>


</div>

		 

			</form>
	 
</div>
 

	 
	</div>
	<div class="box" id="datewise_table" style="display: none">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>Date Wise
				</h3>
				<div class="box-tool">
								<a  onclick="showdatewisetable()">PO List</a> <a data-action="collapse" href="#"><i
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
								
								<div align="center" class="form-group">
									<!-- <div class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0"> -->
										<input class="btn btn-primary" value="Pending" id="searchmixall"
											onclick="pending()">
				
										<input class="btn btn-primary" value="View All" id="searchmixall"
											onclick="searchPo()">

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
						<div class="col-md-12 table-responsive">
						 <input type="hidden" name="flag" id="flag" value="${flag}">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid1">
								<thead>
								
									<tr>
										<th>Sr.No.</th>
										<th>PO.No.</th>
										<th>PO Date</th>
										<th>Po Type</th>
										<th>Suppiler</th>
										<th>Value</th>
										<th>Status</th>
										<th colspan="2">Action</th>

									</tr>
								</thead>
								<tbody> 

								</tbody>
							</table>
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
	 <script>
		function search()
		{
			var search=document.getElementById("search").value; 
			var flag = document.getElementById("flag").value;
			$('#loader').show();
			
			$
			.getJSON(
					'${searchPo}',

					{
						 
						search : search,
						 
						ajax : 'true'

					},
					function(data) {
						
						
						$('#table_grid td').remove();
						$('#loader').hide();

						if (data == "") {
							 	alert("No Record");

						} 
						
						
						$.getJSON(
								'${supplier}',

								{ 
									ajax : 'true'

								},
								function(sp) { 
									 
									
								var splength=sp.length;
								 

					  $.each(data,function(key, itemList) {
									
										var spname;
										var type;
										var status;
										var tr = $('<tr></tr>');
										
										if(itemList.poType==1)
											{
											type="Inclusive";
											}
										else
											{
											type="Open";
											}
										if(itemList.poStatus==0)
										{
											status="Pending";
										}
									else if(itemList.poStatus==1)
										{
											status="Requested";
										}
									else if(itemList.poStatus==3)
									{
										status="Rejected";
									}
 
									  	tr.append($('<td></td>').html(key+1));

									  	tr.append($('<td></td>').html(itemList.poNo)); 
									  	tr.append($('<td></td>').html(itemList.poDate));
									  	tr.append($('<td></td>').html(type));
									  	for(var i=0;i<splength;i++)
									  		{
									  			if(itemList.suppId==sp[i].suppId)
									  				{
									  				spname=sp[i].suppName;
									  				break;
									  				}
									  		}
									  	tr.append($('<td></td>').html(spname)); 
									  	tr.append($('<td></td>').html(itemList.poTotalValue));
									  	tr.append($('<td></td>').html(status));
									  	if(itemList.poStatus==0 || itemList.poStatus==3)
									  		{
									  		tr.append($('<td></td>').html('  <a href="poHeaderWithDetailed/'+itemList.poId+'/'+flag+'" class="action_btn" > <abbr title="Detail"><i class="fa fa-list"></i></abbr></a> <a href="deletePoRecord/'+itemList.poId+'" onClick="return confirm("Are you sure want to delete this record");"><abbr title="Delete"></abbr><spanclass="glyphicon glyphicon-remove"></span></a><a href="deletePoRecord/'+itemList.poId+'" onClick="return confirm("Are you sure want to delete this record");"><abbr title="Delete"></abbr><span class="glyphicon glyphicon-remove"></span></a>'));
										  	
									  		}
									  	else
									  		{
									  		tr.append($('<td></td>').html('  <a href="poHeaderWithDetailed/'+itemList.poId+'/'+flag+'" class="action_btn" > <abbr title="Detail"><i class="fa fa-list"></i></abbr></a>'));
										  	
									  		}
									  	 
									  	 





										$('#table_grid tbody').append(tr);

										 

									})
									
					});
						
					});
			
			
		}
		
	
		
	</script>
	<script>
		function searchPo()
		{
			var from_date=document.getElementById("from_date").value; 
			var to_date=document.getElementById("to_date").value; 
			var flag = document.getElementById("flag").value;
			var all =1;
			$('#loader').show();
			$
			.getJSON(
					'${dateWisePo}',

					{
						 
						from_date : from_date,
						to_date : to_date,
						all : all,
						ajax : 'true'

					},
					function(data) {
						
						
						$('#table_grid1 td').remove();
						$('#loader').hide();

						if (data == "") {
							 
							alert("No Record");
						} 
						
						
						$.getJSON(
								'${supplier}',

								{ 
									ajax : 'true'

								},
								function(sp) { 
									 
									
								var splength=sp.length;
								 

					  $.each(data,function(key, itemList) {
									
										var spname;
										var type;
										var status;
										var tr = $('<tr></tr>');
										
										if(itemList.poType==1)
											{
											type="Inclusive";
											}
										else
											{
											type="Open";
											}
										if(itemList.poStatus==0)
										{
											status="Pending";
										}
										else if(itemList.poStatus==1)
										{
											status="Requested to Purchase";
										}
										else if(itemList.poStatus==2)
										{
											status="Requested to Director";
										}
										else if(itemList.poStatus==3)
										{
										status="Rejected By Purchase";
										}
										if(itemList.poStatus==4)
										{
											status="Rejected By Director";
										}
										else if(itemList.poStatus==5)
										{
											status="Approved Po By Director";
										}
										else if(itemList.poStatus==6)
										{
										status="Partially Closed";
										}
										else if(itemList.poStatus==7)
										{
										status="Closed";
										}
 
									  	tr.append($('<td></td>').html(key+1));

									  	tr.append($('<td></td>').html(itemList.poNo)); 
									  	tr.append($('<td></td>').html(itemList.poDate));
									  	tr.append($('<td></td>').html(type));
									  	for(var i=0;i<splength;i++)
									  		{
									  			if(itemList.suppId==sp[i].suppId)
									  				{
									  				spname=sp[i].suppName;
									  				break;
									  				}
									  		}
									  	tr.append($('<td></td>').html(spname)); 
									  	tr.append($('<td></td>').html(itemList.poTotalValue));
									  	tr.append($('<td></td>').html(status));
									  	 
									  	if(itemList.poStatus==0 || itemList.poStatus==3)
								  		{
								  		tr.append($('<td></td>').html('  <a href="poHeaderWithDetailed/'+itemList.poId+'/'+flag+'" class="action_btn" > <abbr title="Detail"><i class="fa fa-list"></i></abbr></a> <a href="deletePoRecord/'+itemList.poId+'" onClick="return confirm("Are you sure want to delete this record");"><abbr title="Delete"></abbr><spanclass="glyphicon glyphicon-remove"></span></a><a href="deletePoRecord/'+itemList.poId+'" onClick="return confirm("Are you sure want to delete this record");"><abbr title="Delete"></abbr><span class="glyphicon glyphicon-remove"></span></a>'));
									  	
								  		}
								  	else
								  		{
								  		tr.append($('<td></td>').html('  <a href="poHeaderWithDetailed/'+itemList.poId+'/'+flag+'" class="action_btn" > <abbr title="Detail"><i class="fa fa-list"></i></abbr></a>'));
									  	
								  		}





										$('#table_grid1 tbody').append(tr);

										 

									})
									
					});
						
					});
			
			
		}
		function pending()
		{
			var from_date=document.getElementById("from_date").value; 
			var to_date=document.getElementById("to_date").value; 
			var flag = document.getElementById("flag").value;
			var all =0;
			$('#loader').show();
			$
			.getJSON(
					'${dateWisePo}',

					{
						 
						from_date : from_date,
						to_date : to_date,
						all : all,
						ajax : 'true'

					},
					function(data) {
						
						
						$('#table_grid1 td').remove();
						$('#loader').hide();

						if (data == "") {
							 
							alert("No Record");
						} 
						
						
						$.getJSON(
								'${supplier}',

								{ 
									ajax : 'true'

								},
								function(sp) { 
									 
									
								var splength=sp.length;
								 

					  $.each(data,function(key, itemList) {
									
										var spname;
										var type;
										var status;
										var tr = $('<tr></tr>');
										
										if(itemList.poType==1)
											{
											type="Inclusive";
											}
										else
											{
											type="Open";
											}
										if(itemList.poStatus==0)
										{
											status="Pending";
										}
									 
 
									  	tr.append($('<td></td>').html(key+1));

									  	tr.append($('<td></td>').html(itemList.poNo)); 
									  	tr.append($('<td></td>').html(itemList.poDate));
									  	tr.append($('<td></td>').html(type));
									  	for(var i=0;i<splength;i++)
									  		{
									  			if(itemList.suppId==sp[i].suppId)
									  				{
									  				spname=sp[i].suppName;
									  				break;
									  				}
									  		}
									  	tr.append($('<td></td>').html(spname)); 
									  	tr.append($('<td></td>').html(itemList.poTotalValue));
									  	tr.append($('<td></td>').html(status));
									  	 
									  	if(itemList.poStatus==0 || itemList.poStatus==3)
								  		{
								  		tr.append($('<td></td>').html('  <a href="poHeaderWithDetailed/'+itemList.poId+'/'+flag+'" class="action_btn" > <abbr title="Detail"><i class="fa fa-list"></i></abbr></a> <a href="deletePoRecord/'+itemList.poId+'" onClick="Confirm("Are you sure want to delete this record");"><abbr title="Delete"></abbr><spanclass="glyphicon glyphicon-remove"></span></a><a href="deletePoRecord/'+itemList.poId+'" onClick="return confirm("Are you sure want to delete this record");"><abbr title="Delete"></abbr><span class="glyphicon glyphicon-remove"></span></a>'));
									  	
								  		}
								   
										$('#table_grid1 tbody').append(tr);

										 

									})
									
					});
						
					});
			
			
		}
	
		
	</script>
<script type="text/javascript">
	var div=0
	function showdatewisetable() {
		
		if(div==0)
		{
			$('#polist').hide();
			$("#datewise_table").show();
			div=1;
		}
		else if(div==1)
		{
			$('#polist').show();
			$("#datewise_table").hide();
			div=0;
		}
	 
}
	
	 
	</script>
  
</body>
</html>