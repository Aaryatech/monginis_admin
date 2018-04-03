<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
	
	<c:url var="machineListOrVehicleList" value="/machineListOrVehicleList"></c:url>
	<c:url var="getTypeList" value="/getTypeList"></c:url>
 


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
						<i class="fa fa-file-o"></i>Upload Rule File
					</h1>
				</div>
			</div>
			<!-- END Page Title -->

			<div class="row">
				<div class="col-md-12">

					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i>Upload Rule File
							</h3>
							
						 
							
						</div>
						  
						
						<div class="box-content">

							<form id="submitMaterialStore" action="${pageContext.request.contextPath}/submitUploadFile" method="post"
							enctype="multipart/form-data">
							 
							   
							 
							<div class="box-content">
							 
									<div class="col-md-2" >Select PDF Rules And Regulation </div>
												<div class="col-md-4"> 
													<input type="file" id="attachFile1" name="attachFile1" size="60" > 
													<input type="hidden" value="${document1}" id="document1" name="document1" size="60"  >
													 
												</div>
												<div class="col-md-6"> 
												Last Upload Date: ${date1}
													<a href="${url}${document1}" target="blank" ><input type="button" value="View Document File" class="btn btn-info">
										</a>
												</div>
									</div><br>	<br>
								<div class="box-content">				
													<div class="col-md-2" >Select PDF Product Information </div>
									<div class="col-md-4"> 
													<input type="file" id="attachFile2" name="attachFile2" size="60"  > 
													<input type="hidden" value="${document2}" id="document2" name="document2" size="60" >
													 
												</div>
												<div class="col-md-6"> 
													 Last Upload Date: ${date2}
													<a href="${url}${document2}" target="blank" ><input type="button" value="View Document File" class="btn btn-info">
										</a><br>
												</div>
							
							</div><br><br>
							
							<div class="box-content">				
													<div class="col-md-2" >Select PDF SpCake Undertaking </div>
									<div class="col-md-4"> 
													<input type="file" id="attachFile5" name="attachFile5" size="60"  > 
													<input type="hidden" value="${document5}" id="document5" name="document5" size="60" >
													 
												</div>
												<div class="col-md-6"> 
													 Last Upload Date: ${date5}
													<a href="${url}${document5}" target="blank" ><input type="button" value="View Document File" class="btn btn-info">
										</a><br>
												</div>
							
							</div><br><br>
							
							<div class="box-content">
							 
									<div class="col-md-2" >Select Word File Rules And Regulation </div>
												<div class="col-md-4"> 
													<input type="file" id="attachFile3" name="attachFile3" size="60"  >   
													<input type="hidden" value="${document3}" id="document3" name="document3" size="60"  >
													 
												</div>
											 
												<div class="col-md-4"> 
													 Last Upload Date: ${date3}
													<a href="${url}${document3}" target="blank" ><input type="button" value="Download File" class="btn btn-info">
										</a>
												</div>
										</div><br>	<br>
								<div class="box-content">				
													<div class="col-md-2" >Select Word File Product Information </div>
									<div class="col-md-4"> 
													<input type="file" id="attachFile4" name="attachFile4" size="60"  > 
													<input type="hidden" value="${document4}" id="document4" name="document4" size="60"  >
													 
												</div>
												
												<div class="col-md-4"> 
													 Last Upload Date: ${date4}
													<a href="${url}${document4}" target="blank" ><input type="button" value="Download File" class="btn btn-info">
										</a>
												</div>
							
							</div><br><br>
							
							<div class="box-content">				
													<div class="col-md-2" >Select Word File SpCake UnderTaking </div>
									<div class="col-md-4"> 
													<input type="file" id="attachFile6" name="attachFile6" size="60"  > 
													<input type="hidden" value="${document6}" id="document6" name="document6" size="60"  >
													 
												</div>
												
												<div class="col-md-4"> 
													 Last Upload Date: ${date6}
													<a href="${url}${document6}" target="blank" ><input type="button" value="Download File" class="btn btn-info">
										</a>
												</div>
							
							</div><br><br>
							 
								
							<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Submit" onclick="check();">
