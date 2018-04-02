<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
 	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>


	<c:url var="getMenuForFr" value="/getMenuListByFr"></c:url>
	<c:url var="getItemListByMenu" value="/getItemListById"></c:url>
 


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
					<i class="fa fa-file-o"></i>Franchise Opening Stock
				</h1>
				<h4>Franchise Opening Stock Configuration</h4>
			</div>
		</div>
		<!-- END Page Title -->

		<!-- BEGIN Breadcrumb -->
		<div id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i> <a
					href="${pageContext.request.contextPath}/home">Home</a> <span
					class="divider"><i class="fa fa-angle-right"></i></span></li>
				<li class="active">Sell Transaction<span
					class="divider"><i class="fa fa-angle-right"></i></span></li>	
				<li class="active">Franchise Opening Stock</li>
			</ul>
		</div>
		<!-- END Breadcrumb -->

		<!-- BEGIN Main Content -->
		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>Menu
				</h3>

			</div>

			<div class="box-content">
				<div class="row">
					<div class="form-group col-md-9">
						<label class=" col-md-2 control-label franchisee_label">Select
							Franchise </label>
						<div class=" col-md-7 controls franchisee_select">
							<select class="form-control chosen " tabindex="6" id="selectFr"
								name="selectFr" onchange="getMenu()">

								<option value="-1">Select Franchisee</option>
								<c:forEach items="${frList}" var="fr" varStatus="count">
									<option value="${fr.frId}"><c:out value="${fr.frName}"/></option>
								</c:forEach>

							</select>
						</div>
					</div>

					<div class="form-group col-md-9">
						<label class=" col-md-2 control-label menu_label">Select
							Menu</label>
						<div class=" col-md-5 controls menu_select">

							<select data-placeholder="Choose Menu"
								class="form-control chosen" tabindex="6" id="selectMenu"
								name="selectMenu">
							</select>
						</div>
					</div>

					<div class="form-group col-md-9">
					<div align="center" id="loader" style="display: none">

						<span>
							<h4>
								<font color="#343690">Loading</font>
							</h4>
						</span> <span class="l-1"></span> <span class="l-2"></span> <span
							class="l-3"></span> <span class="l-4"></span> <span class="l-5"></span>
						<span class="l-6"></span>
					</div>
						<div class="row" align="left">
							<div class="col-md-12" style="text-align: center">
								<button class="btn btn-primary" onclick="getItems()">Search</button>

							</div>

						</div>
					</div>

				</div>
			</div>
        </div>
			<div class="box">
				<div class="box-title">
					<h3>
						<i class="fa fa-list-alt"></i>Opening Stock
					</h3>

				</div>

				<form id="openingStockForm"
					action="${pageContext.request.contextPath}/saveFrOpeningStockProcess"
					method="post">
					<div class=" box-content">
						<div class="row">
							<div class="col-md-12 table-responsive">
								<table class="table table-bordered table-striped fill-head "
									style="width:50%" id="table_grid" align="left">
									<thead>
										<tr>
											<th width="10">Sr.No.</th>
											<th width="40">Item Id</th>
											<th width="200">Item Name</th>
											<th width="50">Opening Quantity</th>

										</tr>
									</thead>
									<tbody>

									</tbody>
								</table>
							</div>
						</div>
					
						<div class="row">
							<div class="col-md-offset-6 col-md-6">
								<button  id="submitStock"  style="display:none;" class="btn btn-info pull-right"
									style="margin-right: 5px;" onclick="submitForm()">Submit</button>
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
				function getMenu() {
					var selectedFr = $("#selectFr").val();
				//alert(selectedFr);
					
					$.getJSON('${getMenuForFr}', {
						fr_id: selectedFr,
						ajax : 'true'
					}, function(data) {
						//alert(data);
						var len = data.length;
						
						$('#selectMenu')
					    .find('option')
					    .remove()
					    .end()
					    
			
						 $("#selectMenu").append(
	                                $("<option></option>").attr(
	                                    "value",'-1').text('Select Menu')
	                            );		
								
						for ( var i = 0; i < len; i++) {
							
					          
	                        $("#selectMenu").append(
	                                $("<option></option>").attr(
	                                    "value", data[i].menuId).text(data[i].menuTitle)
	                            );
						}
					 
						   $("#selectMenu").trigger("chosen:updated");
						 
					});
				}
			</script>









		<script type="text/javascript">
				function getItems() {

					
					var selectedMenu = $("#selectMenu").val();
					$('#loader').show();
					
					$.getJSON('${getItemListByMenu}', {
						menu_id: selectedMenu,
						ajax : 'true'
					}, function(data) {
						
						var len = data.length;
					
						
						$('#table_grid td').remove();
						$('#loader').hide();
						
						if (data == "" || data==null) {
							alert("No Items found !!");
							$('#submitStock').hide();
						}else{
						$.each(data,function(key, item) {
							
							var index = key + 1;

							var tr = "<tr>";

							var index = "<td>&nbsp;&nbsp;&nbsp;"
									+ index
									+ "</td>";

							var itemCode = "<td>&nbsp;&nbsp;&nbsp;"
									+ item.itemCode
									+ "</td>";

							var itemName = "<td>&nbsp;&nbsp;&nbsp;"
									+ item.itemName
									+ "</td>";

						

							var itemStockQty = "<td align=center><input type=number min=0 max=500 class=form-control   id= stockQty"
									+ item.itemId
									+ " name=stockQty"
									+ item.itemId
									+ " value = "
									+ item.regOpeningStock
									+ "></td>";



							var trclosed = "</tr>";

							$('#table_grid tbody').append(tr);
							$('#table_grid tbody').append(index);
							
							$('#table_grid tbody').append(itemCode);
							$('#table_grid tbody').append(itemName);
							$('#table_grid tbody').append(itemStockQty);
							
							$('#table_grid tbody').append(trclosed);
							$('#submitStock').show();
						
															
								});
						
						}
					
					});
				}
			</script>

		

		<script type="text/javascript">
				function validate() {

				
					var selectedMenu = $("#selectMenu").val();

					var isValid = true;

					if (selectedFr == "-1" || selectedFr == null) {

						isValid = false;
						alert("Please select Franchise");

					} else if (selectedMenu == "" || selectedMenu == null) {

						isValid = false;
						alert("Please select Menu");

					}
					return isValid;

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