package com.ats.adminpanel.controller;

import java.text.DateFormat;
import java.text.ParseException;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.controller.model.GetSfData;
import com.ats.adminpanel.model.GetPrepData;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.RawMaterial.ItemSfDetail;
import com.ats.adminpanel.model.RawMaterial.SfItemDetailList;
import com.ats.adminpanel.model.item.FrItemStockConfigureList;
import com.ats.adminpanel.model.production.GetProdPlanHeader;
import com.ats.adminpanel.model.production.GetProdPlanHeaderList;
import com.ats.adminpanel.model.production.PostProdPlanHeader;
import com.ats.adminpanel.model.production.mixing.temp.GetSFMixingForBomList;
import com.ats.adminpanel.model.production.mixing.temp.GetSFPlanDetailForMixing;
import com.ats.adminpanel.model.production.mixing.temp.GetSFPlanDetailForMixingList;
import com.ats.adminpanel.model.production.mixing.temp.ProdMixingReqP1;
import com.ats.adminpanel.model.production.mixing.temp.ProdMixingReqP1List;
import com.ats.adminpanel.model.production.mixing.temp.TempMixing;
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

				System.out.println("inside if ");
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

			System.out.println("prod header " + prodPlanHeaderList.toString());
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
	
	List<ItemSfDetail> sfItemDetailListNew=null;
	@RequestMapping(value = "/getSfDetails", method = RequestMethod.POST)
	public @ResponseBody List<ItemSfDetail> getSfDetails(@RequestBody List<GetSfData> data) throws ParseException {
	
		List<ItemSfDetail> sfItemDetailListRes;
	   sfItemDetailListNew=new ArrayList<>();
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
			
			
			for (int i = 0; i < sfItemDetailListNew.size(); i++) {
				float rmQty=0;
				float rmWeight=0;
				for (int j = 0; j < sfItemDetailListRes.size(); j++) {
					
					if(sfItemDetailListRes.get(i).getRmType()==sfDetaiListItems.getSfItemDetail().get(j).getRmType() && sfItemDetailListRes.get(i).getRmId()==sfDetaiListItems.getSfItemDetail().get(j).getRmId())
					{
						rmQty=rmQty+sfDetaiListItems.getSfItemDetail().get(j).getRmQty();
						rmWeight=rmWeight+sfDetaiListItems.getSfItemDetail().get(j).getRmWeight();
					}
					
				}
				sfItemDetailListNew.get(i).setRmQty(rmQty);
				sfItemDetailListNew.get(i).setRmWeight(rmWeight);
			
			}
			
			for (int i = 0; i < data.size(); i++) {
				for (int j = 0;j < sfItemDetailListNew.size(); j++) {
					if(data.get(i).getSfId()==sfItemDetailListNew.get(j).getSfId())
					{
						sfItemDetailListNew.get(i).setRmQty((data.get(i).getRmQty()*sfItemDetailListNew.get(j).getRmQty()*sfItemDetailListNew.get(j).getRmWeight()));
					}
				}
			}
			System.err.println(sfItemDetailListNew.toString());
			
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
			
			
			for (int i = 0; i < sfItemDetailListLayering.size(); i++) {
				float rmQty=0;
				float rmWeight=0;
				for (int j = 0; j < sfItemDetailListRes.size(); j++) {
					
					if(sfItemDetailListRes.get(i).getRmType()==sfDetaiListItems.getSfItemDetail().get(j).getRmType() && sfItemDetailListRes.get(i).getRmId()==sfDetaiListItems.getSfItemDetail().get(j).getRmId())
					{
						rmQty=rmQty+sfDetaiListItems.getSfItemDetail().get(j).getRmQty();
						rmWeight=rmWeight+sfDetaiListItems.getSfItemDetail().get(j).getRmWeight();
					}
					
				}
				sfItemDetailListLayering.get(i).setRmQty(rmQty);
				sfItemDetailListLayering.get(i).setRmWeight(rmWeight);
			
			}
			
			for (int i = 0; i < data.size(); i++) {
				for (int j = 0;j < sfItemDetailListLayering.size(); j++) {
					if(data.get(i).getSfId()==sfItemDetailListLayering.get(j).getSfId())
					{
						sfItemDetailListLayering.get(i).setRmQty((data.get(i).getRmQty()*sfItemDetailListLayering.get(j).getRmQty()*sfItemDetailListLayering.get(j).getRmWeight()));
					}
				}
			}
			System.err.println(sfItemDetailListLayering.toString());
			
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
				mixingHeader.setIsBom(0);
				mixingHeader.setExBool1(0);
				mixingHeader.setExInt1(settingList.getFrItemStockConfigure().get(0).getSettingValue());//deptId
				mixingHeader.setExInt2(0);
				mixingHeader.setExInt3(0);
				mixingHeader.setExVarchar1("");
				mixingHeader.setExVarchar2("");
				mixingHeader.setExVarchar3("");
				
				mixingHeader.setMixingDetailed(addmixingDetailedlist);
				System.out.println("while inserting Mixing Header = " + mixingHeader.toString());
				RestTemplate rest = new RestTemplate();
				MixingHeader mixingHeaderin = rest.postForObject(Constants.url + "insertMixingHeaderndDetailed", mixingHeader,
						MixingHeader.class);
				/*map = new LinkedMultiValueMap<String, Object>();
				map.add("productionId", prodHeaderId);
				map.add("flag", 0);
				map.add("deptId", settingList.getFrItemStockConfigure().get(0).getSettingValue());
				if (mixingHeaderin != null) {
					int updateisMixing = rest.postForObject(Constants.url + "updateisMixingandBom", map, Integer.class);
				}*/
				if (mixingHeaderin != null) {
					flag+=1;
				}
				
				String ret="";

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
					billOfMaterialHeader.setApprovedUserId(0);
					billOfMaterialHeader.setDelStatus(0);
					 
					billOfMaterialHeader.setProductionDate(date);
					billOfMaterialHeader.setProductionId(prodHeaderId);
					billOfMaterialHeader.setReqDate(date);
					billOfMaterialHeader.setSenderUserid(1);//hardcoded
					billOfMaterialHeader.setStatus(0);
				
					billOfMaterialHeader.setExInt1(postProdPlanHeader.getItemGrp1());//Category
					billOfMaterialHeader.setRejApproveDate(date);
					billOfMaterialHeader.setRejApproveUserId(0);
					billOfMaterialHeader.setRejDate(date);
					billOfMaterialHeader.setRejUserId(0);
					
					billOfMaterialHeader.setIsManual(0);

						map = new LinkedMultiValueMap<String, Object>();
						String settingKey1 = new String();
						map.add("settingKeyList", "BMSSTORE");
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
						int prodId=billOfMaterialHeader.getProductionId();
						Info info = restTemplate.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);
						System.out.println(info);
						if(info.getError()==false)
						{
							flag+=1;
						}
						/*if(info.getError()==false)
						{
							map = new LinkedMultiValueMap<String, Object>();
							map.add("productionId", prodId);
							map.add("flag", 1);
							map.add("deptId", toDeptId);
							int updateisBom = restTemplate.postForObject(Constants.url + "updateisMixingandBom", map,
									Integer.class); 
							System.out.println("updateIsBom "+updateisBom);
						}*/
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
				mixingHeader.setIsBom(0);
				mixingHeader.setExBool1(0);
				mixingHeader.setExInt1(settingList.getFrItemStockConfigure().get(0).getSettingValue());//deptId
				mixingHeader.setExInt2(0);
				mixingHeader.setExInt3(0);
				mixingHeader.setExVarchar1("");
				mixingHeader.setExVarchar2("");
				mixingHeader.setExVarchar3("");
				
				mixingHeader.setMixingDetailed(addmixingDetailedlist);
				System.out.println("while inserting Mixing Header = " + mixingHeader.toString());
				RestTemplate rest = new RestTemplate();
				MixingHeader mixingHeaderin = rest.postForObject(Constants.url + "insertMixingHeaderndDetailed", mixingHeader,
						MixingHeader.class);
				/*map = new LinkedMultiValueMap<String, Object>();
				map.add("productionId", prodHeaderId);
				map.add("flag", 0);
				map.add("deptId", settingList.getFrItemStockConfigure().get(0).getSettingValue());
				if (mixingHeaderin != null) {
					int updateisMixing = rest.postForObject(Constants.url + "updateisMixingandBom", map, Integer.class);
				}*/
				if (mixingHeaderin != null) {
					flag+=1;
				}
				
				String ret="";

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
					billOfMaterialHeader.setApprovedUserId(0);
					billOfMaterialHeader.setDelStatus(0);
					 
					billOfMaterialHeader.setProductionDate(date);
					billOfMaterialHeader.setProductionId(prodHeaderId);
					billOfMaterialHeader.setReqDate(date);
					billOfMaterialHeader.setSenderUserid(1);//hardcoded
					billOfMaterialHeader.setStatus(0);
				
					billOfMaterialHeader.setExInt1(postProdPlanHeader.getItemGrp1());//Category
					billOfMaterialHeader.setRejApproveDate(date);
					billOfMaterialHeader.setRejApproveUserId(0);
					billOfMaterialHeader.setRejDate(date);
					billOfMaterialHeader.setRejUserId(0);
					
					billOfMaterialHeader.setIsManual(0);

						map = new LinkedMultiValueMap<String, Object>();
						String settingKey1 = new String();
						map.add("settingKeyList", "BMSSTORE");
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
						int prodId=billOfMaterialHeader.getProductionId();
						Info info = restTemplate.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);
						System.out.println(info);
						if(info.getError()==false)
						{
							flag+=1;
						}
						/*if(info.getError()==false)
						{
							map = new LinkedMultiValueMap<String, Object>();
							map.add("productionId", prodId);
							map.add("flag", 1);
							map.add("deptId", toDeptId);
							int updateisBom = restTemplate.postForObject(Constants.url + "updateisMixingandBom", map,
									Integer.class); 
							System.out.println("updateIsBom "+updateisBom);
						}*/
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
				mixingHeader.setIsBom(0);
				mixingHeader.setExBool1(0);
				mixingHeader.setExInt1(settingList.getFrItemStockConfigure().get(0).getSettingValue());//deptId
				mixingHeader.setExInt2(0);
				mixingHeader.setExInt3(0);
				mixingHeader.setExVarchar1("");
				mixingHeader.setExVarchar2("");
				mixingHeader.setExVarchar3("");
				
				mixingHeader.setMixingDetailed(addmixingDetailedlist);
				System.out.println("while inserting Mixing Header = " + mixingHeader.toString());
				RestTemplate rest = new RestTemplate();
				MixingHeader mixingHeaderin = rest.postForObject(Constants.url + "insertMixingHeaderndDetailed", mixingHeader,
						MixingHeader.class);
				/*map = new LinkedMultiValueMap<String, Object>();
				map.add("productionId", prodHeaderId);
				map.add("flag", 0);
				map.add("deptId", settingList.getFrItemStockConfigure().get(0).getSettingValue());
				if (mixingHeaderin != null) {
					int updateisMixing = rest.postForObject(Constants.url + "updateisMixingandBom", map, Integer.class);
				}*/
				if (mixingHeaderin != null) {
					flag+=1;
				}
				
				String ret="";

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
					billOfMaterialHeader.setApprovedUserId(0);
					billOfMaterialHeader.setDelStatus(0);
					 
					billOfMaterialHeader.setProductionDate(date);
					billOfMaterialHeader.setProductionId(prodHeaderId);
					billOfMaterialHeader.setReqDate(date);
					billOfMaterialHeader.setSenderUserid(1);//hardcoded
					billOfMaterialHeader.setStatus(0);
				
					billOfMaterialHeader.setExInt1(postProdPlanHeader.getItemGrp1());//Category
					billOfMaterialHeader.setRejApproveDate(date);
					billOfMaterialHeader.setRejApproveUserId(0);
					billOfMaterialHeader.setRejDate(date);
					billOfMaterialHeader.setRejUserId(0);
					
					billOfMaterialHeader.setIsManual(0);

						map = new LinkedMultiValueMap<String, Object>();
						String settingKey1 = new String();
						map.add("settingKeyList", "BMSSTORE");
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
						int prodId=billOfMaterialHeader.getProductionId();
						Info info = restTemplate.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);
						System.out.println(info);
						if(info.getError()==false)
						{
							flag+=1;
						}
						/*if(info.getError()==false)
						{
							map = new LinkedMultiValueMap<String, Object>();
							map.add("productionId", prodId);
							map.add("flag", 1);
							map.add("deptId", toDeptId);
							int updateisBom = restTemplate.postForObject(Constants.url + "updateisMixingandBom", map,
									Integer.class); 
							System.out.println("updateIsBom "+updateisBom);
						}*/
					 }catch (Exception e) {
							e.printStackTrace();
						}
				
		 }catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
		
	}
}
