<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	 
	<style type="text/css">
	 <style type="text/css">
	table {
            width: 100%;
        }

        thead, tbody, trtd, , th { display: block; }

        tr:after {
            content: ' ';
            display: block;
            visibility: hidden;
            clear: both;
        }

        thead th {
            height: 35px;

             text-align: left; 
        }

        tbody {
            height: 500px;
            overflow-y: auto;
           
        }

        thead {
             fallback  
        }

 
	 
	   tbody td, thead th {
            width: 16.2%;
            float: left;
        }
	</style>
	 
	<body>
 	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
<c:url var="getCurrentStoreStock" value="/getCurrentStoreStock" />
<c:url var="getMonthWiseStoreStock" value="/getMonthWiseStoreStock" />
<c:url var="getDateWiseStoreStock" value="/getDateWiseStoreStock" />
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
						<i class="fa fa-file-o"></i>Store
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
								<i class="fa fa-bars"></i>Search Store Stock
							</h3>
							<div class="box-tool">
								<a href="">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>


						<div class="box-content">
							<%-- <form action="${pageContext.request.contextPath}/getBmsStock"
								class="form-horizontal" method="post" id="validation-form">
							 --%>	



								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Select
										View Option Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<select name="selectStock"  class="form-control chosen"
											tabindex="6" id="selectStock" onchange="showDiv(this)"
											required>

											<option value="-1">Select Option</option>
											<option value="1" id="currentStock">Get Current Stock</option>
											<option value="2" id="monthStock">Get Stock Between Month</option>
											<option value="3" id="dateStock">Get Stock Between Dates</option>

										</select>
									</div>

								</div>
								
							<br/>

							<div class="form-group">
								<div>
									<div class="colOuter" style="display: none"
										id=select_month_year><br/>
										<div class="col-md-2">
											<div class="col1title"> From :</div>
										</div>
										<div class="col-md-3" align="center">


											  <input type='text' placeholder="Select From Date" 
												name="from_stockdate" id="from_stockdate" required class="form-control date-picker" /> 
												 
										</div> 

										  <div class="col-md-1"></div> 

										<div class="col-md-2">
											<div class="col1title"> To :</div>
										</div>
										<div class="col-md-3" align="center">
											<input type='text' placeholder="Select To Date" 
												name="to_stockdate" id="to_stockdate" required class="form-control date-picker"/>
										</div>

									</div>

									<!-- <div class="colOuter" style="display: none" id=select_date>
										<div class="col-md-2"><br/>
											<div class="col1title">From Date:</div>
										</div>
										<div class="col-md-3" align="center">

											<input id="fromdatepicker" class="form-control date-picker"
												placeholder="From Date" name="from_datepicker" type="text">

										</div>

										<div class="col-md-1"></div>

										<div class="col-md-2">
											<div class="col1title">To Date:</div>
										</div>
										<div class="col-md-3" align="center">
											<input id="todatepicker" class="form-control date-picker"
												placeholder="To Date" name="to_datepicker" type="text">

										</div>

									</div> -->

								</div>

							</div><br/><br/>
							
							<div class="row">
					<div class="col-md-12" style="text-align: center">
						<input type="button"  class="btn btn-primary" value="Get Stock" onclick="getStock()">
					 


					</div>
				</div><br/>
							
							<!-- </form> -->


							<form action="${pageContext.request.contextPath}/dayEndStoreStock"
								class="form-horizontal" method="post" id="validation-form">

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
	
								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i> BMS Stock List
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
										</div>
									</div>

									<div class="box-content">
<jsp:include page="/WEB-INF/views/include/tableSearch.jsp"></jsp:include>

										<div class="clearfix"></div>
										<div class="table-responsive" style="border: 0">
											<table width="100%"
												class="table table-advance table-responsive table-position"
												id="table1">
												<thead>
													<tr>

														<th>Sr No</th>
														<th>Material Name</th>
														<th>Invert Qty</th>
														<th>BOM Qty</th>
														 
														<th>Opening Stock</th>
														<th>Closing Qty</th>
													<!-- 	<th>Status</th> -->

													</tr>

												</thead>
												 <tbody>
													<%--<c:forEach items="${stockList}" var="stockList"
														varStatus="count">

														<tr>
															<td><c:out value="${count.index+1}"></c:out></td>
															<td><c:out value="${stockList.rmName}"></c:out></td>
															<td><c:out value="${stockList.invertQty}"></c:out>
															</td>
															<td><c:out value="${stockList.bomQty}"></c:out>
															</td>
															 
															<td><c:out value="${stockList.openingQty}"></c:out>
															<td><c:out value="${stockList.closingQty}"></c:out>
															<td><c:out value="Status"></c:out></td>

														</tr>
													</c:forEach>--%>
												</tbody> 

											</table>
										</div>
<input type="hidden" id="stockDate">
										<!-- this is for ajax call<input type="submit" class="btn btn-primary" value="Submit"
										id="callSubmit" onclick="callSubmitGrn(); getGrnId();"> -->


										 
											 <div class="row">
					<div class="col-md-12" style="text-align: center" >

											<input type="button" value="Day End Process"
												class="btn btn-warning" id="submitDay" onclick="dayEnd()">  

