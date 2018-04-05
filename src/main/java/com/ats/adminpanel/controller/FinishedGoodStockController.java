package com.ats.adminpanel.controller;

import java.awt.Dimension;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;
import org.zefer.pd4ml.PD4PageMark;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.DateConvertor;
import com.ats.adminpanel.model.ExportToExcel;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.item.AllItemsListResponse;
import com.ats.adminpanel.model.item.CategoryListResponse;
import com.ats.adminpanel.model.item.Item;
import com.ats.adminpanel.model.item.MCategoryList;
import com.ats.adminpanel.model.item.StockDetail; 
import com.ats.adminpanel.model.stock.FinGoodBean;
import com.ats.adminpanel.model.stock.FinishedGoodStock;
import com.ats.adminpanel.model.stock.FinishedGoodStockDetail;
import com.ats.adminpanel.model.stock.GetCurProdAndBillQty;
import com.ats.adminpanel.model.stock.GetCurProdAndBillQtyList;
import com.itextpdf.text.log.SysoCounter;
import com.itextpdf.text.log.SysoLogger;

@Controller
public class FinishedGoodStockController {

	public  List<Item> globalItemList;

	List<MCategoryList> filteredCatList;

	int selectedCat;

	GetCurProdAndBillQtyList getCurProdAndBillQtyList = new GetCurProdAndBillQtyList();

	List<GetCurProdAndBillQty> getCurProdAndBillQty;

	List<FinishedGoodStockDetail> showFinStockDetail = new ArrayList<FinishedGoodStockDetail>();

	int isFirstStock = 0;

	FinishedGoodStock showStockHeader;

