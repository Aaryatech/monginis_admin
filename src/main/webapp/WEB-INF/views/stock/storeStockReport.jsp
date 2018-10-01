<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<style>
table{
  width:100%;
  table-layout: fixed;
  border:1px solid #ddd;
}</style>
	<!--  
	<style type="text/css">
	 <style type="text/css">
	table {
            width: 100%;
        }

        thead, tbody, trtd, , th { display: block; }

        tr:after {
            content: ' ';
            display: block;
            visibility: hidden;
            clear: both;
        }

        thead th {
            height: 35px;

             text-align: left; 
        }

        tbody {
            height: 500px;
            overflow-y: auto;
           
        }

        thead {
             fallback  
        }

 
	 
	   tbody td, thead th {
            width: 16.2%;
            float: left;
        }
	</style>
	  -->
	<body onload="disabledDate()">
 	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
<c:url var="getCurrentStoreStock" value="/getCurrentStoreStockReport" />
<c:url var="getMonthWiseStoreStock" value="/getMonthWiseStoreStock" />
<c:url var="getDateWiseStoreStock" value="/getDateWiseStoreStock" />
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
			
			<!-- END Page Title -->



			<!-- BEGIN Main Content -->
			<div class="row">
				<div class="col-md-12">
					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-bars"></i>Store Stock Report
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showStoreStockPoReport">Generated PO</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>


						<div class="box-content">
							<%-- <form action="${pageContext.request.contextPath}/getBmsStock"
								class="form-horizontal" method="post" id="validation-form">
							 --%>	



								<div class="form-group">
						
  <label class="col-sm-2 col-lg-1 control-label">Group</label>
														<div class="col-sm-1 col-lg-2 controls">
															<select data-placeholder="Select Category"
																class="form-control chosen" tabindex="6" name="group_id" onchange="onGrpChange(this.value)"
																id="group_id">
																	<option value="0">Select Group</option>
																<c:forEach items="${rmItemGroupList}" var="rmItemGroupList">	<c:choose>
																<c:when test="${subCatId==rmItemGroupList.grpId}">
                                  								<option  value="${rmItemGroupList.grpId}" selected>${rmItemGroupList.grpName}</option>
                                                                </c:when>
                                                                <c:otherwise>
															     <option  value="${rmItemGroupList.grpId}" >${rmItemGroupList.grpName}</option>
                                                                </c:otherwise>
																</c:choose>
																</c:forEach>
															</select>
														</div>
					
					<div class="col-md-1" >
						<input type="button"  class="btn btn-primary" value="Get Stock" onclick="getStock()">
					 


					</div>
				</div>
							<div class="form-group">&nbsp;&nbsp;
					
								</div>
							
							<!-- </form> -->


							<form action="${pageContext.request.contextPath}/dayEndStoreStock"
								class="form-horizontal" method="post" id="validation-form">
<input type="hidden" id="subCat" name="subCat"/>

			<div align="center" id="loader" style="display:none">

	<span>
	<h4><font color="#343690">Loading</font></h4></span>
	<span class="l-1"></span>
	<span class="l-2"></span>
	<span class="l-3"></span>
	<span class="l-4"></span>
	<span class="l-5"></span>
	<span class="l-6"></span>
	</div>
	
								<div class="box">
							

									<div class="box-content">
