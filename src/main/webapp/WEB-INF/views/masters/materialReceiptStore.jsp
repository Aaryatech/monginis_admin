<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Material Receipt Store</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<!--base css styles-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/data-tables/bootstrap3/dataTables.bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-fileupload/bootstrap-fileupload.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/chosen-bootstrap/chosen.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-timepicker/compiled/timepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/clockface/css/clockface.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/css/datepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/daterangepicker.css" />



<!--page specific css styles-->

<!--flaty css styles-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flaty.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flaty-responsive.css">

<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/img/favicon.png">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/common.js"></script>
</head>
<body>
<c:url var="withPoRef" value="/withPoRef"></c:url>

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
						<i class="fa fa-file-o"></i> Material Receipt Store
					</h1>
				</div>
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i> Material Receipt Store
							</h3>
							
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/mrnentry">MRN ENTRY</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						
						
						<div class="box-content">

							<form id="submitMrnEntry" action="${pageContext.request.contextPath}/" method="post">
							<div class="box-content">
								<div class="col-md-2">MRN No.</div>
								<div class="col-md-3"><input type="text" id="mrn_no" name="mrn_no" value="${mrnid}" class="form-control" readonly>
								</div>
								
							<div class="col-md-2"> Date</div> 
							<div class="col-md-3">
							<input type="text" id="mrn_date" name="mrn_date" value="${date}" class="form-control" readonly>
							
							</div>
					
							</div><br>
							
							<div class="box-content">
							
								<div class="col-md-2">Inward no.</div>
									<div class="col-md-3"><input type="text" id="inward_no" name="inward_no" value="${mrnno}" class="form-control" readonly>
								</div>
								<div class="col-md-2">Inward date</div>
									<div class="col-md-3">
									<input class="form-control" id="inward_date" size="16"
											type="text" name="inward_date" value="${date}"  readonly />
									</div>
								
				 
							</div><br>
							
							
							<div class="box-content">
							
							<div class="col-md-2" >MRN Type</div>
									<div class="col-md-3">
										<select name="mrn_id" id="mrn_id" class="form-control" tabindex="-1" required>
									
										<option selected value="">Select MRN Type</option>
											
											<c:forEach items="${mrntype}" var="mrntype">
                                              
                                              
										<option value="${mrntype.grpId}"><c:out value="${mrntype.grpName}"></c:out> </option>
													

											</c:forEach>
										</select>
									</div>
									
									<div class="col-md-2">Supplier </div>
									<div class="col-md-3"><input type="text" id="supp_id" name="supp_id" value="${suplrname}" 
									class="form-control" readonly>
								</div>
								
							</div><br>
							
							
							<div class="box-content">
							
							<div class="col-md-2" >Invoice No.</div>
									<div class="col-md-3"><input type="text" id="invoice_no" name="invoice_no" placeholder="Invoice No" class="form-control" required />
								</div>
									
									<div class="col-md-2">Date</div>
										<div class="col-md-3">
										<input class="form-control date-picker" id="dp1" size="16"
											type="text" name="invoice_date" value="" placeholder="Invoice Date" required />
									</div>
							
							</div><br>
							
							<div class="box-content">
							
									<div class="col-md-2" >Vehical No.</div>
									<div class="col-md-3"><input type="text" id="vehical_no" name="vehical_no" value="${vehicalno}" class="form-control" readonly>
									</div>
									
									<div class="col-md-2">Transporter</div>
										<div class="col-md-3"><input type="text" id="transporter" name="transporter" value="${transname}" class="form-control" readonly>
									</div>
							
							</div><br>
							
							<div class="box-content">
							
							<div class="col-md-2" >Against PO</div>
									<div class="col-md-3">
										<select name="po_id" id="po_id" class="form-control" tabindex="6" required>
											<option value=""> select</option>
											<option value="1">Yes</option>
											<option value="2">No</option>

										</select>
									</div>
									
									<div class="col-md-2">LR No.</div>
									<div class="col-md-3"><input type="text" id="lr_no" name="lr_no" value="${lrno}" 
									class="form-control" readonly>
								</div>
								
							</div><br>
							
							
							
							<div class="box-content">
							
									
									<div class="col-md-2" >PO Reference</div>
									<div class="col-md-3">
										<select name="poref_id" id="poref_id" class="form-control" tabindex="-1"  disabled required>
									
										<option selected value="">Select Po Ref</option>
											
											<c:forEach items="${polist}" var="polist">
                                              
                                              
										<option value="${polist.poId}"><c:out value="${polist.poNo}"></c:out> </option>
													

											</c:forEach>
										</select>
									</div>
									
									<div class="col-md-2">PO Date</div>
										<div class="col-md-3">
										<input class="form-control date-picker" id="po_date" size="16"
											type="text" name="po_date" value="" placeholder="PO Date" disabled required />
									</div>
							
							</div><br><br>
							
							
							<div class=" box-content">
								<div class="row">
								<div class="col-md-12 table-responsive">
									<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Item</th>
										<th>IN QTY</th>
										<th>PO QTY</th>
										<th>STOCK QTY</th>
										<th>PO Rate</th>

									</tr>
								</thead>
									<tbody>
									<c:forEach items="${rawlist}" var="RawmaterialList"
													varStatus="count">

													<tr>
														<td><c:out value="${count.index+1}" /></td>

														<td align="left"><c:out
																value="${RawmaterialList.rmName}" /></td>


														<td align="left"><c:out
																value="${RawmaterialList.qty}" /></td>
 														<td><input class="form-control" id="po_qty" size="16"
											type="text" name="po_qty" value="" placeholder="po_qty" disabled required /></td>
											
											<td><input class="form-control" id="stockqty" size="16"
											type="text" name="stockqty" value="" placeholder="stockqty" disabled required /></td>
											
											<td><input class="form-control" id="porate" size="16"
											type="text" name="porate" value="" placeholder="porate" disabled required /></td>
											
												</tr>
												</c:forEach>
								

									</tbody>
									</table>
								</div>
								</div>
							</div>
							<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Submit">
