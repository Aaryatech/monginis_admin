package com.ats.adminpanel.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.item.FrItemStockConfigureList;
import com.ats.adminpanel.model.production.GetProdPlanHeader;
import com.ats.adminpanel.model.production.GetProdPlanHeaderList;
import com.ats.adminpanel.model.production.PostProdPlanHeader;
import com.ats.adminpanel.model.production.mixing.temp.GetSFMixingForBomList;
import com.ats.adminpanel.model.production.mixing.temp.GetSFPlanDetailForMixing;
import com.ats.adminpanel.model.production.mixing.temp.GetSFPlanDetailForMixingList;
import com.ats.adminpanel.model.production.mixing.temp.ProdMixingReqP1;
import com.ats.adminpanel.model.production.mixing.temp.ProdMixingReqP1List;
import com.ats.adminpanel.model.production.mixing.temp.TempMixing;

@Controller
@Scope("session")
public class ProductionApplController {

	@RequestMapping(value = "/generateMixingForProduction/{type}", method = RequestMethod.GET)
	public ModelAndView prodListForGenerateMixingForProd(@PathVariable("type")int type,HttpServletRequest request, HttpServletResponse response) {

		String fromDate,toDate;
		ModelAndView model = new ModelAndView("production/prodList");
		try {
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			if (request.getParameter("from_date") == null || request.getParameter("to_date") == null) {
				Date date = new Date();
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				fromDate = df.format(date);
				toDate = df.format(date);
				System.out.println("From Date And :" + fromDate + "ToDATE" + toDate);

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

				System.out.println("inside if ");
			} else {
				fromDate = request.getParameter("from_date");
				toDate = request.getParameter("to_date");

				System.out.println("inside Else ");

				System.out.println("fromDate " + fromDate);

				System.out.println("toDate " + toDate);

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

			}

			GetProdPlanHeaderList prodHeader = restTemplate.postForObject(Constants.url + "getProdPlanHeader", map,
					GetProdPlanHeaderList.class);

			List<GetProdPlanHeader> prodPlanHeaderList = new ArrayList<>();

			prodPlanHeaderList = prodHeader.getProdPlanHeader();

			System.out.println("prod header " + prodPlanHeaderList.toString());
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
			model.addObject("type", type);
			model.addObject("planHeader", prodPlanHeaderList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;

	}
	@RequestMapping(value = "/showDetailsForLayering", method = RequestMethod.GET)
	public List<GetSFPlanDetailForMixing> showDetailsForLayering(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		
		List<GetSFPlanDetailForMixing> sfPlanDetailForBom=null;
		try {
			int prodHeaderId=Integer.parseInt(request.getParameter("prodHeaderId"));
			String toDept=request.getParameter("toDept");
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
		
            map.add("settingKeyList", toDept);
            FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
		    FrItemStockConfigureList.class);
            map = new LinkedMultiValueMap<String, Object>();
				map.add("headerId", prodHeaderId);
				map.add("deptId", settingList.getFrItemStockConfigure().get(0).getSettingValue());
				GetSFPlanDetailForMixingList getSFPlanDetailForBomList = restTemplate.postForObject(Constants.url + "getSfPlanDetailForBom", map,
						GetSFPlanDetailForMixingList.class);

		sfPlanDetailForBom = getSFPlanDetailForBomList.getSfPlanDetailForMixing();

		
		} catch (Exception e) {
			e.printStackTrace();
	}
		
	return sfPlanDetailForBom;

	}
}
