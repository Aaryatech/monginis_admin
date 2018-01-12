package com.ats.adminpanel.controller.news;

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
import com.ats.adminpanel.model.AllSchedulerResponse;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.Route;
import com.ats.adminpanel.model.Scheduler;

@Controller
public class LatestNewsController {
	
	@RequestMapping(value = "/addNews")
	public ModelAndView redirectToAddNews(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("latestNews/addNew");
		Constants.mainAct=1;
		Constants.subAct=121;
		return model;
	}
	
	@RequestMapping(value = "/updateNews/{schId}")
	public ModelAndView redirectToUpdateNews(@PathVariable int schId) {
		ModelAndView model = new ModelAndView("latestNews/editNews");
		System.out.println("schId"+schId);
		RestTemplate restTemplate = new RestTemplate();

		
		Scheduler scheduler=restTemplate.getForObject(Constants.url+"getScheduler?schId={schId}",Scheduler.class,schId);
		
		int activeStatus=scheduler.getIsActive();
		String strIsActive=String.valueOf(activeStatus);
		
		model.addObject("isActive",strIsActive);
		model.addObject("scheduler",scheduler);
		
		
		return model;
	}
	
	@RequestMapping(value = "/addLatestNews", method = RequestMethod.POST)

	public String addLatestNews(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("latestNews/addNew");

		RestTemplate rest = new RestTemplate();

		String schDate= request.getParameter("sch_date");
		String schTodate= request.getParameter("sch_to_date");
	
			
		String schOccasionname = request.getParameter("sch_occasion_name");
		String schMessage = request.getParameter("sch_message");
		int isActive = Integer.parseInt(request.getParameter("is_active"));

		//double schFrdttime = Double.parseDouble(request.getParameter("schFrdtTime"));
		//double schTodttime = Double.parseDouble(request.getParameter("schTodtTime"));
		


		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("schDate", schDate);
		map.add("schTodate", schTodate);
		map.add("schOccasionname", schOccasionname);
		map.add("schMessage", schMessage);
		map.add("isActive", isActive);

		map.add("schFrdttime", 00.00);
		map.add("schTodttime", 00.00);
		
		
			Info info = rest.postForObject(Constants.url+"insertScheduler", map, Info.class);
			
			
			if(info.getError())
			{
			   return "redirect:/showAllLatestNews";
			}
			else
			{
				   return "redirect:/showAllLatestNews";

			}
	}

	@RequestMapping(value = "/showAllLatestNews", method=RequestMethod.GET)

	public ModelAndView showAllLatestNews(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("latestNews/allLatestNews");
		RestTemplate restTemplate = new RestTemplate();
		Constants.mainAct=1;
		Constants.subAct=1;
		try {
			
			AllSchedulerResponse allSchedulerResponse=restTemplate
					.getForObject(Constants.url+"showSchedulerList", AllSchedulerResponse.class);
			
			List<Scheduler> schedulerList=new  ArrayList<Scheduler>();
			schedulerList=allSchedulerResponse.getSchedulerList();
			model.addObject("schedulerList",schedulerList);
		}
		catch(Exception e)
		{
			System.out.println("Error in route list display"+e.getMessage());
		}
	
		return model;
	}
	
	
	@RequestMapping(value = "/deleteNews/{schId}",method=RequestMethod.GET)
	public String deleteSpecialCake(@PathVariable int schId) {
		ModelAndView model = new ModelAndView("latestNews/allLatestNews");
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("schId", schId);
		RestTemplate restTemplate=new RestTemplate();
		Info info=restTemplate.postForObject(Constants.url+"deleteScheduler", map,Info.class);
		
	  	if(info.getError())
		{
		   return "redirect:/showAllLatestNews";
		}
		else
		{
			   return "redirect:/showAllLatestNews";

		}
	
	}
	
	@RequestMapping(value = "/updateNews/updateLatestNewsProcess",method=RequestMethod.POST)

	public String redirectToUpdatNewsProcess(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("latestNews/allLatestNews");
		
		try {
		
		RestTemplate restTemplate = new RestTemplate();
		String schDate= request.getParameter("sch_date");
		String schTodate= request.getParameter("sch_to_date");
	
			
		String schOccasionname = request.getParameter("sch_occasion_name");
		String schMessage = request.getParameter("sch_message");
		int isActive = Integer.parseInt(request.getParameter("is_active"));
		int schId = Integer.parseInt(request.getParameter("schId"));

		/*double schFrdttime = Double.parseDouble(request.getParameter("schFrdtTime"));
		double schTodttime = Double.parseDouble(request.getParameter("schTodtTime"));
		*/


		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("schDate", schDate);
		System.out.println("sch date is "+schDate);
		
		map.add("schToDate", schTodate);
		System.out.println("sch to date is "+schTodate);
		
		map.add("schOccasionName", schOccasionname);
		System.out.println("occasion name "+schOccasionname);
		
		map.add("schMessage", schMessage);
		System.out.println("sch message "+schMessage);
		
		map.add("isActive", isActive);
		System.out.println("is active "+isActive);

		map.add("schFrdtTime", 00.00);
		map.add("schTodtTime", 00.00);
		map.add("id", schId);
		
		
			String info = restTemplate.postForObject(Constants.url+"updateScheduler", map, String.class);
			
		}catch (Exception e) {
			System.out.println("Latest news controller exce 184 "+e.getMessage());
		}
		
			   return "redirect:/showAllLatestNews";

	}

	
}
