<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body>

	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>


	<c:url var="getItemByCateId" value="/getItemByCateId"></c:url>

	<c:url var="getFrStock" value="/getFrStock"></c:url>


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
					<i class="fa fa-file-o"></i>Franchise Item Stock
				</h1>
				<h4>Stock for franchises</h4>
			</div>
		</div>
		<!-- END Page Title -->

		<!-- BEGIN Breadcrumb -->
		<div id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i> <a
					href="${pageContext.request.contextPath}/home">Home</a> <span
					class="divider"><i class="fa fa-angle-right"></i></span></li>
				<li class="active">Franchise Current Stock</li>
			</ul>
		</div>
		<!-- END Breadcrumb -->

		<!-- BEGIN Main Content -->
		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>Show Franchise Current Stock
				</h3>

			</div>

			<div class="box-content">




				<div class="row">
					<div class="form-group">
						<label class="col-sm-3 col-lg-2 control-label">Select
							Route</label>
						<div class="col-sm-6 col-lg-4 controls">
							<select data-placeholder="Select Route"
								class="form-control chosen" name="selectRoute" id="selectRoute"
								onchange="disableFr()">
								<option value="0">Select Route</option>
								<c:forEach items="${routeList}" var="route" varStatus="count">
									<option value="${route.routeId}"><c:out value="${route.routeName}"/> </option>

								</c:forEach>
							</select>

						</div>

						<label class="col-sm-3 col-lg-2 control-label"><b>OR</b>
							Select Franchisee </label>
						<div class="col-sm-6 col-lg-4">

							<select data-placeholder="Choose Franchisee"
								class="form-control chosen" multiple="multiple" tabindex="6"
								id="selectFr" name="selectFr" onchange="disableRoute()">


								<c:forEach items="${unSelectedFrList}" var="fr"
									varStatus="count">
									<option value="${fr.frId}"><c:out value="${fr.frName}"/></option>
								</c:forEach>
							</select>

						</div>
					</div>
				</div>
				<div class="row">


					<div class="form-group">
						<label class="col-sm-3 col-lg-2	 control-label">Select
							Category</label>
						<div class="col-sm-6 col-lg-4 controls date_select">

							<select data-placeholder="Select Category"
								class="form-control chosen" tabindex="6" name="cat_name"
								id="cat_name">

								<c:forEach items="${ItemIdCategory}" var="catIdName"
									varStatus="count">
									<option value="${catIdName.catId}"><c:out value="${catIdName.catName}"/></option>
								</c:forEach>

							</select>
						</div>

						<!-- </div>

					<div class="form-group  "> -->

						<label class="col-sm-3 col-lg-2	 control-label">Select
							Items</label>
						<div class="col-sm-6 col-lg-4 controls">
							<select data-placeholder="Select Items" name="items[]"
								class="form-control chosen" tabindex="-1" id="items"
								multiple="multiple" data-rule-required="true">

							</select>
						</div>
					</div>

				</div>


				<br> <br>
				<div class="row">
					<div class="col-md-12" style="text-align: center;">
						<button class="btn btn-info" id="getStockButton" onclick="getStockInfo()">Get
							Stock</button>


					</div>
				</div>


				<div align="center" id="loader" style="display: none">

					<span>
						<h4>
							<font color="#343690">Loading</font>
						</h4>
					</span> <span class="l-1"></span> <span class="l-2"></span> <span
						class="l-3"></span> <span class="l-4"></span> <span class="l-5"></span>
					<span class="l-6"></span>
				</div>

			</div>
		</div>

		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-list-alt"></i>Stock
				</h3>

			</div>

			<form id="submitBillForm"
				action="${pageContext.request.contextPath}/submitNewBill"
				method="post">
				<div class=" box-content">
								<div class="row">
							<div class="col-md-12 table-responsive">
							<div style="overflow:scroll;height:100%;width:100%;overflow:auto">
							
								<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
									<thead>
										<tr>
											<!-- <th>Sr.No.</th>
											<th>Franchisee Name</th>
											<th>Category</th>
											<th>Item Name</th>
											<th>Bill Qty</th> -->
											
										</tr>
									</thead>
									<tbody>

									</tbody>
								</table>
							</div>
							</div>
							<div class="form-group" style="display: none;" id="range">
								 
											 
											 
											<div class="col-sm-3  controls">
											 <input type="button" id="expExcel" class="btn btn-primary" value="EXPORT TO Excel" onclick="exportToExcel();" disabled="disabled">
											</div>
											</div>
						</div>

					<div class="row">
						<div class="col-md-offset-6 col-md-6">
							<!-- 							<button class="btn btn-info pull-right">Submit & PDF</button>
 -->
							<%-- <a href="${pageContext.request.contextPath}/pdf?url=showBillPdf"
								target="_blank">PDF</a> --%>

						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- END Main Content -->

	<footer>
		<p>2017 © Monginis.</p>
	</footer>

	<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
		class="fa fa-chevron-up"></i></a>

	<script type="text/javascript">
