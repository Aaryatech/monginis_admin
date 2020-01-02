
<%@page contentType="text/html; charset=ISO8859_1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.lang.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>FR Bill PDF</title>

</head>
<body>
	<c:forEach items="${billDetails}" var="frDetails" varStatus="count">
	
	<c:set var="srCnt" value="0" />
		<c:set var="totalRowCount" value="0" />
						<c:set var="maxRowCount" value="20" />
			
			
	
		<div style="text-align: center; font-size: 12px;"><b>TAX INVOICE</b></div>
		<div style="text-align: right; font-size: 10px;">CIN -
			${frDetails.company.cinNo}</div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			style="border-left: 1px solid #313131; border-right: 1px solid #313131; border-top: 1px solid #313131;">
			<tr>
				<td colspan="2" width="20%"
					style="padding: 10px; color: #FFF; font-size: 15px;">&nbsp;</td>
				<td width="60%" colspan="6"
					style="border-left: 1px solid #313131; padding: 5px; color: #000; font-size: 15px; text-align: center">
					<h4 style="color: #000; font-size: 16px; text-align: center; margin: 0px;">${frDetails.company.compName}</h4>
					<p style="color: #000; font-size: 10px; text-align: center; margin: 0px;">
						Factory Add: ${frDetails.company.factAddress} <br />
						Phone:${frDetails.company.phoneNo1}, Email: ${frDetails.company.email}
					</p>
				</td>
				<td colspan="3" width="20%"
					style="border-left: 1px solid #313131; padding: 10px; color: #FFF; font-size: 15px;">
					<p
						style="color: #000; font-size: 11px; text-align: left; margin: 0px;">
						Original for Buyer <br /> Duplicate for Transporter<br />Triplicate
						for Assesse
					</p>
				</td>

			</tr>

			<tr>
				<td width="50%" colspan="6"
					style="border-top: 1px solid #313131; padding: 8px; color: #FFF; font-size: 14px;">
					<p
						style="color: #000; font-size: 13px; text-align:; left; margin: 0px;">
						GSTIN:
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>${frDetails.company.gstin}</b>&nbsp;&nbsp;&nbsp;&nbsp;<span>
							State:&nbsp;${frDetails.company.stateCode} ${frDetails.company.state} </span>
					</p> <!--         <p style="color:#000; font-size:13px; text-align:left;margin:0px;"></p>
 -->
					<p
						style="color: #000; font-size: 13px; text-align: left; margin: 0px;">
						Invoice No: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>${frDetails.invoiceNo}</b>
					</p>
					<p
						style="color: #000; font-size: 13px; text-align: left; margin: 0px;">
						Invoice Date: &nbsp;&nbsp;&nbsp;<b>${frDetails.billDate}</b>
					</p>
					<p
						style="color: #000; font-size: 13px; text-align: left; margin: 0px;">Tax
						is payable on reverse charges(Yes/No): No</p>
				</td>

				<td width="50%" colspan="5"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; padding: 8px; color: #FFF; font-size: 15px;">
					<p
						style="color: #000; font-size: 13px; text-align:; left; margin: 0px;">
						Mode of Transport: &nbsp;&nbsp;&nbsp;&nbsp;<b>${transportMode}</b>
					</p>
					<p
						style="color: #000; font-size: 13px; text-align: left; margin: 0px;">
						Vehicle No:
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>${vehicleNo}</b>
					</p>
					<p
						style="color: #000; font-size: 13px; text-align: left; margin: 0px;">
						Print Dt & Time:&nbsp;&nbsp;&nbsp; &nbsp;<b>${dateTime}</b>
					</p>
					<p
						style="color: #000; font-size: 13px; text-align: left; margin: 0px;">Place
						of supply: &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; ${frDetails.company.state}</p>
				</td>
			</tr>
			<tr>
				<td width="50%" colspan="6"
					style="border-top: 1px solid #313131; padding: 7px; color: #FFF; font-size: 15px;">
					<p
						style="color: #000; font-size: 13px; text-align:; left; margin: 0px;">
						<b> Billed To:&nbsp; &nbsp; ${frDetails.frName}</b>
					</p>
					<p
						style="color: #000; font-size: 11px; text-align:; left; margin: 0px;">${frDetails.frAddress}</p>

				</td>
				<td width="50%" colspan="5"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; padding: 7px; color: #FFF; font-size: 15px;">
					<p
						style="color: #000; font-size: 13px; text-align:; left; margin: 0px;">
						<b> Ship To:&nbsp; &nbsp; ${frDetails.frName}</b>
					</p>
					<p
						style="color: #000; font-size: 11px; text-align:; left; margin: 0px;">${frDetails.frAddress}</p>

				</td>
			</tr>
		</table>

		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			style="border-top: 1px solid #313131; border-right: 1px solid #313131">
			<tr>
				<td rowspan="2" width="2%"
					style="border-bottom: 1px solid #313131; border-bottom: 1px solid #313131; border-left: 1px solid #313131; padding: 5px; color: #000; font-size: 10px;">No.</td>
				<td align="left" width="36%" rowspan="2"
					style="border-bottom: 1px solid #313131; border-left: 1px solid #313131; padding: 15px; color: #000; font-size: 10px; text-align: left">Item
					Description</td>
				<td align="center" width="5%" rowspan="2"
					style="border-bottom: 1px solid #313131; border-left: 1px solid #313131; padding: 0.2px; color: #000; font-size: 10px;">HSN
					Code</td>

				<td align="center" width="5%" rowspan="2"
					style="border-bottom: 1px solid #313131; border-left: 1px solid #313131; padding: 10px; color: #000; font-size: 10px;">Qty</td>
				<td align="center" width="5%" rowspan="2"
					style="border-bottom: 1px solid #313131; border-left: 1px solid #313131; padding: 10px; color: #000; font-size: 10px;">UOM
				</td>
				<td align="center" width="5%" rowspan="2"
					style="border-bottom: 1px solid #313131; border-left: 1px solid #313131; padding: 10px; color: #000; font-size: 10px;">Rate</td>
				<td align="center" width="10%" rowspan="2"
					style="border-bottom: 1px solid #313131; border-left: 1px solid #313131; padding: 10px; color: #000; font-size: 10px;">Amount</td>
				<c:choose>
				<c:when test="${frDetails.isSameState==1}">
				<td align="center" width="10%" colspan="2"
					style="border-left: 1px solid #313131; padding: 10px; color: #000; font-size: 10px; text-align: center;">
					CGST</td>
				<td align="center" width="10%" colspan="2"
					style="border-left: 1px solid #313131; padding: 10px; color: #000; font-size: 10px; text-align: center;">SGST</td>
			   </c:when>
			   <c:otherwise>
			   <td align="center" width="20%" colspan="4"
					style="border-left: 1px solid #313131; padding: 10px; color: #000; font-size: 10px; text-align: center;">
					IGST</td>
			   </c:otherwise>
			   </c:choose>
			</tr>
			<tr>
			<c:choose>
				<c:when test="${frDetails.isSameState==1}">
				<td align="center"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 10px;">Rate%
				</td>
				<td align="center"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 10px;">Amount</td>
				<td align="center"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 10px;">Rate%</td>
				<td align="center"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 10px;">Amount</td>
			  </c:when>
			   <c:otherwise>
			  <td align="center"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 10px;">Rate%</td>
				<td align="center"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 10px;">Amount</td>
			
			   </c:otherwise>
			   </c:choose>
			</tr>

			<c:set var="totalQty" value="0" />
			<c:set var="totalAmt" value="0" />
			<c:set var="totalCgst" value="0" />
			<c:set var="totalSgst" value="0" />
		
			
			<c:forEach items="${frDetails.subCatList}" var="category">
	<c:set var="totalRowCount" value="${totalRowCount+1}" />

				<tr>
					<td
						style="border-left: 1px solid #313131; padding: 3px 5px; color: white; font-size: 10px;">-</td>
					<td
						style="border-left: 1px solid #313131; padding: 3px 5px; color: #000; font-size: 10px;"><u><b>${category.subCatName}</b></u></td>
					<td
						style="border-left: 1px solid #313131; padding: 3px 5px; color: white; font-size: 10px;">-</td>
					<td
						style="border-left: 1px solid #313131; padding: 3px 5px; color: white; font-size: 10px;">-</td>
					<td
						style="border-left: 1px solid #313131; padding: 3px 5px; color: white; font-size: 10px;">-</td>
					<td
						style="border-left: 1px solid #313131; padding: 3px 5px; color: white; font-size: 10px;">-</td>
					<td
						style="border-left: 1px solid #313131; padding: 3px 5px; color: white; font-size: 10px;">-</td>
					<c:choose>
				<c:when test="${frDetails.isSameState==1}">
					<td
						style="border-left: 1px solid #313131; padding: 3px 5px; color: white; font-size: 10px;">-</td>
					<td
						style="border-left: 1px solid #313131; padding: 3px 5px; color: white; font-size: 10px;">-</td>
					<td
						style="border-left: 1px solid #313131; padding: 3px 5px; color: white; font-size: 10px;">-</td>
					<td
						style="border-left: 1px solid #313131; padding: 3px 5px; color: white; font-size: 10px;">-</td>
                   </c:when>
                   <c:otherwise>
                   <td
						style="border-left: 1px solid #313131; padding: 3px 5px; color: white; font-size: 10px;">-</td>
					<td
						style="border-left: 1px solid #313131; padding: 3px 5px; color: white; font-size: 10px;">-</td>
                 
                   </c:otherwise>
                  </c:choose>

				</tr>
				<c:forEach items="${frDetails.billDetailsList}" var="billDetails"
					varStatus="count">
						
					<c:choose>
						<c:when test="${category.subCatId eq billDetails.subCatId}">
						
						
					<c:choose>
						
						<c:when test="${totalRowCount eq maxRowCount}">
						
						</table>
						
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
			style="border-top: 1px solid #313131; border-right: 1px solid #313131;">
			<tr>
				<td colspan="6" width="60%"
					style="border-left: 1px solid #313131; border-right: 1px solid #313131; padding: 8px; color: #000; font-size: 12px;">
					<p
						style="color: #000; font-size: 12px; text-align: left; margin: 0px;">FDA
						Declaration: ${frDetails.company.fdaDeclaration}
						purpose to be. FDA Lic. No: ${frDetails.company.fdaLicenceNo}</p>
				</td>


				<td colspan="5" width="40%" rowspan="2"
					style="border-left: 1px solid #313131; color: #000; font-size: 12px;">

					
				</td>
			</tr>
			<tr>
				<td colspan="6" width="50%"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-right: 1px solid #313131; padding: 8px; color: #000; font-size: 12px;"><p>Certified
						that the particulars given above are true and correct.</p></td>
			</tr>
			<tr>
				<td colspan="6" width="60%"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; padding: 10px; color: #000; font-size: 12px;"><p>
						<b></b><br></br>
					</p></td>
				<td colspan="5" width="40%"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; padding: 8px; color: #000; font-size: 15px;">

					<table width="100%" border="0" align="left" cellpadding="0"
						cellspacing="0" style="border-right: 0px solid #313131">
						<tr>
							<td align="center" width="9%" colspan="2"
								style="border-top: 0px solid #313131; padding: 2px; color: #000; font-size: 13px; text-align: center;"><b>Continue...</b></td>
						</tr>

					</table>

				</td>
			</tr>
			<tr>
				<td colspan="6" width="50%"
					style="border-bottom: 1px solid #313131; border-top: 1px solid #313131; border-left: 1px solid #313131; padding: 10px; color: #000; font-size: 11px;">
					<p
						style="color: #000; font-size: 11px; text-align: left; margin: 0px;">Subject
						to Aurangabad Jurisdiction
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Receiver's
						Signature</p>
				</td>

				<td align="center" colspan="5" width="38%"
					style="border-bottom: 1px solid #313131; border-top: 1px solid #313131; border-left: 1px solid #313131; padding: 10px; color: #000; font-size: 11px;"><p>
						<b>For ${frDetails.company.compName}.<br></br> <br></br> <br></br>
							Authorised Signatory
						</b>
					</p></td>
			</tr>



		</table>
		<p
			style="color: #000; font-size: 11px; text-align: center; margin: 0px;">
			<b>This Is A Computer Generated Invoice Does Not Require
				Signature </b>
		</p>
						
						
						
						<div style="page-break-after: always;"></div>
						
						
								
		<div style="text-align: center; font-size: 12px;"><b>TAX INVOICE</b></div>
		<div style="text-align: right; font-size: 10px;">CIN -
			${frDetails.company.cinNo}</div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			style="border-left: 1px solid #313131; border-right: 1px solid #313131; border-top: 1px solid #313131;">
			<tr>
				<td colspan="2" width="20%"
					style="padding: 10px; color: #FFF; font-size: 15px;">&nbsp;</td>
				<td width="60%" colspan="6"
					style="border-left: 1px solid #313131; padding: 5px; color: #000; font-size: 15px; text-align: center">
					<h4 style="color: #000; font-size: 16px; text-align: center; margin: 0px;">${frDetails.company.compName}</h4>
					<p style="color: #000; font-size: 10px; text-align: center; margin: 0px;">
						Factory Add: ${frDetails.company.factAddress}<br />
						Phone:${frDetails.company.phoneNo1}, Email: ${frDetails.company.email}
					</p>
				</td>
				<td colspan="3" width="20%"
					style="border-left: 1px solid #313131; padding: 10px; color: #FFF; font-size: 15px;">
					<p
						style="color: #000; font-size: 11px; text-align: left; margin: 0px;">
						Original for Buyer <br /> Duplicate for Transporter<br />Triplicate
						for Assesse
					</p>
				</td>

			</tr>

			<tr>
				<td width="50%" colspan="6"
					style="border-top: 1px solid #313131; padding: 8px; color: #FFF; font-size: 14px;">
					<p
						style="color: #000; font-size: 13px; text-align:; left; margin: 0px;">
						GSTIN:
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>${frDetails.company.gstin}</b>&nbsp;&nbsp;&nbsp;&nbsp;<span>
							State:&nbsp;${frDetails.company.stateCode} ${frDetails.company.state} </span>
					</p> <!--         <p style="color:#000; font-size:13px; text-align:left;margin:0px;"></p>
 -->
					<p
						style="color: #000; font-size: 13px; text-align: left; margin: 0px;">
						Invoice No: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>${frDetails.invoiceNo}</b>
					</p>
					<p
						style="color: #000; font-size: 13px; text-align: left; margin: 0px;">
						Invoice Date: &nbsp;&nbsp;&nbsp;<b>${frDetails.billDate}</b>
					</p>
					<p
						style="color: #000; font-size: 13px; text-align: left; margin: 0px;">Tax
						is payable on reverse charges(Yes/No): No</p>
				</td>

				<td width="50%" colspan="5"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; padding: 8px; color: #FFF; font-size: 15px;">
					<p
						style="color: #000; font-size: 13px; text-align:; left; margin: 0px;">
						Mode of Transport: &nbsp;&nbsp;&nbsp;&nbsp;<b>${transportMode}</b>
					</p>
					<p
						style="color: #000; font-size: 13px; text-align: left; margin: 0px;">
						Vehicle No:
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>${vehicleNo}</b>
					</p>
					<p
						style="color: #000; font-size: 13px; text-align: left; margin: 0px;">
						Supply Dt & Time:&nbsp;&nbsp;&nbsp; &nbsp;<b>${dateTime}</b>
					</p>
					<p
						style="color: #000; font-size: 13px; text-align: left; margin: 0px;">Place
						of supply:&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; ${frDetails.company.state}</p>
				</td>
			</tr>
			<tr>
				<td width="50%" colspan="6"
					style="border-top: 1px solid #313131; padding: 7px; color: #FFF; font-size: 15px;">
					<p
						style="color: #000; font-size: 13px; text-align:; left; margin: 0px;">
						<b> Billed To:&nbsp; &nbsp; ${frDetails.frName}</b>
					</p>
					<p
						style="color: #000; font-size: 11px; text-align:; left; margin: 0px;">${frDetails.frAddress}</p>
  
				</td>
				<td width="50%" colspan="5"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; padding: 7px; color: #FFF; font-size: 15px;">
					<p
						style="color: #000; font-size: 13px; text-align:; left; margin: 0px;">
						<b> Ship To:&nbsp; &nbsp; ${frDetails.frName}</b>
					</p>
					<p
						style="color: #000; font-size: 11px; text-align:; left; margin: 0px;">${frDetails.frAddress}</p>

				</td>
			</tr>
		</table>
						
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
			style="border-top: 1px solid #313131; border-right: 1px solid #313131">
			<tr>
				<td rowspan="2" width="2%"
					style="border-bottom: 1px solid #313131; border-bottom: 1px solid #313131; border-left: 1px solid #313131; padding: 5px; color: #000; font-size: 10px;">No.</td>
				<td align="left" width="36%" rowspan="2"
					style="border-bottom: 1px solid #313131; border-left: 1px solid #313131; padding: 15px; color: #000; font-size: 10px; text-align: left">Item
					Description</td>
				<td align="center" width="5%" rowspan="2"
					style="border-bottom: 1px solid #313131; border-left: 1px solid #313131; padding: 0.2px; color: #000; font-size: 10px;">HSN
					Code</td>


				<td align="center" width="5%" rowspan="2"
					style="border-bottom: 1px solid #313131; border-left: 1px solid #313131; padding: 10px; color: #000; font-size: 10px;">Qty</td>
				<td align="center" width="5%" rowspan="2"
					style="border-bottom: 1px solid #313131; border-left: 1px solid #313131; padding: 10px; color: #000; font-size: 10px;">UOM
				</td>
				<td align="center" width="5%" rowspan="2"
					style="border-bottom: 1px solid #313131; border-left: 1px solid #313131; padding: 10px; color: #000; font-size: 10px;">Rate</td>
				<td align="center" width="10%" rowspan="2"
					style="border-bottom: 1px solid #313131; border-left: 1px solid #313131; padding: 10px; color: #000; font-size: 10px;">Amount</td>
				<c:choose>
				<c:when test="${frDetails.isSameState==1}">
				<td align="center" width="10%" colspan="2"
					style="border-left: 1px solid #313131; padding: 10px; color: #000; font-size: 10px; text-align: center;">
					CGST</td>
				<td align="center" width="10%" colspan="2"
					style="border-left: 1px solid #313131; padding: 10px; color: #000; font-size: 10px; text-align: center;">SGST</td>
				</c:when>
				<c:otherwise>
				<td align="center" width="20%" colspan="4"
					style="border-left: 1px solid #313131; padding: 10px; color: #000; font-size: 10px; text-align: center;">IGST</td>
				</c:otherwise>
				</c:choose>
				
			</tr>
			<tr>
			<c:choose>
				<c:when test="${frDetails.isSameState==1}">
				<td align="center"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 10px;">Rate%
				</td>
				<td align="center"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 10px;">Amount</td>
				<td align="center"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 10px;">Rate%</td>
				<td align="center"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 10px;">Amount</td>
		</c:when>
				<c:otherwise>
				<td align="center"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 10px;">Rate%</td>
				<td align="center"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 10px;">Amount</td>
	
				</c:otherwise>
				</c:choose>
			</tr>
						
							
							
							<c:set var="totalRowCount" value="0" />
							
							</c:when>
						</c:choose>
						<c:set var="srCnt" value="${srCnt+1}" />
							<c:set var="totalRowCount" value="${totalRowCount+1}" />
							<tr>
								<td
									style="border-left: 1px solid #313131; padding: 3px 5px; color: #000; font-size: 12px;">${srCnt}</td>
									
									<c:choose>
									<c:when test="${billDetails.grnType==3}">
									<td
								style="border-left: 1px solid #313131; padding: 3px 5px; color: #000; font-size: 12px;">${billDetails.itemName} [NR]
								<c:choose>
									<c:when test="${billDetails.remark ne '0'}">
								------ ${billDetails.remark} Kg
								</c:when>
								</c:choose>
								
								</td>
									
									</c:when>
									<c:when test="${billDetails.grnType==4}">
									<td
									style="border-left: 1px solid #313131; padding: 3px 5px; color: #000; font-size: 12px;">${billDetails.itemName} [R]</td>
									</c:when>
									<c:otherwise>
									
									<td
									style="border-left: 1px solid #313131; padding: 3px 5px; color: #000; font-size: 12px;">${billDetails.itemName}</td>
									
									</c:otherwise>
									
									</c:choose>
								
								<td align="left"
									style="border-left: 1px solid #313131; padding: 3px 5px; color: #000; font-size: 12px;">${billDetails.itemHsncd}</td>
								<td align="right"
									style="border-left: 1px solid #313131; padding: 3px 5px; color: #000; font-size: 12px;"><fmt:formatNumber
										type="number" maxFractionDigits="2" minFractionDigits="2"
										value="${billDetails.billQty}" /></td>
								<c:set var="totalQty" value="${totalQty+billDetails.billQty}" />
								<td align="center"
									style="border-left: 1px solid #313131; padding: 3px 5px; color: #000; font-size: 12px;">${billDetails.itemUom}</td>
								<td align="right"
									style="border-left: 1px solid #313131; padding: 3px 4px; color: #000; font-size: 12px;"><fmt:formatNumber
										type="number" maxFractionDigits="2" minFractionDigits="2"
										value="${billDetails.baseRate}" /></td>
								<td align="right"
									style="border-left: 1px solid #313131; padding: 3px 4px; color: #000; font-size: 12px;"><fmt:formatNumber
										type="number" maxFractionDigits="2" minFractionDigits="2"
										value="${billDetails.taxableAmt}" /></td>
								<c:set var="totalAmt" value="${totalAmt+billDetails.taxableAmt}" />
								<c:choose>
				<c:when test="${frDetails.isSameState==1}">
								<td align="right"
									style="border-left: 1px solid #313131; padding: 3px 5px; color: #000; font-size: 12px;"><fmt:formatNumber
										type="number" maxFractionDigits="2" minFractionDigits="2"
										value="${billDetails.cgstPer}" /></td>
								<td align="right"
									style="border-left: 1px solid #313131; padding: 3px 5px; color: #000; font-size: 12px;"><fmt:formatNumber
										type="number" maxFractionDigits="2" minFractionDigits="2"
										value="${billDetails.cgstRs}" /></td>
								<c:set var="totalCgst" value="${totalCgst+billDetails.cgstRs}" />
								<td align="right"
									style="border-left: 1px solid #313131; padding: 3px 5px; color: #000; font-size: 12px;"><fmt:formatNumber
										type="number" maxFractionDigits="2" minFractionDigits="2"
										value="${billDetails.sgstPer}" /></td>
								<td align="right"
									style="border-left: 1px solid #313131; padding: 3px 5px; color: #000; font-size: 12px;"><fmt:formatNumber
										type="number" maxFractionDigits="2" minFractionDigits="2"
										value="${billDetails.sgstRs}" /></td>
								<c:set var="totalSgst" value="${totalSgst+billDetails.sgstRs}" />
								</c:when>
								<c:otherwise>
								<td align="right"
									style="border-left: 1px solid #313131; padding: 3px 5px; color: #000; font-size: 12px;"><fmt:formatNumber
										type="number" maxFractionDigits="2" minFractionDigits="2"
										value="${billDetails.igstPer}" /></td>
								<td align="right"
									style="border-left: 1px solid #313131; padding: 3px 5px; color: #000; font-size: 12px;"><fmt:formatNumber
										type="number" maxFractionDigits="2" minFractionDigits="2"
										value="${billDetails.igstRs}" /></td>
								<c:set var="totalIgst" value="${totalIgst+billDetails.igstRs}" />
								</c:otherwise>
								</c:choose>
							</tr>
						</c:when>
					</c:choose>
				</c:forEach>
			</c:forEach>
			<tr>
				<td align="left"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 0px;">-</td>
				<td align="left"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 12px;"><b>Total</b></td>
				<td align="center"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 0px;">-</td>
				<td align="right"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 12px;"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${totalQty}" /></b></td>
				<td align="center"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 0px;">-</td>
				<td align="center"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 0px;">-</td>
				<td align="right"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 12px;"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${totalAmt}" /></b></td>
					<c:choose>
				<c:when test="${frDetails.isSameState==1}">	
					<td align="center"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 0px;">-</td>
			
				<td align="right"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 12px;"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${totalCgst}" /></b></td>
				<td align="center"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 0px;">-</td>
				<td align="right"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 12px;"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${totalSgst}" /></b></td>
				</c:when>
				<c:otherwise>
				<td align="center"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 0px;">-</td>
			
				<td align="right"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 12px;"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${totalIgst}" /></b></td>
				
				</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<c:set var="finalAmt" value="${totalAmt+totalCgst+totalSgst}"></c:set>
				<%
					double fAmt = 0;// (Double)pageContext.getAttribute("finalAmt");
						fAmt = Math.round(fAmt);
				%>
				<td align="right"
					style="border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 0px;">-</td>
				<td align="right"
					style="border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 0px;">-</td>
				<td style="border-bottom: 1px solid #313131; font-size: 0px;">-</td>
				<td style="border-bottom: 1px solid #313131; font-size: 0px;">-</td>
				<td style="border-bottom: 1px solid #313131; font-size: 0px;">-</td>
				<td
					style="border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 0px;">-</td>
				<td style="border-bottom: 1px solid #313131; font-size: 0px;">-</td>
				<c:choose>
				<c:when test="${frDetails.isSameState==1}">	
				<td
					style="border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 0px;">-</td>
				<td style="border-bottom: 1px solid #313131; font-size: 0px;">-</td>
				</c:when>
				</c:choose>
				<td style="border-bottom: 1px solid #313131; font-size: 12px;"><b>Total:</b></td>
				<td align="right"
					style="border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 4px; color: #000; font-size: 12px;"><b>
						<fmt:formatNumber type="number" value="${frDetails.grandTotal}" />
				</b></td>
			</tr>
		</table>

		<table width="56%" border="0" cellpadding="0" cellspacing="0"
			style="border-right: 1px solid #313131">
			<tr>
				<td align="center" width="9%" colspan="2"
					style="border-left: 1px solid #313131; padding: 2px; color: #000; font-size: 10px; text-align: center;">Tax(%)</td>
				<td align="center" width="9%" colspan="2"
					style="border-left: 1px solid #313131; padding: 2px; color: #000; font-size: 10px; text-align: center;">Taxable
					Amount</td>
