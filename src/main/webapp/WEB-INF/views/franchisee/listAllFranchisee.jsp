<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<body>
<c:url var="getFranchiseeListByStatus" value="/getFranchiseeListByStatus" />

	<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>

	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/tableSearch.css">


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
						<i class="fa fa-file-o"></i> Franchisee
					</h1>
				</div>
			</div>
			<!-- END Page Title -->
			

			<c:set var="isEdit" value="0">
			</c:set>

			<c:set var="isDelete" value="0">
			</c:set>

			<c:forEach items="${sessionScope.newModuleList}" var="modules">
				<c:forEach items="${modules.subModuleJsonList}" var="subModule">
			
				
					<c:choose>
						<c:when
							test="${subModule.subModuleMapping eq 'listAllFranchisee'}">

								
							<c:choose>
								<c:when test="${subModule.editReject=='visible'}">
									<c:set var="isEdit" value="1">
									</c:set>
								</c:when>
								<c:otherwise>
									<c:set var="isEdit" value="0">
									</c:set>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${subModule.deleteRejectApprove=='visible'}">
									<c:set var="isDelete" value="1">
									</c:set>
								</c:when>
								<c:otherwise>
									<c:set var="isDelete" value="0">
									</c:set>
								</c:otherwise>
							</c:choose>
						</c:when>
					</c:choose>

				</c:forEach>
			</c:forEach>


			<!-- BEGIN Main Content -->
			<div class="row">
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-12">
							<div class="box">
								<div class="box-title">
									<h3>
										<i class="fa fa-bars"></i> Franchisee List
									</h3>
									<div class="box-tool">
										<a href="${pageContext.request.contextPath}/listAllFranchisee">Back
											to List</a> <a data-action="collapse" href="#"><i
											class="fa fa-chevron-up"></i></a>
									</div>
								</div>
								
								<div class="box-content">
								
									<div class="col-md-9"></div>
									<label for="search" class="col-md-3" id="search"> <i
										class="fa fa-search" style="font-size: 20px"></i> <input
										type="text" id="myInput" onkeyup="myFunction()"
										placeholder="Search.." title="Type in a name">
										<input type="hidden" id="activeTable" name="activeTable" value="table1">
										
									</label>
									<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Franchisee</label>
											<div class="col-sm-9 col-lg-10 controls">
												<label class="radio-inline"> <input type="radio" onclick="showFranchisee(this.value,'table1')" 
													name="fr_state" id="optionsRadios1" value="-1" checked />
													All
												</label> <label class="radio-inline"> <input type="radio"
													name="fr_state" id="optionsRadios1" value="0" onclick="showFranchisee(this.value,'table2')" >
													Active
												</label>
												
												<label class="radio-inline"> <input type="radio"
													name="fr_state" id="optionsRadios1" value="1" onclick="showFranchisee(this.value,'table3')" >
													Inactive
												</label>
												<label class="radio-inline"> <input type="radio"
													name="fr_state" id="optionsRadios1" value="2" onclick="showFranchisee(this.value,'table4')" >
													Non-Regular Party
												</label>
											</div>
										</div>
									<div class="clearfix"></div>
									
									<div id="table-scroll" class="table-scroll">
										<!-- <div id="faux-table" class="faux-table" aria="hidden">
											<table id="table2" class="main-table">
												<thead>
													<tr class="bgpink">
														<th width="158" style="width: 18px" align="left">#</th>
														<th class="col-md-2">Code2</th>
														<th class="col-md-2">Name</th>
														<th class="col-md-2">Image</th>
														<th class="col-md-2">Owner</th>
														<th class="col-md-2">City</th>
														<th class="col-md-2">Mobile No.</th>
														<th class="col-md-2">Route</th>
														<th class="col-md-2">Password</th>
														<th class="col-md-2">Rating</th>
														<th class="col-md-2">Status</th>

														<th class="col-md-2" width="90px">Action</th>
													</tr>
												</thead>
												
											</table>

										</div> -->
										
										<!-- 1 All Franchisee-->
										<div class="table-wrap" id="tab1">

											<table id="table1" class="sachin table table-advance">
												<thead>
													<tr class="bgpink">
														<th width="138" style="width: 18px" align="left">#</th>
														<th class="col-md-2">Code1</th>
														<th class="col-md-2">Name</th>
														<th class="col-md-2">Image</th>
														<th class="col-md-2">Owner</th>
														<th class="col-md-2">City</th>
														<th class="col-md-2">Mobile No.</th>
														<th class="col-md-2">Route</th>
														<th class="col-md-2">Password</th>
														<!-- <th class="col-md-2">Rating</th> -->
														<!--<th class="col-md-2">Status</th> -->
														<th class="col-md-2" width="90px">Action</th>
													</tr>
												</thead>
												<tbody style="padding-top: 100px" >
	
													<c:forEach items="${franchiseeList}" var="franchiseeList"
														varStatus="count">
														
														<tr>
															<td><c:out value="${count.index+1}"></c:out></td>
															<td align="left"><c:out
																	value="${franchiseeList.frCode}" /></td>
															<td align="left"><c:out
																	value="${franchiseeList.frName}" /></td>
															<td align="left"><img
																src="${url}${franchiseeList.frImage}" height="80"
																width="80"
																onerror="this.src='${pageContext.request.contextPath}/resources/img/No_Image_Available.jpg';" />


															</td>
															<td align="left"><c:out
																	value="${franchiseeList.frOwner}" /></td>
															<td align="left"><c:out
																	value="${franchiseeList.frCity}" /></td>
															<td align="left"><c:out
																	value="${franchiseeList.frMob}" /></td>
															<td align="left"><c:forEach items="${routeList}"
																	var="routeList">

																	<c:choose>
																		<c:when
																			test="${routeList.routeId==franchiseeList.frRouteId}">
																			<c:out value="${routeList.routeName}" />
																		</c:when>

																		<c:otherwise></c:otherwise>
																	</c:choose>
																</c:forEach></td>
																
																<td align="left"><c:out
																	value="${franchiseeList.frPassword}" /></td>
															

														
															<%-- <td align="left"><c:choose>
																	<c:when test="${franchiseeList.stockType==1}">
																		<c:out value="Type1" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==2}">
																		<c:out value="Type2" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==3}">
																		<c:out value="Type3" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==4}">
																		<c:out value="Type4" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==5}">
																		<c:out value="Type5" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==6}">
																		<c:out value="Type6" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==7}">
																		<c:out value="Type7" />
																	</c:when>
																	<c:otherwise></c:otherwise>
																</c:choose></td> --%>
														<%-- 	<td align="left"><c:choose>
																	<c:when test="${franchiseeList.frRate==0}">0.5</c:when>
																	<c:when test="${franchiseeList.frRate==1}">1</c:when>
																	<c:when test="${franchiseeList.frRate==2}">1.5</c:when>
																	<c:when test="${franchiseeList.frRate==3}">2</c:when>
																	<c:when test="${franchiseeList.frRate==4}">2.5</c:when>
																	<c:when test="${franchiseeList.frRate==5}">3</c:when>
																	<c:when test="${franchiseeList.frRate==6}">3.5</c:when>
																	<c:when test="${franchiseeList.frRate==7}">4</c:when>
																	<c:when test="${franchiseeList.frRate==8}">4.5</c:when>
																	<c:when test="${franchiseeList.frRate==9}">5</c:when>
																</c:choose></td> --%>

															<c:choose>
																<c:when test="${isEdit==1 and isDelete==1}">

																	<td align="left">
																	
																	<a
																		href="updateFranchisee/${franchiseeList.frId}"><span
																			class="glyphicon glyphicon-edit"></span></a>&nbsp;
																			 <%-- <a
																		href="deleteFranchisee/${franchiseeList.frId}"
																		onClick="return confirm('Are you sure want to delete this record');"><span
																			class="glyphicon glyphicon-remove"></span></a> --%>
																	</td>
																</c:when>

																<c:when test="${isEdit==1 and isDelete==0}">

																	<td align="left">
																		
																		<a
																		href="updateFranchisee/${franchiseeList.frId}"><span
																			class="glyphicon glyphicon-edit"></span></a>&nbsp; <%-- <a
																		href="deleteFranchisee/${franchiseeList.frId}"
																		class="disableClick"
																		onClick="return confirm('Are you sure want to delete this record');"><span
																			class="glyphicon glyphicon-remove"></span></a> --%></td>
																</c:when>

																<c:when test="${isEdit==0 and isDelete==1}">

																	<td align="left">
																	
																		<a
																		href="updateFranchisee/${franchiseeList.frId}"
																		class="disableClick"><span
																			class="glyphicon glyphicon-edit"></span></a>&nbsp;
																			 <%-- <a
																		href="deleteFranchisee/${franchiseeList.frId}"
																		onClick="return confirm('Are you sure want to delete this record');"><span
																			class="glyphicon glyphicon-remove">EEEE</span></a> --%>
																			</td>
																</c:when>

																<c:otherwise>


																	<td align="left">
																	
																	<a
																		href="updateFranchisee/${franchiseeList.frId}"
																		class="disableClick"><span
																			class="glyphicon glyphicon-edit"></span></a>&nbsp; 
																			<%-- <a
																		href="deleteFranchisee/${franchiseeList.frId}"
																		class="disableClick"
																		onClick="return confirm('Are you sure want to delete this record');"><span
																			class="glyphicon glyphicon-remove"></span></a> --%>
																			</td>

																</c:otherwise>
															</c:choose>

															<%-- <td align="left"><a
																href="updateFranchisee/${franchiseeList.frId}"><span
																	class="glyphicon glyphicon-edit"></span></a>&nbsp; <a
																href="deleteFranchisee/${franchiseeList.frId}"
																onClick="return confirm('Are you sure want to delete this record');"><span
																	class="glyphicon glyphicon-remove"></span></a></td> --%>
														</tr>

													</c:forEach>

												</tbody>

											</table>
										</div>
										<!-- 1 -->
										
											<!-- 2 Active Franchisee-->
											<c:set value="0" var="srno"/>
										<div class="sachintable-wrap" id="tab2" style="display: none;">

											<table id="table2" class="sachin table table-advance">
												<thead>
													<tr class="bgpink">
														<th width="138" style="width: 18px" align="left">#</th>
														<th class="col-md-2">Code2</th>
														<th class="col-md-2">Name</th>
														<th class="col-md-2">Image</th>
														<th class="col-md-2">Owner</th>
														<th class="col-md-2">City</th>
														<th class="col-md-2">Mobile No.</th>
														<th class="col-md-2">Route</th>
														<th class="col-md-2">Password</th>
														<!-- <th class="col-md-2">Rating</th> -->
														<!--<th class="col-md-2">Status</th> -->
														<th class="col-md-2" width="90px">Action</th>
													</tr>
												</thead>
												<tbody style="padding-top: 100px" >
	
													<c:forEach items="${franchiseeList}" var="franchiseeList"
														varStatus="count">
														<%-- <c:set value="${count.index+1}" var="sr"/> --%>
														<c:if test="${franchiseeList.delStatus==0}">
														<tr>														
															<td><c:out value="${srno=srno+1}"/></td>
															
															<td align="left"><c:out
																	value="${franchiseeList.frCode}" /></td>
															<td align="left"><c:out
																	value="${franchiseeList.frName}" /></td>
															<td align="left"><img
																src="${url}${franchiseeList.frImage}" height="80"
																width="80"
																onerror="this.src='${pageContext.request.contextPath}/resources/img/No_Image_Available.jpg';" />


															</td>
															<td align="left"><c:out
																	value="${franchiseeList.frOwner}" /></td>
															<td align="left"><c:out
																	value="${franchiseeList.frCity}" /></td>
															<td align="left"><c:out
																	value="${franchiseeList.frMob}" /></td>
															<td align="left"><c:forEach items="${routeList}"
																	var="routeList">

																	<c:choose>
																		<c:when
																			test="${routeList.routeId==franchiseeList.frRouteId}">
																			<c:out value="${routeList.routeName}" />
																		</c:when>

																		<c:otherwise></c:otherwise>
																	</c:choose>
																</c:forEach></td>
																
																<td align="left"><c:out
																	value="${franchiseeList.frPassword}" /></td>
															

														
															<%-- <td align="left"><c:choose>
																	<c:when test="${franchiseeList.stockType==1}">
																		<c:out value="Type1" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==2}">
																		<c:out value="Type2" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==3}">
																		<c:out value="Type3" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==4}">
																		<c:out value="Type4" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==5}">
																		<c:out value="Type5" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==6}">
																		<c:out value="Type6" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==7}">
																		<c:out value="Type7" />
																	</c:when>
																	<c:otherwise></c:otherwise>
																</c:choose></td> --%>
														<%-- 	<td align="left"><c:choose>
																	<c:when test="${franchiseeList.frRate==0}">0.5</c:when>
																	<c:when test="${franchiseeList.frRate==1}">1</c:when>
																	<c:when test="${franchiseeList.frRate==2}">1.5</c:when>
																	<c:when test="${franchiseeList.frRate==3}">2</c:when>
																	<c:when test="${franchiseeList.frRate==4}">2.5</c:when>
																	<c:when test="${franchiseeList.frRate==5}">3</c:when>
																	<c:when test="${franchiseeList.frRate==6}">3.5</c:when>
																	<c:when test="${franchiseeList.frRate==7}">4</c:when>
																	<c:when test="${franchiseeList.frRate==8}">4.5</c:when>
																	<c:when test="${franchiseeList.frRate==9}">5</c:when>
																</c:choose></td> --%>

															<c:choose>
																<c:when test="${isEdit==1 and isDelete==1}">

																	<td align="left"><a
																		href="updateFranchisee/${franchiseeList.frId}"><span
																			class="glyphicon glyphicon-edit"></span></a>&nbsp;
																			 <%-- <a
																		href="deleteFranchisee/${franchiseeList.frId}"
																		onClick="return confirm('Are you sure want to delete this record');"><span
																			class="glyphicon glyphicon-remove"></span></a> --%>
																			</td>
																</c:when>

																<c:when test="${isEdit==1 and isDelete==0}">

																	<td align="left"><a
																		href="updateFranchisee/${franchiseeList.frId}"><span
																			class="glyphicon glyphicon-edit"></span></a>&nbsp; <%-- <a
																		href="deleteFranchisee/${franchiseeList.frId}"
																		class="disableClick"
																		onClick="return confirm('Are you sure want to delete this record');"><span
																			class="glyphicon glyphicon-remove"></span></a> --%></td>
																</c:when>

																<c:when test="${isEdit==0 and isDelete==1}">

																	<td align="left"><a
																		href="updateFranchisee/${franchiseeList.frId}"
																		class="disableClick"><span
																			class="glyphicon glyphicon-edit"></span></a>&nbsp;
																			 <%-- <a
																		href="deleteFranchisee/${franchiseeList.frId}"
																		onClick="return confirm('Are you sure want to delete this record');"><span
																			class="glyphicon glyphicon-remove">EEEE</span></a> --%>
																			</td>
																</c:when>

																<c:otherwise>


																	<td align="left"><a
																		href="updateFranchisee/${franchiseeList.frId}"
																		class="disableClick"><span
																			class="glyphicon glyphicon-edit"></span></a>&nbsp; 
																			<%-- <a
																		href="deleteFranchisee/${franchiseeList.frId}"
																		class="disableClick"
																		onClick="return confirm('Are you sure want to delete this record');"><span
																			class="glyphicon glyphicon-remove"></span></a> --%>
																			</td>

																</c:otherwise>
															</c:choose>

															<%-- <td align="left"><a
																href="updateFranchisee/${franchiseeList.frId}"><span
																	class="glyphicon glyphicon-edit"></span></a>&nbsp; <a
																href="deleteFranchisee/${franchiseeList.frId}"
																onClick="return confirm('Are you sure want to delete this record');"><span
																	class="glyphicon glyphicon-remove"></span></a></td> --%>
														</tr>
