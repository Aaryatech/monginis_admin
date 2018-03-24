<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
 <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
 <jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
<body>
	<%-- 	<c:url var="getRmSubCategory" value="/getRmSubCategory" />
	
	<c:url var="insertSfItemDetail" value="/insertSfItemDetail" />
	<c:url var="getRmCategory" value="/getRmCategory" />

	<c:url var="getItemDetail" value="/getItemDetail" />
		<c:url var="getSingleItem" value="/getSingleItem" /> --%>

	<c:url var="getMaterial" value="/getMaterial" />

	<c:url var="manBomAddItem" value="/manBomAddItem" />

	<c:url var="getMatUom" value="/getMatUom" />

	<c:url var="deleteBomDetail" value="/deleteBomDetail" />

	<c:url var="insertBomHeader" value="/insertBomHeader" />

 


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
						<i class="fa fa-file-o"></i>Manual BOM Request
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
								<i class="fa fa-bars"></i>Add Manual Bom - <b>-</b>
							</h3>
							<div class="box-tool">
								<a href="">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>

						</div>
						<div class="box-content">
							<form action="" method="post" class="form-horizontal"
								id="validation-form" method="post">

								<div class="form-group">


									<c:choose>
										<c:when test="${isProd==1}">
											<h4 align="center">Prod Header Id ${prodHeaderId} Prod
												Date ${prodDate}</h4>
										</c:when>
										<c:otherwise>

											<h4 align="center">Mix Header Id ${prodHeaderId} Mix
												Date ${prodDate}</h4>

										</c:otherwise>
									</c:choose>


									<label class="col-sm-3 col-lg-2 control-label"> From
										Dept</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="from_dept" id="from_dept" class="form-control"
											placeholder="Material Type" data-rule-required="true">
											<option value="0">Select From Dept</option>

											<c:forEach items="${deptList}" var="dept">
												<option value="${dept.deptId}">${dept.deptName}</option>
											</c:forEach>

										</select>
									</div>


									<label class="col-sm-3 col-lg-2 control-label"> To Dept</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="to_dept" id="to_dept" class="form-control"
											placeholder="Material Type" data-rule-required="true">
											<option value="0">Select To Dept</option>
											<c:forEach items="${deptList}" var="dept">
												<option value="${dept.deptId}">${dept.deptName}</option>
											</c:forEach>
										</select>
									</div>

								</div>


								<div class="form-group">

									<label class="col-sm-3 col-lg-2 control-label">
										Material Type</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="material_type" id="material_type"
											class="form-control" placeholder="Material Type"
											data-rule-required="true">
											<option value="0">Select Material Type</option>
											<option value="1">RM</option>
											<option value="2">SF</option>
										</select>
									</div>

									<label class=" col-sm-3 col-lg-2 control-label">
										Material Name</label>
									<div class="col-sm-6 col-lg-4 controls" id="chooseRM">
										<select name="rm_material_name" id="rm_material_name"
											class="form-control" placeholder="Material Name"
											data-rule-required="true">
											<option value="-1">Select Material</option>

										</select>
									</div>

								</div>

								<div class="form-group">

									<label class="col-sm-3 col-lg-2 control-label">UOM</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="uom" id="uom" class="form-control"
											placeholder="UOM" data-rule-required="true" />

									</div>

									<label class="col-sm-3 col-lg-2 control-label">RM Req
										Qty</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="rm_req_qty" id="rm_req_qty"
											class="form-control" placeholder="Qty"
											data-rule-required="true"
											onKeyPress="return isNumberCommaDot(event)" />
									</div>

								</div>



								<div class="row">
									<div class="col-md-12" style="text-align: center">
										<input type="button" class="btn btn-info" value="Submit Items"
											onclick="submitItem()">

									</div>
								</div>

								<div class="clearfix"></div>
								<div class="table-responsive" style="border: 0">
									<table width="100%" class="table table-advance" id="table1">
										<thead>
											<tr>

												<th width="140" style="width: 30px" align="left">Sr No</th>
												<th width="138" align="left">Material Name</th>
												<th width="120" align="left">Material Type</th>
												<th width="120" align="left">Qty</th>
												<th width="120" align="left">UOM</th>
												<th width="120" align="left">Action</th>

											</tr>

										</thead>

										<tbody>

											<%-- <c:forEach items="${sfDetailList}" var="sfDetailList"
												varStatus="count">

												<c:choose>
												
												
												<tr>
												
												<td><c:out value="${count.index+1}" /></td>

													<td align="left"><c:out
															value="${sfDetailList.rmName}" /></td>
															
															<c:choose>
															<c:when test="${sfDetailList.rmType == 1}">
															<td align="left"><c:out
															value="RM" /></td>
															</c:when>
															
															<c:when test="${sfDetailList.rmType == 2}">
															<td align="left"><c:out
															value="SF" /></td>
															</c:when>
															</c:choose>
															<td align="left"><c:out
															value="${sfDetailList.rmQty}" /></td>
															
															<td align="left"><c:out
															value="${sfDetailList.rmWeight}" /></td>

													<td>
													
													<input type="button" id="delete" onClick="deleteSfDetail(${count.index})" value="Delete"> <input type="button" id="edit" onClick="editSfDetail(${count.index})" value="Edit">
													
													</td>
													</tr>
												
												
													<a
														href="${pageContext.request.contextPath}/delete/${sfDetailList.rmId}"
														class="action_btn"> <abbr title="Delete"><i
																class="fa fa-list"></i></abbr></a>
												
											</c:forEach>
 --%>
										</tbody>
									</table>
								</div>

								<input type="button" class="btn btn-info" value="Submit List"
									onclick="insertItemDetail()"> <input type="hidden"
									id="prodHeaderId" value="${prodHeaderId}"> <input
									type="hidden" id="prodDate" value="${prodDate}">

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
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/common.js"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>


	<script type="text/javascript">