<!-- 										<button type="button" class="btn">Cancel</button>
 -->									</div>
								</div><br><br>
						
							
							

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
		<script type="text/javascript">
		
		var editItem=0;
		function addPart() {

			         
				var sprId = $("#sprId").val();
				var sprName = $("#sprId option:selected").text();
				var groupId = $("#groupId").val();
				var groupName = $("#groupId option:selected").text();
				var vehId = $("#vehId").val();
				var vehName = $("#vehId option:selected").text();
				var spareRate = $("#spareRate").val();
				var spareQty = $("#spareQty").val();
				var taxaleAmtDetail = $("#taxaleAmtDetail").val();
				var taxAmtDetail = $("#taxAmtDetail").val();
				var discDetail = $("#discDetail").val();
				var extraChargeDetail = $("#extraChargeDetail").val();
				var totalDetail = $("#totalDetail").val();
				var servTypeDetail = $("#servTypeDetail").val();
				var dealerId = $("#dealerId").val(); 
				var discPer = $("#discPer").val();
				var extraChargePer = $("#extraChargePer").val(); 
				var taxPer = $("#taxPer").val(); 
				var labourGroupId = $("#labourGroupId").val();
				
				if(validation()==true){	
				
					if(editItem==0)
						{
						//alert("in if"+editItem);
						$('#loader').show();

						$ .getJSON(
										'${addSparePart}',

										{
											 
											sprId : sprId,
											sprName : sprName,
											groupId : groupId,
											groupName : groupName,
											vehId : vehId,
											vehName : vehName,
											spareRate : spareRate,
											spareQty : spareQty,
											taxaleAmtDetail : taxaleAmtDetail,
											taxAmtDetail : taxAmtDetail,
											discDetail : discDetail,
											extraChargeDetail : extraChargeDetail,
											totalDetail : totalDetail,
											servTypeDetail : servTypeDetail,
											discPer : discPer,
											extraChargePer : extraChargePer,
											taxPer : taxPer,
											ajax : 'true'

										},
										function(data) {

											$('#table_grid td').remove();
											$('#loader').hide();

											if (data == "") {
												alert("No records found !!");

											}
										 
											
											var taxAmt=0;
											var totSparePart=0;
											var disc=0;
											var extraCharge=0;
											var total=0;
											var labourCharge=0;
		  									var taxaleAmt=0
										  $.each( data, function(key, itemList) {
														
														 
															 var tr = $('<tr></tr>');

														  	tr.append($('<td></td>').html(key+1));

														  	tr.append($('<td></td>').html(itemList.partName)); 
														  	tr.append($('<td></td>').html(itemList.groupName));
														  	tr.append($('<td></td>').html(itemList.sprQty));
														  	tr.append($('<td></td>').html(itemList.sprRate));
														  	tr.append($('<td></td>').html(itemList.sprTaxableAmt));
														  	taxaleAmt=taxaleAmt+itemList.sprTaxableAmt;
														  	tr.append($('<td></td>').html(itemList.disc));
														  	disc=disc+itemList.disc;
														  	tr.append($('<td></td>').html(itemList.extraCharges));
														  	extraCharge=extraCharge+itemList.extraCharges;
														  	tr.append($('<td></td>').html(itemList.sprTaxAmt));
														  	taxAmt=taxAmt+itemList.sprTaxAmt;  
														  	tr.append($('<td></td>').html(itemList.total));
														  	total=total+itemList.total;
														  	if(itemList.groupId==labourGroupId)
													  		{
													  		 
													  		labourCharge=labourCharge+itemList.total;
													  		}
													  	else
													  		{
													  		 
													  		totSparePart=totSparePart+itemList.total;
													  		}
														  	tr.append($('<td></td>').html('<span class="glyphicon glyphicon-edit" id="edit'+key+'" onclick="edit('+key+');"> </span><span style="visibility: hidden;" class="glyphicon glyphicon-ok" onclick="submit('+key+');" id="ok'+key+'"></span><span class="glyphicon glyphicon-remove"  onclick="del('+key+')" id="del'+key+'"></span>'));
														    $('#table_grid tbody').append(tr);

															 
		 
														})  
														    
											document.getElementById("total").value=(total).toFixed(2);
											document.getElementById("totDisc").value=(disc).toFixed(2);
											document.getElementById("totExtraCharge").value=(extraCharge).toFixed(2);
											document.getElementById("taxAmt").value=(taxAmt).toFixed(2);
											document.getElementById("taxaleAmt").value=(taxaleAmt).toFixed(2); 
											document.getElementById("totPart").value=(totSparePart).toFixed(2); 
											document.getElementById("totalfirst").value=(total).toFixed(2);
											document.getElementById("labCharge").value=(labourCharge).toFixed(2);
											//null detail
											
											document.getElementById("spareRate").value="";
											document.getElementById("discPer").value="";
											document.getElementById("extraChargePer").value="";
											document.getElementById("taxPer").value="";
											document.getElementById("discDetail").value=""; 
											document.getElementById("extraChargeDetail").value="";
											document.getElementById("taxaleAmtDetail").value="";
											document.getElementById("taxAmtDetail").value="";
											document.getElementById("totalDetail").value="";	
											document.getElementById("spareQty").value=""; 
											document.getElementById("groupId").value="";  
											$('#groupId').trigger("chosen:updated");
											var html = '<option value="">Select Spare Part</option>';
											$('#sprId').html(html);
											$('#sprId').trigger("chosen:updated");
											document.getElementById("servTypeDetail").value="";
											$('#servTypeDetail').trigger("chosen:updated");
										});

						}
					else
						{
						$('#loader').show();

						$ .getJSON(
										'${changeQtyOfSparePart}',

										{
											 
											sprId : sprId,
											sprName : sprName,
											groupId : groupId,
											groupName : groupName,
											vehId : vehId,
											vehName : vehName,
											spareRate : spareRate,
											spareQty : spareQty,
											taxaleAmtDetail : taxaleAmtDetail,
											taxAmtDetail : taxAmtDetail,
											discDetail : discDetail,
											extraChargeDetail : extraChargeDetail,
											totalDetail : totalDetail,
											servTypeDetail : servTypeDetail, 
											discPer : discPer,
											extraChargePer : extraChargePer,
											taxPer : taxPer,
											ajax : 'true'

										},
										function(data) {

											$('#table_grid td').remove();
											$('#loader').hide();

											if (data == "") {
												alert("No records found !!");

											}
										 
											
											var taxAmt=0;
											var totSparePart=0;
											var disc=0;
											var extraCharge=0;
											var total=0;
											var labourCharge=0;
		  									var taxaleAmt=0
										  $.each( data, function(key, itemList) {
														
														 
															 var tr = $('<tr></tr>');

														  	tr.append($('<td></td>').html(key+1));

														  	tr.append($('<td></td>').html(itemList.partName)); 
														  	tr.append($('<td></td>').html(itemList.groupName));
														  	tr.append($('<td></td>').html(itemList.sprQty));
														  	tr.append($('<td></td>').html(itemList.sprRate));
														  	tr.append($('<td></td>').html(itemList.sprTaxableAmt));
														  	taxaleAmt=taxaleAmt+itemList.sprTaxableAmt;
														  	tr.append($('<td></td>').html(itemList.disc));
														  	disc=disc+itemList.disc;
														  	tr.append($('<td></td>').html(itemList.extraCharges));
														  	extraCharge=extraCharge+itemList.extraCharges;
														  	tr.append($('<td></td>').html(itemList.sprTaxAmt));
														  	taxAmt=taxAmt+itemList.sprTaxAmt;  
														  	tr.append($('<td></td>').html(itemList.total));
														  	total=total+itemList.total;
														  	if(itemList.groupId==labourGroupId)
													  		{
													  		 
													  		labourCharge=labourCharge+itemList.total;
													  		}
													  	else
													  		{
													  		 
													  		totSparePart=totSparePart+itemList.total;
													  		}
														  	tr.append($('<td></td>').html('<span class="glyphicon glyphicon-edit" id="edit'+key+'" onclick="edit('+key+');"> </span><span style="visibility: hidden;" class="glyphicon glyphicon-ok" onclick="submit('+key+');" id="ok'+key+'"></span><span class="glyphicon glyphicon-remove"  onclick="del('+key+')" id="del'+key+'"></span>'));
														    $('#table_grid tbody').append(tr);

															 
		 
														})  
														    
											document.getElementById("total").value=(total).toFixed(2);
											document.getElementById("totDisc").value=(disc).toFixed(2);
											document.getElementById("totExtraCharge").value=(extraCharge).toFixed(2);
											document.getElementById("taxAmt").value=(taxAmt).toFixed(2);
											document.getElementById("taxaleAmt").value=(taxaleAmt).toFixed(2); 
											document.getElementById("totPart").value=(totSparePart).toFixed(2); 
											document.getElementById("totalfirst").value=(total).toFixed(2);
											document.getElementById("labCharge").value=(labourCharge).toFixed(2);
											editItem=0;
											//null detail
											
											document.getElementById("spareRate").value="";
											document.getElementById("discPer").value="";
											document.getElementById("extraChargePer").value="";
											document.getElementById("taxPer").value="";
											document.getElementById("discDetail").value=""; 
											document.getElementById("extraChargeDetail").value="";
											document.getElementById("taxaleAmtDetail").value="";
											document.getElementById("taxAmtDetail").value="";
											document.getElementById("totalDetail").value="";	
											document.getElementById("spareQty").value=""; 
											document.getElementById("groupId").value="";  
											$('#groupId').trigger("chosen:updated");
											var html = '<option value="">Select Spare Part</option>';
											$('#sprId').html(html);
											$('#sprId').trigger("chosen:updated");
											document.getElementById("servTypeDetail").value="";
											$('#servTypeDetail').trigger("chosen:updated");
										});

						}
				
				
			 
		}
				
				
			
	}
		
		function edit(key)
		{
			//alert(key);
			$('#loader').show();

			$
					.getJSON(
							'${editInvoiceSparePart}',

							{
								 
								index : key, 
								ajax : 'true'

							},
							function(data) {

								 
								$('#loader').hide();
 
								document.getElementById("spareRate").value=data.sprRate;
								document.getElementById("discPer").value=data.discPer;
								document.getElementById("extraChargePer").value=data.extraChargesPer;
								document.getElementById("taxPer").value=data.sprTaxAmtPer;
								document.getElementById("discDetail").value=data.disc; 
								document.getElementById("extraChargeDetail").value=data.extraCharges;
								document.getElementById("taxaleAmtDetail").value=data.sprTaxableAmt;
								document.getElementById("taxAmtDetail").value=data.sprTaxAmt;
								document.getElementById("totalDetail").value=data.total;	
								document.getElementById("spareQty").value=data.sprQty; 
								document.getElementById("groupId").value=data.groupId;  
								$('#groupId').trigger("chosen:updated"); 
								document.getElementById("servTypeDetail").value=data.servType;
								$('#servTypeDetail').trigger("chosen:updated");
								 editItem=1;
								//alert("editItem " + editItem);
								$.getJSON('${sparePartByGroupId}', {
									
									groupId : data.groupId,
									ajax : 'true'
								},
										function(data1) {
									 
									var html = '<option value="">Select Part</option>';
									
									var len = data1.length;
									for ( var i = 0; i < len; i++) {
										if(data.sprId==data1[i].sprId)
										{
										 
										html += '<option value="' + data1[i].sprId + '" selected>'
										+ data1[i].sprName + '</option>';
										document.getElementById("sprId").value=data.sprId;
										$('#sprId').trigger("chosen:updated");
										}
									else
										{
										html += '<option value="' + data1[i].sprId + '">'
										+ data1[i].sprName + '</option>';
										}
										
									}
									html += '</option>';
									$('#sprId').html(html);
									$("#sprId").trigger("chosen:updated");
															
										});
							});
		}
		 
		function del(key)
		{
			var labourGroupId = $("#labourGroupId").val();
			var key=key;
			$('#loader').show();
			$
			.getJSON(
					'${deleteSparePart}',

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
						var taxAmt=0;
						var totSparePart=0;
						var labourCharge=0;
						var disc=0;
						var extraCharge=0;
						var total=0;
							var taxaleAmt=0;

						 $.each( data, function(key, itemList) {
								
							 
							 var tr = $('<tr></tr>');

						  	tr.append($('<td></td>').html(key+1)); 
						  	tr.append($('<td></td>').html(itemList.partName)); 
						  	tr.append($('<td></td>').html(itemList.groupName));
						  	tr.append($('<td></td>').html(itemList.sprQty));
						  	tr.append($('<td></td>').html(itemList.sprRate));
						  	tr.append($('<td></td>').html(itemList.sprTaxableAmt));
						  	taxaleAmt=taxaleAmt+itemList.sprTaxableAmt;
						  	tr.append($('<td></td>').html(itemList.disc));
						  	disc=disc+itemList.disc;
						  	tr.append($('<td></td>').html(itemList.extraCharges));
						  	extraCharge=extraCharge+itemList.extraCharges;
						  	tr.append($('<td></td>').html(itemList.sprTaxAmt));
						  	taxAmt=taxAmt+itemList.sprTaxAmt;  
						  	tr.append($('<td></td>').html(itemList.total));
						  	total=total+itemList.total;
						  	if(itemList.groupId==labourGroupId)
						  		{
						  		 
						  		labourCharge=labourCharge+itemList.total;
						  		}
						  	else
						  		{
						  		 
						  		totSparePart=totSparePart+itemList.total;
						  		}
						  	
						  	tr.append($('<td></td>').html('<span class="glyphicon glyphicon-edit" id="edit'+key+'" onclick="edit('+key+');"> </span><span style="visibility: hidden;" class="glyphicon glyphicon-ok" onclick="submit('+key+');" id="ok'+key+'"></span><span class="glyphicon glyphicon-remove"  onclick="del('+key+')" id="del'+key+'"></span>'));
						    $('#table_grid tbody').append(tr);

							 

						})  
						    
						document.getElementById("total").value=(total).toFixed(2);
							document.getElementById("totDisc").value=(disc).toFixed(2);
							document.getElementById("totExtraCharge").value=(extraCharge).toFixed(2);
							document.getElementById("taxAmt").value=(taxAmt).toFixed(2);
							document.getElementById("taxaleAmt").value=(taxaleAmt).toFixed(2); 
							document.getElementById("totPart").value=(totSparePart).toFixed(2); 
							document.getElementById("totalfirst").value=(total).toFixed(2);
							document.getElementById("labCharge").value=(labourCharge).toFixed(2);
						
					});
			
			
		}
		
		function updateNextServicingDueKm()
		{
			
			var vehId = $("#vehId").val();
			var typeId = $("#typeId").val();
			var typeName = $("#typeId option:selected").text();
			var servDoneKm = parseFloat($("#servDoneKm").val()); 
			$
			.getJSON(
					'${updateNextServicingDueKm}',

					{
						 
						vehId : vehId,
						ajax : 'true'

					},
					function(data) { 
						if(typeId==1)
							document.getElementById("nextDueKm").value=data.freqKm+servDoneKm; 
						else if(typeId==6)
							document.getElementById("nextDueKm").value=data.battaryChangeFreq+servDoneKm; 
						else if(typeId==7)
							document.getElementById("nextDueKm").value=data.acChangeFreq+servDoneKm;
						else 
							document.getElementById("nextDueKm").value=data.wheelChangeFreq+servDoneKm;
						
						document.getElementById("typeName").value=typeName;
			 
					});
			
			
		}
	</script>
