<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tableSearch.css">
	
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>


<c:url var="getRawMaterialList" value="/getRawMaterialList" />
<c:url var="getBaseQty" value="/getBaseQty" />
<c:url var="insertItemDetail" value="/insertItemDetail"/>
<c:url var="deleteItemDetail" value="/deleteItemDetail"/>
<c:url var="editItemDetail" value="/editItemDetail"/>
<c:url var="editItem" value="/editItem"/>
<c:url var="addItemDetail" value="/addItemDetail"/>
<c:url var="redirectToItemList" value="/redirectToItemList"/>
<c:url var="getRmCategory" value="/getRmCategory" />
<c:url var="getRmListByCatId" value="/getRmListByCatId" />
<c:url var="getItemsForItemDetail" value="/getItemsForItemDetail" />

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
						<i class="fa fa-file-o"></i>Item Detail
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
								<i class="fa fa-bars"></i><span>Add Item Detail</span>
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/itemList">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							<!-- <div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> <a data-action="close" href="#"><i
									class="fa fa-times"></i></a>
							</div> -->
						</div>


						<div class="box-content">
							<form action="${pageContext.request.contextPath}/addItemDetails" method="post" class="form-horizontal" id=
									"validation-form"
										 method="post">
							

								<div class="form-group">
									<label class="col-sm-2 col-lg-2 control-label">Item Name</label>
									<div class="col-sm-9 col-lg-4 controls">
									
								  <input type="text" name="item_name" id="item_name" class="form-control" placeholder="Item Name" value="${item.itemName}" disabled data-rule-required="true"/>
								  <input type="hidden" name="item_id" id="item_id" class="form-control"  value="${item.id}" />
								  	
										<%-- <select name="item_id" id="item_id" class="form-control"placeholder="Item Name"data-rule-required="true">
											<option value="-1">Select Item</option>
									 	<c:forEach items="${itemsList}" var="itemsList"
													varStatus="count">
												<option value="${itemsList.id}"><c:out value="${itemsList.itemName}"/></option>
												</c:forEach> 
										</select> --%>
									</div>
                                    
									
								</div>
								<div class="box"><div class="box-title">
										<h3>
											<i class="fa fa-record"></i> Add Item 
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
										</div>
									</div></div>
								<div class="form-group">
									<label class="col-sm-2 col-lg-2 control-label">RM Type</label>
									<div class="col-sm-6 col-lg-2 controls">
                                    <select name="rm_type" id="rm_type" class="form-control"  placeholder="Raw Material Type"data-rule-required="true" onchange="return rmTypeChange()">
											<option value="">RM Type</option>
											<option value="1">Raw Material</option>
											<option value="2">Semi Finished</option>
									
								   </select>									
								   </div>
                        <label class="col-sm-3 col-lg-1 control-label">Rm Group</label>
									<div class="col-sm-6 col-lg-2 controls">
										<select name="rm_group" id="rm_group" class="form-control" tabindex="6">
										<option value="0" disabled="disabled" selected="selected">RM Group</option>
											 <c:forEach items="${rmItemGroupList}" var="rmItemGroupList"
							varStatus="count">
							<c:choose>
						            	<c:when test="${rmItemGroupList.grpId!=2&&rmItemGroupList.grpId!=3}">
							   <option value="${rmItemGroupList.grpId}"><c:out value="${rmItemGroupList.grpName}"/></option>
 												</c:when></c:choose>	 
												</c:forEach>
						

										</select>
									</div>
								
								<!-- </div>
									<div class="form-group"> -->
				              <div class="col-sm-2 col-lg-2 control-label" >RM Category</div>
									<div class="col-md-2">
									<select name="rm_cat" id="rm_cat" class="form-control" tabindex="6">
										<option value="0"disabled="disabled" selected="selected">RM Category</option>
											 
										</select>
									</div>
		                  	</div><div class="form-group">
		                         <label class="col-sm-3 col-lg-2 control-label">RM Item</label>
								<div class="col-sm-6 col-lg-2 controls">
									<select name="rm_id" id="rm_id"class="form-control chosen" onchange="onRmIdChange()" tabindex="6"  placeholder="Raw Material"data-rule-required="true">
											<option value="0">Raw Material</option>
										    
							     	</select> 	
				                 </div>
				
									
									
								
								<!-- <label class="col-sm-3 col-lg-2 control-label">RM Unit</label>
									<div class="col-sm-6 col-lg-4 controls">-->
						     	<input type="hidden" name="rm_unit_id" id="rm_unit_id "class="form-control"placeholder="RM Unit"/>
									
									<!--</div> -->
								<label class="col-sm-3 col-lg-1 control-label">RM Qty</label>
					      	    <div class="col-sm-6 col-lg-2 controls">
							    <input type="text" name="rm_qty" id="rm_qty" class="form-control"placeholder="RM Qty" required/>
					     	    </div>
					     	    
					     	   <!--  <label class="col-sm-3 col-lg-1 control-label">RM Weight</label>
					      	    <div class="col-sm-5 col-lg-4 controls"> -->
							    <input type="hidden" name="rm_weight"   id="rm_weight" class="form-control"placeholder="RM Weight(KG)" value="1" data-rule-required="true"/>
					     	   <!--  </div> -->
								<!-- </div>
								<div class="form-group"> -->
				
				                <label class="col-sm-3 col-lg-2 control-label">No. Of Pieces Per Item</label>
								 	<div class="col-sm-6 col-lg-2 controls">
						     	    <input type="text" name="base_qty" id="base_qty" class="form-control"placeholder="No. Of Pieces Per Item" value="${item.minQty}"   data-rule-required="true"/>
									
									</div> 
								</div>
									<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">is Multipl Factor Appl?</label>
									<div class="col-sm-9 col-lg-2 controls">
									</label> <label class="radio-inline"> <input type="radio"
											name="isMultiFactor" id="optionsRadios1" value="0" checked/> No
										</label>
										<label class="radio-inline"> <input type="radio"
											name="isMultiFactor" id="optionsRadios2" value="1"  />
											Yes
										
									</div>
									<div style="display: none" id="itemDiv">
									<label class="col-sm-3 col-lg-1 control-label">Item</label>
								<div class="col-sm-6 col-lg-2 controls">
								
									<select name="ratio_item_id" id="ratio_item_id"class="form-control chosen"  tabindex="6"  placeholder="Raw Material"data-rule-required="true">
										    <option value="" selected>Select Item</option>
										    
							     	</select> 	
				                 </div>
									  <label class="col-sm-3 col-lg-2 control-label">Multiplication Factor</label>
								 	<div class="col-sm-6 col-lg-2 controls">
						     	    <input type="text" name="multiFactor" id="multiFactor" class="form-control" placeholder="Multiplication Factor" value="1.0"   data-rule-required="true"/>
									
									</div> 
									 </div>
								</div>
								
								
					<div class="row">
						<div class="col-md-12" style="text-align: center">
							<input type="button" class="btn btn-info" value="ADD" name="add" id="add">
                            <input type="button" class="btn btn-info" value="CANCEL" name="cancel" id="cancel">

						</div>
						<div align="center" id="loader" style="display: none">

					<span>
						<h4>
							<font color="#343690">Loading</font>
						</h4>
					</span> <span class="l-1"></span> <span class="l-2"></span> <span
						class="l-3"></span> <span class="l-4"></span> <span class="l-5"></span>
					<span class="l-6"></span>
				</div>
					</div>
					</form>
					
					<form action="${pageContext.request.contextPath}/addItemDetail" method="post" class="form-horizontal" id=
									"validation-form"
										 method="post">
						<div class="box">
									<!-- <div class="box-title">
										<h3>
											<i class="fa fa-table"></i> Item Detail List
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											<a data-action="close" href="#"><i class="fa fa-times"></i></a>
										</div>
									</div> -->

									<div class="box-content">
