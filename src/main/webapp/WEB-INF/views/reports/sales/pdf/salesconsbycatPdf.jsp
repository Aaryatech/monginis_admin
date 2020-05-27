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
<title>Sales Report Royalty</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->



<style type="text/css">
table {
	border-collapse: collapse;
	font-size: 10;
	width: 100%;
	page-break-inside: auto !important
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
	<%-- <h4 align="center">${FACTORYNAME}</h4>
	<p align="center">${FACTORYADDRESS}</p> --%>
	<div align="center">
		<h5>Sales Royalty Consolidated Report (Category/Item wise)
			&nbsp;&nbsp;&nbsp;&nbsp; From &nbsp; ${fromDate} &nbsp;To &nbsp;
			${toDate}</h5>
	</div>

	<%-- <div align="center">
		<h5>Franchisee :- &nbsp;&nbsp;&nbsp;&nbsp; ${fr}</h5>
	</div> --%>


	<%-- <div align="center">
		<h5>
			By :- &nbsp;&nbsp;&nbsp;&nbsp;
			<c:choose>
				<c:when test="${getBy==1}">
		Taxable Amount
		</c:when>
				<c:when test="${getBy==2}">
		Grand Total
		</c:when>
			</c:choose>

			&nbsp;&nbsp;and&nbsp;&nbsp;

			<c:choose>
				<c:when test="${type==1}">
			GRN
			</c:when>
				<c:when test="${type==2}">
			CRN
			</c:when>
			</c:choose>
		</h5>
	</div> --%>

	<table align="center" border="1" cellspacing="0" cellpadding="1"
		id="table_grid" class="table table-bordered">
		<thead>
			<tr class="bgpink">
				<th height="25">Sr.No.</th>
				<th>Item Name</th>
				<th>Sale Qty</th>
				<th>Sale Value</th>
				<th>GRN Qty</th>
				<th>GRN Value</th>
				<th>GVN Qty</th>
				<th>GVN Value</th>
				<th>Net Qty</th>
				<th>Net Value</th>
				<th>Royalty %</th>
				<th>Royalty Value</th>
				<th>Return % GRN</th>
				<th>Return % GVN</th>
				<th>Return % SUM</th>
				<th>Contribution %</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="sumSaleQty" value="${0}" />
			<c:set var="sumSaleValue" value="${0}" />
			<c:set var="sumGrnQty" value="${0}" />
			<c:set var="sumGrnValue" value="${0}" />
			<c:set var="sumGvnQty" value="${0}" />
			<c:set var="sumGvnValue" value="${0}" />
			<c:set var="sumNetQty" value="${0}" />
			<c:set var="sumNetValue" value="${0}" />
			<c:set var="rAmtValue" value="${0}" />
			<c:set var="contriTotal" value="${0}" />

			<c:set var="netTotalContri" value="${0}" />
			<c:forEach items="${royaltyList.salesReportRoyalty}" var="royalty"
				varStatus="count">

				<c:set var="billVal" value="0"></c:set>
				<c:set var="grnVal" value="0"></c:set>
				<c:set var="gvnVal" value="0"></c:set>

				<c:choose>
					<c:when test="${getBy==1}">

						<c:set var="billVal" value="${royalty.taxableAmt}"></c:set>

						<c:choose>
							<c:when test="${type==1}">
								<c:set var="grnVal" value="${royalty.grnTaxableAmt}"></c:set>
								<c:set var="gvnVal" value="${royalty.gvnTaxableAmt}"></c:set>
							</c:when>

							<c:otherwise>
								<c:set var="grnVal" value="${royalty.crnGrnTaxableAmt}"></c:set>
								<c:set var="gvnVal" value="${royalty.crnGvnTaxableAmt}"></c:set>
							</c:otherwise>
						</c:choose>

					</c:when>

					<c:otherwise>

						<c:set var="billVal" value="${royalty.grandTotal}"></c:set>

						<c:choose>
							<c:when test="${type==1}">
								<c:set var="grnVal" value="${royalty.grnGrandTotal}"></c:set>
								<c:set var="gvnVal" value="${royalty.gvnGrandTotal}"></c:set>
							</c:when>

							<c:otherwise>
								<c:set var="grnVal" value="${royalty.crnGrnGrandTotal}"></c:set>
								<c:set var="gvnVal" value="${royalty.crnGvnGrandTotal}"></c:set>
							</c:otherwise>
						</c:choose>

					</c:otherwise>

				</c:choose>

				<c:set var="netValue" value="${billVal -(grnVal+gvnVal)}"></c:set>
				<c:set var="netTotalContri" value="${netTotalContri+netValue}"></c:set>

			</c:forEach>

			<c:forEach items="${royaltyList.categoryList}" var="report"
				varStatus="count">


				<c:set var="sumSaleQtyCat" value="${0}" />
				<c:set var="sumSaleValueCat" value="${0}" />
				<c:set var="sumGrnQtyCat" value="${0}" />
				<c:set var="sumGrnValueCat" value="${0}" />
				<c:set var="sumGvnQtyCat" value="${0}" />
				<c:set var="sumGvnValueCat" value="${0}" />
				<c:set var="sumNetQtyCat" value="${0}" />
				<c:set var="sumNetValueCat" value="${0}" />
				<c:set var="rAmtValueCat" value="${0}" />


				<tr>

					<td><b>${report.catName}</b></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>

					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>

					<%-- <td><c:out value="${total}" /></td> --%>

					<%-- <td><fmt:formatNumber type="number"
								maxFractionDigits="2" value="${total}" /></td> --%>
				</tr>




				<c:set var="srNo" value="1"></c:set>
				<c:forEach items="${royaltyList.salesReportRoyalty}" var="royalty"
					varStatus="count">

					<c:choose>
						<c:when test="${royalty.catId==report.catId}">

							<c:set var="billQty" value="${royalty.billQty}"></c:set>
							<c:set var="billVal" value="0"></c:set>

							<c:set var="grnQty" value="0"></c:set>
							<c:set var="grnVal" value="0"></c:set>

							<c:set var="gvnQty" value="0"></c:set>
							<c:set var="gvnVal" value="0"></c:set>

							<c:choose>
								<c:when test="${getBy==1}">

									<c:set var="billVal" value="${royalty.taxableAmt}"></c:set>

									<c:choose>
										<c:when test="${type==1}">
											<c:set var="grnVal" value="${royalty.grnTaxableAmt}"></c:set>
											<c:set var="gvnVal" value="${royalty.gvnTaxableAmt}"></c:set>
										</c:when>

										<c:otherwise>
											<c:set var="grnVal" value="${royalty.crnGrnTaxableAmt}"></c:set>
											<c:set var="gvnVal" value="${royalty.crnGvnTaxableAmt}"></c:set>
										</c:otherwise>
									</c:choose>

								</c:when>

								<c:otherwise>

									<c:set var="billVal" value="${royalty.grandTotal}"></c:set>

									<c:choose>
										<c:when test="${type==1}">
											<c:set var="grnVal" value="${royalty.grnGrandTotal}"></c:set>
											<c:set var="gvnVal" value="${royalty.gvnGrandTotal}"></c:set>
										</c:when>

										<c:otherwise>
											<c:set var="grnVal" value="${royalty.crnGrnGrandTotal}"></c:set>
											<c:set var="gvnVal" value="${royalty.crnGvnGrandTotal}"></c:set>
										</c:otherwise>
									</c:choose>

								</c:otherwise>

							</c:choose>

							<c:choose>
								<c:when test="${type==1}">
									<c:set var="grnQty" value="${royalty.grnQty}"></c:set>
									<c:set var="gvnQty" value="${royalty.gvnQty}"></c:set>
								</c:when>

								<c:when test="${type==2}">
									<c:set var="grnQty" value="${royalty.crnGrnQty}"></c:set>
									<c:set var="gvnQty" value="${royalty.crnGvnQty}"></c:set>
								</c:when>
							</c:choose>

							<c:if test="${billVal>0 || grnVal>0 || gvnVal>0 }">

								<tr>
									<td width="100"><c:out value="${srNo}" /></td>
									<c:set var="srNo" value="${srNo+1}"></c:set>
									<td width="250"><c:out value="${royalty.item_name}" /></td>
									<td width="100" align="right"><fmt:formatNumber
											type="number" maxFractionDigits="2" minFractionDigits="2"
											value="${billQty}" /></td>
									<%-- <td><c:out value="${royalty.tBillTaxableAmt}" /></td> --%>



									<td width="100" align="right"><fmt:formatNumber
											type="number" maxFractionDigits="2" minFractionDigits="2"
											value="${billVal}" /></td>

									<td width="100" align="right"><fmt:formatNumber
											type="number" maxFractionDigits="2" minFractionDigits="2"
											value="${grnQty}" /></td>

									<%-- 								<td><c:out value="${royalty.tGrnTaxableAmt}" /></td>
 --%>
									<td width="100" align="right"><fmt:formatNumber
											type="number" maxFractionDigits="2" minFractionDigits="2"
											value="${grnVal}" /></td>

									<td width="100" align="right"><fmt:formatNumber
											type="number" maxFractionDigits="2" minFractionDigits="2"
											value="${gvnQty}" /></td>

									<%-- 								<td><c:out value="${royalty.tGvnTaxableAmt}" /></td>
 --%>
									<td width="100" align="right"><fmt:formatNumber
											type="number" maxFractionDigits="2" minFractionDigits="2"
											value="${gvnVal}" /></td>
									<c:set var="netQty" value="${billQty -(grnQty+gvnQty)}"></c:set>

									<c:set var="netValue" value="${billVal -(grnVal+gvnVal)}"></c:set>

									<td width="100" align="right"><fmt:formatNumber
											type="number" maxFractionDigits="2" minFractionDigits="2"
											value="${netQty}" /></td>

									<td width="100" align="right"><fmt:formatNumber
											type="number" maxFractionDigits="2" minFractionDigits="2"
											value="${netValue}" /></td>
									<c:set var="rAmt" value="${netValue* royPer/ 100}"></c:set>
									<td width="100" align="right"><fmt:formatNumber
											type="number" maxFractionDigits="2" minFractionDigits="2"
											value="${royPer}" /></td>
									<td width="100" align="right"><fmt:formatNumber
											type="number" maxFractionDigits="2" minFractionDigits="2"
											value="${rAmt}" /></td>



									<c:set var="grnRet" value="0"></c:set>
									<c:set var="gvnRet" value="0"></c:set>
									<c:set var="sumRet" value="0"></c:set>


									<c:if test="${billVal>0}">
										<c:set var="grnRet" value="${(grnVal*100)/billVal}"></c:set>
										<c:set var="gvnRet" value="${(gvnVal*100)/billVal}"></c:set>
										<c:set var="sumRet" value="${((grnVal+gvnVal)*100)/billVal}"></c:set>

									</c:if>


									<td width="100" align="right"><fmt:formatNumber
											type="number" maxFractionDigits="2" minFractionDigits="2"
											value="${grnRet}" /></td>

									<td width="100" align="right"><fmt:formatNumber
											type="number" maxFractionDigits="2" minFractionDigits="2"
											value="${gvnRet}" /></td>

									<td width="100" align="right"><fmt:formatNumber
											type="number" maxFractionDigits="2" minFractionDigits="2"
											value="${sumRet}" /></td>


									<c:set var="contri" value="${(netValue*100)/netTotalContri}"></c:set>

									<td width="100" align="right"><fmt:formatNumber
											type="number" maxFractionDigits="2" minFractionDigits="2"
											value="${contri}" /></td>

									<c:set var="contriTotal" value="${contriTotal+contri}"></c:set>


									<c:set var="sumSaleQtyCat" value="${billQty+sumSaleQtyCat}"></c:set>
									<c:set var="sumSaleValueCat" value="${billVal+sumSaleValueCat}"></c:set>

									<c:set var="sumGrnQtyCat" value="${grnQty+sumGrnQtyCat}"></c:set>

									<c:set var="sumGrnValueCat" value="${grnVal+sumGrnValueCat}"></c:set>

									<c:set var="sumGvnQtyCat" value="${gvnQty+sumGvnQtyCat}"></c:set>

									<c:set var="sumGvnValueCat" value="${gvnVal+sumGvnValueCat}"></c:set>

									<c:set var="sumNetQtyCat" value="${sumNetQtyCat+netQty}"></c:set>


									<c:set var="sumNetValueCat" value="${netValue+sumNetValueCat}"></c:set>
									<c:set var="rAmtValueCat" value="${rAmt+rAmtValueCat}"></c:set>


									<c:set var="sumSaleQty" value="${billQty+sumSaleQty}"></c:set>
									<c:set var="sumSaleValue" value="${billVal+sumSaleValue}"></c:set>

									<c:set var="sumGrnQty" value="${grnQty+sumGrnQty}"></c:set>

									<c:set var="sumGrnValue" value="${grnVal+sumGrnValue}"></c:set>

									<c:set var="sumGvnQty" value="${gvnQty+sumGvnQty}"></c:set>

									<c:set var="sumGvnValue" value="${gvnVal+sumGvnValue}"></c:set>

									<c:set var="sumNetQty" value="${sumNetQty+netQty}"></c:set>


									<c:set var="sumNetValue" value="${netValue+sumNetValue}"></c:set>
									<c:set var="rAmtValue" value="${rAmt+rAmtValue}"></c:set>

								</tr>
							</c:if>
						</c:when>
					</c:choose>


				</c:forEach>

				<tr>

					<td colspan='2'><b>Total</b></td>
					<td width="100" align="right"><b><fmt:formatNumber
								type="number" maxFractionDigits="2" minFractionDigits="2"
								value="${sumSaleQtyCat}" /></b></td>
					<td width="100" align="right"><b><fmt:formatNumber
								type="number" maxFractionDigits="2" minFractionDigits="2"
								value="${sumSaleValueCat}" /></b></td>
					<td width="100" align="right"><b><fmt:formatNumber
								type="number" maxFractionDigits="2" minFractionDigits="2"
								value="${sumGrnQtyCat}" /></b></td>
					<td width="100" align="right"><b><fmt:formatNumber
								type="number" maxFractionDigits="2" minFractionDigits="2"
								value="${sumGrnValueCat}" /></b></td>


					<td width="100" align="right"><b><fmt:formatNumber
								type="number" maxFractionDigits="2" minFractionDigits="2"
								value="${sumGvnQtyCat}" /></b></td>

					<td width="100" align="right"><b><fmt:formatNumber
								type="number" maxFractionDigits="2" minFractionDigits="2"
								value="${sumGvnValueCat}" /></b></td>


					<td width="100" align="right"><b><fmt:formatNumber
								type="number" maxFractionDigits="2" minFractionDigits="2"
								value="${sumNetQtyCat}" /></b></td>


					<td width="100" align="right"><b><fmt:formatNumber
								type="number" maxFractionDigits="2" minFractionDigits="2"
								value="${sumNetValueCat}" /></b></td>
					<td width="100" align="right">-</td>
					<td width="100" align="right"><b><fmt:formatNumber
								type="number" maxFractionDigits="2" minFractionDigits="2"
								value="${rAmtValueCat}" /></b></td>


					<c:set var="grnRet" value="${(sumGrnValueCat*100)/sumSaleValueCat}"></c:set>
					<c:set var="gvnRet" value="${(sumGvnValueCat*100)/sumSaleValueCat}"></c:set>
					<c:set var="sumRet"
						value="${((sumGrnValueCat+sumGvnValueCat)*100)/sumSaleValueCat}"></c:set>

					<td width="100" align="right"><b><fmt:formatNumber
								type="number" maxFractionDigits="2" minFractionDigits="2"
								value="${grnRet}" /></b></td>

					<td width="100" align="right"><b><fmt:formatNumber
								type="number" maxFractionDigits="2" minFractionDigits="2"
								value="${gvnRet}" /></b></td>

					<td width="100" align="right"><b><fmt:formatNumber
								type="number" maxFractionDigits="2" minFractionDigits="2"
								value="${sumRet}" /></b></td>

					<td width="100" align="right"><b><fmt:formatNumber
								type="number" maxFractionDigits="2" minFractionDigits="2"
								value="" /></b></td>



					<%-- <td></td>
				<td><b><fmt:formatNumber type="number"
							maxFractionDigits="2" value="${sumNetValue}" /></b></td> --%>
					<!--  <td><b>Total</b></td> -->
				</tr>



			</c:forEach>
			<tr>

				<td colspan='2'><b>Total</b></td>
				<td width="100" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${sumSaleQty}" /></b></td>
				<td width="100" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${sumSaleValue}" /></b></td>
				<td width="100" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${sumGrnQty}" /></b></td>
				<td width="100" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${sumGrnValue}" /></b></td>


				<td width="100" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${sumGvnQty}" /></b></td>

				<td width="100" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${sumGvnValue}" /></b></td>


				<td width="100" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${sumNetQty}" /></b></td>


				<td width="100" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${sumNetValue}" /></b></td>
				<td width="100" align="right">-</td>

				<td width="100" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${rAmtValue}" /></b></td>

				<c:set var="grnRet" value="${(sumGrnValue*100)/sumSaleValue}"></c:set>
				<c:set var="gvnRet" value="${(sumGvnValue*100)/sumSaleValue}"></c:set>
				<c:set var="sumRet"
					value="${((sumGrnValue+sumGvnValue)*100)/sumSaleValue}"></c:set>

				<td width="100" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${grnRet}" /></b></td>

				<td width="100" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${gvnRet}" /></b></td>

				<td width="100" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${sumRet}" /></b></td>

				<td width="100" align="right"><b><fmt:formatNumber
							type="number" maxFractionDigits="2" minFractionDigits="2"
							value="${contriTotal}" /></b></td>





				<%-- <td></td>
				<td><b><fmt:formatNumber type="number"
							maxFractionDigits="2" value="${sumNetValue}" /></b></td> --%>
				<!--  <td><b>Total</b></td> -->
			</tr>
		</tbody>
	</table>


	<!-- END Main Content -->

</body>
</html>