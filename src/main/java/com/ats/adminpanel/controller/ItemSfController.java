package com.ats.adminpanel.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.RawMaterial.GetItemSfHeader;
import com.ats.adminpanel.model.RawMaterial.GetSfType;
import com.ats.adminpanel.model.RawMaterial.ItemSfDetail;
import com.ats.adminpanel.model.RawMaterial.ItemSfHeader;
import com.ats.adminpanel.model.RawMaterial.ItemSfHeaderList;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetails;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetailsList;
import com.ats.adminpanel.model.RawMaterial.RawMaterialUom;
import com.ats.adminpanel.model.RawMaterial.RmItemGroup;
import com.ats.adminpanel.model.RawMaterial.SfItemDetailList;
import com.ats.adminpanel.model.franchisee.CommonConf;
import com.ats.adminpanel.model.item.Item;

@Controller
@Scope("session")

public class ItemSfController {
	
	List<GetSfType> sfTypeList;
	List<RawMaterialUom> rawMaterialUomList;
	List<ItemSfDetail> sfDetailList=new ArrayList<>();
	RawMaterialDetailsList rawMaterialDetailsList;
	public  int globalSfId=0;
	
	public   List<CommonConf> commonConfs=new ArrayList<CommonConf>();

	int editSfId=0;
	SfItemDetailList sfDetaiListItems;
	
	List<ItemSfDetail> sfItemDetail=new ArrayList<>();
	
	public  List<GetItemSfHeader> itemHeaderList=new ArrayList<GetItemSfHeader>();;
	
