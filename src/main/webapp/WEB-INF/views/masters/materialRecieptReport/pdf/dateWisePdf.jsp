<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Date wise Purchase Report</title>


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

<div align="center"> <h5>Purchase Report (Date wise )&nbsp;&nbsp;&nbsp;&nbsp; From &nbsp; ${from_date}  &nbsp;To &nbsp; ${to_date}</h5></div>

<table  align="center" border="1" cellspacing="0" cellpadding="1" 
		id="table_grid" class="table table-bordered">
								<thead >
									<tr class="bgpink">
									
									<th style="text-align:center;height: 25px">Sr.No.</th> 
										
										<th style="text-align:center;">Booking Date</th> 
										<th style="text-align:center;">Basic Value</th>
										<th style="text-align:center;">Discount</th>
										<th style="text-align:center;">Other</th>
										<th style="text-align:center;">Fret Amt</th>
										<th style="text-align:center;">Insu Amt</th> 
										<th style="text-align:center;">CGST</th>
										<th style="text-align:center;">SGST</th>
										<th style="text-align:center;">IGST</th> 
										<th style="text-align:center;">Cess</th>
										<th style="text-align:center;">Total</th>
								  </tr>
								</thead>
								
								 <tbody >
								  <tbody >
								 <c:set var="basicValue"   value="${0}"/>
								  <c:set var="discAmt"   value="${0}"/>
								  <c:set var="other"   value="${0}"/>
								   <c:set var="freightAmt"   value="${0}"/>
								    <c:set var="insuranceAmt"   value="${0}"/>
								<c:set var="igst"  value="${0 }"/>
								<c:set var="cgst"  value="${0 }"/>
								<c:set var="sgst"  value="${0 }"/>
								<c:set var="cess"   value="${0}"/>
								<c:set var="billAmount"  value="${0 }"/>
								  	<c:forEach items="${staticlist}" var="staticlist" varStatus="count">
												<tr>
													<td><c:out value="${count.index+1}" /></td> 
													<td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${staticlist.invBookDate}" /></td>
													<td style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.basicValue}"/>
													</td>
													 <c:set var="basicValue" value="${basicValue + staticlist.basicValue}"/>
													<td style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.discAmt}"/>
													 </td>
													 	 <c:set var="discAmt" value="${discAmt + staticlist.discAmt}"/>
													 	 <td style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.other}"/>
													 </td>
													 	 <c:set var="other" value="${other + staticlist.other}"/>
													<td style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.freightAmt}"/>
													  </td>
													  	<c:set var="freightAmt" value="${freightAmt + staticlist.freightAmt}"/>
													<td style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.insuranceAmt}"/>
													   </td>
													   	<c:set var="insuranceAmt" value="${insuranceAmt + staticlist.insuranceAmt}"/>
													<td style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.cgst}"/>
													 </td> <c:set var="cgst"  value="${cgst+staticlist.cgst }"/>
													<td style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.sgst}"/>
													 </td> <c:set var="sgst"  value="${sgst+staticlist.sgst }"/>
													<td style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.igst}"/>
													  </td> <c:set var="igst"  value="${igst + staticlist.igst}"/> 
													<td style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.cess}"/>
													 </td>
													 	 <c:set var="cess" value="${cess + staticlist.cess}"/>
													<td style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.billAmount}"/>
													  </td> 
													 
														<c:set var="billAmount"  value="${billAmount+staticlist.billAmount }"/>
													
												</tr>
												</c:forEach>
								  <tr>
								  <td colspan='2'><b>Total</b></td>
								  
								  <td style="text-align:right"><b><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value ="${basicValue}"/></b></td>
								  <td style="text-align:right"><b><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value ="${discAmt}"/></b></td>
								  <td style="text-align:right"><b><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value ="${other}"/></b></td>
								 <td style="text-align:right"><b><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value ="${freightAmt}"/></b></td>
								 <td style="text-align:right"><b><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value ="${insuranceAmt}"/></b></td>
								 <td style="text-align:right"><b><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${cgst}"/></b></td>
								   <td style="text-align:right"><b><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${sgst}"/></b></td>
								     <td style="text-align:right"><b><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${igst}"/></b></td> 
								     
								     <td style="text-align:right"><b><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value ="${cess}"/></b></td>
								     <td style="text-align:right"><b><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${billAmount}"/></b></td>
								      
								  </tr>
							 </tbody>
								</table>

	
</body>
</html>