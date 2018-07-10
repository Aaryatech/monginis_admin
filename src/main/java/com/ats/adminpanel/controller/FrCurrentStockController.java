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
import com.ats.adminpanel.model.AllFrIdName;
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.AllRoutesListResponse;
import com.ats.adminpanel.model.FrIdNames;
import com.ats.adminpanel.model.ItemNameId;
import com.ats.adminpanel.model.ItemNameIdList;
import com.ats.adminpanel.model.Route;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteId;
import com.ats.adminpanel.model.franchisee.FrNameIdByRouteIdResponse;
import com.ats.adminpanel.model.item.CategoryListResponse;
import com.ats.adminpanel.model.item.Item;
import com.ats.adminpanel.model.item.MCategoryList;
import com.ats.adminpanel.model.stock.PostFrItemStockHeader;


@Controller
public class FrCurrentStockController {
	
	public AllFrIdNameList allFrIdNameList = new AllFrIdNameList();
	public  List<MCategoryList> itemsWithCategoriesList;

	@RequestMapping(value = "/showfrCurStock")
	public ModelAndView showGenerateBill(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
	
		try {
			model = new ModelAndView("frCurStock");
			RestTemplate restTemplate = new RestTemplate();
		
			// get Routes

			AllRoutesListResponse allRouteListResponse = restTemplate.getForObject(Constants.url + "showRouteList",
					AllRoutesListResponse.class);

			List<Route> routeList = new ArrayList<Route>();

			routeList = allRouteListResponse.getRoute();

			// end get Routes

			allFrIdNameList = new AllFrIdNameList();
			try {

				allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

			} catch (Exception e) {
				System.out.println("Exception in getAllFrIdName" + e.getMessage());
				e.printStackTrace();

			}
			
			
			
			
			
			List<AllFrIdName> selectedFrListAll = new ArrayList();

		//	System.out.println(" Fr " + allFrIdNameList.getFrIdNamesList());
			
			
			
			CategoryListResponse itemsWithCategoryResponseList = restTemplate
					.getForObject(Constants.url + "showAllCategory", CategoryListResponse.class);

			itemsWithCategoriesList = itemsWithCategoryResponseList.getmCategoryList();

			model.addObject("ItemIdCategory", itemsWithCategoriesList);

			model.addObject("unSelectedFrList", allFrIdNameList.getFrIdNamesList());

			model.addObject("routeList", routeList);
			

		} catch (Exception e) {

			System.out.println("Exc in show frCurrentStock " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}
	
	
	
	ArrayList<Item> itemList = null;

	@RequestMapping(value = "/getItemByCateId", method = RequestMethod.GET)
	public @ResponseBody List<Item> getItemByCateId(HttpServletRequest request, HttpServletResponse response) {

		//ModelAndView model = new ModelAndView("items/itemlist");
		ModelAndView model = new ModelAndView("items/itemConfig");
		
		//Constants.mainAct = 4;
		//Constants.subAct = 44;
		try {

			int catId=0;
			
		String	catIds = request.getParameter("catId");
		
		if(catIds==null || catIds=="") {
			catId=catId;
		}else {
			catId=Integer.parseInt(catIds);
		}
			System.out.println("cat Id "+catId);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			//MultiValueMap<String, Object> mapItemList = new LinkedMultiValueMap<String, Object>();

			map.add("itemGrp1", catId);

			RestTemplate restTemplate = new RestTemplate();

			Item[] item = restTemplate.postForObject(Constants.url + "getItemsByCatId", map, Item[].class);

			 itemList = new ArrayList<Item>(Arrays.asList(item));

		}catch (Exception e) {
			
			System.err.println("Exce in FrCurStock Cont @items by Cat Id Ajax call " +e.getMessage());
			
			e.printStackTrace();
		}
		return itemList;
	}
	
	
	
	
	public String selectedFrArray;
	public List<String> frList = new ArrayList<>();

	
	
	@RequestMapping(value = "/getFrStock", method = RequestMethod.GET)
	public @ResponseBody ViewStockBackEnd getFrStock(HttpServletRequest request, HttpServletResponse response) {

		System.err.println("In Ajax Call get Fr Stock"); 

		selectedFrArray = null;
	List<String> itemsList=new ArrayList<>();
	List<ItemNameId> itemNameList=new ArrayList<ItemNameId>();

		List<ViewFrStockBackEnd> currentStockDetailList =new ArrayList<ViewFrStockBackEnd>();
		ViewStockBackEnd backEndStock=new ViewStockBackEnd();
		try {

			String selectedFr = request.getParameter("fr_id_list");
			String selectedItems = request.getParameter("item_id_list");
			String routeId = request.getParameter("route_id");
			System.err.println("Selected Fr before " +selectedFr);
			int catId=Integer.parseInt(request.getParameter("cat_id"));
			
			selectedFr = selectedFr.substring(1, selectedFr.length() - 1);
			selectedFr = selectedFr.replaceAll("\"", "");
			System.err.println("Selected Fr after " +selectedFr);

			selectedItems = selectedItems.substring(1, selectedItems.length() - 1);
			selectedItems = selectedItems.replaceAll("\"", "");

List<String> selectedItemList=new ArrayList<>();

selectedItemList=Arrays.asList(selectedItems);

			frList = new ArrayList<>();
			frList = Arrays.asList(selectedFr);
			List<AllFrIdName> frIdNamesList=allFrIdNameList.getFrIdNamesList();
			List<FrIdNames> frIdNamesLists=new ArrayList<FrIdNames>();



			itemsList= Arrays.asList(selectedItems);
			System.err.println("**** Selected Item s  " +selectedItems);
			System.err.println("**** Selected Item itemList " +itemsList);

			// route-wise billing

			if (!routeId.equalsIgnoreCase("0")) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				RestTemplate restTemplate = new RestTemplate();

				map.add("routeId", routeId);

				FrNameIdByRouteIdResponse frNameId = restTemplate.postForObject(Constants.url + "getFrNameIdByRouteId",
						map, FrNameIdByRouteIdResponse.class);

				List<FrNameIdByRouteId> frNameIdByRouteIdList = frNameId.getFrNameIdByRouteIds();

				System.out.println("route wise franchisee " + frNameIdByRouteIdList.toString());

				StringBuilder sbForRouteFrId = new StringBuilder();
				for (int i = 0; i < frNameIdByRouteIdList.size(); i++) {

					sbForRouteFrId = sbForRouteFrId.append(frNameIdByRouteIdList.get(i).getFrId().toString() + ",");

				}

				String strFrIdRouteWise = sbForRouteFrId.toString();
				selectedFr = strFrIdRouteWise.substring(0, strFrIdRouteWise.length() - 1);
				System.out.println("fr Id Route WISE = " + selectedFr);

			} // end of if

			// end of route wise billing
			PostFrItemStockHeader frItemStockHeader;

			System.out.println("Fr List  " +frList);
			try {
				
				int runningMonth = 0;

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				RestTemplate restTemplate = new RestTemplate();

				//for(String fr:frList) {
					
				String[] frIdArr=selectedFr.split(",");
				System.out.println("Fr frIdArr  " +frIdArr.toString());

					//for(int k=0;k<frList.size();k++) {
				for(int k=0;k<frIdArr.length;k++) {

						String frId=frIdArr[k];
					
					System.err.println("fr Id from frIDArray " +frId);
					
					 map = new LinkedMultiValueMap<String, Object>();
						map.add("frId", frId);

					frItemStockHeader = restTemplate.postForObject(Constants.url + "getRunningMonth", map,
							PostFrItemStockHeader.class);

					System.out.println("Fr Opening Stock " + frItemStockHeader.toString());
					runningMonth = frItemStockHeader.getMonth();
					
				}
				
				
				//
				
				
				for(int k=0;k<frIdArr.length;k++) {
					
					for(int b=0;b<frIdNamesList.size();b++) {
						if(Integer.parseInt(frIdArr[k])==frIdNamesList.get(b).getFrId()) {
							System.err.println("Fr ID Matched ");
							FrIdNames frNames=new  FrIdNames();
							
							frNames.setFrId(frIdNamesList.get(b).getFrId());
							frNames.setFrName(frIdNamesList.get(b).getFrName());
							frIdNamesLists.add(frNames);
						}
					}
				}
				
				System.err.println("//// Fr Names ss " +frIdNamesLists.toString());
String[] selItemArr=selectedItems.split(",");
for(int k=0;k<selItemArr.length;k++) {
	
	for(int b=0;b<itemList.size();b++) {
		if(Integer.parseInt(selItemArr[k])==itemList.get(b).getId()) {
			System.err.println("Item ID Matched ");
			ItemNameId itemNameId=new  ItemNameId();
			
			itemNameId.setItemId(itemList.get(b).getId());
			itemNameId.setItemName(itemList.get(b).getItemName());
			itemNameList.add(itemNameId);
		}
	}
}

				
				//
					
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					DateFormat yearFormat = new SimpleDateFormat("yyyy");

					Date todaysDate = new Date();
					System.out.println(dateFormat.format(todaysDate));

					Calendar cal = Calendar.getInstance();
					cal.setTime(todaysDate);

					cal.set(Calendar.DAY_OF_MONTH, 1);

					Date firstDay = cal.getTime();

					System.out.println("First Day of month " + firstDay);

					String strFirstDay = dateFormat.format(firstDay);

					System.out.println("Year " + yearFormat.format(todaysDate));
					boolean isMonthCloseApplicable = false;

				
						map = new LinkedMultiValueMap<String, Object>();


						DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
						Date date = new Date();
						System.out.println(dateFormat1.format(date));

						Calendar cal1 = Calendar.getInstance();
						cal1.setTime(date);

						int dayOfMonth = cal1.get(Calendar.DATE);

						int calCurrentMonth = cal1.get(Calendar.MONTH) + 1;
						System.out.println("Current Cal Month " + calCurrentMonth);

						System.out.println("Day Of Month is: " + dayOfMonth);

						if (runningMonth < calCurrentMonth) {

							isMonthCloseApplicable = true;
							System.out.println("Day Of Month End ......");

						}

						if (isMonthCloseApplicable) {
							System.err.println("### Inside iMonthclose app");
							String strDate;
							int year;
							if (runningMonth == 12) {
								System.err.println("running month =12");
								year = (Calendar.getInstance().getWeekYear() - 1);
								System.err.println("year value " + year);
							} else {
								System.err.println("running month not eq 12");
								year = Calendar.getInstance().getWeekYear();
								System.err.println("year value " + year);
							}

							// strDate="01/"+runningMonth+"/"+year;

							strDate = year + "/" + runningMonth + "/01";

							map.add("fromDate", strDate);
						} else {

							map.add("fromDate", dateFormat.format(firstDay));

						}

						map.add("frId", selectedFr);
						map.add("itemIdList", selectedItems);

						map.add("frStockType",7);
						//map.add("fromDate", dateFormat1.format(firstDay));
						map.add("toDate", dateFormat.format(todaysDate));
						map.add("currentMonth", String.valueOf(runningMonth));
						map.add("year", yearFormat.format(todaysDate));
						map.add("catId", catId);

						ParameterizedTypeReference<List<ViewFrStockBackEnd>> typeRef2 = new ParameterizedTypeReference<List<ViewFrStockBackEnd>>() {
						};
						ResponseEntity<List<ViewFrStockBackEnd>> responseEntity2 = restTemplate
								.exchange(Constants.url + "getCurrentStockBackEnd", HttpMethod.POST, new HttpEntity<>(map), typeRef2);

						currentStockDetailList = responseEntity2.getBody();
						//System.out.println("Current Stock Details : " + currentStockDetailList.toString());

					
					
				System.out.println("Current Stock Details : " + currentStockDetailList.toString());

					
			} catch (Exception e) {
				System.out.println("Exception " + e.getMessage());
				e.printStackTrace();
			}

			System.out.println("Current Stock Details : " + currentStockDetailList.toString());
			backEndStock.setCurrentStockDetailList(currentStockDetailList);
			backEndStock.setFrIdNamesList(frIdNamesLists);
			backEndStock.setItemList(itemNameList);
		
	}catch (Exception e) {
		System.out.println("Exception last catch " + e.getMessage());
		e.printStackTrace();
	}
		return backEndStock;
		
	}
	
}
