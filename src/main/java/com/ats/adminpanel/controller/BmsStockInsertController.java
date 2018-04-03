package com.ats.adminpanel.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.BmsStockDetailed;
import com.ats.adminpanel.model.BmsStockHeader;
import com.ats.adminpanel.model.BmsStockItemList;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.SfndRawItem;
import com.ats.adminpanel.model.RawMaterial.GetItemSfHeader;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetailsList;
import com.ats.adminpanel.model.RawMaterial.RawMaterialUom;
import com.ats.adminpanel.model.RawMaterial.RawMaterialUomList;
import com.itextpdf.text.log.SysoCounter;




@Controller
@Scope("session")
public class BmsStockInsertController {
	
	public List<BmsStockItemList> sfndRawItemlist = new ArrayList<BmsStockItemList>();
	public int value;
	BmsStockHeader bmsStockHeaderedit= new BmsStockHeader();
	
	@RequestMapping(value = "/bmsstock", method = RequestMethod.GET)
	public ModelAndView bmsstock(HttpServletRequest request, HttpServletResponse response) {
		Constants.mainAct =8;
		Constants.subAct=122;
		
		ModelAndView model = new ModelAndView("productionPlan/bmsstock");//
		

		return model;

	}
	
	
	@RequestMapping(value = "/getSfndRawItem", method = RequestMethod.GET)
	@ResponseBody
	public List<BmsStockItemList> getSfndRawItem(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		BmsStockItemList sfndRawItem = new BmsStockItemList();
		sfndRawItemlist = new ArrayList<BmsStockItemList>();
		
		RestTemplate rest = new RestTemplate();
		List<GetItemSfHeader> itemHeaderList=new ArrayList<GetItemSfHeader>();
		value=Integer.parseInt(request.getParameter("itemType"));
		System.out.println("Value "+value);
		
		 RawMaterialUomList rawMaterialUomList = rest.getForObject(Constants.url + "rawMaterial/getRmUomList",
	                RawMaterialUomList.class);

	        List<RawMaterialUom> uomList = rawMaterialUomList.getRawMaterialUom();
	        
	        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	        map.add("type", value);
	        try
			{ 
	        bmsStockHeaderedit =  rest.postForObject(Constants.url + "getBmsStockForEdit",map,BmsStockHeader.class);
	        System.out.println("bmsStockHeader"+bmsStockHeaderedit);
	      
		
			
			if(value==1)
			{
				System.out.println("in If  ");
				RawMaterialDetailsList rawMaterialDetailsList=rest.getForObject(Constants.url +"rawMaterial/getAllRawMaterial", RawMaterialDetailsList.class);
				if(bmsStockHeaderedit.getBmsStockId()!=0)
				{
					System.out.println("raw edit");
					for(int k=0;k<rawMaterialDetailsList.getRawMaterialDetailsList().size();k++)
					{
						int flag=0;
						 
						for(int i=0;i<bmsStockHeaderedit.getBmsStockDetailed().size();i++)
						{
							if(rawMaterialDetailsList.getRawMaterialDetailsList().get(k).getRmId()==bmsStockHeaderedit.getBmsStockDetailed().get(i).getRmId())
							{
								sfndRawItem = new BmsStockItemList();
								sfndRawItem.setBsmId(bmsStockHeaderedit.getBmsStockDetailed().get(i).getBmsStockDeatilId());
								sfndRawItem.setItemId(bmsStockHeaderedit.getBmsStockDetailed().get(i).getRmId());
								sfndRawItem.setName(bmsStockHeaderedit.getBmsStockDetailed().get(i).getRmName());
								sfndRawItem.setQty(bmsStockHeaderedit.getBmsStockDetailed().get(i).getBmsOpeningStock());
								for(int j=0;j<uomList.size();j++)
								{
									if(uomList.get(j).getUomId()==bmsStockHeaderedit.getBmsStockDetailed().get(i).getRmUom())
									{
										sfndRawItem.setUomId(uomList.get(j).getUomId());
										sfndRawItem.setUomName(uomList.get(j).getUom());
									}
								}
								sfndRawItemlist.add(sfndRawItem); 
								flag=1;
							}
							
						}
						if(flag==0)
						{ 
							sfndRawItem = new BmsStockItemList();
							sfndRawItem.setItemId(rawMaterialDetailsList.getRawMaterialDetailsList().get(k).getRmId());
							sfndRawItem.setName(rawMaterialDetailsList.getRawMaterialDetailsList().get(k).getRmName());
							for(int j=0;j<uomList.size();j++)
							{
								if(uomList.get(j).getUomId()==rawMaterialDetailsList.getRawMaterialDetailsList().get(k).getRmUomId())
								{
									sfndRawItem.setUomId(uomList.get(j).getUomId());
									sfndRawItem.setUomName(uomList.get(j).getUom());
								}
							}
							sfndRawItemlist.add(sfndRawItem);
							
						}
						 
						
					}
					
					
					
				}
				else
				{
					System.out.println("raw new");
					for(int i=0;i<rawMaterialDetailsList.getRawMaterialDetailsList().size();i++)
					{
						sfndRawItem = new BmsStockItemList();
						sfndRawItem.setItemId(rawMaterialDetailsList.getRawMaterialDetailsList().get(i).getRmId());
						sfndRawItem.setName(rawMaterialDetailsList.getRawMaterialDetailsList().get(i).getRmName());
						for(int j=0;j<uomList.size();j++)
						{
							if(uomList.get(j).getUomId()==rawMaterialDetailsList.getRawMaterialDetailsList().get(i).getRmUomId())
							{
								sfndRawItem.setUomId(uomList.get(j).getUomId());
								sfndRawItem.setUomName(uomList.get(j).getUom());
							}
						}
						sfndRawItemlist.add(sfndRawItem);
					}
				}
				
				System.out.println(sfndRawItemlist.toString());
			}
			else if(value==2)
			{
				System.out.println("in if else ");
				  map = new LinkedMultiValueMap<String, Object>();
				
				map.add("delStatus", 0);
				ParameterizedTypeReference<List<GetItemSfHeader>> typeRef = new ParameterizedTypeReference<List<GetItemSfHeader>>() {
				};
				ResponseEntity<List<GetItemSfHeader>> responseEntity = rest.exchange(Constants.url + "getItemSfHeaderList",
						HttpMethod.POST, new HttpEntity<>(map), typeRef);
				
				itemHeaderList = responseEntity.getBody();
				System.out.println("sf List "+itemHeaderList.toString());
				
				if(bmsStockHeaderedit.getBmsStockId()!=0)
				{
					System.out.println("sf edit");
					for(int k=0;k<itemHeaderList.size();k++)
					{
						int flag=0;
						 
						for(int i=0;i<bmsStockHeaderedit.getBmsStockDetailed().size();i++)
						{
							if(itemHeaderList.get(k).getSfId()==bmsStockHeaderedit.getBmsStockDetailed().get(i).getRmId())
							{
								sfndRawItem = new BmsStockItemList();
								sfndRawItem.setBsmId(bmsStockHeaderedit.getBmsStockDetailed().get(i).getBmsStockDeatilId());
								sfndRawItem.setItemId(bmsStockHeaderedit.getBmsStockDetailed().get(i).getRmId());
								sfndRawItem.setName(bmsStockHeaderedit.getBmsStockDetailed().get(i).getRmName());
								sfndRawItem.setQty(bmsStockHeaderedit.getBmsStockDetailed().get(i).getBmsOpeningStock());
								for(int j=0;j<uomList.size();j++)
								{
									if(uomList.get(j).getUomId()==bmsStockHeaderedit.getBmsStockDetailed().get(i).getRmUom())
									{
										sfndRawItem.setUomId(uomList.get(j).getUomId());
										sfndRawItem.setUomName(uomList.get(j).getUom());
									}
								}
								flag=1;
								sfndRawItemlist.add(sfndRawItem);
								 
							}
							
						}
						if(flag==0)
						{

							sfndRawItem = new BmsStockItemList();
							sfndRawItem.setItemId(itemHeaderList.get(k).getSfId());
							sfndRawItem.setName(itemHeaderList.get(k).getSfName());
							for(int j=0;j<uomList.size();j++)
							{
								if(uomList.get(j).getUomId()==itemHeaderList.get(j).getSfUomId())
								{
									sfndRawItem.setUomId(uomList.get(j).getUomId());
									sfndRawItem.setUomName(uomList.get(j).getUom());
								}
							}
							sfndRawItemlist.add(sfndRawItem);
							
						}
						 
					}
					
					
					
				}
				else
				{
					System.out.println("sf new");
					for(int i=0;i<itemHeaderList.size();i++)
					{
						sfndRawItem = new BmsStockItemList();
						sfndRawItem.setItemId(itemHeaderList.get(i).getSfId());
						sfndRawItem.setName(itemHeaderList.get(i).getSfName());
						for(int j=0;j<uomList.size();j++)
						{
							if(uomList.get(j).getUomId()==itemHeaderList.get(j).getSfUomId())
							{
								sfndRawItem.setUomId(uomList.get(j).getUomId());
								sfndRawItem.setUomName(uomList.get(j).getUom());
							}
						}
						sfndRawItemlist.add(sfndRawItem);
					}
				}
				
				
			}
				
			System.out.println(sfndRawItemlist.toString());
			
		}catch(Exception e)
		{
			System.out.println("errorr  "+e.getMessage());
			e.printStackTrace();
		}
		return sfndRawItemlist;
		

	}
	
