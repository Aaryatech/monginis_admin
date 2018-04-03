<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
 	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>


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
						<i class="fa fa-file-o"></i>Cake Allocation
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
								<i class="fa fa-bars"></i>Search Stationwise Allocated Cake
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showCkAllocToStation">Stationwise Cake Allocation</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
						</div>
							<form action="${pageContext.request.contextPath}/searchStSpCkAlloc" method="post" class="form-horizontal" id="validation-form" method="post">

						<div class="box-content">
							
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Station</label>
									<div class="col-sm-6 col-lg-8 controls">
                                    <select name="st_id" id="st_id" class="form-control" placeholder="Station"data-rule-required="true" >
								   <option value="-1">All</option>
								  
								   <c:forEach items="${spStationList}" var="spStationList">
								       <c:choose>
								     
								        <c:when test="${spStationList.stId==selStId}">
									     <option value="${spStationList.stId}" selected><c:out value="${spStationList.stName}"></c:out></option>
								       </c:when>
									 <c:otherwise>
									     <option value="${spStationList.stId}"><c:out value="${spStationList.stName}"></c:out></option>
									 </c:otherwise>
									  </c:choose>
									</c:forEach> 
								   </select>									
							    </div>
							   </div>
								
								<div class=" box-content">
						        <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">From Date:</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="from_date" size="16"
											 type="text" name="from_date" value="${fromDate}" required />
										</div>
										
									<label class="col-sm-3 col-lg-2 control-label">To Date:</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="to_date" size="16"
											 type="text" name="to_date" value="${toDate}"  required />
										</div>
								   </div>
						  <div >
							 <input type="submit" class="btn btn-info" value="Search" name="add" id="add">
						</div>
								</div>
						
						
					 
					
				           </div>
				      </form>
				           
				         
			<div class="row">
				<div class="col-md-12">

					<div class="box" id="todayslist">
						<div class="box-title">
							<h3>
								<i class="fa fa-table"></i>Stationwise Allocated Cake List
							</h3>
							<div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						
						<div class=" box-content">
					<div class="row">
					
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 100%" id="table_grid1">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Req.Date</th>
										<th>Station</th>
										<th>Special Cake</th>
										<th>BOM</th>
										
									</tr>
								</thead>
								
								<tbody>
							  <c:forEach items="${getAllocStationCkList}" var="getAllocStationCkList"
													varStatus="count">

													<tr>
														<td><c:out value="${count.index+1}" /></td>

																<td align="left"><c:out
																value="${getAllocStationCkList.reqDate}" /></td>
														
															<td align="left"><c:out	
																value="${getAllocStationCkList.stName}" />
																</td>
																
																
																<td align="left"><c:out	
																value="${getAllocStationCkList.spName}" />
																</td>
																<c:if test="${getAllocStationCkList.isBom==0}">
																<td align="left"><a
													href="${pageContext.request.contextPath}/getSpDetailForBom/${getAllocStationCkList.spId}/${getAllocStationCkList.spCkAllocDId}"><input type="button" value="Bom" name="BOM" id="${getAllocStationCkList.spId}"/></a>
																</td>
						                                    </c:if>
						                                    <c:if test="${getAllocStationCkList.isBom==1}">
						                                    	<td align="left"><input type="button" value="Bom" name="BOM" id="bomCompleted" onclick="return bomStatus()"/>
																</td>															
						                                    </c:if>
																</tr>
												</c:forEach> 
										
									
								</tbody>
							</table>
						</div>
					</div>

		</div>
			</div>			
						
					
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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>

			<script type="text/javascript"
				src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
			<script type="text/javascript"
				src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>
				
</body>
<script type="text/javascript">
function bomStatus()
{

	alert("Bill Of Material Already Completed")
}
</script>

</html>