	@RequestMapping(value = "/showItemSf", method = RequestMethod.GET)
	public ModelAndView showItemSf(HttpServletRequest request, HttpServletResponse response) {

		Constants.mainAct =3;
		Constants.subAct =24;

		ModelAndView model = new ModelAndView("masters/rawMaterial/itemSf");
		try {

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			rawMaterialUomList= new ArrayList<>();

			rawMaterialUomList = restTemplate.getForObject(Constants.url + "rawMaterial/getRmUom",
					List.class);
			
				map.add("delStatus", 0);
				sfTypeList=new ArrayList<>();
				
			 sfTypeList=restTemplate.postForObject(Constants.url+"getSfType",map,List.class);
			
			System.out.println("sfTypeList data : " + sfTypeList);

			System.out.println("RM UOM data : " + rawMaterialUomList);
			//itemHeaderList=new ArrayList<>();
			
			ParameterizedTypeReference<List<GetItemSfHeader>> typeRef = new ParameterizedTypeReference<List<GetItemSfHeader>>() {
			};
			ResponseEntity<List<GetItemSfHeader>> responseEntity = restTemplate.exchange(Constants.url + "getItemSfHeaderList",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);
			
			itemHeaderList = responseEntity.getBody();
			
			//itemHeaderList=restTemplate.postForObject(Constants.url+"getItemSfHeaderList",map,List.class);

			System.out.println(" Header List "+itemHeaderList);
			
			model.addObject("itemHeaderList",itemHeaderList);

			model.addObject("rmUomList", rawMaterialUomList);
			
			model.addObject("sfTypeList", sfTypeList);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "/deleteItemSf/{sfId}", method = RequestMethod.GET)
	public String deleteItemSf(@PathVariable int sfId,HttpServletRequest request, HttpServletResponse response) {
  
		try {

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("sfId", sfId);

			Info info = restTemplate.postForObject(Constants.url + "/deleteSfItem",map,
					Info.class);
			
				 
			System.out.println("info" + info); 
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "redirect:/showItemSf";

	}

	@RequestMapping(value = "/insertSfItemHeader", method = RequestMethod.POST)
	public String insertSfItemHeader(HttpServletRequest request, HttpServletResponse response) {
		
		//ModelAndView model=new ModelAndView("masters/rawMaterial/itemSf");
		
		try {
			
		RestTemplate restTemplate=new RestTemplate();
		
		String sfItemName=request.getParameter("sf_item_name");
		
		String sfType=request.getParameter("sf_item_type");
		
		
		int sfItemUoM=Integer.parseInt(request.getParameter("sf_item_uom"));
				
		float sfItewWeight=Float.parseFloat(request.getParameter("sf_item_weight"));
		
		int sfStockQty=Integer.parseInt(request.getParameter("sf_stock_qty"));
						
		float sfReorderQty=Float.parseFloat(request.getParameter("sf_reorder_level_qty"));
		
		float sfMinQty=Float.parseFloat(request.getParameter("sf_min_qty"));
		
		float sfMaxQty=Float.parseFloat(request.getParameter("sf_max_qty"));
		
		float mulFactor=Float.parseFloat(request.getParameter("mul_factor"));
		
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
		header.setMulFactor(mulFactor);
		System.out.println("header= "+header.toString());

		Info info=restTemplate.postForObject(Constants.url+"postSfItemHeader",header,Info.class);
		
		System.out.println("Insert Header response "+info.toString());
		
		//List<GetItemSfHeader> itemHeaderList=restTemplate.getForObject(Constants.url+"getItemSfHeader",List.class);

		//System.out.println(" Header response "+itemHeaderList);
		
		//model.addObject("itemHeaderList",itemHeaderList);
		//model.addObject("rmUomList", rawMaterialUomList);
		//model.addObject("sfTypeList", sfTypeList);
		
		}
		catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("ex in Item Sf  header Insert = "+e.getMessage());
			
		}
		
	return "redirect:/showItemSf";
	
	}
	
	
	@RequestMapping(value = "/showAddSfItemDetail/{sfId}/{sfName}/{sfTypeName}", method = RequestMethod.GET)
	public ModelAndView showAddSfItemDetail(@PathVariable int sfId,@PathVariable String sfName,@PathVariable  String sfTypeName,HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("Inside show details");
			
		ModelAndView model=new ModelAndView("masters/rawMaterial/itemSfDetail");
	
		try {
			
			if(globalSfId!=sfId) {
				
				sfDetailList=new ArrayList<>();
				
			}
			System.out.println("sfId============"+sfId);
			globalSfId=sfId;
			
		RestTemplate restTemplate=new RestTemplate();
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		
		map.add("delStatus", 0);
		map.add("sfId", sfId);
		
		sfDetaiListItems=restTemplate.postForObject(Constants.url+"getSfItemDetailList",map,SfItemDetailList.class);
		
		
		 rawMaterialDetailsList=restTemplate.getForObject(Constants.url +"rawMaterial/getAllRawMaterial", RawMaterialDetailsList.class);
		
		 System.out.println("LIst :"+rawMaterialDetailsList.toString());
		
			List<RmItemGroup> rmItemGroupList=restTemplate.getForObject(Constants.url + "rawMaterial/getAllRmItemGroup", List.class);

		model.addObject("rmDetailList",rawMaterialDetailsList.getRawMaterialDetailsList());
		System.out.println("sf header List "+itemHeaderList.toString());
		
		sfDetailList=sfDetaiListItems.getSfItemDetail();
		
		model.addObject("rmItemGroupList", rmItemGroupList);
		model.addObject("sfDetailList",sfDetailList);
		model.addObject("itemHeaderList",itemHeaderList);
		model.addObject("sfName",sfName);	
		model.addObject("sfType",sfTypeName);	
		}catch (Exception e) {
			System.out.println("Error in showAddSfItemDetail Details ");
			System.out.println(e.getMessage());
				e.printStackTrace();

		}
		return model;
	}	
	
	//edit sf Header

	@RequestMapping(value = "/editSfItemHeader/{sfId}/{sfName}/{sfTypeName}", method = RequestMethod.GET)
	public ModelAndView editSfItemDetail(@PathVariable int sfId,@PathVariable String sfName,@PathVariable  String sfTypeName,HttpServletRequest request, HttpServletResponse response) {
		
			System.out.println("Inside show edit Item Header ");
			
		ModelAndView model=new ModelAndView("masters/rawMaterial/editItemSfHeader");
	
		try {
			
		RestTemplate restTemplate=new RestTemplate();
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		//GetItemSfHeader editHeader=new GetItemSfHeader();
		GetItemSfHeader editHeader=new GetItemSfHeader();
		for(int i=0;i<itemHeaderList.size();i++) {
		
			if(itemHeaderList.get(i).getSfId()==sfId)
			editHeader=itemHeaderList.get(i);

		}
		editSfId=editHeader.getSfId();
		
		 rawMaterialDetailsList=restTemplate.getForObject(Constants.url +"rawMaterial/getAllRawMaterial", RawMaterialDetailsList.class);
		
		 System.out.println("LIst :"+rawMaterialDetailsList.toString());
		
		model.addObject("rmDetailList",rawMaterialDetailsList.getRawMaterialDetailsList());
		
		System.out.println("sf header List "+itemHeaderList.toString());
		
		
		 map = new LinkedMultiValueMap<String, Object>();

		 rawMaterialUomList=new ArrayList<>();
		rawMaterialUomList = restTemplate.getForObject(Constants.url + "rawMaterial/getRmUom",
				List.class);
		
			map.add("delStatus", 0);
			sfTypeList=new ArrayList<>();
		 sfTypeList=restTemplate.postForObject(Constants.url+"getSfType",map,List.class);
		
		 
		model.addObject("editHeader",editHeader);
		model.addObject("sfName",sfName);	
		model.addObject("sfType",sfTypeName);
		model.addObject("sfTypeList", sfTypeList);
		
		model.addObject("rmUomList", rawMaterialUomList);
		
		//model.addObject("sfTypeList", sfTypeList);

		}catch (Exception e) {
			
			System.out.println("Error in editItem SfItemDetail Details ");
			
			System.out.println(e.getMessage());
			e.printStackTrace();

		}
		
		return model;
	}	
	
	
	@RequestMapping(value = "/editSfHeader", method = RequestMethod.POST)
	public String editSfHeader(HttpServletRequest request, HttpServletResponse response) {
		
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
		
		float sfMaxQty=Float.parseFloat(request.getParameter("sf_max_qty"));
		
		float mulFactor=Float.parseFloat(request.getParameter("mul_factor"));
		
		System.out.println("sfMaxQty"+sfMaxQty);
		
		ItemSfHeader header=new ItemSfHeader();
		
		header.setSfId(editSfId);
		header.setDelStatus(0);
		header.setMaxLevelQty(sfMaxQty);
		header.setMinLevelQty(sfMinQty);
		header.setReorderLevelQty(sfReorderQty);
		header.setSfName(sfItemName);
		header.setSfType(sfType);
		header.setSfUomId(sfItemUoM);
		header.setSfWeight(sfItewWeight);
		header.setStockQty(sfStockQty);
		header.setMulFactor(mulFactor);
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
			System.out.println("ex in header Edit Process = "+e.getMessage());
			
		}
		
		return "redirect:/showItemSf";
	
	}
	
