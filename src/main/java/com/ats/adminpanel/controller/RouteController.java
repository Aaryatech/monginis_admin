package com.ats.adminpanel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.AllRoutesListResponse;
import com.ats.adminpanel.model.SpecialCake;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.Route;
import com.ats.adminpanel.model.SpCakeResponse;
@Controller
public class RouteController {
	@RequestMapping(value = "/addRouteProcess", method=RequestMethod.POST)

	public String addRouteProcess(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("masters/route");
		RestTemplate rest = new RestTemplate();
		
		String routeName = request.getParameter("route_name");
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("routeName", routeName);
		String routeResponse = rest.postForObject(Constants.url+"insertRoute", map, String.class);
		model=new ModelAndView("masters/route");
		return "redirect:/addroute";
	}

	@RequestMapping(value = "/addroute", method=RequestMethod.GET)
	public ModelAndView addroute(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("masters/route");
		System.out.println("route disp");
		
		Constants.mainAct=1;
		Constants.subAct=8;
		RestTemplate restTemplate = new RestTemplate();
		try {
			
			AllRoutesListResponse allRouteListResponse=restTemplate
					.getForObject(Constants.url+"showRouteList", AllRoutesListResponse.class);
			
			List<Route> routeList=new  ArrayList<Route>();
			routeList=allRouteListResponse.getRoute();
			model.addObject("routeList",routeList);
		}
		catch(Exception e)
		{
			System.out.println("Error in route list display"+e.getMessage());
		}
	
		return model;
	}
	
	@RequestMapping(value = "/deleteRoute/{routeId}", method=RequestMethod.GET)

	public String deleteRoute(@PathVariable int routeId) {
		ModelAndView model = new ModelAndView("masters/route");
		RestTemplate rest = new RestTemplate();

			
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("routeId", routeId);
		Info info = rest.postForObject(Constants.url+"deleteRoute", map, Info.class);
		if(info.getError())
		{
		   return "redirect:/addroute";
		}
		else
		{
			   return "redirect:/addroute";

		}
	}
	
	
	@RequestMapping(value = "/updateRoute/{routeId}")

	public ModelAndView updateRoute(@PathVariable int routeId, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("masters/editRoute");
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("routeId", routeId);
		RestTemplate restTemplate = new RestTemplate();
		try {
		Route route=restTemplate
				.getForObject(""+Constants.url+"getRoute?routeId={routeId}",Route.class,routeId);
	
		AllRoutesListResponse allRouteListResponse=restTemplate
				.getForObject(Constants.url+"showRouteList", AllRoutesListResponse.class);
		
		List<Route> routeList=new  ArrayList<Route>();
		routeList=allRouteListResponse.getRoute();
		model.addObject("routeList",routeList);
		model.addObject("route",route);
		}
		catch(Exception e)
		{
			System.out.println("Exception In updateRoute:"+e.getMessage());
		}
		
 return model;
		
	}
	
	@RequestMapping(value = "/updateRoute/editRouteProcess",method=RequestMethod.POST)

	public String editRouteProcess(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("masters/route");
		
		
		
		RestTemplate restTemplate = new RestTemplate();
		
		String routeName = request.getParameter("route_name");
		int routeId = Integer.parseInt(request.getParameter("route_id"));

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		
		map.add("id", routeId);
		map.add("routeName", routeName);
		
		String routeResponse=restTemplate
				.postForObject(""+Constants.url+"updateRoute",map,String.class);
		
		
			   return "redirect:/addroute";

	}

}