</c:if>
													</c:forEach>

												</tbody>

											</table>
										</div>
										<!-- 2 -->
										
											<!-- 3 Inactive Franchisee-->
											<c:set value="0" var="srno"/>
										<div class="sachin table-wrap" id="tab3" style="display: none;">

											<table id="table3" class="sachin table table-advance">
												<thead>
													<tr class="bgpink">
														<th width="138" style="width: 18px" align="left">#</th>
														<th class="col-md-2">Code3</th>
														<th class="col-md-2">Name</th>
														<th class="col-md-2">Image</th>
														<th class="col-md-2">Owner</th>
														<th class="col-md-2">City</th>
														<th class="col-md-2">Mobile No.</th>
														<th class="col-md-2">Route</th>
														<th class="col-md-2">Password</th>
														<!-- <th class="col-md-2">Rating</th> -->
														<!--<th class="col-md-2">Status</th> -->
														<th class="col-md-2" width="90px">Action</th>
													</tr>
												</thead>
												<tbody style="padding-top: 100px" >
												
													<c:forEach items="${franchiseeList}" var="franchiseeList"
														varStatus="count">
														<c:if test="${franchiseeList.delStatus==1}">
														<tr>														
															<td><c:out value="${srno=srno+1}"/></td>
															
															<td align="left"><c:out
																	value="${franchiseeList.frCode}" /></td>
															<td align="left"><c:out
																	value="${franchiseeList.frName}" /></td>
															<td align="left"><img
																src="${url}${franchiseeList.frImage}" height="80"
																width="80"
																onerror="this.src='${pageContext.request.contextPath}/resources/img/No_Image_Available.jpg';" />


															</td>
															<td align="left"><c:out
																	value="${franchiseeList.frOwner}" /></td>
															<td align="left"><c:out
																	value="${franchiseeList.frCity}" /></td>
															<td align="left"><c:out
																	value="${franchiseeList.frMob}" /></td>
															<td align="left"><c:forEach items="${routeList}"
																	var="routeList">

																	<c:choose>
																		<c:when
																			test="${routeList.routeId==franchiseeList.frRouteId}">
																			<c:out value="${routeList.routeName}" />
																		</c:when>

																		<c:otherwise></c:otherwise>
																	</c:choose>
																</c:forEach></td>
																
																<td align="left"><c:out
																	value="${franchiseeList.frPassword}" /></td>
															

														
															<%-- <td align="left"><c:choose>
																	<c:when test="${franchiseeList.stockType==1}">
																		<c:out value="Type1" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==2}">
																		<c:out value="Type2" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==3}">
																		<c:out value="Type3" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==4}">
																		<c:out value="Type4" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==5}">
																		<c:out value="Type5" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==6}">
																		<c:out value="Type6" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==7}">
																		<c:out value="Type7" />
																	</c:when>
																	<c:otherwise></c:otherwise>
																</c:choose></td> --%>
														<%-- 	<td align="left"><c:choose>
																	<c:when test="${franchiseeList.frRate==0}">0.5</c:when>
																	<c:when test="${franchiseeList.frRate==1}">1</c:when>
																	<c:when test="${franchiseeList.frRate==2}">1.5</c:when>
																	<c:when test="${franchiseeList.frRate==3}">2</c:when>
																	<c:when test="${franchiseeList.frRate==4}">2.5</c:when>
																	<c:when test="${franchiseeList.frRate==5}">3</c:when>
																	<c:when test="${franchiseeList.frRate==6}">3.5</c:when>
																	<c:when test="${franchiseeList.frRate==7}">4</c:when>
																	<c:when test="${franchiseeList.frRate==8}">4.5</c:when>
																	<c:when test="${franchiseeList.frRate==9}">5</c:when>
																</c:choose></td> --%>

															<c:choose>
																<c:when test="${isEdit==1 and isDelete==1}">

																	<td align="left"><a
																		href="updateFranchisee/${franchiseeList.frId}"><span
																			class="glyphicon glyphicon-edit"></span></a>&nbsp;
																			 <%-- <a
																		href="deleteFranchisee/${franchiseeList.frId}"
																		onClick="return confirm('Are you sure want to delete this record');"><span
																			class="glyphicon glyphicon-remove"></span></a> --%>
																			</td>
																</c:when>

																<c:when test="${isEdit==1 and isDelete==0}">

																	<td align="left"><a
																		href="updateFranchisee/${franchiseeList.frId}"><span
																			class="glyphicon glyphicon-edit"></span></a>&nbsp; <%-- <a
																		href="deleteFranchisee/${franchiseeList.frId}"
																		class="disableClick"
																		onClick="return confirm('Are you sure want to delete this record');"><span
																			class="glyphicon glyphicon-remove"></span></a> --%></td>
																</c:when>

																<c:when test="${isEdit==0 and isDelete==1}">

																	<td align="left"><a
																		href="updateFranchisee/${franchiseeList.frId}"
																		class="disableClick"><span
																			class="glyphicon glyphicon-edit"></span></a>&nbsp;
																			 <%-- <a
																		href="deleteFranchisee/${franchiseeList.frId}"
																		onClick="return confirm('Are you sure want to delete this record');"><span
																			class="glyphicon glyphicon-remove">EEEE</span></a> --%>
																			</td>
																</c:when>

																<c:otherwise>


																	<td align="left"><a
																		href="updateFranchisee/${franchiseeList.frId}"
																		class="disableClick"><span
																			class="glyphicon glyphicon-edit"></span></a>&nbsp; 
																			<%-- <a
																		href="deleteFranchisee/${franchiseeList.frId}"
																		class="disableClick"
																		onClick="return confirm('Are you sure want to delete this record');"><span
																			class="glyphicon glyphicon-remove"></span></a> --%>
																			</td>

																</c:otherwise>
															</c:choose>

															<%-- <td align="left"><a
																href="updateFranchisee/${franchiseeList.frId}"><span
																	class="glyphicon glyphicon-edit"></span></a>&nbsp; <a
																href="deleteFranchisee/${franchiseeList.frId}"
																onClick="return confirm('Are you sure want to delete this record');"><span
																	class="glyphicon glyphicon-remove"></span></a></td> --%>
														</tr>
