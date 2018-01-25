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
	<p align="center">Sales Report (Item Wise)</p><!--Report 8  -->
	<!-- report 8  -->

	<div align="center">From ${fromDate}- To ${toDate}</div>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		id="table_grid" class="table table-bordered">
		<thead>
			<tr class="bgpink">
				<th>Sr.No.</th>
				<th>Item Name</th>
				<th>HSNCD</th>
				<th>Tax Rate</th>
				<th>Qty</th>
				<th>Taxable Value</th>
				<th>CGST</th>
				<th>SGST</th>
				<th>IGST</th>
				<th>Total GST</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="qtySum" value="${0}" />
			<c:set var="taxAmtSum" value="${0 }" />
			<c:set var="cgstSum" value="${0 }" />
			<c:set var="sgstSum" value="${0 }" />
			<c:set var="igstSum" value="${0 }" />
			<c:set var="GrandTotalGst" value="${0 }" />
			<c:forEach items="${report}" var="report" varStatus="count">
				<tr>
					<td><c:out value="${count.index+1}" /></td>
					<td><c:out value="${report.itemName}" /></td>
					<td><c:out value="${report.itemHsncd}" /></td>
					<td><c:out value="${report.itemTax1 + report.itemTax2}" /></td>


					<c:set var="qtySum" value="${report.billQtySum + qtySum}" />
					<c:set var="taxAmtSum" value="${report.taxableAmtSum + taxAmtSum}" />
					<c:set var="cgstSum" value="${report.cgstRsSum + cgstSum}" />

					<c:set var="sgstSum" value="${report.sgstRsSum + sgstSum}" />

					<c:set var="igstSum" value="${report.igstRsSum + igstSum}" />


					<td><c:out value="${report.billQtySum}" /></td>
					<td align="right"><c:out value="${report.taxableAmtSum}" /></td>
					<td align="right"><c:out value="${report.cgstRsSum}"/></td>
					<td align="right"><c:out value="${report.sgstRsSum}"/></td>
					<td align="right"><c:out value="${report.igstRsSum}"/></td>

					<c:set var="totalGst"
						value="${report.sgstRsSum + report.cgstRsSum}" />
					<td align="right"><fmt:formatNumber type="number" maxFractionDigits="2"
							value="${totalGst}" /></td>

					<c:set var="GrandTotalGst" value="${totalGst + GrandTotalGst}" />

				</tr>

			</c:forEach>
			<tr>

				<td colspan='4'><b>Total</b></td>
				<td align="right"><b><fmt:formatNumber type="number"
							maxFractionDigits="2" value="${qtySum}" /></b></td>
				<td align="right"><b><fmt:formatNumber type="number"
							maxFractionDigits="2" value="${taxAmtSum}" /></b></td>
				<td align="right"><b><fmt:formatNumber type="number"
							maxFractionDigits="2" value="${cgstSum}" /></b></td>
				<td align="right"><b><fmt:formatNumber type="number"
							maxFractionDigits="2" value="${sgstSum}" /></b></td>
							<td><b><fmt:formatNumber type="number"
							maxFractionDigits="2" value="${igstSum}" /></b></td>
							
				<td align="right"><b><fmt:formatNumber type="number"
							maxFractionDigits="2" value="${GrandTotalGst}" /></b></td>
				<!--  <td><b>Total</b></td> -->
			</tr>
		</tbody>
	</table>


	<!-- END Main Content -->

</body>
</html>