package com.ats.adminpanel.controller;

import java.text.DateFormat;
import java.text.ParseException;
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
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.MCategory;
import com.ats.adminpanel.model.franchisee.CommonConf;
import com.ats.adminpanel.model.franchisee.Menu;
import com.ats.adminpanel.model.item.CategoryListResponse;
import com.ats.adminpanel.model.item.Item;
import com.ats.adminpanel.model.item.MCategoryList;
import com.ats.adminpanel.model.production.GetOrderItemQty;
import com.ats.adminpanel.model.production.GetProductionItemQty;
import com.ats.adminpanel.model.production.GetRegSpCakeOrderQty;
import com.ats.adminpanel.model.production.PlanQtyAjaxResponse;
import com.ats.adminpanel.model.production.PostProdPlanHeader;
import com.ats.adminpanel.model.production.PostProductionDetail;
import com.ats.adminpanel.model.production.PostProductionHeader;
import com.ats.adminpanel.model.production.PostProductionPlanDetail;
import com.ats.adminpanel.model.stock.FinishedGoodStock;
import com.ats.adminpanel.model.stock.FinishedGoodStockDetail;
import com.ats.adminpanel.model.stock.GetCurProdAndBillQty;
import com.ats.adminpanel.model.stock.GetCurProdAndBillQtyList;

@Controller
public class ProdForcastingController {

	List<MCategoryList> filteredCatList;
	public static List<GetProductionItemQty> getProdItemQtyList;
	public static int[] timeSlot;
	public static String productionDate;
	public static int selectedCat;
	public static List<Item> globalItemList; 
	
	
	GetCurProdAndBillQtyList getCurProdAndBillQtyList = new GetCurProdAndBillQtyList();


