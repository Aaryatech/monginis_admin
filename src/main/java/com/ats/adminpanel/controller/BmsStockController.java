package com.ats.adminpanel.controller;

import java.awt.geom.CubicCurve2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.model.BmsStockDetailed;
import com.ats.adminpanel.model.BmsStockHeader;
import com.ats.adminpanel.model.RawMaterial.GetItemSfHeader;
import com.ats.adminpanel.model.item.FrItemStockConfigure;
import com.ats.adminpanel.model.item.FrItemStockConfigureList;
import com.ats.adminpanel.model.production.mixing.temp.GetSFPlanDetailForMixingList;
import com.ats.adminpanel.model.stock.GetBmsCurrentStock;
import com.ats.adminpanel.model.stock.GetBmsCurrentStockList;

@Controller
public class BmsStockController {

	GetBmsCurrentStockList bmsCurrentStockList;

	List<GetBmsCurrentStock> bmsCurrentStock = new ArrayList<>();
	List<BmsStockDetailed> stockBetDate = new ArrayList<>();

	@RequestMapping(value = "/showBmsStock", method = RequestMethod.GET)
	public ModelAndView showBmsStock(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("stock/bmsStock");

		/*
		 * MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,
		 * Object>(); RestTemplate restTemplate = new RestTemplate();
		 * 
		 * String settingKey = new String();
		 * 
		 * settingKey = "PROD" + "," + "MIX" + "," + "BMS" + "," + "STORE";
		 * 
		 * map.add("settingKeyList", settingKey);
		 * 
		 * FrItemStockConfigureList settingList =
		 * restTemplate.postForObject(Constants.url + "getDeptSettingValue", map,
		 * FrItemStockConfigureList.class);
		 * 
		 * System.out.println("SettingKeyList" + settingList.toString());
		 * 
		 * 
		 * int prodDeptId = 0, storeDeptId = 0, mixDeptId = 0;
		 * 
		 * List<FrItemStockConfigure> settingKeyList =
		 * settingList.getFrItemStockConfigure();
		 * 
		 * for (int i = 0; i < settingKeyList.size(); i++) {
		 * 
		 * if (settingKeyList.get(i).getSettingKey().equalsIgnoreCase("PROD")) {
		 * 
		 * prodDeptId = settingKeyList.get(i).getSettingValue();
		 * 
		 * } if (settingKeyList.get(i).getSettingKey().equalsIgnoreCase("Store")) {
		 * 
		 * storeDeptId = settingKeyList.get(i).getSettingValue();
		 * 
		 * } if (settingKeyList.get(i).getSettingKey().equalsIgnoreCase("Mix")) {
		 * 
		 * mixDeptId = settingKeyList.get(i).getSettingValue();
		 * 
		 * }
		 * 
		 * }
		 * 
		 * 
		 * System.out.println("Mix Dept Id "+mixDeptId);
		 * System.out.println("Prod Dept Id "+prodDeptId);
		 * 
		 * System.out.println("Store Dept Id "+storeDeptId);
		 * 
		 * map = new LinkedMultiValueMap<String, Object>(); map.add("prodDeptId",
		 * prodDeptId); map.add("storeDeptId", storeDeptId); map.add("mixDeptId",
		 * mixDeptId); bmsCurrentStockList=restTemplate.postForObject(Constants.url +
		 * "getCuurentBmsStock", map, GetBmsCurrentStockList.class);
		 * 
		 * 
		 * bmsCurrentStock=bmsCurrentStockList.getBmsCurrentStock();
		 * 
		 * mav.addObject("stockList",bmsCurrentStock);
		 * 
		 * System.out.println("BMS Stock List ="+bmsCurrentStockList.getBmsCurrentStock(
		 * ).toString());
		 */
		return mav;

	}

