<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  
 <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
 <jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tableSearch.css">
 
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/component.css" />
 <style type="text/css">
    a[disabled="disabled"] {
        pointer-events: none;
    }
    .switch-toggle {
  width: 14em;
}
input[type="checkbox"] {
    display: none;
}
</style>
<body>
<c:url var="showDetailsForLayering" value="/showDetailsForLayering"/>
<c:url var="showDetailsForCp" value="/showDetailsForCp"/>
<c:url var="showDetailsForCoating" value="/showDetailsForCoating"/>
<c:url var="findItemsByGrpId" value="/findItemsByGrpIdForRmIssue"/>
<c:url var="findSfByTypeId" value="/findSfsByTypeId"/>
<c:url var="showDetailItemLayering" value="/showDetailItemLayering"/>
<c:url var="getItemDetailsForManualProduction" value="/getItemDetailsForManualProduction"/>

<c:url var="getItemsByProductionIdCatId" value="/getItemsByProductionIdCatId"/>
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
			
						<i class="fa fa-file-o"></i>Production Headers
					
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
								<i class="fa fa-bars"></i> View Production
							</h3>
							<div class="box-tool">
								<a href=""></a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
						</div>
		             <div class="box-content">
							<form action="${pageContext.request.contextPath}/generateMixingForProduction/${type}" class="form-horizontal"
								id="validation-form" method="get">
                            
								<input type="hidden" name="mode_add" id="mode_add"
									value="add_att">
									
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">From
										Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="from_date" autocomplete="off"
											size="16" type="text" name="from_date" value="${fromDate}"
											required  />
									</div>

									<label class="col-sm-3 col-lg-2 control-label">To Date</label>
									<div class="col-sm-5 col-lg-3 controls">
										<input class="form-control date-picker" id="to_date" autocomplete="off" size="16"
											type="text"  name="to_date" required  value="${toDate}"/>
											
									</div>

									<div
										class="col-sm-25 col-sm-offset-3 col-lg-30 col-lg-offset-0">
										<input type="submit" value="Submit" class="btn btn-primary">
									</div>

								</div>


								<div class="clearfix"></div>
								<div id="table-scroll" class="table-scroll">
							 
									<div id="faux-table" class="faux-table" aria="hidden">
									<table id="table2" class="main-table">
											<thead>
												<tr class="bgpink">
										<th width="80" style="width: 90px">Prod ID</th>
												<th width="200" align="left">Production Date</th>
												<th width="98" align="left">Category</th>
												<th width="200" align="left">Status</th>
												<th width="132" align="left">IsPlanned</th>
												<th width="278" align="right">Action</th>
												</tr>
												</thead>
												</table>
									
									</div>
									<div class="table-wrap">
									
										<table id="table1" class="table table-advance">
											<thead>
												<tr class="bgpink">
												<th width="80" style="width: 90px">Prod ID</th>
												<th width="200" align="left">Production Date</th>
												<th width="98" align="left">Category</th>
												<th width="200" align="left">Status</th>
												<th width="132" align="left">IsPlanned</th>
												<th width="278" align="right">Action</th>
												</tr>
												</thead>
										<tbody>

											<c:forEach items="${planHeader}" var="planHeader">
<input type="hidden" name="prodStatus" id="prodStatus${planHeader.productionHeaderId}" value="${planHeader.productionStatus}"/>
												<tr>

													<td align="left"><c:out
															value="${planHeader.productionHeaderId}" /></td>
													<td align="left"><c:out
															value="${planHeader.productionDate}" /></td>
													<td align="left"><c:out value="${planHeader.catName}" /></td>

													<c:choose>
														<c:when test="${planHeader.productionStatus==1}">
															<td align="left"><c:out value="Planning"></c:out></td>

														</c:when>
														<c:when test="${planHeader.productionStatus==2}">
															<td align="left"><c:out value="Added From Order"></c:out></td>

														</c:when>
														<c:when test="${planHeader.productionStatus==3}">
															<td align="left"><c:out value="Production Started"></c:out></td>

														</c:when>
														<c:when test="${planHeader.productionStatus==4}">
															<td align="left"><c:out value="Production Completed"></c:out></td>

														</c:when>
														<c:when test="${planHeader.productionStatus==5}">
															<td align="left"><c:out value="Closed"></c:out></td>

														</c:when>
														<c:otherwise>
															<td align="left"><c:out value=""></c:out></td>

														</c:otherwise>

													</c:choose>
													<c:choose>
														<c:when test="${planHeader.isPlanned==1}">
															<td align="left"><c:out value="Yes"></c:out></td>

														</c:when>
														<c:otherwise>
															<td align="left"><c:out value="No"></c:out></td>
														</c:otherwise>
													</c:choose>


													<td align="left">
														<c:choose>
																<c:when test="${type==1}">
																	<a
																		href="${pageContext.request.contextPath}/addToMixing/${planHeader.productionHeaderId}/MIX"><span
																		class="glyphicon glyphicon-info-sign"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
																</c:when>
																<c:when test="${type==2}">
															<c:choose>
														    <c:when test="${planHeader.productionStatus!=5}">
																	<div class="text-center">
 																 <a href="" onclick="showDetailsForCp(${planHeader.productionHeaderId},'${planHeader.productionDate}')" class="btn btn-default btn-rounded" data-toggle="modal" data-target="#elegantModalForm">CP</a>&nbsp;&nbsp;
																 <a href="" onclick="showDetailsForLayering(${planHeader.productionHeaderId},'${planHeader.productionDate}')" class="btn btn-default btn-rounded" data-toggle="modal" data-target="#elegantModalForm2">Layering</a>
																 <a href="" onclick="showDetailsForCoating(${planHeader.productionHeaderId},'${planHeader.productionDate}')" class="btn btn-default btn-rounded" data-toggle="modal" data-target="#elegantModalForm3">Coating</a>
																 <a href="" onclick="showDetailsForIssue(${planHeader.productionHeaderId},'${planHeader.productionDate}')" class="btn btn-default btn-rounded" data-toggle="modal" data-target="#elegantModalForm4">Issue</a>
															  <a href="" onclick="showDetailsForManual(${planHeader.productionHeaderId},'${planHeader.productionDate}')" class="btn btn-default btn-rounded" data-toggle="modal" data-target="#elegantModalForm5">Manual Prod</a>
 															</div>
 															</c:when>
 															 <c:when test="${planHeader.productionStatus==5}">
																	<div class="text-center">
 																 <a href="#"  class="btn btn-default btn-rounded"  style="color:green;">CP</a>&nbsp;&nbsp;
																 <a href="#" class="btn btn-default btn-rounded" style="color:green;">Layering</a>
																 <a href="#"  class="btn btn-default btn-rounded" style="color:green;">Coating</a>
																 <a href="" onclick="showDetailsForIssue(${planHeader.productionHeaderId},'${planHeader.productionDate}')" class="btn btn-default btn-rounded" data-toggle="modal" data-target="#elegantModalForm4">Issue</a>
															  <a href="" onclick="showDetailsForManual(${planHeader.productionHeaderId},'${planHeader.productionDate}')" class="btn btn-default btn-rounded" data-toggle="modal" data-target="#elegantModalForm5">Manual Prod</a>
 															</div>
 															</c:when>
 															</c:choose>
															    </c:when>
																
																<c:otherwise>
																--
																</c:otherwise>
														</c:choose>
														</td>
												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>

							</div>
							</form>
						</div>
					</div>

				</div>

	</div>
	
<!------------------------------------------ MODEL 1-------------------------------------------------->
<div class="modal fade" id="elegantModalForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
  aria-hidden="true" >
  
  <!--SAVE LOADER-->
    <div id="overlay">
	<div class="clock"></div>
  </div>
  
  <div class="modal-dialog" role="document" style="width:80%;height:50%;">
    <!--Content-->
    <div class="modal-content form-elegant">
      <!--Header-->
       <div class="modal-header text-center">
        <h3 class="modal-title w-100 dark-grey-text font-weight-bold my-3" id="myModalLabel" style="color:#ea4973;"><strong>Generate Mixing For Cream Preparation</strong></h3>     
            <a href="#" class="close" data-dismiss="modal" aria-label="Close" id="closeHrefModel">
                <img src="${pageContext.request.contextPath}/resources/img/close.png" alt="X" class="imageclass"/>
            </a> 
         <div>
      </div> 
      <div class="modal-body mx-6" >
      	<form name="modalfrm" id="modalfrm"  method="post"> 
      			<label class="col-sm-3 col-lg-3 control-label" style="color:#e20b31;">Production Id :<span id="prodIdSpan"></span></label>
      		    <label class="col-sm-3 col-lg-4 control-label" style="color:#e20b31;">Production Date :<span id="prodDateSpan"></span></label>
      		     <label class="col-sm-3 col-lg-4 control-label" style="color:blue;">DEPT :<span id="dep1">  BMS to BMS </span></label>
     		 <input type="hidden" name="dept" id="dept"  />
     		  <input type="hidden" name="prodHeaderId" id="prodHeaderId"  />
     			<div class="component">
     		
									<table width="80%"  id="modeltable" style="font-size: 13px; font-weight:bold; border: 1px solid;border-color: #91d6b8;" > <!-- class="table table-advance" -->
										<thead>
											<tr>
											<th width="17" style="width: 18px"><input type="checkbox" />  </th>
												<th width="17" style="width: 18px">Sr No</th>
												<th width="120" align="left">Product Name</th>
												<th width="100" align="left">Product Type</th>
												<th width="120" align="left">Qty</th>

												<th width="120" align="left">Edit Qty</th>

												<th width="100" align="left">UOM</th>
											</tr>
										</thead>
										<tbody>										
										</tbody>
									</table>
									
								</div>
								<div class="component" >
									<table width="80%"  id="modeltable2" style="font-size: 13px; font-weight:bold; border: 1px solid;border-color: #91d6b8;" > <!-- class="table table-advance" -->
										<thead>
											<tr>
												<th width="17" style="width: 18px">Sr No</th>
												<th width="120" align="left">Product Name</th>
												<th width="100" align="left">Product Type</th>
												<th width="120" align="left">Qty</th>
												<th width="120" align="left">Edit Qty</th>
												<th width="100" align="left">UOM</th> 
											</tr>
										</thead>
										<tbody>
											
										</tbody>
									</table>
								
								</div>
								</form>	
						</div>			
      <!--Body-->
      <div class="modal-body mx-4" >
        <!--Body-->
        <div class="text-center mb-1">
          <button type="button" class="btn btn-primary" id="sbtbtn" disabled="disabled">Submit</button>
        </div>          
      </div>
      <!--Footer-->   
    </div>
    <!--/.Content-->
  </div>
