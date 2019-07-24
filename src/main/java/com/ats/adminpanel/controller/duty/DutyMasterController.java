package com.ats.adminpanel.controller.duty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.duty.DutyHeader;
import com.ats.adminpanel.model.duty.DutyHeaderDisplay;
import com.ats.adminpanel.model.duty.DutyShift;
import com.ats.adminpanel.model.duty.SaveDutyAndTask;
import com.ats.adminpanel.model.duty.TaskDetail;
import com.ats.adminpanel.model.hr.Company;
import com.ats.adminpanel.model.hr.EmpDeptDisplay;
import com.ats.adminpanel.model.hr.EmployeeCategoryDisplay;
import com.ats.adminpanel.model.hr.Info;
import com.ats.adminpanel.model.login.UserResponse;

@Controller
public class DutyMasterController {

	List<TaskDetail> tempList = new ArrayList<TaskDetail>();
	int isError = 0;

	// ---------------------------------SHIFT----------------------------------------

	@RequestMapping(value = "/showAddDutyShift", method = RequestMethod.GET)
	public ModelAndView showAllShift(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("duty/addShift");
		try {
			RestTemplate restTemplate = new RestTemplate();

			DutyShift[] shiftArray = restTemplate.getForObject(Constants.security_app_url + "duty/master/allShift",
					DutyShift[].class);
			List<DutyShift> shiftList = new ArrayList<>(Arrays.asList(shiftArray));

			model.addObject("shiftList", shiftList);

		} catch (Exception e) {
			System.out.println("Exc In showAllShift:" + e.getMessage());
		}
		return model;
	}

	// ------------ADD SHIFT------------------------------------

	@RequestMapping(value = "/addDutyShift", method = RequestMethod.POST)
	public String addCompany(HttpServletRequest request, HttpServletResponse response) {

		try {
			int shiftId = 0;

			try {
				shiftId = Integer.parseInt(request.getParameter("shiftId"));

			} catch (Exception e) {
				shiftId = 0;

				System.out.println("In Catch of Add shift Exc:" + e.getMessage());

			}

			String shiftName = request.getParameter("shiftName");
			String fromTime = request.getParameter("fromTime");
			String toTime = request.getParameter("toTime");

			SimpleDateFormat dtFormat = new SimpleDateFormat("HH:mm");
			Calendar cal = Calendar.getInstance();

			Date from = dtFormat.parse(fromTime);
			Date to = dtFormat.parse(toTime);

			long diff = to.getTime() - from.getTime();
			long diffHours = diff / (60 * 60 * 1000) % 24;

			DutyShift shift = new DutyShift(shiftId, shiftName, fromTime, toTime, String.valueOf(diffHours), 1, 1, 0, 0,
					0);

			RestTemplate restTemplate = new RestTemplate();

			DutyShift saveShift = restTemplate.postForObject(Constants.security_app_url + "duty/master/saveShift",
					shift, DutyShift.class);
			System.out.println("Response: " + saveShift.toString());

			return "redirect:/showAddDutyShift";

		} catch (Exception e) {
			System.out.println("Exception In Add Shift Process:" + e.getMessage());
			e.printStackTrace();
		}

		return "redirect:/showAddDutyShift";
	}