$(document).ready(function() {
	
    $('#cat_name').change(
            function() {
            	
                $.getJSON('${getItemByCateId}', {
                    catId : $(this).val(),
                    ajax : 'true'
                }, function(data) {
                
                    var len = data.length;

					$('#items')
				    .find('option')
				    .remove()
				    .end()
				// $("#items").append($("<option></option>").attr( "value",-1).text("ALL"));
                    for ( var i = 0; i < len; i++) {
                            
                                
                        $("#items").append(
                                $("<option></option>").attr(
                                    "value", data[i].id).text(data[i].itemName)
                            );
                    }

                    $("#items").trigger("chosen:updated");
                });
            });
});
</script>




	<script type="text/javascript">
		function getStockInfo() {
	//	alert("Hi ");
			document.getElementById("getStockButton").disabled=true;

	
				 var selectedFr = $("#selectFr").val();
					//alert("selectedFr " +selectedFr);
					 var catId=$("#cat_name").val();
					//alert("catId " +catId);
				
			 	var routeId=$("#selectRoute").val();
				var selectedItems = $("#items").val();
				//alert("selectedItems " +selectedItems);
				
				$('#loader').show();

				$
						.getJSON(
								'${getFrStock}',

								{
									fr_id_list : JSON.stringify(selectedFr),
									item_id_list : JSON.stringify(selectedItems),
									route_id : routeId,
									cat_id : catId,
									ajax : 'true'

								},
								function(data) {
									$('#table_grid th').remove();

									$('#table_grid td').remove();
								//	alert("Hi");
									
									$('#loader').hide();
/*                                     var frListLength=data.frList.length;
 * 
 */
 							if (data == "") {
										alert("No records found !!");
									}
									
									 var tr;
								        tr = document.getElementById('table_grid').tHead.children[0];
								        tr.insertCell(0).outerHTML = "<th align='left'>Sr.No.</th>"

								        tr.insertCell(1).outerHTML = "<th style='width=130px'>Item Name</th>"
								        	var i=0;var j=0;
								        	 $.each(data.frIdNamesList, function(key,fr){  
								        	       i=key+2;
								                 tr.insertCell(i).outerHTML = "<th>"+fr.frName+"</th>"
								         });//franchise for end    
								         //tr.insertCell(i+1).outerHTML = "<th style='font-weight:bold'>Stock</th>"
								        	 $(
												'#table_grid tbody')
												.append(
														tr);
								         
								             	var srNo = 0;
													$.each(data.itemList,
																	function(key,item) {
													//alert(item.itemName)
														srNo = srNo + 1;
														var tr = $('<tr></tr>');
														tr
																.append($(
																		'<td></td>')
																		.html(
																				srNo));
														tr
																.append($(
																		'<td></td>')
																		.html(
																				item.itemName));
														 $.each(data.frIdNamesList, function(key,franchise){  
																//alert(franchise.frId)

															tr
															.append($(
																	'<td></td>')
																	.html("<input type=text style='text-align:right; width:70px' class=form-control name=itemQty"+franchise.frId+""+item.itemId+" id=itemQty"+franchise.frId+""+item.itemId+" value=0 disabled/>"));
														});
														
														$(
																'#table_grid tbody')
																.append(
																		tr);
														
													});//itemList for end
								         
													//	var srNo = 0;
														 $.each(data.currentStockDetailList,
																		function(key,report) {
                                                                
													         document.getElementById('itemQty'+report.frId+''+report.itemId).value = report.regCurStock;

															       

																		});
														
													     	});	
																		 
																		
																
								        	 

		}

		
		</script>


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
</body>
</html>