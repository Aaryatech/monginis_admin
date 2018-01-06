package com.ats.adminpanel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.accessright.AccessRightModule;
import com.ats.adminpanel.model.accessright.AccessRightModuleList;
import com.ats.adminpanel.model.accessright.AccessRightSubModule;
import com.ats.adminpanel.model.accessright.AssignRoleDetailList;
import com.ats.adminpanel.model.accessright.CreatedRoleList;
import com.ats.adminpanel.model.accessright.ModuleJson;
import com.ats.adminpanel.model.accessright.SubModuleJson;
import com.ats.adminpanel.model.franchisee.AllMenuResponse;
import com.ats.adminpanel.model.login.User;
import com.ats.adminpanel.model.spprod.EmployeeList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class AccessRightController {

	
	RestTemplate rest=new RestTemplate();
	public static AccessRightModuleList accessRightModuleList;
	
	@RequestMapping(value = "/showCreateRole", method = RequestMethod.GET)
	public ModelAndView showAccessRight(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("accessRight/createRole");
		Constants.mainAct = 8;
		Constants.subAct = 82;
		try {
		  accessRightModuleList=rest.getForObject(Constants.url + "getAllModuleAndSubModule",
				AccessRightModuleList.class);
		System.out.println("Access List "+accessRightModuleList.toString());
		model.addObject("allModuleList",accessRightModuleList.getAccessRightModuleList());
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/submitCreateRole", method = RequestMethod.POST)
	public String submitAssignRole(HttpServletRequest request, HttpServletResponse response) {

		List<AccessRightModule> accessRightModule=accessRightModuleList.getAccessRightModuleList();
		//List<AccessRightModule> newModuleList=new ArrayList<>();
		List<ModuleJson> moduleJsonList=new ArrayList<>();
		for(int i=0;i<accessRightModule.size();i++)
		{
			List<SubModuleJson> subModuleJsonList=new ArrayList<>();
			
			boolean isPresent=false;
			List<AccessRightSubModule> accessRightSubModuleList=accessRightModule.get(i).getAccessRightSubModuleList();
			for(int j=0;j<accessRightSubModuleList.size();j++)
			{
				String [] subModuleId=request.getParameterValues(accessRightSubModuleList.get(j).getSubModuleId()+""+accessRightModule.get(i).getModuleId());
				String view="hidden";
				String add="hidden";
				String edit="hidden";
				String delete="hidden";
				if(subModuleId!=null)
				{
					AccessRightSubModule accessRightSubModule=accessRightSubModuleList.get(j);
					
					SubModuleJson subModuleJson=new SubModuleJson();
					
					subModuleJson.setModuleId(accessRightSubModule.getModuleId());
					subModuleJson.setSubModuleId(accessRightSubModule.getSubModuleId());
					subModuleJson.setSubModuleDesc(accessRightSubModule.getSubModuleDesc());
					subModuleJson.setSubModuleMapping(accessRightSubModule.getSubModuleMapping());
					subModuleJson.setSubModulName(accessRightSubModule.getSubModulName());
					subModuleJson.setType(accessRightSubModule.getType());
					
				for(int k=0;k<subModuleId.length;k++)
				{
					if(subModuleId[k].equals("view"))
					{
						view=new String("visible");
					}
					else if(subModuleId[k].equals("add"))
					{
						add=new String("visible");
					}
					else if(subModuleId[k].equals("edit"))
					{
						edit=new String("visible");
					}
					else if(subModuleId[k].equals("delete"))
					{
						delete=new String("visible");
					}
				}
				isPresent=true;
				subModuleJson.setView(view);
				subModuleJson.setEditReject(edit);
				subModuleJson.setAddApproveConfig(add);
				subModuleJson.setDeleteRejectApprove(delete);
				subModuleJsonList.add(subModuleJson);
				}
			}
			if(isPresent)
			{
				
				AccessRightModule module=accessRightModule.get(i);
				ModuleJson moduleJson=new ModuleJson();
				
				moduleJson.setModuleId(module.getModuleId());
				moduleJson.setModuleDesc(module.getModuleDesc());
				moduleJson.setModuleName(module.getModuleName());
				moduleJson.setSubModuleJsonList(subModuleJsonList);
				
				 
				moduleJsonList.add(moduleJson);
			 
			}
		}
		
		if(moduleJsonList!=null && !moduleJsonList.isEmpty()){
				String roleName=request.getParameter("roleName");
		AssignRoleDetailList assignRoleDetailList=new AssignRoleDetailList();
		ObjectMapper mapper = new ObjectMapper();
		try {
			String newsLetterJSON = mapper.writeValueAsString(moduleJsonList);
			
			System.out.println("JSON  "+newsLetterJSON);
			assignRoleDetailList.setRoleJson(newsLetterJSON);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//assignRoleDetailList.setAccessRightModuleList(newModuleList);
		assignRoleDetailList.setRoleName(roleName);
		assignRoleDetailList.setDelStatus(0);
		System.out.println("accessRightModule List "+assignRoleDetailList.toString());
		System.out.println("heare");
		
		Info info=rest.postForObject(Constants.url + "saveAssignRole",assignRoleDetailList, Info.class);
		}
		return "redirect:/showCreateRole";
	}

	
	@RequestMapping(value = "/showAssignRole", method = RequestMethod.GET)
	public ModelAndView showAssignRloe(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("accessRight/assignAccessRole");
		Constants.mainAct = 8;
		Constants.subAct = 82;
		 try {
			 
			 MultiValueMap<String, Object> map=new LinkedMultiValueMap<String, Object>();
			 map.add("empType", 1);

				/*EmployeeList employeeList = rest.postForObject(Constants.url + "/spProduction/getEmployeeList", map,
						EmployeeList.class);*/
			 List<User> userList=rest.getForObject(Constants.url+"getAllUser", List.class);
			 CreatedRoleList createdRoleList=rest.getForObject(Constants.url + "getAllAccessRole",
					 CreatedRoleList.class);
		System.out.println("Access List "+createdRoleList.toString());
		model.addObject("userList",userList);
		model.addObject("createdRoleList",createdRoleList.getAssignRoleDetailList());
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		return model;
	}
	
	
	@RequestMapping(value = "/submitAssignedRole", method = RequestMethod.POST)
	public String submitAssignedRole(HttpServletRequest request, HttpServletResponse response) {
		
		int roleId=Integer.parseInt(request.getParameter("role"));
		int empId=Integer.parseInt(request.getParameter("empId"));
			try {
			 
			 MultiValueMap<String, Object> map=new LinkedMultiValueMap<String, Object>();
			 map.add("id", empId);
			 map.add("roleId", roleId);
			 
			 Info info=rest.postForObject(Constants.url + "updateEmpRole",map,Info.class);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		
		return "redirect:/showAssignRole";
	}
	
	@RequestMapping(value = "/showAssignUserDetail/{userId}/{userName}/{roleName}", method = RequestMethod.GET)
	public ModelAndView showAssignUserDetail(@PathVariable int userId, @PathVariable String userName, @PathVariable String roleName, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("accessRight/viewAssignRoleDetails");
		
		 MultiValueMap<String, Object> map=new LinkedMultiValueMap<String, Object>();
		 map.add("usrId", userId);
		 ParameterizedTypeReference<List<ModuleJson>> typeRef = new ParameterizedTypeReference<List<ModuleJson>>() {
			};
			ResponseEntity<List<ModuleJson>> responseEntity = rest.exchange(Constants.url + "getRoleJson",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);
			
			 List<ModuleJson> newModuleList = responseEntity.getBody();
			 
			 model.addObject("moduleJsonList", newModuleList);
			 model.addObject("userName",userName);
			 model.addObject("roleName",roleName);
			 
	
		return model;
	}
}
