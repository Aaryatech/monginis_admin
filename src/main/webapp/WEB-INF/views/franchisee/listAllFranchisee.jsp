	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Dashboard - MONGINIS Admin</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">



<!--base css styles-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/data-tables/bootstrap3/dataTables.bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-fileupload/bootstrap-fileupload.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/chosen-bootstrap/chosen.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-timepicker/compiled/timepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/clockface/css/clockface.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/css/datepicker.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/daterangepicker.css" />



<!--page specific css styles-->

<!--flaty css styles-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/flaty.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/flaty-responsive.css">

<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.png">

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>

</head>
<body>

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>


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
						<i class="fa fa-file-o"></i> Franchisees
					</h1>
				</div>
			</div>
			<!-- END Page Title -->


			<!-- BEGIN Main Content -->
			<div class="row">
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-12">
							<div class="box">
								<div class="box-title">
									<h3>
										<i class="fa fa-bars"></i>  Franchisees List
									</h3>
									<div class="box-tool">
										<a href="${pageContext.request.contextPath}/resources/index.php/franchisee/list_all">Back to
											List</a> <a data-action="collapse" href="#"><i
											class="fa fa-chevron-up"></i></a>
									</div>
								</div>
								
                            <div class="box-content">
                                
                              <div class="clearfix"></div>
<div class="table-responsive" style="border:0">
    <table width="100%" class="table table-advance" id="table1">
        <thead>
            <tr>
                <th width="18" style="width:18px">#</th>
                <th width="195" align="left">Name</th>
                <th width="447" align="left">Image</th>
                <th width="344" align="left">Owner</th>
                <th width="70" align="left">City</th>
                <th width="70" align="left">Status</th>
                <th width="70" align="left">Action</th>
            </tr>
        </thead>
        <tbody>
      <c:forEach items="${franchiseeList}" var="franchiseeList" varStatus="count">
            <tr>
              <td><c:out value="${count.index+1}"></c:out></td>
              <td align="left"><c:out value="${franchiseeList.frName}"/></td>
              <td align="left">
            <img
													src="${url}${franchiseeList.frImage}"height="80" width="80"  
													onerror="this.src='${pageContext.request.contextPath}/resources/img/No_Image_Available.jpg';"/>
													
              
              <c:out value="${franchiseeList.frImage}"/>
                </td>
              <td align="left"><c:out value="${franchiseeList.frOwner}"/></td>
              <td align="left"><c:out value="${franchiseeList.frCity}"/></td>
              <td align="left">
             <c:choose>
  <c:when test="${franchiseeList.delStatus==0}">
  Active    
  </c:when>
 
  <c:otherwise>
 In-Active
  </c:otherwise>
</c:choose>
              
             
              <td align="left">
                <a href="updateFranchisee/${franchiseeList.frId}" ><span class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
                
                <a href="deleteFranchisee/${franchiseeList.frId}" onClick="return confirm('Are you sure want to delete this record');"><span class="glyphicon glyphicon-remove"></span></a>
              </td>
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

			<script type="text/javascript"
				src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
			<script type="text/javascript"
				src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>
				
				
				
</body>
</html>