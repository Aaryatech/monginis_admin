<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>


	<c:url var="getBillList" value="/generateNewBill"></c:url>

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
					<i class="fa fa-file-o"></i>Franchisee FR Invoice
				</h1>
				<h4>Bill for franchises</h4>
			</div>
		</div>
		<!-- END Page Title -->

		<!-- BEGIN Breadcrumb -->
		<div id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i> <a
					href="${pageContext.request.contextPath}/home">Home</a> <span
					class="divider"><i class="fa fa-angle-right"></i></span></li>
				<li class="active">Franchise Bill</li>
			</ul>
		</div>
		<!-- END Breadcrumb -->

		<!-- BEGIN Main Content -->
		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>Generate Franchisee Bill
				</h3>

			</div>

			<div class="box-content">
				<div class="row">


					<div class="form-group">
						<label class="col-sm-3 col-lg-2	 control-label">Delivery
							Date</label>
						<div class="col-sm-6 col-lg-4 controls date_select">
							<input class="form-control date-picker" id="deliveryDate"
								name="deliveryDate" size="30" type="text" value="${todaysDate}" />
						</div>

						<!-- </div>

					<div class="form-group  "> -->

						<label class="col-sm-3 col-lg-2	 control-label">Select
							Menu</label>
						<div class="col-sm-6 col-lg-4 controls">
							<select data-placeholder="Choose Menu"
								class="form-control chosen" multiple="multiple" tabindex="6"
								id="selectMenu" name="selectMenu">

								<option value="-1"><c:out value="All"/></option>

								<c:forEach items="${unSelectedMenuList}" var="unSelectedMenu"
									varStatus="count">
									<option value="${unSelectedMenu.menuId}"><c:out value="${unSelectedMenu.menuTitle}"/></option>
								</c:forEach>


							</select>
						</div>
					</div>

				</div>


				<br>

				<!-- <div class="col-sm-9 col-lg-5 controls">
 -->
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

						<label class="col-sm-3 col-lg-2 control-label"><b>OR</b> Select
							Franchisee </label>
						<div class="col-sm-6 col-lg-4">

							<select data-placeholder="Choose Franchisee"
								class="form-control chosen" multiple="multiple" tabindex="6"
								id="selectFr" name="selectFr" onchange="disableRoute()">

								<option value="-1"><c:out value="All"/></option>

								<c:forEach items="${unSelectedFrList}" var="fr"
									varStatus="count">
									<option value="${fr.frId}"><c:out value="${fr.frName}"/></option>
								</c:forEach>
							</select>

						</div>
					</div>
				</div>


				<%-- <div class="form-group col-md-9">
					<label class=" col-md-2">Select
						Franchise </label>
					<div class=" col-md-7">
						<select data-placeholder="Choose Franchisee"
							class="form-control chosen " multiple="multiple" tabindex="6"
							id="selectFr" name="selectFr">

							<option value="-1"><c:out value="All"/></option>

							<c:forEach items="${unSelectedFrList}" var="fr" varStatus="count">
								<option value="${fr.frId}"><c:out value="${fr.frName}"/></option>
							</c:forEach>
						</select>
					</div>

				</div> --%>


				<br>
				<div class="row">
					<div class="col-md-12" style="text-align: center;">
						<button class="btn btn-info" onclick="generateNewBill()">Search
							Bill</button>


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
					<i class="fa fa-list-alt"></i>Bill
				</h3>

			</div>

			<form id="submitBillForm"
				action="${pageContext.request.contextPath}/submitNewBill"
				method="post">
				<div class=" box-content">
					<div class="row">
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Franchise Name</th>
										<th>Menu Name</th>
										<th>Item Name</th>
										<th>Order Qty</th>
										<th>Bill Qty</th>
										<th>Base Rate</th>
										<th>Amount</th>
										<th>Tax %</th>
										<th>SGST Rs</th>
										<th>CGST Rs</th>
										<th>IGST Rs</th>
										<th>Total</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
					</div>



					<div class="row">
						<div class="col-md-offset-6 col-md-6">
