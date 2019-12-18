<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Dispatch Report Pdf</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->



<style type="text/css">
table {
	border-collapse: collapse;
	font-size: 14;
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

<h4 align="center">MONGINIS</h4>
	
	
	
	<c:choose>

		<c:when test="${submit1==1}">

			<c:set var="totalRouteOrderQty" value="0"></c:set>
			<c:set var="totalRouteTrayQty" value="0"></c:set>
			<c:set var="frSubCount" value="0"></c:set>
			<c:forEach items="${routeListForFr}" var="routeList"
				varStatus="count">
				<div class="row">
					<div class="form-group">
						<label class="col-sm-3 col-lg-2	 control-label">Route Name
							: </label>
						<div class="col-sm-6 col-lg-4 controls date_select">
							${routeList.routeName}</div>

					</div>
				</div>
				<table class="table table-bordered  " style="width: 100%"
					id="table_gridFR" border="1">
					<thead style="background-color: #f3b5db;">
						<tr>
							<th>Sr.No.</th>
							<th width="60%">Franchisee Name</th>
							<th width="20%">SubCategory Name</th>
							<th width="10%">Qty</th>
							<th width="10%">Tray Qty</th>
						</tr>
					</thead>
					<tbody>


						<c:set var="totalOrderQty" value="0"></c:set>
						<c:set var="totalTrayQty" value="0"></c:set>

						<c:set var="totalOrderFRQty" value="0"></c:set>
						<c:set var="totalTrayFRQty" value="0"></c:set>

						<c:forEach items="${routeList.frList}"
							var="frNameIdByRouteIdList" varStatus="c1">

							<c:forEach items="${calListForFr}" var="calListForFr"
								varStatus="count">

								<c:choose>
									<c:when test="${frNameIdByRouteIdList.frId==calListForFr.frId}">

										<c:choose>
											<c:when
												test="${calListForFr.frRouteId==routeList.routeId}">



												<c:set var="totalOrderQty"
													value="${totalOrderQty+(calListForFr.orderQty)}"></c:set>

												<c:set var="totalTrayQty"
													value="${totalTrayQty+(calListForFr.trayQty)}"></c:set>

												<c:set var="frSubCount" value="${frSubCount+1}"></c:set>

												<tr>
													<td style="text-align: center">${frSubCount}</td>
													<td align="left"><c:out value="${calListForFr.frName}" /></td>
													<td align="left"><c:out
															value="${calListForFr.subCatName}" /></td>

													<td align="right"><c:out
															value="${calListForFr.orderQty}" /></td>
													<td align="right"><c:out
															value="${calListForFr.trayQty}" /></td>
												</tr>
											</c:when>
										</c:choose>
									</c:when>
								</c:choose>

							</c:forEach>


						</c:forEach>




						<c:set var="totalRouteOrderQty"
							value="${totalRouteOrderQty+totalOrderQty}"></c:set>
						<c:set var="totalRouteTrayQty"
							value="${totalRouteTrayQty+totalTrayQty}"></c:set>
						<tr>
							<td></td>
							<td></td>
							<td>Total</td>
							<td align="right"><c:out value="${totalOrderQty}" /></td>
							<td align="right"><c:out value="${totalTrayQty}" /></td>

						</tr>
					</tbody>
				</table>
			</c:forEach>
			<table class="table table-bordered  " style="width: 100%"
				id="table_grid12">
				<thead style="background-color: #f3b5db;">
				</thead>
				<tbody>
					<tr>
						<td width="60%"></td>
						<td width="20%">Total</td>
						<td width="10%" align="right"><c:out
								value="${totalRouteOrderQty}" /></td>
						<td width="10%" align="right"><c:out
								value="${totalRouteTrayQty}" /></td>
					</tr>
				</tbody>
			</table>


		</c:when>

		<c:when test="${submit2==2}">

			<c:forEach items="${routeListForFr}" var="routeList"
				varStatus="count">
				<div class="row">
					<div class="form-group">
						<label class="col-sm-3 col-lg-2	 control-label">Route Name
							: </label>
						<div class="col-sm-6 col-lg-4 controls date_select">
							${routeList.routeName}</div>

					</div>
				</div>
				<table class="table table-bordered  " style="width: 100%"
					id="table_gridFR2" border="1">
					<thead style="background-color: #f3b5db;">
						<tr>
							<th width="10%">Sr.No.</th>
							<th width="70%">Franchisee Name</th>
							<th width="10%">Qty</th>
							<th width="10%">Tray Qty</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="totalFRFinalOrderQty" value="0" />
						<c:set var="totalFRFinalTrayQty" value="0" />
						<c:set var="frCount" value="0"></c:set>


						<c:forEach items="${routeList.frList}"
							var="frNameIdByRouteIdList" varStatus="count">
							<c:set var="totalFROrderQty" value="0" />
							<c:set var="totalFRTrayQty" value="0" />


							<c:forEach items="${calListForFr}" var="calListForFr"
								varStatus="cnt">

								<c:choose>
									<c:when test="${calListForFr.frId==frNameIdByRouteIdList.frId}">

										<c:set var="totalFROrderQty"
											value="${totalFROrderQty+calListForFr.orderQty}" />

										<c:set var="totalFRTrayQty"
											value="${totalFRTrayQty+calListForFr.trayQty}" />
									</c:when>
								</c:choose>

							</c:forEach>
							<c:set var="frCount" value="${frCount+1}"></c:set>

							<c:set var="totalFRFinalOrderQty"
								value="${totalFROrderQty+totalFRFinalOrderQty}" />

							<c:set var="totalFRFinalTrayQty"
								value="${totalFRTrayQty+totalFRFinalTrayQty}" />



							<tr>
								<td style="text-align: center">${frCount}</td>
								<td align="left"><c:out
										value="${frNameIdByRouteIdList.frName}" /></td>



								<td align="right"><c:out value="${totalFROrderQty}" /></td>
								<td align="right"><c:out value="${totalFRTrayQty}" /></td>


							</tr>
						</c:forEach>

						<tr>
							<td></td>
							<td>Total</td>

							<td align="right"><c:out value="${totalFRFinalOrderQty}" /></td>
							<td align="right"><c:out value="${totalFRFinalTrayQty}" /></td>


						</tr>




					</tbody>
				</table>
			</c:forEach>
			<%-- <button class="btn btn-primary" value="2" id="submit2" name="submit2"
				onclick="genPdf2()">PDF</button>
			<input id="date" name="date" value="${date}" type="hidden">
			<input id="routeIds" name="routeIds" value="${routeIds}"
				type="hidden">
			<input id="menuIds" name="menuIds" value="${menuIds}" type="hidden"> --%>


		</c:when>




		<c:when test="${submit3==3}">

			<table class="table table-bordered  " style="width: 100%"
				id="table_gridFR3" border="1">
				<thead style="background-color: #f3b5db;">
					<tr>
						<th>Sr.No.</th>
						<th>Route Name</th>
						<th>Qty</th>
						<th>Tray Qty</th>

					</tr>
				</thead>
				<tbody>

					<c:set var="FinalTotalOrderQty" value="0" />
					<c:set var="FinalTotalTrayQty" value="0" />



					<c:forEach items="${routeListForFr}" var="routeList"
						varStatus="count">
						<c:set var="totalROrderQty" value="0" />
						<c:set var="totalRTrayQty" value="0" />
						<c:forEach items="${calListForFr}" var="calListForFr"
							varStatus="cnt">

							<c:choose>
								<c:when test="${calListForFr.frRouteId==routeList.routeId}">

									<c:set var="totalROrderQty"
										value="${totalROrderQty+calListForFr.orderQty}" />

									<c:set var="totalRTrayQty"
										value="${totalRTrayQty+calListForFr.trayQty}" />
								</c:when>
							</c:choose>




						</c:forEach>
						<c:set var="FinalTotalOrderQty"
							value="${totalROrderQty+FinalTotalOrderQty}" />

						<c:set var="FinalTotalTrayQty"
							value="${totalRTrayQty+FinalTotalTrayQty}" />



						<tr>
							<td width="10%" style="text-align: center">${count.index+1}</td>
							<td width="70%" align="left"><c:out
									value="${routeList.routeName}" /></td>

							<td width="10%" align="right"><c:out
									value="${totalROrderQty}" /></td>
							<td width="10%" align="right"><c:out
									value="${totalRTrayQty}" /></td>


						</tr>
					</c:forEach>

					<tr>
						<td width="10%"></td>
						<td width="70%">Total</td>

						<td width="10%" align="right"><c:out
								value="${FinalTotalOrderQty}" /></td>
						<td width="10%" align="right"><c:out
								value="${FinalTotalTrayQty}" /></td>


					</tr>





				</tbody>
			</table>

		</c:when>


		<c:when test="${submit4==4}">

			<c:set var="totalSubOrderQtyFinal" value="0"></c:set>
			<c:set var="totalRouteTrayQtyFinal" value="0"></c:set>

			<table class="table table-bordered  " style="width: 100%"
				id="table_gridFR4" border="1">
				<thead style="background-color: #f3b5db;">
					<tr>
						<th>Sr.No.</th>
						<th>Route Name</th>
						<th>SubCategory Name</th>
						<th>Qty</th>
						<th>Tray Qty</th>


					</tr>
				</thead>
				<tbody>



					<c:forEach items="${routeListForFr}" var="routeList"
						varStatus="count">
						<c:set var="SubOrderQty" value="0"></c:set>
						<c:set var="SubTrayQty" value="0"></c:set>
						<c:set var="srNo" value="0"></c:set>
						<c:forEach items="${subCatAList}" var="subCatAList" varStatus="c1">



							<c:set var="totalSubOrderQty" value="0"></c:set>
							<c:set var="totalSubTrayQty" value="0"></c:set>

							<c:forEach items="${calListForFr}" var="calListForFr"
								varStatus="cnt">
								<c:choose>
									<c:when test="${routeList.routeId==calListForFr.frRouteId}">
										<c:choose>
											<c:when test="${subCatAList.subCatId==calListForFr.subCatId}">
												<c:set var="totalSubOrderQty"
													value="${totalSubOrderQty+calListForFr.orderQty}" />

												<c:set var="totalSubTrayQty"
													value="${totalSubTrayQty+calListForFr.trayQty}" />
											</c:when>
										</c:choose>
									</c:when>
								</c:choose>


							</c:forEach>



							<tr>
								<c:choose>
									<c:when test="${totalSubOrderQty>0}">
										<c:set var="srNo" value="${srNo+1}"></c:set>


										<td width="5%" style="text-align: center">${srNo}</td>
										<td width="50%" align="left"><c:choose>
												<c:when test="${srNo==1}">
													<c:out value="${routeList.routeName}" />
												</c:when>
												<c:otherwise>

												</c:otherwise>
											</c:choose></td>
										<td width="25%" align="left"><c:out
												value="${subCatAList.subCatName}" /></td>

										<td width="10%" align="right"><c:out
												value="${totalSubOrderQty}" /></td>
										<td width="10%" align="right"><c:out
												value="${totalSubTrayQty}" /></td>

										<c:set var="SubOrderQty"
											value="${totalSubOrderQty+SubOrderQty}" />

										<c:set var="SubTrayQty" value="${totalSubTrayQty+SubTrayQty}" />
									</c:when>
								</c:choose>

							</tr>
						</c:forEach>

						<c:set var="totalSubOrderQtyFinal"
							value="${totalSubOrderQtyFinal+SubOrderQty}" />

						<c:set var="totalRouteTrayQtyFinal"
							value="${totalRouteTrayQtyFinal+SubTrayQty}" />
						<c:choose>
							<c:when test="${SubOrderQty>0}">
								<tr>



									<td width="5%" style="text-align: center"></td>
									<td width="50%" align="left"></td>
									<td width="25%" align="left"><b>Total</b></td>

									<td width="10%" align="right"><b><c:out
												value="${SubOrderQty}" /></b></td>
									<td width="10%" align="right"><b><c:out
												value="${SubTrayQty}" /></b></td>

								</tr>
							</c:when>
						</c:choose>

					</c:forEach>
				</tbody>
			</table>
			<table class="table table-bordered  " style="width: 100%"
				id="table_grid" border="1">
				<thead style="background-color: #f3b5db;">
				</thead>
				<tbody>
					<tr>

						<td width="80%"><b>Total</b></td>
						<td width="10%" align="right"><b><c:out
									value="${totalSubOrderQtyFinal}" /></b></td>
						<td width="10%" align="right"><b><c:out
									value="${totalRouteTrayQtyFinal}" /></b></td>

					</tr>

				</tbody>
			</table>


		</c:when>
	</c:choose>




	<div style="page-break-after: always;"></div>
</body>
</html>