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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.RawMaterial.GetRawmaterialByGroup;
import com.ats.adminpanel.model.RawMaterial.GetUomAndTax;
import com.ats.adminpanel.model.RawMaterial.Info;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetails;
import com.ats.adminpanel.model.RawMaterial.RawMaterialTaxDetails;
import com.ats.adminpanel.model.RawMaterial.RawMaterialUom;
import com.ats.adminpanel.model.RawMaterial.RmItemCategory;
import com.ats.adminpanel.model.RawMaterial.RmItemGroup;
import com.ats.adminpanel.model.RawMaterial.RmItemSubCategory;
import com.ats.adminpanel.model.RawMaterial.RmRateVerification;
import com.ats.adminpanel.model.franchisee.AllMenuResponse;
import com.ats.adminpanel.model.franchisee.Menu;
import com.ats.adminpanel.model.supplierMaster.SupplierDetails;
import com.ats.adminpanel.util.ImageS3Util; 


@Controller
public class RawMaterialController {
	
	
	@RequestMapping(value = "/showAddRawMaterial", method = RequestMethod.GET)
	public ModelAndView showRowMaterial(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/rawMaterial/addRawMaterial");
		RestTemplate rest=new RestTemplate();
		List<RmItemGroup> rmItemGroupList=rest.getForObject(Constants.url + "rawMaterial/getAllRmItemGroup", List.class);
		System.out.println("Group list :: "+rmItemGroupList.toString());
		List<RawMaterialUom> rawMaterialUomList=rest.getForObject(Constants.url + "rawMaterial/getRmUom", List.class);
		System.out.println("RM UOM data : "+rawMaterialUomList);
		List<RawMaterialTaxDetails> rawMaterialTaxDetailsList=rest.getForObject(Constants.url + "rawMaterial/getAllRmTax", List.class);
		System.out.println("RM Tax data : "+rawMaterialTaxDetailsList);
		
		
		model.addObject("rmUomList", rawMaterialUomList);
		model.addObject("rmTaxList", rawMaterialTaxDetailsList);
		model.addObject("groupList", rmItemGroupList);
 
		return model;
	}
	
	
	@RequestMapping(value = "/showRmRateVerification", method = RequestMethod.GET)
	public ModelAndView showRmRateVerification(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/rawMaterial/rmRateVerification");
			RestTemplate rest=new RestTemplate();
		List<RawMaterialDetails> rawMaterialDetailsList=rest.getForObject(Constants.url +"rawMaterial/getAllRawMaterial", List.class);
		
		System.out.println("RM Details : "+rawMaterialDetailsList.toString());
		  List<SupplierDetails> supplierDetailsList=rest.getForObject(Constants.url + "getAllSupplier",   List.class);

			model.addObject("supplierList", supplierDetailsList);
			model.addObject("RawmaterialList", rawMaterialDetailsList);
		

		return model;
	}
	
