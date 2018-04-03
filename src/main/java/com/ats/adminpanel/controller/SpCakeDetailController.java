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
import com.ats.adminpanel.model.SfndRawItem;
import com.ats.adminpanel.model.SpCakeDetailed;
import com.ats.adminpanel.model.SpCakeDetailedList;
import com.ats.adminpanel.model.SpCakeResponse;
import com.ats.adminpanel.model.RawMaterial.Info;
import com.ats.adminpanel.model.RawMaterial.ItemSfHeader;
import com.ats.adminpanel.model.RawMaterial.ItemSfHeaderList;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetails;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetailsList;
import com.ats.adminpanel.model.franchisee.CommonConf;



@Controller
@Scope("session")
public class SpCakeDetailController {
	
	
	public List<SpCakeDetailed> spCakeDetailedList = new ArrayList<SpCakeDetailed>();
	public String cakeName = null;
	List<SfndRawItem> sfndRawItemlist = new ArrayList<SfndRawItem>();
	private int sp_Id;
	List<CommonConf> commonConfList=new ArrayList<CommonConf>();
	
	
	
	@RequestMapping(value = "/viewSpCakeDetailed/{spId}", method = RequestMethod.GET)
	public ModelAndView viewSpCakeDetailed(@PathVariable int spId,HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("spcake/spCakeDetailed");
		spCakeDetailedList = new ArrayList<SpCakeDetailed>();
		//String spId= request.getParameter("spId");
		sp_Id=spId;
		
		System.out.println(spId);
		try {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("SpId",spId);
		RestTemplate rest = new RestTemplate();
		
		SpCakeResponse spCakeResponse = rest.getForObject(Constants.url + "showSpecialCakeList",
				SpCakeResponse.class);
		
		for(int i=0;i<spCakeResponse.getSpecialCake().size();i++)
		{
			if(spId==spCakeResponse.getSpecialCake().get(i).getSpId())
			{
				cakeName=spCakeResponse.getSpecialCake().get(i).getSpName();
				break;
			}
		}
		
		SpCakeDetailedList spCakeDetailedLi=rest.postForObject(Constants.url + "/getSpCakeList",map, SpCakeDetailedList.class);
		spCakeDetailedList =spCakeDetailedLi.getSpCakeDetailed();
		System.out.println(spCakeDetailedLi.toString());
			model.addObject("spCakeDetailedList",spCakeDetailedList);
			model.addObject("cakeId", spId);
			model.addObject("cakeName", cakeName);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/getRawMaterialListinSpcake", method = RequestMethod.GET)
	public @ResponseBody List<CommonConf> getRawMaterialListinSpcake(HttpServletRequest request, HttpServletResponse response) {

		
		int rmType=Integer.parseInt(request.getParameter("rm_type"));
		   System.out.println("rmType:"+rmType);
		   commonConfList=new ArrayList<CommonConf>();
		RestTemplate rest=new RestTemplate();
		
		

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

			   }
			   System.out.println("Common Rm List2:"+commonConfList.toString());

		}
		
		return commonConfList;
		}
	
