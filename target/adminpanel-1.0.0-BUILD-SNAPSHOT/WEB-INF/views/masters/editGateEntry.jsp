<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
  
<body onload="startTime()">

<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
<c:url var="addRmitemInEditGateEntry" value="/addRmitemInEditGateEntry"></c:url>
<c:url var="editRmQtyinEditGate" value="/editRmQtyinEditGate"></c:url>
	<c:url var="deleteRmIteminEditGate" value="/deleteRmIteminEditGate"></c:url>
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
						<i class="fa fa-file-o"></i>Gate Entry
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
								<i class="fa fa-bars"></i>Edit Gate Entry
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/gateEntries">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>




						<div class="box-content">
							<form action="${pageContext.request.contextPath}/submitEditGateEntry" class="form-horizontal"
								method="post" id="validation-form" enctype="multipart/form-data">

                  <div class="form-group">
								<div class="col1">
								 <label class="col-sm-1 col-lg-2 control-label" for="Inward No" >InwardNo:</label>
								 <div class="col-md-1">
										<input type="text" name="mrn_no" id="mrn_no" value="${materialRecNote.mrnNo}"
											 class="form-control"
											readonly />
													<div> <input type="hidden" name="prevImage1" value="${materialRecNote.photo1}"></div>
													<div> <input type="hidden" name="prevImage2" value="${materialRecNote.photo2}"></div>
													
											
									</div>
								 
								</div>
                               <div class="col1">
									<label class="col-sm-1 col-lg-1 control-label" for="Date">Date:</label>
									<label class="col-sm-1 col-lg-2 control-label" for="Date">${materialRecNote.gateEntryDate}
                                    
                                   </label>
								</div>
								 <div class="col1">
									<label class="col-sm-1 col-lg-1 control-label" for="Time">Time:</label>
                                    <label class="col-sm-1 col-lg-2 control-label" for="Date">${materialRecNote.gateEntryTime}</label>			
 					            </div>
								<div class="col1">
									

								</div>
                             
                   </div>
                    
                                 <div class="col1">
									<label class="col-sm-1 col-lg-2 control-label" for="Supplier">Supplier</label>
									<div class="col-sm-1 col-lg-3 controls">
										<select data-placeholder=""
											class="form-control chosen" name="supp_id" tabindex="-1"
											id="supp_id" data-rule-required="true">
											<c:forEach items="${supplierList}" var="supplierList"
													varStatus="count">
																<c:choose>
													<c:when test="${materialRecNote.supplierId==supplierList.suppId}">
													<option selected value="${supplierList.suppId}">${supplierList.suppName}</option>
													</c:when>
													 </c:choose>
													 </c:forEach>
											
											
											
											
											
											
											<%-- <option selected>${materialRecNote.supplierId}</option> --%>
											<c:forEach items="${supplierList}" var="supplierList">
                                              
                                              
										<option value="${supplierList.suppId}"><c:out value="${supplierList.suppName}"></c:out> </option>
													

											</c:forEach>
											</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-1 col-lg-2 control-label" for="Vehicle No">Vehicle No</label>
									<div class="col-sm-1 col-lg-3 controls">
										<input type="text" name="vehicle_no" value="${materialRecNote.vehicleNo}" id="vehicle_no"
											placeholder="Vehicle No" class="form-control" required/>
									</div>
								</div>
								<div class="col1">
									<label class="col-sm-1 col-lg-2 control-label" for="LR No">LR No</label>
									<div class="col-sm-1 col-lg-3 controls">
										<input type="text" name="lr_no" id="lr_no" value="${materialRecNote.lrNo}"
											placeholder="LR No" class="form-control" pattern="\d+" 
											required/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-1 col-lg-2 control-label" for="Transporter">Transporter</label>
									<div class="col-sm-1 col-lg-3 controls">
										<select data-placeholder="Select Transporter"
											class="form-control chosen" name="tran_id" tabindex="-1"
											id="tran_id" data-rule-required="true">
											<c:forEach items="${transport}" var="transport"
													varStatus="count">
																<c:choose>
													<c:when test="${materialRecNote.transportId==transport.tranId}">
													<option selected value="${transport.tranId}">${transport.tranName}</option>
													</c:when>
													 </c:choose>
													 </c:forEach>
													 
											<c:forEach items="${transport}" var="transport">
														<option value="${transport.tranId}"><c:out value="${transport.tranName}"></c:out> </option>
													
											</c:forEach>
											</select>
									</div>
								</div>	
								 <div class="col1">
									<label class="col-sm-1 col-lg-2 control-label" for="Description of Goods">Description</label>
									<div class="col-sm-1 col-lg-3 controls">
										<input type="text" name="description_of_goods" id="description_of_goods" value="${materialRecNote.gateRemark}"
											placeholder="Description of Goods" class="form-control"
											>
									</div>
								</div>
								 <div class="form-group">
									<label class="col-sm-1 col-lg-2 control-label" for="No of Items">No of Items</label>
									<div class="col-sm-1 col-lg-3 controls">
										<input type="text" name="no_of_items" id="no_of_items"
										 value="${materialRecNote.noOfItem}"
											placeholder="No of Items" class="form-control"
											pattern="\d+" required/>
									</div>
								</div>
								 <div class="col1">
									<label class="col-sm-1 col-lg-2 control-label" for="Description of Goods">Remark</label>
									<div class="col-sm-1 col-lg-3 controls">
										<input type="text" name="remark" id="remark" value="${materialRecNote.gateRemark}"
											placeholder="Remark" class="form-control"
											data-rule-required="true">
									</div>
								</div>
								
								<div class="form-group">
									
								</div>
									<div class="col1">
									<label class="col-sm-2 col-lg-2 control-label">Image ${imageUrl}${materialRecNoteHeader.photo2}</label>
									<div class="col-sm-2 col-lg-4 controls">
										<div class="fileupload fileupload-new"
											data-provides="fileupload">
											<div class="fileupload-new img-thumbnail"
												style="width: 200px; height: 150px;">
												<img src="${imageUrl}${materialRecNote.photo1}"
											onerror="this.src='${pageContext.request.contextPath}/resources/img/No_Image_Available.jpg';"
											alt="" />
											</div>
											<div
												class="fileupload-preview fileupload-exists img-thumbnail"
												style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
											<div>
												<span class="btn btn-default btn-file"><span
													class="fileupload-new">Select image</span> <span
													class="fileupload-exists">Change</span> <input type="file"
													class="file-input" name="image1" id="image2"
													 /></span> <a href="#"
													class="btn btn-default fileupload-exists"
													data-dismiss="fileupload">Remove</a>
											</div>
										</div>

									</div>
								</div>
									<div class="form-group">
									<label class="col-sm-2 col-lg-1 control-label">Image</label>
									<div class="col-sm-2 col-lg-4 controls">
										<div class="fileupload fileupload-new"
											data-provides="fileupload">
											<div class="fileupload-new img-thumbnail"
												style="width: 200px; height: 150px;">
												<img src="${imageUrl}${materialRecNote.photo2}"
											onerror="this.src='${pageContext.request.contextPath}/resources/img/No_Image_Available.jpg';"
											alt="" />
											</div>
											<div
												class="fileupload-preview fileupload-exists img-thumbnail"
												style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
											<div>
												<span class="btn btn-default btn-file"><span
													class="fileupload-new">Select image</span> <span
													class="fileupload-exists">Change</span> <input type="file"
													class="file-input" name="image2" id="image2"
													 /></span> <a href="#"
													class="btn btn-default fileupload-exists"
													data-dismiss="fileupload">Remove</a>
											</div>
										</div>

									</div>
								</div>	
								
								
								
								<hr/>
								<div class="box-content">
									<div class="col-md-2" >Select Raw Material Name</div>
									<div class="col-md-2">
										<select data-placeholder="Select RM Name" class="form-control chosen" name="rm_id" tabindex="-1"
											id="rm_id">
											<option selected value="">Select Raw Material Name</option>
											
											<c:forEach items="${rmlist}" var="rmlist">
                                              
                                              
														<option value="${rmlist.rmId}"><c:out value="${rmlist.rmName}"></c:out> </option>
													

											</c:forEach>
											</select>
									</div>
									
									
									<div class="col-md-2" for="rm_qty">Qty</div>
									<div class="col-md-2">
										<input type="text" name="rm_qty" id="rm_qty"
											placeholder="Qty" class="form-control"
											 />
												 
									</div>
									
									
									
									<div class="col-md-2">
									
								<div><input type="button" class="btn btn-info pull-right" onclick="addItem()" value="Add Item"></div>
					 
									</div>
								</div><br><br><br><br>
								<div align="center" id="loader" style="display: none">

									<span>
										<h4>
									<font color="#343690">Loading</font>
									</h4>
									</span> <span class="l-1"></span> <span class="l-2"></span> <span
						class="l-3"></span> <span class="l-4"></span> <span class="l-5"></span>
							<span class="l-6"></span>
							</div>
								
							<div class=" box-content">
							<div class="row">
								<div class="col-md-12 table-responsive">
									<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Name</th>
										<th>Qty</th>
										<th>Action</th>

									</tr>
								</thead>
								<tbody>
								
								 <c:set var = "srNo" value="0"/>
									<c:forEach items="${materialRecNoteDetail}" var="materialRecNoteDetail"
													varStatus="count">

													<tr>
														<td><c:out value="${count.index+1}" /></td>
 														<c:set var = "srNo" value="${srNo+1}"/>
														<td align="left"><c:out
																value="${materialRecNoteDetail.rmName}" /></td>


														<td><input type="text" id="recdQty${count.index}" onkeyup="changeQty(${count.index});" value="${materialRecNoteDetail.recdQty}" class="form-control" disabled="true"></td>
														
														<td align="left"><span class='glyphicon glyphicon-edit' onclick="edit(${count.index})" id="edit${count.index}"></span>
																
		                                                    <span  style="visibility: hidden;" class="glyphicon glyphicon-ok" onclick="submit(${count.index})" id="ok${count.index}"></span>
		                                                    <span class="glyphicon glyphicon-remove" onclick="del(${count.index})" id="del${count.index}"></span>
     														 </td>
															
												</tr>
												</c:forEach>

								</tbody>
							</table>
						</div>
					</div>
								</div>
								
								
								
								<div class=" box-content">
							<div class="row">
								<div class="col-md-12 table-responsive">
									
							
							<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Submit">
