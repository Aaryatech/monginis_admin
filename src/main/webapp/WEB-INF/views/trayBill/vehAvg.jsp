<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	
	<style>
	.buttonload {
    background-color: transparent; /* Green background */
    border: none;  /*Remove borders */
    color: #ec268f; /* White text */
  /*   padding: 12px 20px; /* Some padding */ */
    font-size: 15px; /* Set a font-size */
    display: none;
}
	</style>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<c:url var="getVehicleAvg" value="/getVehicleAvg"></c:url>
	<c:url var="updateVehDetailByAdminJSP" value="/updateVehDetailByAdmin"></c:url>
	
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
					<i class="fa fa-file-o"></i>Report
				</h1>
				<h4></h4>
			</div>
		</div>
		<!-- END Page Title -->

		<!-- BEGIN Breadcrumb -->
		<div id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i> <a
					href="${pageContext.request.contextPath}/home">Home</a> <span
					class="divider"><i class="fa fa-angle-right"></i></span></li>
				<li class="active">Bill Report</li>
			</ul>
		</div>
		<!-- END Breadcrumb -->

		<!-- BEGIN Main Content -->
		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>Vehicle Avg. Report
				</h3>

			</div>

			<div class="box-content">
				<div class="row">
						<label class="col-sm-3 col-lg-2	 control-label"> Date</label>
						<div class="col-sm-6 col-lg-2 controls date_select">
							<input class="form-control date-picker" id="date" placeholder="dd-mm-yyyy"
								name="date" size="30" type="text" />
						</div>

					<div class="col-md-2">
						<button class="btn btn-info" onclick="searchReport()">Search</button>
					<button class="btn btn-primary" value="PDF" id="PDFButton" onclick="genPdf()" disabled>PDF</button>
					</div>
					
					<button class="buttonload" id="loader1">
                                   <i class="fa fa-spinner fa-spin"></i>
                                   </button>
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
					<i class="fa fa-list-alt"></i>Vehicle Avg. Report
				</h3>

			</div>

		
				<div class=" box-content">
					<div class="row">
						<div class="col-md-12 table-responsive">
							<table class="w3-table w3-bordered"
								style="width: 100%" id="table_grid">
								<thead>
									<tr bgcolor=#5ab4da style="color:white;">
										<th>Sr.No.</th>
										<th>Vehicle No</th>
										<th>Driver Name</th>
										<th>Route</th>
										<th>Out Kms.</th>
										<th>In Kms.</th>
										<th>Running Kms.</th>
										<th>Diesel</th>
										<th>Actual Avg.</th>
										<th>Minimum Avg.</th>
								    	<th>Standard Avg.</th>
									
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
		</div>

	<!-- END Main Content -->

	<footer>
	<p>2018 © Monginis.</p>
	</footer>

	<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
		class="fa fa-chevron-up"></i></a>
</div></div>

	<script type="text/javascript">
		function searchReport() {
		//	var isValid = validate();

//$('#loader1').show();
				var date = $("#date").val();

				$('#loader').show();

				$
						.getJSON(
								'${getVehicleAvg}',

								{
									date : date,
									ajax : 'true'

								},
								function(data) {
									
								
									$('#table_grid td').remove();
									$('#loader').hide();
									 document.getElementById("PDFButton").disabled=false;
									  document.getElementById("expExcel").disabled=false;
									if (data == "") {
										alert("No records found !!");
										  document.getElementById("expExcel").disabled=true;
										  document.getElementById("PDFButton").disabled=true;
									}
									

									$
											.each(
													data,
													function(key, report) {
															document.getElementById('range').style.display = 'block';
													    var color=0;
													    var actualAvg=parseFloat(report.vehRunningKm/report.diesel);
	                                                     var minAvg=parseInt(report.vehMiniAvg); 
															if(actualAvg<minAvg)
																{
																color=1;
																}
														var index = key + 1;
														if(color==1){
														var tr = $('<tr bgcolor=#f77676></tr>');
														}else
															{
														var tr = $('<tr></tr>');	
															}
													  	tr.append($('<td></td>').html(key+1));

													  	tr.append($('<td></td>').html(report.vehNo));

													  	tr.append($('<td></td>').html(report.driverName));

													  	tr.append($('<td></td>').html(report.routeName));
													  	
													  	
													  	tr.append($('<td    onchange="onMrpRateChange('+report.tranId+' ,1)"  id=veh_out_km'+report.tranId+' name=veh_out_km'+report.tranId+'></td>').html(report.vehOutkm));

													  	tr.append($('<td onchange="onMrpRateChange('+report.tranId+' ,2)"  id=veh_in_km'+report.tranId+' name=veh_in_km'+report.tranId+'></td>').html(report.vehInkm));

													  	tr.append($('<td></td>').html(report.vehRunningKm));
													  	
														tr.append($('<td onchange="onMrpRateChange('+report.tranId+' ,3)"  id=veh_diesel'+report.tranId+' name=veh_diesel'+report.tranId+'></td>').html(report.diesel));
														if(actualAvg>0){
													  	tr.append($('<td></td>').html(actualAvg.toFixed(2)));
														}
														else{
														  	tr.append($('<td></td>').html(0));

														}
													  	tr.append($('<td></td>').html(report.vehMiniAvg));

													  	tr.append($('<td></td>').html(report.vehStandAvg));

														$('#table_grid tbody')
																.append(
																		tr);
														

													})
													applyEdit("table_grid", [4,5,7]);

								});
				
			
		}
	</script>

	<script type="text/javascript">
		function validate() {
			var date = $("#date").val();
            var isValid = true;

			if (date == "" || date == null  ) {
 
		    alert("Please Select Date");		
			isValid=false;
			} 
			return isValid;
		}
	</script>

	
	<script>

