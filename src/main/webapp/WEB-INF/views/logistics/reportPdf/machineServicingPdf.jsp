<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Machine Servicing</title>

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
<div align="center"> <h5> Machine Servicing &nbsp;&nbsp;&nbsp;&nbsp; From &nbsp; ${fromDate}  &nbsp;To &nbsp; ${toDate}</h5></div>
	<table  align="center" border="1" cellspacing="0" cellpadding="1" 
		id="table_grid" class="table table-bordered">
	
								<thead >
									<tr class="bgpink">
									
									<th style="text-align:center;height: 25px">Sr.No.</th> 
										<th style="text-align:center;">Machine Name</th>
										<th style="text-align:center;">Dealer Name</th>  
										<th style="text-align:center;">Person Name</th> 
										<th style="text-align:center;">Servicing Date</th>
										<th style="text-align:center;">Next Date Date</th>
										<th style="text-align:center;">Alert Date Date</th>
										<th style="text-align:center;">Status</th>  
								  </tr>
								</thead>
								
								 <tbody >
								  <tbody >
								 
								  	<c:forEach items="${staticlist}" var="staticlist" varStatus="count">
								  	<c:choose>
								  		<c:when test="${staticlist.isApproved==0}">
								  		<c:set var="approved" value="Pending"></c:set>
								  		</c:when>
								  		<c:otherwise>
								  		<c:set var="approved" value="Approved"></c:set>
								  		</c:otherwise>
								  	</c:choose>
												<tr>
													<td ><c:out value="${count.index+1}" /></td>
													 
													<td><c:out value="${staticlist.machineName}" /></td> 
													<td><c:out value="${staticlist.dealerName}" /></td> 
													<td ><c:out value="${staticlist.prsnName}" /></td> 
													<td ><c:out value="${staticlist.date}" /></td>
													<td ><c:out value="${staticlist.nextServDate}" /></td>
													<td ><c:out value="${staticlist.alertServDate}" /></td>
													<td ><c:out value="${approved}" /></td>
													 
													
												</tr>
												</c:forEach>
								  
							 </tbody>
								</table>

	
</body>
</html>