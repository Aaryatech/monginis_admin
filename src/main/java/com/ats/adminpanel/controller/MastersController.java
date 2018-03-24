package com.ats.adminpanel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.AllEventListResponse;
import com.ats.adminpanel.model.Route;
import com.ats.adminpanel.model.events.Event;
import com.ats.adminpanel.model.flavours.AllFlavoursListResponse;
import com.ats.adminpanel.model.flavours.Flavour;
import com.ats.adminpanel.model.item.AllItemsListResponse;
import com.ats.adminpanel.model.item.Item;
import com.ats.adminpanel.model.masters.AllRatesResponse;
import com.ats.adminpanel.model.masters.AllspMessageResponse;
import com.ats.adminpanel.model.masters.Rate;
import com.ats.adminpanel.model.masters.SpMessage;
import com.ats.adminpanel.model.modules.ErrorMessage;



@Controller
public class MastersController {
	
	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

	
	@RequestMapping(value="/addFlavour")
	  public String addFlavour(HttpServletRequest request, HttpServletResponse response) {
	
		
		
		String spfName=request.getParameter("spf_name");
		double spfAdonRate=Double.parseDouble(request.getParameter("spf_adon_rate"));
		int spType=Integer.parseInt(request.getParameter("sp_type"));
		
		  ModelAndView mav = new ModelAndView("masters/flavours");
		
			RestTemplate rest = new RestTemplate();
	        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	        map.add("spfName",spfName);
	        map.add("spfAdonRate",spfAdonRate);
	        map.add("spType",spType);
		   ErrorMessage errorResponse = rest.postForObject(""+Constants.url+"insertFlavour", map,ErrorMessage.class);
           System.out.println(errorResponse.toString());

         /*  if(errorResponse.getError()) {
               mav = new ModelAndView("masters/flavours");

           }else {
               mav = new ModelAndView("masters/flavours");

           }*/

	return "redirect:/flavoursList";

	  }
	
	

	@RequestMapping(value="/addMessage")
	  public String addMessage(HttpServletRequest request, HttpServletResponse response) {
		 ModelAndView mav = new ModelAndView("masters/messages");
		 
		
			
			String message=request.getParameter("message");
			
			
				RestTemplate rest = new RestTemplate();
		        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		        map.add("spMsgText",message);
		      
			   ErrorMessage errorResponse = rest.postForObject(""+Constants.url+"insertspMessage", map,ErrorMessage.class);


		return "redirect:/showSpMessages";
	  }
	
	@RequestMapping(value="/addRate")
	  public String addRate(HttpServletRequest request, HttpServletResponse response) {
		  System.out.println("Add Rate Request");
		  
			
		  ModelAndView mav = new ModelAndView("masters/rates");
			
			String sprName=request.getParameter("spr_name");
			double sprRate=Double.parseDouble(request.getParameter("spr_rate"));
			double sprAdOnRate=Double.parseDouble(request.getParameter("spr_adon_rate"));
			
			
				RestTemplate rest = new RestTemplate();
		        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		        map.add("sprName",sprName);
		        map.add("sprRate",sprRate);
		        map.add("sprAddOnRate",sprAdOnRate);
			   ErrorMessage errorResponse = rest.postForObject(Constants.url+"insertRate", map,ErrorMessage.class);
	           System.out.println(errorResponse.toString());


		return "redirect:/showRates";
	  }
	@RequestMapping(value="/showRates")
	  public ModelAndView showAllRates(HttpServletRequest request, HttpServletResponse response) {
		  
		    ModelAndView mav = new ModelAndView("masters/rates");

		    Constants.mainAct=1;
			Constants.subAct=12;
			
			RestTemplate restTemplate = new RestTemplate();
			AllRatesResponse allRatesListResponse=restTemplate.getForObject(
					Constants.url +"getAllRates",
					AllRatesResponse.class);
			
			 
			List<Rate> ratesList= new ArrayList<Rate>();
			ratesList=allRatesListResponse.getRates();
			mav.addObject("ratesList",ratesList);
		
			return mav;
			
	  }
	//update rate
	@RequestMapping(value = "/updateRate/{sprId}")

	public ModelAndView redirectToUpdateRate(@PathVariable int sprId, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("masters/editrate");
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("sprId", sprId);
		RestTemplate restTemplate = new RestTemplate();
		
		Rate rate=restTemplate
				.getForObject(Constants.url+"getRate?sprId={sprId}",Rate.class,sprId);
		System.out.println("spr add on rate="+rate.getSprAddOnRate());
		
		model.addObject("rate",rate);
		
		
	return model;
		
	}

	@RequestMapping(value = "/updateRate/updateRateProcess",method=RequestMethod.POST)

