package com.ats.adminpanel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.RawMaterial.Info; 


@Controller
public class RawMaterialController {
	
	
	@RequestMapping(value = "/showRowMaterial", method = RequestMethod.GET)
	public ModelAndView showRowMaterial(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/rowMaterialMaster");

 
		return model;
	}
	
	
	@RequestMapping(value = "/showRmRateVerification", method = RequestMethod.GET)
	public ModelAndView showRmRateVerification(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/rmRateVerification");


		return model;
	}
	
	

}
