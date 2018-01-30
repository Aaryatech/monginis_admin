<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

 
 
 <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<style>

table {
  overflow: hidden;
}

tr:hover {
  background-color: #ffa;
}

td, th {
  position: relative;
}
td:hover::after,
th:hover::after {
  content: "";
  position: absolute;
  background-color: #ffa;
  left: 0;
  top: -5000px;
  height: 10000px;
  width: 100%;
  z-index: -1;
}
</style>

 
<body>

<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>


	<c:url var="callSearchOrdersProcess" value="/searchOrdersProcess" />
	<c:url var="callChangeQty" value="/callChangeQty" />
	<c:url var="callDeleteOrder" value="/callDeleteOrder" />


	 


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
						<i class="fa fa-file-o"></i>Orders
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
								<i class="fa fa-bars"></i>Search Order
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
							<form action="searchOrdersProcess" class="form-horizontal"
								method="post" id="validation-form">




								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Franchisee
									</label>
									<div class="col-sm-9 col-lg-10 controls">

										<select data-placeholder="Select Franchisee"
											class="form-control chosen" multiple="multiple" tabindex="6"
											name="fr_id" id="fr_id">

											<option value="0">All</option>
											<c:forEach items="${franchiseeList}" var="franchiseeList">
												<option value="${franchiseeList.frId}">${franchiseeList.frName}</option>


											</c:forEach>

										</select>
									</div>
								</div>

								<div class="form-group">
									<label for="textfield2" class="col-xs-3 col-lg-2 control-label">Menu</label>
									<div class="col-sm-9 col-lg-10 controls">
										<select class="form-control chosen" multiple="multiple"
											tabindex="6" name="item_id" id="item_id">

											<c:forEach items="${menuList}" var="menuList">
												<option value="${menuList.menuId}">${menuList.menuTitle}</option>

											</c:forEach>

										</select>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="date" size="16"
											type="text" name="date" value="${date}" required />
									</div>
								</div>


								<div align="center" class="form-group">
									<div
										class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
										<input type="button" class="btn btn-primary" value="Submit" id="callSubmit"
											onclick="callSearch()">


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
								</div>





<c:set var="dis" value="none"/>


								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i> Order List
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
											<table width="100%" class="table table-advance" id="table1">
												<thead>
													<tr>
														<th width="138" style="width: 18px" align="left">Sr
															No</th>
														<th width="138" align="left">Franchise Name</th>
														<th width="159" align="left">Item Name</th>
														<th width="159" align="left">Category</th>
														<!-- <th width="159" align="left">Item Code</th> -->
														<th width="159" align="left">Quantity</th>
														<th width="100" align="left">Action</th>

														<!-- <th width="91" align="left">Quantity</th> -->
														<!-- 	<th width="105" align="left">MRP</th> -->
														<!-- <th width="423" align="left">isEdit</th>
														<th width="88" align="left">edit Quantity</th>
														<th width="423" align="left">is Positive</th> -->
														<!-- <th width="70" align="left">Total</th> -->
														<!-- <th width="70" align="left">Remarks</th>
														<th width="70" align="left">Action</th> -->

													</tr>
												</thead>
												<tbody>
													<c:forEach items="${orderList}" var="orderList"
														varStatus="count">
							<c:set var="dis" value="block"/>
														<tr>
															<td><c:out value="${count.index+1}" /></td>

															<td align="left"><c:out value="${orderList.frName}" /></td>

															<td align="left"><c:out
																	value="${orderList.itemName}" /></td>


															<td align="left"><c:out value="${orderList.catName}" /></td>


															<td align="left"><c:out value="${orderList.id}" /></td>

															<%-- <td align="left"><c:out
																	value="${orderList.orderQty}" /></td>
															<td align="left"><c:out value="" /></td> --%>

															<%-- 
															<td align="left"><label><input type="radio"
																	name="is_edit${orderList.orderId}"
																	id="is_edit${orderList.orderId}" value="1" checked>
																	false</label> <label><input type="radio"
																	name="is_edit${orderList.orderId}"
																	id="is_edit${orderList.orderId}" value="0">true</label></td>

															<td align="left"><label><input type="text"
																	style="width: 60px; padding: 2px" name="edit-qty"
																	id="edit-qty" value="${orderList.editQty}"></label></td>

															<td align="left"><label><input type="radio"
																	name="is_positive${orderList.orderId}"
																	id="is_positive${orderList.orderId}" value="1" checked>false</label>
																<label><input type="radio"
																	name="is_positive${orderList.orderId}"
																	id="is_positive${orderList.orderId}" value="0">true</label></td>
 --%>
															<!-- <td align="left"><label><input type="text"
																	style="width: 60px; padding: 2px" name="total"maxlength="10" size="10"
																	id="total" value="total amt" readonly="readonly"></label></td> -->

															<!-- <td align="left"><label><input type="text"
																	style="width: 60px; padding: 2px" name="remarks"
																	id="remarks" value="ramarks"></label></td>
															<td align="left"><label><input type="submit"
																	name="submit_button" id="submit_button"></label></td>
 -->

														</tr>
													</c:forEach>

												</tbody>
											</table>
										</div>
									</div>


								</div>
								<div class="form-group" style="display: <c:out value="${dis}" />;" id="range">
								 
											 
											 
											<div class="col-sm-3  controls">
											 <input type="button" id="expExcel" class="btn btn-primary" value="EXPORT TO Excel" onclick="exportToExcel();" disabled="disabled">
											</div>
											</div>
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

