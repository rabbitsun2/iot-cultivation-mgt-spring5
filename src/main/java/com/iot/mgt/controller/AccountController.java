package com.iot.mgt.controller;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.iot.mgt.util.MemberChecker;
import com.iot.mgt.util.Paging;
import com.iot.mgt.util.SHA256;
import com.iot.mgt.vo.MemberVO;
import com.iot.mgt.vo.PageCriteria;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/uni/core/cf3/acc/*")
public class AccountController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Inject
	private MemberService memberService;
		
	@RequestMapping(value = "m1", method = RequestMethod.GET)
	public ModelAndView m1(Locale locale, 
												Model model,
												HttpServletRequest req,
												HttpServletResponse res) {
		
		logger.info("Welcome home! The client locale is {}.", locale);

		ModelAndView mav = new ModelAndView();
		Date serverDate = new Date();
		String pattern = "yyyy-MM-dd HH:mm:ss";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(serverDate);
		
		HttpSession session = req.getSession();
		
		MemberVO session_vo = (MemberVO) session.getAttribute("vo");
		
		mav.addObject("serverDate", date);
		mav.addObject("sessionVo", session_vo);
		mav.addObject("usrTheme", "uni/core/cf3");
		mav.setViewName("uni/core/cf3/acc/m1");
		
		return mav;
	
	}

	@RequestMapping(value = "m1_ok", method = RequestMethod.POST)
	public ModelAndView m1_ok(Locale locale, 
												Model model,
												String passwd1,
												String passwd2,
												String usrdate,
												String token,
												MemberVO vo) {
		
		String msg = "";
		int result = 0;
		
		logger.info("Welcome home! The client locale is {}.", locale);

		ModelAndView mav = new ModelAndView();
		Date serverDate = new Date();
		
		MemberChecker memberChecker = MemberChecker.getInstance();

		memberChecker.setMemberService(memberService);
		memberChecker.setPasswd(passwd1, passwd2);
		memberChecker.setUsrdate(usrdate);
		memberChecker.setToken(token);
		memberChecker.setMemberVo(vo);
		
		result = memberChecker.runChecker(MemberChecker.MEMBER_JOIN);
		
		// 등록 처리 프로세스
		if ( result == 1 ) {
			
			UUID uuid = UUID.randomUUID();
			SHA256 sha256 = SHA256.getInstance();
			
			try {
				String enc_passwd = sha256.encrypt(vo.getPasswd());
				vo.setPasswd(enc_passwd);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
			vo.setUuid(uuid);
			vo.setRemember(0);
			vo.setLocked(0);
			vo.setRegidate(serverDate);
			
			memberService.insertMember(vo);
			
		}
		
		msg = memberChecker.getMessage(result, MemberChecker.MEMBER_JOIN);
		
		mav.addObject("message", msg);
		mav.setViewName("common/ajaxMessage");

		return mav;
	
	}

	@RequestMapping(value = "m2", method = RequestMethod.GET)
	public ModelAndView m2(Locale locale, 
												Model model,
												PageCriteria cri,
												HttpServletRequest req,
												HttpServletResponse res) {
		
		logger.info("Welcome home! The client locale is {}.", locale);

		Paging paging = new Paging();
		List<MemberVO> memberList = null;
		int total_count = -1;
		
		ModelAndView mav = new ModelAndView();
		Date serverDate = new Date();
		String pattern = "yyyy-MM-dd HH:mm:ss";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(serverDate);

		total_count = memberService.selectAllCount();
		
		int page_no = cri.getPage();	// 현재 페이지
		int page_size = cri.getPerPageNum();	// 단위 페이지
		
		paging.setPageNo(page_no);
		paging.setPageSize(page_size);
		paging.setTotalCount(total_count);
		
		long startnum = paging.getDbStartNum();
		long endnum = paging.getDbEndNum();
		
		memberList = memberService.selectRngMember(startnum, endnum);

		HttpSession session = req.getSession();
		
		MemberVO session_vo = (MemberVO) session.getAttribute("vo");
		
		mav.addObject("serverDate", date);
		mav.addObject("usrTheme", "uni/core/cf3");
		mav.addObject("sessionVo", session_vo);
		mav.addObject("memberPagingUrl", "m2");
		mav.addObject("memberList", memberList);
		mav.addObject("memberPaging", paging);
		mav.setViewName("uni/core/cf3/acc/m2");
		
		return mav;
	
	}
	

	@RequestMapping(value = "m2_remove", method = RequestMethod.POST)
	public ModelAndView m2_remove(Locale locale, 
												Model model, 
												MemberVO vo) {
		
		String msg = "";
		int result = 0;
		
		logger.info("Welcome home! The client locale is {}.", locale);

		ModelAndView mav = new ModelAndView();
		Date serverDate = new Date();
				
		result = memberService.deleteMember(vo);
		msg = "success";
		
		mav.addObject("message", msg);
		mav.setViewName("common/ajaxMessage");

		return mav;
	
	}

	@RequestMapping(value = "m3", method = RequestMethod.GET)
	public ModelAndView m3(Locale locale, 
												Model model,
												MemberVO usrVO,
												HttpServletRequest req,
												HttpServletResponse res) {
		
		boolean status = false;
		
		logger.info("Welcome home! The client locale is {}.", locale);

		ModelAndView mav = new ModelAndView();
		Date serverDate = new Date();
		String pattern = "yyyy-MM-dd HH:mm:ss";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(serverDate);
		
		MemberVO memberInfo = memberService.selectMember(usrVO);

		HttpSession session = req.getSession();
		MemberVO session_vo = (MemberVO) session.getAttribute("vo");
		
		if ( memberInfo != null ) {
			status = true;
		}
		
		// 데이터 존재 여부
		if ( status == true ) {
			mav.addObject("sessionVo", session_vo);
			mav.addObject("memberInfo", memberInfo);
			mav.addObject("serverDate", date);
			mav.addObject("usrTheme", "uni/core/cf3");
			mav.setViewName("uni/core/cf3/acc/m3");
		}
		else {
			mav.addObject("message", "비정상적인 접근입니다.");
			mav.addObject("locationUrl", "m2");
			mav.setViewName("common/message");
		}
		
		return mav;
	
	}
	
	@RequestMapping(value = "m3_ok", method = RequestMethod.POST)
	public ModelAndView m3_ok(Locale locale, 
												Model model,
												String passwd1,
												String passwd2,
												String usrdate,
												String token,
												MemberVO vo) {

		String msg = "";
		int result = 0;
		
		logger.info("Welcome home! The client locale is {}.", locale);

		ModelAndView mav = new ModelAndView();
		Date serverDate = new Date();
		
		MemberChecker memberChecker = MemberChecker.getInstance();

		memberChecker.setMemberService(memberService);
		memberChecker.setPasswd(passwd1, passwd2);
		memberChecker.setUsrdate(usrdate);
		memberChecker.setToken(token);
		memberChecker.setMemberVo(vo);
		
		result = memberChecker.runChecker(MemberChecker.MEMBER_MODIFY);
		
		// 등록 처리 프로세스
		if ( result == 1 ) {
			SHA256 sha256 = SHA256.getInstance();

			try {
				String enc_passwd = sha256.encrypt(vo.getPasswd());
				vo.setPasswd(enc_passwd);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
			vo.setRemember(0);
			vo.setRegidate(serverDate);
			
			//memberService.insertMember(vo);
			memberService.updateMember(vo);
		}
		
		msg = memberChecker.getMessage(result, MemberChecker.MEMBER_MODIFY);
		
		mav.addObject("message", msg);
		mav.setViewName("common/ajaxMessage");
		
		return mav;
	
	}	
	
}