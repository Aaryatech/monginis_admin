

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>FR Bill PDF</title>

</head>
<body>
  	<c:forEach items="${billDetails}" var="frDetails" varStatus="count">
						<h6 align="center">TAX INVOICE</h6>
<table width="100%" border="0"  cellpadding="0" cellspacing="0" style="border-top:1px solid #313131;border-right:1px solid #313131;">
  <tr>
    <td colspan="2" width="20%" style="border-left:1px solid #313131; padding:10px;color:#FFF; font-size:15px;">&nbsp;</td>
    <td width="60%" colspan="6" style="border-left:1px solid #313131; padding:5px;color:#000; font-size:15px; text-align:center">
    <h4 style="color:#000; font-size:20px; text-align:center; margin:0px;">Galdhar Foods Pvt.Ltd</h4>
   <p style="color:#000; font-size:16px; text-align:center;margin:0px;">Factory Add: A-32 Shendra, MIDC, Auraangabad-4331667 <br />
Phone:0240-2466217, Email: aurangabad@monginis.net</p>
 </td>
    <td colspan="3" width="20%" style="border-left:1px solid #313131; padding:10px;color:#FFF; font-size:15px;">
   	<p style="color:#000; font-size:16px; text-align:center;margin:0px;"> Original for buyer <br />
duplicate for tranpoter</p> 
    </td>
    
  </tr>
<!--   </table>
 -->  
 <hr></hr>
 <!--  <p style="color:#000; font-size:16px; text-align:;left;margin:0px;">GSTIIN: 27AHIPJ7279D1Z3</p>
  <hr></hr> -->
       <%--  <p style="color:#000; font-size:16px; text-align:left;margin:0px;">State:27 Maharashtra</p><hr></hr>
        <p style="color:#000; font-size:16px; text-align:left;margin:0px;">Invoice No: ${frDetails.invoiceNo} Invoice Date: ${frDetails.billDate}</p><hr></hr>
        <p style="color:#000; font-size:16px; text-align:left;margin:0px;">Tax is payble on reverse charges(Yes/no):No</p><hr></hr>
         <p style="color:#000; font-size:16px; text-align:;left;margin:0px;"> Mode of Transport ${transportMode} Vehicle no  ${vehicleNo}</p><hr></hr>
        <p style="color:#000; font-size:16px; text-align:left;margin:0px;">Supply Date & Time ${dateTime} Palce of supply Maharashtra</p><hr></hr>
        
         <p style="color:#000; font-size:16px; text-align:;left;margin:0px;"> Billed to: ${frDetails.frName}</p><br /><br /><br /><br />
         --%>
  <tr>
    <td width="50%" colspan="6" style="border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#FFF; font-size:15px;">
        <p style="color:#000; font-size:16px; text-align:;left;margin:0px;">GSTIIN: <b>27AHIPJ7279D1Z3</b></p>
        <p style="color:#000; font-size:16px; text-align:left;margin:0px;">State:27 Maharashtra</p>
        <p style="color:#000; font-size:16px; text-align:left;margin:0px;">Invoice No: <b>${frDetails.invoiceNo}</b></p>
        <p style="color:#000; font-size:16px; text-align:left;margin:0px;">Invoice Date:<b>${frDetails.billDate}</b></p>
        <p style="color:#000; font-size:16px; text-align:left;margin:0px;">Tax is payble on reverse charges(Yes/no):No</p>
    </td>

    <td width="50%" colspan="5" style="border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#FFF; font-size:15px;">
        <p style="color:#000; font-size:16px; text-align:;left;margin:0px;"> Mode of Transport <b>${transportMode}</b></p>
        <p style="color:#000; font-size:16px; text-align:left;margin:0px;">Vehicle no  <b>${vehicleNo}</b></p>
        <p style="color:#000; font-size:16px; text-align:left;margin:0px;">Supply Dt & Time <b>${dateTime}</b></p>
        <p style="color:#000; font-size:16px; text-align:left;margin:0px;">Place of supply Maharashtra</p>
    </td>
  </tr>
  <tr>
    <td width="20%" colspan="6" style="border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#FFF; font-size:15px;">
        <p style="color:#000; font-size:16px; text-align:;left;margin:0px;"> Billed to:<b>${frDetails.frName}</b> ${frDetails.frAddress}</p><br /><br /><br /><br />
    </td>
    <td width="20%" colspan="5" style="border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#FFF; font-size:15px;">
        <p style="color:#000; font-size:16px; text-align:;left;margin:0px;"> Billed to: <b>${frDetails.frName}</b> ${frDetails.frAddress}</p><br /><br /><br /><br />
    </td>
      </tr>
      </table>
      
      <table width="100%" border="0"  cellpadding="0" cellspacing="0" style="border-top:1px solid #313131;border-right:1px solid #313131;">
  <tr>
    <td width="1%" rowspan="2"  style="border-top:1px solid #313131;border-bottom:1px solid #313131; border-bottom:1px solid #313131;border-left:1px solid #313131; padding:5px;color:#000; font-size:10px;">Sr.</td>
    <td align="left" width="3%" rowspan="2" style="border-top:1px solid #313131;border-bottom:1px solid #313131; border-left:1px solid #313131; padding:15px;color:#000; font-size:10px;">Item Decription</td>
   <td align="center" width="1%" rowspan="2" style="border-top:1px solid #313131;border-bottom:1px solid #313131; border-left:1px solid #313131; padding:0.2px;color:#000; font-size:10px;">HSN Code</td>
 
     
 <td align="center" width="1%" rowspan="2" style="border-top:1px solid #313131; border-bottom:1px solid #313131; border-left:1px solid #313131; padding:4px;color:#000; font-size:10px;">Qty</td>
    <td align="center" width="1%" rowspan="2" style="border-top:1px solid #313131;border-bottom:1px solid #313131; border-left:1px solid #313131; padding:4px;color:#000; font-size:10px;">UOM </td>
    <td align="center" width="1%" rowspan="2" style="border-top:1px solid #313131;border-bottom:1px solid #313131; border-left:1px solid #313131; padding:4px;color:#000; font-size:10px;">Rate</td>
    <td align="center" width="1%" rowspan="2" style="border-top:1px solid #313131;border-bottom:1px solid #313131; border-left:1px solid #313131; padding:4px;color:#000; font-size:10px;">Amount</td>
    <td align="center" width="1%" colspan="2" style="border-top:1px solid #313131;border-bottom:1px solid #313131; border-left:1px solid #313131; padding:10px;color:#000; font-size:10px; text-align:center;"> CGST</td>
    <td align="center" width="1%" colspan="2" style="border-top:1px solid #313131;border-bottom:1px solid #313131; border-left:1px solid #313131; padding:10px;color:#000; font-size:10px;text-align:center;">SGST</td>
  </tr>
  <tr>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:1px;color:#000; font-size:10px;">Rate% </td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131;  padding:2px;color:#000; font-size:10px;">Amount</td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:1px;color:#000; font-size:10px;">Rate%</td>
    <td align="center" style="border-top:1px solid #313131;border-left:1px solid #313131;border-bottom:1px solid #313131; padding:2px;color:#000; font-size:10px;">Amount</td>
  </tr>
        <c:forEach items="${frDetails.billDetailsList}" var="billDetails" varStatus="count">
  
  <tr>
    <td  style="border-top:0px solid #313131;border-left:1px solid #313131; padding:3px;color:#000; font-size:10px;">${count.index+1}</td>
    <td style="border-top:0px solid #313131;border-left:1px solid #313131;  padding:15px;color:#000; font-size:10px;">${billDetails.itemName}</td>
    <td style="border-top:0px solid #313131;border-left:1px solid #313131;  padding:3px;color:#000; font-size:10px;">${billDetails.itemHsncd}</td>
    <td align="center" style="border-top:0px solid #313131;border-left:1px solid #313131; padding:3px;color:#000;font-size:10px;">${billDetails.billQty}</td>
    <td align="center" style="border-top:0px solid #313131;border-left:1px solid #313131; padding:3px;color:#000; font-size:10px;">${billDetails.itemUom}</td>
    <td align="center" style="border-top:0px solid #313131;border-left:1px solid #313131; padding:3px;color:#000; font-size:10px;">${billDetails.baseRate}</td>
    <td align="center" style="border-top:0px solid #313131;border-left:1px solid #313131; padding:3px;color:#000;font-size:10px;">${billDetails.taxableAmt}</td>
    <td align="center" style="border-top:0px solid #313131;border-left:1px solid #313131; padding:1px;color:#000; font-size:10px;">${billDetails.cgstPer}</td>
    <td align="center" style="border-top:0px solid #313131;border-left:1px solid #313131; padding:1px;color:#000; font-size:10px;">${billDetails.sgstPer}</td>
    <td align="center" style="border-top:0px solid #313131;border-left:1px solid #313131; padding:2px;color:#000;font-size:10px;">${billDetails.cgstRs}</td>
    <td align="center" style="border-top:0px solid #313131;border-left:1px solid #313131; padding:2px;color:#000;font-size:10px;">${billDetails.sgstRs}</td>
  </tr>
  </c:forEach>
  
