<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
 
 <c:url var="machineListOrVehicleList" value="/machineListOrVehicleList"></c:url>
<c:url var="serchAmcList" value="/serchAmcList"></c:url>


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
					<i class="fa fa-file-o"></i>AMC List
				</h1>
				<!-- <h4>Bill for franchises</h4> -->
			</div>
		</div>
		<!-- END Page Title -->

		
		<!-- BEGIN Main Content -->
		<div class="box" id="pending">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>AMC List
				</h3>
				<div class="box-tool">
								<a href="${pageContext.request.contextPath}/insertAmc">Insert AMC</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
				<!-- <div class="box-tool">
				<a  onclick="showdatewisetable()">Show Datewise All Record</a> 
				</div> -->

			</div> 
			
			
				<div class=" box-content">
					
					<div class="box-content">
								<div class="col-md-2">Select Type* </div>
								<div class="col-md-3">
								 <select name="typeId" id="typeId" class="form-control chosen" tabindex="6" required>
											<option value="">Select Option</option>
											<option value="1">Vehicle</option>
											<option value="2">Machine</option>
										</select>
								</div>
								
								
							<div class="col-md-2">Select Machine Or Vehicle* </div> 
								<div class="col-md-3">
							 
                                    <select name="mechId" id="mechId" class="form-control chosen" tabindex="6" required>
											 
										</select>
								</div>
					
							</div><br><br>
							
								
							<div class=" box-content">
								  
								<div align="center" class="form-group">
									 
										<input type="button" class="btn btn-primary" value="Search" 
											onclick="serchAmc()"> 

									 
									
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
								
				
					<div class="row">
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Vehicle Or Machine Name</th>
										<th>Type</th>
										<th>Dealer Name</th>
										<th>From Date</th>
										<th>To date</th>
										 <th>Bill No</th>
										 <th>Taxable Amt</th>
										 <th>Tax Amt</th>
										 <th>Total</th> 


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
		$(document).ready(function() { 
			$('#typeId').change(
					function() {
						//alert("makeId"+$(this).val());
						var typeId=$(this).val(); 
					    
						$.getJSON('${machineListOrVehicleList}', {
							
							typeId : $(this).val(), 
							ajax : 'true'
						},
								function(data) {
							 
							var html = '<option value="0" selected >All</option>';
							
							var len = data.length;
							for ( var i = 0; i < len; i++) {
								html += '<option value="' + data[i].mechId + '">'
										+ data[i].mechName + '</option>';
							}
							html += '</option>';
							$('#mechId').html(html);
							$("#mechId").trigger("chosen:updated");
													
								});
					 
						 
				})
				 		 
		});
		
	function serchAmc()
	{
		var typeId=document.getElementById("typeId").value; 
		var mechId=document.getElementById("mechId").value; 
	 
		$('#loader').show();
		$
		.getJSON(
				'${serchAmcList}',

				{
					 
					typeId : typeId,
					mechId : mechId, 
					ajax : 'true'

				},
				function(data) {
					 
					 $('#loader').hide();
						$('#table_grid td').remove();

					if (data == "") {
						 
						alert("No Record");
					}  
					$.each(data,function(key, itemList) {
						
									var type;
									if(itemList.typeId==1)
										{
										type="Vehicle";
										}
									else
										{
										type="Machine";
										}
									var tr = $('<tr></tr>'); 
								  	tr.append($('<td></td>').html(key+1)); 
								  	tr.append($('<td></td>').html(itemList.mechName)); 
								  	tr.append($('<td></td>').html(type)); 
								  	tr.append($('<td></td>').html(itemList.dealerName)); 
								  	tr.append($('<td></td>').html(itemList.amcFromDate)); 
								  	tr.append($('<td></td>').html(itemList.amcFromDate)); 
								  	tr.append($('<td></td>').html(itemList.billNo)); 
								  	tr.append($('<td></td>').html(itemList.amcTaxableAmt)); 
								  	tr.append($('<td></td>').html(itemList.amcTaxAmt)); 
								  	tr.append($('<td></td>').html(itemList.amcTotal)); 
								  	$('#table_grid tbody').append(tr);
 

								})
								
				
					
				});
		
		
	}
	</script>
		
		
		
		
</body>
</html>