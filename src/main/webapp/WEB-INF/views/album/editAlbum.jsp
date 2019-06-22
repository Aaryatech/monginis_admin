<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>

<!---------------Script For Translate Special Instructions------->
<!-- <script type="text/javascript">
	// Load the Google Transliterate API
	google.load("elements", "1", {
		packages : "transliteration"
	});

	function onLoad() {
		var options = {
			sourceLanguage : google.elements.transliteration.LanguageCode.ENGLISH,
			destinationLanguage : [ google.elements.transliteration.LanguageCode.MARATHI ],
			shortcutKey : 'ctrl+g',
			transliterationEnabled : true
		};

		// Create an instance on TransliterationControl with the required
		// options.
		var control = new google.elements.transliteration.TransliterationControl(
				options);

		// Enable transliteration in the textbox with id
		// 'transliterateTextarea'.
		control.makeTransliteratable([ 'transliterateTextarea' ]);
		control.makeTransliteratable([ 'transliterateTextarea1' ]);
	}
	google.setOnLoadCallback(onLoad);
</script> -->
<!--------------------------------END------------------------------------>


<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body>
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<%@ page import="java.util.Calendar"%>
	<%@ page import="java.util.Date"%>
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
						<i class="fa fa-file-o"></i> Album
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
								<i class="fa fa-bars"></i> Add Album
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/showAlbums">Back
									to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							<!-- <div class="box-tool">
								<a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a> <a data-action="close" href="#"><i
									class="fa fa-times"></i></a>
							</div> -->
						</div>
						<div class="box-content">
							<form action="updateAlbumProcess" class="form-horizontal"
								id="validation-form" method="post" enctype="multipart/form-data">





								<input type="hidden" name="mode_add" id="mode_add"
									value="add_att">
								<%
									Calendar cal = Calendar.getInstance();
									Date date = cal.getTime();
									pageContext.setAttribute("frdate", date);
								%>

								<div class="form-group">
									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Special
											Cake</label>
										<div class="col-sm-9 col-lg-3 controls">
											<select name="spId" id="spId" class="form-control chosen"
												placeholder="Special Cake  " data-rule-required="true">
												<option value="">Select Special Cake</option>
												<c:forEach items="${spList}" var="spList" varStatus="count">
													<c:choose>
														<c:when test="${spList.spId==album.spId}">
															<option value="${spList.spId}" selected><c:out
																	value="${spList.spName}" /></option>
														</c:when>
														<c:otherwise>
															<option value="${spList.spId}"><c:out
																	value="${spList.spName}" /></option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</div>

									<%-- <div class="form-group row">
										<label class="col-form-label col-lg-2" for="select2">Select
											Location :</label>
										<div class="col-lg-10">
											<select name="locId" data-placeholder="Select Location"
												id="locId"
												class="form-control form-control-select2 select2-hidden-accessible"
												multiple="multiple" tabindex="-1" aria-hidden="true">
												<option></option>


												<c:forEach items="${locationList}" var="location">
													<c:set var="flag" value="0"></c:set>
													<c:forEach items="${locIdList}" var="selFr"
														varStatus="count2">
														<c:choose>
															<c:when test="${selFr==location.locId}">
																<option selected value="${location.locId}"><c:out
																		value="${location.locName}" /></option>
																<c:set var="flag" value="1"></c:set>
															</c:when>
															<c:otherwise>

															</c:otherwise>
														</c:choose>
													</c:forEach>
													<c:choose>
														<c:when test="${flag==0}">
															<option value="${location.locId}"><c:out
																	value="${location.locName}" /></option>
														</c:when>
													</c:choose>
												</c:forEach>
												<c:forEach items="${locationList}" var="location">
													<c:choose>
														<c:when test="${location.locId == editHoliday.companyId}">
															<option value="${location.locId}" selected="selected">${location.locName}</option>
														</c:when>
														<c:otherwise>
															<option value="${location.locId}">${location.locName}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select> <span class="validation-invalid-label" id="error_locId"
												style="display: none;">This field is required.</span>
										</div>
									</div>
 --%>
									<%-- <div class="col2">
										<label class="col-sm-3 col-lg-2 control-label"> Select
											Flavour </label>
										<div class="col-sm-9 col-lg-3 controls">
											<select name="flavourId" id="flavourId"
												class="form-control chosen" placeholder="Select Flavour"
												data-rule-required="true" multiple="multiple">







												<c:forEach items="${flavoursList}" var="flavour">
													<c:set var="flag" value="0"></c:set>
													<c:forEach items="${fIdList}" var="selFr"
														varStatus="count2">
														<c:choose>
															<c:when test="${selFr==flavour.spfId}">
																<option selected value="${flavour.spfId}"><c:out
																		value="${flavour.spfName}" /></option>
																<c:set var="flag" value="1"></c:set>
															</c:when>
															<c:otherwise>

															</c:otherwise>
														</c:choose>
													</c:forEach>
													<c:choose>
														<c:when test="${flag==0}">
															<option value="${flavour.spfId}"><c:out
																	value="${flavour.spfName}" /></option>
														</c:when>
													</c:choose>
												</c:forEach>

 
											</select>
										</div>
									</div> --%>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Album
										Code </label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="albumCode" id="albumCode"
											placeholder="Album Code" class="form-control"
											value="${album.albumCode}" data-rule-required="true" />
									</div>
								</div>



								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Album
										Name </label>
									<div class="col-sm-9 col-lg-10 controls">
										<input type="text" name="albumName" id="
											albumName"
											value="${album.albumName}" placeholder="Album Name"
											class="form-control" data-rule-required="true" />
									</div>
								</div>
								<input type="hidden" name="albumId" value="${album.albumId}" />
