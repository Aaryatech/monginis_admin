<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>


<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<style>
.buttonload {
  background-color: white; /* Green background */
  border: none; /* Remove borders */
  color: blue; /* White text */
  padding: 12px 24px; /* Some padding */
  font-size: 16px; /* Set a font-size */
}

/* Add a right margin to each icon */
.fa {
  margin-left: -12px;
  margin-right: 8px;
}
</style>
<body>

	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<c:url var="searchCategoryStock" value="/searchCategoryStock"></c:url>
	<c:url var="calcCategoryStock" value="/calcCategoryStock"></c:url>
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
					<i class="fa fa-file-o"></i>Category Stock Utility
				</h1>
				<h4></h4>
			</div>
		</div>
		<!-- END Page Title -->

		<!-- BEGIN Breadcrumb -->
		
		<!-- END Breadcrumb -->

		<!-- BEGIN Main Content -->
		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>Category Stock Utility
				</h3>

			</div>

			<div class="box-content">
				<div class="row">


					<div class="form-group">
						<label class="col-sm-3 col-lg-2	 control-label">From Date</label>
						<div class="col-sm-6 col-lg-4 controls date_select">
							<input class="form-control date-picker" id="fromDate"
								name="fromDate" size="30" type="text" value="${todaysDate}" />
						</div>

						<!-- </div>

					<div class="form-group  "> -->

						<label class="col-sm-3 col-lg-2	 control-label">To Date</label>
						<div class="col-sm-6 col-lg-4 controls date_select">
							<input class="form-control date-picker" id="toDate" name="toDate"
								size="30" type="text" value="${todaysDate}" />
						</div>
					</div>

				</div>
		<br>

				<div class="row">
					<div class="form-group">
						<label class="col-sm-3 col-lg-2 control-label">Sub-Category</label>
						<div class="col-sm-3 col-lg-4">

							<select data-placeholder="Choose Sub-Category"
								class="form-control chosen" multiple="multiple" tabindex="6"
								id="selectSubCat" name="selectSubCat" >

								<%-- <option value="${allSubCats}"><c:out value="All"/></option> --%>

								<c:forEach items="${subCatList}" var="subcat" varStatus="count">
									<option value="${subcat.subCatId}"><c:out value="${subcat.subCatName}"/></option>
								</c:forEach>
							</select>
						</div>
						<label class="col-sm-3 col-lg-2 control-label">
							Franchisee</label>
						<div class="col-sm-6 col-lg-4">

							<select data-placeholder="Choose Franchisee"
								class="form-control chosen" multiple="multiple" tabindex="7"
								id="selectFr" name="selectFr" >

								<%-- <option value="${allFrs}"><c:out value="All"/></option> --%>

								<c:forEach items="${unSelectedFrList}" var="fr"
									varStatus="count">
									<option value="${fr.frId}"><c:out value="${fr.frName}"/></option>
								</c:forEach>
							</select>

						</div>
					

					</div>
						<div class="col-sm-3 col-md-12" style="text-align: center;"><br>
						<button class="btn btn-info" onclick="searchCategoryStock()" id="searchBtn">Search</button>
				<div  id="loader" style="display: none;" >
						<button class="buttonload">
  <i class="fa fa-spinner fa-spin"></i>Loading..
</button>
					</div></div>

				</div>
			</div>


			<div class="box">
				
				<form id="submitBillForm"
					action="${pageContext.request.contextPath}/submitCatOrder"
					method="post">
					<div class="box-content">
						<div class="row">
							<div class="col-md-12 table-responsive">
								<table class="table table-bordered table-striped fill-head "
									style="width: 100%" id="table_grid">
									<thead>
										<tr>
										</tr>
									</thead>
									<tbody>

									</tbody>
								</table>
								
								
									<div class="form-group">
					<label class="col-sm-3 col-lg-2	 control-label">Delivery Date</label> 
						<div class="col-sm-6 col-lg-2 controls date_select">
							 <input class="form-control date-picker" id="delDate"
								name="delDate" size="30" type="text" value="${todaysDate}" /> 
						</div>
						<div class="form-group">
											<label class="col-sm-3 col-lg-1 control-label">Menu</label>
											<div class="col-sm-6 col-lg-3 controls">
												<select data-placeholder="Select Menu" name="menu"
													class="form-control chosen" tabindex="-1" id="menu"
													data-rule-required="true">
	                                             <optgroup label="All Menus">                                                     
											<c:forEach items="${menuList}" var="menu"  varStatus="count">
									        <option value="${menu.menuId}"><c:out value="${menu.menuTitle}"/></option>
								     	   </c:forEach>
													</optgroup>

												</select>
											</div>
										</div>
						<div class="col-sm-6 col-lg-2 controls date_select">	
								 <button class="btn btn-info" onclick="searchReport()">SAVE</button> 
						</div>
						</div>
							</div>
						
						</div>

					</div>
				</form>
			</div></div>
		<footer>
			<p>2019 © Monginis.</p>
		</footer>

		<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
			class="fa fa-chevron-up"></i></a>

</div></div>

