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
<div align="center"> <h5> Sales Report (Sales Royalty Franchisee Wise) &nbsp;&nbsp;&nbsp;&nbsp; From &nbsp; ${fromDate}  &nbsp;To &nbsp; ${toDate}</h5></div>
	<table  align="center" border="1" cellspacing="0" cellpadding="1" 
		id="table_grid" class="table table-bordered">
	
		<thead>
			<tr class="bgpink">
				<th height="25">Sr.No.</th>
				<th>Fr Name</th>
				<th>City</th>
				<th>Sale Value</th>
				<th>Grn Value</th>
				<th>Gvn Value</th>
				<th>%</th>
				<th>Net Value</th>
				<th>Amount</th>
		<!-- 		<th>Royalty Amt</th> -->
			</tr>
		</thead>
		<tbody>
		
			<c:set var="grandNetValue" value="${0 }" />
						<c:set var="granBillTaxableValue" value="${0 }" />
			
			<c:set var="grandGrnValue" value="${0 }" />
			<c:set var="grandGvnValue" value="${0 }" />
			<c:set var="FinalNetValue" value="${0 }" />
				<c:set var="taxPer" value="${0}" />
				
								<c:set var="rAmtSum" value="${0}" />
				
			<c:forEach items="${report}" var="report" varStatus="count">
				<tr>
					<td><c:out value="${count.index+1}" /></td>
					<td width="200"><c:out value="${report.frName}" /></td>
					<td width="100"><c:out value="${report.frCity}" /></td>
					<td  width="100" align="right"><fmt:formatNumber type="number"
								maxFractionDigits="2" value="${report.tBillTaxableAmt}" /></td>
								
								
														<c:set var="granBillTaxableValue" value="${granBillTaxableValue+report.tBillTaxableAmt}" />
								
					
					<td  width="100" align="right">
					<fmt:formatNumber type="number"
								maxFractionDigits="2" value="${report.tGrnTaxableAmt}" /></td>
					<td  width="100" align="right">
					<fmt:formatNumber type="number"
								maxFractionDigits="2" value="${report.tGvnTaxableAmt}" /></td>
					<td  width="100" align="right"><c:out value="${royPer}" /></td>
					
					<c:set var="netValue" value="${report.tBillTaxableAmt -(report.tGrnTaxableAmt + report.tGvnTaxableAmt)}" />
					
					<c:set var="grandNetValue"
						value="${grandNetValue + netValue}" />
						
					<c:set var="grandGrnValue" value="${grandGrnValue + report.tGrnTaxableAmt}" />
					
										<c:set var="grandGvnValue" value="${grandGvnValue + report.tGvnTaxableAmt}" />
				
					<td  width="100" align="right">
					<fmt:formatNumber type="number"
								maxFractionDigits="2" value="${netValue}" /></td>
								
					<c:set var="rAmt"
						value="${netValue*royPer/100}" />
							<c:set var="taxPer"
						value="${taxPer + 3}" />
							<c:set var="FinalNetValue"
						value="${FinalNetValue + netValue}" />
						
					<td  width="100" align="right"><fmt:formatNumber type="number"
								maxFractionDigits="2" value="${rAmt}" /></td>
								
																<c:set var="rAmtSum" value="${rAmtSum+rAmt}" />
								
				</tr>

			</c:forEach>
				<tr>
					<td colspan='3'><b>Total</b></td>
					<td  width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" value="${granBillTaxableValue}" /></b></td>
					<td  width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" value="${grandGrnValue}" /></b></td>
					<td  width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" value="${grandGvnValue}" /></b></td>
								<td></td>
				 <%--  <td  width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" value="${taxPer}" /></b></td> --%>
					<td  width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" value="${FinalNetValue}" /></b></td>
								
								
								<td  width="100" align="right"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" value="${rAmtSum}" /></b></td>
								
								
								
								
								
					<td></td>
					
				</tr>
		</tbody>
	</table>

	<!-- END Main Content -->

</body>
</html>