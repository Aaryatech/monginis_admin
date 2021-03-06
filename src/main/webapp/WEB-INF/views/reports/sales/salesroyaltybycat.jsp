<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<c:url var="getBillList" value="/getSaleRoyaltyByCat"></c:url>
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
					<i class="fa fa-file-o"></i>Royalty Report By Category
				</h1>
				<h4></h4>
			</div>
		</div>
		<!-- END Page Title -->

		<!-- BEGIN Breadcrumb -->
		<%-- <div id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i> <a
					href="${pageContext.request.contextPath}/home">Home</a> <span
					class="divider"><i class="fa fa-angle-right"></i></span></li>
				<li class="active">Bill Report</li>
			</ul>
		</div> --%>
		<!-- END Breadcrumb -->

		<!-- BEGIN Main Content -->
		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>View Sales Royalty by Category
				</h3>

			</div>

			<div class="box-content">
				<div class="row">


					<div class="form-group">
						<label class="col-sm-3 col-lg-2	 control-label">From Date</label>
						<div class="col-sm-6 col-lg-4 controls date_select">
							<input class="form-control date-picker" id="fromDate"
								name="fromDate" size="30" type="text" value="${todaysDate}" />
						</div>

						<!-- </div>

					<div class="form-group  "> -->

						<label class="col-sm-3 col-lg-2	 control-label">To Date</label>
						<div class="col-sm-6 col-lg-4 controls date_select">
							<input class="form-control date-picker" id="toDate" name="toDate"
								size="30" type="text" value="${todaysDate}" />
						</div>
					</div>

				</div>


				<br>

				
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

						<label class="col-sm-3 col-lg-2 control-label"><b>OR</b>Select
							Franchisee</label>
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

				<br>
				<div class="row">
					<div class="col-md-12" style="text-align: center;">
						<button class="btn btn-info" onclick="searchReport()">Search
							 Report</button>
							
							
							<button class="btn btn-primary" value="PDF" id="PDFButton"
							onclick="genPdf()">PDF</button>
							
						<%-- 	<a href="${pageContext.request.contextPath}/pdfForReport?url=showSaleRoyaltyByCatPdf"
								target="_blank">PDF</a>
 --%>
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
					<i class="fa fa-list-alt"></i>Royalty  Report (R5)
				</h3>

			</div>

			<form id="submitBillForm"
				action="${pageContext.request.contextPath}/submitNewBill"
				method="post">
				<div class="box-content">
					<div class="row">
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Item Name</th>
										<th>Sale Qty</th>
										<th>Sale Value</th>
										<th>GRN Qty</th>
										<th>GRN Value</th>
										<th>GVN Qty</th>
										<th>GVN Value</th>
										<th>Net Qty</th>
										<th>Net Value</th>
										<th>Royalty %</th>
										<th>Royalty Amt</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
						<div class="form-group" style="display: none;" id="range">
								 
											 
											 
											<div class="col-sm-3  controls">
											
											 <input type="button" id="expExcel" class="btn btn-primary" value="EXPORT TO Excel" onclick="exportToExcel();" disabled="disabled">
											</div>
											</div>
					</div>

				</div>
			</form>
		</div>
	
	<!-- END Main Content -->

	<footer>
	<p>2020 © Monginis.</p>
	</footer>

	<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
		class="fa fa-chevron-up"></i></a>
