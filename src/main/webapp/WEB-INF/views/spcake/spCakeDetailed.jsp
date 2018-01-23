<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
	
<c:url var="editSpCakeDetail" value="/editSpCakeDetail" />

<c:url var="insertSpCakeDetailed" value="/insertSpCakeDetailed" />
<c:url var="editSpItemDetail" value="/editSpItemDetail"/>
<c:url var="deleteSpCakeDetail" value="/deleteSpCakeDetail"/>
<c:url var="getRawMaterialListinSpcake" value="/getRawMaterialListinSpcake" />
<c:url var="addCakeDetailItem" value="/addCakeDetailItem"/> 


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
						<i class="fa fa-file-o"></i>Cake Detail
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
								<i class="fa fa-bars"></i>Add Cake Detail
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showSpecialCake">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							<!-- <div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> <a data-action="close" href="#"><i
									class="fa fa-times"></i></a>
							</div> -->
						</div>


						<div class="box-content">
							<form action="addCakeDetailItem" method="post" class="form-horizontal" id=
									"validation-form"
										 method="post">
							

								<div class="form-group">
									<label class="col-sm-2 col-lg-2 control-label">Cake Name</label>
									<div class="col-sm-9 col-lg-4 controls">
									
								  <input type="text" name="cakeName" id="cakeName" class="form-control" placeholder="Item Name" value="${cakeName}" disabled data-rule-required="true"/>
								  <input type="hidden" name="cake_Id" id="cake_Id" class="form-control"  value="${cakeId}" />
								  	
		
									</div>
                                    
									
								</div>
								<div class="box"><div class="box-title">
										<h3>
											<i class="fa fa-record"></i> Add Item 
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											
										</div>
									</div></div>
								<div class="form-group">
									<label class="col-sm-2 col-lg-2 control-label">RM Type</label>
									<div class="col-sm-6 col-lg-4 controls">
                                    <select name="rm_type" id="rm_type" class="form-control chosen" placeholder="Raw Material Type" data-rule-required="true" >
											<option value="-1">Select RM Type</option>
											<option value="1">Raw Material</option>
											<option value="2">Semi Finished</option>
									
								   </select>									
								   </div>

									<label class="col-sm-3 col-lg-1 control-label">Raw Material</label>
									<div class="col-sm-6 col-lg-4 controls">
										<select name="rm_id" id="rm_id" class="form-control chosen" placeholder="Raw Material" data-rule-required="true">
											<option value="-1">Select Raw Material</option>
										    
								</select>
									</div>
								</div>
							
								<div class="form-group">
								
						     	<input type="hidden" name="rm_unit_id" id="rm_unit_id "class="form-control"placeholder="RM Unit"/>
									
								<label class="col-sm-3 col-lg-2 control-label">RM Qty</label>
					      	    <div class="col-sm-6 col-lg-4 controls">
							    <input type="text" name="rm_qty" id="rm_qty" class="form-control"placeholder="RM Qty" required/>
					     	    </div>
					     	    
					     	    <label class="col-sm-3 col-lg-1 control-label">RM Weight</label>
					      	    <div class="col-sm-5 col-lg-4 controls">
							    <input type="text" name="rm_weight"   id="rm_weight" class="form-control"placeholder="RM Weight(KG)"data-rule-required="true"/>
					     	    </div>
								</div>
								<div class="form-group">
				
				                <label class="col-sm-3 col-lg-2 control-label">No. Of Pieces Per Item</label>
								 	<div class="col-sm-6 col-lg-4 controls">
						     	    <input type="text" name="base_qty" id="base_qty" class="form-control"placeholder="No. Of Pieces Per Item" value="${item.minQty}"   data-rule-required="true"/>
									
									</div> 
								</div>
					<div class="row">
						<div class="col-md-12" style="text-align: center">
							<input type="button" class="btn btn-info" value="ADD" name="add" id="add">


						</div>
					</div>
					</form>
					<br>
					<form action="${pageContext.request.contextPath}/insertSpCakeDetailed" method="post" class="form-horizontal" id=
									"validation-form"
										 method="post">
						<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i> Item Detail List
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
											<table width="100%" class="table table-advance" id="table1">
												<thead>
													<tr>
														<th width="45" style="width: 18px">Sr.No.</th>
														<!-- <th width="100" align="left">Item Name</th> -->
														<th width="100" align="left">RM Type</th>
														<th width="100" align="left">Raw Material</th>
														<th width="100" align="left">RM Unit</th>
														<th width="100" align="left">RM Weight</th>
														<th width="100" align="left">RM Qty</th>
														<th width="81" align="left">Action</th>
													</tr>
												</thead>
												<tbody>
													 <c:forEach items="${spCakeDetailedList}" var="spCakeDetailedList" varStatus="count">
														<tr>
														<c:choose>
         
                                                                <c:when test = "${spCakeDetailedList.delStatus==0}">
                                                                
															<td><c:out value="${count.index+1}"/></td>
															<%-- <td align="left"><c:out
																	value="${cakeName}"></c:out></td> --%>
															<c:choose>
         
                                                                <c:when test = "${spCakeDetailedList.rmType==1}">
                                                                 
																	<td align="left"><c:out
																	value="Raw Material"></c:out></td>
                                                                </c:when>
         
      															   <c:when test = "${spCakeDetailedList.rmType==2}">
       															   
																	<td align="left"><c:out
																	value="Semi Finished"></c:out></td>
       																  </c:when>
         
       															  <c:otherwise>
       															  </c:otherwise>
      														</c:choose>
															
																	<td align="left"><c:out
																	value="${spCakeDetailedList.rmName}"></c:out></td>
															<td align="left"><c:out
																	value="${spCakeDetailedList.rmUomId}"></c:out></td>	
															<td align="left"><c:out
																	value="${spCakeDetailedList.rmWeight}"></c:out></td>
															<td align="left"><c:out
																	value="${spCakeDetailedList.rmQty}"></c:out></td>						
																	
															<td align="left"><a href='#' class='action_btn' onclick="editItemDetail(${count.index})"> <abbr title='edit'> <i class='fa fa-edit  fa-lg' ></i></abbr> </a>&nbsp;&nbsp;&nbsp;&nbsp;

		                                                    <a href='#' class='action_btn'onclick="deleteItemDetail(${count.index})"><abbr title='Delete'><i class='fa fa-trash-o  fa-lg'></i></abbr></a>														</td>
														     
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
							<input type="button" class="btn btn-info" value="Submit" name="Submit" id="Submit">

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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>

			<script type="text/javascript"
				src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
			<script type="text/javascript"
				src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>
				
