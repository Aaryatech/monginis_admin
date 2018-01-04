package com.ats.adminpanel.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.*;
import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.*;
import com.ats.adminpanel.model.grngvn.GetGrnGvnDetails;
import com.ats.adminpanel.model.grngvn.GetGrnGvnDetailsList;


@Controller
@Scope("session")
public class TempGvnGrnController {
	
	
	public List<GetGrnGvnDetails> grnGvnDetailsList;
	GetGrnGvnDetailsList getGrnGvnDetailsList;
	AllFrIdNameList allFrIdNameList;
	
	
	@RequestMapping(value = "/getGrnList", method = RequestMethod.GET)
	public @ResponseBody List<GetGrnGvnDetails> getGrnDetails(HttpServletRequest request,
			HttpServletResponse response) {
		// ModelAndView modelAndView = new ModelAndView("grngvn/displaygrn");

		System.out.println("in method");
		String selectedFr = request.getParameter("fr_id_list");
			selectedFr=selectedFr.substring(1, selectedFr.length()-1);
			selectedFr=selectedFr.replaceAll("\"", "");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");

		

		RestTemplate restTemplate = new RestTemplate();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		
		map.add("frId", selectedFr);
		map.add("fromDate", fromDate);
		map.add("toDate", toDate);
		// getFrGrnDetail
		try {
			// getGrnGvnDetailsList=restTemplate.postForObject(Constant.URL+
			// "getFrGrnDetail",map, GetGrnGvnDetailsList.class);

			ParameterizedTypeReference<GetGrnGvnDetailsList> typeRef = new ParameterizedTypeReference<GetGrnGvnDetailsList>() {
			};
			ResponseEntity<GetGrnGvnDetailsList> responseEntity = restTemplate
					.exchange(Constants.url + "getFrGrnDetails", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			getGrnGvnDetailsList = responseEntity.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		grnGvnDetailsList = getGrnGvnDetailsList.getGrnGvnDetails();

		
		System.out.println("grn  list " + grnGvnDetailsList);

		// modelAndView.addObject("grnList",grnGvnDetailsList);
		// modelAndView.addObject("fromDate",fromDate);
		// modelAndView.addObject("toDate",toDate);

		return grnGvnDetailsList;

	}

	@RequestMapping(value = "/displayGrn", method = RequestMethod.GET)
	public ModelAndView showGrnDetails(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("grngvn/displaygrn");
		
		
		RestTemplate restTemplate = new RestTemplate();
		allFrIdNameList = new AllFrIdNameList();
		try {

			allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

		} catch (Exception e) {
			System.out.println("Exception in getAllFrIdName" + e.getMessage());
			e.printStackTrace();

		}
		
		modelAndView.addObject("allFrIdNameList", allFrIdNameList.getFrIdNamesList());
		return modelAndView;

	}

	@RequestMapping(value = "/displayGvn", method = RequestMethod.GET)
	public ModelAndView showGvnDetails(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("grngvn/displaygvn");
		RestTemplate restTemplate = new RestTemplate();
		allFrIdNameList = new AllFrIdNameList();
		try {

			allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

		} catch (Exception e) {
			System.out.println("Exception in getAllFrIdName" + e.getMessage());
			e.printStackTrace();

		}
		
		modelAndView.addObject("allFrIdNameList", allFrIdNameList.getFrIdNamesList());
		
		return modelAndView;

	}

	@RequestMapping(value = "/getGvnList", method = RequestMethod.GET)
	public @ResponseBody List<GetGrnGvnDetails> getGvnDetails(HttpServletRequest request,
			HttpServletResponse response) {
		// ModelAndView modelAndView = new ModelAndView("grngvn/displaygvn");

		System.out.println("in method");
		String selectedFr = request.getParameter("fr_id_list");
			selectedFr=selectedFr.substring(1, selectedFr.length()-1);
			selectedFr=selectedFr.replaceAll("\"", "");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		System.out.println("From " + fromDate + "   To   " + toDate);
		

		RestTemplate restTemplate = new RestTemplate();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		
		map.add("frId", selectedFr);
		map.add("fromDate", fromDate);
		map.add("toDate", toDate);
		// getFrGrnDetail
		try {
			// getGrnGvnDetailsList=restTemplate.postForObject(Constant.URL+
			// "getFrGvnDetail",map, GetGrnGvnDetailsList.class);

			ParameterizedTypeReference<GetGrnGvnDetailsList> typeRef = new ParameterizedTypeReference<GetGrnGvnDetailsList>() {
			};
			ResponseEntity<GetGrnGvnDetailsList> responseEntity = restTemplate
					.exchange(Constants.url + "getFrGvnDetails", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			getGrnGvnDetailsList = responseEntity.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		grnGvnDetailsList = getGrnGvnDetailsList.getGrnGvnDetails();

		System.out.println("gvn  list " + grnGvnDetailsList);
		for (int i = 0; i < grnGvnDetailsList.size(); i++) {
			grnGvnDetailsList.get(i)
					.setGvnPhotoUpload1(Constants.SPCAKE_IMAGE_URL + grnGvnDetailsList.get(i).getGvnPhotoUpload1());
			grnGvnDetailsList.get(i)
					.setGvnPhotoUpload2(Constants.SPCAKE_IMAGE_URL + grnGvnDetailsList.get(i).getGvnPhotoUpload2());

		}

		// modelAndView.addObject("gvnList",grnGvnDetailsList);
		// modelAndView.addObject("fromDate",fromDate);
		// modelAndView.addObject("toDate",toDate);

		return grnGvnDetailsList;

	}

}