<script type="text/javascript">
function validation()
{
	        
	var groupId = $("#groupId").val();
	var sprId = $("#sprId").val();
	var vehId = $("#vehId").val();
	var typeId = $("#typeId").val();
	var spareRate = $("#spareRate").val();
	var spareQty = $("#spareQty").val();
	var taxaleAmtDetail = $("#taxaleAmtDetail").val();
	var taxAmtDetail = $("#taxAmtDetail").val();
	var discDetail = $("#discDetail").val();
	var extraChargeDetail = $("#extraChargeDetail").val();
	var totalDetail = $("#totalDetail").val();
	var servTypeDetail = $("#servTypeDetail").val(); 
	var dealerId = $("#dealerId").val(); 
	var type = $("#type").val(); 
	
	var isValid = true;
	 if(isNaN(type) || type < 0 || type=="")
		{
		isValid = false;
		alert("Please Select Vehicle or Machine Type"); 
		} 
	else if(isNaN(typeId) || typeId < 0 || typeId=="")
		{
		isValid = false;
		alert("Please Enter Type"); 
		} 
	 else if(isNaN(dealerId) || dealerId < 0 || dealerId=="")
		{
		isValid = false;
		alert("Please Enter Dealer"); 
		} 
	 else if(isNaN(vehId) || vehId < 0 || vehId=="")
		{
		isValid = false;
		alert("Please Enter Vehicle"); 
		} 
	 else if(isNaN(groupId) || groupId < 0 || groupId=="")
		{
		isValid = false;
		alert("Please Enter Group");
		}
		else if(isNaN(sprId) || sprId < 0 || sprId=="")
		{
		isValid = false;
		alert("Please Enter Spare Part");
		}
	else if(isNaN(spareRate) || spareRate < 0 || spareRate=="")
	{
	isValid = false;
	alert("Please Enter Rate"); 
	} 
	else if(isNaN(spareQty) || spareQty < 0 || spareQty=="")
	{
	isValid = false;
	alert("Please Enter Quantity");
	
	} 
	else if(isNaN(taxaleAmtDetail) || taxaleAmtDetail < 0 || taxaleAmtDetail=="")
	{
	isValid = false;
	alert("Please Enter Taxable Amt"); 
	}
	else if(isNaN(taxAmtDetail) || taxAmtDetail < 0 || taxAmtDetail=="")
	{
	isValid = false;
	alert("Please Enter Tax Amt"); 
	}
	else if(isNaN(discDetail) || discDetail < 0 || discDetail=="")
	{
	isValid = false;
	alert("Please Enter Discount");
	}
	else if(isNaN(extraChargeDetail) || extraChargeDetail < 0 || extraChargeDetail=="")
	{
	isValid = false;
	alert("Please Enter Extra Charges");
	}
	else if(isNaN(totalDetail) || totalDetail < 0 || totalDetail=="")
	{
	isValid = false;
	alert("Please Enter Total");
	}
	else if(isNaN(servTypeDetail) || servTypeDetail < 0 || servTypeDetail=="")
	{
	isValid = false;
	alert("Please Enter Service Type");
	} 
	
return isValid;
	
}

