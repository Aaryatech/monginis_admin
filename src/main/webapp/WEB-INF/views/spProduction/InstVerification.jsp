<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	 
 	
<body onload="hideStationAndShift(${isEdit})">
 
<jsp:include page="/WEB-INF/views/include/logout.jsp"></jsp:include>
<c:url var="getInstrumentStatus" value="/getInstrumentStatus"/>

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
						<i class="fa fa-file-o"></i>Instrument Verification
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
								<i class="fa fa-bars"></i>Instrument Verification
							</h3>
							<div class="box-tool">
								<a href="${pageContext.request.contextPath}/"></a> <a data-action="collapse" href="#"><i
									class="fa fa-chevron-up"></i></a>
							</div>
							
						</div>




						<div class="box-content">
							<form action="${pageContext.request.contextPath}/addInstVerification" class="form-horizontal"
								method="post" id="validation-form">
         
         
          <div class="col2">
									<label class="col-sm-3 col-lg-2 control-label">Station</label>
									<div class="col-sm-1 col-lg-3 controls">
									<select name="st_id" id="st_id" class="form-control" placeholder="Select Station" data-rule-required="true">
											<option value="-1">Select Station</option>
											<c:forEach items="${spStationList}" var="spStationList">
										    	<c:choose>
													<c:when test="${spStationList.stId==instVerificationHeader.stationId}">
												          <option value="${spStationList.stId}" selected><c:out value="${spStationList.stName}"></c:out></option>
													</c:when>
													<c:otherwise>
										            	  <option value="${spStationList.stId}"><c:out value="${spStationList.stName}"></c:out></option>
													</c:otherwise>
												</c:choose>
											</c:forEach> 
								</select>	
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-lg-2 control-label">Shift</label>
									<div class="col-sm-2 col-lg-3 controls">
									<select name="shift_id" id="shift_id" class="form-control" placeholder="Select Shift" data-rule-required="true">
											<option value="-1">Select Shift</option>
										  	<c:forEach items="${shiftList}" var="shiftList">
										    	<c:choose>
													<c:when test="${shiftList.shiftId==instVerificationHeader.shiftId}">
												          <option value="${shiftList.shiftId}" selected><c:out value="${shiftList.shiftName}"></c:out></option>
													</c:when>
													<c:otherwise>
										            	  <option value="${shiftList.shiftId}"><c:out value="${shiftList.shiftName}"></c:out></option>
													</c:otherwise>
												</c:choose>
											</c:forEach> 
								</select>	
									</div>
								</div>
                             <div class="form-group">
                             <label class="col-sm-2 col-lg-4 control-label"id="status"></label>
									<div class="col-sm-2 col-sm-offset-4 col-lg3 col-lg-offset-4">
									<%-- <c:choose>
													<c:when test="${instVerificationHeader.stStatus==1}"> --%>
													
													<input type="button" class="btn btn-primary" id="start" value="Start" onclick="return updateCheckBoxBefore()">
									             	&nbsp;
													<%-- </c:when>
													<c:otherwise> --%>
							                    	<input type="button" class="btn btn-primary" id="close" value="Close" onclick="return updateCheckBoxAfter()">
													<%-- </c:otherwise>
									</c:choose> --%>
						           </div>
							</div> 
							
								<input type="hidden" name="isStationAvail" id="isStationAvail" value="0"/>
         
							<br>
								<div class="box">
									<div class="box-title">
										<h3>
											<i class="fa fa-table"></i>Instruments Status
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
											<table width="100%" class="table table-advance" id="table1">
												<thead>
													<tr>
														<th width="15" style="width: 18px">Sr.No.</th>
														<th width="150" align="left">Instruments</th>
														<th width="80" align="left">Before</th>
												    	<th width="80" align="left">after</th>
													
														<!-- <th width="81" align="left">Action</th> -->
													</tr>
												</thead>
												<tbody>
													   <c:forEach items="${instrumentsList}" var="instrumentsList" varStatus="count">
														<tr>
														
															<td><c:out value="${count.index+1}"/></td>
															<td align="left"><c:out value="${instrumentsList.instrumentName}"></c:out></td>
													       <td align="left"><input type="checkbox" name="before" id="beforeSelection${instrumentsList.instrumentId}" value="${instrumentsList.instrumentId}"disabled="disabled"> <%-- <label for="beforeSelection${count.index+1}">${instrumentsList.instrumentName}</label> --%><BR></td>		
														
															<td align="left"><input type="checkbox" name="after" id="afterSelection${instrumentsList.instrumentId}" value="${instrumentsList.instrumentId}" disabled="disabled"><%-- <label for="afterSelection${count.index+1}">${instrumentsList.instrumentName}</label> --%><BR></td>
													
													
														<%-- b	<td align="left"><c:out
																	value="${stationList.empHelperName}"></c:out></td>
																			
															<td align="left"><a href="${pageContext.request.contextPath}/updateStationAllocation/${stationList.allocationId}"><span
														class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;
                                                        
                                                        <a href="${pageContext.request.contextPath}/deleteStationAllocation/${stationList.}"
													    onClick="return confirm('Are you sure want to delete this record');"><span
														class="glyphicon glyphicon-remove"></span></a></td>	 --%>
														 
														</tr>
													  <c:if test="${count.last}"><input type="hidden" name="cnt" id="cnt" value="${count.index}"/></c:if>

													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
										<input type="submit" class="btn btn-primary" value="Submit" onclick="return validation()">
