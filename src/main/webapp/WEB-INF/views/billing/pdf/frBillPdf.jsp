<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Franchisee Bill</title>


<style type="text/css">
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: left;
	padding: 2px;
	font-size: 10;
}

tr:nth-child(even) {
	background-color: #f2f2f2
}

th {
	background-color: blue;
	color: white;
}


</style>
</head>
<body>
	<c:forEach items="${billDetails}" var="frDetails" varStatus="count">
		<h2>Franchisee Bill</h2>
		
	<h2>Mode Of Transport: ${transportMode}</h2>
	<h2>Vehicle No : ${vehicleNo}</h2>
	
	<h4>FR Name:${frDetails.frName}</h4>
													<h4>Invoice No:${frDetails.invoiceNo}</h4> 
													<h4>Address:s${frDetails.frAddress}</h4>
													 
													
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		id="table_grid" class="table table-bordered">
			
		<thead>
			 									
													<tr>
													<th width="140" style="width: 30px" align="left">Sr No</th>
													<th width="138" align="left">Item Name</th>
													<th width="120" align="left">Group</th>
													<th width="130" align="right">Billed Qty</th>
													<th width="100" align="left">MRP</th>
													<th width="100" align="left">Rate</th>
													<th width="140" align="left">Taxable Amt</th>
													<th width="105" align="left">GST %</th>
													<th width="105" align="left">Total Tax</th>
													<th width="130" align="left">Grand Total</th>
												</tr>
											</thead>
											<tbody>

												

													<%-- <tr>
													
													<td align="left"><c:out
																value="${frDetails.frName}" /></td>


														<td align="left"><c:out
																value="${frDetails.invoiceNo}" /></td>
																
																<td align="left"><c:out
																value="${frDetails.frAddress}" /></td>
																
																</tr> --%>
									<c:forEach items="${frDetails.billDetailsList}" var="billDetails">
									<tr>
														<td><c:out value="${count.index+1}" /></td>

														<td align="left"><c:out
																value="${billDetails.itemName}" /></td>


														<td align="left"><c:out
																value="${billDetails.catName}" /></td>

														
														<td align="center"><c:out value="${billDetails.billQty}" /></td>

														<td align="left"><c:out value="${billDetails.mrp}" /></td>

														<td align="left"><c:out value="${billDetails.rate}" /></td>
														<td align="left"><c:out
																value="${billDetails.taxableAmt}" /></td>
																
														<c:set var="sgstPer" value="${billDetails.sgstPer}" />
														<c:set var="cgstPer" value="${billDetails.cgstPer}" />

														<td align="left"><c:out value="${sgstPer + cgstPer}" /></td>

														<td align="left"><c:out
																value="${billDetails.totalTax}" /></td>
														<td align="center"><c:out
																value="${billDetails.grandTotal}" /></td>
														<!-- Total -->


														<!-- <td rowspan="1" align="left"> <input
																type="button" value="View"> <input type="button"
																value="Edit"> <input type="button"
																value="Cancel"></td> -->


														<!-- <td align="left"><label><input type="submit"
																	name="submit_button" id="submit_button"></label></td>  -->


													</tr>
													<div class="page-break">this content may page-break if content above this </div>
												</c:forEach>
<!-- </tr>
 -->												
<%--  </c:forEach>
 --%>
											</tbody>
										</table>
									
								</c:forEach>

</body>
</html>