<div class="col-md-9" ></div> 
					<label for="search" class="col-md-3" id="search">
    <i class="fa fa-search" style="font-size:20px"></i>
									<input type="text"  id="myInput" onkeyup="myFunction()" placeholder="Search.." style="border-radius: 25px;" title="Type in a name">
										</label>  
										<div class="clearfix"></div>
										<div id="table-scroll" class="table-scroll">
							 <!-- 
									<div id="faux-table" class="faux-table" aria="hidden">
									<table id="table2" class="main-table">
											<thead>
												<tr class="bgpink">
									<th width="45" style="width: 18px">Sr.No.</th>
														<th width="100" align="left">Item Name</th>
														<th width="100" align="left">RM Type</th>
														<th width="100" align="left">Raw Material</th>
     													<th width="100" align="left">RM Weight</th>
														<th width="100" align="left">RM Qty</th>
														<th width="100" align="left">No. Of Pieces/Item</th>
														<th width="81" align="left">Action</th>
												</tr>
												</thead>
												</table>
									
									</div> -->
									<div class="table-wrap">
									
										<table id="table1" class="table table-bordered table-striped fill-head">
											<thead>
												<tr class="bgpink">
											<th width="45" style="width: 18px">Sr.No.</th>
														<th width="100" align="left">Item Name</th>
														<th width="100" align="left">RM Type</th>
														<th width="100" align="left">Raw Material</th>
     													<th width="100" align="left">RM Weight</th>
														<th width="100" align="left">RM Qty</th>
														<th width="100" align="left">No. Of Pieces/Item</th>
														<th width="81" align="left">Action</th>
												</tr>
												</thead>
										<!-- <div class="table-responsive" style="border: 0">
											<table width="100%" class="table table-advance" id="table1">
												<thead>
													<tr>
														<th width="45" style="width: 18px">Sr.No.</th>
														<th width="100" align="left">Item Name</th>
														<th width="100" align="left">RM Type</th>
														<th width="100" align="left">Raw Material</th>
     													<th width="100" align="left">RM Weight</th>
														<th width="100" align="left">RM Qty</th>
														<th width="100" align="left">No. Of Pieces/Item</th>
														<th width="81" align="left">Action</th>
													</tr>
												</thead> -->
												<tbody>
													 <c:forEach items="${itemDetailList}" var="itemDetailList" varStatus="count">
														<tr>
														<c:choose>
         
                                                                <c:when test = "${itemDetailList.delStatus==0}">
                                                                
															<td><c:out value="${count.index+1}"/></td>
															<td align="left"><c:out
																	value="${itemDetailList.itemName}"></c:out></td>
															<c:choose>
         
                                                                <c:when test = "${itemDetailList.rmType==1}">
                                                                 
																	<td align="left"><c:out
																	value="Raw Material"></c:out></td>
                                                                </c:when>
         
      															   <c:when test = "${itemDetailList.rmType==2}">
       															   
																	<td align="left"><c:out
																	value="Semi Finished"></c:out></td>
       																  </c:when>
         
       															  <c:otherwise>
       															  </c:otherwise>
      														</c:choose>
															
																	<td align="left"><c:out
																	value="${itemDetailList.rmName}"></c:out></td>
														<%-- 	<td align="left"><c:out
																	value="${itemDetailList.rmUomId}"></c:out></td>	 --%>
															<td align="left"><c:out
																	value="${itemDetailList.rmWeight}"></c:out></td>
															<td align="left"><c:out
																	value="${itemDetailList.rmQty}"></c:out></td>						
																<td align="left"><c:out
																	value="${itemDetailList.noOfPiecesPerItem}"></c:out></td>	
															<td align="left"><a href='#' class='action_btn' onclick="editItemDetail(${count.index})"> <abbr title='edit'> <i class='fa fa-edit  fa-lg' ></i></abbr> </a>

		                                                    <a href='#' class='action_btn'onclick="deleteItemDetail(${count.index});"><abbr title='Delete'><i class='fa fa-trash-o fa-lg'></i></abbr></a>	</td>
														     
														     </c:when>
       															  <c:otherwise>
       															  </c:otherwise>
      														</c:choose>
														</tr>

													</c:forEach> 
												</tbody>
											</table>
										</div>
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
	     </div></div>
	<!-- END Main Content -->
	<footer>
	<p>2019 © MONGINIS.</p>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>

			<script type="text/javascript"
				src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
			<script type="text/javascript"
				src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>

				
