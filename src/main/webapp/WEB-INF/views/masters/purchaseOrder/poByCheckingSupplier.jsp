<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body onload="showDiv(${type},${group},${itemId})">
	
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include> 

		<c:url var="getRmCategory" value="/getRmCategory" />
		<c:url var="getRmListByCatId" value="/getRmListByCatId" />
		<c:url var="getRmRateAndTax" value="/getRmRateAndTax" />
<c:url var="getRmListForPoByGrpId" value="/getRmList1ForPoByGrpId" />
<c:url var="getRmListForPoByGrpId2" value="/getRmListForPoByGrpId2" />

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
					<i class="fa fa-file-o"></i>PO
				</h1>
				<!-- <h4>Bill for franchises</h4> -->
			</div>
		</div>
		<!-- END Page Title -->
		
		<!-- BEGIN Main Content -->
		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>PO
				</h3>

			</div>

				<div class=" box-content">
			<form id="myForm"
				action="${pageContext.request.contextPath}/searchSuppliersByItem"
				method="post">
				 
		<div class="box">
			
				<div class="box-content">
				<div class="col-md-2" >Search By</div>
									<div class="col-md-2">
										<select name="type" id="type" class="form-control" required onchange="disable(this.value)">
										<option value="" disabled="disabled" selected="selected">Search By</option>
										<c:choose>
										<c:when test="${type==1}">
							  <option value="1" selected><c:out value="By Supplier"/></option>
 							  <option value="2"><c:out value="By ItemWise"/></option>
 							</c:when>
 							<c:when test="${type==2}">
 							  <option value="1" ><c:out value="By Supplier"/></option>
 							  <option value="2"selected><c:out value="By ItemWise"/></option>
 							</c:when>
 							<c:otherwise>
 							 <option value="1" ><c:out value="By Supplier"/></option>
 							  <option value="2"><c:out value="By ItemWise"/></option>
 							</c:otherwise>
										</c:choose>	
										</select>
									</div>
			<div id="bySupplier" style="display: none;">
			
				<div class="col-md-1" >Supplier</div>
									<div class="col-md-2">
										<select name="supp_id" id="supp_id" class="form-control chosen" tabindex="6" required>
										<option value="" disabled="disabled" selected="selected">Select Supplier</option>
											 <c:forEach items="${supplierList}" var="supplierList"
							varStatus="count">
							<c:choose>
							<c:when test="${suppId==supplierList.suppId}">
						 <option value="${supplierList.suppId}" selected><c:out value="${supplierList.suppName}"/></option>
							
							</c:when>
							<c:otherwise>
							
							  <option value="${supplierList.suppId}"><c:out value="${supplierList.suppName}"/></option>
 									</c:otherwise>		</c:choose>		 
												</c:forEach>
						

										</select>
									</div>
									
									
									
										<div class="col-md-1" >Group</div>
											<div class="col-md-3">
												<select name="rm_group" id="rm_group" class="form-control chosen" tabindex="6" onchange="onGroupChange(this.value,0)">
												<option value="-1" disabled="disabled" selected="selected">Select RM Group</option>
													 <c:forEach items="${rmItemGroupList}" var="rmItemGroupList" varStatus="count">
									  						<c:choose><c:when test="${rmItemGroupList.grpId==group}">
									  						 <option value="${rmItemGroupList.grpId}" selected><c:out value="${rmItemGroupList.grpName}"/></option>
		 											
									  						</c:when>
									  						<c:otherwise>
									  						 <option value="${rmItemGroupList.grpId}"><c:out value="${rmItemGroupList.grpName}"/></option>
		 											
									  						</c:otherwise>
									  						
									  						</c:choose>
		 											</c:forEach> 
												</select>
											</div>
										<div class="col-md-1"> 
							
									<input type="submit" class="btn btn-info pull-right"  value="Search"> 
						
					</div></div>
