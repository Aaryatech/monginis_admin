package com.ats.adminpanel.controller;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.DepartmentList;
import com.ats.adminpanel.model.GetUserTypeList;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.login.User;

@Controller
public class UserController {

	Instant start = null;
	
	@RequestMapping(value = "/showAddUser")
	public ModelAndView showAddUser(HttpServletRequest request, HttpServletResponse response) {
		
		 
		ModelAndView model=new  ModelAndView("user/addUser");
		Constants.mainAct=22;
		Constants.subAct=108;
		RestTemplate restTemplate = new RestTemplate();
		GetUserTypeList getUserTypeList=restTemplate.getForObject(Constants.url+"getAllUserType", GetUserTypeList.class);
		DepartmentList departmentList=restTemplate.getForObject(Constants.url+"getAllDept", DepartmentList.class);
		model.addObject("getUserTypeList", getUserTypeList.getGetUserTypeList());
		model.addObject("departmentList",departmentList.getDepartmentList());
		
		return model;
	}
	
	
	
 
	@RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
	public String addNewUser(HttpServletRequest request, HttpServletResponse response) {
		
		String uname=request.getParameter("uname");
		String upass=request.getParameter("upass");
		String email = request.getParameter("email");
		String contact = request.getParameter("contact");
		
		int deptId=Integer.parseInt(request.getParameter("dept_id"));
		int userType=Integer.parseInt(request.getParameter("user_type"));
		User user=new User();
		
		
		user.setUsername(uname);
		user.setPassword(upass);
		user.setEmail(email);
		user.setContact(contact);
		user.setDeptId(deptId);
		user.setUsertype(userType);
		
		RestTemplate restTemplate = new RestTemplate();
		try {
		Info info=restTemplate.postForObject(Constants.url+"insertNewUser",user,Info.class);
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "redirect:/showAddUser";
	}
	
	/******************************************************************/
	@RequestMapping(value = "/getUniqueEmail", method = RequestMethod.GET)
	public @ResponseBody Info getUniqueEmail(HttpServletRequest request, HttpServletResponse response) {
		Info info = new Info();
	try{
		
		RestTemplate rest = new RestTemplate();
		String email = request.getParameter("email");
		System.out.println("Email--------------------------"+email);
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("email", email);
		
		User user = rest.postForObject(Constants.url + "getUserInfoByEmail", map, User.class);
		System.err.println("User-----------"+user);
		if(user!=null) {
			info.setError(false);
			info.setMessage("User Found");
			System.err.println(info);
		}else {
			info.setError(true);
			info.setMessage("User Not Found");
			System.err.println(info);
		}
	}catch (Exception e) {
		e.printStackTrace();		
	}
		
		return info;
		
	}
	
	@RequestMapping(value = "/getUniqueContact", method = RequestMethod.GET)
	public @ResponseBody Info getUniqueContact(HttpServletRequest request, HttpServletResponse response) {
		Info info = new Info();
	try{
		
		RestTemplate rest = new RestTemplate();
		String contact = request.getParameter("contact");
		System.out.println("Contact--------------------------"+contact);
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("contact", contact);
		
		User user = rest.postForObject(Constants.url + "getUserInfoByContact", map, User.class);
		System.err.println("User-----------"+user);
		if(user!=null) {
			info.setError(false);
			info.setMessage("User Found");
			System.err.println(info);
		}else {
			info.setError(true);
			info.setMessage("User Not Found");
			System.err.println(info);
		}
	}catch (Exception e) {
		e.printStackTrace();		
	}
		
		return info;
		
	}
	

	@RequestMapping(value = "/getUniqueUser", method = RequestMethod.GET)
	public @ResponseBody Info getUniqueUser(HttpServletRequest request, HttpServletResponse response) {
		Info info = new Info();
	try{
		
		RestTemplate rest = new RestTemplate();
		String uname = request.getParameter("uname");
		System.out.println("uname--------------------------"+uname);
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("uname", uname);
		
		User user = rest.postForObject(Constants.url + "getUserInfoByUser", map, User.class);
		System.err.println("User-----------"+user);
		if(user!=null) {
			info.setError(false);
			info.setMessage("User Found");
			System.err.println(info);
		}else {
			info.setError(true);
			info.setMessage("User Not Found");
			System.err.println(info);
		}
	}catch (Exception e) {
		e.printStackTrace();		
	}
		
		return info;
		
	}
	@RequestMapping("/forgetPwd")
	public ModelAndView forgetPwd(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("forgetPassUser");
		return mav;
	}
	
	
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
	public ModelAndView getUserInfo(HttpServletRequest request, HttpServletResponse response) {
		Info info = new Info();
		ModelAndView model = null;
		
	try{
		
		RestTemplate rest = new RestTemplate();
		String username = request.getParameter("username");
		//System.out.println("Contact--------------------------"+username);
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("username", username);
		
		User user = rest.postForObject(Constants.url + "getUserInfoByUsername", map, User.class);
		System.err.println("User Info-----------"+user);
		if(user!=null) {
			model = new ModelAndView("verifyOTP");
			model.addObject("username", username);
			info.setError(false);
			info.setMessage("User Found");
			System.err.println(info);
			
			start = Instant.now();

			;
		}else {
			info.setError(true);
			model = new ModelAndView("forgetPassUser");
			info.setMessage("User Not Found");
			System.err.println(info);
		}
	}catch (Exception e) {
		e.printStackTrace();		
	}
		
		return model;
		
	}
	
	
	
	@RequestMapping(value = "/OTPVerification", method = RequestMethod.POST)
	public ModelAndView OTPVerificationByContact(HttpServletRequest request, HttpServletResponse response) {

		System.err.println("Hiii  OTPVerification  ");
		Info info = new Info();
		ModelAndView model = null;

		try {
			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String otp = request.getParameter("otp");

			map.add("otp", otp);

			User user = rest.postForObject(Constants.url + "VerifyOTP", map, User.class);	
		//	System.err.println("OTP User--------------"+user);

			if (user.getId() == 0) {
				model = new ModelAndView("forgetPassUser");
				model.addObject("msg", "Incorrect OTP");

			} else {				
				System.err.println("User" + user);
				model = new ModelAndView("changePassword");
				model.addObject("userId", user.getId());
				
			}

		} catch (Exception e) {
			System.err.println("Exce in checkUniqueField  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}
	
	
	@RequestMapping(value = "/changeToNewPassword", method = RequestMethod.POST)
	public String changeToNewPassword(HttpServletRequest request, HttpServletResponse response) {
		Info info = new Info();
		ModelAndView model = null;
		HttpSession session = request.getSession();
		try {

			RestTemplate rest = new RestTemplate();
			int userId = Integer.parseInt(request.getParameter("userId"));
			String newPass = request.getParameter("newPass");
			//System.out.println("Passs--------------------------" + userId + " " + newPass);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("userId", userId);
			map.add("newPass", newPass);

			Info inf = rest.postForObject(Constants.url + "updateToNewPassword", map, Info.class);

			if (inf.getError()) {
				model = new ModelAndView("login");
				session.setAttribute("changePasswordFail", "Password Not Change");
				info.setError(true);
				info.setMessage("User Not Found");
				System.err.println(info);
			} else {
				model = new ModelAndView("login");			
				session.setAttribute("changePassword", "Password Change Sucessfully");
				info.setError(false);
				info.setMessage("User Found");
				System.err.println(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/";

	}
	
	@RequestMapping(value = "/reGenOtp1", method = RequestMethod.POST)
	public ModelAndView reGenOtp1(HttpServletRequest request, HttpServletResponse response) {
		String c = null;
		System.err.println("Hiii  checkValue  ");
		Info info = new Info();
		ModelAndView model = null;
		User user = new User();
		try {
			
			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String username = request.getParameter("username");
		//	System.err.println("Username for regeneration mob***  " + username);

			map.add("username", username);

			user = rest.postForObject(Constants.url + "getUserInfoByUsername", map, User.class);
			System.err.println("Info Response  " + info.toString());

			if (user!=null) {
				model = new ModelAndView("verifyOTP");
				model.addObject("username", username);
				model.addObject("msg", "OTP Resent Please check");
				start = Instant.now();
			} else {

				model = new ModelAndView("forgetPassUser");
				model.addObject("msg", "Invalid User Name");

			}

		} catch (Exception e) {
			System.err.println("Exce in reGenOtp1  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}
}
