package com.iot.mgt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


//로그인처리를 담당하는 인터셉터
public class AuthLoginInterceptor implements HandlerInterceptor{

		private static final Logger logger = LoggerFactory.getLogger(AuthLoginInterceptor.class);

		@Override
		public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
	          throws Exception {
	      
			  System.out.println("preHandle1");
		      HttpSession session = req.getSession();
		      Object obj = session.getAttribute("vo");
		
		      logger.debug("인터셉터 가동중(전단계)" + obj);
				
		      String loginUrl = req.getContextPath() + "/member/login";
				
		      if ( obj == null ){
		    	  res.sendRedirect(loginUrl);	// login.do
		    	  return false;
		      }
		
		      return true;
	  }
	
	  @Override
	  public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler,
	          ModelAndView modelAndView) throws Exception {
	      
	  		logger.debug("postHandle1");
	      
	  }
	
	  @Override
	  public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex)
	          throws Exception {
	      
	  		logger.debug("afterCompletion1");
	      
	  }    
	
}