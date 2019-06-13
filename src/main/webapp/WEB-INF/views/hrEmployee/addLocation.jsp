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
						<i class="fa fa-file-o"></i>Location
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
								<i class="fa fa-bars"></i> Add Location
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showAddLoc"></a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>

						<div class="box-content">
							<form action="${pageContext.request.contextPath}/addLoc" class="form-horizontal"
								method="post" id="validation-form">

                      <input type="hidden" name="loc_id" id="loc_id" value="${loc.locId}"/>
                      
                       <div class="form-group">
                       			<div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Company</label>
									<div class="col-sm-3 col-lg-4 controls">
									<select name="comp_id" id="comp_id" class="form-control"  data-rule-required="true" required>
											<option value="">Select Company</option>
										    	<c:forEach items="${compList}" var="companyList">
										    	<c:choose>
													<c:when test="${companyList.companyId==loc.compId}">
												          <option value="${companyList.companyId}" selected><c:out value="${companyList.companyName}"></c:out></option>
													</c:when>
													<c:otherwise>
										            	  <option value="${companyList.companyId}"><c:out value="${companyList.companyName}"></c:out></option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
								</select>	
									</div>
									</div>
									
									<div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Location
										Name</label>
									<div class="col-sm-3 col-lg-4 controls">
										<input type="text" name="loc_name" id="loc_name"
											placeholder="Location Name" class="form-control"
											data-rule-required="true" value="${loc.locName}"/>
									</div>
								</div>
								
								</div> 
								
                      <div class="form-group">
								
								
								<div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Short Name
										</label>
									<div class="col-sm-3 col-lg-4 controls">
										<input type="text" name="loc_short_name" id="loc_short_name"
											placeholder="Short Name" class="form-control"
											data-rule-required="true" value="${loc.locNameShort}"/>
									</div>
								</div>
								
								<div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Address</label>
									<div class="col-sm-3 col-lg-4 controls">
										<input type="text" name="loc_add" id="loc_add"
											placeholder="Address" class="form-control"
											data-rule-required="true" value="${loc.locShortAddress}"/>
									</div>
									</div>
							</div>
							
							 <div class="form-group">
								
									
									<div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Description</label>
									<div class="col-sm-3 col-lg-4 controls">
										<input type="text" name="loc_desc" id="loc_desc"
											placeholder="Description" class="form-control"
											 value="${loc.locRemarks}"/>
									</div>
									
								</div>
								
								<div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">HR Contact Person</label>
									<div class="col-sm-3 col-lg-4 controls">
										<input type="text" name="loc_contact_person" id="loc_contact_person"
											placeholder="HR Contact Name" class="form-control"
											data-rule-required="true" value="${loc.locHrContactPerson}"/>
									</div>
									</div>
								
								
								</div>
						
								
								 <div class="form-group">
								
									<div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Contact Number</label>
									<div class="col-sm-3 col-lg-4 controls">
										<input type="text" name="loc_contact_no" id="loc_contact_no"
											placeholder="Conttact Number" class="form-control" pattern="^\d{10}$"
											data-rule-required="true" value="${loc.locHrContactNumber}"/>
									</div>
								</div>
								
									<div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Email Address</label>
									<div class="col-sm-3 col-lg-4 controls">
										<input type="text" name="loc_email" id="loc_email"
											placeholder="Email Address" class="form-control"
											data-rule-required="true" value="${loc.locHrContactEmail}"/>
									</div>
								</div>
								
								</div>
								
<br>
								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Submit">
<!-- 										<button type="button" class="btn">Cancel</button>
 -->									</div>
								</div>
							
							</form>
							<br>
								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i>Location List
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
														<th width="100" align="left">Location</th>
														<th width="100" align="left">Short Name</th>
														<th width="100" align="left">Address</th>
														<th width="100" align="left">Company</th>
														<th width="100" align="left">Contact Person</th>
														<th width="100" align="left">Contact Number</th>
														<th width="100" align="left">Email</th>
														
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
														<th width="100" align="left">Location</th>
														<th width="100" align="left">Short Name</th>
														<th width="100" align="left">Address</th>
														<th width="100" align="left">Company</th>
														<th width="100" align="left">Contact Person</th>
														<th width="100" align="left">Contact Number</th>
														<th width="100" align="left">Email</th>
														
														<th width="81" align="left">Action</th>
												</tr>
												</thead> 

												<tbody>
													  <c:forEach items="${locList}" var="locList" varStatus="count">
														<tr>
														
															<td><c:out value="${count.index+1}"/></td>
															<td align="left"><c:out
																	value="${locList.locName}"></c:out></td>
																	<td  align="left"><c:out
																	value="${locList.locNameShort}"></c:out></td>
																	<td  align="left"><c:out
																	value="${locList.locShortAddress}"></c:out></td>
																		<td  align="left"><c:out
																	value="${locList.companyName}"></c:out></td>
																	
																	<td  align="left"><c:out
																	value="${locList.locHrContactPerson}"></c:out></td>
																	<td  align="left"><c:out
																	value="${locList.locHrContactNumber}"></c:out></td>
																	<td " align="left"><c:out
																	value="${locList.locHrContactEmail}"></c:out></td>
															
															
															<td  align="left"><a href="${pageContext.request.contextPath}/updateLoc/${locList.locId}"><span
														class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;
                                                        
                                                        <a href="${pageContext.request.contextPath}/deleteLoc/${locList.locId}"
													    onClick="return confirm('Are you sure want to delete this record');"><span
														class="glyphicon glyphicon-remove"></span></a></td>	
														 
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
</html>
