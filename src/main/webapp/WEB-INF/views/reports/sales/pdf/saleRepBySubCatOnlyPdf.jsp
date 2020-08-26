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
<title>Purchase Report Billwise PDF</title>
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
	<h3 align="center">${FACTORYNAME}</h3>
	<p align="center">${FACTORYADDRESS}</p>
	<div align="center">
		<h5>Purchase Report (Sub Category Wise) &nbsp;&nbsp;&nbsp;&nbsp;
			From &nbsp; ${fromDate} &nbsp;To &nbsp; ${toDate}</h5>
	</div>
	<table align="center" border="1" cellspacing="0" cellpadding="1"
		id="table_grid" class="table table-bordered">
		<thead>
			<tr class="bgpink">
				<th>Sr.No.</th>
				<th>Sub Category Name</th>
				<th>Sold Qty</th>
				<th>Sold Amt</th>
				<th>Var Qty</th>
				<th>Var Amt</th>
				<th>Ret Qty</th>
				<th>Ret Amt</th>
				<th>Net Qty</th>
				<th>Net Amt</th>
				<th>Ret Amt %</th>
				<th>Contribution %</th>
			</tr>
		</thead>
		<tbody>

			<c:set var="totalSoldQty" value="${0}" />
			<c:set var="totalSoldAmt" value="${0}" />
			<c:set var="totalVarQty" value="${0}" />
			<c:set var="totalVarAmt" value="${0}" />
			<c:set var="totalRetQty" value="${0}" />
			<c:set var="totalRetAmt" value="${0}" />
			<c:set var="totalNetQty" value="${0}" />
			<c:set var="totalNetAmt" value="${0}" />
			<c:set var="retAmtPer" value="${0}" />
			<c:set var="contriTotal" value="${0}" />

			<c:set var="netTotalForcontri" value="${0}" />

			<c:forEach items="${subCatReportList}" var="report" varStatus="count">
				<c:set var="netTotalForcontri"
					value="${netTotalForcontri+(report.netAmt)}" />
			</c:forEach>


			<tr>


			</tr>
			<c:forEach items="${subCatReportList}" var="report" varStatus="count">
				<tr>

					<td width="10"><c:out value="${count.index+1}" /></td>
					<td width="200"><c:out value="${report.subCatName}" /></td>
					<td width="10" align="right"><fmt:formatNumber type="number"
							maxFractionDigits="2" minFractionDigits="2"
							value="${report.soldQty}" /></td>

					<td width="10" align="right"><fmt:formatNumber type="number"
							maxFractionDigits="2" minFractionDigits="2"
							value="${report.soldAmt}" /></td>


					<td width="10" align="right"><fmt:formatNumber type="number"
							maxFractionDigits="2" minFractionDigits="2"
							value="${report.varQty}" /></td>


					<td width="10" align="right"><fmt:formatNumber type="number"
							maxFractionDigits="2" minFractionDigits="2"
							value="${report.varAmt}" /></td>


					<td width="10" align="right"><fmt:formatNumber type="number"
							maxFractionDigits="2" minFractionDigits="2"
							value="${report.retQty}" /></td>


					<td width="10" align="right"><fmt:formatNumber type="number"
							maxFractionDigits="2" minFractionDigits="2"
							value="${report.retAmt}" /></td>


					<td width="10" align="right"><fmt:formatNumber type="number"
							maxFractionDigits="2" minFractionDigits="2"
							value="${report.netQty}" /></td>


					<td width="10" align="right"><fmt:formatNumber type="number"
							maxFractionDigits="2" minFractionDigits="2"
							value="${report.netAmt}" /></td>

					<c:choose>

						<c:when test="${report.retAmtPer == 'NaN'}">
							<td width="10" align="right"><fmt:formatNumber type="number"
									maxFractionDigits="2" minFractionDigits="2" value="0.00" /></td>

						</c:when>

						<c:otherwise>
							<td width="10" align="right"><fmt:formatNumber type="number"
									maxFractionDigits="2" minFractionDigits="2"
									value="${report.retAmtPer}" /></td>

						</c:otherwise>

					</c:choose>

					<c:set var="contri"
						value="${(report.netAmt*100)/netTotalForcontri}" />
					<c:set var="contriTotal" value="${contriTotal+contri}" />

					<td width="10" align="right"><fmt:formatNumber type="number"
							maxFractionDigits="2" minFractionDigits="2" value="${contri}" /></td>

					<c:set var="totalSoldQty" value="${totalSoldQty+(report.soldQty)}" />
					<c:set var="totalSoldAmt" value="${totalSoldAmt+(report.soldAmt)}" />
					<c:set var="totalVarQty" value="${totalVarQty+(report.varQty)}" />
					<c:set var="totalVarAmt" value="${totalVarAmt+(report.varAmt)}" />
					<c:set var="totalRetQty" value="${totalRetQty+(report.retQty)}" />
					<c:set var="totalRetAmt" value="${totalRetAmt+(report.retAmt)}" />
					<c:set var="totalNetQty" value="${totalNetQty+(report.netQty)}" />
					<c:set var="totalNetAmt" value="${totalNetAmt+(report.netAmt)}" />

					<c:if test="${report.retAmtPer != 'NaN'}">
						<c:set var="retAmtPer" value="${retAmtPer+(report.retAmtPer)}" />
					</c:if>



				</tr>

			</c:forEach>

			<tr>

				<td></td>

				<td colspan='1'><b>Total</b></td>


				<td width="10" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${totalSoldQty}" /></b></td>

				<td width="10" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${totalSoldAmt}" /></b></td>
				<td width="10" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${totalVarQty}" /></b></td>

				<td width="10" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${totalVarAmt}" /></b></td>


				<td width="10" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${totalRetQty}" /></b></td>


				<td width="10" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${totalRetAmt}" /></b></td>


				<td width="10" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${totalNetQty}" /></b></td>



				<td width="10" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${totalNetAmt}" /></b></td>

				<td width="10" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${retAmtPer}" /></b></td>

				<td width="10" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${contriTotal}" /></b></td>



				<!--  <td><b>Total</b></td> -->
			</tr>


		</tbody>
	</table>


	<!-- END Main Content -->

</body>
</html>