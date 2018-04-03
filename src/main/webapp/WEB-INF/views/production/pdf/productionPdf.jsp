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
<title>Prod From Order Report PDF</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->


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
	background-color: #EA3291;
	color: white;
}
</style>
</head>
<body onload="myFunction()">
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		id="table_grid" class="table table-bordered">
		<thead>
			<tr class="bgpink">
				<th width="18" style="width: 18px">Sr No</th>
				<th width="50">Item Id</th>
				<th width="100">Item Name</th>
				<th width="100">Cur Closing Qty</th>
				<th width="100">cur Opening Qty</th>
				<th width="100">Order Quantity</th>
			</tr>
		</thead>
		<tbody>
			
			<c:forEach items="${prodFromOrderReport}" var="report" varStatus="count">
				<tr>
					<td><c:out value="${count.index+1}" /></td>
					<td><c:out value="${report.itemId}" /></td>
					<td><c:out value="${report.itemName}" /></td>
					<td><c:out value="${report.curClosingQty}" /></td>
					<td><c:out value="${report.curOpeQty}" /></td>
					<td><c:out value="${report.qty}" /></td>
				</tr>
			</c:forEach>
			
		</tbody>
	</table>

	<!-- END Main Content -->

</body>
</html>