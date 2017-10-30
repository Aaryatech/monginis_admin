package com.ats.adminpanel;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CheckUserInterceptor extends HandlerInterceptorAdapter {

   
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws IOException, LoginFailException {
    	 HttpSession session = request.getSession(false);

         String path = request.getRequestURI().substring(request.getContextPath().length());

         if(request.getServletPath().equals("/") || request.getServletPath().equals("/loginProcess") ||request.getServletPath().equals("/logout") ||request.getServletPath().equals("/login")){ //||request.getServletPath().equals("/logout")
        	 System.out.println("Login request");
             return true;
         }
         else 
         if(session == null || session.getAttribute("userName") == null || session.getAttribute("userName").equals("")) {
             request.setAttribute("emassage", "login failed");                
             throw new LoginFailException("login failed");                
         }else{                
             return true;
         }    

}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
		
		super.postHandle(request, response, handler, modelAndView);
	}
    
    
}