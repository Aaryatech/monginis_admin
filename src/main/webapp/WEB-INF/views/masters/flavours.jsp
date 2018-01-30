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
						<i class="fa fa-file-o"></i> Flavour Ledger
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
								<i class="fa fa-bars"></i> Add Flavour
							</h3>
							<div class="box-tool">
								<a href=""></a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							<!-- <div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> <a data-action="close" href="#"><i
									class="fa fa-times"></i></a>
							</div>  -->
						</div>


						<div class="box-content">
							<form action="addFlavour" class="form-horizontal"
								id="validation-form" method="post">



								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Flavour
										Name</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="spf_name" id="spf_name"
											placeholder="Flavour Name" class="form-control"
											data-rule-required="true" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Add On
										Rate</label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="spf_adon_rate" id="spf_adon_rate"
											placeholder="Add on rate" class="form-control" 
											  data-rule-required="true" data-rule-number="true"
											onKeyPress="return isNumberCommaDot(event)" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Type</label>
									<div class="col-sm-9 col-lg-10 controls">
									</label> <label class="radio-inline"> <input type="radio"
											name="sp_type" id="optionsRadios1" value="2" checked/> FC
										</label>
										<label class="radio-inline"> <input type="radio"
											name="sp_type" id="optionsRadios1" value="1"  />
											Chocolate
										
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-4 col-lg-10 col-lg-offset-2">
										<button type="submit" class="btn btn-primary" style="width: 70px">
											 Save
										</button>
										<!--<button type="button" class="btn">Cancel</button>-->
									</div>
								</div>
							</form>

						</div>
					</div>
				</div>
			</div>

			<div class="box">
				<div class="box-title">
					<h3>
						<i class="fa fa-table"></i> Flavours List
					</h3>
					<div class="box-tool">
						<a data-action="collapse" href="#"><i class="fa fa-chevron-up"></i></a>
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
											<th width="17" style="width: 18px">#</th>
									<th width="348" align="left">Name</th>
									<th width="322" align="left">Add on rate</th>
									<th width="290" align="left">Type</th>
									<th width="80" align="left">Action</th>
												</tr>
												</thead>
												</table>
									
									</div>
									<div class="table-wrap">
									
										<table id="table1" class="table table-advance">
											<thead>
												<tr class="bgpink">
											<th width="17" style="width: 18px">#</th>
									<th width="348" align="left">Name</th>
									<th width="322" align="left">Add on rate</th>
									<th width="290" align="left">Type</th>
									<th width="80" align="left">Action</th>
												</tr>
												</thead>
												<tbody>
					<c:forEach items="${flavoursList}" var="flavoursList" varStatus="count">
									<tr>
										<td><c:out value="${count.index+1}" /></td>
										<td align="left"><c:out value="${flavoursList.spfName}" /></td>
										<td align="left"><c:out
												value="${flavoursList.spfAdonRate}" /></td>

										<c:set var="strSpType" value="${flavoursList.spType}"></c:set>
										<c:choose>
											<c:when test="${flavoursList.spType==1}">
												<td align="left"><c:out value="Chocolate" /></td>
											</c:when>
											<c:when test="${flavoursList.spType==2}">
												<td align="left"><c:out value="FC" /></td>
                                           </c:when>
                                           <c:otherwise>
                                           <td align="left"><c:out value=""/></td>
                                           </c:otherwise>
										</c:choose>

										<td align="left"><a
											href="updateFlavour/${flavoursList.spfId}"><span
												class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;

											<a href="deleteFlavour/${flavoursList.spfId}"
											onClick="return confirm('Are you sure want to delete this record');"><span
												class="glyphicon glyphicon-remove"></span></a>
										</td>
									</tr>
								</c:forEach>

							</tbody>

						</table>
					</div>
				</div>
				
						</div>

				<%-- <div class="box-content">
<jsp:include page="/WEB-INF/views/include/tableSearch.jsp"></jsp:include>

					<div class="clearfix"></div>
					<div class="table-responsive" style="border: 0">
						<table width="100%" class="table table-advance" id="table1">
							<thead>
								<tr>
									<th width="17" style="width: 18px">#</th>
									<th width="348" align="left">Name</th>
									<th width="322" align="left">Add on rate</th>
									<th width="290" align="left">Type</th>
									<th width="80" align="left">Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${flavoursList}" var="flavoursList" varStatus="count">
									<tr>
										<td><c:out value="${count.index+1}" /></td>
										<td align="left"><c:out value="${flavoursList.spfName}" /></td>
										<td align="left"><c:out
												value="${flavoursList.spfAdonRate}" /></td>

										<c:set var="strSpType" value="${flavoursList.spType}"></c:set>
										<c:choose>
											<c:when test="${flavoursList.spType==1}">
												<td align="left"><c:out value="Chocolate" /></td>
											</c:when>
											<c:when test="${flavoursList.spType==2}">
												<td align="left"><c:out value="FC" /></td>
                                           </c:when>
                                           <c:otherwise>
                                           <td align="left"><c:out value=""/></td>
                                           </c:otherwise>
										</c:choose>

										<td align="left"><a
											href="updateFlavour/${flavoursList.spfId}"><span
												class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;

											<a href="deleteFlavour/${flavoursList.spfId}"
											onClick="return confirm('Are you sure want to delete this record');"><span
												class="glyphicon glyphicon-remove"></span></a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

				</div>
 --%>			</div>


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

</body>
</html>