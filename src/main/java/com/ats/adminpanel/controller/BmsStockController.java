package com.ats.adminpanel.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.zefer.html.doc.s;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.model.BmsStockDetailed;
import com.ats.adminpanel.model.BmsStockHeader;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.item.FrItemStockConfigure;
import com.ats.adminpanel.model.item.FrItemStockConfigureList;
import com.ats.adminpanel.model.stock.GetBmsCurrentStock;
import com.ats.adminpanel.model.stock.GetBmsCurrentStockList;
import com.ats.adminpanel.model.stock.GetCurrentBmsSFStock;
import com.ats.adminpanel.model.stock.GetCurrentBmsSFStockList;
import com.ats.adminpanel.model.stock.UpdateBmsSfStock;
import com.ats.adminpanel.model.stock.UpdateBmsSfStockList;
import com.ats.adminpanel.model.stock.UpdateBmsStock;
import com.ats.adminpanel.model.stock.UpdateBmsStockList;
import com.itextpdf.awt.geom.CubicCurve2D;

@Controller
@Scope("session")
public class BmsStockController {

	GetBmsCurrentStockList bmsCurrentStockList;

	GetCurrentBmsSFStockList currentBmsSFStockList;

	List<GetBmsCurrentStock> bmsCurrentStock, bmsRmStockBetDate = new ArrayList<>();

	List<GetCurrentBmsSFStock> bmsCurrentStockSf, bmsSfStockBetDate = new ArrayList<>();

	List<BmsStockDetailed> stockBetDate = new ArrayList<>();

	private List<BmsStockDetailed> bmsStockDetailedList;

	Date globalHeaderDate;

	int globalRmType;

	int globalBmsHeaderId;

	@RequestMapping(value = "/showBmsStock", method = RequestMethod.GET)
	public ModelAndView showBmsStock(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("stock/bmsStock");
		System.out.println("inside show BMS stock page ");

		Constants.mainAct = 8;
		Constants.subAct = 48;
		return mav;

	}

