package com.ats.adminpanel.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.supplierMaster.SupplierDetails;
import com.ats.adminpanel.model.supplierMaster.TransporterList;

@Controller
public class GateEntryController {

	
	@RequestMapping(value = "/gateEntries", method = RequestMethod.GET)
	public ModelAndView gateEntries(HttpServletRequest request, HttpServletResponse response) {
		Constants.mainAct = 17;
		Constants.subAct=184;
		
		ModelAndView model = new ModelAndView("masters/gateEntryList");


		return model;
	}
	@RequestMapping(value = "/addGateEntry", method = RequestMethod.GET)
	public ModelAndView addGateEntry(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/gateEntry");
		try {
		RestTemplate rest = new RestTemplate();
		List<SupplierDetails> supplierDetailsList = rest.getForObject(Constants.url + "/getAllSupplier", List.class);

		TransporterList transporterList = rest.getForObject(Constants.url + "/showTransporters",
				TransporterList.class);
		
		
		System.out.println("Transporter List Response:" + transporterList.toString());

		model.addObject("supplierList", supplierDetailsList);

		model.addObject("transporterList", transporterList.getTransporterList());

		} catch (Exception e) {
			System.out.println("Exception In Add Gate Entry :" + e.getMessage());

		}

		return model;
	}
	
}
