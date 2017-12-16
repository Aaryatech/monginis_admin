<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Dashboard - MONGINIS Admin</title>
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


	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<c:url var="updatePlanQty" value="/updatePlanQty"/>

<c:url var="goToManualBom" value="/goToManualBom"/>

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
						<i class="fa fa-file-o"></i>Prod Detail
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
								<i class="fa fa-bars"></i> View Production Detail
							</h3>
							<div class="box-tool">
								<a href="">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>


						<div class="box-content">
							<form action="${pageContext.request.contextPath}/updateQty" class="form-horizontal"
								id="validation_form" method="post" name="form">



								<input type="hidden" name="mode_add" id="mode_add"
									value="add_att">

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Prod
										Date </label>
									<div class="col-sm-5 col-lg-3 controls">
										<input " type="text" name="prod_date" id="prod_date" class="form-control"
											value="${planHeader.productionDate}" />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Time
										Slot</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input type="text" value="${planHeader.timeSlot}"
											name="time_slot" class="form-control" />
									</div>

								</div>

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Category
									</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input  type="text" name="cat_name" id="cat_name"
											value="${planHeader.catName}" class="form-control" required />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">Prod ID</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input type="text" value="${planHeader.productionHeaderId}"
											id="prod_id" name="prod_id" class="form-control" />
									</div>

								</div>

								<div class="clearfix"></div>

								<div class="table-responsive" style="border: 0">
									<table width="100%" class="table table-advance" id="table1">
										<thead>
											<tr>

												<th width="17" style="width: 18px">Item Name</th>
												<th width="150" align="left">Stock</th>
												<th width="150" align="left">Plan Qty</th>
												<th width="150" align="left">Order Qty</th>

												<th width="150" align="left">Actual Prod</th>
												<th width="150" align="left">Total Qty</th>

												<th width="180" style="width: 18px">Rejected Qty</th>

											</tr>
										</thead>
										<tbody>

											<c:forEach items="${planDetail}" var="planDetail" varStatus="count">
												<input type="hidden" name="item${count.index}" id="item${planDetail.productionDetailId}" value="${planDetail.itemId}"/>
												<tr>
													<td align="left"><c:out value="${planDetail.itemName}" /></td>
													
													<td align="left"><input align="left" type="text"  value="${planDetail.openingQty}"
														placeholder="Stock Qty" class="form-control" name="stk_qty${planDetail.productionDetailId}" id="stk_qty${planDetail.productionDetailId}"
														data-rule-required="true" style="width: 65px" disabled/></td>

                                                      <c:choose>
         
                                                      <c:when test = "${planHeader.productionStatus==1}">
													<td align="left"><input align="left" type="text" name="plan_qty${planDetail.productionDetailId}status1" id="plan_qty${planDetail.productionDetailId}status1"
														placeholder="Plan Qty" class="form-control" value="${planDetail.planQty}"
														data-rule-required="true" style="width: 65px"/></td>
														</button>
                                                    </c:when>
                                                       <c:otherwise>
                                                           <td align="left"><input align="left" type="text" name="plan_qty${planDetail.productionDetailId}status2" id="plan_qty${planDetail.productionDetailId}status2"
														placeholder="Plan Qty" class="form-control" value="${planDetail.planQty}"
														data-rule-required="true" style="width: 65px" disabled/></td>
                                                       </c:otherwise>
                                                     </c:choose>
                                                     <c:choose>
                                                     <c:when test = "${planHeader.productionStatus==2}">
													  <td align="left"><input align="left" type="text" name="order_qty${planDetail.productionDetailId}status1" id="order_qty${planDetail.productionDetailId}status1"
														placeholder="Order Qty" class="form-control" value="${planDetail.orderQty}"
														data-rule-required="true" style="width: 65px"/></td>
														 </c:when>
														  <c:otherwise>
														   <td align="left"><input align="left" type="text" name="order_qty${planDetail.productionDetailId}status2" id="order_qty${planDetail.productionDetailId}status2"
														   placeholder="Order Qty" class="form-control" value="${planDetail.orderQty}"
														   data-rule-required="true" style="width: 65px" disabled/></td>
														  </c:otherwise>
														  </c:choose>
														    <c:choose>
                                                      <c:when test = "${planHeader.productionStatus==4}">
													<td align="left"><input align="left" type="text" name="act_prod_qty${planDetail.productionDetailId}status4" id="act_prod_qty${planDetail.productionDetailId}status4"
														placeholder="Actual Prod" class="form-control" value="${planDetail.productionQty}"
														data-rule-required="true" style="width: 65px"disabled/></td>
														 </c:when>
														 <c:when test = "${planHeader.productionStatus==5}">
														 	<td align="left"><input align="left" type="text" name="act_prod_qty${planDetail.productionDetailId}status5" id="act_prod_qty${planDetail.productionDetailId}status5"
														    placeholder="Actual Prod" class="form-control" value="${planDetail.productionQty}"
														    data-rule-required="true" style="width: 65px"disabled/></td>
														 </c:when>
														   <c:otherwise>
														   <td align="left"><input align="left" type="text" name="act_prod_qty${planDetail.productionDetailId}status1" id="act_prod_qty${planDetail.productionDetailId}status1"
														    placeholder="Actual Prod" class="form-control" value="${planDetail.productionQty}"
														    data-rule-required="true" style="width: 65px" /></td>
														   </c:otherwise>
														 </c:choose>
													<td align="left"><input align="left" type="text" name="total_qty${planDetail.productionDetailId}" id="total_qty${planDetail.productionDetailId}" 
														placeholder="Total Qty" class="form-control" value="total"
														data-rule-required="true" style="width: 65px" disabled/></td>
														
													<td align="left"><input align="left" type="text" name="rej_qty${planDetail.productionDetailId}"  id="rej_qty${planDetail.productionDetailId}" 
														placeholder="Rejected Qty" class="form-control" value="0"
														data-rule-required="true" style="width: 65px" disabled/></td>


													<%-- <td align="left"><a
														href="viewDetails/${planHeader.productionHeaderId}"><span
															class="glyphicon glyphicon-info-sign"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;

													</td> --%>
													
													  <c:if test="${count.last}"><input type="hidden" name="cnt" id="cnt" value="${count.index}"/></c:if>
												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>

								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
									<c:choose>
									  <c:when test = "${planHeader.isBom==0}">
                                   <a href="${pageContext.request.contextPath}/showBom/${planHeader.productionHeaderId}/1/${planHeader.productionDate}/${planHeader.isPlanned}">   <button type="button" class="btn btn-primary">
											<i class="fa fa-check"></i> Req.To BOM
										</button></a>
                                    </c:when>
         
                                    <c:when test = "${planHeader.isBom==1}">
									<button type="button" class="btn btn-primary" disabled="disabled">
											<i class="fa fa-check"></i> Req. BOM
										</button>
                                    </c:when>
                                   <c:otherwise>
                                   </c:otherwise>
                                   </c:choose>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<c:choose>
									  <c:when test = "${planHeader.isMixing==0}">
                                     <a href="${pageContext.request.contextPath}/addMixing"> <button type="button" class="btn btn-primary">
											<i class="fa fa-check"></i> Req. Mixing
										</button></a>
										
                                    </c:when>
         
                                    <c:when test = "${planHeader.isMixing==1}">
								<button type="button" class="btn btn-primary"disabled="disabled">
											<i class="fa fa-check"></i> Req. Mixing
									</button>
										
                                    </c:when>
                                   <c:otherwise>
                                   </c:otherwise>
                                   </c:choose>
										<input type="hidden" name="productionStatus" id="productionStatus" value="${planHeader.productionStatus}"/>
									&nbsp;&nbsp;&nbsp;&nbsp;
									  <c:choose>
         
                                    <c:when test = "${planHeader.productionStatus==1}">
                                    <button type="submit" class="btn btn-primary" id="editPlanQty">
											<i class="fa fa-check"></i> Edit Plan Qty
									</button>
                                    </c:when>
         
                                    <c:when test = "${planHeader.productionStatus==2}">
									<button type="submit" class="btn btn-primary" id="editOrderQty">
											<i class="fa fa-check"></i> Order Plan Qty
									</button>
                                    </c:when>
         
                                   <c:otherwise>
                                   </c:otherwise>
                                   </c:choose>
                                   &nbsp;&nbsp;&nbsp;&nbsp;
									 <c:choose>
         
                                    <c:when test = "${planHeader.productionStatus==4}">
										<button type="submit" class="btn btn-primary" disabled="disabled">
											<i class="fa fa-check"></i>Complete Production
										</button>
                                    </c:when>
         
                                    <c:when test = "${planHeader.productionStatus==5}">
									    <button type="submit" class="btn btn-primary" disabled="disabled">
											<i class="fa fa-check"></i>Complete Production
										</button>
                                    </c:when>
                                    <c:otherwise>
									    <button type="submit" class="btn btn-primary">
											<i class="fa fa-check"></i>Complete Production
										</button>
                                   </c:otherwise>
                                   </c:choose>
                                   <c:choose>
                                   
                                   <c:when test = "${planHeader.isBom==1}">
									    <button type="button" class="btn btn-primary" id="man_bom_button" onclick="goToManBom()">
											<i class="fa fa-check"></i>Manual Bom
										</button>
                                    </c:when>
                                   
                                   </c:choose>
										
									  
										
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



$('#man_bom_button').click(function(){
    var form = document.getElementById("validation_form")
    form.action ="${pageContext.request.contextPath}/goToManualBom";
    form.submit();
});

 /* function goToManBom(){
		
	 alert("inside got to man bom");
		var prodDate=$("#prod_date").val();
		
		var prodId=$("#prod_id").val();
		
		alert(prodDate);
		alert(prodId);

		$.getJSON('${goToManualBom}',
				{
			
			prodId : prodId,
			prodDate :prodDate,
			ajax : 'true',
	});
		
		
	} */
</script>
<script type="text/javascript">
$("#editOrderQty").click(function() {
	/* var i; 

	var count=document.getElementById("cnt").value;	
	for(i=0;i<=count;i++)
		{
	       $("#order_qty"+i).attr('disabled', !$("#order_qty"+i).attr('disabled'));
	  
		}  */
	});
</script>

</body>
</html>