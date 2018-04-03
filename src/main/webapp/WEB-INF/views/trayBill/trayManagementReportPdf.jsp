<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tray Management Report</title>

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

<div align="center"> <h5>Tray Management Report&nbsp;&nbsp;&nbsp;&nbsp; From &nbsp; ${fromDate}  &nbsp;To &nbsp; ${toDate}</h5></div>

<table  align="center" border="1" cellspacing="0" cellpadding="1" 
		id="table_grid" class="table table-bordered">
								<thead >
									<tr class="bgpink">
									<th style="text-align:center;height: 25px">Sr.No.</th>
										<th style="text-align:center;">Date</th> 
										<th style="text-align:center;">Route</th>
										<th style="text-align:center;">Vehicle No</th>
										<th style="text-align:center;">Driver Name</th>
										<th style="text-align:center;">Running Km</th>
										<th style="text-align:center;">Diesel</th>
										<th style="text-align:center;">AVG</th>
									
								  </tr>
								</thead>
								
								 <tbody >
								  <tbody >
								  <c:set var="basicValue"  value="${0 }"/>
								  <c:set var="discAmt"  value="${0 }"/>
								  <c:set var="other"  value="${0 }"/>
								  <c:set var="freightAmt"  value="${0 }"/>
								  <c:set var="insuranceAmt"  value="${0 }"/>
								<c:set var="igst"  value="${0 }"/>
								<c:set var="cgst"  value="${0 }"/>
								<c:set var="sgst"  value="${0 }"/>
								<c:set var="cess"  value="${0 }"/>
								<c:set var="billAmount"  value="${0 }"/>
								  	<c:forEach items="${staticlist}" var="staticlist" varStatus="count">
												<tr>
													<td><c:out value="${count.index+1}" /></td>
													 
													<td><c:out value="${staticlist.tranDate}" /></td>
													<td><c:out value="${staticlist.routeName}" /></td> 
													<td><c:out value="${staticlist.vehNo}" /></td> 
													<td><c:out value="${staticlist.driverName}" /></td> 
													<td  style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.vehRunningKm}"/></td> 
													<td  style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${staticlist.diesel}"/></td> 
													<c:choose>
														<c:when test="${staticlist.diesel==0}"> 
															<c:set var="avg" value="0"></c:set>
														</c:when>
														<c:otherwise>
															<c:set var="avg" value="${staticlist.vehRunningKm/staticlist.diesel}"></c:set>
														</c:otherwise>
													</c:choose>  
													<td  style="text-align:right"><fmt:formatNumber type = "number"  maxFractionDigits = "2" minFractionDigits="2" value = "${avg}"/>
													  </td> 
													 
														 
													
												</tr>
												</c:forEach>
								 
							 </tbody>
								</table>

	
</body>
</html>