</c:if>
													</c:forEach>

												</tbody>

											</table>
										</div>
										<!-- 3 -->
										
											<!-- 4 Non-Regular Party-->
											<c:set value="0" var="srno"/>
										<div class="table-wrap" id="tab4" style="display: none;">

											<table id="table4" class="sachin table table-advance">
												<thead>
													<tr class="bgpink">
														<th width="138" style="width: 18px" align="left">#</th>
														<th class="col-md-2">Code4</th>
														<th class="col-md-2">Name</th>
														<th class="col-md-2">Image</th>
														<th class="col-md-2">Owner</th>
														<th class="col-md-2">City</th>
														<th class="col-md-2">Mobile No.</th>
														<th class="col-md-2">Route</th>
														<th class="col-md-2">Password</th>
														<!-- <th class="col-md-2">Rating</th> -->
														<!--<th class="col-md-2">Status</th> -->
														<th class="col-md-2" width="90px">Action</th>
													</tr>
												</thead>
												<tbody style="padding-top: 100px" >
	
													<c:forEach items="${franchiseeList}" var="franchiseeList"
														varStatus="count">
														
														<c:if test="${franchiseeList.delStatus==2}">
														<tr>
															<td><c:out value="${srno=srno+1}"/></td>
															<td align="left"><c:out
																	value="${franchiseeList.frCode}" /></td>
															<td align="left"><c:out
																	value="${franchiseeList.frName}" /></td>
															<td align="left"><img
																src="${url}${franchiseeList.frImage}" height="80"
																width="80"
																onerror="this.src='${pageContext.request.contextPath}/resources/img/No_Image_Available.jpg';" />


															</td>
															<td align="left"><c:out
																	value="${franchiseeList.frOwner}" /></td>
															<td align="left"><c:out
																	value="${franchiseeList.frCity}" /></td>
															<td align="left"><c:out
																	value="${franchiseeList.frMob}" /></td>
															<td align="left"><c:forEach items="${routeList}"
																	var="routeList">

																	<c:choose>
																		<c:when
																			test="${routeList.routeId==franchiseeList.frRouteId}">
																			<c:out value="${routeList.routeName}" />
																		</c:when>

																		<c:otherwise></c:otherwise>
																	</c:choose>
																</c:forEach></td>
																
																<td align="left"><c:out
																	value="${franchiseeList.frPassword}" /></td>
															

														
															<%-- <td align="left"><c:choose>
																	<c:when test="${franchiseeList.stockType==1}">
																		<c:out value="Type1" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==2}">
																		<c:out value="Type2" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==3}">
																		<c:out value="Type3" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==4}">
																		<c:out value="Type4" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==5}">
																		<c:out value="Type5" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==6}">
																		<c:out value="Type6" />
																	</c:when>
																	<c:when test="${franchiseeList.stockType==7}">
																		<c:out value="Type7" />
																	</c:when>
																	<c:otherwise></c:otherwise>
																</c:choose></td> --%>
														<%-- 	<td align="left"><c:choose>
																	<c:when test="${franchiseeList.frRate==0}">0.5</c:when>
																	<c:when test="${franchiseeList.frRate==1}">1</c:when>
																	<c:when test="${franchiseeList.frRate==2}">1.5</c:when>
																	<c:when test="${franchiseeList.frRate==3}">2</c:when>
																	<c:when test="${franchiseeList.frRate==4}">2.5</c:when>
																	<c:when test="${franchiseeList.frRate==5}">3</c:when>
																	<c:when test="${franchiseeList.frRate==6}">3.5</c:when>
																	<c:when test="${franchiseeList.frRate==7}">4</c:when>
																	<c:when test="${franchiseeList.frRate==8}">4.5</c:when>
																	<c:when test="${franchiseeList.frRate==9}">5</c:when>
																</c:choose></td> --%>

															<c:choose>
																<c:when test="${isEdit==1 and isDelete==1}">

																	<td align="left"><a
																		href="updateFranchisee/${franchiseeList.frId}"><span
																			class="glyphicon glyphicon-edit"></span></a>&nbsp;
																			 <%-- <a
																		href="deleteFranchisee/${franchiseeList.frId}"
																		onClick="return confirm('Are you sure want to delete this record');"><span
																			class="glyphicon glyphicon-remove"></span></a> --%>
																			</td>
																</c:when>

																<c:when test="${isEdit==1 and isDelete==0}">

																	<td align="left"><a
																		href="updateFranchisee/${franchiseeList.frId}"><span
																			class="glyphicon glyphicon-edit"></span></a>&nbsp; <%-- <a
																		href="deleteFranchisee/${franchiseeList.frId}"
																		class="disableClick"
																		onClick="return confirm('Are you sure want to delete this record');"><span
																			class="glyphicon glyphicon-remove"></span></a> --%></td>
																</c:when>

																<c:when test="${isEdit==0 and isDelete==1}">

																	<td align="left"><a
																		href="updateFranchisee/${franchiseeList.frId}"
																		class="disableClick"><span
																			class="glyphicon glyphicon-edit"></span></a>&nbsp;
																			 <%-- <a
																		href="deleteFranchisee/${franchiseeList.frId}"
																		onClick="return confirm('Are you sure want to delete this record');"><span
																			class="glyphicon glyphicon-remove">EEEE</span></a> --%>
																			</td>
																</c:when>

																<c:otherwise>


																	<td align="left"><a
																		href="updateFranchisee/${franchiseeList.frId}"
																		class="disableClick"><span
																			class="glyphicon glyphicon-edit"></span></a>&nbsp; 
																			<%-- <a
																		href="deleteFranchisee/${franchiseeList.frId}"
																		class="disableClick"
																		onClick="return confirm('Are you sure want to delete this record');"><span
																			class="glyphicon glyphicon-remove"></span></a> --%>
																			</td>

																</c:otherwise>
															</c:choose>

															<%-- <td align="left"><a
																href="updateFranchisee/${franchiseeList.frId}"><span
																	class="glyphicon glyphicon-edit"></span></a>&nbsp; <a
																href="deleteFranchisee/${franchiseeList.frId}"
																onClick="return confirm('Are you sure want to delete this record');"><span
																	class="glyphicon glyphicon-remove"></span></a></td> --%>
														</tr>
														</c:if>

													</c:forEach>

												</tbody>

											</table>
										</div>
										<!-- 4 -->
										
									</div>

								</div>


							</div>
							<div class="form-group" id="range">



								<div class="col-sm-3  controls">
									<input type="button" id="expExcel" class="btn btn-primary"
										value="EXPORT TO Excel" onclick="exportToExcel();">
								</div>
							</div>
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

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/assets/jquery-validation/dist/additional-methods.min.js"></script>