</div>
										</div>
										<!-- </form> -->

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
	<script>
		$("#datepicker").datepicker({
			format : "mm-yyyy",
			startView : "months",
			minViewMode : "months"
		});
	</script>
	<script>
		/* function showDiv(elem) {
			if (elem.value == 1) {
				document.getElementById('select_month_year').style = "display:none";
				document.getElementById('select_date').style = "display:none";
			} else if (elem.value == 2) {
				document.getElementById('select_month_year').style.display = "block";
				document.getElementById('select_date').style = "display:none";
			} else if (elem.value == 3) {
				document.getElementById('select_date').style.display = "block";
				document.getElementById('select_month_year').style = "display:none";
			}
		}
		 */
		 </script>
			<script>
		 
			//document.getElementById("stockDate").value;
			
			var today = new Date();
var dd = today.getDate();
var mm = today.getMonth()+1; //January is 0!

var yyyy = today.getFullYear();
if(dd<10){
    dd='0'+dd;
} 
if(mm<10){
    mm='0'+mm;
} 
var today = dd+'-'+mm+'-'+yyyy;


//document.getElementById("stockDate").value = today;


		function getStock(){
			 
			  var selectId=document.getElementById("selectStock").value;
			  
			
			  
			   if(selectId==1) {
				   $('#loader').show();
					$.getJSON('${getCurrentStoreStock}', {
						fromDate : fromDate,
						toDate : toDate,
						ajax : 'true'
					}, function(data) {
						$('#loader').hide();
						$('#table1 td').remove();
						if(data=="")
						{
						alert("No Record Found");
						}
					else
						{
						
					
					 
					 $.each(data,function(key, stockList) {
												 
					var tr = $('<tr></tr>');

				  	tr.append($('<td></td>').html(key+1));			  	
				  	tr.append($('<td></td>').html(stockList.rmName));
				  	tr.append($('<td></td>').html(stockList.purRecQty));
				  	tr.append($('<td></td>').html(stockList.bmsIssueQty));
				  	tr.append($('<td></td>').html(stockList.storeOpeningStock));
				  	tr.append($('<td></td>').html(stockList.storeClosingStock));
				  	//alert(stockList.storeStockDate);
				  	document.getElementById("stockDate").value = stockList.storeStockDate;
				$('#table1 tbody').append(tr);
				
											})
											
						}
					});
				} 
			  
			   else if(selectId==2){
				   
				var fromDate=document.getElementById("from_stockdate").value;
				  var toDate=document.getElementById("to_stockdate").value;
				 
				if(fromDate!=null && fromDate!="" && toDate!=null && toDate!=""){
					$('#loader').show();
				$.getJSON('${getMonthWiseStoreStock}', {
					fromDate : fromDate,
					toDate : toDate,
					ajax : 'true'
				}, function(data) {
					$('#loader').hide();
					$('#table1 td').remove();
					if(data=="")
					{
						alert("No Record Found");
					}
				else
					{
					
				
				 
				 $.each(data,function(key, stockList) {
											
				var tr = $('<tr></tr>');

			  	tr.append($('<td></td>').html(key+1));			  	
			  	tr.append($('<td></td>').html(stockList.rmName));
			  	tr.append($('<td></td>').html(stockList.purRecQty));
			  	tr.append($('<td></td>').html(stockList.bmsIssueQty));
			  	tr.append($('<td></td>').html(stockList.storeOpeningStock));
			  	tr.append($('<td></td>').html(stockList.storeClosingStock));
			$('#table1 tbody').append(tr);
			
										})
										
					}
										
					});
				}
				else{
				alert("Please Select Months");
				}
			}
			else if(selectId=3){
				
				var fromDate=document.getElementById("from_stockdate").value;
				  var toDate=document.getElementById("to_stockdate").value;
				 
				if(fromDate!=null && fromDate!="" && toDate!=null && toDate!=""){
					  $('#loader').show();
					$.getJSON('${getDateWiseStoreStock}', {
						fromDate : fromDate,
						toDate : toDate,
						ajax : 'true'
					}, function(data) {
					 
						$('#loader').hide();
						$('#table1 td').remove();
						if(data=="")
						{
						alert("No Record Found");
						}
					else
						{
						
						
					 
					 $.each(data,function(key, stockList) {
												 
					var tr = $('<tr></tr>');

				  	tr.append($('<td></td>').html(key+1));			  	
				  	tr.append($('<td></td>').html(stockList.rmName));
				  	tr.append($('<td></td>').html(stockList.purRecQty));
				  	tr.append($('<td></td>').html(stockList.bmsIssueQty));
				  	tr.append($('<td></td>').html(stockList.storeOpeningStock));
				  	tr.append($('<td></td>').html(stockList.storeClosingStock));
				$('#table1 tbody').append(tr);
				
											})
											
						}
					});
				}
				else{
					alert("Please Select Dates");
					}
			}
			
			
			 
		}
		
		
		function dayEnd()
		{
			var stockDate=document.getElementById("stockDate").value;
			if(stockDate>today)
				alert(" You can't day end today, please try tomarrow !!");
			else{
				  document.getElementById("validation-form").submit();
			}
		}
	</script>
	 
	 <script>
	 $(document).ready(function() { 
	$('#selectStock').change(
			function() {
				//alert("hh");
				var select=$(this).val();
			 
				
				if (select == 1) {
					document.getElementById('select_month_year').style = "display:none";
					document.getElementById('submitDay').disabled =false;
				} else if (select == 2) {
					document.getElementById('select_month_year').style.display = "block";

				  	document.getElementById('submitDay').disabled =true; 
				} else if (select == 3) {

				  	document.getElementById('submitDay').disabled =true; 
					document.getElementById('select_month_year').style.display = "block";
				}
				
				

			});

});
	 
	 </script>
</body>
</html>


