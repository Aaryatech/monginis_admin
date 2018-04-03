package com.ats.adminpanel.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.afe.AfeQuestion;
import com.ats.adminpanel.model.afe.AfeQuestionList;
  
@Controller
@Scope("session")
public class AfeController {

	List<AfeQuestion> queList = new ArrayList<AfeQuestion>();

	//showAfeQue
	@RequestMapping(value = "/showAfeQue", method = RequestMethod.GET)
	public ModelAndView showAfeQue(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			RestTemplate restTemp = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("delStatus", 0);

			AfeQuestionList queResponse = restTemp.postForObject(Constants.url + "getAfeQuestionList", map,
					AfeQuestionList.class);

			queList = new ArrayList<AfeQuestion>();
			queList = queResponse.getAfeQuestion();

			System.out.println("Que List " + queList.toString());

			model = new ModelAndView("afe/showafe");
			model.addObject("qList", queList);

		} catch (Exception e) {

			System.err.println("Exce in showAfeQue" + e.getMessage());
			e.printStackTrace();

		}

		return model;
	}

	// insertAfeQue
	@RequestMapping(value = "/insertAfeQue", method = RequestMethod.POST)
	public String insertAfeQue(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			RestTemplate restTemp = new RestTemplate();
			model = new ModelAndView("afe/showafe");
			java.sql.Date queDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

			String que = request.getParameter("que");
			int queType = Integer.parseInt(request.getParameter("que_type"));
			int quePoint = Integer.parseInt(request.getParameter("que_point"));
			String catName = request.getParameter("que_cat");

			System.out.println(
					"Para received que=" + que + "que type" + queType + "que Point " + quePoint + "cat Name" + catName);
			// postAfeQuestion

			AfeQuestion question = new AfeQuestion();
			question.setCatName(catName);
			question.setDelStatus(0);
			question.setQueDate(new SimpleDateFormat("dd-MM-yyyy").format(queDate));
			question.setQuePoint(quePoint);
			question.setQuestion(que);
			question.setQueType(queType);

			question.setExInt1(0);
			question.setExInt2(0);
			question.setExVar1("ex var1");
			question.setExVar2("Ex var 2");

			AfeQuestion queResponse = restTemp.postForObject(Constants.url + "postAfeQuestion", question,
					AfeQuestion.class);
			System.out.println("Que Response " + queResponse.toString());
		
		} catch (Exception e) {

			System.err.println("Exce in showAfeQue" + e.getMessage());
			e.printStackTrace();

		}

		return "redirect:/showAfeQue";
	}

	// deleteQuestion
	@RequestMapping(value = "/deleteQuestion/{qId}", method = RequestMethod.GET)
	public String deleteQuestion(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("qId") int qId) {
		System.out.println("Q ID received " + qId);
		ModelAndView model = null;
		try {
			RestTemplate restTemp = new RestTemplate();
			model = new ModelAndView("afe/showafe");

			AfeQuestion question = new AfeQuestion();

			for (int i = 0; i < queList.size(); i++) {

				if (queList.get(i).getQueId() == qId) {
					question = queList.get(i);

				}

			}
			question.setDelStatus(1);
			question.setQueId(qId);

			AfeQuestion queResponse = restTemp.postForObject(Constants.url + "postAfeQuestion", question,
					AfeQuestion.class);
			System.out.println("Que Response after delete " + queResponse.toString());

		} catch (Exception e) {

			System.err.println("Exce in deleting Afe Question " + e.getMessage());
			e.printStackTrace();
			
		}
		return "redirect:/showAfeQue";
	}
}
