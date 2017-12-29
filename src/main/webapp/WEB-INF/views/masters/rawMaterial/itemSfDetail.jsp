<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
	href="${pageContext.request.contextPath}/resources/css/loader.css">

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

<%-- 
 <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
	  <script src="${pageContext.request.contextPath}/resources/js/choosen.js"></script><!-- 1 css and 2 js for search item   --> --%>
	
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
	<c:url var="getRmSubCategory" value="/getRmSubCategory" />

	<c:url var="insertSfItemDetail" value="/insertSfItemDetail" />
	<c:url var="getRmCategory" value="/getRmCategory" />

	<c:url var="getItemDetail" value="/getItemDetail" />
	<c:url var="getSingleItem" value="/getSingleItem" />

	<c:url var="getRawMaterial" value="/getRawMaterial" />

	<c:url var="itemForEdit" value="/itemForEdit" />

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
						<i class="fa fa-file-o"></i>Semi Finished Item
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
								<i class="fa fa-bars"></i>Add Item Details:- <b> SF
									Name-${sfName}</b>
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showItemSf">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>

						</div>
						<div class="box-content">
							<form
								action="${pageContext.request.contextPath}/insertSfItemDetail"
								method="post" class="form-horizontal" id="validation-form"
								method="post">

								<div class="form-group">

									<label class="col-sm-3 col-lg-2 control-label">SF Type
									</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="sf_item_type_name"
											readonly="readonly" value="${sfTypeName}" id="sf_item_name"
											class="form-control" placeholder="SF Name"
											data-rule-required="true" />
									</div>
									<label class="col-sm-3 col-lg-2 control-label"
										id="itemNameLabel" style="display: none">Material Name
									</label>
									<div class="col-sm-6 col-lg-4 controls" id="item_name_div"
										style="display: none">
										<input type="text" name="item_name" style="display: none"
											readonly="readonly" value="" id="item_name"
											class="form-control" />
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
										<select class="chosen" name="rm_material_name" id="rm_material_name"
											 placeholder="Material Name"
											data-rule-required="true">
											<option value="0">Select Material</option>

										</select>
									</div>

								</div>

								<div class="form-group">

									<label class="col-sm-3 col-lg-2 control-label">SF
										Weight </label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="sf_item_weight" id="sf_item_weight"
											class="form-control" placeholder="SF Weight " />
									</div>

									<label class="col-sm-3 col-lg-2 control-label"> Qty</label>
									<div class="col-sm-6 col-lg-4 controls">
										<input type="text" name="qty" id="qty" class="form-control"
											placeholder="Qty" />
									</div>

								</div>

								<div class="row">
									<div class="col-md-12" style="text-align: center;">
										<input type="button" class="btn btn-info" value="Submit"
											onclick="submitItem();">

								 <input
										type="button" class="btn btn-info" value="Cancel"
										onclick="clearData();">

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
										<th width="120" align="left">Weight</th>
										<th width="120" align="left">Action</th>
										<!-- 	<th width="140" align="left">GST %</th> -->

									</tr>

								</thead>

								<tbody>

									<c:forEach items="${sfDetailList}" var="sfDetailList"
										varStatus="count">

										<c:choose>

											<c:when test="${sfDetailList.delStatus == '0'}">
												<tr>

													<td><c:out value="${count.index+1}" /></td>

													<td align="left"><c:out value="${sfDetailList.rmName}" /></td>

													<c:choose>
														<c:when test="${sfDetailList.rmType == 1}">
															<td align="left"><c:out value="RM" /></td>
														</c:when>

														<c:when test="${sfDetailList.rmType == 2}">
															<td align="left"><c:out value="SF" /></td>
														</c:when>
													</c:choose>
													<td align="left"><c:out value="${sfDetailList.rmQty}" /></td>

													<td align="left"><c:out
															value="${sfDetailList.rmWeight}" /></td>

													<td><a href='#' class='action_btn'
														onclick="deleteSfDetail(${count.index})"><i
															class='fa fa-trash-o fa-lg'></i></a> &nbsp;&nbsp;<a href='#'
														class='action_btn' onclick="editSfDetail(${count.index})"><i
															class='fa fa-edit  fa-lg'></i></a></td>
												</tr>
											</c:when>
										</c:choose>

										<%-- <a
														href="${pageContext.request.contextPath}/delete/${sfDetailList.rmId}"
														class="action_btn"> <abbr title="Delete"><i
																class="fa fa-list"></i></abbr></a> --%>

									</c:forEach>

								</tbody>
							</table>
						</div>