</div>
							<div id="byItem" style="display: none;">		<br>			<div class="box-content">
									<div class="col-md-2" >RM Group</div>
											<div class="col-md-2">
												<select name="rm_group1" id="rm_group1" class="form-control chosen" tabindex="6" onchange="onGroupChange(this.value,0)">
												<option value="-1" disabled="disabled" selected="selected">Select RM Group</option>
													 <c:forEach items="${rmItemGroupList}" var="rmItemGroupList" varStatus="count">
									  						<c:choose><c:when test="${rmItemGroupList.grpId==group}">
									  						 <option value="${rmItemGroupList.grpId}" selected><c:out value="${rmItemGroupList.grpName}"/></option>
		 											
									  						</c:when>
									  						<c:otherwise>
									  						 <option value="${rmItemGroupList.grpId}"><c:out value="${rmItemGroupList.grpName}"/></option>
		 											
									  						</c:otherwise>
									  						
									  						</c:choose>
		 											</c:forEach> 
												</select>
											</div>
							<!-- 	<div class="col-md-2">RM Category </div>
										<div class="col-md-2">
											<select name="rm_cat" id="rm_cat" class="form-control chosen" tabindex="6" onchange="onCatChange(this.value,0)">
												<option value="-1"disabled="disabled" selected="selected">Select RM Category</option>
											 
											</select>
										</div> -->
									
				 
									<!-- </div><br/>
			
					<div class="box-content"> -->
								<div class="col-md-1" >Items</div>
									<div class="col-md-2">
										<select name="rm_id" id="rm_id" class="form-control chosen"placeholder="Select RM " tabindex="6">
										<option value="-1" disabled="disabled" selected="selected">Select Raw Material</option> 
										</select>
									</div>	
		 				
		 					<div class="col-md-1"> 
							
									<input type="button" class="btn btn-info pull-right"  value="Search" onclick="submitAction()"> 
						
					</div>	</div>	
		 					</div>
					
					</div><br/>
					<br/>
			</form>
				<form id="submitForPO"
				action="${pageContext.request.contextPath}/makePoListOfSelectedItems"
				method="post">
				 
			<div id="supplierTable" style="display: none;">
				<div class="box-content">
					<div class="row">
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid">
								<thead style="background-color: #f3b5db;">
									<tr>
										<th><input type="checkbox" />	Sr.No.</th>
										<th>Product</th>
										<th>Quantity</th>
										<th>Rate Inclusive</th>
										<th>Rate Extra</th>
										<th>Discount %</th>
										<th>Schedule Days</th>
										<th>RM Remark</th>

									</tr>
								</thead>
								<tbody>
								<c:set var="submitStatus" value="0"/>
 <c:forEach items="${poDList}" var="po" varStatus="cnt">
											<tr><td>
											        <c:set var="submitStatus" value="1"/>
											        <input type="checkbox" id="chk${po.rmId}" name="chk${po.rmId}" value="${po.rmId}"/>	
											        
											       
											<c:out value="${cnt.index+1}"/>  
												</td>
												<td align="left"><c:out
														value="${po.rmName}" /></td>
											
												<td align="left"><input type="text" id="poQty${po.rmId}" name="poQty${po.rmId}"  value="${po.poQty}" class="form-control" ></td>
											 
												<c:choose>
												<c:when test="${po.rateTaxIncl<=0}">
												<td align="left" style="color:red;">${po.rateTaxIncl}</td>
												</c:when>
												<c:otherwise>
												<td align="left">${po.rateTaxIncl}</td>
												</c:otherwise>
												</c:choose>
												<c:choose>
												<c:when test="${po.rateTaxExtra<=0}">
												<td align="left" style="color:red;">${po.rateTaxExtra}</td>
												</c:when>
												<c:otherwise>
												<td align="left">${po.rateTaxExtra}</td>
												</c:otherwise>
												</c:choose>	
												
								        		
										        <td align="left"><input type="text" id="discPer${po.rmId}" name="discPer${po.rmId}"  value="${po.discPer}" class="form-control" ></td>		
												  <td align="left"><c:out
														value="${po.schDays}" /></td>			
												  <td align="left"><c:out
														value="${po.specification}" /></td>							
													</tr>
														
										</c:forEach> 
								</tbody>
							</table>
						</div>
					</div>

 
		</div>
			<div class="row">
			<div class="box-content">
				<div class="col-md-2" >Taxation</div>
									<div class="col-md-2">
										<select name="taxation" id="taxation" class="form-control chosen" tabindex="6" required>
										 <option value="2">Extra</option>
										<option value="1">Inclusive</option>
										 

										</select>
									</div>
						<div class="col-md-3" style="text-align: center">
						<c:choose>
						<c:when test="${submitStatus==1}">
							<input type="submit" class="btn btn-info" value="Submit" onclick="check()">
						</c:when>
						<c:otherwise>
													<input type="submit" disabled class="btn btn-info" value="Submit" onclick="check()">
						
						</c:otherwise>
						</c:choose>


						</div>
					</div>
		</div></div></form>
			<form id="submitForPO"
				action="${pageContext.request.contextPath}/submitForPo"
				method="post">
				 
		<div id="itemTable" style="display: none;">
	<div class="box-content" >
					<div class="row">
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
							 id="table_grid1">
								<thead style="background-color: #f3b5db;">
									<tr>
										<th>Sr.No.</th>
										<th>Name</th>
										<th>Mobile</th>
										<th>City</th>
										<th>State</th>
										<th>Lead Time</th>
										<th>Rate Inclusive</th>
										<th>Rate Extra</th>

									</tr>
								</thead>
								<tbody>
	                <c:forEach items="${supplierLists}" var="supplier" varStatus="count">
											<tr>
												<td>	
											<c:out value="${count.index+1}"/>  
												</td>
												<td align="left"><c:out
														value="${supplier.suppName}" /></td>
											
												<td align="left"><c:out
														value="${supplier.suppMob1}" /></td>
											
												<td align="left"><c:out
														value="${supplier.suppCity}" /></td>		
								        		<td align="left"><c:out
						  								value="${supplier.suppState}" /></td>		
										        <td align="left"><c:out
														value="${supplier.suppEmail5}" /></td>	
												 <td align="left"><c:out
														value="${supplier.suppPhone1}" /></td>	
												<td align="left"><c:out
														value="${supplier.suppPhone2}" /></td>		
												<%-- <td align="left"><a
													href="${pageContext.request.contextPath}/showDirectPurchaseOrder/${supplier.suppId}">PO</a></td>	 --%>
														</tr>
														
										</c:forEach>  
								</tbody>
							</table>
						</div>
					</div>

 
		</div>
			<div class="row">
						<div class="col-md-12" style="text-align: center">
