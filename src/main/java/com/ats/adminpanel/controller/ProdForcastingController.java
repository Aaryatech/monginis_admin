package com.ats.adminpanel.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.AllFrIdNameList;
import com.ats.adminpanel.model.franchisee.AllMenuResponse;
import com.ats.adminpanel.model.franchisee.Menu;
import com.ats.adminpanel.model.production.GetOrderItemQty;
import com.ats.adminpanel.model.production.GetRegSpCakeOrderQty;

@Controller
public class ProdForcastingController {

	
	List<Menu> menuList ;//= new ArrayList<Menu>();
	
	@RequestMapping(value = "/showItems", method = RequestMethod.GET)
	public ModelAndView showProdForcasting(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("production/prodForcasting");
	Constants.mainAct = 8;
		Constants.subAct = 82;

		

		RestTemplate restTemplate = new RestTemplate();

		AllMenuResponse allMenuResponse = restTemplate.getForObject(Constants.url + "getAllMenu",
				AllMenuResponse.class);

		menuList = allMenuResponse.getMenuConfigurationPage();
		
		model.addObject("menuList", menuList);

		return model;
	}
	
	
	@RequestMapping(value = "/getProductionOrder", method = RequestMethod.GET)
	public @ResponseBody List<GetOrderItemQty> generateOrderList(HttpServletRequest request,
		HttpServletResponse response) {
		
		List<GetOrderItemQty> getOrderItemQtyList=new ArrayList<>();
		List<GetOrderItemQty> QtyList=new ArrayList<>();
		List<GetRegSpCakeOrderQty> getRegSpCakeOrderQtyList=new ArrayList<>();
		
		String orderDate=request.getParameter("orderDate");
		String selectedMenuList=request.getParameter("selectedMenu_list");

		selectedMenuList = selectedMenuList.substring(1, selectedMenuList.length() - 1);
		selectedMenuList = selectedMenuList.replaceAll("\"", "");
		List<String> menuList= new ArrayList<>();
		List<String> regSpCakeList= new ArrayList<>();
		MultiValueMap<String, Object> map=new LinkedMultiValueMap<String, Object>();
		MultiValueMap<String, Object> map2=new LinkedMultiValueMap<String, Object>();
		
		List<String> selectedList=new ArrayList<>();
		selectedList=Arrays.asList(selectedMenuList.split(","));
		for(int i=0;i<selectedList.size();i++)
		{
			if(selectedList.get(i)=="42" || selectedList.get(i)=="40"||selectedList.get(i)=="41"|| selectedList.get(i)=="43" || selectedList.get(i)=="46")
			{
				regSpCakeList.add(selectedList.get(i));
			}
			else {
				menuList.add(selectedList.get(i));
			}
		}
		RestTemplate rest=new RestTemplate();
		if(menuList!=null)
		{
		map.add("orderDate", orderDate);
		map.add("menuId", menuList);
		try
			{
			getOrderItemQtyList=rest.postForObject(Constants.url + "getOrderAllItemQty", map, List.class);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}
		System.out.println("List of Orders : "+ getOrderItemQtyList.toString());
		if(regSpCakeList!=null)
		{
		map2.add("orderDate", orderDate);
		map2.add("menuId", regSpCakeList);
		try
			{
			getRegSpCakeOrderQtyList=rest.postForObject(Constants.url + "getOrderQtyRegSpCakeAllItems", map2, List.class);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}
		System.out.println("List of Orders : "+ getRegSpCakeOrderQtyList.toString());
		GetOrderItemQty addList;
		for(int i=0;i<getRegSpCakeOrderQtyList.size();i++)
		{
			addList=new GetOrderItemQty();
			addList.setItemGrp1(getRegSpCakeOrderQtyList.get(i).getItemGrp1());
			addList.setItemId(String.valueOf(getRegSpCakeOrderQtyList.get(i).getItemId()));
			addList.setItemName(getRegSpCakeOrderQtyList.get(i).getItemName());
			addList.setQty(getRegSpCakeOrderQtyList.get(i).getQty());
			addList.setOrderId(0);
			
			QtyList.add(addList);
		}
		for(int i=0;i<getOrderItemQtyList.size();i++)
		{
			addList=new GetOrderItemQty();
			addList.setItemGrp1(getOrderItemQtyList.get(i).getItemGrp1());
			addList.setItemId(getOrderItemQtyList.get(i).getItemId());
			addList.setItemName(getOrderItemQtyList.get(i).getItemName());
			addList.setQty(getOrderItemQtyList.get(i).getQty());
			addList.setOrderId(0);
			
			QtyList.add(addList);
		}
		System.out.println("List of Orders : "+ QtyList.toString());
		
		return QtyList;
		
	}
}
