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
<title>CRN Sales Report Monthwise PDF</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->


 <style type="text/css">
 table {
	border-collapse: collapse;
	font-size: 10;
	width:100%;
page-break-inside: auto !important 
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

<div align="center"> <h5> CRN Sales Report (Month Wise)  &nbsp;&nbsp;&nbsp;&nbsp; From &nbsp; ${fromDate}  &nbsp;To &nbsp; ${toDate}</h5></div>
	<table  align="center" border="1" cellspacing="0" cellpadding="1" 
		id="table_grid" class="table table-bordered">
		<thead>
			<tr class="bgpink">
				<th>Sr.No.</th>
				<th>Month</th>
				<th>Taxable Amt</th>
				<th>Tax Amt</th>
				<th>Total</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="ttlTaxable" value="${0}" />
			<c:set var="ttlTax" value="${0 }" />
			<c:set var="grandTotal" value="${0 }" />
			<c:forEach items="${report}" var="report" varStatus="count">
				<tr>
					<td width="0" ><c:out value="${count.index+1}" /></td>
					<td width="100" align="left"><c:out value="${report.monthName}-${report.frName}" /></td>
					<td width="80" align="right"><fmt:formatNumber type="number"
								maxFractionDigits="2"  minFractionDigits="2"  value="${report.crnTaxableAmt}" /></td>
					<td width="80" align="right"><fmt:formatNumber type="number"
								maxFractionDigits="2"  minFractionDigits="2"  value="${report.crnTotalTax}" /></td>
					<td width="80" align="right"><fmt:formatNumber type="number"
								maxFractionDigits="2"  minFractionDigits="2"  value="${report.crnGrandTotal}" /></td>
					
					
					<c:set var="ttlTaxable" value="${ttlTaxable + report.crnTaxableAmt}" />					
					<c:set var="ttlTax" value="${ttlTax + report.crnTotalTax}" />
					<c:set var="grandTotal" value="${grandTotal + report.crnGrandTotal}" />		
				</tr>

			</c:forEach>
				<tr>
				
					<td width="100" align="left"><b>Total</b></td>
					<td></td>			
					<td width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2"  minFractionDigits="2"  value="${ttlTaxable}" /></b></td>
					<td width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2"  minFractionDigits="2"  value="${ttlTax}" /></b></td>
					<td width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2"  minFractionDigits="2"  value="${grandTotal}" /></b></td>
				</tr>
		</tbody>
	</table>
	

	<!-- END Main Content -->

</body>
</html>