<div class="row">
									<div class="col-md-12" style="text-align: center;">

						<input type="submit"  class="btn btn-info"
							value="Submit Items" />
							</div>
							</div>
						<!-- <input type="button" class="btn btn-info" value="Submit Items"
											onclick="insertItemDetail()">
 -->
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
	
	var editFlag=false;
	var key1=0;
	
		function submitItem() {
			
			
			if(editFlag==true){
				
				 var materialType = $('#material_type').val();
				var materialName=$("#rm_material_name option:selected").html();
				var materialNameId= $('#rm_material_name').val(); 
				
				var sfWeight = document.getElementById("sf_item_weight").value;
				var qty = document.getElementById("qty").value;
				
				 var validation=validate();
					if(validation){
				
				$.getJSON('${itemForEdit}', {
					/* mat_type : materialType,
					mat_name_id : materialNameId,
					mat_name : materialName, */
					sf_weight : sfWeight,
					qty : qty,
					key : key1,
					 
					ajax : 'true',

				},function(data) {
					alert(data.length);
					
					var len = data.length;
					$('#table1 td').remove();
					
					$.each(data,function(key, sfDetail) {
					
					var tr = $('<tr></tr>');
					var rmTypeName;
					if(sfDetail.rmType == 1){
						rmTypeName="RM";
					}else if(sfDetail.rmType == 2){
						rmTypeName="SF";
					}

					if(sfDetail.delStatus == 0){
						
				  	tr.append($('<td></td>').html(key+1));

				  	tr.append($('<td></td>').html(sfDetail.rmName));

				  	tr.append($('<td></td>').html(""+rmTypeName));

				  	tr.append($('<td></td>').html(sfDetail.rmQty));

				  	tr.append($('<td></td>').html(sfDetail.rmWeight));
				  
				  //	tr.append($('<td></td>').html("<input type=button id=delete onClick=deleteSfDetail("+key+"); Value=Delete> <input type=button id=edit onClick=editSfDetail("+key+"); Value=Edit> "));
				 	tr.append($('<td></td>').html("<a href='#' class='action_btn' onclick=deleteSfDetail("+key+")><i class='fa fa-trash-o fa-lg'></i></a><a href='#' class='action_btn' onclick=editSfDetail("+key+")><i class='fa fa-edit  fa-lg'></i></a>"));

					}
					$('#table1 tbody').append(tr);

					})

					});

				document.getElementById("sf_item_weight").value="";
				document.getElementById("qty").value="";
				//document.getElementById("material_type").options.selectedIndex = "0";
				//document.getElementById("rm_material_name").options.selectedIndex = "0";
				
				$("#item_name_div").hide();
				$("#item_name").hide();
				$("#itemNameLabel").hide();
				 document.getElementById("material_type").options[1].disabled = false;
				 document.getElementById("material_type").options[2].disabled = false;
					}//end of validation
			}// end of if
			else{
			var materialType = $('#material_type').val();
			var materialName=$("#rm_material_name option:selected").html();
			var materialNameId= $('#rm_material_name').val();
			var sfWeight = document.getElementById("sf_item_weight").value;
			var qty = document.getElementById("qty").value;
		
			 var validation=validate();
			if(validation){
			 
			var key=-1;
			var editKey=-1;
			alert("Hi ");
			
			$.getJSON('${getItemDetail}', {
				mat_type : materialType,
				mat_name_id : materialNameId,
				mat_name : materialName,
				sf_weight : sfWeight,
				qty : qty,
				
				key:key,
				editKey:editKey,
				ajax : 'true',

			}, function(data) {
			alert(data.length);
				
				var len = data.length;
				$('#table1 td').remove();
				$.each(data,function(key, sfDetail) {
				var tr = $('<tr></tr>');
				var rmTypeName;
				if(sfDetail.rmType == 1){
					rmTypeName="RM";
				}else if(sfDetail.rmType == 2){
					rmTypeName="SF";
				}

				if(sfDetail.delStatus == 0){
					
			  	tr.append($('<td></td>').html(key+1));

			  	tr.append($('<td></td>').html(sfDetail.rmName));

			  	tr.append($('<td></td>').html(""+rmTypeName));

			  	tr.append($('<td></td>').html(sfDetail.rmQty));

			  	tr.append($('<td></td>').html(sfDetail.rmWeight));
			  
			  //	tr.append($('<td></td>').html("<input type=button id=delete onClick=deleteSfDetail("+key+"); Value=Delete> <input type=button id=edit onClick=editSfDetail("+key+"); Value=Edit> "));
tr.append($('<td></td>').html("<a href='#' class='action_btn' onclick=deleteSfDetail("+key+")><i class='fa fa-trash-o fa-lg'></i></a>        <a href='#' class='action_btn' onclick=editSfDetail("+key+")><i class='fa fa-edit  fa-lg'></i></a>"));			 	//tr.append($('<td></td>').html("<a href='#' class='action_btn' onclick=editItemDetail("+key+")> <abbr title='edit'> <i class='fa fa-edit  fa-lg' ></i></abbr> </a> <a href='#' class='action_btn' onclick=deleteItemDetail("+key+")><abbr title='Delete'><i class='fa fa-trash-o  fa-lg'></i></abbr></a>"));

				}
				$('#table1 tbody').append(tr);

				})

				});
			
			document.getElementById("sf_item_weight").value="";
			document.getElementById("qty").value="";
			$("#material_type").val="0";
			document.getElementById("material_type").options.selectedIndex = "0";
			document.getElementById("rm_material_name").options.selectedIndex = "0";
			}// end of validation if
			}//end of else
				editFlag=false;
				}
		
		
