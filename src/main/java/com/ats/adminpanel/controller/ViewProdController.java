package com.ats.adminpanel.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.RawMaterial.ItemDetailList;
import com.ats.adminpanel.model.production.GetProdPlanDetail;
import com.ats.adminpanel.model.production.GetProdPlanDetailList;
import com.ats.adminpanel.model.production.GetProdPlanHeader;
import com.ats.adminpanel.model.production.GetProdPlanHeaderList;
import com.ats.adminpanel.model.production.PostProductionPlanDetail;
import com.ats.adminpanel.model.production.mixing.temp.GetSFPlanDetailForMixing;
import com.ats.adminpanel.model.production.mixing.temp.GetSFPlanDetailForMixingList;
import com.ats.adminpanel.model.production.mixing.temp.GetTempMixItemDetail;
import com.ats.adminpanel.model.production.mixing.temp.GetTempMixItemDetailList;
import com.ats.adminpanel.model.production.mixing.temp.TempMixing;
import com.ats.adminpanel.model.production.mixing.temp.TempMixingList;

@Controller
public class ViewProdController {
	
	String globalProductionBatch;
	int globalTimeSlot;
	// new Lists

	GetSFPlanDetailForMixingList getSFPlanDetailForMixingList;

	List<GetSFPlanDetailForMixing> sfPlanDetailForMixing = new ArrayList<>();

	GetTempMixItemDetailList getTempMixItemDetailList;

	List<GetTempMixItemDetail> tempMixItemDetail = new ArrayList<>();

	// temp Mix table beans
	TempMixingList tempMixingList;
	List<TempMixing> tempMixing = new ArrayList<>();
	
	
	List<GetProdPlanHeader> prodPlanHeaderList;
	
    public  List<GetProdPlanDetail> prodPlanDetailList;
    public static  List<PostProductionPlanDetail> postProdPlanDetailList;

	int globalHeaderId=0;
	
