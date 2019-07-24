<html style="overflow-x: hidden;">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script type="text/javascript" src="https://www.google.com/jsapi"></script>

<c:url var="addDutyTaskDetail" value="/addDutyTaskDetail" />
<c:url var="getTaskForEdit" value="/getTaskForEdit" />



<!---------------Script For Translate Special Instructions------->
<script type="text/javascript">
	// Load the Google Transliterate API
	google.load("elements", "1", {
		packages : "transliteration"
	});

	function onLoad() {
		var options = {
			sourceLanguage : google.elements.transliteration.LanguageCode.ENGLISH,
			destinationLanguage : [ google.elements.transliteration.LanguageCode.MARATHI ],
			shortcutKey : 'ctrl+g',
			transliterationEnabled : true
		};

		// Create an instance on TransliterationControl with the required
		// options.
		var control = new google.elements.transliteration.TransliterationControl(
				options);

		// Enable transliteration in the textbox with id
		// 'transliterateTextarea'.
		control.makeTransliteratable([ 'transliterateTextarea1' ]);
		control.makeTransliteratable([ 'transliterateTextarea2' ]);
		control.makeTransliteratable([ 'transliterateTextarea3' ]);
		control.makeTransliteratable([ 'transliterateTextarea4' ]);
	}
	google.setOnLoadCallback(onLoad);
</script>
<!--------------------------------END------------------------------------>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

