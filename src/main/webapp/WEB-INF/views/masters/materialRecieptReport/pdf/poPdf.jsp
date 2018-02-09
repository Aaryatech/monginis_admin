

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Purchase Order</title>

</head>
<body>
 
  	 
<table width="100%" border="0"  cellpadding="0" cellspacing="0" style="border-left:1px solid #313131;border-right:1px solid #313131;border-top:1px solid #313131;">
  <tr>
    <td colspan="2" width="20%" style=" padding:10px;color:#FFF; font-size:15px;">&nbsp;</td>
    <td width="60%" colspan="6" style="border-left:1px solid #313131; padding:5px;color:#000; font-size:15px; text-align:center">
    <h4 style="color:#000; font-size:16px; text-align:center; margin:0px;">Galdhar Foods Pvt.Ltd</h4>
   <p style="color:#000; font-size:10px; text-align:center;margin:0px;">Factory Add: A-32 Shendra, MIDC, Auraangabad-4331667  <br>
  Phone:0240-2466217, Email: aurangabad@monginis.net </p>
  <p style="color:#000; font-size:10px; text-align:center;margin:0px;">GSTIIN: <b>27AHIPJ7279D1Z3</b></p>
 </td>
    <td colspan="3" width="20%" style="border-left:1px solid #313131; padding:10px;color:#FFF; font-size:15px;">
   	<p style="color:#000; font-size:11px; text-align:center;margin:0px;"> Original for buyer <br />
duplicate for tranpoter</p> 
    </td>
    
  </tr>

 
  <tr>
    <td width="50%" colspan="6" style="border-top:1px solid #313131;padding:10px;color:#FFF; font-size:14px;">
        <p style="color:#000; font-size:13px; text-align:;left;margin:0px;">To,</p> 
        <p style="color:#000; font-size:13px; text-align:left;margin:0px;"> <b>${purchaseOrderHeaderPdf.suppName}</b></p>
        <p style="color:#000; font-size:13px; text-align:left;margin:0px;"> ${purchaseOrderHeaderPdf.suppAddr} </p>
        <p style="color:#000; font-size:13px; text-align:left;margin:0px;">${purchaseOrderHeaderPdf.suppState}</p>
        <p style="color:#000; font-size:13px; text-align:left;margin:0px;">GSTIIN: <b>${purchaseOrderHeaderPdf.suppGstin}</b></p>
    </td>

    <td width="50%" colspan="5" style="border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#FFF; font-size:15px;">
        <p style="color:#000; font-size:13px; text-align:;left;margin:0px;"> Po No. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>${purchaseOrderHeaderPdf.poNo}</b>  </p>
        <p style="color:#000; font-size:13px; text-align:left;margin:0px;">Po Date   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:formatDate pattern = "dd-MM-yyyy" value = "${purchaseOrderHeaderPdf.poDate}" /></b></p>
        <p style="color:#000; font-size:13px; text-align:left;margin:0px;">Delivery AT &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>${purchaseOrderHeaderPdf.delvAtRem} </b></p>
        <p style="color:#000; font-size:13px; text-align:left;margin:0px;">Delivery Date &nbsp;&nbsp; <b><fmt:formatDate pattern = "dd-MM-yyyy" value = "${purchaseOrderHeaderPdf.delvDateRem}" /></b></p>
        <p style="color:#000; font-size:13px; text-align:left;margin:0px;">Transporter &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b> ${purchaseOrderHeaderPdf.tranName}</b></p>
    </td>
  </tr>
  
  <tr>
    <td width="50%" colspan="6" style="border-top:1px solid #313131;padding:10px;color:#FFF; font-size:14px;">
        <p style="color:#000; font-size:13px; text-align:;left;margin:0px;"> Kind Attention : <b> ${purchaseOrderHeaderPdf.kindAttn}</b></p>
    </td>
     <td width="0%" colspan="6" style="border-top:1px solid #313131;padding:10px;color:#FFF; font-size:14px;">
        <p style="color:#000; font-size:13px; text-align:;left;margin:0px;"></p>
    </td>
      </tr>
      </table>
      
      <table width="100%" border="0"  cellpadding="0" cellspacing="0" style="border-top:1px solid #313131;border-right:1px solid #313131">
  <tr>
    <td   width="2%"  style="border-bottom:1px solid #313131; border-bottom:1px solid #313131;border-left:1px solid #313131; padding:5px;color:#000; font-size:10px;">Sr.</td>
    <td align="left" width="50%"  style="border-bottom:1px solid #313131; border-left:1px solid #313131; padding:15px;color:#000; font-size:10px;">Item Decription</td>
   <td align="center" width="8%"  style="border-bottom:1px solid #313131; border-left:1px solid #313131; padding:0.2px;color:#000; font-size:10px;">HSN Code</td>
 <td align="center" width="8%"   style="border-bottom:1px solid #313131; border-left:1px solid #313131; padding:0.2px;color:#000; font-size:10px;">Unit</td>
  
 <td align="center" width="10%"   style=" border-bottom:1px solid #313131; border-left:1px solid #313131; padding:10px;color:#000; font-size:10px;">Qty</td> 
    <td align="center" width="10%"   style="border-bottom:1px solid #313131; border-left:1px solid #313131; padding:10px;color:#000; font-size:10px;">Rate</td> 
    <td align="center"   style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:4px;color:#000; font-size:10px;">Amount</td>
  </tr>
        <c:forEach items="${purchaseOrderHeaderPdf.purchaseOrderDetail}" var="purchaseOrderDetail" varStatus="count">
  