</body>

<script type="text/javascript">
/* $(document).ready(function() { 
	$('#rm_type').change(
			 */
			
			function rmTypeChange() {
					var rmType = $("#rm_type").val();

				 if(rmType==2)
					{
					 document.getElementById("rm_group").options.selectedIndex =0;
					 document.getElementById("rm_cat").options.selectedIndex =0;

					 document.getElementById("rm_group").disabled = true;
					 document.getElementById("rm_cat").disabled = true;

					
					
				$('#loader').show();

	$.getJSON('${getRawMaterialList}', {
					rm_type : rmType,
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
					$('#rm_id').html(html);
					$("#rm_id").trigger("chosen:updated");
					
				});
			}
				 else
					 {
					 
					 document.getElementById("rm_group").disabled = false;
					 document.getElementById("rm_cat").disabled = false;
					 document.getElementById("rm_id").innerHTML = "";	
						var html = '<option value="0" selected >Select Raw Material</option>';
						html += '</option>';
						$('#rm_id').html(html);
						$("#rm_id").trigger("chosen:updated");

					 }
			 
			 
			 
			 
			 }/* );
			

}); */
</script>
<script type="text/javascript">
function appendRmItem(rmId) {
	var rmType = $("#rm_type").val();

	$('#loader').show();

	$.getJSON('${getRawMaterialList}', {
					rm_type : rmType,
					ajax : 'true',
				},  function(data) {
					$('#loader').hide();

					var html = '<option value="0" selected >Select Raw Material</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						if(data[i].id==rmId)
							{
							html += '<option value="' + data[i].id + '" selected>'
							+ data[i].name + '</option>';
							}
						else
							{
						html += '<option value="' + data[i].id + '">'
								+ data[i].name + '</option>';
							}
					}
					html += '</option>';
					//$("#rm_id").trigger("chosen:updated");
					$('#rm_id').html(html);
					$("#rm_id").trigger("chosen:updated");

				});
}
</script>
<script type="text/javascript">

