<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
 <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
 <jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
<body>

<c:url var="findItemsByCategory" value="/getItemsByCategory"></c:url>
<c:url var="getItemsProdQty" value="/getItemsProdQty"></c:url>

	 


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
						<i class="fa fa-file-o"></i> Production Plan
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
								<i class="fa fa-bars"></i> Production 
							</h3>
							<div class="box-tool">
								<a href="">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							<!-- <div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> <a data-action="close" href="#"><i
									class="fa fa-times"></i></a>
							</div> -->
						</div>

						<div class="box-content">
							<form  class="form-horizontal"
								id="validation-form">



								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Category</label>

									<div class="col-sm-9 col-lg-10 controls">
										<select class="form-control chosen" name="catId" id="catId" >
										
										<c:forEach items="${catList}" var="catList">
										
										<option value="${catList.catId}">${catList.catName} </option>
										
										</c:forEach>

											
										</select>
									</div>

								</div>



								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
									
                         <input type="button" class="btn btn-info" name="submit" value="submit" onclick="searchItemsByCategory()"/>
									</div>
								</div>
					<input type="hidden" id="selectedCatId" name="selectedCatId"/>			

</form>
					<form action="${pageContext.request.contextPath}/submitProductionPlan" method ="post">

								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i> Production List
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
														<th width="20" align="left">Sr No</th>
														<th width="110" align="left">Item Name</th>
