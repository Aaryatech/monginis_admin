<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<style>
@media only screen and (min-width: 1200px) {
	.franchisee_label, .menu_label {
		width: 22%;
	}
	.franchisee_select, .menu_select {
		width: 76%;
	}
	.date_label {
		width: 40%;
	}
	.date_select {
		width: 50%;
		padding-right: 0px;
	}
}
</style> 
 <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
 <jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
<body>



 


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
					<i class="fa fa-file-o"></i>Access Right
				</h1>
				<!-- <h4>Bill for franchises</h4> -->
			</div>
		</div>
		<!-- END Page Title -->


		<!-- BEGIN Main Content -->
		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-bars"></i>Assign Access Role
				</h3>
				<div class="box-tool" style="text-align: right;">
					<h3>	<a href="${pageContext.request.contextPath}/showRoleList">Role List</a></h3><a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
              </div>							
			</div>
			
			<div class=" box-content">
				<form id="validation-form" action="submitAssignedRole" method="post">

					<div class="box-content">

						<div class="col-md-2">Employee Name</div>
						<div class="col-md-4" style="text-align: center">
							<input type="text" name="empName" id="empName"
								class="form-control" required data-rule-required="true" readonly>



						</div>

						<input type="hidden" id="empId" name="empId">
					</div>
					<br />
					<br />
					<br />
					<div class="box-content">

						<div class="col-md-2">Role</div>
						<div class="col-md-4" style="text-align: center">
							<select name="role" id="role" class="form-control" tabindex="6"
								placeholder="Selete Role" required data-rule-required="true">


								<option value="" disabled selected>Select Role</option>


								<c:forEach items="${createdRoleList}" var="createdRoleList"
									varStatus="count">
									<option value="${createdRoleList.roleId}"><c:out value="${createdRoleList.roleName}"/></option>
								</c:forEach>

							</select>
						</div>


					</div>
					<br />
					<br />
					<br />
					<div class="box-content">

						<div class="col-md-2"></div>
						<div class="col-md-4" style="text-align: center">
							<input type="submit" value="Submit" class="btn btn-info">

						</div>
					</div>
					<br />
					<br />

					<div class="row">
						<div class="col-md-12 table-responsive">
							<table class="table table-bordered table-striped fill-head "
								style="width: 70%" id="table_grid">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Users Name</th>
										<th>Assigned Role</th>
										<th>Add/Edit/View</th>



									</tr>
								</thead>

								<tbody>

									<c:forEach items="${userList}" var="userList" varStatus="count">

										<tr>

                                            <c:set var="rId" value=""/>
											<c:set var="empRoll" value="" />
											<c:set var="btnClass" value="glyphicon glyphicon-plus" />
											<c:set var="detail" value="" />
											<c:set var="add" value="Assign" />
											<td><c:out value="${count.index+1}" /></td>

											<td align="left"><c:out value="${userList.username}" /></td>



											<c:forEach items="${createdRoleList}" var="createdRoleList"
												varStatus="count">
												<c:choose>
													<c:when test="${createdRoleList.roleId==userList.roleId}">
														<c:set var="empRoll" value="${createdRoleList.roleName}" />
														<c:set var="rId" value="${createdRoleList.roleId}" />

														<c:set var="btnClass" value="glyphicon glyphicon-edit" />
														<c:set var="detail" value="glyphicon glyphicon-th-list" />
														<c:set var="add" value="Edit" />
													</c:when>
												</c:choose>
											</c:forEach>

											<td align="left"><c:out value="${empRoll}" /></td>





											<td><span class='<c:out value="${btnClass}" />'
												data-toggle="tooltip" title='<c:out value="${add}" />'
						  						onclick="editRole('${userList.username}', ${userList.id})"></span>
												<a
												href="${pageContext.request.contextPath}/showAssignUserDetail/<c:out value="${userList.id}" />/${rId}/<c:out value="${userList.username}" />/${empRoll}"
												data-toggle="tooltip" title="Access Detail"> <span
													class='<c:out value="${detail}" />'></span>
											</a></td>
										</tr>

									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>







				</form>
			</div>



		</div>
	</div>
	<!-- END Main Content -->

	<footer>
	<p>2017 © Monginis.</p>
	</footer>

	<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
		class="fa fa-chevron-up"></i></a>





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

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>



	<script>
		function editRole(empName, empId)
		{
			
			//alert(empId);
			document.getElementById("empId").value=empId;
			document.getElementById("empName").value=empName;
			
			 
		}
		</script>



</body>
</html>