</div></div>

	<script type="text/javascript">
		function searchReport() {
		//	var isValid = validate();

				var selectedFr = $("#selectFr").val();
				var routeId=$("#selectRoute").val();
				
				var from_date = $("#fromDate").val();
				var to_date = $("#toDate").val();
				
				var ttlSalQty = 0;
				var ttlSalVal = 0;
				var ttlBillTaxable = 0;
				var ttlGrnTaxable = 0;
				var ttlGrnQty = 0;
				var ttlGvnTaxable = 0;
				var ttlGvnQty = 0;
				var ttlNetQty = 0;
				var ttlNetVal = 0;
				var ttlRoyalty= 0;

				$('#loader').show();

				$
						.getJSON(
								'${getBillList}',

								{
									fr_id_list : JSON.stringify(selectedFr),
									fromDate : from_date,
									toDate : to_date,
									route_id:routeId,
									ajax : 'true'

								},
								function(data) {

									$('#table_grid td').remove();
									$('#loader').hide();
									
									var royPer=${royPer};
									//alert(royPer);

									if (data == "") {
										alert("No records found !!");
										  document.getElementById("expExcel").disabled=true;

									}

									$
									.each(
											data.categoryList,
											function(key, cat) {
												  document.getElementById("expExcel").disabled=false;
													document.getElementById('range').style.display = 'block';

												var tr = $('<tr></tr>');
												tr.append($('<td colspan="0"></td>').html(cat.catName));
											  	//tr.append($('<td></td>').html(key+1));
											  	tr.append($('<td></td>').html(""));
											  	tr.append($('<td></td>').html(""));
											  	tr.append($('<td></td>').html(""));
											  	
											  	tr.append($('<td></td>').html(""));
												tr.append($('<td></td>').html(""));
											  	tr.append($('<td></td>').html(""));
											  	tr.append($('<td></td>').html(""));
											  	tr.append($('<td></td>').html(""));
											  	tr.append($('<td></td>').html(""));

												$('#table_grid tbody')
														.append(
																tr);
												
									
												var srNo=0;
									$
											.each(
													data.salesReportRoyalty,
													function(key, report) {
														
														if(cat.catId==report.catId){
														//alert("Hi");
														 srNo=srNo+1;
														//var index = key + 1;
														 var tr = $('<tr></tr>');
														//tr.append($('<td></td>').html(cat.catName));
													  	tr.append($('<td></td>').html(srNo));
													  	tr.append($('<td></td>').html(report.item_name));
													  	tr.append($('<td style="text-align:right;"></td>').html(report.tBillQty));
													  	tr.append($('<td style="text-align:right;"></td>').html(report.tBillTaxableAmt));
													  	
													  	tr.append($('<td style="text-align:right;"></td>').html(report.tGrnQty));
														tr.append($('<td style="text-align:right;"></td>').html(report.tGrnTaxableAmt));
													  	tr.append($('<td style="text-align:right;"></td>').html(report.tGvnQty));
													  	tr.append($('<td style="text-align:right;"></td>').html(report.tGvnTaxableAmt));
													  	
													  	var netQty=report.tBillQty-(report.tGrnQty+report.tGvnQty);
													  	netQty=netQty.toFixed(2);
													  	
														var netValue=report.tBillTaxableAmt-(report.tGrnTaxableAmt+report.tGvnTaxableAmt);
														netValue=netValue.toFixed();
														
													  	tr.append($('<td style="text-align:right;"></td>').html(netQty));
													  	tr.append($('<td style="text-align:right;"></td>').html(netValue));
													  	
													  	tr.append($('<td style="text-align:right;"></td>').html(royPer));
													  	
													  	rAmt=netValue*royPer/100;
													  	rAmt=rAmt.toFixed(2);
													  	
													  	tr.append($('<td style="text-align:right;"></td>').html(rAmt));
													  	
														$('#table_grid tbody')
																.append(
																		tr);
														
														ttlSalQty = ttlSalQty + parseFloat(report.tBillQty);	
														ttlSalVal = ttlSalVal + parseFloat(report.tBillTaxableAmt);
														
														ttlGrnQty = ttlGrnQty+parseFloat(report.tGrnQty);
														ttlGrnTaxable = ttlGrnTaxable + parseFloat(report.tGrnTaxableAmt);
														
														ttlGvnQty = ttlGvnQty+parseFloat(report.tGvnQty);
														ttlGvnTaxable = ttlGvnTaxable + parseFloat(report.tGvnTaxableAmt);
														
														ttlNetQty = ttlNetQty+parseFloat(netQty);
														ttlNetVal = ttlNetVal + parseFloat(netValue);
														
														ttlRoyalty = ttlRoyalty + parseFloat(rAmt);
														 
														}//end of if
														
													})
													
											})
											var trr =$('<tr></tr>');
									trr.append($('<td style="text-align:center; font-weight: 700;"></td>').html('Total'));
									trr.append($('<td></td>').html(''));
									
									trr.append($('<td style="text-align:right; font-weight: 700;"></td>').html(addCommas(ttlSalQty.toFixed(2))));
									trr.append($('<td style="text-align:right; font-weight: 700;"></td>').html(addCommas(ttlSalVal.toFixed(2))));
									trr.append($('<td style="text-align:right; font-weight: 700;"></td>').html(addCommas(ttlGrnQty.toFixed(2))));
									trr.append($('<td style="text-align:right; font-weight: 700;"></td>').html(addCommas(ttlGrnTaxable.toFixed(2))));
									
									trr.append($('<td style="text-align:right; font-weight: 700;"></td>').html(addCommas(ttlGvnQty.toFixed(2))));
									trr.append($('<td style="text-align:right; font-weight: 700;"></td>').html(addCommas(ttlGvnTaxable.toFixed(2))));
									
									trr.append($('<td style="text-align:right; font-weight: 700;"></td>').html(addCommas(ttlNetQty.toFixed(2))));
									trr.append($('<td style="text-align:right; font-weight: 700;"></td>').html(addCommas(ttlNetVal.toFixed(2))));
									
									trr.append($('<td style="text-align:right; font-weight: 700;"></td>').html(''));													
									trr.append($('<td style="text-align:right; font-weight: 700;"></td>').html(addCommas(ttlRoyalty.toFixed(2))));
									
									$('#table_grid tbody')
									.append(trr); 

								});

			
		}
	</script>
<script type="text/javascript">
function addCommas(x){

	x=String(x).toString();
	 var afterPoint = '';
	 if(x.indexOf('.') > 0)
	    afterPoint = x.substring(x.indexOf('.'),x.length);
	 x = Math.floor(x);
	 x=x.toString();
	 var lastThree = x.substring(x.length-3);
	 var otherNumbers = x.substring(0,x.length-3);
	 if(otherNumbers != '')
	     lastThree = ',' + lastThree;
	 return otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree + afterPoint;
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


function genPdf()
{
	var from_date = $("#fromDate").val();
	var to_date = $("#toDate").val();
	var selectedFr = $("#selectFr").val();
	var routeId=$("#selectRoute").val();
	
	window.open('pdfForReport?url=pdf/showSaleRoyaltyByCatPdf/'+from_date+'/'+to_date+'/'+selectedFr+'/'+routeId+'/');
	
}
function exportToExcel()
{
	 
	window.open("${pageContext.request.contextPath}/exportToExcelNew");
			document.getElementById("expExcel").disabled=true;
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