var editFlag=false;
var key1=0;
//$('span').text("Add Item Detail");

$(document).ready(function() {
	$("#add").click(function() {
		
	var isValid = validation();//alert(isValid);
	if (isValid) {
		
 	var itemId = $("#item_id").val();
	//alert(itemId);
	var itemName=$('#item_name').val();
	//alert(selectedValue);

	var baseQty = $("#base_qty").val();
	//alert("baseQty"+baseQty)
	
	var rmType = $("#rm_type").val();

	var rmId = $("#rm_id").val();
	
	var rmWeight = $("#rm_weight").val();
	
	var rmQty = $("#rm_qty").val();
	
	var rmName=$('#rm_id option:selected').text();
	var isMultiFactor=$("input:radio:checked").val();
	//alert("isMultiFactor"+isMultiFactor);
	var multiFactor= $("#multiFactor").val();
	var ratioItemId=0;
	if(isMultiFactor!=0){
	//alert("multiFactor"+multiFactor);
	 ratioItemId= $("#ratio_item_id").val();
	 if(ratioItemId==null || ratioItemId=="")
		 ratioItemId=0;
	}
	
	if(editFlag==true)
	{
		editFlag=false;

		$.getJSON('${editItem}', {
			
			itemId : itemId,
			itemName:itemName,
			baseQty:baseQty,
			rmType:rmType,
			rmId:rmId,
			rmName:rmName,
			rmQty:rmQty,
			rmWeight:rmWeight,
			isMultiFactor:isMultiFactor,
			applItemId:ratioItemId,
			multiFactor:multiFactor,
			key:key1,
			
			ajax : 'true',
		},  function(data) { 
			 //$('#loader').hide();
			var len = data.length;

			//alert(len);

			$('#table1 td').remove();

			$.each(data,function(key, item) {

	      if(item.delStatus==0)
		  {
			var tr = $('<tr></tr>');

		  	tr.append($('<td></td>').html(key+1));

		  	tr.append($('<td></td>').html(item.itemName));

		  	if(item.rmType==1)
		  		{
			  	
		  		 tr.append($('<td></td>').html("Raw Material"));

		  		}
		  	else
		  		{
			  	tr.append($('<td></td>').html("Semi Finished"));

		  		}

		  	tr.append($('<td></td>').html(item.rmName));

/* 		  	tr.append($('<td></td>').html(item.rmUomId));
 */		  	
		  	tr.append($('<td></td>').html(item.rmWeight));
		  	
		  	tr.append($('<td></td>').html(item.rmQty));
		  	
		  	tr.append($('<td></td>').html(item.noOfPiecesPerItem));
		  	
		 	tr.append($('<td></td>').html("<a href='#' class='action_btn' onclick=editItemDetail("+key+")> <abbr title='edit'> <i class='fa fa-edit  fa-lg' ></i></abbr> </a> <a href='#' class='action_btn'onclick=deleteItemDetail("+key+ ")><abbr title='Delete'><i class='fa fa-trash-o  fa-lg'></i></abbr></a>"));
		  
		  //	tr.append($('<td></td>').html());
		  	
			$('#table1 tbody').append(tr);
	
		  }
		 }); 
		
		});
	}//if edit false then else....
	else
		{
	
//	alert(rmId);
	$.getJSON('${insertItemDetail}', {
		
		itemId : itemId,
		itemName:itemName,
		baseQty:baseQty,
		rmType:rmType,
		rmId:rmId,
		rmName:rmName,
		rmQty:rmQty,
		rmWeight:rmWeight,
		isMultiFactor:isMultiFactor,
		applItemId:ratioItemId,
		multiFactor:multiFactor,
		
		ajax : 'true',
	},  function(data) { 
 
		 //$('#loader').hide();
		var len = data.length;

		//alert(len);

		$('#table1 td').remove();

		$.each(data,function(key, item) {

	   if(item.delStatus==0)
	   {
		var tr = $('<tr></tr>');

	  	tr.append($('<td></td>').html(key+1));

	  	tr.append($('<td></td>').html(item.itemName));

	  	if(item.rmType==1)
	  		{
		  	
	  		 tr.append($('<td></td>').html("Raw Material"));

	  		}
	  	else
	  		{
		  	tr.append($('<td></td>').html("Semi Finished"));

	  		}

	  	tr.append($('<td></td>').html(item.rmName));

/* 	  	tr.append($('<td></td>').html(item.rmUomId));
 */	  	
	  	tr.append($('<td></td>').html(item.rmWeight));
	  	
	  	tr.append($('<td></td>').html(item.rmQty));
	  	
	  	tr.append($('<td></td>').html(item.noOfPiecesPerItem));

	  	
	 	tr.append($('<td></td>').html("<a href='#' class='action_btn' onclick=editItemDetail("+key+")> <abbr title='edit'> <i class='fa fa-edit  fa-lg' ></i></abbr> </a> <a href='#' class='action_btn'onclick=deleteItemDetail("+key+ ")><abbr title='Delete'><i class='fa fa-trash-o  fa-lg'></i></abbr></a>"));
	  
	  //	tr.append($('<td></td>').html());
	  	
		$('#table1 tbody').append(tr);
	 }
	 }); 
	
	});
 }
	 document.getElementById("rm_weight").value="1";
	 document.getElementById("rm_type").selectedIndex = "0"; 
		var html = '<option value="0" selected >Select Raw Material</option>';
		html += '</option>';
		$('#rm_id').html(html);
		$("#rm_id").trigger("chosen:updated");
	 document.getElementById("rm_id").selectedIndex = "0"; 
	 
	 document.getElementById("rm_qty").value="";
	 document.getElementById("base_qty").value ="";
	 document.getElementById("rm_group").selectedIndex = "0";  
	 document.getElementById("rm_cat").selectedIndex = "0";  
	 document.getElementById("multiFactor").value="0.0";
	 document.getElementById("optionsRadios1").checked = true;
	 
	 var html = '<option value="0" selected >Select Item</option>';
		html += '</option>';
		$('#ratio_item_id').html(html);
		$("#ratio_item_id").trigger("chosen:updated");
		  $("#itemDiv").hide(); 	
	}
});

});
$(document).ready(function() {
	$("#cancel").click(function() {
		// document.getElementById("rm_weight").value="";
		 document.getElementById("rm_type").selectedIndex = "0"; 
			var html = '<option value="0" selected >Select Raw Material</option>';
			html += '</option>';
			$('#rm_id').html(html);
			$("#rm_id").trigger("chosen:updated");
		 document.getElementById("rm_id").selectedIndex = "0"; 
		 document.getElementById("rm_qty").value="";
		 document.getElementById("base_qty").value ="";
		 document.getElementById("rm_group").selectedIndex = "0";  
		 document.getElementById("rm_cat").selectedIndex = "0";  
		 document.getElementById("multiFactor").value="0.0";
		 document.getElementById("optionsRadios1").checked = true;
		 
		  $("#itemDiv").hide(); 	
	});
});

