<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<body>
	<c:url var="insertStoreGvnProcessAgree"
		value="/insertStoreGvnProcessAgree" />

	<c:url var="insertStoreGvnProcessDisAgree"
		value="/insertStoreGvnProcessDisAgree" />
		
		<c:url var="getDateForGvnStore"
		value="/getDateForGvnStore" />
		
	<div class="container" id="main-container">

		<!-- BEGIN Sidebar -->
		<div id="sidebar" class="navbar-collapse collapse">

			<jsp:include page="/WEB-INF/views/include/navigation.jsp"></jsp:include>

			<div id="sidebar-collapse" class="visible-lg">
				<i class="fa fa-angle-double-left"></i>
			</div>
			<!-- END Sidebar Collapse Button -->
		</div>
		<!-- END Sidebar -->

		<!-- BEGIN Content -->
		<div id="main-content">
			<!-- BEGIN Page Title -->
			<div class="page-title">
				<div>
					<h1>
						<i class="fa fa-file-o"></i>GVN
					</h1>

				</div>
			</div>
			<!-- END Page Title -->



			<!-- BEGIN Main Content -->
			<div class="row">
				<div class="col-md-12">
					<div class="box">
						<div class="box-title">
							<h3>
								<i class="fa fa-bars"></i> Sale GVN<!-- Store GVN  -->
							</h3>
							<div class="box-tool">
								<a href="">Back to List</a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>
						<div class="box-content">
							<form
								action="${pageContext.request.contextPath}/insertStoreGvnByCheckBoxes"
								class="form-horizontal" method="post" id="validation-form">

								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i> GVN List Date-${grnDate} srNo-${srNo}
										</h3>
										<div class="box-tool">
											<a data-action="collapse" href="#"><i
												class="fa fa-chevron-up"></i></a>
											<!--<a data-action="close" href="#"><i class="fa fa-times"></i></a>-->
										</div>
									</div>

									<div class="box-content">

										<div class="clearfix"></div>
										<div class="table-responsive" style="border: 0">
											<table width="100%"
												class="table table-advance table-responsive table-position" id="table1">
												<thead>
													<tr>
														<th width="30"></th>
														<th width="50" style="width: 18px" align="left">Sr No</th>
														<th width="100" align="left">Invoice No</th>
														<th width="120" align="left">Franchise Name</th>
														<th width="120" align="left">Item Name</th>
														<th width="100" align="left">GVN Qty</th>
														<th width="100" align="left">Apr Qty Dispatch</th>
														
														<th width="100">Edited Qty</th>
														<th width="100" align="left">PHOTO 1</th>
														<th width="100" align="left">PHOTO 2</th>
														<th width="100" align="left">Status</th>
														<th width="100" align="left">Action</th>
													</tr>

												</thead>
												<tbody>
													<c:forEach items="${gvnList}" var="gvnList"
														varStatus="count">

														<c:choose>
															<c:when
																test="${gvnList.aprQtyGate!=gvnList.grnGvnQty}">

																<c:set var="color" value="red"></c:set>
															</c:when>
															<c:otherwise>
																<c:set var="color" value="white"></c:set>
															</c:otherwise>
														</c:choose>

														<tr bgcolor="${color}">
															<c:choose>
																<c:when test="${gvnList.grnGvnStatus==2}">
																	<td><input type="checkbox" name="select_to_agree"
																		 id="${gvnList.grnGvnId}"
																		value="${gvnList.grnGvnId}"></></td>

																</c:when>
																<c:when test="${gvnList.grnGvnStatus==4}">
																	<td><input type="checkbox" name="select_to_agree" disabled="disabled"
																		id="${gvnList.grnGvnId}" value="${gvnList.grnGvnId}"></></td>

																</c:when>

																<c:when test="${gvnList.grnGvnStatus==5}">
																	<td><input type="checkbox" name="select_to_agree"
																		id="${gvnList.grnGvnId}" value="${gvnList.grnGvnId}"></></td>


																</c:when>

																<c:otherwise>

																	<td><input type="checkbox" name="select_to_agree"
																		disabled="disabled" id="${gvnList.grnGvnId}"
																		value="${gvnList.grnGvnId}"></></td>


																</c:otherwise>
															</c:choose>
															<td><c:out value="${count.index+1}" /></td>

															<td align="left"><c:out value="${gvnList.invoiceNo}" /></td>

															<td align="left"><c:out value="${gvnList.frName}" /></td>


															<td align="left"><c:out value="${gvnList.itemName}" /></td>


															<td align="left"><c:out value="${gvnList.grnGvnQty}" />
															
																<td align="left"><c:out value="${gvnList.aprQtyGate}" />
																<input type="hidden"
																name="approve_store_login${gvnList.grnGvnId}"
																id="approve_store_login${gvnList.grnGvnId}"
																value="${gvnList.approvedLoginStore}" /></td>
																
																<c:set var="qty" value="0"></c:set>


																
															<c:choose>

																<c:when
																	test="${gvnList.grnGvnStatus==1 or gvnList.grnGvnStatus==2 or gvnList.grnGvnStatus==3}">
																	<c:set var="qty" value="${gvnList.aprQtyGate}" />
																</c:when>

																<c:otherwise>
																	<c:set var="qty" value="${gvnList.aprQtyStore}" />
																</c:otherwise>

																
															</c:choose>
																
																<%-- <td><input type="text" name="store_gvn_qty${gvnList.grnGvnId}" style="width: 50px;" class="form-control"
															id='store_gvn_qty${gvnList.grnGvnId}' value="${qty}" onkeypress="checkQty(${gvnList.grnGvnId},${gvnList.grnGvnQty},${gvnList.aprQtyStore},${qty})"/></td>
																 --%>
																 
																 <td><input type="text" name="store_gvn_qty${gvnList.grnGvnId}" style="width: 50px;" class="form-control"
															id='store_gvn_qty${gvnList.grnGvnId}' value="${qty}" onkeyup="return validateQty(${gvnList.grnGvnId},${gvnList.grnGvnQty},${gvnList.aprQtyStore},${qty})"/></td>
																

															<td><a href="${url}${gvnList.gvnPhotoUpload1}"data-lightbox="image-1" >Image 1</a>
																							</td>

															<td><a href="${url}${gvnList.gvnPhotoUpload2}"data-lightbox="image-1" >Image 2</a>

															<c:choose>
																<c:when test="${gvnList.grnGvnStatus==1}">
																	<td align="left"><c:out value="Pending"></c:out></td>

																</c:when>

																<c:when test="${gvnList.grnGvnStatus==2}">
																	<td align="left"><c:out value="Approved From Dispatch"></c:out></td>

																</c:when>

																<c:when test="${gvnList.grnGvnStatus==3}">
																	<td align="left"><c:out value="Reject From Dispatch"></c:out></td>

																</c:when>

																<c:when test="${gvnList.grnGvnStatus==4}">
																	<td align="left"><c:out value="Approved From Sales"></c:out></td>

																</c:when>

																<c:when test="${gvnList.grnGvnStatus==5}">
																	<td align="left"><c:out value="Reject From Sales"></c:out></td>

																</c:when>

																<c:when test="${gvnList.grnGvnStatus==6}">
																	<td align="left"><c:out value="Approved From Account"></c:out></td>

																</c:when>

																<c:when test="${gvnList.grnGvnStatus==7}">
																	<td align="left"><c:out value="Reject From Account"></c:out></td>

																</c:when>

															</c:choose>

															<c:choose>
																<c:when test="${gvnList.grnGvnStatus==2}">

																	<td>
																	
																	<ul class="table-menu">

																			<li><a href="" id="callSubmit"
																				onclick="insertGrnCall(${gvnList.grnGvnId})"> <i
																					class="fa fa-check"></i></a></li>

																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#"
																						data-toggle="dropdown"><i class="fa fa-times"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">

																							<select name="store_remark${gvnList.grnGvnId}"
																								id="store_remark${gvnList.grnGvnId}"
																								class="form-control">
																								<c:forEach items="${remarkList}"
																									var="remarkList">
																									<option value="${remarkList.remark}">${remarkList.remark}</option>
																								</c:forEach>
																							</select> </br> <input class="btn btn-primary" value="Submit"
																								onclick="insertGrnDisAgree(${gvnList.grnGvnId})" />

																						</div>
																					</div>
																				</div>
																			</li>
																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#"
																						data-toggle="dropdown"><i
																						class="fa fa-info-circle"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">
																							Franchisee Remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control"><c:out value="${gvnList.frGrnGvnRemark}"></c:out></textarea>

																						</div>
																					</div>
																				</div>
																			</li>

																		</ul>
																		</td>

																</c:when>



																<c:when test="${gvnList.grnGvnStatus==4}">

																	<td>
																	<ul class="table-menu">

																			<li><a href="" id="callSubmit" class="disableClick"
																				onclick="insertGrnCall(${gvnList.grnGvnId})"> <i
																					class="fa fa-check"></i></a></li>

																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#" 
																						data-toggle="dropdown"><i class="fa fa-times"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">

																							<select name="store_remark${gvnList.grnGvnId}"
																								id="store_remark${gvnList.grnGvnId}"
																								class="form-control">
																								<c:forEach items="${remarkList}"
																									var="remarkList">
																									<option value="${remarkList.remark}">${remarkList.remark}</option>
																								</c:forEach>
																							</select> </br> <input class="btn btn-primary" value="Submit"
																								onclick="insertGrnDisAgree(${gvnList.grnGvnId})" />

																						</div>
																					</div>
																				</div>
																			</li>
																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#"
																						data-toggle="dropdown"><i
																						class="fa fa-info-circle"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">
																							Franchisee Remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control"><c:out value="${gvnList.frGrnGvnRemark}"></c:out></textarea>

																						</div>
																					</div>
																				</div>
																			</li>

																		</ul>
																		
																		</td>

																</c:when>


																<c:when test="${gvnList.grnGvnStatus==5}">

																	<td>
																																			 
																		 <ul class="table-menu">

																			<li><a href="" id="callSubmit"
																				onclick="insertGrnCall(${gvnList.grnGvnId})"> <i
																					class="fa fa-check"></i></a></li>

																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#" id="disableMe"
																						data-toggle="dropdown"><i class="fa fa-times"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">

																							<select name="store_remark${gvnList.grnGvnId}"
																								id="store_remark${gvnList.grnGvnId}"
																								class="form-control">
																								<c:forEach items="${remarkList}"
																									var="remarkList">
																									<option value="${remarkList.remark}">${remarkList.remark}</option>
																								</c:forEach>
																							</select> </br> <input class="btn btn-primary" value="Submit"
																								onclick="insertGrnDisAgree(${gvnList.grnGvnId})" />

																						</div>
																					</div>
																				</div>
																			</li>
																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#"
																						data-toggle="dropdown"><i
																						class="fa fa-info-circle"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">
																							Franchisee Remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control"><c:out value="${gvnList.frGrnGvnRemark}"></c:out></textarea>
																								
																								Store remark
																			<textarea name="t2" readonly="readonly"
																				class="form-control"><c:out value="${gvnList.approvedRemarkStore}"/></textarea>

																						</div>
																					</div>
																				</div>
																			</li>

																		</ul>
																		
																		</td>

																</c:when>


																<c:otherwise>
																<c:choose>
																
																<c:when test="${gvnList.grnGvnStatus==1}">
																
																<td>
																
																<ul class="table-menu">

																			<li><a href="" id="callSubmit" class="disableClick"
																				onclick="insertGrnCall(${gvnList.grnGvnId})"> <i
																					class="fa fa-check"></i></a></li>

																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#" id="disableMe"
																						data-toggle="dropdown"><i class="fa fa-times"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">

																							<select name="store_remark${gvnList.grnGvnId}"
																								id="store_remark${gvnList.grnGvnId}"
																								class="form-control">
																								<c:forEach items="${remarkList}"
																									var="remarkList">
																									<option value="${remarkList.remark}">${remarkList.remark}</option>
																								</c:forEach>
																							</select> </br> <input class="btn btn-primary" value="Submit"
																								onclick="insertGrnDisAgree(${gvnList.grnGvnId})" />

																						</div>
																					</div>
																				</div>
																			</li>
																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#"
																						data-toggle="dropdown"><i
																						class="fa fa-info-circle"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">
																							Franchisee Remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control"><c:out value="${gvnList.frGrnGvnRemark}"></c:out></textarea>

																						</div>
																					</div>
																				</div>
																			</li>

																		</ul>

																		
																		</td>
																
																</c:when>
																
																<c:when test="${gvnList.grnGvnStatus==3}">
																<td>
																
																			
																		<ul class="table-menu">

																			<li><a href="" id="callSubmit" class="disableClick"
																				onclick="insertGrnCall(${gvnList.grnGvnId})"> <i
																					class="fa fa-check"></i></a></li>

																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#" id="disableMe"
																						data-toggle="dropdown"><i class="fa fa-times"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">

																							<select name="store_remark${gvnList.grnGvnId}"
																								id="store_remark${gvnList.grnGvnId}"
																								class="form-control">
																								<c:forEach items="${remarkList}"
																									var="remarkList">
																									<option value="${remarkList.remark}">${remarkList.remark}</option>
																								</c:forEach>
																							</select> </br> <input class="btn btn-primary" value="Submit"
																								onclick="insertGrnDisAgree(${gvnList.grnGvnId})" />

																						</div>
																					</div>
																				</div>
																			</li>
																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#"
																						data-toggle="dropdown"><i
																						class="fa fa-info-circle"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">
																							Franchisee Remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control"><c:out value="${gvnList.frGrnGvnRemark}"></c:out></textarea>

																							Dsipatch remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${gvnList.approvedRemarkGate}</textarea>
																						</div>
																					</div>
																				</div>
																			</li>

																		</ul>
																		
																		
																		</td>
																
																
																</c:when>
																
																
																<c:when test="${gvnList.grnGvnStatus==6}">
																<td>
																
																<%-- <input class="btn btn-primary" value="Approve"
																		id="callSubmit" disabled="disabled"
																		onclick="insertGrnCall(${gvnList.grnGvnId})" /> <input
																		class="accordion btn btn-primary" value="DisApprove"
																		disabled="disabled" />
																		<div class="panel" align="left">
																			Enter Remark
																			<textarea name="store_remark${gvnList.grnGvnId}"
																				id="store_remark${gvnList.grnGvnId}"></textarea>
																			<input class="btn btn-primary" value="Submit"
																				onclick="insertGrnDisAgree(${gvnList.grnGvnId})" />

																		</div> 
																		 --%>
																		 
																		 <ul class="table-menu">

																			<li><a href="" id="callSubmit" class="disableClick"
																				onclick="insertGrnCall(${gvnList.grnGvnId})"> <i
																					class="fa fa-check"></i></a></li>

																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#" id="disableMe"
																						data-toggle="dropdown"><i class="fa fa-times"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">

																							<select name="store_remark${gvnList.grnGvnId}"
																								id="store_remark${gvnList.grnGvnId}"
																								class="form-control">
																								<c:forEach items="${remarkList}"
																									var="remarkList">
																									<option value="${remarkList.remark}">${remarkList.remark}</option>
																								</c:forEach>
																							</select> </br> <input class="btn btn-primary" value="Submit"
																								onclick="insertGrnDisAgree(${gvnList.grnGvnId})" />

																						</div>
																					</div>
																				</div>
																			</li>
																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#"
																						data-toggle="dropdown"><i
																						class="fa fa-info-circle"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">
																							Franchisee Remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control"><c:out value="${gvnList.frGrnGvnRemark}"></c:out></textarea>

																							Dsipatch remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${gvnList.approvedRemarkGate}</textarea>
																						</div>
																					</div>
																				</div>
																			</li>

																		</ul>
																		
																		
																		
																		</td>
																
																
																</c:when>
																
																
																<c:when test="${gvnList.grnGvnStatus==7}">
																
																<td>
																
																<%-- <input class="btn btn-primary" value="Approve"
																		id="callSubmit" disabled="disabled"
																		onclick="insertGrnCall(${gvnList.grnGvnId})" /> <input
																		class="accordion btn btn-primary" value="DisApprove"
																		disabled="disabled" />
																		<div class="panel" align="left">
																			Enter Remark
																			<textarea name="store_remark${gvnList.grnGvnId}"
																				id="store_remark${gvnList.grnGvnId}"></textarea>
																			<input class="btn btn-primary" value="Submit"
																				onclick="insertGrnDisAgree(${gvnList.grnGvnId})" />

																		</div> <input class="accordion btn btn-primary" value="Show" />
																		<div class="panel" align="left">
																			Franchisee Remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${gvnList.frGrnGvnRemark}</textarea>
																			Account remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${gvnList.approvedRemarkAcc}</textarea>
																		</div>
																		 --%>
																		 
																		 <ul class="table-menu">

																			<li><a href="" id="callSubmit" class="disableClick"
																				onclick="insertGrnCall(${gvnList.grnGvnId})"> <i
																					class="fa fa-check"></i></a></li>

																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#" id="disableMe"
																						data-toggle="dropdown"><i class="fa fa-times"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">

																							<select name="store_remark${gvnList.grnGvnId}"
																								id="store_remark${gvnList.grnGvnId}"
																								class="form-control">
																								<c:forEach items="${remarkList}"
																									var="remarkList">
																									<option value="${remarkList.remark}">${remarkList.remark}</option>
																								</c:forEach>
																							</select> </br> <input class="btn btn-primary" value="Submit"
																								onclick="insertGrnDisAgree(${gvnList.grnGvnId})" />

																						</div>
																					</div>
																				</div>
																			</li>
																			<li>
																				<div class="dropdown">
																					<a class="dropdown-toggle" href="#"
																						data-toggle="dropdown"><i
																						class="fa fa-info-circle"></i></a>
																					<div class="dropdown-menu">
																						<div class="form">
																							Franchisee Remark
																							<textarea name="t1" readonly="readonly"
																								class="form-control"><c:out value="${gvnList.frGrnGvnRemark}"></c:out></textarea>

																							Account remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${gvnList.approvedRemarkAcc}</textarea>
																						</div>
																					</div>
																				</div>
																			</li>

																		</ul>
																		
																		
																		
																		
																		</td>
																
																</c:when>
																
																
																
																
																</c:choose>
																
																
																

																	<%-- <td><input class="btn btn-primary" value="Approve"
																		id="callSubmit" disabled="disabled"
																		onclick="insertGrnCall(${gvnList.grnGvnId})" /> <input
																		class="accordion btn btn-primary" value="DisApprove"
																		disabled="disabled" />
																		<div class="panel" align="left">
																			Enter Remark
																			<textarea name="store_remark${gvnList.grnGvnId}"
																				id="store_remark${gvnList.grnGvnId}"></textarea>
																			<input class="btn btn-primary" value="Submit"
																				onclick="insertGrnDisAgree(${gvnList.grnGvnId})" />

																		</div> <input class="accordion btn btn-primary" value="Show" />
																		<div class="panel" align="left">
																			Franchisee Remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${gvnList.frGrnGvnRemark}</textarea>
																			Factory remark
																			<textarea name="t1" readonly="readonly"
																				class="form-control">${gvnList.approvedRemarkGate}</textarea>
																		</div></td> --%>
																</c:otherwise>
															</c:choose>

														</tr>

													</c:forEach>

												</tbody>

											</table>
										</div>

										<!-- this is for ajax call<input type="submit" class="btn btn-primary" value="Submit"
										id="callSubmit" onclick="callSubmitGrn(); getGrnId();"> -->


										<div
											class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
											<input type="submit" value="Submit" class="btn btn-primary">


										</div>
										<!-- </form> -->

									</div>
								</div>

							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- END Main Content -->
			<footer>
			<p>2017 © MONGINIS.</p>
			</footer>


			<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
				class="fa fa-chevron-up"></i></a>
		</div>
		<!-- END Content -->
	</div>
	<!-- END Container -->

	<!--basic scripts-->
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="${pageContext.request.contextPath}/resources/assets/jquery/jquery-2.0.3.min.js"><\/script>')
	</script>
	
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/assets/bootstrap/js/lightbox.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/jquery-cookie/jquery.cookie.js"></script>

	<!--page specific plugin scripts-->
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.resize.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.pie.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.stack.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.crosshair.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/flot/jquery.flot.tooltip.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/sparkline/jquery.sparkline.min.js"></script>


	<!--page specific plugin scripts-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>





	<!--flaty scripts-->
	<script src="${pageContext.request.contextPath}/resources/js/flaty.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/flaty-demo-codes.js"></script>
	<!--page specific plugin scripts-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-fileupload/bootstrap-fileupload.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/chosen-bootstrap/chosen.jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/clockface/js/clockface.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/date.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/bootstrap-daterangepicker/daterangepicker.js"></script>
	<!-- <script type="text/javascript">
		/* $(document).ready(function() { */
			/* $('#callSubmit').submit(function() { */
				function callSubmitGrn(){
					//var selectedGrn = $("#selectFr").val();
					
					
					
					
				
			
			//alert("hello"),
				 $.ajax({
					type : "get",
					
					
					
					url : "insertGateGrnProcess" //this is my servlet
				/*  data: "input=" +$('#ip').val()+"&output="+$('#op').val(), */

				});
			
 
		};
	</script>
 -->


	<!-- insertGrnDisAgree -->


	<script type="text/javascript">


