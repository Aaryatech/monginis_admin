package com.ats.adminpanel.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.controller.model.GetSfData;
import com.ats.adminpanel.model.DepartmentList;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.MiniSubCategory;
import com.ats.adminpanel.model.RawMaterial.GetItemSfHeader;
import com.ats.adminpanel.model.RawMaterial.GetSfType;
import com.ats.adminpanel.model.RawMaterial.ItemSfDetail;
import com.ats.adminpanel.model.RawMaterial.SfItemDetailList;
import com.ats.adminpanel.model.item.FrItemStockConfigureList;
import com.ats.adminpanel.model.item.Item;
import com.ats.adminpanel.model.login.UserResponse;
import com.ats.adminpanel.model.production.GetProdPlanHeader;
import com.ats.adminpanel.model.production.GetProdPlanHeaderList;
import com.ats.adminpanel.model.production.PostProdPlanHeader;
import com.ats.adminpanel.model.production.mixing.temp.GetSFPlanDetailForMixing;
import com.ats.adminpanel.model.production.mixing.temp.GetSFPlanDetailForMixingList;
import com.ats.adminpanel.model.productionplan.BillOfMaterialDetailed;
import com.ats.adminpanel.model.productionplan.BillOfMaterialHeader;
import com.ats.adminpanel.model.productionplan.MixingDetailed;
import com.ats.adminpanel.model.productionplan.MixingHeader;

@Controller
@Scope("session")
public class ProductionApplController {

	
	@RequestMapping(value = "/generateMixingForProduction/{type}", method = RequestMethod.GET)
	public ModelAndView prodListForGenerateMixingForProd(@PathVariable("type")int type,HttpServletRequest request, HttpServletResponse response) {

		String fromDate,toDate;
		ModelAndView model = new ModelAndView("production/prodList");
		try {
			
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			if (request.getParameter("from_date") == null || request.getParameter("to_date") == null) {
				Date date = new Date();
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				fromDate = df.format(date);
				toDate = df.format(date);
				System.out.println("From Date And :" + fromDate + "ToDATE" + toDate);

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

			} else {
				fromDate = request.getParameter("from_date");
				toDate = request.getParameter("to_date");

				System.out.println("inside Else ");

				System.out.println("fromDate " + fromDate);

				System.out.println("toDate " + toDate);

				map.add("fromDate", fromDate);
				map.add("toDate", toDate);

			}

			GetProdPlanHeaderList prodHeader = restTemplate.postForObject(Constants.url + "getProdPlanHeader", map,
					GetProdPlanHeaderList.class);

			List<GetProdPlanHeader> prodPlanHeaderList = new ArrayList<>();

			prodPlanHeaderList = prodHeader.getProdPlanHeader();

			List<MiniSubCategory> miniSubCategory = restTemplate.getForObject(Constants.url + "/showMiniSubCatList",
					 List.class);
			model.addObject("miniSubCategory", miniSubCategory);
			DepartmentList departmentList=restTemplate.getForObject(Constants.url+"getAllDept", DepartmentList.class);
			model.addObject("departmentList", departmentList.getDepartmentList());
		
			if(type==2) {
				  map.add("settingKeyList", "BMS");
		            FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
				    FrItemStockConfigureList.class);
		            model.addObject("deptId", settingList.getFrItemStockConfigure().get(0).getSettingValue());
					System.out.println("deptId"+settingList.getFrItemStockConfigure().get(0).getSettingValue());

			}
			
			map = new LinkedMultiValueMap<String, Object>();
			map.add("delStatus",0);
			List<GetSfType> sfTypeList=restTemplate.postForObject(Constants.url+"getSfType",map,List.class);
			
			model.addObject("sfTypeList", sfTypeList);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
			model.addObject("type", type);
			model.addObject("planHeader", prodPlanHeaderList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;

	}
	@RequestMapping(value = "/showDetailsForCp", method = RequestMethod.GET)
	public @ResponseBody List<GetSFPlanDetailForMixing> showDetailsForCp(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		
		List<GetSFPlanDetailForMixing> sfPlanDetailForBom=null;
		try {
			int prodHeaderId=Integer.parseInt(request.getParameter("prodHeaderId"));
			String toDept=request.getParameter("toDept");
			System.err.println(toDept);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
		
            map.add("settingKeyList", toDept);
            FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
		    FrItemStockConfigureList.class);
            map = new LinkedMultiValueMap<String, Object>();
				map.add("headerId", prodHeaderId);
				map.add("deptId", settingList.getFrItemStockConfigure().get(0).getSettingValue());
			GetSFPlanDetailForMixingList getSFPlanDetailForBomList = restTemplate
					.postForObject(Constants.url + "showDetailsForCp", map,
						GetSFPlanDetailForMixingList.class);

		sfPlanDetailForBom = getSFPlanDetailForBomList.getSfPlanDetailForMixing();
		System.err.println(sfPlanDetailForBom);
		
		} catch (Exception e) {
			e.printStackTrace();
	}
		
	return sfPlanDetailForBom;

	}
	
	@RequestMapping(value = "/showDetailsForLayering", method = RequestMethod.GET)
	public @ResponseBody List<GetSFPlanDetailForMixing> showDetailsForLayering(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		
		List<GetSFPlanDetailForMixing> sfPlanDetailForBom=null;
		try {
			int prodHeaderId=Integer.parseInt(request.getParameter("prodHeaderId"));
			String toDept=request.getParameter("toDept");
			System.err.println(toDept);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
		
            map.add("settingKeyList", toDept);
            FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
		    FrItemStockConfigureList.class);
            map = new LinkedMultiValueMap<String, Object>();
				map.add("headerId", prodHeaderId);
				map.add("deptId", settingList.getFrItemStockConfigure().get(0).getSettingValue());
			GetSFPlanDetailForMixingList getSFPlanDetailForBomList = restTemplate
					.postForObject(Constants.url + "showDetailsForLayering", map,
						GetSFPlanDetailForMixingList.class);

		sfPlanDetailForBom = getSFPlanDetailForBomList.getSfPlanDetailForMixing();
		System.err.println(sfPlanDetailForBom);
		
		} catch (Exception e) {
			e.printStackTrace();
	}
		
