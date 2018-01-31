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

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.model.BmsStockDetailed;
import com.ats.adminpanel.model.BmsStockHeader;
import com.ats.adminpanel.model.item.FrItemStockConfigure;
import com.ats.adminpanel.model.item.FrItemStockConfigureList;
import com.ats.adminpanel.model.stock.GetBmsCurrentStock;
import com.ats.adminpanel.model.stock.GetBmsCurrentStockList;

@Controller
@Scope("session")
public class BmsStockController {

	GetBmsCurrentStockList bmsCurrentStockList;

	List<GetBmsCurrentStock> bmsCurrentStock = new ArrayList<>();
	List<BmsStockDetailed> stockBetDate = new ArrayList<>();

	private List<BmsStockDetailed> bmsStockDetailedList;

	Date globalHeaderDate;
	
	int globalRmType;
	@RequestMapping(value = "/showBmsStock", method = RequestMethod.GET)
	public ModelAndView showBmsStock(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("stock/bmsStock");
		System.out.println("inside show BMS stock page ");

		Constants.mainAct =8;
		Constants.subAct =48;
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
		
		
//if getCurrentStock
		if (Integer.parseInt(request.getParameter("selectStock")) == 1) {
			
			System.out.println("inside If  get Current Stock is selected   ");


			int rmType = Integer.parseInt(request.getParameter("matType"));
			
			
			globalRmType=rmType;

			String settingKey = new String();

			settingKey = "PROD" + "," + "MIX" + ","  + "STORE";
			map = new LinkedMultiValueMap<String, Object>();

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
			
			map = new LinkedMultiValueMap<String, Object>();
			map.add("prodDeptId", prodDeptId);
			map.add("storeDeptId", storeDeptId);
			map.add("mixDeptId", mixDeptId);
			map.add("rmType", rmType);

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

			System.out.println("curDate for Stock bet date to get current Stock " + DateConvertor.convertToYMD(curDate));
			
			map = new LinkedMultiValueMap<String, Object>();

			map.add("fromDate", DateConvertor.convertToYMD(curDate));
			map.add("toDate",  DateConvertor.convertToYMD(curDate));
			map.add("rmType", rmType);

			ParameterizedTypeReference<List<BmsStockDetailed>> typeRef = new ParameterizedTypeReference<List<BmsStockDetailed>>() {
			};
			ResponseEntity<List<BmsStockDetailed>> responseEntity = restTemplate.exchange(Constants.url + "getBmsStock",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);

			stockBetDate = responseEntity.getBody();

			System.out.println("BMS Stock Bet Date  date \n =" + stockBetDate.toString());

			GetBmsCurrentStock curStk = new GetBmsCurrentStock();
			BmsStockDetailed stkBetDate = new BmsStockDetailed();
			boolean isSameItem = false;

			for (int j = 0; j < bmsCurrentStock.size(); j++) {

				curStk = bmsCurrentStock.get(j);

				for (int i = 0; i < stockBetDate.size(); i++) {
					stkBetDate = stockBetDate.get(i);

					if (stkBetDate.getRmId() == curStk.getRmId()) {

						isSameItem = true;
						curStk.setOpeningQty(stkBetDate.getBmsOpeningStock());
						
						System.out.println("Opening qty set for Item "+curStk.getRmName()+"Qty "+stkBetDate.getBmsOpeningStock());

						float closingQty = curStk.getOpeningQty() +curStk.getStore_issue_qty()+
								curStk.getProd_return_qty()+curStk.getMixing_return_qty()-
								(curStk.getProd_issue_qty()+curStk.getMixing_issue_qty()+curStk.getStore_rejected_qty());
								
						curStk.setClosingQty(closingQty);

						bmsCurrentStock.set(j, curStk);

					}
				}

				/*if (isSameItem == true) {

					curStk.setOpeningQty(stkBetDate.getBmsOpeningStock());

					float closingQty = curStk.getOpeningQty() +curStk.getStore_issue_qty()+
							curStk.getProd_return_qty()+curStk.getMixing_return_qty()-
							(curStk.getProd_issue_qty()+curStk.getMixing_issue_qty()+curStk.getStore_rejected_qty());
							
					curStk.setClosingQty(closingQty);

					bmsCurrentStock.set(j, curStk);

				}*/

			}
			
			Date cDate=new Date();
			
			Date headerDate=new Date();
			
			
			
			map = new LinkedMultiValueMap<String, Object>();
			
			map.add("status", 0);
			
			map.add("rmType",globalRmType);

			BmsStockHeader bmsStockHeader=restTemplate.postForObject(Constants.url +"getBmsStockHeader",map, BmsStockHeader.class);
			
			if(bmsStockHeader!=null ) {
				 headerDate=bmsStockHeader.getBmsStockDate();
				
			}
			 
			 globalHeaderDate=bmsStockHeader.getBmsStockDate();
			 
			int showDayEnd=0;
			if(headerDate.before(cDate)|| headerDate.equals(cDate)) {
				
			showDayEnd=1;
			
			}
			else {
				 showDayEnd=0;

			}
			
			System.out.println("show day end "+showDayEnd);
			mav.addObject("showDayEnd",showDayEnd);


		} // end of if getCurrentStock

		if (Integer.parseInt(request.getParameter("selectStock")) == 3) {

			System.out.println("Inside Else stock btw Date ");

			bmsCurrentStock = new ArrayList<>();

			String fromStockdate = request.getParameter("from_datepicker");

			String toStockdate = request.getParameter("to_datepicker");

			int rmType = Integer.parseInt(request.getParameter("matType"));
			
			globalRmType=rmType;

			System.out.println("from Date " + DateConvertor.convertToYMD(fromStockdate) + "\n Todate "
					+ DateConvertor.convertToYMD(toStockdate) + "\n RM type  " + rmType);

			DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd-MM-uuuu" );
			  LocalDate tDate = LocalDate.parse(toStockdate, f);
			
			if(tDate.isAfter(LocalDate.now()) || tDate.isEqual(LocalDate.now())){
				System.out.println("   Date is greater than today"+LocalDate.now().minus(Period.ofDays(1)));
				tDate=LocalDate.now().minus(Period.ofDays(1));
				
				}
			map = new LinkedMultiValueMap<String, Object>();
			
			map.add("fromDate", DateConvertor.convertToYMD(fromStockdate));
			map.add("toDate", ""+ DateConvertor.convertToYMD(toStockdate));
			map.add("rmType", rmType);
			map.add("bmsStatus", 1);


			List<BmsStockDetailed> stockBetDate = new ArrayList<>();

			ParameterizedTypeReference<List<BmsStockDetailed>> typeRef = new ParameterizedTypeReference<List<BmsStockDetailed>>() {
			};
			ResponseEntity<List<BmsStockDetailed>> responseEntity = restTemplate.exchange(Constants.url + "getBmsStockBetDateMonth",
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
		}catch (Exception e) {
			
			System.out.println("Exce in Getting Stock "+e.getMessage());
			
			e.printStackTrace();
		}
	return mav;
	
	}

	
	@RequestMapping(value = "/dayEndProcess", method = RequestMethod.POST)
	public ModelAndView dayEndProcess(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("stock/bmsStock");

		try {
			
			 BmsStockHeader bmsStockHeader = new BmsStockHeader();
			  List<BmsStockDetailed> stokDetailList =new ArrayList<>();

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

						 
			for(int i=0;i<bmsStockDetailedList.size();i++)
			{
				for(int j=0;j<bmsCurrentStockList.getBmsCurrentStock().size();j++)
					{
					if(bmsCurrentStockList.getBmsCurrentStock().get(i).getRmId()==bmsStockDetailedList.get(i).getRmId())
					{
						GetBmsCurrentStock getBmsCurrentStock=bmsCurrentStockList.getBmsCurrentStock().get(j);
						
						BmsStockDetailed bmsStockDetailed=bmsStockDetailedList.get(i);
						
							float stockQty=bmsStockDetailed.getBmsOpeningStock()+getBmsCurrentStock.getStore_issue_qty()+getBmsCurrentStock.getProd_return_qty()+getBmsCurrentStock.getMixing_return_qty()
							-(getBmsCurrentStock.getProd_issue_qty()+getBmsCurrentStock.getMixing_issue_qty()+getBmsCurrentStock.getStore_rejected_qty());
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
							newBmsStock.setMixingIssueQty(getBmsCurrentStock.getMixing_issue_qty());
							newBmsStock.setMixingReceiveRejectedQty(bmsStockDetailed.getMixingReceiveRejectedQty());
							newBmsStock.setMixingRecQty(bmsStockDetailed.getMixingRecQty());
							newBmsStock.setMixingRejected(getBmsCurrentStock.getMixing_rejected_qty());
							newBmsStock.setMixingReturnQty(getBmsCurrentStock.getMixing_return_qty());
							newBmsStock.setProdIssueQty(getBmsCurrentStock.getProd_issue_qty());
							newBmsStock.setProdRejectedQty(getBmsCurrentStock.getProd_rejected_qty());
							newBmsStock.setProdReturnQty(getBmsCurrentStock.getProd_return_qty());
							newBmsStock.setStoreRejectedQty(getBmsCurrentStock.getStore_rejected_qty());
							newBmsStock.setProdReturnQty(getBmsCurrentStock.getProd_return_qty());
							
							newBmsStock.setStoreRecQty(getBmsCurrentStock.getStore_issue_qty());
							
							stokDetailList.add(newBmsStock);
							
					}
				}	
			}
			
			System.out.println("Stock Detail List new For Day End "+stokDetailList.toString());
			
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
			
			//Inserting next Day Entry for BMS Stock//
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
				//bmsStockHeader.setRmType(1);
				bmsStockDetailed.setRmId(bmsStockDetailedList.get(i).getRmId());
				bmsStockDetailed.setRmName(bmsStockDetailedList.get(i).getRmName());
				bmsStockDetailed.setBmsOpeningStock(bmsStockDetailedList.get(i).getClosingQty());
				bmsStockDetailed.setRmType(globalRmType);
				bmsStockDetailed.setRmUom(bmsStockDetailedList.get(i).getRmUom());

				bmsStockDetailedlist.add(bmsStockDetailed);

			}
			bmsStockHeaderInsert.setBmsStockDetailed(bmsStockDetailedlist);

			bmsStockHeader = restTemplate.postForObject(Constants.url + "insertBmsStock", bmsStockHeaderInsert,
					BmsStockHeader.class);// End of inserting BMS Stock for Next Day
		} catch (Exception e) {

			System.out.println("Error iN Inserting Day End Stock Process " + e.getMessage());
			e.printStackTrace();
		}

		return mav;

	}

}
