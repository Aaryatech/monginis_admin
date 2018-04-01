<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bill wise Purchase Report</title>

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
<div align="center"> <h5> Finished Good Stock Adjustment & Overview </h5></div>
<div align="center"> <h5> Date : ${sDate}</h5></div>
	<table  align="center" border="1" cellspacing="0" cellpadding="1" 
		id="table_grid" class="table table-bordered">
	
								<thead >
									<tr class="bgpink">
									
									<th style="text-align:center;height: 25px">Sr.No.</th>
										<th style="text-align:center;">Item Name</th>
										<th style="text-align:center;">T1</th>
										<th style="text-align:center;">T2</th> 
										<th style="text-align:center;">T2</th>   
								  </tr>
								</thead>
								
								 <tbody >
								  <tbody >
								 
								  	<c:forEach items="${staticlist}" var="staticlist" varStatus="count">
												<tr>
													<td ><c:out value="${count.index+1}" /></td>
													 
													<td><c:out value="${staticlist.itemName}" /></td> 
													<td style="text-align:right"><c:out value="${staticlist.opT1}" /></td> 
													<td style="text-align:right"><c:out value="${staticlist.opT2}" /></td> 
													<td style="text-align:right"><c:out value="${staticlist.opT3}" /></td>
													
													 
													
												</tr>
												</c:forEach>
								 
							 </tbody>
								</table>

	
</body>
</html>