function editItemDetail(token){
 
	editFlag=true;
	$.getJSON('${editItemDetail}', {
		
		key:token,
		ajax : 'true',

	}, function(data) {
	   

		var len = data.length;

		if(data.rmType==2)
			{
			 document.getElementById("rm_group").options.selectedIndex =0;
			 document.getElementById("rm_cat").options.selectedIndex =0;

			 document.getElementById("rm_group").disabled = true;
			 document.getElementById("rm_cat").disabled = true;
			}
		document.getElementById("rm_group").disabled = true;
		 document.getElementById("rm_cat").disabled = true;
		         document.getElementById("rm_weight").value=data.rmWeight;
				 document.getElementById("rm_qty").value=data.rmQty;
				 document.getElementById("rm_type").options.selectedIndex =data.rmType;
				 document.getElementById("base_qty").value =data.noOfPiecesPerItem;
				 document.getElementById("rm_id").options.selectedIndex =data.rmId;
				
				 if(data.int1==0){
				 document.getElementById("optionsRadios1").checked = true;
				   $("#itemDiv").hide();
				 }else
					 {
					 onRmIdChangeForEdit(data.rmId,data.rmType,data.int2);
					 document.getElementById("optionsRadios2").checked = true;		
					   $("#itemDiv").show();
					  
					   document.getElementById("multiFactor").value =data.varchar1;
					   
					 }

				 key1=token;
				 
					appendRmItem(data.rmId);

		});
	
	
}
function validation() {
	
	var baseQty = $("#base_qty").val();
	var rmType = $("#rm_type").val();
	var numbers = /^[0-9]+$/; 
	var rmId = $("#rm_id").val();
	var rmWeight = $("#rm_weight").val();
	var rmQty = $("#rm_qty").val();
	var chkedRadioBtn=$("input:radio:checked").val();

	var isValid = true;
	if (rmType==0) { 
		isValid = false;
		alert("Please Select  RM Type");
	} else if (rmId ==0) {
		isValid = false;
		alert("Please Select RM ");
	}/* else if (rmQty == ""||isNaN(rmQty)) {
		isValid = false;
		alert("Please Enter Valid RM Qty");
	} */else if (rmWeight == ""||rmWeight=='0'||isNaN(rmWeight) || rmWeight < 1) {
		isValid = false;
		alert("Please Enter Valid RM Weight");
	}else if (baseQty == ""||isNaN(baseQty) || baseQty < 1) {
		isValid = false;
		alert("Please Enter No. of Pieces Per Item.");
	}else if(chkedRadioBtn==1){
		
	//	var ratioItemId=$("#ratio_item_id").val();
		var multiFactor=$("#multiFactor").val();
		
	/* 	if (ratioItemId ==null) {
			isValid = false;
			alert("Please Select ItemId ");
		}else */ if(multiFactor<=0||isNaN(multiFactor))
			{
			isValid = false;
			alert("Please Enter Valid Multiplication factor");
			}
	}
	return isValid;
}

