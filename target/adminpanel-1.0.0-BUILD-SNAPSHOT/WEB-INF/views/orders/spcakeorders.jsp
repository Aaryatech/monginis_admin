<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
 <jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<body>


	<c:url var="callspCakeOrderProcess" value="/spCakeOrderProcess" />

      <c:url var="deleteSpOrder" value="/deleteSpOrder" />

	 


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
						<i class="fa fa-file-o"></i> Special Cake Orders
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
							<form class="form-horizontal" method="get" id="validation-form">
								<!-- action="spCakeOrderProcess" -->




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

								<%-- <div class="form-group">
										<label for="textfield2"
											class="col-xs-3 col-lg-2 control-label">Items</label>
										<div class="col-sm-9 col-lg-10 controls">
											<select class="form-control input-sm" name="item_name" id="item_name">
												<option value="1" selected>Savouries</option>
												
												<c:forEach items="${menuList}" var="menuList">
											<option value="${menuList.menuId}">${menuList.menuTitle}</option>
											
											</c:forEach>
											
											</select>
										</div>
									</div> --%>




								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Production
										Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" value="${todayDate }" id="dp2" size="16"
											type="text" name="prod_date" data-rule-required="true" />
									</div>
								</div>


								
								<div align="center" class="form-group">
									<div
										class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
										<input  class="btn btn-primary" value="Submit"
											id="callSubmit" onclick="callSearch()" >

										
									</div>
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

								<!-- <div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
										<button type="submit" class="btn btn-primary">
											<i class="fa fa-check"></i> Search
										</button>
										<button type="button" class="btn">Cancel</button>
									</div>
								</div>
 -->
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
									
									<c:set var="dis" value="none"/>
									
									<div class="box-content">

										<div class="clearfix"></div>
										<div class="table-responsive" style="border: 0">
											<table width="100%" class="table table-advance" id="table1">
												<thead>
													<tr>
														<th width="20" align="left">Sr No</th>
														<th width="138" align="left">Franchise Name</th>
														<th width="159" align="left"><span
															style="width: 130px;">Item Code</span></th>
														<th width="159" align="left"><span
															style="width: 130px;">Name</span></th>
														<th width="168" align="left">Flavour</th>
														<th width="140" align="left">Event</th>
														<th width="88" align="left">Delivery Date</th>
														<th width="105" align="left">Rate</th>
														<th width="75" align="left">Add Rate</th>
														<th width="91" align="left">Total</th>
													  <th width="87" align="left">View</th>  
														<th width="87" align="left">PDF</th>
													<th width="87" align="left">Action</th>
														
													</tr>
												</thead>
												<tbody>
												
													<c:forEach items="${spCakeOrderList}" var="spCakeOrder"
														varStatus="count">
														<c:set var="dis" value="block"/>
														<tr>
														
															<td><c:out value="${count.index+1}" /></td>
															<td align="left"><c:out
																	value="${spCakeOrder.frName}"></c:out></td>
															<td align="left"><c:out
																	value="${spCakeOrder.itemId}"></c:out></td>

															<td align="left"><c:out
																	value="${spCakeOrder.spName}"></c:out></td>
															<td align="left"><c:out
																	value="${spCakeOrder.spfName}"></c:out></td>

															<td align="left"><c:out
																	value="${spCakeOrder.spEvents}"></c:out></td>

															<td align="left"><c:out
																	value="${spCakeOrder.spDeliveryDate}"></c:out></td>

															<td align="left"><c:out
																	value="${spCakeOrder.spPrice}"></c:out></td>

															<td align="left"><c:out
																	value="${spCakeOrder.spTotalAddRate}"></c:out></td>

															<c:set var="spAddRate"
																value="${spCakeOrder.spTotalAddRate}" />
															<c:set var="spPrice" value="${spCakeOrder.spPrice}" />


															<td align="left"><c:out
																	value="${spAddRate + spPrice}"></c:out></td>

															<td align="left"><c:out value="PDF"></c:out></td>

															<td align="left"><c:out value="ADMIN PDF"></c:out></td>





														</tr>

													</c:forEach>
												</tbody>
												
											</table>
											
											
										</div>
									</div>
								</div>
								<div class="form-group" style="display: <c:out value="${dis}" />;" id="range">
								<div class="col-sm-2  controls">
											<input type="text" class="form-control"  id="from"  placeholder="to no"  >
											</div>
											<div class="col-sm-2  controls">
											<input type="text"  class="form-control" id="to" placeholder="from no" >
											</div>
											<div class="col-sm-3  controls">
											<input type="button" id="from" class="btn btn-primary" value="EXPORT TO PDF IN RANGE" onclick="inRangePdf();">
											</div>
											<div class="col-sm-3  controls">
											<%-- <a onclick="exportToExcel()" id="expExcel" href="${pageContext.request.contextPath}/download" disabled="true" class="btn btn-primary">EXPORT TO Excel</a> --%>
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

			var frIds=$("#fr_id").val();
			var array=[];
			var prodDate = document.getElementById("dp2").value;
			$('#loader').show();
					
			$.getJSON('${callspCakeOrderProcess}',
							{
								fr_id_list : JSON.stringify(frIds),
								prod_date : prodDate,
								
								ajax : 'true',
							},
							function(data) {
								$('#table1 td').remove();
								$('#loader').hide();
								if(data==""){
									alert("No Orders Found");
									document.getElementById("expExcel").disabled=true;
								}
								$.each(data,function(key, spCakeOrder) {
									document.getElementById("expExcel").disabled=false;
									document.getElementById('range').style.display = 'block';
									var len=data.length
									
									

									
									var tr = $('<tr></tr>');

								  	tr.append($('<td></td>').html(key+1));

								  	tr.append($('<td></td>').html(spCakeOrder.frName));

								  	tr.append($('<td></td>').html(spCakeOrder.itemId));

								  	tr.append($('<td></td>').html(spCakeOrder.spName));

								  	tr.append($('<td></td>').html(spCakeOrder.spfName));
								  	
								  	tr.append($('<td></td>').html(spCakeOrder.spEvents));

								  	tr.append($('<td></td>').html(spCakeOrder.spDeliveryDate));

								  	tr.append($('<td></td>').html(spCakeOrder.spPrice));

								  	tr.append($('<td></td>').html(spCakeOrder.spTotalAddRate));
								  	
									var totalValue=parseFloat(spCakeOrder.spTotalAddRate) + parseFloat(spCakeOrder.spPrice);

								  	tr.append($('<td></td>').html(totalValue));
								  	
								  	tr.append($('<td></td>').html('<a href="${pageContext.request.contextPath}/showHtmlViewSpcakeOrder/'+spCakeOrder.spOrderNo+'" target="blank"><i class="fa fa-file-text-o" style="font-size:24px;"></i></a>'));  
								  	

								  	  tr.append($('<td></td>').html('<a href="${pageContext.request.contextPath}/showSpcakeOrderPdf/'+spCakeOrder.spOrderNo+'" target="blank"><i class="fa fa-file-pdf-o" style="font-size:24px;"></i></a>'));  
								  	  tr.append($('<td></td>').html('<a href=# class=action_btn onclick=deleteSpOrder('+spCakeOrder.spOrderNo+');><abbr title=Delete><i class="glyphicon glyphicon-remove"></i></abbr></a>')); 

								
									$('#table1 tbody').append(tr);
									
									})

								});

								}
		
		function inRangePdf()
		{
			var to=document.getElementById("to").value;
			
			var from=document.getElementById("from").value;
			
			if(from==null ||from=="")
			{
			alert("Enter to from");
			}
			else if(to==null ||to=="")
				{
				alert("Enter to no");
				}
			else{

				window.open("${pageContext.request.contextPath}/showSpcakeOrderPdfInRange/"+from+"/"+to);
				
			}
		}
											
	</script>
    <script type="text/javascript">
		function deleteSpOrder(spOrderNo) {
			
			 if (confirm("Do you want to Delete this order?") == true) {
			$.getJSON('${deleteSpOrder}',
					{
			        	sp_order_no:spOrderNo,
						ajax : 'true',
					},
					function(data) {
						$('#table1 td').remove();
						$('#loader').hide();
						if(data==""){
							alert("No Orders Found");
							document.getElementById("expExcel").disabled=true;
						}
						$.each(data,function(key, spCakeOrder) {
							document.getElementById("expExcel").disabled=false;
							document.getElementById('range').style.display = 'block';
							var len=data.length
							
							var tr = $('<tr></tr>');

						  	tr.append($('<td></td>').html(key+1));

						  	tr.append($('<td></td>').html(spCakeOrder.frName));

						  	tr.append($('<td></td>').html(spCakeOrder.itemId));

						  	tr.append($('<td></td>').html(spCakeOrder.spName));

						  	tr.append($('<td></td>').html(spCakeOrder.spfName));
						  	
						  	tr.append($('<td></td>').html(spCakeOrder.spEvents));

						  	tr.append($('<td></td>').html(spCakeOrder.spDeliveryDate));

						  	tr.append($('<td></td>').html(spCakeOrder.spPrice));

						  	tr.append($('<td></td>').html(spCakeOrder.spTotalAddRate));
						  	
							var totalValue=parseFloat(spCakeOrder.spTotalAddRate) + parseFloat(spCakeOrder.spPrice);

						  	tr.append($('<td></td>').html(totalValue));
						  	
						  	tr.append($('<td></td>').html('<a href="${pageContext.request.contextPath}/showHtmlViewSpcakeOrder/'+spCakeOrder.spOrderNo+'" target="blank"><i class="fa fa-file-text-o" style="font-size:24px;"></i></a>'));  
						  	

						  	  tr.append($('<td></td>').html('<a href="${pageContext.request.contextPath}/showSpcakeOrderPdf/'+spCakeOrder.spOrderNo+'" target="blank"><i class="fa fa-file-pdf-o" style="font-size:24px;"></i></a>'));  
						  	  tr.append($('<td></td>').html('<a href=# class=action_btn onclick=deleteSpOrder('+spCakeOrder.spOrderNo+');><abbr title=Delete><i class="glyphicon glyphicon-remove"></i></abbr></a>')); 

						
							$('#table1 tbody').append(tr);
							
							})

					});
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