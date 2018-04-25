<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body onload="disableFranchise(${isEdit})">

	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<c:url var="findFranchiseeData" value="/findFranchiseeData" />


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
						<i class="fa fa-file-o"></i>Franchisee Supplement
					</h1>

				</div>
			</div>
			<!-- END Page Title -->

			<c:set var="isEdit" value="0">
			</c:set>

			<c:set var="isDelete" value="0">
			</c:set>

			<c:set var="isAdd" value="0">
			</c:set>


			<c:forEach items="${sessionScope.newModuleList}" var="modules">
				<c:forEach items="${modules.subModuleJsonList}" var="subModule">
					<c:choose>
						<c:when
							test="${subModule.subModuleMapping eq 'showAddFranchiseSup'}">

							<c:choose>
								<c:when test="${subModule.editReject=='visible'}">
									<c:set var="isEdit" value="1">
									</c:set>
								</c:when>
								<c:otherwise>
									<c:set var="isEdit" value="0">
									</c:set>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${subModule.deleteRejectApprove=='visible'}">
									<c:set var="isDelete" value="1">
									</c:set>
								</c:when>
								<c:otherwise>
									<c:set var="isDelete" value="0">
									</c:set>
								</c:otherwise>
							</c:choose>


							<c:choose>
								<c:when test="${subModule.addApproveConfig=='visible'}">
									<c:set var="isAdd" value="1">
									</c:set>
								</c:when>
								<c:otherwise>
									<c:set var="isAdd" value="0">
									</c:set>
								</c:otherwise>
							</c:choose>


						</c:when>
					</c:choose>

				</c:forEach>
			</c:forEach>


			<!-- BEGIN Main Content -->
			<div class="row">
				<div class="col-md-12">
					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-bars"></i> Add Franchisee Supplement
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/"></a> <a
									data-action="collapse" href="#"><i class="fa fa-chevron-up"></i></a>
							</div>

						</div>




						<div class="box-content">
							<form action="${pageContext.request.contextPath}/addFrSupProcess"
								class="form-horizontal" method="post" id="validation-form">

								<input type="hidden" name="id" id="id" value="${frSup.id}" />

								<div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Franchisee</label>
									<div class="col-sm-9 col-lg-3 controls">
										<select name="fr_id" id="fr_id" class="form-control"
											placeholder="Select Franchise" data-rule-required="true">
											<option value="">Select Franchise</option>
											<c:forEach items="${franchiseeList}" var="franchiseeList">
												<c:choose>
													<c:when test="${franchiseeList.frId==frSup.frId}">
														<option value="${franchiseeList.frId}" selected><c:out value="${franchiseeList.frName}"></c:out></option>
													</c:when>
													<c:otherwise>
														<option value="${franchiseeList.frId}"><c:out value="${franchiseeList.frName}"></c:out></option>
													</c:otherwise>
												</c:choose>
											</c:forEach>

										</select>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">PAN No.</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="pan_no" id="pan_no"
											placeholder="PAN No" class="form-control"
											data-rule-required="true" value="${frSup.frPanNo}" />
									</div>
								</div>
								<div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Country</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="fr_country" id="fr_country"
											placeholder="Country" class="form-control"
											data-rule-required="true" value="India" />
									</div>

								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">State</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="fr_state" id="fr_state"
											placeholder="State" class="form-control"
											data-rule-required="true" value="${state}" />
									</div>

								</div>
								<div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Owner
										Password</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="pass1" id="pass1"
											placeholder="User1 Password" class="form-control"
											data-rule-required="true" value="${frSup.pass1}" readonly />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Captain
										Password</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="pass2" id="pass2"
											placeholder="User2 Password" class="form-control"
											data-rule-required="true" value="${frSup.pass2}" />
									</div>
								</div>
								<div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">CSP
										Password</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="pass3" id="pass3"
											placeholder="User3 Password" class="form-control"
											data-rule-required="true" value="${frSup.pass3}" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Pest
										Control Date</label>
									<div class="col-sm-9 col-lg-3 controls">

										<input class="form-control date-picker" id="pest_control_date"
											size="16" type="text" name="pest_control_date" required
											placeholder="Pest Control Date"
											value="${frSup.pestControlDate}"
											onblur="return onPestControlDateChange()" />
									</div>
								</div>
								<div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Frequency</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="frequency" id="frequency"
											placeholder="Frequency" class="form-control"
											data-rule-required="true" value="${frSup.frequency}" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">No. In
										Route</label>
									<div class="col-sm-9 col-lg-3 controls">
										<input type="text" name="no_in_route" id="no_in_route"
											placeholder="No. In Route" class="form-control"
											data-rule-required="true" value="${frSup.noInRoute}" />
									</div>
								</div>
								<%-- <div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Remainder Date</label>
									<div class="col-sm-9 col-lg-3 controls">
										
										<input class="form-control date-picker" id="remainder_date" size="16"
													type="text" name="remainder_date"
													required placeholder="Remainder Date" value="${frSup.remainderDate}"/>
									</div>
							  </div> --%>
								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
									<c:choose>
										<c:when test="${isAdd==1}">
											<input type="submit" class="btn btn-primary" value="Submit">
										</c:when>
										<c:otherwise>
											<input type="submit" disabled="disabled"
												class="btn btn-primary" value="Submit">
										</c:otherwise>
										</c:choose>
									</div>
								</div>
							</form>
							<div class="box">
								<div class="box-title">
									<h3>
										<i class="fa fa-table"></i>Franchisee List
									</h3>
									<div class="box-tool">
										<a data-action="collapse" href="#"><i
											class="fa fa-chevron-up"></i></a>
										<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
									</div>
								</div>
								<div class="box-content">
									<jsp:include page="/WEB-INF/views/include/tableSearch.jsp"></jsp:include>

									<div class="clearfix"></div>


									<div id="table-scroll" class="table-scroll">

										<div id="faux-table" class="faux-table" aria="hidden">
											<table id="table2" class="main-table">
												<thead>
													<tr class="bgpink">
														<th width="45" style="width: 18px">Sr.No.</th>
														<th width="100" align="left">Franchisee</th>
														<th width="100" align="left">PAN No.</th>
														<th width="100" align="left">State</th>
														<th width="100" align="left">Country</th>
														<th width="290" align="left">PestControl Date</th>
														<th width="60" align="right">Frequency</th>
														<th width="190" align="left">Remainder Date</th>
														<th width="70" align="left">Pass1</th>
														<th width="70" align="left">Pass2</th>
														<th width="70" align="left">Pass3</th>

														<th width="81" align="left">Action</th>
													</tr>
												</thead>
											</table>

										</div>
										<div class="table-wrap">

											<table id="table1" class="table table-advance">
												<thead>
													<tr class="bgpink">
														<th width="45" style="width: 18px">Sr.No.</th>
														<th width="100" align="left">Franchisee</th>
														<th width="100" align="left">PAN No.</th>
														<th width="100" align="left">State</th>
														<th width="100" align="left">Country</th>
														<th width="290" align="left">PestControl Date</th>
														<th width="60" align="right">Frequency</th>
														<th width="190" align="left">Remainder Date</th>
														<th width="70" align="left">Pass1</th>
														<th width="70" align="left">Pass2</th>
														<th width="70" align="left">Pass3</th>

														<th width="81" align="left">Action</th>
													</tr>
												</thead>
												<tbody>

													<c:forEach items="${frSupList}" var="frSupList"
														varStatus="count">
														<tr>

															<td><c:out value="${count.index+1}" /></td>
															<td align="left"><c:out value="${frSupList.frName}"></c:out></td>
															<td align="left"><c:out value="${frSupList.frPanNo}"></c:out></td>
															<td align="left"><c:out value="${frSupList.frState}"></c:out></td>
															<td align="left"><c:out
																	value="${frSupList.frCountry}"></c:out></td>
															<td align="left"><c:out
																	value="${frSupList.pestControlDate}"></c:out></td>
															<td align="center"><c:out
																	value="${frSupList.frequency}"></c:out></td>
															<td align="left"><c:out
																	value="${frSupList.remainderDate}"></c:out></td>
															<td align="left"><c:out value="${frSupList.pass1}"></c:out></td>
															<td align="left"><c:out value="${frSupList.pass2}"></c:out></td>
															<td align="left"><c:out value="${frSupList.pass3}"></c:out></td>

															<c:choose>
																<c:when test="${isEdit==1}">
																	<td align="left"><a
																		href="${pageContext.request.contextPath}/updateFranchiseSup/${frSupList.id}"><span
																			class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;
																	</td>
																</c:when>
																<c:otherwise>
																	<td align="left"><a
																		href="${pageContext.request.contextPath}/updateFranchiseSup/${frSupList.id}"
																		class="disableClick"><span
																			class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;
																	</td>
																</c:otherwise>
															</c:choose>


														</tr>

													</c:forEach>


												</tbody>

											</table>
										</div>
									</div>

								</div>


								<%-- <div class="box-content">

										<div class="clearfix"></div>
										<div class="table-responsive" style="border: 0">
											<table width="100%" class="table table-advance" id="table1">
												<thead>
													<tr>
														<th width="45" style="width: 18px">Sr.No.</th>
														<th width="100" align="left">Franchisee</th>
														<th width="100" align="left">PAN No.</th>
														<th width="100" align="left">State</th>
														<th width="100" align="left">Country</th>
														<th width="290" align="left">PestControl Date</th>
														<th width="60" align="right">Frequency</th>
														<th width="190" align="left">Remainder Date</th>
													<th width="70" align="left">Pass1</th>
													<th width="70" align="left">Pass2</th>
													<th width="70" align="left">Pass3</th>
												
														<th width="81" align="left">Action</th>
													</tr>
												</thead>
												<tbody>
												 <c:forEach items="${frSupList}" var="frSupList" varStatus="count">
														<tr>
														
															<td><c:out value="${count.index+1}"/></td>
															<td align="left"><c:out
																	value="${frSupList.frName}"></c:out></td>
															<td align="left"><c:out
																	value="${frSupList.frPanNo}"></c:out></td>	
												        	<td align="left"><c:out
																	value="${frSupList.frState}"></c:out></td>		
															<td align="left"><c:out
																	value="${frSupList.frCountry}"></c:out></td>		
															<td align="left"><c:out
																	value="${frSupList.pestControlDate}"></c:out></td>	
															<td align="center"><c:out
																	value="${frSupList.frequency}"></c:out></td>		
															<td align="left"><c:out
																	value="${frSupList.remainderDate}"></c:out></td>												
															<td align="left"><c:out
																	value="${frSupList.pass1}"></c:out></td>
															<td align="left"><c:out
																	value="${frSupList.pass2}"></c:out></td>
															<td align="left"><c:out
																	value="${frSupList.pass3}"></c:out></td>
															
																	
															<td align="left"><a href="${pageContext.request.contextPath}/updateFranchiseSup/${frSupList.id}"><span
														class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;
                                                        </td>		
																												
       													
														</tr>

													</c:forEach>  
              										</tbody>
											</table>
										</div>
									</div>
 --%>
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


