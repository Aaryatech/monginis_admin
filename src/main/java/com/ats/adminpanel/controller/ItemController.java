
package com.ats.adminpanel.controller;

import java.io.File;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.item.AllItemsListResponse;
import com.ats.adminpanel.model.item.CategoryListResponse;
import com.ats.adminpanel.model.item.FrItemStock;
import com.ats.adminpanel.model.item.FrItemStockConfiPostResponse;
import com.ats.adminpanel.model.item.FrItemStockConfiResponse;
import com.ats.adminpanel.model.item.FrItemStockConfigure;
import com.ats.adminpanel.model.item.FrItemStockConfigurePost;
import com.ats.adminpanel.model.item.FrItemStockList;
import com.ats.adminpanel.model.item.GetItemSup;
import com.ats.adminpanel.model.item.GetPrevItemStockResponse;
import com.ats.adminpanel.model.item.Item;
import com.ats.adminpanel.model.item.ItemSup;
import com.ats.adminpanel.model.item.ItemSupList;
import com.ats.adminpanel.model.item.MCategoryList;
import com.ats.adminpanel.model.item.StockDetail;
import com.ats.adminpanel.model.item.SubCategory;
import com.ats.adminpanel.model.modules.ErrorMessage;
import com.ats.adminpanel.model.spprod.SpStation;
import com.ats.adminpanel.util.ImageS3Util;

@Controller
public class ItemController {

	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

	AllItemsListResponse allItemsListResponse;

	public static List<MCategoryList> mCategoryList = null;

	public static CategoryListResponse categoryListResponse;

	public static List<MCategoryList> itemsWithCategoriesList;
	public static int settingValue;

	public static List<FrItemStockConfigurePost> frItemStockConfigureList;

	ArrayList<Item> itemList;

	public static List<GetPrevItemStockResponse> getPrevItemStockResponsesList;

	ArrayList<String> tempItemList;
	public static int  catId = 0; 

