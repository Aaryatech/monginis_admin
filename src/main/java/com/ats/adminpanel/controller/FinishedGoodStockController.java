package com.ats.adminpanel.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.dvcs.CPDRequestBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.item.CategoryListResponse;
import com.ats.adminpanel.model.item.Item;
import com.ats.adminpanel.model.item.MCategoryList;
import com.ats.adminpanel.model.item.StockDetail;
import com.ats.adminpanel.model.stock.FinishedGoodStock;
import com.ats.adminpanel.model.stock.FinishedGoodStockDetail;
import com.ats.adminpanel.model.stock.GetCurProdAndBillQty;
import com.ats.adminpanel.model.stock.GetCurProdAndBillQtyList;

@Controller
public class FinishedGoodStockController {

	public static List<Item> globalItemList;

	List<MCategoryList> filteredCatList;

	int selectedCat;

	GetCurProdAndBillQtyList getCurProdAndBillQtyList = new GetCurProdAndBillQtyList();

	List<GetCurProdAndBillQty> getCurProdAndBillQty;

	@RequestMapping(value = "/showFinishedGoodStock", method = RequestMethod.GET)
	public ModelAndView showFinishedGoodStock(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("stock/finishedGoodStock");

		Constants.mainAct =4;
		Constants.subAct =40;

		RestTemplate restTemplate = new RestTemplate();

		CategoryListResponse allCategoryResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
				CategoryListResponse.class);

		List<MCategoryList> catList = allCategoryResponse.getmCategoryList();

		filteredCatList = new ArrayList<MCategoryList>();
		System.out.println("catList :" + catList.toString());

		for (MCategoryList mCategory : catList) {
			if (mCategory.getCatId() != 5 && mCategory.getCatId() != 3) {
				filteredCatList.add(mCategory);

			}
		}

		model.addObject("catList", filteredCatList);

