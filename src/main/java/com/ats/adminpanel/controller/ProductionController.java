package com.ats.adminpanel.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.DateConvertor;

import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.Variance;
import com.ats.adminpanel.model.VarianceList;
import com.ats.adminpanel.model.franchisee.AllMenuResponse;
import com.ats.adminpanel.model.franchisee.Menu;
import com.ats.adminpanel.model.item.AllItemsListResponse;
import com.ats.adminpanel.model.item.CategoryListResponse;
import com.ats.adminpanel.model.item.Item;
import com.ats.adminpanel.model.item.MCategoryList;
import com.ats.adminpanel.model.production.GetOrderItemQty;
import com.ats.adminpanel.model.production.GetRegSpCakeOrderQty;
import com.ats.adminpanel.model.production.PostProdPlanHeader;
import com.ats.adminpanel.model.production.PostProductionDetail;
import com.ats.adminpanel.model.production.PostProductionHeader;
import com.ats.adminpanel.model.production.PostProductionPlanDetail;
import com.ats.adminpanel.model.production.UpdateOrderStatus;
import com.ats.adminpanel.model.stock.FinishedGoodStock;
import com.ats.adminpanel.model.stock.FinishedGoodStockDetail;
import com.ats.adminpanel.model.stock.GetCurProdAndBillQty;
import com.ats.adminpanel.model.stock.GetCurProdAndBillQtyList;

@Controller
@Scope("session")
public class ProductionController {

	// AllFrIdNameList allFrIdNameList;
	List<Menu> menuList;
	public static List<MCategoryList> categoryList;
	public static String selectedCat;
	public static String productionDate;
	public static List<GetRegSpCakeOrderQty> getRegSpCakeOrderQtyList;
	public static List<GetOrderItemQty> getOrderItemQtyList;
	public static int[] timeSlot;

	
	int selCate;
	
	GetCurProdAndBillQtyList getCurProdAndBillQtyList = new GetCurProdAndBillQtyList();

	@RequestMapping(value = "/showproduction", method = RequestMethod.GET)
	public ModelAndView showProdForcasting(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("production/production");
		Constants.mainAct = 4;
		Constants.subAct = 32;

		RestTemplate restTemplate = new RestTemplate();

		CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
				CategoryListResponse.class);

		categoryList = categoryListResponse.getmCategoryList();
		// allFrIdNameList = new AllFrIdNameList();
		System.out.println("Category list  " + categoryList);
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
		model.addObject("todayDate", new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		model.addObject("unSelectedCatList", categoryList);
		model.addObject("productionTimeSlot", timeSlot);

		return model;
	}

