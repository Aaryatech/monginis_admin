package com.ats.adminpanel.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.VpsImageUpload;
import com.ats.adminpanel.model.RawMaterial.ItemDetail;
import com.ats.adminpanel.model.RawMaterial.ItemDetailList;
import com.ats.adminpanel.model.RawMaterial.GetRawMaterialDetailList;
import com.ats.adminpanel.model.RawMaterial.GetRawmaterialByGroup;
import com.ats.adminpanel.model.RawMaterial.GetUomAndTax;
import com.ats.adminpanel.model.RawMaterial.Info;
import com.ats.adminpanel.model.RawMaterial.ItemSfDetail;
import com.ats.adminpanel.model.RawMaterial.ItemSfHeader;
import com.ats.adminpanel.model.RawMaterial.ItemSfHeaderList;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetails;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetailsList;
import com.ats.adminpanel.model.RawMaterial.RawMaterialTaxDetails;
import com.ats.adminpanel.model.RawMaterial.RawMaterialTaxDetailsList;
import com.ats.adminpanel.model.RawMaterial.RawMaterialUom;
import com.ats.adminpanel.model.RawMaterial.RawMaterialUomList;
import com.ats.adminpanel.model.RawMaterial.RmItemCatList;
import com.ats.adminpanel.model.RawMaterial.RmItemCategory;
import com.ats.adminpanel.model.RawMaterial.RmItemGroup;
import com.ats.adminpanel.model.RawMaterial.RmItemSubCatList;
import com.ats.adminpanel.model.RawMaterial.RmItemSubCategory;
import com.ats.adminpanel.model.RawMaterial.RmRateVerification;
import com.ats.adminpanel.model.franchisee.AllMenuResponse;
import com.ats.adminpanel.model.franchisee.CommonConf;
import com.ats.adminpanel.model.franchisee.Menu;
import com.ats.adminpanel.model.item.AllItemsListResponse;
import com.ats.adminpanel.model.item.ErrorMessage;
import com.ats.adminpanel.model.item.Item;
import com.ats.adminpanel.model.supplierMaster.SupplierDetails;

@Controller
@Scope("session")
public class RawMaterialController {
	
	public  List<Item> itemList;
	public  List<ItemDetail> itemDetailList;
	public  List<CommonConf> commonConfs=new ArrayList<CommonConf>();
	public  int globalId=0;
	