$(document).ready(function() { 
	$('#typeId').change(
			function() {
				//alert("typeId"+$(this).val());
				var typeId=$(this).val();
			    
				$.getJSON('${groupByTypeId}', {
					
					typeId : $(this).val(),
					ajax : 'true'
				},
						function(data) {
					 
					var html = '<option value="">Select Group</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].groupId + '">'
								+ data[i].groupName + '</option>';
					}
					html += '</option>';
					$('#groupId').html(html);
					$("#groupId").trigger("chosen:updated");
											
						});
			 
				 
		})
		 		 
});

$(document).ready(function() { 
	$('#type').change(
			function() {
				//alert("typeId"+$(this).val());
				var type=$(this).val();
			    
				$.getJSON('${getTypeList}', {
					
					type : $(this).val(),
					ajax : 'true'
				},
						function(data) {
					 
					var html = '<option value="">Select Type</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].typeId + '">'
								+ data[i].typeName + '</option>';
					}
					html += '</option>';
					$('#typeId').html(html);
					$("#typeId").trigger("chosen:updated");
					if(type==2)
					{
						//alert("in if"); 
					document.getElementById("km").style.visibility="hidden"; 
					document.getElementById("servDoneKm").required=0; 
					document.getElementById("nextDueKm").required=0;
					}
					else if(type==1)
					{
						//alert("in else");
					document.getElementById("km").style.visibility="visible";  
					document.getElementById("servDoneKm").required=""; 
					document.getElementById("nextDueKm").required="";
					}			
						});
			 
				 
		})
		 		 
});