	@RequestMapping(value = "/addItem", method = RequestMethod.GET)
	public ModelAndView showAddCategory(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("items/addnewitem");

		Constants.mainAct = 4;
		Constants.subAct = 42;
		try {

			System.out.println("Add Item Request");

			RestTemplate restTemplate = new RestTemplate();
			// CategoryListResponse
			categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
					CategoryListResponse.class);
			mCategoryList = new ArrayList<MCategoryList>();
			mCategoryList = categoryListResponse.getmCategoryList();
			System.out.println("Main Cat is  " + categoryListResponse.toString());

			model.addObject("mCategoryList", mCategoryList);

		} catch (Exception e) {
			System.out.println("error in item show sachin" + e.getMessage());
		}
		return model;
	}

	// try for ajax
	// ajax today
	@RequestMapping(value = "/getGroup2ByCatId", method = RequestMethod.GET)
	public @ResponseBody List<SubCategory> subCatById(@RequestParam(value = "catId", required = true) int catId) {
		logger.debug("finding Items for menu " + catId);

		List<SubCategory> subCatList = new ArrayList<SubCategory>();
		System.out.println("CatId" + mCategoryList.size());
		for (int x = 0; x < mCategoryList.size(); x++) {
			System.out.println("mCategoryList.get(x).getCatId(" + mCategoryList.get(x).getCatId());
			if (mCategoryList.get(x).getCatId() == catId) {
				subCatList = mCategoryList.get(x).getSubCategory();
				System.out.println("SubCat List" + subCatList);
			}

		}

		System.out.println("Finding sub cat List " + subCatList.toString());

		return subCatList;
	}

	// end

	// Franchisee Item Configuration -- new work 26/09

	@RequestMapping(value = "/showFrItemConfiguration")
	public ModelAndView showFrItemConfiguration(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Item Request");
		ModelAndView model = new ModelAndView("items/itemConfig");
		Constants.mainAct = 4;
		Constants.subAct = 44;

		try {

			RestTemplate restTemplate = new RestTemplate();

			FrItemStockConfiResponse frItemStockConfiResponse = restTemplate
					.getForObject(Constants.url + "getfrItemConfSetting", FrItemStockConfiResponse.class);

			List<FrItemStockConfigure> frItemStockConfigures = new ArrayList<FrItemStockConfigure>();

			frItemStockConfigures = frItemStockConfiResponse.getFrItemStockConfigure();

			for (int i = 0; i < frItemStockConfigures.size(); i++) {

				if (frItemStockConfigures.get(i).getSettingKey().equals("frItemStockType")) {

					settingValue = frItemStockConfigures.get(i).getSettingValue();

				}

			}

			System.out.println("settingValue-------------------------------------------==" + settingValue);

			CategoryListResponse itemsWithCategoryResponseList = restTemplate
					.getForObject(Constants.url + "showAllCategory", CategoryListResponse.class);

			itemsWithCategoriesList = itemsWithCategoryResponseList.getmCategoryList();

			System.out.println("item Id Cat Name --" + itemsWithCategoriesList.toString());

			for (int i = 0; i < itemsWithCategoriesList.size(); i++) {

				System.out.println("cat id== " + itemsWithCategoriesList.get(i).getCatId());
				if (itemsWithCategoriesList.get(i).getCatId() == 5) {

					itemsWithCategoriesList.remove(i);

				}

			}

			for (int i = 0; i < itemsWithCategoriesList.size(); i++) {

				System.out.println("cat id== " + itemsWithCategoriesList.get(i).getCatId());
				if (itemsWithCategoriesList.get(i).getCatId() == 6) {

					itemsWithCategoriesList.remove(i);

				}

			}

			model.addObject("settingValue", settingValue);
			model.addObject("ItemIdCategory", itemsWithCategoriesList);
			model.addObject("catId", catId);
			model.addObject("itemList", getPrevItemStockResponsesList);

		} catch (Exception e) {

			System.out.println("Exception in showing fr Item Stock Confi " + e.getMessage());
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/getItemsbyCatIdProcess", method = RequestMethod.GET)
	public String getItemsbyCatIdProcess(HttpServletRequest request, HttpServletResponse response) {

		//ModelAndView model = new ModelAndView("items/itemlist");
		ModelAndView model = new ModelAndView("items/itemConfig");
		
		Constants.mainAct = 4;
		Constants.subAct = 44;

		try {

			catId = Integer.parseInt(request.getParameter("cat_name"));
			System.out.println("cat Id "+catId);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("itemGrp1", catId);

			RestTemplate restTemplate = new RestTemplate();

			Item[] item = restTemplate.postForObject(Constants.url + "getItemsByCatId", map, Item[].class);

			ArrayList<Item> tempItemList = new ArrayList<Item>(Arrays.asList(item));

			StringBuilder stringBuilder = new StringBuilder();

			String itemId;

			for (int i = 0; i < tempItemList.size(); i++) {

				itemId = (tempItemList.get(i).getId()) + ",";
				stringBuilder.append(itemId);

			}

			String itemIds = stringBuilder.toString();
			itemIds = itemIds.substring(0, itemIds.length() - 1);

			System.out.println("itemId :" + itemIds);

			map = new LinkedMultiValueMap<String, Object>();
			
			map.add("itemId", itemIds);

			getPrevItemStockResponsesList = new ArrayList<GetPrevItemStockResponse>();

			ParameterizedTypeReference<List<GetPrevItemStockResponse>> typeRef = new ParameterizedTypeReference<List<GetPrevItemStockResponse>>() {
			};

			ResponseEntity<List<GetPrevItemStockResponse>> responseEntity = restTemplate.exchange(
					Constants.url + "getAllFrItemConfPost", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			getPrevItemStockResponsesList = responseEntity.getBody();

			if (getPrevItemStockResponsesList.size() < tempItemList.size()) {

				List<GetPrevItemStockResponse> tempPrevItemStockList = new ArrayList<GetPrevItemStockResponse>();

				for (int i = 0; i < tempItemList.size(); i++) {

					Item tempItem = tempItemList.get(i);

					GetPrevItemStockResponse tempItemStockResponse = new GetPrevItemStockResponse();

					tempItemStockResponse.setItemId(tempItem.getId());
					tempItemStockResponse.setItemName(tempItem.getItemName());

					List<StockDetail> stockDetailsList = new ArrayList<StockDetail>();

					for (int j = 1; j <= settingValue; j++) {

						StockDetail stockDetail = new StockDetail();

						stockDetail.setFrStockId(0);
						stockDetail.setMaxQty(0);
						stockDetail.setMinQty(0);
						stockDetail.setType(j);

						stockDetailsList.add(stockDetail);

					}

					tempItemStockResponse.setStockDetails(stockDetailsList);

					for (int j = 0; j < getPrevItemStockResponsesList.size(); j++) {

						if (getPrevItemStockResponsesList.get(j).getItemId() == tempItemList.get(i).getId()) {

							tempItemStockResponse = getPrevItemStockResponsesList.get(j);
						}

					}

					tempPrevItemStockList.add(tempItemStockResponse);

				}

				System.out.println("\n\n ####### Updated Stock List is: " + tempPrevItemStockList.toString());

				getPrevItemStockResponsesList = new ArrayList<GetPrevItemStockResponse>();

				getPrevItemStockResponsesList = tempPrevItemStockList;
			}

			itemList = new ArrayList<Item>(Arrays.asList(item));

			System.out.println(" item Stock response  List " + getPrevItemStockResponsesList.toString());
			System.out.println("item list size= " + getPrevItemStockResponsesList.size());

			model.addObject("catId", catId);
			model.addObject("itemList", getPrevItemStockResponsesList);

		} catch (Exception e) {

			System.out.println("exe in item get By CatId frItemConf " + e.getMessage());
			e.printStackTrace();
		}

		return "redirect:/showFrItemConfiguration";

	}
	
	
	@RequestMapping(value = "/frItemStockConfigurationProcess", method = RequestMethod.POST)
	public String frItemStockInsertProcess(HttpServletRequest request, HttpServletResponse response) {
	//ModelAndView mav = new ModelAndView("items/itemlist");

		List<FrItemStock> frItemStocksList = new ArrayList<FrItemStock>();

		try {
			
			RestTemplate rest = new RestTemplate();


			//catId = Integer.parseInt(request.getParameter("cat_name"));

		for (int i = 0; i < getPrevItemStockResponsesList.size(); i++) {

			GetPrevItemStockResponse getPrevItemStockResponse = getPrevItemStockResponsesList.get(i);

			for (int j = 0; j < getPrevItemStockResponse.getStockDetails().size(); j++) {

				FrItemStock frItemStock = new FrItemStock();
				StockDetail stockDetail = getPrevItemStockResponse.getStockDetails().get(j);

				// ${item.itemId}stockId${count.index}

				String frStockId = request.getParameter("" + getPrevItemStockResponse.getItemId() + "stockId" + j);
				String minQty = request.getParameter("" + getPrevItemStockResponse.getItemId() + "min" + j);

				String maxQty = request.getParameter("" + getPrevItemStockResponse.getItemId() + "max" + j);
				System.out.println("min Qty = " + minQty);
				System.out.println("max Qty = " + maxQty);

				if (!minQty.equalsIgnoreCase("") && minQty != null && !maxQty.equalsIgnoreCase("") && maxQty != null) {

					if (Integer.parseInt(minQty) != stockDetail.getMinQty()
							|| Integer.parseInt(maxQty) != stockDetail.getMaxQty()) {

						frItemStock.setFrStockId(Integer.parseInt(frStockId));
						frItemStock.setMinQty(Integer.parseInt(minQty));

						frItemStock.setMaxQty(Integer.parseInt(maxQty));

						frItemStock.setItemId(getPrevItemStockResponse.getItemId());
						frItemStock.setType(j + 1);
						frItemStocksList.add(frItemStock);

					}
				}

			}
		}
		

		System.out.println("Fr item Stock " + frItemStocksList.toString());
		System.out.println("fr item stock size " + frItemStocksList.size());

		ErrorMessage errorResponse = rest.postForObject(Constants.url + "frItemStockPost", frItemStocksList,
				ErrorMessage.class);
		
		
		//sachin
		
		
		
		
		//sachin
		
		
		
		
		} catch (Exception e) {

			System.out.println("exe in fr Item  stock insert  process  " + e.getMessage());
			
			e.printStackTrace();
		}
		
		return "redirect:/getItemsbyCatIdProcess";

	}

	@RequestMapping(value = "/addItemProcess", method = RequestMethod.POST)
	public String addItemProcess(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("item_image") MultipartFile file) {
		ModelAndView mav = new ModelAndView("items/itemlist");

		String itemId = request.getParameter("item_id");

		int minQty = Integer.parseInt(request.getParameter("min_qty"));

		String itemName = request.getParameter("item_name");

		String itemGrp1 = request.getParameter("item_grp1");

		String itemGrp2 = request.getParameter("item_grp2");

		String itemGrp3 = request.getParameter("item_grp3");

		double itemRate1 = Double.parseDouble(request.getParameter("item_rate1"));

		double itemRate2 = Double.parseDouble(request.getParameter("item_rate2"));

		double itemRate3 = Double.parseDouble(request.getParameter("item_rate3"));

		double itemMrp1 = Double.parseDouble(request.getParameter("item_mrp1"));

		double itemMrp2 = Double.parseDouble(request.getParameter("item_mrp2"));

		double itemMrp3 = Double.parseDouble(request.getParameter("item_mrp3"));

		// String itemImage = request.getParameter("item_image");

		double itemTax1 = Double.parseDouble(request.getParameter("item_tax1"));

		double itemTax2 = Double.parseDouble(request.getParameter("item_tax2"));

		double itemTax3 = Double.parseDouble(request.getParameter("item_tax3"));

		int itemIsUsed = Integer.parseInt(request.getParameter("is_used"));

		double itemSortId = Double.parseDouble(request.getParameter("item_sort_id"));

		int grnTwo = Integer.parseInt(request.getParameter("grn_two"));

		int itemShelfLife = Integer.parseInt(request.getParameter("item_shelf_life"));

		logger.info("Add new item request mapping.");

		String itemImage = ImageS3Util.uploadItemImage(file);

		RestTemplate rest = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("itemId", itemId);
		map.add("itemName", itemName);
		map.add("itemGrp1", itemGrp1);
		map.add("itemGrp2", itemGrp2);
		map.add("itemGrp3", itemGrp3);
		map.add("minQty", minQty);
		map.add("itemRate1", itemRate1);
		map.add("itemRate2", itemRate2);
		map.add("itemRate3", itemRate3);
		map.add("itemMrp1", itemMrp1);
		map.add("itemMrp2", itemMrp2);
		map.add("itemMrp3", itemMrp3);
		map.add("itemImage", itemImage);
		map.add("itemTax1", itemTax1);
		map.add("itemTax2", itemTax2);
		map.add("itemTax3", itemTax3);
		map.add("itemIsUsed", itemIsUsed);
		map.add("itemSortId", itemSortId);
		map.add("grnTwo", grnTwo);
		map.add("itemShelfLife", itemShelfLife);

		ErrorMessage errorResponse = rest.postForObject("" + Constants.url + "insertItem", map, ErrorMessage.class);
		System.out.println(errorResponse.toString());
		System.out.println("Response:" + errorResponse.getMessage());

		return "redirect:/itemList";

	}

	@RequestMapping(value = "/itemList")
	public ModelAndView showAddItem(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("List Item Request");
		ModelAndView mav = new ModelAndView("items/itemlist");
		Constants.mainAct = 4;
		Constants.subAct = 43;

		RestTemplate restTemplate = new RestTemplate();
		// CategoryListResponse
		categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory", CategoryListResponse.class);
		List<MCategoryList> mCategoryList = new ArrayList<MCategoryList>();
		mCategoryList = categoryListResponse.getmCategoryList();

		mav.addObject("mCategoryList", mCategoryList);
		try {

			// RestTemplate restTemplate = new RestTemplate();
			allItemsListResponse = restTemplate.getForObject(Constants.url + "getAllItems", AllItemsListResponse.class);

			List<Item> itemsList = new ArrayList<Item>();
			itemsList = allItemsListResponse.getItems();
			System.out.println("LIst of items" + itemsList.toString());

			mav.addObject("mCategoryList", mCategoryList);
			mav.addObject("itemsList", itemsList);
			mav.addObject("url", Constants.ITEM_IMAGE_URL);
		} catch (Exception e) {
			System.out.println("exce in listing filtered group itme" + e.getMessage());
		}

		return mav;

	}

	@RequestMapping(value = "/searchItem")
	public ModelAndView showSearchItem(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("List Item Request");

		int catId = Integer.parseInt(request.getParameter("catId"));
		ModelAndView mav = new ModelAndView("items/itemlist");

		RestTemplate restTemplate = new RestTemplate();
		categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory", CategoryListResponse.class);
		// mCategoryList = new ArrayList<MCategoryList>();
		List<MCategoryList> mCategoryList = categoryListResponse.getmCategoryList();

		try {

			List<Item> tempItemsList = new ArrayList<Item>();
			List<Item> itemsList = new ArrayList<Item>();
			itemsList = allItemsListResponse.getItems();

			System.out.println("item count before" + itemsList.size());

			System.out.println("item to show m cat is " + catId);
			for (int i = 0; i < itemsList.size(); i++) {

				if (Integer.parseInt(itemsList.get(i).getItemGrp1()) == catId) {
					tempItemsList.add(itemsList.get(i));

				}

			}

			System.out.println("after filter itemList " + tempItemsList.toString());

			mav.addObject("catId", catId);
			mav.addObject("mCategoryList", mCategoryList);
			mav.addObject("itemsList", tempItemsList);

			mav.addObject("url", Constants.ITEM_IMAGE_URL);
		} catch (Exception e) {
			System.out.println("exce in listing filtered group itme" + e.getMessage());
		}

		return mav;

	}

	@RequestMapping(value = "/deleteItem/{id}", method = RequestMethod.GET)
	public String deleteItem(@PathVariable int id) {

		// String id=request.getParameter("id");

		ModelAndView mav = new ModelAndView("items/itemList");

		RestTemplate rest = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("id", id);

		ErrorMessage errorResponse = rest.postForObject("" + Constants.url + "deleteItem", map, ErrorMessage.class);
		System.out.println(errorResponse.toString());

		Info info = rest.postForObject("" + Constants.url + "deleteItemSup", map, Info.class);
		System.out.println(errorResponse.toString());

		if (errorResponse.getError()) {
			return "redirect:/itemList";

		} else {
			return "redirect:/itemList";

		}
	}

	@RequestMapping(value = "/updateItem/{id}", method = RequestMethod.GET)
	public ModelAndView updateMessage(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("items/editItem");


		RestTemplate restTemplate = new RestTemplate();
		// CategoryListResponse
		categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory", CategoryListResponse.class);
		mCategoryList = new ArrayList<MCategoryList>();
		mCategoryList = categoryListResponse.getmCategoryList();
		System.out.println("Main Cat is  " + categoryListResponse.toString());

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("id", id);
		
		Item item = restTemplate.postForObject("" + Constants.url + "getItem", map,Item.class);
		System.out.println("ItemResponse" + item);
		String grp1 = item.getItemGrp1();
		mav.addObject("grp1", grp1);

		String grp2 = item.getItemGrp2();
		System.out.println("GrP 2=#### " + grp2);
		// mav.addObject(" grp2 id",grp2);

		mav.addObject("mCategoryList", mCategoryList);

		List<SubCategory> subCategoryList = new ArrayList<SubCategory>();

		for (int i = 0; i < mCategoryList.size(); i++) {
			if (Integer.parseInt(item.getItemGrp1()) == (mCategoryList.get(i).getCatId()))
				subCategoryList = mCategoryList.get(i).getSubCategory();

		}

		String selectedItem = "";
		int selectedItemId = 0;
		System.out.println("sub cat list is =" + subCategoryList.toString());
		for (int i = 0; i < subCategoryList.size(); i++) {

			if (subCategoryList.get(i).getSubCatId() == Integer.parseInt((item.getItemGrp2()))) {

				selectedItem = subCategoryList.get(i).getSubCatName();
				selectedItemId = subCategoryList.get(i).getSubCatId();

				subCategoryList.remove(i);

			}

		}
		System.out.println("Removed item $$$$$ " + selectedItem);

		mav.addObject("selectedItem", selectedItem);
		mav.addObject("selectedItemId", String.valueOf(selectedItemId));
		mav.addObject("subCategoryList", subCategoryList);

		int grn2app = item.getGrnTwo();
		String strGrnAppl = String.valueOf(grn2app);
		mav.addObject("strGrnAppl", strGrnAppl);

		int isUsed = item.getItemIsUsed();
		String strIsUsed = String.valueOf(isUsed);
		mav.addObject("strIsUsed", strIsUsed);

		mav.addObject("item", item);
		mav.addObject("url", Constants.ITEM_IMAGE_URL);

		int itemGrp3 = item.getItemGrp3();
		mav.addObject("itemGrp3", String.valueOf(itemGrp3));

		return mav;

	}

	@RequestMapping(value = "/updateItem/updateItemProcess", method = RequestMethod.POST)

	public String updateMessage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("item_image") MultipartFile file) {
		System.out.println("HI");

		ModelAndView model = new ModelAndView("items/itemList");

		RestTemplate restTemplate = new RestTemplate();
		String itemId = request.getParameter("item_id");

		int minQty = Integer.parseInt(request.getParameter("min_qty"));

		String itemName = request.getParameter("item_name");

		String itemGrp1 = request.getParameter("item_grp1");

		String itemGrp2 = request.getParameter("item_grp2");

		String itemGrp3 = request.getParameter("item_grp3");

		double itemRate1 = Double.parseDouble(request.getParameter("item_rate1"));

		double itemRate2 = Double.parseDouble(request.getParameter("item_rate2"));

		double itemRate3 = Double.parseDouble(request.getParameter("item_rate3"));

		double itemMrp1 = Double.parseDouble(request.getParameter("item_mrp1"));

		double itemMrp2 = Double.parseDouble(request.getParameter("item_mrp2"));

		double itemMrp3 = Double.parseDouble(request.getParameter("item_mrp3"));

		/*
		 * String itemImage = request.getParameter("item_image");
		 */
		double itemTax1 = Double.parseDouble(request.getParameter("item_tax1"));

		double itemTax2 = Double.parseDouble(request.getParameter("item_tax2"));

		double itemTax3 = Double.parseDouble(request.getParameter("item_tax3"));

		int itemIsUsed = Integer.parseInt(request.getParameter("is_used"));

		double itemSortId = Double.parseDouble(request.getParameter("item_sort_id"));

		int grnTwo = Integer.parseInt(request.getParameter("grn_two"));
		int id = Integer.parseInt(request.getParameter("itemId"));
		int shelfLife = Integer.parseInt(request.getParameter("item_shelf_life"));

		logger.info("Add new item request mapping.");

		String itemImage = request.getParameter("prevImage");

		if (!file.getOriginalFilename().equalsIgnoreCase("")) {

			System.out.println("Empty image");
			itemImage = ImageS3Util.uploadItemImage(file);
		}

		RestTemplate rest = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("itemId", itemId);
		map.add("itemName", itemName);
		map.add("itemGrp1", itemGrp1);
		map.add("itemGrp2", itemGrp2);
		map.add("itemGrp3", itemGrp3);
		map.add("itemRate1", itemRate1);
		map.add("itemRate2", itemRate2);
		map.add("itemRate3", itemRate3);
		map.add("minQty", minQty);
		map.add("itemMrp1", itemMrp1);
		map.add("itemMrp2", itemMrp2);
		map.add("itemMrp3", itemMrp3);
		map.add("itemImage", itemImage);
		map.add("itemTax1", itemTax1);
		map.add("itemTax2", itemTax2);
		map.add("itemTax3", itemTax3);
		map.add("itemIsUsed", itemIsUsed);
		map.add("itemSortId", itemSortId);
		map.add("grnTwo", grnTwo);
		map.add("id", id);

		map.add("itemShelfLife", shelfLife);
		ErrorMessage errorResponse = rest.postForObject("" + Constants.url + "updateItem", map, ErrorMessage.class);

		return "redirect:/itemList";

	}
	
	@RequestMapping(value = "/showAddItemSup", method = RequestMethod.GET)
	public ModelAndView showAddItemSup(HttpServletRequest request, HttpServletResponse response) {
		Constants.mainAct = 4;
		Constants.subAct = 45;
		ModelAndView model = new ModelAndView("items/itemSup");
		try
		{		
			RestTemplate restTemplate = new RestTemplate();

			categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
					CategoryListResponse.class);
			mCategoryList = categoryListResponse.getmCategoryList();
			List<MCategoryList> resCatList=new ArrayList<MCategoryList>();
			for(MCategoryList mCat:mCategoryList)
			{
				if(mCat.getCatId()!=5 && mCat.getCatId()!=6)
				{
					resCatList.add(mCat);
				}
			}

			model.addObject("mCategoryList", resCatList);
			model.addObject("isEdit", 0);
		}
		catch(Exception e)
		{
			System.out.println("Excption In /showAddItemSup");
		}
		return model;

	}
	@RequestMapping(value = "/getItemsByCatId", method = RequestMethod.GET)
	public @ResponseBody List<Item> getItemsByCatId(HttpServletRequest request, HttpServletResponse response) {
	
		ArrayList<Item> itemsList=new ArrayList<Item>();
		try
		{
	int catId = Integer.parseInt(request.getParameter("cat_id"));
	System.out.println("cat Id "+catId);

	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	map.add("itemGrp1", catId);

	RestTemplate restTemplate = new RestTemplate();

	Item[] item = restTemplate.postForObject(Constants.url + "getItemsByCatId", map, Item[].class);

	itemsList = new ArrayList<Item>(Arrays.asList(item));
	
	
		}
		catch(Exception e)
		{
			System.out.println("Exception in /AJAX getItemsByCatId");
		}
	return itemsList;
	}
	@RequestMapping(value = "/showItemSupList", method = RequestMethod.GET)
	public ModelAndView itemSupList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("items/itemSupList");
		Constants.mainAct = 4;
		Constants.subAct = 46;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
              ItemSupList itemSupList=restTemplate.getForObject(Constants.url+"/getItemSupList", ItemSupList.class);
			
			mav.addObject("itemsList", itemSupList.getItemSupList());
			
		} catch (Exception e) {
			System.out.println("Exc In /itemSupList" + e.getMessage());
		}

		return mav;

	}
	// ------------------------------ADD ItemSup Process------------------------------------
		@RequestMapping(value = "/addItemSupProcess", method = RequestMethod.POST)
		public String addItemSupProcess(HttpServletRequest request, HttpServletResponse response) {

			ModelAndView model = new ModelAndView("items/itemSup");
			try {

				int id = 0;

				try {
					id = Integer.parseInt(request.getParameter("id"));

				} catch (Exception e) {
					id = 0;
					System.out.println("In Catch of Add ItemSup Process Exc:" + e.getMessage());

				}
				int itemId = Integer.parseInt(request.getParameter("sel_item_id"));

				String itemHsncd = request.getParameter("item_hsncd");

				String itemUom = request.getParameter("item_uom");

				float actualWeight = Float.parseFloat(request.getParameter("actual_weight"));

				float baseWeight = Float.parseFloat(request.getParameter("base_weight"));

				float inputPerQty = Float.parseFloat(request.getParameter("input_per_qty"));
				
				int isGateSale = Integer.parseInt(request.getParameter("is_gate_sale"));
				
				int isGateSaleDisc = Integer.parseInt(request.getParameter("is_gate_sale_disc"));

				int isAllowBday= Integer.parseInt(request.getParameter("is_allow_bday"));
				
				ItemSup itemSup=new ItemSup();
				itemSup.setId(id);
				itemSup.setItemId(itemId);
				itemSup.setItemUom(itemUom);
				itemSup.setItemHsncd(itemHsncd);
				itemSup.setIsGateSale(isGateSale);
				itemSup.setActualWeight(actualWeight);
				itemSup.setBaseWeight(baseWeight);
				itemSup.setInputPerQty(inputPerQty);
				itemSup.setIsGateSaleDisc(isGateSaleDisc);
				itemSup.setIsAllowBday(isAllowBday);
				itemSup.setDelStatus(0);
				
				RestTemplate restTemplate = new RestTemplate();

				Info info = restTemplate.postForObject(Constants.url + "/saveItemSup",
						itemSup, Info.class);
				System.out.println("Response: " + info.toString());

				if (info.getError() == true) {

					System.out.println("Error:True" + info.toString());
					return "redirect:/showItemSupList";

				} else {
					return "redirect:/showItemSupList";
				}

			} catch (Exception e) {

				System.out.println("Exception In Add Item Sup Process:" + e.getMessage());

			}

			return "redirect:/showItemSupList";
		}

		// ----------------------------------------END---------------------------------------------------
	
		@RequestMapping(value = "/updateItemSup/{id}", method = RequestMethod.GET)
		public ModelAndView updateItemSup(@PathVariable("id")int id,HttpServletRequest request, HttpServletResponse response) {
			ModelAndView mav = new ModelAndView("items/itemSup");
			

			RestTemplate restTemplate = new RestTemplate();
			
			try {
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("id", id);
				
	              GetItemSup itemSupRes=restTemplate.postForObject(Constants.url+"/getItemSup",map, GetItemSup.class);
				System.out.println("itemSupRes"+itemSupRes.toString() );
				mav.addObject("itemSupp", itemSupRes);
				mav.addObject("isEdit", 1);

			} catch (Exception e) {
				System.out.println("Exc In /updateItemSup" + e.getMessage());
			}

			return mav;

		}
	
}

