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
<title>Finish Good Stock</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<style type="text/css">
table {
	border-collapse: collapse;
	font-size: 14;
	width: 100%;
}

p {
	color: black;
	font-family: arial;
	font-size: 60%;
	margin-top: 0;
	padding: 0;
}

h6 {
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

	<table align="center" border="1" cellspacing="0" cellpadding="1"
		id="table_grid" class="table table-bordered">
		<thead>
			<!-- <tr class="bgpink">
				<th width="7%" align="center">Sr.No.</th>
				<th align="center">Item Name</th>
				<th width="20%" align="center">Closing Stock</th>
			</tr> -->
		</thead>
		<tbody>


			<c:forEach items="${subCatList}" var="subCatList">

				<tr>
					<td colspan="3"><b><c:out value="${subCatList.subCatName}" /></b></td>
				</tr>
				<tr class="bgpink">
					<th width="7%" align="center">Sr.No.</th>
					<th align="center">Item Name</th>
					<th width="20%" align="center">Closing Stock</th>
				</tr>
				<c:set value="0" var="sr"></c:set>
				<c:forEach items="${goodstocklistforpdf}" var="report">
					<c:forEach items="${itemList}" var="itemList">
						<c:if
							test="${itemList.id==report.itemId && report.catId==itemList.itemGrp1 && subCatList.subCatId==itemList.itemGrp2}">
							<tr>
								<td align="center"><c:out value="${sr+1}" /> <c:set
										value="${sr+1}" var="sr"></c:set></td>
								<td><c:out value="${report.itemName}" /></td>
								<td align="right"><c:out value="${report.totalCloStk}" /></td>
							</tr>
						</c:if>

					</c:forEach>
				</c:forEach>

			</c:forEach>
			<%-- <c:forEach items="${goodstocklistforpdf}" var="report">

				<tr>
					<td align="center"><c:out value="${count.index+1}" /></td>
					<td><c:out value="${report.itemName}" /></td>
					<td align="right"><c:out value="${report.totalCloStk}" /></td>
				</tr>

			</c:forEach> --%>
			<%-- <tr>

				<td width="100" colspan='5' align="right"><b>Total</b></td>
				<td width="100" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="0" minFractionDigits="0"
							value="${reqQtySum}" /></b></td>
				<td width="100" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${reqValSum}" /></b></td>
				<td width="100" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="0" minFractionDigits="0"
							value="${aprQtySum}" /></b></td>

				<td width="100" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${aprValSum}" /></b></td>
			</tr> --%>
		</tbody>
	</table>


	<!-- END Main Content -->

</body>
</html>