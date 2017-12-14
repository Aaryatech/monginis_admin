package com.ats.adminpanel.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.RawMaterial.RmItemCategory;
import com.ats.adminpanel.model.item.ErrorMessage;
import com.ats.adminpanel.model.spprod.Employee;
import com.ats.adminpanel.model.spprod.EmployeeList;
import com.ats.adminpanel.model.spprod.Instrument;
import com.ats.adminpanel.model.spprod.InstrumentList;
import com.ats.adminpanel.model.spprod.MDept;
import com.ats.adminpanel.model.spprod.MDeptList;
import com.ats.adminpanel.model.spprod.SpStation;
import com.ats.adminpanel.model.spprod.SpStationList;

@Controller
public class SpProductionController {

	@RequestMapping(value = "/showAddEmployee", method = RequestMethod.GET)
	public ModelAndView showAddEmployee(HttpServletRequest request, HttpServletResponse response) {

		 Constants.mainAct=19;
		 Constants.subAct=191;

		ModelAndView model = new ModelAndView("spProduction/addEmployee");

		RestTemplate restTemplate = new RestTemplate();

		MDeptList mDeptList = restTemplate.getForObject(Constants.url + "/spProduction/mDeptList", MDeptList.class);
		System.out.println("Response: " + mDeptList.toString());

		EmployeeList employeeList = restTemplate.getForObject(Constants.url + "/spProduction/getEmployeeList",
				EmployeeList.class);
		System.out.println("Response: " + employeeList.toString());

		model.addObject("mDeptList", mDeptList.getList());
		model.addObject("employeeList", employeeList.getEmployeeList());
		return model;
	}
	
	@RequestMapping(value = "/showAddDepartment", method = RequestMethod.GET)
	public ModelAndView showAddDepartment(HttpServletRequest request, HttpServletResponse response) {

		 Constants.mainAct=2;
		 Constants.subAct=26;

		ModelAndView model = new ModelAndView("masters/department");

		RestTemplate restTemplate = new RestTemplate();

		MDeptList mDeptList = restTemplate.getForObject(Constants.url + "/spProduction/mDeptList", MDeptList.class);
		System.out.println("Response: " + mDeptList.toString());

		model.addObject("deptList", mDeptList.getList());
		return model;
	}
	@RequestMapping(value = "/showAddStation", method = RequestMethod.GET)
	public ModelAndView showAddStation(HttpServletRequest request, HttpServletResponse response) {

		 Constants.mainAct=19;
		 Constants.subAct=192;

		ModelAndView model = new ModelAndView("spProduction/addSpStation");
		RestTemplate restTemplate = new RestTemplate();

		MDeptList mDeptList = restTemplate.getForObject(Constants.url + "/spProduction/mDeptList", MDeptList.class);
		System.out.println("Response: " + mDeptList.toString());

		SpStationList spStationList = restTemplate.getForObject(Constants.url + "/spProduction/getSpStationList",
				SpStationList.class);
		System.out.println("Response: " + spStationList.toString());

		model.addObject("mDeptList", mDeptList.getList());
		model.addObject("spStationList", spStationList.getSpStationList());
		model.addObject("spStation", new SpStation());

		return model;
	}

	@RequestMapping(value = "/showAddInstrument", method = RequestMethod.GET)
	public ModelAndView showAddInstrument(HttpServletRequest request, HttpServletResponse response) {


		 Constants.mainAct=19;
		 Constants.subAct=193;

		ModelAndView model = new ModelAndView("spProduction/addInstrument");

		return model;
	}

