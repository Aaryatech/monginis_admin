<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Dashboard - MONGINIS Admin</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<!--base css styles-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/loader.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap/css/bootstrap.min.css">
 <link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/font-awesome/css/font-awesome.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/data-tables/bootstrap3/dataTables.bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-fileupload/bootstrap-fileupload.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/chosen-bootstrap/chosen.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-timepicker/compiled/timepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/clockface/css/clockface.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/css/datepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/daterangepicker.css" />



<!--page specific css styles-->

<!--flaty css styles-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flaty.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flaty-responsive.css">

<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/img/favicon.png">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/common.js"></script>
</head>
<body>




<c:url var="callSearchOrdersProcess"
		value="/searchOrdersProcess" />
	<c:url var="callChangeQty"
		value="/callChangeQty" />
		<c:url var="callDeleteOrder"
		value="/callDeleteOrder" />	


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
										<label for="textfield2"
											class="col-xs-3 col-lg-2 control-label">Items</label>
										<div class="col-sm-9 col-lg-10 controls">
											<select class="form-control chosen" multiple="multiple" tabindex="6" name="item_id" id="item_id">
												
												<c:forEach items="${menuList}" var="menuList">
											<option value="${menuList.menuId}">${menuList.menuTitle}</option>
											
											</c:forEach>
											
											</select>
										</div>
									</div>

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Production
										Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="dp2" size="16"
											type="text" name="prod_date" required />
									</div>
								</div>

								
								<div align="center" class="form-group">
									<div
										class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
										<input  class="btn btn-primary" value="Submit"
											id="callSubmit" onclick="callSearch()" >

										
									</div>
									<div align="center" id="loader" style="display:none">

	<span>
	<h4><font color="#343690">Loading</font></h4></span>
	<span class="l-1"></span>
	<span class="l-2"></span>
	<span class="l-3"></span>
	<span class="l-4"></span>
	<span class="l-5"></span>
	<span class="l-6"></span>
	</div>
								</div>
								
									
								

								
																		
									
									
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
														<th width="159" align="left">Item Code</th>
														<th width="159" align="left">Quantity</th> 
														<th width="159" align="left">Action</th>
													
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
													<c:forEach items="${orderList}" var="orderList" varStatus="count">

														<tr>
															<td><c:out value="${count.index+1}"/></td>

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
				
		
			var prodDate = document.getElementById("dp2").value;
			$('#loader').show();
					
			
			$.getJSON('${callSearchOrdersProcess}',
							{
								fr_id_list : JSON.stringify(frIds),
								item_id_list : JSON.stringify(itemIds),
								prod_date : prodDate,
								
								ajax : 'true'
								
							},
							function(data) {
								
								$('#table1 td').remove();
								$('#loader').hide();
								if(data==""){
									alert("No Orders Found");
								}

								$.each(data,function(key, orders) {

												
													var index = key + 1;
													
													var tr = "<tr>";

													var index = "<td>&nbsp;&nbsp;&nbsp;"
															+ index + "</td>";
															
													var frName = "<td>&nbsp;&nbsp;&nbsp;"
															+ orders.frName
															+ "</td>";
															
													var itemName = "<td>&nbsp;&nbsp;&nbsp;"
															+ orders.itemName
															+ "</td>";
															
													var catName = "<td>&nbsp;&nbsp;&nbsp;"
															+ orders.catName
															+ "</td>";
															
													var itemCode = "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
															+ orders.id
															+ "</td>";
															
															 var itemQuantity = "<td><input type=number id="+orders.orderId+"  Value="+orders.orderQty+" disabled></td>";
																var action = "<td><input type=button id=edit onClick=editQty("+orders.orderId+"); Value=Edit> <input type=button id=delete"+orders.orderId+" onClick=deleteOrder("+orders.orderId+"); Value=Delete></td>";

															
															
													
													
													var trclosed = "</tr>";
													
													$('#table1 tbody').append(tr);
													$('#table1 tbody').append(index);
													$('#table1 tbody').append(frName);
													$('#table1 tbody').append(itemName);
													$('#table1 tbody').append(catName);
													$('#table1 tbody').append(itemCode);
													$('#table1 tbody').append(itemQuantity);
													$('#table1 tbody').append(action);
											
													$('#table1 tbody').append(trclosed);


												})
												var tbodyclosing="</tbody>";


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
			
				document.getElementById(orderId).disabled=false;
				
				}
			else{
				document.getElementById(orderId).disabled=true;
				$.getJSON('${callChangeQty}',
						{
					
							order_id : orderId,
							order_qty : textId,
							
							ajax : 'true'
							
						});
			}
		}
		</script>
			<script type="text/javascript">
		function deleteOrder(orderId)
		{
			
			var txt;
		    if (confirm("You want to Delete Order!") == true) {
		    	$.getJSON('${callDeleteOrder}',
						{
							order_id : orderId,
							
							ajax : 'true'
							
						});
		    } 
		    
					
			
			

		}
		
		</script>
		
		
</body>
</html>