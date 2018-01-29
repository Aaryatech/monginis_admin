<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body>
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<c:url var="findItemsByCategory" value="/getItemsByCatIdForFinGood"></c:url>

	<c:url var="finishedGoodDayEnd" value="/finishedGoodDayEnd"></c:url>
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
						<i class="fa fa-file-o"></i>Finished Good Stock
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
								<i class="fa fa-bars"></i> Good Stock
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
							<form class="form-horizontal" id="validation-form">

								<%-- <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Category</label>

									<div class="col-sm-9 col-lg-10 controls">
										<select class="form-control chosen" name="catId" id="catId">


										<option value="-1">All</option>

											<c:forEach items="${catList}" var="catList">

												<option value="${catList.catId}">${catList.catName} </option>

											</c:forEach>


										</select>
									</div>

								</div>

								 --%>

								<!-- 
								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">

										<input type="button" class="btn btn-info" name="submit"
											value="submit" onclick="searchItemsByCategory()" />
									</div>
								</div> -->
								<input type="hidden" id="selectedCatId" name="selectedCatId" />

							</form>
							<form action="insertOpeningStock" method="post"
								id="validation-form">

								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i> Finished Good
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
														<th width="30" align="left">Sr No</th>
														<th width="120" align="left">Item Name</th>
														<th width="50">T1</th>
														<th width="50">T2</th>
														<th width="50">T3</th>

													</tr>
												</thead>
												<tbody>
											
													<c:forEach items="${itemsList}" var="item" varStatus="count">
														<tr>
															<td><c:out value="${count.index+1}"></c:out></td>
															<td><c:out value="${item.itemName}"></c:out></td>
															<td><input type=text  class=form-control  id="qty1${item.itemId}" value="${item.opT1}" name="qty1${item.itemId}" ></td>
															<td><input type=text  class=form-control  id="qty2${item.itemId}" value="${item.opT2}" name="qty2${item.itemId}"  ></td>
															<td><input type=text  class=form-control  id="qty3${item.itemId}" value="${item.opT3}" name="qty3${item.itemId}"  ></td>
														</tr>
													</c:forEach>

												</tbody>
											</table>
										</div>
									</div>

								</div>

								<div align="center" class="form-group">

									<div
										class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
										<input type="submit" class="btn btn-primary" value="Submit">

										<!-- <input type="button" class="btn btn-danger"
											value="Day End Process" id="dayEndButton"> -->



									</div>

								</div>

							</form>
						</div>


					</div>
					<!-- </form> -->
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
			
			/* var catId = $("#catId").val();
			document.getElementById("selectedCatId").value =catId;
			 */
			var option= $("#selectStock").val();
			
			$('#loader').show();

			$
					.getJSON(
							'${findItemsByCategory}',
							{
								
							/* 	catId : catId,
								option : option, */
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
															+ item.itemName
															+ "</td>";
															
								
											
												    	var qty1 = "<td align=center ><input type=text  class=form-control  id= qty1"+ item.itemId+ " value=0 name=qty1"+item.itemId+"  ></td>"; 
														
														var qty2 = "<td align=center ><input type=text  class=form-control  id= qty2"+ item.itemId+ " value=0 name=qty2"+item.itemId+"  ></td>";

														var qty3 = "<td align=center ><input type=text  class=form-control  id= qty3"+ item.itemId+ " value=0 name=qty3"+item.itemId+"  ></td>";
												    	
								 				var trclosed = "</tr>";

													$('#table1 tbody')
															.append(tr);
													$('#table1 tbody')
															.append(index);
													$('#table1 tbody')
															.append(itemName);
													$('#table1 tbody')
															.append(
																	qty1);
											
													$('#table1 tbody')
															.append(qty2);
												
													$('#table1 tbody')
															.append(
																	qty3);
													
													
													$('#table1 tbody')
															.append(
																	trclosed); 

												})

							});
			
			
		}

</script>



	<script type="text/javascript">

$('#dayEndButton').click(function(){
	
	var option= $("#selectStock").val();
if(option==1){
	alert("Day End ");
	$('#loader').show();

	$.getJSON('${finishedGoodDayEnd}',
			{
				ajax : 'true',
				
				
	
})
	$('#loader').hide();

));

}else{alert("Please Select Current Stock")}});

</script>

	<script>
		function showDiv(elem) {
			if (elem.value == 1) {
				document.getElementById('select_month_year').style = "display:none";
				document.getElementById('select_date').style = "display:none";

			} else if (elem.value == 2) {
				document.getElementById('select_month_year').style.display = "block";
				document.getElementById('select_date').style = "display:none";
			} else if (elem.value == 3) {
				document.getElementById('select_date').style.display = "block";
				document.getElementById('select_month_year').style = "display:none";
				
			}
			
		}
	</script>
</body>

</html>