	//edit df header
	
	@RequestMapping(value = "/getItemDetail", method = RequestMethod.GET)
	@ResponseBody public List<ItemSfDetail> getItemDetail(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside get Item Detail  " );

		ModelAndView model=new ModelAndView("masters/rawMaterial/itemSfDetail");
		//add new Item
		ItemSfDetail sfDetail=new ItemSfDetail();
		
		try {
			
			if(Integer.parseInt(request.getParameter("key"))==-1 && Integer.parseInt(request.getParameter("editKey"))==-1 ) {
				
			int materialType=Integer.parseInt(request.getParameter("mat_type"));
		
			int materialNameId=Integer.parseInt(request.getParameter("mat_name_id"));

			float sfWeight=Float.parseFloat(request.getParameter("sf_weight"));
			
			float qty=Float.parseFloat(request.getParameter("qty"));
			
			String matName=request.getParameter("mat_name");
			System.out.println("mat name "+matName);
			//int unitOM=Integer.parseInt(request.getParameter("unit_o_M"));
			int unitOM=0;
			
			for(int i=0;i<commonConfs.size();i++) {
				
				if(commonConfs.get(i).getId()==materialNameId) {
					
					unitOM=commonConfs.get(i).getRmUomId();
				}
				
			}
			
			sfDetail.setDelStatus(0);
			sfDetail.setRmType(materialType);
			sfDetail.setRmId(materialNameId);
			sfDetail.setRmName(matName);
			sfDetail.setRmQty(qty);
			sfDetail.setRmWeight(sfWeight);
			sfDetail.setSfId(globalSfId);
			sfDetail.setRmUnit(unitOM);
			
			sfDetailList.add(sfDetail);// end of add new Item
			}
			else if(Integer.parseInt(request.getParameter("key"))!=-1 && Integer.parseInt(request.getParameter("editKey"))==-2 ) {

				int key=Integer.parseInt(request.getParameter("key"));
				System.out.println("key for delete "+ key);
				sfDetailList.get(key).setDelStatus(1);
				System.out.println("delete Status setted  Successfully ");

			}
			else if(Integer.parseInt(request.getParameter("key"))==-1 && Integer.parseInt(request.getParameter("editKey"))==-3 ) {
				System.out.println(" inside edit ");
			}
			
		}catch (Exception e) {
			System.out.println("Failed To receive Item Detail "+e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("final List "+sfDetailList.toString());
		  
		
	return sfDetailList;
	
	}
	@RequestMapping(value = "/getSingleItem", method = RequestMethod.GET)
	@ResponseBody public List<ItemSfDetail> getSingleItem(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside get  Single  Item  " );
		/*int key=Integer.parseInt(request.getParameter("key"));*/
		
		/*ItemSfDetail singleItem=sfDetailList.get(key);
		List<ItemSfDetail> singleItemList=new ArrayList<>();
		
		singleItemList.add(singleItem);
		
		System.out.println("single Item "+singleItem.toString());*/
		
		return sfDetailList;
		
	}
	
	@RequestMapping(value = "/itemForEdit", method = RequestMethod.GET)
	@ResponseBody public List<ItemSfDetail> itemForEdit(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(" Inside  Item For Edit " );

		ModelAndView model=new ModelAndView("masters/rawMaterial/itemSfDetail");
		
		int unitOM=0;
		
		int key=Integer.parseInt(request.getParameter("key"));
		
		try {
			//String strMaterialNameId=request.getParameter("mat_name_id");

			int materialType=Integer.parseInt(request.getParameter("mat_type"));
		
			int materialNameId=Integer.parseInt(request.getParameter("mat_name_id"));

			float sfWeight=Float.parseFloat(request.getParameter("sf_weight"));
			
			float qty=Float.parseFloat(request.getParameter("qty"));
			
			String matName=request.getParameter("mat_name");
			
			System.out.println("mat name "+matName);

			
			for(int i=0;i<commonConfs.size();i++) {
				
				if(commonConfs.get(i).getId()==materialNameId) {
					
					unitOM=commonConfs.get(i).getRmUomId();
				}
			}
			
			System.out.println("inside If material Id matched");
			
			 sfDetailList.get(key).setDelStatus(0);;
			// sfDetailList.get(key).setRmType(sfDetailList.get(key).getRmType());;
			 sfDetailList.get(key).setRmType(materialType);;
			 sfDetailList.get(key).setRmId(materialNameId);;
			 sfDetailList.get(key).setRmName(matName);;
			 sfDetailList.get(key).setRmQty(qty);
			 sfDetailList.get(key).setRmWeight(sfWeight);
			 sfDetailList.get(key).setSfId(globalSfId);
			 sfDetailList.get(key).setRmUnit(unitOM);;
			
		}catch (Exception e) {
			
			System.out.println("Failed To receive Item Detail "+e.getMessage());
			
			e.printStackTrace();
		}
		
		System.out.println("final List "+sfDetailList.toString());
		  
	return sfDetailList;
	
	}
	
	@RequestMapping(value = "/insertSfItemDetail", method = RequestMethod.POST)
	 public String insertItemDetail(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("i De " );
		
		ModelAndView model = new ModelAndView("masters/rawMaterial/itemSf");

		
		System.out.println("Inside show details");
		RestTemplate restTemplate=new RestTemplate();
		
		System.out.println("Item Sf Detail Before Submit "+sfDetailList.toString());
		
		Info info=restTemplate.postForObject(Constants.url+"postSfItemDetail",sfDetailList,Info.class);
		
		if(!info.getError()) {
		sfDetailList=new ArrayList<ItemSfDetail>() ;
		}
		
		System.out.println("Redirecting to show Sf Item  after Inserting item details ");

		return "redirect:/showItemSf";
	}
	
	 //---------------------------------------AJAX For RM List -----------------------------------------
	@RequestMapping(value = "/getRawMaterial", method = RequestMethod.GET)
	public @ResponseBody List<CommonConf> getRawMaterialList(HttpServletRequest request, HttpServletResponse response) {

	ModelAndView model = new ModelAndView("masters/rawMaterial/addItemDetail");
	
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
}