</div></div>
<!----------------------------------------------End MODEL 1------------------------------------------------>

<!------------------------------------------ MODEL 2-------------------------------------------------->
<div class="modal fade" id="elegantModalForm2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
  aria-hidden="true" >
  <!-- SAVE LOADER -->
  <div id="overlay2">
	<div class="clock"></div>
</div>

  <div class="modal-dialog" role="document" style="width:80%;height:50%;">
    <!--Content-->
    <div class="modal-content form-elegant">
      <!--Header-->
      <div class="modal-header text-center">
        <h3 class="modal-title w-100 dark-grey-text font-weight-bold my-3" id="myModalLabel" style="color:#ea4973;"><strong>Generate Mixing For Layering</strong></h3>
       
            <a href="#" class="close" data-dismiss="modal" aria-label="Close" id="closeHrefModel2">
      <img src="${pageContext.request.contextPath}/resources/img/close.png" alt="X" class="imageclass"/>
    </a> 
     <div>
      </div>
       <div class="modal-body mx-6" >
      	<form name="modalfrm2" id="modalfrm2"  method="post"> 
     		 <input type="hidden" name="dept2" id="dept2"  />
     		 	<label class="col-sm-3 col-lg-3 control-label" style="color:#e20b31;">Production Id :<span id="prodIdSpan2"></span></label>
      		    <label class="col-sm-3 col-lg-4 control-label" style="color:#e20b31;">Production Date :<span id="prodDateSpan2"></span></label>
      		      <label class="col-sm-3 col-lg-4 control-label" style="color:blue;">DEPT :<span id="dep1">  BMS to BMS </span></label>
     		 <input type="hidden" name="prodHeaderId2" id="prodHeaderId2"  />
     		  <input type="hidden" name="itemDetailId2" id="itemDetailId2"  />
     		 
     		 
     			<div class="component">
     		
									<table width="80%"  id="modeltable3" style="font-size: 13px; font-weight:bold; border: 1px solid;border-color: #91d6b8;" > <!-- class="table table-advance" -->
										<thead>
											<tr>
												<th width="17" style="width: 18px">Sr No</th>
												<th width="120" align="left">Product Name</th>
												<th width="100" align="left">Product Type</th>
												<th width="120" align="left">Prep.Qty</th>
												<th width="120" align="left">Prod. Qty</th>
												<th width="100" align="left">UOM</th>
										        <th width="17" style="width: 18px">Action</th>
												
											</tr>
										</thead>
										<tbody>
											
										</tbody>
									</table>
									
								</div>
								<div class="component" >
									<table width="80%"  id="modeltable4" style="font-size: 13px; font-weight:bold; border: 1px solid;border-color: #91d6b8;" > <!-- class="table table-advance" -->
										<thead>
											<tr>
												<th width="17" style="width: 18px">Sr No</th>
												<th width="120" align="left">Product Name</th>
												<th width="100" align="left">Product Type</th>
												<th width="120" align="left">Qty</th>
												<th width="120" align="left">Edit Qty</th>
												<th width="100" align="left">UOM</th> 
											</tr>
										</thead>
										<tbody>
											
										</tbody>
									</table>
								
								</div>
								</form>	
						</div>			
      <!--Body-->
      <div class="modal-body mx-4" >
        <!--Body-->
        <div class="text-center mb-1">
          <button type="button" class="btn btn-primary" id="sbtbtn2" disabled="disabled">Submit</button>
        </div>         
      </div>
      <!--Footer-->    
    </div>
    <!--/.Content-->
  </div>
</div>
</div>
<!----------------------------------------------End Model 2------------------------------------------------>

<!------------------------------------------ MODEL 3-------------------------------------------------->
<div class="modal fade" id="elegantModalForm3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
  aria-hidden="true" >
  <!-- SAVE LOADER -->
<div id="overlay3">
	<div class="clock"></div>
</div>

  <div class="modal-dialog" role="document" style="width:80%;height:50%;">
    <!--Content-->
    <div class="modal-content form-elegant">
      <!--Header-->
      <div class="modal-header text-center">
        <h3 class="modal-title w-100 dark-grey-text font-weight-bold my-3" id="myModalLabel" style="color:#ea4973;"><strong>Generate Mixing For Coating</strong></h3>
       
            <a href="#" class="close" data-dismiss="modal" aria-label="Close" id="closeHrefModel3">
      <img src="${pageContext.request.contextPath}/resources/img/close.png" alt="X" class="imageclass"/>
    </a> 
     <div>
      </div>
       <div class="modal-body mx-6" >
      	<form name="modalfrm3" id="modalfrm3"  method="post"> 
     		 <input type="hidden" name="dept3" id="dept3"  />
     		 	<label class="col-sm-3 col-lg-3 control-label" style="color:#e20b31;">Production Id :<span id="prodIdSpan3"></span></label>
      		    <label class="col-sm-3 col-lg-4 control-label" style="color:#e20b31;">Production Date :<span id="prodDateSpan3"></span></label>
      		      <label class="col-sm-3 col-lg-4 control-label" style="color:blue;">DEPT :<span id="dep1">  BMS to BMS </span></label>
     		 <input type="hidden" name="prodHeaderId3" id="prodHeaderId3"  />
     		  <input type="hidden" name="itemDetailId3" id="itemDetailId3"  />
     		 
     		 
     			<div class="component">
     		
									<table width="80%"  id="modeltable5" style="font-size: 13px; font-weight:bold; border: 1px solid;border-color: #91d6b8;" > <!-- class="table table-advance" -->
										<thead>
											<tr>
												<th width="17" style="width: 18px">Sr No</th>
												<th width="120" align="left">Product Name</th>
												<th width="100" align="left">Product Type</th>
												<th width="120" align="left">Qty</th>
												<th width="120" align="left">Edit Qty</th>
												<th width="100" align="left">UOM</th>
										        <th width="17" style="width: 18px">Action</th>
												
											</tr>
										</thead>
										<tbody>
											
										</tbody>
									</table>
									
								</div>
								<div class="component" >
									<table width="80%"  id="modeltable6" style="font-size: 13px; font-weight:bold; border: 1px solid;border-color: #91d6b8;" > <!-- class="table table-advance" -->
										<thead>
											<tr>
												<th width="17" style="width: 18px">Sr No</th>
												<th width="120" align="left">Product Name</th>
												<th width="100" align="left">Product Type</th>
												<th width="120" align="left">Qty</th>
												<th width="120" align="left">Edit Qty</th>
												<th width="100" align="left">UOM</th> 
											</tr>
										</thead>
										<tbody>
											
										</tbody>
									</table>
								
								</div>
								</form>	
						</div>			
      <!--Body-->
      <div class="modal-body mx-4" >
        <!--Body-->
        <div class="text-center mb-1">
          <button type="button" class="btn btn-primary" id="sbtbtn3" disabled="disabled">Submit</button>
        </div>         
      </div>
      <!--Footer-->    
    </div>
    <!--/.Content-->
  </div>
</div>
</div>
<!----------------------------------------------End Model 3----------------------------------------------->
<!------------------------------------------ MODEL 4-------------------------------------------------->
<div class="modal fade" id="elegantModalForm4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
  aria-hidden="true" >
  <!-- SAVE LOADER -->
<div id="overlay4">
	<div class="clock"></div>
</div>
	<form name="modalfrm4" id="modalfrm4"  method="post"> 
  <div class="modal-dialog" role="document" style="width:80%;height:50%;">
    <!--Content-->
    <div class="modal-content form-elegant">
      <!--Header-->
      <div class="modal-header text-center">
    
        <h3 class="modal-title w-100 dark-grey-text font-weight-bold my-3" id="myModalLabel" style="color:#ea4973;"><strong>ISSUE RM</strong>  <div class="switch-toggle switch-3 switch-candy">
  <input id="on" name="stated" type="radio" checked="" value="0" style="color:green;" onclick="onGrpChange(0)"/>
  <label for="on" onclick="">Issue</label>

  <input id="off" name="stated" type="radio" value="1" style="color:green;"   onclick="onGrpChange(1)"/>
  <label for="off" onclick="">Manual Issue</label>

  <a></a>
</div></h3>
       
            <a href="#" class="close" data-dismiss="modal" aria-label="Close" id="closeHrefModel4">
      <img src="${pageContext.request.contextPath}/resources/img/close.png" alt="X" class="imageclass"/>
    </a> 
     <div>
      </div>
       <div class="modal-body mx-6" >
      
      	 <div class="form-group">	<label class="col-sm-3 col-lg-3 control-label" style="color:#e20b31;">Production Id :<span id="prodIdSpan4"></span></label>
      		    <label class="col-sm-3 col-lg-4 control-label" style="color:#e20b31;">Production Date :<span id="prodDateSpan4"></span></label>
      		   <label class="col-sm-3 col-lg-4 control-label" style="color:blue;">DEPT :<span id="dep1">  Production to BMS </span></label>
      		   </div> <br><br>
     		 <input type="hidden" name="dept4" id="dept4"  />
     		 <input type="hidden" name="prodHeaderId4" id="prodHeaderId4"  />
     		  <input type="hidden" name="itemDetailId4" id="itemDetailId4"  />
     		 <div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Group</label>
											<div class="col-sm-9 col-lg-8 controls">
												<select data-placeholder="Select Group" name="grpId"
													class="form-control chosen" tabindex="-1" id="grpId" onchange="onGrpChange(0)"
													data-rule-required="true"  >
						<option value="" disabled="disabled" selected style="text-align: left;">Select Group</option>
													
	                                            	<c:forEach items="${miniSubCategory}" var="miniSubCategory">
												<option value="${miniSubCategory.miniCatId}" style="text-align: left;">${miniSubCategory.miniCatName}</option>
											</c:forEach>

												</select>
											</div>
										</div>
