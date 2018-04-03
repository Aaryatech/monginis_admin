<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<style>
@media only screen and (min-width: 1200px) {
	.franchisee_label, .menu_label {
		width: 22%;
	}
	.franchisee_select, .menu_select {
		width: 76%;
	}
	.date_label {
		width: 40%;
	}
	.date_select {
		width: 50%;
		padding-right: 0px;
	}
}
</style>
  <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
 <jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
<body>



	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>


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
					<i class="fa fa-file-o"></i>Bill of Material to Store
				</h1>
				<!-- <h4>Bill for franchises</h4> -->
			</div>
		</div>
		<!-- END Page Title -->

		
		<!-- BEGIN Main Content -->
		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>Raw Material Stock Calculation
				</h3>

			</div> 
				<div class=" box-content">
				 <form action="submitBmstoStore" method="post" class="form-horizontal" id=
									"validation-form"
										enctype="multipart/form-data" method="post">
					<div class="row">
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										
										<th align="left"><input type="checkbox"
													onClick="selectAll(this)"
													  /> Select All</th>
										<th>Sr.No.</th>
										<th>RM Name</th>
										<th>Re Order</th>
										<th>Max Order</th>
										<th>Current Stock Qty</th>
										 <th>Order Qty</th>
									 


									</tr>
								</thead>
								
								<tbody>

									<c:forEach items="${rmList}" var="rmList"
													varStatus="count">

											 	
													 
													
												 
												 
												 <c:forEach items="${rmStockList}" var="rmStockList" >
													
													
													<c:choose>
													<c:when test="${rmList.rmId==rmStockList.rmId}">
													 
													<tr>
													<c:choose>
													<c:when test="${rmStockList.closingQty < rmList.rmRolQty}">
													 <td><input type="checkbox" name="select_to_approve"
																id="select_to_approve" checked readonly
																value="${rmList.rmId}"  
																 ></td>
																 <c:set var="orderQty" value="${rmList.rmMaxQty-rmStockList.closingQty}" />
																  
													</c:when>
													 
													 
													
													<c:otherwise>
													  <td><input type="checkbox" name="select_to_approve"
																id="select_to_approve"
																value="${rmList.rmId}"  
																onchange="selectCheck(${rmList.rmId})" ></td>
																  <c:set var="orderQty" value="0" />
																   
													
													</c:otherwise>
													</c:choose>  
													
													
													
													
														<td  ><c:out value="${count.index+1}" /></td>

														<td align="left"  ><c:out
																value="${rmList.rmName}" /></td>
																
																<td align="right"  ><c:out
																value="${rmList.rmRolQty}" /></td>
																
																 
													  
												 	 <td align="right"  >${rmList.rmMaxQty}  
																<input type="hidden" name='rmMaxQty${rmList.rmId }' class='form-control' readonly id='rmMaxQty${rmList.rmId }'   value="${rmList.rmMaxQty}">
																</td>
												 	
													<td align="right"  >${rmStockList.closingQty}
																<input type="hidden" name='closingQty${rmList.rmId }' class='form-control' readonly id='closingQty${rmList.rmId }'   value=<c:out value = "${rmStockList.closingQty}"/>>
																</td>
																
																 <c:choose>
													<c:when test="${rmStockList.closingQty < rmList.rmRolQty}">
													<td align="right"><input type=text name='orderQty${rmList.rmId}' onkeyup="validation('${rmList.rmId}')" class='form-control'   id='orderQty${rmList.rmId }'   value=<c:out value = "${orderQty}"/>>
																</td> 
																  
													</c:when>
													 
													 
													
													<c:otherwise>
													  <td align="right"><input type=text name='orderQty${rmList.rmId}' onkeyup="validation('${rmList.rmId}')" class='form-control' readonly id='orderQty${rmList.rmId }'   value=<c:out value = "${orderQty}"/>>
																</td>
																   
													
													</c:otherwise>
													</c:choose>
													  <%-- <td align="right"><input type=text name='orderQty${rmList.rmId}' onkeyup="validation('${rmList.rmId}')" class='form-control' readonly id='orderQty${rmList.rmId }'   value=<c:out value = "${orderQty}"/>>
																</td> --%>  	
														
												 
												
													 	</tr>
															 </c:when>
															 </c:choose>
															</c:forEach>	
							 
															
																 
												</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
			<div class="row">
						<div class="col-md-12" style="text-align: center">
							<input type="submit" class="btn btn-info" value="Submit" name="Submit" id="Submit">

						</div>
					</div>
					</form>
		</div>
		
		
		
				 
	 
	</div>

	</div>
	<!-- END Main Content -->

	<footer>
	<p>2017 © Monginis.</p>
	</footer>

	<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
		class="fa fa-chevron-up"></i></a>

 

	

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
		
		
		
		<script>
	
		function selectAll(source) {
			checkboxes = document.getElementsByName('select_to_approve');
			
			for (var i = 0, n = checkboxes.length; i < n; i++) {
				checkboxes[i].checked = source.checked;
			}
			
		}
		
		function selectCheck(id)
		{
		 
			var x=document.getElementById("orderQty"+id).readOnly;
			if(x)
			document.getElementById("orderQty"+id).readOnly=false;
			else{
				document.getElementById("orderQty"+id).readOnly=true;
				document.getElementById("orderQty"+id).value=0;
			}
		}
		function validation(id)
		{
			  
			var maxQty=parseFloat(document.getElementById("rmMaxQty"+id).value); 
			var stockQty=parseFloat(document.getElementById("closingQty"+id).value); 
			var orderQty=parseFloat(document.getElementById("orderQty"+id).value); 
			if((maxQty-stockQty)<orderQty)
				{
				document.getElementById("orderQty"+id).value=maxQty-stockQty;
				alert("You Cannot Give Order Greater Than Max Qty ");
				} 
			else{
				document.getElementById("orderQty"+id).value=orderQty;
			}
			if(isNaN(orderQty))
				{
				document.getElementById("orderQty"+id).value=0;
				}
		}
		
		</script>
		
		
</body>
</html>