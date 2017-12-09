package com.ats.adminpanel.controller;

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
import com.ats.adminpanel.model.production.PostProdPlanHeader;
import com.ats.adminpanel.model.production.PostProductionDetail;
import com.ats.adminpanel.model.production.PostProductionHeader;
import com.ats.adminpanel.model.production.PostProductionPlanDetail;

@Controller
public class ProdForcastingController {

	List<MCategoryList> filteredCatList;
	public static List<GetProductionItemQty> getProdItemQtyList;
	public static int[] timeSlot;
	public static String productionDate;
	public static int selectedCat;
	public static List<Item> globalItemList; 

	@RequestMapping(value = "/showProdForcast", method = RequestMethod.GET)
	public ModelAndView showProdForcasting(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("production/prodForcasting");

		Constants.mainAct = 16;
		Constants.subAct = 163;

		try {

			RestTemplate restTemplate = new RestTemplate();

			CategoryListResponse allCategoryResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
					CategoryListResponse.class);

			List<MCategoryList> catList = allCategoryResponse.getmCategoryList();

			filteredCatList = new ArrayList<MCategoryList>();
			System.out.println("catList :" + catList.toString());

			for (MCategoryList mCategory : catList) {
				if (mCategory.getCatId() != 5 && mCategory.getCatId() != 6) {
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

		Item[] item = restTemplate.postForObject(Constants.url + "getItemsByCatId", map, Item[].class);
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
	public @ResponseBody List<GetProductionItemQty> getItemsProdQty(HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("In method");
		getProdItemQtyList = new ArrayList<GetProductionItemQty>();

		String productionDate = request.getParameter("prodDate");
		System.out.println("prodDate" + productionDate);

		int catId = Integer.parseInt(request.getParameter("catId"));
		System.out.println("catId" + catId);

		RestTemplate rest = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("productionDate", productionDate);
		map.add("catId", selectedCat);
		try {
			ParameterizedTypeReference<List<GetProductionItemQty>> typeRef = new ParameterizedTypeReference<List<GetProductionItemQty>>() {
			};
			ResponseEntity<List<GetProductionItemQty>> responseEntity = rest.exchange(Constants.url + "getProduItemQty",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);

			getProdItemQtyList = responseEntity.getBody();

			// getOrderItemQtyList=rest.postForObject(Constants.url + "getOrderAllItemQty",
			// map, List.class);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("List of Orders : " + getProdItemQtyList.toString());

		return getProdItemQtyList;

	}

	// --------------------------------------------------------------------------------------------------------------
	java.sql.Date convertedDate;

	@RequestMapping(value = "/submitProductionPlan", method = RequestMethod.POST)
	public String submitProduction(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("production/production");

		// String productionDate=request.getParameter("production_date");
		List<CommonConf> prodPlanItems=new ArrayList<CommonConf>();
		
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
		
		 productionDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		
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
			postProductionHeader.setProductionDate(convertedDate);
			postProductionHeader.setDelStatus(0);
			postProductionHeader.setIsBom(1);
			postProductionHeader.setIsMixing(1);
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