	@RequestMapping(value = "/addRawMaterial", method = RequestMethod.POST)
	public String addRawMaterial(HttpServletRequest request, HttpServletResponse response, @RequestParam("rm_icon") MultipartFile file)
	{
		ModelAndView model = new ModelAndView();
		
		String strReturn="redirect:/showAddRawMaterial";
		
		System.out.println("In method");
		
		String rmId=request.getParameter("rm_id");
		System.out.println(rmId);
		String rmName=request.getParameter("rm_name");
		String rmCode=request.getParameter("rm_code");
		String rmUomId=request.getParameter("rm_uom");
		String rmSpecification=request.getParameter("rm_specification");
		String rmGroup=request.getParameter("rm_group");
		
		String rmCat=request.getParameter("rm_cat");
		String rmSubCat=request.getParameter("rm_sub_cat");
		
		String rmWeight=request.getParameter("rm_weight");
		String rmPackQty=request.getParameter("rm_pack_qty");
		String rmRate=request.getParameter("rm_rate");
		String rmTaxId=request.getParameter("rm_tax_id");
		String rmMinQty=request.getParameter("rm_min_qty");
		String rmMaxQty=request.getParameter("rm_max_qty");
		String rmRolQty=request.getParameter("rm_rol_qty");

		String rmOpRate=request.getParameter("rm_op_rate");
		String rmOpQty=request.getParameter("rm_op_qty");
		String rmRecdQty=request.getParameter("rm_recd_qty");
		
		String rmIssQty=request.getParameter("rm_iss_qty");
		String rmRejQty=request.getParameter("rm_rej_qty");
		String rmCloQty=request.getParameter("rm_clo_qty");
		String rmIsCritical=request.getParameter("rm_is_critical");
		
		String extRmIcon=request.getParameter("prevImage");
	 
	
			if(!file.getOriginalFilename().equalsIgnoreCase("")) {
			
			System.out.println("Empty image");
			extRmIcon=ImageS3Util.uploadFrImage(file);
		}
			
		
		RawMaterialDetails rawMaterialDetails=new RawMaterialDetails();
	
		if(rmId!=null)
		{
			int rm_Id=Integer.parseInt(rmId);
			rawMaterialDetails.setRmId(rm_Id);
			strReturn=new String("redirect:/showRawMaterialDetails");
			//model = new ModelAndView("masters/supplierDetails");
			//model.addObject("supplierList", supplierDetailsList);
		}
		rawMaterialDetails.setRmName(rmName);
		rawMaterialDetails.setRmCloQty(Integer.parseInt(rmCloQty));
		rawMaterialDetails.setRmCode(rmCode);
		rawMaterialDetails.setRmTaxId(Integer.parseInt(rmTaxId));
		//rawMaterialDetails.setRmIcon(rmIcon);
		
		rawMaterialDetails.setRmIcon(extRmIcon);
		
		rawMaterialDetails.setRmIsCritical(Integer.parseInt(rmIsCritical));
		rawMaterialDetails.setRmIssQty(Integer.parseInt(rmIssQty));
		rawMaterialDetails.setRmMaxQty(Integer.parseInt(rmMaxQty));
		rawMaterialDetails.setRmMinQty(Integer.parseInt(rmMinQty));
		rawMaterialDetails.setRmOpQty(Integer.parseInt(rmOpQty));
		rawMaterialDetails.setRmSpecification(rmSpecification);
		rawMaterialDetails.setRmWeight(Integer.parseInt(rmWeight));
		rawMaterialDetails.setRmUomId(Integer.parseInt(rmUomId));
		rawMaterialDetails.setRmRolQty(Integer.parseInt(rmRolQty));
		
		rawMaterialDetails.setRmRejQty(Integer.parseInt(rmRejQty));
		rawMaterialDetails.setRmReceivedQty(Integer.parseInt(rmRecdQty));
		rawMaterialDetails.setRmRate(Integer.parseInt(rmRate));
		rawMaterialDetails.setRmPackQty(Integer.parseInt(rmPackQty));
		
		rawMaterialDetails.setRmOpRate(Integer.parseInt(rmOpRate));
		rawMaterialDetails.setGrpId(Integer.parseInt(rmGroup));
		rawMaterialDetails.setCatId(Integer.parseInt(rmCat));
		rawMaterialDetails.setSubCatId(Integer.parseInt(rmSubCat));
		 
	
		
		
		
		
		rawMaterialDetails.setDelStatus(0);
		
		System.out.println("Data  : "+rawMaterialDetails.toString());
		RestTemplate rest=new RestTemplate();
		Info info=rest.postForObject(Constants.url + "/rawMaterial/addRawMaterial", rawMaterialDetails, Info.class);
		
		
		System.out.println("Response : " +info.toString());
		
		return strReturn;
	}
	@RequestMapping(value = "/showRawMaterialDetails", method = RequestMethod.POST)
	public ModelAndView showRawMaterialDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/rawMaterial/showAllRawMaterial");

		String grp_id=request.getParameter("rm_group");
		int grpId=Integer.parseInt(grp_id);
		