function deleteSfDetail(key){
	
	var isDel=confirm('Are you Sure to Delete this Record');
	if(isDel==true){
	var editKey=-2;
	if(key == null ){
		key=-2;
		alert(key);
	}
	$.getJSON('${getItemDetail}', {
		
		key:key,
		editKey : editKey,
		ajax : 'true',

	}, function(data) {
		
		var len = data.length;

		$('#table1 td').remove();

		$.each(data,function(key, sfDetail) {

		var tr = $('<tr></tr>');
		var rmTypeName;
		if(sfDetail.rmType == 1){
			rmTypeName="RM";
		}else if(sfDetail.rmType == 2){
			rmTypeName="SF";
		}
		if(sfDetail.delStatus == 0){
	  	tr.append($('<td></td>').html(key+1));

	  	tr.append($('<td></td>').html(sfDetail.rmName));

	  	tr.append($('<td></td>').html(""+rmTypeName));

	  	tr.append($('<td></td>').html(sfDetail.rmQty));

	  	tr.append($('<td></td>').html(sfDetail.rmWeight));
	  
	 // tr.append($('<td></td>').html("<input type=button class= btn btn-primary  id=delete onClick=deleteSfDetail("+key+"); Value=Delete> <input type=button id=edit onClick=editSfDetail("+key+"); Value=Edit> "));
	  	tr.append($('<td></td>').html("<a href='#' class='action_btn' onclick=deleteSfDetail("+key+")><i class='fa fa-trash-o fa-lg'></i></a>        <a href='#' class='action_btn' onclick=editSfDetail("+key+")><i class='fa fa-edit  fa-lg'></i></a>"));
			}
	  	
		$('#table1 tbody').append(tr);

		})

		});
	}//end of if isDel
	else{
		isDel=false;
	}
}