	@RequestMapping(value = "/showFinishedGoodStock", method = RequestMethod.GET)
	public ModelAndView showFinishedGoodStock(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("stock/finishedGoodStock");
		try {
			Constants.mainAct = 4;
			Constants.subAct = 40;
			String sDate="";
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
			List<Item> itemsList = new ArrayList<>();

			allItemsListResponse = restTemplate.getForObject(Constants.url + "getAllItems", AllItemsListResponse.class);

			itemsList = new ArrayList<Item>();
			itemsList = allItemsListResponse.getItems();
			System.out.println("LIst of items" + itemsList.toString());
			List<Item> tempItemList = itemsList;

			for (int i = 0; i < tempItemList.size(); i++) {

				if (tempItemList.get(i).getItemGrp1() == 3) {
					System.out.println("item removed " + itemsList.get(i).getItemName());

					itemsList.remove(i);

				}

			}
			showFinStockDetail = new ArrayList<FinishedGoodStockDetail>();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("stockStatus", 0);

			FinishedGoodStock stockHeader = restTemplate.postForObject(Constants.url + "getFinGoodStockHeader", map,
					FinishedGoodStock.class);
			DateFormat dfYmd = new SimpleDateFormat("dd-MM-yyyy");

			if (stockHeader != null) {
				showFinStockDetail = new ArrayList<FinishedGoodStockDetail>();
				showStockHeader = stockHeader;
				 sDate = dfYmd.format(stockHeader.getFinGoodStockDate());

				System.out.println("s Date ===" + sDate);
				map = new LinkedMultiValueMap<String, Object>();
				map.add("stockDate", sDate);

				ParameterizedTypeReference<List<FinishedGoodStockDetail>> typeRef = new ParameterizedTypeReference<List<FinishedGoodStockDetail>>() {
				};
				ResponseEntity<List<FinishedGoodStockDetail>> responseEntity = restTemplate.exchange(
						Constants.url + "getFinGoodStockDetailAllCat", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				showFinStockDetail = responseEntity.getBody();
				List<Integer> idList=new ArrayList<Integer>();
				
				
for(int i=0;i<showFinStockDetail.size();i++) {
					
					idList.add(showFinStockDetail.get(i).getItemId());
					
					
}
			
					
					
					
					for(int j=0;j<itemsList.size();  j++) {
						
						if(!idList.contains(itemsList.get(j).getId())) {
							
							FinishedGoodStockDetail d=new FinishedGoodStockDetail();
							d.setCatId(itemsList.get(j).getItemGrp1());
							d.setItemId(itemsList.get(j).getId());
							d.setItemName(itemsList.get(j).getItemName());
							
							System.err.println("New Item Found and added " +d.getItemName());
							showFinStockDetail.add(d);
						}
						
					}
			

			} else {
				System.out.println("showFinStockDetail" + showFinStockDetail.toString());
				showFinStockDetail = new ArrayList<FinishedGoodStockDetail>();
				for (int i = 0; i < itemsList.size(); i++) {
					FinishedGoodStockDetail detail = new FinishedGoodStockDetail();
					detail.setItemName(itemsList.get(i).getItemName());
					detail.setCatId(itemsList.get(i).getItemGrp1());
					detail.setItemId(itemsList.get(i).getId());

					showFinStockDetail.add(detail);
				}

				// assign name to detail Object ;
				isFirstStock = 1;
			}

			// model.addObject("itemsList", itemsList);

			model.addObject("itemsList", showFinStockDetail);
			model.addObject("sDate", sDate);//mahesh code (3 march)
			
			try
			{
				
				List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
				
				ExportToExcel expoExcel=new ExportToExcel();
				List<String> rowData=new ArrayList<String>();
				 
				rowData.add("Sr.No."); 
				rowData.add("Item Name");  
				rowData.add("T1");
				rowData.add("T2");
				rowData.add("T3");  
			 
					
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel); 
				for(int i=0;i<showFinStockDetail.size();i++)
				{
					  expoExcel=new ExportToExcel();
					 rowData=new ArrayList<String>();
					 
				 
					 
					 rowData.add(""+(i+1));  
					rowData.add(""+showFinStockDetail.get(i).getItemName()); 
					rowData.add(""+showFinStockDetail.get(i).getOpT1());
					rowData.add(""+showFinStockDetail.get(i).getOpT2());
					rowData.add(""+showFinStockDetail.get(i).getOpT3());  
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					 
				}
				
				 
				
				HttpSession session = request.getSession();
				session.setAttribute("exportExcelList", exportToExcelList);
				session.setAttribute("excelName", "finishGoodStock");
				
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Exception in generate excel ");
			}

			globalItemList=new ArrayList<>();
			globalItemList = itemsList;
		} catch (Exception e) {

			System.out.println("Exe in showing add Fin good Stock Page " + e.getMessage());
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping(value = "pdf/finishedGoodStockPdf", method = RequestMethod.GET)
	public ModelAndView billWisePdf(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("stock/pdf/finishedGoodStockPdf");
		try {
			Constants.mainAct = 4;
			Constants.subAct = 40;
			String sDate="";
			RestTemplate restTemplate = new RestTemplate();

			CategoryListResponse allCategoryResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
					CategoryListResponse.class);

			List<MCategoryList> catList = allCategoryResponse.getmCategoryList();

			List<MCategoryList> filteredCatList = new ArrayList<MCategoryList>();
			System.out.println("catList :" + catList.toString());

			for (MCategoryList mCategory : catList) {
				if (mCategory.getCatId() != 5 && mCategory.getCatId() != 3) {
					filteredCatList.add(mCategory);

				}
			}

			model.addObject("catList", filteredCatList);
			List<Item> itemsList = new ArrayList<>();

			AllItemsListResponse allItemsListResponse = restTemplate.getForObject(Constants.url + "getAllItems", AllItemsListResponse.class);

			itemsList = new ArrayList<Item>();
			itemsList = allItemsListResponse.getItems();
			System.out.println("LIst of items" + itemsList.toString());
			List<Item> tempItemList = itemsList;

			for (int i = 0; i < tempItemList.size(); i++) {

				if (tempItemList.get(i).getItemGrp1() == 3) {
					System.out.println("item removed " + itemsList.get(i).getItemName());

					itemsList.remove(i);

				}

			}
			List<FinishedGoodStockDetail> showFinStockDetail = new ArrayList<FinishedGoodStockDetail>();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("stockStatus", 0);

			FinishedGoodStock stockHeader = restTemplate.postForObject(Constants.url + "getFinGoodStockHeader", map,
					FinishedGoodStock.class);
			DateFormat dfYmd = new SimpleDateFormat("dd-MM-yyyy");

			if (stockHeader != null) {
				showFinStockDetail = new ArrayList<FinishedGoodStockDetail>(); 
				 sDate = dfYmd.format(stockHeader.getFinGoodStockDate());

				System.out.println("s Date ===" + sDate);
				map = new LinkedMultiValueMap<String, Object>();
				map.add("stockDate", sDate);

				ParameterizedTypeReference<List<FinishedGoodStockDetail>> typeRef = new ParameterizedTypeReference<List<FinishedGoodStockDetail>>() {
				};
				ResponseEntity<List<FinishedGoodStockDetail>> responseEntity = restTemplate.exchange(
						Constants.url + "getFinGoodStockDetailAllCat", HttpMethod.POST, new HttpEntity<>(map), typeRef);

				showFinStockDetail = responseEntity.getBody();
				List<Integer> idList=new ArrayList<Integer>();
				
				
for(int i=0;i<showFinStockDetail.size();i++) {
					
					idList.add(showFinStockDetail.get(i).getItemId());
					
					
}
			
					
					
					
					for(int j=0;j<itemsList.size();  j++) {
						
						if(!idList.contains(itemsList.get(j).getId())) {
							
							FinishedGoodStockDetail d=new FinishedGoodStockDetail();
							d.setCatId(itemsList.get(j).getItemGrp1());
							d.setItemId(itemsList.get(j).getId());
							d.setItemName(itemsList.get(j).getItemName());
							
							System.err.println("New Item Found and added " +d.getItemName());
							showFinStockDetail.add(d);
						}
						
					}
			

			} else {
				System.out.println("showFinStockDetail" + showFinStockDetail.toString());
				showFinStockDetail = new ArrayList<FinishedGoodStockDetail>();
				for (int i = 0; i < itemsList.size(); i++) {
					FinishedGoodStockDetail detail = new FinishedGoodStockDetail();
					detail.setItemName(itemsList.get(i).getItemName());
					detail.setCatId(itemsList.get(i).getItemGrp1());
					detail.setItemId(itemsList.get(i).getId());

					showFinStockDetail.add(detail);
				}

				// assign name to detail Object ;
			 
			}

			// model.addObject("itemsList", itemsList);

			model.addObject("staticlist", showFinStockDetail);
			model.addObject("sDate", sDate);//mahesh code (3 march)
			 
			 
		} catch (Exception e) {

			System.out.println("Exe in showing add Fin good Stock Page " + e.getMessage());
			e.printStackTrace();
		}
		return model;

	}

	AllItemsListResponse allItemsListResponse=new AllItemsListResponse();

	@RequestMapping(value = "/getItemsByCatIdForFinGood", method = RequestMethod.GET)
	public @ResponseBody List<Item> getItemsByCategory(HttpServletRequest request, HttpServletResponse response) {

		RestTemplate restTemplate = new RestTemplate();
		List<Item> itemsList;
		// int catId = Integer.parseInt(request.getParameter("catId"));

		// int option = Integer.parseInt(request.getParameter("option"));
		allItemsListResponse=new AllItemsListResponse();
		allItemsListResponse = restTemplate.getForObject(Constants.url + "getAllItems", AllItemsListResponse.class);

		itemsList = new ArrayList<Item>();
		itemsList = allItemsListResponse.getItems();
		System.out.println("LIst of items" + itemsList.toString());

		/*
		 * else { selectedCat = catId;
		 * 
		 * MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,
		 * Object>(); map.add("itemGrp1", catId);
		 * 
		 * Item[] item = restTemplate.postForObject(Constants.url + "getItemsByCatId",
		 * map, Item[].class); itemsList = new ArrayList<Item>(Arrays.asList(item));
		 * 
		 * System.out.println(" Item List " + itemsList.toString());
		 * 
		 * // Get Current Stock Option
		 * 
		 * System.out.println("Item List Fresh "+itemsList.toString());
		 * 
		 * globalItemList = itemsList; }
		 */

		return itemsList;

	}

	@RequestMapping(value = "/insertOpeningStock", method = RequestMethod.POST)
	public ModelAndView insertOpeningStock(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("stock/finishedGoodStock");
		RestTemplate restTemplate = new RestTemplate();

		if (isFirstStock == 1) {
			System.out.println("Its Opening Stock Insert call");

			FinishedGoodStock goodStockHeader = new FinishedGoodStock();

			List<FinishedGoodStockDetail> finGoodStockList = new ArrayList<>();
			FinishedGoodStockDetail detail;

			for (int i = 0; i < globalItemList.size(); i++) {
				// detail=new FinishedGoodStockDetail();
				// detail=showFinStockDetail.get(i);

				float t1 = Float.parseFloat(request.getParameter("qty1" + globalItemList.get(i).getId()));
				float t2 = Float.parseFloat(request.getParameter("qty2" + globalItemList.get(i).getId()));
				float t3 = Float.parseFloat(request.getParameter("qty3" + globalItemList.get(i).getId()));

				// System.out.println("t1 for Item :" + detail.getItemName() + ":" + t1);
				// System.out.println("t2 for Item :" + detail.getItemName() + ":" + t2);
				// System.out.println("t3 for Item :" + detail.getItemName() + ":" + t3);

				FinishedGoodStockDetail finGoodStockDetail = new FinishedGoodStockDetail();

				finGoodStockDetail.setItemId(globalItemList.get(i).getId());
				finGoodStockDetail.setItemName(globalItemList.get(i).getItemName());
				finGoodStockDetail.setOpT1(t1);
				finGoodStockDetail.setOpT2(t2);
				finGoodStockDetail.setOpT3(t3);
				finGoodStockDetail.setOpTotal(t1 + t2 + t3);
				finGoodStockDetail.setStockDate(new Date());
				finGoodStockDetail.setDelStatus(0);
				finGoodStockDetail.setCatId(globalItemList.get(i).getItemGrp1());
				finGoodStockList.add(finGoodStockDetail);

			}

			goodStockHeader.setCatId(selectedCat);
			goodStockHeader.setFinGoodStockDate(new Date());
			goodStockHeader.setFinGoodStockStatus(0);
			goodStockHeader.setDelStatus(0);
			goodStockHeader.setFinishedGoodStockDetail(finGoodStockList);

			model.addObject("catList", filteredCatList);

			Info info = restTemplate.postForObject(Constants.url + "insertFinishedGoodOpStock", goodStockHeader,
					Info.class);

			isFirstStock = 0;

		} // end of if isFirstStock
		else if (isFirstStock == 0) {

			System.out.println("Its Opening Stock Update call");

			FinishedGoodStockDetail detail;
			List<FinishedGoodStockDetail> finGoodStockList = new ArrayList<>();

			for (int i = 0; i < showFinStockDetail.size(); i++) {

				detail = showFinStockDetail.get(i);

				float t1 = Float.parseFloat(request.getParameter("qty1" + detail.getItemId()));
				float t2 = Float.parseFloat(request.getParameter("qty2" + detail.getItemId()));
				float t3 = Float.parseFloat(request.getParameter("qty3" + detail.getItemId()));

				System.out.println("t1 for Item :" + detail.getItemName() + ":" + t1);
				System.out.println("t2 for Item :" + detail.getItemName() + ":" + t2);
				System.out.println("t3 for Item :" + detail.getItemName() + ":" + t3);

				/*
				 * detail.setItemId(detail.getItemId());
				 * detail.setItemName(detail.getItemName());
				 */
				detail.setOpT1(t1);
				detail.setOpT2(t2);
				detail.setOpT3(t3);
				detail.setOpTotal(t1 + t2 + t3);
				/*
				 * detail.setStockDate(new Date()); detail.setDelStatus(0);
				 * detail.setCatId(detail.getCatId());
				 */
				finGoodStockList.add(detail);

			}
			showStockHeader.setFinishedGoodStockDetail(finGoodStockList);

			Info info = restTemplate.postForObject(Constants.url + "insertFinishedGoodOpStock", showStockHeader,
					Info.class);

		}

		return model;

	}

	@RequestMapping(value = "/showFinishGoodStock", method = RequestMethod.GET)
	public ModelAndView showFinGoodStock(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("stock/showFinGoodStock");
		Constants.mainAct = 4;
		Constants.subAct = 36;

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
	public @ResponseBody FinGoodBean getFinGoodStock(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside get Fin good Ajax Call");
		FinGoodBean bean = new FinGoodBean();
		RestTemplate restTemplate = new RestTemplate();

		int catId = Integer.parseInt(request.getParameter("catId"));
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		if (catId != -1)
			selectedCat = catId;

		int option = Integer.parseInt(request.getParameter("option"));
		List<FinishedGoodStockDetail> updateStockDetailList = new ArrayList<>();

		try {

			// Get Current Stock For Finished Good
			if (option == 1) {
				int isDayEndEnable = 0;
				System.out.println("Cat ID OPTION ***********" + selectedCat);

				System.out.println("inside option 1");

				DateFormat dfYmd = new SimpleDateFormat("yyyy-MM-dd");

				map = new LinkedMultiValueMap<String, Object>();
				map.add("stockStatus", 0);

				FinishedGoodStock stockHeader = restTemplate.postForObject(Constants.url + "getFinGoodStockHeader", map,
						FinishedGoodStock.class);

				System.out.println("stock Header " + stockHeader.toString());

				Date stockDate = stockHeader.getFinGoodStockDate();
				
				bean.setStockDate(new SimpleDateFormat("dd-MM-yyyy").format(stockDate));
				String timestamp = stockHeader.getTimestamp();

				Date curDate = new Date();

				List<GetCurProdAndBillQty> getCurProdAndBillQty = new ArrayList<>();

				if (selectedCat == -1 || selectedCat == 0) {

					if (stockDate.before(curDate) || curDate.equals(stockDate)) {
						System.out.println("Current Date is After Stock Date Allow to End Previous Days Day End  ");

						isDayEndEnable = 1;// Allow to End A day
					} else {

						isDayEndEnable = 0;

					}
					bean.setIsDayEndEnable(isDayEndEnable);

					System.out.println("Inside If Selected Cat iD ==-1");
					map = new LinkedMultiValueMap<String, Object>();

					System.out.println("stock date " + stockDate);
					String prodDate = dfYmd.format(stockDate);
					map.add("prodDate", prodDate);
					// map.add("catId", selectedCat);

					map.add("timestamp", timestamp);
					map.add("delStatus", 0);

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					map.add("curTimeStamp", dateFormat.format(cal.getTime()));

					getCurProdAndBillQtyList = restTemplate.postForObject(
							Constants.url + "getCurrentProdAndBillQtyAllCat", map, GetCurProdAndBillQtyList.class);

				} else if (selectedCat != -1 || selectedCat != 0) {
					System.out.println("Else Specific catId Selected ");
					map = new LinkedMultiValueMap<String, Object>();
					System.out.println("stock date " + stockDate);
					String prodDate = dfYmd.format(stockDate);
					map.add("prodDate", prodDate);
					map.add("catId", selectedCat);
					map.add("timestamp", timestamp);
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();
					map.add("curTimeStamp", dateFormat.format(cal.getTime()));
					map.add("delStatus", 0);

					getCurProdAndBillQtyList = restTemplate.postForObject(Constants.url + "getCurrentProdAndBillQty",
							map, GetCurProdAndBillQtyList.class);

					bean.setIsDayEndEnable(0);
				}

				getCurProdAndBillQty = getCurProdAndBillQtyList.getGetCurProdAndBillQty();

				System.out.println("Cur Prod And Bill Qty Listy " + getCurProdAndBillQty.toString());
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

				String stkDate = df.format(stockDate);

				System.out.println("Stock date for getting stock bet date " + stkDate);
				
				List<FinishedGoodStockDetail> finGoodDetail = new ArrayList<>();

				if (selectedCat == -1 || selectedCat == 0) {

					map = new LinkedMultiValueMap<String, Object>();
					map.add("stockDate", stkDate);

					ParameterizedTypeReference<List<FinishedGoodStockDetail>> typeRef = new ParameterizedTypeReference<List<FinishedGoodStockDetail>>() {
					};
					ResponseEntity<List<FinishedGoodStockDetail>> responseEntity = restTemplate.exchange(
							Constants.url + "getFinGoodStockDetailAllCat", HttpMethod.POST, new HttpEntity<>(map),
							typeRef);
					finGoodDetail = new ArrayList<>();
					finGoodDetail = responseEntity.getBody();

					System.out.println("Detail IS for All Cat " + finGoodDetail.toString());

				} else {

					map = new LinkedMultiValueMap<String, Object>();
					map.add("stockDate", stkDate);
					map.add("catId", selectedCat);

					ParameterizedTypeReference<List<FinishedGoodStockDetail>> typeRef = new ParameterizedTypeReference<List<FinishedGoodStockDetail>>() {
					};
					ResponseEntity<List<FinishedGoodStockDetail>> responseEntity = restTemplate.exchange(
							Constants.url + "getFinGoodStockDetail", HttpMethod.POST, new HttpEntity<>(map), typeRef);

					finGoodDetail = new ArrayList<>();

					finGoodDetail = responseEntity.getBody();

					System.out.println("Detail IS Specific Cat " + finGoodDetail.toString());
					bean.setIsDayEndEnable(0);

				}
				// new code

				FinishedGoodStockDetail stockDetail = new FinishedGoodStockDetail();
				GetCurProdAndBillQty curProdBilQty = new GetCurProdAndBillQty();

				for (int i = 0; i < getCurProdAndBillQty.size(); i++) {

					curProdBilQty = getCurProdAndBillQty.get(i);

					for (int j = 0; j < finGoodDetail.size(); j++) {

						stockDetail = finGoodDetail.get(j);

						if (curProdBilQty.getId() == stockDetail.getItemId()) {

							/*
							 * System.out.println( "item Id Matched " + curProdBilQty.getId() + "and " +
							 * stockDetail.getItemId());
							 */
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

							// stockDetail.setIsDayEndEnable(isDayEndEnable);

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

							// System.out.println("---------");
							// System.out.println("bill Qty = " + curProdBilQty.getBillQty());
							// System.out.println(" for Item Id " + curProdBilQty.getId());
							// System.out.println("a =" + a + "b = " + b + "c= " + c);
							float damagedQty = curProdBilQty.getDamagedQty();

							float curIssue = billQty - (a + b + c);

							// System.out.println("cur Issue qty =" + curIssue);

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

							

						} // end of if isSameItem =true
					} // end of Inner For Loop
				} // End of outer For loop

				// end of new code

			} // end of Option 1

			if (option == 3) {

				System.out.println("Inside Option 3 stock bet Date  ");

				String fromDate = request.getParameter("from_datepicker");

				String toDate = request.getParameter("to_datepicker");

				System.out.println("from Date " + fromDate);
				System.out.println("to Date " + toDate);

				updateStockDetailList = new ArrayList<FinishedGoodStockDetail>();

				DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-uuuu");
				LocalDate tDate = LocalDate.parse(toDate, f);

				if (tDate.isAfter(LocalDate.now()) || tDate.isEqual(LocalDate.now())) {
					System.out.println("    Date is greater than today" + LocalDate.now().minus(Period.ofDays(1)));
					tDate = LocalDate.now().minus(Period.ofDays(1));

				}

				map = new LinkedMultiValueMap<String, Object>();

				// map.add("stockStatus", 1);
				map.add("fromDate", fromDate);
				map.add("toDate", "" + toDate);

				if (selectedCat == -1 || selectedCat == 0) {
					map = new LinkedMultiValueMap<String, Object>();
					System.out.println("If All Category Selected Option");
					// map.add("stockStatus", 1);
					map.add("fromDate", fromDate);
					map.add("toDate", "" + toDate);
					updateStockDetailList=new ArrayList<>();

					ParameterizedTypeReference<List<FinishedGoodStockDetail>> typeRef = new ParameterizedTypeReference<List<FinishedGoodStockDetail>>() {
					};
					ResponseEntity<List<FinishedGoodStockDetail>> responseEntity = restTemplate.exchange(
							Constants.url + "getFinGoodStockBetTwoDate", HttpMethod.POST, new HttpEntity<>(map),
							typeRef);

					updateStockDetailList = responseEntity.getBody();
				} else {

					System.out.println("If Specific Category Selected Option Cat ID =" + selectedCat);
					updateStockDetailList=new ArrayList<>();
					map = new LinkedMultiValueMap<String, Object>();

					map.add("catId", selectedCat);
					map.add("fromDate", fromDate);
					map.add("toDate", "" + toDate);
					ParameterizedTypeReference<List<FinishedGoodStockDetail>> typeRef = new ParameterizedTypeReference<List<FinishedGoodStockDetail>>() {
					};
					ResponseEntity<List<FinishedGoodStockDetail>> responseEntity = restTemplate.exchange(
							Constants.url + "getFinGoodStockBetTwoDateByCat", HttpMethod.POST, new HttpEntity<>(map),
							typeRef);

					updateStockDetailList = responseEntity.getBody();

				}
			}

			System.out.println("View Finish good Stock List " + updateStockDetailList.toString());
		} catch (Exception e) {
			System.out.println("Error In Getting Finished good  Stock " + e.getMessage());
			e.printStackTrace();
		}

		selectedCat = 0;

		System.out.println("isDayEnd " + bean.getIsDayEndEnable());
		bean.setStockDetail(updateStockDetailList);
		
		System.out.println("Final Bean Fin Good Stock : "+bean.toString());
		
		try
		{
			
			List<ExportToExcel> exportToExcelList=new ArrayList<ExportToExcel>();
			
			ExportToExcel expoExcel=new ExportToExcel();
			List<String> rowData=new ArrayList<String>();
			 
			rowData.add("Sr.No."); 
			rowData.add("Item Name");  
			rowData.add("T");
			rowData.add("T-1");
			rowData.add("T-2"); 
			rowData.add("Op Total"); 
			rowData.add("Prod Qty"); 
			rowData.add("Rej Qty"); 
			
			rowData.add("Bill Qty"); 
			rowData.add("Dummy Qty");  
			rowData.add("Current Closing ");
			rowData.add("Close T");
			rowData.add("Close T1");
			rowData.add("Close T2"); 
			rowData.add("Total Closing"); 
		 
				
			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel); 
			for(int i=0;i<bean.getStockDetail().size();i++)
			{
				  expoExcel=new ExportToExcel();
				 rowData=new ArrayList<String>();
				 
			 
				 
				 rowData.add(""+(i+1));  
				rowData.add(""+bean.getStockDetail().get(i).getItemName()); 
				rowData.add(""+bean.getStockDetail().get(i).getOpT1());
				rowData.add(""+bean.getStockDetail().get(i).getOpT2());
				rowData.add(""+bean.getStockDetail().get(i).getOpT3()); 
				rowData.add(""+bean.getStockDetail().get(i).getOpTotal()); 
				rowData.add(""+bean.getStockDetail().get(i).getProdQty());
				rowData.add(""+bean.getStockDetail().get(i).getRejQty()); 
				
				rowData.add(""+bean.getStockDetail().get(i).getFrSaleQty()); 
				rowData.add(""+bean.getStockDetail().get(i).getGateSaleQty()); 
				rowData.add(""+bean.getStockDetail().get(i).getCloCurrent()); 
				rowData.add(""+bean.getStockDetail().get(i).getCloT1()); 
				rowData.add(""+bean.getStockDetail().get(i).getCloT2()); 
				rowData.add(""+bean.getStockDetail().get(i).getCloT3());  
				rowData.add(""+bean.getStockDetail().get(i).getTotalCloStk()); 
				
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				 
			}
			
			 
			
			HttpSession session = request.getSession();
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "currentStock");
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception in generate excel ");
		}
		return bean;

	}