	@RequestMapping(value = "/showAllInstrument", method = RequestMethod.GET)
	public ModelAndView showAllInstrument(HttpServletRequest request, HttpServletResponse response) {


		 Constants.mainAct=19;
		 Constants.subAct=194;

		ModelAndView model = new ModelAndView("spProduction/instrumentsList");
		RestTemplate restTemplate = new RestTemplate();

		InstrumentList instrumentsList = restTemplate.getForObject(Constants.url + "/spProduction/getInstrumentList",
				InstrumentList.class);
		System.out.println("Response: " + instrumentsList.toString());

		model.addObject("instrumentsList", instrumentsList.getInstrumentList());

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
			
			int stId=0;
			
		try {
				 stId = Integer.parseInt(request.getParameter("st_id"));

			}
			catch(Exception e)
			{
			  stId=0;
           	  System.out.println("In Catch of Add Station Process Exc:"+e.getMessage());

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

	// ----------------------------------------END-------------------------------------------------------------
	// ------------------------------ADD Employee Process------------------------------------
	@RequestMapping(value = "/addEmpProcess", method = RequestMethod.POST)
	public String addEmployee(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("spProduction/addEmployee");
		try {
             int empId=0;
             
             try {
             empId = Integer.parseInt(request.getParameter("emp_id"));
             
             }
             catch(Exception e)
             {
            	 empId=0;

            	 System.out.println("In Catch of Add Emp Process Exc:"+e.getMessage());

             }
			
			String empName = request.getParameter("emp_name");

			int deptId = Integer.parseInt(request.getParameter("dept_id"));

			int isUsed = Integer.parseInt(request.getParameter("is_used"));

			Employee emp = new Employee();
			emp.setEmpId(empId);
			emp.setDeptId(deptId);
			emp.setEmpName(empName);
			emp.setIsUsed(isUsed);
			emp.setDelStatus(0);

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
             int instrumentId=0;
			
             try {
            	 instrumentId = Integer.parseInt(request.getParameter("instrument_id"));

             }
             catch(Exception e)
             {
            	 instrumentId=0;
     			System.out.println("Exception In add Instrument:" + e.getMessage());

             }
			String instName = request.getParameter("instrument_name");

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

			EmployeeList employeeList = rest.getForObject(Constants.url + "/spProduction/getEmployeeList",
					EmployeeList.class);
			System.out.println("Response: " + employeeList.toString());

			if (emp.isError()) {

			       return mav;

			} else {
				mav.addObject("mDeptList", mDeptList.getList());
				mav.addObject("employeeList", employeeList.getEmployeeList());
				mav.addObject("emp", emp);
			}
		} catch (Exception e) {
			System.out.println("Exception In Edit Emp:" + e.getMessage());

		       return mav;

		}
       return mav;
	}
	//-----------------------------------------------------------------------------------------------------------
	// ------------------------------Edit Instrument-------------------------------------------------------------

		@RequestMapping(value = "/updateInstrument/{instrumentId}", method = RequestMethod.GET)
		public ModelAndView updateInstrument(@PathVariable int instrumentId) {

			ModelAndView mav = new ModelAndView("spProduction/editInstrument");
			try {

				RestTemplate rest = new RestTemplate();
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("instrumentId", instrumentId);

				Instrument instrument = rest.postForObject(Constants.url + "/spProduction/getInstrument", map, Instrument.class);
				System.out.println(instrument.toString());

				if (instrument.isError()) {

				       return mav;

				} else {

					mav.addObject("instrument", instrument);
				}
			} catch (Exception e) {
				System.out.println("Exception In Edit instrument:" + e.getMessage());

			       return mav;
			}
	       return mav;
		}
		//----------------------------------------------------------------------------------------------
		// ------------------------------Edit SpStation-------------------------------------------------------------

		@RequestMapping(value = "/updateSpStation/{stId}", method = RequestMethod.GET)
		public ModelAndView updateSpStation(@PathVariable int stId) {

			ModelAndView mav = new ModelAndView("spProduction/addSpStation");
			try {

				RestTemplate rest = new RestTemplate();
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("stId", stId);

				SpStation spStation = rest.postForObject(Constants.url + "/spProduction/getSpStation", map, SpStation.class);
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
		//-------------------------------------------------------------------------------------------
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


						if (department!=null) {

							mav.addObject("deptList", mDeptList.getList());
							mav.addObject("dept", department);

						}
					
					} catch (Exception e) {
						System.out.println("Exception In Edit Dept:" + e.getMessage());

					       return mav;
					}
			       return mav;
				}
		//------------------------------------------------------------------------------------
		// ------------------------------ADD Department Process------------------------------------
				@RequestMapping(value = "/addDeptProcess", method = RequestMethod.POST)
				public String addDeptProcess(HttpServletRequest request, HttpServletResponse response) {

					ModelAndView model = new ModelAndView("masters/department");
					try {
			             int deptId=0;
			             
			             try {
			            	 deptId = Integer.parseInt(request.getParameter("dept_id"));
			             
			             }
			             catch(Exception e)
			             {
			            	 deptId=0;

			            	 System.out.println("In Catch of Add Dept Process Exc:"+e.getMessage());

			             }
						
						String deptName = request.getParameter("dept_name");

						String deptCode = request.getParameter("dept_code");

						int isActive = Integer.parseInt(request.getParameter("is_active"));

						MDept mDept=new MDept();
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
}
