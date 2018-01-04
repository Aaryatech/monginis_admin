package com.ats.adminpanel.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.AllFrIdName;
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.FrMenu;

import com.ats.adminpanel.model.GetFrMenus;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.item.Item;
import com.ats.adminpanel.model.stock.PostFrItemStockCommon;
import com.ats.adminpanel.model.stock.PostFrItemStockDetail;
import com.ats.adminpanel.model.stock.PostFrItemStockHeader;

@Controller
@Scope("session")
public class StockController {

	private static final Logger logger = LoggerFactory.getLogger(StockController.class);
	List<FrMenu> filterFrMenus = new ArrayList<FrMenu>();
	List<Item> itemList;
	String frId;
	String menuId="0";

	@RequestMapping(value = "/showFrOpeningStock")
	public ModelAndView showFrOpeningStock(HttpServletRequest request, HttpServletResponse response) {

		logger.info("/showFrOpeningStock request mapping.");

		ModelAndView model = new ModelAndView("stock/fropeningstock");
		Constants.mainAct = 17;
		Constants.subAct = 171;

		RestTemplate restTemplate = new RestTemplate();

		AllFrIdNameList allFrIdNameList = new AllFrIdNameList();
		try {

			allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

		} catch (Exception e) {
			System.out.println("Exception in getAllFrIdName" + e.getMessage());
			e.printStackTrace();

		}

		model.addObject("frList", allFrIdNameList.getFrIdNamesList());

		return model;
	}

	// AJAX Call for menu
	@RequestMapping(value = "/getMenuListByFr", method = RequestMethod.GET)
	public @ResponseBody List<FrMenu> getMenuListByFr(HttpServletRequest request, HttpServletResponse response) {

		logger.info("/getMenuListByFr AJAX Call mapping.");

		frId = request.getParameter("fr_id");

		RestTemplate restTemplate = new RestTemplate();

		MultiValueMap<String, Object> menuMap = new LinkedMultiValueMap<String, Object>();
		menuMap.add("frId", frId);

		GetFrMenus getFrMenus = restTemplate.postForObject(Constants.url + "getFrConfigMenus", menuMap,
				GetFrMenus.class);

		filterFrMenus = new ArrayList<FrMenu>();

		for (int i = 0; i < getFrMenus.getFrMenus().size(); i++) {

			FrMenu frMenu = getFrMenus.getFrMenus().get(i);

			if (frMenu.getMenuId() == 26 || frMenu.getMenuId() == 31 || frMenu.getMenuId() == 33
					|| frMenu.getMenuId() == 34 || frMenu.getMenuId() == 49) {

				filterFrMenus.add(frMenu);

			}

		}
		
		
		
		return filterFrMenus;
	}

	// AJAX Call for Items
	@RequestMapping(value = "/getItemListById", method = RequestMethod.GET)
	public @ResponseBody List<Item> getItems(HttpServletRequest request, HttpServletResponse response) {

		logger.info("/getItemListById AJAX Call mapping.");

		 menuId = request.getParameter("menu_id");

		System.out.println("req param menuId " + menuId);

		RestTemplate restTemplate = new RestTemplate();

		String itemShow = null;

		for (int i = 0; i < filterFrMenus.size(); i++) {

			if (filterFrMenus.get(i).getMenuId() == Integer.parseInt(menuId)) {

				itemShow = filterFrMenus.get(i).getItemShow();
				System.out.println("Item Show List is: " + itemShow);

				break;
			}

		}

		MultiValueMap<String, Object> menuMap = new LinkedMultiValueMap<String, Object>();
		menuMap.add("itemList", itemShow);

		// itemList = restTemplate.postForObject(Constants.url + "getItemsByItemId",
		// menuMap, List.class);

		ParameterizedTypeReference<List<Item>> typeRef = new ParameterizedTypeReference<List<Item>>() {
		};
		ResponseEntity<List<Item>> responseEntity = restTemplate.exchange(Constants.url + "getItemsByItemId",
				HttpMethod.POST, new HttpEntity<>(menuMap), typeRef);
		itemList = responseEntity.getBody();

		System.out.println("Item List " + itemList.toString());

		return itemList;
	}

	// Save item opening stock

	@RequestMapping(value = "/saveFrOpeningStockProcess")
	public ModelAndView saveOpeningStock(HttpServletRequest request, HttpServletResponse response) {

		logger.info("/showFrOpeningStock request mapping.");

		ModelAndView model = new ModelAndView("stock/fropeningstock");
		Constants.mainAct = 17;
		Constants.subAct = 171;

		// stockQty
		Date date = new Date();
		ZoneId z = ZoneId.of("Asia/Calcutta");
		LocalDate localDate = date.toInstant().atZone(z).toLocalDate();
		int year  = localDate.getYear();
		int month = localDate.getMonthValue();
		
	
		System.out.println("Month "+month+" year "+year);
		//date = df.parse(date);

		PostFrItemStockHeader postFrItemStockHeader = new PostFrItemStockHeader();

		List<PostFrItemStockDetail> postFrItemStockDetailList = new ArrayList<>();

		for (int i = 0; i < itemList.size(); i++) {

			Item item = itemList.get(i);
			System.out.println("Current Item " + item.toString());

			String stockQty = request.getParameter("stockQty" + item.getId());

			System.out.println("new qty " + stockQty);
			PostFrItemStockDetail itemStockDetail = new PostFrItemStockDetail();

			itemStockDetail.setItemId(item.getId());
			itemStockDetail.setRegOpeningStock(Integer.parseInt(stockQty));
			itemStockDetail.setSpOpeningStock(0);

			postFrItemStockDetailList.add(itemStockDetail);

		}

		postFrItemStockHeader.setFrId(Integer.parseInt(frId));
		postFrItemStockHeader.setIsMonthClosed(0);
		postFrItemStockHeader.setMonth(month);
		postFrItemStockHeader.setPostFrItemStockDetailList(postFrItemStockDetailList);
		postFrItemStockHeader.setYear(year);
		
		 /*
		 
		 *menuid=catid
		 *26=1
		 *31=2
		 *33=3
		 *34=4
		 *49=6
		 
		 */
		
		
		if(menuId.equals("26")) {
			
			postFrItemStockHeader.setCatId(1);
		
		}else if(menuId.equals("31")) {
			
			postFrItemStockHeader.setCatId(2);

		}else if(menuId.equals("33")) {

			postFrItemStockHeader.setCatId(3);
			
		}else if(menuId.equals("34")){
			
			postFrItemStockHeader.setCatId(4);

		}else if(menuId.equals("49")) {
			
			postFrItemStockHeader.setCatId(6);

		}		
		
		
		System.out.println("post fr item stock "+postFrItemStockHeader.toString());

		RestTemplate restTemplate = new RestTemplate();

		//postFrOpStock
		Info info = restTemplate.postForObject(Constants.url + "postFrOpStock", postFrItemStockHeader, Info.class);
		
		System.out.println("Post Fr Op Stock response "+info.toString() );
		
		AllFrIdNameList allFrIdNameList = new AllFrIdNameList();
		try {

			allFrIdNameList = restTemplate.getForObject(Constants.url + "getAllFrIdName", AllFrIdNameList.class);

		} catch (Exception e) {
			System.out.println("Exception in getAllFrIdName" + e.getMessage());
			e.printStackTrace();

		}

		model.addObject("frList", allFrIdNameList.getFrIdNamesList());

		return model;
	}

}
