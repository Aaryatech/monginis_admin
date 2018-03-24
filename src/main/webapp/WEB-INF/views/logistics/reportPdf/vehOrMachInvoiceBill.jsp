<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vehicle Or Machine Invoice Report</title>

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

<div align="center"> <h5>Vehicle Or Machine Invoice Report&nbsp;&nbsp;&nbsp;&nbsp; From &nbsp; ${from_date}  &nbsp;To &nbsp; ${to_date}</h5></div>

<table  align="center" border="1" cellspacing="0" cellpadding="1" 
		id="table_grid" class="table table-bordered">
								<thead >
									<tr class="bgpink">
									<th style="text-align:center;height: 25px">Sr.No.</th>
										<th style="text-align:center;">Bill No</th> 
										<th style="text-align:center;">Bill Date</th>
										<th style="text-align:center;">Type</th>
										<th style="text-align:center;">Vehicle/Machine</th>
										<th style="text-align:center;">Type</th> 
										<th style="text-align:center;">Taxable Amt</th> 
										<th style="text-align:center;">Disc Amt</th> 
										<th style="text-align:center;">Extra Amt</th> 
										<th style="text-align:center;">Tax Amt</th>
										<th style="text-align:center;">Total</th>
										 
									
								  </tr>
								</thead>
								
								 <tbody >
								  <tbody >
								  <c:set var="taxaleAmt"  value="${0 }"/>
								  <c:set var="discAmt"  value="${0 }"/>
								  <c:set var="extra"  value="${0 }"/> 
								  <c:set var="taxAmt"  value="${0 }"/> 
								<c:set var="total"  value="${0 }"/>
								  	<c:forEach items="${staticlist}" var="staticlist" varStatus="count">
								  	<c:choose>
								  		<c:when test="${staticlist.servType2==1}">
								  			<c:set var="type" value="Vehicle"></c:set>
								  		</c:when>
								  		<c:otherwise>
								  			<c:set var="type" value="Machine"></c:set>
								  		</c:otherwise>
								  	</c:choose>
								  	 
												<tr>
													<td><c:out value="${count.index+1}" /></td> 
													<td><c:out value="${staticlist.billNo}" /></td>
													<td><c:out value="${staticlist.billDate}" /></td>
													<td><c:out value="${type}" /></td>
													<td><c:out value="${staticlist.vehNo}" /></td>
													<c:forEach items="${mechTypeList}" var="mechTypeList"> 
														<c:choose>
															<c:when test="${mechTypeList.typeId==staticlist.typeId}">
																<td align="left" ><c:out value="${mechTypeList.typeName}" /></td>
															</c:when> 
														</c:choose>
													</c:forEach> 
													<td  style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.taxableAmt}"/></td>
													<c:set var="taxableAmt"  value="${taxableAmt+staticlist.taxableAmt }"/>
													<td  style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.totalDisc}"/> </td>
													<c:set var="discAmt"  value="${discAmt+staticlist.totalDisc }"/>
													<td  style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.totalExtra}"/> </td>
													<c:set var="extra"  value="${extra+staticlist.totalExtra }"/> 
													  <td  style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.taxAmt}"/> </td>
													<c:set var="taxAmt"  value="${taxAmt+staticlist.taxAmt }"/>
													<td  style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.total}"/>
													  </td> 
														<c:set var="total"  value="${total+staticlist.total }"/>
														 
												</tr>
												</c:forEach>
								  <tr>
								  <td colspan='6'><b>Total</b></td>
								  
								    <td style="text-align:right"><b><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${taxableAmt}"/></b></td>
								    <td style="text-align:right"><b><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${discAmt}"/></b></td>
								    <td style="text-align:right"><b><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${extra}"/></b></td> 
								    <td style="text-align:right"><b><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${taxAmt}"/></b></td>
								     <td  style="text-align:right"><b><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${total}"/></b></td>
								      <!--  <td><b>Total</b></td> -->
								  </tr>
							 </tbody>
								</table>

	
</body>
</html>