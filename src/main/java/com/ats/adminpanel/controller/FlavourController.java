package com.ats.adminpanel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
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
import com.ats.adminpanel.model.RawMaterial.Info;
import com.ats.adminpanel.model.RawMaterial.ItemDetail;
import com.ats.adminpanel.model.RawMaterial.ItemDetailList;
import com.ats.adminpanel.model.RawMaterial.ItemSfHeader;
import com.ats.adminpanel.model.RawMaterial.ItemSfHeaderList;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetails;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetailsList;
import com.ats.adminpanel.model.RawMaterial.RmItemGroup;
import com.ats.adminpanel.model.flavours.Flavour;
import com.ats.adminpanel.model.flavours.FlavourDetail;
import com.ats.adminpanel.model.flavours.FlavourDetailList;
import com.ats.adminpanel.model.franchisee.CommonConf;
import com.ats.adminpanel.model.item.Item;

@Controller
@Scope("session")
public class FlavourController {

	//public  List<Item> itemList;	
//	public  List<ItemDetail> itemDetailList;
	
	public List<Flavour> flavoursList;
	public List<FlavourDetail> flavourDetailList;
	//FlavourDetail fvlrDetail = new FlavourDetail();
	public  List<CommonConf> commonConfs=new ArrayList<CommonConf>();
	public  int globalId=0;
	int grpIdGlobal = 0;
	
	//----------------------------Show Add Flavour Detail Jsp----------------------------------------------------
		@RequestMapping(value = "/showFlavourDetail/{spfId}", method = RequestMethod.GET)
		public ModelAndView showItemDetail(@PathVariable int spfId,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/rawMaterial/addFlavourDetail");
		
		try {
		
		RestTemplate rest=new RestTemplate();
		flavoursList= new ArrayList<Flavour>();
		flavourDetailList=new ArrayList<FlavourDetail>();
		globalId=spfId;
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("id", spfId);

		Flavour flavour = rest.postForObject(Constants.url + "getFlavourById", map,Flavour.class);
		
		//ItemDetailList itemDetailsList= rest.postForObject(Constants.url + "rawMaterial/getItemDetails",map, ItemDetailList.class);
		FlavourDetailList flavourDetailsList = rest.postForObject(Constants.url + "getflavourDetails",map, FlavourDetailList.class);
		
		
		List<RmItemGroup> rmItemGroupList=rest.getForObject(Constants.url + "rawMaterial/getAllRmItemGroup", List.class);
		
		System.out.println("RMItem---"+rmItemGroupList);
		for(int i=0;i< flavourDetailsList.getFlavourDetailList().size();i++)
		{  
			FlavourDetail flavourDetail=new FlavourDetail();
			
			flavourDetail.setFlavourDetailId(flavourDetailsList.getFlavourDetailList().get(i).getFlavourDetailId());
			flavourDetail.setFlavourId(flavourDetailsList.getFlavourDetailList().get(i).getFlavourId());
			flavourDetail.setFlavourName(flavourDetailsList.getFlavourDetailList().get(i).getFlavourName());
			flavourDetail.setNoPiecesPerFlavour(flavourDetailsList.getFlavourDetailList().get(i).getNoPiecesPerFlavour());
			flavourDetail.setRmId(flavourDetailsList.getFlavourDetailList().get(i).getRmId());
			flavourDetail.setRmName(flavourDetailsList.getFlavourDetailList().get(i).getRmName());
			flavourDetail.setRmQty(flavourDetailsList.getFlavourDetailList().get(i).getRmQty());
			flavourDetail.setRmType(flavourDetailsList.getFlavourDetailList().get(i).getRmType());
			flavourDetail.setRmUomId(flavourDetailsList.getFlavourDetailList().get(i).getRmUomId());
			flavourDetail.setRmWeight(flavourDetailsList.getFlavourDetailList().get(i).getRmWeight());
			flavourDetail.setDelStatus(flavourDetailsList.getFlavourDetailList().get(i).getDelStatus());
			flavourDetail.setInt1(flavourDetailsList.getFlavourDetailList().get(i).getInt1());//new
			flavourDetail.setInt2(flavourDetailsList.getFlavourDetailList().get(i).getInt2());//new
			flavourDetail.setVarchar1(flavourDetailsList.getFlavourDetailList().get(i).getVarchar1());//new
			flavourDetail.setVarchar2(flavourDetailsList.getFlavourDetailList().get(i).getVarchar2());//new
			flavourDetail.setSequenceNo(flavourDetailsList.getFlavourDetailList().get(i).getSequenceNo());

			flavourDetailList.add(flavourDetail);
		}
		model.addObject("flavourDetailList", flavourDetailsList.getFlavourDetailList());
		model.addObject("flavour", flavour);
		model.addObject("rmItemGroupList", rmItemGroupList);
		}
		catch(Exception e)
		{
			System.err.println();
		}
		return model;
		}
		//----------------------------------------END-------------------------------------------------------------
	
