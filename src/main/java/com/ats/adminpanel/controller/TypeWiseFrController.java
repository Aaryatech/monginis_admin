package com.ats.adminpanel.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.commons.VpsImageUpload;
import com.ats.adminpanel.model.album.TypeWiseFr;
import com.ats.adminpanel.model.album.TypeWiseFrDisplay;
import com.ats.adminpanel.model.franchisee.AllFranchiseeList;
import com.ats.adminpanel.model.franchisee.FranchiseeList;
import com.ats.adminpanel.model.hr.EmpDisplay;
import com.ats.adminpanel.model.hr.Employee;
import com.ats.adminpanel.model.hr.Salary;
import com.ats.adminpanel.model.hr.Settings;
import com.ats.adminpanel.model.login.UserResponse;

@Controller
public class TypeWiseFrController {

	@RequestMapping(value = "/typeWiseFrList", method = RequestMethod.GET)
	public ModelAndView typeWiseFrList(HttpServletRequest request, HttpServletResponse response) {
		RestTemplate restTemplate = new RestTemplate();
		ModelAndView mav = new ModelAndView("typewiseFr/typeWiseFrList");

		AllFranchiseeList allFranchiseeList = restTemplate.getForObject(Constants.url + "getAllFranchisee",
				AllFranchiseeList.class);

		List<FranchiseeList> franchiseeList = new ArrayList<FranchiseeList>();
		franchiseeList = allFranchiseeList.getFranchiseeList();
		System.out.println("Franchisee List:" + franchiseeList.toString());

		TypeWiseFrDisplay[] typeArray = restTemplate.getForObject(Constants.url + "getTypeWiseFrNameList",
				TypeWiseFrDisplay[].class);
		List<TypeWiseFrDisplay> typeList = new ArrayList<>(Arrays.asList(typeArray));

		mav.addObject("typeList", typeList);
		mav.addObject("frList", franchiseeList);

		System.err.println("TO STRING--------------------------- " + mav.toString());

		return mav;
	}

	// ---------------UPDATE ACCOUNT-----------------------------
	@RequestMapping(value = "/updateTypeFr/{typeId}", method = RequestMethod.GET)
	public ModelAndView updateTypeFr(@PathVariable int typeId) {

		ModelAndView mav = new ModelAndView("typewiseFr/addTypeWiseFr");
		try {

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("typeId", typeId);

			TypeWiseFr typeFr = restTemplate.postForObject(Constants.url + "getTypeFrById", map, TypeWiseFr.class);

			AllFranchiseeList allFranchiseeList = restTemplate.getForObject(Constants.url + "getAllFranchisee",
					AllFranchiseeList.class);

			List<FranchiseeList> franchiseeList = new ArrayList<FranchiseeList>();
			franchiseeList = allFranchiseeList.getFranchiseeList();
			System.out.println("Franchisee List:" + franchiseeList.toString());

			List<Integer> frIdList = Stream.of(typeFr.getFrIds().split(",")).map(Integer::parseInt)
					.collect(Collectors.toList());

			mav.addObject("frIdList", frIdList);
			mav.addObject("frList", franchiseeList);
			mav.addObject("typeModel", typeFr);

		} catch (Exception e) {
			System.out.println("Exception In Edit Type:" + e.getMessage());

			return mav;

		}
		return mav;
	}
	
	
	@RequestMapping(value = "/addTypwWiseFr", method = RequestMethod.POST)
	public String addHrEmpAcc(HttpServletRequest request, HttpServletResponse response) {

		try {

			RestTemplate restTemplate = new RestTemplate();
			
			int typeId = Integer.parseInt(request.getParameter("typeId"));
			String typeName = request.getParameter("typeName");
			String[] frIds = request.getParameterValues("selectedFr");
			
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < frIds.length; i++) {
			sb = sb.append(frIds[i] + ",");

			}
			String frIdList = sb.toString();
			frIdList = frIdList.substring(0, frIdList.length() - 1);

			System.err.println("TYPE ID : "+typeId);
			System.err.println("TYPE NAME : "+typeName);
			System.err.println("FR IDS : "+frIdList);

			TypeWiseFr typeModel=new TypeWiseFr(typeId,typeName,frIdList);
		
			TypeWiseFr saveType=restTemplate.postForObject(Constants.url + "saveType", typeModel,
					TypeWiseFr.class);
			
			return "redirect:/typeWiseFrList";

		} catch (Exception e) {
			System.out.println("Exception In Add Type Process:" + e.getMessage());
		}

		return "redirect:/typeWiseFrList";
	}
	

}
