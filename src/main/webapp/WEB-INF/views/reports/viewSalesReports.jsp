<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
<<<<<<< HEAD
	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
		<!-- BEGIN Sidebar -->
		<div id="sidebar" class="navbar-collapse collapse">
			<jsp:include page="/WEB-INF/views/include/navigation.jsp"></jsp:include>
			<div id="sidebar-collapse" class="visible-lg">
				<i class="fa fa-angle-double-left"></i>
			</div>
			<!-- END Sidebar Collapse Button -->
		</div>
=======
	 
</head>
<body>

>>>>>>> branch 'master' of https://github.com/Aaryatech/monginis_admin.git
<a href="${pageContext.request.contextPath}/showSaleReportByDate">showSaleReportByDate</a><br>
<a href="${pageContext.request.contextPath}/showSaleReportByFr">showSaleReportByFr</a><br>
<a href="${pageContext.request.contextPath}/showSaleReportGrpByDate">showSaleReportGrpByDate</a><br>
<a href="${pageContext.request.contextPath}/showSaleReportByMonth">showSaleReportByMonth</a><br>
<a href="${pageContext.request.contextPath}/showSaleRoyaltyByCat">showSaleRoyaltyByCat</a><br>
<a href="${pageContext.request.contextPath}/showSaleRoyaltyByFr">showSaleRoyaltyByFr</a><br>
<a href="${pageContext.request.contextPath}/showSaleReportItemwise">showSaleReportItemwise</a><br>
<a href="${pageContext.request.contextPath}/showSaleReportBillwiseAllFr">showSaleReportBillwiseAllFr</a><br>
<a href="${pageContext.request.contextPath}/showSaleReportRoyConsoByCat">showSaleReportRoyConsoByCat</a>
</body>
</html>