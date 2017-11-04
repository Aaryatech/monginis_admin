package com.ats.adminpanel.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.ats.adminpanel.model.franchisee.AllMenuResponse;
import com.ats.adminpanel.model.franchisee.Menu;
import com.ats.adminpanel.model.item.CategoryListResponse;
import com.ats.adminpanel.model.item.Item;
import com.ats.adminpanel.model.item.MCategoryList;
import com.ats.adminpanel.model.production.GetOrderItemQty;
import com.ats.adminpanel.model.production.GetRegSpCakeOrderQty;
import com.ats.adminpanel.model.production.PostProductionDetail;
import com.ats.adminpanel.model.production.PostProductionHeader;

@Controller
public class ProductionController {

	//AllFrIdNameList allFrIdNameList;
	List<Menu> menuList ;
	public static List<MCategoryList> categoryList ;
	public static String selectedCat;
	public static String productionDate;
	public static List<GetRegSpCakeOrderQty> getRegSpCakeOrderQtyList;
	public static List<GetOrderItemQty> getOrderItemQtyList;
	public static int []timeSlot ;
	
	@RequestMapping(value = "/showproduction", method = RequestMethod.GET)
	public ModelAndView showProdForcasting(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("production/production");
//		Constants.mainAct = 8;
//		Constants.subAct = 82;

		

		RestTemplate restTemplate = new RestTemplate();
		
		

		CategoryListResponse categoryListResponse = restTemplate.getForObject(Constants.url + "showAllCategory",
				CategoryListResponse.class);

		categoryList = categoryListResponse.getmCategoryList();
		//allFrIdNameList = new AllFrIdNameList();
		System.out.println("Category list  " +categoryList);
		int productionTimeSlot=0;
		try {

			  productionTimeSlot = restTemplate.getForObject(Constants.url + "getProductionTimeSlot", Integer.class);
			  System.out.println("time slot  "  +productionTimeSlot);
		}catch (Exception e) {
			//System.out.println(e.getMessage());
			//e.printStackTrace();
			
		}
	
		
		timeSlot = new int[productionTimeSlot] ;
		for(int i=0;i<productionTimeSlot;i++)
			timeSlot[i]=i+1;
		
		model.addObject("unSelectedCatList", categoryList);
		model.addObject("productionTimeSlot", timeSlot);

		return model;
	}
	
