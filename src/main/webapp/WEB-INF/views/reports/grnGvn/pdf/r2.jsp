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
<title>Grn Gvn Report  PDF</title>
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

<div align="center"> <h5> GRN GVN Report (Franchise Wise)  &nbsp;&nbsp;&nbsp;&nbsp; From &nbsp; ${fromDate}  &nbsp;To &nbsp; ${toDate}</h5></div>
	<table  align="center" border="1" cellspacing="0" cellpadding="1" 
		id="table_grid" class="table table-bordered">
		<thead>
			<tr class="bgpink">
				<th >Sr. No.</th>
				<th >Type</th>
				<th >Party Name</th>
				<th >Req Qty</th>
				<th  >Req Value</th>
				<th >Apr Qty</th>
				<th >Apr Amt</th>
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
					<td width="60" align="center" ><c:out value="${count.index+1}" /></td>
					<c:choose>
					<c:when test="${report.isGrn==0 || report.isGrn==2}">
								<c:set var="type" value="GVN" />
					</c:when>
					<c:when test="${report.isGrn==1}">
													<c:set var="type" value="GRN" />
					</c:when>
					</c:choose>
					<td width="100" align="center"><c:out value="${type}" /></td>
					<td width="120"><c:out value="${report.frName}" /></td>
					<td width="100" align="center"><c:out value="${report.reqQty}" /></td>
					<td width="100" align="right"><c:out value="${report.totalAmt}" /></td>
										<td align="center" width="100"><c:out value="${report.aprQty}" /></td>
										<td  align="right" width="100"><c:out value="${report.aprGrandTotal}" /></td>
					<%-- <c:set var="taxAmount" value="${taxAmount + report.taxableAmt}" />
					<c:set var="grandTotal"
						value="${grandTotal+total}" /> --%>
					<%-- <td><c:out value="${total}" /></td> --%>
				</tr>
			</c:forEach>
				<tr>
				
					<%-- <td width="100"colspan='5' align="left"><b>Total</b></td>
					<td width="100"align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2"  minFractionDigits="2"  value="${taxAmount}" /></b></td>
					<td width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2"  minFractionDigits="2"  value="${cgst}" /></b></td>
					<td width="100"align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2"  minFractionDigits="2"  value="${sgst}" /></b></td>
				
					<td width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2"  minFractionDigits="2"  value="${grandTotal}" /></b></td> --%>
					<!--  <td><b>Total</b></td> -->
				</tr>
		</tbody>
	</table>
	

	<!-- END Main Content -->

</body>
</html>