<%@page
contentType="text/html; charset=ISO8859_1"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<%@ page import="java.lang.Math"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Credit Note</title>
 
</head>
<body>
  	<c:forEach items="${crnPrint}" var="headerH" varStatus="count">
						<h4 align="center">Credit Note</h4>
    <h4 style="color:#000; font-size:16px; text-align:center; margin:0px;">Galdhar Foods Pvt.Ltd</h4>
   <p style="color:#000; font-size:10px; text-align:center;margin:0px;">Factory Add: A-32 Shendra, MIDC, Auraangabad-4331667 <br />
Phone:0240-2466217, Email: aurangabad@monginis.net</p>
<br></br> 
<table width="100%" border="0"  cellpadding="0" cellspacing="0" style="border-left:1px solid #313131;border-right:1px solid #313131;">
  <tr>
    <td width="54.8%" colspan="5" style="border-top:1px solid #313131;padding:8px;color:#FFF; font-size:14px;">
       <p style="color:#000; font-size:13px; text-align:left;margin:0px;">To, &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>${headerH.creditHeader.frName}</b>&nbsp;${headerH.creditHeader.frAddress}</p>
        <p style="color:#000; font-size:13px; text-align:;left;margin:0px;">GSTIIN: &nbsp;<b>${headerH.creditHeader.frGstNo}</b>&nbsp;&nbsp;&nbsp;&nbsp;<span> State:&nbsp;27 Maharashtra </span> </p>
    </td>

    <td width="50%" colspan="5" style="border-top:1px solid #313131;border-left:1px solid #313131; padding:8px;color:#FFF; font-size:15px;">
        <p style="color:#000; font-size:13px; text-align:;left;margin:0px;"> Credit Note No. : &nbsp;&nbsp;&nbsp;&nbsp;<b>${headerH.creditHeader.crnId}</b></p>
        <p style="color:#000; font-size:13px; text-align:left;margin:0px;">Date :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>${headerH.creditHeader.crnDate}</b></p>
    </td>
  </tr>
  <tr>
  <c:choose>
  <c:when test="${headerH.creditHeader.isGrn==1}">
    <c:set var="type" value="GRN"></c:set>
  </c:when>
  <c:otherwise>
        <c:set var="type" value="GVN"></c:set>
  </c:otherwise>
  </c:choose>
    <td width="58.9%" colspan="4" style="border-top:1px solid #313131;border-right:1px solid #313131;padding:7px;color:#FFF; font-size:15px;">
        <p style="color:#000; font-size:13px; text-align:;left;margin:0px;"><b>Being the amount Credited to your Account towards ${type} as &nbsp; &nbsp;</b></p>
    </td>
    
      </tr>
      </table>
      
      <table width="100%" border="0"  cellpadding="0" cellspacing="0" style="border-top:1px solid #313131;border-right:1px solid #313131">
      
  <tr>
    <td rowspan="2"  width="2%"  style="border-bottom:1px solid #313131; border-bottom:1px solid #313131;border-left:1px solid #313131; padding:5px;color:#000; font-size:10px;">No.</td>
    <td align="left" width="23%" rowspan="2" style="border-bottom:1px solid #313131; border-left:1px solid #313131; padding:15px;color:#000; font-size:10px;text-align: left">Item Decription</td>
   <td align="center" width="5%" rowspan="2" style="border-bottom:1px solid #313131; border-left:1px solid #313131; padding:0.2px;color:#000; font-size:10px;">HSN Code</td>
 
     
 <td align="center" width="5%" rowspan="2" style=" border-bottom:1px solid #313131; border-left:1px solid #313131; padding:10px;color:#000; font-size:10px;">Qty</td>
    <td align="center" width="5%" rowspan="2" style="border-bottom:1px solid #313131; border-left:1px solid #313131; padding:10px;color:#000; font-size:10px;">UOM </td>
    <td align="center" width="5.3%" rowspan="2" style="border-bottom:1px solid #313131; border-left:1px solid #313131; padding:10px;color:#000; font-size:10px;">Rate</td>
    <td align="center" width="5%" rowspan="2" style="border-bottom:1px solid #313131; border-left:1px solid #313131; padding:10px;color:#000; font-size:10px;">Value</td>
    <td align="center" width="10%" colspan="2" style="border-left:1px solid #313131; padding:10px;color:#000; font-size:10px; text-align:center;"> CGST</td>
    <td align="center" width="10%" colspan="2" style="border-left:1px solid #313131; padding:10px;color:#000; font-size:10px;text-align:center;">SGST</td>
  </tr>
  <tr>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:10px;">Rate% </td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131;  padding:4px;color:#000; font-size:10px;">Amount</td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:10px;">Rate%</td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:10px;">Amount</td>
  </tr>
 
  <c:set var = "totalQty" value = "0"/>
   <c:set var = "totalAmt" value = "0"/>
    <c:set var = "totalCgst" value = "0"/>
        <c:set var = "totalSgst" value = "0"/>
      
       <c:forEach items="${headerH.creditHeader.srNoDateList}" var="srNos">
       <tr>
           <td  style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:0px;">-</td>
       
          <td style="border-left:1px solid #313131;  padding:3px 5px;color:#000; font-size:10px;">Ref No&nbsp; &nbsp;<b><c:out value="${srNos.srNo}"></c:out></b>&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;<c:out value="${srNos.grnGvnDate}"></c:out></td>
    
        <td style="border-left:1px solid #313131;  padding:3px 5px;color:#000; font-size:0px;">-</td>
    
    <td align="left" style="border-left:1px solid #313131;  padding:3px 5px;color:#000; font-size:0px;">-</td>
        <td align="center" style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:0px;">-</td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000;font-size:0px;">-</td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 4px;color:#000; font-size:0px;">-</td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 4px;color:#000;font-size:0px;">-</td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:0px;">-</td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:0px;">-</td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000;font-size:0px;">-</td>
  </tr>
        <c:forEach items="${headerH.creditHeader.crnDetails}" var="crnDetail" varStatus="count1">
  
   <c:choose>
    <c:when test="${srNos.srNo eq crnDetail.grngvnSrno}">
    <tr>
 
    <td  style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:10px;">${count1.index+1}</td>
  
    
        <td style="border-left:1px solid #313131;  padding:3px 5px;color:#000; font-size:10px;"><c:out value="${crnDetail.itemName}"></c:out></td>
    
        <td style="border-left:1px solid #313131;  padding:3px 5px;color:#000; font-size:10px;"><c:out value="${crnDetail.itemHsncd}"></c:out></td>
    
    <td align="left" style="border-left:1px solid #313131;  padding:3px 5px;color:#000; font-size:10px;">${crnDetail.grnGvnQty}</td>
        <td align="center" style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:10px;">${crnDetail.itemUom}</td>
    
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000;font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${crnDetail.baseRate}"/></td>
			  <c:set var = "totalQty" value = "${totalQty+crnDetail.grnGvnQty}"/>					
    <td align="right" style="border-left:1px solid #313131; padding:3px 4px;color:#000; font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${crnDetail.taxableAmt}"/></td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 4px;color:#000;font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${crnDetail.cgstPer}"/></td>
								   <c:set var = "totalAmt" value = "${totalAmt+crnDetail.taxableAmt}"/>
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${crnDetail.cgstRs}"/></td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${crnDetail.sgstPer}"/></td>
								  <c:set var = "totalCgst" value = "${totalCgst+crnDetail.cgstRs}"/>
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000;font-size:10px;"><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${crnDetail.sgstRs}"/></td>
								
								 <c:set var = "totalSgst" value = "${totalSgst+crnDetail.sgstRs}"/>
   
  </tr>
    </c:when>
  <%--   <c:otherwise>
      <tr>
           <td  style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:0px;">-</td>
       
          <td style="border-left:1px solid #313131;  padding:3px 5px;color:#000; font-size:10px;"><c:out value="${srNo}"></c:out></td>
    
        <td style="border-left:1px solid #313131;  padding:3px 5px;color:#000; font-size:0px;">-</td>
    
    <td align="left" style="border-left:1px solid #313131;  padding:3px 5px;color:#000; font-size:0px;">-</td>
        <td align="center" style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:0px;">-</td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000;font-size:0px;">-</td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 4px;color:#000; font-size:0px;">-</td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 4px;color:#000;font-size:0px;">-</td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:0px;">-</td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000; font-size:0px;">-</td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 5px;color:#000;font-size:0px;">-</td>
    </tr>
    </c:otherwise> --%>
    </c:choose> 
    
  </c:forEach>
  </c:forEach>
   <tr>
    <td  align="left" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:white; font-size:10px;">-</td>
    <td align="left" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:12px;"><b>Total</b></td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:white; font-size:10px;">-</td>
    <td align="right" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:12px;"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${totalQty}"/></b></td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:white; font-size:10px;">-</td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:white; font-size:10px;">-</td>
    <td align="right" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:12px;"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${totalAmt}"/></b></td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:white; font-size:10px;">-</td>
    <td align="right" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:12px;"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${totalCgst}"/></b></td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:white; font-size:10px;">-</td>
    <td align="right" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:12px;"><b><fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2" value="${totalSgst}"/></b></td>
  </tr>
   <tr>
   
    <td align="right" style="border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:white; font-size:10px;">-</td>
        <td align="right" style="border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:white; font-size:10px;">-</td>
    <td style="border-bottom:1px solid #313131; font-size:0px;">-</td><td style="border-bottom:1px solid #313131; font-size:10px;">-</td><td style="border-bottom:1px solid #313131;font-size:0px;">-</td><td style="border-bottom:1px solid #313131;padding:4px;color:#000; font-size:0px;">-</td><td style="border-bottom:1px solid #313131;font-size:0px;">-</td><td style="border-bottom:1px solid #313131;padding:4px;color:#000; font-size:0px;">-</td><td style="border-bottom:1px solid #313131;font-size:0px;">-</td><td style="border-bottom:1px solid #313131;font-size:12px;"><b>Grand Total:</b></td>
    <td align="right" style="border-left:1px solid #313131;border-bottom:0px solid #313131; padding:4px;color:#000; font-size:12px;"><b><fmt:formatNumber type="number"
								maxFractionDigits="0" value="${totalAmt+totalCgst+totalSgst}"/></b></td>
  </tr>