	// ---------------UPDATE SHIFT------------------------
	@RequestMapping(value = "/updateDutyShift/{shiftId}", method = RequestMethod.GET)
	public ModelAndView updateCompany(@PathVariable int shiftId) {

		ModelAndView mav = new ModelAndView("duty/addShift");
		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("shiftId", shiftId);

			DutyShift shift = rest.postForObject(Constants.security_app_url + "duty/master/getShiftById", map,
					DutyShift.class);
			System.out.println(shift.toString());

			DutyShift[] shiftArray = rest.getForObject(Constants.security_app_url + "duty/master/allShift",
					DutyShift[].class);
			List<DutyShift> shiftList = new ArrayList<>(Arrays.asList(shiftArray));

			mav.addObject("shiftList", shiftList);
			mav.addObject("shift", shift);

		} catch (Exception e) {
			System.out.println("Exception In Edit Shift:" + e.getMessage());

			return mav;

		}
		return mav;
	}

	// ---------------DELETE COMPANY--------------------------
	@RequestMapping(value = "/deleteDutyShift/{shiftId}", method = RequestMethod.GET)
	public String deleteCompany(@PathVariable int shiftId) {

		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("shiftId", shiftId);

			Info errorResponse = rest.postForObject(Constants.security_app_url + "duty/master/deleteShift", map,
					Info.class);
			System.out.println(errorResponse.toString());

			if (errorResponse.isError()) {

				return "redirect:/showAddDutyShift";

			} else {
				return "redirect:/showAddDutyShift";

			}
		} catch (Exception e) {
			System.out.println("Exception In delete Shift:" + e.getMessage());

			return "redirect:/showAddDutyShift";

		}

	}

	// ---------------------------------DUTY
	// HEADER----------------------------------------

	@RequestMapping(value = "/showAllDutyHeader", method = RequestMethod.GET)
	public ModelAndView showAllDutyHeader(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("duty/allDutyList");
		try {
			RestTemplate restTemplate = new RestTemplate();

			DutyHeaderDisplay[] headerArray = restTemplate.getForObject(
					Constants.security_app_url + "duty/master/allDutyHeaderDetail", DutyHeaderDisplay[].class);
			List<DutyHeaderDisplay> headerList = new ArrayList<>(Arrays.asList(headerArray));

			model.addObject("dutyList", headerList);

		} catch (Exception e) {
			System.out.println("Exc In showAllDutyHeader:" + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/showAddDuty", method = RequestMethod.GET)
	public ModelAndView showAddDuty(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("duty/addDuty");
		tempList = new ArrayList<TaskDetail>();
		try {
			RestTemplate restTemplate = new RestTemplate();

			EmpDeptDisplay[] deptArray = restTemplate.getForObject(
					Constants.security_app_url + "master/allEmployeeDepartmentList", EmpDeptDisplay[].class);
			List<EmpDeptDisplay> deptList = new ArrayList<>(Arrays.asList(deptArray));

			EmployeeCategoryDisplay[] catArray = restTemplate.getForObject(
					Constants.security_app_url + "master/allEmployeeCategoryList", EmployeeCategoryDisplay[].class);
			List<EmployeeCategoryDisplay> catList = new ArrayList<>(Arrays.asList(catArray));

			List<Integer> dateList = new ArrayList<>();
			for (int i = 1; i <= 31; i++) {
				dateList.add(i);
			}

			DutyShift[] shiftArray = restTemplate.getForObject(Constants.security_app_url + "duty/master/allShift",
					DutyShift[].class);
			List<DutyShift> shiftList = new ArrayList<>(Arrays.asList(shiftArray));

			System.err.println("Shift List --------------------- " + shiftList);
			System.err.println("Dept List --------------------- " + deptList);
			System.err.println("Desg List --------------------- " + catList);
			System.err.println("date List --------------------- " + dateList);

			model.addObject("shiftList", shiftList);
			model.addObject("deptList", deptList);
			model.addObject("desgList", catList);
			model.addObject("dateList", dateList);
			model.addObject("isError", isError);
			isError = 0;

		} catch (Exception e) {
			System.out.println("Exc In showAddDuty:" + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/addDutyTaskDetail", method = RequestMethod.GET)
	public @ResponseBody List<TaskDetail> addDutyTaskDetail(HttpServletRequest request, HttpServletResponse response) {

		String taskNameEng = null, taskNameMar = null, taskNameHin = null, taskDescEng = null, taskDescMar = null,
				taskDescHin = null, timeReqVar = null;
		int photo = 0, remark = 0, weight = 0, timeReq = 0;

		try {

			int isDelete = Integer.parseInt(request.getParameter("isDelete"));
			int isEdit = Integer.parseInt(request.getParameter("isEdit"));
			int index = Integer.parseInt(request.getParameter("index"));

			try {
				taskNameEng = request.getParameter("taskNameEng");
				taskNameMar = request.getParameter("taskNameMar");
				taskNameHin = request.getParameter("taskNameHin");
				taskDescEng = request.getParameter("taskDescEng");
				taskDescMar = request.getParameter("taskDescMar");
				taskDescHin = request.getParameter("taskDescHin");
				timeReqVar = request.getParameter("timeReqVar");

				photo = Integer.parseInt(request.getParameter("photoReq"));
				remark = Integer.parseInt(request.getParameter("remarkReq"));
				weight = Integer.parseInt(request.getParameter("weight"));
				timeReq = Integer.parseInt(request.getParameter("timeReq"));
				
				System.err.println("TIME-------------------------------------- "+timeReqVar);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("IsDelete" + isDelete);
			System.out.println("IsEdit" + isEdit);
			System.out.println("Index" + index);

			System.out.println("timeReqtimeReqtimeReq" + timeReq);

			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");

			int userId = userResponse.getUser().getId();

			SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();

			String todaysDate = dtFormat.format(cal.getTimeInMillis());

			if (isDelete == 1) {
				System.out.println("IsDelete" + isDelete);

				int key = Integer.parseInt(request.getParameter("index"));
				tempList.remove(key);

			} else if (isEdit == 1) {
				System.out.println("isEdit=1");

				if (tempList.size() > 0) {

					tempList.get(index).setTaskNameEng(taskNameEng);
					tempList.get(index).setTaskNameMar(taskNameMar);
					tempList.get(index).setTaskNameHin(taskNameHin);
					tempList.get(index).setTaskDescEng(taskDescEng);
					tempList.get(index).setTaskDescMar(taskDescMar);
					tempList.get(index).setTaskDescHin(taskDescHin);
					tempList.get(index).setPhotoReq(photo);
					tempList.get(index).setRemarkReq(remark);
					tempList.get(index).setTaskWeight(weight);
					tempList.get(index).setExInt1(timeReq);
					tempList.get(index).setExVar1(timeReqVar);

					System.out.println("templist  =====" + tempList.toString());
				}

			} else {
				
				System.err.println("ELSE TIME---------------------- "+timeReqVar);

				TaskDetail temp = new TaskDetail(0, 0, taskNameEng, taskNameMar, taskNameHin, taskDescEng, taskDescMar,
						taskDescHin, photo, remark, weight, timeReq, timeReqVar, userId, todaysDate, 1);
				tempList.add(temp);

				System.err.println("tempList in else " + tempList.toString());

			}

		} catch (Exception e) {
			System.err.println("Exce In temp  tempList List " + e.getMessage());
			e.printStackTrace();
		}
		System.err.println("tempList " + tempList.toString());
		

		return tempList;

	}
	

	@RequestMapping(value = "/getTaskForEdit", method = RequestMethod.GET)
	public @ResponseBody TaskDetail getTaskForEdit(HttpServletRequest request, HttpServletResponse response) {

		int index = Integer.parseInt(request.getParameter("index"));

		return tempList.get(index);

	}

	// INSERT DUTY AND TASK HEADER AND DETAIL
	@RequestMapping(value = "/insertDutyAndTask", method = RequestMethod.POST)
	public String insertDutyAndTask(HttpServletRequest request, HttpServletResponse response) {

		try {

			String dutyCode = request.getParameter("dutyCode");
			String dutyName = request.getParameter("dutyName");
			int deptId = 0;
			int desgId = 0;
			int shiftId = 0;
			int typeId = 0;
			String selectedDaysArray[] = request.getParameterValues("selectDay");
			String[] selectedDateArray = request.getParameterValues("selectDate");

			String selectedDate = "0";
			try {
				String arrStr = Arrays.asList(selectedDateArray).toString();
				selectedDate = arrStr.substring(1, (arrStr.length() - 1));

				System.err.println("STR ------------------------------------------------ " + selectedDate);

				selectedDate = selectedDate.replaceAll("\\s+", "");

				System.err.println("DATE----------------------------------------------------" + selectedDate);

			} catch (Exception e) {
				e.printStackTrace();
			}

			String selectedDays = "0";
			try {
				String arrStr = Arrays.asList(selectedDaysArray).toString();
				selectedDays = arrStr.substring(1, (arrStr.length() - 1));

				System.err.println("STR ------------------------------------------------ " + selectedDays);

				selectedDays = selectedDays.replaceAll("\\s+", "");

				System.err.println("DAY---------------------------------------------------- " + selectedDays);

			} catch (Exception e) {
				e.printStackTrace();
			}

			try {

				deptId = Integer.parseInt(request.getParameter("deptId"));
				desgId = Integer.parseInt(request.getParameter("desgId"));
				shiftId = Integer.parseInt(request.getParameter("shiftId"));
				typeId = Integer.parseInt(request.getParameter("typeId"));

			} catch (Exception e) {
				e.printStackTrace();
			}

			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");

			int userId = userResponse.getUser().getId();

			SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String todaysDate = dtFormat.format(cal.getTimeInMillis());

			String typeDesc = "";

			if (typeId == 1) {
				typeDesc = "0";
			} else if (typeId == 2) {
				typeDesc = selectedDays;
			} else if (typeId == 3) {
				typeDesc = selectedDate;
			}

			DutyHeader dutyHeader = new DutyHeader(0, dutyCode, dutyName, deptId, desgId, typeId, typeDesc, shiftId,
					userId, todaysDate, 0, 1);

			List<TaskDetail> detailList = new ArrayList<>();

			for (int i = 0; i < tempList.size(); i++) {

				TaskDetail detail = new TaskDetail(0, 0, tempList.get(i).getTaskNameEng(),
						tempList.get(i).getTaskNameMar(), tempList.get(i).getTaskNameHin(),
						tempList.get(i).getTaskDescEng(), tempList.get(i).getTaskDescMar(),
						tempList.get(i).getTaskDescHin(), tempList.get(i).getPhotoReq(), tempList.get(i).getRemarkReq(),
						tempList.get(i).getTaskWeight(), tempList.get(i).getExInt1(), tempList.get(i).getExVar1(),
						tempList.get(i).getCreatedBy(), tempList.get(i).getCreatedDate(), 1);
				

				detailList.add(detail);

			}

			SaveDutyAndTask model = new SaveDutyAndTask();
			model.setDutyHeader(dutyHeader);
			model.setTaskDetailList(detailList);

			RestTemplate restTemplate = new RestTemplate();

			Info res = restTemplate.postForObject(Constants.security_app_url + "duty/master/saveDutyAndTask", model,
					Info.class);

			System.err.println("OUTPUT------------------------------------------------- " + res);

			if (res != null) {

				if (!res.isError()) {
					isError = 2;
				} else {
					isError = 1;
				}

			} else {
				isError = 1;
			}

		} catch (Exception e) {

			System.err.println("Exce In insert duty and task method  " + e.getMessage());
			e.printStackTrace();

		}

		return "redirect:/showAddDuty";

	}

	DutyHeader editDuty;
	List<TaskDetail> taskList;

	@RequestMapping(value = "/editDutyAndTask/{dutyId}", method = RequestMethod.GET)
	public ModelAndView editDutyAndTask(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int dutyId) {

		ModelAndView model = null;
		try {
			model = new ModelAndView("duty/editDuty");

			RestTemplate restTemplate = new RestTemplate();

			EmpDeptDisplay[] deptArray = restTemplate.getForObject(
					Constants.security_app_url + "master/allEmployeeDepartmentList", EmpDeptDisplay[].class);
			List<EmpDeptDisplay> deptList = new ArrayList<>(Arrays.asList(deptArray));

			EmployeeCategoryDisplay[] catArray = restTemplate.getForObject(
					Constants.security_app_url + "master/allEmployeeCategoryList", EmployeeCategoryDisplay[].class);
			List<EmployeeCategoryDisplay> catList = new ArrayList<>(Arrays.asList(catArray));

			List<Integer> dateList = new ArrayList<>();
			for (int i = 1; i <= 31; i++) {
				dateList.add(i);
			}

			DutyShift[] shiftArray = restTemplate.getForObject(Constants.security_app_url + "duty/master/allShift",
					DutyShift[].class);
			List<DutyShift> shiftList = new ArrayList<>(Arrays.asList(shiftArray));

			model.addObject("shiftList", shiftList);
			model.addObject("deptList", deptList);
			model.addObject("desgList", catList);
			model.addObject("dateList", dateList);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("dutyId", dutyId);

			editDuty = restTemplate.postForObject(Constants.security_app_url + "duty/master/getDutyById", map,
					DutyHeader.class);

			try {
				List<Integer> selectedDayList = Stream.of(editDuty.getTypeDesc().split(",")).map(Integer::parseInt)
						.collect(Collectors.toList());

				model.addObject("selectedDayList", selectedDayList);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				List<Integer> selectedDateList = Stream.of(editDuty.getTypeDesc().split(",")).map(Integer::parseInt)
						.collect(Collectors.toList());

				model.addObject("selectedDateList", selectedDateList);
			} catch (Exception e) {
				e.printStackTrace();
			}

			TaskDetail[] taskArray = restTemplate
					.postForObject(Constants.security_app_url + "duty/master/allTaskByDuty", map, TaskDetail[].class);
			taskList = new ArrayList<>(Arrays.asList(taskArray));

			model.addObject("editDuty", editDuty);
			model.addObject("editTaskList", taskList);

		} catch (Exception e) {
			System.err.println("exception In editDuty " + e.getMessage());
			e.printStackTrace();

		}

		return model;
	}

	@RequestMapping(value = "/editTaskDetail", method = RequestMethod.GET)
	public @ResponseBody List<TaskDetail> editInAddMatIssueDetail(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String taskNameEng = null, taskNameMar = null, taskNameHin = null, taskDescEng = null, taskDescMar = null,
					taskDescHin = null, timeReqVar = null;
			int photo = 0, remark = 0, weight = 0, timeReq = 0;

			RestTemplate rest = new RestTemplate();

			int isDelete = Integer.parseInt(request.getParameter("isDelete"));
			int isEdit = Integer.parseInt(request.getParameter("isEdit"));
			int index = Integer.parseInt(request.getParameter("index"));

			try {
				taskNameEng = request.getParameter("taskNameEng");
				taskNameMar = request.getParameter("taskNameMar");
				taskNameHin = request.getParameter("taskNameHin");
				taskDescEng = request.getParameter("taskDescEng");
				taskDescMar = request.getParameter("taskDescMar");
				taskDescHin = request.getParameter("taskDescHin");
				timeReqVar = request.getParameter("timeReqVar");

				photo = Integer.parseInt(request.getParameter("photoReq"));
				remark = Integer.parseInt(request.getParameter("remarkReq"));
				weight = Integer.parseInt(request.getParameter("weight"));
				timeReq = Integer.parseInt(request.getParameter("timeReq"));

			} catch (Exception e) {
				e.printStackTrace();
			}

			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");

			int userId = userResponse.getUser().getId();

			SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();

			String todaysDate = dtFormat.format(cal.getTimeInMillis());

			if (isDelete == 1) {

				System.out.println("IsDelete" + isDelete);

				TaskDetail deleteDetail = taskList.get(index);
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map = new LinkedMultiValueMap<String, Object>();
				map.add("taskId", deleteDetail.getTaskId());

				Info errMsg = rest.postForObject(Constants.security_app_url + "duty/master/deleteTask", map,
						Info.class);

				taskList.remove(index);

			} else if (isEdit == 1) {

				if (taskList.size() > 0) {

					taskList.get(index).setTaskNameEng(taskNameEng);
					taskList.get(index).setTaskNameMar(taskNameMar);
					taskList.get(index).setTaskNameHin(taskNameHin);
					taskList.get(index).setTaskDescEng(taskDescEng);
					taskList.get(index).setTaskDescMar(taskDescMar);
					taskList.get(index).setTaskDescHin(taskDescHin);
					taskList.get(index).setPhotoReq(photo);
					taskList.get(index).setRemarkReq(remark);
					taskList.get(index).setTaskWeight(weight);
					taskList.get(index).setExInt1(timeReq);
					taskList.get(index).setExVar1(timeReqVar);

					System.out.println("templist  =====" + tempList.toString());
				}

			}

			else {

				int dutyId = Integer.parseInt(request.getParameter("dutyId"));

				if (taskList.size() > 0) {

					TaskDetail temp = new TaskDetail(0, dutyId, taskNameEng, taskNameMar, taskNameHin, taskDescEng,
							taskDescMar, taskDescHin, photo, remark, weight, timeReq, timeReqVar, userId, todaysDate,
							1);

					System.err.println("tempList in else " + tempList.toString());

					taskList.add(temp);

				}
			}

		} catch (Exception e) {
			System.err.println("Exce In edit task detail List " + e.getMessage());
			e.printStackTrace();
		}

		return taskList;

	}

	@RequestMapping(value = "/updateDutyAndTask", method = RequestMethod.POST)
	public String updateMaterialContr(HttpServletRequest request, HttpServletResponse response) {

		try {

			int dutyId = Integer.parseInt(request.getParameter("dutyId"));

			String dutyCode = request.getParameter("dutyCode");
			String dutyName = request.getParameter("dutyName");
			int deptId = 0;
			int desgId = 0;
			int shiftId = 0;
			int typeId = 0;
			String selectedDaysArray[] = request.getParameterValues("selectDay");
			String[] selectedDateArray = request.getParameterValues("selectDate");

			String selectedDate = "0";
			try {
				String arrStr = Arrays.asList(selectedDateArray).toString();
				selectedDate = arrStr.substring(1, (arrStr.length() - 1));

				System.err.println("STR ------------------------------------------------ " + selectedDate);

				selectedDate = selectedDate.replaceAll("\\s+", "");

				System.err.println("DATE----------------------------------------------------" + selectedDate);

			} catch (Exception e) {
				e.printStackTrace();
			}

			String selectedDays = "0";
			try {
				String arrStr = Arrays.asList(selectedDaysArray).toString();
				selectedDays = arrStr.substring(1, (arrStr.length() - 1));

				System.err.println("STR ------------------------------------------------ " + selectedDays);

				selectedDays = selectedDays.replaceAll("\\s+", "");

				System.err.println("DAY---------------------------------------------------- " + selectedDays);

			} catch (Exception e) {
				e.printStackTrace();
			}

			try {

				deptId = Integer.parseInt(request.getParameter("deptId"));
				desgId = Integer.parseInt(request.getParameter("desgId"));
				shiftId = Integer.parseInt(request.getParameter("shiftId"));
				typeId = Integer.parseInt(request.getParameter("typeId"));

			} catch (Exception e) {
				e.printStackTrace();
			}

			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");

			int userId = userResponse.getUser().getId();

			SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String todaysDate = dtFormat.format(cal.getTimeInMillis());

			String typeDesc = "";

			if (typeId == 1) {
				typeDesc = "0";
			} else if (typeId == 2) {
				typeDesc = selectedDays;
			} else if (typeId == 3) {
				typeDesc = selectedDate;
			}

			DutyHeader dutyHeader = new DutyHeader(dutyId, dutyCode, dutyName, deptId, desgId, typeId, typeDesc,
					shiftId, userId, todaysDate, 0, 1);

			List<TaskDetail> detailList = new ArrayList<>();

			for (int i = 0; i < taskList.size(); i++) {

				TaskDetail detail = new TaskDetail(taskList.get(i).getTaskId(), dutyId,
						taskList.get(i).getTaskNameEng(), taskList.get(i).getTaskNameMar(),
						taskList.get(i).getTaskNameHin(), taskList.get(i).getTaskDescEng(),
						taskList.get(i).getTaskDescMar(), taskList.get(i).getTaskDescHin(),
						taskList.get(i).getPhotoReq(), taskList.get(i).getRemarkReq(), taskList.get(i).getTaskWeight(),
						taskList.get(i).getExInt1(), taskList.get(i).getExVar1(), taskList.get(i).getCreatedBy(),
						taskList.get(i).getCreatedDate(), 1);

				detailList.add(detail);

			}

			SaveDutyAndTask model = new SaveDutyAndTask();
			model.setDutyHeader(dutyHeader);
			model.setTaskDetailList(detailList);

			RestTemplate restTemplate = new RestTemplate();

			Info res = restTemplate.postForObject(Constants.security_app_url + "duty/master/saveDutyAndTask", model,
					Info.class);

			System.err.println("OUTPUT------------------------------------------------- " + res);

			if (res != null) {

				if (!res.isError()) {
					isError = 2;
				} else {
					isError = 1;
				}

			} else {
				isError = 1;
			}

		} catch (Exception e) {

			System.err.println("Exce In update duty method  " + e.getMessage());
			e.printStackTrace();

		}

		return "redirect:/showAddDuty";

	}

	@RequestMapping(value = "/getTaskForEdit1", method = RequestMethod.GET)
	public @ResponseBody TaskDetail getTaskForEdit1(HttpServletRequest request, HttpServletResponse response) {

		int index = Integer.parseInt(request.getParameter("index"));

		return taskList.get(index);

	}

	@RequestMapping(value = "/deleteDuty/{dutyId}", method = RequestMethod.GET)
	public String deleteMatIssueCon(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int dutyId) {

		try {
			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("dutyId", dutyId);
			map.add("status", 0);

			Info errMsg = restTemplate.postForObject(Constants.security_app_url + "duty/master/deleteDuty", map,
					Info.class);

		} catch (Exception e) {

			System.err.println("Exception in /delete duty" + e.getMessage());
			e.printStackTrace();
		}

		return "redirect:/showAllDutyHeader";
	}

}
