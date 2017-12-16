package com.ats.adminpanel.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ats.adminpanel.commons.Constants;

@Controller
@RequestMapping("/logoutDemoo")
public class LogoutController {

  @RequestMapping(method=RequestMethod.GET)
  public String logout(HttpSession session) {
		Constants.mainAct=0;
		Constants.subAct=0;
	  System.out.println("Logout Controller User Logout");
    session.invalidate();
    return "redirect:/";
  }
}