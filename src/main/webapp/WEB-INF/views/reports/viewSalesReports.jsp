<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Monginis Admin-Sales Report</title>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	 
</head>
<body>

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