package com.iot.mgt.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iot.mgt.service.MemberService;
import com.iot.mgt.util.SHA256;
import com.iot.mgt.vo.MemberVO;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Inject
	private MemberService memberService;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/login");
		
		return mav;
	}
	
	@RequestMapping(value = "loginCheck", method = RequestMethod.POST)
	public ModelAndView loginCheck(Locale locale, 
															  Model model,
															  MemberVO vo,
															  String csrf_token,
															  HttpServletRequest req,
															  HttpServletResponse res) {
		
		logger.info("Welcome home! The client locale is {}.", locale);
		logger.info(vo.getEmail() + "이메일 주소");
		
		int result = 0;
		MemberVO sourceVO = null;
		String enc_passwd = "";
		String ori_passwd = "";
		
		String msg = "";
		
		ModelAndView mav = new ModelAndView();
		
		// 토큰 일치 여부(CSRF 문제)
		if ( csrf_token.equals("abcd") ) {
			result = 1;
		}
		
		// 인증 확인
		if ( result == 1 ) {

			SHA256 sha256 = SHA256.getInstance();
			
			// SHA256 암호 적용
			try {
				enc_passwd = sha256.encrypt(vo.getPasswd() );
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
			// 회원 데이터 가져오기
			sourceVO = memberService.selectMember(vo);
			
			if ( sourceVO != null ) {
				result = 1;

				// 비밀번호 일치확인
				ori_passwd = sourceVO.getPasswd();
				
			}else {
				result = 2;
			}
			
		}
		
		// 비밀번호 일치 및 비교하기
		if ( result == 1 ) {

			if ( ori_passwd.equals(enc_passwd)) {
				result = 1;
			}else {
				result = 3;
			}
			
		}
		
		// 세션 생성
		if ( result == 1 ) {
			HttpSession session = req.getSession();
			session.setAttribute("vo", sourceVO);
		}
		
		switch ( result ) {
		
			case 1:
				msg = "success";
				mav.setViewName("redirect:/uni/core/cf3/m1");
				break;
			
			case 2:
				msg = "이메일 주소를 확인하세요.";
				mav.addObject("message", msg);
				mav.addObject("locationUrl", "login");
				mav.setViewName("common/message");
				break;
		
			case 3:
				msg = "비밀번호를 확인하세요.";
				mav.addObject("message", msg);
				mav.addObject("locationUrl", "login");
				mav.setViewName("common/message");
				break;
				
			default:
				msg = "예기치 못한 문제입니다.";
				mav.addObject("message", msg);
				mav.addObject("locationUrl", "login");
				mav.setViewName("common/message");
				break;
				
		}
		
		return mav;
	}
	
	@RequestMapping(value = "emailCheck", method = RequestMethod.POST)
	public ModelAndView emailCheck(Locale locale, 
												Model model,
												MemberVO memberVo,
												String token) {
		
		logger.info("Welcome home! The client locale is {}.", locale);

		boolean status = true;
		MemberVO vo = null;
		
		ModelAndView mav = new ModelAndView();
		
		// 토큰 확인
		if(token.equals("abcd")) {
			vo = memberService.selectMember(memberVo);
		}
		
		if (vo != null) {
			status = false;
		}
		
		if (memberVo.getEmail().length() == 0) {
			status = false;
		}
		
		//System.out.println("이메일 길이:" + memberVo.getEmail().length());
		
		mav.addObject("message", status);
		mav.setViewName("common/ajaxMessage");
		
		return mav;
	
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public ModelAndView logout(Locale locale, 
													Model model,
													HttpServletRequest req,
													HttpServletResponse res) {
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = req.getSession(false);
		
		if (session != null && session.getAttribute("vo") != null ) {
			session.invalidate();
		}

		mav.setViewName("redirect:/");
		
		return mav;
	}
	
}