<tr>
    <td  style="border-left:1px solid #313131; padding:3px 7px;color:#000; font-size:10px;">${count.index+1}</td>
    <td style="border-left:1px solid #313131;  padding:3px 7px;color:#000; font-size:10px;">${purchaseOrderDetail.rmName}</td>
    <td style="border-left:1px solid #313131;  padding:3px 7px;color:#000; font-size:10px;">${purchaseOrderDetail.hsnCode}</td>
    <td align="center" style="border-left:1px solid #313131; padding:3px 7px;color:#000;font-size:10px;">${purchaseOrderDetail.uom}</td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 7px;color:#000; font-size:10px;"><fmt:formatNumber
										type="number" maxFractionDigits="2"  minFractionDigits="2" 
										value="${purchaseOrderDetail.poQty}" /></td>
    <td align="right" style="border-left:1px solid #313131; padding:3px 7px;color:#000; font-size:10px;"><fmt:formatNumber
										type="number" maxFractionDigits="2"  minFractionDigits="2" 
										value="${purchaseOrderDetail.poRate}" /></td> 
    <td align="right" style="border-left:1px solid #313131; padding:3px 7px;color:#000;font-size:10px;"><fmt:formatNumber
										type="number" maxFractionDigits="2"  minFractionDigits="2" 
										value="${purchaseOrderDetail.poQty*purchaseOrderDetail.poRate}" /></td>
  </tr>
  </c:forEach>
   
    <!-- <tr>
 <td colspan='2' style="border-left:1px solid #313131; padding:3px 10px;color:#000; font-size:10px;"><b>Total</b></td>
  </tr> -->
</table>
  <table width="100%" border="0"  cellpadding="0" cellspacing="0" style="border-top:1px solid #313131;border-right:1px solid #313131;">
  
 
    
  <tr>
    <td colspan="6" width="50%" style="border-left:1px solid #313131; padding:8px;color:#000; font-size:12px;">
     <p style="color:#000; font-size:10px; text-align:left;margin:0px;">FDA Declaration: We hereby verify  food mentioned in the tax invoice is warranted to be of the nature and quality which it puports to be.</p>
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
    <p style="color:#000; font-size:12px; text-align:left;margin:0px;">Continue...</p></td>
  </tr>
  
  <tr>
    <td colspan="6"  width="50%" style="border-bottom:1px solid #313131;border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000; font-size:12px;">
     <p style="color:#000; font-size:12px; text-align:left;margin:0px;">Subject to : Aurangabad</p>
</td>
    <td align="center" colspan="5" width="38%" style="border-bottom:1px solid #313131;border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000;font-size:12px;">Authorised Signature</td>
  </tr>
  
</table>
<div style="page-break-after: always;"></div>
 

</body>
</html>