		@RequestMapping(value = "/insertFlavourDetail", method = RequestMethod.GET)
		public @ResponseBody List<FlavourDetail> insertFlavourDetail(HttpServletRequest request, HttpServletResponse response) {
			
			System.out.println("flavourrrrrrrrrrrrrr");
			
			int flavourId=Integer.parseInt(request.getParameter("itemId")); //flavour Id
			System.out.println("flavourId"+flavourId);
			
			String flavourName=request.getParameter("itemName"); //flavour Name
			System.out.println("flavourName"+flavourName);

			
			int noOfPiecesPerItem=Integer.parseInt(request.getParameter("baseQty"));
			
			int rmType=Integer.parseInt(request.getParameter("rmType"));
			
			int rmId=Integer.parseInt(request.getParameter("rmId"));
			
			String rmName=request.getParameter("rmName");
			
			int seq = Integer.parseInt(request.getParameter("sequenceNo"));
			
			int rmWeight=Integer.parseInt(request.getParameter("rmWeight"));
			
			float rmQty=Float.parseFloat(request.getParameter("rmQty"));
			
            int int1=Integer.parseInt(request.getParameter("isMultiFactor"));//isMultiFactor new
			
			int int2=Integer.parseInt(request.getParameter("applItemId"));//Item // ratio_item_id:applItemId new
			
			String varchar1=request.getParameter("multiFactor");//multiFactor
			
			FlavourDetail flavourDetail=new FlavourDetail();
			
			flavourDetail.setFlavourId(flavourId);
			flavourDetail.setFlavourName(flavourName);
			flavourDetail.setRmId(rmId);
			flavourDetail.setRmName(rmName);
			flavourDetail.setRmWeight(rmWeight);
			flavourDetail.setRmQty(rmQty);
			flavourDetail.setRmType(rmType);
			flavourDetail.setNoPiecesPerFlavour(noOfPiecesPerItem);
			flavourDetail.setSequenceNo(seq);
			
			
			flavourDetail.setInt1(int1);//isMultiplication Factor  0=No, 1=Yes
			flavourDetail.setInt2(int2);//new
			flavourDetail.setVarchar1(varchar1);//Multiplication Factor
			flavourDetail.setVarchar2("NA");//new
			flavourDetail.setBoll1(0);
			flavourDetail.setBoll2(0);
			
			for(CommonConf commonConf:commonConfs)
			{
				if(commonConf.getId()==flavourDetail.getRmId())
				{
					flavourDetail.setRmUomId(commonConf.getRmUomId());

				}
			}			
			
			flavourDetail.setDelStatus(0);
			System.out.println("FlavourDetail-------"+flavourDetail);
			
			flavourDetailList.add(flavourDetail);
			
			System.out.println("FlavourDetail List:"+flavourDetailList.toString());
			return flavourDetailList;
		
		}
		