<input type="hidden" name="prevPh1" value="${album.photo1}">
<input type="hidden" name="prevPh2" value="${album.photo2}">
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Photo1</label>
									<div class="col-sm-6 col-lg-3 controls">
										<div class="fileupload fileupload-new"
											data-provides="fileupload">
											<div class="fileupload-new img-thumbnail"
												style="width: 200px; height: 150px;">
												<img src="${url}${album.photo1}"
													onerror="this.src='${pageContext.request.contextPath}/resources/img/No_Image_Available.jpg';" />
											</div>
											<div
												class="fileupload-preview fileupload-exists img-thumbnail"
												style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
											<div>
												<span class="btn btn-default btn-file"><span
													class="fileupload-new">Select image</span> <span
													class="fileupload-exists">Change</span> <input type="file"
													class="file-input" name="photo1" id="photo1"
													value="${album.photo1}" /></span> <a href="#"
													class="btn btn-default fileupload-exists"
													data-dismiss="fileupload">Remove</a>
											</div>
										</div>

									</div>

									<label class="col-sm-3 col-lg-2 control-label">Photo2</label>
									<div class="col-sm-6 col-lg-3 controls">
										<div class="fileupload fileupload-new"
											data-provides="fileupload">
											<div class="fileupload-new img-thumbnail"
												style="width: 200px; height: 150px;">
												<img src="${url}${album.photo2}"
													onerror="this.src='${pageContext.request.contextPath}/resources/img/No_Image_Available.jpg';" />
											</div>
											<div
												class="fileupload-preview fileupload-exists img-thumbnail"
												style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
											<div>
												<span class="btn btn-default btn-file"><span
													class="fileupload-new">Select image</span> <span
													class="fileupload-exists">Change</span> <input type="file"
													class="file-input" name="photo2" id="photo2"
													value="${album.photo2}" /></span> <a href="#"
													class="btn btn-default fileupload-exists"
													data-dismiss="fileupload">Remove</a>
											</div>
										</div>

									</div>
								</div>

								 	<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Minimum
										Weight</label>
									<div class="col-sm-6 col-lg-3 controls">
										<input type="text" name="minWt" id="minWt"
											placeholder="Minimum Weight" class="form-control"
											data-rule-required="true" value="${album.minWt}" />
									</div>


									<label class="col-sm-3 col-lg-2 control-label">Maximum
										Weight</label>
									<div class="col-sm-6 col-lg-3 controls">
										<input type="text" name="maxWt" id="maxWt"
											placeholder="Maximum Weight" class="form-control"
											data-rule-required="true" value="${album.maxWt}" />
									</div>
								</div>
 


								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Description
										Details</label>
									<div class="col-sm-9 col-lg-10 controls">
										<textarea class="form-control" rows="3" name="desc"
											id="transliterateTextarea1" data-rule-required="true">${album.albumDesc}</textarea>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Status</label>
									<div class="col-sm-9 col-lg-10 controls">

										<select class="form-control input-sm" name="is_active"
											id="is_active">

											<c:choose>
												<c:when test="${isActive.equals('1')}">
													<option selected value="1">Active</option>
													<option value="0">In-Active</option>


												</c:when>
												<c:when test="${isActive.equals('0')}">
													<option selected value="0">In-Active</option>
													<option value="1">Active</option>


												</c:when>
											</c:choose>


										</select>

									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
										<button type="submit" class="btn btn-primary">
											<i class="fa fa-check"></i> Save
										</button>
										<!--<button type="button" class="btn">Cancel</button>-->
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
</body>
</html>
