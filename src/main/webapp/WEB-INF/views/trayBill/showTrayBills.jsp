<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
<c:url var="getTrayBillListProcess" value="/getTrayBillListProcess" />

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
						<i class="fa fa-file-o"></i>View Tray Bills
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
								<i class="fa fa-bars"></i> Search Bill List
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

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">From
										Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="dp1" size="16"
											value="${todaysDate}" type="text" name="from_date" required />
									</div>




									<label class="col-sm-3 col-lg-2 control-label">To Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="dp2" size="16"
											value="${todaysDate}" type="text" name="to_date" required />

									</div>

								</div>



								<div class="form-group">

									<label for="textfield2" class="col-xs-3 col-lg-2 control-label">Select
										Franchisee </label>
									<div class="col-sm-9 col-lg-10 controls">

										<select class="form-control chosen" multiple="multiple"
											tabindex="6" name="fr_id" id="fr_id">

											<option value="-1">All</option>
											<c:forEach items="${allFrIdNameList}" var="allFrIdNameList"
												varStatus="count">
												<option value="${allFrIdNameList.frId}">${allFrIdNameList.frName}</option>

											</c:forEach>

										</select>
									</div>


								</div>
								<div align="center" class="form-group">
									<div
										class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
										<input type="button" class="btn btn-primary" value="Submit"
											id="callSubmit" onclick="callSearch()">


									</div>
								</div>
								
								<div align="center" id="loader" style="display: none">

									<span>
										<h4>
											<font color="#343690">Loading</font>
										</h4>
									</span> <span class="l-1"></span> <span class="l-2"></span> <span
										class="l-3"></span> <span class="l-4"></span> <span
										class="l-5"></span> <span class="l-6"></span>
								</div>

								 </form>

								
							<form action="${pageContext.request.contextPath}/getSelectedTrayIdForPrint" class="form-horizontal"
								method="post" id="validation-form"> 
								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i> Bill List
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
										</div>
									</div>

									<div class="box-content">

										<div class="clearfix"></div>
										<div class="table-responsive" style="border: 0">
											<table width="100%" class="table table-bordered table-striped fill-head" id="table1">
												<thead>
													<tr>
													   <th ><input type="checkbox" name="select_all" onchange="selectAll()"
																id="select_all"
																value="1" >ALL</th>
														<th>Sr No</th>
														<th>Id</th>
														<th>Date</th>
														<th>Franchisee</th>
														<th>Small Tray</th>
														<th>Big Tray </th>
									                  	<th>Lead Tray </th>
										                <th>Extra Tray </th>
										                <th>Taxable Amt</th>
										                <th>Tax Amt</th>
										                <th>Grand Total</th>

													</tr>
												</thead>
												<tbody>
													
												</tbody>
											</table>
										</div>
									</div>
	                           <div align="center" class="form-group">
									<div
										class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
										<input type="button" class="btn btn-primary" value="Print"
											id="print"  onclick="printTrayBill()" disabled/>


									</div>
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
		$(document).ready(function() {
			$('#callSubmit2').submit(function() {
				alert("searching");
				$.ajax({
					type : "get",
					url : "/getBillListProcess", //this is my servlet
					/*   data: "input=" +$('#ip').val()+"&output="+$('#op').val(), */
					success : function(data) {
						alert("success");

					}

				});
			});

		});
	</script>

	<script type="text/javascript">
		function callSearch() {

			//var frId = document.getElementById("fr_id").value;
			var array=[];
			var frIds=$("#fr_id").val();
			var fromDate = document.getElementById("dp1").value;
			var toDate = document.getElementById("dp2").value;
			
			$('#loader').show();


			$.getJSON(
							'${getTrayBillListProcess}',
							{
								fr_id_list : JSON.stringify(frIds),
								from_date : fromDate,
								to_date : toDate,
								ajax : 'true'
							},
							function(data) {
								$('#table1 td').remove();
								$('#loader').hide();
							
								if(data==""){
									alert("No Bill Found");
								}

								$.each(
												data,
												function(key, bill) {
                                              
													var tr = $('<tr></tr>');
													tr.append($('<td ></td>').html("<input type='checkbox' name='select_to_print' onchange='selectToPrint()' id='select_to_print' value="+bill.tranDetailId+">"));
												  	tr.append($('<td></td>').html(key+1));

												  	tr.append($('<td></td>').html(bill.tranDetailId));

												  	tr.append($('<td></td>').html(bill.outtrayDate));

												  	tr.append($('<td></td>').html(bill.frName));
                                                     var qtySmallPrice=bill.qtySmall*bill.priceSmall;
												  	tr.append($('<td></td>').html(""+bill.qtySmall+"*"+bill.priceSmall+"="+qtySmallPrice));
												  	 var qtyBigPrice=bill.qtyBig*bill.priceBig;
												  	tr.append($('<td></td>').html(""+bill.qtyBig+"*"+bill.priceBig+"="+qtyBigPrice));
												  	var qtyLeadPrice=bill.qtyLead*bill.priceLead;
												  	tr.append($('<td></td>').html(""+bill.qtyLead+"*"+bill.priceLead+"="+qtyLeadPrice));
												  	var qtyExtraPrice=bill.qtyExtra*bill.priceExtra;
												  	tr.append($('<td></td>').html(""+bill.qtyExtra+"*"+bill.priceExtra+"="+qtyExtraPrice));

												  	tr.append($('<td style="text-align:right;"></td>').html((bill.taxableAmt).toFixed(2)));
												  	tr.append($('<td></td>').html(bill.taxAmt+"("+bill.gstPer+"%)"));
												  	tr.append($('<td style="text-align:right;"></td>').html((bill.grandTotal).toFixed(2)));
												  	
													$('#table1 tbody').append(tr);

													

												});
								$('#table1 tbody').append(trclosed);
							});

		}
	</script>
	<script>
function printTrayBill()
	{
	
		
		var checkedId=[];
		var checkboxes=document.getElementsByName("select_to_print");
		
		 
			for (var i = 0, n = checkboxes.length; i < n; i++) {
				if(checkboxes[i].checked) {
					
					checkedId.push(checkboxes[i].value );
					
				}
			}
		/* 	alert(checkboxes);
			 $.getJSON('${getSelectedTrayIdForPrint}',{

					id :  JSON.stringify(checkedId),
					ajax : 'true',
				

				 }); */
				  
		window.open("${pageContext.request.contextPath}/printSelectedTrayBill/"+checkedId+"");
	}
	
	function selectToPrint()
	{
		//alert("hh");
	 
		var checkboxes=document.getElementsByName("select_to_print");
		//alert(checkboxes[0].value);
		var flag=0;
			for (var i = 0, n = checkboxes.length; i < n; i++) {
				if(checkboxes[i].checked) {
					 
					flag=1;
				}
			}
		if(flag==1)
			{
		 //alert("KK");
			document.getElementById("print").disabled=false;
			}
		else{
		document.getElementById("print").disabled=true;
		}
		
		 
	}
	function selectAll()
	{
		document.getElementById("print").disabled=false;
	}
	</script>


</body>
</html>