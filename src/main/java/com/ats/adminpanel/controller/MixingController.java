package com.ats.adminpanel.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.Info;

import com.ats.adminpanel.model.production.mixing.temp.GetSFPlanDetailForMixing;
import com.ats.adminpanel.model.production.mixing.temp.GetSFPlanDetailForMixingList;
import com.ats.adminpanel.model.production.mixing.temp.GetTempMixItemDetail;
import com.ats.adminpanel.model.production.mixing.temp.GetTempMixItemDetailList;
import com.ats.adminpanel.model.production.mixing.temp.TempMixing;
import com.ats.adminpanel.model.production.mixing.temp.TempMixingList;

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
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Ex oc");
		}

		return mav;

	}

	/*@RequestMapping(value = "/aaa", method = RequestMethod.GET)
	public ModelAndView viewMixing(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("message/addNewMessage");

		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			itemwisePlanDetailList = restTemplate.getForObject(Constants.url + "getItemwisePlanDetail",
					GetItemwisePlanDetailList.class);

			itemwisePlanDetail = itemwisePlanDetailList.getItemwisePlanDetail();

			System.out.println("itwm Wise Detail " + itemwisePlanDetail.toString());

			String itemIdList = new String();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < itemwisePlanDetail.size(); i++) {

				sb.append(String.valueOf(itemwisePlanDetail.get(i).getItemId()) + ",");

				itemIdList = new String(itemIdList + "," + itemwisePlanDetail.get(i).getItemId());
			}
			itemIdList = itemIdList.substring(1, itemIdList.length() - 1);

			System.out.println("item Id List " + itemIdList.toString());

			map.add("itemIdList", itemIdList);

			itemDetailForMixingList = restTemplate.postForObject(Constants.url + "getItemDetailForMixing", map,
					GetItemDetailForMixingList.class);

			itemDetailForMixing = itemDetailForMixingList.getItemDetailForMixing();

			System.out.println("item Mixing List " + itemDetailForMixing.toString());

			MixingItem mxItem = null;

			for (int j = 0; j < itemwisePlanDetail.size(); j++) {

				GetItemwisePlanDetail planDetail = itemwisePlanDetail.get(j);

				for (int i = 0; i < itemDetailForMixing.size(); i++) {

					GetItemDetailForMixing mixingDetail = itemDetailForMixing.get(i);

					if (mixingDetail.getItemId() == planDetail.getItemId()) {

						mxItem = new MixingItem();

						mxItem.setCalcQty(
								(planDetail.getTotal() * mixingDetail.getRmQty()) / mixingDetail.getNoPiecesPerItem());
						mxItem.setRmId(mixingDetail.getRmId());
						mxItem.setRmName(mixingDetail.getRmName());
						mxItem.setUom(mixingDetail.getRmUomId());

					}

				}

			}

			mixingItemList.add(mxItem);

			System.out.println("first Mixing Itel List " + mixingItemList.toString());

			map = new LinkedMultiValueMap<String, Object>();

			String sfIdList = new String();
			for (int i = 0; i < mixingItemList.size(); i++) {

				sfIdList = new String(sfIdList + "," + mixingItemList.get(i).getRmId());
			}

			sfIdList = sfIdList.substring(1, sfIdList.length() - 1);

			System.out.println("sfIdList Id List " + sfIdList.toString());

			map.add("sfIdList", sfIdList);

			sffDetailForMixingList = restTemplate.postForObject(Constants.url + "getSfDetailForMixing", map,
					GetSfDetailForMixingList.class);

			sfDetailForMixing = sffDetailForMixingList.getSfDetailForMixing();

			System.out.println("sf Detail For Mixing = " + sfDetailForMixing.toString());

			MixingItem mixingItem = new MixingItem();

			for (int i = 0; i < mixingItemList.size(); i++) {

				MixingItem mixItem = mixingItemList.get(i);

				for (int j = 0; j < sfDetailForMixing.size(); j++) {

					GetSfDetailForMixing sfDetail = sfDetailForMixing.get(j);

					if (mixItem.getRmId() == sfDetail.getSfId()) {

						mixingItem.setCalcQty(mixItem.getCalcQty() * sfDetail.getRmQty() * sfDetail.getRmWeight());

						mixingItem.setRmId(sfDetail.getRmId());

						mixingItem.setRmName(sfDetail.getRmName());

						mixingItem.setUom(sfDetail.getRmUnit());

					}

				}

			}

			mixingItemList.add(mixingItem);

			System.out.println("second Mixing Itel List " + mixingItemList.toString());

		} catch (Exception e) {

			System.out.println("	Ex occured	");
			e.printStackTrace();

		}

		return mav;
	}*/
}
