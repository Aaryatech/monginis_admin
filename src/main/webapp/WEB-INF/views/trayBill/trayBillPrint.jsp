<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tray Invoice</title>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>
    </title>
    </head>
<style type="text/css">
<!--
.style2 {font-size: 14px}
.style5 {font-size: 10px}
.style6 {font-size: 9px}
.style7 {
	font-size: 12px;
	font-weight: bold;
}
.style8 {
	font-size: 11px;
	font-weight: bold;
}
-->
</style>
</head>

</head>
<body>
	 <c:forEach items="${billsList}" var="billList" varStatus="count"> 
	<table width="250" border="0" cellspacing="0" cellpadding="0" style="padding:5px; font-family:verdana; font-size:12px; border:1px solid #E7E7E7;">

  <tbody> 
  <tr>
  <td align="right" style="padding:0px;"></td>  </tr><tr>
      <td colspan="2" align="center" style="padding:1px;"><p>TRAY BILL</p></td> 
    </tr>
    <tr>
      <td colspan="2" align="center" style="padding:2px; border-bottom:1px solid #E7E7E7;"><p class="style2" ><b>${billList.frName}</b><br /></p>
       </td>
    </tr>
   
    <tr>
      <td colspan="2">
      <table width="100%" border="0" cellspacing="0" cellpadding="7">
  <tbody>
    <tr>
      <td  align="left">Invoice No: &nbsp;&nbsp;${billList.tranDetailId} </td>
  
      <td >Date: &nbsp;${billList.outtrayDate}</td>
        
    <%--   <td >${date} </td> --%>
    </tr >
    
    <tr>
      <td colspan="4"><table width="100%" border="0" cellspacing="0" cellpadding="5" class="tbl-inner">
        <tbody>
          <tr>
            <th width="43%" align="left" bgcolor="#ECECEC">Tray Type</th>
            <th width="8%" bgcolor="#ECECEC">Qty</th>
            <th width="13%" bgcolor="#ECECEC">Rate</th>
            <th width="29%"  bgcolor="#ECECEC">Amt</th>
          </tr>
          <%-- <c:forEach items="${billList}" var="billList" varStatus="count"> --%>
          <c:choose>
          <c:when test="${billList.qtySmall>0}">
          <tr>
            <td><p style="font-size:12px"></p>
				<p style="font-size:10px">Small Tray</p></td>
            <td align="center"><p style="font-size:12px">${billList.qtySmall}</p></td>
            <td align="center"><p style="font-size:12px">${billList.priceSmall}</p></td>
            <td style="text-align: center;"><p style="font-size:12px">${billList.qtySmall*billList.priceSmall}</p></td> 
            <c:set var="smallPrice" value="${billList.qtySmall*billList.priceSmall}"/>
          </tr>
         </c:when>
         <c:when test="${billList.qtyBig>0}">
           <tr>
            <td><p style="font-size:12px"></p>
				<p style="font-size:10px">Big Tray</p></td>
            <td align="center"><p style="font-size:12px">${billList.qtyBig}</p></td>
            <td align="center"><p style="font-size:12px">${billList.priceBig}</p></td>
            <td style="text-align: center;"><p style="font-size:12px">${billList.qtyBig*billList.priceBig}</p></td> 
              <c:set var="bigPrice" value="${billList.qtyBig*billList.priceBig}"/>
          </tr>
          </c:when>
             <c:when test="${billList.qtyLead>0}">
           <tr>
            <td><p style="font-size:12px"></p>
				<p style="font-size:10px">Lead Tray</p></td>
            <td align="center"><p style="font-size:12px">${billList.qtyLead}</p></td>
            <td align="center"><p style="font-size:12px">${billList.priceLead}</p></td>
            <td style="text-align: center;"><p style="font-size:12px">${billList.qtyLead*billList.priceLead}</p></td> 
             <c:set var="leadPrice" value="${billList.qtyLead*billList.priceLead}"/>
          </tr>
          </c:when>
           <c:when test="${billList.qtyExtra>0}">
           <tr>
            <td><p style="font-size:12px"></p>
				<p style="font-size:10px">Extra Tray</p></td>
            <td align="center"><p style="font-size:12px">${billList.qtyExtra}</p></td>
            <td align="center"><p style="font-size:12px">${billList.priceExtra}</p></td>
            <td style="text-align: center;"><p style="font-size:12px">${billList.qtyExtra*billList.priceExtra}</p></td> 
             <c:set var="extraPrice" value="${billList.qtyExtra*billList.priceExtra}"/>
          </tr>
          </c:when>
          </c:choose>
        <%--   </c:forEach> --%>
          <tr>
            
           <td colspan="2" ><span class="style5"><strong>Tax(%):</strong></span></td>
            <td align="right"><span class="style5"><strong>${((smallPrice+bigPrice+leadPrice+extraPrice)*billList.gstPer)/100}</strong></span></td> 
                     <c:set var="taxAmt" value="${((smallPrice+bigPrice+leadPrice+extraPrice)*billList.gstPer)/100}"/>
         
          </tr>
            <tr>
          
             <c:set var="totalAmt" value="${smallPrice+bigPrice+leadPrice+extraPrice}"/>
            <td colspan="2" ><span class="style5"><strong>Total :</strong></span></td>
            <td align="right"><span class="style5"><strong>${totalAmt}</strong></span></td> 
          </tr>
         
          <tr>
             <td colspan="2" style="text-align: left;"><strong>Bill Total:</strong></td>
            <td style="text-align: right;"><strong>${totalAmt+taxAmt}</strong></td> 
          </tr>
        </tbody>
      </table></td>
      </tr>
  </tbody>
</table>      </td>
    </tr>
    
    <tr>
      <td width="200" align="center" style="border-top:1px solid #E7E7E7; padding:5px 7px;"><strong>For ${billList.frName}</strong></td>
    </tr>
  </tbody>
</table>
<!-- <div style="page-break-after: always;"></div>
 --> </c:forEach>  
</body>	

<body onload="directPrint()">
	<script>
	 function directPrint()
	{
		 
		window.print();
		//window.close();
	} 
	
	</script>
</body>
</html>