<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
 <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
 <jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
<body>
<script>
  $( function() {
    $( "#datepicker" ).datepicker({ dateFormat: 'dd-mm-yy' });
  } );
  $( function() {
    $( "#datepicker2" ).datepicker({ dateFormat: 'dd-mm-yy' });
  } );
  
  </script>
	<c:url var="getProdOrderForecating" value="/getProdOrderForecating" />
	<c:url var="getMenu" value="/getMenu" />	
	<c:url var="getProductionRegSpCakeOrder" value="/getProductionRegSpCakeOrder" />

 


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
						<i class="fa fa-file-o"></i>Add Production Forecasting
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
								<i class="fa fa-bars"></i> Search Orders
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
						 <form class="form-horizontal" method="get" id="validation-form">
							<br/>
								<div class="form-group">

									<label for="textfield2" class="col-xs-3 col-lg-2 control-label">Select
										Category </label>
									<div class="col-sm-9 col-lg-10 controls">

										<select class="form-control chosen" data-placeholder="Choose Category"
											 name="selectCategory" id="selectCategory" tabindex="-1" data-rule-required="true">

										<option selected>Selece Category</option>
											
											<c:forEach items="${unSelectedCatList}" var="unSelectedCat"
													varStatus="count">
												<option value="${unSelectedCat.catId}"><c:out value="${unSelectedCat.catName}"/></option>
												</c:forEach>

										</select>
									</div>


								</div>
							
							<div class="form-group">

									<label class="col-sm-3 col-lg-2 control-label">Menu</label>
									<div class="col-sm-9 col-lg-10 controls">
										<select data-placeholder="Select Category"
											class="form-control chosen-select" name="selectMenu"
											tabindex="-1" id="selectMenu" data-rule-required="true">

										</select>
									</div>


								</div>
<br/>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Production
										Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="datepicker" size="16"
											 type="text" name="production_date" required />
									 

										Select Time Slot <select  data-placeholder="Choose Time Slot"
											tabindex="-1" name="selectTime" id="selectTime" data-rule-required="true">

										
											<c:forEach items="${productionTimeSlot}" var="productionTime"
													varStatus="count">
												<option value="${productionTime}"><c:out value="Time Slot ${productionTime}"/></option>
												</c:forEach>
										
										
										</select>
										</div>
								</div>
	
								<div align="center" class="form-group">
									<div
										class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
										<input class="btn btn-primary" value="Search..." id="callSubmit"
											onclick="searchOrder()">


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
											<i class="fa fa-table"></i>  Orders Difference List
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
											<table width="100%" class="table table-advance" id="table1" name="table1">
												<thead>
													<tr>
														<th width="18" style="width: 18px">Sr No</th>
														
														<th width="100">Item Name</th>
														<th width="50">Order Quantity</th>
														<th width="100">Production Quantity</th>
														<th width="50">Stock Quantity</th>
														<th width="50">Production</th>
														<th width="50">In Stock</th>
									
													</tr>
												</thead>
												<tbody>
											
												
												</tbody>
												
											</table>
										</div>
									</div>


								</div>
								
								
							 </form> 
						</div>
						
					
										
					</div>
					<form action="submitProduction" method ="post">
						
								<div align="center" class="form-group">
								
									<div
										class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
										<input type="submit" class="btn btn-primary" value="Submit" id="callSubmit">


									</div>
								</div>
						</form>		
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
	$('#selectCategory').change(
			function() {
				$.getJSON('${getMenu}', {
					selectedCat : $(this).val(),
					ajax : 'true'
				}, function(data) {
					var html = '<option value="" selected >Select Menu</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].menuId + '">'
								+ data[i].menuTitle + '</option>';
					}
					html += '</option>';
					$('#selectMenu').html(html);
					$('#selectMenu').formcontrol('refresh');

				});
			});
});
</script>
<script type="text/javascript">
	function searchOrder()
	{ 
		$('#table1 td').remove();
		
		var autoindex=0;
		var isValid = validate();
		
		if (isValid) {
			
			
			var productionDate = document.getElementById("datepicker").value;
			var selectedMenu=$("#selectMenu").val();
			$('#loader').show();
			
			
			$.getJSON('${getProdOrderForecating}',{
				
								selectedMenu_list : JSON.stringify(selectedMenu),
								productionDate : productionDate,
								ajax : 'true',

							},
							function(data) {

								//$('#table_grid td').remove();
								
								
								$('#loader').hide();
								/* if (data == "") {
									alert("No records found !!");

								} */
								alert(data);

								
								$.each(data,function(key, order) {

									autoindex = autoindex +1 ;

													var tr = "<tr>";

													var index = "<td>&nbsp;&nbsp;&nbsp;"
														+ autoindex + "</td>";

													/* var itemId = "<td>&nbsp;&nbsp;&nbsp;"
															+ order.itemId
															+ "</td>"; */
															var itemName = "<td>&nbsp;&nbsp;&nbsp;"
																+ order.itemName
																+ "</td>";
																var Qty = "<td>&nbsp;&nbsp;&nbsp;"
																	+ order.qty
																	+ "</td>";
																	


													var trclosed = "</tr>";

												
													$('#table1 tbody').append(tr);
													$('#table1 tbody').append(index);
													/* $('#table1 tbody').append(itemId); */
													$('#table1 tbody').append(itemName);
													$('#table1 tbody').append(Qty);
													
													$('#table1 tbody').append(trclosed);
													
													

												})
													

							});
			
			$.getJSON('${getProductionRegSpCakeOrder}',{
				
				selectedMenu_list : JSON.stringify(selectedMenu),
				productionDate : productionDate,
				ajax : 'true',

			},
			function(data) {
				
				//$('#table_grid td').remove();
				
				
				$('#loader').hide();
				/* if (data == "") {
					alert("No records found !!");

				} */
				alert(data);

				
				$.each(data,function(key, order) {

					autoindex =  autoindex +1;

									var tr = "<tr>";

									var index = "<td>&nbsp;&nbsp;&nbsp;"
										+ autoindex + "</td>";

								/* 	var itemId = "<td>&nbsp;&nbsp;&nbsp;"
											+ order.itemId
											+ "</td>"; */
											var itemName = "<td>&nbsp;&nbsp;&nbsp;"
												+ order.itemName
												+ "</td>";
												var Qty = "<td>&nbsp;&nbsp;&nbsp;"
													+ order.qty
													+ "</td>";
													


									var trclosed = "</tr>";

								
									$('#table1 tbody').append(tr);
									$('#table1 tbody').append(index);
									/* $('#table1 tbody').append(itemId); */
									$('#table1 tbody').append(itemName);
									$('#table1 tbody').append(Qty);
									
									$('#table1 tbody').append(trclosed);
									
									

								})
									

			});


		}
	}
	</script>
	<script type="text/javascript">
		function validate() {
		
			var selectedMenu = $("#selectMenu").val();
			
			var selectOrderDate =$("#datepicker").val();
			

			var isValid = true;

			

			 if (selectedMenu == "" || selectedMenu == null) {

				isValid = false;
				alert("Please select Menu");

			}
			else if (selectOrderDate == "" || selectOrderDate == null) {

				isValid = false;
				alert("Please select Order Date");
			}
			return isValid;

		}
	</script>


</body>
</html>