function editSfDetail(token){
 
	editFlag=true;
	
	$.getJSON('${getSingleItem}', {
		
		 
		ajax : 'true',

	}, function(data) {
		//alert(data);
		var len = data.length;

		//$('#table1 td').remove();
 
		 $.each(data,function(key, sfDetail) {
			editKey=key; 

	//	var tr = $('<tr></tr>');
		//var rmTypeName;
		//alert(token);
		if(key==token)
			{
		if(sfDetail.rmType == 1){
			rmTypeName="RM";
		}else if(sfDetail.rmType == 2){
			rmTypeName="SF";
		}
		var m_type= sfDetail.rmType;
				document.getElementById("sf_item_weight").value=sfDetail.rmWeight;
				 document.getElementById("qty").value=sfDetail.rmQty;
					 document.getElementById("material_type").options.selectedIndex =sfDetail.rmType;
					 
					// document.getElementById("material_type").options[1].disabled = true;
					// document.getElementById("material_type").options[2].disabled = true;
					//document.getElementById("rm_material_name").options.selectedIndex =sfDetail.rmId;
					$('#rm_material_name').val('sfDetail.rmId').prop('selected', true);
					
					document.getElementById("item_name").value=sfDetail.rmName;
					
					//$("#item_name_div").show();
					//$("#item_name").show();
					//$("#itemNameLabel").show();
					
					appendItem(sfDetail.rmId);
				
				 key1=key;
			}
	  	 
		 })  

		});
	/* document.getElementById("sf_item_weight").value="";
	document.getElementById("qty").value="";
	
	$("#material_type").val("0"); */
	
}
function appendItem(rmId){
	
	var rmType = document.getElementById("material_type").value;
	$('#loader').show();


	$.getJSON('${getRawMaterial}', {
		material_type : rmType,
					
					ajax : 'true',
				},  function(data) {
					$('#loader').hide();

					var html = '<option value="0" selected >Select Raw Material</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						if(data[i].id==rmId){
							html += '<option value="' + data[i].id + '"selected>'
							+ data[i].name + '</option>';
						}else{
						html += '<option value="' + data[i].id + '">'
								+ data[i].name + '</option>';
					}
					}
					html += '</option>';
					$('#rm_material_name').html(html);
				});
	
}

</script>
	<script type="text/javascript">

function insertItemDetail(){
	
	
	$.getJSON('${insertSfItemDetail}',
			{
				ajax : 'true',
}
);
	

}


</script>

	<script type="text/javascript">
$(document).ready(function() { 
	$('#material_type').change(
			function() {
				$('#loader').show();


	$.getJSON('${getRawMaterial}', {
		material_type : $(this).val(),
					
					ajax : 'true',
				},  function(data) {
					$('#loader').hide();

					var html = '<option value="0" selected >Select Raw Material</option>';
					
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
function validate(){
	var isValid=true;
	var materialType = $('#material_type').val();
	var materialName=$("#rm_material_name option:selected").html();
	var materialNameId= $('#rm_material_name').val();
	var sfWeight = document.getElementById("sf_item_weight").value;
	var qty = document.getElementById("qty").value;

	if(materialType == 0){
		alert("Please Select Valid Material Type");
		isValid= false;
	}else if(materialNameId==0){
		alert("Please Select Valid Material");
		isValid= false;
	}else if(sfWeight==null || sfWeight=="" || sfWeight<0 || isNaN(sfWeight)){
		alert("Please Select Valid sfWeight");
		isValid= false;
	}else if(qty==null || qty== "" || qty<0 || isNaN(qty)){
		alert("Please Select Valid Qty ");
		isValid= false;
	}
	return isValid;
	
}
function clearData(){
	document.getElementById("sf_item_weight").value="";
	document.getElementById("qty").value="";
	$("#material_type").val="0";
	document.getElementById("material_type").options.selectedIndex = "0";
	document.getElementById("rm_material_name").options.selectedIndex = "0";
}
</script>
<script type="text/javascript">
$(".chosen").chosen();
</script>
</body>
</html>