</table>

  <table width="100%" border="0"  cellpadding="0" cellspacing="0" style="border-top:1px solid #313131;border-right:1px solid #313131;">
  
  <tr>
    <td colspan="6" width="50%" style="border-left:1px solid #313131; padding:8px;color:#000; font-size:12px;">
     <p style="color:#000; font-size:12px; text-align:left;margin:0px;">Narration<br>Being ${type} For the period of the dated <%-- ${headerH.creditHeader.fromDate} to ${headerH.creditHeader.toDate} --%></p>
</td>
    <td colspan="5" width="38%" rowspan="2" style="border-left:1px solid #313131; padding:8px;color:#000;font-size:15px;">&nbsp;</td>
  </tr>
  
    <tr>
    <td colspan="6" width="50%"  style="border-top:1px solid #313131;border-left:1px solid #313131; padding:8px;color:#000; font-size:15px;">&nbsp;</td>
  </tr>
  
    
  <tr>
    <td colspan="6" width="50%"  style="border-top:1px solid #313131;border-left:1px solid #313131; padding:8px;color:#000; font-size:15px;">&nbsp;

</td>
    <td colspan="5" width="38%" style="border-top:1px solid #313131;border-left:1px solid #313131; padding:8px;color:#000;font-size:15px;">     
    <p style="color:#000; font-size:11px; text-align:left;margin:0px;">Continue...</p></td>
  </tr>
  
  <tr>
    <td colspan="6"  width="50%" style="border-bottom:1px solid #313131;border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000; font-size:11px;">
     <p style="color:#000; font-size:11px; text-align:left;margin:0px;">Subject to Aurangabad Jurisdiction &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Receiver's Signature</p>
</td>
    <td  align="center" colspan="5" width="38%" style="border-bottom:1px solid #313131;border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000;font-size:11px;">Authorised Signature</td>
  </tr>
  
</table>

<div style="page-break-after: always;"></div>
  </c:forEach>

</body>
</html>