$(document).ready(function() { 
	$('#type').change(
			function() {
				//alert("typeId"+$(this).val());
				var typeId=$(this).val();
			    
				$.getJSON('${machineListOrVehicleList}', {
					
					typeId : $(this).val(),
					ajax : 'true'
				},
						function(data) {
					 
					var html = '<option value="">Select Vehicle Or Machine</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].mechId + '">'
								+ data[i].mechName + '</option>';
					}
					html += '</option>';
					$('#vehId').html(html);
					$("#vehId").trigger("chosen:updated"); 
					
					 
					
						});
			 
				 
		})
		 		 
});

$(document).ready(function() { 
	$('#groupId').change(
			function() {
				//alert("makeId"+$(this).val());
				var groupId=$(this).val();
			    
				$.getJSON('${sparePartByGroupId}', {
					
					groupId : $(this).val(),
					ajax : 'true'
				},
						function(data) {
					 
					var html = '<option value="">Select Part</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].sprId + '">'
								+ data[i].sprName + '</option>';
					}
					html += '</option>';
					$('#sprId').html(html);
					$("#sprId").trigger("chosen:updated");
											
						});
			 
				 
		})
		 		 
});

$(document).ready(function() { 
	$('#sprId').change(
			function() {
				var dealerId = $("#dealerId").val();
				var sprId=$(this).val();
			if(dealerId=="")
				{
				alert("Select Dealer");
				}
			 
			else
				{
				$.getJSON('${partDetailById}', {
					
					sprId : $(this).val(),
					dealerId : dealerId,
					ajax : 'true'
				},
						function(data) {
					
					document.getElementById("spareRate").value=data.sprRate1;
					document.getElementById("discPer").value=data.disc;
					document.getElementById("extraChargePer").value=data.extraCharges;
					document.getElementById("taxPer").value=data.cgst+data.igst+data.sgst; 
					document.getElementById("taxaleAmtDetail").value=0;
					document.getElementById("taxAmtDetail").value=0;
					document.getElementById("totalDetail").value="";	
					document.getElementById("spareQty").value="";
						});
				}
				 
		})
		 		 
});

