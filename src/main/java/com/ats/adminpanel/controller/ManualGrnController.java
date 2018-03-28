package com.ats.adminpanel.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.FrMenu;
import com.ats.adminpanel.model.grngvn.GetBillsForFr;
import com.ats.adminpanel.model.grngvn.GetBillsForFrList;
import com.ats.adminpanel.model.grngvn.GetGrnConfResponse;
import com.ats.adminpanel.model.grngvn.GetGrnItemConfig;
import com.ats.adminpanel.model.stock.PostFrItemStockDetail;

@Controller
@Scope("session")

public class ManualGrnController {

	List<GetBillsForFr> frBillList;
	GetBillsForFrList billsForFr = new GetBillsForFrList();

	@RequestMapping(value = "/showManGrn", method = RequestMethod.GET)
	public ModelAndView showManGrn(HttpServletRequest request, HttpServletResponse response) {

		System.err.println("Inside showManGrn");
		ModelAndView model = new ModelAndView("grngvn/manGrn");
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		AllFrIdNameList allFrIdNameList = new AllFrIdNameList();
		try {

			allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

		} catch (Exception e) {
			System.out.println("Exception in getAllFrIdName" + e.getMessage());
			e.printStackTrace();

		}

		model.addObject("frList", allFrIdNameList.getFrIdNamesList());

		return model;

	}

	String frId;

	@RequestMapping(value = "/getBillForFr", method = RequestMethod.GET)
	public @ResponseBody List<GetBillsForFr> billForFr(HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		frId = request.getParameter("fr_id");

		map.add("frId", frId);
		java.util.Date cDate = new java.util.Date();

		String curDate = new SimpleDateFormat("dd-MM-yyyy").format(cDate);

		map.add("curDate", curDate);

		billsForFr = new GetBillsForFrList();

		billsForFr = restTemplate.postForObject(Constants.url + "getBillsForFr", map, GetBillsForFrList.class);
		frBillList = new ArrayList<>();
		frBillList = billsForFr.getGetBillsForFr();

		System.err.println("Bills received " + frBillList.toString());
		return frBillList;

	}

	GetGrnConfResponse grnGvnConfResponse=new GetGrnConfResponse();

	List<GetGrnItemConfig> grnConfList,selectedGrn;

	@RequestMapping(value = "/getItemsByBillNo", method = RequestMethod.GET)
	public @ResponseBody List<GetGrnItemConfig> getItemsByBillNo(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("In Jax /getItemsByBillNo 1");
		try {

			System.err.println("In Jax /getItemsByBillNo 2 try");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String billNo = request.getParameter("bill_no");

			System.out.println("req param billNo " + billNo);

			RestTemplate restTemplate = new RestTemplate();

			map.add("frId", frId);
			map.add("billNo", billNo);

			grnGvnConfResponse = restTemplate.postForObject(Constants.url + "getItemsForManGrn", map,
					GetGrnConfResponse.class);
			grnConfList = new ArrayList<>();

			grnConfList = grnGvnConfResponse.getGetGrnItemConfigs();

			System.out.println("bill table data " + grnConfList.toString());

		} catch (Exception e) {
			System.out.println("Exception in getItemsByBillNo" + e.getMessage());
			e.printStackTrace();
		}
		return grnConfList;

	}
	
	
	@RequestMapping(value = "/insertManGrn", method = RequestMethod.POST)
	public ModelAndView insertManGrn(HttpServletRequest request, HttpServletResponse response) {

		System.err.println("Inside insertManGrn");
		ModelAndView model = new ModelAndView("grngvn/manGrn");
		selectedGrn=new ArrayList<>();
		try {
		
			for(int i=0;i<grnConfList.size();i++) {
			String billNo =request.getParameter(""+grnConfList.get(i).getBillDetailNo());
			
			String grnQty=request.getParameter("qty"+billNo);
			
			if(billNo!=null) {
				
				grnConfList.get(i).setAutoGrnQty(Integer.parseInt(grnQty));
				selectedGrn.add(grnConfList.get(i));
			}
			System.err.println("grnQty" + grnQty);
			
			System.err.println("Bill no "+ billNo);
				System.err.println("");	
			}
			
			System.err.println("selected GRn " +selectedGrn.toString());
			
			
			

		}
		catch (Exception e) {
			
		System.err.println("Exception in insert Man GRN " + e.getMessage());
		e.printStackTrace();
		
		}
		
		
		
		return model;
		
	}

}
