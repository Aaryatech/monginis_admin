package com.ats.adminpanel.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.RawMaterial.GetItemSfHeader;
import com.ats.adminpanel.model.RawMaterial.RmItemCategory;
import com.ats.adminpanel.model.item.ErrorMessage;
import com.ats.adminpanel.model.item.FrItemStockConfigureList;
import com.ats.adminpanel.model.login.UserResponse;
import com.ats.adminpanel.model.masters.GateSaleDiscount;
import com.ats.adminpanel.model.productionplan.BillOfMaterialDetailed;
import com.ats.adminpanel.model.productionplan.BillOfMaterialHeader;
import com.ats.adminpanel.model.spprod.Employee;
import com.ats.adminpanel.model.spprod.EmployeeList;
import com.ats.adminpanel.model.spprod.GetAllocStationCk;
import com.ats.adminpanel.model.spprod.GetAllocStationCkList;
import com.ats.adminpanel.model.spprod.GetEmployeeList;
import com.ats.adminpanel.model.spprod.GetInstVerifHeader;
import com.ats.adminpanel.model.spprod.GetInstrVerifHeader;
import com.ats.adminpanel.model.spprod.GetSpDetailForBom;
import com.ats.adminpanel.model.spprod.GetSpDetailForBomList;
import com.ats.adminpanel.model.spprod.InstAllocToStation;
import com.ats.adminpanel.model.spprod.InstAllocToStationList;
import com.ats.adminpanel.model.spprod.InstVerificationDetail;
import com.ats.adminpanel.model.spprod.InstVerificationHeader;
import com.ats.adminpanel.model.spprod.Instrument;
import com.ats.adminpanel.model.spprod.InstrumentList;
import com.ats.adminpanel.model.spprod.MDept;
import com.ats.adminpanel.model.spprod.MDeptList;
import com.ats.adminpanel.model.spprod.Shift;
import com.ats.adminpanel.model.spprod.ShiftList;
import com.ats.adminpanel.model.spprod.SpCkAllocDetail;
import com.ats.adminpanel.model.spprod.SpCkAllocHeader;
import com.ats.adminpanel.model.spprod.SpStation;
import com.ats.adminpanel.model.spprod.SpStationList;
import com.ats.adminpanel.model.spprod.StationAllocList;
import com.ats.adminpanel.model.spprod.StationAllocation;
import com.ats.adminpanel.model.spprod.StationSpCake;
import com.ats.adminpanel.model.spprod.StationSpCakeList;
import com.ats.adminpanel.model.spprod.StationWiseCkCount;
import com.ats.adminpanel.model.spprod.TypeList;

@Controller
@Scope("session")
public class SpProductionController {
 
	private int empType = 1;// employee Type
	private int instType = 2;// instrument Type
	private int mistryId = 101;
	private int helperId = 102;
	private  InstVerificationHeader instVerificationHeaderRes = null;
	private  int userId = 0;
	private  int userType = 6;
	private  StationSpCakeList stationSpCakeList=new StationSpCakeList();
	private  List<GetAllocStationCk> getAllocStationCkRes=null;
	private  List<GetSpDetailForBom> getSpDetailForBomList=null;
	
