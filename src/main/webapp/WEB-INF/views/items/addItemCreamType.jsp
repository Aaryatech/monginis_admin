<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
	
	<c:url var="getItemsByCatId" value="/getItemsByCatId" />

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
						<i class="fa fa-file-o"></i>Item Cream Type
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
								<i class="fa fa-bars"></i> Add Item Cream Type
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showItemCreamType">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>


						<div class="box-content">
							<form action="${pageContext.request.contextPath}/addItemCreamType" class="form-horizontal"
								method="post" id="validation-form">	                   
								<input type="hidden" name="item_cream_id" id="item_cream_id" value="${itemCream.itemCreamId}"/>
								
							<div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Category</label>
									<div class="col-sm-9 col-lg-3 controls">
									<select name="cat_id" id="cat_id" class="form-control" placeholder="Select Category">
											<option value="-1">Select Category</option>											
										 <c:forEach items="${mCategoryList}" var="mCategoryList">
											<c:choose>
											 	<c:when test="${itemCream.categoryId==mCategoryList.catId}">
											       <option selected="selected" value="${mCategoryList.catId}"><c:out value="${mCategoryList.catName}"></c:out></option>
												</c:when>
												<c:otherwise>
													<option value="${mCategoryList.catId}"><c:out value="${mCategoryList.catName}"></c:out></option>
												</c:otherwise>
											</c:choose>
										</c:forEach> 
												
								</select>	
									</div>
								</div>
							  <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Name</label>
									<div class="col-sm-9 col-lg-3 controls">
											<input type="text" name="item_cream_name" id="item_cream_name"
											placeholder="Item Cream Type" class="form-control"
											data-rule-required="true" value="${itemCream.itemCreamName}"/>
									</div>
							  </div> 
							  
							  <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Sequence No.</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="sequence_no" id="sequence_no"
											placeholder="Sequence No." class="form-control"
											data-rule-required="true" data-rule-number="true"value="${itemCream.sequenceNo}"/>
									</div>
							  </div>
							  
							 
								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Submit">
<!-- 										<button type="button" class="btn">Cancel</button>
 -->									</div>
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


</body>
<script type="text/javascript">
$(document).ready(function() { 
	$('#cat_id').change(
			function() {
				
				$.getJSON('${getItemsByCatId}', {
					cat_id : $(this).val(),
					ajax : 'true'
				}, function(data) {
					var html = '<option value="-1"selected >Select Item</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].id + '">'
								+ data[i].itemName + '</option>';
					}
					html += '</option>';
					$('#item_id').html(html);

				});
			});
});
</script>
<script type="text/javascript">
$(document).ready(function() { 
	$('#item_id').change(
			function() {
				document.getElementById('sel_item_id').value=$(this).val();
				
				document.getElementById('item_name').value=$('#item_id option:selected').text();

			});
});
</script>
<script type="text/javascript">

			function uomChanged() {
				
				document.getElementById('uom').value=$('#item_uom option:selected').text();
				
			}
</script>
</html>