		@RequestMapping(value = "/getRawMaterialList1", method = RequestMethod.GET)
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
		
		
		@RequestMapping(value = "/addFlavourDetail", method = RequestMethod.POST)
		public  String addItemDetail(HttpServletRequest request, HttpServletResponse response) {
			
			RestTemplate restTemplate=new RestTemplate();
			
			System.out.println("Item  Detail Before Submit "+flavourDetailList.toString());
			Info  info = null;
			try
			{
			info=restTemplate.postForObject(Constants.url+"/saveFlavourDetails",flavourDetailList,Info.class);
			
			flavourDetailList=new ArrayList<FlavourDetail>();//new Object for Item Detail List
			
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", globalId);
			FlavourDetailList flavourDetailsList = restTemplate.postForObject(Constants.url + "getflavourDetails",map, FlavourDetailList.class);
			
			for(int i=0;i< flavourDetailsList.getFlavourDetailList().size();i++)
			{  
				FlavourDetail flavourDetail=new FlavourDetail();
				
				flavourDetail.setFlavourDetailId(flavourDetailsList.getFlavourDetailList().get(i).getFlavourDetailId());
				flavourDetail.setFlavourId(flavourDetailsList.getFlavourDetailList().get(i).getFlavourId());
				flavourDetail.setFlavourName(flavourDetailsList.getFlavourDetailList().get(i).getFlavourName());
				flavourDetail.setNoPiecesPerFlavour(flavourDetailsList.getFlavourDetailList().get(i).getNoPiecesPerFlavour());
				flavourDetail.setRmId(flavourDetailsList.getFlavourDetailList().get(i).getRmId());
				flavourDetail.setRmName(flavourDetailsList.getFlavourDetailList().get(i).getRmName());
				flavourDetail.setRmQty(flavourDetailsList.getFlavourDetailList().get(i).getRmQty());
				flavourDetail.setRmType(flavourDetailsList.getFlavourDetailList().get(i).getRmType());
				flavourDetail.setRmUomId(flavourDetailsList.getFlavourDetailList().get(i).getRmUomId());
				flavourDetail.setRmWeight(flavourDetailsList.getFlavourDetailList().get(i).getRmWeight());
				flavourDetail.setDelStatus(flavourDetailsList.getFlavourDetailList().get(i).getDelStatus());
				flavourDetail.setInt1(flavourDetailsList.getFlavourDetailList().get(i).getInt1());//isMultiFactor new
				flavourDetail.setInt2(flavourDetailsList.getFlavourDetailList().get(i).getInt2());//new
				flavourDetail.setVarchar1(flavourDetailsList.getFlavourDetailList().get(i).getVarchar1());//new
				flavourDetail.setVarchar2(flavourDetailsList.getFlavourDetailList().get(i).getVarchar2());//new
				flavourDetail.setSequenceNo(flavourDetailsList.getFlavourDetailList().get(i).getSequenceNo());

				flavourDetailList.add(flavourDetail);
			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("EXC:"+e.getStackTrace());

			}
			System.out.println("flavourDetailList:"+flavourDetailList.toString());


			return "redirect:/flavoursList";
			
		}
		
		@RequestMapping(value = "/editFlavourDetail", method = RequestMethod.GET)
		public @ResponseBody FlavourDetail editFlavourDetail(HttpServletRequest request, HttpServletResponse response) {
			
			int index=Integer.parseInt(request.getParameter("key"));
			System.out.println("Key:"+index);
			FlavourDetail  getFlevourDetail=new FlavourDetail(); 
			
			System.out.println("flavourDetailList::"+flavourDetailList.toString());
			for(int i=0;i<flavourDetailList.size();i++)
			{
				if(i==index)
				{
					getFlevourDetail=flavourDetailList.get(index);
				}
			
			}
			System.out.println("Edit Flavour Detail Ajax: "+getFlevourDetail.toString());
			return getFlevourDetail;
		}
		
		@RequestMapping(value = "/deleteFlavourDetail", method = RequestMethod.GET)
		public @ResponseBody List<FlavourDetail> deleteFlavourDetail(HttpServletRequest request, HttpServletResponse response) {
			
			int index=Integer.parseInt(request.getParameter("key"));

			if(flavourDetailList.get(index).getFlavourDetailId()==0)
			{
				flavourDetailList.remove(index);
			}
			else
			{
				flavourDetailList.get(index).setDelStatus(1);
			}
 			System.out.println("FlavourDetail List D:"+flavourDetailList.toString());

			return flavourDetailList;
		}
		
		@RequestMapping(value = "/editFlavour", method = RequestMethod.GET)
		public @ResponseBody List<FlavourDetail> editItem(HttpServletRequest request, HttpServletResponse response) {
			
			int itemId=Integer.parseInt(request.getParameter("itemId"));
			System.out.println("itemId"+itemId);
			
			String itemName=request.getParameter("itemName");
			System.out.println("itemName"+itemName);

			int noPiecesPerFlavour=Integer.parseInt(request.getParameter("baseQty"));
			
			int rmType=Integer.parseInt(request.getParameter("rmType"));
			
			int rmId=Integer.parseInt(request.getParameter("rmId"));
			
			String rmName=request.getParameter("rmName");
			
			int rmWeight=Integer.parseInt(request.getParameter("rmWeight"));
			
			float rmQty=Float.parseFloat(request.getParameter("rmQty"));
			
			int index=Integer.parseInt(request.getParameter("key"));
			System.out.println("Key:"+index);
			
			int int1=Integer.parseInt(request.getParameter("isMultiFactor"));//isMultiFactor new
			
			int int2=Integer.parseInt(request.getParameter("applItemId"));//applItemId new
			
			String varchar1=request.getParameter("multiFactor");//multiFactor new
			int seq = Integer.parseInt(request.getParameter("sequenceNo"));
			System.out.println("flavourDetailList::"+flavourDetailList.toString());
			for(int i=0;i<flavourDetailList.size();i++)
			{
				if(i==index)
				{
					flavourDetailList.get(index).setFlavourId(itemId);
					flavourDetailList.get(index).setFlavourName(itemName);
					flavourDetailList.get(index).setRmId(rmId);
					flavourDetailList.get(index).setRmName(rmName);
					flavourDetailList.get(index).setRmQty(rmQty);
					flavourDetailList.get(index).setRmWeight(rmWeight);
					flavourDetailList.get(index).setRmQty(rmQty);
					flavourDetailList.get(index).setRmType(rmType);
					flavourDetailList.get(index).setSequenceNo(seq);
					flavourDetailList.get(index).setNoPiecesPerFlavour(noPiecesPerFlavour);
					flavourDetailList.get(index).setInt1(int1);//isMultiFactor new
					flavourDetailList.get(index).setInt2(int2);//new
					flavourDetailList.get(index).setVarchar1(varchar1);//new
					flavourDetailList.get(index).setVarchar2("NA");//new
					 

			    for(CommonConf commonConf:commonConfs)
			    {
				   if(commonConf.getId()== flavourDetailList.get(index).getRmId())
				   {
					   flavourDetailList.get(index).setRmUomId(commonConf.getRmUomId());

				   }
			    }
			
			    flavourDetailList.get(index).setDelStatus(0);
			  System.out.println("ItemDetail"+ flavourDetailList.get(index));
			
			 }
				
			}
			System.out.println("Edit FlavourDetail Ajax: "+ flavourDetailList.get(index).toString());
 			System.out.println("ItemDetail List:"+flavourDetailList.toString());
			return flavourDetailList;
			
		}
}
