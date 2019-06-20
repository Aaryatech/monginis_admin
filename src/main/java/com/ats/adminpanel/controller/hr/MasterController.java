package com.ats.adminpanel.controller.hr;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.commons.VpsImageUpload;
import com.ats.adminpanel.model.hr.Info;
import com.ats.adminpanel.model.hr.LocDisplay;
import com.ats.adminpanel.model.hr.Location;
import com.ats.adminpanel.model.hr.Salary;
import com.ats.adminpanel.model.hr.Settings;
import com.ats.adminpanel.model.ExportToExcel;
import com.ats.adminpanel.model.hr.Company;
import com.ats.adminpanel.model.hr.EmpDept;
import com.ats.adminpanel.model.hr.EmpDeptDisplay;
import com.ats.adminpanel.model.hr.EmpDisplay;
import com.ats.adminpanel.model.hr.EmpGatepassDisplay;
import com.ats.adminpanel.model.hr.EmpType;
import com.ats.adminpanel.model.hr.EmpWiseVisitorReport;
import com.ats.adminpanel.model.hr.Employee;
import com.ats.adminpanel.model.hr.EmployeeCategory;
import com.ats.adminpanel.model.hr.EmployeeCategoryDisplay;
import com.ats.adminpanel.model.login.UserResponse;

@Controller
public class MasterController {

	// ---------------------------------COMPANY--------------------------------------------------------

	@RequestMapping(value = "/showAddCompany", method = RequestMethod.GET)
	public ModelAndView showAddEmployee(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("hrEmployee/addCompany");
		try {
			RestTemplate restTemplate = new RestTemplate();

			Company[] cmpArray = restTemplate.getForObject(Constants.security_app_url + "master/allCompany",
					Company[].class);
			List<Company> companyList = new ArrayList<>(Arrays.asList(cmpArray));

			System.out.println("Response: " + companyList.toString());
			model.addObject("companyList", companyList);
			model.addObject("url", Constants.APP_IMAGE_URL);

		} catch (Exception e) {
			System.out.println("Exc In showAddCompany:" + e.getMessage());
		}
		return model;
	}

	// ------------ADD COMPANY------------------------------------

	@RequestMapping(value = "/addCompany", method = RequestMethod.POST)
	public String addCompany(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("logo") List<MultipartFile> file) {

		try {
			int cmpId = 0;

			try {
				cmpId = Integer.parseInt(request.getParameter("cmp_id"));

			} catch (Exception e) {
				cmpId = 0;

				System.out.println("In Catch of Add Company Exc:" + e.getMessage());

			}

			String cmpName = request.getParameter("cmp_name");
			String cmpDesc = request.getParameter("cmp_desc");
			String imageName = request.getParameter("logo");

			if (!file.get(0).getOriginalFilename().equalsIgnoreCase("")) {
				imageName = null;

				VpsImageUpload upload = new VpsImageUpload();

				Calendar cal1 = Calendar.getInstance();
				long lo = cal1.getTimeInMillis();

				String curTimeStamp = String.valueOf(lo);

				imageName = curTimeStamp + "_" + file.get(0).getOriginalFilename().replace(' ', '_');

				try {

					upload.saveUploadedFiles(file, Constants.APP_IMAGE_TYPE, imageName);
					System.out.println("upload method called " + file.toString());

				} catch (IOException e) {

					System.out.println("Exce in File Upload In Item Insert " + e.getMessage());
					e.printStackTrace();
				}

			}

			if (imageName == null) {
				imageName = "na";
			}

			System.out.println("IMAGE NAME----------------- " + imageName);

			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");

			int userId = userResponse.getUser().getId();

			SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();

			Company cmp = new Company(cmpId, cmpName, imageName, cmpDesc, 1, 1, userId,
					"" + dtFormat.format(cal.getTimeInMillis()));

			RestTemplate restTemplate = new RestTemplate();

			Company company = restTemplate.postForObject(Constants.security_app_url + "master/saveCompany", cmp,
					Company.class);
			System.out.println("Response: " + company.toString());

			return "redirect:/showAddCompany";

		} catch (Exception e) {
			System.out.println("Exception In Add Employee Process:" + e.getMessage());
			e.printStackTrace();
		}

		return "redirect:/showAddCompany";
	}