<br><br><br>
											<div class="form-group">
											<label class="col-sm-3 col-lg-2 control-label">Items</label>
											<div class="col-sm-9 col-lg-8 controls">
												<select data-placeholder="Select Items" name="items[]"
													class="form-control chosen" tabindex="-1" id="items" multiple="multiple"
													data-rule-required="true" onchange='onAllGrpSelect()'>
                                                   
												</select>
												
											</div>
										<!-- </div><br>
										 <div class="form-group"> -->
										 <input type="button" class="btn bn-primary" value="Search" id="searchIssueItems" onclick="searchIssueItems()"/>
										 </div>
     		 
     			<div class="component">
     		
									<table width="80%"  id="modeltable7" style="font-size: 13px; font-weight:bold; border: 1px solid;border-color: #91d6b8;" > <!-- class="table table-advance" -->
										<thead>
											<tr>
												<th width="17" style="width: 18px">Sr No</th>
												<th width="120" align="left">Product Name</th>
												<th width="100" align="left">Product Type</th>
												<th width="120" align="left">Qty</th>
												<th width="120" align="left">Edit Qty</th>
												<th width="100" align="left">UOM</th>
												
											</tr>
										</thead>
										<tbody>
											
										</tbody>
									</table>
									
								</div>
								

						</div>			
      <!--Body-->
      <div class="modal-body mx-4" >
        <!--Body-->
        <div class="text-center mb-1">
          <button type="button" class="btn btn-primary" id="sbtbtn4" disabled="disabled">Submit</button>
        </div>         
      </div>
      <!--Footer-->    
    </div>
    <!--/.Content-->
  </div>
</div>								</form>	
</div>
<!----------------------------------------------End Model 4----------------------------------------------->
<!------------------------------------------ MODEL 5-------------------------------------------------->
<div class="modal fade" id="elegantModalForm5" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
  aria-hidden="true"  >
<div id="overlay7">
	<div class="clock"></div>
</div>
  <div class="modal-dialog" role="document" style="width:80%;height:50%;">
    <!--Content-->
    <div class="modal-content form-elegant">
      <!--Header-->
      <div class="modal-header text-center">
        <h3 class="modal-title w-100 dark-grey-text font-weight-bold my-3" id="myModalLabel" style="color:#ea4973;"><strong>Manual MIX & BOM</strong></h3>
       
            <a href="#" class="close" data-dismiss="modal" aria-label="Close" id="closeHrefModel5">
      <img src="${pageContext.request.contextPath}/resources/img/close.png" alt="X" class="imageclass"/>
    </a> 
     <div>
      </div>
       <div class="modal-body mx-6" >
      	<form name="modalfrm5" id="modalfrm5"  method="post"> 
      	 <div class="form-group">
      		<label class="col-sm-3 col-lg-3 control-label" style="color:#e20b31;">Production Id :<span id="prodIdSpan5"></span></label>
      		    <label class="col-sm-3 col-lg-4 control-label" style="color:#e20b31;">Production Date :<span id="prodDateSpan5"></span></label>
       <label class="col-sm-3 col-lg-4 control-label" style="color:blue;">DEPT :<span id="dep1">  BMS to BMS </span></label>
      	</div>   <br><br>
     		 <input type="hidden" name="dept5" id="dept5"  />
     		 <input type="hidden" name="prodHeaderId5" id="prodHeaderId5"  />
     		  <input type="hidden" name="itemDetailId5" id="itemDetailId5"  />
     		 <div class="form-group">
											<%-- <label class="col-sm-3 col-lg-2 control-label">Dept</label>
											<div class="col-sm-9 col-lg-3 controls">
												<select data-placeholder="Select Type" name="deptId"
													class="form-control chosen" tabindex="-1" id="deptId" onchange="onDeptChange(this.value)"
													data-rule-required="true"  >
						<option value="" disabled="disabled" selected style="text-align: left;">Select Department</option>
														
	                                         <c:forEach items="${departmentList}" var="departmentList">
												<option value="${departmentList.deptId}" style="text-align: left;">${departmentList.deptName}</option>
											 </c:forEach> 

												</select>
											</div> --%>
											<label class="col-sm-3 col-lg-1 control-label">Type</label>
											<div class="col-sm-9 col-lg-3 controls"> 
												<select data-placeholder="Select Type" name="typeId"
													class="form-control chosen" tabindex="-1" id="typeId" 
													data-rule-required="true" onchange="onTypeChange(this.value)"  >    
						<option value=""  selected style="text-align: left;">Select Type</option>
												
	                                       <c:forEach items="${sfTypeList}" var="sfTypeList">
	                                       <c:choose>
	                                       <c:when test="${deptId==sfTypeList.delStatus}">
	                                       <option value="${sfTypeList.id}" style="text-align: left;" >${sfTypeList.sfTypeName}</option>
	                                       </c:when>
	                                      
	                                       </c:choose>
												
											</c:forEach> 
												</select>
										</div>	
										<div class="col-sm-9 col-lg-1 controls">	
											 <input type="button"  value="Search" id="searchItemsByType" class='btn btn-default btn-rounded' data-toggle='modal' data-target='#elegantModalForm7' onclick="onItemGetManualProd()"/>
									</div>	<!-- </div>
                                            <br><br><br>
											<div class="form-group"> -->
											<label class="col-sm-3 col-lg-1 control-label">Product</label>
											<div class="col-sm-9 col-lg-3 controls">
												<select data-placeholder="Select Products" name="sfitems"
													class="form-control chosen" tabindex="-1" id="sfitems" 
													data-rule-required="true" onchange='onProductChange(this.value)'>
                                                   
												</select>
												
											</div>
											<label class="col-sm-3 col-lg-1 control-label" style="width: 4.333333%;">Kg</label>
											<div class="col-sm-9 col-lg-2 controls" style="width: 10.333333%;">
												<input type="text" name="no_of_kg" id="no_of_kg" readonly style="font-size:10px;" value="0" class="form-control" />
												
											</div>
										<!-- </div><br>
										 <div class="form-group"> -->
										 	<div class="col-sm-9 col-lg-1 controls">
										 <input type="button" class="btn bn-primary" value="Details" id="searchManualItems" onclick="onSfAddManualProdLayering()"/>
										 </div>
										 </div>
     		 	<div class="component" >
									<table width="80%"  id="modeltable9" style="font-size: 13px; font-weight:bold; border: 1px solid;border-color: #91d6b8;" > <!-- class="table table-advance" -->
										<thead>
											<tr>
												<th width="17" style="width: 18px">Sr No</th>
												<th width="120" align="left">Product Name</th>
												<th width="100" align="left">Product Type</th>
												<th width="120" align="left">Qty</th>
												<th width="120" align="left">Edit Qty</th>
												<th width="100" align="left">UOM</th> 
											</tr>
										</thead>
										<tbody>
											
										</tbody>
									</table>
								
								</div>
     			
								
								</form>	
						</div>			
      <!--Body-->
      <div class="modal-body mx-4" >
        <!--Body-->
        <div class="text-center mb-1">
          <button type="button" class="btn btn-primary" id="sbtbtn7" disabled="disabled">Submit</button>
        </div>         
      </div>
      <!--Footer-->    
    </div>
    <!--/.Content-->
  </div>
</div>
</div>
<!----------------------------------------------End Model 5----------------------------------------------->
<!------------------------------------------ MODEL 6-------------------------------------------------->
<div class="modal fade" id="elegantModalForm6" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
  aria-hidden="true" >
  <!-- SAVE LOADER -->
<div id="overlay6">
	<div class="clock"></div>
</div>

  <div class="modal-dialog" role="document" style="width:50%;height:50%; margin-right: 20px; margin-top: 200px;">
    <!--Content-->
    <div class="modal-content form-elegant">
      <!--Header-->
      <div class="modal-header text-center">
<!--         <h3 class="modal-title w-100 dark-grey-text font-weight-bold my-3" id="myModalLabel" style="color:#ea4973;"><strong>Items</strong></h3>
 -->       
            <a href="#" class="close" data-dismiss="modal" aria-label="Close" id="closeHrefModel6">
      <img src="${pageContext.request.contextPath}/resources/img/close.png" alt="X" class="imageclass"/>
    </a> 
   <!--   <div>
      </div> -->
       <div class="modal-body mx-6" >
      	<form name="modalfrm6" id="modalfrm6"  method="post"> 
      	 <div class="form-group">	
      	 <label class="col-sm-3 col-lg-3 control-label" style="color:#e20b31;">Prod.Id :<span id="prodIdSpan6"></span></label>
      	        <label class="col-sm-3 col-lg-3 control-label" style="color:#e20b31;">Prep.Qty :<span id="prepQtySpan6"></span></label>
      		    <label class="col-sm-3 col-lg-6 control-label" style="color:#e20b31;">Item :<span id="itemNameSpan6"></span></label>
      		   </div> <br><br>
     		 <input type="hidden" name="dept6" id="dept6"  />
     		 <input type="hidden" name="prodHeaderId6" id="prodHeaderId6"  />
     		  <input type="hidden" name="itemDetailId6" id="itemDetailId6"  />
     			<div class="component">
     		
									<table width="80%"  id="modeltable8" style="font-size: 13px; font-weight:bold; border: 1px solid;border-color: #91d6b8;"  >
										<thead>
											<tr>
											<th width="100" align="left">Select</th>
												<th width="17" style="width: 18px">Sr No</th>
												<th width="120" align="left">Item Name</th>
												<th width="120" align="left">Qty</th>
												<th width="100" align="left">UOM</th>
												
											</tr>
										</thead>
										<tbody>
											
										</tbody>
									</table>
									
								</div>
								
								</form>	
						</div>			
      <!--Body-->
     <!--  <div class="modal-body mx-4" >
        Body
        <div class="text-center mb-1">
          <button type="button" class="btn btn-primary" id="sbtbtn6" disabled="disabled">Submit</button>
        </div>         
      </div> -->
      <!--Footer-->    
    </div>
    <!--/.Content-->
  </div>
</div>
</div>
<!----------------------------------------------End Model 6---------------------------------------------->

<!------------------------------------------ MODEL 7-------------------------------------------------->
<div class="modal fade" id="elegantModalForm7" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
  aria-hidden="true" >
  <!-- SAVE LOADER -->
<div id="overlay8">
	<div class="clock"></div>
</div>

  <div class="modal-dialog" role="document" style="width:50%;height:50%; margin-right: 20px; margin-top: 5px;">
    <!--Content-->
    <div class="modal-content form-elegant">
      <!--Header-->
      <div class="modal-header text-center">
