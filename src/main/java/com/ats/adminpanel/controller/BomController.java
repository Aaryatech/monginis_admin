package com.ats.adminpanel.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.bcel.classfile.Constant;
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
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.login.UserResponse;
import com.ats.adminpanel.model.production.mixing.temp.GetSFMixingForBom;
import com.ats.adminpanel.model.production.mixing.temp.GetSFMixingForBomList;
import com.ats.adminpanel.model.production.mixing.temp.GetSFPlanDetailForMixing;
import com.ats.adminpanel.model.production.mixing.temp.GetSFPlanDetailForMixingList;
import com.ats.adminpanel.model.production.mixing.temp.GetTempMixItemDetail;
import com.ats.adminpanel.model.production.mixing.temp.GetTempMixItemDetailList;
import com.ats.adminpanel.model.production.mixing.temp.TempMixing;
import com.ats.adminpanel.model.production.mixing.temp.TempMixingList;
import com.ats.adminpanel.model.productionplan.BillOfMaterialDetailed;
import com.ats.adminpanel.model.productionplan.BillOfMaterialHeader;
import com.ats.adminpanel.model.productionplan.GetBillOfMaterialList;
import com.ats.adminpanel.model.productionplan.MixingHeader;

@Controller
public class BomController {

	GetSFPlanDetailForMixingList getSFPlanDetailForBomList;

	List<GetSFPlanDetailForMixing> sfPlanDetailForBom = new ArrayList<>();

	GetSFMixingForBomList sFMixingForBomList;
	
	List<GetSFMixingForBom> sFMixingForBom = new ArrayList<>();

	String prodOrMixDate;
	int globalHeaderId;
int globalIsPlan;
	@RequestMapping(value = "/showBom/{prodHeaderId}/{isMix}/{date}/{isPlan}", method = RequestMethod.GET)
	public ModelAndView showBom2(@PathVariable int prodHeaderId, @PathVariable int isMix, @PathVariable String date,
			@PathVariable int isPlan,
			HttpServletRequest request, HttpServletResponse response) throws ParseException {

		prodOrMixDate = date;

		globalHeaderId = prodHeaderId;

		globalIsPlan=isPlan;
		ModelAndView mav = new ModelAndView("production/addBom");

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();
			if (isMix == 1) {

				map.add("headerId", prodHeaderId);

				getSFPlanDetailForBomList = restTemplate.postForObject(Constants.url + "getSfPlanDetailForBom", map,
						GetSFPlanDetailForMixingList.class);

				sfPlanDetailForBom = getSFPlanDetailForBomList.getSfPlanDetailForMixing();

				System.out.println("sf Plan Detail For Bom  " + sfPlanDetailForBom.toString());
				
				mav.addObject("planDetailForBom", sfPlanDetailForBom);
				
			} else if (isMix == 0) {
				
				System.out.println("inside Else");

				map = new LinkedMultiValueMap<String, Object>();

				map.add("mixingId", prodHeaderId);

				sFMixingForBomList = restTemplate.postForObject(Constants.url + "getSFMixingForBom", map,
						GetSFMixingForBomList.class);

				sFMixingForBom = sFMixingForBomList.getsFMixingForBom();

				System.out.println("sf Mixing Detail For Bom  " + sFMixingForBom.toString());

				mav.addObject("planDetailForBom", sFMixingForBom);
			}

			mav.addObject("isMix", isMix);

		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("Error In showBom " + e.getMessage());
		}
		
