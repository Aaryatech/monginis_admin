package com.ats.adminpanel.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.zefer.html.doc.t;

import com.amazonaws.retry.PredefinedRetryPolicies.SDKDefaultRetryCondition;
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
import com.ats.adminpanel.model.RawMaterial.SfItemDetailList;
import com.ats.adminpanel.model.franchisee.CommonConf;
import com.ats.adminpanel.model.item.Item;

@Controller
public class ItemSfController {
	
	List<GetSfType> sfTypeList;
	List<RawMaterialUom> rawMaterialUomList;
	List<ItemSfDetail> sfDetailList=new ArrayList<>();
	RawMaterialDetailsList rawMaterialDetailsList;
	public static int globalSfId=0;
	
	public static  List<CommonConf> commonConfs=new ArrayList<CommonConf>();

	
	SfItemDetailList sfDetaiListItems;
	
	List<ItemSfDetail> sfItemDetail=new ArrayList<>();
	
	public  List<GetItemSfHeader> itemHeaderList=new ArrayList<GetItemSfHeader>();;
	
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
	
	
	@RequestMapping(value = "/showAddSfItemDetail/{sfId}/{sfName}/{sfTypeName}", method = RequestMethod.GET)
	public ModelAndView showAddSfItemDetail(@PathVariable int sfId,@PathVariable String sfName,@PathVariable  String sfTypeName,HttpServletRequest request, HttpServletResponse response) {
		
		Constants.mainAct=4;
		Constants.subAct=41;
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
		
		model.addObject("rmDetailList",rawMaterialDetailsList.getRawMaterialDetailsList());
		System.out.println("sf header List "+itemHeaderList.toString());
		
		sfDetailList=sfDetaiListItems.getSfItemDetail();
		
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
	
	@RequestMapping(value = "/getItemDetail", method = RequestMethod.GET)
	@ResponseBody public List<ItemSfDetail> getItemDetail(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside get Item Detail  " );

		Constants.mainAct=4;
		Constants.subAct=41;
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

		Constants.mainAct=4;
		Constants.subAct=41;
		ModelAndView model=new ModelAndView("masters/rawMaterial/itemSfDetail");
		
		int unitOM=0;
		
		int key=Integer.parseInt(request.getParameter("key"));
		
		try {
			/*String strMaterialNameId=request.getParameter("mat_name_id");

			int materialType=Integer.parseInt(request.getParameter("mat_type"));
		
			int materialNameId=Integer.parseInt(request.getParameter("mat_name_id"));
*/
			float sfWeight=Float.parseFloat(request.getParameter("sf_weight"));
			
			float qty=Float.parseFloat(request.getParameter("qty"));
			
			/*String matName=request.getParameter("mat_name");*/
			
		/*	System.out.println("mat name "+matName);

			
			for(int i=0;i<commonConfs.size();i++) {
				
				if(commonConfs.get(i).getId()==materialNameId) {
					
					unitOM=commonConfs.get(i).getRmUomId();
				}
			}*/
			
			System.out.println("inside If material Id matched");
			
			 sfDetailList.get(key).setDelStatus(0);;
			 sfDetailList.get(key).setRmType(sfDetailList.get(key).getRmType());;
			 sfDetailList.get(key).setRmId(sfDetailList.get(key).getRmId());;
			 sfDetailList.get(key).setRmName(sfDetailList.get(key).getRmName());;
			 sfDetailList.get(key).setRmQty(qty);
			 sfDetailList.get(key).setRmWeight(sfWeight);
			 sfDetailList.get(key).setSfId(globalSfId);
			 sfDetailList.get(key).setRmUnit(sfDetailList.get(key).getRmUnit());;
			
		}catch (Exception e) {
			
			System.out.println("Failed To receive Item Detail "+e.getMessage());
			
			e.printStackTrace();
		}
		
		System.out.println("final List "+sfDetailList.toString());
		  
	return sfDetailList;
	
	}
	
	@RequestMapping(value = "/insertSfItemDetail", method = RequestMethod.GET)
	@ResponseBody public String insertItemDetail(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("i De " );
		
		Constants.mainAct=4;
		Constants.subAct=41;
		
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