<!-- 							<input type="submit" class="btn btn-info" value="Submit" onclick="check()">
 -->

						</div>
					</div>
		</div>
			
		</form>
				
		
			</div>	
		</div>
	</div>
	
	<!-- END Main Content -->

	<footer>
	<p>2018 © Monginis.</p>
	</footer>

	<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
		class="fa fa-chevron-up"></i></a>

	<script type="text/javascript">
		function validate() {
			

			 
	 
			var rm_qty = $("#rm_qty").val();
			var supp_id = $("#supp_id").val();
  
			var rm_id = $("#rm_id").val();
			var disc_per = $("#disc_per").val();

			var isValid = true;

			if (rm_qty=="") {

				isValid = false;
				alert("Please enter valid qty");

			}
			else if (isNaN(rm_qty) || rm_qty <= 0){
				isValid = false;
				alert("Please enter valid qty");
			}
			else if (rm_id == "" || rm_id == null) {

				isValid = false;
				alert("Please select RM Item");

			}
			
			else if (supp_id == "" || supp_id == null) {

				isValid = false;
				alert("Please select Supplier");

			}
			
			else if (  disc_per=="") {

				isValid = false;
				alert("Please enter valid Discount %");
				

			}
			else if(isNaN(disc_per) || disc_per < 0)
				{
				isValid = false;
				alert("Please enter valid Discount %");
				}
			return isValid;

		}
		
	</script>


<!--   <script>
    /*
//  jquery equivalent
jQuery(document).ready(function() {
   jQuery(".main-table").clone(true).appendTo('#table-scroll .faux-table').addClass('clone');
   jQuery(".main-table.clone").clone(true).appendTo('#table-scroll .faux-table').addClass('clone2');
 });
*/
(function() {
  var fauxTable = document.getElementById("faux-table");
  var mainTable = document.getElementById("table_grid");
  var clonedElement = table_grid.cloneNode(true);
  var clonedElement2 = table_grid.cloneNode(true);
  clonedElement.id = "";
  clonedElement2.id = "";
  fauxTable.appendChild(clonedElement);
  fauxTable.appendChild(clonedElement2);
})();