<!-- 							<button class="btn btn-info pull-right">Submit & PDF</button>
 -->
							<%-- <a href="${pageContext.request.contextPath}/pdf?url=showBillPdf"
								target="_blank">PDF</a> --%>
							<button class="btn btn-info pull-right"
								style="margin-right: 5px;" onclick="submitBill()">Submit
								Bill</button>
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
			function submitBill() {

				//submitBillForm.submit();
				 //window.open("${pageContext.request.contextPath}/pdf?url=showBillPdf");
				 
				// window.open("${pageContext.request.contextPath}/showBillListForPrint");
			}
		</script>


	<script type="text/javascript">
		function generateNewBill() {

			var isValid = validate();

			if (isValid) {
				var selectedFr = $("#selectFr").val();
				var routeId=$("#selectRoute").val();
				
				var selectedMenu = $("#selectMenu").val();
				var deliveryDate = $("#deliveryDate").val();

				$('#loader').show();

				$
						.getJSON(
								'${getBillList}',

								{
									fr_id_list : JSON.stringify(selectedFr),
									menu_id_list : JSON.stringify(selectedMenu),
									deliveryDate : deliveryDate,
									route_id:routeId,
									ajax : 'true'

								},
								function(data) {

									$('#table_grid td').remove();
									$('#loader').hide();

									if (data == "") {
										alert("No records found !!");

									}

									$
											.each(
													data,
													function(key, bill) {
														
														if(bill.orderQty>0){

														var index = key + 1;

														var tr = "<tr>";

														var index = "<td>&nbsp;&nbsp;&nbsp;"
																+ index
																+ "</td>";

														var frName = "<td>&nbsp;&nbsp;&nbsp;"
																+ bill.frName
																+ "</td>";

														var menuTitle = "<td>&nbsp;&nbsp;&nbsp;"
																+ bill.menuTitle
																+ "</td>";

														/* var itemId = "<td>&nbsp;&nbsp;&nbsp;"
																+ bill.itemId
																+ "</td>"; */

														var itemName = "<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
																+ bill.itemName
																+ "</td>";

														var orderQty = "<td align=center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
																+ bill.orderQty
																+ "</td>";

														 var billQty = "<td align=center><input type=number min=0 max=500 style='width: 5em' class=form-control   onkeyup= updateTotal("
																+ bill.orderId + ","
																+ bill.orderRate + ") onchange= updateTotal("+ bill.orderId+ ","+ bill.orderRate+ ")  id= billQty"+ bill.orderId+ " name=billQty"+bill.orderId+" value = "+ bill.orderQty+ "></td>"; 
																
																//var billQty = "<td align=center><input name=newId id=newId value=21 type=number ></td>";

															var baseRateAmt	;
														if(bill.isSameState==1)	{
														 baseRateAmt=(bill.orderRate*100)/(100+bill.itemTax1+bill.itemTax2);	
														}
														else{
															baseRateAmt=(bill.orderRate*100)/(100+bill.itemTax3);	
														}
																
														//var baseRateAmt=(bill.orderRate*100)/(100+bill.itemTax1+bill.itemTax2);
														//alert("base Rate Amt ="+baseRateAmt);
														baseRateAmt=baseRateAmt.toFixed(2);
														var baseRate = "<td align=center>&nbsp;&nbsp;&nbsp;"
															+ baseRateAmt+ "</td>";
															
														/* var orderRate = "<td align=center id=billRate"+bill.orderId+"  value="
																+ bill.orderRate
																+ ">"
																+ bill.orderRate
																+ "</td>" */;
																var t1=parseFloat(bill.itemTax1);
																var t2=parseFloat(bill.itemTax2);
																var t3=parseFloat(bill.itemTax3);
																//alert("taxes ="+t1+"-"+t2+"-"+t3);

																var taxableAmt= baseRateAmt * bill.orderQty;
																
																taxableAmt=taxableAmt.toFixed(2);
																//var taxableAmount = "<td align=center"+taxableAmt+">"+"</td>";
																var taxableAmount ="<td align=center>&nbsp;&nbsp;&nbsp;"
																+ taxableAmt+ "</td>";
																//alert("taxable amt "+taxableAmt);
																
																var sgstRS=0;
																var cgstRS=0;
																var igstRS=0;
																var totalTax=0;
																if(bill.isSameState==1)	{
																	
																	 sgstRS=(t1*taxableAmt)/100;
																	 cgstRS=(t2*taxableAmt)/100;
																	 igstRS=0;
																	 
																	 totalTax=sgstRS+cgstRS;
																}
																else{
																	
																	 sgstRS=0;
																	 cgstRS=0;
																	 igstRS=(t3*taxableAmt)/100;
																	 totalTax=igstRS;
																}
																//var sgstRS=(t1*taxableAmt)/100;
																//var cgstRS=(t2*taxableAmt)/100;
																//var igstRS=(t3*taxableAmt)/100;
																sgstRS=sgstRS.toFixed(2);
																cgstRS=cgstRS.toFixed(2);
																igstRS=igstRS.toFixed(2);
																
																//alert("rs 1"+sgstRS);
																//alert("rs 2 "+cgstRS);
																//alert("rs 3 "+igstRS);
																//var totalTax=sgstRS+cgstRS+igstRS;
																//alert(totalTax);

																var sgst = "<td align=center>&nbsp;&nbsp;&nbsp;"
																	+ sgstRS+ "</td>";

																var cgst = "<td align=center>&nbsp;&nbsp;&nbsp;"
																	+ cgstRS+ "</td>";
																var igst ="<td align=center>&nbsp;&nbsp;&nbsp;"
																	+ igstRS+ "</td>";
																var totTaxP;
																
																if(bill.isSameState==1)	{
																	 totTaxP=t1+t2;
																	
																}else{
																	
																	totTaxP=t3;
																}
																
																var totTaxPer = "<td align=center>&nbsp;&nbsp;&nbsp;"
																	+ totTaxP+ "</td>";
																
																	
														var total = parseFloat(taxableAmt)+parseFloat(totalTax);
																
														total=total.toFixed(2);
														
														var totaLBill = "<td align=center id=billTotal"+bill.orderId+">"
																+ total
																+ "</td>";

														var trclosed = "</tr>";

														$('#table_grid tbody')
																.append(tr);
														$('#table_grid tbody')
																.append(index);
														$('#table_grid tbody')
																.append(frName);
														$('#table_grid tbody')
																.append(
																		menuTitle);
														/* $('#table_grid tbody')
																.append(itemId); */
														$('#table_grid tbody')
																.append(
																		itemName);
														$('#table_grid tbody')
																.append(
																		orderQty);
														$('#table_grid tbody')
																.append(billQty);
														$('#table_grid tbody')
																.append(
																		baseRate);
														$('#table_grid tbody')
														.append(
																taxableAmount);
														 $('#table_grid tbody')
															.append(
																	totTaxPer); 
														 
														 $('#table_grid tbody')
															.append(
																	sgst); 
														 
														 $('#table_grid tbody')
															.append(
																	cgst); 
														 
														 $('#table_grid tbody')
															.append(
																	igst); 
														 
													
														 
														
														$('#table_grid tbody')
																.append(
																		totaLBill);

														$('#table_grid tbody')
																.append(
																		trclosed);
														
													}

													})

								});

			}
		}
	</script>

	<script type="text/javascript">
		function validate() {

			var selectedFr = $("#selectFr").val();
			var selectedMenu = $("#selectMenu").val();
			var selectedRoute = $("#selectRoute").val();


			var isValid = true;

			if (selectedFr == "" || selectedFr == null  ) {
 
				if(selectedRoute=="" || selectedRoute ==null ) {
					alert("Please Select atleast one ");
					isValid = false;
				}
				//alert("Please select Franchise/Route");
 
			} else if (selectedMenu == "" || selectedMenu == null) {

				isValid = false;
				alert("Please select Menu");

			}
			return isValid;

		}
	</script>

	<script type="text/javascript">
		function updateTotal(orderId, rate) {
			
			var newQty = $("#billQty" + orderId).val();

			var total = parseFloat(newQty) * parseFloat(rate);


			 $('#billTotal'+orderId).html(total);
		}
	</script>

	<script>
$('.datepicker').datepicker({
    format: {
        /*
         * Say our UI should display a week ahead,
         * but textbox should store the actual date.
         * This is useful if we need UI to select local dates,
         * but store in UTC
         */
    	 format: 'mm/dd/yyyy',
    	    startDate: '-3d'
    }
});

</script>

	<script type="text/javascript">

function disableFr(){

	//alert("Inside Disable Fr ");
document.getElementById("selectFr").disabled = true;

}

function disableRoute(){

	//alert("Inside Disable route ");
	var x=document.getElementById("selectRoute")
	//alert(x.options.length);
	var i;
	for(i=0;i<x;i++){
		document.getElementById("selectRoute").options[i].disabled;
		 //document.getElementById("pets").options[2].disabled = true;
	}
//document.getElementById("selectRoute").disabled = true;

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