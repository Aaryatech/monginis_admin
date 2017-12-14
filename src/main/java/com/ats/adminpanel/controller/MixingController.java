package com.ats.adminpanel.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.Info;
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

@Controller
public class MixingController {

	// new Lists

	GetSFPlanDetailForMixingList getSFPlanDetailForMixingList;

	List<GetSFPlanDetailForMixing> sfPlanDetailForMixing = new ArrayList<>();

	GetTempMixItemDetailList getTempMixItemDetailList;

	List<GetTempMixItemDetail> tempMixItemDetail = new ArrayList<>();

	// temp Mix table beans
	TempMixingList tempMixingList;
	List<TempMixing> tempMixing = new ArrayList<>();

	GetSFPlanDetailForMixingList getSFPlanDetailForBomList;

	List<GetSFPlanDetailForMixing> sfPlanDetailForBom = new ArrayList<>();

	GetSFMixingForBomList sFMixingForBomList;

	List<GetSFMixingForBom> sFMixingForBom = new ArrayList<>();

	@RequestMapping(value = "/bbb", method = RequestMethod.GET)
	public ModelAndView showMixing(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("production/addMixing");

		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("headerId", 53);

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

				tempMx.setProdHeaderId(53);

				tempMixing.add(tempMx);
			}

			System.out.println("temp Mix List " + tempMixing.toString());

			Info info = restTemplate.postForObject(Constants.url + "insertTempMixing", tempMixing, Info.class);

			map = new LinkedMultiValueMap<String, Object>();

			map.add("prodHeaderId", 53);

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
				if (isSameItem == false) {

					newItem = new GetSFPlanDetailForMixing();

					newItem.setRmName(tempMixItem.getRmName());
					newItem.setRmType(tempMixItem.getRmType());
					newItem.setRmId(tempMixItem.getSfId());
					newItem.setTotal(tempMixItem.getTotal());
					newItem.setUom(tempMixItem.getUom());

					sfPlanDetailForMixing.add(newItem);

				}
			}

			System.out.println("Final List " + sfPlanDetailForMixing.toString());

			mav.addObject("mixingList", sfPlanDetailForMixing);
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Ex oc");
		}

		return mav;

	}

	@RequestMapping(value = "/bom", method = RequestMethod.GET)
	public ModelAndView showBom(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("production/addBom");

		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("headerId", 53);

			getSFPlanDetailForBomList = restTemplate.postForObject(Constants.url + "getSfPlanDetailForBom", map,
					GetSFPlanDetailForMixingList.class);

			sfPlanDetailForBom = getSFPlanDetailForBomList.getSfPlanDetailForMixing();

			System.out.println("sf Plan Detail For Bom  " + sfPlanDetailForBom.toString());

			GetSFMixingForBomList sFMixingForBomList;

			List<GetSFMixingForBom> sFMixingForBom = new ArrayList<>();

			map = new LinkedMultiValueMap<String, Object>();

			map.add("mixingId", 1);

			sFMixingForBomList = restTemplate.postForObject(Constants.url + "getSFMixingForBom", map,
					GetSFMixingForBomList.class);

			sFMixingForBom = sFMixingForBomList.getsFMixingForBom();

			System.out.println("sf Mixing Detail For Bom  " + sFMixingForBom.toString());
			mav.addObject("planDetailForBom", sfPlanDetailForBom);
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Ex in bom");
		}
		return mav;
	}

	@RequestMapping(value = "/showBom/{prodHeaderId}/{isMix}", method = RequestMethod.GET)
	public ModelAndView showBom2(@PathVariable int prodHeaderId, @PathVariable int isMix, HttpServletRequest request,
			HttpServletResponse response) {

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

				List<GetSFMixingForBom> sFMixingForBom = new ArrayList<>();

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

		String isMix = request.getParameter("isMix");
		System.out.println("isMix " + isMix);
		int isMixing = Integer.parseInt(isMix);
		System.out.println("inside insert Bom ");
		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			Date date = new Date();

			BillOfMaterialHeader billOfMaterialHeader = new BillOfMaterialHeader();

			billOfMaterialHeader.setApprovedDate(date);
			billOfMaterialHeader.setApprovedUserId(0);
			billOfMaterialHeader.setDelStatus(0);
			billOfMaterialHeader.setFromDeptId(0);
			billOfMaterialHeader.setFromDeptName("Prod");
			billOfMaterialHeader.setIsProduction(1);
			billOfMaterialHeader.setProductionDate(date);
			billOfMaterialHeader.setProductionId(53);
			billOfMaterialHeader.setReqDate(date);
			billOfMaterialHeader.setSenderUserid(0);
			billOfMaterialHeader.setStatus(0);
			billOfMaterialHeader.setToDeptId(0);
			billOfMaterialHeader.setToDeptName("BMS");

			List<BillOfMaterialDetailed> bomDetailList = new ArrayList<BillOfMaterialDetailed>();
			BillOfMaterialDetailed bomDetail = null;

			if (isMixing == 1) {

				for (int i = 0; i < sfPlanDetailForBom.size(); i++) {

					String editQty = request.getParameter("editQty" + i);
					bomDetail = new BillOfMaterialDetailed();

					System.out.println("editQty " + editQty);

					bomDetail.setDelStatus(0);
					bomDetail.setRmId(sfPlanDetailForBom.get(i).getRmId());
					bomDetail.setRmIssueQty(1.0F);
					bomDetail.setUom(sfPlanDetailForBom.get(i).getUom());
					bomDetail.setRmType(sfPlanDetailForBom.get(i).getRmType());
					bomDetail.setRmReqQty(sfPlanDetailForBom.get(i).getTotal());
					bomDetail.setRmName(sfPlanDetailForBom.get(i).getRmName());

					bomDetailList.add(bomDetail);

				}
			}

			else {

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

					bomDetailList.add(bomDetail);

				}

			}
			System.out.println("bom detail List " + bomDetailList.toString());
			billOfMaterialHeader.setBillOfMaterialDetailed(bomDetailList);

			System.out.println(" insert List " + billOfMaterialHeader.toString());
			
			  Info info = restTemplate.postForObject(Constants.url + "saveBom",
			  billOfMaterialHeader, Info.class);
			  
			  System.out.println("Info = "+info.toString());
			 
		} catch (Exception e) {
			e.printStackTrace();

		}
		return mav;
	}

}
