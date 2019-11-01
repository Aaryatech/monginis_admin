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
	font-size: 10;
	width: 100%;
	page-break-inside: auto !important
}

p {
	color: black;
	font-family: arial;
	font-size: 60%;
	margin-top: 0;
	padding: 0;
}

h6 {
	color: black;
	font-family: arial;
	font-size: 80%;
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

	<div align="center">
		<h5>Item Wise GRN GVN Report &nbsp;&nbsp;&nbsp;&nbsp; From &nbsp;
			${fromDate} &nbsp;To &nbsp; ${toDate}</h5>
	</div>
	<table align="center" border="1" cellspacing="0" cellpadding="1"
		id="table_grid" class="table table-bordered">
		<thead>
			<tr class="bgpink">
				<th width="7%">Sr.No.</th>
				<th>Item Name</th>
				<th width="10%">Requested QTY</th>
				<th width="10%">Approved QTY</th>
				<th width="10%">Taxable AMT</th>
				<th width="10%">Tax AMT</th>
				<th width="10%">Total AMT</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="taxAmount" value="${0}" />
			<c:set var="taxableAmt" value="${0 }" />
			<c:set var="total" value="${0 }" />
			<c:set var="gvnQty" value="${0 }" />
			<c:set var="aprQty" value="${0 }" />
			<c:forEach items="${list}" var="list" varStatus="count">
				<tr>
					<td><c:out value="${count.index+1}" /></td>
					<td><c:out value="${list.itemName}" /></td>
					<td style="text-align: right;"><fmt:formatNumber type="number"
							maxFractionDigits="2" minFractionDigits="2" groupingUsed="false"
							value="${list.grnGvnQty}" /></td>
					<td style="text-align: right;"><fmt:formatNumber type="number"
							maxFractionDigits="2" minFractionDigits="2" groupingUsed="false"
							value="${list.aprQtyAcc}" /></td>
					<td style="text-align: right;"><fmt:formatNumber type="number"
							maxFractionDigits="2" minFractionDigits="2" groupingUsed="false"
							value="${list.aprTaxableAmt}" /></td>
					<td style="text-align: right;"><fmt:formatNumber type="number"
							maxFractionDigits="2" minFractionDigits="2" groupingUsed="false"
							value="${list.aprTotalTax}" /></td>
					<td style="text-align: right;"><fmt:formatNumber type="number"
							maxFractionDigits="2" minFractionDigits="2" groupingUsed="false"
							value="${list.aprGrandTotal}" /></td>

					<c:set var="taxAmount" value="${taxAmount+list.aprTotalTax}" />
					<c:set var="taxableAmt" value="${taxableAmt+list.aprTaxableAmt}" />
					<c:set var="total" value="${total+list.aprGrandTotal}" />
					<c:set var="gvnQty" value="${gvnQty+list.grnGvnQty}" />
					<c:set var="aprQty" value="${aprQty+list.aprQtyAcc}" />
				</tr>

			</c:forEach>
			<tr>

				<td width="100" colspan='2' align="left"><b>Total</b></td>
				<td width="100" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							groupingUsed="false" value="${gvnQty}" /></b></td>
				<td width="100" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							groupingUsed="false" value="${aprQty}" /></b></td>
				<td width="100" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							groupingUsed="false" value="${taxableAmt}" /></b></td>

				<td width="100" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							groupingUsed="false" value="${taxAmount}" /></b></td>
				<td width="100" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							groupingUsed="false" value="${total}" /></b></td>
				<!--  <td><b>Total</b></td> -->
			</tr>
		</tbody>
	</table>


	<!-- END Main Content -->

</body>
</html>