	@RequestMapping(value = "/showProdHeader", method = RequestMethod.GET)
	public ModelAndView showProdHeader(HttpServletRequest request, HttpServletResponse response) {
		
		Constants.mainAct=16;
		Constants.subAct=164;
		postProdPlanDetailList=new ArrayList<PostProductionPlanDetail>();
		
		ModelAndView model = new ModelAndView("production/viewProdHeader");
		try {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		
		
		if(request.getParameter("from_date")==null || request.getParameter("to_date")==null) {
		Date date=new Date();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String fromDate=df.format(date);
		String toDate=df.format(date);
		System.out.println("From Date And :"+fromDate+"ToDATE"+toDate);
		
		map.add("fromDate", fromDate);
		map.add("toDate", toDate);
		
		System.out.println("inside if ");
		}
		else {
			String fromDate=request.getParameter("from_date");
			String toDate=request.getParameter("to_date");
			
			System.out.println("inside Else ");

			System.out.println("fromDate "+fromDate);

			System.out.println("toDate "+toDate);

			map.add("fromDate", fromDate);
			map.add("toDate", toDate);
			
		}
		
		GetProdPlanHeaderList prodHeader=restTemplate.postForObject(Constants.url + "getProdPlanHeader",map, GetProdPlanHeaderList.class);
		
		prodPlanHeaderList=new ArrayList<>();
		
		prodPlanHeaderList=prodHeader.getProdPlanHeader();
		
		System.out.println("prod header "+prodPlanHeaderList.toString());
		
		model.addObject("planHeader",prodPlanHeaderList);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return model;
		
	}	
	
	
	@RequestMapping(value = "/getProdDetail/{productionHeaderId}", method = RequestMethod.GET)
	public ModelAndView getProdDetail(@PathVariable("productionHeaderId")int productionHeaderId,
			HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("inside Prod Detail");
		
		Constants.mainAct=16;
		Constants.subAct=164;
		ModelAndView model = new ModelAndView("production/prodDetail");
		
		try {
		globalHeaderId=productionHeaderId;
		System.out.println("After model ");
		
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		
		map.add("planHeaderId", productionHeaderId);
		
		GetProdPlanDetailList prodDetail=restTemplate.postForObject(Constants.url + "getProdPlanDetail",map, GetProdPlanDetailList.class);
		
		prodPlanDetailList=new ArrayList<>();
		
		prodPlanDetailList=prodDetail.getProdPlanDetail();
		
		
		GetProdPlanHeader planHeader=new GetProdPlanHeader();
		
		for(int i=0;i<prodPlanHeaderList.size();i++) {
			
			if(prodPlanHeaderList.get(i).getProductionHeaderId()==globalHeaderId) {
				
			 planHeader=prodPlanHeaderList.get(i);
			}
				
		}
		globalProductionBatch=planHeader.getProductionBatch();
		globalTimeSlot=planHeader.getTimeSlot();
		
		for(int j=0;j<prodPlanDetailList.size();j++)
		{
			PostProductionPlanDetail postProductionPlanDetail=new PostProductionPlanDetail();
			postProductionPlanDetail.setProductionDetailId(prodPlanDetailList.get(j).getProductionDetailId());
			postProductionPlanDetail.setItemId(prodPlanDetailList.get(j).getItemId());
			postProductionPlanDetail.setOpeningQty(prodPlanDetailList.get(j).getOpeningQty());
			postProductionPlanDetail.setPlanQty(prodPlanDetailList.get(j).getPlanQty());
			postProductionPlanDetail.setProductionBatch(prodPlanDetailList.get(j).getProductionBatch());
			postProductionPlanDetail.setProductionDate(prodPlanDetailList.get(j).getProductionDate());
			postProductionPlanDetail.setProductionHeaderId(prodPlanDetailList.get(j).getProductionHeaderId());
			postProductionPlanDetail.setProductionQty(prodPlanDetailList.get(j).getProductionQty());
			postProductionPlanDetail.setOrderQty(prodPlanDetailList.get(j).getOrderQty());
			postProductionPlanDetail.setRejectedQty(prodPlanDetailList.get(j).getRejectedQty());
			postProdPlanDetailList.add(postProductionPlanDetail);
		}
		
		model.addObject("planDetail", prodPlanDetailList);
		
		model.addObject("planHeader",planHeader);
		
		}catch (Exception e) {
			
			System.out.println("in catch model ");
			
			e.printStackTrace();
			
			
		}
		return model;
	
		
	}
	
	@RequestMapping(value = "/updateQty", method = RequestMethod.POST)
	public ModelAndView updatePlanQty(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("production/prodDetail");

		try {
			int prodStatus=Integer.parseInt(request.getParameter("productionStatus"));

	//	List<PostProductionPlanDetail> getProdPlanDetailList=new ArrayList<PostProductionPlanDetail>();
		
		   for(int i=0;i<postProdPlanDetailList.size();i++)
		   {
		 
			 if(prodStatus==1)
			 {
			   int planQty=Integer.parseInt(request.getParameter("plan_qty"+postProdPlanDetailList.get(i).getProductionDetailId()+"status1"));
			   System.out.println("planQty"+planQty);
			   
			   int prodQty=Integer.parseInt(request.getParameter("act_prod_qty"+postProdPlanDetailList.get(i).getProductionDetailId()+"status1"));
			   System.out.println("prodQty:"+prodQty);
			   
				postProdPlanDetailList.get(i).setPlanQty(planQty);
				postProdPlanDetailList.get(i).setProductionQty(prodQty);

			 }
			 else if(prodStatus==2)
			 {
				 int orderQty=Integer.parseInt(request.getParameter("order_qty"+postProdPlanDetailList.get(i).getProductionDetailId()+"status1"));
				 System.out.println("orderQty:"+orderQty);
				 
				 int prodQty=Integer.parseInt(request.getParameter("act_prod_qty"+postProdPlanDetailList.get(i).getProductionDetailId()+"status1"));
				   System.out.println("prodQty:"+prodQty);
			  
					postProdPlanDetailList.get(i).setOrderQty(orderQty);
					postProdPlanDetailList.get(i).setProductionQty(prodQty);

			 }
			 else if(prodStatus==3)
			 {
				 int prodQty=Integer.parseInt(request.getParameter("act_prod_qty"+postProdPlanDetailList.get(i).getProductionDetailId()+"status1"));
				   System.out.println("prodQty:"+prodQty);
				   
					postProdPlanDetailList.get(i).setProductionQty(prodQty);
			 }
			 
			
		   }
		System.out.println("ItemDetail List:"+postProdPlanDetailList.toString());
		
		RestTemplate restTemplate=new RestTemplate();

		
		Info info= restTemplate.postForObject(Constants.url + "updateProdQty",postProdPlanDetailList, Info.class);

		System.out.println("Info"+info.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception In Update Plan Qty");
		}
		return model;
	}
	@RequestMapping(value = "/addMixing", method = RequestMethod.GET)
	public ModelAndView showMixing(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("production/addMixing");

		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			System.out.println("globalHeaderId:"+globalHeaderId);
			map.add("headerId", globalHeaderId);

			getSFPlanDetailForMixingList = restTemplate.postForObject(Constants.url + "getSfPlanDetailForMixing", map,
					GetSFPlanDetailForMixingList.class);

			sfPlanDetailForMixing = getSFPlanDetailForMixingList.getSfPlanDetailForMixing();

			System.out.println("sf Plan Detail For Mixing  " + sfPlanDetailForMixing.toString());

			TempMixing tempMx = null;
			for (int i = 0; i < sfPlanDetailForMixing.size(); i++) {

				GetSFPlanDetailForMixing planMixing = sfPlanDetailForMixing.get(i);

				tempMx = new TempMixing();

				tempMx.setQty(planMixing.getTotal());

				tempMx.setRmId(planMixing.getRmId());
				tempMx.setSfId(1);

				tempMx.setProdHeaderId(globalHeaderId);

				tempMixing.add(tempMx);
			}

			System.out.println("temp Mix List " + tempMixing.toString());

			Info info = restTemplate.postForObject(Constants.url + "insertTempMixing", tempMixing, Info.class);

			map = new LinkedMultiValueMap<String, Object>();

			map.add("prodHeaderId", globalHeaderId);

			getTempMixItemDetailList = restTemplate.postForObject(Constants.url + "getTempMixItemDetail", map,
					GetTempMixItemDetailList.class);

			tempMixItemDetail = getTempMixItemDetailList.getTempMixItemDetail();

			System.out.println("temp Mix Item Detail  " + tempMixItemDetail.toString());

			// Calculations

			boolean isSameItem = false;
			GetSFPlanDetailForMixing newItem = null;
			
			for (int j = 0; j < tempMixItemDetail.size(); j++) {
				
				GetTempMixItemDetail tempMixItem = tempMixItemDetail.get(j);

			for (int i = 0; i < sfPlanDetailForMixing.size(); i++) {

				GetSFPlanDetailForMixing planMixing = sfPlanDetailForMixing.get(i);

					if (tempMixItem.getRmId() == planMixing.getRmId()) {
						
						planMixing.setTotal(planMixing.getTotal() + tempMixItem.getTotal());

						isSameItem = true;

					}
				}
				if(isSameItem==false) {
					
					 newItem= new  GetSFPlanDetailForMixing();
					
					newItem.setRmName(tempMixItem.getRmName());
					newItem.setRmType(tempMixItem.getRmType());
					newItem.setRmId(tempMixItem.getSfId());
					newItem.setTotal(tempMixItem.getTotal());
					newItem.setUom(tempMixItem.getUom());
					
					sfPlanDetailForMixing.add(newItem);
					
				}
			}

					
			System.out.println("Final List "+sfPlanDetailForMixing.toString());
			
       mav.addObject("mixingList",sfPlanDetailForMixing);
       mav.addObject("productionBatch",globalProductionBatch);
       mav.addObject("globalTimeSlot",globalTimeSlot);
       
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Ex oc");
		}

		return mav;

	}

			
}