</script> -->


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
		
		
		
		<script type="text/javascript">

			function onGroupChange(grpId,itemId) {
				var html = '<option value="0" selected >Select Raw Material</option>';
				html += '</option>';
				$('#rm_id').html(html);
				$("#rm_id").trigger("chosen:updated");
				if(grpId==4||grpId==5){
				$.getJSON('${getRmListForPoByGrpId}', {
					grpId : grpId,
					ajax : 'true'
				}, function(data) {
					/* var html = '<option value="" disabled="disabled" selected >Select Category</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						if(catId!=0 && data[i].catId==catId)
						{
						html += '<option value="' + data[i].catId + '"selected>'
						+ data[i].catName + '</option>';
						}
						html += '<option value="' + data[i].catId + '">'
								+ data[i].catName + '</option>';
					}
					html += '</option>';
					$('#rm_cat').html(html);
					$('#rm_cat').trigger("chosen:updated"); */
                    var html = '<option value="" disabled="disabled" selected >Select Raw Material</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						if(itemId!=0 && data[i].rmId==itemId)
							{
							html += '<option value="' + data[i].id + '"selected>'
							+ data[i].itemName + '</option>';
							}else{
						html += '<option value="' + data[i].id + '">'
								+ data[i].itemName + '</option>';
							}
					}
					html += '</option>';
					$('#rm_id').html(html);
					$("#rm_id").trigger("chosen:updated");


				});
			}else
				{
				$.getJSON('${getRmListForPoByGrpId2}', {
					grpId : grpId,
					ajax : 'true'
				}, function(data) {
                    var html = '<option value="" disabled="disabled" selected >Select Raw Material</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						if(itemId!=0 && data[i].rmId==itemId)
							{
							html += '<option value="' + data[i].rmId + '"selected>'
							+ data[i].rmName + '</option>';
							}else{
						html += '<option value="' + data[i].rmId + '">'
								+ data[i].rmName + '</option>';
							}
					}
					html += '</option>';
					$('#rm_id').html(html);
					$("#rm_id").trigger("chosen:updated");


				});
				}
			}

			/* function onCatChange(catId,itemId) {
				$.getJSON('${getRmListByCatId}', {
					catId : catId,
					ajax : 'true'
				}, function(data) {
					var html = '<option value="" disabled="disabled" selected >Select Raw Material</option>';
					
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						if(itemId!=0 && data[i].rmId==itemId)
							{
							html += '<option value="' + data[i].rmId + '"selected>'
							+ data[i].rmName + '</option>';
							}
						html += '<option value="' + data[i].rmId + '">'
								+ data[i].rmName + '</option>';
					}
					html += '</option>';
					$('#rm_id').html(html);
					$("#rm_id").trigger("chosen:updated");

				});
				
			 
	
			} */

 /* 
 $(document).ready(function() { 
	$('#rm_id').change(
			function() {
			var	rm_id =document.getElementById("rm_id").value; 
			
				$.getJSON('${getUomForRawMaterial}', { 
					ajax : 'true'
				}, function(uomlist) {
					
					 var uomlistlength = uomlist.length; 
					 cId=document.getElementById("rm_cat").value; 
								$.getJSON('${getRmListByCatId}', {
									
									catId : cId,
									ajax : 'true'
									
								}, 
								function(data) {
									
									var len = data.length;
									var uom; 
									for ( var i = 0; i < len; i++) { 
											if(data[i].rmId==rm_id)
												{
												uom=data[i].rmUomId;
												break;
												} 
									}
									
									for(var j = 0; j< uomlistlength; j++)
									{
									 
									if(uom==uomlist[j].uomId)
										{ 
										document.getElementById("rm_uom").value=uomlist[j].uom;
										break;
										}
									
									}
									
									 
								});

				});
				
			 
	
			});
}); */
</script>

<script type="text/javascript">

function disable(id) {
   if(id==1){
	var x = document.getElementById("bySupplier");
    x.style.display = "block";
	var y = document.getElementById("byItem");
    y.style.display = "none";
    
    var w = document.getElementById("supplierTable");
    w.style.display = "block";
    var z = document.getElementById("itemTable");
    z.style.display = "none";
   }
   else
	   if(id==2)
		   {
		
		   var x = document.getElementById("bySupplier");
		    x.style.display = "none";
			var y = document.getElementById("byItem");
		    y.style.display = "block";
		    
		    var w = document.getElementById("supplierTable");
		    w.style.display = "none";
		    var z = document.getElementById("itemTable");
		    z.style.display = "block";
		   }
}
function showDiv(type,groupId,itemId) {
	
	  if(type==1){
		  onGroupChange(groupId,itemId);
			var x = document.getElementById("bySupplier");
		    x.style.display = "block";
			var y = document.getElementById("byItem");
		    y.style.display = "none";
		    
		    var w = document.getElementById("supplierTable");
		    w.style.display = "block";
		    var z = document.getElementById("itemTable");
		    z.style.display = "none";
		   }
		   else
			   if(type==2)
				   {
				   onGroupChange(groupId,itemId);
				   //onCatChange(catId,itemId);
				
				   var x = document.getElementById("bySupplier");
				    x.style.display = "none";
					var y = document.getElementById("byItem");
				    y.style.display = "block";
				    
				    var w = document.getElementById("supplierTable");
				    w.style.display = "none";
				    var z = document.getElementById("itemTable");
				    z.style.display = "block";
				   }
	
}
</script>
<script type="text/javascript">
function submitAction()
{
    document.getElementById("myForm").submit();

	
}
</script>		
		
</body>
</html>