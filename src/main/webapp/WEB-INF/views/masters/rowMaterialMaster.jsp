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
						<i class="fa fa-file-o"></i>Row material Master
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
								<i class="fa fa-bars"></i>Add Row Material
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
							<form action="" method="post" enctype="multipart/form-data"
								name="form1" id="form1">

<div class="form-group">
								<label class="col-sm-3 col-lg-2 control-label">RM Code</label>
								<div class="col-sm-6 col-lg-4 controls">
								 <input type="text" name="textfield" class="form-control" />
								 </div>
								
								 <label class="col-sm-3 col-lg-2 control-label">RM Name </label>
								 <div class="col-sm-6 col-lg-4 controls">
								 <input type="text" name="textfield2" class="form-control" />
								 </div>
								 
								</div><br/>
								<br/><br/><div class="form-group">
								 <label class="col-sm-3 col-lg-2 control-label">RM UOP</label>
								  <div class="col-sm-6 col-lg-4 controls">
								 <select  name="select"  class="form-control">
										<option value="-1">Select RM UOP</option>
								</select>
								</div>
							
									<label class="col-sm-3 col-lg-2 control-label">RM Specification </label>
									 <div class="col-sm-6 col-lg-4 controls">
								 <input type="text"
										name="textfield3" class="form-control"/>
									 </div>
									 </div>
									<br/><br/>
									<div class="form-group">
									 <label class="col-sm-3 col-lg-2 control-label">RM Icon</label>
									  <div class="col-sm-6 col-lg-4 controls">
								  <input type="file" name="file"  class="form-control"/>
									 </div>
								 
									<label class="col-sm-3 col-lg-2 control-label">Group</label>
									  <div class="col-sm-6 col-lg-4 controls">
								 <select name="select2" class="form-control">
											<option value="-1">Select Group</option>
									</select>
									  </div>
									   </div>
									<br/><br/>
									<div class="form-group">
										<label class="col-sm-3 col-lg-2 control-label">Category</label>
									 <div class="col-sm-6 col-lg-4 controls">
								 	 <select name="select3" class="form-control">
											<option value="-1">Select Category</option>
									</select>
									 </div>
							 
									<label class="col-sm-3 col-lg-2 control-label">RM Pack Quantity</label>
									
									 <div class="col-sm-6 col-lg-4 controls">
								  <input type="text"
										name="textfield4"  class="form-control"/></div>
										 </div>
									<br/><br/>
									<div class="form-group">
									  <label class="col-sm-3 col-lg-2 control-label">RM min Quantity</label>
									  <div class="col-sm-6 col-lg-4 controls">
								   <input type="text"
										name="textfield7" class="form-control" /></div>
									 <label class="col-sm-3 col-lg-2 control-label">RM max Quantity </label>
									 
									 <div class="col-sm-6 col-lg-4 controls">
								  <input type="text"
										name="textfield8"class="form-control" />
									</div>
							  </div>
									<br/><br/>
									<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Weight</label>
									 <div class="col-sm-6 col-lg-4 controls">
								  <input type="text" name="textfield5"class="form-control" /></div>
									  <label class="col-sm-3 col-lg-2 control-label">Rate </label>
									 <div class="col-sm-6 col-lg-4 controls">
								   <input type="text" name="textfield6" class="form-control"/></div>
								    </div>
									<br/><br/>
									<div class="form-group">
									  <label class="col-sm-3 col-lg-2 control-label">RM GST % </label>
									 <div class="col-sm-6 col-lg-4 controls">
								    <select name="select4" class="form-control">
											<option value="-1">Select RM GST</option>
									</select>
								 </div>
								 
									<label class="col-sm-3 col-lg-2 control-label">RM ROL </label>
									 <div class="col-sm-6 col-lg-4 controls">
								 <input type="text" name="textfield10" class="form-control"/></div>
								  </div>
									<br/><br/>
									<div class="form-group">
									  <label class="col-sm-3 col-lg-2 control-label">RM OP Rate </label>
								 <div class="col-sm-6 col-lg-4 controls">
								 	  <input type="text" name="textfield9" class="form-control"/></div>
									<label class="col-sm-3 col-lg-2 control-label">RM OP Qty </label>
								 <div class="col-sm-6 col-lg-4 controls">
								 	<input type="text" name="textfield11" class="form-control"/>
									</div>
								  </div>
									<br/><br/>
									<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">RM ReOrder Qty </label>
								 <div class="col-sm-6 col-lg-4 controls">
								 	<input type="text"
										name="textfield12" class="form-control"/></div>
									<label class="col-sm-3 col-lg-2 control-label">RM Issue Qty </label>
								 <div class="col-sm-6 col-lg-4 controls">
								 	<input type="text"
										name="textfield13" class="form-control"/></div>
										 </div>
									<br/><br/>
									<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">RM Rejected Qty </label>
								 <div class="col-sm-6 col-lg-4 controls">
								 	<input type="text"
										name="textfield14"class="form-control" />
									</div>
							 
									<label class="col-sm-3 col-lg-2 control-label">RM Close Qty </label>
									
								 <div class="col-sm-6 col-lg-4 controls">
								 	<input type="text"
										name="textfield15"class="form-control" /></div>
										 </div>
									<br/><br/>
									<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">RM isCritical </label>
								 <div class="col-sm-6 col-lg-4 controls">
								 	<input type="text"
										name="textfield16" class="form-control"/></div>
									<label class="col-sm-3 col-lg-2 control-label">RM Activeyn </label>
									 <div class="col-sm-6 col-lg-4 controls">
								 <input type="text"
										name="textfield17" class="form-control"/>
									 </div>
									 <br/>
									 <br/>
								 </div>
				<div class="row">
					<div class="col-md-12" style="text-align: center">
						<input type="submit" class="btn btn-info" value="Submit">


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