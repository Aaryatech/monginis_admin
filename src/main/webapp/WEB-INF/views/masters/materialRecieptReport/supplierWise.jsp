<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
 
 	
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
 
<body>
<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<c:url var="materialRecieptsSupplierWiseReport" value="/materialRecieptsSupplierWiseReport"></c:url> 


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
						<i class="fa fa-file-o"></i>Inward Report Supplier Wise
					</h1>
					
				</div>
				
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					 
					<div class="box" id="supplierwise" >
					
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i>Inward Report Supplier Wise
							</h3>
							<div class="box-tool">
								 
							</div>
						</div>
						<div class=" box-content">
						<div class="form-group">
									<div class="col-md-2">From Date:</div>
									<div class="col-md-3">
										<input class="form-control date-picker" id="from_date" size="16"
											 type="text" name="from_date" required />
									
										</div>
										
										<div class="col-md-2">To Date:</div>
									<div class="col-md-3">
										<input class="form-control date-picker" id="to_date" size="16"
											 type="text" name="to_date" required />
									
										</div>
										
										
										</div><br>
						
								</div>
								<div class=" box-content">
								    <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Select supplier</label>
									<div class="col-sm-5 col-lg-3 controls">
										<select data-placeholder="Select Supplier" name="suppliers[]"
													class="form-control chosen" tabindex="-1" id="suppliers" multiple="multiple"
													data-rule-required="true">
													<option value="-1" selected>All</option>
													<c:forEach items="${supplierList}" var="supplierList"> 
													<option value="${supplierList.suppId}"><c:out value="${supplierList.suppName}"></c:out> </option>
													

											</c:forEach> 
												</select>
									
										</div>
										 
										</div><br>
						
								</div>
								<div class=" box-content">
									<div class="form-group">
									<div class="col-md-2">Group By </div>
									<div class="col-md-3">
										<input class="form-control" id="Supplier" size="16" value="Supplier"
											 type="text" name="book_Date" readonly />
											
									
										</div>
										 
										</div><br>
						
								</div> 
								<div class=" box-content">
								<div class="form-group">
								
								<div align="center" class="form-group">
									<div class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
										 
										<input type="button" class="btn btn-primary" value="View All" id="searchmixall"
											onclick="searchsupplierwise()">
											<a href="${pageContext.request.contextPath}/materialRec?url=supplierWisePdf"
								target="_blank"><input type="button" class="btn btn-primary" value="Pdf"  ></a>
								<button class="btn search_btn" onclick="showChart()" >Graph</button>

									</div><br>
									
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
								</div>
								</div>
								
							

						<div class=" box-content">
					<div class="row">
					
					
						<div class="col-md-12 table-responsive">
							<div style="overflow:scroll;height:100%;width:100%;overflow:auto">
									<table width="100%" height="100%" border="0"class="table table-bordered table-striped fill-head "
										style="width: 100%" id="table_grid1">
								<thead>
									<tr>
										<th style="text-align:center;">Sr.No.</th> 
										<th style="text-align:center;">Party Name</th>
										<th style="text-align:center;">City</th>
										<th style="text-align:center;">GSTIN</th>
										 
										<th style="text-align:center;">Basic Value</th>
										<th style="text-align:center;">Discount</th>
										<th style="text-align:center;">Other</th>
										<th style="text-align:center;">Freight Amt</th>
										<th style="text-align:center;">Insurance Amt</th>
										 
										<th style="text-align:center;">CGST</th>
										<th style="text-align:center;">SGST</th>
										<th style="text-align:center;">IGST</th>
										<th style="text-align:center;">Round Off</th>
										<th style="text-align:center;">Cess</th>
										<th style="text-align:center;">Total</th>
										
									</tr>
								</thead>
								
								<tbody>
									 
										
									
								</tbody>
							</table>
							<div id="chart" > <br><br> <br>
							 
      							
        <div align="center" id="chart_div" style="width: 50%" ></div>
       <div  align="center" id="PieChart_div" style="width: 50%"></div>
      
     
    </div>
						</div>
					</div>
					</div>

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
	 
	</script>
		<script type="text/javascript">
		function searchsupplierwise() {
			document.getElementById('chart').style.display ="display:none";
			   document.getElementById("table_grid1").style= "block";
			var from_date = $("#from_date").val();
			var to_date = $("#to_date").val();
			var suppliers = $("#suppliers").val();
			
			
			$('#loader').show();

			$
					.getJSON(
							'${materialRecieptsSupplierWiseReport}',

							{
								 
								from_date : from_date,
								to_date : to_date,
								suppliers : suppliers,
								ajax : 'true'

							},
							function(data) {

								$('#table_grid1 td').remove();
								$('#loader').hide();

								if (data == "") {
									alert("No records found !!");

								}
							 

							  $.each( data, function(key, itemList) {
												
												var tr = $('<tr></tr>');
											  	tr.append($('<td style="text-align:center;"></td>').html(key+1)); 
											  	tr.append($('<td style="text-align:center;"></td>').html(itemList.suppName));
											  	tr.append($('<td style="text-align:center;"></td>').html(itemList.suppCity)); 
											  	tr.append($('<td style="text-align:center;"></td>').html(itemList.suppGstin)); 
											  	tr.append($('<td style="text-align:right"></td>').html((itemList.basicValue).toFixed(2)));
											  	tr.append($('<td style="text-align:right"></td>').html((itemList.discAmt).toFixed(2))); 
											  	tr.append($('<td style="text-align:right"></td>').html((itemList.other).toFixed(2))); 
											  	tr.append($('<td style="text-align:right"></td>').html((itemList.freightAmt).toFixed(2))); 
											  	tr.append($('<td style="text-align:right"></td>').html((itemList.insuranceAmt).toFixed(2))); 
											  	tr.append($('<td style="text-align:right"></td>').html((itemList.cgst).toFixed(2)));
											  	tr.append($('<td style="text-align:right"></td>').html((itemList.sgst).toFixed(2)));
											  	tr.append($('<td style="text-align:right"></td>').html((itemList.igst).toFixed(2))); 
											  	tr.append($('<td style="text-align:right"></td>').html((itemList.roundOff).toFixed(2))); 
											  	tr.append($('<td style="text-align:right"></td>').html((itemList.cess).toFixed(2))); 
											  	tr.append($('<td style="text-align:right"></td>').html((itemList.billAmount).toFixed(2))); 
											  	 
												$('#table_grid1 tbody').append(tr);
												 

											})  
							});

		 
	}
		 
	</script>
	<script type="text/javascript">