<!-- 										<button type="button" class="btn">Cancel</button>
 -->									</div>
								</div><br><br>
								
						
							
							<div class="box-content">
							
							<div class="col-md-2" >Remark</div>
									
									<div class="col-md-3"><input type="text" id="Remark" name="Remark" value="" 
									class="form-control" >
									</div>
									
							</div><br>
							
							
								
								
							
							
							<div class="box-content">
							
							
							</div><br><br><br>
							
							

						</form>
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
	$(document).ready(function() { 
	$('#po_id').change(
			function() {
			 
				var po_id = $(this).val();
				if(po_id==1)
				{
					document.getElementById("poref_id").disabled = false;
					document.getElementById("po_date").disabled = false;
					document.getElementById("po_qty").disabled = false;
					document.getElementById("stockqty").disabled = false;
					document.getElementById("porate").disabled = false;
				}
				
				else
				{
					document.getElementById("poref_id").disabled = true;
					document.getElementById("po_date").disabled = true;
					document.getElementById("po_qty").disabled = true;
					document.getElementById("stockqty").disabled = true;
					document.getElementById("porate").disabled = true;
				}
		})
					 
});
	
	$(document).ready(function() { 
		$('#poref_id').change(
				function() {
				 alert("in function");
					$.getJSON('${withPoRef}', {
						poref_id : $(this).val(),
						ajax : 'true'
					},
							function(data) {
						if(data=="")
							{
							
							}
						else
							{
							
							$('#table_grid td').remove();
						
							
						alert(data);
						
						 $.each(
												data,
												function(key, itemList) {
						var tr = $('<tr></tr>');

					  	tr.append($('<td></td>').html(key+1));			  	
					  	tr.append($('<td></td>').html(itemList.rmName));
						tr.append($('<td></td>').html(itemList.qty));
						tr.append($('<td></td>').html(itemList.poQty));
						tr.append($('<td></td>').html(itemList.stockQty));
						tr.append($('<td></td>').html(itemList.poRate));
					$('#table_grid tbody').append(tr);
					
												})
												
							}
												
							});
					
			})
						 
	});
	</script>poref_id
	
	
								
							
	
</body>
</html>