</script>
<script type="text/javascript">
function deleteItemDetail(key){
	var isDel=confirm('Are you sure want to delete this record');
	if(isDel==true)
	{
	$.getJSON('${deleteItemDetail}', {
		
		key:key,
	
		ajax : 'true',

	}, function(data) {
		
		var len = data.length;
		$('#table1 td').remove();

		$.each(data,function(key, item) {
			
	if(item.delStatus==0)
	{
			
		var tr = $('<tr></tr>');

	  	tr.append($('<td></td>').html(key+1));

	  	tr.append($('<td></td>').html(item.itemName));

	  	if(item.rmType==1)
	  		{
		  	
	  		 tr.append($('<td></td>').html("Raw Material"));

	  		}
	  	else
	  		{
		  	tr.append($('<td></td>').html("Semi Finished"));

	  		}

	  	tr.append($('<td></td>').html(item.rmName));

/* 	  	tr.append($('<td></td>').html(item.rmUomId));
 */	  	
	  	tr.append($('<td></td>').html(item.rmWeight));
	  	
	  	tr.append($('<td></td>').html(item.rmQty));
	  	
	  	tr.append($('<td></td>').html(item.noOfPiecesPerItem));

	 	tr.append($('<td></td>').html("<a href='#' class='action_btn' onclick=editItemDetail("+key+")> <abbr title='edit'> <i class='fa fa-edit  fa-lg' ></i></abbr> </a> <a href='#' class='action_btn'onclick=deleteItemDetail("+key+ ")><abbr title='Delete'><i class='fa fa-trash-o  fa-lg'></i></abbr></a>"));
	  
	  //	tr.append($('<td></td>').html());
	  	
		$('#table1 tbody').append(tr);

	}
		})
		
		});
	}
	else
		{
		isDel=false;
		}
}
</script>