<!-- 										
 -->									</div>
								</div>
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
		function addItem() {

		 

			  
				var rm_id = $("#rm_id").val();
				var rm_qty = $("#rm_qty").val();
				
				
				$('#loader').show();

				$
						.getJSON(
								'${addRmitemInEditGateEntry}',

								{
									 
									rm_id : rm_id,
									rm_qty : rm_qty,
									ajax : 'true'

								},
								function(data) {

									$('#table_grid td').remove();
									$('#loader').hide();

									if (data == "") {
										alert("No records found !!");

									}
								 
  
								  $.each(
												data,
												function(key, itemList) {
													
													if(itemList.delStatus==0)
													{

													var tr = $('<tr></tr>');

												  	tr.append($('<td></td>').html(key+1));
												  	tr.append($('<td></td>').html(itemList.rmName));
												  	tr.append($('<td></td>').html('<input type="text" id="recdQty'+key+'" onkeyup="changeQty('+key+');" value="'+itemList.recdQty+'" class="form-control" disabled="true">'));
												  	tr.append($('<td></td>').html('<span class="glyphicon glyphicon-edit" id="edit'+key+'" onclick="edit('+key+');"> </span><span style="visibility: hidden;" class="glyphicon glyphicon-ok" onclick="submit('+key+');" id="ok'+key+'"></span><span class="glyphicon glyphicon-remove"  onclick="del('+key+')" id="del'+key+'"></span>'));
												    $('#table_grid tbody').append(tr);

													}
 
												})  
								});

			 
		}
		
		function edit(key)
		{
			//alert(key);
			document.getElementById("recdQty"+key).disabled = false;
			document.getElementById("edit"+key).style.visibility="hidden";
			document.getElementById("ok"+key).style.visibility="visible";
			
			//style="visibility: hidden;"
		}
		function submit(key)
		{
			//alert("key"+key);
			var qty=document.getElementById("recdQty"+key).value;
			document.getElementById("recdQty"+key).disabled = true;
			document.getElementById("edit"+key).style.visibility="visible";
			document.getElementById("ok"+key).style.visibility="hidden";
			alert(qty);
			$
			.getJSON(
					'${editRmQtyinEditGate}',

					{
						 
						index : key,
						updateQty : qty,
					
						ajax : 'true'

					},
					function(data) {
						
					});
			
			
		}
		function del(key)
		{
			//alert("key1"+key);
			var key=key;
			$('#loader').show();
			$
			.getJSON(
					'${deleteRmIteminEditGate}',

					{
						 
						index : key,
						ajax : 'true'

					},
					function(data) {
						
						$('#table_grid td').remove();
						$('#loader').hide();

						if (data == "") {
							alert("No records found !!");

						}
					 

					  $.each(
									data,
									function(key, itemList) {
									
								if(itemList.delStatus==0)
									{
										var tr = $('<tr></tr>');
										var tr = $('<tr></tr>');
										tr.append($('<td></td>').html(key+1));
										tr.append($('<td></td>').html(itemList.rmName));
									  	tr.append($('<td></td>').html('<input type="text" id="recdQty'+key+'" onkeyup="changeQty('+key+');" value="'+itemList.recdQty+'" class="form-control" disabled="true">'));
									  	tr.append($('<td></td>').html('<span class="glyphicon glyphicon-edit" id="edit'+key+'" onclick="edit('+key+');"> </span><span style="visibility: hidden;" class="glyphicon glyphicon-ok" onclick="submit('+key+');" id="ok'+key+'"></span><span class="glyphicon glyphicon-remove"  onclick="del('+key+')" id="del'+key+'"></span>'));
									    $('#table_grid tbody').append(tr);
									}
										
									  	
									})
						
					});
			
			
		}
	</script>
</body>
</html>