		MultiValueMap<String , Object> map =new LinkedMultiValueMap<String, Object>();
		map.add("grpId", grpId);
		
		RestTemplate rest=new RestTemplate();
		
		List<GetRawmaterialByGroup> getRawmaterialByGroupList=rest.postForObject(Constants.url +"rawMaterial/getRawMaterialDetailByGroup", map,  List.class);
		
		List<RmItemGroup> rmItemGroupList=rest.getForObject(Constants.url + "rawMaterial/getAllRmItemGroup", List.class);
		System.out.println("Group list :: "+rmItemGroupList.toString());
		
		System.out.println("RM Details : "+getRawmaterialByGroupList.toString());
		
		model.addObject("groupList", rmItemGroupList);
		model.addObject("RawmaterialList", getRawmaterialByGroupList);
		return model;
	}
	@RequestMapping(value = "/showRawMaterial", method = RequestMethod.GET)
	public ModelAndView showRawMaterial(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/rawMaterial/showAllRawMaterial");

		RestTemplate rest=new RestTemplate();
		List<RmItemGroup> rmItemGroupList=rest.getForObject(Constants.url + "rawMaterial/getAllRmItemGroup", List.class);
		System.out.println("Group list :: "+rmItemGroupList.toString());
		
	 
		
		model.addObject("groupList", rmItemGroupList);
	 
		return model;
	}
	
	//---------------------------------------getRMCategory------------------------
	@RequestMapping(value = "/getRmCategory", method = RequestMethod.GET)
	public @ResponseBody List<RmItemCategory> getRmCategory(HttpServletRequest request,
		HttpServletResponse response) {
		
		 
		String selectedGroup=request.getParameter("grpId");
		int grpId=Integer.parseInt(selectedGroup);
		MultiValueMap<String, Object> map=new LinkedMultiValueMap<String, Object>();
		RestTemplate rest=new RestTemplate();
		
		map.add("grpId", grpId);
		List<RmItemCategory> rmItemCategoryList=new ArrayList<RmItemCategory>();
		try {
	
			 rmItemCategoryList = rest.postForObject(Constants.url + "rawMaterial/getRmItemCategories",map,
				List.class);
		
		 
	
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("List of Menu : "+ rmItemCategoryList.toString());
		
		return rmItemCategoryList;
		
	}
	
	//---------------------------------------getRMSubCategory------------------------
	@RequestMapping(value = "/getRmSubCategory", method = RequestMethod.GET)
	public @ResponseBody List<RmItemSubCategory> getRmSubCategory(HttpServletRequest request,
		HttpServletResponse response) {
		
		 
		String selectedCat=request.getParameter("catId");
		int catId=Integer.parseInt(selectedCat);
		MultiValueMap<String, Object> map=new LinkedMultiValueMap<String, Object>();
		RestTemplate rest=new RestTemplate();
		
		map.add("catId", catId);
		List<RmItemSubCategory> rmItemSubCategoryList=new ArrayList<RmItemSubCategory>();
		try {
	
			rmItemSubCategoryList = rest.postForObject(Constants.url + "rawMaterial/getRmItemSubCategories",map,
				List.class);
		
		 
	
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("List of Menu : "+ rmItemSubCategoryList.toString());
		
		return rmItemSubCategoryList;
		
	}
	
	
	//----------------------------------getRM ---------------------------------------------
	
	@RequestMapping(value = "/getRawMaterialDetails", method = RequestMethod.GET)
	public ModelAndView getRawMaterialDetails(HttpServletRequest request,
		HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("masters/rawMaterial/showRawMaterial");
		
		System.out.println("In method");
		String rm_id=request.getParameter("selectedRmId");
		int rmId=Integer.parseInt(rm_id);
		
		MultiValueMap<String, Object> map=new LinkedMultiValueMap<String, Object>();
		map.add("rmId", rmId);
		RestTemplate rest=new RestTemplate();
		RawMaterialDetails rawMaterialDetails=rest.postForObject(Constants.url + "rawMaterial/getRawMaterialDetail", map, RawMaterialDetails.class);
		//String rmIconStr=rawMaterialDetails.getRmIcon();
		//rawMaterialDetails.setRmIcon(Constants.ITEM_IMAGE_URL+rawMaterialDetails.getRmIcon());
		
		
		System.out.println("Raw Material data  : "+ rawMaterialDetails);
		List<RmItemGroup> rmItemGroupList=rest.getForObject(Constants.url + "rawMaterial/getAllRmItemGroup", List.class);
		System.out.println("Group list :: "+rmItemGroupList.toString());
		
		List<RawMaterialUom> rawMaterialUomList=rest.getForObject(Constants.url + "rawMaterial/getRmUom", List.class);
		System.out.println("RM UOM data : "+rawMaterialUomList);
		
		List<RawMaterialTaxDetails> rawMaterialTaxDetailsList=rest.getForObject(Constants.url + "rawMaterial/getAllRmTax", List.class);
		System.out.println("RM Tax data : "+rawMaterialTaxDetailsList);
		
		map=new LinkedMultiValueMap<String, Object>();
		map.add("catId", rawMaterialDetails.getCatId());
		List<RmItemSubCategory> rmItemSubCategoryList = rest.postForObject(Constants.url + "rawMaterial/getRmItemSubCategories",map,
				List.class);
		
		map=new LinkedMultiValueMap<String, Object>();
		map.add("grpId", rawMaterialDetails.getGrpId());
		List<RmItemCategory> rmItemCategoryList = rest.postForObject(Constants.url + "rawMaterial/getRmItemCategories",map,
					List.class);
		
		int rmUomId=rawMaterialDetails.getRmUomId();
		System.out.println("UOM ID : "+rmUomId);
		
		model.addObject("url",Constants.FR_IMAGE_URL);
		//model.addObject("rmIconStr", rmIconStr);
		model.addObject("rmUomList", rawMaterialUomList);
		model.addObject("rmTaxList", rawMaterialTaxDetailsList);
		model.addObject("groupList", rmItemGroupList);
		model.addObject("rmUomIdInt", rmUomId);
		model.addObject("rmItemCategoryList", rmItemCategoryList);
		model.addObject("rmItemSubCategoryList", rmItemSubCategoryList);
		
		
		model.addObject("rawMaterialDetails", rawMaterialDetails);
		return model;
	}
	
	
	@RequestMapping(value = "/deleteRawMaterial", method = RequestMethod.POST)
	public String deleteRawMaterial(HttpServletRequest request, HttpServletResponse response) {

		 

		String rm_id=request.getParameter("rm_id");
		int rmId=Integer.parseInt(rm_id);
		MultiValueMap<String, Object> map=new LinkedMultiValueMap<String,Object>();
		map.add("rmId", rmId);
		RestTemplate rest=new RestTemplate();
		  Info info=rest.postForObject(Constants.url + "rawMaterial/deleteRawMaterial",map, Info.class);

		  System.out.println("response : "+ info.toString());
		 
		return "redirect:/showRawMaterialDetails";
	}
	@RequestMapping(value = "/showAddRmTax", method = RequestMethod.GET)
	public ModelAndView showAddRmTax(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/rawMaterial/addRmTax");
		
		

		return model;
	}
	
	
	@RequestMapping(value = "/addRmTax", method = RequestMethod.POST)
	public String addRmTax(HttpServletRequest request, HttpServletResponse response) {

		 

		String taxDesc=request.getParameter("tax_desc");
		String igstPer=request.getParameter("igst_per");
		String sgstPer=request.getParameter("sgst_per");
		String cgstPer=request.getParameter("cgst_per");
		String tax_id=request.getParameter("tax_id");
		
		RawMaterialTaxDetails rawMaterialTaxDetails=new RawMaterialTaxDetails();
		
		if(tax_id!=null)
		{
		int taxId=Integer.parseInt(tax_id);
		rawMaterialTaxDetails.setTaxId(taxId);
		}
		
		rawMaterialTaxDetails.setCgstPer(Float.parseFloat(cgstPer));
		rawMaterialTaxDetails.setIgstPer(Float.parseFloat(igstPer));
		rawMaterialTaxDetails.setSgstPer(Float.parseFloat(sgstPer));
		rawMaterialTaxDetails.setTaxDesc(taxDesc);
		
		
		RestTemplate rest=new RestTemplate();
		  Info info=rest.postForObject(Constants.url + "rawMaterial/insertRmTax",rawMaterialTaxDetails, Info.class);

		  System.out.println("response : "+ info.toString());
		 
		return "redirect:/showAddRmTax";
	}
	
	@RequestMapping(value = "/showAddRmUmo", method = RequestMethod.GET)
	public ModelAndView showAddRmUmo(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/rawMaterial/addRmUom");
		
		

		return model;
	}
	@RequestMapping(value = "/addRmUom", method = RequestMethod.POST)
	public String addRmUom(HttpServletRequest request, HttpServletResponse response) {

		 

		String uom=request.getParameter("uom");
		
		String umo_id=request.getParameter("umo_id");
		
		RawMaterialUom rawMaterialUom=new RawMaterialUom();
		
		if(umo_id!=null)
		{
		int umoId=Integer.parseInt(umo_id);
		rawMaterialUom.setUomId(umoId);
		}
		
		rawMaterialUom.setUom(uom);
		
		
		
		RestTemplate rest=new RestTemplate();
		  Info info=rest.postForObject(Constants.url + "rawMaterial/insertRmUom",rawMaterialUom, Info.class);

		  System.out.println("response : "+ info.toString());
		 
		return "redirect:/showAddRmUmo";
	}
	
	//---------------------------------------getRMCategory------------------------
		@RequestMapping(value = "/getRmRateVerification", method = RequestMethod.GET)
		public @ResponseBody RmRateVerification getRmRateVerification(HttpServletRequest request,
			HttpServletResponse response) {
			
			 
			String supp_id=request.getParameter("supp_id");
			String rm_id=request.getParameter("rm_id");
			
			int suppId=Integer.parseInt(supp_id);
			int rmId=Integer.parseInt(rm_id);
			MultiValueMap<String, Object> map=new LinkedMultiValueMap<String, Object>();
			RestTemplate rest=new RestTemplate();
			
			map.add("suppId", suppId);
			map.add("rmId", rmId);
			RmRateVerification rmRateVerification=new RmRateVerification();
			try {
		
				rmRateVerification = rest.postForObject(Constants.url + "rawMaterial/getRmRateVerification",map,
						RmRateVerification.class);
			
			 
		
				
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			System.out.println("List of Menu : "+ rmRateVerification.toString());
			
			return rmRateVerification;
			
		}
		//-----------------------------------------insert RM Rate Verification-------------------
		
		@RequestMapping(value = "/submitRmRateVerification", method = RequestMethod.POST)
		public String submitRmRateVerification(HttpServletRequest request, HttpServletResponse response) {

			 

			String currRateDate=request.getParameter("curr_rate_date");
			String currRateTaxExtra=request.getParameter("curr_rate_tax_extra");
			String currRateTaxIncl=request.getParameter("curr_rate_tax_incl");
			System.out.println(" Currr Date   extra : "+currRateTaxExtra);
			
			String rateDate=request.getParameter("rate_date");
			System.out.println(" rateDate     extra : "+rateDate);
			String rateTaxExtra=request.getParameter("tax_extra");
			System.out.println("Date 1  extra : "+rateTaxExtra);
			String rateTaxIncl=request.getParameter("tax_incl");
			System.out.println(" rateTaxIncl     extra : "+rateTaxIncl);
			
			String date1=request.getParameter("rate_date1");
			System.out.println(" date1  fff    : "+date1);
			String rateTaxIncl1=request.getParameter("tax_incl1");
			System.out.println(" rateTaxIncl1     extra : "+rateTaxIncl1);
			String rateTaxExtra1=request.getParameter("tax_extra1");
			System.out.println(" rateTaxExtra1     extra : "+rateTaxExtra1);
			
			
			/*String date2=request.getParameter("date2");
			String rateTaxIncl2=request.getParameter("rate_tax_incl2");
			String rateTaxExtra2=request.getParameter("rate_tax_extra2");*/
			
			String rm_rate_ver_id=request.getParameter("rm_rate_ver_id");
			String rm_tax_id=request.getParameter("tax_id");
			
			String supp_id=request.getParameter("supp_id");
			String rm_id=request.getParameter("rm_id");
			
				int rmId=Integer.parseInt(rm_id);
				int suppId=Integer.parseInt(supp_id);
				int taxId=Integer.parseInt(rm_tax_id);
				
			RmRateVerification rmRateVerification=new RmRateVerification();
			
			if(rm_rate_ver_id!=null)
			{
			int rmRateVerId=Integer.parseInt(rm_rate_ver_id);
			rmRateVerification.setRmRateVerId(rmRateVerId);
			}
			
			rmRateVerification.setRateDate(currRateDate);
			rmRateVerification.setRateTaxExtra(Float.parseFloat(currRateTaxExtra));
			rmRateVerification.setRateTaxIncl(Float.parseFloat(currRateTaxIncl));
			if(rateTaxExtra!=null)
			{
			rmRateVerification.setDate1(rateDate);
			rmRateVerification.setRateTaxExtra1(Float.parseFloat(rateTaxExtra));
			rmRateVerification.setRateTaxIncl1(Float.parseFloat(rateTaxIncl));
			rmRateVerification.setDate2(date1);
			rmRateVerification.setRateTaxExtra2(Float.parseFloat(rateTaxExtra1));
			rmRateVerification.setRateTaxIncl2(Float.parseFloat(rateTaxIncl1));
			}
			else {
				rmRateVerification.setDate1(currRateDate);
				rmRateVerification.setRateTaxExtra1(Float.parseFloat(currRateTaxExtra));
				rmRateVerification.setRateTaxIncl1(Float.parseFloat(currRateTaxIncl));
				rmRateVerification.setDate2(currRateDate);
				rmRateVerification.setRateTaxExtra2(Float.parseFloat(currRateTaxExtra));
				rmRateVerification.setRateTaxIncl2(Float.parseFloat(currRateTaxIncl));
			}
			rmRateVerification.setRmId(rmId);
			rmRateVerification.setSuppId(suppId);
			rmRateVerification.setTaxId(taxId);
			
			
			RestTemplate rest=new RestTemplate();
			  Info info=rest.postForObject(Constants.url + "rawMaterial/insertRmRateVerification",rmRateVerification, Info.class);

			  System.out.println("response : "+ info.toString());
			 
			return "redirect:/showRmRateVerification";
		}
		
		
		//---------------------------------------getRMUomTax------------------------
				@RequestMapping(value = "/getUomTax", method = RequestMethod.GET)
				public @ResponseBody GetUomAndTax getUomTax(HttpServletRequest request,
					HttpServletResponse response) {
					
					 
				 
					String rm_id=request.getParameter("rmId");
					
				 
					int rmId=Integer.parseInt(rm_id);
					MultiValueMap<String, Object> map=new LinkedMultiValueMap<String, Object>();
					RestTemplate rest=new RestTemplate();
					
					 
					map.add("rmId", rmId);
					
					GetUomAndTax getUomAndTax=new GetUomAndTax();
					try {
				
						getUomAndTax = rest.postForObject(Constants.url + "rawMaterial/getUomAndTax",map,
								GetUomAndTax.class);
					
					 
				
						
					}catch (Exception e) {
						System.out.println(e.getMessage());
					}
					
					System.out.println("List  : "+ getUomAndTax.toString());
					
					return getUomAndTax;
}
}