	@RequestMapping(value = "/addCakeDetailItem", method = RequestMethod.GET)
	@ResponseBody
	public List<SpCakeDetailed> addCakeDetailItem(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("asdfsdfdfa");
		int cakeId=Integer.parseInt(request.getParameter("cakeId"));
		int rmType=Integer.parseInt(request.getParameter("rmType"));
		int baseQty=Integer.parseInt(request.getParameter("baseQty"));
		int rmId=Integer.parseInt(request.getParameter("rmId"));
		float rmWeight=Float.parseFloat(request.getParameter("rmWeight"));
		int rmQty=Integer.parseInt(request.getParameter("rmQty"));
		String rmName=request.getParameter("rmName");
		

		System.out.println("int add cake details"+rmType+ rmName);
		try
		{
			SpCakeDetailed spCakeDetailed = new SpCakeDetailed();

			
			spCakeDetailed.setSpId(cakeId);
			spCakeDetailed.setRmType(rmType);
			spCakeDetailed.setRmId(rmId);
			spCakeDetailed.setRmWeight(rmWeight);
			spCakeDetailed.setRmQty(rmQty);
			spCakeDetailed.setRmName(rmName);
			spCakeDetailed.setNoOfPiecesPerItem(baseQty);
			
			for(int j=0;j<commonConfList.size();j++)
			{
				if(rmName.equals(commonConfList.get(j).getName()))
				{
					spCakeDetailed.setRmUomId(commonConfList.get(j).getRmUomId());
					break;
				}
			}
			
			 
			
			spCakeDetailed.setDelStatus(0);
			spCakeDetailedList.add(spCakeDetailed);
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return spCakeDetailedList;
	}
	
	@RequestMapping(value = "/deleteSpCakeDetail", method = RequestMethod.GET)
	public @ResponseBody List<SpCakeDetailed> deleteItemDetail(HttpServletRequest request, HttpServletResponse response) {
		
		int index=Integer.parseInt(request.getParameter("key"));
		System.out.println("key no."+index);
		if(spCakeDetailedList.get(index).getSpCakeDetailedId()==0)
		{
			spCakeDetailedList.remove(index);
		}
		else
		{
			spCakeDetailedList.get(index).setDelStatus(1);
		}
			System.out.println("ItemDetail List D:"+spCakeDetailedList.toString());

		return spCakeDetailedList;
	}
	
	
	
	@RequestMapping(value = "/editSpCakeDetail", method = RequestMethod.GET)
	public @ResponseBody List<SpCakeDetailed> editdetail(HttpServletRequest request, HttpServletResponse response) {
		
		int cakeId=Integer.parseInt(request.getParameter("cakeId"));
		int rmType=Integer.parseInt(request.getParameter("rmType"));
		int rmId=Integer.parseInt(request.getParameter("rmId"));
		float rmWeight=Float.parseFloat(request.getParameter("rmWeight"));
		int rmQty=Integer.parseInt(request.getParameter("rmQty"));
		String rmName=request.getParameter("rmName");
		int baseQty=Integer.parseInt(request.getParameter("baseQty"));
		
		
		int index=Integer.parseInt(request.getParameter("key"));
		System.out.println("Key:"+index);
		try
		{
			System.out.println("itemDetailList::"+spCakeDetailedList.toString());
			for(int i=0;i<spCakeDetailedList.size();i++)
			{
				if(i==index)
				{
					spCakeDetailedList.get(index).setSpId(cakeId);
					
					spCakeDetailedList.get(index).setRmId(rmId);
					spCakeDetailedList.get(index).setRmName(rmName);
					spCakeDetailedList.get(index).setRmQty(rmQty);
					spCakeDetailedList.get(index).setRmWeight(rmWeight);
					spCakeDetailedList.get(index).setRmQty(rmQty);
					spCakeDetailedList.get(index).setRmType(rmType);
					spCakeDetailedList.get(index).setNoOfPiecesPerItem(baseQty);
					for(int j=0;j<commonConfList.size();j++)
					{
						if(rmName.equals(commonConfList.get(j).getName()))
						{
							spCakeDetailedList.get(index).setRmUomId(commonConfList.get(j).getRmUomId());
							break;
						}
					}
					spCakeDetailedList.get(index).setDelStatus(0);
			  System.out.println("ItemDetail"+ spCakeDetailedList.get(index));
			
			 }
				
			}
			System.out.println("Edit ItemDetail Ajax: "+ spCakeDetailedList.get(0).toString());
				System.out.println("ItemDetail List:"+spCakeDetailedList.toString());
		}catch(Exception e)
		{
			e.getStackTrace();
		}
		
		return spCakeDetailedList;
		
	}
	
	@RequestMapping(value = "/editSpItemDetail", method = RequestMethod.GET)
	public @ResponseBody SpCakeDetailed editSpItemDetail(HttpServletRequest request, HttpServletResponse response) {
		
		int index=Integer.parseInt(request.getParameter("key"));
		System.out.println("Key:"+index);
		SpCakeDetailed  getItemDetail=new SpCakeDetailed(); 
		
		System.out.println("itemDetailList::"+spCakeDetailedList.toString());
		for(int i=0;i<spCakeDetailedList.size();i++)
		{
			if(i==index)
			{ 
		     getItemDetail=spCakeDetailedList.get(index);
			}
		
		}
		System.out.println("Edit ItemDetail Ajax: "+getItemDetail.toString());
		return getItemDetail;
	}
	
	
	@RequestMapping(value = "/insertSpCakeDetailed", method = RequestMethod.GET)
	public @ResponseBody List<SpCakeDetailed> insertSpCakeDetailed(HttpServletRequest request, HttpServletResponse response) {
		 
		
		System.out.println("spCakeDetailedList While submiting::"+spCakeDetailedList.toString());
		try
		{
			RestTemplate restTemplate = new RestTemplate();
			SpCakeDetailedList spCakeDetailed = new SpCakeDetailedList();
			spCakeDetailed.setSpCakeDetailed(spCakeDetailedList);
			Info info=restTemplate.postForObject(Constants.url+"/insertSpCakeDetailed",spCakeDetailed,Info.class);
			System.out.println(info);
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("SpId",sp_Id);
			RestTemplate rest = new RestTemplate();
			
			SpCakeResponse spCakeResponse = rest.getForObject(Constants.url + "showSpecialCakeList",
					SpCakeResponse.class);
			
			for(int i=0;i<spCakeResponse.getSpecialCake().size();i++)
			{
				if(sp_Id==spCakeResponse.getSpecialCake().get(i).getSpId())
				{
					cakeName=spCakeResponse.getSpecialCake().get(i).getSpName();
					break;
				}
			}
			
			SpCakeDetailedList spCakeDetailedLi=rest.postForObject(Constants.url + "/getSpCakeList",map, SpCakeDetailedList.class);
			spCakeDetailedList =spCakeDetailedLi.getSpCakeDetailed();
			System.out.println(spCakeDetailedLi.toString());

		}catch(Exception e)
		{
			e.getStackTrace();
			System.out.println("exce "+e.getMessage());
		}
		System.out.println("Edit ItemDetail Ajax: "+spCakeDetailedList.toString());
		return spCakeDetailedList;
		
		
	}
	

	
	
}
