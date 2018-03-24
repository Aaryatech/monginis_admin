<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Sales Report Billwise PDF</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->


<style type="text/css">
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: left;
	padding: 2px;
	font-size: 10;
}

tr:nth-child(even) {
	background-color: #f2f2f2
}

th {
	background-color: #EA3291;
	color: white;
}
</style>
</head>
<body onload="myFunction()">
<h3 align="center">Galdhar Foods Pvt Ltd</h3>
<p align="center">A-89, Shendra M.I.D.C., Aurangabad</p>
<p align="center">(All Sales)</p>
<p align="center">Sales Report (Sales Royalty Fr Wise)</p><!--  -->

<div align="center">From ${fromDate}- To ${toDate}</div>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		id="table_grid" class="table table-bordered">
		<thead>
			<tr class="bgpink">
				<th>Sr.No.</th>
				<th>Fr Name</th>
				<th>City</th>
				<th>Sale Value</th>
				<th>Grn Value</th>
				<th>Gvn Value</th>
				<th>%</th>
				<th>Net Value</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="grandNetValue" value="${0 }" />
			<c:set var="grandGrnValue" value="${0 }" />
			<c:set var="grandGvnValue" value="${0 }" />
			<c:set var="FinalNetValue" value="${0 }" />
			<c:forEach items="${report}" var="report" varStatus="count">
				<tr>
					<td><c:out value="${count.index+1}" /></td>
					<td><c:out value="${report.month}" /></td>
					<td><c:out value="${report.tBillTaxableAmt}" /></td>
					
					<td><c:out value="${report.tGrnTaxableAmt}" /></td>
					<td><c:out value="${report.tGvnTaxableAmt}"></c:out></td>
					<td><c:out value="3" /></td>
					
					<c:set var="netValue" value="${report.tBillTaxableAmt -(report.tGrnTaxableAmt + report.tGvnTaxableAmt)}" />
					
					<c:set var="grandNetValue"
						value="${grandNetValue + netValue}" />
						
					<c:set var="grandGrnValue" value="${grandGrnValue + report.tGrnTaxableAmt}" />
					
										<c:set var="grandGvnValue" value="${grandGvnValue + report.tGvnTaxableAmt}" />
				
					<td><c:out value="${netValue}" /></td>
					<c:set var="rAmt"
						value="${netValue*3/100}" />
						
							<c:set var="FinalNetValue"
						value="${FinalNetValue + rAmt}" />
						
					<td><fmt:formatNumber type="number"
								maxFractionDigits="2" value="${rAmt}" /></td>
				</tr>

			</c:forEach>
				<tr>
					<td colspan='3'><b>Total</b></td>
					<td><b><fmt:formatNumber type="number"
								maxFractionDigits="2" value="${grandNetValue}" /></b></td>
					<td><b><fmt:formatNumber type="number"
								maxFractionDigits="2" value="${grandGrnValue}" /></b></td>
					<td><b><fmt:formatNumber type="number"
								maxFractionDigits="2" value="${grandGvnValue}" /></b></td>
					<td><b><fmt:formatNumber type="number"
								maxFractionDigits="2" value="${FinalNetValue}" /></b></td>
					<td></td>
					
				</tr>
		</tbody>
	</table>

	<!-- END Main Content -->

</body>
</html>