<jsp:include page="/WEB-INF/views/include/tableSearch.jsp"></jsp:include>

										<div class="clearfix"></div>
										<div class="table-responsive"  id="div1">
											<table
												class="table" border="1"
												id="table1">
												<thead style="background-color: #eeeeef;">
													<tr>

													
														<th class="col-md-1">Sr No</th>
														<th class="col-md-1">Code</th>
														<th class="col-md-2">Material Name</th>
												    	<th class="col-md-1">Op Stock</th>
														
														<th class="col-md-1">InwardQty</th>
														<th class="col-md-1">BOM Qty</th>
														 
														<th class="col-md-1">Clos Qty</th>
														<th class="col-md-1">Min Qty</th>
														<th class="col-md-1">ROL Qty</th>
														<th class="col-md-1">Max Qty</th>
														<th class="col-md-1">PO Qty</th>
														<th class="col-md-1">Status</th>
													<!-- 	<th>Status</th> -->

													</tr>

												</thead>
												 <tbody>
													<%--<c:forEach items="${stockList}" var="stockList"
														varStatus="count">

														<tr>
															<td><c:out value="${count.index+1}"></c:out></td>
															<td><c:out value="${stockList.rmName}"></c:out></td>
															<td><c:out value="${stockList.invertQty}"></c:out>
															</td>
															<td><c:out value="${stockList.bomQty}"></c:out>
															</td>
															 
															<td><c:out value="${stockList.openingQty}"></c:out>
															<td><c:out value="${stockList.closingQty}"></c:out>
															<td><c:out value="Status"></c:out></td>

														</tr>
													</c:forEach>--%>
												</tbody> 

											</table>
										</div>
										<div class="table-responsive" style="display: none;" id="div2">
											<table class="table" id="table2">
												<thead style="background-color: #eeeeef;">
													<tr>

														<th class="col-md-1">Sr No</th>
														<th class="col-md-3">Material Name</th>
														<th class="col-md-2">Op Stock</th>		
														<th class="col-md-2">Inward Qty</th>
														<th class="col-md-2">BOM Qty</th>
														<th class="col-md-2">Clos Qty</th>
														
													<!-- 	<th>Status</th> -->

													</tr>

												</thead>
												 <tbody>
													<%--<c:forEach items="${stockList}" var="stockList"
														varStatus="count">

														<tr>
															<td><c:out value="${count.index+1}"></c:out></td>
															<td><c:out value="${stockList.rmName}"></c:out></td>
															<td><c:out value="${stockList.invertQty}"></c:out>
															</td>
															<td><c:out value="${stockList.bomQty}"></c:out>
															</td>
															 
															<td><c:out value="${stockList.openingQty}"></c:out>
															<td><c:out value="${stockList.closingQty}"></c:out>
															<td><c:out value="Status"></c:out></td>

														</tr>
													</c:forEach>--%>
												</tbody> 

											</table>
										</div>

										<!-- </form> -->

									</div>
								</div>

							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- END Main Content -->
			<footer>
			<p>2018 © MONGINIS.</p>
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
	<script>
		$("#datepicker").datepicker({
			format : "mm-yyyy",
			startView : "months",
			minViewMode : "months"
		});
	</script>