<script type="text/javascript">
function searchCategoryStock()
{
	var isValid=validate();
	if(isValid==true){
		
		document.getElementById("searchBtn").disabled = true;
		var selectSubCat=$("#selectSubCat").val();
		var fromDate=$("#fromDate").val();
		var toDate=$("#toDate").val();
		var selectFr=$("#selectFr").val();
		$('#loader').show();
		$.getJSON(
						'${searchCategoryStock}',

						{
							fromDate:fromDate,
							toDate:toDate,
							subcatidlist:JSON.stringify(selectSubCat),
							selectFr:JSON.stringify(selectFr),
							ajax : 'true'

						},
						function(data) {
							  $('#table_grid > tr').remove();

							$('#loader').hide();
                            var frListLength=data.frList.length;
							if (data == "") {
								alert("No records found !!");
							}
							
							 var tr;
						        tr = document.getElementById('table_grid').tHead.children[0];
						        tr.insertCell(0).outerHTML = "<th align='left'>Sr.No.</th>";
						        
						        tr.insertCell(1).outerHTML = "<th style='width=170px'>Franchise</th>";
						        	var i=0;var j=0;
						        	 $.each(data.subCatList, function(key,subCat){  
						        		 i=key+2;
						        	       
						        	       var options="<option value=''>Select Item</option> ";
							                 $.each(data.itemList, function(key,item){  
							                	 options=options+"<option value="+item.id+">"+item.itemName+"</option>";
							                	  });  
						                 tr.insertCell(i).outerHTML = "<th >"+subCat.subCatName+"&nbsp;&nbsp;<select class='form-control'  required    name=items"+subCat.subCatId+" id=items"+subCat.subCatId+"    onchange='onItemChange("+subCat.subCatId+")'   >"+options+"</select></th>";
						             
						         });//franchise for end    
						         $(
									'#table_grid tbody')
									.append(tr);
						         
						         
						     	var srNo = 0;
								$.each(data.frList,function(key,fr) {
									var tr = $('<tr></tr>');
									srNo=srNo+1;
									tr
									.append($(
											'<td></td>')
											.html(""+srNo));
					        		tr.append($('<td ></td>').html(fr.frName));
					        		 $.each(data.subCatList, function(key,subCat){  
					        			 
					        			 tr.append($('<td class="col-md-4"></td>').html("<span id=billQty"+fr.frId+""+subCat.subCatId+">0</span><input type=hidden name=billQtyInp"+fr.frId+""+subCat.subCatId+" id=billQtyInp"+fr.frId+""+subCat.subCatId+"  /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id=stkQty"+fr.frId+""+subCat.subCatId+">0</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style='border: 1px dashed #AAA; width: 60px;height: 30px; border: 1px solid #CCC;'><input type='text' value=0   id=orderQty"+fr.frId+""+subCat.subCatId+"  name=orderQty"+fr.frId+""+subCat.subCatId+" style='width:100px;' />"));
					        		 });
					        		
					        		 $('#table_grid tbody').append(tr);
								});
								
								 $.each(data.categoryWiseOrderDataList,
											function(key,report) {
									 
									 $("#billQty"+report.frId+''+report.subCatId+"").html((report.billQty-report.grnQty)+"");
									 $("#billQtyInp"+report.frId+''+report.subCatId+"").val((report.billQty-report.grnQty));
									 $("#orderQty"+report.frId+''+report.subCatId+"").val((report.billQty-report.grnQty));

								 });
					});
	}
	}
</script>
<script type="text/javascript">
function onItemChange(subCatId)
{
	var itemId=$("#items"+subCatId).val();
	var fromDate=$("#fromDate").val();
	var toDate=$("#toDate").val();
	var selectFr=$("#selectFr").val();
	$('#loader').show();

	$
			.getJSON(
					'${calcCategoryStock}',

					{
						fromDate:fromDate,
						toDate:toDate,
						itemId:itemId,
						selectFr:JSON.stringify(selectFr),
						ajax : 'true'

					},
					function(data) {
						$('#loader').hide();	
						
						 $.each(data,
									function(key,report) {
						
							 $("#stkQty"+report.frId+''+subCatId+"").html((report.billQty-report.grnQty)+"");
							 var grpQty=$("#billQtyInp"+report.frId+''+subCatId+"").val();
							 var itemQty=report.billQty-report.grnQty;
							 
							 if(itemQty<grpQty)
								 {
								 $("#orderQty"+report.frId+''+subCatId+"").val(grpQty);
								 }
							 else
								 {
								 var diff=itemQty-grpQty;
								 var a=grpQty-diff;
								 
								 if(a>0)
									 {
									 $("#orderQty"+report.frId+''+subCatId+"").val(a);
									 }else
										 {
										 $("#orderQty"+report.frId+''+subCatId+"").val(0);
										 }
								 }
							 

						 });
						
					});
	
	
}
</script>

		<script type="text/javascript">
			function validate() {

				var selectSubCat = $("#selectSubCat").val();
				var selectedFr = $("#selectFr").val();
				var fromDate = $("#fromDate").val();
				var toDate=$("#toDate").val();
				var isValid = true;

			if (fromDate == "" || fromDate == null) {
				isValid = false;
				alert("Please Select From Date");

			}else
				if (toDate == "" || toDate == null) {
					
					isValid = false;
					alert("Please Select To Date");
				}else
				if (selectedFr == "" || selectedFr == null) {

					isValid = false;
					alert("Please Select Franchises");
				} else if (selectSubCat == "" || selectSubCat == null) {
					isValid = false;
					alert("Please select Sub categories");

				}
				return isValid;

			}
		</script>

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
</body>
</html>