	@RequestMapping(value = "/getBmsStock", method = RequestMethod.POST)
	public ModelAndView getBmsStock(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("stock/bmsStock");

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		RestTemplate restTemplate = new RestTemplate();

		if (Integer.parseInt(request.getParameter("selectStock")) == 1) {

			String settingKey = new String();

			settingKey = "PROD" + "," + "MIX" + "," + "BMS" + "," + "STORE";

			map.add("settingKeyList", settingKey);

			FrItemStockConfigureList settingList = restTemplate.postForObject(Constants.url + "getDeptSettingValue",
					map, FrItemStockConfigureList.class);

			System.out.println("SettingKeyList" + settingList.toString());

			int prodDeptId = 0, storeDeptId = 0, mixDeptId = 0;

			List<FrItemStockConfigure> settingKeyList = settingList.getFrItemStockConfigure();

			for (int i = 0; i < settingKeyList.size(); i++) {

				if (settingKeyList.get(i).getSettingKey().equalsIgnoreCase("PROD")) {

					prodDeptId = settingKeyList.get(i).getSettingValue();

				}
				if (settingKeyList.get(i).getSettingKey().equalsIgnoreCase("Store")) {

					storeDeptId = settingKeyList.get(i).getSettingValue();

				}
				if (settingKeyList.get(i).getSettingKey().equalsIgnoreCase("Mix")) {

					mixDeptId = settingKeyList.get(i).getSettingValue();

				}

			}

			System.out.println("Mix Dept Id " + mixDeptId);
			System.out.println("Prod Dept Id " + prodDeptId);

			System.out.println("Store Dept Id " + storeDeptId);

			map = new LinkedMultiValueMap<String, Object>();
			map.add("prodDeptId", prodDeptId);
			map.add("storeDeptId", storeDeptId);
			map.add("mixDeptId", mixDeptId);
			map.add("rmType", 1);
			bmsCurrentStockList = restTemplate.postForObject(Constants.url + "getCuurentBmsStock", map,
					GetBmsCurrentStockList.class);

			bmsCurrentStock = bmsCurrentStockList.getBmsCurrentStock();

			mav.addObject("stockList", bmsCurrentStock);

			System.out.println("BMS Stock List Before Adding =" + bmsCurrentStockList.getBmsCurrentStock().toString());

			List<BmsStockDetailed> stockBetDate = new ArrayList<>();
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); // Now use today date.
		
			String curDate = sdf.format(c.getTime());
			
			System.out.println("curDate " + curDate);

			map.add("fromDate", curDate);
			map.add("toDate", curDate);
			map.add("rmType", 1);

			ParameterizedTypeReference<List<BmsStockDetailed>> typeRef = new ParameterizedTypeReference<List<BmsStockDetailed>>() {
			};
			ResponseEntity<List<BmsStockDetailed>> responseEntity = restTemplate.exchange(Constants.url + "getBmsStock",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);

			stockBetDate = responseEntity.getBody();

			System.out.println("Bom Detailed bet date \n =" + stockBetDate.toString());

			GetBmsCurrentStock curStk = null;
			BmsStockDetailed stkBetDate = null;
			boolean isSameItem = false;

			for (int j = 0; j < bmsCurrentStock.size(); j++) {

				curStk = bmsCurrentStock.get(j);

				for (int i = 0; i < stockBetDate.size(); i++) {
					stkBetDate = stockBetDate.get(i);

					if (stkBetDate.getRmId() == curStk.getRmId()) {

						isSameItem = true;
					}
				}

				if (isSameItem == true) {

					curStk.setOpeningQty(stkBetDate.getBmsOpeningStock());

					float closingQty = curStk.getOpeningQty() + stkBetDate.getStoreRecQty()
							+ stkBetDate.getProdReturnQty() + stkBetDate.getMixingReturnQty()
							- (stkBetDate.getProdIssueQty() + stkBetDate.getMixingIssueQty()
									+ stkBetDate.getStoreRejectedQty());

					curStk.setClosingQty(closingQty);

					bmsCurrentStock.set(j, curStk);

				}

			}

		} // end of if getCurrentStock

