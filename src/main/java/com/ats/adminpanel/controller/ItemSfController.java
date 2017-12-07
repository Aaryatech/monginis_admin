package com.ats.adminpanel.controller;

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
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.RawMaterial.GetItemSfHeader;
import com.ats.adminpanel.model.RawMaterial.GetSfType;
import com.ats.adminpanel.model.RawMaterial.ItemSfHeader;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetails;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetailsList;
import com.ats.adminpanel.model.RawMaterial.RawMaterialUom;

@Controller
public class ItemSfController {
	List<GetSfType> sfTypeList;
	List<RawMaterialUom> rawMaterialUomList;
	
	RawMaterialDetailsList rawMaterialDetailsList;
	
	List<GetItemSfHeader> itemHeaderList;
	
	@RequestMapping(value = "/showItemSf", method = RequestMethod.GET)
	public ModelAndView showItemSf(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct = 4;
		Constants.subAct = 41;

		ModelAndView model = new ModelAndView("masters/rawMaterial/itemSf");
		try {

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();


			rawMaterialUomList = restTemplate.getForObject(Constants.url + "rawMaterial/getRmUom",
					List.class);
			
				map.add("delStatus", 0);
				
			 sfTypeList=restTemplate.postForObject(Constants.url+"getSfType",map,List.class);
			
			System.out.println("sfTypeList data : " + sfTypeList);

			System.out.println("RM UOM data : " + rawMaterialUomList);
			
		 itemHeaderList=restTemplate.getForObject(Constants.url+"getItemSfHeader",List.class);

			System.out.println(" Header response "+itemHeaderList);
			
			model.addObject("itemHeaderList",itemHeaderList);

			model.addObject("rmUomList", rawMaterialUomList);
			
			model.addObject("sfTypeList", sfTypeList);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return model;

	}

	@RequestMapping(value = "/insertSfItemHeader", method = RequestMethod.POST)
	public ModelAndView insertSfItemHeader(HttpServletRequest request, HttpServletResponse response) {
		
		Constants.mainAct=4;
		Constants.subAct=41;

		ModelAndView model=new ModelAndView("masters/rawMaterial/itemSf");
		
		try {
			
		RestTemplate restTemplate=new RestTemplate();
		
		String sfItemName=request.getParameter("sf_item_name");
		
		String sfType=request.getParameter("sf_item_type");
		
		
		int sfItemUoM=Integer.parseInt(request.getParameter("sf_item_uom"));
				
		int sfItewWeight=Integer.parseInt(request.getParameter("sf_item_weight"));
		
		int sfStockQty=Integer.parseInt(request.getParameter("sf_stock_qty"));
						
		float sfReorderQty=Float.parseFloat(request.getParameter("sf_reorder_level_qty"));
		
		float sfMinQty=Float.parseFloat(request.getParameter("sf_min_qty"));
		
		float sfMaxQty=Float.parseFloat(request.getParameter("sf_min_qty"));
		
		ItemSfHeader header=new ItemSfHeader();
		header.setDelStatus(0);
		
		header.setMaxLevelQty(sfMaxQty);
		header.setMinLevelQty(sfMinQty);
		header.setReorderLevelQty(sfReorderQty);
		header.setSfName(sfItemName);
		header.setSfType(sfType);
		header.setSfUomId(sfItemUoM);
		header.setSfWeight(sfItewWeight);
		header.setStockQty(sfStockQty);
		
		System.out.println("header= "+header.toString());

		Info info=restTemplate.postForObject(Constants.url+"postSfItemHeader",header,Info.class);
		
		System.out.println("Insert Header response "+info.toString());
		
		//List<GetItemSfHeader> itemHeaderList=restTemplate.getForObject(Constants.url+"getItemSfHeader",List.class);

		//System.out.println(" Header response "+itemHeaderList);
		
		model.addObject("itemHeaderList",itemHeaderList);
		model.addObject("rmUomList", rawMaterialUomList);
		model.addObject("sfTypeList", sfTypeList);
		
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("ex in header insert = "+e.getMessage());
			
		}
		
	return model;
	
	}
	
	
	@RequestMapping(value = "/showAddSfItemDetail/{sfId}", method = RequestMethod.GET)
	public ModelAndView showAddSfItemDetail(@PathVariable int sfId,HttpServletRequest request, HttpServletResponse response) {
		
		Constants.mainAct=4;
		Constants.subAct=41;
			System.out.println("Inside show details");
		ModelAndView model=new ModelAndView("masters/rawMaterial/itemSfDetail");
		
		try {
			System.out.println("Ganesh");
		RestTemplate restTemplate=new RestTemplate();
		
		 rawMaterialDetailsList=restTemplate.getForObject(Constants.url +"rawMaterial/getAllRawMaterial", RawMaterialDetailsList.class);
		System.out.println("LIst :"+rawMaterialDetailsList.toString());
		
		model.addObject("rmDetailList",rawMaterialDetailsList.getRawMaterialDetailsList());
		
		}catch (Exception e) {
			System.out.println("Error in getting raw Material Details ");
			System.out.println(e.getMessage());
				e.printStackTrace();

		}
		return model;
	}	
		
}
