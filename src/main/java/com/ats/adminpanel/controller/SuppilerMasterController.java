package com.ats.adminpanel.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.DumpOrderList;
import com.ats.adminpanel.model.RawMaterial.Info;
import com.ats.adminpanel.model.supplierMaster.SupplierDetails;

@Controller
public class SuppilerMasterController {

	
	public static List<SupplierDetails> supplierDetailsList;
	@RequestMapping(value = "/showAddSupplier", method = RequestMethod.GET)
	public ModelAndView showAddSupplier(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addSupplier");


		return model;
	}

	
 
	
	@RequestMapping(value = "/addSupplier", method = RequestMethod.POST)
	public String addSupplier(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView();
		String strReturn="redirect:/showAddSupplier";
		System.out.println("In method");
		String suppId=request.getParameter("supp_id");
		System.out.println(suppId);
		String suppName=request.getParameter("supp_name");
		String suppAddr=request.getParameter("supp_addr");
		String suppCity=request.getParameter("supp_city");
		String suppState=request.getParameter("supp_state");
		String suppCountry=request.getParameter("supp_country");
		
		String suppMob1=request.getParameter("supp_mob1");
		String suppMob2=request.getParameter("supp_mob2");
		String suppMob3=request.getParameter("supp_mob3");
		String suppPhone1=request.getParameter("supp_phone1");
		String suppPhone2=request.getParameter("supp_phone2");
		String suppEmail1=request.getParameter("supp_email1");
		String suppEmail2=request.getParameter("supp_email2");
		String suppEmail3=request.getParameter("supp_email3");
		String suppEmail4=request.getParameter("supp_email4");
		String suppEmail5=request.getParameter("supp_email5");

		String suppGstin=request.getParameter("supp_gstin");
		String suppCPerson=request.getParameter("supp_c_person");
		String suppFdaLic=request.getParameter("supp_fdalic");
		
		String suppPayId=request.getParameter("supp_pay_id");
		String suppCreditDays=request.getParameter("supp_credit_days");
		String suppPanNo=request.getParameter("supp_panno");
		
		SupplierDetails supplierDetails=new SupplierDetails();
	
		if(suppId!=null)
		{
			int supp_Id=Integer.parseInt(suppId);
			supplierDetails.setSuppId(supp_Id);
			strReturn=new String("redirect:/showSupplierDetails");
			//model = new ModelAndView("masters/supplierDetails");
			model.addObject("supplierList", supplierDetailsList);
		}
		
		supplierDetails.setSuppAddr(suppAddr);
		supplierDetails.setSuppCity(suppCity);
		supplierDetails.setSuppState(suppState);
		supplierDetails.setSuppCountry(suppCountry);
		supplierDetails.setSuppCPerson(suppCPerson);
		supplierDetails.setSuppCreditDays(Integer.parseInt(suppCreditDays));
		supplierDetails.setSuppEmail1(suppEmail1);
		supplierDetails.setSuppEmail2(suppEmail2);
		supplierDetails.setSuppEmail3(suppEmail3);
		supplierDetails.setSuppEmail4(suppEmail4);
		supplierDetails.setSuppEmail5(suppEmail5);
		supplierDetails.setSuppFdaLic(suppFdaLic);
		supplierDetails.setSuppPhone2(suppPhone2);
		supplierDetails.setSuppPhone1(suppPhone1);
		supplierDetails.setSuppGstin(suppGstin);
		supplierDetails.setSuppMob1(suppMob1);
		supplierDetails.setSuppMob2(suppMob2);
		supplierDetails.setSuppMob3(suppMob3);
		
		supplierDetails.setSuppPayId(Integer.parseInt(suppPayId));
		supplierDetails.setSuppPanNo(suppPanNo);
		supplierDetails.setSuppName(suppName);
		supplierDetails.setDelStatus(0);
		
		System.out.println("Data  : "+supplierDetails);
		RestTemplate rest=new RestTemplate();
		Info info=rest.postForObject(Constants.url + "addNewSupplier", supplierDetails, Info.class);
		
		
		System.out.println("Response : " +info.toString());
		
		return strReturn;
	}
	@RequestMapping(value = "/showSupplierDetails", method = RequestMethod.GET)
	public ModelAndView showSupplierDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/supplierDetails");

		RestTemplate rest=new RestTemplate();
		  supplierDetailsList=rest.getForObject(Constants.url + "getAllSupplier",   List.class);

		model.addObject("supplierList", supplierDetailsList);
		return model;
	}
	
	
	@RequestMapping(value = "/getSupplierDetails", method = RequestMethod.GET)
	public @ResponseBody SupplierDetails getSupplierDetails(HttpServletRequest request,
		HttpServletResponse response) {
		System.out.println("In method");
		String supp_id=request.getParameter("selectedSupplier");
		int suppId=Integer.parseInt(supp_id);
		
		MultiValueMap<String, Object> map=new LinkedMultiValueMap<String, Object>();
		map.add("suppId", suppId);
		RestTemplate rest=new RestTemplate();
		SupplierDetails supplierDetails=rest.postForObject(Constants.url + "getSupplierDetails", map, SupplierDetails.class);
		
		System.out.println("Supplier data  : "+ supplierDetails);
		return supplierDetails;
	}
	
	@RequestMapping(value = "/deleteSupplier", method = RequestMethod.POST)
	public String deleteSupplier(HttpServletRequest request, HttpServletResponse response) {

		//ModelAndView model = new ModelAndView("masters/supplierDetails");

		String supp_id=request.getParameter("supp_id");
		int suppId=Integer.parseInt(supp_id);
		MultiValueMap<String, Object> map=new LinkedMultiValueMap<String,Object>();
		map.add("suppId", suppId);
		RestTemplate rest=new RestTemplate();
		  Info info=rest.postForObject(Constants.url + "deleteSupplier",map, Info.class);

		  System.out.println("response : "+ info.toString());
		//model.addObject("supplierList", supplierDetailsList);
		return "redirect:/showSupplierDetails";
	}
}
