package com.ats.adminpanel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.DepartmentList;
import com.ats.adminpanel.model.GetUserTypeList;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.login.User;

@Controller
public class UserController {

	
	
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
		
		int deptId=Integer.parseInt(request.getParameter("dept_id"));
		int userType=Integer.parseInt(request.getParameter("user_type"));
		User user=new User();
		
		
		user.setUsername(uname);
		user.setPassword(upass);
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
}