		return model;

	}

	@RequestMapping(value = "/getItemsByCatIdForFinGood", method = RequestMethod.GET)
	public @ResponseBody List<Item> getItemsByCategory(HttpServletRequest request, HttpServletResponse response) {

		RestTemplate restTemplate = new RestTemplate();

		int catId = Integer.parseInt(request.getParameter("catId"));

		//int option = Integer.parseInt(request.getParameter("option"));

		selectedCat = catId;

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("itemGrp1", catId);

		Item[] item = restTemplate.postForObject(Constants.url + "getItemsByCatId", map, Item[].class);
		ArrayList<Item> itemList = new ArrayList<Item>(Arrays.asList(item));

		System.out.println(" Item List " + itemList.toString());

		// Get Current Stock Option

		
		System.out.println("Item List Fresh "+itemList.toString());

		globalItemList = itemList;

		return itemList;

	}

	@RequestMapping(value = "/insertOpeningStock", method = RequestMethod.POST)
	public ModelAndView insertOpeningStock(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("stock/finishedGoodStock");

		FinishedGoodStock goodStockHeader = new FinishedGoodStock();

		List<FinishedGoodStockDetail> finGoodStockList = new ArrayList<>();

		RestTemplate restTemplate = new RestTemplate();

		for (int i = 0; i < globalItemList.size(); i++) {

			int t1 = Integer.parseInt(request.getParameter("qty1" + globalItemList.get(i).getId()));
			int t2 = Integer.parseInt(request.getParameter("qty2" + globalItemList.get(i).getId()));
			int t3 = Integer.parseInt(request.getParameter("qty3" + globalItemList.get(i).getId()));

			System.out.println("t1 for Item :" + globalItemList.get(i).getItemName() + ":" + t1);
			System.out.println("t2 for Item :" + globalItemList.get(i).getItemName() + ":" + t2);
			System.out.println("t3 for Item :" + globalItemList.get(i).getItemName() + ":" + t3);

			FinishedGoodStockDetail finGoodStockDetail = new FinishedGoodStockDetail();

			if (t1 != 0 && t2 != 0 && t3 != 0) {

				finGoodStockDetail.setItemId(globalItemList.get(i).getId());
				finGoodStockDetail.setItemName(globalItemList.get(i).getItemName());
				finGoodStockDetail.setOpT1(t1);
				finGoodStockDetail.setOpT2(t2);
				finGoodStockDetail.setOpT3(t3);
				finGoodStockDetail.setOpTotal(t1 + t2 + t3);
				finGoodStockDetail.setStockDate(new Date());
				finGoodStockDetail.setDelStatus(0);

				finGoodStockList.add(finGoodStockDetail);
			}

		}

		goodStockHeader.setCatId(selectedCat);
		goodStockHeader.setFinGoodStockDate(new Date());
		goodStockHeader.setFinGoodStockStatus(0);
		goodStockHeader.setDelStatus(0);
		goodStockHeader.setFinishedGoodStockDetail(finGoodStockList);

		model.addObject("catList", filteredCatList);

		Info info = restTemplate.postForObject(Constants.url + "insertFinishedGoodOpStock", goodStockHeader,
				Info.class);

		info.toString();

		return model;

	}

	
	
	
	
	@RequestMapping(value = "/showFinishGoodStock", method = RequestMethod.GET)
	public ModelAndView showFinGoodStock(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("stock/showFinGoodStock");
		Constants.mainAct =4;
		Constants.subAct =36;
		
		
		RestTemplate restTemplate = new RestTemplate();

		CategoryListResponse allCategoryResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
				CategoryListResponse.class);

		List<MCategoryList> catList = allCategoryResponse.getmCategoryList();

		filteredCatList = new ArrayList<MCategoryList>();
		System.out.println("catList :" + catList.toString());

		for (MCategoryList mCategory : catList) {
			if (mCategory.getCatId() != 5 && mCategory.getCatId() != 3) {
				filteredCatList.add(mCategory);

			}
		}

		model.addObject("catList", filteredCatList);
		
		return model;
	}
	
	
	
	@RequestMapping(value = "/getFinGoodStock", method = RequestMethod.GET)
	public @ResponseBody List<FinishedGoodStockDetail> getFinGoodStock(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside get Fin good Ajax Call");

		RestTemplate restTemplate = new RestTemplate();

		int catId = Integer.parseInt(request.getParameter("catId"));
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		selectedCat=catId;

		int option = Integer.parseInt(request.getParameter("option"));
		List<FinishedGoodStockDetail> updateStockDetailList=new ArrayList<>();
		
		try {

			//Get Current Stock For Finished Good 
		if (option == 1) {
			

			System.out.println("inside option 1");

			DateFormat dfYmd = new SimpleDateFormat("yyyy-MM-dd");

			map = new LinkedMultiValueMap<String, Object>();
			map.add("stockStatus", 0);

			FinishedGoodStock stockHeader = restTemplate.postForObject(Constants.url + "getFinGoodStockHeader", map,
					FinishedGoodStock.class);

			System.out.println("stock Header " + stockHeader.toString());

			Date stockDate = stockHeader.getFinGoodStockDate();

			List<GetCurProdAndBillQty> getCurProdAndBillQty = new ArrayList<>();
			map = new LinkedMultiValueMap<String, Object>();

			System.out.println("stock date " + stockDate);
			String prodDate = dfYmd.format(stockDate);
			map.add("prodDate", prodDate);
			map.add("catId", catId);
			map.add("delStatus", 0);

			getCurProdAndBillQtyList = restTemplate.postForObject(Constants.url + "getCurrentProdAndBillQty", map,
					GetCurProdAndBillQtyList.class);

			getCurProdAndBillQty = getCurProdAndBillQtyList.getGetCurProdAndBillQty();

			System.out.println("Cur Prod And Bill Qty Listy " + getCurProdAndBillQty.toString());
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

			String stkDate = df.format(stockDate);
			map = new LinkedMultiValueMap<String, Object>();
			map.add("stockDate", stkDate);

			ParameterizedTypeReference<List<FinishedGoodStockDetail>> typeRef = new ParameterizedTypeReference<List<FinishedGoodStockDetail>>() {
			};
			ResponseEntity<List<FinishedGoodStockDetail>> responseEntity = restTemplate
					.exchange(Constants.url + "getFinGoodStockDetail", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			List<FinishedGoodStockDetail> finGoodDetail = responseEntity.getBody();

			System.out.println("Finished Good Stock Detail " + finGoodDetail.toString());

			// new code
			
			FinishedGoodStockDetail stockDetail = new FinishedGoodStockDetail();
			GetCurProdAndBillQty curProdBilQty = new GetCurProdAndBillQty();
			

			for (int i = 0; i < getCurProdAndBillQty.size(); i++) {

				curProdBilQty = getCurProdAndBillQty.get(i);

				for (int j = 0; j < finGoodDetail.size(); j++) {

					stockDetail = finGoodDetail.get(j);

					if (curProdBilQty.getId() == stockDetail.getItemId()) {

						System.out
								.println("item Id Matched " + curProdBilQty.getId() + "and " + stockDetail.getItemId());

						float a = 0, b = 0, c = 0;

						float cloT1 = 0;
						float cloT2 = 0;
						float cloT3 = 0;

						float curClosing = 0;

						float totalClosing = 0;

						int billQty = curProdBilQty.getBillQty() + curProdBilQty.getDamagedQty();
						int prodQty = curProdBilQty.getProdQty();
						int rejQty = curProdBilQty.getRejectedQty();

						float t1 = stockDetail.getOpT1();
						float t2 = stockDetail.getOpT2();
						float t3 = stockDetail.getOpT3();

						System.out.println("t1 : " + t1 + " t2: " + t2 + " t3: " + t3);

						if (t3 > 0) {

							if (billQty < t3) {
								c = billQty;
							} else {
								c = t3;
							}

						} // end of t3>0

						if (t2 > 0) {

							if ((billQty - c) < t2) {
								b = (billQty - c);
							} else {

								b = t2;
							}

						} // end of t2>0

						if (t1 > 0) {

							if ((billQty - c - b) < t1) {

								a = (billQty - b - c);

							} else {

								a = t1;
							}
						} // end of if t1>0

						System.out.println("---------");
						System.out.println("bill Qty = " + curProdBilQty.getBillQty());
						System.out.println(" for Item Id " + curProdBilQty.getId());
						System.out.println("a =" + a + "b = " + b + "c= " + c);
						float damagedQty=curProdBilQty.getDamagedQty();

						float curIssue = billQty - (a + b + c);

						System.out.println("cur Issue qty =" + curIssue);

						cloT1 = t1 - a;
						cloT2 = t2 - b;
						cloT3 = t3 - c;

						curClosing = prodQty - rejQty - curIssue;
						totalClosing = ((t1 + t2 + t3) + (prodQty - rejQty)) - billQty;
						stockDetail.setCloCurrent(curClosing);
						stockDetail.setCloT1(cloT1);
						stockDetail.setCloT2(cloT2);
						stockDetail.setCloT3(cloT3);
						stockDetail.setFrSaleQty(billQty);
						stockDetail.setGateSaleQty(damagedQty);
						stockDetail.setProdQty(prodQty);
						stockDetail.setRejQty(rejQty);
						stockDetail.setTotalCloStk(totalClosing);
						
						updateStockDetailList.add(stockDetail);
						
						System.out.println("closing Qty  : t1 " + cloT1 + " t2 " + cloT2 + " t3 " + cloT3);

						System.out.println("cur Closing " + curClosing);
						System.out.println("total closing " + totalClosing);

						System.out.println("---------");

					} // end of if isSameItem =true
				} // end of Inner For Loop
			} // End of outer For loop
			
			
			//end of new code
			
			
		} // end of Option 1

		if (option == 3) {
			
			System.out.println("Inside Option 3 stock bet Date  ");
			
			String fromDate=request.getParameter("from_datepicker");
			
			String toDate=request.getParameter("to_datepicker");
			
			
			System.out.println("from Date "+fromDate);
			System.out.println("to Date "+toDate);
			
			updateStockDetailList=new ArrayList<FinishedGoodStockDetail>();
			
			map = new LinkedMultiValueMap<String, Object>();
			
			map.add("stockStatus", 1);
			map.add("stockFromDate", fromDate);
			map.add("stockToDate", toDate);
			
			ParameterizedTypeReference<List<FinishedGoodStockDetail>> typeRef = new ParameterizedTypeReference<List<FinishedGoodStockDetail>>() {
			};
			ResponseEntity<List<FinishedGoodStockDetail>> responseEntity = restTemplate
					.exchange(Constants.url + "getFinGoodStockBetDate", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			updateStockDetailList = responseEntity.getBody();

		}
		
		System.out.println("View Stock List "+updateStockDetailList.toString());
		}catch (Exception e) {
			System.out.println("Error In Getting Stock "+e.getMessage());
			e.printStackTrace();
		}
		return updateStockDetailList;
		
	}

	@RequestMapping(value = "/finishedGoodDayEnd", method = RequestMethod.GET)
	@ResponseBody public String finishedGoodDayEnd(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("stock/finishedGoodStock");

		System.out.println("Inside Finished Good Day End ");

		//Constants.mainAct = 12;
	//	Constants.subAct = 123;
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		try {
			DateFormat dfYmd = new SimpleDateFormat("yyyy-MM-dd");

			map = new LinkedMultiValueMap<String, Object>();
			map.add("stockStatus", 0);

			FinishedGoodStock stockHeader = restTemplate.postForObject(Constants.url + "getFinGoodStockHeader", map,
					FinishedGoodStock.class);

			System.out.println("stock Header " + stockHeader.toString());

			Date stockDate = stockHeader.getFinGoodStockDate();

			List<GetCurProdAndBillQty> getCurProdAndBillQty = new ArrayList<>();
			map = new LinkedMultiValueMap<String, Object>();

			System.out.println("stock date " + stockDate);
			String prodDate = dfYmd.format(stockDate);
			map.add("prodDate", prodDate);
			map.add("catId", selectedCat);
			map.add("delStatus", 0);

			getCurProdAndBillQtyList = restTemplate.postForObject(Constants.url + "getCurrentProdAndBillQty", map,
					GetCurProdAndBillQtyList.class);

			getCurProdAndBillQty = getCurProdAndBillQtyList.getGetCurProdAndBillQty();

			System.out.println("Cur Prod And Bill Qty Listy " + getCurProdAndBillQty.toString());
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

			String stkDate = df.format(stockDate);
			map = new LinkedMultiValueMap<String, Object>();
			map.add("stockDate", stkDate);

			ParameterizedTypeReference<List<FinishedGoodStockDetail>> typeRef = new ParameterizedTypeReference<List<FinishedGoodStockDetail>>() {
			};
			ResponseEntity<List<FinishedGoodStockDetail>> responseEntity = restTemplate
					.exchange(Constants.url + "getFinGoodStockDetail", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			List<FinishedGoodStockDetail> finGoodDetail = responseEntity.getBody();

			System.out.println("Finished Good Stock Detail " + finGoodDetail.toString());

			FinishedGoodStockDetail stockDetail = new FinishedGoodStockDetail();
			GetCurProdAndBillQty curProdBilQty = new GetCurProdAndBillQty();
			
			List<FinishedGoodStockDetail> updateStockDetailList=new ArrayList<>();

			for (int i = 0; i < getCurProdAndBillQty.size(); i++) {

				curProdBilQty = getCurProdAndBillQty.get(i);

				for (int j = 0; j < finGoodDetail.size(); j++) {

					stockDetail = finGoodDetail.get(j);

					if (curProdBilQty.getId() == stockDetail.getItemId()) {

						System.out
								.println("item Id Matched " + curProdBilQty.getId() + "and " + stockDetail.getItemId());

						float a = 0, b = 0, c = 0;

						float cloT1 = 0;
						float cloT2 = 0;
						float cloT3 = 0;

						float curClosing = 0;

						float totalClosing = 0;

						int billQty = curProdBilQty.getBillQty() + curProdBilQty.getDamagedQty();
						int prodQty = curProdBilQty.getProdQty();
						int rejQty = curProdBilQty.getRejectedQty();

						float t1 = stockDetail.getOpT1();
						float t2 = stockDetail.getOpT2();
						float t3 = stockDetail.getOpT3();

						System.out.println("t1 : " + t1 + " t2: " + t2 + " t3: " + t3);

						if (t3 > 0) {

							if (billQty < t3) {
								c = billQty;
							} else {
								c = t3;
							}

						} // end of t3>0

						if (t2 > 0) {

							if ((billQty - c) < t2) {
								b = (billQty - c);
							} else {

								b = t2;
							}

						} // end of t2>0

						if (t1 > 0) {

							if ((billQty - c - b) < t1) {

								a = (billQty - b - c);

							} else {

								a = t1;
							}
						} // end of if t1>0

						System.out.println("---------");
						System.out.println("bill Qty = " + curProdBilQty.getBillQty());
						System.out.println(" for Item Id " + curProdBilQty.getId());
						System.out.println("a =" + a + "b = " + b + "c= " + c);
						float damagedQty=curProdBilQty.getDamagedQty();

						float curIssue = billQty - (a + b + c);

						System.out.println("cur Issue qty =" + curIssue);

						cloT1 = t1 - a;
						cloT2 = t2 - b;
						cloT3 = t3 - c;

						curClosing = prodQty - rejQty - curIssue;

						totalClosing = ((t1 + t2 + t3) + (prodQty - rejQty)) - billQty;
						stockDetail.setCloCurrent(curClosing);
						stockDetail.setCloT1(cloT1);
						stockDetail.setCloT2(cloT2);
						stockDetail.setCloT3(cloT3);
						stockDetail.setFrSaleQty(billQty);
						stockDetail.setGateSaleQty(damagedQty);
						stockDetail.setProdQty(prodQty);
						stockDetail.setRejQty(rejQty);
						stockDetail.setTotalCloStk(totalClosing);
						
						
						updateStockDetailList.add(stockDetail);
						
						System.out.println("closing Qty  : t1 " + cloT1 + " t2 " + cloT2 + " t3 " + cloT3);

						System.out.println("cur Closing " + curClosing);
						System.out.println("total closing " + totalClosing);

						System.out.println("---------");

					} // end of if isSameItem =true
				} // end of Inner For Loop
			} // End of outer For loop
			
			stockHeader.setFinGoodStockStatus(1);
			stockHeader.setFinishedGoodStockDetail(updateStockDetailList);
			
			Info curDayUpdate = restTemplate.postForObject(Constants.url + "insertFinishedGoodOpStock", stockHeader,
					Info.class);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(stockDate); // Now use stock date.
			c.add(Calendar.DATE, 1); // Adding 1 day
			String output = sdf.format(c.getTime());
			System.out.println(output);
			Date finGoodStkDate = sdf.parse(output);
			System.out.println("new Date " + stockDate);
			
			FinishedGoodStock nextDayOpStockHeader=new FinishedGoodStock();
			
			nextDayOpStockHeader.setCatId(selectedCat);
			nextDayOpStockHeader.setFinGoodStockDate(finGoodStkDate);
			nextDayOpStockHeader.setFinGoodStockStatus(0);
			nextDayOpStockHeader.setDelStatus(0);
			
			List<FinishedGoodStockDetail> nextDayStockDetail=new ArrayList<>();
			for(int i=0;i<updateStockDetailList.size();i++) {
				
				FinishedGoodStockDetail finStkDetail=new FinishedGoodStockDetail();
				
				FinishedGoodStockDetail prevDetail=updateStockDetailList.get(i);
				
				finStkDetail.setOpT1(prevDetail.getCloCurrent());
				finStkDetail.setOpT2(prevDetail.getOpT1());
				finStkDetail.setOpT3(prevDetail.getOpT2());
				finStkDetail.setItemId(prevDetail.getItemId());
				finStkDetail.setItemName(prevDetail.getItemName());
				finStkDetail.setStockDate(finGoodStkDate);
				
				finStkDetail.setDelStatus(0);
				
				nextDayStockDetail.add(finStkDetail);
			}
			
			nextDayOpStockHeader.setFinishedGoodStockDetail(nextDayStockDetail);

			Info nextDayInsert = restTemplate.postForObject(Constants.url + "insertFinishedGoodOpStock", nextDayOpStockHeader,
					Info.class);
			

		} catch (Exception e) {

			System.out.println("Exce in Getting cur Prod And Bill Qty  " + e.getMessage());
			e.printStackTrace();
		}

		return "Success";

	}

}