	public String redirectToUpdateRateProcess(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("masters/rates");
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		try {
		RestTemplate restTemplate = new RestTemplate();
		
		String sprName = request.getParameter("spr_name");
		int sprId = Integer.parseInt(request.getParameter("spr_id"));
		int sprRate = Integer.parseInt(request.getParameter("spr_rate"));
		int sprAdOnRate=Integer.parseInt(request.getParameter("spr_adon_rate"));
		
		map.add("sprId",sprId);
		map.add("sprRate",sprRate);
		map.add("sprAddOnRate",sprAdOnRate);
		map.add("sprName",sprName);
		
		String rateResponse=restTemplate
				.postForObject(""+Constants.url+"updateRate",map,String.class);
		
		AllEventListResponse allEventsListResponse=restTemplate.getForObject(
				Constants.url+"showEventList",
				AllEventListResponse.class);
		
		
		}
		catch (Exception e) {
			
		}
		return "redirect:/showRates";

	}

	
	@RequestMapping(value="/showSpMessages")
	  public ModelAndView showAllMessages(HttpServletRequest request, HttpServletResponse response) {
		  
		    ModelAndView mav = new ModelAndView("masters/messages");
		    Constants.mainAct=1;
			Constants.subAct=9;

			RestTemplate restTemplate = new RestTemplate();
			AllspMessageResponse allspMessageList=restTemplate.getForObject(
					Constants.url+"getAllSpMessage",
					AllspMessageResponse.class);
			
			 
			List<SpMessage> spMessageList= new ArrayList<SpMessage>();
			spMessageList=allspMessageList.getSpMessage();
			mav.addObject("spMessageList",spMessageList);
		
			return mav;
			
	  }
	@RequestMapping(value="/addAndShowEvents")
	  public ModelAndView showAllEvents(HttpServletRequest request, HttpServletResponse response) {
		  System.out.println("List Event Request");
		  ModelAndView mav = new ModelAndView("masters/events");
		  Constants.mainAct=1;
			Constants.subAct=11;

			RestTemplate restTemplate = new RestTemplate();
			AllEventListResponse allEventsListResponse=restTemplate.getForObject(
					Constants.url+"showEventList",
					AllEventListResponse.class);
			
			 
			List<com.ats.adminpanel.model.Event> eventsList= new ArrayList<com.ats.adminpanel.model.Event>();
			eventsList=allEventsListResponse.getEvent();
			mav.addObject("eventsList",eventsList);
		
			return mav;
			
	  }
	
@RequestMapping(value="/flavoursList")
	  public ModelAndView showAllFlavours(HttpServletRequest request, HttpServletResponse response) {
		  System.out.println("List Flavour Request");
		  ModelAndView mav = new ModelAndView("masters/flavours");
		  Constants.mainAct=1;
			Constants.subAct=10;
			
			RestTemplate restTemplate = new RestTemplate();
			AllFlavoursListResponse allFlavoursListResponse=restTemplate.getForObject(
					Constants.url+"showFlavourList",
					AllFlavoursListResponse.class);
			
			 
			List<Flavour> flavoursList= new ArrayList<Flavour>();
			List<Integer> spTypeArray= new ArrayList<Integer>();
			flavoursList=allFlavoursListResponse.getFlavour();
			System.out.println("LIst of flavours"+flavoursList.toString());
			mav.addObject("flavoursList",flavoursList);
			String spTypeName="";
			for(int i=0;i<flavoursList.size();i++) {
				int sptype=flavoursList.get(i).getSpType();
				
				
				switch(sptype) {
				case 2:
					spTypeName="FC";
					break;
				case 1:
					spTypeName="Chocolate";
					break;
					default:
						spTypeName="";
						break;
				
				}
				spTypeArray.add(sptype);
				
				
				
			}
			
			mav.addObject("spTypeName",spTypeName);
			mav.addObject("spTypeArray",spTypeArray);
		
			return mav;
			
	  }
@RequestMapping(value="/addEvent",method=RequestMethod.POST)
public String addEvent(HttpServletRequest request, HttpServletResponse response) {

	String speName=request.getParameter("spe_name");
	
	  ModelAndView mav = new ModelAndView("masters/events");
	  
	
		RestTemplate rest = new RestTemplate();
      MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
      map.add("speName",speName);
   
	   ErrorMessage errorResponse = rest.postForObject(Constants.url+"insertEvent", map,ErrorMessage.class);
     System.out.println(errorResponse.toString());

   /*  if(errorResponse.getError()) {
         mav = new ModelAndView("masters/flavours");

     }else {
         mav = new ModelAndView("masters/flavours");

     }*/

return "redirect:/addAndShowEvents";

}

/*//from mahesh
@RequestMapping(value="/deleteFlavour/{spfId}",method=RequestMethod.GET)
public String deleteFlavours(@PathVariable int spfId) {

	//String id=request.getParameter("id");
	
	  ModelAndView mav = new ModelAndView("masters/flavours");
	
		RestTemplate rest = new RestTemplate();
      MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
      map.add("spfId",spfId);
   
	   ErrorMessage errorResponse = rest.postForObject("http://localhost:8089/deleteFlavour", map,ErrorMessage.class);
     System.out.println(errorResponse.toString());

  if(errorResponse.getError()) {
	  return "redirect:/flavoursList";

     }else {
    	 return "redirect:/flavoursList";

     }

}
*/

// event update
@RequestMapping(value = "/updateEvent/{speId}")

public ModelAndView redirectToUpdateEvent(@PathVariable int speId, HttpServletRequest request, HttpServletResponse response) {
	ModelAndView model = new ModelAndView("masters/editEvent");
	
	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	map.add("speId", speId);
	RestTemplate restTemplate = new RestTemplate();
	
	Event event=restTemplate
			.getForObject(Constants.url+"getEvent?speId={speId}",Event.class,speId);
	
	model.addObject("event",event);
	
	
return model;
	
}

@RequestMapping(value = "/updateEvent/updateEventProcess",method=RequestMethod.POST)

public ModelAndView fjdfhfj (HttpServletRequest request, HttpServletResponse response) {
	ModelAndView model = new ModelAndView("masters/events");
	
	
	try {
	RestTemplate restTemplate = new RestTemplate();
	
	String speName = request.getParameter("event_name");
	int speId = Integer.parseInt(request.getParameter("spe_id"));

	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	
	map.add("id", speId);
	map.add("speName", speName);
	
	String eventResponse=restTemplate
			.postForObject(""+Constants.url+"updateEvent",map,String.class);
	
	AllEventListResponse allEventsListResponse=restTemplate.getForObject(
			Constants.url+"showEventList",
			AllEventListResponse.class);
	
	 
	List<com.ats.adminpanel.model.Event> eventsList= new ArrayList<com.ats.adminpanel.model.Event>();
	eventsList=allEventsListResponse.getEvent();
	model.addObject("eventsList",eventsList);
	}
	catch (Exception e) {
		
	}
		   return model;

}

// end of event update
@RequestMapping(value="/deleteFlavour/{spfId}",method=RequestMethod.GET)
public String deleteFlavour(@PathVariable int spfId) {

	//String id=request.getParameter("id");
	
	  ModelAndView mav = new ModelAndView("masters/flavours");
	
		RestTemplate restTemplate = new RestTemplate();
      MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
      map.add("spfId",spfId);
   
	   ErrorMessage errorResponse = restTemplate.postForObject(Constants.url+"deleteFlavour", map,ErrorMessage.class);
     System.out.println(errorResponse.toString());

  if(errorResponse.getError()) {
	  return "redirect:/flavoursList";
	 
     }else {
    	 return "redirect:/flavoursList";

     }



}
//flavour update 


@RequestMapping(value = "/updateFlavour/{spfId}", method=RequestMethod.GET)

public ModelAndView redirectToUpdateFlavour(@PathVariable int spfId, HttpServletRequest request, HttpServletResponse response) {
	ModelAndView model = new ModelAndView("masters/editflavours");
	try {
	
	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	
	RestTemplate restTemplate = new RestTemplate();
	
	Flavour flavour=restTemplate
			.getForObject(Constants.url+"getFlavour?spfId={spfId}",Flavour.class,spfId);
	System.out.println("flav are"+flavour.getSpfName());
	model.addObject("flavour",flavour);
	
	int spType=flavour.getSpType();
	System.out.println("sp type before edit =="+spType);
	String strSpType=String.valueOf(spType);
	model.addObject("strSpType",strSpType);
	//model.addObject("spType", spType);
	String strSpTypeName="";
	switch(spType) {
	case 0:
		strSpTypeName="FC";
		break;
	case 1:
		strSpTypeName="Chocolate";
		break;
	default:
		strSpTypeName="";
		break;
		
	}
	
	model.addObject("strSpTypeName",strSpTypeName);
	
	}
	catch(Exception e) {
		System.out.println("error in getting flavour"+e.getMessage());
		e.printStackTrace();
	}
	
	
return model;
	
}

@RequestMapping(value = "/updateFlavour/updateFlavourProcess",method=RequestMethod.POST)

public String updateFlavour(HttpServletRequest request, HttpServletResponse response) {
	ModelAndView model = new ModelAndView("masters/flavours");
	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	
	int speId=Integer.parseInt(request.getParameter("spe_id"));
	int spType=Integer.parseInt(request.getParameter("sp_type"));
	String spfName=request.getParameter("spf_name");
	double spfAdOnRate=Double.parseDouble(request.getParameter("spf_adon_rate"));
	System.out.println("sp type after edit=="+spType);
	
	RestTemplate restTemplate = new RestTemplate();
	map.add("id", speId);
	map.add("spType", spType);
	map.add("spfName", spfName);
	map.add("spfAdOnRate", spfAdOnRate);
	
	String flavourResponse=restTemplate
			.postForObject(""+Constants.url+"updateFlavour",map,String.class);
	/*
	AllFlavoursListResponse allFlavoursListResponse=restTemplate.getForObject(
			Constants.url+"/showFlavourList",
			AllFlavoursListResponse.class);
	model.addObject(allFlavoursListResponse);
	*/
	 
	/*List<com.ats.adminpanel.model.Event> eventsList= new ArrayList<com.ats.adminpanel.model.Event>();
	eventsList=allEventsListResponse.getEvent();
	model.addObject("eventsList",eventsList);
*/
	 return "redirect:/flavoursList";
	//return model;

}


@RequestMapping(value="/deleteEvent/{speId}",method=RequestMethod.GET)
public String deleteEvent(@PathVariable int speId) {

	//String id=request.getParameter("id");
	
	  ModelAndView mav = new ModelAndView("masters/events");
	
		RestTemplate rest = new RestTemplate();
      MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
      map.add("id",speId);
   
	   ErrorMessage errorResponse = rest.postForObject(Constants.url+"deleteEvent", map,ErrorMessage.class);
     System.out.println(errorResponse.toString());

  if(errorResponse.getError()) {
	  return "redirect:/addAndShowEvents";

     }else {
    	 return "redirect:/addAndShowEvents";

     }

}
	// from mahesh
@RequestMapping(value="/deleteRate/{sprId}",method=RequestMethod.GET)
public String deleteRates(@PathVariable int sprId) {

	//String id=request.getParameter("id");
	
	  ModelAndView mav = new ModelAndView("masters/rates");
	
		RestTemplate rest = new RestTemplate();
      MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
      map.add("sprId",sprId);
   
	   ErrorMessage errorResponse = rest.postForObject(Constants.url+"deleteRates", map,ErrorMessage.class);
     System.out.println(errorResponse.toString());

  if(errorResponse.getError()) {
	  return "redirect:/showRates";

     }else {
    	 return "redirect:/showRates";

     }



}
// from mahesh
@RequestMapping(value="/deleteSpMessage/{spMsgId}",method=RequestMethod.GET)
public String deleteSpMessage(@PathVariable int spMsgId) {

	//String id=request.getParameter("id");
	
	  ModelAndView mav = new ModelAndView("masters/messages");
	   
	  
	  // resttemplate object
	  
		RestTemplate rest = new RestTemplate();
      MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
      map.add("spMsgId",spMsgId);
   
	   ErrorMessage errorResponse = rest.postForObject(Constants.url+"deleteSpMessage", map,ErrorMessage.class);
     System.out.println(errorResponse.toString());

  if(errorResponse.getError()) {
	  return "redirect:/showSpMessages";

     }else {
    	 return "redirect:/showSpMessages";

     }

}

@RequestMapping(value = "/updateSpMessage/{spMsgId}", method=RequestMethod.GET)

public ModelAndView redirectToUpdateSpMessage(@PathVariable("spMsgId") int msgId, HttpServletRequest request, HttpServletResponse response) {
	ModelAndView model = new ModelAndView("masters/editmessage");
	
	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	//msgId=Integer.parseInt(request.getParameter("spMsgId"));
	map.add("msgId", msgId);
	//String message=request.getParameter("message");
	//map.add("spMsgText", message);
	RestTemplate restTemplate = new RestTemplate();
	/*Route route=restTemplate
			.postForObject(""+Constants.url+"/getRoute", map,Route.class);
	*/
	SpMessage spMessage=restTemplate
			.getForObject(""+Constants.url+"getSpMessage?msgId={msgId}",SpMessage.class,msgId);
	
	model.addObject("spmessage",spMessage);
	
	
return model;
	
}

@RequestMapping(value = "/updateSpMessage/updateSpMessageProcess",method=RequestMethod.POST)

public String redirectToUpdateRouteProcess(HttpServletRequest request, HttpServletResponse response) {
	ModelAndView model = new ModelAndView("masters/messages");
	
	RestTemplate restTemplate = new RestTemplate();
	
	String spMsgText = request.getParameter("message");
	int spMsgId = Integer.parseInt(request.getParameter("spMsgId"));
	
	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	
	map.add("spMsgId", spMsgId);
	map.add("spMsgText", spMsgText);
	
	String routeResponse=restTemplate
			.postForObject(""+Constants.url+"updateSpMessage",map,String.class);
	
		   return "redirect:/showSpMessages";

}

}