function showChart(){
	
	//alert("Hi");
	var suppliers=$("#suppliers").val();
	
	var from_date = $("#from_date").val();
	var to_date = $("#to_date").val();
	
	//alert(from_date);
	//alert(to_date);
	//alert(suppliers);
		
	$("#PieChart_div").empty();
	$("#chart_div").empty();
		document.getElementById('chart').style.display = "block";
		   document.getElementById("table_grid1").style="display:none";
		 
		     
			$.getJSON(
					'${materialRecieptsSupplierWiseReport}',

					{
						 
						from_date : from_date,
						to_date : to_date,
						suppliers : suppliers,
						ajax : 'true'

					},
					function(data) {

								//alert(data);
							 if (data == "") {
									alert("No records found !!");

								}
							 var i=0;
							 
							 google.charts.load('current', {'packages':['corechart', 'bar']});
							 google.charts.setOnLoadCallback(drawStuff);

							 function drawStuff() {
								 
								 //alert("Inside DrawStuff");
 
							   var chartDiv = document.getElementById('chart_div');
							  // document.getElementById("chart_div").style.border = "thin dotted red";
							   
							   
							   var PiechartDiv = document.getElementById('PieChart_div');
							   //document.getElementById("PieChart_div").style.border = "thin dotted red";
							   
							   
						       var dataTable = new google.visualization.DataTable();
						       dataTable.addColumn('string', 'Supplier Wise'); // Implicit domain column.
						       dataTable.addColumn('number', 'Base Value'); // Implicit data column.
						       dataTable.addColumn('number', 'Total');
						       
						       var piedataTable = new google.visualization.DataTable();
						       piedataTable.addColumn('string', 'Supplier Wise'); // Implicit domain column.
						       piedataTable.addColumn('number', 'Total');
						       
						       
						       $.each(data,function(key, report) {

									//alert("loop");						    	   
						    	  
						    	   var baseValue=report.basicValue;
						    	   //alert("baseValue"+baseValue);
						    	  
						    	   var total=report.billAmount;
						    	   //alert("total"+total);
									
									var supplier=report.suppName;
									//alert("suppName "+supplier);
									 
									
								   dataTable.addRows([
									 
									   
									   [supplier, baseValue,total],
									   
								            
								             
								           ]);
								   
								   
								   
								   piedataTable.addRows([
									 
									   
									   [supplier, total],
									   
								          
								           ]);
								     }) // end of  $.each(data,function(key, report) {-- function

            // Instantiate and draw the chart.
          						    
 var materialOptions = {
						    	
          width: 500,
          chart: {
            title: 'Date wise Tax Graph',
            subtitle: 'Total tax & Taxable Amount per day',
           

          },
          series: {
            0: { axis: 'distance' }, // Bind series 0 to an axis named 'distance'.
            1: { axis: 'brightness' } // Bind series 1 to an axis named 'brightness'.
          },
          axes: {
            y: {
              distance: {label: 'Total Tax'}, // Left y-axis.
              brightness: {side: 'right', label: 'Taxable Amount'} // Right y-axis.
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
						          {width: 600, height: 600, title: 'Sales Summary Group By Supplier'});
						       
						       
						       Piechart.draw(piedataTable,
								          {width: 600, height: 600, title: 'Sales Summary Group By Supplier',is3D:true});
						      // drawMaterialChart();
							 };
							 
										
							  	});
			
}

</script>
	
</body>
</html>