$(document).ready(function() { 
	$('#dealerId').change(
			function() {
				document.getElementById("sprId").value="";  
				$('#sprId').trigger("chosen:updated");
				 
		})
		 		 
});

function calculateDetail()
{
	
	var spareQty = parseFloat($("#spareQty").val()); 
	var spareRate = parseFloat($("#spareRate").val());  
	var taxPer = parseFloat($("#taxPer").val());
	var discPer = parseFloat($("#discPer").val());
	var extraChargePer = parseFloat($("#extraChargePer").val()); 
	
	var discDetail=((spareQty*spareRate)*discPer)/100;
	var extraChargeDetail=((spareQty*spareRate)*extraChargePer)/100; 
	var taxaleAmtDetail=(spareQty*spareRate)-discDetail+extraChargeDetail;
	var taxAmtDetail=(taxaleAmtDetail*taxPer)/100;
	var totalDetail=(spareQty*spareRate)-discDetail+extraChargeDetail+taxAmtDetail;
	document.getElementById("taxAmtDetail").value=(taxAmtDetail).toFixed(2);
	document.getElementById("discDetail").value=(discDetail).toFixed(2);
	document.getElementById("extraChargeDetail").value=(extraChargeDetail).toFixed(2);
	document.getElementById("totalDetail").value=(totalDetail).toFixed(2); 
	document.getElementById("taxaleAmtDetail").value=(taxaleAmtDetail).toFixed(2); 
}

/* function calculateHeader()
{
	 
	
	var labCharge = parseFloat($("#labCharge").val());  
	var totalfirst = parseFloat($("#totalfirst").val());  
	document.getElementById("total").value=totalfirst+labCharge; 
} */

var specialKeys = new Array();
specialKeys.push(8); //Backspace
function IsNumeric(e) {
    var keyCode = e.which ? e.which : e.keyCode
    var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1 || keyCode==9);
   // document.getElementById("error").style.display = ret ? "none" : "inline";
    return ret;
}

function check()
{
	 
	var servType = $("#servType").val();
	var dealerId = $("#dealerId").val();
	var file = $("#file").val();
	if(servType=="")
		{
		alert("Select Service Type");
		}
	else if(dealerId=="")
	{
	alert("Select Dealer");
	}
	else if(file=="")
	{
	alert("Select file");
	}
}
</script>
	
								
							
	
</body>
</html>