function insertGrnDisAgree(grnGvnId){
//alert("second function called ");
var grnId=grnGvnId;
var approve_store_login=$("#approve_store_login"+grnGvnId).val();
var store_remark=$("#store_remark"+grnId).val();
var store_gvn_qty=$("#store_gvn_qty"+grnGvnId).val();

if($("#store_remark"+grnGvnId).val() == ''){
	alert("Please Enter Grn Remark!");
	
}
else{
	
	
	$.getJSON('${insertStoreGvnProcessDisAgree}',
			{
			
			grnId : grnId,
			approveStoreLogin : approve_store_login,
			storeRemark : store_remark,	
			store_gvn_qty : store_gvn_qty,

				ajax : 'true',
			

			}
);


	




callRefreshDisAgree();
/* callSecondRefresh();
callThirdRefresh();
callfourthRefresh(); */
}
}


</script>

	<script type="text/javascript">
function callRefreshDisAgree(){
	
		alert("DisApproved Successfully");
		window.location.reload();
	//document.getElementById("validation-form").reload();
	}

</script>



	<!-- insertGrnDisAgree -->


	<!-- insertGrnAgree -->

	<script type="text/javascript">


function insertGrnCall(grnGvnId){
//alert("second function called ");
var grnId=grnGvnId;
var approve_store_login=$("#approve_store_login"+grnGvnId).val();
var gate_remark=$("#gate_remark"+grnGvnId).val();
var store_gvn_qty=$("#store_gvn_qty"+grnGvnId).val();


/* alert(grnId);
alert(approve_gate_login); */

	

	$.getJSON('${insertStoreGvnProcessAgree}',
							{
							
							grnId : grnId,
							approveStoreLogin:approve_store_login,
							store_gvn_qty : store_gvn_qty,

								
								ajax : 'true',
							
	 complete: function() {
	       //alert("ajax completed");
	       
 	  }
}
);

callRefresh();
/* callSecondRefresh();
callThirdRefresh();
callfourthRefresh(); */
}


