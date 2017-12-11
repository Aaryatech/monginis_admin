package com.ats.adminpanel.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.production.GetProdPlanDetail;
import com.ats.adminpanel.model.production.GetProdPlanDetailList;
import com.ats.adminpanel.model.production.GetProdPlanHeader;
import com.ats.adminpanel.model.production.GetProdPlanHeaderList;

@Controller

public class ViewProdController {
	
	List<GetProdPlanHeader> prodPlanHeaderList;
	
	List<GetProdPlanDetail> prodPlanDetailList;
	
	int globalHeaderId=0;
	
	@RequestMapping(value = "/showProdHeader", method = RequestMethod.GET)
	public ModelAndView showProdHeader(HttpServletRequest request, HttpServletResponse response) {
		
		Constants.mainAct=16;
		Constants.subAct=164;
		ModelAndView model = new ModelAndView("production/viewProdHeader");
		try {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		
		
		if(request.getParameter("from_date")==null || request.getParameter("to_date")==null) {
		Date date=new Date();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String fromDate=df.format(date);
		String toDate=df.format(date);
		map.add("fromDate", fromDate);
		map.add("toDate", toDate);
		
		System.out.println("inside if ");
		}
		else {
			String fromDate=request.getParameter("from_date");
			String toDate=request.getParameter("to_date");
			
			System.out.println("inside Else ");

			System.out.println("fromDate "+fromDate);

			System.out.println("toDate "+toDate);

			map.add("fromDate", fromDate);
			map.add("toDate", toDate);
			
		}
		
		GetProdPlanHeaderList prodHeader=restTemplate.postForObject(Constants.url + "getProdPlanHeader",map, GetProdPlanHeaderList.class);
		
		prodPlanHeaderList=new ArrayList<>();
		
		prodPlanHeaderList=prodHeader.getProdPlanHeader();
		
		System.out.println("prod header "+prodPlanHeaderList.toString());
		
		model.addObject("planHeader",prodPlanHeaderList);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return model;
		
	}	
	
	
	@RequestMapping(value = "/getProdDetail/{productionHeaderId}", method = RequestMethod.GET)
	public ModelAndView getProdDetail(@PathVariable("productionHeaderId")int productionHeaderId,
			HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("inside Prod Detail");
		
		Constants.mainAct=16;
		Constants.subAct=164;
		ModelAndView model = new ModelAndView("production/prodDetail");
		
		try {
		globalHeaderId=productionHeaderId;
		System.out.println("After model ");
		
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		
		map.add("planHeaderId", productionHeaderId);
		
		GetProdPlanDetailList prodDetail=restTemplate.postForObject(Constants.url + "getProdPlanDetail",map, GetProdPlanDetailList.class);
		
		prodPlanDetailList=new ArrayList<>();
		
		prodPlanDetailList=prodDetail.getProdPlanDetail();
		GetProdPlanHeader planHeader=new GetProdPlanHeader();
		
		for(int i=0;i<prodPlanHeaderList.size();i++) {
			
			if(prodPlanHeaderList.get(i).getProductionHeaderId()==globalHeaderId) {
				
			 planHeader=prodPlanHeaderList.get(i);
			}
				
		}
		
		
		model.addObject("planDetail", prodPlanDetailList);
		
		model.addObject("planHeader",planHeader);
		
		}catch (Exception e) {
			
			System.out.println("in catech model ");
			
			e.printStackTrace();
			
			
		}
		return model;
	
		
	}
	
}