<c:choose>
				<c:when test="${frDetails.isSameState==1}">	
				<td align="center" width="9%" colspan="2"
					style="border-left: 1px solid #313131; padding: 2px; color: #000; font-size: 10px; text-align: center;">CGST
					Amount</td>
				<td align="center" width="9%" colspan="2"
					style="border-left: 1px solid #313131; padding: 2px; color: #000; font-size: 10px; text-align: center;">SGST
					Amount</td>
	</c:when>
	<c:otherwise>
	<td align="center" width="18%" colspan="4"
					style="border-left: 1px solid #313131; padding: 2px; color: #000; font-size: 10px; text-align: center;">IGST
					Amount</td>
	</c:otherwise>
	</c:choose>
				<td align="center" width="9%" colspan="2"
					style="border-left: 1px solid #313131; padding: 2px; color: #000; font-size: 10px; text-align: center;">Total
					Tax</td>
				<td align="center" width="9%" colspan="2"
					style="border-left: 1px solid #313131; padding: 2px; color: #000; font-size: 10px; text-align: center;">Total
					Amount</td>

			</tr>
			<c:forEach items="${slabwiseBillList}" var="slabwiseBills"
				varStatus="count">

				<c:choose>
					<c:when test="${slabwiseBills.billNo==frDetails.billNo}">

						<c:forEach items="${slabwiseBills.slabwiseBill}"
							var="slabwiseBill" varStatus="count">
							<tr>
								<td align="right" width="9%" colspan="2"
									style="border-top: 1px solid #313131; border-left: 1px solid #313131; padding: 5px; color: #000; font-size: 10px; text-align: right">${slabwiseBill.taxPer}</td>
								<td align="right" width="9%" colspan="2"
									style="border-top: 1px solid #313131; border-left: 1px solid #313131; padding: 5px; color: #000; font-size: 10px; text-align: right"><fmt:formatNumber
										type="number" maxFractionDigits="2" minFractionDigits="2"
										value="${slabwiseBill.taxableAmt}" /></td>