<!--         <h3 class="modal-title w-100 dark-grey-text font-weight-bold my-3" id="myModalLabel" style="color:#ea4973;"><strong>Items</strong></h3>
 -->       
            <a href="#" class="close" data-dismiss="modal" aria-label="Close" id="closeHrefModel7">
      <img src="${pageContext.request.contextPath}/resources/img/close.png" alt="X" class="imageclass"/>
    </a> 
   <!--   <div>
      </div> -->
       <div class="modal-body mx-6" >
      	<form name="modalfrm7" id="modalfrm7"  method="post"> 
      	 <div class="form-group">	
      	 <label class="col-sm-3 col-lg-3 control-label" style="color:#e20b31;">Prod.Id :<span id="prodIdSpan7"></span></label>
      		    <label class="col-sm-3 col-lg-6 control-label" style="color:#e20b31;">Type :<span id="itemNameSpan7"></span></label>
      		   </div>
     		 <input type="hidden" name="dept7" id="dept7"  />
     		 <input type="hidden" name="prodHeaderId7" id="prodHeaderId7"  />
     		  <input type="hidden" name="itemDetailId7" id="itemDetailId7"  />
     			<div class="component">
     			<div class="col-md-7" ></div> 
     		<label for="search" class="col-md-5" id="search">
    <i class="fa fa-search" style="font-size:20px"></i>
									<input type="text"  id="myInput" onkeyup="myFunction()" placeholder="Search items by Name" title="Type in a name" style="border-radius:25px;">
										</label>  
									<table width="80%"  id="modeltable10" style="font-size: 13px; font-weight:bold; border: 1px solid;border-color: #91d6b8;"  >
										<thead>
											<tr>
												<th width="17" style="width: 18px">Sr No</th>
												<th width="120" align="left">Item Name</th>
												<th width="120" align="left">Qty</th>
												<th width="100" align="left">UOM</th>
												
											</tr>
										</thead>
										<tbody>
											
										</tbody>
									</table>
									
								</div>
								
								</form>	
						</div>			
      <!--Body-->
     <!--  <div class="modal-body mx-4" >
        Body
        <div class="text-center mb-1">
          <button type="button" class="btn btn-primary" id="sbtbtn6" disabled="disabled">Submit</button>
        </div>         
      </div> -->
      <!--Footer-->    
    </div>
    <!--/.Content-->
  </div>
</div>
</div>
<!----------------------------------------------End Model 7---------------------------------------------->
	<!-- END Main Content -->
	<footer>
	<p>2019 © MONGINIS.</p>
	</footer>
	
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/jquery.ba-throttle-debounce.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/jquery.stickyheader.js"></script>
	<a id="btn-scrollup" class="btn btn-circle btn-lg" href="#"><i
		class="fa fa-chevron-up"></i></a>
</div>
	<!-- END Content -->
</div>
	<!-- END Container -->