	@RequestMapping(value = "/showProdForcast", method = RequestMethod.GET)
	public ModelAndView showProdForcasting(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("production/prodForcasting");

		Constants.mainAct = 4;
		Constants.subAct = 34;

		try {

			RestTemplate restTemplate = new RestTemplate();

			CategoryListResponse allCategoryResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
					CategoryListResponse.class);

			List<MCategoryList> catList = allCategoryResponse.getmCategoryList();

			filteredCatList = new ArrayList<MCategoryList>();
			System.out.println("catList :" + catList.toString());

			for (MCategoryList mCategory : catList) {
				if (mCategory.getCatId() != 5 && mCategory.getCatId() != 6 && mCategory.getCatId() != 1 && mCategory.getCatId() != 3) {
					filteredCatList.add(mCategory);

				}
			}
			int productionTimeSlot = 0;
			try {

				productionTimeSlot = restTemplate.getForObject(Constants.url + "getProductionTimeSlot", Integer.class);
				System.out.println("time slot  " + productionTimeSlot);
			} catch (Exception e) {
				// System.out.println(e.getMessage());
				// e.printStackTrace();

			}

			timeSlot = new int[productionTimeSlot];
			for (int i = 0; i < productionTimeSlot; i++)
				timeSlot[i] = i + 1;

			model.addObject("productionTimeSlot", timeSlot);

			model.addObject("catList", filteredCatList);

		} catch (Exception e) {
			System.out.println("Exception in Show Production Forecasting.");

			filteredCatList = new ArrayList<MCategoryList>();
			model.addObject("catList", filteredCatList);

		}
		return model;
	}

	// ----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/getItemsByCategory", method = RequestMethod.GET)
	public @ResponseBody List<CommonConf> getItemsByCategory(HttpServletRequest request, HttpServletResponse response) {

		RestTemplate restTemplate = new RestTemplate();

		int catId = Integer.parseInt(request.getParameter("catId"));
		selectedCat = catId;

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("itemGrp1", catId);

		Item[] item = restTemplate.postForObject(Constants.url + "getItemsByCatIdAndSortId", map, Item[].class);
		ArrayList<Item> itemList = new ArrayList<Item>(Arrays.asList(item));
		System.out.println("Filter Item List " + itemList.toString());
		
		globalItemList=itemList;
		// -------------------------------------------------------------------------------
		MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();

		mvm.add("productionDate", getYesterdayDate());
		mvm.add("catId", catId);
		try {
			ParameterizedTypeReference<List<GetProductionItemQty>> typeRef = new ParameterizedTypeReference<List<GetProductionItemQty>>() {
			};
			ResponseEntity<List<GetProductionItemQty>> responseEntity = restTemplate
					.exchange(Constants.url + "getProduItemQty", HttpMethod.POST, new HttpEntity<>(mvm), typeRef);

			getProdItemQtyList = responseEntity.getBody();

			// getOrderItemQtyList=rest.postForObject(Constants.url + "getOrderAllItemQty",
			// map, List.class);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("List of Orders : " + getProdItemQtyList.toString());
		List<CommonConf> commonConfList = new ArrayList<CommonConf>();

		for (Item items : itemList) {
			CommonConf commonConf = new CommonConf();
			commonConf.setId(items.getId());
			commonConf.setName(items.getItemName());

			for (GetProductionItemQty getProductionItemQty : getProdItemQtyList) {
				if (items.getId() == getProductionItemQty.getItemId()) {
					commonConf.setQty(getProductionItemQty.getQty());
				}
			}
			commonConfList.add(commonConf);
		}
		
		
		
		
		// new Code
		List<FinishedGoodStockDetail> updateStockDetailList = new ArrayList<>();

		try {
			
			map = new LinkedMultiValueMap<String, Object>();
			DateFormat dfYmd = new SimpleDateFormat("yyyy-MM-dd");
			map = new LinkedMultiValueMap<String, Object>();
			map = new LinkedMultiValueMap<String, Object>();
			map.add("stockStatus", 0);

			FinishedGoodStock stockHeader = restTemplate.postForObject(Constants.url + "getFinGoodStockHeader", map,
					FinishedGoodStock.class);

			System.out.println("stock Header " + stockHeader.toString());

			Date stockDate = stockHeader.getFinGoodStockDate();

			List<GetCurProdAndBillQty> getCurProdAndBillQty = new ArrayList<>();
			map = new LinkedMultiValueMap<String, Object>();

			System.out.println("stock date " + stockDate);
			String stkDate = dfYmd.format(stockDate);
			//int selCate=Integer.parseInt(selectedCat);
			System.out.println("stk Date for get Cur Prod and Bill Qty "+stkDate);
			
			System.out.println("stk CatId for get Cur Prod and Bill Qty "+catId);
			map = new LinkedMultiValueMap<String, Object>();
			
			map.add("prodDate", stkDate);
			map.add("catId", catId);
			map.add("delStatus", 0);
			
map.add("timestamp", stockHeader.getTimestamp());
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			 
			map.add("curTimeStamp", dateFormat.format(cal.getTime()));

			getCurProdAndBillQtyList = restTemplate.postForObject(Constants.url + "getCurrentProdAndBillQty", map,
					GetCurProdAndBillQtyList.class);

			getCurProdAndBillQty = getCurProdAndBillQtyList.getGetCurProdAndBillQty();

			System.out.println("Cur Prod And Bill Qty Listy " + getCurProdAndBillQty.toString());
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

			String stockkDate = df.format(stockDate);
			map = new LinkedMultiValueMap<String, Object>();
			map.add("stockDate", stockkDate);
			map.add("catId", catId);

			ParameterizedTypeReference<List<FinishedGoodStockDetail>> typeRef = new ParameterizedTypeReference<List<FinishedGoodStockDetail>>() {
			};
			ResponseEntity<List<FinishedGoodStockDetail>> responseEntity = restTemplate.exchange(
					Constants.url + "getFinGoodStockDetail", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			List<FinishedGoodStockDetail> finGoodDetail = responseEntity.getBody();

			System.out.println("Finished Good Stock Detail " + finGoodDetail.toString());

			FinishedGoodStockDetail stockDetail = new FinishedGoodStockDetail();
			GetCurProdAndBillQty curProdBilQty = new GetCurProdAndBillQty();

			for (int i = 0; i < getCurProdAndBillQty.size(); i++) {

				curProdBilQty = getCurProdAndBillQty.get(i);

				for (int j = 0; j < finGoodDetail.size(); j++) {

					stockDetail = finGoodDetail.get(j);

					if (curProdBilQty.getId() == stockDetail.getItemId()) {

						System.out.println(
								"item Id Matched " + curProdBilQty.getId() + "and " + stockDetail.getItemId());

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

						System.out.println("closing Qty  : t1 " + cloT1 + " t2 " + cloT2 + " t3 " + cloT3);

						System.out.println("cur Closing " + curClosing);
						System.out.println("total closing " + totalClosing);

						System.out.println("---------");

					} // end of if isSameItem =true
				} // end of Inner For Loop
			} // End of outer For loop

		} catch (Exception e) {
			System.out.println("Excein Prod Controller get Current Fin good Stock " + e.getMessage());
			e.printStackTrace();

		}
		
		for(int i=0;i<commonConfList.size();i++) {
			
			for(int j=0;j<updateStockDetailList.size();j++) {
				
				if(commonConfList.get(i).getId()==updateStockDetailList.get(j).getItemId()) {
					
					commonConfList.get(i).setCurClosingQty(updateStockDetailList.get(j).getCloCurrent());
					
					commonConfList.get(i).setCurOpeQty(updateStockDetailList.get(j).getTotalCloStk());
					
				}
				
				
			}
		}
		
		
		
//end of new Codes



		System.out.println("------------------------");

		System.out.println("itemCommonConf" + commonConfList.toString());

		return commonConfList;

	}

	public static String getYesterdayDate() {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return dateFormat.format(cal.getTime());
	}

	/*
	 * @RequestMapping(value = "/getItemsProdQty", method = RequestMethod.GET)
	 * public @ResponseBody List<GetRegSpCakeOrderQty>
	 * getItemsProdQty(HttpServletRequest request, HttpServletResponse response) {
	 * System.out.println("AJAX");
	 * 
	 * 
	 * getRegSpCakeOrderQtyList=new ArrayList<GetRegSpCakeOrderQty>();
	 * 
	 * String productionDate=request.getParameter("prodDate");
	 * System.out.println("prodDate"+productionDate);
	 * 
	 * int catId=Integer.parseInt(request.getParameter("catId"));
	 * System.out.println("catId"+catId);
	 * 
	 * 
	 * RestTemplate rest=new RestTemplate();
	 * 
	 * MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String,
	 * Object>(); mvm.add("catId", catId);
	 * 
	 * 
	 * List<Integer> menuIdList=rest.postForObject(Constants.url +
	 * "getMenuIdByCatId",mvm, List.class);
	 * 
	 * 
	 * StringBuilder commaSepValueBuilder = new StringBuilder();
	 * 
	 * //Looping through the list for ( int i = 0; i< menuIdList.size(); i++){
	 * //append the value into the builder
	 * commaSepValueBuilder.append(menuIdList.get(i));
	 * 
	 * //if the value is not the last element of the list //then append the comma(,)
	 * as well if ( i != menuIdList.size()-1){ commaSepValueBuilder.append(", "); }
	 * } System.out.println("commaSepValueBuilder"+commaSepValueBuilder);
	 * 
	 * 
	 * MultiValueMap<String, Object> map=new LinkedMultiValueMap<String, Object>();
	 * 
	 * 
	 * map.add("productionDate", productionDate); map.add("menuId",
	 * commaSepValueBuilder); try {
	 * ParameterizedTypeReference<List<GetRegSpCakeOrderQty>> typeRef = new
	 * ParameterizedTypeReference<List<GetRegSpCakeOrderQty>>() { };
	 * ResponseEntity<List<GetRegSpCakeOrderQty>> responseEntity =
	 * rest.exchange(Constants.url + "getOrderQtyRegSpCakeAllItems",
	 * HttpMethod.POST, new HttpEntity<>(map), typeRef);
	 * 
	 * getRegSpCakeOrderQtyList = responseEntity.getBody();
	 * //getRegSpCakeOrderQtyList=rest.postForObject(Constants.url +
	 * "getOrderQtyRegSpCakeAllItems", map, List.class);
	 * 
	 * }catch (Exception e) { System.out.println(e.getMessage()); }
	 * 
	 * System.out.println("List of Orders : "+ getRegSpCakeOrderQtyList.toString());
	 * 
	 * return getRegSpCakeOrderQtyList;
	 * 
	 * }
	 */

	@RequestMapping(value = "/getItemsProdQty", method = RequestMethod.GET)
	public @ResponseBody PlanQtyAjaxResponse getItemsProdQty(HttpServletRequest request,
			HttpServletResponse response) {

		int maxTimeSlot=0;
		PlanQtyAjaxResponse planQtyAjaxResponse=new PlanQtyAjaxResponse();

		System.out.println("In method");
		//List<GetProductionItemQty> getProdItemQtyList = new ArrayList<GetProductionItemQty>();

		String productionDate = request.getParameter("prodDate");
		System.out.println("prodDate" + productionDate);

		int catId = Integer.parseInt(request.getParameter("catId"));
		System.out.println("catId" + catId);

		int id = Integer.parseInt(request.getParameter("id"));
		RestTemplate rest = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("productionDate", productionDate);
		map.add("catId", selectedCat);
		if(id==2)
		{
			try {
				
				GetProductionItemQty[] responseEntity = rest.postForObject(Constants.url + "getOrderuItemQty",map,GetProductionItemQty[].class);

				
				//PostProdPlanHeader postProductionHeader= rest.postForObject(Constants.url + "getProductionTimeSlot", map,PostProdPlanHeader.class);

				//maxTimeSlot=postProductionHeader.getTimeSlot();
				ArrayList<GetProductionItemQty> getProdItemQtyList = new ArrayList<GetProductionItemQty>(Arrays.asList(responseEntity));
				System.out.println("Filter Item List " + getProdItemQtyList.toString());
				
				
				planQtyAjaxResponse.setGetProductionItemQtyList(getProdItemQtyList);
				planQtyAjaxResponse.setItemList(globalItemList);
				System.out.println("planQtyAjaxResponse"+planQtyAjaxResponse.toString());
				// getOrderItemQtyList=rest.postForObject(Constants.url + "getOrderAllItemQty",
				// map, List.class);

			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		else
		{
			try {
				
				GetProductionItemQty[] responseEntity = rest.postForObject(Constants.url + "getProduItemQty",map,GetProductionItemQty[].class);

				
				//PostProdPlanHeader postProductionHeader= rest.postForObject(Constants.url + "getProductionTimeSlot", map,PostProdPlanHeader.class);

				//maxTimeSlot=postProductionHeader.getTimeSlot();
				ArrayList<GetProductionItemQty> getProdItemQtyList = new ArrayList<GetProductionItemQty>(Arrays.asList(responseEntity));
				System.out.println("Filter Item List " + getProdItemQtyList.toString());
				
				
				planQtyAjaxResponse.setGetProductionItemQtyList(getProdItemQtyList);
				planQtyAjaxResponse.setItemList(globalItemList);
				System.out.println("planQtyAjaxResponse"+planQtyAjaxResponse.toString());
				// getOrderItemQtyList=rest.postForObject(Constants.url + "getOrderAllItemQty",
				// map, List.class);

			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		

		System.out.println("List of Orders : " + getProdItemQtyList.toString());

		return planQtyAjaxResponse;

	}

	// --------------------------------------------------------------------------------------------------------------
	java.sql.Date convertedDate;

	@RequestMapping(value = "/submitProductionPlan", method = RequestMethod.POST)
	public String submitProduction(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("production/production");

		// String productionDate=request.getParameter("production_date");
		List<CommonConf> prodPlanItems=new ArrayList<CommonConf>();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		String planDate=request.getParameter("datepicker5");
		for(Item item:globalItemList)
		{
			CommonConf commonConf=new CommonConf();
			int qty =Integer.parseInt(request.getParameter("qty5"+item.getId()));
          
			System.out.println(qty);
		if(qty>0)
		{
			commonConf.setId(item.getId());
            commonConf.setName(item.getItemName());
            commonConf.setQty(qty);
			
            prodPlanItems.add(commonConf);
		 }
		}
		
		String selectTime = request.getParameter("selectTime");
		System.out.println("Time:"+selectTime);
		String productionDate = null;
		try
		{
			Date dt = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(dt); 
			c.add(Calendar.DATE, 1);
			dt = c.getTime();

		 productionDate = new SimpleDateFormat("dd-MM-yyyy").format(dt);
		System.out.println("production Date"+productionDate);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

		
				
		
		
		
		
		
		
		
		
		System.out.println("productionDate"+productionDate);
		String convertedDate = null;
		if (productionDate != null && productionDate != "" && selectTime != null && selectTime != "") {
			try {
				SimpleDateFormat ymdSDF = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat dmySDF = new SimpleDateFormat("dd-MM-yyyy");
				Date dmyDate = dmySDF.parse(productionDate);

				convertedDate = ymdSDF.format(dmyDate);
System.out.println("converted date"+convertedDate);
			} catch (ParseException e) {

				e.printStackTrace();
			}

			int timeSlot = Integer.parseInt(selectTime);

			System.out.println("Date  :  " + convertedDate);
			for (int i = 0; i < prodPlanItems.size(); i++) {

				System.out.println("item  Id " + prodPlanItems.get(i).getId());
			}
			RestTemplate restTemplate = new RestTemplate();

			PostProdPlanHeader postProductionHeader = new PostProdPlanHeader();

			postProductionHeader.setTimeSlot(timeSlot);
			postProductionHeader.setItemGrp1(selectedCat);
			postProductionHeader.setProductionDate(planDate);
			postProductionHeader.setDelStatus(0);
			postProductionHeader.setIsBom(0);
			postProductionHeader.setIsMixing(0);
			postProductionHeader.setIsPlanned(1);
			postProductionHeader.setProductionBatch("0");
			postProductionHeader.setProductionStatus(1);
			postProductionHeader.setProductionHeaderId(0);
			
			List<PostProductionPlanDetail> postProductionDetailList = new ArrayList<>();
			PostProductionPlanDetail postProductionDetail;

			System.out.println("List    :" + prodPlanItems);

			for (int i = 0; i < prodPlanItems.size(); i++) {
				postProductionDetail = new PostProductionPlanDetail();
				int id = prodPlanItems.get(i).getId();
				// System.out.println("a============"+a);
				postProductionDetail.setProductionDate(planDate);
				postProductionDetail.setItemId(id);
				postProductionDetail.setOpeningQty(0);
				postProductionDetail.setOrderQty(0);
				postProductionDetail.setProductionBatch("0");
				postProductionDetail.setRejectedQty(0);
				postProductionDetail.setProductionQty(0);
				postProductionDetail.setPlanQty(prodPlanItems.get(i).getQty());
				postProductionDetailList.add(postProductionDetail);
			}

			postProductionHeader.setPostProductionPlanDetail(postProductionDetailList);
			System.out.println("postProductionHeader"+postProductionHeader.toString());
			try {

				Info info = restTemplate.postForObject(Constants.url + "postProductionPlan", postProductionHeader,
						Info.class);

				System.out.println("Message :   " + info.getMessage());
				System.out.println("Error  :    " + info.getError());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			
		}
		return "redirect:/showProdForcast";
	}

}