function callSearch() {

	
	var itemIds=$("#item_id").val();
	
	var array=[];
	

	var frIds=$("#fr_id").val();
		

	var date = $("#date").val(); 
	//alert(date);
	$('#loader').show();

$.getJSON('${callSearchOrdersProcess}', {

	fr_id_list : JSON.stringify(frIds),
	item_id_list : JSON.stringify(itemIds),
	date : date,
	
	ajax : 'true'

}, function(data) {
	document.getElementById("expExcel").disabled=true;
	$('#loader').hide();
	var len = data.length;


	$('#table1 td').remove();

	$.each(data,function(key, orders) {
		document.getElementById("expExcel").disabled=false;
		document.getElementById('range').style.display = 'block';
	var tr = $('<tr></tr>');

  	tr.append($('<td></td>').html(key+1));

  	tr.append($('<td></td>').html(orders.frName));

  	tr.append($('<td></td>').html(orders.itemName));

  	tr.append($('<td></td>').html(orders.catName));

 	tr.append($('<td></td>').html("<input type=number onkeypress='return IsNumeric(event);' ondrop='return false;' onpaste='return false;' style='text-align: center;' class='form-control' min=0 id="+orders.orderId+" Value="+orders.orderQty+" disabled>"));
  
 	tr.append($('<td></td>').html(' <a>   <span class="glyphicon glyphicon-edit" id="edit'+orders.orderId+'" onClick=editQty('+orders.orderId+');> </span> </a><a><span class="glyphicon glyphicon-remove" id="delete'+orders.orderId+'" onClick=deleteOrder('+orders.orderId+');> </span></a>'));
  		
 
	$('#table1 tbody').append(tr);

})

});

}

</script>




	<script type="text/javascript">
		function editQty(orderId)
		{
			var state=document.getElementById(orderId).disabled;
			var textId=document.getElementById(orderId).value;
			//alert(textId);
			//document.getElementById(orderId).disabled=false;
			if(state)
				{
				$("#edit"+orderId).removeClass("glyphicon glyphicon-edit");
				 $("#edit"+orderId).addClass("glyphicon glyphicon-ok");
				document.getElementById(orderId).disabled=false;
				
				}
			else{
				$("#edit"+orderId).removeClass("glyphicon glyphicon-ok");
				 $("#edit"+orderId).addClass("glyphicon glyphicon-edit");
				document.getElementById(orderId).disabled=true;
				$.getJSON('${callChangeQty}',
						{
					
							order_id : orderId,
							order_qty : textId,
							
							ajax : 'true'
							
						});
			}
		}
		
		var specialKeys = new Array();
        specialKeys.push(8); //Backspace
        function IsNumeric(e) {
            var keyCode = e.which ? e.which : e.keyCode
            var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1);
           /*  document.getElementById("error").style.display = ret ? "none" : "inline"; */
            return ret;
        }
        
        
		</script>
	 <script type="text/javascript">
		function deleteOrder(orderId)
		{
			
		    if (confirm("Do you want to Delete this order?") == true) {
		    	$.getJSON('${callDeleteOrder}',
						{
							order_id : orderId,
							
							ajax : 'true'
							
						}, function(data) {
							document.getElementById("expExcel").disabled=true;
							$('#loader').hide();
							var len = data.length;


							$('#table1 td').remove();
							
							$.each(data,function(key, orders) {
								document.getElementById("expExcel").disabled=false;
								document.getElementById('range').style.display = 'block';
							var tr = $('<tr></tr>');

						  	tr.append($('<td></td>').html(key+1));

						  	tr.append($('<td></td>').html(orders.frName));

						  	tr.append($('<td></td>').html(orders.itemName));

						  	tr.append($('<td></td>').html(orders.catName));

						 	tr.append($('<td></td>').html("<input type=number onkeypress='return IsNumeric(event);' ondrop='return false;' onpaste='return false;' style='text-align: center;' class='form-control' min=0 id="+orders.orderId+" Value="+orders.orderQty+" disabled>"));
						  
						 	tr.append($('<td></td>').html(' <a>   <span class="glyphicon glyphicon-edit" id="edit'+orders.orderId+'" onClick=editQty('+orders.orderId+');> </span> </a><a><span class="glyphicon glyphicon-remove" id="delete'+orders.orderId+'" onClick=deleteOrder('+orders.orderId+');> </span></a>'));
						  		
						 
							$('#table1 tbody').append(tr);

						})

					
		    } );
		    
		    }		
			
			

		}
		
		</script>

<script>
	function exportToExcel()
		{
			 
			window.open("${pageContext.request.contextPath}/exportToExcel");
					document.getElementById("expExcel").disabled=true;
		}
			</script>
</body>
</html>