function genPdf()
{
	window
	.open('${pageContext.request.contextPath}/getVehAvgReportPdf');
	}
</script>
<script type="text/javascript">
function exportToExcel()
{
	 
	window.open("${pageContext.request.contextPath}/exportToExcel");
			document.getElementById("expExcel").disabled=true;
}
</script>
<script type="text/javascript">

function getStyle(el, cssprop) {
	if (el.currentStyle)
		return el.currentStyle[cssprop];	 // IE
	else if (document.defaultView && document.defaultView.getComputedStyle)
		return document.defaultView.getComputedStyle(el, "")[cssprop];	// Firefox
	else
		return el.style[cssprop]; //try and get inline style
}

function applyEdit(tabID, editables) {
	//alert("Hi")
	var tab = document.getElementById(tabID);
	if (tab) {
		var rows = tab.getElementsByTagName("tr");
		for(var r = 0; r < rows.length; r++) {
			var tds = rows[r].getElementsByTagName("td");
			for (var c = 0; c < tds.length; c++) {
				if (editables.indexOf(c) > -1)
					tds[c].onclick = function () { beginEdit(this); };
			}
		}
	}
}
var oldColor, oldText, padTop, padBottom = "";
function beginEdit(td) {

	if (td.firstChild && td.firstChild.tagName == "INPUT")
		return;

	oldText = td.innerHTML.trim();
	oldColor = getStyle(td, "backgroundColor");
	padTop = getStyle(td, "paddingTop");
	padBottom = getStyle(td, "paddingBottom");

	var input = document.createElement("input");
	input.value = oldText;

	//// ------- input style -------
	var left = getStyle(td, "paddingLeft").replace("px", "");
	var right = getStyle(td, "paddingRight").replace("px", "");
	input.style.width = td.offsetWidth - left - right - (td.clientLeft * 2) - 2 + "px";
	input.style.height = td.offsetHeight - (td.clientTop * 2) - 2 + "px";
	input.style.border = "0px";
	input.style.fontFamily = "inherit";
	input.style.fontSize = "inherit";
	input.style.textAlign = "inherit";
	input.style.backgroundColor = "LightGoldenRodYellow";

	input.onblur = function () { endEdit(this); };

	td.innerHTML = "";
	td.style.paddingTop = "0px";
	td.style.paddingBottom = "0px";
	td.style.backgroundColor = "LightGoldenRodYellow";
	td.insertBefore(input, td.firstChild);
	input.select();
}
function endEdit(input) {
	var td = input.parentNode;
	td.removeChild(td.firstChild);	//remove input
	td.innerHTML = input.value;
	if (oldText != input.value.trim() )
		td.style.color = "red";

	td.style.paddingTop = padTop;
	td.style.paddingBottom = padBottom;
	td.style.backgroundColor = oldColor;
}
//applyEdit("table_grid", [4,5,6]); commented by sachin added in post  ajax data appended to table
</script>
<script type="text/javascript">
function onMrpRateChange(id,flag) {
	//alert("HI")
	   $('#loader1').show();
	  
	   				//var  vehOutkm = parseFloat($('#veh_out_km'+id).text());
	   				  var vehOutkm = 0.0;
				         var  diesel =0.0
		   				  var vehInkm = 0.0;
	   					
	   				if(flag==1){
	   				      vehOutkm = $('#veh_out_km'+id).find('input').val();
				          diesel = parseFloat($('#veh_diesel'+id).text());
		   				  vehInkm = parseFloat($('#veh_in_km'+id).text());
	   					
	   				}else if(flag==2){
	   				     vehInkm = $('#veh_in_km'+id).find('input').val();
				          diesel = parseFloat($('#veh_diesel'+id).text());
		   				  vehOutkm = parseFloat($('#veh_out_km'+id).text());
	   				}else if(flag==3){
	   				     diesel = $('#veh_diesel'+id).find('input').val();
	   					  vehInkm = parseFloat($('#veh_in_km'+id).text());
		   				  vehOutkm = parseFloat($('#veh_out_km'+id).text());

	   				}
	   			    //var vehOutkm = $('#veh_out_km'+id).find('input').val();
			        //var  diesel = parseFloat($('#veh_diesel'+id).text());
	   				//var  vehInkm = parseFloat($('#veh_in_km'+id).text());
	   				var paramKey =flag;
	   
	   $.getJSON(
				'${updateVehDetailByAdminJSP}',
				{
					tranId : id,
					vehOutkm : vehOutkm,
					vehInkm : vehInkm,
					diesel : diesel,
					paramKey : paramKey,
					ajax : 'true',

				},
				function(data) {
					if(data.error==false){
						alert("Success")
					   searchReport();
					 $('#loader1').hide();
					}else{
						alert("Failed")
						searchReport();
						 $('#loader1').hide();
					}
				}
				);
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