	@RequestMapping(value = "/showAddRawMaterial", method = RequestMethod.GET)
	public ModelAndView showRowMaterial(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/rawMaterial/addRawMaterial");
		Constants.mainAct=9;
		Constants.subAct=49;
		
		RestTemplate rest=new RestTemplate();
		try {
		List<RmItemGroup> rmItemGroupList=rest.getForObject(Constants.url + "rawMaterial/getAllRmItemGroup", List.class);
		System.out.println("Group list :: "+rmItemGroupList.toString());
		List<RawMaterialUom> rawMaterialUomList=rest.getForObject(Constants.url + "rawMaterial/getRmUom", List.class);
		System.out.println("RM UOM data : "+rawMaterialUomList);
		List<RawMaterialTaxDetails> rawMaterialTaxDetailsList=rest.getForObject(Constants.url + "rawMaterial/getAllRmTax", List.class);
		System.out.println("RM Tax data : "+rawMaterialTaxDetailsList);
		
		
		model.addObject("rmUomList", rawMaterialUomList);
		model.addObject("rmTaxList", rawMaterialTaxDetailsList);
		model.addObject("groupList", rmItemGroupList);
		}
		catch(Exception e)
		{
			System.out.println("Exception in /showAddRawMaterial"+e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/showRmRateVerification", method = RequestMethod.GET)
	public ModelAndView showRmRateVerification(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/rawMaterial/rmRateVerification");
		Constants.mainAct=14;
		Constants.subAct=85;
			RestTemplate rest=new RestTemplate();
			try {
			RawMaterialDetailsList rawMaterialDetailsList=rest.getForObject(Constants.url +"rawMaterial/getAllRawMaterial", RawMaterialDetailsList.class);
		
		System.out.println("RM Details : "+rawMaterialDetailsList.toString());
		  List<SupplierDetails> supplierDetailsList=rest.getForObject(Constants.url + "getAllSupplier",   List.class);

			model.addObject("supplierList", supplierDetailsList);
			model.addObject("RawmaterialList", rawMaterialDetailsList.getRawMaterialDetailsList());
			}
			catch(Exception e)
			{
				System.out.println("Exception In /showRmRateVerification"+e.getMessage());
			}

		return model;
	}
	
	@RequestMapping(value = "/addRawMaterial", method = RequestMethod.POST)
	public ModelAndView addRawMaterial(HttpServletRequest request, HttpServletResponse response, @RequestParam("rm_icon") List<MultipartFile> file)
	{
		ModelAndView model = new ModelAndView("masters/rawMaterial/showAllRawMaterial");
		
		try {
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
	 
	
			if(!file.get(0).getOriginalFilename().equalsIgnoreCase("")) {
			
			System.out.println("Empty image");
			//extRmIcon=ImageS3Util.uploadFrImage(file);
			
			VpsImageUpload upload = new VpsImageUpload();

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			System.out.println(sdf.format(cal.getTime()));


			String curTimeStamp = sdf.format(cal.getTime());
			extRmIcon=null;
			try {
				extRmIcon=curTimeStamp + "-" + file.get(0).getOriginalFilename();
				upload.saveUploadedFiles(file, Constants.RAW_MAT_IMAGE_TYPE, curTimeStamp + "-" + file.get(0).getOriginalFilename());
				System.out.println("upload method called for image Upload " + file.toString());
				
			} catch (IOException e) {
				
				System.out.println("Exce in File Upload In Sp Cake  Insert " + e.getMessage());
				e.printStackTrace();
			}
		}
		
			
			
			VpsImageUpload upload = new VpsImageUpload();

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			System.out.println(sdf.format(cal.getTime()));


			String curTimeStamp = sdf.format(cal.getTime());
			extRmIcon=null;
			try {
				extRmIcon=curTimeStamp + "-" + file.get(0).getOriginalFilename();
				upload.saveUploadedFiles(file, Constants.RAW_MAT_IMAGE_TYPE, curTimeStamp + "-" + file.get(0).getOriginalFilename());
				System.out.println("upload method called for image Upload " + file.toString());
				
			} catch (IOException e) {
				
				System.out.println("Exce in File Upload In Sp Cake  Insert " + e.getMessage());
				e.printStackTrace();
			}	
		 
			
		
		RawMaterialDetails rawMaterialDetails=new RawMaterialDetails();
	
		if(rmId!=null)
		{
			int rm_Id=Integer.parseInt(rmId);
			rawMaterialDetails.setRmId(rm_Id);
			//strReturn=new String("redirect:/showRawMaterial");
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
		rawMaterialDetails.setIsTallySync(0);
		rawMaterialDetails.setDelStatus(0);
		
		System.out.println("Data  : "+rawMaterialDetails.toString());
		RestTemplate rest=new RestTemplate();
		Info info=rest.postForObject(Constants.url + "/rawMaterial/addRawMaterial", rawMaterialDetails, Info.class);
		
		
		System.out.println("Response : " +info.toString());
		MultiValueMap<String , Object> map =new LinkedMultiValueMap<String, Object>();
		map.add("grpId",Integer.parseInt(rmGroup));
		
		List<GetRawmaterialByGroup> getRawmaterialByGroupList=rest.postForObject(Constants.url +"rawMaterial/getRawMaterialDetailByGroup", map,  List.class);
		
		List<RmItemGroup> rmItemGroupList=rest.getForObject(Constants.url + "rawMaterial/getAllRmItemGroup", List.class);
		System.out.println("Group list :: "+rmItemGroupList.toString());
		
		System.out.println("RM Details : "+getRawmaterialByGroupList.toString());
		model.addObject("grpId",Integer.parseInt(rmGroup));

		model.addObject("groupList", rmItemGroupList);
		model.addObject("RawmaterialList", getRawmaterialByGroupList);
		}
		catch(Exception e)
		{
			System.out.println("Exception In /addRawMaterial"+e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/showRawMaterialDetails", method = RequestMethod.POST)
	public ModelAndView showRawMaterialDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/rawMaterial/showAllRawMaterial");
		
      try {
		String grp_id=request.getParameter("rm_group");
		int grpId=Integer.parseInt(grp_id);
		
		MultiValueMap<String , Object> map =new LinkedMultiValueMap<String, Object>();
		map.add("grpId", grpId);
		
		RestTemplate rest=new RestTemplate();
		
		List<GetRawmaterialByGroup> getRawmaterialByGroupList=rest.postForObject(Constants.url +"rawMaterial/getRawMaterialDetailByGroup", map,  List.class);
		
		List<RmItemGroup> rmItemGroupList=rest.getForObject(Constants.url + "rawMaterial/getAllRmItemGroup", List.class);
		System.out.println("Group list :: "+rmItemGroupList.toString());
		
		System.out.println("RM Details : "+getRawmaterialByGroupList.toString());
		model.addObject("grpId",grpId);
		model.addObject("groupList", rmItemGroupList);
		model.addObject("RawmaterialList", getRawmaterialByGroupList);
      }
      catch(Exception e)
      {
    	  System.out.println("Exception In /showRawMaterialDetails"+e.getMessage());
      }
		return model;
	}
	@RequestMapping(value = "/showRawMaterial", method = RequestMethod.GET)
	public ModelAndView showRawMaterial(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/rawMaterial/showAllRawMaterial");
		Constants.mainAct=13;
		Constants.subAct=80;
		try {
		RestTemplate rest=new RestTemplate();
		List<RmItemGroup> rmItemGroupList=rest.getForObject(Constants.url + "rawMaterial/getAllRmItemGroup", List.class);
		System.out.println("Group list :: "+rmItemGroupList.toString());
		
		model.addObject("groupList", rmItemGroupList);
        }
        catch(Exception e)
        {
        	System.out.println("Exception In /showRawMaterial"+e.getMessage());
        }
		return model;
	}
	
	//---------------------------------------getRMCategory------------------------
	@RequestMapping(value = "/getRmCategory", method = RequestMethod.GET)
	public @ResponseBody List<RmItemCategory> getRmCategory(HttpServletRequest request,
		HttpServletResponse response) {
		List<RmItemCategory> rmItemCategoryList=new ArrayList<RmItemCategory>();

		try {
		 
		String selectedGroup=request.getParameter("grpId");
		int grpId=Integer.parseInt(selectedGroup);
		MultiValueMap<String, Object> map=new LinkedMultiValueMap<String, Object>();
		RestTemplate rest=new RestTemplate();
		
		map.add("grpId", grpId);
		try {
	
			 rmItemCategoryList = rest.postForObject(Constants.url + "rawMaterial/getRmItemCategories",map,
				List.class);
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("List of Menu : "+ rmItemCategoryList.toString());
		}catch(Exception e)
		{
			System.out.println("Exception In /getRmCategory"+e.getMessage());
		}
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
	
	
	@RequestMapping(value = "/deleteRawMaterial/{rmId}/{grpId}", method = RequestMethod.GET)
	public ModelAndView deleteRawMaterial(@PathVariable("rmId")int rmId,@PathVariable("grpId")int grpId,HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("masters/rawMaterial/showAllRawMaterial");

		  MultiValueMap<String, Object> map=new LinkedMultiValueMap<String,Object>();
		  map.add("rmId", rmId);
		  RestTemplate rest=new RestTemplate();
		    Info info=rest.postForObject(Constants.url + "rawMaterial/deleteRawMaterial",map, Info.class);

		    System.out.println("response : "+ info.toString());
		    map =new LinkedMultiValueMap<String, Object>();
			map.add("grpId", grpId);
			
			List<GetRawmaterialByGroup> getRawmaterialByGroupList=rest.postForObject(Constants.url +"rawMaterial/getRawMaterialDetailByGroup", map,  List.class);
			
			List<RmItemGroup> rmItemGroupList=rest.getForObject(Constants.url + "rawMaterial/getAllRmItemGroup", List.class);
			System.out.println("Group list :: "+rmItemGroupList.toString());
			
			System.out.println("RM Details : "+getRawmaterialByGroupList.toString());
			
			model.addObject("groupList", rmItemGroupList);
			model.addObject("RawmaterialList", getRawmaterialByGroupList);
			
		    return model;
	}
	@RequestMapping(value = "/showAddRmTax", method = RequestMethod.GET)
	public ModelAndView showAddRmTax(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/rawMaterial/addRmTax");
		Constants.mainAct=13;
		Constants.subAct=111;
		RestTemplate rest=new RestTemplate();
        try {
		RawMaterialTaxDetailsList rawMaterialTaxDetailsList=rest.getForObject(Constants.url + "rawMaterial/getAllRmTaxList",RawMaterialTaxDetailsList.class);

		System.out.println("rawMaterialTaxDetailsList"+rawMaterialTaxDetailsList.toString());
		model.addObject("rmTaxList", rawMaterialTaxDetailsList.getRawMaterialTaxDetailsList());
        }
        catch(Exception e)
        {
        	System.out.println("Exception in showAddRmTax"+e.getMessage());
        }
        return model;
	}
	
	
	@RequestMapping(value = "/addRmTax", method = RequestMethod.POST)
	public String addRmTax(HttpServletRequest request, HttpServletResponse response) {

		try {
		int taxId = 0;

		try {
			taxId = Integer.parseInt(request.getParameter("tax_id"));		


		} catch (Exception e) {
			taxId = 0;
			System.out.println("In Catch of Add RmTax Process Exc:" + e.getMessage());

		}
		String taxDesc=request.getParameter("tax_desc");
		float igstPer=Float.parseFloat(request.getParameter("igst_per"));
		float sgstPer=Float.parseFloat(request.getParameter("sgst_per"));
		float cgstPer=Float.parseFloat(request.getParameter("cgst_per"));

		RawMaterialTaxDetails rawMaterialTaxDetails=new RawMaterialTaxDetails();
		rawMaterialTaxDetails.setCgstPer(cgstPer);
		rawMaterialTaxDetails.setIgstPer(igstPer);
		rawMaterialTaxDetails.setSgstPer(sgstPer);
		rawMaterialTaxDetails.setTaxDesc(taxDesc);
		rawMaterialTaxDetails.setTaxId(taxId);
		
		RestTemplate rest=new RestTemplate();
		  Info info=rest.postForObject(Constants.url + "rawMaterial/insertRmTax",rawMaterialTaxDetails, Info.class);

		  System.out.println("response : "+ info.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception In AddRmTax:"+e.getMessage());
		}
		return "redirect:/showAddRmTax";
	}
	
	@RequestMapping(value = "/showAddRmUmo", method = RequestMethod.GET)
	public ModelAndView showAddRmUmo(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/rawMaterial/addRmUom");
		Constants.mainAct=2;
		Constants.subAct=23;
		RestTemplate rest = new RestTemplate();
		RawMaterialUomList rawMaterialUomList=rest.getForObject(Constants.url + "/rawMaterial/getRmUomList",RawMaterialUomList.class);
		
		model.addObject("rmUomList", rawMaterialUomList.getRawMaterialUom());
		return model;
	}
	// ------------------------------Delete Uom Process------------------------------------
		@RequestMapping(value = "/deleteRmUom/{uomId}", method = RequestMethod.GET)
		public String deleteUom(@PathVariable int uomId) {

			ModelAndView mav = new ModelAndView("masters/rawMaterial/addRmUom");
			try {

				RestTemplate rest = new RestTemplate();
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("uomId", uomId);

				Info info = rest.postForObject(Constants.url + "/rawMaterial/deleteRmUom", map, Info.class);
				System.out.println(info.toString());

				if (info.isError()) {

					return "redirect:/showAddRmUmo";

				} else {
					return "redirect:/showAddRmUmo";

				}
			} catch (Exception e) {
				System.out.println("Exception In delete RmUom:" + e.getMessage());

				return "redirect:/showAddRmUmo";

			}

		}
		// ------------------------------Delete RM Tax Process------------------------------------
				@RequestMapping(value = "/deleteRmTax/{taxId}", method = RequestMethod.GET)
				public String deleteRmTax(@PathVariable int taxId) {

					ModelAndView mav = new ModelAndView("masters/rawMaterial/addRmTax");
					try {

						RestTemplate rest = new RestTemplate();
						MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
						map.add("taxId", taxId);

						Info info = rest.postForObject(Constants.url + "/rawMaterial/deleteRmTax", map, Info.class);
						System.out.println(info.toString());

						if (info.isError()) {

							return "redirect:/showAddRmTax";

						} else {
							return "redirect:/showAddRmTax";

						}
					} catch (Exception e) {
						System.out.println("Exception In delete RmTax:" + e.getMessage());

						return "redirect:/showAddRmTax";

					}

				}
		@RequestMapping(value = "/updateRmTax/{taxId}", method = RequestMethod.GET)
		public ModelAndView updateRmTax(@PathVariable int taxId) {

			ModelAndView mav = new ModelAndView("masters/rawMaterial/addRmTax");
			try {

				RestTemplate rest = new RestTemplate();
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("taxId", taxId);

				RawMaterialTaxDetails rawMaterialTaxDetails = rest.postForObject(Constants.url + "/rawMaterial/getRMTax", map,RawMaterialTaxDetails.class);
				System.out.println(rawMaterialTaxDetails.toString());

				RawMaterialTaxDetailsList rawMaterialTaxDetailsList=rest.getForObject(Constants.url + "rawMaterial/getAllRmTaxList",RawMaterialTaxDetailsList.class);

				mav.addObject("rmTaxList", rawMaterialTaxDetailsList.getRawMaterialTaxDetailsList());				

				if (rawMaterialTaxDetails!=null) {

					mav.addObject("rmTaxList", rawMaterialTaxDetailsList.getRawMaterialTaxDetailsList());

					mav.addObject("rmTax", rawMaterialTaxDetails);
				}
			} catch (Exception e) {
				System.out.println("Exception In Edit updateRmTax:" + e.getMessage());

				return mav;
			}
			return mav;
		}
		// ----------------------------------------------------------------------------------------------
		// ------------------------------Edit RMUom-------------------------------------------------------------
		@RequestMapping(value = "/updateRmUom/{uomId}", method = RequestMethod.GET)
		public ModelAndView updateInstrument(@PathVariable int uomId) {

			ModelAndView mav = new ModelAndView("masters/rawMaterial/addRmUom");
			try {

				RestTemplate rest = new RestTemplate();
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("uomId", uomId);

				RawMaterialUom rawMaterialUom = rest.postForObject(Constants.url + "/rawMaterial/getRmUomByUomId", map,RawMaterialUom.class);
				System.out.println(rawMaterialUom.toString());

				RawMaterialUomList rawMaterialUomList=rest.getForObject(Constants.url + "/rawMaterial/getRmUomList",RawMaterialUomList.class);
				

				if (rawMaterialUom!=null) {

					mav.addObject("rmUomList", rawMaterialUomList.getRawMaterialUom());

					mav.addObject("rmUom", rawMaterialUom);
				}
			} catch (Exception e) {
				System.out.println("Exception In Edit rawMaterialUom:" + e.getMessage());

				return mav;
			}
			return mav;
		}
		// ----------------------------------------------------------------------------------------------
		// ----------------------------------------END-------------------------------------------------------------
	@RequestMapping(value = "/addRmUom", method = RequestMethod.POST)
	public String addRmUom(HttpServletRequest request, HttpServletResponse response) {

		String uom=request.getParameter("uom");
		
		int uomId = 0;

		try {
			uomId = Integer.parseInt(request.getParameter("umo_id"));

		} catch (Exception e) {
			uomId = 0;
			System.out.println("In Catch of Add Uom Process Exc:" + e.getMessage());

		}
		
		RawMaterialUom rawMaterialUom=new RawMaterialUom();
		rawMaterialUom.setUomId(uomId);
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
			RmRateVerification rmRateVerification=null;
			try {
		
				rmRateVerification = rest.postForObject(Constants.url + "rawMaterial/getRmRateVerification",map,
						RmRateVerification.class);
			
			 
		
				
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		 
			
			return rmRateVerification;
			
		}
		//-----------------------------------------insert RM Rate Verification-------------------
		
		@RequestMapping(value = "/submitRmRateVerification", method = RequestMethod.POST)
		public String submitRmRateVerification(HttpServletRequest request, HttpServletResponse response) {

			 

			String currRateDate=request.getParameter("curr_rate_date");
			System.out.println(" curr_rate_date      : "+currRateDate);
			String currRateTaxExtra=request.getParameter("curr_rate_tax_extra");
			System.out.println(" currRateTaxExtra      : "+currRateTaxExtra);
			String currRateTaxIncl=request.getParameter("curr_rate_tax_incl");
			System.out.println(" currRateTaxIncl : "+currRateTaxIncl);
			
			String rateDate=request.getParameter("rate_date");
			System.out.println(" rateDate      : "+rateDate);
			String rateTaxExtra=request.getParameter("tax_extra");
			System.out.println("rateTaxExtra : "+rateTaxExtra);
			String rateTaxIncl=request.getParameter("tax_incl");
			System.out.println(" rateTaxIncl       : "+rateTaxIncl);
			
			String date1=request.getParameter("rate_date1");
			System.out.println(" date1       : "+date1);
			String rateTaxIncl1=request.getParameter("tax_incl1");
			System.out.println(" rateTaxIncl1       : "+rateTaxIncl1);
			String rateTaxExtra1=request.getParameter("tax_extra1");
			System.out.println(" rateTaxExtra1       : "+rateTaxExtra1);
			
			
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
			
			//if(rm_rate_ver_id!=null && rm_rate_ver_id!="" && rm_rate_ver_id!="0")
			//{
			int rmRateVerId=Integer.parseInt(rm_rate_ver_id);
			rmRateVerification.setRmRateVerId(rmRateVerId);
			//}
			System.out.println("R v Id ---"+rmRateVerId);
			rmRateVerification.setRateDate(currRateDate);
			rmRateVerification.setRateTaxExtra(Float.parseFloat(currRateTaxExtra));
			rmRateVerification.setRateTaxIncl(Float.parseFloat(currRateTaxIncl));
			if(rmRateVerification.getRmRateVerId()==0)
			{
				rmRateVerification.setDate1(currRateDate);
				rmRateVerification.setRateTaxExtra1(Float.parseFloat(currRateTaxExtra));
				rmRateVerification.setRateTaxIncl1(Float.parseFloat(currRateTaxIncl));
				rmRateVerification.setDate2(currRateDate);
				rmRateVerification.setRateTaxExtra2(Float.parseFloat(currRateTaxExtra));
				rmRateVerification.setRateTaxIncl2(Float.parseFloat(currRateTaxIncl));
			}
			else {
				
				
				rmRateVerification.setDate1(rateDate);
				rmRateVerification.setRateTaxExtra1(Float.parseFloat(rateTaxExtra));
				rmRateVerification.setRateTaxIncl1(Float.parseFloat(rateTaxIncl));
				rmRateVerification.setDate2(date1);
				rmRateVerification.setRateTaxExtra2(Float.parseFloat(rateTaxExtra1));
				rmRateVerification.setRateTaxIncl2(Float.parseFloat(rateTaxIncl1));
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
				
				//------------------------------Show Raw Material Item Category Jsp------------------------------------
				@RequestMapping(value = "/showRmItemCategory", method = RequestMethod.GET)
				public ModelAndView showRmItemCategory(HttpServletRequest request, HttpServletResponse response) {
				
					ModelAndView model = new ModelAndView("masters/rmItemCategoryStores");
					Constants.mainAct =9;
					Constants.subAct=51;
					
					
					List<RmItemGroup> rmItemGroupList =new ArrayList<RmItemGroup>();
					
					try {
					
						RestTemplate restTemplate = new RestTemplate();

						ResponseEntity<List<RmItemGroup>> rateResponse =
						        restTemplate.exchange(""+Constants.url +"/rawMaterial/getAllRmItemGroup",
						                    HttpMethod.GET, null, new ParameterizedTypeReference<List<RmItemGroup>>() {
						            });
						
						 rmItemGroupList = rateResponse.getBody();
						
						model.addObject("rmItemGroupList", rmItemGroupList);
					
					}catch(Exception e)
					{
					   model.addObject("rmItemGroupList", rmItemGroupList);
			           System.out.println("Exception In /showRmItemCategory:"+e.getMessage());
			             

					}
					return model;
				}
				//----------------------------------------END-------------------------------------------------------------
				
				//------------------------------Show Raw Material Item SubCategory Jsp------------------------------------

				@RequestMapping(value = "/showRmItemSubCategory", method = RequestMethod.GET)
				public ModelAndView showRmItemSubCategory(HttpServletRequest request, HttpServletResponse response) {
				
					ModelAndView model = new ModelAndView("masters/rmItemSubCatStores");
					Constants.mainAct =9;
					Constants.subAct=53;
					
					try {
					RestTemplate restTemplate = new RestTemplate();

					RmItemCatList rmItemCatList=restTemplate.getForObject(Constants.url +"/rawMaterial/showRmItemCategories",RmItemCatList.class);
					
					System.out.println("Category Response:"+rmItemCatList.getRmItemCategoryList().toString());
					
					model.addObject("rmItemCatList", rmItemCatList.getRmItemCategoryList());
					}
					catch(Exception e)
					{
						System.out.println("Exception In Show Item Sub Category:"+e.getMessage());
					}

					return model;
				}
				//----------------------------------------END-------------------------------------------------------------
				
				//------------------------------ADD  Raw Material Item Category Process------------------------------------

				@RequestMapping(value = "/addRmCategoryProcess", method = RequestMethod.POST)
				public String addRmCategoryProcess(HttpServletRequest request, HttpServletResponse response) {

					ModelAndView model = new ModelAndView("masters/rmItemCategoryStores");
					try {
						
						String catName=request.getParameter("cat_name");
						
						String catDesc=request.getParameter("cat_desc");
						
						int grpId=Integer.parseInt(request.getParameter("grp_id"));
						
						RmItemCategory rmItemCategory=new RmItemCategory();
						
						rmItemCategory.setCatId(0);
						rmItemCategory.setCatName(catName);
						rmItemCategory.setCatDesc(catDesc);
						rmItemCategory.setGrpId(grpId);
						rmItemCategory.setDelStatus(0);
						
						RestTemplate restTemplate = new RestTemplate();

						
						ErrorMessage errorMessage=restTemplate.postForObject(Constants.url + "/rawMaterial/saveRmItemCategory", rmItemCategory,
								ErrorMessage.class);
						System.out.println("Response: "+errorMessage.toString());
					
					if(errorMessage.getError()==true) {
						
						System.out.println("Error:True"+errorMessage.toString());
						return "redirect:/showItemCatList";
						
					}else
					{
						return "redirect:/showItemCatList";
					}
						
					}catch(Exception e)
					{
						
						System.out.println("Exception In Add Item Category Process:"+e.getMessage());

					}

					return "redirect:/showItemCatList";
					}
				//----------------------------------------END-------------------------------------------------------------
				//------------------------------ADD  Raw Material Item SubCategory Process------------------------------------

					@RequestMapping(value = "/addRmSubCategoryProcess", method = RequestMethod.POST)
					public String addRmSubCategoryProcess(HttpServletRequest request, HttpServletResponse response) {

						ModelAndView model = new ModelAndView("masters/rmItemSubCatStores");
						try {
							
							String catName=request.getParameter("sub_cat_name");
							
							int catId=Integer.parseInt(request.getParameter("cat_id"));

							
							String catDesc=request.getParameter("sub_cat_desc");
							
							RmItemSubCategory rmItemSubCategory=new RmItemSubCategory();
							
							rmItemSubCategory.setSubCatId(0);
							rmItemSubCategory.setCatId(catId);
							rmItemSubCategory.setSubCatName(catName);
							rmItemSubCategory.setSubCatDesc(catDesc);
							
							rmItemSubCategory.setDelStatus(0);
							
							RestTemplate restTemplate = new RestTemplate();

							
							ErrorMessage errorMessage=restTemplate.postForObject(Constants.url + "/rawMaterial/saveRmItemSubCategory", rmItemSubCategory,
									ErrorMessage.class);
							System.out.println("Response: "+errorMessage.toString());
						}catch(Exception e)
						{
							System.out.println("Exception In Add Item SubCategory Process:"+e.getMessage());

							return "redirect:/showItemSubCatList";

						}

						return "redirect:/showItemSubCatList";
						}
					//----------------------------------------END-------------------------------------------------------------
					
				//------------------------------Update Raw Material Item Category Process------------------------------------

				@RequestMapping(value = "/updateRmCategoryProcess", method = RequestMethod.POST)
				public String updateRmCategoryProcess(HttpServletRequest request, HttpServletResponse response) {

					ModelAndView model = new ModelAndView("masters/editItemCatStore");
					try {
						int catId=Integer.parseInt(request.getParameter("cat_id"));
						
						String catName=request.getParameter("cat_name");
						
						String catDesc=request.getParameter("cat_desc");
						
						int grpId=Integer.parseInt(request.getParameter("grp_id"));
						
						RmItemCategory rmItemCategory=new RmItemCategory();
						
						rmItemCategory.setCatId(catId);
						rmItemCategory.setCatName(catName);
						rmItemCategory.setCatDesc(catDesc);
						rmItemCategory.setGrpId(grpId);
						rmItemCategory.setDelStatus(0);
						
						RestTemplate restTemplate = new RestTemplate();

						
						ErrorMessage errorMessage=restTemplate.postForObject(Constants.url + "/rawMaterial/saveRmItemCategory", rmItemCategory,
								ErrorMessage.class);
						System.out.println("ErrorMessage Response: "+errorMessage.toString());
					}catch(Exception e)
					{
						System.out.println("Exception In Update RM Category Process:"+e.getMessage());

						return "redirect:/showItemCatList";
					}

					return "redirect:/showItemCatList";
					}
				//----------------------------------------END-------------------------------------------------------------
				//------------------------------Update Raw Material Item Category JSP------------------------------------

				@RequestMapping(value = "/updateRmItemCategory/{catId}", method = RequestMethod.GET)
				public ModelAndView updateRmCategory(@PathVariable("catId") int catId) {

						ModelAndView model = new ModelAndView("masters/editItemCatStore");
						try {
							RestTemplate restTemplate = new RestTemplate();

							MultiValueMap<String, Object> map=new LinkedMultiValueMap<String, Object>();
							map.add("catId", catId);
							
							RmItemCategory rmItemCategory=restTemplate.postForObject(Constants.url + "/rawMaterial/getRmItemCategory", map,
									RmItemCategory.class);
					
							System.out.println("RmItemCategory Response: "+rmItemCategory.toString());
							
							ResponseEntity<List<RmItemGroup>> rateResponse =
							        restTemplate.exchange(""+Constants.url +"/rawMaterial/getAllRmItemGroup",
							                    HttpMethod.GET, null, new ParameterizedTypeReference<List<RmItemGroup>>() {
							            });
							List<RmItemGroup> rmItemGroupList = rateResponse.getBody();
							
							model.addObject("rmItemGroupList", rmItemGroupList);
							
							
							model.addObject("rmItemCategory", rmItemCategory);
						}catch(Exception e)
						{
							System.out.println("Exception In Update RM Category JspPage Show:"+e.getMessage());

						}

						return model;
					}
					//----------------------------------------END-------------------------------------------------------------
					//------------------------------Update Raw Material Item SubCategory JSP------------------------------------

							@RequestMapping(value = "/updateRmSubCategory/{subCatId}", method = RequestMethod.GET)
							public ModelAndView updateRmSubCategory(@PathVariable("subCatId") int subCatId) {

								ModelAndView model = new ModelAndView("masters/editItemSubCatStore");
								try {
									RestTemplate restTemplate = new RestTemplate();

									MultiValueMap<String, Object> map=new LinkedMultiValueMap<String, Object>();
									map.add("subCatId", subCatId);
									
									RmItemSubCategory rmItemSubCategory=restTemplate.postForObject(Constants.url + "/rawMaterial/getRmItemSubCategory", map,
											RmItemSubCategory.class);
									System.out.println("RmItemSubCategory Response: "+rmItemSubCategory.toString());
									
									RmItemCatList rmItemCatList=restTemplate.getForObject(Constants.url +"/rawMaterial/showRmItemCategories",RmItemCatList.class);
									
									System.out.println("Category Response:"+rmItemCatList.toString());
									
									model.addObject("rmItemCatList", rmItemCatList.getRmItemCategoryList());
									
									model.addObject("rmItemSubCategory", rmItemSubCategory);

									
								}catch(Exception e)
								{
									System.out.println("Exception In Update RM SubCategory JspPage Show:"+e.getMessage());

								}

								return model;
								}
				//----------------------------------------END-------------------------------------------------------------
				
				//------------------------------Update Raw Material Item SubCategory Process------------------------------------

					@RequestMapping(value = "/updateRmSubCategoryProcess", method = RequestMethod.POST)
					public String updateRmSubCatProcess(HttpServletRequest request, HttpServletResponse response) {

						ModelAndView model = new ModelAndView("masters/editItemSubCatStore");
						try {
							
							String subCatName=request.getParameter("sub_cat_name");
							System.out.println("Sub Category Name:"+subCatName);
							
							String subCatDesc=request.getParameter("sub_cat_desc");
							System.out.println("Sub Category Description:"+subCatDesc);

							int catId=Integer.parseInt(request.getParameter("cat_id"));
							System.out.println("CatId:"+catId);

							
							int subCatId=Integer.parseInt(request.getParameter("sub_cat_id"));
							System.out.println("subCatId:"+subCatId);

							
							RmItemSubCategory rmItemSubCategory=new RmItemSubCategory();
							
							
							rmItemSubCategory.setCatId(catId);
							rmItemSubCategory.setSubCatId(subCatId);
							rmItemSubCategory.setSubCatName(subCatName);
							rmItemSubCategory.setSubCatDesc(subCatDesc);
							rmItemSubCategory.setDelStatus(0);
							System.out.println("RmItemSubCategory:"+rmItemSubCategory.toString());
							
							RestTemplate restTemplate = new RestTemplate();

							
							ErrorMessage errorMessage=restTemplate.postForObject(Constants.url + "/rawMaterial/saveRmItemSubCategory", rmItemSubCategory,
									ErrorMessage.class);
							System.out.println("Response: "+errorMessage.toString());
						}catch(Exception e)
						{
							System.out.println("Exception In Update RM SubCategory Process:"+e.getMessage());

							return "redirect:/showItemSubCatList";
						}

						return "redirect:/showItemSubCatList";
						}
					//----------------------------------------END-------------------------------------------------------------
				//------------------------------show Raw Material Item Category List Jsp------------------------------------
				@RequestMapping(value = "/showItemCatList")
				public ModelAndView showItemCatList(HttpServletRequest request, HttpServletResponse response) {
					ModelAndView mav = new ModelAndView("masters/rmItemCatStoreList");
					Constants.mainAct =9;
					Constants.subAct=52;
					try {
						RestTemplate restTemplate = new RestTemplate();

						RmItemCatList rmItemCatList=restTemplate.getForObject(Constants.url +"/rawMaterial/showRmItemCategories",RmItemCatList.class);
						
						System.out.println("Category Response:"+rmItemCatList.toString());
						
						mav.addObject("rmItemCatList", rmItemCatList.getRmItemCategoryList());
						
					}catch(Exception e)
					{
						System.out.println("Exception In RM Category List Show:"+e.getMessage());

					}

					return mav;
				}
				//----------------------------------------END-------------------------------------------------------------
				//------------------------------show Raw Material Item SubCategory List Jsp------------------------------------
					@RequestMapping(value = "/showItemSubCatList")
					public ModelAndView showItemSubCatList(HttpServletRequest request, HttpServletResponse response) {
						ModelAndView mav = new ModelAndView("masters/rmItemSubCatStoreList");
						Constants.mainAct =9;
						Constants.subAct=54;
						try {
							RestTemplate restTemplate = new RestTemplate();

							RmItemSubCatList rmItemSubCatList=restTemplate.getForObject(Constants.url +"/rawMaterial/showRmItemSubCategories",RmItemSubCatList.class);
							
							System.out.println("SubCategory Response:"+rmItemSubCatList.toString());
							
							mav.addObject("rmItemSubCatList", rmItemSubCatList.getRmItemSubCategory());
							
						}catch(Exception e)
						{
							System.out.println("Exception In RM SubCategory List Show:"+e.getMessage());

						}

						return mav;
					}
					//----------------------------------------END-------------------------------------------------------------
					
				//------------------------------Delete Raw Material Item Category Process------------------------------------
				@RequestMapping(value = "/deleteRmItemCategory/{id}", method = RequestMethod.GET)
				public String deleteRmItemCategory(@PathVariable int id) {


					ModelAndView mav = new ModelAndView("masters/rmItemCatStoreList");
					try {
						
					
					RestTemplate rest = new RestTemplate();
					MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
					map.add("catId", id);

					ErrorMessage errorResponse = rest.postForObject(Constants.url + "/rawMaterial/deleteRmItemCategory", map, ErrorMessage.class);
					System.out.println(errorResponse.toString());

					if (errorResponse.getError()) {
						
						return "redirect:/showItemCatList";

					} else {
						return "redirect:/showItemCatList";

					}
					}catch(Exception e){
						System.out.println("Exception In delete Rm Item Category:"+e.getMessage());

						return "redirect:/showItemCatList";

					}

				}
				
				//----------------------------------------END-------------------------------------------------------------
				//------------------------------Delete Raw Material Item SubCategory Process------------------------------------
					@RequestMapping(value = "/deleteRmItemSubCategory/{id}", method = RequestMethod.GET)
					public String deleteRmItemSubCategory(@PathVariable int id) {


						ModelAndView mav = new ModelAndView("masters/rmItemSubCatStoreList");
						try {
							
						RestTemplate rest = new RestTemplate();
						MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
						map.add("subCatId", id);

						ErrorMessage errorResponse = rest.postForObject(Constants.url + "/rawMaterial/deleteRmItemSubCategory", map, ErrorMessage.class);
						System.out.println(errorResponse.toString());

						if (errorResponse.getError()) {
							return "redirect:/showItemSubCatList";

						} else {
							return "redirect:/showItemSubCatList";

						}
						
					  }catch(Exception e){
						System.out.println("Exception In delete Rm Item SubCategory:"+e.getMessage());

						return "redirect:/showItemCatList";

					}
					}
					
	//----------------------------------------END-------------------------------------------------------------
    //----------------------------Show Add Item Detail Jsp----------------------------------------------------
	@RequestMapping(value = "/showItemDetail/{id}", method = RequestMethod.GET)
	public ModelAndView showItemDetail(@PathVariable int id,HttpServletRequest request, HttpServletResponse response) {

	ModelAndView model = new ModelAndView("masters/rawMaterial/addItemDetail");
	
	try {
	
	RestTemplate rest=new RestTemplate();
	itemDetailList=new ArrayList<ItemDetail>();
	globalId=id;
	
	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	map.add("id", id);

	Item item = rest.postForObject("" + Constants.url + "getItem", map,Item.class);

	ItemDetailList itemDetailsList= rest.postForObject(Constants.url + "rawMaterial/getItemDetails",map, ItemDetailList.class);
	List<RmItemGroup> rmItemGroupList=rest.getForObject(Constants.url + "rawMaterial/getAllRmItemGroup", List.class);
	
	for(int i=0;i< itemDetailsList.getItemDetailList().size();i++)
	{  
		ItemDetail itemDetail=new ItemDetail();
		
		itemDetail.setItemDetailId(itemDetailsList.getItemDetailList().get(i).getItemDetailId());
		itemDetail.setItemId(itemDetailsList.getItemDetailList().get(i).getItemId());
		itemDetail.setItemName(itemDetailsList.getItemDetailList().get(i).getItemName());
		itemDetail.setNoOfPiecesPerItem(itemDetailsList.getItemDetailList().get(i).getNoOfPiecesPerItem());
		itemDetail.setRmId(itemDetailsList.getItemDetailList().get(i).getRmId());
		itemDetail.setRmName(itemDetailsList.getItemDetailList().get(i).getRmName());
		itemDetail.setRmQty(itemDetailsList.getItemDetailList().get(i).getRmQty());
		itemDetail.setRmType(itemDetailsList.getItemDetailList().get(i).getRmType());
		itemDetail.setRmUomId(itemDetailsList.getItemDetailList().get(i).getRmUomId());
		itemDetail.setRmWeight(itemDetailsList.getItemDetailList().get(i).getRmWeight());
		itemDetail.setDelStatus(itemDetailsList.getItemDetailList().get(i).getDelStatus());
		itemDetailList.add(itemDetail);
	}
	model.addObject("itemDetailList", itemDetailsList.getItemDetailList());
	model.addObject("item", item);
	model.addObject("rmItemGroupList", rmItemGroupList);
	}
	catch(Exception e)
	{
		System.err.println();
	}
	return model;
	}
	//----------------------------------------END-------------------------------------------------------------
	
	@RequestMapping(value = "/getBaseQty", method = RequestMethod.GET)
	public @ResponseBody int getBaseQty(HttpServletRequest request, HttpServletResponse response) {
		
		int baseQty = 0;
		
		int id=Integer.parseInt(request.getParameter("id"));
		
		for(Item item:itemList)
		{
			if(item.getId()==id)
			{
				baseQty=item.getMinQty();
				
				break;
			}
			
		}
		return baseQty;
	}

	  //---------------------------------------AJAX For RM List -----------------------------------------
		@RequestMapping(value = "/getRawMaterialList", method = RequestMethod.GET)
		public @ResponseBody List<CommonConf> getRawMaterialList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/rawMaterial/addItemDetail");
		
		int rmType=Integer.parseInt(request.getParameter("rm_type"));
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
		//----------------------------------------END-------------------------------------------------------------
		
		@RequestMapping(value = "/insertItemDetail", method = RequestMethod.GET)
		public @ResponseBody List<ItemDetail> insertItemDetail(HttpServletRequest request, HttpServletResponse response) {
			
			
			
			int itemId=Integer.parseInt(request.getParameter("itemId"));
			System.out.println("itemId"+itemId);
			
			String itemName=request.getParameter("itemName");
			System.out.println("itemName"+itemName);

			
			int noOfPiecesPerItem=Integer.parseInt(request.getParameter("baseQty"));
			
			int rmType=Integer.parseInt(request.getParameter("rmType"));
			
			int rmId=Integer.parseInt(request.getParameter("rmId"));
			
			String rmName=request.getParameter("rmName");

			
			int rmWeight=Integer.parseInt(request.getParameter("rmWeight"));
			
			int rmQty=Integer.parseInt(request.getParameter("rmQty"));
			
			
			ItemDetail itemDetail=new ItemDetail();
			
			itemDetail.setItemId(itemId);
			itemDetail.setItemName(itemName);
			itemDetail.setRmId(rmId);
			itemDetail.setRmName(rmName);
			itemDetail.setRmQty(rmQty);
			itemDetail.setRmWeight(rmWeight);
			itemDetail.setRmQty(rmQty);
			itemDetail.setRmType(rmType);
			itemDetail.setNoOfPiecesPerItem(noOfPiecesPerItem);
			
			for(CommonConf commonConf:commonConfs)
			{
				if(commonConf.getId()==itemDetail.getRmId())
				{
					itemDetail.setRmUomId(commonConf.getRmUomId());

				}
			}
			
			
			itemDetail.setDelStatus(0);
			System.out.println("ItemDetail"+itemDetail);
			
			itemDetailList.add(itemDetail);
			
			System.out.println("ItemDetail List:"+itemDetailList.toString());
			return itemDetailList;
		
		}
		@RequestMapping(value = "/addItemDetail", method = RequestMethod.POST)
		public  String addItemDetail(HttpServletRequest request, HttpServletResponse response) {
			
			RestTemplate restTemplate=new RestTemplate();
			
			System.out.println("Item  Detail Before Submit "+itemDetailList.toString());
			Info  info = null;
			try
			{
			info=restTemplate.postForObject(Constants.url+"/rawMaterial/saveItemDetails",itemDetailList,Info.class);
			
			itemDetailList=new ArrayList<ItemDetail>();//new Object for Item Detail List
			
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", globalId);

			ItemDetailList itemDetailsList= restTemplate.postForObject(Constants.url + "rawMaterial/getItemDetails",map, ItemDetailList.class);

			
			for(int i=0;i< itemDetailsList.getItemDetailList().size();i++)
			{  
				ItemDetail itemDetail=new ItemDetail();
				
				itemDetail.setItemDetailId(itemDetailsList.getItemDetailList().get(i).getItemDetailId());
				itemDetail.setItemId(itemDetailsList.getItemDetailList().get(i).getItemId());
				itemDetail.setItemName(itemDetailsList.getItemDetailList().get(i).getItemName());
				itemDetail.setNoOfPiecesPerItem(itemDetailsList.getItemDetailList().get(i).getNoOfPiecesPerItem());
				itemDetail.setRmId(itemDetailsList.getItemDetailList().get(i).getRmId());
				itemDetail.setRmName(itemDetailsList.getItemDetailList().get(i).getRmName());
				itemDetail.setRmQty(itemDetailsList.getItemDetailList().get(i).getRmQty());
				itemDetail.setRmType(itemDetailsList.getItemDetailList().get(i).getRmType());
				itemDetail.setRmUomId(itemDetailsList.getItemDetailList().get(i).getRmUomId());
				itemDetail.setRmWeight(itemDetailsList.getItemDetailList().get(i).getRmWeight());
				itemDetail.setDelStatus(itemDetailsList.getItemDetailList().get(i).getDelStatus());
				itemDetailList.add(itemDetail);
			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("EXC:"+e.getStackTrace());

			}
			System.out.println("itemDetailList:"+itemDetailList.toString());


			return "redirect:/itemList";
			
		}
		@RequestMapping(value = "/editItem", method = RequestMethod.GET)
		public @ResponseBody List<ItemDetail> editItem(HttpServletRequest request, HttpServletResponse response) {
			
			int itemId=Integer.parseInt(request.getParameter("itemId"));
			System.out.println("itemId"+itemId);
			
			String itemName=request.getParameter("itemName");
			System.out.println("itemName"+itemName);

			int noOfPiecesPerItem=Integer.parseInt(request.getParameter("baseQty"));
			
			int rmType=Integer.parseInt(request.getParameter("rmType"));
			
			int rmId=Integer.parseInt(request.getParameter("rmId"));
			
			String rmName=request.getParameter("rmName");
			
			int rmWeight=Integer.parseInt(request.getParameter("rmWeight"));
			
			int rmQty=Integer.parseInt(request.getParameter("rmQty"));
			
			int index=Integer.parseInt(request.getParameter("key"));
			System.out.println("Key:"+index);
			
			System.out.println("itemDetailList::"+itemDetailList.toString());
			for(int i=0;i<itemDetailList.size();i++)
			{
				if(i==index)
				{
			    	 itemDetailList.get(index).setItemId(itemId);
					 itemDetailList.get(index).setItemName(itemName);
					 itemDetailList.get(index).setRmId(rmId);
					 itemDetailList.get(index).setRmName(rmName);
					 itemDetailList.get(index).setRmQty(rmQty);
					 itemDetailList.get(index).setRmWeight(rmWeight);
					 itemDetailList.get(index).setRmQty(rmQty);
					 itemDetailList.get(index).setRmType(rmType);
					 itemDetailList.get(index).setNoOfPiecesPerItem(noOfPiecesPerItem);

			    for(CommonConf commonConf:commonConfs)
			    {
				   if(commonConf.getId()== itemDetailList.get(index).getRmId())
				   {
					  itemDetailList.get(index).setRmUomId(commonConf.getRmUomId());

				   }
			    }
			
			  itemDetailList.get(index).setDelStatus(0);
			  System.out.println("ItemDetail"+ itemDetailList.get(index));
			
			 }
				
			}
			System.out.println("Edit ItemDetail Ajax: "+ itemDetailList.get(index).toString());
 			System.out.println("ItemDetail List:"+itemDetailList.toString());
			return itemDetailList;
			
		}
		
		@RequestMapping(value = "/deleteItemDetail", method = RequestMethod.GET)
		public @ResponseBody List<ItemDetail> deleteItemDetail(HttpServletRequest request, HttpServletResponse response) {
			
			int index=Integer.parseInt(request.getParameter("key"));

			if(itemDetailList.get(index).getItemDetailId()==0)
			{
			   itemDetailList.remove(index);
			}
			else
			{
				itemDetailList.get(index).setDelStatus(1);
			}
 			System.out.println("ItemDetail List D:"+itemDetailList.toString());

			return itemDetailList;
		}
		

		@RequestMapping(value = "/editItemDetail", method = RequestMethod.GET)
		public @ResponseBody ItemDetail editItemDetail(HttpServletRequest request, HttpServletResponse response) {
			
			int index=Integer.parseInt(request.getParameter("key"));
			System.out.println("Key:"+index);
			ItemDetail  getItemDetail=new ItemDetail(); 
			
			System.out.println("itemDetailList::"+itemDetailList.toString());
			for(int i=0;i<itemDetailList.size();i++)
			{
				if(i==index)
				{
			     getItemDetail=itemDetailList.get(index);
				}
			
			}
			System.out.println("Edit ItemDetail Ajax: "+getItemDetail.toString());
			return getItemDetail;
		}
			
			
}