</body>

<script type="text/javascript">
$(document).ready(function() { 
	$('#rm_type').change(
			function() {
//alert("type");
var rm_type = $("#rm_type").val();
//alert(rm_type);

	$.getJSON('${getRawMaterialListinSpcake}', {
					rm_type : rm_type,
					ajax : 'true',
				},  function(data) {
					var html = '<option value="" selected >Select Raw Material</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].id + '">'
								+ data[i].name + '</option>';
					}
					html += '</option>';
					$('#rm_id').html(html);
					$("#rm_id").trigger("chosen:updated");
				});
			});
			

});
</script>

<script type="text/javascript">

var editFlag=false;
var key1=0;

$(document).ready(function() {
	$("#add").click(function() {
		
	var isValid = validation();
	if (isValid) {
		//alert("asdf");
		var cakeName = $("#cakeName").val();
 	var cakeId = $("#cake_Id").val();
	//alert("cake_Id"+cakeId)
	var baseQty = $("#base_qty").val();
	//alert("baseQty"+baseQty)
	
	var rmType = $("#rm_type").val();
	//alert("rmType"+rmType);
	var rmId = $("#rm_id").val();
	//alert("rmId"+rmId);
	var rmWeight = $("#rm_weight").val();
	//alert(rmWeight);
	var rmQty = $("#rm_qty").val();
	//alert("rm_qty"+rmQty);
	var rmName=$('#rm_id option:selected').text();
	//alert("rmName"+rmName);
	if(editFlag==true)
		{
		editFlag=false;
		//alert("in if");
		//alert("key for edit "+key1);
		$.getJSON('${editSpCakeDetail}', {
			
			cakeId : cakeId,
			baseQty:baseQty,
			rmType:rmType,
			rmId:rmId,
			rmName:rmName,
			rmQty:rmQty,
			rmWeight:rmWeight,
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


		  	if(item.rmType==1)
		  		{
			  	
		  		 tr.append($('<td></td>').html("Raw Material"));

		  		}
		  	else
		  		{
			  	tr.append($('<td></td>').html("Semi Finished"));

		  		}

		  	tr.append($('<td></td>').html(item.rmName));

		  	tr.append($('<td></td>').html(item.rmUomId));
		  	
		  	tr.append($('<td></td>').html(item.rmWeight));
		  	
		  	tr.append($('<td></td>').html(item.rmQty));
		  	
		 	tr.append($('<td></td>').html("<a href='#' class='action_btn' onclick=editItemDetail("+key+")> <abbr title='edit'> <i class='fa fa-edit  fa-lg' ></i></abbr> </a> <a href='#' class='action_btn'onclick=deleteItemDetail("+key+ ")><abbr title='Delete'><i class='fa fa-trash-o  fa-lg'></i></abbr></a>"));
		  
		  //	tr.append($('<td></td>').html());
		  	
			$('#table1 tbody').append(tr);
	
		  }
		 }); 
		
		});
		}
	else
		{
		$.getJSON('${addCakeDetailItem}', {
			
			
			cakeName:cakeName,
			cakeId : cakeId,
			baseQty:baseQty,
			rmType:rmType,
			rmId:rmId,
			rmName:rmName,
			rmQty:rmQty,
			rmWeight:rmWeight,
			
			ajax : 'true',
		},  function(data) { 
	 
			//alert("ala re bho");
			var len = data.length;

			$('#table1 td').remove();

			$.each(data,function(key, item) {

	      if(item.delStatus==0)
		  {
			var tr = $('<tr></tr>');

		  	tr.append($('<td></td>').html(key+1));

		  	/* tr.append($('<td></td>').html(cakeName)); */

		  	if(item.rmType==1)
		  		{
			  	
		  		 tr.append($('<td></td>').html("Raw Material"));

		  		}
		  	else
		  		{
			  	tr.append($('<td></td>').html("Semi Finished"));

		  		}

		  	tr.append($('<td></td>').html(item.rmName));

		  	tr.append($('<td></td>').html(item.rmUomId));
		  	
		  	tr.append($('<td></td>').html(item.rmWeight));
		  	
		  	tr.append($('<td></td>').html(item.rmQty));
		  	
		 	tr.append($('<td></td>').html("<a href='#' class='action_btn' onclick=editItemDetail("+key+")> <abbr title='edit'> <i class='fa fa-edit  fa-lg' ></i></abbr> </a> <a href='#' class='action_btn'onclick=deleteItemDetail("+key+ ")><abbr title='Delete'><i class='fa fa-trash-o  fa-lg'></i></abbr></a>"));
		  
		  //	tr.append($('<td></td>').html());
		  	
			$('#table1 tbody').append(tr);
	
		  }
		 }); 
		
		});
		
	}
	
	
	
	}
});

});