	@RequestMapping(value = "/finishedGoodDayEnd", method = RequestMethod.POST)

	public ModelAndView finishedGoodDayEnd(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("stock/showFinGoodStock");

		System.out.println("Inside Finished Good Day End ");

		// Constants.mainAct = 12;
		// Constants.subAct = 123;
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
			// map.add("catId", selectedCat);
			map.add("timestamp", stockHeader.getTimestamp());
			map.add("delStatus", 0);

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			map.add("curTimeStamp", dateFormat.format(cal.getTime()));

			getCurProdAndBillQtyList = restTemplate.postForObject(Constants.url + "getCurrentProdAndBillQtyAllCat", map,
					GetCurProdAndBillQtyList.class);

			getCurProdAndBillQty = getCurProdAndBillQtyList.getGetCurProdAndBillQty();

			System.out.println("Cur Prod And Bill Qty Listy " + getCurProdAndBillQty.toString());
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

			String stkDate = df.format(stockDate);
			map = new LinkedMultiValueMap<String, Object>();
			map.add("stockDate", stkDate);

			ParameterizedTypeReference<List<FinishedGoodStockDetail>> typeRef = new ParameterizedTypeReference<List<FinishedGoodStockDetail>>() {
			};
			ResponseEntity<List<FinishedGoodStockDetail>> responseEntity = restTemplate.exchange(
					Constants.url + "getFinGoodStockDetailAllCat", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			List<FinishedGoodStockDetail> finGoodDetail = responseEntity.getBody();

			System.out.println("Finished Good Stock Detail " + finGoodDetail.toString());

			FinishedGoodStockDetail stockDetail = new FinishedGoodStockDetail();
			GetCurProdAndBillQty curProdBilQty = new GetCurProdAndBillQty();

			List<FinishedGoodStockDetail> updateStockDetailList = new ArrayList<>();

			for (int i = 0; i < getCurProdAndBillQty.size(); i++) {

				curProdBilQty = getCurProdAndBillQty.get(i);

				for (int j = 0; j < finGoodDetail.size(); j++) {

					stockDetail = finGoodDetail.get(j);

					if (curProdBilQty.getId() == stockDetail.getItemId()) {

						// System.out
						// .println("item Id Matched " + curProdBilQty.getId() + "and " +
						// stockDetail.getItemId());

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

						// System.out.println("t1 : " + t1 + " t2: " + t2 + " t3: " + t3);

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

						// System.out.println("---------");
						// System.out.println("bill Qty = " + curProdBilQty.getBillQty());
						// System.out.println(" for Item Id " + curProdBilQty.getId());
						// System.out.println("a =" + a + "b = " + b + "c= " + c);
						float damagedQty = curProdBilQty.getDamagedQty();

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

						// System.out.println("closing Qty : t1 " + cloT1 + " t2 " + cloT2 + " t3 " +
						// cloT3);

						// System.out.println("cur Closing " + curClosing);
						// System.out.println("total closing " + totalClosing);

						// System.out.println("---------");

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

			FinishedGoodStock nextDayOpStockHeader = new FinishedGoodStock();

			nextDayOpStockHeader.setCatId(selectedCat);
			nextDayOpStockHeader.setFinGoodStockDate(finGoodStkDate);
			nextDayOpStockHeader.setFinGoodStockStatus(0);
			nextDayOpStockHeader.setDelStatus(0);

			List<FinishedGoodStockDetail> nextDayStockDetail = new ArrayList<>();
			for (int i = 0; i < updateStockDetailList.size(); i++) {

				FinishedGoodStockDetail finStkDetail = new FinishedGoodStockDetail();

				FinishedGoodStockDetail prevDetail = updateStockDetailList.get(i);

				finStkDetail.setOpT1(prevDetail.getCloCurrent());
				finStkDetail.setOpT2(prevDetail.getCloT1());
				finStkDetail.setOpT3(prevDetail.getCloT2());
				finStkDetail.setOpTotal(finStkDetail.getOpT1() + finStkDetail.getOpT2() + finStkDetail.getOpT3());
				finStkDetail.setItemId(prevDetail.getItemId());
				finStkDetail.setItemName(prevDetail.getItemName());
				finStkDetail.setStockDate(finGoodStkDate);
				finStkDetail.setCatId(prevDetail.getCatId());
				finStkDetail.setDelStatus(0);

				nextDayStockDetail.add(finStkDetail);
			}

			nextDayOpStockHeader.setFinishedGoodStockDetail(nextDayStockDetail);

			Info nextDayInsert = restTemplate.postForObject(Constants.url + "insertFinishedGoodOpStock",
					nextDayOpStockHeader, Info.class);

		} catch (Exception e) {

			System.out.println("Exce in Getting cur Prod And Bill Qty  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}
	
	private Dimension format = PD4Constants.A2;
	private boolean landscapeValue = false;
	private int topValue = 8;
	private int leftValue = 0;
	private int rightValue = 0;
	private int bottomValue =8;
	private String unitsValue = "m";
	private String proxyHost = "";
	private int proxyPort = 0;

	private int userSpaceWidth = 750;
	private static int BUFFER_SIZE = 1024;

	@RequestMapping(value = "/finishedGoodStockPdfFnction", method = RequestMethod.GET)
	public void showPDF(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside PDf For Report URL ");
		String url = request.getParameter("url");
		System.out.println("URL " + url);
		
		File f = new File("/opt/tomcat-latest/webapps/uploads/Inward.pdf");
		//File f = new File("C:/pdf/ordermemo221.pdf");

		try {
			runConverter(Constants.ReportURL + url, f,request,response);
			//runConverter("www.google.com", f,request,response);

		} catch (IOException e) {

			System.out.println("Pdf conversion exception " + e.getMessage());
		}

		// get absolute path of the application
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("");
		 //String filePath = "C:/pdf/ordermemo221.pdf";

		String filePath = "/opt/tomcat-latest/webapps/uploads/Inward.pdf";

		// construct the complete absolute path of the file
		String fullPath = appPath + filePath;
		File downloadFile = new File(filePath);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(downloadFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			// get MIME type of the file
			String mimeType = context.getMimeType(fullPath);
			if (mimeType == null) {
				// set to binary type if MIME mapping not found
				mimeType = "application/pdf";
			}
			System.out.println("MIME type: " + mimeType);

			String headerKey = "Content-Disposition";

			// response.addHeader("Content-Disposition", "attachment;filename=report.pdf");
			response.setContentType("application/pdf");

			OutputStream outStream;

			outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;


			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outStream.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void runConverter(String urlstring, File output, HttpServletRequest request, HttpServletResponse response ) throws IOException {

		if (urlstring.length() > 0) {
			if (!urlstring.startsWith("http://") && !urlstring.startsWith("file:")) {
				urlstring = "http://" + urlstring;
			}
			System.out.println("PDF URL " + urlstring);
			java.io.FileOutputStream fos = new java.io.FileOutputStream(output);

			PD4ML pd4ml = new PD4ML();
		
			try {

				Dimension landscapeA4 = pd4ml.changePageOrientation(PD4Constants.A4);
				pd4ml.setPageSize(landscapeA4);
			
				PD4PageMark footer = new PD4PageMark();  
				
	            footer.setPageNumberTemplate("Page $[page] of $[total]");  
	            footer.setPageNumberAlignment(PD4PageMark.RIGHT_ALIGN);  
	            footer.setFontSize(10);  
	            footer.setAreaHeight(20);     
	            
	            pd4ml.setPageFooter(footer); 
				
			} catch (Exception e) {
				System.out.println("Pdf conversion method excep " + e.getMessage());
			}

			if (unitsValue.equals("mm")) {
				pd4ml.setPageInsetsMM(new Insets(topValue, leftValue, bottomValue, rightValue));
			} else {
				pd4ml.setPageInsets(new Insets(topValue, leftValue, bottomValue, rightValue));
			}

			pd4ml.setHtmlWidth(userSpaceWidth);

			pd4ml.render(urlstring, fos);
		}
	}

}