<script type="text/javascript">
$(document).ready(function() { 
	$('#rm_group').change(
			function() {
				$.getJSON('${getRmCategory}', {
					grpId : $(this).val(),
					ajax : 'true'
				}, function(data) {
					var html = '<option value="" disabled="disabled" selected >Select Category</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].catId + '">'
								+ data[i].catName + '</option>';
					}
					html += '</option>';
					$('#rm_cat').html(html);
					$('#rm_cat').formcontrol('refresh');

				});
			});
});
$(document).ready(function() { 
	$('#rm_cat').change(
			function() {
				$.getJSON('${getRmListByCatId}', {
					catId : $(this).val(),
					ajax : 'true'
				}, function(data) {
					var html = '<option value="" disabled="disabled" selected >Select Category</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].rmId + '">'
								+ data[i].rmName + '</option>';
					}
					html += '</option>';
					$('#rm_id').html(html);
					$("#rm_id").trigger("chosen:updated");

				});
			});
});

</script>
<script>
function myFunction() {
  var input, filter, table, tr, td, i;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("table1");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1];
    if (td) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}
</script>
<script>
$(document).ready(function() {
    $("input[name$='isMultiFactor']").click(function() {
        var test = $(this).val();
        if(test==1){
        $("#itemDiv").show();
        onRmIdChange();
        }else
        	{
        	  $("#itemDiv").hide(); 	
        	}
       
    });
});

</script>
<script type="text/javascript">
function onRmIdChange()
{
	var rmType = $("#rm_type").val();
	var rmId = $("#rm_id").val();

		$.getJSON('${getItemsForItemDetail}', {
			rmType : rmType,
			rmId:rmId,
			ajax : 'true'
		}, function(data) {
			var html = '<option value="" disabled="disabled" selected >Select Item</option>';
			
			var len = data.length;
			for ( var i = 0; i < len; i++) {
				html += '<option value="' + data[i].id + '">'
						+ data[i].itemName + '</option>';
			}
			html += '</option>';
			$('#ratio_item_id').html(html);
			$("#ratio_item_id").trigger("chosen:updated");

		});
	
}

function onRmIdChangeForEdit(rmId,rmType,id)
{
	
		$.getJSON('${getItemsForItemDetail}', {
			rmType : rmType,
			rmId:rmId,
			ajax : 'true'
		}, function(data) {
			var html = '<option value="" disabled="disabled" selected >Select Item</option>';
			
			var len = data.length;
			for ( var i = 0; i < len; i++) {
				if(id==data[i].id){
				html += '<option value="' + data[i].id + '" selected>'
						+ data[i].itemName + '</option>';
				}
				else
					{
					html += '<option value="' + data[i].id + '">'
					+ data[i].itemName + '</option>';
					}
			}
			html += '</option>';
			$('#ratio_item_id').html(html);
			$("#ratio_item_id").trigger("chosen:updated");

		});
	
}
</script>

</html>