	return sfPlanDetailForBom;

	}
	@RequestMapping(value = "/showDetailsForCoating", method = RequestMethod.GET)
	public @ResponseBody List<GetSFPlanDetailForMixing> showDetailsForCoating(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		
		List<GetSFPlanDetailForMixing> sfPlanDetailForBom=null;
		try {
			int prodHeaderId=Integer.parseInt(request.getParameter("prodHeaderId"));
			String toDept=request.getParameter("toDept");
			System.err.println(toDept);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
		
            map.add("settingKeyList", toDept);
            FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
		    FrItemStockConfigureList.class);
            map = new LinkedMultiValueMap<String, Object>();
				map.add("headerId", prodHeaderId);
				map.add("deptId", settingList.getFrItemStockConfigure().get(0).getSettingValue());
			GetSFPlanDetailForMixingList getSFPlanDetailForBomList = restTemplate
					.postForObject(Constants.url + "showDetailsForCoating", map,
						GetSFPlanDetailForMixingList.class);

		sfPlanDetailForBom = getSFPlanDetailForBomList.getSfPlanDetailForMixing();
		System.err.println(sfPlanDetailForBom);
		
		} catch (Exception e) {
			e.printStackTrace();
	}
		
	return sfPlanDetailForBom;

	}
	@RequestMapping(value = "/showDetailItemLayering", method = RequestMethod.GET)
	public @ResponseBody List<GetSFPlanDetailForMixing> showDetailItemLayering(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		
		List<GetSFPlanDetailForMixing> sfPlanDetailForBom=null;
		try {
			int prodHeaderId=Integer.parseInt(request.getParameter("prodHeaderId"));
			int rmId=Integer.parseInt(request.getParameter("rmId"));
			String toDept=request.getParameter("toDept");
			System.err.println(toDept);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
		
            map.add("settingKeyList", toDept);
            FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
		    FrItemStockConfigureList.class);
            map = new LinkedMultiValueMap<String, Object>();
				map.add("headerId", prodHeaderId);
				map.add("rmId", rmId);
				map.add("deptId", settingList.getFrItemStockConfigure().get(0).getSettingValue());
			GetSFPlanDetailForMixingList getSFPlanDetailForBomList = restTemplate
					.postForObject(Constants.url + "showDetailItemLayering", map,
						GetSFPlanDetailForMixingList.class);

		sfPlanDetailForBom = getSFPlanDetailForBomList.getSfPlanDetailForMixing();
		System.err.println(sfPlanDetailForBom);
		
		} catch (Exception e) {
			e.printStackTrace();
	}
		
	return sfPlanDetailForBom;

	}
	List<ItemSfDetail> sfItemDetailListNew=null;
	@RequestMapping(value = "/getSfDetails", method = RequestMethod.POST)
	public @ResponseBody List<ItemSfDetail> getSfDetails(@RequestBody List<GetSfData> data) throws ParseException {
	
		List<ItemSfDetail> sfItemDetailListRes;
	   sfItemDetailListNew=new ArrayList<>();
	   List<ItemSfDetail> sfItemDetailListNew1=new ArrayList<>();
       try {
    		StringBuilder sfIds = new StringBuilder();
			for (int i = 0; i < data.size(); i++) {
				sfIds = sfIds.append(data.get(i).getSfId() + ",");
			}

			String sfIdsString = sfIds.toString();
			sfIdsString = sfIdsString.substring(0, sfIdsString.length() - 1);
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("sfId", sfIdsString);
			RestTemplate restTemplate = new RestTemplate();
			SfItemDetailList sfDetaiListItems=restTemplate.postForObject(Constants.url+"getSfItemDetailsForCreamPrep",map,SfItemDetailList.class);
			sfItemDetailListRes=sfDetaiListItems.getSfItemDetail();
			
			for (int i = 0; i < sfItemDetailListRes.size(); i++) {
				if(sfItemDetailListNew.size()==0)
				{
					sfItemDetailListNew.add(sfItemDetailListRes.get(0));
				}
				else 
				{ int flag=0;
					for(int y=0;y<sfItemDetailListNew.size();y++)
					{
						if(sfItemDetailListNew.get(y).getRmId()==sfItemDetailListRes.get(i).getRmId()&&sfItemDetailListNew.get(y).getRmType()==sfItemDetailListRes.get(i).getRmType())
						{
							flag=1;
							
						}
					}
					if(flag==0)
					{
						sfItemDetailListNew.add(sfItemDetailListRes.get(i));
					}
				}
				
	           }
			
			
			for (int i = 0; i < data.size(); i++) {
				
				float totalRmWeight=0;
				float totalRmWeightPer1=0;

				for (int j = 0;j < sfItemDetailListRes.size(); j++) {
					if(data.get(i).getSfId()==sfItemDetailListRes.get(j).getSfId())
					totalRmWeight+=sfItemDetailListRes.get(j).getRmWeight();
				}
				totalRmWeightPer1=totalRmWeight/100;

				for (int j = 0;j < sfItemDetailListRes.size(); j++) {
					if(data.get(i).getSfId()==sfItemDetailListRes.get(j).getSfId())
					{
						float totalValueItemPerc=sfItemDetailListRes.get(j).getRmWeight()/totalRmWeightPer1;
						
						ItemSfDetail sfDetail=new ItemSfDetail();
						sfDetail.setSfDid(sfItemDetailListRes.get(j).getSfDid());
						sfDetail.setRmId(sfItemDetailListRes.get(j).getRmId());
						sfDetail.setRmName(sfItemDetailListRes.get(j).getRmName());
						sfDetail.setRmType(sfItemDetailListRes.get(j).getRmType());
						sfDetail.setRmUnit(sfItemDetailListRes.get(j).getRmUnit());
						sfDetail.setRmWeight(sfItemDetailListRes.get(j).getRmWeight());
						sfDetail.setSfId(sfItemDetailListRes.get(j).getSfId());
						sfDetail.setRmQty((data.get(i).getRmQty()*totalValueItemPerc)/100);
						sfItemDetailListNew1.add(sfDetail);
						
					}
				}
			}
			for (int i = 0; i < sfItemDetailListNew.size(); i++) {
				float rmQty=0;
				float rmWeight=0;
				for (int j = 0; j < sfItemDetailListNew1.size(); j++) {
					
					if(sfItemDetailListNew.get(i).getRmType()==sfItemDetailListNew1.get(j).getRmType() && sfItemDetailListNew.get(i).getRmId()==sfItemDetailListNew1.get(j).getRmId())
					{
						rmQty=rmQty+sfItemDetailListNew1.get(j).getRmQty();
						rmWeight=rmWeight+sfItemDetailListNew1.get(j).getRmWeight();
					}
					
				}
				sfItemDetailListNew.get(i).setRmQty(rmQty);
				sfItemDetailListNew.get(i).setRmWeight(rmWeight);
			
			}
		
			
       }catch (Exception e) {
		e.printStackTrace();
	}
		return sfItemDetailListNew;
	}
	List<ItemSfDetail> sfItemDetailListLayering=null;
	@RequestMapping(value = "/getSfDetailsForLayering", method = RequestMethod.POST)
	public @ResponseBody List<ItemSfDetail> getSfDetailsForLayering(@RequestBody List<GetSfData> data) throws ParseException {
	
		List<ItemSfDetail> sfItemDetailListRes;
		sfItemDetailListLayering=new ArrayList<>();
		List<ItemSfDetail>  sfItemDetailListLayering1=new ArrayList<>();

       try {
    		StringBuilder sfIds = new StringBuilder();
			for (int i = 0; i < data.size(); i++) {
				sfIds = sfIds.append(data.get(i).getSfId() + ",");
			}

			String sfIdsString = sfIds.toString();
			sfIdsString = sfIdsString.substring(0, sfIdsString.length() - 1);
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("sfId", sfIdsString);
			RestTemplate restTemplate = new RestTemplate();
			SfItemDetailList sfDetaiListItems=restTemplate.postForObject(Constants.url+"getSfItemDetailsForCreamPrep",map,SfItemDetailList.class);
			sfItemDetailListRes=sfDetaiListItems.getSfItemDetail();
			
			for (int i = 0; i < sfItemDetailListRes.size(); i++) {
				if(sfItemDetailListLayering.size()==0)
				{
					sfItemDetailListLayering.add(sfItemDetailListRes.get(0));
				}
				else 
				{ int flag=0;
					for(int y=0;y<sfItemDetailListLayering.size();y++)
					{
						if(sfItemDetailListLayering.get(y).getRmId()==sfItemDetailListRes.get(i).getRmId()&&sfItemDetailListLayering.get(y).getRmType()==sfItemDetailListRes.get(i).getRmType())
						{
							flag=1;
							
						}
					}
					if(flag==0)
					{
						sfItemDetailListLayering.add(sfItemDetailListRes.get(i));
					}
				}
				
	           }
			
			
			for (int i = 0; i < data.size(); i++) {
				
				float totalRmWeight=0;
				float totalRmWeightPer1=0;

				for (int j = 0;j < sfItemDetailListRes.size(); j++) {
					if(data.get(i).getSfId()==sfItemDetailListRes.get(j).getSfId())
					totalRmWeight+=sfItemDetailListRes.get(j).getRmWeight();
				}
				totalRmWeightPer1=totalRmWeight/100;

				for (int j = 0;j < sfItemDetailListRes.size(); j++) {
					if(data.get(i).getSfId()==sfItemDetailListRes.get(j).getSfId())
					{
						float totalValueItemPerc=sfItemDetailListRes.get(j).getRmWeight()/totalRmWeightPer1;
						
						ItemSfDetail sfDetail=new ItemSfDetail();
						sfDetail.setSfDid(sfItemDetailListRes.get(j).getSfDid());
						sfDetail.setRmId(sfItemDetailListRes.get(j).getRmId());
						sfDetail.setRmName(sfItemDetailListRes.get(j).getRmName());
						sfDetail.setRmType(sfItemDetailListRes.get(j).getRmType());
						sfDetail.setRmUnit(sfItemDetailListRes.get(j).getRmUnit());
						sfDetail.setRmWeight(sfItemDetailListRes.get(j).getRmWeight());
						sfDetail.setSfId(sfItemDetailListRes.get(j).getSfId());
						sfDetail.setRmQty((data.get(i).getRmQty()*totalValueItemPerc)/100);
						sfItemDetailListLayering1.add(sfDetail);
						
					}
				}
			}
			for (int i = 0; i < sfItemDetailListLayering.size(); i++) {
				float rmQty=0;
				float rmWeight=0;
				for (int j = 0; j < sfItemDetailListLayering1.size(); j++) {
					
					if(sfItemDetailListLayering.get(i).getRmType()==sfItemDetailListLayering1.get(j).getRmType() && sfItemDetailListLayering.get(i).getRmId()==sfItemDetailListLayering1.get(j).getRmId())
					{
						rmQty=rmQty+sfItemDetailListLayering1.get(j).getRmQty();
						rmWeight=rmWeight+sfItemDetailListLayering1.get(j).getRmWeight();
					}
					
				}
				sfItemDetailListLayering.get(i).setRmQty(rmQty);
				sfItemDetailListLayering.get(i).setRmWeight(rmWeight);
			
			}
			
       }catch (Exception e) {
		e.printStackTrace();
	}
		return sfItemDetailListLayering;
	}
	
	@RequestMapping(value = "/postCreamPrepData", method = RequestMethod.POST)
	public @ResponseBody int postCreamPrepData(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		 int flag=0;
		try {
				
			 RestTemplate restTemplate = new RestTemplate();
				HttpSession session = request.getSession();
				UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");

			 List<MixingDetailed> addmixingDetailedlist = new ArrayList<MixingDetailed>();
			 Date date = new Date();

			 String[] checkedList=request.getParameterValues("chk");
			 String dept=request.getParameter("dept");
			 System.err.println(checkedList[0]+"dept"+dept);

			 int prodHeaderId=Integer.parseInt(request.getParameter("prodHeaderId"));
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			 map.add("planHeaderId", prodHeaderId);

				PostProdPlanHeader	postProdPlanHeader = restTemplate.postForObject(Constants.url + "PostProdPlanHeaderwithDetailed", map,
						PostProdPlanHeader.class);
				
				map = new LinkedMultiValueMap<String, Object>();
	            map.add("settingKeyList", dept);
	            FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
			    FrItemStockConfigureList.class);
	            
			 for(int i=0;i<checkedList.length;i++)
			 {
				 int itemDetailId=Integer.parseInt(checkedList[i]);
				 int sfId=Integer.parseInt(request.getParameter("sfId"+itemDetailId));
				 float rmQty=Float.parseFloat(request.getParameter("rmQty"+itemDetailId));
				 float prevRmQty=Float.parseFloat(request.getParameter("prevRmQty"+itemDetailId));
				 float mulFactor=Float.parseFloat(request.getParameter("mulFactor"+itemDetailId));
				 String sfName=request.getParameter("rmName"+itemDetailId);
				 String uom=request.getParameter("uom"+itemDetailId);
				 
				   MixingDetailed mixingDetailed = new MixingDetailed();
					mixingDetailed.setMixing_detailId(0);
					mixingDetailed.setMixingId(0);
					mixingDetailed.setSfId(sfId);

					mixingDetailed.setSfName(sfName);
					mixingDetailed.setReceivedQty(rmQty);
					mixingDetailed.setProductionQty(rmQty);//req qty set to Production

					mixingDetailed.setUom(uom);
					mixingDetailed.setMixingDate(date);
					mixingDetailed.setExBool1(0);
					mixingDetailed.setExInt2(0);
					mixingDetailed.setExInt1(0);
					mixingDetailed.setExInt3(0);
					mixingDetailed.setExVarchar1(""+mulFactor);//+prodMixingReqP1.get(i).getMulFactor()
					mixingDetailed.setExVarchar2("");
					mixingDetailed.setExVarchar3("");
					mixingDetailed.setOriginalQty(prevRmQty*mulFactor);//prodMixingReqP1.get(i).getPrevTotal()// new field 22 Jan
					mixingDetailed.setAutoOrderQty(prevRmQty*mulFactor);// prodMixingReqP1.get(i).getMulFactor() * prodMixingReqP1.get(i).getPrevTotal()
																															// field
					addmixingDetailedlist.add(mixingDetailed);
			 }
				MixingHeader mixingHeader = new MixingHeader();
			
				mixingHeader.setMixId(0);
				mixingHeader.setMixDate(date);
				mixingHeader.setProductionId(prodHeaderId);
				mixingHeader.setProductionBatch(postProdPlanHeader.getProductionBatch());
				mixingHeader.setStatus(2);
				mixingHeader.setDelStatus(0);
				mixingHeader.setTimeSlot(postProdPlanHeader.getTimeSlot());
				mixingHeader.setIsBom(1);
				mixingHeader.setExBool1(0);
				mixingHeader.setExInt1(settingList.getFrItemStockConfigure().get(0).getSettingValue());//deptId
				mixingHeader.setExInt2(0);
				mixingHeader.setExInt3(0);
				mixingHeader.setExVarchar1("");
				mixingHeader.setExVarchar2("");
				mixingHeader.setExVarchar3("");
				
				mixingHeader.setMixingDetailed(addmixingDetailedlist);
				System.out.println("while inserting Mixing Header = " + mixingHeader.toString());
				
				
				try {
					 String[] sfDidList=request.getParameterValues("sfDid");
					 List<BillOfMaterialDetailed> bomDetailList = new ArrayList<BillOfMaterialDetailed>();

					 for(int i=0;i<sfDidList.length;i++)
					 {
							int rmId = Integer.parseInt(request.getParameter("rmId" + sfDidList[i]));
							int rmType = Integer.parseInt(request.getParameter("rmType" +sfDidList[i]));
							float rmQty = Float.parseFloat(request.getParameter("rmQty2" + sfDidList[i]));
							float prevRmQty = Float.parseFloat(request.getParameter("prevRmQty" +sfDidList[i]));
							 String rmName=request.getParameter("rmName" + sfDidList[i]);
							 String uom=request.getParameter("uomRm" + sfDidList[i]);
							 
							BillOfMaterialDetailed	bomDetail = new BillOfMaterialDetailed();

							bomDetail.setDelStatus(0);
							bomDetail.setRmId(rmId);
							bomDetail.setRmIssueQty(rmQty);
							bomDetail.setUom(uom);
							bomDetail.setRmType(rmType);
							bomDetail.setRmReqQty(rmQty);
							bomDetail.setRmName(rmName);
							
							bomDetail.setRejectedQty(0);
							bomDetail.setAutoRmReqQty(prevRmQty);
							
							bomDetail.setReturnQty(0);
							bomDetailList.add(bomDetail);
					 }
					
					int fromDeptId=settingList.getFrItemStockConfigure().get(0).getSettingValue(); //change on 18-09-2019
					String fromDeptName=settingList.getFrItemStockConfigure().get(0).getSettingKey();
					
					BillOfMaterialHeader billOfMaterialHeader = new BillOfMaterialHeader();

					billOfMaterialHeader.setApprovedDate(date);
					billOfMaterialHeader.setApprovedUserId(userResponse.getUser().getId());//hardcoded
					billOfMaterialHeader.setDelStatus(0);
					DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
					Date date1 = new Date();
					date1 = df.parse(postProdPlanHeader.getProductionDate());
					billOfMaterialHeader.setProductionDate(date1);
					billOfMaterialHeader.setProductionId(prodHeaderId);
					billOfMaterialHeader.setReqDate(date);
					billOfMaterialHeader.setSenderUserid(userResponse.getUser().getId());//hardcoded
					billOfMaterialHeader.setStatus(4);
				
					billOfMaterialHeader.setExInt1(postProdPlanHeader.getItemGrp1());//Category
					billOfMaterialHeader.setRejApproveDate(date);
					billOfMaterialHeader.setRejApproveUserId(0);
					billOfMaterialHeader.setRejDate(date);
					billOfMaterialHeader.setRejUserId(0);
					
					billOfMaterialHeader.setIsManual(0);

						map = new LinkedMultiValueMap<String, Object>();
						map.add("settingKeyList",dept);
						FrItemStockConfigureList settingList1 = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
								FrItemStockConfigureList.class);
						int toDeptId=settingList1.getFrItemStockConfigure().get(0).getSettingValue();
						String toDeptName=settingList1.getFrItemStockConfigure().get(0).getSettingKey();
						
						billOfMaterialHeader.setToDeptId(toDeptId);
						billOfMaterialHeader.setToDeptName(toDeptName);
						billOfMaterialHeader.setIsProduction(1);
						billOfMaterialHeader.setFromDeptId(fromDeptId);
						billOfMaterialHeader.setFromDeptName(fromDeptName);
						billOfMaterialHeader.setIsPlan(postProdPlanHeader.getIsPlanned());			

						
						System.out.println("bom detail List " + bomDetailList.toString());
						billOfMaterialHeader.setBillOfMaterialDetailed(bomDetailList);

						System.out.println(" insert List " + billOfMaterialHeader.toString());
						Info info = restTemplate.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);
						System.out.println(info);
						if(info.getError()==false)
						{
							flag=1;
						}
						
						if(flag==1) {
						RestTemplate rest = new RestTemplate();
						MixingHeader mixingHeaderin = rest.postForObject(Constants.url + "insertMixingHeaderndDetailed", mixingHeader,
								MixingHeader.class);
						
						if (mixingHeaderin != null) {
							flag+=1;
						}
						}
					 }catch (Exception e) {
							e.printStackTrace();
						}
				
		 }catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
		
	}
	@RequestMapping(value = "/postLayeringData", method = RequestMethod.POST)
	public @ResponseBody int postLayeringData(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		 int flag=0;
		try {
				
			 RestTemplate restTemplate = new RestTemplate();
			 HttpSession session = request.getSession();
				UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");
			 List<MixingDetailed> addmixingDetailedlist = new ArrayList<MixingDetailed>();
			 Date date = new Date();

			 String dept=request.getParameter("dept2");

			 int prodHeaderId=Integer.parseInt(request.getParameter("prodHeaderId2"));
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			 map.add("planHeaderId", prodHeaderId);

				PostProdPlanHeader	postProdPlanHeader = restTemplate.postForObject(Constants.url + "PostProdPlanHeaderwithDetailed", map,
						PostProdPlanHeader.class);
				
				map = new LinkedMultiValueMap<String, Object>();
	            map.add("settingKeyList", dept);
	            FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
			    FrItemStockConfigureList.class);
	          
				 int itemDetailId=Integer.parseInt(request.getParameter("itemDetailId2"));
				 int sfId=Integer.parseInt(request.getParameter("sfId"+itemDetailId));
				 float sfQty=Float.parseFloat(request.getParameter("rmQty"+itemDetailId));
				 float prevSfQty=Float.parseFloat(request.getParameter("prevRmQty"+itemDetailId));
				 float mulFactor=Float.parseFloat(request.getParameter("mulFactor"+itemDetailId));
				 String sfName=request.getParameter("rmName"+itemDetailId);
				 String uom=request.getParameter("uom"+itemDetailId);
				 String  itemIds=request.getParameter("layer"+itemDetailId);
				 
				   MixingDetailed mixingDetailed = new MixingDetailed();
					mixingDetailed.setMixing_detailId(0);
					mixingDetailed.setMixingId(0);
					mixingDetailed.setSfId(sfId);

					mixingDetailed.setSfName(sfName);
					mixingDetailed.setReceivedQty(sfQty);
					mixingDetailed.setProductionQty(sfQty);//req qty set to Production

					mixingDetailed.setUom(uom);
					mixingDetailed.setMixingDate(date);
					mixingDetailed.setExBool1(0);
					mixingDetailed.setExInt2(0);
					mixingDetailed.setExInt1(0);
					mixingDetailed.setExInt3(0);
					mixingDetailed.setExVarchar1(""+mulFactor);//+prodMixingReqP1.get(i).getMulFactor()
					mixingDetailed.setExVarchar2("");
					mixingDetailed.setExVarchar3("");
					mixingDetailed.setOriginalQty(prevSfQty*mulFactor);//prodMixingReqP1.get(i).getPrevTotal()// new field 22 Jan
					mixingDetailed.setAutoOrderQty(prevSfQty*mulFactor);// prodMixingReqP1.get(i).getMulFactor() * prodMixingReqP1.get(i).getPrevTotal()
																															// field
					addmixingDetailedlist.add(mixingDetailed);
				MixingHeader mixingHeader = new MixingHeader();
			
				mixingHeader.setMixId(0);
				mixingHeader.setMixDate(date);
				mixingHeader.setProductionId(prodHeaderId);
				mixingHeader.setProductionBatch(postProdPlanHeader.getProductionBatch());
				mixingHeader.setStatus(2);
				mixingHeader.setDelStatus(0);
				mixingHeader.setTimeSlot(postProdPlanHeader.getTimeSlot());
				mixingHeader.setIsBom(1);
				mixingHeader.setExBool1(0);
				mixingHeader.setExInt1(settingList.getFrItemStockConfigure().get(0).getSettingValue());//deptId
				mixingHeader.setExInt2(0);
				mixingHeader.setExInt3(0);
				mixingHeader.setExVarchar1(""+itemIds);
				mixingHeader.setExVarchar2("");
				mixingHeader.setExVarchar3("");
				
				mixingHeader.setMixingDetailed(addmixingDetailedlist);
				System.out.println("while inserting Mixing Header = " + mixingHeader.toString());
				
				

				try {
					 String[] sfDidList=request.getParameterValues("sfDid");
					 List<BillOfMaterialDetailed> bomDetailList = new ArrayList<BillOfMaterialDetailed>();

					 for(int i=0;i<sfDidList.length;i++)
					 {
							int rmId = Integer.parseInt(request.getParameter("rmId" + sfDidList[i]));
							int rmType = Integer.parseInt(request.getParameter("rmType" +sfDidList[i]));
							float rmQty = Float.parseFloat(request.getParameter("rmQty2" + sfDidList[i]));
							float prevRmQty = Float.parseFloat(request.getParameter("prevRmQty" +sfDidList[i]));
							 String rmName=request.getParameter("rmName" + sfDidList[i]);
							 String uomName=request.getParameter("uomRm" + sfDidList[i]);
							 
							BillOfMaterialDetailed	bomDetail = new BillOfMaterialDetailed();

							bomDetail.setDelStatus(0);
							bomDetail.setRmId(rmId);
							bomDetail.setRmIssueQty(rmQty);
							bomDetail.setUom(uomName);
							bomDetail.setRmType(rmType);
							bomDetail.setRmReqQty(rmQty);
							bomDetail.setRmName(rmName);
							
							bomDetail.setRejectedQty(0);
							bomDetail.setAutoRmReqQty(prevRmQty);
							
							bomDetail.setReturnQty(0);
							bomDetailList.add(bomDetail);
					 }
					
					int fromDeptId=settingList.getFrItemStockConfigure().get(0).getSettingValue(); //change on 18-09-2019
					String fromDeptName=settingList.getFrItemStockConfigure().get(0).getSettingKey();
					
					BillOfMaterialHeader billOfMaterialHeader = new BillOfMaterialHeader();

					billOfMaterialHeader.setApprovedDate(date);
					billOfMaterialHeader.setApprovedUserId(userResponse.getUser().getId());
					billOfMaterialHeader.setDelStatus(0);
					DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
					Date date1 = new Date();
					date1 = df.parse(postProdPlanHeader.getProductionDate());
					billOfMaterialHeader.setProductionDate(date1);
					billOfMaterialHeader.setProductionId(prodHeaderId);
					billOfMaterialHeader.setReqDate(date);
					billOfMaterialHeader.setSenderUserid(userResponse.getUser().getId());//hardcoded
					billOfMaterialHeader.setStatus(4);
				
					billOfMaterialHeader.setExInt1(postProdPlanHeader.getItemGrp1());//Category
					billOfMaterialHeader.setRejApproveDate(date);
					billOfMaterialHeader.setRejApproveUserId(0);
					billOfMaterialHeader.setRejDate(date);
					billOfMaterialHeader.setRejUserId(0);
					
					billOfMaterialHeader.setIsManual(0);

						map = new LinkedMultiValueMap<String, Object>();
						map.add("settingKeyList", "BMS");
						FrItemStockConfigureList settingList1 = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
								FrItemStockConfigureList.class);
						int toDeptId=settingList1.getFrItemStockConfigure().get(0).getSettingValue();
						String toDeptName=settingList1.getFrItemStockConfigure().get(0).getSettingKey();
						
						billOfMaterialHeader.setToDeptId(toDeptId);
						billOfMaterialHeader.setToDeptName(toDeptName);
						billOfMaterialHeader.setIsProduction(1);
						billOfMaterialHeader.setFromDeptId(fromDeptId);
						billOfMaterialHeader.setFromDeptName(fromDeptName);
						billOfMaterialHeader.setIsPlan(postProdPlanHeader.getIsPlanned());			

						
						System.out.println("bom detail List " + bomDetailList.toString());
						billOfMaterialHeader.setBillOfMaterialDetailed(bomDetailList);

						System.out.println(" insert List " + billOfMaterialHeader.toString());
						Info info = restTemplate.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);
						System.out.println(info);
						if(info.getError()==false)
						{
							flag=1;
						}
					
						if(flag==1) {
						RestTemplate rest = new RestTemplate();
						MixingHeader mixingHeaderin = rest.postForObject(Constants.url + "insertMixingHeaderndDetailed", mixingHeader,
								MixingHeader.class);
					
						if (mixingHeaderin != null) {
							flag+=1;
						}
						}
					 }catch (Exception e) {
							e.printStackTrace();
						}
				
		 }catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
		
	}
	@RequestMapping(value = "/postCoatingData", method = RequestMethod.POST)
	public @ResponseBody int postCoatingData(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		 int flag=0;
		try {
				
			 RestTemplate restTemplate = new RestTemplate();
			 HttpSession session = request.getSession();
				UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");
			 List<MixingDetailed> addmixingDetailedlist = new ArrayList<MixingDetailed>();
			 Date date = new Date();

			 String dept=request.getParameter("dept3");

			 int prodHeaderId=Integer.parseInt(request.getParameter("prodHeaderId3"));
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			 map.add("planHeaderId", prodHeaderId);

				PostProdPlanHeader	postProdPlanHeader = restTemplate.postForObject(Constants.url + "PostProdPlanHeaderwithDetailed", map,
						PostProdPlanHeader.class);
				
				map = new LinkedMultiValueMap<String, Object>();
	            map.add("settingKeyList", dept);
	            FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
			    FrItemStockConfigureList.class);
	          
				 int itemDetailId=Integer.parseInt(request.getParameter("itemDetailId3"));
				 int sfId=Integer.parseInt(request.getParameter("sfId"+itemDetailId));
				 float sfQty=Float.parseFloat(request.getParameter("rmQty"+itemDetailId));
				 float prevSfQty=Float.parseFloat(request.getParameter("prevRmQty"+itemDetailId));
				 float mulFactor=Float.parseFloat(request.getParameter("mulFactor"+itemDetailId));
				 String sfName=request.getParameter("rmName"+itemDetailId);
				 String uom=request.getParameter("uom"+itemDetailId);
				 
				   MixingDetailed mixingDetailed = new MixingDetailed();
					mixingDetailed.setMixing_detailId(0);
					mixingDetailed.setMixingId(0);
					mixingDetailed.setSfId(sfId);

					mixingDetailed.setSfName(sfName);
					mixingDetailed.setReceivedQty(sfQty);
					mixingDetailed.setProductionQty(sfQty);//req qty set to Production

					mixingDetailed.setUom(uom);
					mixingDetailed.setMixingDate(date);
					mixingDetailed.setExBool1(0);
					mixingDetailed.setExInt2(0);
					mixingDetailed.setExInt1(0);
					mixingDetailed.setExInt3(0);
					mixingDetailed.setExVarchar1(""+mulFactor);//+prodMixingReqP1.get(i).getMulFactor()
					mixingDetailed.setExVarchar2("");
					mixingDetailed.setExVarchar3("");
					mixingDetailed.setOriginalQty(prevSfQty*mulFactor);//prodMixingReqP1.get(i).getPrevTotal()// new field 22 Jan
					mixingDetailed.setAutoOrderQty(prevSfQty*mulFactor);// prodMixingReqP1.get(i).getMulFactor() * prodMixingReqP1.get(i).getPrevTotal()
																															// field
					addmixingDetailedlist.add(mixingDetailed);
				MixingHeader mixingHeader = new MixingHeader();
			
				mixingHeader.setMixId(0);
				mixingHeader.setMixDate(date);
				mixingHeader.setProductionId(prodHeaderId);
				mixingHeader.setProductionBatch(postProdPlanHeader.getProductionBatch());
				mixingHeader.setStatus(2);
				mixingHeader.setDelStatus(0);
				mixingHeader.setTimeSlot(postProdPlanHeader.getTimeSlot());
				mixingHeader.setIsBom(1);
				mixingHeader.setExBool1(0);
				mixingHeader.setExInt1(settingList.getFrItemStockConfigure().get(0).getSettingValue());//deptId
				mixingHeader.setExInt2(0);
				mixingHeader.setExInt3(0);
				mixingHeader.setExVarchar1("");
				mixingHeader.setExVarchar2("");
				mixingHeader.setExVarchar3("");
				
				mixingHeader.setMixingDetailed(addmixingDetailedlist);
				System.out.println("while inserting Mixing Header = " + mixingHeader.toString());
				
				

				try {
					 String[] sfDidList=request.getParameterValues("sfDid");
					 List<BillOfMaterialDetailed> bomDetailList = new ArrayList<BillOfMaterialDetailed>();

					 for(int i=0;i<sfDidList.length;i++)
					 {
							int rmId = Integer.parseInt(request.getParameter("rmId" + sfDidList[i]));
							int rmType = Integer.parseInt(request.getParameter("rmType" +sfDidList[i]));
							float rmQty = Float.parseFloat(request.getParameter("rmQty2" + sfDidList[i]));
							float prevRmQty = Float.parseFloat(request.getParameter("prevRmQty" +sfDidList[i]));
							 String rmName=request.getParameter("rmName" + sfDidList[i]);
							 String uomName=request.getParameter("uomRm" + sfDidList[i]);
							 
							BillOfMaterialDetailed	bomDetail = new BillOfMaterialDetailed();

							bomDetail.setDelStatus(0);
							bomDetail.setRmId(rmId);
							bomDetail.setRmIssueQty(rmQty);
							bomDetail.setUom(uomName);
							bomDetail.setRmType(rmType);
							bomDetail.setRmReqQty(rmQty);
							bomDetail.setRmName(rmName);
							
							bomDetail.setRejectedQty(0);
							bomDetail.setAutoRmReqQty(prevRmQty);
							
							bomDetail.setReturnQty(0);
							bomDetailList.add(bomDetail);
					 }
					
					int fromDeptId=settingList.getFrItemStockConfigure().get(0).getSettingValue(); //change on 18-09-2019
					String fromDeptName=settingList.getFrItemStockConfigure().get(0).getSettingKey();
					
					BillOfMaterialHeader billOfMaterialHeader = new BillOfMaterialHeader();

					billOfMaterialHeader.setApprovedDate(date);
					billOfMaterialHeader.setApprovedUserId(userResponse.getUser().getId());//hardcoded
					billOfMaterialHeader.setDelStatus(0);
					DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
					 Date date1 = new Date();
					 date1 = df.parse(postProdPlanHeader.getProductionDate());
					 
					billOfMaterialHeader.setProductionDate(date1);
					billOfMaterialHeader.setProductionId(prodHeaderId);
					billOfMaterialHeader.setReqDate(date);
					billOfMaterialHeader.setSenderUserid(userResponse.getUser().getId());//hardcoded
					billOfMaterialHeader.setStatus(4);
				
					billOfMaterialHeader.setExInt1(postProdPlanHeader.getItemGrp1());//Category
					billOfMaterialHeader.setRejApproveDate(date);
					billOfMaterialHeader.setRejApproveUserId(0);
					billOfMaterialHeader.setRejDate(date);
					billOfMaterialHeader.setRejUserId(0);
					
					billOfMaterialHeader.setIsManual(0);

						map = new LinkedMultiValueMap<String, Object>();
						map.add("settingKeyList", "BMS");
						FrItemStockConfigureList settingList1 = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
								FrItemStockConfigureList.class);
						int toDeptId=settingList1.getFrItemStockConfigure().get(0).getSettingValue();
						String toDeptName=settingList1.getFrItemStockConfigure().get(0).getSettingKey();
						
						billOfMaterialHeader.setToDeptId(toDeptId);
						billOfMaterialHeader.setToDeptName(toDeptName);
						billOfMaterialHeader.setIsProduction(1);
						billOfMaterialHeader.setFromDeptId(fromDeptId);
						billOfMaterialHeader.setFromDeptName(fromDeptName);
						billOfMaterialHeader.setIsPlan(postProdPlanHeader.getIsPlanned());			

						
						System.out.println("bom detail List " + bomDetailList.toString());
						billOfMaterialHeader.setBillOfMaterialDetailed(bomDetailList);

						System.out.println(" insert List " + billOfMaterialHeader.toString());
						Info info = restTemplate.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);
						System.out.println(info);
						if(info.getError()==false)
						{
							flag=1;
						}
						if(flag==1) {
						RestTemplate rest = new RestTemplate();
						MixingHeader mixingHeaderin = rest.postForObject(Constants.url + "insertMixingHeaderndDetailed", mixingHeader,
								MixingHeader.class);
					
						if (mixingHeaderin != null) {
							flag+=1;
						}
						}
					 }catch (Exception e) {
							e.printStackTrace();
						}
				
		 }catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
		
	}
	
	
	@RequestMapping(value = "/findItemsByGrpIdForRmIssue", method = RequestMethod.GET)
	public @ResponseBody List<Item> findItemsByGrpIdForRmIssue(HttpServletRequest request, HttpServletResponse response) {
	
		ArrayList<Item> itemsList = new ArrayList<Item>();
		try {
			RestTemplate restTemplate = new RestTemplate();

			int grpId = Integer.parseInt(request.getParameter("grpId"));
			int prodHeaderId  = Integer.parseInt(request.getParameter("prodHeaderId"));
			String fromDept=request.getParameter("fromDept");
			String toDept=request.getParameter("toDept");
			System.out.println("cat Id " + grpId);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
            map.add("settingKeyList", fromDept);
            FrItemStockConfigureList frSettingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
		    FrItemStockConfigureList.class);
            
            map = new LinkedMultiValueMap<String, Object>();
            map.add("settingKeyList", toDept);
            FrItemStockConfigureList toSettingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
		    FrItemStockConfigureList.class);
            
			 map = new LinkedMultiValueMap<String, Object>();
			map.add("itemGrp3", grpId);
			map.add("prodHeaderId", prodHeaderId);
            map.add("fromDept", frSettingList.getFrItemStockConfigure().get(0).getSettingValue());
            map.add("toDept", toSettingList.getFrItemStockConfigure().get(0).getSettingValue());

			Item[] item = restTemplate.postForObject(Constants.url + "findItemsByGrpIdForRmIssue", map, Item[].class);

			itemsList = new ArrayList<Item>(Arrays.asList(item));
			System.out.println("itemsList"+itemsList.toString());

		} catch (Exception e) {
			System.out.println("Exception in /AJAX findItemsByGrpIdForRmIssue");
		}
		return itemsList;
	}
	List<GetItemSfHeader> itemHeaderList = new ArrayList<GetItemSfHeader>();

	@RequestMapping(value = "/findSfsByTypeId", method = RequestMethod.GET)
	public @ResponseBody List<GetItemSfHeader> findSfsByTypeId(HttpServletRequest request, HttpServletResponse response) {
	
	 try {
			int typeId = Integer.parseInt(request.getParameter("typeId"));
			System.err.println("typeId"+typeId);
			//int prodHeaderId  = Integer.parseInt(request.getParameter("prodHeaderId"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("sfType", typeId);
			
			RestTemplate restTemplate = new RestTemplate();
				ParameterizedTypeReference<List<GetItemSfHeader>> typeRef = new ParameterizedTypeReference<List<GetItemSfHeader>>() {
				};
				ResponseEntity<List<GetItemSfHeader>> responseEntity = restTemplate.exchange(Constants.url + "getItemSfHeadersBySfType",
						HttpMethod.POST, new HttpEntity<>(map), typeRef);
				
				itemHeaderList = responseEntity.getBody();
				System.err.println("itemHeaderList"+itemHeaderList.toString());

		} catch (Exception e) {
			System.out.println("Exception in /AJAX findSfByTypeId"+e.getMessage());
		}
		return itemHeaderList;
	}
	
	@RequestMapping(value = "/getSfDetailsForIssue", method = RequestMethod.POST)
	public @ResponseBody List<GetSFPlanDetailForMixing> getSfDetailsForIssue(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		
		List<GetSFPlanDetailForMixing> sfPlanDetailForBom=null;
		try {
			int prodHeaderId=Integer.parseInt(request.getParameter("prodHeaderId4"));
			 String dept=request.getParameter("dept4");
			System.err.println(dept);
			String[] itemIds = request.getParameterValues("items[]");

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < itemIds.length; i++) {
				sb = sb.append(itemIds[i] + ",");

			}
			String items = sb.toString();
			items = items.substring(0, items.length() - 1);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
		
            map.add("settingKeyList", dept);
            FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
		    FrItemStockConfigureList.class);
            map = new LinkedMultiValueMap<String, Object>();
				map.add("headerId", prodHeaderId);
				map.add("deptId", settingList.getFrItemStockConfigure().get(0).getSettingValue());
				map.add("itemId", items);
			GetSFPlanDetailForMixingList getSFPlanDetailForBomList = restTemplate
					.postForObject(Constants.url + "getSfDetailsForIssue", map,
						GetSFPlanDetailForMixingList.class);

		sfPlanDetailForBom = getSFPlanDetailForBomList.getSfPlanDetailForMixing();
		System.err.println(sfPlanDetailForBom);
		
		} catch (Exception e) {
			e.printStackTrace();
	}
		
	return sfPlanDetailForBom;

	}

	@RequestMapping(value = "/postIssueData", method = RequestMethod.POST)
	public @ResponseBody int postIssueData(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		 int flag=0;
		try {
				
			 RestTemplate restTemplate = new RestTemplate();
			 HttpSession session = request.getSession();
				UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");
			 Date date = new Date();

			 String dept=request.getParameter("dept4");

			 int prodHeaderId=Integer.parseInt(request.getParameter("prodHeaderId4"));
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			 map.add("planHeaderId", prodHeaderId);

				PostProdPlanHeader	postProdPlanHeader = restTemplate.postForObject(Constants.url + "PostProdPlanHeaderwithDetailed", map,
						PostProdPlanHeader.class);
				
				map = new LinkedMultiValueMap<String, Object>();
	            map.add("settingKeyList", dept);
	            FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
			    FrItemStockConfigureList.class);

				try {
					 String[] itemDetailIdList=request.getParameterValues("itemDetailId");
					 List<BillOfMaterialDetailed> bomDetailList = new ArrayList<BillOfMaterialDetailed>();
                     String items="";
					 StringBuilder sb = new StringBuilder();
                     List<Integer> itemAList=new ArrayList<>();
					 for(int i=0;i<itemDetailIdList.length;i++)
					 {
							
							
							int rmId = Integer.parseInt(request.getParameter("sfId" + itemDetailIdList[i]));
							int rmType = Integer.parseInt(request.getParameter("rmType" +itemDetailIdList[i]));
							float rmQty = Float.parseFloat(request.getParameter("rmQty" + itemDetailIdList[i]));
							float prevRmQty = Float.parseFloat(request.getParameter("prevRmQty" +itemDetailIdList[i]));
							 String rmName=request.getParameter("rmName" + itemDetailIdList[i]);
							 String uomName=request.getParameter("uom" + itemDetailIdList[i]);
							 int itemId=Integer.parseInt(request.getParameter("itemId" + itemDetailIdList[i]));
							 if(!itemAList.contains(itemId))
							 {
							   itemAList.add(itemId); 
		                       sb = sb.append(itemId + ",");
							 }

							BillOfMaterialDetailed	bomDetail = new BillOfMaterialDetailed();

							bomDetail.setDelStatus(0);
							bomDetail.setRmId(rmId);
							bomDetail.setRmIssueQty(rmQty);
							bomDetail.setUom(uomName);
							bomDetail.setRmType(rmType);
							bomDetail.setRmReqQty(rmQty);
							bomDetail.setRmName(rmName);
							
							bomDetail.setRejectedQty(0);
							bomDetail.setAutoRmReqQty(prevRmQty);
							
							bomDetail.setReturnQty(0);
							bomDetail.setExInt1(1);//Status of issue
							bomDetailList.add(bomDetail);
					 }
					 String itemsString = sb.toString();
					 itemsString = itemsString.substring(0, itemsString.length() - 1);
					int fromDeptId=settingList.getFrItemStockConfigure().get(0).getSettingValue(); //change on 18-09-2019
					String fromDeptName=settingList.getFrItemStockConfigure().get(0).getSettingKey();
					
					BillOfMaterialHeader billOfMaterialHeader = new BillOfMaterialHeader();

					billOfMaterialHeader.setApprovedDate(date);
					billOfMaterialHeader.setApprovedUserId(userResponse.getUser().getId());//hardcoded
					billOfMaterialHeader.setDelStatus(0);
					DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
					Date date1 = new Date();
					date1 = df.parse(postProdPlanHeader.getProductionDate());
					billOfMaterialHeader.setProductionDate(date1);
					billOfMaterialHeader.setProductionId(prodHeaderId);
					billOfMaterialHeader.setReqDate(date);
					billOfMaterialHeader.setSenderUserid(userResponse.getUser().getId());//hardcoded
					billOfMaterialHeader.setStatus(4);
				
					billOfMaterialHeader.setExInt1(postProdPlanHeader.getItemGrp1());//Category
					billOfMaterialHeader.setRejApproveDate(date);
					billOfMaterialHeader.setRejApproveUserId(0);
					billOfMaterialHeader.setRejDate(date);
					billOfMaterialHeader.setRejUserId(0);
					billOfMaterialHeader.setExInt2(1);//Status of issue
					billOfMaterialHeader.setExVarchar2(itemsString);//itemId
					billOfMaterialHeader.setIsManual(0);

						map = new LinkedMultiValueMap<String, Object>();
						map.add("settingKeyList", "BMS");
						FrItemStockConfigureList settingList1 = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
								FrItemStockConfigureList.class);
						int toDeptId=settingList1.getFrItemStockConfigure().get(0).getSettingValue();
						String toDeptName=settingList1.getFrItemStockConfigure().get(0).getSettingKey();
						
						billOfMaterialHeader.setToDeptId(toDeptId);
						billOfMaterialHeader.setToDeptName(toDeptName);
						billOfMaterialHeader.setIsProduction(1);
						billOfMaterialHeader.setFromDeptId(fromDeptId);
						billOfMaterialHeader.setFromDeptName(fromDeptName);
						billOfMaterialHeader.setIsPlan(postProdPlanHeader.getIsPlanned());			

						
						System.out.println("bom detail List " + bomDetailList.toString());
						billOfMaterialHeader.setBillOfMaterialDetailed(bomDetailList);

						System.out.println(" insert List " + billOfMaterialHeader.toString());
						Info info = restTemplate.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);
						System.out.println(info);
						if(info.getError()==false)
						{
							flag=1;
						}
						
					 }catch (Exception e) {
							e.printStackTrace();
						}
				
		 }catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
		
	}
	@RequestMapping(value = "/postManualData", method = RequestMethod.POST)
	public @ResponseBody int postManualData(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		 int flag=0;
		try {
				
			 RestTemplate restTemplate = new RestTemplate();
			 HttpSession session = request.getSession();
				UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");
			 List<MixingDetailed> addmixingDetailedlist = new ArrayList<MixingDetailed>();
			 Date date = new Date();

			 String dept=request.getParameter("dept5");

			 int prodHeaderId=Integer.parseInt(request.getParameter("prodHeaderId5"));
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			 map.add("planHeaderId", prodHeaderId);

				PostProdPlanHeader	postProdPlanHeader = restTemplate.postForObject(Constants.url + "PostProdPlanHeaderwithDetailed", map,
						PostProdPlanHeader.class);
				
				map = new LinkedMultiValueMap<String, Object>();
	            map.add("settingKeyList", dept);
	            FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
			    FrItemStockConfigureList.class);
	            int sfId=Integer.parseInt(request.getParameter("sfitems"));
				 float sfQty=Float.parseFloat(request.getParameter("no_of_kg"));
	            System.err.println("itemHeaderList"+sfId);System.err.println("itemHeaderList"+itemHeaderList.toString());
				 for(int m=0;m<itemHeaderList.size();m++) {
					 if(itemHeaderList.get(m).getSfId()==sfId) 
					 {
				
				 float prevSfQty=sfQty;
				 float mulFactor=itemHeaderList.get(m).getMulFactor();
				 String sfName=itemHeaderList.get(m).getSfName();
				 String uom="";//itemHeaderList.get(m).u
				 String  itemIds="";
				 
				   MixingDetailed mixingDetailed = new MixingDetailed();
					mixingDetailed.setMixing_detailId(0);
					mixingDetailed.setMixingId(0);
					mixingDetailed.setSfId(sfId);

					mixingDetailed.setSfName(sfName);
					mixingDetailed.setReceivedQty(sfQty);
					mixingDetailed.setProductionQty(sfQty);//req qty set to Production

					mixingDetailed.setUom(uom);
					mixingDetailed.setMixingDate(date);
					mixingDetailed.setExBool1(0);
					mixingDetailed.setExInt2(0);
					mixingDetailed.setExInt1(0);
					mixingDetailed.setExInt3(0);
					mixingDetailed.setExVarchar1(""+mulFactor);//+prodMixingReqP1.get(i).getMulFactor()
					mixingDetailed.setExVarchar2("");
					mixingDetailed.setExVarchar3("");
					mixingDetailed.setOriginalQty(prevSfQty*mulFactor);//prodMixingReqP1.get(i).getPrevTotal()// new field 22 Jan
					mixingDetailed.setAutoOrderQty(prevSfQty*mulFactor);// prodMixingReqP1.get(i).getMulFactor() * prodMixingReqP1.get(i).getPrevTotal()
																															// field
					addmixingDetailedlist.add(mixingDetailed);
				MixingHeader mixingHeader = new MixingHeader();
			
				mixingHeader.setMixId(0);
				mixingHeader.setMixDate(date);
				mixingHeader.setProductionId(prodHeaderId);
				mixingHeader.setProductionBatch(postProdPlanHeader.getProductionBatch());
				mixingHeader.setStatus(2);
				mixingHeader.setDelStatus(0);
				mixingHeader.setTimeSlot(postProdPlanHeader.getTimeSlot());
				mixingHeader.setIsBom(1);
				mixingHeader.setExBool1(0);
				mixingHeader.setExInt1(settingList.getFrItemStockConfigure().get(0).getSettingValue());//deptId
				mixingHeader.setExInt2(1);//manual mixing
				mixingHeader.setExInt3(0);
				mixingHeader.setExVarchar1("");
				mixingHeader.setExVarchar2("");
				mixingHeader.setExVarchar3("");
				
				mixingHeader.setMixingDetailed(addmixingDetailedlist);
				System.out.println("while inserting Mixing Header = " + mixingHeader.toString());
				
				

				try {
					 String[] sfDidList=request.getParameterValues("sfDid");
					 List<BillOfMaterialDetailed> bomDetailList = new ArrayList<BillOfMaterialDetailed>();

					 for(int i=0;i<sfDidList.length;i++)
					 {
							int rmId = Integer.parseInt(request.getParameter("rmId" + sfDidList[i]));
							int rmType = Integer.parseInt(request.getParameter("rmType" +sfDidList[i]));
							float rmQty = Float.parseFloat(request.getParameter("rmQty2" + sfDidList[i]));
							float prevRmQty = Float.parseFloat(request.getParameter("prevRmQty" +sfDidList[i]));
							 String rmName=request.getParameter("rmName" + sfDidList[i]);
							 String uomName=request.getParameter("uomRm" + sfDidList[i]);
							 
							BillOfMaterialDetailed	bomDetail = new BillOfMaterialDetailed();

							bomDetail.setDelStatus(0);
							bomDetail.setRmId(rmId);
							bomDetail.setRmIssueQty(rmQty);
							bomDetail.setUom(uomName);
							bomDetail.setRmType(rmType);
							bomDetail.setRmReqQty(rmQty);
							bomDetail.setRmName(rmName);
							
							bomDetail.setRejectedQty(0);
							bomDetail.setAutoRmReqQty(prevRmQty);
							
							bomDetail.setReturnQty(0);
							bomDetailList.add(bomDetail);
					 }
					
					int fromDeptId=settingList.getFrItemStockConfigure().get(0).getSettingValue(); //change on 18-09-2019
					String fromDeptName=settingList.getFrItemStockConfigure().get(0).getSettingKey();
					
					BillOfMaterialHeader billOfMaterialHeader = new BillOfMaterialHeader();

					billOfMaterialHeader.setApprovedDate(date);
					billOfMaterialHeader.setApprovedUserId(userResponse.getUser().getId());
					billOfMaterialHeader.setDelStatus(0);
					DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
					Date date1 = new Date();
					date1 = df.parse(postProdPlanHeader.getProductionDate());
					billOfMaterialHeader.setProductionDate(date1);
					billOfMaterialHeader.setProductionId(prodHeaderId);
					billOfMaterialHeader.setReqDate(date);
					billOfMaterialHeader.setSenderUserid(userResponse.getUser().getId());//hardcoded
					billOfMaterialHeader.setStatus(4);
				
					billOfMaterialHeader.setExInt1(postProdPlanHeader.getItemGrp1());//Category
					billOfMaterialHeader.setRejApproveDate(date);
					billOfMaterialHeader.setRejApproveUserId(0);
					billOfMaterialHeader.setRejDate(date);
					billOfMaterialHeader.setRejUserId(0);
					
					billOfMaterialHeader.setIsManual(1);

						map = new LinkedMultiValueMap<String, Object>();
						map.add("settingKeyList", "BMS");
						FrItemStockConfigureList settingList1 = restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
								FrItemStockConfigureList.class);
						int toDeptId=settingList1.getFrItemStockConfigure().get(0).getSettingValue();
						String toDeptName=settingList1.getFrItemStockConfigure().get(0).getSettingKey();
						
						billOfMaterialHeader.setToDeptId(toDeptId);
						billOfMaterialHeader.setToDeptName(toDeptName);
						billOfMaterialHeader.setIsProduction(1);
						billOfMaterialHeader.setFromDeptId(fromDeptId);
						billOfMaterialHeader.setFromDeptName(fromDeptName);
						billOfMaterialHeader.setIsPlan(postProdPlanHeader.getIsPlanned());			

						System.out.println("bom detail List " + bomDetailList.toString());
						billOfMaterialHeader.setBillOfMaterialDetailed(bomDetailList);

						System.out.println(" insert List " + billOfMaterialHeader.toString());
						Info info = restTemplate.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);
						System.out.println(info);
						if(info.getError()==false)
						{
							flag=1;
						}
					
						if(flag==1) {
						RestTemplate rest = new RestTemplate();
						MixingHeader mixingHeaderin = rest.postForObject(Constants.url + "insertMixingHeaderndDetailed", mixingHeader,
								MixingHeader.class);
					
						if (mixingHeaderin != null) {
							flag+=1;
						}
						}
					 }catch (Exception e) {
							e.printStackTrace();
						}
				 }
				 }
		 }catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
		
	}
}