	@RequestMapping(value = "/insertBmsStock", method = RequestMethod.POST)
	public String insertBmsStock(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
			
		BmsStockHeader bmsStockHeader= new BmsStockHeader();
		
		try
		{
			if(bmsStockHeaderedit.getBmsStockId()!=0)
			{
				System.out.println("raw insertedit");
				 int qty;
				 
				 for(int i=0;i<sfndRawItemlist.size();i++)
				 {
					 qty=Integer.parseInt(request.getParameter("stockQty"+sfndRawItemlist.get(i).getItemId()));
					 System.out.println("qty  "+qty);
					 sfndRawItemlist.get(i).setQty(qty);
					 
				 }
				 
				System.out.println("sfndRawItemlist"+sfndRawItemlist);
				List<BmsStockDetailed> bmsStockDetailedlist = new ArrayList<BmsStockDetailed>();
				
				for(int i=0;i<sfndRawItemlist.size();i++)
				{
					int flag=0;
				 
					for(int j=0;j<bmsStockHeaderedit.getBmsStockDetailed().size();j++)
					{
						
							if(bmsStockHeaderedit.getBmsStockDetailed().get(j).getBmsStockDeatilId()==sfndRawItemlist.get(i).getBsmId() && 
									bmsStockHeaderedit.getBmsStockDetailed().get(j).getBmsOpeningStock()!=sfndRawItemlist.get(i).getQty()) 
							{ 
								bmsStockHeaderedit.getBmsStockDetailed().get(j).setBmsOpeningStock(sfndRawItemlist.get(i).getQty());
								bmsStockDetailedlist.add(bmsStockHeaderedit.getBmsStockDetailed().get(j)); 
								flag=1;
								break;
							}
							
								
					}
					if(sfndRawItemlist.get(i).getBsmId()==0 && flag==0) 
					{
						BmsStockDetailed bmsStockDetailed = new BmsStockDetailed();
						bmsStockDetailed.setBmsStockDeatilId(0);
						bmsStockDetailed.setBmsStockId(bmsStockHeaderedit.getBmsStockId());
						bmsStockDetailed.setBmsStockDate(bmsStockHeaderedit.getBmsStockDate()); 
						bmsStockDetailed.setRmId(sfndRawItemlist.get(i).getItemId());
						bmsStockDetailed.setRmName(sfndRawItemlist.get(i).getName());
						bmsStockDetailed.setBmsOpeningStock(sfndRawItemlist.get(i).getQty());
						bmsStockDetailed.setRmType(value);
						bmsStockDetailed.setRmUom(sfndRawItemlist.get(i).getUomId());
						bmsStockDetailedlist.add(bmsStockDetailed);
					}
				}
				
				 
				 
				bmsStockHeaderedit.setBmsStockDetailed(bmsStockDetailedlist);
				System.out.println("bmsStockHeaderedit  "+bmsStockHeaderedit.toString());
				RestTemplate rest = new RestTemplate();
				 
				bmsStockHeaderedit =  rest.postForObject(Constants.url + "insertBmsStock",bmsStockHeaderedit,BmsStockHeader.class);
				
				/*if(bmsStockDetailedlist!=null)
				{
					System.out.println("bmsStockDetailedlist"+bmsStockDetailedlist);
					Info info =  rest.postForObject(Constants.url + "insertBmsStockDetailed",bmsStockDetailedlist,Info.class);
					System.out.println("info"+info);
					
				}*/
				 
			}
			else
			{
				System.out.println("insertnew");
				Date date = new Date();
				
				 bmsStockHeader.setBmsStockId(0);
				 bmsStockHeader.setBmsStockDate(date);
				 bmsStockHeader.setBmsStatus(0);
				 if(value==1)
					 bmsStockHeader.setRmType(1);
				 else
					 bmsStockHeader.setRmType(2);
				 bmsStockHeader.setExInt(0);
				 bmsStockHeader.setExInt1(0);
				 bmsStockHeader.setExBoll(0);
				 bmsStockHeader.setExBoll1(0);
				 bmsStockHeader.setExVarchar("");
				 
				 int qty;
				 
				 for(int i=0;i<sfndRawItemlist.size();i++)
				 {
					 qty=Integer.parseInt(request.getParameter("stockQty"+sfndRawItemlist.get(i).getItemId()));
					 System.out.println("qty  "+qty);
					 sfndRawItemlist.get(i).setQty(qty);
					 
				 }
				 
				
				List<BmsStockDetailed> bmsStockDetailedlist = new ArrayList<BmsStockDetailed>();
				for(int i=0;i<sfndRawItemlist.size();i++)
				{
					 
					BmsStockDetailed bmsStockDetailed = new BmsStockDetailed();
					bmsStockDetailed.setBmsStockDeatilId(0);
					bmsStockDetailed.setBmsStockId(0);
					bmsStockDetailed.setBmsStockDate(date); 
					 bmsStockDetailed.setRmId(sfndRawItemlist.get(i).getItemId());
					 bmsStockDetailed.setRmName(sfndRawItemlist.get(i).getName());
					 bmsStockDetailed.setBmsOpeningStock(sfndRawItemlist.get(i).getQty());
					 bmsStockDetailed.setRmType(value);
					 bmsStockDetailed.setRmUom(sfndRawItemlist.get(i).getUomId());
					 bmsStockDetailedlist.add(bmsStockDetailed);
					 
				 
				}
				bmsStockHeader.setBmsStockDetailed(bmsStockDetailedlist);
				System.out.println("bmsStockHeader  "+bmsStockHeader.toString());
				RestTemplate rest = new RestTemplate();
				 
				bmsStockHeader =  rest.postForObject(Constants.url + "insertBmsStock",bmsStockHeader,BmsStockHeader.class);
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("in controller "+e.getMessage());
		}
		
		

		return "redirect:/bmsstock";

	}
	
	 
}