<script>
//document.getElementById("stockDate").value = today;


		function getStock(){
			 
			//  var selectId=document.getElementById("selectStock").value;
			  var grpId=document.getElementById("group_id").value;

			
			  
			  // if(selectId==1) {
				  // document.getElementById("div1").style.display="block";
			        document.getElementById("div2").style.display="none";
				   $('#loader').show();
					$.getJSON('${getCurrentStoreStock}', {
						grpId:grpId,
						ajax : 'true'
					}, function(data) {
						$('#loader').hide();
						$('#table1 td').remove();
						if(data=="")
						{
						alert("No Record Found");
						}
					else
						{
						

					 var cnt=0;
					 $.each(data,function(key, stockList) {
						 
						 var tr ;
							if(stockList.storeClosingStock<stockList.rmMinQty)	{
								cnt=cnt+1;
								 tr = $('<tr bgcolor="#fd8080"></tr>');
								 	tr.append($('<td class="col-md-1"></td>').html(cnt));			  	
								  	tr.append($('<td class="col-md-1"></td>').html(stockList.rmCode));
								  	tr.append($('<td class="col-md-2"></td>').html(stockList.rmName));
								  	tr.append($('<td class="col-md-1"></td>').html(stockList.storeOpeningStock));

								  	tr.append($('<td class="col-md-2"></td>').html(stockList.purRecQty));
								  	tr.append($('<td class="col-md-1"></td>').html(stockList.bmsIssueQty));
								  	tr.append($('<td class="col-md-1"></td>').html(stockList.storeClosingStock));
								  	tr.append($('<td class="col-md-1"></td>').html(stockList.rmMinQty));
								  	tr.append($('<td class="col-md-1"></td>').html(stockList.rmRolQty));
								  	tr.append($('<td class="col-md-1"></td>').html(stockList.rmMaxQty));
								  	var poQty=parseInt((stockList.rmMaxQty-stockList.storeClosingStock));
									tr.append($('<td class="col-md-1"></td>').html(poQty));
									tr.append($('<td class="col-md-1"></td>').html("Below Min"));
								  	//alert(stockList.storeStockDate);
								$('#table1 tbody').append(tr);
							}else if(stockList.storeClosingStock<stockList.rmRolQty)	{
								cnt=cnt+1;
								 tr = $('<tr bgcolor="#ef922d" ></tr>');
								 	tr.append($('<td class="col-md-1"></td>').html(cnt));			  	
								  	tr.append($('<td class="col-md-1"></td>').html(stockList.rmCode));
								  	tr.append($('<td class="col-md-2"></td>').html(stockList.rmName));
								  	tr.append($('<td class="col-md-1"></td>').html(stockList.storeOpeningStock));

								  	tr.append($('<td class="col-md-2"></td>').html(stockList.purRecQty));
								  	tr.append($('<td class="col-md-1"></td>').html(stockList.bmsIssueQty));
								  	tr.append($('<td class="col-md-1"></td>').html(stockList.storeClosingStock));
								  	tr.append($('<td class="col-md-1"></td>').html(stockList.rmMinQty));
								  	tr.append($('<td class="col-md-1"></td>').html(stockList.rmRolQty));
								  	tr.append($('<td class="col-md-1"></td>').html(stockList.rmMaxQty));
								  	var poQty=parseInt((stockList.rmMaxQty-stockList.storeClosingStock));
									tr.append($('<td class="col-md-1"></td>').html(poQty));
									tr.append($('<td class="col-md-1"></td>').html("Below ROL"));
								  	//alert(stockList.storeStockDate);
								$('#table1 tbody').append(tr);
							}					 
							else{
								
							}
											})
											
						}
					});
			/* 	} 
			  
			   else if(selectId==2){
				   document.getElementById("div1").style.display="none";
			        document.getElementById("div2").style.display="block";
			        
				var fromDate=document.getElementById("from_stockdate").value;
				  var toDate=document.getElementById("to_stockdate").value;
				 
				if(fromDate!=null && fromDate!="" && toDate!=null && toDate!=""){
					$('#loader').show();
				$.getJSON('${getMonthWiseStoreStock}', {
					fromDate : fromDate,
					toDate : toDate,grpId:grpId,
					ajax : 'true'
				}, function(data) {
					$('#loader').hide();
					$('#table2 td').remove();
					if(data=="")
					{
						alert("No Record Found");
					}
				else
					{
					
				
				 
				 $.each(data,function(key, stockList) {
											
				var tr = $('<tr></tr>');

			  	tr.append($('<td></td>').html(key+1));			  	
			  	tr.append($('<td></td>').html(stockList.rmName));
			  	tr.append($('<td></td>').html(stockList.storeOpeningStock));		  	
			  	tr.append($('<td></td>').html(stockList.purRecQty));
			  	tr.append($('<td></td>').html(stockList.bmsIssueQty));
			  	tr.append($('<td></td>').html(stockList.storeClosingStock));
			  	document.getElementById("stockDate").value = stockList.storeStockDate;

			$('#table2 tbody').append(tr);
			
										})
										
					}
										
					});
				}
				else{
				alert("Please Select Months");
				}
			}
			else if(selectId=3){
				 document.getElementById("div1").style.display="none";
			        document.getElementById("div2").style.display="block";
			
				var fromDate=document.getElementById("from_stockdate").value;
				  var toDate=document.getElementById("to_stockdate").value;
				 
				if(fromDate!=null && fromDate!="" && toDate!=null && toDate!=""){
					  $('#loader').show();
					$.getJSON('${getDateWiseStoreStock}', {
						fromDate : fromDate,
						toDate : toDate,
						grpId:grpId,
						ajax : 'true'
					}, function(data) {
					 
						$('#loader').hide();
						$('#table2 td').remove();
						if(data=="")
						{
						alert("No Record Found");
						}
					else
						{
						
						
					 
					 $.each(data,function(key, stockList) {
												 
					var tr = $('<tr></tr>');

				  	tr.append($('<td></td>').html(key+1));			  	
				  	tr.append($('<td></td>').html(stockList.rmName));
				  	tr.append($('<td></td>').html(stockList.storeOpeningStock));
				  	tr.append($('<td></td>').html(stockList.purRecQty));
				  	tr.append($('<td></td>').html(stockList.bmsIssueQty));
				  	tr.append($('<td></td>').html(stockList.storeClosingStock));
				$('#table2 tbody').append(tr);
				
											})
											
						}
					});
				}
				else{
					alert("Please Select Dates");
					}
			}
			
			 */
			 
		}
		
		
	</script>
	 
	
	
</body>
</html>