<script type="text/javascript">
    function showDetailsForCp(prodHeaderId,prodDate)
    {
    	$("#prodIdSpan").css("color", "red");
		$("#prodDateSpan").css("color", "red");
    	$('#modeltable2 td').remove();
    	$.getJSON('${showDetailsForCp}', {
    		
    		prodHeaderId:prodHeaderId,
    		toDept:'BMS',
    		ajax : 'true',
    	},  function(data) {
    		var len = data.length;
    		$('#modeltable td').remove();
    		document.getElementById("dept").value="BMS";
    		document.getElementById("prodHeaderId").value=prodHeaderId;
    		document.getElementById("prodIdSpan").innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;"+prodHeaderId;
    		document.getElementById("prodDateSpan").innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;"+prodDate;

    		var approveStatusStyle="disabled";var styleClass="";
    		$.each(data,function(key, data) {
    			
    			if(data.doubleCut==0){
    				approveStatusStyle="";
    				styleClass="";
    			}
    			if(data.doubleCut==1){
    				styleClass="disabled";
    			}
    			
    			 var actQty=0;
				 if(data.total>0)
    			 actQty=(data.total/1000).toFixed(3);
						var tr = $('<tr></tr>');
						tr.append($('<td></td>').html("<input type=checkbox name='chk'  "+styleClass+" value="+data.itemDetailId+"   id="+ data.itemDetailId+"  onclick='onEditCp()'>  <label for="+ data.itemDetailId+" ></label>"));
					  	tr.append($('<td></td>').html(key+1));
					  	tr.append($('<td></td>').html(data.rmName+""+"<input type=hidden value='"+data.rmName+"'  id=rmName"+data.itemDetailId+"  name=rmName"+data.itemDetailId+"  >"));
					 
					  	if(data.rmType==1)
					  	tr.append($('<td></td>').html("RM"+"<input type=hidden value="+data.singleCut+"  id=mulFactor"+data.itemDetailId+"  name=mulFactor"+data.itemDetailId+"  >"));
					  	else
						  	tr.append($('<td></td>').html("SF"+"<input type=hidden value="+data.singleCut+"  id=mulFactor"+data.itemDetailId+"  name=mulFactor"+data.itemDetailId+"  >"));
					  	tr.append($('<td></td>').html(actQty+""+"<input type=hidden value="+actQty+"  id=prevRmQty"+data.itemDetailId+"  name=prevRmQty"+data.itemDetailId+"  >"));
					  	tr.append($('<td></td>').html("<input type=text onkeypress='return IsNumeric(event);' style='width:100px;border-radius:25px; font-weight:bold;text-align:center;'   ondrop='return false;' min='0'  onpaste='return false;' style='text-align: center;' class='form-control' name='rmQty"+data.itemDetailId+"'  id=rmQty"
								+ data.itemDetailId+" value="+actQty+" oninput='onSfAdd()' /> &nbsp;  <input type=hidden id=sfId"+data.itemDetailId+"  name=sfId"+data.itemDetailId+"  value="+data.rmId+" />" ));
						tr.append($('<td></td>').html(data.uom+""+"<input type=hidden value='"+data.uom+"'  id=uom"+data.itemDetailId+"  name=uom"+data.itemDetailId+"  >"));
						$('#modeltable tbody').append(tr);
    		});
    		var tr = $('<tr></tr>');
			tr.append($('<td colspan="6"></td>').html("<a href='#' disabled="+approveStatusStyle+"  style='text-decoration:underline;' onclick='checkAll()' > Select All </a> &nbsp;&nbsp;<a href='#' style='color:grey;text-decoration:underline;' onclick='uncheckAll()'> Remove </a> "));
			tr.append($('<td ></td>').html(" <button type='button' "+approveStatusStyle+"   class='btn btn-primary' id='addSfItem' onclick='onSfAdd()'  >Add</button>"));
			$('#modeltable tbody').append(tr);
    		if(approveStatusStyle=="disabled")
    		{
    			$("#prodIdSpan").css("color", "green");
    			$("#prodDateSpan").css("color", "green");
    		}
    	});
    }
   
   
    function checkAll ()
    {
    	$("input[type=checkbox]").prop('checked', true);alert("jj");
    	showTotalQty();
    }
    function uncheckAll ()
    {
    	$("input[type=checkbox]").prop('checked', false);
    	showTotalQty();
    }
    </script>
    <script type="text/javascript">
 function onSfAdd() {
	 
	    document.getElementById("addSfItem").value="Adding...";
	  var sfItems = [];

	 var arr=[];
		$('input[name="chk"]:checked').each(function() {
			 arr.push(this.value);
		});
		arr.forEach(function(v) {
		var sf = {
				  "sfId": $('#sfId'+v).val(),
				  "rmQty": $('#rmQty'+v).val()
				}
		sfItems.push(sf);
		});
  			$("#sbtbtn").prop("disabled", true);
  		 	$.ajax({
  		             type: "POST",
  		             contentType: "application/json",
  		             url: "${pageContext.request.contextPath}/getSfDetails",
  		             data: JSON.stringify(sfItems),
  		             dataType: 'json',
  		             timeout: 600000,
  		             success: function (data) {
  		                 $("#sbtbtn").prop("disabled", false);
  		               
  		               var len = data.length;
  		     		$('#modeltable2 td').remove();
  		     		$.each(data,function(key, data) {
  		     		    document.getElementById("addSfItem").value="Add";

  	    			  var rmName=(data.rmName.split("#"))[0]; var uom=(data.rmName.split("#"))[1];
  	    			 var actQty=0;
  					 if(data.rmQty>0)
  	    			 actQty=(data.rmQty).toFixed(3);
  		 						var tr = $('<tr></tr>');
  		 						//tr.append($('<td></td>').html("<input type=checkbox name='chk'  value="+data.itemDetailId+"   id="+ data.itemDetailId+"  >  <label for="+ data.itemDetailId+" ></label>"));
  		 					  	tr.append($('<td></td>').html((key+1)+"<input type=hidden name='sfDid'  value="+data.sfDid+"   id="+ data.sfDid+"  > "));
  		 					  	tr.append($('<td></td>').html(rmName+"<input type=hidden name=rmName"+data.sfDid+" id=rmName"+data.sfDid+" value='"+rmName+"' />"));
  		 					  	if(data.rmType==1)
  		 					  	tr.append($('<td></td>').html("RM"+"<input type=hidden name=rmType"+data.sfDid+" id=rmType"+data.sfDid+" value=1 />"));
  		 					  	else
  		 						  	tr.append($('<td></td>').html("SF"+"<input type=hidden name=rmType"+data.sfDid+" id=rmType"+data.sfDid+" value=2 />"));
  		 					  	tr.append($('<td></td>').html(actQty+"<input type=hidden name=prevRmQty"+data.sfDid+" id=prevRmQty"+data.sfDid+" value="+data.rmQty+" />"));
  		 					  	tr.append($('<td></td>').html("<input type=text onkeypress='return IsNumeric(event);' style='width:99px;border-radius:25px; font-weight:bold;text-align:center;'   ondrop='return false;' min='0'  onpaste='return false;' style='text-align: center;' class='form-control'  name=rmQty2"+ data.sfDid+"  id=rmQty2"
  		 								+ data.sfDid+" value="+actQty+" readonly   /> &nbsp;  <input type=hidden id=rmId"+data.sfDid+"  name=rmId"+data.sfDid+"  value="+data.rmId+" />" ));
  		 						tr.append($('<td></td>').html(uom+""+"<input type=hidden value='"+uom+"'  id=uomRm"+data.sfDid+"  name=uomRm"+data.sfDid+"  >"));
  		 						$('#modeltable2 tbody').append(tr);
  	     		});
  		     	        
  		             },
  		             error: function (e) {
  		                 $("#sbtbtn").prop("disabled", false);
   		     		    document.getElementById("addSfItem").value="Add";

  		             }
  			});
  		

  		}

    </script>
    <script type="text/javascript">
    	$('#sbtbtn').click(function(){
    		$("#overlay").fadeIn(300);
    		
    		$.ajax({
    			    type: "POST",
		             url: "${pageContext.request.contextPath}/postCreamPrepData",
		             data: $("#modalfrm").serialize(),
		             dataType: 'json',
    			success: function(data){
    				if(data==2)
    					{
    					$('#modeltable td').remove();
    					$('#modeltable2 td').remove();
    					alert("Mixing And Bom Done")
    					$("#overlay").fadeOut(300);
    					$("#closeHrefModel")[0].click()


    					}
    			}
    		}).done(function() {
    			setTimeout(function(){
    				$("#overlay").fadeOut(300);
    			},500);
    		});
    	});	
    	
    </script>
    <script type="text/javascript">
    function showDetailsForLayering(prodHeaderId,prodDate)
    {
    	$('#modeltable4 td').remove();
    	$.getJSON('${showDetailsForLayering}', {
    		
    		prodHeaderId:prodHeaderId,
    		toDept:'BMS',
    		ajax : 'true',
    	},  function(data) {
    		var len = data.length;
    		$('#modeltable3 tbody').empty();

    		document.getElementById("dept2").value="BMS";
    		document.getElementById("prodHeaderId2").value=prodHeaderId;
    		document.getElementById("prodIdSpan2").innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;"+prodHeaderId;
    		document.getElementById("prodDateSpan2").innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;"+prodDate;
    		$.each(data,function(key, data) {
    			 var actQty=0;
    				 if(data.total>0)
    					 actQty=(data.total/1000).toFixed(3);
						var tr = $('<tr id="modeltable3'+data.itemDetailId+'" ></tr>');
					  	tr.append($('<td></td>').html(key+1+"<input type=hidden name=layer"+data.itemDetailId+" id=layer"+data.itemDetailId+" > "));
					  	tr.append($('<td></td>').html("<a href='#' onclick='showDetailsForLayerDetails("+prodHeaderId+","+data.rmId+","+data.itemDetailId+",\""+prodDate+"\","+actQty+",\""+data.rmName+"\")'   class='btn btn-default btn-rounded' data-toggle='modal' data-target='#elegantModalForm6'>"+data.rmName+"</a>"+"<input type=hidden value='"+data.rmName+"'  id=rmName"+data.itemDetailId+"  name=rmName"+data.itemDetailId+"  >"));
					 
					  	if(data.rmType==1)
					  	tr.append($('<td></td>').html("RM"+"<input type=hidden value="+data.singleCut+"  id=mulFactor"+data.itemDetailId+"  name=mulFactor"+data.itemDetailId+"  >"));
					  	else
						  	tr.append($('<td></td>').html("SF"+"<input type=hidden value="+data.singleCut+"  id=mulFactor"+data.itemDetailId+"  name=mulFactor"+data.itemDetailId+"  >"));
					  	tr.append($('<td></td>').html(actQty+""+"<input type=hidden value="+actQty+"  id=prevRmQty"+data.itemDetailId+"  name=prevRmQty"+data.itemDetailId+"  >"));
					  	tr.append($('<td></td>').html("<input type=text onkeypress='return IsNumeric(event);' style='width:100px;border-radius:25px; font-weight:bold;text-align:center;'   ondrop='return false;' min='0'  onpaste='return false;' style='text-align: center;' class='form-control' name='rmQty"+data.itemDetailId+"'  id='rmQty"
								+ data.itemDetailId+"' value="+0+"  readonly /> &nbsp;  <input type=hidden id=sfId"+data.itemDetailId+"  name=sfId"+data.itemDetailId+"  value="+data.rmId+" />" ));
						tr.append($('<td></td>').html(data.uom+""+"<input type=hidden value='"+data.uom+"'  id=uom"+data.itemDetailId+"  name=uom"+data.itemDetailId+"  >"));
						tr.append($('<td></td>').html("<input type=button name='btn' id='layerbombtn"+data.itemDetailId+"' value='BOM' class='btn btn-primary' onclick=onSfAdd2("+data.itemDetailId+") > "));

						$('#modeltable3 tbody').append(tr);
    		});
    		
    		
    	});
    }
    </script>
     <script type="text/javascript">
    function showDetailsForCoating(prodHeaderId,prodDate)
    {
    	$("#prodIdSpan3").css("color", "red");
		$("#prodDateSpan3").css("color", "red");
    	$('#modeltable6 td').remove();
    	$.getJSON('${showDetailsForCoating}', {
    		
    		prodHeaderId:prodHeaderId,
    		toDept:'BMS',
    		ajax : 'true',
    	},  function(data) {
    		var len = data.length;
    		$('#modeltable5 tbody').empty();

    		document.getElementById("dept3").value="BMS";
    		document.getElementById("prodHeaderId3").value=prodHeaderId;
    		document.getElementById("prodIdSpan3").innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;"+prodHeaderId;
    		document.getElementById("prodDateSpan3").innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;"+prodDate;
    		var approveStatusStyle="disabled";var styleClass="";
    		$.each(data,function(key, data) {
    			
    			if(data.doubleCut==0){
    				approveStatusStyle="";
    			}
    			if(data.doubleCut==1){
    				styleClass="disabled";
    			}
    			
    			
    			 var actQty=0;
				 if(data.total>0)
    			 actQty=(data.total/1000).toFixed(3);
						var tr = $('<tr id="modeltable5'+data.itemDetailId+'" ></tr>');
					  	tr.append($('<td></td>').html(key+1));
					  	tr.append($('<td></td>').html(data.rmName+""+"<input type=hidden value='"+data.rmName+"'  id=rmName"+data.itemDetailId+"  name=rmName"+data.itemDetailId+"  >"));
					 
					  	if(data.rmType==1)
					  	tr.append($('<td></td>').html("RM"+"<input type=hidden value="+data.singleCut+"  id=mulFactor"+data.itemDetailId+"  name=mulFactor"+data.itemDetailId+"  >"));
					  	else
						  	tr.append($('<td></td>').html("SF"+"<input type=hidden value="+data.singleCut+"  id=mulFactor"+data.itemDetailId+"  name=mulFactor"+data.itemDetailId+"  >"));
					  	tr.append($('<td></td>').html(actQty+""+"<input type=hidden value="+actQty+"  id=prevRmQty"+data.itemDetailId+"  name=prevRmQty"+data.itemDetailId+"  >"));
					  	tr.append($('<td></td>').html("<input type=text onkeypress='return IsNumeric(event);' style='width:100px;border-radius:25px; font-weight:bold;text-align:center;'   ondrop='return false;' min='0'  onpaste='return false;' style='text-align: center;' class='form-control' name='rmQty"+data.itemDetailId+"'  id=rmQty"
								+ data.itemDetailId+" value="+actQty+" readonly  /> &nbsp;  <input type=hidden id=sfId"+data.itemDetailId+"  name=sfId"+data.itemDetailId+"  value="+data.rmId+" />" ));
						tr.append($('<td></td>').html(data.uom+""+"<input type=hidden value='"+data.uom+"'  id=uom"+data.itemDetailId+"  name=uom"+data.itemDetailId+"  >"));
						tr.append($('<td></td>').html("<input type=button name='btn'  "+styleClass+"    id='btn' value='BOM' class='btn btn-primary' onclick=onSfAdd3("+data.itemDetailId+") > "));

						$('#modeltable5 tbody').append(tr);
    		});
    		
    		if(approveStatusStyle=="disabled")
    		{
    			$("#prodIdSpan3").css("color", "green");
    			$("#prodDateSpan3").css("color", "green");
    		}
    	});
    }
    </script>
     <script type="text/javascript">
 function onSfAdd2(itemDetailId) {
	 
	 $('#modeltable3 tr').removeClass("pinkselected");
     $('#modeltable3 tr').addClass("blackselected");
     document.getElementById('modeltable3'+itemDetailId).className = 'pinkselected';
     document.getElementById('itemDetailId2').value=itemDetailId;
     document.getElementById('layerbombtn'+itemDetailId).disabled=true;

	  var sfItems = [];

	 var arr=[];
	 var flag=0;
			 arr.push(itemDetailId);
		arr.forEach(function(v) {
			if($('#rmQty'+v).val()<=0){
				flag=1;
			}
		var sf = {
				  "sfId": $('#sfId'+v).val(),
				  "rmQty": $('#rmQty'+v).val()
				}
		sfItems.push(sf);
		});
  			$("#sbtbtn2").prop("disabled", true);
  			if(flag==1)
  				{
  				alert("Qty should be greator than 0")
  				}
  			else{
  		 	$.ajax({
  		             type: "POST",
  		             contentType: "application/json",
  		             url: "${pageContext.request.contextPath}/getSfDetailsForLayering",
  		             data: JSON.stringify(sfItems),
  		             dataType: 'json',
  		             timeout: 600000,
  		             success: function (data) {
  		                 $("#sbtbtn2").prop("disabled", false);
  		               
  		               var len = data.length;
  		     		$('#modeltable4 td').remove();
  		     		
  		     		$.each(data,function(key, data) {
  		     			
  	    			  var rmName=(data.rmName.split("#"))[0]; var uom=(data.rmName.split("#"))[1];
  	    			 var actQty=0;
  					 if(data.rmQty>0)
  	    			 actQty=(data.rmQty).toFixed(3);
  		 						var tr = $('<tr></tr>');
  		 						//tr.append($('<td></td>').html("<input type=checkbox name='chk'  value="+data.itemDetailId+"   id="+ data.itemDetailId+"  >  <label for="+ data.itemDetailId+" ></label>"));
  		 					  	tr.append($('<td></td>').html((key+1)+"<input type=hidden name='sfDid'  value="+data.sfDid+"   id="+ data.sfDid+"  > "));
  		 					  	tr.append($('<td></td>').html(rmName+"<input type=hidden name=rmName"+data.sfDid+" id=rmName"+data.sfDid+" value='"+rmName+"' />"));
  		 					  	if(data.rmType==1)
  		 					  	tr.append($('<td></td>').html("RM"+"<input type=hidden name=rmType"+data.sfDid+" id=rmType"+data.sfDid+" value=1 />"));
  		 					  	else
  		 						  	tr.append($('<td></td>').html("SF"+"<input type=hidden name=rmType"+data.sfDid+" id=rmType"+data.sfDid+" value=2 />"));
  		 					  	tr.append($('<td></td>').html(actQty+"<input type=hidden name=prevRmQty"+data.sfDid+" id=prevRmQty"+data.sfDid+" value="+data.rmQty+" />"));
  		 					  	tr.append($('<td></td>').html("<input type=text onkeypress='return IsNumeric(event);' style='width:100px;border-radius:25px; font-weight:bold;text-align:center;'   ondrop='return false;' min='0'  onpaste='return false;' style='text-align: center;' class='form-control'  name=rmQty2"+ data.sfDid+"  id=rmQty2"
  		 								+ data.sfDid+" value="+actQty+" readonly /> &nbsp;  <input type=hidden id=rmId"+data.sfDid+"  name=rmId"+data.sfDid+"  value="+data.rmId+" />" ));
  		 						tr.append($('<td></td>').html(uom+""+"<input type=hidden value='"+uom+"'  id=uomRm"+data.sfDid+"  name=uomRm"+data.sfDid+"  >"));
  		 						$('#modeltable4 tbody').append(tr);
  	     		});
  		     	        
  		             },
  		             error: function (e) {
  		                 $("#sbtbtn2").prop("disabled", false);
  		                 //...
  		             }
  			});
  			}//if close

  		}

    </script>
    <script type="text/javascript">
 function onSfAdd3(itemDetailId) {
	 
	 $('#modeltable5 tr').removeClass("pinkselected");
     $('#modeltable5 tr').addClass("blackselected");
     document.getElementById('modeltable5'+itemDetailId).className = 'pinkselected';
     document.getElementById('itemDetailId3').value=itemDetailId;
	  var sfItems = [];

	 var arr=[];
			 arr.push(itemDetailId);
		arr.forEach(function(v) {
		var sf = {
				  "sfId": $('#sfId'+v).val(),
				  "rmQty": $('#rmQty'+v).val()
				}
		sfItems.push(sf);
		});
  			$("#sbtbtn3").prop("disabled", true);
  		 	$.ajax({
  		             type: "POST",
  		             contentType: "application/json",
  		             url: "${pageContext.request.contextPath}/getSfDetailsForLayering",
  		             data: JSON.stringify(sfItems),
  		             dataType: 'json',
  		             timeout: 600000,
  		             success: function (data) {
  		                 $("#sbtbtn3").prop("disabled", false);
  		               
  		               var len = data.length;
  		     		$('#modeltable6 td').remove();
  		     		$.each(data,function(key, data) {
  		     			
  	    			  var rmName=(data.rmName.split("#"))[0]; var uom=(data.rmName.split("#"))[1];
  	    			 var actQty=0;
  					 if(data.rmQty>0)
  	    			 actQty=(data.rmQty).toFixed(3);
  		 						var tr = $('<tr></tr>');
  		 						//tr.append($('<td></td>').html("<input type=checkbox name='chk'  value="+data.itemDetailId+"   id="+ data.itemDetailId+"  >  <label for="+ data.itemDetailId+" ></label>"));
  		 					  	tr.append($('<td></td>').html((key+1)+"<input type=hidden name='sfDid'  value="+data.sfDid+"   id="+ data.sfDid+"  > "));
  		 					  	tr.append($('<td></td>').html(rmName+"<input type=hidden name=rmName"+data.sfDid+" id=rmName"+data.sfDid+" value='"+rmName+"' />"));
  		 					  	if(data.rmType==1)
  		 					  	tr.append($('<td></td>').html("RM"+"<input type=hidden name=rmType"+data.sfDid+" id=rmType"+data.sfDid+" value=1 />"));
  		 					  	else
  		 						  	tr.append($('<td></td>').html("SF"+"<input type=hidden name=rmType"+data.sfDid+" id=rmType"+data.sfDid+" value=2 />"));
  		 					  	tr.append($('<td></td>').html(actQty+"<input type=hidden name=prevRmQty"+data.sfDid+" id=prevRmQty"+data.sfDid+" value="+data.rmQty+" />"));
  		 					  	tr.append($('<td></td>').html("<input type=text onkeypress='return IsNumeric(event);' style='width:100px;border-radius:25px; font-weight:bold;text-align:center;'   ondrop='return false;' min='0'  onpaste='return false;' style='text-align: center;' class='form-control'  name=rmQty2"+ data.sfDid+"  id=rmQty2"
  		 								+ data.sfDid+" value="+actQty+" readonly /> &nbsp;  <input type=hidden id=rmId"+data.sfDid+"  name=rmId"+data.sfDid+"  value="+data.rmId+" />" ));
  		 						tr.append($('<td></td>').html(uom+""+"<input type=hidden value='"+uom+"'  id=uomRm"+data.sfDid+"  name=uomRm"+data.sfDid+"  >"));
  		 						$('#modeltable6 tbody').append(tr);
  	     		});
  		     	        
  		             },
  		             error: function (e) {
  		                 $("#sbtbtn3").prop("disabled", false);
  		                 //...
  		             }
  			});
  		

  		}

    </script>
    <script type="text/javascript">
    $('#sbtbtn2').click(function(){
		$("#overlay2").fadeIn(300);
		
		$.ajax({
			    type: "POST",
	             url: "${pageContext.request.contextPath}/postLayeringData",
	             data: $("#modalfrm2").serialize(),
	             dataType: 'json',
			success: function(data){
				if(data==2)
					{
					$('#modeltable3 td').remove();
					$('#modeltable4 td').remove();
					alert("Mixing And Bom Done")
					$("#overlay2").fadeOut(300);
					$("#closeHrefModel2")[0].click()


					}
			}
		}).done(function() {
			setTimeout(function(){
				$("#overlay2").fadeOut(300);
			},500);
		});
	});	
    </script>
    <script type="text/javascript">
    $('#sbtbtn3').click(function(){
		$("#overlay3").fadeIn(300);
		
		$.ajax({
			    type: "POST",
	             url: "${pageContext.request.contextPath}/postCoatingData",
	             data: $("#modalfrm3").serialize(),
	             dataType: 'json',
			success: function(data){
				if(data==2)
					{
					$('#modeltable5 td').remove();
					$('#modeltable6 td').remove();
					alert("Mixing And Bom Done")
					$("#overlay3").fadeOut(300);
					$("#closeHrefModel3")[0].click()
					}
			}
		}).done(function() {
			setTimeout(function(){
				$("#overlay3").fadeOut(300);
			},500);
		});
	});	
    </script>
    
