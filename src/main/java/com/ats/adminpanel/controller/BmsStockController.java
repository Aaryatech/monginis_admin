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
import com.ats.adminpanel.model.item.FrItemStockConfigure;
import com.ats.adminpanel.model.item.FrItemStockConfigureList;
import com.ats.adminpanel.model.stock.GetBmsCurrentStock;
import com.ats.adminpanel.model.stock.GetBmsCurrentStockList;
import com.ats.adminpanel.model.stock.GetCurrentBmsSFStock;
import com.ats.adminpanel.model.stock.GetCurrentBmsSFStockList;
import com.itextpdf.awt.geom.CubicCurve2D;

@Controller
@Scope("session")
public class BmsStockController {

	GetBmsCurrentStockList bmsCurrentStockList;

	GetCurrentBmsSFStockList currentBmsSFStockList;

	List<GetBmsCurrentStock> bmsCurrentStock, bmsRmStockBetDate = new ArrayList<>();

	List<GetCurrentBmsSFStock> bmsCurrentStockSf,bmsSfStockBetDate = new ArrayList<>();

	List<BmsStockDetailed> stockBetDate = new ArrayList<>();

	private List<BmsStockDetailed> bmsStockDetailedList;

	Date globalHeaderDate;

	int globalRmType;

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
						bmsCurrentStock.get(i).setBmsClosingStock((
								(stock.getBmsOpeningStock() + stock.getProdReturnQty() + stock.getStoreIssueQty())
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
						bmsCurrentStockSf.get(i)
								.setBmsClosingStock(((stock.getBmsOpeningStock() + stock.getMixingIssueQty()
										+ stock.getProdReturnQty()) - stock.getProdIssueQty()));

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

				int showDayEnd = 0;
				if (headerDate.before(cDate) || headerDate.equals(cDate)) {
					
					System.out.println("Day End Dates Header Date "+headerDate+"curDate "+cDate);

					showDayEnd = 1;

				} else {
					showDayEnd = 0;
					
					System.out.println("Day End Dates Header Date "+headerDate+"curDate "+cDate);

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

				else if(globalRmType==2) {
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
			List<BmsStockDetailed> stokDetailList = new ArrayList<>();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();

			System.out.println(" Inside day End Process ");

			// Update bms stock Day end Process

			map.add("fromDate", new SimpleDateFormat("yyyy-MM-dd").format(globalHeaderDate));
			map.add("toDate", new SimpleDateFormat("yyyy-MM-dd").format(globalHeaderDate));
			map.add("rmType", globalRmType);

			ParameterizedTypeReference<List<BmsStockDetailed>> typeRef = new ParameterizedTypeReference<List<BmsStockDetailed>>() {
			};
			ResponseEntity<List<BmsStockDetailed>> responseEntity = restTemplate.exchange(Constants.url + "getBmsStock",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);

			bmsStockDetailedList = responseEntity.getBody();
			
			if(globalRmType==1) {

			for (int i = 0; i < bmsStockDetailedList.size(); i++) {
				for (int j = 0; j < bmsCurrentStockList.getBmsCurrentStock().size(); j++) {
					if (bmsCurrentStockList.getBmsCurrentStock().get(i).getRmId() == bmsStockDetailedList.get(i)
							.getRmId()) {
						GetBmsCurrentStock getBmsCurrentStock = bmsCurrentStockList.getBmsCurrentStock().get(j);

						BmsStockDetailed bmsStockDetailed = bmsStockDetailedList.get(i);

						float stockQty = bmsStockDetailed.getBmsOpeningStock() + getBmsCurrentStock.getStoreIssueQty()
								+ getBmsCurrentStock.getProdIssueQty() + getBmsCurrentStock.getMixingReturnQty()
								- (getBmsCurrentStock.getProdIssueQty() + getBmsCurrentStock.getMixingIssueQty()
										);
						bmsStockDetailedList.get(i).setClosingQty(stockQty);

						BmsStockDetailed newBmsStock = new BmsStockDetailed();

						bmsStockDetailed.setBmsStockDeatilId(bmsStockDetailedList.get(i).getBmsStockDeatilId());

						newBmsStock.setClosingQty(bmsStockDetailed.getClosingQty());

						newBmsStock.setBmsStockDeatilId(bmsStockDetailed.getBmsStockDeatilId());

						newBmsStock.setBmsStockId(bmsStockDetailed.getBmsStockId());
						newBmsStock.setBmsStockDate(bmsStockDetailed.getBmsStockDate());
						newBmsStock.setRmId(bmsStockDetailed.getRmId());
						newBmsStock.setRmName(bmsStockDetailed.getRmName());
						newBmsStock.setBmsOpeningStock(bmsStockDetailed.getBmsOpeningStock());
						newBmsStock.setRmType(bmsStockDetailed.getRmType());
						newBmsStock.setRmUom(bmsStockDetailed.getRmId());

						newBmsStock.setIsDelStatus(bmsStockDetailed.getIsDelStatus());
						newBmsStock.setMixingIssueQty(getBmsCurrentStock.getMixingIssueQty());
						newBmsStock.setMixingReceiveRejectedQty(bmsStockDetailed.getMixingReceiveRejectedQty());
						newBmsStock.setMixingRecQty(getBmsCurrentStock.getMixingIssueQty());
						newBmsStock.setMixingRejected(getBmsCurrentStock.getMixingRejectedQty());
						newBmsStock.setMixingReturnQty(getBmsCurrentStock.getMixingReturnQty());
						newBmsStock.setProdIssueQty(getBmsCurrentStock.getProdIssueQty());
						newBmsStock.setProdRejectedQty(getBmsCurrentStock.getProdRejectedQty());
						newBmsStock.setProdReturnQty(getBmsCurrentStock.getProdIssueQty());
						newBmsStock.setStoreRejectedQty(getBmsCurrentStock.getStoreRejectedQty());
						newBmsStock.setProdReturnQty(getBmsCurrentStock.getProdIssueQty());

						newBmsStock.setStoreRecQty(getBmsCurrentStock.getStoreIssueQty());

						stokDetailList.add(newBmsStock);

					}
				}
			}

			System.out.println("Stock Detail List new For Day End " + stokDetailList.toString());

			bmsStockHeader.setBmsStockDetailed(stokDetailList);

			bmsStockHeader.setBmsStockId(bmsStockDetailedList.get(0).getBmsStockId());
			bmsStockHeader.setBmsStockDate(bmsStockDetailedList.get(0).getBmsStockDate());
			bmsStockHeader.setBmsStatus(1);
			bmsStockHeader.setRmType(bmsStockDetailedList.get(0).getRmType());
			bmsStockHeader.setExInt(0);
			bmsStockHeader.setExInt1(0);
			bmsStockHeader.setExBoll(0);
			bmsStockHeader.setExBoll1(0);
			bmsStockHeader.setExVarchar("");

			bmsStockHeader = restTemplate.postForObject(Constants.url + "insertBmsStock", bmsStockHeader,
					BmsStockHeader.class);// end of update bms stock Day end Process
			
			System.out.println("bsm RM Stock Header Update Response"+bmsStockHeader.toString());

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

			for (int i = 0; i < bmsStockDetailedList.size(); i++) {

				BmsStockDetailed bmsStockDetailed = new BmsStockDetailed();
				// bmsStockDetailed.setBmsStockId(0);
				bmsStockDetailed.setBmsStockDate(stockDate);
				// bmsStockHeader.setRmType(1);
				bmsStockDetailed.setRmId(bmsStockDetailedList.get(i).getRmId());
				bmsStockDetailed.setRmName(bmsStockDetailedList.get(i).getRmName());
				bmsStockDetailed.setBmsOpeningStock(bmsStockDetailedList.get(i).getClosingQty());
				bmsStockDetailed.setRmType(globalRmType);
				bmsStockDetailed.setRmUom(bmsStockDetailedList.get(i).getRmUom());

				bmsStockDetailedlist.add(bmsStockDetailed);

			}
			bmsStockHeaderInsert.setBmsStockDetailed(bmsStockDetailedlist);
			
			bmsStockHeader=new BmsStockHeader();
			
			bmsStockHeader = restTemplate.postForObject(Constants.url + "insertBmsStock", bmsStockHeaderInsert,
					BmsStockHeader.class);// End of inserting BMS Stock for Next Day
			
			System.out.println("bsm RM Stock Header Insert Response"+bmsStockHeader.toString());

			
			}
			else if(globalRmType==2) {
				//day end for Sf Stock
				System.out.println("Day End For SF ");
				
				for (int i = 0; i < bmsStockDetailedList.size(); i++) {
					for (int j = 0; j < bmsCurrentStockSf.size(); j++) {
						if (bmsCurrentStockSf.get(i).getSfId() == bmsStockDetailedList.get(i)
								.getRmId()) {
							GetCurrentBmsSFStock getBmsCurrentStock =bmsCurrentStockSf.get(j);

							BmsStockDetailed bmsStockDetailed = bmsStockDetailedList.get(i);
							float stockQty=(bmsStockDetailed.getBmsOpeningStock()+getBmsCurrentStock.getMixingIssueQty()+
									getBmsCurrentStock.getProdReturnQty())-getBmsCurrentStock.getProdIssueQty();
							
							bmsStockDetailedList.get(i).setClosingQty(stockQty);

							BmsStockDetailed newBmsStock = new BmsStockDetailed();

							bmsStockDetailed.setBmsStockDeatilId(bmsStockDetailedList.get(i).getBmsStockDeatilId());

							newBmsStock.setClosingQty(bmsStockDetailed.getClosingQty());

							newBmsStock.setBmsStockDeatilId(bmsStockDetailed.getBmsStockDeatilId());

							newBmsStock.setBmsStockId(bmsStockDetailed.getBmsStockId());
							newBmsStock.setBmsStockDate(bmsStockDetailed.getBmsStockDate());
							newBmsStock.setRmId(bmsStockDetailed.getRmId());
							newBmsStock.setRmName(bmsStockDetailed.getRmName());
							newBmsStock.setBmsOpeningStock(bmsStockDetailed.getBmsOpeningStock());
							newBmsStock.setRmType(bmsStockDetailed.getRmType());
							newBmsStock.setRmUom(bmsStockDetailed.getRmId());

							newBmsStock.setIsDelStatus(bmsStockDetailed.getIsDelStatus());
							newBmsStock.setMixingIssueQty(getBmsCurrentStock.getMixingIssueQty());
							newBmsStock.setMixingReceiveRejectedQty(bmsStockDetailed.getMixingReceiveRejectedQty());
							newBmsStock.setMixingRecQty(getBmsCurrentStock.getMixingIssueQty());
							newBmsStock.setMixingRejected(getBmsCurrentStock.getMixingRejectedQty());
							newBmsStock.setMixingReturnQty(0);
							newBmsStock.setProdIssueQty(getBmsCurrentStock.getProdIssueQty());
							newBmsStock.setProdRejectedQty(getBmsCurrentStock.getProdRejectedQty());
							newBmsStock.setProdReturnQty(getBmsCurrentStock.getProdReturnQty());
							newBmsStock.setStoreRejectedQty(0);
							newBmsStock.setProdReturnQty(getBmsCurrentStock.getProdReturnQty());

							newBmsStock.setStoreRecQty(0);

							stokDetailList.add(newBmsStock);

						}//end of if
					}//end of inner for
				}//end of outer for

				System.out.println("Stock Detail List new For Day End " + stokDetailList.toString());

				bmsStockHeader.setBmsStockDetailed(stokDetailList);

				bmsStockHeader.setBmsStockId(bmsStockDetailedList.get(0).getBmsStockId());
				bmsStockHeader.setBmsStockDate(bmsStockDetailedList.get(0).getBmsStockDate());
				bmsStockHeader.setBmsStatus(1);
				bmsStockHeader.setRmType(bmsStockDetailedList.get(0).getRmType());
				bmsStockHeader.setExInt(0);
				bmsStockHeader.setExInt1(0);
				bmsStockHeader.setExBoll(0);
				bmsStockHeader.setExBoll1(0);
				bmsStockHeader.setExVarchar("");
				
				bmsStockHeader=new BmsStockHeader();
				bmsStockHeader = restTemplate.postForObject(Constants.url + "insertBmsStock", bmsStockHeader,
						BmsStockHeader.class);// end of update bms stock Day end Process
				System.out.println("bMS SF Stock Header Update Response"+bmsStockHeader.toString());
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

				for (int i = 0; i < bmsStockDetailedList.size(); i++) {

					BmsStockDetailed bmsStockDetailed = new BmsStockDetailed();
					// bmsStockDetailed.setBmsStockId(0);
					bmsStockDetailed.setBmsStockDate(stockDate);
					// bmsStockHeader.setRmType(1);
					bmsStockDetailed.setRmId(bmsStockDetailedList.get(i).getRmId());
					bmsStockDetailed.setRmName(bmsStockDetailedList.get(i).getRmName());
					bmsStockDetailed.setBmsOpeningStock(bmsStockDetailedList.get(i).getClosingQty());
					bmsStockDetailed.setRmType(globalRmType);
					bmsStockDetailed.setRmUom(bmsStockDetailedList.get(i).getRmUom());

					bmsStockDetailedlist.add(bmsStockDetailed);

				}
				bmsStockHeaderInsert.setBmsStockDetailed(bmsStockDetailedlist);

				bmsStockHeader=new BmsStockHeader();
				bmsStockHeader = restTemplate.postForObject(Constants.url + "insertBmsStock", bmsStockHeaderInsert,
						BmsStockHeader.class);// End of inserting BMS Stock for Next Day
				System.out.println("bMS SF Stock Header iNSERT Response"+bmsStockHeader.toString());

				
			}
		} catch (Exception e) {

			System.out.println("Error iN Inserting Day End Stock Process " + e.getMessage());
			e.printStackTrace();
		}

		return mav;

	}

}