	// ---------------UPDATE COMPANY------------------------
	@RequestMapping(value = "/updateCompany/{cmpId}", method = RequestMethod.GET)
	public ModelAndView updateCompany(@PathVariable int cmpId) {

		ModelAndView mav = new ModelAndView("hrEmployee/addCompany");
		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("companyId", cmpId);

			Company company = rest.postForObject(Constants.security_app_url + "/master/getCompanyById", map,
					Company.class);
			System.out.println(company.toString());

			Company[] cmpArray = rest.getForObject(Constants.security_app_url + "master/allCompany", Company[].class);
			List<Company> companyList = new ArrayList<>(Arrays.asList(cmpArray));

			System.out.println("Response: " + companyList.toString());
			mav.addObject("companyList", companyList);
			mav.addObject("cmp", company);
			mav.addObject("url", Constants.APP_IMAGE_URL);

		} catch (Exception e) {
			System.out.println("Exception In Edit Company:" + e.getMessage());

			return mav;

		}
		return mav;
	}

	// ---------------DELETE COMPANY--------------------------
	@RequestMapping(value = "/deleteCompany/{cmpId}", method = RequestMethod.GET)
	public String deleteCompany(@PathVariable int cmpId) {

		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("companyId", cmpId);

			Info errorResponse = rest.postForObject(Constants.security_app_url + "/master/deleteCompany", map,
					Info.class);
			System.out.println(errorResponse.toString());

			if (errorResponse.isError()) {

				return "redirect:/showAddCompany";

			} else {
				return "redirect:/showAddCompany";

			}
		} catch (Exception e) {
			System.out.println("Exception In delete Company:" + e.getMessage());

			return "redirect:/showAddCompany";

		}

	}

	// -----------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------EMPLOYEE CATEGORY
	// (DESIGNATION)------------------------------------------------

	@RequestMapping(value = "/showAddEmpCategory", method = RequestMethod.GET)
	public ModelAndView showAddEmpCategory(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("hrEmployee/addEmpCategory");
		try {
			RestTemplate restTemplate = new RestTemplate();

			EmployeeCategoryDisplay[] catArray = restTemplate.getForObject(
					Constants.security_app_url + "master/allEmployeeCategoryList", EmployeeCategoryDisplay[].class);
			List<EmployeeCategoryDisplay> catList = new ArrayList<>(Arrays.asList(catArray));

			Company[] cmpArray = restTemplate.getForObject(Constants.security_app_url + "master/allCompany",
					Company[].class);
			List<Company> companyList = new ArrayList<>(Arrays.asList(cmpArray));

			System.out.println("Response: " + companyList.toString());
			System.out.println("Response: " + catList.toString());

			model.addObject("catList", catList);
			model.addObject("compList", companyList);
		} catch (Exception e) {
			System.out.println("Exc In showAddCategory:" + e.getMessage());
		}
		return model;
	}

	// ------------ADD CATEGORY------------------------------------

	@RequestMapping(value = "/addEmpCategory", method = RequestMethod.POST)
	public String addCategory(HttpServletRequest request, HttpServletResponse response) {

		try {
			int empCatId = 0;

			try {
				empCatId = Integer.parseInt(request.getParameter("cat_id"));

			} catch (Exception e) {
				empCatId = 0;

				System.out.println("In Catch of Add Category Exc:" + e.getMessage());

			}

			int compId = Integer.parseInt(request.getParameter("comp_id"));

			String catName = request.getParameter("cat_name");
			String catshortName = request.getParameter("cat_short_name");
			String catDesc = request.getParameter("cat_desc");

			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");

			int userId = userResponse.getUser().getId();

			SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();

			EmployeeCategory cat = new EmployeeCategory(empCatId, compId, catName, catshortName, catDesc, 1, 1, userId,
					"" + dtFormat.format(cal.getTimeInMillis()));

			RestTemplate restTemplate = new RestTemplate();

			EmployeeCategory empCat = restTemplate.postForObject(
					Constants.security_app_url + "master/saveEmployeeCategory", cat, EmployeeCategory.class);
			System.out.println("Response: " + empCat.toString());

			return "redirect:/showAddEmpCategory";

		} catch (Exception e) {
			System.out.println("Exception In Add Category Process:" + e.getMessage());
		}

		return "redirect:/showAddEmpCategory";
	}

	// ---------------UPDATE CATEGORY------------------------
	@RequestMapping(value = "/updateCategory/{empCatId}", method = RequestMethod.GET)
	public ModelAndView updateCategory(@PathVariable int empCatId) {

		ModelAndView mav = new ModelAndView("hrEmployee/addEmpCategory");
		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("empCatId", empCatId);

			EmployeeCategory empCat = rest.postForObject(Constants.security_app_url + "/master/getEmployeeCategoryById",
					map, EmployeeCategory.class);

			System.out.println(empCat.toString());

			EmployeeCategoryDisplay[] catArray = rest.getForObject(
					Constants.security_app_url + "master/allEmployeeCategoryList", EmployeeCategoryDisplay[].class);
			List<EmployeeCategoryDisplay> catList = new ArrayList<>(Arrays.asList(catArray));

			Company[] cmpArray = rest.getForObject(Constants.security_app_url + "master/allCompany", Company[].class);
			List<Company> companyList = new ArrayList<>(Arrays.asList(cmpArray));

			System.out.println("Response: " + companyList.toString());
			System.out.println("Response: " + catList.toString());

			mav.addObject("catList", catList);
			mav.addObject("compList", companyList);
			mav.addObject("empCat", empCat);

		} catch (Exception e) {
			System.out.println("Exception In Edit Category:" + e.getMessage());

			return mav;

		}
		return mav;
	}

	// ---------------DELETE CATEGORY--------------------------
	@RequestMapping(value = "/deleteCategory/{empCatId}", method = RequestMethod.GET)
	public String deleteCategory(@PathVariable int empCatId) {

		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("empCatId", empCatId);

			Info errorResponse = rest.postForObject(Constants.security_app_url + "/master/deleteEmployeeCategory", map,
					Info.class);
			System.out.println(errorResponse.toString());

			if (errorResponse.isError()) {

				return "redirect:/showAddEmpCategory";

			} else {
				return "redirect:/showAddEmpCategory";

			}
		} catch (Exception e) {
			System.out.println("Exception In delete Category:" + e.getMessage());

			return "redirect:/showAddEmpCategory";

		}

	}

	// -----------------------------------------------------------------------------------------------------------------------
	// ----------------------------------EMPLOYEE
	// DEPARTMENT---------------------------------------------------------------------

	@RequestMapping(value = "/showAddHrEmpDept", method = RequestMethod.GET)
	public ModelAndView showAddEmpDept(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("hrEmployee/addEmpDepartment");
		try {
			RestTemplate restTemplate = new RestTemplate();

			EmpDeptDisplay[] deptArray = restTemplate.getForObject(
					Constants.security_app_url + "master/allEmployeeDepartmentList", EmpDeptDisplay[].class);
			List<EmpDeptDisplay> deptList = new ArrayList<>(Arrays.asList(deptArray));

			Company[] cmpArray = restTemplate.getForObject(Constants.security_app_url + "master/allCompany",
					Company[].class);
			List<Company> companyList = new ArrayList<>(Arrays.asList(cmpArray));

			System.out.println("Response: " + companyList.toString());
			System.out.println("Response: " + deptList.toString());

			model.addObject("deptList", deptList);
			model.addObject("compList", companyList);
			model.addObject("url", Constants.APP_IMAGE_URL);

		} catch (Exception e) {
			System.out.println("Exc In showAddDept:" + e.getMessage());
		}
		return model;
	}

	// ------------ADD DEPARTMENT------------------------------------

	@RequestMapping(value = "/addHrEmpDept", method = RequestMethod.POST)
	public String addEmpDept(HttpServletRequest request, HttpServletResponse response) {

		try {
			int deptId = 0;

			try {
				deptId = Integer.parseInt(request.getParameter("dept_id"));

			} catch (Exception e) {
				deptId = 0;

				System.out.println("In Catch of Add DEPT Exc:" + e.getMessage());

			}

			int compId = Integer.parseInt(request.getParameter("comp_id"));

			String deptName = request.getParameter("dept_name");
			String deptshortName = request.getParameter("dept_short_name");
			String deptDesc = request.getParameter("dept_desc");

			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");

			int userId = userResponse.getUser().getId();

			SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();

			EmpDept dept = new EmpDept(deptId, compId, deptName, deptshortName, deptDesc, 1, 1, userId,
					"" + dtFormat.format(cal.getTimeInMillis()));

			RestTemplate restTemplate = new RestTemplate();

			EmpDept saveDept = restTemplate.postForObject(Constants.security_app_url + "master/saveEmployeeDepartment",
					dept, EmpDept.class);

			System.out.println("Response: " + saveDept.toString());

			return "redirect:/showAddHrEmpDept";

		} catch (Exception e) {
			System.out.println("Exception In Add Dept Process:" + e.getMessage());
		}

		return "redirect:/showAddHrEmpDept";
	}

	// ---------------UPDATE DEPARTMENT------------------------
	@RequestMapping(value = "/updateHrDept/{deptId}", method = RequestMethod.GET)
	public ModelAndView updateDept(@PathVariable int deptId) {

		ModelAndView mav = new ModelAndView("hrEmployee/addEmpDepartment");
		try {

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("empDeptId", deptId);

			EmpDept dept = restTemplate.postForObject(Constants.security_app_url + "/master/getEmployeeDepartmentById",
					map, EmpDept.class);

			System.out.println(dept.toString());

			EmpDeptDisplay[] deptArray = restTemplate.getForObject(
					Constants.security_app_url + "master/allEmployeeDepartmentList", EmpDeptDisplay[].class);
			List<EmpDeptDisplay> deptList = new ArrayList<>(Arrays.asList(deptArray));

			Company[] cmpArray = restTemplate.getForObject(Constants.security_app_url + "master/allCompany",
					Company[].class);
			List<Company> companyList = new ArrayList<>(Arrays.asList(cmpArray));

			System.out.println("Response: " + companyList.toString());
			System.out.println("Response: " + deptList.toString());

			mav.addObject("deptList", deptList);
			mav.addObject("compList", companyList);
			mav.addObject("dept", dept);
			mav.addObject("url", Constants.APP_IMAGE_URL);

		} catch (Exception e) {
			System.out.println("Exception In Edit Department:" + e.getMessage());

			return mav;

		}
		return mav;
	}

	// ---------------DELETE DEPARTMENT--------------------------
	@RequestMapping(value = "/deleteHrDept/{deptId}", method = RequestMethod.GET)
	public String deleteDept(@PathVariable int deptId) {

		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("empDeptId", deptId);

			Info errorResponse = rest.postForObject(Constants.security_app_url + "/master/deleteEmployeeDepartment",
					map, Info.class);
			System.out.println(errorResponse.toString());

			if (errorResponse.isError()) {

				return "redirect:/showAddHrEmpDept";

			} else {
				return "redirect:/showAddHrEmpDept";

			}
		} catch (Exception e) {
			System.out.println("Exception In delete Dept:" + e.getMessage());

			return "redirect:/showAddHrEmpDept";

		}

	}

	// -----------------------------------------------------------------------------------------------------------------------
	// ----------------------------------LOCATION---------------------------------------------------------------------

	@RequestMapping(value = "/showAddLoc", method = RequestMethod.GET)
	public ModelAndView showAddLoc(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("hrEmployee/addLocation");
		try {
			RestTemplate restTemplate = new RestTemplate();

			LocDisplay[] locArray = restTemplate.getForObject(Constants.security_app_url + "master/allLocationList",
					LocDisplay[].class);
			List<LocDisplay> locList = new ArrayList<>(Arrays.asList(locArray));

			Company[] cmpArray = restTemplate.getForObject(Constants.security_app_url + "master/allCompany",
					Company[].class);
			List<Company> companyList = new ArrayList<>(Arrays.asList(cmpArray));

			System.out.println("Response: " + locList.toString());

			model.addObject("locList", locList);
			model.addObject("compList", companyList);
			model.addObject("locList", locList);
		} catch (Exception e) {
			System.out.println("Exc In showAddDept:" + e.getMessage());
		}
		return model;
	}

	// ------------ADD LOCATION------------------------------------

	@RequestMapping(value = "/addLoc", method = RequestMethod.POST)
	public String addLoc(HttpServletRequest request, HttpServletResponse response) {

		try {
			int locId = 0;

			try {
				locId = Integer.parseInt(request.getParameter("loc_id"));

			} catch (Exception e) {
				locId = 0;

				System.out.println("In Catch of Add LOC Exc:" + e.getMessage());

			}

			int compId = Integer.parseInt(request.getParameter("comp_id"));

			String name = request.getParameter("loc_name");
			String shortName = request.getParameter("loc_short_name");
			String address = request.getParameter("loc_add");
			String desc = request.getParameter("loc_desc");
			String contactPerson = request.getParameter("loc_contact_person");
			String contactNo = request.getParameter("loc_contact_no");
			String email = request.getParameter("loc_email");

			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");

			int userId = userResponse.getUser().getId();

			SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();

			Location loc = new Location(locId, name, shortName, address, contactPerson, contactNo, email, desc, 1, 1,
					userId, "" + dtFormat.format(cal.getTimeInMillis()), compId);

			RestTemplate restTemplate = new RestTemplate();

			Location saveLoc = restTemplate.postForObject(Constants.security_app_url + "master/saveLocation", loc,
					Location.class);

			System.out.println("Response: " + saveLoc.toString());

			return "redirect:/showAddLoc";

		} catch (Exception e) {
			System.out.println("Exception In Add loc Process:" + e.getMessage());
		}

		return "redirect:/showAddLoc";
	}

	// ---------------UPDATE LOCATION------------------------
	@RequestMapping(value = "/updateLoc/{locId}", method = RequestMethod.GET)
	public ModelAndView updateLoc(@PathVariable int locId) {

		ModelAndView mav = new ModelAndView("hrEmployee/addLocation");
		try {

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("locId", locId);

			Location loc = restTemplate.postForObject(Constants.security_app_url + "/master/getLocById", map,
					Location.class);

			System.out.println(loc.toString());

			LocDisplay[] locArray = restTemplate.getForObject(Constants.security_app_url + "master/allLocationList",
					LocDisplay[].class);
			List<LocDisplay> locList = new ArrayList<>(Arrays.asList(locArray));

			Company[] cmpArray = restTemplate.getForObject(Constants.security_app_url + "master/allCompany",
					Company[].class);
			List<Company> companyList = new ArrayList<>(Arrays.asList(cmpArray));

			System.out.println("Response: " + companyList.toString());
			System.out.println("Response: " + locList.toString());

			mav.addObject("locList", locList);
			mav.addObject("compList", companyList);
			mav.addObject("loc", loc);

		} catch (Exception e) {
			System.out.println("Exception In Edit Loc:" + e.getMessage());

			return mav;

		}
		return mav;
	}

	// ---------------DELETE LOCATION--------------------------
	@RequestMapping(value = "/deleteLoc/{locId}", method = RequestMethod.GET)
	public String deleteLoc(@PathVariable int locId) {

		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("locId", locId);

			Info errorResponse = rest.postForObject(Constants.security_app_url + "/master/deleteLocation", map,
					Info.class);
			System.out.println(errorResponse.toString());

			if (errorResponse.isError()) {

				return "redirect:/showAddLoc";

			} else {
				return "redirect:/showAddLoc";

			}
		} catch (Exception e) {
			System.out.println("Exception In delete Loc:" + e.getMessage());

			return "redirect:/showAddLoc";

		}

	}

	// -----------------------------------------------------------------------------------------------------------------------
	// ----------------------------------EMPLOYEE---------------------------------------------------------------------

	@RequestMapping(value = "/showAddHrEmp", method = RequestMethod.GET)
	public ModelAndView showAddHrEmp(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("hrEmployee/addHrEmployee");
		try {
			RestTemplate restTemplate = new RestTemplate();

			EmpDisplay[] empArray = restTemplate.getForObject(Constants.security_app_url + "master/allEmployeeList",
					EmpDisplay[].class);
			List<EmpDisplay> empList = new ArrayList<>(Arrays.asList(empArray));

			Company[] cmpArray = restTemplate.getForObject(Constants.security_app_url + "master/allCompany",
					Company[].class);
			List<Company> companyList = new ArrayList<>(Arrays.asList(cmpArray));

			LocDisplay[] locArray = restTemplate.getForObject(Constants.security_app_url + "master/allLocationList",
					LocDisplay[].class);
			List<LocDisplay> locList = new ArrayList<>(Arrays.asList(locArray));

			EmpDeptDisplay[] deptArray = restTemplate.getForObject(
					Constants.security_app_url + "master/allEmployeeDepartmentList", EmpDeptDisplay[].class);
			List<EmpDeptDisplay> deptList = new ArrayList<>(Arrays.asList(deptArray));

			EmployeeCategoryDisplay[] catArray = restTemplate.getForObject(
					Constants.security_app_url + "master/allEmployeeCategoryList", EmployeeCategoryDisplay[].class);
			List<EmployeeCategoryDisplay> catList = new ArrayList<>(Arrays.asList(catArray));

			EmpType[] typeArray = restTemplate.getForObject(Constants.security_app_url + "master/allEmployeeType",
					EmpType[].class);
			List<EmpType> typeList = new ArrayList<>(Arrays.asList(typeArray));

			Salary[] salArray = restTemplate.getForObject(Constants.security_app_url + "master/allSalaryBracket",
					Salary[].class);
			List<Salary> salList = new ArrayList<>(Arrays.asList(salArray));

			System.out.println("COMPANY -------------- " + companyList);
			System.out.println("LOCATION -------------- " + locList);
			System.out.println("DEPT -------------- " + deptList);
			System.out.println("CATEGORY -------------- " + catList);
			System.out.println("TYPE -------------- " + typeList);
			System.out.println("EMPLOYEE -------------- " + empList);

			model.addObject("locList", locList);
			model.addObject("empList", empList);
			model.addObject("compList", companyList);
			model.addObject("deptList", deptList);
			model.addObject("catList", catList);
			model.addObject("typeList", typeList);
			model.addObject("salList", salList);
			model.addObject("url", Constants.APP_IMAGE_URL);

		} catch (Exception e) {
			System.out.println("Exc In showAddEmp:" + e.getMessage());
		}
		return model;
	}

	// ------------ADD EMPLOYEE------------------------------------

	// private static final String ALPHA_NUMERIC_STRING =
	// "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final String ALPHA_NUMERIC_STRING = "0123456789";

	public static String randomAlphaNumeric(int count) {

		StringBuilder builder = new StringBuilder();

		while (count-- != 0) {

			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());

			builder.append(ALPHA_NUMERIC_STRING.charAt(character));

		}

		return builder.toString();

	}

	@RequestMapping(value = "/addHrEmp", method = RequestMethod.POST)
	public String addHrEmp(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("photo") List<MultipartFile> file) {

		try {
			int empId = 0;
			// String dsc = "";

			try {
				empId = Integer.parseInt(request.getParameter("emp_id"));
				// dsc = request.getParameter("dsc");

			} catch (Exception e) {
				empId = 0;

				System.out.println("In Catch of Add EMP Exc:" + e.getMessage());

			}

			// if (empId == 0) {
			// dsc = randomAlphaNumeric(5);
			// }

			// System.out.println(
			// "DSC
			// *****************************************************************************
			// " + dsc);

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("empId", empId);

			Employee emp = restTemplate.postForObject(Constants.security_app_url + "/master/getEmployeeById", map,
					Employee.class);

			String dsc = request.getParameter("dsc");
			int compId = Integer.parseInt(request.getParameter("comp_id"));
			int deptId = Integer.parseInt(request.getParameter("dept_id"));
			int catId = Integer.parseInt(request.getParameter("cat_id"));
			int locId = Integer.parseInt(request.getParameter("loc_id"));
			int typeId = Integer.parseInt(request.getParameter("type_id"));
			String bgId = request.getParameter("bloodGrp");

			// dsc = request.getParameter("dsc");
			String empCode = request.getParameter("emp_code");
			String fName = request.getParameter("f_name");
			String mName = request.getParameter("m_name");
			String sName = request.getParameter("l_name");
			String imageName = request.getParameter("prevPhoto");
			String mobile1 = request.getParameter("mobile1");
			String mobile2 = request.getParameter("mobile2");
			String email = request.getParameter("email");
			String perAdd = request.getParameter("perAdd");
			String tmpAdd = request.getParameter("tmpAdd");
			String emrgPer1 = request.getParameter("emrgPer1");
			String emrgNo1 = request.getParameter("emrgNo1");
			String emrgPer2 = request.getParameter("emrgPer2");
			String emrgNo2 = request.getParameter("emrgNo2");
			String terms = request.getParameter("terms");
			String strGender = request.getParameter("gender");
			String strDob = request.getParameter("dob");

			String strFile1, strFile2, gender, joinDate, lvReason, leaveDate, lock;
			float grSal, nHrs, fRate, fYrExp, fMonExp;
			int pf, esic, bonus, cl, sl, pl, salId;

			if (empId == 0) {

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				strFile1 = "na";
				strFile2 = "na";
				grSal = 0;
				nHrs = 0;
				gender = "1";
				pf = 0;
				esic = 0;
				bonus = 0;
				cl = 0;
				sl = 0;
				pl = 0;
				fRate = 0;
				fYrExp = 0;
				fMonExp = 0;
				joinDate = sdf.format(Calendar.getInstance().getTimeInMillis());
				leaveDate = sdf.format(Calendar.getInstance().getTimeInMillis());
				lvReason = "na";
				lock = "0";
				salId = 0;

			} else {

				strFile1 = emp.getScanCopy1();
				strFile2 = emp.getScanCopy2();
				grSal = emp.getGrossSalary();
				nHrs = emp.getNoOfHrs();
				gender = emp.getGender();
				pf = emp.getPf();
				esic = emp.getEsic();
				bonus = emp.getBonus();
				cl = emp.getCl();
				sl = emp.getSl();
				pl = emp.getPl();
				fRate = emp.getEmpRatePerhr();
				fYrExp = emp.getEmpPrevExpYrs();
				fMonExp = emp.getEmpPrevExpMonths();
				joinDate = emp.getEmpJoiningDate();
				leaveDate = emp.getEmpLeavingDate();
				lvReason = emp.getEmpLeavingReason();
				lock = emp.getLockPeriod();
				salId = emp.getSalaryId();

			}

			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");

			int userId = userResponse.getUser().getId();

			if (!file.get(0).getOriginalFilename().equalsIgnoreCase("")) {
				imageName = null;

				VpsImageUpload upload = new VpsImageUpload();

				Calendar cal1 = Calendar.getInstance();
				long lo = cal1.getTimeInMillis();

				String curTimeStamp = String.valueOf(lo);

				imageName = curTimeStamp + "_" + file.get(0).getOriginalFilename().replace(' ', '_');

				try {

					upload.saveUploadedFiles(file, Constants.APP_IMAGE_TYPE, imageName);
					System.out.println("upload method called " + file.toString());

				} catch (IOException e) {

					System.out.println("Exce in File Upload In Emp Insert " + e.getMessage());
					e.printStackTrace();
				}

			}

			if (imageName == null) {
				imageName = "na";
			}

			System.out.println("IMAGE NAME----------------- " + imageName);

			SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();

			String dob = DateConvertor.convertToYMD(strDob);

			Employee emp1 = new Employee(empId, dsc, empCode, compId, catId, typeId, deptId, locId, fName, mName, sName,
					imageName, mobile1, mobile2, email, tmpAdd, perAdd, bgId, emrgPer1, emrgNo1, emrgPer2, emrgNo2,
					fRate, joinDate, fYrExp, fMonExp, leaveDate, lvReason, lock, terms, salId, 1, 1, userId,
					"" + dtFormat.format(cal.getTimeInMillis()), grSal, nHrs, strGender, dob, strFile1, strFile2, pf,
					esic, bonus, cl, sl, pl);

			MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<String, Object>();
			map1.add("code", empCode);

			String result = restTemplate.postForObject(Constants.security_app_url + "transaction/checkUniqueEmpCode",
					map1, String.class);

			boolean isValidCode = false;

			if (result != null) {

				if (result.equalsIgnoreCase("Yes")) {
					isValidCode = true;
				} else if (result.equalsIgnoreCase("No")) {
					isValidCode = false;
				}

			}

			if (isValidCode) {
				Employee saveEmp = restTemplate.postForObject(Constants.security_app_url + "master/saveEmployee", emp1,
						Employee.class);

				System.out.println("Response: " + saveEmp.toString());
			}

			// return "redirect:/showAddHrEmp";

		} catch (Exception e) {
			System.out.println("Exception In Add Emp Process:" + e.getMessage());
			e.printStackTrace();
		}

		return "redirect:/showAddHrEmp";
	}

	@RequestMapping(value = "/showHrEmpList", method = RequestMethod.GET)
	public ModelAndView showHrEmpList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("hrEmployee/empList");
		try {
			RestTemplate restTemplate = new RestTemplate();

			EmpDisplay[] empArray = restTemplate.getForObject(Constants.security_app_url + "master/allEmployeeList",
					EmpDisplay[].class);
			List<EmpDisplay> empList = new ArrayList<>(Arrays.asList(empArray));

			model.addObject("empList", empList);
			model.addObject("url", Constants.APP_IMAGE_URL);

		} catch (Exception e) {
			System.out.println("Exc In showAddEmp:" + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/addHrEmpAcc", method = RequestMethod.POST)
	public String addHrEmpAcc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file1") List<MultipartFile> imgFile1, @RequestParam("file2") List<MultipartFile> imgFile2) {

		try {
			int empId = 0;

			try {
				empId = Integer.parseInt(request.getParameter("emp_id"));

			} catch (Exception e) {
				empId = 0;

				System.out.println("In Catch of Add EMP Acc Exc:" + e.getMessage());
			}

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("empId", empId);

			Employee emp = restTemplate.postForObject(Constants.security_app_url + "/master/getEmployeeById", map,
					Employee.class);

			String dsc = emp.getEmpDsc();
			int compId = emp.getCompanyId();
			int deptId = emp.getEmpDeptId();
			int catId = emp.getEmpCatId();
			int locId = emp.getLocId();
			int typeId = emp.getEmpTypeId();
			int salId = Integer.parseInt(request.getParameter("sal_id"));
			String bgId = emp.getEmpBloodgrp();

			// dsc = request.getParameter("dsc");
			String empCode = emp.getEmpCode();
			String fName = emp.getEmpFname();
			String mName = emp.getEmpMname();
			String sName = emp.getEmpSname();
			String imageName = emp.getEmpPhoto();
			String mobile1 = emp.getEmpMobile1();
			String mobile2 = emp.getEmpMobile2();

			String email = emp.getEmpEmail();
			String perAdd = emp.getEmpAddressPerm();
			String tmpAdd = emp.getEmpAddressTemp();

			String emrgPer1 = emp.getEmpEmergencyPerson1();
			String emrgNo1 = emp.getEmpEmergencyNo1();
			String emrgPer2 = emp.getEmpEmergencyPerson2();
			String emrgNo2 = emp.getEmpEmergencyNo2();
			String rate = request.getParameter("rate");
			String jDate = request.getParameter("jDate");
			String yrExp = request.getParameter("yrExp");
			String monExp = request.getParameter("monExp");
			String lvDate = request.getParameter("lvDate");
			String lvReason = request.getParameter("lvReason");
			String lock = request.getParameter("lock");
			String terms = emp.getTermConditions();

			String strGrSal = request.getParameter("grSal");
			String strNHrs = request.getParameter("nHrs");
			String strGender = emp.getGender();
			String strDob = emp.getDob();
			String strFile1 = request.getParameter("scan1");
			String strFile2 = request.getParameter("scan2");
			String strPF = request.getParameter("pf");
			String strEsic = request.getParameter("esic");
			String strBonus = request.getParameter("bonus");
			String strCL = request.getParameter("cl");
			String strSL = request.getParameter("sl");
			String strPL = request.getParameter("pl");

			float grSal = Float.parseFloat(strGrSal);
			float nHrs = Float.parseFloat(strNHrs);

			int gender = Integer.parseInt(strGender);
			int pf = Integer.parseInt(strPF);
			int esic = Integer.parseInt(strEsic);
			int bonus = Integer.parseInt(strBonus);
			int cl = Integer.parseInt(strCL);
			int sl = Integer.parseInt(strSL);
			int pl = Integer.parseInt(strPL);

			float fRate = Float.parseFloat(rate);

			float fYrExp = Float.parseFloat(yrExp);
			float fMonExp = Float.parseFloat(monExp);

			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");

			int userId = userResponse.getUser().getId();

			if (!imgFile1.get(0).getOriginalFilename().equalsIgnoreCase("")) {
				strFile1 = null;

				VpsImageUpload upload = new VpsImageUpload();

				Calendar cal1 = Calendar.getInstance();
				long lo = cal1.getTimeInMillis();

				String curTimeStamp = String.valueOf(lo);

				strFile1 = curTimeStamp + "_" + imgFile1.get(0).getOriginalFilename().replace(' ', '_');

				try {

					upload.saveUploadedFiles(imgFile1, Constants.APP_IMAGE_TYPE, strFile1);
					System.out.println("upload method called " + imgFile1.toString());

				} catch (IOException e) {

					System.out.println("Exce in File Upload In Emp Insert " + e.getMessage());
					e.printStackTrace();
				}

			}

			if (strFile1 == null) {
				strFile1 = "na";
			}

			System.out.println("strFile1 NAME----------------- " + strFile1);

			if (!imgFile2.get(0).getOriginalFilename().equalsIgnoreCase("")) {
				strFile2 = null;

				VpsImageUpload upload = new VpsImageUpload();

				Calendar cal1 = Calendar.getInstance();
				long lo = cal1.getTimeInMillis();

				String curTimeStamp = String.valueOf(lo);

				strFile2 = curTimeStamp + "_" + imgFile2.get(0).getOriginalFilename().replace(' ', '_');

				try {

					upload.saveUploadedFiles(imgFile2, Constants.APP_IMAGE_TYPE, strFile2);
					System.out.println("upload method called " + imgFile2.toString());

				} catch (IOException e) {

					System.out.println("Exce in File Upload In Emp Insert " + e.getMessage());
					e.printStackTrace();
				}

			}

			if (strFile2 == null) {
				strFile2 = "na";
			}

			System.out.println("strFile2 NAME----------------- " + strFile2);

			SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();

			String joinDate = DateConvertor.convertToYMD(jDate);
			String leaveDate = DateConvertor.convertToYMD(lvDate);
			// String dob = DateConvertor.convertToYMD(strDob);

			Employee emp1 = new Employee(empId, dsc, empCode, compId, catId, typeId, deptId, locId, fName, mName, sName,
					imageName, mobile1, mobile2, email, tmpAdd, perAdd, bgId, emrgPer1, emrgNo1, emrgPer2, emrgNo2,
					fRate, joinDate, fYrExp, fMonExp, leaveDate, lvReason, lock, terms, salId, 1, 1, userId,
					"" + dtFormat.format(cal.getTimeInMillis()), grSal, nHrs, strGender, strDob, strFile1, strFile2, pf,
					esic, bonus, cl, sl, pl);

			Employee saveEmp = restTemplate.postForObject(Constants.security_app_url + "master/saveEmployee", emp1,
					Employee.class);

			System.out.println("Response: " + saveEmp.toString());

			return "redirect:/showHrEmpList";

		} catch (Exception e) {
			System.out.println("Exception In Add Emp Acc Process:" + e.getMessage());
		}

		return "redirect:/showHrEmpList";
	}

	// ---------------UPDATE EMPLOYEE------------------------
	@RequestMapping(value = "/updateHrEmp/{empId}", method = RequestMethod.GET)
	public ModelAndView updateHrEmp(@PathVariable int empId) {

		ModelAndView mav = new ModelAndView("hrEmployee/addHrEmployee");
		try {

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("empId", empId);

			Employee emp = restTemplate.postForObject(Constants.security_app_url + "/master/getEmployeeById", map,
					Employee.class);

			System.out.println(emp.toString());

			EmpDisplay[] empArray = restTemplate.getForObject(Constants.security_app_url + "master/allEmployeeList",
					EmpDisplay[].class);
			List<EmpDisplay> empList = new ArrayList<>(Arrays.asList(empArray));

			Company[] cmpArray = restTemplate.getForObject(Constants.security_app_url + "master/allCompany",
					Company[].class);
			List<Company> companyList = new ArrayList<>(Arrays.asList(cmpArray));

			LocDisplay[] locArray = restTemplate.getForObject(Constants.security_app_url + "master/allLocationList",
					LocDisplay[].class);
			List<LocDisplay> locList = new ArrayList<>(Arrays.asList(locArray));

			EmpDeptDisplay[] deptArray = restTemplate.getForObject(
					Constants.security_app_url + "master/allEmployeeDepartmentList", EmpDeptDisplay[].class);
			List<EmpDeptDisplay> deptList = new ArrayList<>(Arrays.asList(deptArray));

			EmployeeCategoryDisplay[] catArray = restTemplate.getForObject(
					Constants.security_app_url + "master/allEmployeeCategoryList", EmployeeCategoryDisplay[].class);
			List<EmployeeCategoryDisplay> catList = new ArrayList<>(Arrays.asList(catArray));

			EmpType[] typeArray = restTemplate.getForObject(Constants.security_app_url + "master/allEmployeeType",
					EmpType[].class);
			List<EmpType> typeList = new ArrayList<>(Arrays.asList(typeArray));

			Salary[] salArray = restTemplate.getForObject(Constants.security_app_url + "master/allSalaryBracket",
					Salary[].class);
			List<Salary> salList = new ArrayList<>(Arrays.asList(salArray));

			try {

				String dob = DateConvertor.convertToDMY(emp.getDob());

				emp.setDob(dob);

			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("COMPANY -------------- " + companyList);
			System.out.println("LOCATION -------------- " + locList);
			System.out.println("DEPT -------------- " + deptList);
			System.out.println("CATEGORY -------------- " + catList);
			System.out.println("TYPE -------------- " + typeList);
			System.out.println("EMPLOYEE -------------- " + empList);

			mav.addObject("locList", locList);
			mav.addObject("empList", empList);
			mav.addObject("compList", companyList);
			mav.addObject("deptList", deptList);
			mav.addObject("catList", catList);
			mav.addObject("typeList", typeList);
			mav.addObject("salList", salList);

			mav.addObject("emp", emp);
			mav.addObject("url", Constants.APP_IMAGE_URL);

		} catch (Exception e) {
			System.out.println("Exception In Edit EMP:" + e.getMessage());

			return mav;

		}
		return mav;
	}

	// ---------------UPDATE ACCOUNT-----------------------------
	@RequestMapping(value = "/updateHrEmpAcc/{empId}", method = RequestMethod.GET)
	public ModelAndView updateHrEmpAcc(@PathVariable int empId) {

		ModelAndView mav = new ModelAndView("hrEmployee/addHrEmpAccountDetails");
		try {

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("empId", empId);

			Employee emp = restTemplate.postForObject(Constants.security_app_url + "/master/getEmployeeById", map,
					Employee.class);

			System.out.println(emp.toString());

			EmpDisplay[] empArray = restTemplate.getForObject(Constants.security_app_url + "master/allEmployeeList",
					EmpDisplay[].class);
			List<EmpDisplay> empList = new ArrayList<>(Arrays.asList(empArray));

			Salary[] salArray = restTemplate.getForObject(Constants.security_app_url + "master/allSalaryBracket",
					Salary[].class);
			List<Salary> salList = new ArrayList<>(Arrays.asList(salArray));

			try {

				String joinDate = DateConvertor.convertToDMY(emp.getEmpJoiningDate());
				String leaveDate = DateConvertor.convertToDMY(emp.getEmpLeavingDate());

				System.out.println("JOIN DATE : ------------------------------------   " + joinDate
						+ "                                LEAVE DATE : " + leaveDate);

				emp.setEmpJoiningDate(joinDate);
				emp.setEmpLeavingDate(leaveDate);

			} catch (Exception e) {
				e.printStackTrace();
			}

			MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<String, Object>();
			map1.add("key", emp.getEmpTypeId());

			Settings settings = restTemplate.postForObject(Constants.security_app_url + "master/getSettingsByKey", map1,
					Settings.class);

			System.err.println("SETTINGS : ----------------------------- " + settings);

			float noOfHrs = 0;
			if (settings != null) {
				noOfHrs = Float.parseFloat(settings.getSettingValue());
			}

			mav.addObject("noOfHrs", noOfHrs);

			mav.addObject("empList", empList);
			mav.addObject("salList", salList);

			mav.addObject("emp", emp);
			mav.addObject("url", Constants.APP_IMAGE_URL);

		} catch (Exception e) {
			System.out.println("Exception In Edit EMP:" + e.getMessage());

			return mav;

		}
		return mav;
	}

	// ---------------DELETE EMPLOYEE--------------------------
	@RequestMapping(value = "/deleteHrEmp/{empId}", method = RequestMethod.GET)
	public String deleteHrEmp(@PathVariable int empId) {

		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("empId", empId);

			Info errorResponse = rest.postForObject(Constants.security_app_url + "/master/deleteEmployee", map,
					Info.class);
			System.out.println(errorResponse.toString());

			if (errorResponse.isError()) {

				return "redirect:/showAddHrEmp";

			} else {
				return "redirect:/showAddHrEmp";

			}
		} catch (Exception e) {
			System.out.println("Exception In delete EMP:" + e.getMessage());

			return "redirect:/showAddHrEmp";

		}

	}

	@RequestMapping(value = "/deleteHrEmpFromList/{empId}", method = RequestMethod.GET)
	public String deleteHrEmpFromList(@PathVariable int empId) {

		try {

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("empId", empId);

			Info errorResponse = rest.postForObject(Constants.security_app_url + "/master/deleteEmployee", map,
					Info.class);
			System.out.println(errorResponse.toString());

			if (errorResponse.isError()) {

				return "redirect:/showHrEmpList";

			} else {
				return "redirect:/showHrEmpList";

			}
		} catch (Exception e) {
			System.out.println("Exception In delete EMP:" + e.getMessage());

			return "redirect:/showHrEmpList";

		}

	}

	// --------------CHECK UNIQUE EMP CODE----------------------------------

	@RequestMapping(value = "/checkuniqueEmpCodeProcess", method = RequestMethod.GET)
	public @ResponseBody Info checkuniqueEmpCode(HttpServletRequest request, HttpServletResponse response) {

		Info result = new Info();

		try {

			String code = request.getParameter("code");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			map.add("code", code);

			result = restTemplate.postForObject(Constants.security_app_url + "transaction/checkUniqueEmpCode", map,
					Info.class);

			System.err.println("result *********///////////////***************" + result);

		} catch (Exception e) {
			System.out.println("get emp wise Report  " + e.getMessage());
			e.printStackTrace();

		}

		return result;
	}

}
