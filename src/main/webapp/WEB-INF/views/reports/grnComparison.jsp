<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body>
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
	<c:url var="getGrnGvnByDatewise" value="/getGrnReportComparison"></c:url>
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
					<i class="fa fa-file-o"></i>Grn  Report
				</h1>
				<h4></h4>
			</div>
		</div>
		<!-- END Page Title -->

		<!-- BEGIN Breadcrumb -->
	<%-- 	<div id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i> <a
					href="${pageContext.request.contextPath}/home">Home</a> <span
					class="divider"><i class="fa fa-angle-right"></i></span></li>
				<li class="active">Grn  Report</li>
			</ul>
		</div> --%>
		<!-- END Breadcrumb -->

		<!-- BEGIN Main Content -->
		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>View Grn  Report
				</h3>

			</div>

			<div class="box-content">
				<div class="row">


					<div class="form-group">
						<label class="col-sm-3 col-lg-2	 control-label">Select
							Month</label>
						<div class="col-sm-6 col-lg-4 controls date_select">
							<select class="form-control" name="sel_month" id="sel_month">

								<option value="1">Jan  ${year}</option>
								<option value="2">Feb  ${year}</option>
								<option value="3">Mar  ${year}</option>
								<option value="4">Apr  ${year}</option>
								<option value="5">May  ${year}</option>
								<option value="6">Jun  ${year}</option>
								<option value="7">Jul  ${year}</option>
								<option value="8">Aug  ${year}</option>
								<option value="9">Sep  ${year}</option>
								<option value="10">Oct ${year}</option>
								<option value="11">Nov ${year}</option>
								<option value="12">Dec  ${year}</option>

							</select>
						</div>
						<label class="col-sm-3 col-lg-2	 control-label"></label>
						<div class="col-sm-6 col-lg-4 controls date_select">
							<button class="btn btn-info" onclick="searchReport()">Search
								Report</button>
								<button class="btn btn-primary" value="PDF" id="PDFButton"
							onclick="genPdf()" disabled="disabled">PDF</button>
						</div>
					</div>
				</div>
				<br>

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
					<i class="fa fa-list-alt"></i>Sales Comparison Report
				</h3>

			</div>

			<form id="submitBillForm" method="post">
