package com.ats.adminpanel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.RawMaterial.ItemSfHeader;
import com.ats.adminpanel.model.RawMaterial.ItemSfHeaderList;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetails;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetailsList;
import com.ats.adminpanel.model.RawMaterial.RawMaterialUom;
import com.ats.adminpanel.model.RawMaterial.RawMaterialUomList;
import com.ats.adminpanel.model.franchisee.CommonConf;

@Controller
public class TempManualBom {
	
	public static  List<CommonConf> commonConfs=new ArrayList<CommonConf>();

	@RequestMapping(value = "/manualBom", method = RequestMethod.GET)
	public ModelAndView manualBom(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView modelAndView = new ModelAndView("production/manualBom");
		System.out.println("inside manual BoM");
		RestTemplate restTemplate = new RestTemplate();
		
		return modelAndView;
		
	
}
	
	
	@RequestMapping(value = "/getMaterial", method = RequestMethod.GET)
	public @ResponseBody List<CommonConf> getRawMaterialList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView("production/manualBom");
	
	int rmType=Integer.parseInt(request.getParameter("material_type"));
	   System.out.println("rmType:"+rmType);

	RestTemplate rest=new RestTemplate();
	
	List<CommonConf> commonConfList=new ArrayList<CommonConf>();

	if(rmType==1)
	{
		System.out.println("inside if");
	try
	{
	RawMaterialDetailsList rawMaterialDetailsList=rest.getForObject(Constants.url +"rawMaterial/getAllRawMaterial", RawMaterialDetailsList.class);
	
	System.out.println("RM Details : "+rawMaterialDetailsList.toString());
	
	   for(RawMaterialDetails rawMaterialDetails:rawMaterialDetailsList.getRawMaterialDetailsList())
	   {
		   CommonConf commonConf=new CommonConf();
		   
		   commonConf.setId(rawMaterialDetails.getRmId());
		   commonConf.setName(rawMaterialDetails.getRmName());
		   commonConf.setRmUomId(rawMaterialDetails.getRmUomId());
		   
		   commonConfList.add(commonConf);
		   commonConfs.add(commonConf);
	   }
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	   System.out.println("Common Rm List1:"+commonConfList.toString());
	   
	}
	else
	{
		//if rmType=2,call Semi finished service
		ItemSfHeaderList itemHeaderDetailList = rest.getForObject(Constants.url + "rawMaterial/getItemSfHeaders", ItemSfHeaderList.class);
		
		System.out.println("ItemSfHeaderList Details : "+itemHeaderDetailList.toString());
		
		   for(ItemSfHeader itemSfHeader:itemHeaderDetailList.getItemSfHeaderList())
		   {
			   CommonConf commonConf=new CommonConf();
			   
			   commonConf.setId(itemSfHeader.getSfId());
			   commonConf.setName(itemSfHeader.getSfName());
			   commonConf.setRmUomId(itemSfHeader.getSfUomId());

			   commonConfList.add(commonConf);
			   commonConfs.add(commonConf);

		   }
		   System.out.println("Common Rm List2:"+commonConfList.toString());

	}
	
	return commonConfList;
	}
	
	
	@RequestMapping(value = "/getMatUom", method = RequestMethod.GET)
	public @ResponseBody RawMaterialUom getMatUom(HttpServletRequest request, HttpServletResponse response) {
		
		
		RawMaterialUom uomObject=null;
		CommonConf cf=new CommonConf();
		 String matNameId=request.getParameter("rm_material_name");
			
		 int unitId=0;
		 for(int i=0;i<commonConfs.size();i++) {
			 
			if(commonConfs.get(i).getId()==Integer.parseInt(matNameId)) {
				
				 unitId=commonConfs.get(i).getRmUomId();
				
			}
			 
		 }
		 
		 RestTemplate rest=new RestTemplate();
		 
		 System.out.println("rm mat name "+matNameId);
		
		 RawMaterialUomList rawMaterialUomList=rest.getForObject(Constants.url + "rawMaterial/getRmUomList", RawMaterialUomList.class);
			
		 
		 List<RawMaterialUom> uomList = rawMaterialUomList.getRawMaterialUom();
		 
		 
			for(int i=0;i<uomList.size();i++) {
				
				RawMaterialUom uom=uomList.get(i);
				
				if(uom.getUomId()==unitId) {
					 uomObject=uomList.get(i);
				}
					
				System.out.println("raw mat uom new  = "+uomObject.toString());
				
			}
				
		return uomObject;

	}
	
	
}