</body>

<script type="text/javascript">
function showFranchisee(status,tableClass) {
	//var url = ${url}
	
	if(status==0){
		//alert("option-----" + status+"  "+"Active");
		document.getElementById("tab2").style.display = "block";
		
		document.getElementById("tab1").style.display = "none";
		document.getElementById("tab3").style.display = "none";
		document.getElementById("tab4").style.display = "none";
		
	}else if(status==1){
		//alert("option-----" + status+"  "+"Inactive");
		document.getElementById("tab3").style.display = "block";
		
		document.getElementById("tab1").style.display = "none";
		document.getElementById("tab2").style.display = "none";
		document.getElementById("tab4").style.display = "none";
	}
	else if(status==2){
		//alert("option-----" + status+"  "+"Non Regular");
		document.getElementById("tab4").style.display = "block";
		
		document.getElementById("tab1").style.display = "none";
		document.getElementById("tab2").style.display = "none";
		document.getElementById("tab3").style.display = "none";
	}
	else{
		//alert("option-----" + status+"  "+"All");
		document.getElementById("tab1").style.display = "block";
		document.getElementById("tab2").style.display = "none";
		document.getElementById("tab3").style.display = "none";
		document.getElementById("tab4").style.display = "none";
	}
	
	$("#activeTable").val(tableClass);
		/* $
				.getJSON(
						'${getFranchiseeListByStatus}',
						{
							status : status,
							ajax : 'true',

						},
						function(data) {

							alert("franchiseeList-----"+JSON.stringify(data.fr));
							alert("route-------"+JSON.stringify(data.route.routeName));

							$('#table1 td').remove();
							
							

							 $
									.each(
											data.fr,
											function(key, item) {
												alert("IN-------"+JSON.stringify(item));
											
									
									var tr = $('<tr></tr>');

												tr.append($('<td></td>').html(
														key + 1));

												tr.append($('<td></td>').html(
														item.frCode));

												tr.append($('<td></td>').html(
														item.frName));

												tr.append($('<td></td>').html(
														item.frImage));

												tr.append($('<td></td>').html(
														item.frOwner));

												tr.append($('<td></td>').html(
														item.frCity));

												tr.append($('<td></td>').html(
														item.frMob));
												
												tr.append($('<td></td>').html(
														item.frRouteId));

												tr.append($('<td></td>').html(
														item.frPassword));

												tr
														.append($('<td></td>')
																.html(
																		"<a href='#' class='action_btn' onclick=editItemDetail("
																				+ key
																				+ ")> <abbr title='edit'> <i class='fa fa-edit  fa-lg' ></i></abbr> </a> <a href='#' class='action_btn'onclick=deleteItemDetail("
																				+ key
																				+ ")><abbr title='Delete'><i class='fa fa-trash-o  fa-lg'></i></abbr></a>"));
 
									
												$('#table1 tbody').append(tr);

											});

						 }); */

	}
</script>


<script>
function myFunction() {
	
  var input, filter, table,idtable1, tr, td,td1, i;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  idtable1 = document.getElementById("activeTable").value;
  table = document.getElementById(idtable1);
 //alert(table);
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1];
    td1 = tr[i].getElementsByTagName("td")[2];
    if (td || td1) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      }else
    	  if (td1.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
  
}
</script>
<script type="text/javascript">
function exportToExcel()
{
	window.open("${pageContext.request.contextPath}/exportToExcel");
			document.getElementById("expExcel").disabled=true;
}
</script>

</html>