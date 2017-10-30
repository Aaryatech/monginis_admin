package com.ats.adminpanel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.Login;
import com.ats.adminpanel.model.OrderCount;
import com.ats.adminpanel.model.OrderCountsResponse;
import com.ats.adminpanel.model.login.User;
import com.ats.adminpanel.model.login.UserResponse;
import com.ats.adminpanel.session.UserSession;

/**
 * Handles requests for the application home page.
 */

@Controller
public class HomeController {

	// github testing file changes

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("login");
		logger.info("/ request mapping.");

		return model;

	}

	@RequestMapping(value = "/homenew", method = RequestMethod.GET)
	public ModelAndView displayHome(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("homenew");
		logger.info("/homenew request mapping.");

		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView redirectToLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("login");
		Login login = new Login();
		Constants.mainAct=0;
		Constants.subAct=0;
		model.addObject("login", login);
		return model;

	}

	@RequestMapping(value = "/homeold", method = RequestMethod.GET)
	public ModelAndView redirectToOldHome(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("homeold");

		return model;

	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("index request mapping.");
		return "index";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		logger.info(" /home request mapping.");

		ModelAndView mav = new ModelAndView("home");
		try {
			
		
		RestTemplate restTemplate = new RestTemplate();

		
		OrderCountsResponse orderCountList=restTemplate.getForObject(
				Constants.url+"/showOrderCounts",
				OrderCountsResponse.class);
		List<OrderCount> orderCounts=new ArrayList<OrderCount>();
		orderCounts=orderCountList.getOrderCount();
		mav.addObject("orderCounts",orderCounts);
		}
		catch(Exception e)
		{
			System.out.println("HomeController Home Request Page Exception:  " + e.getMessage());
		}
		
		
		
		return mav;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		System.out.println("User Logout");

		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/AddNewFranchisee", method = RequestMethod.GET)
	public ModelAndView addNewFranchisee(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Home request mapping.");

		ModelAndView mav = new ModelAndView("franchisee/addnewfranchisee");
		return mav;
	}
	
	
	
	

	@RequestMapping("/loginProcess")
	public ModelAndView helloWorld(HttpServletRequest request, HttpServletResponse res) throws IOException {

		String name = request.getParameter("username");
		String password = request.getParameter("userpassword");

		ModelAndView mav = new ModelAndView("login");

		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		HttpSession session = request.getSession();

		try {
			System.out.println("Login Process " + name);

			if (name.equalsIgnoreCase("") || password.equalsIgnoreCase("") || name == null || password == null) {

				mav = new ModelAndView("login");
			} else {

				RestTemplate restTemplate = new RestTemplate();

				UserResponse userObj = restTemplate.getForObject(
						Constants.url+"/login?username=" + name + "&password=" + password,
						UserResponse.class);

				System.out.println("JSON Response Objet " + userObj.toString());
				String loginResponseMessage="";

				
				if (userObj.getErrorMessage().isError()==false) {
					
					session.setAttribute("userName", name);
					
					loginResponseMessage="Login Successful";
					mav.addObject("loginResponseMessage",loginResponseMessage);
					
					
					
					mav = new ModelAndView("home");
					
					OrderCountsResponse orderCountList=restTemplate.getForObject(
							Constants.url+"/showOrderCounts",
							OrderCountsResponse.class);
					List<OrderCount> orderCounts=new ArrayList<OrderCount>();
					orderCounts=orderCountList.getOrderCount();
					mav.addObject("orderCounts",orderCounts);
					System.out.println("menu list =="+orderCounts.toString());
					System.out.println("order count tile -"+orderCounts.get(0).getMenuTitle());
					System.out.println("order  count -"+orderCounts.get(0).getTotal());
					
					

				} else {

					
					mav = new ModelAndView("login");
					
					loginResponseMessage="Invalid Login Credentials";
					mav.addObject("loginResponseMessage",loginResponseMessage);
					
					System.out.println("Invalid login credentials");

				}

			}
		} catch (Exception e) {
			System.out.println("HomeController Login API Excep:  " + e.getMessage());
		}

		return mav;

	}

	@ExceptionHandler(LoginFailException.class)
	public String redirectToLogin() {
		System.out.println("HomeController Login Fail Excep:");

		return "login";
	}

}