	return mav;

	}

	@RequestMapping(value = "/insertBom", method = RequestMethod.GET)
	public ModelAndView insertBom(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("production/addBom");

		HttpSession session=request.getSession();
		UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");
		
		int deptId=userResponse.getUser().getDeptId();
		
		int userId=userResponse.getUser().getId();
		
		System.out.println("new Field Dept Id = "+userResponse.getUser().getDeptId());
		
		String isMix = request.getParameter("isMix");
		System.out.println("isMix " + isMix);
		int isMixing = Integer.parseInt(isMix);
		System.out.println("inside insert Bom ");
		Date prodOrMixDate1 = null;

		SimpleDateFormat dtFormat = new SimpleDateFormat("dd-MM-yyyy");

		try {
			prodOrMixDate1 = dtFormat.parse(prodOrMixDate);
		} catch (ParseException e1) {

			System.out.println("Exce In Date conversion");
			e1.printStackTrace();
		}

		try {

			RestTemplate restTemplate = new RestTemplate();


			Date date = new Date();

			BillOfMaterialHeader billOfMaterialHeader = new BillOfMaterialHeader();

			billOfMaterialHeader.setApprovedDate(date);
			billOfMaterialHeader.setApprovedUserId(0);
			billOfMaterialHeader.setDelStatus(0);
			billOfMaterialHeader.setFromDeptId(deptId);
			billOfMaterialHeader.setProductionDate(prodOrMixDate1);
			billOfMaterialHeader.setProductionId(globalHeaderId);
			billOfMaterialHeader.setReqDate(date);
			billOfMaterialHeader.setSenderUserid(userId);
			billOfMaterialHeader.setStatus(0);
			billOfMaterialHeader.setToDeptId(0);
			billOfMaterialHeader.setToDeptName("BMS");
			
			billOfMaterialHeader.setRejApproveDate(date);
			billOfMaterialHeader.setRejApproveUserId(0);
			billOfMaterialHeader.setRejDate(date);
			billOfMaterialHeader.setRejUserId(0);
			
			billOfMaterialHeader.setIsManual(0);

			List<BillOfMaterialDetailed> bomDetailList = new ArrayList<BillOfMaterialDetailed>();
			BillOfMaterialDetailed bomDetail = null;

			if (isMixing == 1) {
				billOfMaterialHeader.setIsProduction(1);

				billOfMaterialHeader.setFromDeptName("Prod");
				billOfMaterialHeader.setIsPlan(globalIsPlan);

				
				billOfMaterialHeader.setIsPlan(0);

				for (int i = 0; i < sfPlanDetailForBom.size(); i++) {

					String editQty = request.getParameter("editQty" + i);
					bomDetail = new BillOfMaterialDetailed();

					System.out.println("editQty " + editQty);

					bomDetail.setDelStatus(0);
					bomDetail.setRmId(sfPlanDetailForBom.get(i).getRmId());
					bomDetail.setRmIssueQty(0.0F);
					bomDetail.setUom(sfPlanDetailForBom.get(i).getUom());
					bomDetail.setRmType(sfPlanDetailForBom.get(i).getRmType());
					bomDetail.setRmReqQty(Integer.parseInt(editQty));
					bomDetail.setRmName(sfPlanDetailForBom.get(i).getRmName());
					
					bomDetail.setRejectedQty(0);
					bomDetail.setAutoRmReqQty(sfPlanDetailForBom.get(i).getTotal());
					
					bomDetail.setReturnQty(0);
					bomDetailList.add(bomDetail);

				}
				
				System.out.println("bom detail List " + bomDetailList.toString());
				billOfMaterialHeader.setBillOfMaterialDetailed(bomDetailList);

				System.out.println(" insert List " + billOfMaterialHeader.toString());
				int prodId=billOfMaterialHeader.getProductionId();
				Info info = restTemplate.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("productionId", prodId);
				map.add("flag", 1);
				int updateisBom = restTemplate.postForObject(Constants.url + "updateisMixingandBom", map,
						Integer.class);

				
				
			}

			else {

				billOfMaterialHeader.setFromDeptName("Mixing");
				billOfMaterialHeader.setIsProduction(0);
				
				billOfMaterialHeader.setIsPlan(0);

				for (int i = 0; i < sFMixingForBom.size(); i++) {

					String editQty = request.getParameter("editQty" + i);
					bomDetail = new BillOfMaterialDetailed();

					System.out.println("editQty " + editQty);

					bomDetail.setDelStatus(0);
					bomDetail.setRmId(sFMixingForBom.get(i).getRmId());
					bomDetail.setRmIssueQty(1.0F);
					bomDetail.setUom(sFMixingForBom.get(i).getUom());
					bomDetail.setRmType(sFMixingForBom.get(i).getRmType());
					bomDetail.setRmReqQty(sFMixingForBom.get(i).getTotal());
					bomDetail.setRmName(sFMixingForBom.get(i).getRmName());
					
					bomDetail.setRmReqQty(Integer.parseInt(editQty));

					bomDetail.setReturnQty(0);


					bomDetailList.add(bomDetail);

				}
				
				System.out.println("bom detail List " + bomDetailList.toString());
				billOfMaterialHeader.setBillOfMaterialDetailed(bomDetailList);

				System.out.println(" insert List " + billOfMaterialHeader.toString());
				int mixId=billOfMaterialHeader.getProductionId();
				Info info = restTemplate.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("mixId", mixId);
				int updateisBom = restTemplate.postForObject(Constants.url + "updateisBomInMixing", map,
						Integer.class);
			}

			


		} catch (Exception e) {
			e.printStackTrace();

		}
		
	return mav;
	
	}
	
	//from akshay View BOM-------------
	
	private List<BillOfMaterialHeader> getBOMListall;
	public List<BillOfMaterialHeader> getbomList = new ArrayList<BillOfMaterialHeader>();
	BillOfMaterialHeader billOfMaterialHeader = new BillOfMaterialHeader();
	List<BillOfMaterialDetailed> bomwithdetaild = new ArrayList<BillOfMaterialDetailed>();
	
	@RequestMapping(value = "/getBomList", method = RequestMethod.GET)
	public ModelAndView getBomList(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		
		ModelAndView model = new ModelAndView("productionPlan/getbomlist");//
		getbomList = new ArrayList<BillOfMaterialHeader>();
		
		RestTemplate rest = new RestTemplate();
		try
		{
			GetBillOfMaterialList getBillOfMaterialList= rest.getForObject(Constants.url + "/getallBOMHeaderList", GetBillOfMaterialList.class);
			 
			
			System.out.println("getbomList"+getBillOfMaterialList.getBillOfMaterialHeader().toString());
			for(int i=0;i<getBillOfMaterialList.getBillOfMaterialHeader().size();i++)
			{
				BillOfMaterialHeader billOfMaterialHeader=getBillOfMaterialList.getBillOfMaterialHeader().get(i);
				int Status=billOfMaterialHeader.getStatus();
				if(Status==0)
				{
					getbomList.add(getBillOfMaterialList.getBillOfMaterialHeader().get(i));
				}
			}
			System.out.println("bomHeaderList"+getBillOfMaterialList.getBillOfMaterialHeader().toString());
			
		}catch(Exception e)
		{
			System.out.println("error in controller "+e.getMessage());
		}
		model.addObject("getbomList",getbomList) ;
		return model;

	}
	
	
	
	
	@RequestMapping(value = "/getBomAllListWithDate", method = RequestMethod.GET)
	@ResponseBody
	public List<BillOfMaterialHeader> getBomAllListWithDate(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		String frmdate=request.getParameter("from_date");
		String todate=request.getParameter("to_date");
		
		System.out.println("in getMixingListWithDate   "+frmdate+todate);
		String frdate=DateConvertor.convertToYMD(frmdate);
		String tdate=DateConvertor.convertToYMD(todate);
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("frmdate",frdate);
		map.add("todate",tdate);
		System.out.println("in getBOMListWithDate   "+frdate+tdate);
		RestTemplate rest = new RestTemplate();
		GetBillOfMaterialList getBillOfMaterialList= rest.postForObject(Constants.url + "/getBOMHeaderList",map, GetBillOfMaterialList.class);
		getBOMListall  = getBillOfMaterialList.getBillOfMaterialHeader();
		return getBOMListall;
	

	}
	
	@RequestMapping(value = "/viewDetailBOMRequest", method = RequestMethod.GET)
	public ModelAndView viewDetailBOMRequest(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		ModelAndView model = new ModelAndView("productionPlan/showbomdetailed");
		
		
		//String mixId=request.getParameter("mixId");
		int reqId=Integer.parseInt(request.getParameter("reqId"));
		System.out.println(reqId);
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("reqId",reqId);
		RestTemplate rest = new RestTemplate();
		billOfMaterialHeader=rest.postForObject(Constants.url + "/getDetailedwithreqId",map, BillOfMaterialHeader.class);
		bomwithdetaild =billOfMaterialHeader.getBillOfMaterialDetailed();
		
		model.addObject("billOfMaterialHeader",billOfMaterialHeader);
		model.addObject("bomwithdetaild", bomwithdetaild);
		
		return model;
	}
	
	
	@RequestMapping(value = "/approvedBom", method = RequestMethod.POST)
	public String approvedBom(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		Date date= new Date();
		
		HttpSession session=request.getSession();
		UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");
		
		
		int userId=userResponse.getUser().getId();
		
		
		for(int i=0;i<billOfMaterialHeader.getBillOfMaterialDetailed().size();i++)
		{
			System.out.println(12);
			 
			 
				System.out.println(13);
				String issue_qty=request.getParameter("issue_qty"+billOfMaterialHeader.getBillOfMaterialDetailed().get(i).getReqDetailId());
				
				if(issue_qty!=null) {
					System.out.println("issue_qty Qty   :"+issue_qty);
					float issueqty= Float.parseFloat(issue_qty);
					billOfMaterialHeader.getBillOfMaterialDetailed().get(i).setRmIssueQty(issueqty);
					System.out.println("productionQty  :"+issueqty);
				}
				else
				{
					billOfMaterialHeader.getBillOfMaterialDetailed().get(i).setRmIssueQty(0);
				}
				System.out.println(2);
			 
		}
		billOfMaterialHeader.setStatus(1);
		billOfMaterialHeader.setApprovedUserId(userId);
		billOfMaterialHeader.setApprovedDate(date);
		
		System.out.println(billOfMaterialHeader.toString());
		
		RestTemplate rest = new RestTemplate();
		
		Info info = rest.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);	
		System.out.println(info);
		
		return "redirect:/getBomList";
	}
	
	
	@RequestMapping(value = "/rejectiontoBms", method = RequestMethod.GET)
	public ModelAndView rejectiontoBms(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		ModelAndView model = new ModelAndView("productionPlan/rejectforbom");
		
		
		
		System.out.println("in rejection form ");
		
		model.addObject("billOfMaterialHeader",billOfMaterialHeader);
		model.addObject("bomwithdetaild", bomwithdetaild);
		
		return model;
	}
	
	
	@RequestMapping(value = "/updateRejectedQty", method = RequestMethod.POST)
	public String updateRejectedQty(HttpServletRequest request, HttpServletResponse response) {
		/*Constants.mainAct = 17;
		Constants.subAct=184;*/
		Date date= new Date();
		HttpSession session=request.getSession();
		UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");
		
		
		int userId=userResponse.getUser().getId();
		
		for(int i=0;i<billOfMaterialHeader.getBillOfMaterialDetailed().size();i++)
		{
			System.out.println(12);
			 
			 
				System.out.println(13);
				String reject_qty=request.getParameter("rejectedQty"+billOfMaterialHeader.getBillOfMaterialDetailed().get(i).getReqDetailId());
				String return_qty=request.getParameter("returnQty"+billOfMaterialHeader.getBillOfMaterialDetailed().get(i).getReqDetailId());
				
				if(reject_qty!=null) {
					System.out.println("reject_qty Qty   :"+reject_qty);
					float rejectqty= Float.parseFloat(reject_qty);
					billOfMaterialHeader.getBillOfMaterialDetailed().get(i).setRejectedQty(rejectqty);
					System.out.println("reject_qty  :"+rejectqty);
				}
				else
				{
					billOfMaterialHeader.getBillOfMaterialDetailed().get(i).setRejectedQty(0);
				}
				
				if(return_qty!=null) {
					System.out.println("return_qty Qty   :"+return_qty);
					float returnqty= Float.parseFloat(return_qty);
					billOfMaterialHeader.getBillOfMaterialDetailed().get(i).setReturnQty(returnqty);
					System.out.println("return_qty  :"+returnqty);
				}
				else
				{
					billOfMaterialHeader.getBillOfMaterialDetailed().get(i).setReturnQty(0);
				}
				System.out.println(2);
			 
		}
		billOfMaterialHeader.setStatus(2);
		billOfMaterialHeader.setRejDate(date);
		billOfMaterialHeader.setRejUserId(userId);
		
		System.out.println(billOfMaterialHeader.toString());
		
		RestTemplate rest = new RestTemplate();
		
		Info info = rest.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);	
		System.out.println(info);
		
		return "redirect:/getBomList";
	}
	
	
	
	
	@RequestMapping(value = "/approveRejected", method = RequestMethod.GET)
	public String approveRejected(HttpServletRequest request, HttpServletResponse response) {
		
		
		System.out.println("inside Approve Rejected ");
		Date date= new Date();
		HttpSession session=request.getSession();
		UserResponse userResponse =(UserResponse) session.getAttribute("UserDetail");
		
		int reqId=Integer.parseInt(request.getParameter("reqId"));
		System.out.println(reqId);
		
		int userId=userResponse.getUser().getId();
		billOfMaterialHeader.setRejApproveDate(date);
		billOfMaterialHeader.setRejApproveUserId(userId);
		
		billOfMaterialHeader.setStatus(3);//3 for Approve Rejected 
		
		RestTemplate rest = new RestTemplate();
		
		Info info = rest.postForObject(Constants.url + "saveBom", billOfMaterialHeader, Info.class);	
		System.out.println(info.toString());
		
		return "redirect:/getBomList";
	}
	
	

	// commented code Sachin

	/*
	 * @RequestMapping(value = "/bbb", method = RequestMethod.GET) public
	 * ModelAndView showMixing(HttpServletRequest request, HttpServletResponse
	 * response) {
	 * 
	 * ModelAndView mav = new ModelAndView("production/addMixing");
	 * 
	 * try {
	 * 
	 * RestTemplate restTemplate = new RestTemplate();
	 * 
	 * MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,
	 * Object>();
	 * 
	 * map.add("headerId", 53);
	 * 
	 * getSFPlanDetailForMixingList = restTemplate.postForObject(Constants.url +
	 * "getSfPlanDetailForMixing", map, GetSFPlanDetailForMixingList.class);
	 * 
	 * sfPlanDetailForMixing =
	 * getSFPlanDetailForMixingList.getSfPlanDetailForMixing();
	 * 
	 * System.out.println("sf Plan Detail For Mixing  " +
	 * sfPlanDetailForMixing.toString());
	 * 
	 * TempMixing tempMx = null; for (int i = 0; i < sfPlanDetailForMixing.size();
	 * i++) {
	 * 
	 * GetSFPlanDetailForMixing planMixing = sfPlanDetailForMixing.get(i);
	 * 
	 * tempMx = new TempMixing();
	 * 
	 * tempMx.setQty(planMixing.getTotal());
	 * 
	 * tempMx.setRmId(planMixing.getRmId()); tempMx.setSfId(1);
	 * 
	 * tempMx.setProdHeaderId(53);
	 * 
	 * tempMixing.add(tempMx); }
	 * 
	 * System.out.println("temp Mix List " + tempMixing.toString());
	 * 
	 * Info info = restTemplate.postForObject(Constants.url + "insertTempMixing",
	 * tempMixing, Info.class);
	 * 
	 * map = new LinkedMultiValueMap<String, Object>();
	 * 
	 * map.add("prodHeaderId", 53);
	 * 
	 * getTempMixItemDetailList = restTemplate.postForObject(Constants.url +
	 * "getTempMixItemDetail", map, GetTempMixItemDetailList.class);
	 * 
	 * tempMixItemDetail = getTempMixItemDetailList.getTempMixItemDetail();
	 * 
	 * System.out.println("temp Mix Item Detail  " + tempMixItemDetail.toString());
	 * 
	 * // Calculations
	 * 
	 * boolean isSameItem = false; GetSFPlanDetailForMixing newItem = null;
	 * 
	 * for (int j = 0; j < tempMixItemDetail.size(); j++) {
	 * 
	 * GetTempMixItemDetail tempMixItem = tempMixItemDetail.get(j);
	 * 
	 * for (int i = 0; i < sfPlanDetailForMixing.size(); i++) {
	 * 
	 * GetSFPlanDetailForMixing planMixing = sfPlanDetailForMixing.get(i);
	 * 
	 * if (tempMixItem.getRmId() == planMixing.getRmId()) {
	 * 
	 * planMixing.setTotal(planMixing.getTotal() + tempMixItem.getTotal());
	 * 
	 * isSameItem = true;
	 * 
	 * } } if (isSameItem == false) {
	 * 
	 * newItem = new GetSFPlanDetailForMixing();
	 * 
	 * newItem.setRmName(tempMixItem.getRmName());
	 * newItem.setRmType(tempMixItem.getRmType());
	 * newItem.setRmId(tempMixItem.getSfId());
	 * newItem.setTotal(tempMixItem.getTotal());
	 * newItem.setUom(tempMixItem.getUom());
	 * 
	 * sfPlanDetailForMixing.add(newItem);
	 * 
	 * } }
	 * 
	 * System.out.println("Final List " + sfPlanDetailForMixing.toString());
	 * 
	 * mav.addObject("mixingList", sfPlanDetailForMixing); } catch (Exception e) {
	 * 
	 * e.printStackTrace(); System.out.println("Ex oc"); }
	 * 
	 * return mav;
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/bom", method = RequestMethod.GET) public
	 * ModelAndView showBom(HttpServletRequest request, HttpServletResponse
	 * response) {
	 * 
	 * ModelAndView mav = new ModelAndView("production/addBom");
	 * 
	 * try {
	 * 
	 * RestTemplate restTemplate = new RestTemplate();
	 * 
	 * MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,
	 * Object>();
	 * 
	 * map.add("headerId", 53);
	 * 
	 * getSFPlanDetailForBomList = restTemplate.postForObject(Constants.url +
	 * "getSfPlanDetailForBom", map, GetSFPlanDetailForMixingList.class);
	 * 
	 * sfPlanDetailForBom = getSFPlanDetailForBomList.getSfPlanDetailForMixing();
	 * 
	 * System.out.println("sf Plan Detail For Bom  " +
	 * sfPlanDetailForBom.toString());
	 * 
	 * GetSFMixingForBomList sFMixingForBomList;
	 * 
	 * List<GetSFMixingForBom> sFMixingForBom = new ArrayList<>();
	 * 
	 * map = new LinkedMultiValueMap<String, Object>();
	 * 
	 * map.add("mixingId", 1);
	 * 
	 * sFMixingForBomList = restTemplate.postForObject(Constants.url +
	 * "getSFMixingForBom", map, GetSFMixingForBomList.class);
	 * 
	 * sFMixingForBom = sFMixingForBomList.getsFMixingForBom();
	 * 
	 * System.out.println("sf Mixing Detail For Bom  " + sFMixingForBom.toString());
	 * mav.addObject("planDetailForBom", sfPlanDetailForBom); } catch (Exception e)
	 * {
	 * 
	 * e.printStackTrace(); System.out.println("Ex in bom"); } return mav; }
	 */

}