<!-- 														<th width="30" align="left">Cur Closing</th>
 -->														<th width="90" align="left">Cur Opening</th>
														
														<th width="90" align="left">
										 					<div>
 									                     	<input class="form-control date-picker" id="datepicker1" size="16" type="text" name="datepicker1" value="" placeholder="Date1"  disabled/>
 <%-- 								                     	  <a href="${pageContext.request.contextPath}/"> <span class="	glyphicon glyphicon-circle-arrow-right"></span></a>
 --%>								                     	  
                                                              </div>
														</th>
                                                       <th width="5" align="left"><i class="glyphicon glyphicon-circle-arrow-right  fa-2x" onclick=" return getProdQty(1,5)"></i>
														 </th>
                                                      	<th width="100" align="left">
															<div>
									                     	<input class="form-control date-picker" id="datepicker2" size="16" type="text" name="datepicker2" value="" placeholder="Date2"  onblur=" return getProdQty(2,2)" />
								                     	     </div>
														 </th>
														<th width="5" align="left">  <i class="	glyphicon glyphicon-circle-arrow-right  fa-2x"onclick=" return getProdQty(2,5)"></i>
														 </th>
														 
														<th width="100" align="left">
															<div>
									                     	<input class="form-control date-picker" id="datepicker3" size="16" type="text" name="datepicker3" value="" placeholder="Date3"  onblur=" return getProdQty(3,3)"/>
								                        	</div>
														</th>
													 <th width="5" align="left"> <i class="	glyphicon glyphicon-circle-arrow-right  fa-2x" onclick=" return getProdQty(3,5)"></i>
														 </th>
														<th width="100" align="left">
															<div>
									                     	<input class="form-control date-picker" id="datepicker4" size="16" type="text" name="datepicker4" value="" placeholder="Date4"  onblur=" return getProdQty(4,4)"/>
								                     	    </div>
														</th>
													 <th width="5" align="left">  <i class="	glyphicon glyphicon-circle-arrow-right  fa-2x" onclick=" return getProdQty(4,5)"></i>
														 </th>
													<!-- 	<th width="120" align="left">
															<div>
									                     	<input class="form-control date-picker" id="datepicker5" size="16" type="text" name="datepicker5" value="" placeholder="Date5"  onblur=" return getProdQty(5)"/>
								                     	    </div>
														</th> -->
														
														<th width="100" align="left" >
															<div>
									                     	<input class="form-control date-picker" id="datepicker5" size="16" type="text" name="datepicker5" value="" placeholder="Date5"  onblur=" return getProdQty(5,5)"/>
								                     	    </div>
														</th>
													</tr>
												</thead>
												<tbody>
												
												</tbody>
											</table>
										</div>
									</div>
								</div>
						</div>
					</div>
						
								<div align="center" class="form-group">
								<div class="col-sm-5 col-lg-10 controls">

										Select Time Slot <select  data-placeholder="Choose Time Slot"
											tabindex="-1" name="selectTime" id="selectTime" data-rule-required="true">

										
									 	<c:forEach items="${productionTimeSlot}" var="productionTime"
													varStatus="count">
												<option value="${productionTime}"><c:out value="Time Slot ${productionTime}"/></option>
												</c:forEach>
										
										
										</select>
										</div>
									<div
										class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
										<input type="submit" class="btn btn-primary" value="Submit" id="callSubmit">


									</div>
								</div>
						</form>		
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
		function searchItemsByCategory() {
			
			var catId = $("#catId").val();
			document.getElementById("selectedCatId").value =catId;
			var todayTimeStamp = +new Date; // Unix timestamp in milliseconds
			var oneDayTimeStamp = 1000 * 60 * 60 * 24; // Milliseconds in a day
			var diff = todayTimeStamp - oneDayTimeStamp;
			var yesterdayDate = new Date(diff);
			var todaysDate = new Date(todayTimeStamp);

			var yesterdayString =  yesterdayDate.getDate()+ '-' + (yesterdayDate.getMonth() + 1) + '-' +yesterdayDate.getFullYear();
			var tommarowString =  (todaysDate.getDate()+1)+ '-' + (todaysDate.getMonth() + 1) + '-' +todaysDate.getFullYear();

			$("#datepicker1").val(yesterdayString); 
            $("#datepicker5").val(tommarowString); 
 
			//alert(catId);
			
			$('#loader').show();

			$
					.getJSON(
							'${findItemsByCategory}',
							{
								
								catId : catId,
								ajax : 'true'

							},
							function(data) {

								$('#table1 td').remove();
								
								$('#loader').hide();
								if (data == "") {
									alert("No records found !!");

								}

								
								$
										.each(
												data,
												function(key, item) {

													
												 	var index = key + 1;

													var tr = "<tr>";

													var index = "<td>&nbsp;&nbsp;&nbsp;"
															+ index
															+ "</td>";

													var itemName = "<td>&nbsp;&nbsp;&nbsp;"
															+ item.name
															+ "</td>";
															
															
/* 															var curClosing = "<td align=center colspan='2'><input type=text  class=form-control  id= curClos"+ item.id+ " name=curClos"+item.id+" value ="+item.curClosingQty+"></td>"; 
 */
															var curOpening = "<td align=center colspan='1'><input type=text  class=form-control  id= curOpe"+ item.id+ " name=curOpe"+item.id+" value ="+item.curOpeQty+"></td>"; 


													var qty1 = "<td align=center colspan='2'><input type=text  class=form-control  id= qty1"+ item.id+ " name=qty1"+item.id+" value = "+item.qty+ " disabled></td>"; 
													
											
													var qty2 = "<td align=center colspan='2'><input type=text  class=form-control  id= qty2"+ item.id+ " name=qty2"+item.id+" value = "+0+ " disabled></td>";

													var qty3 = "<td align=center colspan='2'><input type=text  class=form-control  id= qty3"+ item.id+ " name=qty3"+item.id+" value = "+0+ " disabled></td>";

													var qty4 = "<td align=center colspan='2'><input type=text  class=form-control  id= qty4"+ item.id+ " name=qty4"+item.id+" value = "+0+ " disabled></td>";

/* 													var qty5 = "<td align=center><input type=text min=0 max=500 class=form-control  id= qty5"+ item.id+ " name=qty5"+item.id+" value = "+0+ " disabled></td>";
 */
													var qty5 = "<td align=center colspan='2'><input type=number  class=form-control  id= qty5"+ item.id+ " name=qty5"+item.id+" value = "+0+ "></td>";

													var trclosed = "</tr>";

													$('#table1 tbody')
															.append(tr);
													$('#table1 tbody')
															.append(index);
													$('#table1 tbody')
															.append(itemName);
													
												
													$('#table1 tbody')
													.append(curOpening);
													
													$('#table1 tbody')
															.append(
																	qty1);
											
													$('#table1 tbody')
															.append(qty2);
												
													$('#table1 tbody')
															.append(
																	qty3);
													 $('#table1 tbody')
															.append(qty4); 
													$('#table1 tbody')
															.append(
																	qty5);
													
													$('#table1 tbody')
															.append(
																	trclosed); 

												})

							});
			
			
			
			
		}

</script>

<script type="text/javascript">
		function getProdQty(token,id) {
			
			var prodDate = document.getElementById("datepicker"+token).value;
			
			var selectedCatId = document.getElementById("selectedCatId").value;

		   //  alert("Your typed in " + prodDate);
		    
		    
		     $.getJSON('${getItemsProdQty}',
							{
								
								prodDate : prodDate,
								catId: selectedCatId,
								
								ajax : 'true'

							},
							function(data) {

                               
								var len = data.length;
								$.each(data.itemList,function(key, item) {
							         document.getElementById('qty'+id+''+item.id).value =0;
							
							})
								
								
                                var prodQtyListLength=data.getProductionItemQtyList.length;
                              if(prodQtyListLength>0)
                                {
								$.each(data.getProductionItemQtyList,function(key, prod) {
									
									$.each(data.itemList,function(key, item) {
										
										if(prod.itemId==item.id)
											{
									         document.getElementById('qty'+id+''+prod.itemId).value = prod.qty;
											}
										
										
									
									})

								})
                                }
                                else
                                	{
                                       $.each(data.itemList,function(key, item) {
									         document.getElementById('qty'+id+''+item.id).value =0;
									
									})
                                	
                                	} 
                                
								
							});
		     
			
		}
</script>
</body>

</html>