<script type="text/javascript">

            function onGrpChange(isManual) { 
            	var isManual = $("input[name='stated']:checked").val();
            	$('#modeltable7 td').remove();
            	$("#sbtbtn4").prop("disabled", true);
            	var grpId=$('#grpId').val();
            	$('#items')
			    .find('option')
			    .remove()
			    .end();
            	var prodHeaderId=$('#prodHeaderId4').val();
                $.getJSON('${findItemsByGrpId}', {
                    grpId : grpId,
                    prodHeaderId : prodHeaderId,
                    fromDept:"PROD",
                    toDept:"BMS",
                    ajax : 'true'
                }, function(data) {
            
                    var len = data.length;

					$('#items')
				    .find('option')
				    .remove()
				    .end()
				 $("#items").append($("<option style='text-align: left;'></option>").attr( "value",-1).text("ALL"));
                    for ( var i = 0; i < len; i++) {
                           
                                if(data[i].itemId==1 && isManual==0){
                        $("#items").append(
                                $("<option style='text-align: left;' disabled></option>").attr(
                                    "value", data[i].id).text(data[i].itemName)   );
                                }
                                else
                                	{
                                	 $("#items").append(
                                             $("<option style='text-align: left;'></option>").attr(
                                                 "value", data[i].id).text(data[i].itemName)   );
                                	}
                         
                       
                    }

                    $("#items").trigger("chosen:updated");
                });
            }
 function onAllGrpSelect() { 
            	
	 var items=$('#items').val();
	 
	 if(items.includes("-1")){
		 $('#modeltable7 td').remove();
     	$("#sbtbtn4").prop("disabled", true);
            	var prodHeaderId=$('#prodHeaderId4').val();
            	var grpId=$('#grpId').val();
                $.getJSON('${findItemsByGrpId}', {
                    grpId : grpId,
                    prodHeaderId : prodHeaderId, 
                    fromDept:"PROD",
                    toDept:"BMS",
                    ajax : 'true'
                }, function(data) {
            
                    var len = data.length;

					$('#items')
				    .find('option')
				    .remove()
				    .end()
				 $("#items").append($("<option style='text-align: left;' ></option>").attr( "value",-1).text("ALL"));
                    for ( var i = 0; i < len; i++) {
                            
                                
                    	 if(data[i].itemId==1){
                             $("#items").append(
                                     $("<option style='text-align: left;' disabled></option>").attr(
                                         "value", data[i].id).text(data[i].itemName)   );
                                     }
                                     else
                                     	{
                                     	 $("#items").append(
                                                  $("<option style='text-align: left;' selected></option>").attr(
                                                      "value", data[i].id).text(data[i].itemName)   );
                                     	}
                       
                    }

                    $("#items").trigger("chosen:updated");
                });
	 }
}
 function onTypeChange(typeId) { 
	 $('#modeltable9 td').remove();
 	document.getElementById("no_of_kg").value =0;

 	var prodHeaderId=$('#prodHeaderId4').val();
     $.getJSON('${findSfByTypeId}', {
    	 typeId : typeId,
         prodHeaderId : prodHeaderId,
         ajax : 'true'
     }, function(data) {
 
    	 if(typeId==4){
    		 document.getElementById("searchItemsByType").style.display="none";
    		 document.getElementById("no_of_kg").readOnly = false;
         var len = data.length;

			$('#sfitems')
		    .find('option')
		    .remove()
		    .end()
		 $("#sfitems").append($("<option style='text-align: left;'></option>").attr( "value","").text("Select Product"));
         for ( var i = 0; i < len; i++) {
             $("#sfitems").append(
                     $("<option style='text-align: left;'></option>").attr(
                         "value", data[i].sfId).text(data[i].sfName)
                 );
         }

         $("#sfitems").trigger("chosen:updated"); 
    	 }else
    		 {
    	
    		 document.getElementById("no_of_kg").readOnly = true;
    		 document.getElementById("searchItemsByType").style.display="block";

    			$('#sfitems')
    		    .find('option')
    		    .remove()
    		    .end();
    	         $("#sfitems").trigger("chosen:updated"); 

    		 }
     });
 }
