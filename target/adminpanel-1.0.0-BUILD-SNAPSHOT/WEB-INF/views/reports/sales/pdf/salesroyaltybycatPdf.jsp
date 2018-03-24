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
<title>Sales Report Royalty</title>
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
	<p align="center">(Royalty)</p>
	<p align="center">Royalty Report (Cat/Item wise )</p><!--Report R5  -->

	<div align="center">From ${fromDate}- To ${toDate}</div>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		id="table_grid" class="table table-bordered">
		<thead>
			<tr class="bgpink">
				<th>Sr.No.</th>
				<th>Item Name</th>
				<th>Sale Qty</th>
				<th>Sale Value</th>
				<th>GRN Qty</th>
				<th>GRN Value</th>
				<th>GVN Qty</th>
				<th>GVN Value</th>
				<th>Net Qty</th>
				<th>Net Value</th>
				<th>Royalty %</th>
				<th>Royalty Amt</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="taxAmount" value="${0}" />
			<c:set var="igst" value="${0 }" />
			<c:set var="cgst" value="${0 }" />
			<c:set var="sgst" value="${0 }" />
			<c:set var="grandTotal" value="${0 }" />
			<c:forEach items="${catList}" var="report" varStatus="count">
				<tr>

					<td><b>${report.catName}</b></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>

					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>

					<%-- <td><c:out value="${total}" /></td> --%>

					<%-- <td><fmt:formatNumber type="number"
								maxFractionDigits="2" value="${total}" /></td> --%>
				</tr>
				<c:set var="srNo" value="1"></c:set>
				<c:forEach items="${royaltyList}" var="royalty" varStatus="count">

					<c:choose>
						<c:when test="${royalty.catId==report.catId}">

							<tr>
								<td><c:out value="${srNo}" /></td>
								<c:set var="srNo" value="${srNo+1}"></c:set>
								<td><c:out value="${royalty.item_name}" /></td>
								<td><c:out value="${royalty.tBillQty}" /></td>
								<%-- <td><c:out value="${royalty.tBillTaxableAmt}" /></td> --%>
									
								<td><fmt:formatNumber
										type="number" maxFractionDigits="2"
										value="${royalty.tBillTaxableAmt}" /></td>
										
								<td><c:out value="${royalty.tGrnQty}" /></td>

<%-- 								<td><c:out value="${royalty.tGrnTaxableAmt}" /></td>
 --%>								
								<td><fmt:formatNumber
										type="number" maxFractionDigits="2"
										value="${royalty.tGrnTaxableAmt}" /></td>
										
										
										
								<td><c:out value="${royalty.tGvnQty}" /></td>
								
<%-- 								<td><c:out value="${royalty.tGvnTaxableAmt}" /></td>
 --%>
 
 <td><fmt:formatNumber
										type="number" maxFractionDigits="2"
										value="${royalty.tGvnTaxableAmt}" /></td>
								<c:set var="netQty"
									value="${royalty.tBillQty -(royalty.tGrnQty+royalty.tGvnQty)}"></c:set>

								<c:set var="netValue"
									value="${royalty.tBillTaxableAmt -(royalty.tGrnTaxableAmt+royalty.tGvnTaxableAmt)}"></c:set>


								<td><c:out value="${netQty}" /></td>
								
								<td style="text-align: center;"><fmt:formatNumber
										type="number" maxFractionDigits="2"
										value="${netValue}" />
										
<%-- 								<td><c:out value="${netValue}" /></td>
 --%>								<td align="center"><c:out value="${3}" /></td>
								<c:set var="rAmt" value="${(netValue*3)/100}"></c:set>

								<td style="text-align: center;"><fmt:formatNumber
										type="number" maxFractionDigits="2"
										value="${rAmt}" />
<%-- 								<td><c:out value="${rAmt}" /></td>
 --%>
							</tr>
						</c:when>
					</c:choose>


				</c:forEach>


			</c:forEach>
			<tr>

				<td colspan='2'><b>Total</b></td>
				<td><b><fmt:formatNumber type="number"
							maxFractionDigits="2" value="${taxAmount}" /></b></td>
				<td><b><fmt:formatNumber type="number"
							maxFractionDigits="2" value="${cgst}" /></b></td>
				<td><b><fmt:formatNumber type="number"
							maxFractionDigits="2" value="${sgst}" /></b></td>
				<td><b><fmt:formatNumber type="number"
							maxFractionDigits="2" value="${igst}" /></b></td>
				<td></td>
				<td><b><fmt:formatNumber type="number"
							maxFractionDigits="2" value="${grandTotal}" /></b></td>
				<!--  <td><b>Total</b></td> -->
			</tr>
		</tbody>
	</table>


	<!-- END Main Content -->

</body>
</html>