</script>

	<script type="text/javascript">
function callRefresh(){
	alert("Approved Successfully");
		window.location.reload();
	//document.getElementById("validation-form").reload();
	}

</script>

	<script type="text/javascript">

function callSecondRefresh(){

	window.location.reload();

	
}


</script>

	<script type="text/javascript">

function callThirdRefresh(){
	window.location.reload();

}

</script>

	<script type="text/javascript">

function callfourthRefresh(){
	window.reload();


}

</script>
	<!-- insertGrnAgree -->


	<script>
var acc = document.getElementsByClassName("accordion");
var i;

for (i = 0; i < acc.length; i++) {
  acc[i].onclick = function() {
    this.classList.toggle("active");
    var panel = this.nextElementSibling;
    if (panel.style.maxHeight){
      panel.style.maxHeight = null;
    } else {
      panel.style.maxHeight = panel.scrollHeight + "px";
    } 
  }
}
</script>


<script type="text/javascript">

function showGateGvnDetails(){
	
	alert("hi");
		var fromDate=$("#from_date").val();
	
		var toDate=$("#to_date").val();
		
		//alert(fromDate);
		//alert(toDate);
		
		$.getJSON('${showGateGvnDetails}',
				{
				
				from_date :fromDate,
				to_date	: toDate,
					
					ajax : 'true',
				
				});


}


function validateQty(grnId,grnQty,aprQty,qty){
	
	//alert("JJJ");
	var entered=$("#store_gvn_qty"+grnId).val();
	//alert("received = " +entered);
	if(entered>grnQty){
		alert("Can not Enter Qty Greater than auto Qty ");
		document.getElementById("store_gvn_qty"+grnId).value=qty;
	}

</script>

<script type="text/javascript">

function getDate(){
	
	
	var fromDate=$("#from_date").val();
	var toDate=$("#to_date").val();
	
	
	
	
	$.getJSON('${getDateForGvnStore}',
			{
			
			fromDate : fromDate,
			toDate:toDate,
				
				ajax : 'true',
			

}
);
	
	
}
</script>
<script type="text/javascript">

</script>

<script>
	$(document).ready(function(){
	    //Handles menu drop down
	    $('.dropdown-menu').find('.form').click(function (e) {
	        e.stopPropagation();
	    });
	});
</script>
</body>
</html>