<c:choose>
				<c:when test="${frDetails.isSameState==1}">	
								<td align="right" width="9%" colspan="2"
									style="border-top: 1px solid #313131; border-left: 1px solid #313131; padding: 5px; color: #000; font-size: 10px; text-align: right"><fmt:formatNumber
										type="number" maxFractionDigits="2" minFractionDigits="2"
										value="${slabwiseBill.cgstAmt}" /></td>
								<td align="right" width="9%" colspan="2"
									style="border-top: 1px solid #313131; border-left: 1px solid #313131; padding: 5px; color: #000; font-size: 10px; text-align: right"><fmt:formatNumber
										type="number" maxFractionDigits="2" minFractionDigits="2"
										value="${slabwiseBill.sgstAmt}" /></td>
				</c:when>
				<c:otherwise>
				<td align="right" width="18%" colspan="4"
									style="border-top: 1px solid #313131; border-left: 1px solid #313131; padding: 5px; color: #000; font-size: 10px; text-align: right"><fmt:formatNumber
										type="number" maxFractionDigits="2" minFractionDigits="2"
										value="${slabwiseBill.totalTax}" /></td>
				</c:otherwise>
</c:choose>
								<td align="right" width="9%" colspan="2"
									style="border-top: 1px solid #313131; border-left: 1px solid #313131; padding: 5px; color: #000; font-size: 10px; text-align: right"><fmt:formatNumber
										type="number" maxFractionDigits="2" minFractionDigits="2"
										value="${slabwiseBill.totalTax}" /></td>
								<td align="right" width="9%" colspan="2"
									style="border-top: 1px solid #313131; border-left: 1px solid #313131; padding: 5px; color: #000; font-size: 10px; text-align: right"><fmt:formatNumber
										type="number" maxFractionDigits="2" minFractionDigits="2"
										value="${slabwiseBill.grandTotal}" /></td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</c:forEach>
		</table>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			style="border-top: 1px solid #313131; border-right: 1px solid #313131;">
			<tr>
				<td colspan="2"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-right: 1px solid #313131; padding: 8px; color: #000; font-size: 12px;">
					<p
						style="color: #000; font-size: 12px; text-align: left; margin: 0px;">FDA
						Declaration : ${frDetails.company.fdaDeclaration} FDA Lic. No : ${frDetails.company.fdaLicenceNo}</p></td>
						
				
			</tr>
			
			<tr>
				<td colspan="2"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-right: 1px solid #313131; padding: 8px; color: #000; font-size: 12px;">
					<p
						style="color: #000; font-size: 12px; text-align: left; margin: 0px;">NOTE : Tax is not payable on Reverse Charge Basis</p></td>
						
				
			</tr>
		</table>
		

		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			style="border-top: 1px solid #313131; border-right: 1px solid #313131;">
			
			<tr>
				<td colspan="6" width="50%"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; border-right: 1px solid #313131; padding: 8px; color: #000; font-size: 12px;"><p>Certified
						that the particulars given above are true and correct.</p></td>
			</tr>
			<tr>
				<td colspan="6" width="60%"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; padding: 10px; color: #000; font-size: 12px;"><p>
						<b>Invoice Value in Rs.</b><br></br> ${frDetails.amtInWords}
					</p></td>
				<td colspan="5" width="40%"
					style="border-top: 1px solid #313131; border-left: 1px solid #313131; padding: 8px; color: #000; font-size: 15px;">

					<table width="100%" border="0" align="left" cellpadding="0"
						cellspacing="0" style="border-right: 0px solid #313131">
						<tr>
							<td align="center" width="9%" colspan="2"
								style="border-top: 0px solid #313131; padding: 2px; color: #000; font-size: 13px; text-align: center;"><b>Grand
									Total: Rs. </b>&nbsp;&nbsp;<b><fmt:formatNumber type="number"
										value="${frDetails.grandTotal}" /></b></td>
						</tr>

					</table>

				</td>
			</tr>
			<tr>
				<td colspan="6" width="50%"
					style="border-bottom: 1px solid #313131; border-top: 1px solid #313131; border-left: 1px solid #313131; padding: 10px; color: #000; font-size: 11px;">
					<p
						style="color: #000; font-size: 11px; text-align: left; margin: 0px;">Subject
						to Aurangabad Jurisdiction
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Receiver's
						Signature</p>
				</td>

				<td align="center" colspan="5" width="38%"
					style="border-bottom: 1px solid #313131; border-top: 1px solid #313131; border-left: 1px solid #313131; padding: 10px; color: #000; font-size: 11px;"><p>
						<b>For ${frDetails.company.compName}<br></br> <br></br> <br></br>
							Authorised Signatory
						</b>
					</p></td>
			</tr>



		</table>
		<p
			style="color: #000; font-size: 11px; text-align: center; margin: 0px;">
			<b>This Is A Computer Generated Invoice Does Not Require
				Signature </b>
		</p>


		<div style="page-break-after: always;"></div>
	</c:forEach>
</body>
</html>