function onAllSfSelect() { 
 	
var items=$('#sfitems').val();
if(items.includes("-1")){

 	var prodHeaderId=$('#prodHeaderId4').val();
 	var typeId=$('#typeId').val();
     $.getJSON('${findSfByTypeId}', {
    	 typeId : typeId,
         prodHeaderId : prodHeaderId,
         ajax : 'true'
     }, function(data) {
 
         var len = data.length;

			$('#sfitems')
		    .find('option')
		    .remove()
		    .end()
		 $("#sfitems").append($("<option style='text-align: left;' ></option>").attr( "value","").text("Select Product"));
         for ( var i = 0; i < len; i++) {
                 
                     
             $("#sfitems").append(
                     $("<option style='text-align: left;'  selected></option>").attr(
                         "value", data[i].sfId).text(data[i].sfName)
                 );
            
         }

         $("#sfitems").trigger("chosen:updated");
     });
}
}
</script>
 <script type="text/javascript">
    function showDetailsForIssue(prodHeaderId,prodDate)
    {
    
    	$('#modeltable7 td').remove();
    	$("#sbtbtn4").prop("disabled", true);
    	var prodStatus=document.getElementById("prodStatus"+prodHeaderId).value;
    	if(prodStatus==5)
    		{
    		$('input[id=on]').attr("disabled",true);
    		$("#off").prop("checked", true);
    		}else
    			{
    			$('input[id=on]').attr("disabled",false);
    			$("#on").prop("checked", true);
    			}
    	document.getElementById("dept4").value="PROD";
    	document.getElementById("prodHeaderId4").value=prodHeaderId;
    	document.getElementById("prodIdSpan4").innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;"+prodHeaderId;
		document.getElementById("prodDateSpan4").innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;"+prodDate;
		document.getElementById("grpId").value="";   $("#grpId").trigger("chosen:updated");
    	$('#items')
	    .find('option')
	    .remove()
	    .end()
	    $("#items").trigger("chosen:updated");
    	
    }
    function showDetailsForManual(prodHeaderId,prodDate)
    {
    	document.getElementById("prodHeaderId5").value=prodHeaderId;document.getElementById("dept5").value="BMS";
    	document.getElementById("prodIdSpan5").innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;"+prodHeaderId;
		document.getElementById("prodDateSpan5").innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;"+prodDate;
		$('#modeltable9 td').remove();
		$('#sfitems')
	    .find('option')
	    .remove()
	    .end();
		$("#sfitems").trigger("chosen:updated");
		  document.getElementById("no_of_kg").value="0";
    }
    </script>
     <script type="text/javascript">