</table>
  <table width="100%" border="0"  cellpadding="0" cellspacing="0" style="border-top:1px solid #313131;border-right:1px solid #313131;">
  
  <tr>
    <td colspan="6" width="60%" style="border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000; font-size:15px;">&nbsp;</td>
    <td colspan="5" width="40%" style="border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000;font-size:15px;">&nbsp;</td>
  </tr>
  
    
  <tr>
    <td colspan="6" width="60%" style="border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000; font-size:12px;">
     <p style="color:#000; font-size:12px; text-align:left;margin:0px;">FDA Declaration: We hereby vertify  food mentionerl in the tax invoice is wa.</p>
</td>
    <td colspan="5" width="40%" rowspan="2" style="border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000;font-size:15px;">&nbsp;</td>
  </tr>
  
    <tr>
    <td colspan="6" width="60%"  style="border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000; font-size:15px;">&nbsp;</td>
  </tr>
  
    
  <tr>
    <td colspan="6" width="60%"  style="border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000; font-size:15px;">&nbsp;

</td>
    <td colspan="5" width="40%" style="border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000;font-size:15px;">     <p style="color:#000; font-size:12px; text-align:left;margin:0px;">Continue...</p></td>
  </tr>
  
    <tr>
    <td colspan="6" width="60%" style="border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000; font-size:15px;">&nbsp;</td>
    <td colspan="5" width="40%" style="border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000;font-size:15px;">&nbsp;</td>
  </tr>
  
    
  <tr>
    <td colspan="6"  width="60%" style="border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000; font-size:12px;">
     <p style="color:#000; font-size:12px; text-align:left;margin:0px;">Subject to : Aurangabad</p>
</td>
    <td colspan="5" width="40%" style="border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000;font-size:15px;">&nbsp;</td>
  </tr>
  
   <tr>
    <td colspan="6" width="60%" style="border-bottom:1px solid #313131;border-top:1px solid #313131;border-left:1px solid #313131; padding:10px;color:#000; font-size:15px;">&nbsp;</td>
    <td colspan="5" width="40%" style="border-bottom:1px solid #313131;border-top:1px solid #313131; padding:10px;color:#000;font-size:15px;">&nbsp;</td>
  </tr>
  
</table>
<div style="page-break-after: always;"></div>
  </c:forEach>

</body>
</html>