	@RequestMapping(value = "/showAddEmployee", method = RequestMethod.GET)
	public ModelAndView showAddEmployee(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct=17;
		Constants.subAct =90;

		ModelAndView model = new ModelAndView("spProduction/addEmployee");
		try {
			RestTemplate restTemplate = new RestTemplate();

			MDeptList mDeptList = restTemplate.getForObject(Constants.url + "/spProduction/mDeptList", MDeptList.class);
			System.out.println("Response: " + mDeptList.toString());

			MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();
			mvm.add("empType", empType);

			EmployeeList employeeList = restTemplate.postForObject(Constants.url + "/spProduction/getEmployeeList", mvm,
					EmployeeList.class);
			System.out.println("Response: " + employeeList.toString());

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("typeId", empType);

			TypeList typeList = restTemplate.postForObject(Constants.url + "/spProduction/getTypeList", map,
					TypeList.class);
			
			map = new LinkedMultiValueMap<String, Object>();
			map.add("userType",userType);
			List<GateSaleDiscount> discountList=restTemplate.postForObject(Constants.url + "/gatesale/getGateSaleDiscByUsrType", map,List.class);

			System.out.println("Response: " + typeList.toString());
            model.addObject("discountList", discountList);
			model.addObject("typeList", typeList.getTypeList());
			model.addObject("mDeptList", mDeptList.getList());
			model.addObject("employeeList", employeeList.getEmployeeList());
		} catch (Exception e) {
			System.out.println("Exc In ShowAddEmployee:" + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/showAddDepartment", method = RequestMethod.GET)
	public ModelAndView showAddDepartment(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct =17;
		Constants.subAct =91;

		ModelAndView model = new ModelAndView("masters/department");
		try {
			RestTemplate restTemplate = new RestTemplate();

			MDeptList mDeptList = restTemplate.getForObject(Constants.url + "/spProduction/mDeptList", MDeptList.class);
			System.out.println("Response: " + mDeptList.toString());

			model.addObject("deptList", mDeptList.getList());
		} catch (Exception e) {
			System.out.println("Exc In showAddDept:" + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/showAddStation", method = RequestMethod.GET)
	public ModelAndView showAddStation(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct =19;
		Constants.subAct =95;

		ModelAndView model = new ModelAndView("spProduction/addSpStation");
		try {
			RestTemplate restTemplate = new RestTemplate();

			MDeptList mDeptList = restTemplate.getForObject(Constants.url + "/spProduction/mDeptList", MDeptList.class);
			System.out.println("Response: " + mDeptList.toString());

			SpStationList spStationList = restTemplate.getForObject(Constants.url + "/spProduction/getSpStationList",
					SpStationList.class);
			System.out.println("Response: " + spStationList.toString());

			model.addObject("mDeptList", mDeptList.getList());
			model.addObject("spStationList", spStationList.getSpStationList());
		//	model.addObject("spStation", new SpStation());
		} catch (Exception e) {
			System.out.println("Exc In showAddStation:" + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/showAddInstrument", method = RequestMethod.GET)
	public ModelAndView showAddInstrument(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct =19;
		Constants.subAct =96;
		RestTemplate restTemplate = new RestTemplate();

		ModelAndView model = new ModelAndView("spProduction/addInstrument");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("typeId", instType);

			TypeList typeList = restTemplate.postForObject(Constants.url + "/spProduction/getTypeList", map,
					TypeList.class);
			System.out.println("Response: " + typeList.toString());

			model.addObject("typeList", typeList.getTypeList());
		} catch (Exception e) {
			System.out.println("Exception In showAllInstrument" + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/showAllInstrument", method = RequestMethod.GET)
	public ModelAndView showAllInstrument(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct =19;
		Constants.subAct =97;

		ModelAndView model = new ModelAndView("spProduction/instrumentsList");
		try {
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();
			mvm.add("instType", instType);

			InstrumentList instrumentsList = restTemplate
					.postForObject(Constants.url + "/spProduction/getInstrumentList", mvm, InstrumentList.class);
			System.out.println("Response: " + instrumentsList.toString());

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("typeId", instType);

			TypeList typeList = restTemplate.postForObject(Constants.url + "/spProduction/getTypeList", map,
					TypeList.class);
			System.out.println("Response: " + typeList.toString());

			model.addObject("typeList", typeList.getTypeList());

			model.addObject("instrumentsList", instrumentsList.getInstrumentList());
		} catch (Exception e) {
			System.out.println("Exception In showAllInstrument" + e.getMessage());
		}
		return model;
	}

	// ------------------------------Delete SpStation Process------------------------------------
	@RequestMapping(value = "/deleteSpStation/{stId}", method = RequestMethod.GET)
	public String deleteRmItemCategory(@PathVariable int stId) {

		ModelAndView mav = new ModelAndView("spProduction/instrumentsList");
		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("spId", stId);

			Info errorResponse = rest.postForObject(Constants.url + "/spProduction/deleteSPStation", map, Info.class);
			System.out.println(errorResponse.toString());

			if (errorResponse.getError()) {

				return "redirect:/showAddStation";

			} else {
				return "redirect:/showAddStation";

			}
		} catch (Exception e) {
			System.out.println("Exception In delete SpStation:" + e.getMessage());

			return "redirect:/showAddStation";

		}

	}

	// ----------------------------------------END-------------------------------------------------------------
	// ------------------------------Delete Department Process------------------------------------
	@RequestMapping(value = "/deleteDept/{deptId}", method = RequestMethod.GET)
	public String deleteDepartment(@PathVariable int deptId) {

		ModelAndView mav = new ModelAndView("masters/department");
		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("deptId", deptId);

			Info errorResponse = rest.postForObject(Constants.url + "/spProduction/deleteMDept", map, Info.class);
			System.out.println(errorResponse.toString());

			if (errorResponse.getError()) {

				return "redirect:/showAddDepartment";

			} else {
				return "redirect:/showAddDepartment";

			}
		} catch (Exception e) {
			System.out.println("Exception In delete Department:" + e.getMessage());

			return "redirect:/showAddDepartment";

		}

	}

	// ----------------------------------------END-------------------------------------------------------------
	// ------------------------------Delete Instrument Process------------------------------------
	@RequestMapping(value = "/deleteInstrument/{instId}", method = RequestMethod.GET)
	public String deleteInstrument(@PathVariable int instId) {

		ModelAndView mav = new ModelAndView("spProduction/instrumentsList");
		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("instrumentId", instId);

			Info errorResponse = rest.postForObject(Constants.url + "/spProduction/deleteInstrument", map, Info.class);
			System.out.println(errorResponse.toString());

			if (errorResponse.getError()) {

				return "redirect:/showAllInstrument";

			} else {
				return "redirect:/showAllInstrument";

			}
		} catch (Exception e) {
			System.out.println("Exception In delete Instrument:" + e.getMessage());

			return "redirect:/showAllInstrument";

		}

	}

	// ----------------------------------------END-------------------------------------------------------------
	// ------------------------------Delete Employee Process------------------------------------
	@RequestMapping(value = "/deleteEmp/{empId}", method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable int empId) {

		ModelAndView mav = new ModelAndView("spProduction/addEmployee");
		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("empId", empId);

			Info errorResponse = rest.postForObject(Constants.url + "/spProduction/deleteEmployee", map, Info.class);
			System.out.println(errorResponse.toString());

			if (errorResponse.getError()) {

				return "redirect:/showAddEmployee";

			} else {
				return "redirect:/showAddEmployee";

			}
		} catch (Exception e) {
			System.out.println("Exception In delete Employee:" + e.getMessage());

			return "redirect:/showAddEmployee";

		}

	}

	// ----------------------------------------END-------------------------------------------------------------
	// ------------------------------ADD SpStation Process------------------------------------
	@RequestMapping(value = "/addSpStationProcess", method = RequestMethod.POST)
	public String addSpStation(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addSpStation");
		try {

			int stId = 0;

			try {
				stId = Integer.parseInt(request.getParameter("st_id"));

			} catch (Exception e) {
				stId = 0;
				System.out.println("In Catch of Add Station Process Exc:" + e.getMessage());

			}

			String stName = request.getParameter("station_name");

			int deptId = Integer.parseInt(request.getParameter("dept_id"));

			String stLocation = request.getParameter("st_location");

			int stIsUsed = Integer.parseInt(request.getParameter("st_is_used"));

			SpStation spStation = new SpStation();
			spStation.setStId(stId);
			spStation.setDeptId(deptId);
			spStation.setStName(stName);
			spStation.setStLocation(stLocation);
			spStation.setStIsUsed(stIsUsed);
			spStation.setDelStatus(0);

			RestTemplate restTemplate = new RestTemplate();

			ErrorMessage errorMessage = restTemplate.postForObject(Constants.url + "/spProduction/saveStation",
					spStation, ErrorMessage.class);
			System.out.println("Response: " + errorMessage.toString());

			if (errorMessage.getError() == true) {

				System.out.println("Error:True" + errorMessage.toString());
				return "redirect:/showAddStation";

			} else {
				return "redirect:/showAddStation";
			}

		} catch (Exception e) {

			System.out.println("Exception In Add Item Category Process:" + e.getMessage());

		}

		return "redirect:/showItemCatList";
	}

	// ----------------------------------------END---------------------------------------------------
	// ------------------------------ADD Employee Process------------------------------------
	@RequestMapping(value = "/addEmpProcess", method = RequestMethod.POST)
	public String addEmployee(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("spProduction/addEmployee");
		try {
			int empId = 0;

			try {
				empId = Integer.parseInt(request.getParameter("emp_id"));

			} catch (Exception e) {
				empId = 0;

				System.out.println("In Catch of Add Emp Process Exc:" + e.getMessage());

			}

			String empName = request.getParameter("emp_name");

			int empType = Integer.parseInt(request.getParameter("emp_type"));

			int deptId = Integer.parseInt(request.getParameter("dept_id"));

			int isUsed = Integer.parseInt(request.getParameter("is_used"));
			
			String empDob=request.getParameter("emp_dob");
			
			String empDoj=request.getParameter("emp_doj");
			
			int empFamMemb=Integer.parseInt(request.getParameter("emp_fam_memb"));
			
			String empMobile= request.getParameter("emp_mob");
			
			float monthlyLimit= Float.parseFloat(request.getParameter("monthly_limit"));
			
			float yearlyLimit= Float.parseFloat(request.getParameter("yearly_limit"));
			
			int discId= Integer.parseInt(request.getParameter("disc_id"));
			Employee emp = new Employee();
			emp.setEmpId(empId);
			emp.setDeptId(deptId);
			emp.setEmpName(empName);
			emp.setIsUsed(isUsed);
			emp.setEmpType(empType);
			emp.setDelStatus(0);
			emp.setEmpDob(empDob);
			emp.setEmpDoj(empDoj);
			emp.setEmpFamMemb(empFamMemb);
			emp.setEmpMobile(empMobile);
			emp.setMonthlyLimit(monthlyLimit);
			emp.setYearlyLimit(yearlyLimit);
			emp.setDiscId(discId);

			RestTemplate restTemplate = new RestTemplate();

			ErrorMessage errorMessage = restTemplate.postForObject(Constants.url + "/spProduction/saveEmployee", emp,
					ErrorMessage.class);
			System.out.println("Response: " + errorMessage.toString());

			if (errorMessage.getError() == true) {

				System.out.println("Error:True" + errorMessage.toString());
				return "redirect:/showAddEmployee";
			} else {
				return "redirect:/showAddEmployee";
			}
		} catch (Exception e) {
			System.out.println("Exception In Add Employee Process:" + e.getMessage());
		}

		return "redirect:/showAddEmployee";
	}

	// ----------------------------------------END-------------------------------------------------------------
	// ------------------------------ADD Employee Process------------------------------------
	@RequestMapping(value = "/addInstrumentProcess", method = RequestMethod.POST)
	public String addInstrumentProcess(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("spProduction/addInstrument");
		try {
			int instrumentId = 0;

			try {
				instrumentId = Integer.parseInt(request.getParameter("instrument_id"));

			} catch (Exception e) {
				instrumentId = 0;
				System.out.println("Exception In add Instrument:" + e.getMessage());

			}
			String instName = request.getParameter("instrument_name");

			int instType = Integer.parseInt(request.getParameter("inst_type"));

			int instrumentOpqty = Integer.parseInt(request.getParameter("instrument_opqty"));

			int instrumentClqty = Integer.parseInt(request.getParameter("instrument_clqty"));

			String instStatus = request.getParameter("instrument_status");

			int stockQty = Integer.parseInt(request.getParameter("stock_qty"));

			int instIsUsed = Integer.parseInt(request.getParameter("instrument_is_used"));

			Instrument instrument = new Instrument();
			instrument.setInstrumentId(instrumentId);
			instrument.setInstrumentName(instName);
			instrument.setInstrumentIsUsed(instIsUsed);
			instrument.setInstrumentOpqty(instrumentOpqty);
			instrument.setInstrumentClqty(instrumentClqty);
			instrument.setInstrumentStatus(instStatus);
			instrument.setStockQty(stockQty);
			instrument.setInstType(instType);

			RestTemplate restTemplate = new RestTemplate();

			ErrorMessage errorMessage = restTemplate.postForObject(Constants.url + "/spProduction/saveInstrument",
					instrument, ErrorMessage.class);
			System.out.println("Response: " + errorMessage.toString());

			if (errorMessage.getError() == true) {

				System.out.println("Error:True" + errorMessage.toString());
				return "redirect:/showAllInstrument";
			} else {
				return "redirect:/showAllInstrument";
			}
		} catch (Exception e) {
			System.out.println("Exception In Add Instrument Process:" + e.getMessage());
		}

		return "redirect:/showAllInstrument";
	}
	// ----------------------------------------END-------------------------------------------------------------
	// ------------------------------Edit Employee------------------------------------

	@RequestMapping(value = "/updateEmp/{empId}", method = RequestMethod.GET)
	public ModelAndView updateEmp(@PathVariable int empId) {

		ModelAndView mav = new ModelAndView("spProduction/addEmployee");
		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("empId", empId);

			Employee emp = rest.postForObject(Constants.url + "/spProduction/getEmployee", map, Employee.class);
			System.out.println(emp.toString());

			MDeptList mDeptList = rest.getForObject(Constants.url + "/spProduction/mDeptList", MDeptList.class);
			System.out.println("Response: " + mDeptList.toString());

			MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();
			mvm.add("empType", empType);

			EmployeeList employeeList = rest.postForObject(Constants.url + "/spProduction/getEmployeeList", mvm,
					EmployeeList.class);
			System.out.println("Response: " + employeeList.toString());

			map = new LinkedMultiValueMap<String, Object>();
			map.add("typeId", empType);
			TypeList typeList = rest.postForObject(Constants.url + "/spProduction/getTypeList",map, TypeList.class);
			System.out.println("Response: " + typeList.toString());
			
			map = new LinkedMultiValueMap<String, Object>();
			map.add("userType",userType);
			List<GateSaleDiscount> discountList=rest.postForObject(Constants.url + "/gatesale/getGateSaleDiscByUsrType", map,List.class);

			System.out.println("Response: " + typeList.toString());
			mav.addObject("discountList", discountList);
			if (emp.isError()) {

				return mav;

			} else {
				mav.addObject("mDeptList", mDeptList.getList());
				mav.addObject("employeeList", employeeList.getEmployeeList());
				mav.addObject("emp", emp);
				mav.addObject("typeList", typeList.getTypeList());

			}
		} catch (Exception e) {
			System.out.println("Exception In Edit Emp:" + e.getMessage());

			return mav;

		}
		return mav;
	}
	// -----------------------------------------------------------------------------------------------------------
	// ------------------------------Edit Instrument-------------------------------------------------------------

	@RequestMapping(value = "/updateInstrument/{instrumentId}", method = RequestMethod.GET)
	public ModelAndView updateInstrument(@PathVariable int instrumentId) {

		ModelAndView mav = new ModelAndView("spProduction/editInstrument");
		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("instrumentId", instrumentId);

			Instrument instrument = rest.postForObject(Constants.url + "/spProduction/getInstrument", map,
					Instrument.class);
			System.out.println(instrument.toString());
			MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();
			mvm.add("typeId",instType);

			TypeList typeList = rest.postForObject(Constants.url + "/spProduction/getTypeList",mvm,TypeList.class);
			System.out.println("Response: " + typeList.toString());

			if (instrument.isError()) {

				return mav;

			} else {

				mav.addObject("typeList", typeList.getTypeList());

				mav.addObject("instrument", instrument);
			}
		} catch (Exception e) {
			System.out.println("Exception In Edit instrument:" + e.getMessage());

			return mav;
		}
		return mav;
	}
	// ----------------------------------------------------------------------------------------------
	// ------------------------------Edit SpStation-------------------------------------------------------------

	@RequestMapping(value = "/updateSpStation/{stId}", method = RequestMethod.GET)
	public ModelAndView updateSpStation(@PathVariable int stId) {

		ModelAndView mav = new ModelAndView("spProduction/addSpStation");
		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("stId", stId);

			SpStation spStation = rest.postForObject(Constants.url + "/spProduction/getSpStation", map,
					SpStation.class);
			System.out.println(spStation.toString());

			MDeptList mDeptList = rest.getForObject(Constants.url + "/spProduction/mDeptList", MDeptList.class);
			System.out.println("Response: " + mDeptList.toString());

			SpStationList spStationList = rest.getForObject(Constants.url + "/spProduction/getSpStationList",
					SpStationList.class);
			System.out.println("Response: " + spStationList.toString());

			if (spStation.isError()) {

				return mav;

			} else {
				mav.addObject("mDeptList", mDeptList.getList());
				mav.addObject("spStationList", spStationList.getSpStationList());
				mav.addObject("spStation", spStation);
			}
		} catch (Exception e) {
			System.out.println("Exception In Edit spStation:" + e.getMessage());

			return mav;
		}
		return mav;
	}
	// -------------------------------------------------------------------------------------------
	// ------------------------------Edit Dept-------------------------------------------------------------

	@RequestMapping(value = "/updateDept/{deptId}", method = RequestMethod.GET)
	public ModelAndView updateDepartment(@PathVariable int deptId) {

		ModelAndView mav = new ModelAndView("masters/department");
		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("deptId", deptId);

			MDept department = rest.postForObject(Constants.url + "/spProduction/getDepartment", map, MDept.class);
			System.out.println(department.toString());

			MDeptList mDeptList = rest.getForObject(Constants.url + "/spProduction/mDeptList", MDeptList.class);
			System.out.println("Response: " + mDeptList.toString());

			if (department != null) {

				mav.addObject("deptList", mDeptList.getList());
				mav.addObject("dept", department);

			}

		} catch (Exception e) {
			System.out.println("Exception In Edit Dept:" + e.getMessage());

			return mav;
		}
		return mav;
	}

	// ------------------------------------------------------------------------------------
	// ------------------------------ADD Department Process------------------------------------
	@RequestMapping(value = "/addDeptProcess", method = RequestMethod.POST)
	public String addDeptProcess(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/department");
		try {
			int deptId = 0;

			try {
				deptId = Integer.parseInt(request.getParameter("dept_id"));

			} catch (Exception e) {
				deptId = 0;

				System.out.println("In Catch of Add Dept Process Exc:" + e.getMessage());

			}

			String deptName = request.getParameter("dept_name");

			String deptCode = request.getParameter("dept_code");

			int isActive = Integer.parseInt(request.getParameter("is_active"));

			MDept mDept = new MDept();
			mDept.setDeptId(deptId);
			mDept.setDeptCode(deptCode);
			mDept.setDeptName(deptName);
			mDept.setIsActive(isActive);

			RestTemplate restTemplate = new RestTemplate();

			ErrorMessage errorMessage = restTemplate.postForObject(Constants.url + "/spProduction/saveDept", mDept,
					ErrorMessage.class);
			System.out.println("Response: " + errorMessage.toString());

			if (errorMessage.getError() == true) {

				System.out.println("Error:True" + errorMessage.toString());
				return "redirect:/showAddDepartment";
			} else {
				return "redirect:/showAddDepartment";
			}
		} catch (Exception e) {
			System.out.println("Exception In Add Department Process:" + e.getMessage());
		}

		return "redirect:/showAddDepartment";
	}

	// ----------------------------------------END-------------------------------------------------------------
	// ------------------------------ADD Shift Process------------------------------------
	@RequestMapping(value = "/addShiftProcess", method = RequestMethod.POST)
	public String addShiftProcess(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("spProduction/addShift");
		try {
			int shiftId = 0;

			try {
				shiftId = Integer.parseInt(request.getParameter("shift_id"));

			} catch (Exception e) {
				shiftId = 0;

				System.out.println("In Catch of Add Shift Process Exc:" + e.getMessage());

			}

			String shiftName = request.getParameter("shift_name");

			int shiftNo = Integer.parseInt(request.getParameter("shift_no"));

			int isUsed = Integer.parseInt(request.getParameter("is_used"));

			Shift shift = new Shift();
			shift.setShiftId(shiftId);
			shift.setShiftName(shiftName);
			shift.setShiftNo(shiftNo);
			shift.setIsUsed(isUsed);
			shift.setDelStatus(0);

			RestTemplate restTemplate = new RestTemplate();

			ErrorMessage errorMessage = restTemplate.postForObject(Constants.url + "/spProduction/saveShift", shift,
					ErrorMessage.class);
			System.out.println("Response: " + errorMessage.toString());

			if (errorMessage.getError() == true) {

				System.out.println("Error:True" + errorMessage.toString());
				return "redirect:/showAddShift";
			} else {
				return "redirect:/showAddShift";
			}
		} catch (Exception e) {
			System.out.println("Exception In Add Shift Process:" + e.getMessage());
		}

		return "redirect:/showAddShift";
	}

	// ----------------------------------------END-------------------------------------------------------------

	@RequestMapping(value = "/showAddShift", method = RequestMethod.GET)
	public ModelAndView showAddShift(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct =19;
		Constants.subAct =98;

		ModelAndView model = new ModelAndView("spProduction/addShift");
		RestTemplate restTemplate = new RestTemplate();
		try {
			ShiftList shiftList = restTemplate.getForObject(Constants.url + "/spProduction/getShiftList",
					ShiftList.class);
			System.out.println("Response: " + shiftList.toString());

			model.addObject("shiftList", shiftList.getShiftList());
		} catch (Exception e) {
			System.out.println("Exc In Add Shift" + e.getMessage());
		}
		return model;
	}

	// -------------------------------Delete Shift---------------------------------
	@RequestMapping(value = "/deleteShift/{shiftId}", method = RequestMethod.GET)
	public String deleteShift(@PathVariable int shiftId) {

		ModelAndView mav = new ModelAndView("spProduction/addShift");
		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("shiftId", shiftId);

			Info errorResponse = rest.postForObject(Constants.url + "/spProduction/deleteShift", map, Info.class);
			System.out.println(errorResponse.toString());

			if (errorResponse.getError()) {

				return "redirect:/showAddShift";

			} else {
				return "redirect:/showAddShift";

			}
		} catch (Exception e) {
			System.out.println("Exception In delete Delete Shift:" + e.getMessage());

			return "redirect:/showAddShift";

		}

	}

	// ----------------------------------------END-------------------------------------------------------------
	// ------------------------------Edit Shift-------------------------------------------------------------

	@RequestMapping(value = "/updateShift/{shiftId}", method = RequestMethod.GET)
	public ModelAndView updateShift(@PathVariable int shiftId) {

		ModelAndView mav = new ModelAndView("spProduction/addShift");
		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("shiftId", shiftId);

			Shift shift = rest.postForObject(Constants.url + "/spProduction/getShift", map, Shift.class);
			System.out.println(shift.toString());

			ShiftList shiftListRes = rest.getForObject(Constants.url + "/spProduction/getShiftList", ShiftList.class);
			System.out.println("Response: " + shiftListRes.toString());

			if (shift != null) {

				mav.addObject("shift", shift);
				mav.addObject("shiftList", shiftListRes.getShiftList());

			}

		} catch (Exception e) {
			System.out.println("Exception In Edit Shift:" + e.getMessage());

			return mav;
		}
		return mav;
	}

	// -------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/configureStation", method = RequestMethod.GET)
	public ModelAndView configureStation(HttpServletRequest request, HttpServletResponse response) {

		 Constants.mainAct =20;
		 Constants.subAct =99;

		ModelAndView model = new ModelAndView("spProduction/configureStation");
		try {
			RestTemplate restTemplate = new RestTemplate();

			SpStationList spStationList = restTemplate.getForObject(Constants.url + "/spProduction/getSpStationList",
					SpStationList.class);
			System.out.println("Response: " + spStationList.toString());
			MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();
			mvm.add("empType", mistryId);

			GetEmployeeList employeeList = restTemplate
					.postForObject(Constants.url + "/spProduction/getEmployeesByType", mvm, GetEmployeeList.class);
			System.out.println("Response: " + employeeList.toString());

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("empType", helperId);

			GetEmployeeList helperList = restTemplate.postForObject(Constants.url + "/spProduction/getEmployeesByType",
					map, GetEmployeeList.class);
			System.out.println("Response: " + helperList.toString());

			ShiftList shiftListRes = restTemplate.getForObject(Constants.url + "/spProduction/getShiftList",
					ShiftList.class);
			System.out.println("Response: " + shiftListRes.toString());

			StationAllocList stationAllocList = restTemplate
					.getForObject(Constants.url + "/spProduction/getStationAllocList", StationAllocList.class);

			model.addObject("stationAllocList", stationAllocList.getStationAllocationList());

			model.addObject("shiftList", shiftListRes.getShiftList());
			model.addObject("spStationList", spStationList.getSpStationList());
			model.addObject("isEdit", 0);
			model.addObject("helperList", helperList.getGetEmpList());
			model.addObject("employeeList", employeeList.getGetEmpList());
		} catch (Exception e) {
			System.out.println("Exc In ShowAddEmployee:" + e.getMessage());
		}
		return model;
	}

	// ----------------------------------------------------------------------------------------------------------
	// ------------------------------ADD StAllocation
	// Process------------------------------------
	@RequestMapping(value = "/addStationAllocation", method = RequestMethod.POST)
	public String addStationAllocation(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("spProduction/configureStation");
		try {
			int allocationId = 0;

			try {
				allocationId = Integer.parseInt(request.getParameter("allocation_id"));

				System.out.println("try : allocationId" + allocationId);
			} catch (Exception e) {
				allocationId = 0;

				System.out.println("In Catch of Add StationAllocation Process Exc:" + e.getMessage());

			}

			int stId = Integer.parseInt(request.getParameter("st_id"));
			System.out.println("stId" + stId);

			int shiftId = Integer.parseInt(request.getParameter("shift_id"));
			System.out.println("shiftId" + shiftId);

			int mId = Integer.parseInt(request.getParameter("m_id"));
			System.out.println("mId" + mId);

			int hId = Integer.parseInt(request.getParameter("h_id"));
			System.out.println("hId" + hId);

			StationAllocation stationAllocation = new StationAllocation();
			stationAllocation.setAllocationId(allocationId);
			stationAllocation.setDelStatus(0);
			stationAllocation.setEmpHelperId(hId);
			stationAllocation.setEmpMistryId(mId);
			stationAllocation.setShiftId(shiftId);
			stationAllocation.setStationId(stId);
			System.out.println("stationAllocation:" + stationAllocation.toString());

			RestTemplate restTemplate = new RestTemplate();

			ErrorMessage errorMessage = restTemplate.postForObject(
					Constants.url + "/spProduction/saveProdStationAllocation", stationAllocation, ErrorMessage.class);
			System.out.println("Response: " + errorMessage.toString());

			if (errorMessage.getError() == true) {

				System.out.println("Error:True" + errorMessage.toString());
				return "redirect:/configureStation";
			} else {
				return "redirect:/configureStation";
			}
		} catch (Exception e) {
			System.out.println("Exception In ConfigureStation Process:" + e.getMessage());
		}

		return "redirect:/configureStation";
	}

	// ----------------------------------------END-------------------------------------------------------------
	// ------------------------------Edit
	// StAllocation-------------------------------------------------------------

	@RequestMapping(value = "/updateStationAllocation/{allocationId}", method = RequestMethod.GET)
	public ModelAndView updateStationAllocation(@PathVariable int allocationId) {

		ModelAndView mav = new ModelAndView("spProduction/configureStation");
		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("allocationId", allocationId);

			StationAllocation stationAllocation = rest.postForObject(Constants.url + "/spProduction/getStationAlloc",
					map, StationAllocation.class);
			System.out.println(stationAllocation.toString());

			SpStationList spStationList = rest.getForObject(Constants.url + "/spProduction/getSpStationList",
					SpStationList.class);
			System.out.println("Response: " + spStationList.toString());
			MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();
			mvm.add("empType", mistryId);

			GetEmployeeList employeeList = rest.postForObject(Constants.url + "/spProduction/getEmployeesByType", mvm,
					GetEmployeeList.class);
			System.out.println("Response: " + employeeList.toString());

			MultiValueMap<String, Object> mvc = new LinkedMultiValueMap<String, Object>();
			mvc.add("empType", helperId);

			GetEmployeeList helperList = rest.postForObject(Constants.url + "/spProduction/getEmployeesByType", mvc,
					GetEmployeeList.class);
			System.out.println("Response: " + helperList.toString());

			ShiftList shiftListRes = rest.getForObject(Constants.url + "/spProduction/getShiftList", ShiftList.class);
			System.out.println("Response: " + shiftListRes.toString());

			StationAllocList stationAllocList = rest.getForObject(Constants.url + "/spProduction/getStationAllocList",
					StationAllocList.class);

			if (stationAllocation != null) {

				mav.addObject("cnfStation", stationAllocation);

				mav.addObject("stationAllocList", stationAllocList.getStationAllocationList());

				mav.addObject("shiftList", shiftListRes.getShiftList());
				mav.addObject("spStationList", spStationList.getSpStationList());
				mav.addObject("helperList", helperList.getGetEmpList());
				mav.addObject("employeeList", employeeList.getGetEmpList());
				mav.addObject("isEdit", 1);
			}

		} catch (Exception e) {
			System.out.println("Exception In Edit stationAllocation:" + e.getMessage());

			return mav;
		}
		return mav;
	}

