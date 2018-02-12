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

			Date cDate = new Date();

			// if getCurrentStock
			if (Integer.parseInt(request.getParameter("selectStock")) == 1) {

				System.out.println("inside If  get Current Stock is selected   ");

				int rmType = Integer.parseInt(request.getParameter("matType"));

				if (rmType == 1) {

					System.out.println("It is Current Stock Of RM ");

					globalRmType = rmType;

					Date headerDate = new Date();

					map = new LinkedMultiValueMap<String, Object>();

					map.add("status", 0);

					map.add("rmType", 1);

					BmsStockHeader bmsStockHeader = restTemplate.postForObject(Constants.url + "getBmsStockHeader", map,
							BmsStockHeader.class);

					System.out.println("RM : Bms Stock  Header " + bmsStockHeader.toString());

					if (bmsStockHeader != null) {
						headerDate = bmsStockHeader.getBmsStockDate();

					}

					globalHeaderDate = bmsStockHeader.getBmsStockDate();

					SimpleDateFormat sdF = new SimpleDateFormat("dd-MM-yyyy");

					String globalDate = sdF.format(globalHeaderDate);

					globalHeaderDate = sdF.parse(globalDate);

					System.out.println(" RM :Global Header Date " + globalHeaderDate);

					globalBmsHeaderId = bmsStockHeader.getBmsStockId();

					int showDayEnd = 0;
					
					if (headerDate.before(cDate) || headerDate.equals(cDate)) {

						showDayEnd = 1;

					} else {
						
						showDayEnd = 0;
					}

					// System.out.println("show day end " + showDayEnd);
					mav.addObject("showDayEnd", showDayEnd);

					// globalRmType = rmType;

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

					System.out.println(
							"Mix Dept Id " + mixDeptId + "Prod Dept Id " + prodDeptId + "BMS Dept Id " + bmsDeptId);
					map = new LinkedMultiValueMap<String, Object>();

					map = new LinkedMultiValueMap<String, Object>();
					map.add("prodDeptId", prodDeptId);
					map.add("bmsDeptId", bmsDeptId);
					map.add("mixDeptId", mixDeptId);
					map.add("stockDate", globalDate);

					bmsCurrentStockList = restTemplate.postForObject(Constants.url + "getCurentBmsStockRM", map,
							GetBmsCurrentStockList.class);

					bmsCurrentStock = bmsCurrentStockList.getBmsCurrentStock();

					GetBmsCurrentStock stock = new GetBmsCurrentStock();

					for (int i = 0; i < bmsCurrentStock.size(); i++) {

						stock = new GetBmsCurrentStock();
						stock = bmsCurrentStock.get(i);

						bmsCurrentStock.get(i).setBmsClosingStock(0);

						bmsCurrentStock.get(i).setBmsClosingStock(((stock.getBmsOpeningStock()
								+ stock.getProdReturnQty() + stock.getStoreIssueQty() + stock.getMixingReturnQty())
								- (stock.getProdIssueQty() + stock.getMixingIssueQty() + stock.getStoreRejectedQty())));

					}

					mav.addObject("isRm", String.valueOf(1));
					mav.addObject("stockList", bmsCurrentStock);

				} // end of if rmtype==1 ie RM

				else if (rmType == 2) {
					System.out.println("It is Current Stock Of SF ");

					globalRmType = rmType;

					Date headerDate = new Date();

					map = new LinkedMultiValueMap<String, Object>();

					map.add("status", 0);

					map.add("rmType", globalRmType);

					BmsStockHeader bmsStockHeader = restTemplate.postForObject(Constants.url + "getBmsStockHeader", map,
							BmsStockHeader.class);

					System.out.println("SF : BMS Stock Header " + bmsStockHeader.toString());

					if (bmsStockHeader != null) {
						headerDate = bmsStockHeader.getBmsStockDate();

					}

					globalHeaderDate = bmsStockHeader.getBmsStockDate();

					SimpleDateFormat sdF = new SimpleDateFormat("dd-MM-yyyy");

					String globalDate = sdF.format(globalHeaderDate);

					globalHeaderDate = sdF.parse(globalDate);

					System.out.println("SF: Global Header Date " + globalHeaderDate);

					globalBmsHeaderId = bmsStockHeader.getBmsStockId();

					int showDayEnd = 0;
					
					if (headerDate.before(cDate) || headerDate.equals(cDate)) {

						showDayEnd = 1;

					} else {
						
						showDayEnd = 0;
						
					}

					// System.out.println("show day end " + showDayEnd);
					mav.addObject("showDayEnd", showDayEnd);

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

					System.out.println("SF: Prod Dept Id " + prodDeptId);

					map = new LinkedMultiValueMap<String, Object>();
					map.add("prodDeptId", prodDeptId);
					map.add("stockDate", globalDate);

					currentBmsSFStockList = restTemplate.postForObject(Constants.url + "getCurentBmsStockSF", map,
							GetCurrentBmsSFStockList.class);

					bmsCurrentStockSf = new ArrayList<>();
					bmsCurrentStockSf = currentBmsSFStockList.getCurrentBmsSFStock();

					System.out.println("Sf: current Stock List " + bmsCurrentStockSf.toString());

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

			} // end of if getCurrentStock

			// Getting Stock Between Date for RM and SF

			if (Integer.parseInt(request.getParameter("selectStock")) == 3) {
				System.out.println("Stock BET DATE");

				int rmType = Integer.parseInt(request.getParameter("matType"));

				if (rmType == 1) {

					// get RM Stock bet Date
					globalRmType = rmType;

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

					System.out.println("RM Stock List Between Date : " + bmsRmStockBetDate.toString());

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

					currentBmsSFStockList = restTemplate.postForObject(Constants.url + "getBmsStockSFBetDate", map,
							GetCurrentBmsSFStockList.class);

					bmsSfStockBetDate = new ArrayList<>();
					bmsSfStockBetDate = currentBmsSFStockList.getCurrentBmsSFStock();

					System.out.println("Sf  Stock List Between Date : " + bmsCurrentStockSf.toString());
					mav.addObject("isRm", String.valueOf(2));
					mav.addObject("stockList", bmsSfStockBetDate);

				}

			} // end of else Get Stock Between two Date

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

			System.out.println(" Inside day End Process BMS Stock ");

			if (globalRmType == 1) {

				System.out.println("current list size " + bmsCurrentStockList.getBmsCurrentStock().size());

				UpdateBmsStockList rmUpdate = new UpdateBmsStockList();

				List<UpdateBmsStock> rmStockList = new ArrayList<>();

				for (int j = 0; j < bmsCurrentStockList.getBmsCurrentStock().size(); j++) {

					GetBmsCurrentStock getBmsCurrentStock = bmsCurrentStockList.getBmsCurrentStock().get(j);

					UpdateBmsStock rmStock = new UpdateBmsStock();

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

					rmStockList.add(rmStock);

				}

				rmUpdate.setBmsStock(rmStockList);

				Info updateBmsRmStockResponse = restTemplate.postForObject(Constants.url + "updateBmsStockForRM",
						rmUpdate, Info.class);
				System.out.println("Response BMS Stock Update for Day End RM : " + updateBmsRmStockResponse);

				bmsStockHeader.setBmsStockId(globalBmsHeaderId);

				bmsStockHeader.setBmsStatus(1);

				bmsStockHeader.setBmsStockDate(globalHeaderDate);

				bmsStockHeader.setRmType(1);
				bmsStockHeader.setExInt(0);
				bmsStockHeader.setExInt1(0);
				bmsStockHeader.setExBoll(0);
				bmsStockHeader.setExBoll1(0);
				bmsStockHeader.setExVarchar(" ");

				bmsStockHeaderResponse = restTemplate.postForObject(Constants.url + "insertBmsStock", bmsStockHeader,
						BmsStockHeader.class);// end of update bms stock Day end Process

				System.out.println(
						"bsm RM Stock Header Update Response:for Day end " + bmsStockHeaderResponse.toString());

				// Inserting next Day Entry for BMS Stock//

				System.out.println("Inserting next day stock Entry for BMS Stock RM");

				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Calendar c = Calendar.getInstance();
				c.setTime(globalHeaderDate); // Now use today date.
				c.add(Calendar.DATE, 1); // Adding 1 day
				String output = sdf.format(c.getTime());
				Date stockDate = sdf.parse(output);

				System.out.println("Date for Next Day Entry : RM Stock " + stockDate);

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

				for (int i = 0; i < bmsCurrentStockList.getBmsCurrentStock().size(); i++) {

					BmsStockDetailed bmsStockDetailed = new BmsStockDetailed();

					GetBmsCurrentStock curStock = bmsCurrentStockList.getBmsCurrentStock().get(i);

					bmsStockDetailed.setBmsStockDate(stockDate);
					bmsStockDetailed.setRmId(curStock.getRmId());
					bmsStockDetailed.setRmName(curStock.getRmName());
					bmsStockDetailed.setBmsOpeningStock(curStock.getBmsClosingStock());
					bmsStockDetailed.setRmType(globalRmType);
					bmsStockDetailed.setRmUom(curStock.getRmUomId());
					bmsStockDetailedlist.add(bmsStockDetailed);

				}
				
				bmsStockHeaderInsert.setBmsStockDetailed(bmsStockDetailedlist);

				bmsStockHeader = new BmsStockHeader();

				bmsStockHeader = restTemplate.postForObject(Constants.url + "insertBmsStock", bmsStockHeaderInsert,
						BmsStockHeader.class);// End of inserting BMS Stock for Next Day

				System.out.println("bsm RM Stock Header Insert Response" + bmsStockHeader.toString());

			} else if (globalRmType == 2) {
				
				// day end for Sf Stock

				UpdateBmsSfStockList sfUpdate = new UpdateBmsSfStockList();

				List<UpdateBmsSfStock> sfStockList = new ArrayList<>();
		
				System.out.println("Day End For SF ");

				for (int j = 0; j < bmsCurrentStockSf.size(); j++) {

					GetCurrentBmsSFStock curStock = bmsCurrentStockSf.get(j);
					UpdateBmsSfStock sfStock = new UpdateBmsSfStock();

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

				Info updateBmsSfStockResponse = restTemplate.postForObject(Constants.url + "updateBmsStockForSF",
						sfUpdate, Info.class);

				System.out.println("sf update Response " + updateBmsSfStockResponse);

				bmsStockHeader.setBmsStockId(globalBmsHeaderId);
				bmsStockHeader.setBmsStockDate(globalHeaderDate);
				bmsStockHeader.setRmType(globalRmType);
				bmsStockHeader.setExInt(0);
				bmsStockHeader.setExInt1(0);
				bmsStockHeader.setExBoll(0);
				bmsStockHeader.setExBoll1(0);
				bmsStockHeader.setExVarchar("");
				bmsStockHeader.setBmsStatus(1);
				
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
				System.out.println("new Date For Sf Next Day Stock Entry :" + stockDate);

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
					bmsStockDetailed.setBmsStockDate(stockDate);
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