</body>
<script>
function disableFranchise(isEdit) {
	if(isEdit==1)
		{
		$("#fr_id option:not(:selected)").prop("disabled", true);
		}

}
</script>
<script type="text/javascript">
$(document).ready(function() { 
	$('#fr_id').change(
			function() {
				$.getJSON('${findFranchiseeData}', {
					fr_id : $(this).val(),
					ajax : 'true'
				}, function(data) {
					var len = data.length;
					document.getElementById("pass1").value=data.frPassword;
				});
			});
});
</script>
<!-- 
<script type="text/javascript">
$(document).ready(function () {
    $('#pest_control_date').datepicker();
    $('#remainder_date').datepicker();
});


function onPestControlDateChange() {
	//$('#remainder_date').datepicker({ dateFormat: 'dd-mm-yy' }).val();
	
    var tt = document.getElementById('pest_control_date').value;
    var numberOfDaysToAdd =document.getElementById("frequency").value;

    var date = new Date(tt);
    var newdate = new Date(date);

    newdate.setDate(newdate.getDate());
    
    var dd = newdate.getDate()+numberOfDaysToAdd;
    var mm = newdate.getMonth() + 1;
    var y = newdate.getFullYear();

    var someFormattedDate = mm + '-' + dd + '-' + y;
    document.getElementById('remainder_date').value = someFormattedDate;
}
</script> -->
</html>