function editItemDetail(token){
 
	editFlag=true;
	//alert("in function"+editFlag);
	//alert("key"+token);
	$.getJSON('${editSpItemDetail}', {
		
		key:token,
		ajax : 'true',

	}, function(data) {
		//alert(data);
		var len = data.length;
 		//alert("alare");
		         document.getElementById("rm_weight").value=data.rmWeight;
		       
				 document.getElementById("rm_qty").value=data.rmQty;
				 document.getElementById("rm_type").options.selectedIndex =data.rmType;
				/*  var ht = '<option value="" selected >Select Rm Type</option>';
				 if(data.rmType==1)
					 {
					 ht += '<option value="' + data[i].id + '" selected>'
						+ data[i].name + '</option>';
					 } */
				 document.getElementById("base_qty").value =data.noOfPiecesPerItem;
				 document.getElementById("rm_id").options.selectedIndex =data.rmId;
				 var id = data.rmId;
				 //alert("id"+id+"data.rm_id"+data.rmId);
				 
					$.getJSON('${getRawMaterialListinSpcake}', {
						rm_type : data.rmType,
						ajax : 'true',
					},  function(data) {
						
						var html = '<option value="" selected >Select Raw Material</option>';
						//alert("id"+id);
						var len = data.length;
						for ( var i = 0; i < len; i++) {
							if(data[i].id==id)
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
						$('#rm_id').html(html);
						$("#rm_id").trigger("chosen:updated");
					});
					//alert(data.rmId)
					 document.getElementById("rm_id").value =data.rmId;

				
				 key1=token;
	 

		});
	
	
}
function validation() {
	
	var baseQty = $("#base_qty").val();
	var rmType = $("#rm_type").val();
	//alert(rmType);
	var rmId = $("#rm_id").val();
	var rmWeight = $("#rm_weight").val();
	var rmQty = $("#rm_qty").val();

	var isValid = true;
	if (rmType==-1) { 
		isValid = false;
		alert("Please Select  RM Type");
	} else if (rmId ==-1) {
		isValid = false;
		alert("Please Select RM ");
	}else if (rmQty == ""||rmQty=='0') {
		isValid = false;
		alert("Please Enter Valid RM Qty");
	}else if (rmWeight == ""||rmWeight=='0') {
		isValid = false;
		alert("Please Enter Valid RM Weight");
	}else if (baseQty == "") {
		isValid = false;
		alert("Please Enter No. of Pieces Per Item.");
	}
	return isValid;
}

</script>
<script type="text/javascript">
function deleteItemDetail(key){

	$.getJSON('${deleteSpCakeDetail}', {
		
		key:key,
	
		ajax : 'true',

	}, function(data) {
		
		 //$('#loader').hide();
		var len = data.length;

		//alert(len);

		$('#table1 td').remove();

		$.each(data,function(key, item) {
			
	if(item.delStatus==0)
	{
			
		var tr = $('<tr></tr>');
		//alert("key "+key);
	  	tr.append($('<td></td>').html(key+1));


	  	if(item.rmType==1)
	  		{
		  	
	  		 tr.append($('<td></td>').html("Raw Material"));

	  		}
	  	else
	  		{
		  	tr.append($('<td></td>').html("Semi Finished"));

	  		}

	  	tr.append($('<td></td>').html(item.rmName));

	  	tr.append($('<td></td>').html(item.rmUomId));
	  	
	  	tr.append($('<td></td>').html(item.rmWeight));
	  	
	  	tr.append($('<td></td>').html(item.rmQty));
	  	
	 	tr.append($('<td></td>').html("<a href='#' class='action_btn' onclick=editItemDetail("+key+")> <abbr title='edit'> <i class='fa fa-edit  fa-lg' ></i></abbr> </a> <a href='#' class='action_btn' onclick=deleteItemDetail("+key+ ")><abbr title='Delete'><i class='fa fa-trash-o  fa-lg'></i></abbr></a>"));
	  
	  //	tr.append($('<td></td>').html());
	  	
		$('#table1 tbody').append(tr);

	}
		})
		
		});
}
</script>

<script type="text/javascript">
$(document).ready(function() {
	$("#Submit").click(function() {

	$.getJSON('${insertSpCakeDetailed}', {
	
	
		ajax : 'true',

	}, function(data) {
		
		 //$('#loader').hide();
		var len = data.length;

		alert("Item Detail Saved Successfully");

		$('#table1 td').remove();

		$.each(data,function(key, item) {

	if(item.delStatus==0)
    {	
		var tr = $('<tr></tr>');

	  	tr.append($('<td></td>').html(key+1));


	  	if(item.rmType==1)
	  		{
		  	
	  		 tr.append($('<td></td>').html("Raw Material"));

	  		}
	  	else
	  		{
		  	tr.append($('<td></td>').html("Semi Finished"));

	  		}

	  	tr.append($('<td></td>').html(item.rmName));

	  	tr.append($('<td></td>').html(item.rmUomId));
	  	
	  	tr.append($('<td></td>').html(item.rmWeight));
	  	
	  	tr.append($('<td></td>').html(item.rmQty));
	  	
	 	tr.append($('<td></td>').html("<a href='#' class='action_btn' onclick=editItemDetail("+key+")> <abbr title='edit'> <i class='fa fa-edit  fa-lg' ></i></abbr> </a> <a href='#' class='action_btn'onclick=deleteItemDetail("+key+ ")><abbr title='Delete'><i class='fa fa-trash-o  fa-lg'></i></abbr></a>"));
	  
	  //	tr.append($('<td></td>').html());
	  	
		$('#table1 tbody').append(tr);
       }

		})

		});
});
});
</script>
</html>