	if (Integer.parseInt(request.getParameter("selectStock")) == 3) {

			System.out.println("Inside Else stock btw Date ");

			bmsCurrentStock = new ArrayList<>();

			
			  String fromStockdate=request.getParameter("from_datepicker");
			  
			  String toStockdate = request.getParameter("to_datepicker");
			 
			 int rmType=Integer.parseInt(request.getParameter("matType"));
			 
			 System.out.println("from Date "+DateConvertor.convertToYMD(fromStockdate)+"\n Todate "+DateConvertor.convertToYMD(toStockdate)+"\n RM type  "+rmType);
			 

			map.add("fromDate", DateConvertor.convertToYMD(fromStockdate));
			map.add("toDate", DateConvertor.convertToYMD(toStockdate));
			map.add("rmType", rmType);

			List<BmsStockDetailed> stockBetDate = new ArrayList<>();
			
			/*map.add("fromDate", "2017-12-15");
			map.add("toDate", "2017-12-16");
			map.add("rmType", 1);
*/
			ParameterizedTypeReference<List<BmsStockDetailed>> typeRef = new ParameterizedTypeReference<List<BmsStockDetailed>>() {
			};
			ResponseEntity<List<BmsStockDetailed>> responseEntity = restTemplate.exchange(Constants.url + "getBmsStock",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);

			stockBetDate = responseEntity.getBody();

			System.out.println("else SBD " + stockBetDate.toString());
			GetBmsCurrentStock currentStock = null;

			for (int i = 0; i < stockBetDate.size(); i++) {

				System.out.println("Inside For Loop");

				currentStock = new GetBmsCurrentStock();

				BmsStockDetailed stkDetail = stockBetDate.get(i);

				currentStock.setClosingQty(stkDetail.getClosingQty());
				currentStock.setMixing_issue_qty(stkDetail.getMixingIssueQty());
				currentStock.setMixing_rejected_qty(stkDetail.getMixingRejected());
				currentStock.setMixing_return_qty(stkDetail.getMixingReturnQty());
				currentStock.setOpeningQty(stkDetail.getBmsOpeningStock());
				currentStock.setProd_issue_qty(stkDetail.getProdIssueQty());
				currentStock.setProd_rejected_qty(stkDetail.getProdRejectedQty());
				currentStock.setProd_return_qty(stkDetail.getProdReturnQty());
				currentStock.setRmId(stkDetail.getRmId());
				currentStock.setRmName(stkDetail.getRmName());
				currentStock.setRmUomId(stkDetail.getRmUom());
				currentStock.setStore_issue_qty(stkDetail.getStoreRecQty());
				currentStock.setStore_rejected_qty(stkDetail.getStoreRejectedQty());

				bmsCurrentStock.add(currentStock);
			}

		} // end of else Get Stock Between two Date
		System.out.println("New List After Adding closing and Opening Qty \n " + bmsCurrentStock.toString());

		mav.addObject("stockList", bmsCurrentStock);
		return mav;
	}

	// Insert BMS Stock;

	@RequestMapping(value = "/dayEndProcess", method = RequestMethod.POST)
	public ModelAndView dayEndProcess(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("stock/bmsStock");

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();

			System.out.println(" Inside day End Process ");

			Date date = new Date();
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); // Now use today date.
			c.add(Calendar.DATE, 1); // Adding 1 day
			String output = sdf.format(c.getTime());
			System.out.println(output);
			Date stockDate=sdf.parse(output);
			System.out.println("new Date "+stockDate);


			BmsStockHeader bmsStockHeader = new BmsStockHeader();

			bmsStockHeader.setBmsStockId(0);
			bmsStockHeader.setBmsStockDate(stockDate);
			bmsStockHeader.setBmsStatus(0);
			bmsStockHeader.setRmType(2);
			bmsStockHeader.setExInt(0);
			bmsStockHeader.setExInt1(0);
			bmsStockHeader.setExBoll(0);
			bmsStockHeader.setExBoll1(0);
			bmsStockHeader.setExVarchar("");

			List<BmsStockDetailed> bmsStockDetailedlist = new ArrayList<BmsStockDetailed>();

			for (int i = 0; i < bmsCurrentStock.size(); i++) {
				BmsStockDetailed bmsStockDetailed = new BmsStockDetailed();
				// bmsStockDetailed.setBmsStockId(0);
				bmsStockDetailed.setBmsStockDate(stockDate);
				bmsStockHeader.setRmType(1);
				bmsStockDetailed.setRmId(bmsCurrentStock.get(i).getRmId());
				bmsStockDetailed.setRmName(bmsCurrentStock.get(i).getRmName());
				bmsStockDetailed.setBmsOpeningStock(bmsCurrentStock.get(i).getClosingQty());
				bmsStockDetailed.setRmType(1);
				bmsStockDetailed.setRmUom(bmsCurrentStock.get(i).getRmId());

				bmsStockDetailedlist.add(bmsStockDetailed);

			}
			bmsStockHeader.setBmsStockDetailed(bmsStockDetailedlist);

			
			 bmsStockHeader = restTemplate.postForObject(Constants.url +
			 "insertBmsStock",bmsStockHeader,BmsStockHeader.class);
		} catch (Exception e) {

			System.out.println("Error iN Inserting Day End Stock Process " + e.getMessage());
			e.printStackTrace();
		}

		return mav;

	}

}
