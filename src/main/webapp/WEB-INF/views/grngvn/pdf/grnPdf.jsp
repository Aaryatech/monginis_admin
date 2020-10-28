<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.ats.adminpanel.commons.Constants"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GRN - GVN Print</title>
<head>
<meta charset="UTF-8" />
<title></title>
</head>
<style type="text/css">
<!--
.style2 {
	font-size: 14px
}

.style5 {
	font-size: 10px
}

.style6 {
	font-size: 9px
}

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
	<c:forEach items="${resBean.ggHeaderList}" var="gHead"
		varStatus="count1">

		<table width="250" border="0" cellspacing="0" cellpadding="0"
			style="padding: 5px; font-family: verdana; font-size: 10px; border: 1px solid #E7E7E7;">

			<tbody>
				<tr>
					<td align="right" style="padding: 0px;"></td>
				</tr>
				<tr>
					<c:choose>
						<c:when test="${gHead.isGrn==1}">
							<td colspan="2" align="center" style="padding: 1px;"><p>Goods
									Return Note (DEBIT)</p></td>

						</c:when>
						<c:otherwise>
							<td colspan="2" align="center" style="padding: 1px;"><p>Goods
									Variation Note (DEBIT)</p></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td colspan="2" align="center"
						style="padding: 2px; border-bottom: 1px solid #E7E7E7;"><p
							class="style2">
							<b>${gHead.frName}</b><br /> <span
								style="font-size: 10px; font-family: Arial;">(The
								Monginis Cake Shop)</span>
						</p></td>
				</tr>
				<%-- <tr>
				<td colspan="2" align="center"
					style="padding: 3px; font-family: Arial; border-bottom: 1px solid #E7E7E7; font-size: 12px;"><p
						class="style5">
						<br /> <strong>add</strong><br /> <br />
						
                GSTIN:<strong>${sessionScope.frDetails.frGstNo}</strong><br/>
                 Phone:<strong>${sessionScope.frDetails.frMob}</strong><br/>
                 </p></td>
			</tr> --%>

				<%-- <tr>
			<td colspan="2" align="center"
					style="padding: 3px; font-family: Arial; border-bottom: 1px solid #E7E7E7; font-size: 12px;"><p
						class="style5">
						<br />To, <strong>${Constant.FACTORYNAME}</strong><br /> 
						<br />${Constant.FACTORYADDRESS}<br /> 
						<br />GSTIN: <strong>${Constant.FACTORYGSTIN}</strong><br />
                       
               
                </p>
                </td>
                </tr> --%>
				<tr>
				<tr>
					<td colspan="2">
						<table width="100%" border="0" cellspacing="0" cellpadding="7">
							<tbody>
								<tr>
									<td style="font-size: 9px">Sr No:</td>
									<td style="font-size: 10px"><b>${gHead.grngvnSrno}</b></td>
									<td style="font-size: 9px">Date:</td>

									<td style="font-size: 10px"><b>${gHead.grngvnDate}</b></td>
								</tr>
								<tr>
									<td colspan="4"><table width="100%" border="0"
											cellspacing="0" cellpadding="1" class="tbl-inner">
											<!--cellpading was 5   -->
											<tbody>
												<tr>
													<th width="30%" align="left" bgcolor="#ECECEC">HsnCode-itemName</th>
													<!-- <th width="30%" bgcolor="#ECECEC">Particular</th> -->
													<th width="14%" bgcolor="#ECECEC">Type-InvoiceNo</th>
													<th width="13%" bgcolor="#ECECEC">Rate-Qty</th>
													<th width="13%" align="right" bgcolor="#ECECEC">Cgst
														%-Rs</th>
													<th width="13%" align="right" bgcolor="#ECECEC">Sgst
														%-Rs</th>
													<th width="17%" bgcolor="#ECECEC">Amt</th>
												</tr>
												<c:set var="headVar" value="${gHead.grnGvnHeaderId}"></c:set>
												<c:forEach items="${resBean.ggDetailList}" var="detail"
													varStatus="count">
													<c:if test="${headVar == detail.grnGvnHeaderId}">
														<tr>

															<c:set var="taxableSum"
																value="${taxableSum+detail.taxableAmt}"></c:set>

															<c:set var="qtySum" value="${qtySum+detail.grnGvnQty}"></c:set>
															<c:set var="totalSum"
																value="${totalSum+ detail.grnGvnAmt}"></c:set>
															<c:set var="cgstRs"
																value="${detail.taxableAmt * detail.cgstPer/100}"></c:set>
															<c:set var="sgstRs"
																value="${detail.taxableAmt * detail.sgstPer/100}"></c:set>

															<c:set var="cgstValue" value="${cgstValue+cgstRs}"></c:set>
															<c:set var="sgstValue" value="${sgstValue+ sgstRs}"></c:set>


															<td><p style="font-size: 10px">${detail.itemHsncd}</p>
																<p style="font-size: 10px">${detail.itemName}</p></td>
															<c:set var="rate" value="aa"></c:set>

															<c:choose>
																<c:when test="${detail.isGrn==0}">
																	<c:set var="type" value="GVN"></c:set>
																	<c:set var="rate" value="${detail.itemRate}"></c:set>
																</c:when>
																<c:otherwise>
																	<c:choose>
																		<c:when test="${detail.grnType==0}">
																			<c:set var="type" value="GRN 1(75%)"></c:set>
																			<c:set var="rate"
																				value="${(detail.baseRate-(detail.baseRate*(detail.itemMrp/100)))* 0.75}"></c:set>

																		</c:when>
																		<c:when test="${detail.grnType==1}">
																			<c:set var="type" value="GRN 2(65%)"></c:set>
																			<c:set var="rate"
																				value="${(detail.baseRate-(detail.baseRate*(detail.itemMrp/100)))* 0.65}"></c:set>

																		</c:when>
																		<c:when
																			test="${detail.grnType==2 or detail.grnType==4}">
																			<c:set var="type" value="GRN 3(100%)"></c:set>
																			<c:set var="rate" value="${detail.baseRate}"></c:set>

																		</c:when>
																	</c:choose>
																</c:otherwise>
															</c:choose>

															<td align="center"><p style="font-size: 10px">${type}-${detail.invoiceNo}</p></td>
															<td align="center"><p style="font-size: 10px">
																	<fmt:formatNumber type="number" maxFractionDigits="2"
																		value="${rate}" />
																</p>
																<p style="font-size: 10px">
																	<fmt:formatNumber type="number" maxFractionDigits="2"
																		value="${detail.grnGvnQty}" />
																</p></td>

															<td align="center"><p style="font-size: 10px">${detail.cgstPer}</p>
																<p style="font-size: 10px">
																	<fmt:formatNumber type="number" maxFractionDigits="2"
																		value="${detail.taxableAmt * detail.cgstPer/100}" />
																</p></td>
															<td align="center"><p style="font-size: 10px">${detail.sgstPer}</p>
																<p style="font-size: 10px">
																	<fmt:formatNumber type="number" maxFractionDigits="2"
																		value="${detail.taxableAmt * detail.sgstPer/100}" />
																</p></td>

															<td align="right"><p style="font-size: 10px">
																	<fmt:formatNumber type="number" minFractionDigits="2"
																		maxFractionDigits="2" value="${detail.grnGvnAmt}" />
																</p></td>
														</tr>
													</c:if>


												</c:forEach>
												<tr>
													<!-- <td rowspan="3">&nbsp;</td> -->
													<td colspan="5" align="right"><span class="style7"><strong>Total
																Qty :</strong></span></td>
													<td align="right"><span class="style5" style="font-size: 14px;"><strong>${qtySum}</strong></span></td>
												</tr>


												<tr>
													<td colspan="5" align="right"><span class="style7">
															Taxable Value:</span></td>
													<td align="right"><span class="style7"><fmt:formatNumber
																type="number" maxFractionDigits="2"
																value="${taxableSum}" /></span></td>
												</tr>


												<tr>
													<td colspan="5" align="right"><span class="style7">
															Cgst Value:</span></td>
													<td align="right"><span class="style7"><fmt:formatNumber
																type="number" maxFractionDigits="2" value="${cgstValue}" /></span></td>
												</tr>

												<tr>
													<td colspan="5" align="right"><span class="style7">
															Sgst Value:</span></td>
													<td align="right"><span class="style7"><fmt:formatNumber
																type="number" maxFractionDigits="2" value="${sgstValue}" /></span></td>
												</tr>
												<tr>
													<!-- 	<td rowspan="3">&nbsp;</td> -->
													<td colspan="5" align="right"><span class="style7">
															Total:</span></td>
													<td align="right"><span class="style7"><fmt:formatNumber
																type="number" maxFractionDigits="2" value="${totalSum}" /></span></td>
												</tr>
											</tbody>

											<c:set var="rate" value="0"></c:set>
											<c:set var="qtySum" value="0"></c:set>
											<c:set var="taxableSum" value="0"></c:set>
											<c:set var="cgstValue" value="0"></c:set>
											<c:set var="sgstValue" value="0"></c:set>
											<c:set var="totalSum" value="0"></c:set>
										</table></td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>

				<tr>

					<td style="border-top: 1px solid #E7E7E7; padding: 5px 7px;"
						colspan="6">Goods Received By</span>
					</td>
				</tr>

				<tr></tr>
				<tr>

					<td style="border-top: 1px solid #E7E7E7; padding: 5px 7px;"
						colspan="2">Goods Checked By</span>
					</td>
				</tr>
				<tr>

					<td
						style="border-top: 1px solid #E7E7E7; padding: 20px 7px 2px 0px;">Authorised
						Sign</span>
					</td>

					<td
						style="border-top: 1px solid #E7E7E7; padding: 20px 7px 2px 0px;">Galdhar
						Foods Phone: 0240-2466217</span>
					</td>


				</tr>
			</tbody>
		</table>
		<br></br>

		<div style="border-bottom: 0px; border-style: dashed;"></div>
		<br></br>

	</c:forEach>
</body>
<body onload="directPrint()">
	<script>
		function directPrint() {

			window.print();
			//window.close();
		}
	</script>
</body>
</html>