<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard - MONGINIS Admin</title>
</head>
<body>

	<c:forEach items="${regularSpCkOrdersList}" var="regularSpCkOrders"
														varStatus="count">
														
<table width="100%" style="font-family:arial; font-size:12px; border:1px solid #000000; margin-bottom:40px;">
  <tr >
    <td colspan="3" style="font-size:20px; border-bottom:1px solid #000000; padding:8px 7px;" align="center" >AURANGABAD MONGINIS</td>
  </tr>
  <tr >
  
    <td width="15%"  style="border-bottom:1px solid #000000; padding:8px 20px; ">${from}</td>
    <td width="45%"  style="font-family:arial; font-size:16px; border-left: 1px solid  #000000;border-bottom:1px solid #000000; padding:8px 20px;font-weight:bold; align=center;">${regularSpCkOrders.frName}</td>
    <td width="40%"  style="border-bottom:1px solid #000000; padding:8px; border-left: 1px solid  #000000;">${regularSpCkOrders.orderDate}</td>
  </tr>
  <tr>
    <td width="15%" style="font-size:12px; border-bottom:1px solid #000000; padding:1px 7px;font-weight:bold;">Sp,Cake Code / Name</td>
    <td colspan="2" width="50%" style="font-family:arial; font-size:18px;border-left: 1px solid  #000000; border-bottom:1px solid #000000;padding:1px 7px; font-weight:bold;">${regularSpCkOrders.itemName} </td>
  </tr>
  <tr>
    <td style="border-bottom:1px solid #000000; padding:5px 7px;font-weight:bold;">Quantity</td>
    <td style="font-size:14px; border-bottom:1px solid #000000;border-left: 1px solid  #000000; padding:5px 7px;font-weight:bold;">${regularSpCkOrders.qty}</td>
    <td style="font-size:14px; border-bottom:1px solid #000000; padding:5px 7px;border-left: 1px solid  #000000; font-weight:bold;"></td>  
  </tr>
 <%--  <tr>
    <td style="font-size:12px; border-bottom:1px solid #000000; padding:10px 7px;">Special Instructions</td>
    <td colspan="2" style="font-family:arial; font-size:16px; border-left: 1px solid  #000000;border-bottom:1px solid #000000; padding:5px 7px;font-weight:bold;">${regularSpCkOrders.spInstructions}</td>
  </tr> --%>
 <!--  <tr>
    <td width="15%" style="font-size:12px; border-bottom:1px solid #000000; padding:10px 7px;" >Message on Cake</td>
    <td colspan="2" style=" font-size:18px; border-bottom:1px solid #000000;border-left: 1px solid  #000000; padding:5px 7px;font-weight:bold;" >Happy Birthday Anushka</td>
  </tr> -->
  <tr>
    <td  style=" font-size:14px;padding:5px 7px;font-weight:bold;">Date of Delivery </td>
    <td style=" font-size:18px;padding:5px 7px;font-weight:bold;border-left: 1px solid  #000000">${regularSpCkOrders.rspDeliveryDt}</td>
    <td  style=" font-size:18px;padding:5px 7px;font-weight:bold;border-left: 1px solid  #000000">Place of Delivery-- ${regularSpCkOrders.rspPlace}</td>
  </tr>
</table>
<c:set var="from" value="${from+1}"/>
</c:forEach>
</body>
</html>