<body>
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

			<div class="page-title">
				<div>
					<h1>
						<i class="fa fa-file-o"></i>Add Duty
					</h1>

				</div>
			</div>

			<!-- BEGIN Main Content -->
			<div class="row">

				<div class="col-md-12">
					<div class="box">

						<%-- <div class="box-title">
							<table width="100%">
								<tr>
									<td width="50%">
										<h4>
											<i class="fa fa-bars"></i> Add Duty
										</h4>
									</td>
									<td width="50%" align="right"><a
										href="${pageContext.request.contextPath}/showAddDutyShift"></a>
										<a href="#"><h4>
												<font color="black">Duty List</font>
											</h4></a></td>
								</tr>
							</table>


						</div> --%>

						<%-- <div class="box-title">
							<div class="col-md-4">
								<h3>
									<strong>Add Duty</strong>
								</h3>
							</div>
							<div class="col-md-4"></div>
							<div class="col-md-4" align="left">
								<a href="${pageContext.request.contextPath}/showAllDutyHeader"
									style="color: black"><strong>Duty List</strong></a>
							</div>

						</div> --%>

						<div class="box-content">
							<form
								action="${pageContext.request.contextPath}/insertDutyAndTask"
								class="form-horizontal" method="post" id="validation-form">

								<div class="form-group">
									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Duty
											Code</label>
										<div class="col-sm-3 col-lg-2 controls">
											<input type="text" name="dutyCode" id="dutyCode"
												placeholder="Duty Code" class="form-control"
												data-rule-required="true" />
										</div>
									</div>

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Duty
											Name</label>
										<div class="col-sm-3 col-lg-6 controls">
											<input type="text" name="dutyName" id="dutyName"
												placeholder="Duty Name" class="form-control"
												data-rule-required="true" />
										</div>
									</div>

								</div>

								<div class="form-group">

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Department</label>
										<div class="col-sm-3 col-lg-2 controls">
											<select name="deptId" id="deptId" class="form-control"
												data-rule-required="true">
												<option value="">Select</option>
												<c:forEach items="${deptList}" var="deptList">
													<option value="${deptList.empDeptId}">${deptList.empDeptName}</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Designation</label>
										<div class="col-sm-3 col-lg-2 controls">
											<select name="desgId" id="desgId" class="form-control"
												data-rule-required="true">
												<option value="">Select</option>
												<c:forEach items="${desgList}" var="desgList">
													<option value="${desgList.empCatId}">${desgList.empCatName}</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Shift</label>
										<div class="col-sm-3 col-lg-2 controls">
											<select name="shiftId" id="shiftId" class="form-control"
												data-rule-required="true">
												<option value="">Select</option>
												<c:forEach items="${shiftList}" var="shiftList">
													<option value="${shiftList.shiftId}">${shiftList.shiftName}</option>
												</c:forEach>
											</select>
										</div>
									</div>

								</div>

								<div class="form-group">
									<div class="col2">
										<label class="col-sm-3 col-lg-2 control-label">Type</label>
										<div class="col-sm-3 col-lg-2 controls">
											<select name="typeId" id="typeId" class="form-control"
												data-rule-required="true" onchange="typeValidation()">
												<option value="1">Daily Basis</option>
												<option value="2">Day Basis</option>
												<option value="3">Date Basis</option>
											</select>
										</div>
									</div>

									<div id="typeDayDisp" style="display: none">

										<div class="col2">
											<label class="col-sm-3 col-lg-2 control-label">Select
												Day </label>
											<div class="col-sm-3 col-lg-6 controls">
												<select data-placeholder="Choose Day"
													class="form-control chosen" multiple="multiple"
													tabindex="6" id="selectDay" name="selectDay">
													<option value="1">Sunday</option>
													<option value="2">Monday</option>
													<option value="3">Tuesday</option>
													<option value="4">Wednesday</option>
													<option value="5">Thursday</option>
													<option value="6">Friday</option>
													<option value="7">Saturday</option>
												</select>
											</div>
										</div>

									</div>

									<div id="typeDateDisp" style="display: none">

										<div class="col2">
											<label class="col-sm-3 col-lg-2 control-label">Select
												Date </label>
											<div class="col-sm-3 col-lg-6 controls">
												<select data-placeholder="Choose Date"
													class="form-control chosen" multiple="multiple"
													tabindex="6" id="selectDate" name="selectDate">
													<c:forEach items="${dateList}" var="dateList">
														<option value="${dateList}">${dateList}</option>
													</c:forEach>
												</select>
											</div>
										</div>

									</div>

								</div>
								<hr>

								<div class="row">

									<div class="form-group">
										<label class="col-sm-3 col-lg-2 control-label">Task
											Name </label>
										<div class="col-sm-3 col-lg-3 controls">
											<textarea name="taskNameEng" id="taskNameEng"
												placeholder="Task Name in English" class="form-control"></textarea>
										</div>

										<div class="col-sm-3 col-lg-3 controls">
											<textarea name="taskNameMar"
												placeholder="Task Name in Marathi" class="form-control"
												id="transliterateTextarea1"></textarea>
										</div>

										<div class="col-sm-3 col-lg-3 controls">
											<textarea name="taskNameHin" placeholder="Task Name in Hindi"
												class="form-control" id="transliterateTextarea2"></textarea>
										</div>

									</div>

									<div class="form-group">
										<label class="col-sm-3 col-lg-2 control-label">Task
											Description </label>
										<div class="col-sm-3 col-lg-3 controls">
											<textarea name="taskDescEng" id="taskDescEng"
												placeholder="Task Description in English"
												class="form-control"></textarea>
										</div>

										<div class="col-sm-3 col-lg-3 controls">
											<textarea name="taskDescMar" id="transliterateTextarea3"
												placeholder="Task Description in Marathi"
												class="form-control"></textarea>
										</div>

										<div class="col-sm-3 col-lg-3 controls">
											<textarea name="taskDescHin" id="transliterateTextarea4"
												placeholder="Task Description in Hindi" class="form-control"></textarea>
										</div>

									</div>

									<div class="form-group">
										<div class="col1">
											<label class="col-sm-2 col-lg-2 control-label">Photo
												Required </label>
											<div class="col-sm-1 col-lg-1 controls">
												<label class="radio-inline"> <input type="radio"
													name="photo" id="photo1" value="1"> YES
												</label> <label class="radio-inline"> <input type="radio"
													name="photo" id="photo2" value="0" checked> NO
												</label>
											</div>
										</div>

										<div class="col1">
											<label class="col-sm-2 col-lg-2 control-label">Remark
												Required </label>
											<div class="col-sm-1 col-lg-1 controls">
												<label class="radio-inline"> <input type="radio"
													name="remark" id="remark1" value="1"> YES
												</label> <label class="radio-inline"> <input type="radio"
													name="remark" id="remark2" value="0" checked> NO
												</label>
											</div>
										</div>

										<div class="col2">
											<label class="col-sm-2 col-lg-2 control-label">Time
												Required </label>
											<div class="col-sm-1 col-lg-1 controls">
												<label class="radio-inline"> <input type="radio"
													name="timeReq" id="timeReq" value="1"
													onchange="timeRequredChange(this.value)"> YES
												</label> <label class="radio-inline"> <input type="radio"
													name="timeReq" id="timeReq1" value="0"
													onchange="timeRequredChange(this.value)" checked>
													NO
												</label>
											</div>
										</div>

										<div style="display: none" id="timeReqVarId">

											<div class="col1">

												<div class="col-sm-1 controls">
													<input type="time" name="timeReqVar" id="timeReqVar"
														placeholder="timeReqVar" class="form-control" />
												</div>
											</div>
										</div>


										<div class="col1">
											<label class="col-sm-1 col-lg-1 control-label">Task
												Weightage </label>
											<div class="col-sm-1 controls">
												<input type="number" name="weight" id="weight"
													placeholder="weight" min="1" max="100" class="form-control"
													value="1" />
											</div>
										</div>

									</div>

									<div class="form-group">
										<div
											class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-5">
											<input type="button" value="Add" class="btn btn-primary"
												style="align-content: center; width: 113px;" onclick="add()" />
										</div>
									</div>

									<input type="hidden" id="isDelete" name="isDelete" value="0">
									<input type="hidden" name="isEdit" id="isEdit" value="0">
									<input type="hidden" name="index" id="index" value="0">

								</div>

								<div class="table-responsive" style="border: 0">

									<table class="table table-advance" id="table_grid">
										<thead>
											<tr>

												<th width="15">Sr.No.</th>
												<th width="200" align="left">Task Name</th>
												<th width="200" align="left">Description</th>
												<th width="50" align="left">Photo</th>
												<th width="50" align="left">Remark</th>
												<th width="50" align="left">Weightage</th>
												<th width="50" align="left">Time Required</th>

												<th width="20px" align="left">Action</th>
											</tr>
										</thead>
										<tbody>

										</tbody>
									</table>
								</div>

								<div class="form-group">

									<div class="col-lg-4"></div>

									<div class="col-lg-2">

										<input type="submit" class="btn btn-primary" value="Submit"
											id="submitButton" disabled
											style="align-content: center; width: 113px; margin-left: 20px;">

									</div>
									<div class="col-lg-2">

										<input type="reset" class="btn btn-primary" value="Clear"
											style="align-content: center; width: 113px; margin-left: 20px;">

									</div>
									<div class="col-lg-4"></div>

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




	<script type="text/javascript">
		function add() {

			document.getElementById("table_grid").style = "block";

			var taskNameEng = $("#taskNameEng").val();
			var taskNameMar = document.getElementById("transliterateTextarea1").value;
			var taskNameHin = document.getElementById("transliterateTextarea2").value;
			var taskDescEng = $("#taskDescEng").val();
			var taskDescMar = document.getElementById("transliterateTextarea3").value;
			var taskDescHin = document.getElementById("transliterateTextarea4").value;
			var timeReqVar = document.getElementById("timeReqVar").value;

			//

			var photoReq = $("#photo").val();
			var remarkReq = $("#remark").val();
			var weight = $("#weight").val();
			var timeReq = $("#timeReq").val();

			if (document.getElementById("photo1").checked == true) {
				photoReq = 1;
			} else {
				photoReq = 0;
			}

			if (document.getElementById("remark1").checked == true) {
				remarkReq = 1;
			} else {
				remarkReq = 0;
			}

			if (document.getElementById("timeReq").checked == true) {
				timeReq = 1;
			} else {
				timeReq = 0;
			}

			var isEdit = document.getElementById("isEdit").value;
			var isDelete = document.getElementById("isDelete").value;
			var index = document.getElementById("index").value;

			if (taskNameEng == "") {

				alert("Please Insert Task Name");

			} else {

				//alert("Inside add ajax");
				$
						.getJSON(
								'${addDutyTaskDetail}',
								{

									isDelete : isDelete,
									isEdit : isEdit,
									index : index,
									taskNameEng : taskNameEng,
									taskNameMar : taskNameMar,
									taskNameHin : taskNameHin,
									taskDescEng : taskDescEng,
									taskDescMar : taskDescMar,
									taskDescHin : taskDescHin,
									photoReq : photoReq,
									remarkReq : remarkReq,
									timeReq : timeReq,
									weight : weight,
									timeReqVar : timeReqVar,
									ajax : 'true',

								},

								function(data) {

									//alert("Data " + JSON.stringify(data));

									$("#taskNameEng").val("");
									$("#taskDescEng").val("");
									//$("#photo").val("0");
									//$("#remark").val("0");
									$("#weight").val("1");

									document.getElementById("photo2").checked = true;
									document.getElementById("remark2").checked = true;

									document
											.getElementById("transliterateTextarea1").value = "";
									document
											.getElementById("transliterateTextarea2").value = "";
									document
											.getElementById("transliterateTextarea3").value = "";
									document
											.getElementById("transliterateTextarea4").value = "";

									document.getElementById("timeReqVar").value = "";
									document.getElementById("index").value = 0;

									$('#table_grid td').remove();

									//var dataTable = $('#table1').DataTable();
									//dataTable.clear().draw();

									$
											.each(
													data,
													function(i, v) {

														var str = '<a href="#" class="action_btn" onclick="callEdit('
																+ v.taskId
																+ ','
																+ i
																+ ')"><i class="glyphicon glyphicon-edit"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="action_btn" onclick="callDelete('
																+ v.taskId
																+ ','
																+ i
																+ ')" ><i class="glyphicon glyphicon-remove"></i></a>'

														/* dataTable.row
																.add(
																		[
																				i + 1,
																				v.taskNameEng,
																				v.taskDescEng,
																				v.photoReq,
																				v.remarkReq,
																				v.taskWeight,
																				str ])
																.draw(); */

														var index = i + 1;
														//var tr = "<tr>";
														var tr = $('<tr></tr>');
														tr
																.append($(
																		'<td></td>')
																		.html(
																				i + 1));
														tr
																.append($(
																		'<td></td>')
																		.html(
																				v.taskNameEng));
														tr
																.append($(
																		'<td></td>')
																		.html(
																				v.taskDescEng));
														tr
																.append($(
																		'<td></td>')
																		.html(
																				v.photoReq));
														tr
																.append($(
																		'<td></td>')
																		.html(
																				v.remarkReq));
														tr
																.append($(
																		'<td></td>')
																		.html(
																				v.taskWeight));

														tr
																.append($(
																		'<td></td>')
																		.html(
																				v.exInt1));
														tr
																.append($(
																		'<td></td>')
																		.html(
																				str));

														$('#table_grid tbody')
																.append(tr);

													});

								});

			}//else

			//document.getElementById("itemName").options.selectedIndex=0;
			document.getElementById("isDelete").value = 0;
			document.getElementById("isEdit").value = 0;
			document.getElementById("index").value = 0;

			document.getElementById("submitButton").disabled = false;

		}

		function callEdit(taskId, index) {

			document.getElementById("isEdit").value = "1";
			$
					.getJSON(
							'${getTaskForEdit}',
							{
								taskId : taskId,
								index : index,
								ajax : 'true',

							},
							function(data) {
								//alert("data" + data);
								//alert(data.exInt1);
								//alert("isDuplicate" + data.isDuplicate);

								$("#taskNameEng").val(data.taskNameEng);
								$("#taskDescEng").val(data.taskDescEng);
								//$("#photo").val(data.photoReq);
								//$("#remark").val(data.remarkReq);
								$("#weight").val(data.taskWeight);

								if (data.photoReq == 1) {
									document.getElementById("photo1").checked = true;
								} else {
									document.getElementById("photo2").checked = true;
								}

								if (data.remarkReq == 1) {
									document.getElementById("remark1").checked = true;
								} else {
									document.getElementById("remark2").checked = true;
								}

								if (data.exInt1 == 1) {
									document.getElementById("timeReq1").checked = true;
								} else {
									document.getElementById("timeReq").checked = true;
								}

								document
										.getElementById("transliterateTextarea1").value = data.taskNameMar;
								document
										.getElementById("transliterateTextarea2").value = data.taskNameHin;
								document
										.getElementById("transliterateTextarea3").value = data.taskDescMar;
								document
										.getElementById("transliterateTextarea4").value = data.taskDescHin;

								document.getElementById("timeReqVar").value = data.exVar1;

								//timeReqVar
								document.getElementById("index").value = index;

							});

		}

		function callDelete(taskId, index) {

			//alert("hii");
			document.getElementById("isEdit").value = 0;
			//alert("index" + index);
			$
					.getJSON(
							'${addDutyTaskDetail}',
							{
								isDelete : 1,
								isEdit : 0,
								index : index,
								ajax : 'true',

							},

							function(data) {

								if (data == null || data == "") {
									document.getElementById("submitButton").disabled = true;
								}

								$("#taskNameEng").val("");
								$("#taskDescEng").val("");
								//$("#photo").val("0");
								//$("#remark").val("0");
								$("#weight").val("1");

								document.getElementById("photo2").checked = true;
								document.getElementById("remark2").checked = true;
								document.getElementById("timeReq").checked = true;

								document
										.getElementById("transliterateTextarea1").value = "";
								document
										.getElementById("transliterateTextarea2").value = "";
								document
										.getElementById("transliterateTextarea3").value = "";
								document
										.getElementById("transliterateTextarea4").value = "";
								document.getElementById("index").value = 0;

								$('#table_grid td').remove();

								$
										.each(
												data,
												function(i, v) {

													var str = '<a href="#" class="action_btn" onclick="callEdit('
															+ v.taskId
															+ ','
															+ i
															+ ')"><i class="glyphicon glyphicon-edit"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="action_btn" onclick="callDelete('
															+ v.taskId
															+ ','
															+ i
															+ ')" ><i class="glyphicon glyphicon-remove"></i></a>'

													/* dataTable.row
															.add(
																	[
																			i + 1,
																			v.taskNameEng,
																			v.taskDescEng,
																			v.photoReq,
																			v.remarkReq,
																			v.taskWeight,
																			str ])
															.draw(); */

													var index = i + 1;
													//var tr = "<tr>";
													var tr = $('<tr></tr>');
													tr.append($('<td></td>')
															.html(i + 1));
													tr
															.append($(
																	'<td></td>')
																	.html(
																			v.taskNameEng));
													tr
															.append($(
																	'<td></td>')
																	.html(
																			v.taskDescEng));
													tr.append($('<td></td>')
															.html(v.photoReq));
													tr.append($('<td></td>')
															.html(v.remarkReq));
													tr
															.append($(
																	'<td></td>')
																	.html(
																			v.taskWeight));
													tr.append($('<td></td>')
															.html(str));

													$('#table_grid tbody')
															.append(tr);

												});
							});

		}

		function validate(s) {
			var rgx = /^[0-9]*\.?[0-9]*$/;
			return s.match(rgx);
		}
		function callAlert(msg) {
			alert(msg);
		}

		function timeRequredChange(timeReq) {
			//alert("hii");
			//	alert(timeReq);

			if (timeReq == 1) {

				document.getElementById("timeReqVarId").style.display = "block";

			} else {

				document.getElementById("timeReqVarId").style.display = "none";

			}

		}
	</script>





	<script>
		function typeValidation() {
			var type;
			type = $("#typeId").val();

			//alert("Type - " + type);

			if (type == 1) {
				$('#typeDayDisp').hide();
				$('#typeDateDisp').hide();
			} else if (type == 2) {
				$('#typeDayDisp').show();
				$('#typeDateDisp').hide();
			} else if (type == 3) {
				$('#typeDayDisp').hide();
				$('#typeDateDisp').show();
			}

			//document.getElementById("rate").value = (sal / nHrs);
		}
	</script>


	<!-- Search -->
	<script>
		function myFunction() {
			var input, filter, table, tr, td, i;
			input = document.getElementById("myInput");
			filter = input.value.toUpperCase();

			if (!document.getElementById('table1')) {

				table = document.getElementById("table_grid");
			} else {
				table = document.getElementById("table1");
			}
			tr = table.getElementsByTagName("tr");
			//td = table.getElementsByTagName("td");
			//alert("td value = "+td.length);
			for (i = 0; i < tr.length; i++) {
				td = tr[i].getElementsByTagName("td")[1];
				if (td) {
					if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
						tr[i].style.display = "";
					} else {
						tr[i].style.display = "none";
					}
				}
			}
		}
	</script>




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
</html>