$('#searchIssueItems').click(function(){
	
    document.getElementById("searchIssueItems").value="Searching...";

 	$('#modeltable7 td').remove();
  		$("#sbtbtn4").prop("disabled", true);
  		 	$.ajax({
  		             type: "POST",
  		             url: "${pageContext.request.contextPath}/getSfDetailsForIssue",
  		             data:  $("#modalfrm4").serialize(),
  		             dataType: 'json',
  		             success: function (data) {
  		                 $("#sbtbtn4").prop("disabled", false);
  		               document.getElementById("searchIssueItems").value="Search";
  		               var len = data.length;
  		     		$('#modeltable7 td').remove();
  		     		$.each(data,function(key, data) {
  		     		   
                   
  		     		 var actQty=0;
  					 if(data.total>0)
  	    			 actQty=(data.total/1000).toFixed(3);
  							var tr = $('<tr id="modeltable7'+data.itemDetailId+'" ></tr>');
  						  	tr.append($('<td></td>').html((key+1)+"<input type=hidden name='itemDetailId'  value="+data.itemDetailId+"   id="+ data.itemDetailId+"  />   <input type=hidden name='itemId"+ data.itemDetailId+"'  value="+data.itemId+"   id="+ data.itemDetailId+"  > "));
  						  	tr.append($('<td></td>').html(data.rmName+""+"<input type=hidden value='"+data.rmName+"'  id=rmName"+data.itemDetailId+"  name=rmName"+data.itemDetailId+"  >"));
  						 
  						  	if(data.rmType==1)
  						  	tr.append($('<td></td>').html("RM"+"<input type=hidden name=rmType"+data.itemDetailId+" id=rmType"+data.itemDetailId+" value='1' />"));
  						  	else
  							  	tr.append($('<td></td>').html("SF"+"<input type=hidden name=rmType"+data.itemDetailId+" id=rmType"+data.itemDetailId+" value='2' />"));
  						  	tr.append($('<td></td>').html(actQty+""+"<input type=hidden value="+actQty+"  id=prevRmQty"+data.itemDetailId+"  name=prevRmQty"+data.itemDetailId+"  >"));
  						  	tr.append($('<td></td>').html("<input type=text onkeypress='return IsNumeric(event);' style='width:100px;border-radius:25px; font-weight:bold;text-align:center;'   ondrop='return false;' min='0'  onpaste='return false;' style='text-align: center;' class='form-control' name='rmQty"+data.itemDetailId+"'  id=rmQty"
  									+ data.itemDetailId+" value="+actQty+" readonly /> &nbsp;  <input type=hidden id=sfId"+data.itemDetailId+"  name=sfId"+data.itemDetailId+"  value="+data.rmId+" />" ));
  							tr.append($('<td></td>').html(data.uom+""+"<input type=hidden value='"+data.uom+"'  id=uom"+data.itemDetailId+"  name=uom"+data.itemDetailId+"  >"));

  							$('#modeltable7 tbody').append(tr);
                   
  	     		});
  		     	        
  		             },
  		             error: function (e) {
  		                 $("#sbtbtn4").prop("disabled", false);
   		     		    document.getElementById("searchIssueItems").value="Search";

  		                 //...
  		             }
  			});
  		

  		});

    </script>
    <script type="text/javascript">
    $('#sbtbtn4').click(function(){
		$("#overlay4").fadeIn(300);
		
		$.ajax({
			    type: "POST",
	             url: "${pageContext.request.contextPath}/postIssueData",
	             data: $("#modalfrm4").serialize(),
	             dataType: 'json',
			success: function(data){
				if(data==1)
					{
					$('#modeltable7 td').remove();
					alert("Bom Done")
					$("#overlay4").fadeOut(300);
					$("#closeHrefModel4")[0].click()
					}
			}
		}).done(function() {
			setTimeout(function(){
				$("#overlay4").fadeOut(300);
			},500);
		});
	});	
    </script>
    <script type="text/javascript">
    function showDetailsForLayerDetails(prodHeaderId,rmId,itemDetailId,prodDate,actQty,rmName)
    {
    	 var itemIds=document.getElementById("layer"+itemDetailId).value;

    	 var itemIdsArr;
    	 if(itemIds!=null && itemIds!="")
    		 itemIdsArr=itemIds.split(',');

    	$.getJSON('${showDetailItemLayering}', {
    		
    		prodHeaderId:prodHeaderId,
    		rmId:rmId,
    		toDept:'BMS',
    		ajax : 'true',
    	},  function(data) {
    		var len = data.length;
    		$('#modeltable8 td').remove();
    		document.getElementById("dept6").value="BMS";
    		document.getElementById("prodHeaderId6").value=prodHeaderId;
    		//document.getElementById("rmQty"+itemDetailId).value=0;

    		document.getElementById("prodIdSpan6").innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;"+prodHeaderId;
    		document.getElementById("prepQtySpan6").innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;"+actQty;
    		document.getElementById("itemNameSpan6").innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;"+rmName;
    		var approveStatusStyle="disabled";var styleClass="";
            $.each(data,function(key, data) {
            	if(data.doubleCut==0){
        			approveStatusStyle="";
        			styleClass="";
        		}
        		if(data.doubleCut==1){
        			styleClass="disabled";
        		}
            	
    			 var actQty=0;
				 if(data.total>0)
    			 actQty=(data.total/1000).toFixed(3);
						var tr = $('<tr id="modeltable8'+data.itemDetailId+'" ></tr>');
						if(data.doubleCut==1){
							tr.append($('<td></td>').html(""));
						}else{
						tr.append($('<td></td>').html("<input type='checkbox'  onclick='showTotalQty()'   name='chklayering'  value="+data.itemId+"   id=ld"+data.itemId+"  >  <label for='ld"+data.itemId+"' ></label>"));
						}
					  	tr.append($('<td></td>').html(key+1));
					  	tr.append($('<td></td>').html(data.rmName));
					 	tr.append($('<td></td>').html(actQty+"<input type='hidden' onkeypress='return IsNumeric(event);' style='width:100px;border-radius:25px; font-weight:bold;text-align:center;'   ondrop='return false;' min='0'  onpaste='return false;' style='text-align: center;' class='form-control' name='itemQty"+data.itemId+"'  id=itemQty"+data.itemId+" value="+actQty+" /> "));
					 	tr.append($('<td></td>').html(data.uom));
						$('#modeltable8 tbody').append(tr);
    		});
            var tr = $('<tr></tr>');
			tr.append($('<td colspan="4"></td>').html("<a href='#' disabled="+approveStatusStyle+"  style='text-decoration:underline;' onclick='checkAll()' > Select All </a> &nbsp;&nbsp;<a href='#' style='color:grey;text-decoration:underline;' onclick='uncheckAll()'> Remove </a> &nbsp;&nbsp;&nbsp;&nbsp;  <input type='text' value='0' id='layerDetailQty' name='layerDetailQty' readonly   style='  padding: 6px 10px; margin: 4px 0; width:100px;	border-radius:25px;text-align:center;  box-sizing: border-box;  border: none; font-weight:bold; background-color: #3CBC8D; color: white;'  /> "));
			tr.append($('<td ></td>').html(" <button type='button' "+approveStatusStyle+"  class='btn btn-primary' id='addLayerItem' onclick='onLayerItemAdd("+itemDetailId+")'  >Add</button>"));
			$('#modeltable8 tbody').append(tr);
    		
    	});
    	if(itemIdsArr.length>0){
    	itemIdsArr.forEach(function(v) {
    		
    		  document.getElementById("ld"+v).checked = true;
    		 

    		
    	});
    	}
    }
    </script>
    <script type="text/javascript">
    function onItemGetManualProd() 
    {
    	document.getElementById("myInput").value="";
    	var itemDetailId=document.getElementById("itemDetailId5").value;
    	var dept=document.getElementById("dept5").value;
    	var prodHeaderId=document.getElementById("prodHeaderId5").value;
    	var typeId=document.getElementById("typeId").value;
    	var typeName=$("#typeId option:selected").text();
    	$("#addManualProdItem").prop("disabled", false);
    	 $.getJSON('${getItemsByProductionIdCatId}', {
             prodHeaderId : prodHeaderId,
             ajax : 'true'
         }, function(data) {
     
             var len = data.length;
             if(len>0)
            	 {
            		$('#modeltable10 td').remove();
            		document.getElementById("dept7").value="BMS";
            		document.getElementById("prodHeaderId7").value=prodHeaderId;

            		document.getElementById("prodIdSpan7").innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;"+prodHeaderId;
            		document.getElementById("itemNameSpan7").innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;"+typeName;
            		var approveStatusStyle="";var styleClass="";
                    $.each(data,function(key, data) {
                    
        				
        						var tr = $('<tr id="modeltable10'+data.id+'" ></tr>');
        					//	if(data.doubleCut==1){
        						
        						/* }else{
        						tr.append($('<td></td>').html("<input type='checkbox'  onclick='showTotalQty()'   name='chkmanualitems'  value="+data.id+"   id=cmi"+data.id+"  >  <label for='cmi"+data.id+"' ></label>"));
        						} */
        					  	tr.append($('<td style="padding: 0.3em 0.5em;"></td>').html(key+1));
        					  	tr.append($('<td style="padding: 0.3em 0.5em;"></td>').html(data.itemName));
        					 	tr.append($('<td style="padding: 0.3em 0.5em;"></td>').html("<input type='number' onkeypress='return IsNumeric(event);' style='width:100px;border-radius:25px; font-weight:bold;text-align:center;'   ondrop='return false;' min='0'  onpaste='return false;' style='text-align: center;' class='form-control' name='manualItemQty"+data.id+"'  id=manualItemQty"+data.id+" value=0  /> "));
        					 	tr.append($('<td> style="padding: 0.3em 0.5em;"</td>').html("NOS."));
        						$('#modeltable10 tbody').append(tr);
            		});
                    var tr = $('<tr></tr>');
        			tr.append($('<td colspan="4" style="text-align:right;"></td>').html(" <button type='button' "+approveStatusStyle+"  class='btn btn-primary' id='addManualProdItem' onclick='onManualProdItemAdd("+typeId+")'  >Add</button>"));
        			$('#modeltable10 tbody').append(tr); 
            	 }else
            		 {
            		   alert("Items Not Found")
            		 }
             
         });
    	
    }
    function onManualProdItemAdd(itemDetailId)
    {
    	localStorage.clear();
    	$("#addManualProdItem").prop("disabled", true);
    	$("#overlay8").fadeIn(300);
    	var prodHeaderId=document.getElementById("prodHeaderId5").value;
    	var typeId=document.getElementById("typeId").value;

    	$.getJSON('${getItemsByProductionIdCatId}', {
            prodHeaderId : prodHeaderId,
            ajax : 'true'
        }, function(data) {
        	 var len = data.length;
             if(len>0)
            	 {
    	var sfItems=[];
    	  $.each(data,function(key, data) {
    	var manualItemQty= $("#manualItemQty"+data.id).val();
    	if(manualItemQty>0){
    	var sf = {
				  "sfId":data.id,
				  "rmQty":manualItemQty,
				  "type":typeId
				}
		sfItems.push(sf);
    	}
    	  });
    	
    	   
    	$.ajax({
	             type: "POST",
	             contentType: "application/json",
	             url: "${pageContext.request.contextPath}/getItemDetailsForManualProduction",
	             data: JSON.stringify(sfItems),
	             dataType: 'json',
	             timeout: 600000,
	             success: function (data1) {
	            	 if(data1.length>0){
	            			$('#sfitems')
            			    .find('option')
            			    .remove()
            			    .end()
            			 $("#sfitems").append($("<option style='text-align: left;'></option>").attr( "value","").text("Select Product"));
            	        
	            		 $.each(data1,function(key1, data1) {
	            			 
	            	        var total=parseFloat((data1.total/1000)).toFixed(3);
	            	         localStorage.setItem(""+data1.rmId, total);
	            	             $("#sfitems").append(
	            	                     $("<option style='text-align: left;'></option>").attr(
	            	                         "value", data1.rmId).text(data1.rmName+" - "+total)
	            	                 );
	            	        

	            		 });
            	         $("#sfitems").trigger("chosen:updated");
            	         $('#modeltable10 td').remove();
            				$("#overlay8").fadeOut(300);
            				$("#closeHrefModel7")[0].click();
	            	 }
	            	 else
	            		 {
	            		 $("#overlay8").fadeOut(300);
	            		    alert("Please Enter Valid Qty!!")
	            		 }
	             },
		             error: function (e) {
		            	 $("#overlay8").fadeOut(300);
		             }
			});
            	 }
        });
    }
    </script>
    <script type="text/javascript">
    function onLayerItemAdd(itemDetailId)
    {
    	 var arr=[]; var itemIdArr=[];
    		$('input[name="chklayering"]:checked').each(function() {
    			 arr.push(this.value);
    		});
    		var totalQty=0;
    		arr.forEach(function(v) {
    				 var itemQty=parseFloat($('#itemQty'+v).val());
    				 totalQty=totalQty+itemQty;
    				 
    				 itemIdArr.push(v);
    				
    		});
    		var itemIds=itemIdArr.join();;
    		  document.getElementById("rmQty"+itemDetailId).value=totalQty.toFixed(3);
    		  document.getElementById("layer"+itemDetailId).value=itemIds;
    		     document.getElementById('layerbombtn'+itemDetailId).disabled=false;

    		  $('#modeltable4 td').remove();
    		  $('#modeltable8 td').remove();
				$("#overlay6").fadeOut(300);
				$("#closeHrefModel6")[0].click()
    }
    </script>
       <script type="text/javascript">
 function onSfAddManualProdLayering() {
	 
	 var sfItems = [];
	 var flag=0;
		
			if($('#no_of_kg').val()<=0){
				flag=1;
			}
		var sf = {
				  "sfId": $('#sfitems').val(),
				  "rmQty": $('#no_of_kg').val()
				}
		sfItems.push(sf);
		
  			//$("#sbtbtn2").prop("disabled", true);
  			if(flag==1)
  				{
  				alert("Qty should be greator than 0")
  				}
  			else{
  		 	$.ajax({
  		             type: "POST",
  		             contentType: "application/json",
  		             url: "${pageContext.request.contextPath}/getSfDetailsForLayering",
  		             data: JSON.stringify(sfItems),
  		             dataType: 'json',
  		             timeout: 600000,
  		             success: function (data) {
  		                 $("#sbtbtn7").prop("disabled", false);
  		               
  		               var len = data.length;
  		     		$('#modeltable9 td').remove();
  		     		
  		     		$.each(data,function(key, data) {
  		     			
  	    			  var rmName=(data.rmName.split("#"))[0]; var uom=(data.rmName.split("#"))[1];
  	    			 var actQty=0;
  					 if(data.rmQty>0)
  	    			 actQty=(data.rmQty).toFixed(3);
  		 						var tr = $('<tr></tr>');
  		 						//tr.append($('<td></td>').html("<input type=checkbox name='chk'  value="+data.itemDetailId+"   id="+ data.itemDetailId+"  >  <label for="+ data.itemDetailId+" ></label>"));
  		 					  	tr.append($('<td></td>').html((key+1)+"<input type=hidden name='sfDid'  value="+data.sfDid+"   id="+ data.sfDid+"  > "));
  		 					  	tr.append($('<td></td>').html(rmName+"<input type=hidden name=rmName"+data.sfDid+" id=rmName"+data.sfDid+" value='"+rmName+"' />"));
  		 					  	if(data.rmType==1)
  		 					  	tr.append($('<td></td>').html("RM"+"<input type=hidden name=rmType"+data.sfDid+" id=rmType"+data.sfDid+" value=1 />"));
  		 					  	else
  		 						  	tr.append($('<td></td>').html("SF"+"<input type=hidden name=rmType"+data.sfDid+" id=rmType"+data.sfDid+" value=2 />"));
  		 					  	tr.append($('<td></td>').html(actQty+"<input type=hidden name=prevRmQty"+data.sfDid+" id=prevRmQty"+data.sfDid+" value="+data.rmQty+" />"));
  		 					  	tr.append($('<td></td>').html("<input type=text onkeypress='return IsNumeric(event);' style='width:100px;border-radius:25px; font-weight:bold;text-align:center;'   ondrop='return false;' min='0'  onpaste='return false;' style='text-align: center;' class='form-control'  name=rmQty2"+ data.sfDid+"  id=rmQty2"
  		 								+ data.sfDid+" value="+actQty+" readonly /> &nbsp;  <input type=hidden id=rmId"+data.sfDid+"  name=rmId"+data.sfDid+"  value="+data.rmId+" />" ));
  		 						tr.append($('<td></td>').html(uom+""+"<input type=hidden value='"+uom+"'  id=uomRm"+data.sfDid+"  name=uomRm"+data.sfDid+"  >"));
  		 						$('#modeltable9 tbody').append(tr);
  	     		});
  		     	        
  		             },
  		             error: function (e) {
  		                 $("#sbtbtn7").prop("disabled", false);
  		                 //...
  		             }
  			});
  			}//if close

  		}

    </script>
    <script type="text/javascript">
    $('#sbtbtn7').click(function(){
		$("#overlay7").fadeIn(300);
	/* 	var typeId=document.getElementById("typeId").value;
		var itemId=0;
		if(typeId!=4)
			{
			itemId=document.getElementById("sfItems").value;
			} */
		$.ajax({
			    type: "POST",
	             url: "${pageContext.request.contextPath}/postManualData",
	             data: $("#modalfrm5").serialize(),
	             dataType: 'json',
			success: function(data){
				if(data>=1)
					{
					$('#modeltable9 td').remove();
					alert("Mixing and Bom Done");
					document.getElementById("no_of_kg").value = 0;
		    		  document.getElementById("sfitems").value="";
		    		  document.getElementById("typeId").value="";
	    	        
	    	    	// window.localStorage.removeItem(''+itemId);
	    	    	 //$("#sfitems option[value="+itemId+"]").remove();
	    	    	 $("#sfitems").trigger("chosen:updated"); 
					$("#overlay7").fadeOut(300);
					//$("#closeHrefModel5")[0].click()
					}
			}
		}).done(function() {
			setTimeout(function(){
				$("#overlay7").fadeOut(300);
			},500);
		});
	});	
    </script>
    <script type="text/javascript">
    function showTotalQty()
    {
    	 var arr=[]; 
 		$('input[name="chklayering"]:checked').each(function() {
 			 arr.push(this.value);
 		});
 		var totalQty=0;
 		arr.forEach(function(v) {
 				 var itemQty=parseFloat($('#itemQty'+v).val());
 				 totalQty=totalQty+itemQty; 				 			
 		});
 		 document.getElementById("layerDetailQty").value=totalQty.toFixed(3);
    }  
    function onEditCp()
    {
    	$('#modeltable2 td').remove();
    }
    </script>
    <script>
    function onProductChange(id)
    {
    	document.getElementById("no_of_kg").value =  localStorage.getItem(""+id);

    }
    </script>
    <script>
	localStorage.clear();

function myFunction() {
  var input, filter, table, tr, td,td1, i;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("modeltable10");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1];
    if (td) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }//end of for
  
 
  
}
</script>
	<!--basic scripts-->
 <script
		src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script> 
	<!-- <script>
		window.jQuery
				|| document
						.write('<script src="${pageContext.request.contextPath}/resources/assets/jquery/jquery-2.0.3.min.js"><\/script>')
	</script>
 -->	<script
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
</body>
</html>