function submitItem() {
				
				var materialType = $('#material_type').val();
				var materialName=$("#rm_material_name option:selected").html();
				var materialNameId= $('#rm_material_name').val(); 
				
				var uom = document.getElementById("uom").value;
				var rmReqQty = document.getElementById("rm_req_qty").value;
				
				$.getJSON('${manBomAddItem}', {
					 mat_type : materialType,
					mat_name_id : materialNameId,
					mat_name : materialName,
					uom : uom,
					qty : rmReqQty,
					 
					ajax : 'true',

				},function(data) {
					
					var len = data.length;
					$('#table1 td').remove();
					
					$.each(data,function(key, bomDetail) {
					
					var tr = $('<tr></tr>');
					var rmTypeName;
					if(bomDetail.rmType == 1){
						rmTypeName="RM";
					}else if(bomDetail.rmType == 2){
						rmTypeName="SF";
					}

					if(bomDetail.delStatus == 0){
						
				  	tr.append($('<td></td>').html(key+1));

				  	tr.append($('<td></td>').html(bomDetail.rmName));

				  	tr.append($('<td></td>').html(""+rmTypeName));

				  	tr.append($('<td></td>').html(bomDetail.rmReqQty));

				  	tr.append($('<td></td>').html(bomDetail.uom));
				  
				  	tr.append($('<td></td>').html("<input type=button id=delete onClick=deleteBomDetail("+key+"); Value=Delete> "));
					}
					$('#table1 tbody').append(tr);

					})

					});

				
				}
				
				function deleteBomDetail(key){
					
					$.getJSON('${deleteBomDetail}',
							{
								key:key,
								ajax : 'true',
							},function(data) {
								
								var len = data.length;
								$('#table1 td').remove();
								
								$.each(data,function(key, bomDetail) {
								
								var tr = $('<tr></tr>');
								var rmTypeName;
								if(bomDetail.rmType == 1){
									rmTypeName="RM";
								}else if(bomDetail.rmType == 2){
									rmTypeName="SF";
								}

								if(bomDetail.delStatus == 0){
									
							  	tr.append($('<td></td>').html(key+1));

							  	tr.append($('<td></td>').html(bomDetail.rmName));

							  	tr.append($('<td></td>').html(""+rmTypeName));

							  	tr.append($('<td></td>').html(bomDetail.rmReqQty));

							  	tr.append($('<td></td>').html(bomDetail.uom));
							  
							  	tr.append($('<td></td>').html("<input type=button id=delete onClick=deleteBomDetail("+key+"); Value=Delete> "));
								}
								$('#table1 tbody').append(tr);

								})

								});

							
				}

</script>

	<script type="text/javascript">

function insertItemDetail(){
	
	alert("inside Bom Header Insert ");
	
	var fromDeptName=$("#from_dept option:selected").html();
	
	var toDeptName=$("#to_dept option:selected").html();

	var prodHeaderId= $('#prodHeaderId').val();
	//var prodHeaderId = document.getElementById("prodHeaderId").value;

	var prodDate=$('#prodDate').val();
	
//salert(prodDate);
alert(prodHeaderId);
alert(prodDate);

	var fromDept = $('#from_dept').val();
	var toDept = $('#to_dept').val();
	
	$.getJSON('${insertBomHeader}',
			{
		fromDeptName:fromDeptName,
		toDeptName:toDeptName,
		fromDept:fromDept,
		toDept:toDept,
		headerId:prodHeaderId,
		prodDate :prodDate,
		ajax : 'true',
});
	
	alert("Inserted Suucessfully");
	$('#table1 td').remove();
}

</script>

	<script type="text/javascript">
$(document).ready(function() { 
	$('#material_type').change(
			function() {
				

	$.getJSON('${getMaterial}', {
		material_type : $(this).val(),
					
					ajax : 'true',
				},  function(data) {
					var html = '<option value="" selected >Select Raw Material</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].id + '">'
								+ data[i].name + '</option>';
					}
					html += '</option>';
					$('#rm_material_name').html(html);
					
				});
			});

});
</script>

	<script type="text/javascript">
$(document).ready(function() { 
	$('#rm_material_name').change(
			function() {

	$.getJSON('${getMatUom}', {
		rm_material_name : $(this).val(),
					
					ajax : 'true',
				},  function(data) {
					
	
	var uom=data.uom;

	document.getElementById("uom").setAttribute('value',data.uom);

				})
					
					})
					
			});
			

</script>


</body>
</html>