	@RequestMapping(value = "/getMenu", method = RequestMethod.GET)
	public @ResponseBody List<Menu> getMenu(HttpServletRequest request,
		HttpServletResponse response) {
		
		 
		selectedCat=request.getParameter("selectedCat");

		MultiValueMap<String, Object> map=new LinkedMultiValueMap<String, Object>();
		RestTemplate rest=new RestTemplate();
		
		map.add("catId", selectedCat);
		
		try {
	
		AllMenuResponse allMenuResponse = rest.postForObject(Constants.url + "getMenuByCat",map,
				AllMenuResponse.class);
		
		menuList = allMenuResponse.getMenuConfigurationPage();
	
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("List of Menu : "+ menuList.toString());
		
		return menuList;
		
	}
	
	
	@RequestMapping(value = "/getProductionOrder", method = RequestMethod.GET)
	public @ResponseBody List<GetOrderItemQty> generateOrderList(HttpServletRequest request,
		HttpServletResponse response) {
		
		  getOrderItemQtyList=new ArrayList<GetOrderItemQty>();
		
		 productionDate=request.getParameter("productionDate");
		String selectedMenuList=request.getParameter("selectedMenu_list");

		selectedMenuList = selectedMenuList.substring(1, selectedMenuList.length() - 1);
		selectedMenuList = selectedMenuList.replaceAll("\"", "");
		MultiValueMap<String, Object> map=new LinkedMultiValueMap<String, Object>();
		
		RestTemplate rest=new RestTemplate();
		
		map.add("productionDate", productionDate);
		map.add("menuId", selectedMenuList);
		try
			{
			ParameterizedTypeReference<List<GetOrderItemQty>> typeRef = new ParameterizedTypeReference<List<GetOrderItemQty>>() {
			};
			ResponseEntity<List<GetOrderItemQty>> responseEntity = rest.exchange(Constants.url + "getOrderAllItemQty",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);
			
			getOrderItemQtyList = responseEntity.getBody();
			
			//getOrderItemQtyList=rest.postForObject(Constants.url + "getOrderAllItemQty", map, List.class);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("List of Orders : "+ getOrderItemQtyList.toString());
		
		return getOrderItemQtyList;
		
	}
	
	@RequestMapping(value = "/getProductionRegSpCakeOrder", method = RequestMethod.GET)
	public @ResponseBody List<GetRegSpCakeOrderQty> generateRegSpCakeOrderList(HttpServletRequest request,
		HttpServletResponse response) {
		
	
		getRegSpCakeOrderQtyList=new ArrayList<GetRegSpCakeOrderQty>();
		
		String productionDate=request.getParameter("productionDate");
		String selectedMenuList=request.getParameter("selectedMenu_list");

		selectedMenuList = selectedMenuList.substring(1, selectedMenuList.length() - 1);
		selectedMenuList = selectedMenuList.replaceAll("\"", "");
		
		MultiValueMap<String, Object> map=new LinkedMultiValueMap<String, Object>();
		
		
//		List<String> selectedList=new ArrayList<>();
//		selectedList=Arrays.asList(selectedMenuList.split(","));
		
		RestTemplate rest=new RestTemplate();
		
		map.add("productionDate", productionDate);
		map.add("menuId", selectedMenuList);
		try
			{
			ParameterizedTypeReference<List<GetRegSpCakeOrderQty>> typeRef = new ParameterizedTypeReference<List<GetRegSpCakeOrderQty>>() {
			};
			ResponseEntity<List<GetRegSpCakeOrderQty>> responseEntity = rest.exchange(Constants.url + "getOrderQtyRegSpCakeAllItems",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);
			
			getRegSpCakeOrderQtyList = responseEntity.getBody();
			//getRegSpCakeOrderQtyList=rest.postForObject(Constants.url + "getOrderQtyRegSpCakeAllItems", map, List.class);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("List of Orders : "+ getRegSpCakeOrderQtyList.toString());
		
		return getRegSpCakeOrderQtyList;
		
	}
	
	java.sql.Date convertedDate;
	
	@RequestMapping(value = "/submitProduction", method = RequestMethod.POST)
	public String submitProduction(HttpServletRequest request, HttpServletResponse response) {

		//ModelAndView model = new ModelAndView("production/production");

//String productionDate=request.getParameter("production_date");
			String selectTime=request.getParameter("selectTime");
			String convertedDate=null;
			if(productionDate!=null && productionDate!="" && selectTime!=null && selectTime!="")
			{
try {
	SimpleDateFormat ymdSDF = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat dmySDF = new SimpleDateFormat("dd-MM-yyyy");
	Date dmyDate = dmySDF.parse(productionDate);
	
	convertedDate=ymdSDF.format(dmyDate);
	
} catch (ParseException e) {
	
	e.printStackTrace();
}
			
int timeSlot=Integer.parseInt(selectTime);

System.out.println("Date  :  "+convertedDate);
for(int i=0;i<getOrderItemQtyList.size();i++)
	{
	
	System.out.println("item  Id "+getOrderItemQtyList.get(i).getItemId());
	}
		RestTemplate restTemplate = new RestTemplate();

	PostProductionHeader postProductionHeader=new PostProductionHeader();
	
		postProductionHeader.setTimeSlot(timeSlot);
		postProductionHeader.setItemGrp1(Integer.parseInt(selectedCat));
		postProductionHeader.setProductionDate(convertedDate);
		
	List<PostProductionDetail> postProductionDetailList=new ArrayList<>();
	PostProductionDetail postProductionDetail;
	
	
	System.out.println("List    :"+getOrderItemQtyList);
	
	for(int i=0;i<getOrderItemQtyList.size();i++)
		{
		postProductionDetail=new PostProductionDetail();
		String a=getOrderItemQtyList.get(i).getItemId();
		//System.out.println("a============"+a);
		
		postProductionDetail.setItemId(Integer.parseInt(a));
		
		postProductionDetail.setProductionQty(getOrderItemQtyList.get(i).getQty());
		postProductionDetailList.add(postProductionDetail);
	}
	for(int i=0;i<getRegSpCakeOrderQtyList.size();i++)
	{
		postProductionDetail=new PostProductionDetail();
		
	postProductionDetail.setItemId(getRegSpCakeOrderQtyList.get(i).getItemId());
	postProductionDetail.setProductionQty(getRegSpCakeOrderQtyList.get(i).getQty());
	postProductionDetailList.add(postProductionDetail);
}
			
	postProductionHeader.setPostProductionDetail(postProductionDetailList);
	try {
	
		Info info = restTemplate.postForObject(Constants.url + "postProduction", postProductionHeader,
				Info.class);

		System.out.println("Message :   "+info.getMessage());
		System.out.println("Error  :    "+info.getError());
	}catch (Exception e) {
		System.out.println(e.getMessage());
	}
	//model.addObject("unSelectedCatList", categoryList);
	//model.addObject("productionTimeSlot", timeSlot);
			}
		return "redirect:/showproduction";
	}

	

	
}