	@RequestMapping(value = "/getMenu", method = RequestMethod.GET)
	public @ResponseBody List<Menu> getMenu(HttpServletRequest request, HttpServletResponse response) {

		selectedCat = request.getParameter("selectedCat");
		selCate=Integer.parseInt(request.getParameter("selectedCat"));
		
		//selCate=Integer.parseInt(request.getParameter("selectedCat"));
		
		System.out.println("Inside getMenu seleCatId VAlue "+selCate);

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		RestTemplate rest = new RestTemplate();

		map.add("catId", selectedCat);

		try {

			AllMenuResponse allMenuResponse = rest.postForObject(Constants.url + "getMenuByCat", map,
					AllMenuResponse.class);

			menuList = allMenuResponse.getMenuConfigurationPage();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("List of Menu : " + menuList.toString());

		return menuList;

	}

	@RequestMapping(value = "/getProductionOrder", method = RequestMethod.GET)
	public @ResponseBody List<GetOrderItemQty> generateOrderList(HttpServletRequest request,
			HttpServletResponse response) {

		getOrderItemQtyList = new ArrayList<GetOrderItemQty>();

		productionDate = request.getParameter("productionDate");
		String selectedMenuList = request.getParameter("selectedMenu_list");

		selectedMenuList = selectedMenuList.substring(1, selectedMenuList.length() - 1);
		selectedMenuList = selectedMenuList.replaceAll("\"", "");
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		RestTemplate rest = new RestTemplate();

		map.add("productionDate", productionDate);
		map.add("menuId", selectedMenuList);
		try {
			ParameterizedTypeReference<List<GetOrderItemQty>> typeRef = new ParameterizedTypeReference<List<GetOrderItemQty>>() {
			};
			ResponseEntity<List<GetOrderItemQty>> responseEntity = rest.exchange(Constants.url + "getOrderAllItemQty",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);

			getOrderItemQtyList = responseEntity.getBody();

			// getOrderItemQtyList=rest.postForObject(Constants.url + "getOrderAllItemQty",
			// map, List.class);
			
			
			
			//new code for getting current stock
			RestTemplate restTemplate = new RestTemplate();

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
			map.add("catId", selCate);
			map.add("delStatus", 0);

			getCurProdAndBillQtyList = restTemplate.postForObject(Constants.url + "getCurrentProdAndBillQty", map,
					GetCurProdAndBillQtyList.class);

			getCurProdAndBillQty = getCurProdAndBillQtyList.getGetCurProdAndBillQty();

			System.out.println("Cur Prod And Bill Qty Listy " + getCurProdAndBillQty.toString());
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

			String stkDate = df.format(stockDate);
			map = new LinkedMultiValueMap<String, Object>();
			map.add("stockDate", stkDate);
			//RestTemplate restTemplate = new RestTemplate();

			ParameterizedTypeReference<List<FinishedGoodStockDetail>> typeRef1 = new ParameterizedTypeReference<List<FinishedGoodStockDetail>>() {
			};
			ResponseEntity<List<FinishedGoodStockDetail>> responseEntity1 = restTemplate
					.exchange(Constants.url + "getFinGoodStockDetail", HttpMethod.POST, new HttpEntity<>(map), typeRef1);

			List<FinishedGoodStockDetail> finGoodDetail = responseEntity1.getBody();

			System.out.println("Finished Good Stock Detail " + finGoodDetail.toString());

			// new code
			List<FinishedGoodStockDetail> updateStockDetailList=new ArrayList<>();

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
			
			for(int i=0;i<getOrderItemQtyList.size();i++) {
				
				for(int j=0;j<updateStockDetailList.size();j++) {
					if(Integer.parseInt(getOrderItemQtyList.get(i).getItemId())==updateStockDetailList.get(j).getItemId()) {
						
						getOrderItemQtyList.get(i).setCurClosingQty(updateStockDetailList.get(j).getCloCurrent());
						getOrderItemQtyList.get(i).setCurOpeQty(updateStockDetailList.get(j).getTotalCloStk());
					}
				}
			}
			
			//end of new Code 

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// System.out.println("List of Orders : "+ getOrderItemQtyList.toString());

		return getOrderItemQtyList;

	}

	@RequestMapping(value = "/getProductionRegSpCakeOrder", method = RequestMethod.GET)
	public @ResponseBody List<GetRegSpCakeOrderQty> generateRegSpCakeOrderList(HttpServletRequest request,
			HttpServletResponse response) {

		getRegSpCakeOrderQtyList = new ArrayList<GetRegSpCakeOrderQty>();

		String productionDate = request.getParameter("productionDate");
		String selectedMenuList = request.getParameter("selectedMenu_list");

		selectedMenuList = selectedMenuList.substring(1, selectedMenuList.length() - 1);
		selectedMenuList = selectedMenuList.replaceAll("\"", "");

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		// List<String> selectedList=new ArrayList<>();
		// selectedList=Arrays.asList(selectedMenuList.split(","));

		RestTemplate rest = new RestTemplate();

		map.add("productionDate", productionDate);
		map.add("menuId", selectedMenuList);
		try {
			ParameterizedTypeReference<List<GetRegSpCakeOrderQty>> typeRef = new ParameterizedTypeReference<List<GetRegSpCakeOrderQty>>() {
			};
			ResponseEntity<List<GetRegSpCakeOrderQty>> responseEntity = rest.exchange(
					Constants.url + "getOrderQtyRegSpCakeAllItems", HttpMethod.POST, new HttpEntity<>(map), typeRef);

			getRegSpCakeOrderQtyList = responseEntity.getBody();
			// getRegSpCakeOrderQtyList=rest.postForObject(Constants.url +
			// "getOrderQtyRegSpCakeAllItems", map, List.class);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("List of Orders : " + getRegSpCakeOrderQtyList.toString());

		return getRegSpCakeOrderQtyList;

	}

	java.sql.Date convertedDate;

	@RequestMapping(value = "/submitProduction", method = RequestMethod.POST)
	public String submitProduction(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("production/production");

		// String productionDate=request.getParameter("production_date");
		String selectTime = request.getParameter("selectTime");
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
			for (int i = 0; i < getOrderItemQtyList.size(); i++) {

				System.out.println("item  Id " + getOrderItemQtyList.get(i).getItemId());
			}
			RestTemplate restTemplate = new RestTemplate();

			PostProductionHeader postProductionHeader = new PostProductionHeader();

			postProductionHeader.setTimeSlot(timeSlot);
			postProductionHeader.setItemGrp1(Integer.parseInt(selectedCat));
			postProductionHeader.setProductionDate(convertedDate);
			postProductionHeader.setDelStatus(0);
			postProductionHeader.setIsBom(0);

			postProductionHeader.setIsMixing(0);
			postProductionHeader.setIsPlanned(0);
			postProductionHeader.setProductionBatch("");
			postProductionHeader.setProductionStatus(2);

			List<PostProductionDetail> postProductionDetailList = new ArrayList<>();
			PostProductionDetail postProductionDetail;

			System.out.println("List    :" + getOrderItemQtyList);
			List<String> orderId = new ArrayList<String>();

			for (int i = 0; i < getOrderItemQtyList.size(); i++) {
				postProductionDetail = new PostProductionDetail();
				String a = getOrderItemQtyList.get(i).getItemId();
				// System.out.println("a============"+a);

				postProductionDetail.setItemId(Integer.parseInt(a));

				postProductionDetail.setOrderQty(getOrderItemQtyList.get(i).getQty());
				postProductionDetail.setProductionDate(convertedDate);
				postProductionDetail.setOpeningQty(0);
				postProductionDetail.setProductionQty(0);
				postProductionDetail.setProductionBatch("");
				postProductionDetail.setRejectedQty(0);
				postProductionDetail.setPlanQty(0);

				postProductionDetailList.add(postProductionDetail);

				orderId.add(String.valueOf(getOrderItemQtyList.get(i).getOrderId()));
			}

			List<Integer> regOrderId = new ArrayList<Integer>();
			regOrderId.add(0);
			for (int i = 0; i < getRegSpCakeOrderQtyList.size(); i++) {
				postProductionDetail = new PostProductionDetail();

				postProductionDetail.setItemId(getRegSpCakeOrderQtyList.get(i).getItemId());
				postProductionDetail.setOrderQty(getRegSpCakeOrderQtyList.get(i).getQty());
				postProductionDetail.setProductionDate(convertedDate);
				postProductionDetail.setOpeningQty(0);
				postProductionDetail.setProductionQty(0);
				postProductionDetail.setRejectedQty(0);
				postProductionDetail.setProductionBatch("");
				postProductionDetail.setPlanQty(0);
				postProductionDetailList.add(postProductionDetail);

				regOrderId.add(getRegSpCakeOrderQtyList.get(i).getItemId());
			}

			postProductionHeader.setPostProductionDetail(postProductionDetailList);
			try {

				Info info = restTemplate.postForObject(Constants.url + "postProduction", postProductionHeader,
						Info.class);

				System.out.println("Info After post to production :   " + info.toString());

				UpdateOrderStatus updateOrderStatus = new UpdateOrderStatus();
				List<String> res = new ArrayList<String>();
				res.add("0");
				for (int i = 0; i < getOrderItemQtyList.size(); i++) {
					String orderId1 = getOrderItemQtyList.get(i).getItemId();
					res.add(orderId1);
				}
				updateOrderStatus.setOrderItemId(res);
				updateOrderStatus.setRegOrderItemId(regOrderId);
				updateOrderStatus.setProdDate(convertedDate);

				info = restTemplate.postForObject(Constants.url + "updateIsBillGenerate", updateOrderStatus,
						Info.class);

				System.out.println("Info After update status  :    " + info.toString());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			// model.addObject("unSelectedCatList", categoryList);
			// model.addObject("productionTimeSlot", timeSlot);
		}
		return "redirect:/showproduction";
	}

	@RequestMapping(value = "/addForecasting", method = RequestMethod.GET)
	public ModelAndView showAddProdForcasting(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("production/addProdForecasting");
		// Constants.mainAct = 8;
		// Constants.subAct = 82;

		RestTemplate restTemplate = new RestTemplate();

		CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
				CategoryListResponse.class);

		categoryList = categoryListResponse.getmCategoryList();
		// allFrIdNameList = new AllFrIdNameList();
		System.out.println("Category list  " + categoryList);
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

		model.addObject("unSelectedCatList", categoryList);
		model.addObject("productionTimeSlot", timeSlot);

		return model;
	}

	@RequestMapping(value = "/getProdOrderForecating", method = RequestMethod.GET)
	public @ResponseBody List<GetOrderItemQty> getProdOrderForecating(HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("In method");
		getOrderItemQtyList = new ArrayList<GetOrderItemQty>();

		productionDate = request.getParameter("productionDate");
		String selectedMenuList = request.getParameter("selectedMenu_list");

		selectedMenuList = selectedMenuList.substring(1, selectedMenuList.length() - 1);
		selectedMenuList = selectedMenuList.replaceAll("\"", "");
		String timeSlot = request.getParameter("timeSlot");
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		RestTemplate rest = new RestTemplate();

		map.add("productionDate", productionDate);
		map.add("menuId", selectedMenuList);
		try {
			ParameterizedTypeReference<List<GetOrderItemQty>> typeRef = new ParameterizedTypeReference<List<GetOrderItemQty>>() {
			};
			ResponseEntity<List<GetOrderItemQty>> responseEntity = rest.exchange(Constants.url + "getOrderAllItemQty",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);

			getOrderItemQtyList = responseEntity.getBody();

			// getOrderItemQtyList=rest.postForObject(Constants.url + "getOrderAllItemQty",
			// map, List.class);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("List of Orders : " + getOrderItemQtyList.toString());

		return getOrderItemQtyList;

	}

	// -----------------------------------------------------Variation--------------------------------------
	List<PostProductionPlanDetail> postProductionPlanDetaillist = new ArrayList<PostProductionPlanDetail>();
	PostProdPlanHeader postProdPlanHeader = new PostProdPlanHeader();
	CategoryListResponse categoryListComp = new CategoryListResponse();

	@RequestMapping(value = "/listForVariation", method = RequestMethod.GET)
	public ModelAndView listForVariation(HttpServletRequest request, HttpServletResponse response) {

		postProductionPlanDetaillist = new ArrayList<PostProductionPlanDetail>();
		ModelAndView model = new ModelAndView("production/variation");
		Constants.mainAct = 4;
		Constants.subAct = 35;

		try {
			RestTemplate restTemplate = new RestTemplate();
			List<PostProdPlanHeader> postProdPlanHeader = restTemplate
					.getForObject(Constants.url + "PostProdPlanHeaderVariationlist", List.class);

			categoryListComp = restTemplate.getForObject(Constants.url + "showAllCategory", CategoryListResponse.class);
			System.out.println("categoryListComp " + categoryListComp.getmCategoryList().toString());

			System.out.println("postProdPlanHeader" + postProdPlanHeader.toString());
			model.addObject("postProdPlanHeaderList", postProdPlanHeader);
			model.addObject("categoryList", categoryListComp.getmCategoryList());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/varianceDetailed", method = RequestMethod.GET)
	public ModelAndView varianceDetailed(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("production/variancelistdetailed");
		PostProductionPlanDetail postProductionPlanDetail = new PostProductionPlanDetail();
		// Constants.mainAct = 8;
		// Constants.subAct = 82;

		int productionHeaderId = Integer.parseInt(request.getParameter("productionHeaderId"));
		System.out.println("productionHeaderId" + productionHeaderId);
		try {
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("planHeaderId", productionHeaderId);

			postProdPlanHeader = restTemplate.postForObject(Constants.url + "PostProdPlanHeaderwithDetailed", map,
					PostProdPlanHeader.class);
			int groupType = postProdPlanHeader.getItemGrp1();
			String prodDate = postProdPlanHeader.getProductionDate();
			System.out.println(prodDate);
			String date = DateConvertor.convertToYMD(prodDate);

			map = new LinkedMultiValueMap<String, Object>();
			map.add("Date", date);
			map.add("groupType", groupType);

			VarianceList getQtyforVariance = restTemplate.postForObject(Constants.url + "getQtyforVariance", map,
					VarianceList.class);

			System.out.println(getQtyforVariance.getVarianceorderlist().size() + "getQtyforVariance"
					+ getQtyforVariance.getVarianceorderlist().toString());
			System.out.println("postProdPlanHeader" + postProdPlanHeader.toString());

			postProductionPlanDetaillist = postProdPlanHeader.getPostProductionPlanDetail();

			model.addObject("getQtyforVariance", getQtyforVariance.getVarianceorderlist());
			System.out.println("unsort size " + getQtyforVariance.getVarianceorderlist().size());

			List<Variance> getVarianceorderlistforsort = new ArrayList<Variance>();
			getVarianceorderlistforsort = getQtyforVariance.getVarianceorderlist();

			for (int i = 0; i < postProductionPlanDetaillist.size(); i++) {
				int planItemid = postProductionPlanDetaillist.get(i).getItemId();

				for (int j = 0; j < getVarianceorderlistforsort.size(); j++) {
					int varianceItemId = getVarianceorderlistforsort.get(j).getId();

					if (planItemid == varianceItemId) {

						int orderQty = getVarianceorderlistforsort.get(j).getOrderQty()
								+ getVarianceorderlistforsort.get(j).getProdRejectedQty();
						System.out.println("updated orderQty" + orderQty);
						postProductionPlanDetaillist.get(i).setOrderQty(orderQty);

						int remainingProQty = postProductionPlanDetaillist.get(i).getOrderQty()
								- (postProductionPlanDetaillist.get(i).getOpeningQty()
										+ postProductionPlanDetaillist.get(i).getProductionQty());

						if (remainingProQty > 0) {
							postProductionPlanDetaillist.get(i).setInt4(remainingProQty);
						} else {
							postProductionPlanDetaillist.get(i).setInt4(0);
						}
						getVarianceorderlistforsort.remove(j);
					}

				}

			}
			AllItemsListResponse allItemsListResponse = restTemplate.getForObject(Constants.url + "getAllItems",
					AllItemsListResponse.class);
			List<Item> itemsList = allItemsListResponse.getItems();

			System.out.println("getVarianceorderlistforsort size " + getVarianceorderlistforsort.size());
			System.out.println("unsort size " + getQtyforVariance.getVarianceorderlist().size());
			System.out.println(postProductionPlanDetaillist.toString());

			// new Code
			List<FinishedGoodStockDetail> updateStockDetailList = new ArrayList<>();

			try {
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
				
				System.out.println("stk CatId for get Cur Prod and Bill Qty "+groupType);

				map.add("prodDate", stkDate);
				map.add("catId", groupType);
				map.add("delStatus", 0);

				getCurProdAndBillQtyList = restTemplate.postForObject(Constants.url + "getCurrentProdAndBillQty", map,
						GetCurProdAndBillQtyList.class);

				getCurProdAndBillQty = getCurProdAndBillQtyList.getGetCurProdAndBillQty();

				System.out.println("Cur Prod And Bill Qty Listy " + getCurProdAndBillQty.toString());
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

				String stockkDate = df.format(stockDate);
				map = new LinkedMultiValueMap<String, Object>();
				map.add("stockDate", stockkDate);

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

			for (int i = 0; i < postProductionPlanDetaillist.size(); i++) {

				for (int j = 0; j < updateStockDetailList.size(); j++) {

					if (postProductionPlanDetaillist.get(i).getItemId() == updateStockDetailList.get(j).getItemId()) {

						postProductionPlanDetaillist.get(i)
								.setCurClosingQty(updateStockDetailList.get(j).getCloCurrent());

						postProductionPlanDetaillist.get(i).setCurOpeQty(updateStockDetailList.get(j).getTotalCloStk());

					}

				}

			}

			

			// end of new Code

			model.addObject("postProdPlanHeader", postProdPlanHeader);
			model.addObject("getVarianceorderlistforsort", getVarianceorderlistforsort);
			model.addObject("itemsList", itemsList);
			model.addObject("categoryList", categoryListComp.getmCategoryList());
			model.addObject("postProdPlanHeaderDetailed", postProductionPlanDetaillist);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/updateOrderQtyinPlan", method = RequestMethod.POST)
	public String updateOrderQtyinPlan(HttpServletRequest request, HttpServletResponse response) {

		List<PostProductionPlanDetail> postProductionPlanDetailnewplan = new ArrayList<PostProductionPlanDetail>();
		PostProductionPlanDetail postProductionPlanDetailnew = new PostProductionPlanDetail();
		PostProdPlanHeader postProdPlanHeadernewplan = new PostProdPlanHeader();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String Pdate = formatter.format(date);
		System.out.println(Pdate);

		RestTemplate restTemplate = new RestTemplate();
		try {
			postProdPlanHeadernewplan.setProductionStatus(2);
			postProdPlanHeadernewplan.setItemGrp1(postProdPlanHeader.getItemGrp1());
			postProdPlanHeadernewplan.setProductionDate(Pdate);
			postProdPlanHeadernewplan.setTimeSlot(postProdPlanHeader.getTimeSlot());
			postProdPlanHeadernewplan.setProductionBatch("");
			for (int i = 0; i < postProductionPlanDetaillist.size(); i++) {
				if (postProductionPlanDetaillist.get(i).getInt4() > 0) {
					postProductionPlanDetailnew = new PostProductionPlanDetail();
					postProductionPlanDetailnew.setItemId(postProductionPlanDetaillist.get(i).getItemId());
					postProductionPlanDetailnew.setOpeningQty(0);
					postProductionPlanDetailnew.setOrderQty(postProductionPlanDetaillist.get(i).getInt4());
					postProductionPlanDetailnew.setProductionQty(0);
					postProductionPlanDetailnew.setRejectedQty(0);
					postProductionPlanDetailnew.setPlanQty(0);
					postProductionPlanDetailnew.setInt4(0);
					postProductionPlanDetailnew.setProductionDate(Pdate);
					postProductionPlanDetailnew.setProductionBatch("");
					postProductionPlanDetailnewplan.add(postProductionPlanDetailnew);

				}
			}

			postProdPlanHeader.setProductionStatus(5);
			postProdPlanHeader.setPostProductionPlanDetail(postProductionPlanDetaillist);
			Info updateOrderQtyinPlan = restTemplate.postForObject(Constants.url + "postProductionPlan",
					postProdPlanHeader, Info.class);
			System.out.println("updateOrderQtyinPlan " + updateOrderQtyinPlan);
			if (updateOrderQtyinPlan.getError() == false) {
				System.out.println("in if insert new plan");
				postProdPlanHeadernewplan.setPostProductionPlanDetail(postProductionPlanDetailnewplan);
				Info insertNewinPlan = restTemplate.postForObject(Constants.url + "postProductionPlan",
						postProdPlanHeadernewplan, Info.class);
				System.out.println("insertNewinPlan" + insertNewinPlan);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listForVariation";
	}

}
