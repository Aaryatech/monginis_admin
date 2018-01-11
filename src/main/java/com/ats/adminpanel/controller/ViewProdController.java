package com.ats.adminpanel.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.ats.adminpanel.model.productionplan.MixingDetailed;
import com.ats.adminpanel.model.productionplan.MixingHeader;

@Controller
@Scope("session")
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
    public  List<PostProductionPlanDetail> postProdPlanDetailList;

	int globalHeaderId=0;
	private int productionId;
	private int isMixing;
	private String globalProdDate;
	String fromDate,toDate;
	@RequestMapping(value = "/showProdHeader", method = RequestMethod.GET)
	public ModelAndView showProdHeader(HttpServletRequest request, HttpServletResponse response) {
		
		Constants.mainAct=4;
		Constants.subAct=118;
		postProdPlanDetailList=new ArrayList<PostProductionPlanDetail>();
		
		ModelAndView model = new ModelAndView("production/viewProdHeader");
		try {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		
		
		if(request.getParameter("from_date")==null || request.getParameter("to_date")==null) {
		Date date=new Date();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		 fromDate=df.format(date);
		 toDate=df.format(date);
		System.out.println("From Date And :"+fromDate+"ToDATE"+toDate);
		
		map.add("fromDate", fromDate);
		map.add("toDate", toDate);
		
		System.out.println("inside if ");
		}
		else {
			 fromDate=request.getParameter("from_date");
			 toDate=request.getParameter("to_date");
			
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
		model.addObject("fromDate",fromDate);
		model.addObject("toDate",toDate);
		
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
		
		//Constants.mainAct=16;
		//Constants.subAct=164;
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
		globalProdDate =planHeader.getProductionDate();
		
		
		globalProductionBatch=planHeader.getProductionBatch();
		globalTimeSlot=planHeader.getTimeSlot();
		productionId=planHeader.getProductionHeaderId();
		isMixing=planHeader.getIsMixing();
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
	public String updateQty(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("production/prodDetail");
		int prodId=0;
		try {
			int prodStatus=Integer.parseInt(request.getParameter("productionStatus"));

			int productionId=Integer.parseInt(request.getParameter("production_id"));
			System.out.println("productionId"+productionId);

			prodId=productionId;
			
			System.out.println("prodStatus"+prodStatus);
		
		   for(int i=0;i<postProdPlanDetailList.size();i++)
		   {
		 
			 if(prodStatus==1)
			 {
			     int planQty=Integer.parseInt(request.getParameter("plan_qty"+postProdPlanDetailList.get(i).getProductionDetailId()));
			     System.out.println("planQty"+planQty);
			   
				 postProdPlanDetailList.get(i).setPlanQty(planQty);

			 }
			 else if(prodStatus==2)
			 {
				 int orderQty=Integer.parseInt(request.getParameter("order_qty"+postProdPlanDetailList.get(i).getProductionDetailId()));
				 System.out.println("orderQty:"+orderQty);
				 
				 postProdPlanDetailList.get(i).setOrderQty(orderQty);

			 }
		
			
		   }
		   
		System.out.println("ItemDetail List:"+postProdPlanDetailList.toString());
		
		RestTemplate restTemplate=new RestTemplate();

		
		Info info= restTemplate.postForObject(Constants.url + "updateProdQty",postProdPlanDetailList, Info.class);

		System.out.println("Info"+info.toString());
		}
	catch(Exception e)
	{
			System.out.println("Exception In Update Plan Qty"+e.getMessage());
	}
		return "redirect:/getProdDetail/"+prodId;
	}
	
	@RequestMapping(value = "/completeProd", method = RequestMethod.POST)
	public String completeProduction(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("production/prodDetail");
		int prodId=0;
		try {
			int prodStatus=Integer.parseInt(request.getParameter("productionStatus"));
			int productionId=Integer.parseInt(request.getParameter("production_id"));
			int isPlan=Integer.parseInt(request.getParameter("is_plan"));
			prodId=productionId;
			
			System.out.println("completeProd prodStatus"+prodStatus);
		
		   for(int i=0;i<postProdPlanDetailList.size();i++)
		   {
			   int prodQty=Integer.parseInt(request.getParameter("act_prod_qty"+postProdPlanDetailList.get(i).getProductionDetailId()));
			   System.out.println("prodQty:"+prodQty);
			   
				postProdPlanDetailList.get(i).setProductionQty(prodQty);

		   }
		   
		System.out.println("ItemDetail List:"+postProdPlanDetailList.toString());
		
		RestTemplate restTemplate=new RestTemplate();

		
		Info info= restTemplate.postForObject(Constants.url + "updateProdQty",postProdPlanDetailList, Info.class);

		if(info.getError()==false) {
			
			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("productionId",productionId);
			if(isPlan==1)
			{
				map.add("prodStatus",4);

			}
			else
			{
				map.add("prodStatus",5);

			}

			int isUpdated= rest.postForObject(Constants.url + "updateProductionStatus",map, Integer.class);

			System.out.println("isProdUpdated:"+isUpdated);
			
			 if(isUpdated==1)
			 {
				  
				 map = new LinkedMultiValueMap<String,Object>();
				 map.add("prodId", productionId);
				 map.add("isProduction", 1);
				 System.out.println("map"+map);
				 
				 info= rest.postForObject(Constants.url + "/updateStatusWhileCompletProd",map, Info.class);
				 System.out.println("info"+info);
			 }
			
			try {
			 map = new LinkedMultiValueMap<String, Object>();
			 map.add("fromDate", fromDate);
			 map.add("toDate", toDate);

			GetProdPlanHeaderList prodHeader=restTemplate.postForObject(Constants.url + "getProdPlanHeader",map, GetProdPlanHeaderList.class);
			
			prodPlanHeaderList=new ArrayList<>();
			
			prodPlanHeaderList=prodHeader.getProdPlanHeader();
			}
			catch(Exception e)
			{
				System.out.println("Exception In Complete Production:"+e.getMessage());
			}
		}
		System.out.println("Info"+info.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception In complete Production");
		}
		return "redirect:/getProdDetail/"+prodId;
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
       mav.addObject("productionId",productionId);
       mav.addObject("isMixing",isMixing);
       ModelAndView model = new ModelAndView("production/addBom");
       model.addObject("prodDate",globalProdDate);
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Ex oc");
		}

		return mav;

	}
	
	
	@RequestMapping(value = "/addMixingreqst", method = RequestMethod.POST)
	public String addMixingreqst(HttpServletRequest request, HttpServletResponse response) {
		//Constants.mainAct = 17;
		//Constants.subAct=184;
		
		

		int timeSlot=Integer.parseInt(request.getParameter("globalTimeSlot")); 
		String globalProductionBatch=request.getParameter("globalProductionBatch");
		int prodId=Integer.parseInt(request.getParameter("productionId"));
		
		MixingHeader mixingHeader =new  MixingHeader();
		System.out.println("globalTimeSlot "+globalTimeSlot+"globalProductionBatch  "+globalProductionBatch +"editQty");
		
		Date date = new Date();
		
		mixingHeader.setMixId(0);
		mixingHeader.setMixDate(date);
		mixingHeader.setProductionId(prodId);
		mixingHeader.setProductionBatch(globalProductionBatch);
		mixingHeader.setStatus(0);
		mixingHeader.setDelStatus(0);
		mixingHeader.setTimeSlot(timeSlot);
		mixingHeader.setIsBom(0);
		mixingHeader.setExBool1(0);
		mixingHeader.setExInt2(0);
		mixingHeader.setExInt3(0);
		mixingHeader.setExVarchar1("");
		mixingHeader.setExVarchar2("");
		mixingHeader.setExVarchar3("");
		
		List<MixingDetailed> addmixingDetailedlist = new ArrayList<MixingDetailed>();
			
		for(int i=0;i<sfPlanDetailForMixing.size();i++)
		{
			System.out.println("in for ");
			MixingDetailed mixingDetailed= new MixingDetailed();
			mixingDetailed.setMixing_detailId(0);
			mixingDetailed.setMixingId(0);
			mixingDetailed.setSfId(sfPlanDetailForMixing.get(i).getRmId());
			mixingDetailed.setSfName(sfPlanDetailForMixing.get(i).getRmName());
			mixingDetailed.setReceivedQty(sfPlanDetailForMixing.get(i).getTotal());
			mixingDetailed.setUom(sfPlanDetailForMixing.get(i).getUom());
			mixingDetailed.setMixingDate(date);
			mixingDetailed.setExBool1(0);
			mixingDetailed.setExInt2(0);
			mixingDetailed.setExInt1(0);
			mixingDetailed.setExInt3(0);
			mixingDetailed.setExVarchar1("");
			mixingDetailed.setExVarchar2("");
			mixingDetailed.setExVarchar3("");
			addmixingDetailedlist.add(mixingDetailed);
			
		}
		int count=0;
		for(int i=0;i<addmixingDetailedlist.size();i++)
		{
			System.out.println("in second for ");
			int prod_qty = Integer.parseInt(request.getParameter("editQty"+count));
			System.out.println("prodqty  "+prod_qty);
			addmixingDetailedlist.get(i).setProductionQty(prod_qty);
			count++;
		}
		
		mixingHeader.setMixingDetailed(addmixingDetailedlist);
		System.out.println("while inserting "+mixingHeader.toString());
		RestTemplate rest = new RestTemplate();
		MixingHeader mixingHeaderin = rest.postForObject(Constants.url + "insertMixingHeaderndDetailed", mixingHeader,
				MixingHeader.class);
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("productionId", prodId);
		map.add("flag", 0);
		int updateisMixing = rest.postForObject(Constants.url + "updateisMixingandBom", map,
				Integer.class);
		
		System.out.println(mixingHeaderin.toString());
		
		return "redirect:/getMixingList";
		
		

	}

			
}