	// -------------------------------------------------------------------------------------------------------
	// -------------------------------Delete StationAllocation---------------------------------
	@RequestMapping(value = "/deleteStationAllocation/{allocationId}", method = RequestMethod.GET)
	public String deleteStationAllocation(@PathVariable int allocationId) {

		ModelAndView mav = new ModelAndView("spProduction/configureStation");
		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("allocationId", allocationId);

			Info errorResponse = rest.postForObject(Constants.url + "/spProduction/deleteStationAllocation", map,
					Info.class);
			System.out.println(errorResponse.toString());

			if (errorResponse.getError()) {

				return "redirect:/configureStation";

			} else {
				return "redirect:/configureStation";

			}
		} catch (Exception e) {
			System.out.println("Exception In delete configuredStation :" + e.getMessage());

			return "redirect:/configureStation";

		}

	}

	// ----------------------------------------END------------------------------------------------------------

	@RequestMapping(value = "/instrumentAllocation", method = RequestMethod.GET)
	public ModelAndView instrumentAllocation(HttpServletRequest request, HttpServletResponse response) {

		 Constants.mainAct =20;
		 Constants.subAct =116;
		int isEdit = 0;
		ModelAndView model = new ModelAndView("spProduction/instrumentAlloc");
		try {
			RestTemplate restTemplate = new RestTemplate();

			SpStationList spStationList = restTemplate.getForObject(Constants.url + "/spProduction/getSpStationList",
					SpStationList.class);
			System.out.println("Response: " + spStationList.toString());
			MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();
			mvm.add("instType", instType);

			InstrumentList instrumentsList = restTemplate
					.postForObject(Constants.url + "/spProduction/getInstrumentList", mvm, InstrumentList.class);
			System.out.println("Response: " + instrumentsList.toString());

			InstAllocToStationList instAllocToStationList = restTemplate
					.getForObject(Constants.url + "/spProduction/getInstAllocToStList", InstAllocToStationList.class);

			model.addObject("spStationList", spStationList.getSpStationList());
			model.addObject("instrumentsList", instrumentsList.getInstrumentList());
			model.addObject("instAllocToStationList", instAllocToStationList.getInstAllocList());
			model.addObject("isEdit", isEdit);
		} catch (Exception e) {
			System.out.println("Exc In instrumentAllocation:" + e.getMessage());
		}
		return model;
	}

	// ------------------------------ADD StAllocation Process------------------------------------
	@RequestMapping(value = "/addInstrumentAlloc", method = RequestMethod.POST)
	public String addInstrumentAlloc(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("spProduction/instrumentAlloc");
		try {
			int instAllocId = 0;

			try {
				instAllocId = Integer.parseInt(request.getParameter("inst_alloc_id"));

				System.out.println("try : instAllocId" + instAllocId);
			} catch (Exception e) {
				instAllocId = 0;

				System.out.println("In Catch of Add StAllocation Process Exc:" + e.getMessage());

			}

			int stId = Integer.parseInt(request.getParameter("st_id"));
			System.out.println("stId" + stId);

			String[] instId = request.getParameterValues("inst_id");
			System.out.println("mId" + instId);

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < instId.length; i++) {
				sb = sb.append(instId[i] + ",");

			}
			String instruments = sb.toString();
			instruments = instruments.substring(0, instruments.length() - 1);

			System.out.println("instruments" + instruments);

			InstAllocToStation instAllocToStation = new InstAllocToStation();

			instAllocToStation.setInstAllocId(instAllocId);
			instAllocToStation.setInstId(instruments);
			instAllocToStation.setStId(stId);
			instAllocToStation.setDelStatus(0);
			System.out.println("InstAllocToStation:" + instAllocToStation.toString());

			RestTemplate restTemplate = new RestTemplate();

			ErrorMessage errorMessage = restTemplate.postForObject(
					Constants.url + "/spProduction/saveInstAllocToStation", instAllocToStation, ErrorMessage.class);
			System.out.println("Response: " + errorMessage.toString());

			if (errorMessage.getError() == true) {

				System.out.println("Error:True" + errorMessage.toString());
				return "redirect:/instrumentAllocation";
			} else {
				return "redirect:/instrumentAllocation";
			}
		} catch (Exception e) {
			System.out.println("Exception In instrumentAllocation Process:" + e.getMessage());
		}

		return "redirect:/instrumentAllocation";
	}

	// ----------------------------------------END-------------------------------------------------------------
	// -------------------------------Delete InstAllocToStation---------------------------------
	@RequestMapping(value = "/deleteInstAllocToStation/{instAllocId}", method = RequestMethod.GET)
	public String deleteInstrumentAlloc(@PathVariable int instAllocId) {

		ModelAndView mav = new ModelAndView("spProduction/instrumentAlloc");
		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("instAllocId", instAllocId);

			Info errorResponse = rest.postForObject(Constants.url + "/spProduction/deleteInstAllocToStation", map,
					Info.class);
			System.out.println(errorResponse.toString());

			if (errorResponse.getError()) {

				return "redirect:/instrumentAllocation";

			} else {
				return "redirect:/instrumentAllocation";

			}
		} catch (Exception e) {
			System.out.println("Exception In delete instrumentAllocation :" + e.getMessage());

			return "redirect:/instrumentAllocation";

		}

	}

	// ----------------------------------------END------------------------------------------------------------
	// ------------------------------updateInstAllocToStation-------------------------------------------------------------

	@RequestMapping(value = "/updateInstAllocToStation/{instAllocId}", method = RequestMethod.GET)
	public ModelAndView updateInstAllocToStation(@PathVariable int instAllocId) {

		ModelAndView mav = new ModelAndView("spProduction/instrumentAlloc");
		try {

			int isEdit = 1;

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("instAllocId", instAllocId);

			InstAllocToStation instAllocToStationRes = rest.postForObject(
					Constants.url + "/spProduction/getInstAllocToStation", map, InstAllocToStation.class);
			System.out.println(instAllocToStationRes.toString());

			MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();
			mvm.add("instType", instType);

			InstrumentList instrumentsList = rest.postForObject(Constants.url + "/spProduction/getInstrumentList", mvm,
					InstrumentList.class);
			System.out.println("Response: " + instrumentsList.toString());

			String instIds = instAllocToStationRes.getInstId();

			List<String> frPrevInstList = Arrays.asList(instIds.split("\\s*,\\s*"));

			List<Instrument> selectedInstrumentList = new ArrayList<Instrument>();
			List<Instrument> tempInstrumentList = instrumentsList.getInstrumentList();

			for (int i = 0; i < frPrevInstList.size(); i++) {
				for (int j = 0; j < instrumentsList.getInstrumentList().size(); j++) {
					if (Integer.parseInt(frPrevInstList.get(i)) == instrumentsList.getInstrumentList().get(j)
							.getInstrumentId()) {
						selectedInstrumentList.add(instrumentsList.getInstrumentList().get(j));
						tempInstrumentList.remove(j);

					}
				}

			}

			InstAllocToStationList instAllocToStationList = rest
					.getForObject(Constants.url + "/spProduction/getInstAllocToStList", InstAllocToStationList.class);

			SpStationList spStationList = rest.getForObject(Constants.url + "/spProduction/getSpStationList",
					SpStationList.class);
			System.out.println("Response: " + spStationList.toString());

			if (instAllocToStationRes != null) {

				mav.addObject("instAllocToStationList", instAllocToStationList.getInstAllocList());
				mav.addObject("spStationList", spStationList.getSpStationList());
				mav.addObject("instrumentsList", instrumentsList.getInstrumentList());
				mav.addObject("instAllocToStationRes", instAllocToStationRes);
				mav.addObject("selectedInstrumentList", selectedInstrumentList);
				mav.addObject("remInstrumentList", tempInstrumentList);
				mav.addObject("isEdit", isEdit);
			}

		} catch (Exception e) {
			System.out.println("Exception In updateInstAllocToStation:" + e.getMessage());

			return mav;
		}
		return mav;
	}

	// -------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/instrVerification", method = RequestMethod.GET)
	public ModelAndView instVerification(HttpServletRequest request, HttpServletResponse response) {

	    Constants.mainAct =20;
		Constants.subAct =101;
		int isEdit = 0;
		ModelAndView model = new ModelAndView("spProduction/InstVerification");
		try {
			RestTemplate restTemplate = new RestTemplate();
			instVerificationHeaderRes = new InstVerificationHeader();

			SpStationList spStationList = restTemplate.getForObject(Constants.url + "/spProduction/getSpStationList",
					SpStationList.class);
			System.out.println("Response: " + spStationList.toString());
			MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();
			mvm.add("instType", instType);

			InstrumentList instrumentsList = restTemplate
					.postForObject(Constants.url + "/spProduction/getInstrumentList", mvm, InstrumentList.class);
			System.out.println("Response: " + instrumentsList.toString());

			ShiftList shiftListRes = restTemplate.getForObject(Constants.url + "/spProduction/getShiftList",
					ShiftList.class);
			System.out.println("Response: " + shiftListRes.toString());

			model.addObject("shiftList", shiftListRes.getShiftList());
			model.addObject("spStationList", spStationList.getSpStationList());
			model.addObject("instrumentsList", instrumentsList.getInstrumentList());
			
		} catch (Exception e) {
			System.out.println("Exc In InstrumentVerification:" + e.getMessage());
		}
		return model;
	}

	// -------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/getInstrumentStatus", method = RequestMethod.GET)
	public @ResponseBody InstVerificationHeader getInstrumentStatus(HttpServletRequest request,
			HttpServletResponse response) {

		int stationId = Integer.parseInt(request.getParameter("stationId"));
		MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();
		mvm.add("stationId", stationId);

		RestTemplate restTemplate = new RestTemplate();

		instVerificationHeaderRes = restTemplate.postForObject(Constants.url + "/spProduction/getStationStatus", mvm,
				InstVerificationHeader.class);

		return instVerificationHeaderRes;
	}

	// ------------------------------ADD InstVerification Process------------------------------------
	@RequestMapping(value = "/addInstVerification", method = RequestMethod.POST)
	public String addInstVerification(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("spProduction/InstVerification");

		HttpSession session = request.getSession();
		UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");

		
		try {
			userId = userResponse.getUser().getId();
		} catch (Exception e) {
			userId = 0;
			System.out.println("User Id not Found Exc:");
		}
		System.out.println("userId:" + userId);

		int stationId = Integer.parseInt(request.getParameter("st_id"));
		System.out.println("Station Id:" + stationId);

		int shiftId = Integer.parseInt(request.getParameter("shift_id"));
		System.out.println("Shift Id:" + shiftId);

		int isStationAvail = Integer.parseInt(request.getParameter("isStationAvail"));
		System.out.println("isStationAvail" + isStationAvail);

		// int lastIndex=Integer.parseInt(request.getParameter("cnt"));
		try {
			InstVerificationHeader instVerificationHeader = new InstVerificationHeader();
			System.out.println("instVerificationHeaderRes" + instVerificationHeaderRes.toString());

			if (instVerificationHeaderRes.isError() == false) {
				System.out.println("instVerificationHeaderRes is Not null!! ");

				instVerificationHeader = instVerificationHeaderRes;
				System.out.println("instVerificationHeader:" + instVerificationHeader.toString());
				System.out.println(
						"instVerificationDetail:" + instVerificationHeader.getInstVerificationDetailList().toString());

				String[] selectedBeforeInstr = null;
				String[] selectedAfterInstr = null;
				
				if (isStationAvail == 1) {
					selectedBeforeInstr = request.getParameterValues("before");
					System.out.println("selectedBeforeInstr" + selectedBeforeInstr);

					instVerificationHeader.setStartTime(java.time.LocalTime.now().toString());
					instVerificationHeader.setStStatus(1);
				} else if (isStationAvail == 0) {
					selectedAfterInstr = request.getParameterValues("after");
					System.out.println("selectedAfterInstr" + selectedAfterInstr.toString());

					instVerificationHeader.setEndTime(java.time.LocalTime.now().toString());
					instVerificationHeader.setStStatus(2);
				}
				

				instVerificationHeader.setInstVerifDate(new Date());
				instVerificationHeader.setUserId(userId);
				instVerificationHeader.setStationId(stationId);
				instVerificationHeader.setShiftId(shiftId);
				instVerificationHeader.setDelStatus(0);

				if (isStationAvail == 1) {
					List<String> list = Arrays.asList(selectedBeforeInstr);
					System.out.println("selectedBeforeInstr List:--" + list.toString());

					for (int i = 0; i < instVerificationHeader.getInstVerificationDetailList().size(); i++) {
						for (int j = 0; j < list.size(); j++) {

							if (instVerificationHeader.getInstVerificationDetailList().get(i).getInstId() == Integer
									.parseInt(list.get(j))) {
								instVerificationHeader.getInstVerificationDetailList().get(i).setBef(1);

							}

						}
					}
				}
				if (isStationAvail == 0) {
					List<String> list = Arrays.asList(selectedAfterInstr);

					System.out.println("selectedAfterInstr List:--" + list.toString());
					for (int i = 0; i < instVerificationHeader.getInstVerificationDetailList().size(); i++) {
						for (int j = 0; j < list.size(); j++) {

							if (instVerificationHeader.getInstVerificationDetailList().get(i).getInstId() == Integer
									.parseInt(list.get(j))) {
								instVerificationHeader.getInstVerificationDetailList().get(i).setAft(1);

							}

						}
					}
				}
				System.out.println("instVerificationHeader1:--"
						+ instVerificationHeader.getInstVerificationDetailList().toString());

			} // if Header is not null
			else {
				System.out.println("instVerificationHeaderRes is null!! ");

				// if Header is null
				instVerificationHeader = new InstVerificationHeader();

				instVerificationHeader.setInstVerifHId(0);
				instVerificationHeader.setUserId(userId);
				instVerificationHeader.setDelStatus(0);
				instVerificationHeader.setInstVerifDate(new Date());
				instVerificationHeader.setShiftId(shiftId);
				instVerificationHeader.setStationId(stationId);
				instVerificationHeader.setStStatus(1);
				instVerificationHeader.setStartTime(java.time.LocalTime.now().toString());
				instVerificationHeader.setEndTime("00:00:00");
				System.out.println("instVerificationHeader" + instVerificationHeader.toString());

				List<InstVerificationDetail> instVerificationDetailList = new ArrayList<InstVerificationDetail>();

				String[] selectedBeforeInstr = null;
				String[] selectedAfterInstr = null;
				List<String> beforeInstrList = null;
				List<String> afterInstrList = null;
				
				if (isStationAvail == 1) {
					selectedBeforeInstr = request.getParameterValues("before");
					System.out.println("selectedBeforeInstr" + selectedBeforeInstr);
					beforeInstrList = Arrays.asList(selectedBeforeInstr);
					System.out.println("selectedBeforeInstr" + beforeInstrList.toString());

					instVerificationHeader.setStartTime(java.time.LocalTime.now().toString());
					instVerificationHeader.setStStatus(1);
				} else if (isStationAvail == 0) {
					selectedAfterInstr = request.getParameterValues("after");
					System.out.println("selectedAfterInstr" + selectedAfterInstr.toString());
					afterInstrList = Arrays.asList(selectedAfterInstr);
					System.out.println("selectedAfterInstr" + afterInstrList.toString());

					instVerificationHeader.setEndTime(java.time.LocalTime.now().toString());
					instVerificationHeader.setStStatus(2);
				}
				if (isStationAvail == 1) {

					for (int j = 0; j < beforeInstrList.size(); j++) {
						InstVerificationDetail iVerificationDetail = new InstVerificationDetail();

						iVerificationDetail.setInstVerifDId(0);
						iVerificationDetail.setInstVerifHId(0);
						iVerificationDetail.setInstId(Integer.parseInt(beforeInstrList.get(j)));
						iVerificationDetail.setBef(1);
						iVerificationDetail.setAft(0);

						instVerificationDetailList.add(iVerificationDetail);
					}
				}
				if (isStationAvail == 0) {

					for (int j = 0; j < afterInstrList.size(); j++) {
						InstVerificationDetail iVerificationDetail = new InstVerificationDetail();

						iVerificationDetail.setInstVerifDId(0);
						iVerificationDetail.setInstVerifHId(0);
						iVerificationDetail.setInstId(Integer.parseInt(afterInstrList.get(j)));
						iVerificationDetail.setBef(0);
						iVerificationDetail.setAft(1);

						instVerificationDetailList.add(iVerificationDetail);
					}
				}

				instVerificationHeader.setInstVerificationDetailList(instVerificationDetailList);
			}
			RestTemplate restTemplate = new RestTemplate();

			Info info = restTemplate.postForObject(Constants.url + "/spProduction/saveInstVerification",
					instVerificationHeader, Info.class);

			System.out.println("Response:" + info.toString());
		} catch (Exception e) {
			System.out.println("Exception In Saving Inst Verification!!" + e.getMessage());

		}
		return "redirect:/instrVerification";
	}

	// -------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/instrVerifDetails", method = RequestMethod.GET)
	public ModelAndView instrVerifDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("spProduction/instVerifHeader");
		
		    Constants.mainAct =20;
			Constants.subAct =117;
    try {
		 RestTemplate restTemplate = new RestTemplate();

		 List<GetInstVerifHeader> getInstVerifHeaderRes = restTemplate
				.getForObject(Constants.url + "/spProduction/getInstVerHeaders", List.class);

		 model.addObject("getInstVerifHeaderRes", getInstVerifHeaderRes);
        }
        catch(Exception e)
        {
    	  System.out.println("Exception in /instrVerifDetails");
        }
		return model;
	 }
	// --------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------
		@RequestMapping(value = "/showInstrVeriDetails/{instVerifHId}", method = RequestMethod.GET)
		public ModelAndView showInstVeriDetails(@PathVariable int instVerifHId,HttpServletRequest request, HttpServletResponse response) {

			ModelAndView model = new ModelAndView("spProduction/instVerifDetail");
			  
				
			System.out.println("instVerifHId"+instVerifHId);
			try
			{
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();
			mvm.add("instVerifId",instVerifHId);
			
			GetInstrVerifHeader getInstrVerifHeaderRes = restTemplate
					.postForObject(Constants.url + "/spProduction/getInstVerifHDetails",mvm, GetInstrVerifHeader.class);

			System.out.println("getInstrVerifHeaderRes"+getInstrVerifHeaderRes.getInstVerificationDetailList().toString());
			
			model.addObject("getInstrVerifDetailRes", getInstrVerifHeaderRes.getInstVerificationDetailList());
			model.addObject("getInstrVerifHeaderRes", getInstrVerifHeaderRes);

			}
			catch(Exception e)
			{
				System.out.println("Exc"+e.getMessage());
				e.printStackTrace();
			}
			return model;
		}
		// --------------------------------------------------------------------------------------------------------------
		// -------------------------------------------------------------------------------------------------------
		@RequestMapping(value = "/showCkAllocToStation", method = RequestMethod.GET)
		public ModelAndView showCkAllocToStation(HttpServletRequest request, HttpServletResponse response) {

			 Constants.mainAct =20;
			 Constants.subAct =100;
			ModelAndView model = new ModelAndView("spProduction/ckAllocToStation");
	    try {
			RestTemplate restTemplate = new RestTemplate();
			SpStationList spStationList = restTemplate.getForObject(Constants.url + "/spProduction/getSpStationList",
					SpStationList.class);
			System.out.println("Response: " + spStationList.toString());
			
			List<StationWiseCkCount> stWiseCkList=restTemplate.getForObject(Constants.url + "/spProduction/getStationwiseCkCount",List.class);
			System.out.println("Response: " + stWiseCkList.toString());

			
			ShiftList shiftListRes = restTemplate.getForObject(Constants.url + "/spProduction/getShiftList",
					ShiftList.class);
			System.out.println("Response: " + shiftListRes.toString());

			model.addObject("shiftList", shiftListRes.getShiftList());
			
			 stationSpCakeList=restTemplate.getForObject(Constants.url + "/spProduction/getStationSpCakeList",StationSpCakeList.class);
			
			model.addObject("stationSpCakeList", stationSpCakeList.getStationSpCakeList());
			model.addObject("spStationList", spStationList.getSpStationList());
			model.addObject("stWiseCkList", stWiseCkList);
	    }
	    catch(Exception e)
	    {
	    	System.out.println("Exception in /showCkAllocToStation");
	    }
			return model;
		}
		// --------------------------------------------------------------------------------------------------------------
		

		// -------------------------------------------------------------------------------------------------------
				@RequestMapping(value = "/addCkAllocToStation", method = RequestMethod.POST)
				public String addCkAllocToStation(HttpServletRequest request, HttpServletResponse response) {

					ModelAndView model = new ModelAndView("spProduction/ckAllocToStation");
			    try {
			    	HttpSession session = request.getSession();
					UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");

					try {
						userId = userResponse.getUser().getId();
					}
					catch(Exception e)
					{
						userId=0;
					}
			    	
			    	int stationId=Integer.parseInt(request.getParameter("st_id"));
			    	System.out.println("Station Id:"+stationId);
			    	
			    	String[] spId=request.getParameterValues("spck_id");
			    	System.out.println("spId"+spId.toString());
			        List<String> spIdList = Arrays.asList(spId);  
			        
			    	int shiftId=Integer.parseInt(request.getParameter("shift_id"));
			    	System.out.println("shiftId"+shiftId);

			    	
			    	RestTemplate restTemplate = new RestTemplate();

                    SpCkAllocHeader  spCkAllocHeader=new SpCkAllocHeader();
                    
                    spCkAllocHeader.setReqDate(new Date());//Current Date 
                    
                    spCkAllocHeader.setReqTime(java.time.LocalTime.now().toString());//Current Time 
                    
                    spCkAllocHeader.setReqUserId(userId);
                    
                    spCkAllocHeader.setShiftId(shiftId);
                    
                    spCkAllocHeader.setSpCkAllocId(0);
                    
                    spCkAllocHeader.setStationId(stationId);
                    
                    List<SpCkAllocDetail> spCkAllocDetailList=new ArrayList<SpCkAllocDetail>();
                    for(int i=0;i<spIdList.size();i++)
                    {
                    	SpCkAllocDetail spCkAllocDetail=new SpCkAllocDetail();
                    	
                    	spCkAllocDetail.setSpCkAllocDId(0);
                    	spCkAllocDetail.setSpCkAllocId(0);
                    	
                    	StationSpCake stationSpCake=new StationSpCake();
                    	System.out.println("Station Special Cake List :"+stationSpCakeList.getStationSpCakeList());
                    	System.out.println("Special Cake Ids:"+spIdList.toString());
                    	
                    	for(int j=0;j<stationSpCakeList.getStationSpCakeList().size();j++)
                    	{
                    		
                    		if(stationSpCakeList.getStationSpCakeList().get(j).getSpOrderNo()==Integer.parseInt(spIdList.get(i)))
                    		{
                    			stationSpCake=stationSpCakeList.getStationSpCakeList().get(j);
                    		    System.out.println("Special Cake:"+stationSpCake.toString());
                    		}
                    	}
                    	spCkAllocDetail.setSpCode(stationSpCake.getSpCode());
                    	spCkAllocDetail.setSpId(stationSpCake.getSpId());
                    	spCkAllocDetail.setSpName(stationSpCake.getSpName());
                    	spCkAllocDetail.settSpCakeId(Integer.parseInt(spIdList.get(i)));
                    	spCkAllocDetail.setPickupDate(new Date());//Current Date 
                    	spCkAllocDetail.setFrId(stationSpCake.getFrId());
                    	spCkAllocDetail.setStatus(0);
                    	spCkAllocDetail.setStartTime("00:00:00");
                    	spCkAllocDetail.setEndTime("00:00:00");
                    	spCkAllocDetail.setDelStatus(0);
                    	
                    	spCkAllocDetailList.add(spCkAllocDetail);
                    }
                    spCkAllocHeader.setSpCkAllocDetailList(spCkAllocDetailList);
           
                    Info info=restTemplate.postForObject(Constants.url + "/spProduction/saveSpCkAllocHeader",spCkAllocHeader,Info.class);
                    
                    System.out.println("addCkAllocToStation Response:"+info.toString());
			    } 
			    catch(Exception e)
			    {
			    	System.out.println("Exception In Saving Cake Alloc Data"+e.getMessage());
			    	
			    	e.printStackTrace();
			    }
				return "redirect:/showSpCksAllocToStation";
				}
	
				@RequestMapping(value = "/showSpCksAllocToStation", method = RequestMethod.GET)
				public ModelAndView showSpCksAllocToStation(HttpServletRequest request, HttpServletResponse response) {

					ModelAndView model = new ModelAndView("spProduction/stationwiseSpAllocList");
					 Constants.mainAct =20;
					 Constants.subAct =115;
					 try {
							RestTemplate restTemplate = new RestTemplate();
							SpStationList spStationList = restTemplate.getForObject(Constants.url + "/spProduction/getSpStationList",
									SpStationList.class);
							System.out.println("Response: " + spStationList.toString());
							
							List<String> stationId = new ArrayList<String>();
							String stIds=new String();
							
							for(int i=0;i<spStationList.getSpStationList().size();i++)
							{
								int stId=spStationList.getSpStationList().get(i).getStId();
								stationId.add(String.valueOf(stId));
								stIds=stIds+","+stId;
								
							}
							stIds=stIds.substring(1);
							
							System.out.println("Station Id:"+stIds);
							
							DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
							DateFormat dmyFr = new SimpleDateFormat("dd-MM-yyyy");

							String currentDate = df.format(new Date());
							String dmyCDate=dmyFr.format(new Date());
							System.out.println("currentDate :"+currentDate);

							
							MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
							map.add("stationId",stIds);
							map.add("fromDate",currentDate);
							map.add("toDate",currentDate);

					/*		ParameterizedTypeReference<List<GetAllocStationCk>> typeRef = new ParameterizedTypeReference<List<GetAllocStationCk>>() {
							};
							ResponseEntity<List<GetAllocStationCk>> responseEntity = restTemplate.exchange(Constants.url + "/spProduction/getAllocStationCk",
									HttpMethod.POST, new HttpEntity<>(map), typeRef);
							
							List<GetAllocStationCk> getAllocStationCkList =new ArrayList<GetAllocStationCk>();
							getAllocStationCkList= responseEntity.getBody();*/
							GetAllocStationCkList getAllocStationCkList=restTemplate.postForObject(Constants.url + "/spProduction/getAllocStationCk",map,GetAllocStationCkList.class);

							System.out.println("getAllocStationCkList"+getAllocStationCkList.toString());
							getAllocStationCkRes=new ArrayList<>();
							getAllocStationCkRes=getAllocStationCkList.getGetAllocStationCkList();
							
							model.addObject("getAllocStationCkList", getAllocStationCkList.getGetAllocStationCkList());
							model.addObject("spStationList",spStationList.getSpStationList());
							model.addObject("fromDate", dmyCDate);
							model.addObject("toDate", dmyCDate);
						    model.addObject("selStId",-1);

					 }
					 catch(Exception e)
					 {
						 System.out.println("Exce while showing /showSpCksAllocToStation"+e.getMessage());
						 
					 }
					return model;
				}
				
				
				
				@RequestMapping(value = "/searchStSpCkAlloc", method = RequestMethod.POST)
				public ModelAndView searchStSpCkAlloc(HttpServletRequest request, HttpServletResponse response) {

					ModelAndView model = new ModelAndView("spProduction/stationwiseSpAllocList");
					
					 try {
						 
						 int selStId=Integer.parseInt(request.getParameter("st_id"));
						 System.out.println("selStId :"+selStId);

						 String fromDate=request.getParameter("from_date");
						 System.out.println("fromDate"+fromDate);
						 
						 String toDate=request.getParameter("to_date");
						 System.out.println("toDate"+toDate);

						 
						    RestTemplate restTemplate = new RestTemplate();
							SpStationList spStationList = restTemplate.getForObject(Constants.url + "/spProduction/getSpStationList",
									SpStationList.class);
							System.out.println("Response: " + spStationList.toString());
							
							List<String> stationId = new ArrayList<String>();
							String stIds=new String();
							
							for(int i=0;i<spStationList.getSpStationList().size();i++)
							{
								int stId=spStationList.getSpStationList().get(i).getStId();
								stationId.add(String.valueOf(stId));
								stIds=stIds+","+stId;
								
							}
							stIds=stIds.substring(1);
							
							System.out.println("Station Id:"+stIds);
							DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
							DateFormat dmyFr = new SimpleDateFormat("dd-MM-yyyy");

							
							MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
							if(selStId==-1)
							{
							 map.add("stationId",stIds);
							}
							else
							{
							 map.add("stationId",selStId);

							}
							Date fmtFrDate=dmyFr.parse(fromDate);
							Date fmtToDate=dmyFr.parse(toDate);

							map.add("fromDate",df.format(fmtFrDate));
							map.add("toDate",df.format(fmtToDate));

							System.out.println("fmtFrDate"+df.format(fmtFrDate));
							/*ParameterizedTypeReference<List<GetAllocStationCk>> typeRef = new ParameterizedTypeReference<List<GetAllocStationCk>>() {
							};
							ResponseEntity<List<GetAllocStationCk>> responseEntity = restTemplate.exchange(Constants.url + "/spProduction/getAllocStationCk",
									HttpMethod.POST, new HttpEntity<>(map), typeRef);
							
							getAllocStationCkList =new ArrayList<GetAllocStationCk>();
							getAllocStationCkList= responseEntity.getBody();
							*/
							GetAllocStationCkList getAllocStationCkList=restTemplate.postForObject(Constants.url + "/spProduction/getAllocStationCk",map,GetAllocStationCkList.class);

							System.out.println("getAllocStationCkList"+getAllocStationCkList.toString());
							getAllocStationCkRes=new ArrayList<>();
							getAllocStationCkRes=getAllocStationCkList.getGetAllocStationCkList();
							
							model.addObject("getAllocStationCkList", getAllocStationCkList.getGetAllocStationCkList());
							
							System.out.println("getAllocStationCkList"+getAllocStationCkList.toString());
							
							model.addObject("spStationList",spStationList.getSpStationList());
						    model.addObject("fromDate", dmyFr.format(fmtFrDate));
						    model.addObject("toDate",dmyFr.format(fmtToDate));
						    model.addObject("selStId", selStId);
					 }
					 catch(Exception e)
					 {
						 System.out.println("Exce in /searchStSpCkAlloc"+e.getMessage());

					 }
				return model;

				}
				
				// -------------------------------------------------------------------------------------------------------
				@RequestMapping(value = "/getSpDetailForBom/{spId}/{spCkAllocDId}", method = RequestMethod.GET)
				public ModelAndView getSpDetailForBom(@PathVariable("spId")int spId,@PathVariable("spCkAllocDId")int spCkAllocDId,HttpServletRequest request, HttpServletResponse response) {

					ModelAndView model = new ModelAndView("spProduction/addSpBom");
					
					RestTemplate restTemplate = new RestTemplate();

					try {
					MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
					map.add("spId",spId);
					
					GetSpDetailForBomList getSpDtailForBom=restTemplate.postForObject(Constants.url + "/getSpDetailForBom", map, GetSpDetailForBomList.class);
					
					System.out.println("GetSpDetailForBomList"+getSpDtailForBom.toString());
					
					
					getSpDetailForBomList=getSpDtailForBom.getGetSpDetailForBomList();
					
					model.addObject("getSpDtailForBom",getSpDtailForBom.getGetSpDetailForBomList());
					model.addObject("spId",spId);
					model.addObject("spCkAllocDId",spCkAllocDId);

					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					return model;
				}
				//-------------------------------------------------------------------------------------------------------
				//insertSpBom getAllocStationCkList

				@RequestMapping(value = "/insertSpBom", method = RequestMethod.POST)
				public String insertBom(HttpServletRequest request, HttpServletResponse response) {

					ModelAndView mav = new ModelAndView("spProduction/addSpBom");
					try {
						int localSpId = 0;
					HttpSession session=request.getSession();
					UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");
					
					int spId = Integer.parseInt(request.getParameter("spId"));
					System.out.println("spId"+spId);
					int spCkAllocDId= Integer.parseInt(request.getParameter("spCkAllocDId"));
					System.out.println("spCkAllocDId"+spCkAllocDId);

					RestTemplate restTemplate = new RestTemplate();
					MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
					
					//int deptId=userResponse.getUser().getDeptId();

					int userId=userResponse.getUser().getId();
					
					String fromSettingKey = new String();

					fromSettingKey = "sp_cake_dept";

					map.add("settingKeyList", fromSettingKey);
					
					// web Service to get Dept Name And Dept Id for bom toDept and toDeptId
					
					FrItemStockConfigureList fromSettingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
							FrItemStockConfigureList.class);
					System.out.println("fromSettingList"+fromSettingList.toString());

					
					String toSettingKey = new String();

					toSettingKey = "BMS";

					map.add("settingKeyList", toSettingKey);
					
					// web Service to get Dept Name And Dept Id for bom toDept and toDeptId
					
					FrItemStockConfigureList toSettingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
							FrItemStockConfigureList.class);
					
					System.out.println("toSettingList"+toSettingList.toString());
					System.out.println("new Field Dept Id = "+userResponse.getUser().getDeptId());
					
					BillOfMaterialHeader billOfMaterialHeader = new BillOfMaterialHeader();

					
			
					for(int i=0;i<getAllocStationCkRes.size();i++)
					{
						if(spId==getAllocStationCkRes.get(i).getSpId())
						{
							localSpId=getAllocStationCkRes.get(i).getSpId();
						int fromDeptId=fromSettingList.getFrItemStockConfigure().get(0).getSettingValue();
						String fromDeptName=fromSettingList.getFrItemStockConfigure().get(0).getSettingKey();
						
						int toDeptId=toSettingList.getFrItemStockConfigure().get(0).getSettingValue();
						String toDeptName=toSettingList.getFrItemStockConfigure().get(0).getSettingKey();
						
						Date date = new Date();


						billOfMaterialHeader.setApprovedDate(date);
						billOfMaterialHeader.setApprovedUserId(0);
						billOfMaterialHeader.setDelStatus(0);
						billOfMaterialHeader.setFromDeptId(fromDeptId);
						

                        DateFormat parser = new SimpleDateFormat("dd-MM-yyyy"); 
                        Date frDate = (Date) parser.parse(getAllocStationCkRes.get(i).getReqDate());

                        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
                        frDate=formatter.parse(formatter.format(frDate));
                        System.out.println(frDate);

						billOfMaterialHeader.setProductionDate(frDate);//
						billOfMaterialHeader.setProductionId(getAllocStationCkRes.get(i).gettSpCakeId());//
						billOfMaterialHeader.setReqDate(date);
						billOfMaterialHeader.setSenderUserid(userId);
						billOfMaterialHeader.setStatus(0);
						billOfMaterialHeader.setToDeptId(toDeptId);
						billOfMaterialHeader.setToDeptName(toDeptName);
						billOfMaterialHeader.setFromDeptName(fromDeptName);

						billOfMaterialHeader.setRejApproveDate(date);
						billOfMaterialHeader.setRejApproveUserId(0);
						billOfMaterialHeader.setRejDate(date);
						billOfMaterialHeader.setRejUserId(0);
						
						billOfMaterialHeader.setIsManual(0);
						billOfMaterialHeader.setIsProduction(4);
						billOfMaterialHeader.setIsPlan(0);
						
						billOfMaterialHeader.setIsPlan(0);
						}
					}
						List<BillOfMaterialDetailed> bomDetailList = new ArrayList<BillOfMaterialDetailed>();
						BillOfMaterialDetailed bomDetail = null;
						for (int i = 0; i < getSpDetailForBomList.size(); i++) {

							String editQty = request.getParameter("editQty" + i);
							bomDetail = new BillOfMaterialDetailed();

							System.out.println("editQty " + editQty);

							bomDetail.setDelStatus(0);
							bomDetail.setRmId(getSpDetailForBomList.get(i).getRmId());
							bomDetail.setRmIssueQty(0.0F);
							bomDetail.setUom(getSpDetailForBomList.get(i).getUom());
							bomDetail.setRmType(getSpDetailForBomList.get(i).getRmType());
							bomDetail.setRmReqQty(Integer.parseInt(editQty));
							bomDetail.setRmName(getSpDetailForBomList.get(i).getRmName());
							
							bomDetail.setRejectedQty(0);
							bomDetail.setAutoRmReqQty(getSpDetailForBomList.get(i).getTotal());
							
							bomDetail.setReturnQty(0);
							bomDetailList.add(bomDetail);

						}
						
						System.out.println("bom detail List " + bomDetailList.toString());
						billOfMaterialHeader.setBillOfMaterialDetailed(bomDetailList);

						System.out.println(" insert List " + billOfMaterialHeader.toString());
						int prodId=billOfMaterialHeader.getProductionId();
						
						Info info = restTemplate.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);
						System.out.println(" Info " + info.toString());

						mav.addObject("getSpDtailForBom",getSpDetailForBomList);
                        mav.addObject("spId", localSpId);
                        
                        map = new LinkedMultiValueMap<String, Object>();
    					map.add("spCkAllocDId",spCkAllocDId);
    					
                        
                        Info updateRes = restTemplate.postForObject(Constants.url + "updateIsBomOfSpAllocn", map, Info.class);
                        System.out.println("Is Bom Updated Res"+updateRes.toString());
                        
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
					return "redirect:/showSpCksAllocToStation";    
					
					
				}
}

				