	@RequestMapping(value = "/getBmsStock", method = RequestMethod.POST)
	public ModelAndView getBmsStock(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("inside get Bms Stock Page ");
		ModelAndView mav = new ModelAndView("stock/bmsStock");
		System.out.println("inside get BMS Stock Page  ");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			// if getCurrentStock
			if (Integer.parseInt(request.getParameter("selectStock")) == 1) {

				System.out.println("inside If  get Current Stock is selected   ");

				int rmType = Integer.parseInt(request.getParameter("matType"));

				if (rmType == 1) {

					System.out.println("It is Current Stock Of RM ");

					globalRmType = rmType;

					String settingKey = new String();

					settingKey = "PROD" + "," + "MIX" + "," + "BMS";
					map = new LinkedMultiValueMap<String, Object>();

					map.add("settingKeyList", settingKey);

					FrItemStockConfigureList settingList = restTemplate
							.postForObject(Constants.url + "getDeptSettingValue", map, FrItemStockConfigureList.class);

					System.out.println("SettingKeyList" + settingList.toString());

					int prodDeptId = 0, bmsDeptId = 0, mixDeptId = 0;

					List<FrItemStockConfigure> settingKeyList = settingList.getFrItemStockConfigure();

					for (int i = 0; i < settingKeyList.size(); i++) {

						if (settingKeyList.get(i).getSettingKey().equalsIgnoreCase("PROD")) {

							prodDeptId = settingKeyList.get(i).getSettingValue();

						}
						if (settingKeyList.get(i).getSettingKey().equalsIgnoreCase("BMS")) {

							bmsDeptId = settingKeyList.get(i).getSettingValue();

						}
						if (settingKeyList.get(i).getSettingKey().equalsIgnoreCase("Mix")) {

							mixDeptId = settingKeyList.get(i).getSettingValue();

						}

					}

					System.out.println("Mix Dept Id " + mixDeptId);
					System.out.println("Prod Dept Id " + prodDeptId);

					System.out.println("BMS Dept Id " + bmsDeptId);
					map = new LinkedMultiValueMap<String, Object>();

					map = new LinkedMultiValueMap<String, Object>();
					map.add("prodDeptId", prodDeptId);
					map.add("bmsDeptId", bmsDeptId);
					map.add("mixDeptId", mixDeptId);
					// map.add("rmType", rmType);

					bmsCurrentStockList = restTemplate.postForObject(Constants.url + "getCurentBmsStockRM", map,
							GetBmsCurrentStockList.class);

					bmsCurrentStock = bmsCurrentStockList.getBmsCurrentStock();
					GetBmsCurrentStock stock = new GetBmsCurrentStock();
					for (int i = 0; i < bmsCurrentStock.size(); i++) {
						stock = new GetBmsCurrentStock();
						stock = bmsCurrentStock.get(i);
						bmsCurrentStock.get(i).setBmsClosingStock(0);
						bmsCurrentStock.get(i).setBmsClosingStock(
								((stock.getBmsOpeningStock() + stock.getProdReturnQty() + stock.getStoreIssueQty())
										- (stock.getProdIssueQty() + stock.getMixingIssueQty())));

					}

					mav.addObject("isRm", String.valueOf(1));
					mav.addObject("stockList", bmsCurrentStock);

				} // end of if rmtype==1 ie RM
				
				
				
				else if (rmType == 2) {
					System.out.println("It is Current Stock Of RM ");

					System.out.println("get SF Current Stock");
					globalRmType = rmType;

					String settingKey = new String();

					settingKey = "PROD";
					map = new LinkedMultiValueMap<String, Object>();

					map.add("settingKeyList", settingKey);

					FrItemStockConfigureList settingList = restTemplate
							.postForObject(Constants.url + "getDeptSettingValue", map, FrItemStockConfigureList.class);

					System.out.println("SettingKeyList" + settingList.toString());

					int prodDeptId = 0;

					List<FrItemStockConfigure> settingKeyList = settingList.getFrItemStockConfigure();

					prodDeptId = settingKeyList.get(0).getSettingValue();

					System.out.println("Prod Dept Id " + prodDeptId);

					map = new LinkedMultiValueMap<String, Object>();
					map.add("prodDeptId", prodDeptId);

					// map.add("rmType", rmType);

					currentBmsSFStockList = restTemplate.postForObject(Constants.url + "getCurentBmsStockSF", map,
							GetCurrentBmsSFStockList.class);
					bmsCurrentStockSf = new ArrayList<>();
					bmsCurrentStockSf = currentBmsSFStockList.getCurrentBmsSFStock();
					System.out.println("Sf current Stock List " + bmsCurrentStockSf.toString());

					GetCurrentBmsSFStock stock;
					for (int i = 0; i < bmsCurrentStockSf.size(); i++) {
						stock = new GetCurrentBmsSFStock();
						stock = bmsCurrentStockSf.get(i);
						bmsCurrentStockSf.get(i).setBmsClosingStock(0);
						bmsCurrentStockSf.get(i).setBmsClosingStock(
								((stock.getBmsOpeningStock() + stock.getMixingIssueQty() + stock.getProdReturnQty())
										- stock.getProdIssueQty()));

					}
					mav.addObject("stockList", bmsCurrentStockSf);
					mav.addObject("isRm", String.valueOf(2));

				} // end of else if rmType==2 ie SF
				/*
				 * System.out.println("BMS Stock List Before Adding =" +
				 * bmsCurrentStockList.getBmsCurrentStock().toString());
				 * 
				 * List<BmsStockDetailed> stockBetDate = new ArrayList<>();
				 * 
				 * SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); Calendar c =
				 * Calendar.getInstance(); c.setTime(new Date()); // Now use today date.
				 * 
				 * String curDate = sdf.format(c.getTime());
				 * 
				 * System.out.println("curDate for Stock bet date to get current Stock " +
				 * DateConvertor.convertToYMD(curDate));
				 * 
				 * map = new LinkedMultiValueMap<String, Object>();
				 * 
				 * map.add("fromDate", DateConvertor.convertToYMD(curDate)); map.add("toDate",
				 * DateConvertor.convertToYMD(curDate)); map.add("rmType", rmType);
				 * 
				 * ParameterizedTypeReference<List<BmsStockDetailed>> typeRef = new
				 * ParameterizedTypeReference<List<BmsStockDetailed>>() { };
				 * ResponseEntity<List<BmsStockDetailed>> responseEntity =
				 * restTemplate.exchange(Constants.url + "getBmsStock", HttpMethod.POST, new
				 * HttpEntity<>(map), typeRef);
				 * 
				 * stockBetDate = responseEntity.getBody();
				 * 
				 * System.out.println("BMS Stock Bet Date  date \n =" +
				 * stockBetDate.toString());
				 * 
				 * GetBmsCurrentStock curStk = new GetBmsCurrentStock(); BmsStockDetailed
				 * stkBetDate = new BmsStockDetailed(); boolean isSameItem = false;
				 * 
				 * for (int j = 0; j < bmsCurrentStock.size(); j++) {
				 * 
				 * curStk = bmsCurrentStock.get(j);
				 * 
				 * for (int i = 0; i < stockBetDate.size(); i++) { stkBetDate =
				 * stockBetDate.get(i);
				 * 
				 * if (stkBetDate.getRmId() == curStk.getRmId()) {
				 * 
				 * isSameItem = true; curStk.setOpeningQty(stkBetDate.getBmsOpeningStock());
				 * 
				 * System.out.println("Opening qty set for Item "+curStk.getRmName()+"Qty "
				 * +stkBetDate.getBmsOpeningStock());
				 * 
				 * float closingQty = curStk.getOpeningQty() +curStk.getStore_issue_qty()+
				 * curStk.getProd_return_qty()+curStk.getMixing_return_qty()-
				 * (curStk.getProd_issue_qty()+curStk.getMixing_issue_qty()+curStk.
				 * getStore_rejected_qty());
				 * 
				 * curStk.setClosingQty(closingQty);
				 * 
				 * bmsCurrentStock.set(j, curStk);
				 * 
				 * } }
				 * 
				 * if (isSameItem == true) {
				 * 
				 * curStk.setOpeningQty(stkBetDate.getBmsOpeningStock());
				 * 
				 * float closingQty = curStk.getOpeningQty() +curStk.getStore_issue_qty()+
				 * curStk.getProd_return_qty()+curStk.getMixing_return_qty()-
				 * (curStk.getProd_issue_qty()+curStk.getMixing_issue_qty()+curStk.
				 * getStore_rejected_qty());
				 * 
				 * curStk.setClosingQty(closingQty);
				 * 
				 * bmsCurrentStock.set(j, curStk);
				 * 
				 * }
				 * 
				 * }
				 */
				Date cDate = new Date();

				Date headerDate = new Date();

				map = new LinkedMultiValueMap<String, Object>();

				map.add("status", 0);

				map.add("rmType", globalRmType);

				BmsStockHeader bmsStockHeader = restTemplate.postForObject(Constants.url + "getBmsStockHeader", map,
						BmsStockHeader.class);

				if (bmsStockHeader != null) {
					headerDate = bmsStockHeader.getBmsStockDate();

				}

				globalHeaderDate = bmsStockHeader.getBmsStockDate();
				
				SimpleDateFormat sdF=new SimpleDateFormat("yyyy-MM-dd");
				
				String globalDate=sdF.format(globalHeaderDate);
				
				globalHeaderDate=sdF.parse(globalDate);
				
				System.out.println("Global Header Date "+globalHeaderDate);

				globalBmsHeaderId = bmsStockHeader.getBmsStockId();

				int showDayEnd = 0;
				if (headerDate.before(cDate) || headerDate.equals(cDate)) {

					System.out.println("Day End Dates Header Date " + headerDate + "curDate " + cDate);

					showDayEnd = 1;

				} else {
					showDayEnd = 0;

					System.out.println("Day End Dates Header Date " + headerDate + "curDate " + cDate);

				}

				System.out.println("show day end " + showDayEnd);
				mav.addObject("showDayEnd", showDayEnd);

			} // end of if getCurrentStock

			if (Integer.parseInt(request.getParameter("selectStock")) == 3) {
				System.out.println("Stock BET DATE");

				int rmType = Integer.parseInt(request.getParameter("matType"));

				globalRmType = rmType;

				if (globalRmType == 1) {
					// get RM Stock bet Date
					System.out.println("It is RM Stock Bet Two Dates ");
					String fromStockdate = request.getParameter("from_datepicker");
					String toStockdate = request.getParameter("to_datepicker");
					map = new LinkedMultiValueMap<String, Object>();

					map.add("fromDate", fromStockdate);
					map.add("toDate", toStockdate);
					bmsCurrentStockList = restTemplate.postForObject(Constants.url + "getBmsStockRMBetDate", map,
							GetBmsCurrentStockList.class);

					bmsRmStockBetDate = new ArrayList<>();
					bmsRmStockBetDate = bmsCurrentStockList.getBmsCurrentStock();
					mav.addObject("isRm", String.valueOf(1));

					mav.addObject("stockList", bmsRmStockBetDate);
				}

				else if (globalRmType == 2) {
					// get SF Stock Bet Date

					System.out.println("It is SF Stock Betw Date");
					String fromStockdate = request.getParameter("from_datepicker");
					String toStockdate = request.getParameter("to_datepicker");
					map = new LinkedMultiValueMap<String, Object>();

					map.add("fromDate", fromStockdate);
					map.add("toDate", toStockdate);

					map.add("fromDate", fromStockdate);
					map.add("toDate", toStockdate);

					// map.add("rmType", rmType);

					currentBmsSFStockList = restTemplate.postForObject(Constants.url + "getBmsStockSFBetDate", map,
							GetCurrentBmsSFStockList.class);
					bmsSfStockBetDate = new ArrayList<>();
					bmsSfStockBetDate = currentBmsSFStockList.getCurrentBmsSFStock();
					System.out.println("Sf current Stock List " + bmsCurrentStockSf.toString());
					mav.addObject("isRm", String.valueOf(2));
					mav.addObject("stockList", bmsSfStockBetDate);

				}
				/*
				 * System.out.println("Inside Else stock btw Date ");
				 * 
				 * bmsCurrentStock = new ArrayList<>();
				 * 
				 * String fromStockdate = request.getParameter("from_datepicker");
				 * 
				 * String toStockdate = request.getParameter("to_datepicker");
				 * 
				 * int rmType = Integer.parseInt(request.getParameter("matType"));
				 * 
				 * globalRmType=rmType;
				 * 
				 * System.out.println("from Date " + DateConvertor.convertToYMD(fromStockdate) +
				 * "\n Todate " + DateConvertor.convertToYMD(toStockdate) + "\n RM type  " +
				 * rmType);
				 * 
				 * DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd-MM-uuuu" ); LocalDate
				 * tDate = LocalDate.parse(toStockdate, f);
				 * 
				 * if(tDate.isAfter(LocalDate.now()) || tDate.isEqual(LocalDate.now())){
				 * System.out.println("   Date is greater than today"+LocalDate.now().minus(
				 * Period.ofDays(1))); tDate=LocalDate.now().minus(Period.ofDays(1));
				 * 
				 * } map = new LinkedMultiValueMap<String, Object>();
				 * 
				 * map.add("fromDate", DateConvertor.convertToYMD(fromStockdate));
				 * map.add("toDate", ""+ DateConvertor.convertToYMD(toStockdate));
				 * map.add("rmType", rmType); map.add("bmsStatus", 1);
				 * 
				 * 
				 * List<BmsStockDetailed> stockBetDate = new ArrayList<>();
				 * 
				 * ParameterizedTypeReference<List<BmsStockDetailed>> typeRef = new
				 * ParameterizedTypeReference<List<BmsStockDetailed>>() { };
				 * ResponseEntity<List<BmsStockDetailed>> responseEntity =
				 * restTemplate.exchange(Constants.url + "getBmsStockBetDateMonth",
				 * HttpMethod.POST, new HttpEntity<>(map), typeRef);
				 * 
				 * stockBetDate = responseEntity.getBody();
				 * 
				 * System.out.println("else SBD " + stockBetDate.toString()); GetBmsCurrentStock
				 * currentStock = null;
				 * 
				 * for (int i = 0; i < stockBetDate.size(); i++) {
				 * 
				 * System.out.println("Inside For Loop");
				 * 
				 * currentStock = new GetBmsCurrentStock();
				 * 
				 * BmsStockDetailed stkDetail = stockBetDate.get(i);
				 * 
				 * currentStock.setClosingQty(stkDetail.getClosingQty());
				 * currentStock.setMixing_issue_qty(stkDetail.getMixingIssueQty());
				 * currentStock.setMixing_rejected_qty(stkDetail.getMixingRejected());
				 * currentStock.setMixing_return_qty(stkDetail.getMixingReturnQty());
				 * currentStock.setOpeningQty(stkDetail.getBmsOpeningStock());
				 * currentStock.setProd_issue_qty(stkDetail.getProdIssueQty());
				 * currentStock.setProd_rejected_qty(stkDetail.getProdRejectedQty());
				 * currentStock.setProd_return_qty(stkDetail.getProdReturnQty());
				 * currentStock.setRmId(stkDetail.getRmId());
				 * currentStock.setRmName(stkDetail.getRmName());
				 * currentStock.setRmUomId(stkDetail.getRmUom());
				 * currentStock.setStore_issue_qty(stkDetail.getStoreRecQty());
				 * currentStock.setStore_rejected_qty(stkDetail.getStoreRejectedQty());
				 * 
				 * 
				 * bmsCurrentStock.add(currentStock); } mav.addObject("stockList",
				 * bmsCurrentStock);
				 */
			} // end of else Get Stock Between two Date
				// System.out.println("New List After Adding closing and Opening Qty \n " +
				// bmsCurrentStock.toString());

		} catch (Exception e) {

			System.out.println("Exce in Getting Stock BMS stock " + e.getMessage());

			e.printStackTrace();
		}
		return mav;

	}

	@RequestMapping(value = "/dayEndProcess", method = RequestMethod.POST)
	public ModelAndView dayEndProcess(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("stock/bmsStock");

		try {

			BmsStockHeader bmsStockHeader = new BmsStockHeader();
			
			BmsStockHeader bmsStockHeaderResponse = new BmsStockHeader();
			List<BmsStockDetailed> stokDetailList = new ArrayList<>();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();

			System.out.println(" Inside day End Process ");

			// Update bms stock Day end Process

			// map.add("fromDate", new
			// SimpleDateFormat("yyyy-MM-dd").format(globalHeaderDate));
			// map.add("toDate", new
			// SimpleDateFormat("yyyy-MM-dd").format(globalHeaderDate));
			// map.add("rmType", globalRmType);

			//map.add("bmsStockId", globalBmsHeaderId);
			/*System.out.println("Global bms Header Id " + globalBmsHeaderId);

			ParameterizedTypeReference<List<BmsStockDetailed>> typeRef = new ParameterizedTypeReference<List<BmsStockDetailed>>() {
			};
			ResponseEntity<List<BmsStockDetailed>> responseEntity = restTemplate
					.exchange(Constants.url + "getBmsStockDetail", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			bmsStockDetailedList = responseEntity.getBody();

			System.out.println("bms Details " + bmsStockDetailedList.toString());*/

			if (globalRmType == 1) {

				System.out.println("current list size " + bmsCurrentStockList.getBmsCurrentStock().size());

				//System.out.println("Detail List Size " + bmsStockDetailedList.size());
				
				 
				
				
				UpdateBmsStockList rmUpdate=new UpdateBmsStockList();
				
				List<UpdateBmsStock> rmStockList= new  ArrayList<>();
			
					for (int j = 0; j < bmsCurrentStockList.getBmsCurrentStock().size(); j++) {

					
							//System.out.println("RM id Matched ");

							GetBmsCurrentStock getBmsCurrentStock = bmsCurrentStockList.getBmsCurrentStock().get(j);
							
							//GetCurrentBmsSFStock curStock=bmsCurrentStockSf.get(j);
							UpdateBmsStock rmStock=new UpdateBmsStock();
							
							rmStock.setBmsClosingStock(getBmsCurrentStock.getBmsClosingStock());
							rmStock.setProdIssueQty(getBmsCurrentStock.getProdIssueQty());
							rmStock.setProdRejectedQty(getBmsCurrentStock.getProdRejectedQty());
							rmStock.setProdReturnQty(getBmsCurrentStock.getProdReturnQty());
							rmStock.setMixingIssueQty(getBmsCurrentStock.getMixingIssueQty());
							rmStock.setMixingRejectedQty(getBmsCurrentStock.getMixingRejectedQty());
							rmStock.setMixingReturnQty(getBmsCurrentStock.getMixingReturnQty());
							rmStock.setStoreIssueQty(getBmsCurrentStock.getStoreIssueQty());
							rmStock.setStoreRejectedQty(getBmsCurrentStock.getStoreRejectedQty());
							rmStock.setRmId(getBmsCurrentStock.getRmId());
							rmStock.setMixingReceiveRejectedQty(0);
							rmStock.setMixingRecQty(0);
							rmStock.setBmsStockId(globalBmsHeaderId);
							
							///
							
							rmStockList.add(rmStock);
							map = new LinkedMultiValueMap<String, Object>();
							map.add("prodIssueQty", getBmsCurrentStock.getProdIssueQty());
							map.add("prodRejectedQty", getBmsCurrentStock.getProdRejectedQty());
							map.add("prodReturnQty", getBmsCurrentStock.getProdReturnQty());
							map.add("mixingIssueQty", getBmsCurrentStock.getMixingIssueQty());
							map.add("mixingRejectedQty", getBmsCurrentStock.getMixingRejectedQty());
							map.add("mixingReturnQty", getBmsCurrentStock.getMixingReturnQty());
							map.add("storeIssueQty", getBmsCurrentStock.getStoreIssueQty());
							map.add("storeRejectedQty", getBmsCurrentStock.getStoreRejectedQty());
							map.add("bmsClosingStock", getBmsCurrentStock.getBmsClosingStock());
							map.add("rmId", getBmsCurrentStock.getRmId());
							map.add("mixingReceiveRejectedQty", 0);
							map.add("mixingRecQty", 0);
							map.add("bmsStockId", globalBmsHeaderId);

						
				}
					
					rmUpdate.setBmsStock(rmStockList);

					Info updateBmsRmStockResponse=restTemplate.postForObject(Constants.url+"updateBmsStockForRM", rmUpdate, Info.class);
					System.out.println("First response BMS Update "+updateBmsRmStockResponse);

					
				//System.out.println("Stock Detail List new For Day End " + stokDetailList.toString());

			//	bmsStockHeader.setBmsStockDetailed(stokDetailList);
					
					System.out.println("Bms Id for BMS  rm Update "+globalBmsHeaderId);

				bmsStockHeader.setBmsStockId(globalBmsHeaderId);
				
				bmsStockHeader.setBmsStatus(1);
				
				bmsStockHeader.setBmsStockDate(globalHeaderDate);
				
				bmsStockHeader.setRmType(1);
				bmsStockHeader.setExInt(0);
				bmsStockHeader.setExInt1(0);
				bmsStockHeader.setExBoll(0);
				bmsStockHeader.setExBoll1(0);
				bmsStockHeader.setExVarchar(" ");
			/*	bmsStockHeader.setBmsStockDate(bmsStockDetailedList.get(0).getBmsStockDate());
				bmsStockHeader.setBmsStatus(1);
				bmsStockHeader.setRmType(bmsStockDetailedList.get(0).getRmType());
				bmsStockHeader.setExInt(0);
				bmsStockHeader.setExInt1(0);
				bmsStockHeader.setExBoll(0);
				bmsStockHeader.setExBoll1(0);
				bmsStockHeader.setExVarchar("");*/

				bmsStockHeaderResponse = restTemplate.postForObject(Constants.url + "insertBmsStock",
				 bmsStockHeader, BmsStockHeader.class);// end of update bms stock Day end Process

				 System.out.println("bsm RM Stock Header Update Response"+bmsStockHeaderResponse.toString());

				//Inserting next Day Entry for BMS Stock//
				System.out.println("Inserting next day stock Entry for BMS Stock RM");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Calendar c = Calendar.getInstance();
				c.setTime(globalHeaderDate); // Now use today date.
				c.add(Calendar.DATE, 1); // Adding 1 day
				String output = sdf.format(c.getTime());
				System.out.println(output);
				Date stockDate = sdf.parse(output);
				System.out.println("new Date " + stockDate);

				BmsStockHeader bmsStockHeaderInsert = new BmsStockHeader();

				bmsStockHeaderInsert.setBmsStockId(0);
				bmsStockHeaderInsert.setBmsStockDate(stockDate);
				bmsStockHeaderInsert.setBmsStatus(0);
				bmsStockHeaderInsert.setRmType(globalRmType);
				bmsStockHeaderInsert.setExInt(0);
				bmsStockHeaderInsert.setExInt1(0);
				bmsStockHeaderInsert.setExBoll(0);
				bmsStockHeaderInsert.setExBoll1(0);
				bmsStockHeaderInsert.setExVarchar("");

				List<BmsStockDetailed> bmsStockDetailedlist = new ArrayList<BmsStockDetailed>();

				for (int i = 0; i <  bmsCurrentStockList.getBmsCurrentStock().size(); i++) {

					BmsStockDetailed bmsStockDetailed = new BmsStockDetailed();
					
					 GetBmsCurrentStock curStock=bmsCurrentStockList.getBmsCurrentStock().get(i);
					 
					 
					// bmsStockDetailed.setBmsStockId(0);
					bmsStockDetailed.setBmsStockDate(stockDate);
					// bmsStockHeader.setRmType(1);
					bmsStockDetailed.setRmId(curStock.getRmId());
					bmsStockDetailed.setRmName(curStock.getRmName());
					bmsStockDetailed.setBmsOpeningStock(curStock.getBmsClosingStock());
					bmsStockDetailed.setRmType(globalRmType);
					bmsStockDetailed.setRmUom(curStock.getRmUomId());
					bmsStockDetailedlist.add(bmsStockDetailed);

				}
				bmsStockHeaderInsert.setBmsStockDetailed(bmsStockDetailedlist);

				bmsStockHeader = new BmsStockHeader();

				bmsStockHeader = restTemplate.postForObject(Constants.url + "insertBmsStock",bmsStockHeaderInsert,BmsStockHeader.class);// End of inserting BMS Stock for Next Day

				System.out.println("bsm RM Stock Header Insert Response"+bmsStockHeader.toString());

				
				
				
			} else if (globalRmType == 2) {
				
				UpdateBmsSfStockList sfUpdate=new UpdateBmsSfStockList();
				
				List<UpdateBmsSfStock> sfStockList= new  ArrayList<>();
				// day end for Sf Stock
				System.out.println("Day End For SF ");

			
					for (int j = 0; j < bmsCurrentStockSf.size(); j++) {
						
							
						GetCurrentBmsSFStock curStock=bmsCurrentStockSf.get(j);
						UpdateBmsSfStock sfStock=new UpdateBmsSfStock();
						
						sfStock.setBmsClosingStock(curStock.getBmsClosingStock());
						
						sfStock.setProdIssueQty(curStock.getProdIssueQty());
						sfStock.setProdRejectedQty(curStock.getProdRejectedQty());
						sfStock.setProdReturnQty(curStock.getProdReturnQty());
						sfStock.setMixingIssueQty(curStock.getMixingIssueQty());
						sfStock.setMixingRejectedQty(curStock.getMixingRejectedQty());
						sfStock.setSfId(curStock.getSfId());
						sfStock.setBmsStockId(globalBmsHeaderId);

						sfStockList.add(sfStock);

							
						}
					sfUpdate.setBmsSfStock(sfStockList);
					
					Info  updateBmsSfStockResponse=restTemplate.postForObject(Constants.url+"updateBmsStockForSF", sfUpdate, Info.class);
					
					System.out.println("sf update Response "+updateBmsSfStockResponse);

					// end of if
					 // end of inner for
				
//commented before
				System.out.println("Stock Detail List new For Day End " + stokDetailList.toString());

				//bmsStockHeader.setBmsStockDetailed(stokDetailList);
				System.out.println("Bms Id for SF  rm Update "+globalBmsHeaderId);

				bmsStockHeader.setBmsStockId(globalBmsHeaderId);
				
				bmsStockHeader.setBmsStockDate(globalHeaderDate);
				
				
				bmsStockHeader.setRmType(2);
				bmsStockHeader.setExInt(0);
				bmsStockHeader.setExInt1(0);
				bmsStockHeader.setExBoll(0);
				bmsStockHeader.setExBoll1(0);
				bmsStockHeader.setExVarchar("");
				
				
				//bmsStockHeader.setBmsStockDate(bmsStockDetailedList.get(0).getBmsStockDate());
				bmsStockHeader.setBmsStatus(1);
				/*bmsStockHeader.setRmType(bmsStockDetailedList.get(0).getRmType());
				bmsStockHeader.setExInt(0);
				bmsStockHeader.setExInt1(0);
				bmsStockHeader.setExBoll(0);
				bmsStockHeader.setExBoll1(0);
				bmsStockHeader.setExVarchar("");*/

				//bmsStockHeader = new BmsStockHeader();
				bmsStockHeaderResponse = restTemplate.postForObject(Constants.url + "insertBmsStock", bmsStockHeader,
						BmsStockHeader.class);// end of update bms stock Day end Process
				System.out.println("bMS SF Stock Header Update Response" + bmsStockHeaderResponse.toString());
				// Inserting next Day Entry for BMS Stock//
				
				System.out.println("Inserting next day stock Entry for BMS Stock");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Calendar c = Calendar.getInstance();
				c.setTime(globalHeaderDate); // Now use today date.
				c.add(Calendar.DATE, 1); // Adding 1 day
				String output = sdf.format(c.getTime());
				System.out.println(output);
				Date stockDate = sdf.parse(output);
				System.out.println("new Date " + stockDate);

				BmsStockHeader bmsStockHeaderInsert = new BmsStockHeader();

				bmsStockHeaderInsert.setBmsStockId(0);
				bmsStockHeaderInsert.setBmsStockDate(stockDate);
				bmsStockHeaderInsert.setBmsStatus(0);
				bmsStockHeaderInsert.setRmType(globalRmType);
				bmsStockHeaderInsert.setExInt(0);
				bmsStockHeaderInsert.setExInt1(0);
				bmsStockHeaderInsert.setExBoll(0);
				bmsStockHeaderInsert.setExBoll1(0);
				bmsStockHeaderInsert.setExVarchar("");

				List<BmsStockDetailed> bmsStockDetailedlist = new ArrayList<BmsStockDetailed>();

				for (int i = 0; i < bmsCurrentStockSf.size(); i++) {

					BmsStockDetailed bmsStockDetailed = new BmsStockDetailed();
					// bmsStockDetailed.setBmsStockId(0);
					bmsStockDetailed.setBmsStockDate(stockDate);
					// bmsStockHeader.setRmType(1);
					bmsStockDetailed.setRmId(bmsCurrentStockSf.get(i).getSfId());
					bmsStockDetailed.setRmName(bmsCurrentStockSf.get(i).getSfName());
					bmsStockDetailed.setBmsOpeningStock(bmsCurrentStockSf.get(i).getBmsClosingStock());
					bmsStockDetailed.setRmType(globalRmType);
					bmsStockDetailed.setRmUom(bmsCurrentStockSf.get(i).getSfUomId());

					bmsStockDetailedlist.add(bmsStockDetailed);

				}
				bmsStockHeaderInsert.setBmsStockDetailed(bmsStockDetailedlist);

				bmsStockHeader = new BmsStockHeader();
				bmsStockHeader = restTemplate.postForObject(Constants.url + "insertBmsStock", bmsStockHeaderInsert,
						BmsStockHeader.class);// End of inserting BMS Stock for Next Day
				System.out.println("bMS SF Stock Header iNSERT Response" + bmsStockHeader.toString());
				
			}

			
		} catch (Exception e) {

			System.out.println("Error iN Inserting Day End Stock Process " + e.getMessage());
			e.printStackTrace();
		}

		return mav;

	}

}
