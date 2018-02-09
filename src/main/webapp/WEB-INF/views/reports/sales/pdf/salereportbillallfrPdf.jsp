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
	width:100%;

} 
p  {
    color: black;
    font-family: arial;
    font-size: 60%;
	margin-top: 0;
	padding: 0;

}
h6  {
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

<div align="center"> <h5>Sales Report (All Fr wise ) &nbsp;&nbsp;&nbsp;&nbsp; From &nbsp; ${fromDate}  &nbsp;To &nbsp; ${toDate}</h5></div>
	
	<table  align="center" border="1" cellspacing="0" cellpadding="1" 
		id="table_grid" class="table table-bordered">
		<thead>
			<tr class="bgpink">
				<th height="25">Sr.No.</th>
				<th>Invoice No</th>
				<th>Date</th>
				<th>Party Name</th>
				<th>City</th>
				<th>GSTIN</th>
				<th>HSNCD</th>
				<th>Tax Rate</th>
				<th>Taxable Value</th>
				<th>CGST</th>
				<th>SGST</th>
				<th>IGST</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="taxAmount" value="${0}" />
			<c:set var="igst" value="${0 }" />
			<c:set var="cgst" value="${0 }" />
			<c:set var="sgst" value="${0 }" />
			<c:set var="grandTotal" value="${0 }" />
			<c:forEach items="${report}" var="report" varStatus="count">
				<tr>
					<td><c:out value="${count.index+1}" /></td>
					<td width="100"><c:out value="${report.invoiceNo}" /></td>
					<td width="150"><c:out value="${report.billDate}" /></td>
					<td width="200"><c:out value="${report.frName}" /></td>
					<td width="100"><c:out value="${report.frCity}" /></td>
					<td width="100"><c:out value="${report.frGstNo}" /></td>
					<td width="100"><c:out value="${report.itemHsncd}" /></td>

					<c:choose>
						<c:when test="${report.isSameState eq 1}">
							<td width="80" align="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${report.itemTax1+report.itemTax2}" /></td>
						</c:when>
						<c:when test="${report.isSameState eq 0}">
							<td width="80" align="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${report.itemTax3}" /></td>
						</c:when>
					</c:choose>
<c:set var="taxAmount" value="${taxAmount+report.taxableAmtSum}" />

<c:set var="cgst" value="${cgst+report.cgstRsSum}" />

<c:set var="sgst" value="${sgst+report.sgstRsSum}" />

<c:set var="igst" value="${igst+report.igstRsSum}" />

					<td width="80" align="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${report.taxableAmtSum}" /></td>
					
					<%-- <td><c:out value="${total}" /></td> --%>

					<td width="80" align="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${report.cgstRsSum}" /></td>
							
								<td width="80" align="right"><fmt:formatNumber type="number" maxFractionDigits="2"  minFractionDigits="2"
							value="${report.sgstRsSum}" /></td>
							
								<td width="80" align="right"><fmt:formatNumber type="number" maxFractionDigits="2"  minFractionDigits="2"
							value="${report.igstRsSum}" /></td>
				</tr>

			</c:forEach>
			<tr>

				<td colspan='8'><b>Total</b></td>
				<td width="100" align="right"><b><fmt:formatNumber type="number"
							maxFractionDigits="2"  minFractionDigits="2" value="${taxAmount}" /></b></td>
				<td width="100" align="right"><b><fmt:formatNumber type="number"
							maxFractionDigits="2"  minFractionDigits="2" value="${cgst}" /></b></td>
				<td width="100" align="right"><b><fmt:formatNumber type="number"
							maxFractionDigits="2"  minFractionDigits="2" value="${sgst}" /></b></td>
				<td width="100" align="right"><b><fmt:formatNumber type="number"
							maxFractionDigits="2" minFractionDigits="2" value="${igst}" /></b></td>
				<td></td>
				<!--  <td><b>Total</b></td> -->
			</tr>
		</tbody>
	</table>


	<!-- END Main Content -->

</body>
</html>