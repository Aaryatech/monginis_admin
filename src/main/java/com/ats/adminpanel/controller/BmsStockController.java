package com.ats.adminpanel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.item.FrItemStockConfigure;
import com.ats.adminpanel.model.item.FrItemStockConfigureList;
import com.ats.adminpanel.model.production.mixing.temp.GetSFPlanDetailForMixingList;
import com.ats.adminpanel.model.stock.GetBmsCurrentStock;
import com.ats.adminpanel.model.stock.GetBmsCurrentStockList;

@Controller
public class BmsStockController {

	GetBmsCurrentStockList bmsCurrentStockList;

	List<GetBmsCurrentStock> bmsCurrentStock = new ArrayList<>();

	@RequestMapping(value = "/showBmsStock", method = RequestMethod.GET)
	public ModelAndView showBmsStock(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("stock/bmsStock");

		/*MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		RestTemplate restTemplate = new RestTemplate();

		String settingKey = new String();

		settingKey = "PROD" + "," + "MIX" + "," + "BMS" + "," + "STORE";

		map.add("settingKeyList", settingKey);

		FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
				FrItemStockConfigureList.class);

		System.out.println("SettingKeyList" + settingList.toString());

		
		int prodDeptId = 0, storeDeptId = 0, mixDeptId = 0;

		List<FrItemStockConfigure> settingKeyList = settingList.getFrItemStockConfigure();

		for (int i = 0; i < settingKeyList.size(); i++) {

			if (settingKeyList.get(i).getSettingKey().equalsIgnoreCase("PROD")) {

				prodDeptId = settingKeyList.get(i).getSettingValue();

			}
			if (settingKeyList.get(i).getSettingKey().equalsIgnoreCase("Store")) {

				storeDeptId = settingKeyList.get(i).getSettingValue();

			}
			if (settingKeyList.get(i).getSettingKey().equalsIgnoreCase("Mix")) {

				mixDeptId = settingKeyList.get(i).getSettingValue();

			}

		}
		
		
		System.out.println("Mix Dept Id "+mixDeptId);
		System.out.println("Prod Dept Id "+prodDeptId);

		System.out.println("Store Dept Id "+storeDeptId);
		
		map = new LinkedMultiValueMap<String, Object>();
		map.add("prodDeptId", prodDeptId);
		map.add("storeDeptId", storeDeptId);
		map.add("mixDeptId", mixDeptId);
		bmsCurrentStockList=restTemplate.postForObject(Constants.url + "getCuurentBmsStock", map,
			GetBmsCurrentStockList.class);
		
		
		bmsCurrentStock=bmsCurrentStockList.getBmsCurrentStock();
		
		mav.addObject("stockList",bmsCurrentStock);
		
		System.out.println("BMS Stock List ="+bmsCurrentStockList.getBmsCurrentStock().toString());*/
		return mav;

	}
	
	@RequestMapping(value = "/getBmsStock", method = RequestMethod.POST)
	public ModelAndView getBmsStock(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("stock/bmsStock");

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		RestTemplate restTemplate = new RestTemplate();
		
		if(Integer.parseInt(request.getParameter("selectStock"))==1) {
		
		String settingKey = new String();

		settingKey = "PROD" + "," + "MIX" + "," + "BMS" + "," + "STORE";

		map.add("settingKeyList", settingKey);

		FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
				FrItemStockConfigureList.class);

		System.out.println("SettingKeyList" + settingList.toString());

		
		int prodDeptId = 0, storeDeptId = 0, mixDeptId = 0;

		List<FrItemStockConfigure> settingKeyList = settingList.getFrItemStockConfigure();

		for (int i = 0; i < settingKeyList.size(); i++) {

			if (settingKeyList.get(i).getSettingKey().equalsIgnoreCase("PROD")) {

				prodDeptId = settingKeyList.get(i).getSettingValue();

			}
			if (settingKeyList.get(i).getSettingKey().equalsIgnoreCase("Store")) {

				storeDeptId = settingKeyList.get(i).getSettingValue();

			}
			if (settingKeyList.get(i).getSettingKey().equalsIgnoreCase("Mix")) {

				mixDeptId = settingKeyList.get(i).getSettingValue();

			}

		}
		
		
		System.out.println("Mix Dept Id "+mixDeptId);
		System.out.println("Prod Dept Id "+prodDeptId);

		System.out.println("Store Dept Id "+storeDeptId);
		
		map = new LinkedMultiValueMap<String, Object>();
		map.add("prodDeptId", prodDeptId);
		map.add("storeDeptId", storeDeptId);
		map.add("mixDeptId", mixDeptId);
		map.add("rmType", 1);
		bmsCurrentStockList=restTemplate.postForObject(Constants.url + "getCuurentBmsStock", map,
			GetBmsCurrentStockList.class);
		
		
		bmsCurrentStock=bmsCurrentStockList.getBmsCurrentStock();
		
		mav.addObject("stockList",bmsCurrentStock);
		
		System.out.println("BMS Stock List ="+bmsCurrentStockList.getBmsCurrentStock().toString());
		}
		return mav;

	}
	
	

}