<!-- 										<button type="button" class="btn">Cancel</button>
 -->									</div>
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


</body>
<script type="text/javascript">
$(document).ready(function() { 
	$('#st_id').change(
			function() {


	$.getJSON('${getInstrumentStatus}', {
					stationId : $(this).val(),
					ajax : 'true',
				},  function(data) {
					
					if(data.error==false)
					{
						//alert("shift"+data.shiftId)
						
					//	document.getElementById('start').style.visibility = 'hidden';
						//getElementById('close').style.visibility = 'show';
						 document.getElementById("shift_id").options.selectedIndex =data.shiftId;
						 document.getElementById("isStationAvail").value =0;
 
						  document.getElementById('status').innerHTML = 'Station is Running';
						  document.getElementById('status').style.color = "Red";
						  
						 var chks = document.getElementsByName("before");
					       // document.getElementById('start').style.visibility = 'hidden';
					            for (var i = 0; i <= chks.length - 1; i++) {
					                chks[i].disabled = false;
					            }
					            $.each(data.instVerificationDetailList,function(key, detail) {
					            
					            	document.getElementById('start').style.visibility = 'hidden';
					            	if(detail.bef==1)
					            		{
					       
					            	 document.getElementById("beforeSelection"+detail.instId).checked =true;
					            	 document.getElementById("beforeSelection"+detail.instId).disabled=true;
						            //	document.getElementById("beforeSelection"+detail.instId).style.visibility = 'hidden';

					            		}
					            })
					            
				    }
					else
						{
						 document.getElementById("isStationAvail").value =1;
						  document.getElementById('status').innerHTML = 'Station is Available';
						  document.getElementById('status').style.color = "green";
						//document.getElementById('close').style.visibility = 'hidden';
						//document.getElementById('start').style.visibility = 'show';
					//document.getElementById("beforeSelection"+detail.instId).disabled=false;

						 // document.getElementById("before").checked =false;
						    var chks = document.getElementsByName("before");

            // document.getElementById('start').style.visibility = 'hidden';
            for (var i = 0; i <= chks.length - 1; i++) {
                chks[i].checked = false;
            }
       
			            	 document.getElementById("beforeSelection"+detail.instId).disabled=true;
						}
					});
			});
			
});

</script>
<script type="text/javascript">
function validation() {
	
	var stId = $("#st_id").val();
	var shiftId = $("#shift_id").val();
	var mId = $("#m_id").val();
	var hId = $("#h_id").val();
	
	var isValid = true;
	if (stId==-1) { 
		isValid = false;
		alert("Please Select Station");
	} else if (shiftId ==-1) {
		isValid = false;
		alert("Please Select Shift");
	}else if (mId ==-1) {
		isValid = false;
		alert("Please Select Mistry");
	}else if (hId==-1) {
		isValid = false;
		alert("Please Select Helper");
	}
	return isValid;
}

</script>
<script>
    function updateCheckBoxBefore() {
    	
        var chks = document.getElementsByName("before");

       // document.getElementById('start').style.visibility = 'hidden';
            for (var i = 0; i <= chks.length - 1; i++) {
                chks[i].disabled = false;
            }
       
        }
  
</script>
<script>
    function updateCheckBoxAfter() {
        var chks = document.getElementsByName("after");

      //  document.getElementById('close').style.visibility = 'hidden';

            for (var i = 0; i <= chks.length - 1; i++) {
                chks[i].disabled = false;
            }
       
        }
  
</script>
</html>
