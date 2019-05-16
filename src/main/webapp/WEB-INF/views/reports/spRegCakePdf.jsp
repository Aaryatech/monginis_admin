<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dashboard - MONGINIS Admin</title>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title></title>
<link rel="stylesheet" type="text/css"
	href="//cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.12.0/semantic.min.css" />
</head>
<body>
	<!-- code goes here -->

	<!-- scripts -->
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js">
		
	</script>
	<script type="text/javascript"
		src="//cdn.rawgit.com/MrRio/jsPDF/master/dist/jspdf.min.js">
		
	</script>
	<script type="text/javascript"
		src="//cdn.rawgit.com/niklasvh/html2canvas/0.5.0-alpha2/dist/html2canvas.min.js">
		
	</script>

	<script type="text/javascript"
		src="<c:url value='/resources/js/app.js'/>"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js"></script>


	<c:forEach items="${spCakeOrder}" var="spCakeOrder" varStatus="count">

		<table width="100%"
			style="font-family: arial; font-size: 12px; border: 1px solid #000000; margin-bottom: 40px;">
			<tr bgcolor=lightgrey>
				<td colspan="3"
					style="font-size: 20px; border-bottom: 1px solid #000000; padding: 8px 7px;"
					align="center">AURANGABAD MONGINIS</td>
			</tr>
			<tr>

				<td width="15%"
					style="border-bottom: 1px solid #000000; padding: 8px 20px;">${spCakeOrder.srNo}</td>
				<td width="45%"
					style="font-family: arial; font-size: 16px; border-left: 1px solid #000000; border-bottom: 1px solid #000000; padding: 8px 20px; font-weight: bold;"
					align="center">${spCakeOrder.frName}</td>
				<td width="40%"
					style="border-bottom: 1px solid #000000; padding: 8px; border-left: 1px solid #000000;">${spCakeOrder.prodDate}</td>
			</tr>
			<tr>
				<td width="15%"
					style="font-size: 12px; border-bottom: 1px solid #000000; padding: 1px 7px; font-weight: bold;">Sp,Cake
					Code / Name</td>
				<td
					style="font-family: arial; font-size: 18px; border-left: 1px solid #000000; border-bottom: 1px solid #000000; padding: 1px 7px; font-weight: bold;">${spCakeOrder.itemName}--${spCakeOrder.frCode}
				</td>

				<td
					style="font-size: 16px; padding: 5px 7px; font-weight: bold; border-left: 1px solid #000000; border-bottom: 1px solid #000000;">Weight
					-- ${spCakeOrder.inputKgProd} kg</td>



			</tr>

			<!--  <tr>
    <td width="15%" style="font-size:12px; border-bottom:1px solid #000000; padding:10px 7px;" >Message on Cake</td>
    <td colspan="2" style=" font-size:18px; border-bottom:1px solid #000000;border-left: 1px solid  #000000; padding:5px 7px;font-weight:bold;" >Happy Birthday Anushka</td>
  </tr> -->
			<tr>
				<td
					style="font-size: 12px; padding: 5px 7px; font-weight: bold; border-bottom: 1px solid #000000;">Date
					of Delivery</td>
				<td
					style="font-size: 16px; padding: 5px 7px; font-weight: bold; border-left: 1px solid #000000; border-bottom: 1px solid #000000;">${spCakeOrder.rspDeliveryDt}</td>
				<td
					style="font-size: 16px; padding: 5px 7px; font-weight: bold; border-left: 1px solid #000000; border-bottom: 1px solid #000000;">Place
					of Delivery-- ${spCakeOrder.rspPlace}</td>
			</tr>


			<tr>
				<td
					style="font-size: 12px; padding: 5px 7px; font-weight: bold; border-left: 1px solid #000000; border-bottom: 1px solid #000000;">Photo</td>
				<td
					style="text-align: left; font-size: 16px; font-weight: bold; border-left: 1px solid #000000; border-bottom: 1px solid #000000;">Photo
					1 :<img src="${imgUrl2}${spCakeOrder.photo1}" height="60"
					width="60" style="border: medium;" alt="NO IMAGE">
				</td>

				<td
					style="text-align: left; font-size: 16px; font-weight: bold; border-left: 1px solid #000000; border-bottom: 1px solid #000000;">Photo
					2 :<img src="${imgUrl}${spCakeOrder.photo2}" height="60" width="60"
					alt="NO IMAGE">
				</td>
			</tr>
		</table>

	</c:forEach>
	<!-- scripts -->
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script type="text/javascript"
		src="//cdn.rawgit.com/niklasvh/html2canvas/0.5.0-alpha2/dist/html2canvas.min.js"></script>
	<script type="text/javascript"
		src="//cdn.rawgit.com/MrRio/jsPDF/master/dist/jspdf.min.js"></script>
	<script type="text/javascript" src="app.js"></script>
</body>
</html>