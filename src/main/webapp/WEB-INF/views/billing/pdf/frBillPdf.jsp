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
	<h3>Franchisee Bill</h3>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		id="table_grid" class="table table-bordered">
		<thead>
			<tr>
				<!-- <th>#</th>
				<th>Franchisee Name</th>
				<th>Menu Name</th>
				<th>Item id</th>
				<th>Item Name</th>
				<th>Order Quantity</th>
				<th>Bill Quantity</th>
														<th>Expiry Date</th>

				<th>Rate</th>
				<th>Total</th>  before table -->
				<th>Sr.No.</th>
				<th>Franchise Name</th>
				<th>Menu Name</th>
				<th>Item Name</th>
				<th>Order Qty</th>
				<th>Bill Qty</th>
				<th>Base Rate</th>
				<th>Amount</th>
				<th>Tax%</th>
				<th>SGST Rs</th>
				<th>CGST Rs</th>
				<th>IGST Rs</th>
				<th>Total</th>
			</tr>
		</thead>
		<tbody>

		</tbody>

		<c:forEach var="getBillList" items="${getBillList}" varStatus="count">

			<tr>
				<td><c:out value="${count.index+1}" /></td>

				<td align="center"><c:out value="${getBillList.frName}" /></td>

				<td align="center"><c:out value="${getBillList.menuTitle}" /></td>

				<td align="center"><c:out value="${getBillList.itemName}" /></td>
				<td align="center"><c:out value="${getBillList.orderQty}" /></td>

				<td align="center"><c:out value="${getBillList.orderQty}" /></td>

				<td align="center"><c:out value="${getBillList.orderRate}" /></td>
				<c:set var="oRate" value="${getBillList.orderRate}" />
				<c:set var="bQty" value="${getBillList.orderQty}" />

				<td align="center"><c:out value="${oRate*bQty}" /></td>

			</tr>

		</c:forEach>
	</table>

</body>
</html>