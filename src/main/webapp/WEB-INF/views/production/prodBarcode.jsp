<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
<body>
	<script>
		$(function() {
			$("#datepicker").datepicker({
				dateFormat : 'dd-mm-yy'
			});
		});
	</script>
	<c:url var="getProductionOrder" value="/getProductionOrderBarcode" />
	<c:url var="getMenu" value="/getMenu" />




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
						<i class="fa fa-file-o"></i>Production Barcode Printing
					</h1>

				</div>
			</div>
			<!-- END Page Title -->



			<!-- BEGIN Main Content -->


			<div class="box">
				<div class="box-title">
					<h3>
						<i class="fa fa-bars"></i> Search Production Order
					</h3>

				</div>

				<div class="box-content">
					<div class="row">


						<div class="form-group col-md-8" align="left">
							<label class=" col-md-3 control-label franchisee_label"></label>
							<label class=" col-md-3 control-label menu_label">Select
								Category </label>
							<div class="col-md-6 controls">

								<select class="form-control chosen"
									data-placeholder="Choose Category" name="selectCategory"
									id="selectCategory" tabindex="-1" data-rule-required="true">


									<c:forEach items="${unSelectedCatList}" var="unSelectedCat"
										varStatus="count">
										<c:choose>

											<c:when
												test="${unSelectedCat.catId==5 || unSelectedCat.catId==3}">
											</c:when>
											<c:otherwise>
												<option value="${unSelectedCat.catId}"><c:out
														value="${unSelectedCat.catName}" /></option>
											</c:otherwise>
										</c:choose>
									</c:forEach>

								</select>
							</div>


						</div>


						<br />
						<div class="form-group col-md-8" align="left">
							<label class=" col-md-3 control-label franchisee_label"></label>
							<label class=" col-md-3 control-label menu_label">Production
								Date</label>
							<div class="col-md-6 controls">
								<input value="${todayDate}" class="form-control date-picker"
									id="datepicker" size="16" type="text" name="production_date"
									required />
							</div>





						</div>
					</div>
					<div class="row" align="center">
						<div class="col-md-12">
							<input type="button" class="btn btn-info" value="Search"
								id="callsearch" onclick="searchOrder()">

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
						<i class="fa fa-table"></i> Production List
					</h3>
					<div class="box-tool">
						<a data-action="collapse" href="#"><i class="fa fa-chevron-up"></i></a>
						<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
					</div>
				</div>


				<form action="submitProductionBarcode" method="post">


					<div class="box-content">
						<div id="table-scroll" class="table-scroll">

							<div class="table-wrap">

								<table id="table1" class="table table-advance">
									<thead>
										<tr class="bgpink">
											<th width="60" style="width: 50px">Sr No</th>
											<th width="90">Item Id</th>
											<th width="150">Item Name</th>
											<th width="100">Production Qty</th>

										</tr>

									</thead>


									<tbody>

									</tbody>

								</table>
							</div>
							<br /> <br />

						</div>

						<div class="row" align="center">
							<div class="col-md-12">
								<input type="submit" class="btn btn-primary"
									value="Download File" disabled id="callSubmit">
									 <input
									type="button" class="btn btn-primary" value="Print"  disabled id="callPrint"
									onclick="print()">
							</div>
						</div>


					</div>




				</form>
			</div>



		</div>
		<!-- END Main Content -->
		<footer>
		<p>2018 © MONGINIS.</p>
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
		$(document)
				.ready(
						function() {
							$('#selectCategory')
									.change(
											function() {
												$
														.getJSON(
																'${getMenu}',
																{
																	selectedCat : $(
																			this)
																			.val(),
																	ajax : 'true'
																},
																function(data) {

																	var len = data.length;
																	$(
																			'#selectMenu')
																			.find(
																					'option')
																			.remove()
																			.end()
																	var html = '<option value="-1">ALL</option>';

																	for (var i = 0; i < len; i++) {

																		html += '<option value="' +data[i].menuId+ '">'
																				+ data[i].menuTitle
																				+ '</option>';

																		/*  $("#selectMenu").append(

																		           $("<option ></option>").attr(

																		               "value", data[i].menuId).text(data[i].menuTitle)

																		       ); */

																	}
																	html += '</option>';
																	$(
																			"#selectMenu")
																			.html(
																					html);

																	$(
																			"#selectMenu")
																			.trigger(
																					"chosen:updated");

																});
											});
						});
	</script>

	<script type="text/javascript">
		function print() {
		
		var oShell = new ActiveXObject("WScript.Shell");
		oShell.Run('D:\\barcode\\print.BAT', 1);
	
	try{
	
		Set objShell = CreateObject("WScript.Shell")
		comspec = objShell.ExpandEnvironmentStrings("%comspec%")
		 
		Set objExec = objShell.Exec(comspec & "D:\\barcode\\apprvlbl.txt")
		    
	}catch (e) {
		alert(e);
	}
	

	return true;
		
		}
		</script>

	<script type="text/javascript">
		function searchOrder() {

			$('#table1 td').remove();

			var autoindex = 0;
			var isValid = validate();

			if (isValid) {

				document.getElementById("callsearch").disabled = true;
				var productionDate = document.getElementById("datepicker").value;
				var selectedCat = $("#selectCategory").val();
				$('#loader').show();

				$.getJSON('${getProductionOrder}', {

					selectedCat : selectedCat,
					productionDate : productionDate,
					ajax : 'true',

				}, function(data) {

					document.getElementById("callsearch").disabled = false;
					$('#loader').hide();

					if (data == "") {
					
						document.getElementById("callSubmit").disabled = true;
						document.getElementById("callPrint").disabled = false;

					}else {
						document.getElementById("callSubmit").disabled = false;
						document.getElementById("callPrint").disabled = false;

						$.each(data, function(key, order) {
							
							var tr = $('<tr></tr>');

							tr.append($('<td></td>').html(key + 1));
							tr.append($('<td></td>').html(order.itemCode));
							tr.append($('<td></td>').html(order.itemName));
							tr.append($('<td></td>').html(order.productionQty));

							$('#table1 tbody').append(tr);

						})
					}

				});

			
			}
		}
	</script>
	<script type="text/javascript">
		function validate() {
			var selectCategory = $("#selectCategory").val();

			var selectOrderDate = $("#datepicker").val();

			var isValid = true;

			if (selectCategory == "" || selectCategory == null) {

				isValid = false;
				alert("Please Select Category");

			} else if (selectOrderDate == "" || selectOrderDate == null) {

				isValid = false;
				alert("Please Select Production Date");
			}
			
			return isValid;

		}

		var specialKeys = new Array();
		specialKeys.push(8);
		function IsNumeric(e) {
			var keyCode = e.which ? e.which : e.keyCode
			var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys
					.indexOf(keyCode) != -1);

			return ret;
		}
	</script>


</body>
</html>