<div class=" box-content">
				<div class="col-md-12 table-responsive">
					<table class="table table-bordered table-striped fill-head "
						style="width: 100%" id="table_grid">
						<thead>
						
							<tr>
							
								<th>Franchisee Name</th>
								<th>Sale</th>
								<th>GRN Amount</th>
								<th>GRN %</th>
								<th>Per Day Amt</th>
						
							</tr>
							
						</thead>
						<tbody>
						</tbody>
					</table>
					 <div class="form-group" style="display: none;" id="range">

						<div class="col-sm-3  controls">
							<input type="button" id="expExcel" class="btn btn-primary"
								value="EXPORT TO Excel" onclick="exportToExcel();"
								disabled="disabled">
						</div>
					</div>
					<div align="center" id="showchart" style="display: none"></div>
				</div>
            </div>
				<!-- 				</div>
				
				<div id="chart" style="display: none"><br><br><br>
	<hr><div  >
	 
			<div  id="chart_div" style="width:60%; height:300; float:left;" ></div> 
		 
			<div   id="PieChart_div" style="width:40%%; height:300; float: right;" ></div> 
			</div>
			
				 
				</div> -->


				<div id="chart"">
					<br> <br> <br>
					<hr>


					<div id="chart_div" style="width: 100%; height: 100%;"></div>


					<div id="PieChart_div" style="width: 100%; height: 100%;"></div>


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
		
			var month = $("#sel_month").val();
		
				

				$('#loader').show();

				$
						.getJSON(
								'${getGrnGvnByDatewise}',

								{
									
									month:month,
									ajax : 'true'

								},
								function(data) {
									
									$('#table_grid td').remove();
									$('#loader').hide();
									document.getElementById("PDFButton").disabled = false;
									  document
										.getElementById('range').style.display = 'block';
									  document.getElementById("expExcel").disabled=false;
									if (data == "") {
										alert("No records found !!");
										  document.getElementById("expExcel").disabled=true;
											document.getElementById("PDFButton").disabled = true;

									}
									
								var saleValue=0;
								var grnValue=0;
								var grnPer=0;
								var perDayAmt=0;
									$.each(data,function(key, report) {
														//  document.getElementById("expExcel").disabled=false;
														//	document.getElementById('range').style.display = 'block';
														
														var index = key + 1;
														//var tr = "<tr>";
														var tr = $('<tr></tr>');
													  	//tr.append($('<td></td>').html(key+1));
													  
													  	tr.append($('<td style="text-align:left;"></td>').html(report.frName));
													  
														tr.append($('<td style="text-align:right;"></td>').html(addCommas((report.billTotal).toFixed(2))));
													 	tr.append($('<td style="text-align:right;"></td>').html(addCommas((report.grnAmt).toFixed(2))));
													 	tr.append($('<td style="text-align:right;"></td>').html(addCommas((report.grnAmt/(report.billTotal/100)).toFixed(2))));
														tr.append($('<td style="text-align:right;"></td>').html(addCommas((report.grnAmt/30).toFixed(2))));
														$('#table_grid tbody').append(tr);
														saleValue=saleValue+(report.billTotal);
														grnValue=grnValue+(report.grnAmt);
														grnPer=grnPer+(report.grnAmt/(report.billTotal/100));
														perDayAmt=perDayAmt+(report.grnAmt/30);
											})
											var tr = $('<tr></tr>');
											tr.append($('<td style="text-align:left; font-weight: 700;"></td>').html("Total"));
									  
									tr.append($('<td style="text-align:right; font-weight: 700;"></td>').html(addCommas((saleValue).toFixed(2))));
								  	tr.append($('<td style="text-align:right; font-weight: 700;"></td>').html(addCommas((grnValue).toFixed(2))));
								 	tr.append($('<td style="text-align:right; font-weight: 700;"></td>').html(addCommas((grnValue/(saleValue/100)).toFixed(2))));
								 	tr.append($('<td style="text-align:right; font-weight: 700;"></td>').html(addCommas((perDayAmt).toFixed(2))));
									$('#table_grid tbody').append(tr);
											
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
function showChart(){
	$("#PieChart_div").empty();
	$("#chart_div").empty();
		document.getElementById('chart').style.display = "block";
		   document.getElementById("table_grid").style="display:none";
		 
		   document.getElementById('chart').style.display ="display:none";
		   document.getElementById("table_grid").style= "block";
			var isGrn = $("#isGrn").val();
			//alert("isGrn " +isGrn);

		 //report 1
				var selectedFr = $("#selectFr").val();
				var routeId=$("#selectRoute").val();
				
				var from_date = $("#fromDate").val();
				var to_date = $("#toDate").val();
				

				$('#loader').show();

				$
						.getJSON(
								'${getGrnGvnByDatewise}',

								{
									fr_id_list : JSON.stringify(selectedFr),
									from_date : from_date,
									to_date : to_date,
									route_id:routeId,
									is_grn:isGrn,
									ajax : 'true'

								},
								function(data) {

								//alert(data);
							 if (data == "") {
									alert("No records found !!");
									$('#loader').hide();

								}
							 var i=0;
							 $('#loader').hide();
							 google.charts.load('current', {'packages':['corechart', 'bar']});
							 google.charts.setOnLoadCallback(drawStuff);

							 function drawStuff() {
								 
								// alert("Inside DrawStuff");
 
							   var chartDiv = document.getElementById('chart_div');
							   document.getElementById("chart_div").style.border = "thin dotted red";
							   
							   
							   var PiechartDiv = document.getElementById('PieChart_div');
							   document.getElementById("PieChart_div").style.border = "thin dotted red";
							   
							   
						       var dataTable = new google.visualization.DataTable();
						       dataTable.addColumn('string', 'Date'); // Implicit domain column.
						       dataTable.addColumn('number', 'Requested Value'); // Implicit data column.
						       dataTable.addColumn('number', 'Approved Value');
						       
						       var piedataTable = new google.visualization.DataTable();
						       piedataTable.addColumn('string', 'Date'); // Implicit domain column.
						       piedataTable.addColumn('number', 'Approved Value');
						       
						       
						       $.each(data,function(key, report) {

						    	   
						    	  // alert("In Data")
						    	   var reqValue=report.totalAmt;
						    	  var aprValue=report.aprGrandTotal;
									
						    	  
						    	  
						    	  
						    	  
						    	  //var total=report.taxableAmt+report.sgstSum+report.cgstSum;
									//alert("total ==="+total);
									//alert("base Value "+baseValue);
									
									var date=report.grngvnDate;
									//alert("frNAme "+frName);
									//var date= item.billDate+'\nTax : ' + item.tax_per + '%';
									
								   dataTable.addRows([
									 
									   
									   [date, reqValue,aprValue],
									   
								           ]);
								   
								   
								   
								   piedataTable.addRows([
									 
									   
									   [date, aprValue],
									   
								          
								           ]);
								     }) // end of  $.each(data,function(key, report) {-- function

            // Instantiate and draw the chart.
          						    
 var materialOptions = {
						    	
          width: 500,
          chart: {
            title: 'Date wise Grn Gvn Report',
            subtitle: 'Requested and Approved Value',
           

          },
          series: {
            0: { axis: 'distance' }, // Bind series 0 to an axis named 'distance'.
            1: { axis: 'brightness' } // Bind series 1 to an axis named 'brightness'.
          },
          axes: {
            y: {
              distance: {label: 'Req Value'}, // Left y-axis.
              brightness: {side: 'right', label: 'Apr  Value'} // Right y-axis.
            }
          }
        };
						       
						       function drawMaterialChart() {
						           var materialChart = new google.charts.Bar(chartDiv);
						           
						          // alert("mater chart "+materialChart);
						           materialChart.draw(dataTable, google.charts.Bar.convertOptions(materialOptions));
						          // button.innerText = 'Change to Classic';
						          // button.onclick = drawClassicChart;
						         }
						       
						        var chart = new google.visualization.ColumnChart(
						                document.getElementById('chart_div'));
						        
						        var Piechart = new google.visualization.PieChart(
						                document.getElementById('PieChart_div'));
						       chart.draw(dataTable,
						          {title: 'Date wise Grn Gvn Report'});
						       
						       
						       Piechart.draw(piedataTable,
								          {title: 'Date wise Grn Gvn Report',is3D:true});
						      // drawMaterialChart();
							 };
							 
										
							  	});
			
}

					
					
function genPdf()
{

	window.open('${pageContext.request.contextPath}/showGrnCompareReport/');
		
}
function exportToExcel()
{
	 
	window.open("${pageContext.request.contextPath}/exportToExcel");
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