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
						<i class="fa fa-file-o"></i> Messages
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
								<i class="fa fa-table"></i> Messages List
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
																						<th width="17" style="width: 18px">#</th>
											<th width="221" align="left">Date</th>
											<th width="301" align="left">Image</th>
											<th width="185" align="left">Header</th>
											<th width="291" align="left">Message</th>
											<th width="190" align="center">Action</th>
												</tr>
												</thead>
												</table>
									
									</div>
									<div class="table-wrap">
									
										<table id="table1" class="table table-advance">
											<thead>
												<tr class="bgpink">
																							<th width="17" style="width: 18px">#</th>
											<th width="221" align="left">Date</th>
											<th width="301" align="left">Image</th>
											<th width="185" align="left">Header</th>
											<th width="291" align="left">Message</th>
											<th width="190" align="center">Action</th>
												</tr>
												</thead>
												<tbody>
	 <c:forEach items="${message}" var="message" varStatus="count">
										<tr>
											<td><c:out value="${count.index+1}"/></td>
											<td align="left"><c:out value="${message.msgFrdt} ${message.msgTodt}" /></td>
											<%-- <td align="left"><c:out value="${message.msgImage}" /></td> --%>
											<td align="left"><img src="${url}${message.msgImage}" width="120" height="100"  onerror="this.src='resources/img/No_Image_Available.jpg';" /></td>
											
											
											<td align="left"><c:out value="${message.msgHeader}"/></td>
											<td align="left"><c:out value="${message.msgDetails}" /></td>
											<td align="center"><a
												href="updateMessage/${message.msgId}"><span
													class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;

												<a
												href="deleteMessage/${message.msgId}"
												onClick="return confirm('Are you sure want to delete this record');"><span
													class="glyphicon glyphicon-remove"></span></a></td>
										</tr>

</c:forEach>


							</tbody>

						</table>
					</div>
				</div>
				
						</div>
<%-- 


						<div class="box-content">
                       <jsp:include page="/WEB-INF/views/include/tableSearch.jsp"></jsp:include>
 
							<div class="clearfix"></div>
							<div class="table-responsive" style="border: 0">
								<table width="100%" class="table table-advance" id="table1">
									<thead>
										<tr>
											<th width="17" style="width: 18px">#</th>
											<th width="121" align="left">Date</th>
											<th width="371" align="left">Image</th>
											<th width="185" align="left">Header</th>
											<th width="291" align="left">Message</th>
											<th width="68" align="left">Action</th>
										</tr>
									</thead>
									<tbody>
					
                             <c:forEach items="${message}" var="message" varStatus="count">
										<tr>
											<td><c:out value="${count.index+1}"/></td>
											<td align="left"><c:out value="${message.msgFrdt} ${message.msgTodt}" /></td>
											<td align="left"><c:out value="${message.msgImage}" /></td>
											<td align="left"><img src="${url}${message.msgImage}" width="120" height="100"  onerror="this.src='resources/img/No_Image_Available.jpg';" /></td>
											
											
											<td align="left"><c:out value="${message.msgHeader}"/></td>
											<td align="left"><c:out value="${message.msgDetails}" /></td>
											<td align="left"><a
												href="updateMessage/${message.msgId}"><span
													class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;

												<a
												href="deleteMessage/${message.msgId}"
												onClick="return confirm('Are you sure want to delete this record');"><span
													class="glyphicon glyphicon-remove"></span></a></td>
										</tr>

</c:forEach>

									</tbody>
								</table>
							</div>
						</div> --%>
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
	<script src="${pageContext.request.contextPath}/resources/assets/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/jquery-cookie/jquery.cookie.js"></script>

	<!--page specific plugin scripts-->
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.resize.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.pie.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.stack.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.crosshair.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.tooltip.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/sparkline/jquery.sparkline.min.js"></script>


	<!--page specific plugin scripts-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>





	<!--flaty scripts-->
	<script src="${pageContext.request.contextPath}/resources/js/flaty.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/flaty-demo-codes.js"></script>
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