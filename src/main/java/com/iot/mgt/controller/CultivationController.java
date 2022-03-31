package com.iot.mgt.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

import com.iot.mgt.chatbot.ChatBotAnswer;
import com.iot.mgt.service.AiChatBotService;
import com.iot.mgt.service.CoolHeatService;
import com.iot.mgt.service.DHTService;
import com.iot.mgt.service.MovieService;
import com.iot.mgt.service.WaterPumpService;
import com.iot.mgt.util.Paging;
import com.iot.mgt.vo.AiChatBotFilterVO;
import com.iot.mgt.vo.AiChatBotVO;
import com.iot.mgt.vo.CoolHeatMonitorVO;
import com.iot.mgt.vo.MemberVO;
import com.iot.mgt.vo.PageCriteria;
import com.iot.mgt.vo.RemoteMovieVO;
import com.iot.mgt.vo.SensorDHTVO;
import com.iot.mgt.vo.SpecificDHTVO;
import com.iot.mgt.vo.WaterPumpVO;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/uni/core/cf3/*")
public class CultivationController {
	
	private static final Logger logger = LoggerFactory.getLogger(CultivationController.class);
	
	@Inject
	private MovieService movieService;
	
	@Inject
	private DHTService dhtService;
	
	@Inject
	private CoolHeatService coolHeatService;
	
	@Inject
	private WaterPumpService waterPumpService;
	
	@Inject
	private AiChatBotService aiChatBotService;
	
	@RequestMapping(value = "/layout/ct", method = RequestMethod.GET)
	public ModelAndView layoutCt(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		ModelAndView mav = new ModelAndView();
		Date serverDate = new Date();
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String date = simpleDateFormat.format(serverDate);
		
		//model.addAttribute("serverTime", formattedDate );
		
		mav.addObject("serverDate", date);
		mav.addObject("usrTheme", "uni/core/cf3");
		mav.setViewName("uni/core/cf3/include/layout/ct/home");
		
		return mav;
	
	}

	@RequestMapping(value = "/layout/acc", method = RequestMethod.GET)
	public ModelAndView layoutAcc(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		ModelAndView mav = new ModelAndView();
		Date serverDate = new Date();
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String date = simpleDateFormat.format(serverDate);
		
		//model.addAttribute("serverTime", formattedDate );
		
		mav.addObject("serverDate", date);
		mav.addObject("usrTheme", "uni/core/cf3");
		mav.setViewName("uni/core/cf3/include/layout/acc/home");
		
		return mav;
	
	}
	
	@RequestMapping(value = "m1", method = RequestMethod.GET)
	public ModelAndView m1(Locale locale, Model model,
											PageCriteria cri,
											String ct_startdate,
											String ct_enddate,
											HttpServletRequest req,
											HttpServletResponse res) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		int result = 0;
		Paging paging = new Paging();
		List<RemoteMovieVO> movieList = null;
		int total_count = -1;
		
		ModelAndView mav = new ModelAndView();
		Date serverDate = new Date();
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String date = simpleDateFormat.format(serverDate);
		
		// 일자 조회
		if (ct_startdate != null && 
			 ct_enddate != null) {
			result = 1;
		}
		
		if (result == 1) {
			if (ct_startdate.length() != 0 && ct_enddate.length() != 0) {}
			else {  result = 0; }
		}
		else { }

		// Result == 0 (전체 조회)
		// Result == 1 (특정 일자 조회)
		if (result == 1) {
			total_count  = movieService.selectBetweenDateCount(ct_startdate, ct_enddate);
		}else {
			total_count = movieService.selectAllCount();
		}
		
		int page_no = cri.getPage();	// 현재 페이지
		int page_size = cri.getPerPageNum();	// 단위 페이지
		
		paging.setPageNo(page_no);
		paging.setPageSize(page_size);
		paging.setTotalCount(total_count);
		
		long startnum = paging.getDbStartNum();
		long endnum = paging.getDbEndNum();
		
		//model.addAttribute("serverTime", formattedDate );
		
		if( result == 0 ) {
			movieList = movieService.selectMovie(startnum, endnum);
		}
		else if ( result == 1) {
			movieList = movieService.selectBetweenDateMovie(startnum, endnum, 
																								ct_startdate, ct_enddate);
		}
		else {
			movieList = null;
		}

		HttpSession session = req.getSession();
		MemberVO session_vo = (MemberVO) session.getAttribute("vo");

		mav.addObject("sessionVo", session_vo);
		mav.addObject("serverDate", date);
		mav.addObject("usrTheme", "uni/core/cf3");
		mav.addObject("moviePagingUrl", "m1");
		mav.addObject("movieList", movieList);
		mav.addObject("moviePaging", paging);
		mav.setViewName("uni/core/cf3/ct/m1");
		
		return mav;
	
	}
	

	@RequestMapping(value = "m2", method = RequestMethod.GET)
	public ModelAndView m2(Locale locale, 
												Model model,
												PageCriteria cri,
												String ct_startdate,
												String ct_enddate,
												HttpServletRequest req,
												HttpServletResponse res) {
		
		logger.info("Welcome home! The client locale is {}.", locale);

		int result = 0;
		Paging paging = new Paging();
		List<SensorDHTVO> dhtList = null;
		int total_count = -1;
		
		ModelAndView mav = new ModelAndView();
		Date serverDate = new Date();
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String date = simpleDateFormat.format(serverDate);
		

		// 일자 조회
		if (ct_startdate != null && 
			 ct_enddate != null) {
			result = 1;
		}
		
		if (result == 1) {
			if (ct_startdate.length() != 0 && ct_enddate.length() != 0) {}
			else {  result = 0; }
		}
		else { }

		// Result == 0 (전체 조회)
		// Result == 1 (특정 일자 조회)
		if (result == 1) {
			total_count  = dhtService.selectBetweenDateCount(ct_startdate, ct_enddate);
		}else {
			total_count = dhtService.selectAllCount();
		}
		
		//System.out.println("전체 글 갯수:"+ total_count);
		
		int page_no = cri.getPage();	// 현재 페이지
		int page_size = cri.getPerPageNum();	// 단위 페이지
		
		paging.setPageNo(page_no);
		paging.setPageSize(page_size);
		paging.setTotalCount(total_count);
		
		long startnum = paging.getDbStartNum();
		long endnum = paging.getDbEndNum();
		
		//model.addAttribute("serverTime", formattedDate );
		
		if( result == 0 ) {
			dhtList = dhtService.selectDHT(startnum, endnum);
		}
		else if ( result == 1) {
			dhtList = dhtService.selectBetweenDHT(startnum, endnum, 
																			ct_startdate, ct_enddate);
		}
		else {
			dhtList = null;
		}

		HttpSession session = req.getSession();
		MemberVO session_vo = (MemberVO) session.getAttribute("vo");

		mav.addObject("sessionVo", session_vo);
		mav.addObject("serverDate", date);
		mav.addObject("usrTheme", "uni/core/cf3");
		mav.addObject("dhtPagingUrl", "m2");
		mav.addObject("dhtList", dhtList);
		mav.addObject("dhtPaging", paging);
		mav.setViewName("uni/core/cf3/ct/m2");
		
		return mav;
	
	}

	@RequestMapping(value = "m3", method = RequestMethod.GET)
	public ModelAndView m3(Locale locale, 
												Model model,
												String startdate,
												HttpServletRequest req,
												HttpServletResponse res) {
		
		logger.info("Welcome home! The client locale is {}.", locale);

		List<SpecificDHTVO> todayDHT = null;
		int result = 0;
		
		ModelAndView mav = new ModelAndView();
		Date serverDate = new Date();
		String pattern = "yyyy-MM-dd HH:mm:ss";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(serverDate);
		
		if ( startdate != null ) {
			result = 1;
		}
		
		if ( result == 0 ) {
			todayDHT = dhtService.selectSpecificDHT(serverDate);
		}else if (result == 1) {
			Date to = null;
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				to = transFormat.parse(startdate);
				todayDHT = dhtService.selectSpecificDHT(to);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		HttpSession session = req.getSession();
		MemberVO session_vo = (MemberVO) session.getAttribute("vo");

		mav.addObject("sessionVo", session_vo);
		mav.addObject("serverDate", date);
		mav.addObject("todayDHT", todayDHT);
		mav.addObject("usrTheme", "uni/core/cf3");
		mav.setViewName("uni/core/cf3/ct/m3");
		
		return mav;
	
	}
	

	@RequestMapping(value = "f1", method = RequestMethod.GET)
	public ModelAndView f1(Locale locale, 
												Model model,
												PageCriteria cri, 
												String ct_startdate,
												String ct_enddate,
												HttpServletRequest req,
												HttpServletResponse res) {
		
		logger.info("Welcome home! The client locale is {}.", locale);

		int result = 0;
		Paging paging = new Paging();
		List<CoolHeatMonitorVO> coolHeatList = null;
		int total_count = -1;
		
		ModelAndView mav = new ModelAndView();
		Date serverDate = new Date();
		String pattern = "yyyy-MM-dd HH:mm:ss";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(serverDate);
		

		// 일자 조회
		if (ct_startdate != null && 
			 ct_enddate != null) {
			result = 1;
		}
		
		if (result == 1) {
			if (ct_startdate.length() != 0 && ct_enddate.length() != 0) {}
			else {  result = 0; }
		}
		else { }

		// Result == 0 (전체 조회)
		// Result == 1 (특정 일자 조회)
		if (result == 1) {
			total_count  = coolHeatService.selectBetweenDateCount(ct_startdate, ct_enddate);
		}else {
			total_count = coolHeatService.selectAllCount();
		}
		
		//System.out.println("전체 글 갯수:"+ total_count);
		
		int page_no = cri.getPage();	// 현재 페이지
		int page_size = cri.getPerPageNum();	// 단위 페이지
		
		paging.setPageNo(page_no);
		paging.setPageSize(page_size);
		paging.setTotalCount(total_count);
		
		long startnum = paging.getDbStartNum();
		long endnum = paging.getDbEndNum();
		
		//model.addAttribute("serverTime", formattedDate );
		
		if( result == 0 ) {
			coolHeatList = coolHeatService.selectCoolHeat(startnum, endnum);
		}
		else if ( result == 1) {
			coolHeatList = coolHeatService.selectBetweenCoolHeat(startnum, endnum, 
																							ct_startdate, ct_enddate);
		}
		else {
			coolHeatList = null;
		}

		HttpSession session = req.getSession();
		MemberVO session_vo = (MemberVO) session.getAttribute("vo");

		mav.addObject("sessionVo", session_vo);
		mav.addObject("serverDate", date);
		mav.addObject("usrTheme", "uni/core/cf3");
		mav.addObject("coolHeatPagingUrl", "f1");
		mav.addObject("coolHeatList", coolHeatList);
		mav.addObject("coolHeatPaging", paging);
		mav.setViewName("uni/core/cf3/ct/f1");
		
		return mav;
	
	}
	
	@RequestMapping(value = "f1_ok", method = RequestMethod.POST)
	public ModelAndView f1_ok(Locale locale, 
												Model model,
												String cool_state,
												String heat_state) {
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		ModelAndView mav = new ModelAndView();
		Date serverDate = new Date();		
		String message = "";
		
		CoolHeatMonitorVO chMonitor = null;
		
		if ( cool_state != null && heat_state == null ) {
			
			if(cool_state.equals("열기") ){
				message = "냉방기를 개방합니다.";
			}
			else {
				message = "냉방기를 닫습니다.";
			}
			
		}
		else if ( heat_state != null && cool_state == null) {
			
			if(heat_state.equals("열기") ) {
				message = "난방기를 개방합니다.";
			}
			else {
				message = "난방기를 닫습니다.";	
			}
			
		}else {
			message = "비정상적인 접근입니다.";
		}
		
		chMonitor = new CoolHeatMonitorVO();
		
		chMonitor.setCreateDate(serverDate);
		chMonitor.setLocation("재배치1");
		chMonitor.setCool_state(cool_state);
		chMonitor.setHeat_state(heat_state);
		chMonitor.setIpv4("");
		chMonitor.setIpv6("");
		
		// 냉온 서비스 등록하기
		coolHeatService.insertCoolHeat(chMonitor);
		mav.addObject("message", message);
		mav.addObject("locationUrl", "f1");
		mav.setViewName("common/message");
		
		return mav;
	
	}
	

	@RequestMapping(value = "f2", method = RequestMethod.GET)
	public ModelAndView f2(Locale locale, 
												Model model,
												PageCriteria cri, 
												String ct_startdate,
												String ct_enddate,
												HttpServletRequest req,
												HttpServletResponse res) {
		
		logger.info("Welcome home! The client locale is {}.", locale);

		int result = 0;
		Paging paging = new Paging();
		List<WaterPumpVO> waterPumpList = null;
		int total_count = -1;
		
		ModelAndView mav = new ModelAndView();
		Date serverDate = new Date();
		String pattern = "yyyy-MM-dd HH:mm:ss";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(serverDate);
		

		// 일자 조회
		if (ct_startdate != null && 
			 ct_enddate != null) {
			result = 1;
		}
		
		if (result == 1) {
			if (ct_startdate.length() != 0 && ct_enddate.length() != 0) {}
			else {  result = 0; }
		}
		else { }

		// Result == 0 (전체 조회)
		// Result == 1 (특정 일자 조회)
		if (result == 1) {
			total_count  = waterPumpService.selectBetweenDateCount(ct_startdate, ct_enddate);
		}else {
			total_count = waterPumpService.selectAllCount();
		}
		
		//System.out.println("전체 글 갯수:"+ total_count);
		
		int page_no = cri.getPage();	// 현재 페이지
		int page_size = cri.getPerPageNum();	// 단위 페이지
		
		paging.setPageNo(page_no);
		paging.setPageSize(page_size);
		paging.setTotalCount(total_count);
		
		long startnum = paging.getDbStartNum();
		long endnum = paging.getDbEndNum();
		
		//model.addAttribute("serverTime", formattedDate );
		
		if( result == 0 ) {
			waterPumpList = waterPumpService.selectWaterPump(startnum, endnum);
		}
		else if ( result == 1) {
			waterPumpList = waterPumpService.selectBetweenWaterPump(startnum, endnum, 
																							ct_startdate, ct_enddate);
		}
		else {
			waterPumpList = null;
		}

		HttpSession session = req.getSession();
		MemberVO session_vo = (MemberVO) session.getAttribute("vo");

		mav.addObject("sessionVo", session_vo);
		mav.addObject("serverDate", date);
		mav.addObject("usrTheme", "uni/core/cf3");
		mav.addObject("waterPumpPagingUrl", "f2");
		mav.addObject("waterPumpList", waterPumpList);
		mav.addObject("waterPumpPaging", paging);
		mav.setViewName("uni/core/cf3/ct/f2");
		
		return mav;
	
	}

	@RequestMapping(value = "f2_ok", method = RequestMethod.POST)
	public ModelAndView f2_ok(Locale locale, 
												Model model,
												String water_state) {
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		ModelAndView mav = new ModelAndView();
		Date serverDate = new Date();
		String message = "";
		
		WaterPumpVO waterPump = null;
		
		if ( water_state != null ) {
			
			if(water_state.equals("열기") ){
				message = "물 펌프를 개방합니다.";
			}
			else {
				message = "물 펌프를 닫습니다.";
			}
			
		}else {
			message = "비정상적인 접근입니다.";
		}
		
		waterPump = new WaterPumpVO();
		
		waterPump.setCreateDate(serverDate);
		waterPump.setLocation("재배치1");
		waterPump.setWater_state(water_state);
		waterPump.setIpv4("");
		waterPump.setIpv6("");
		
		// 물 펌프 등록하기
		waterPumpService.insertWaterPump(waterPump);
		
		mav.addObject("message", message);
		mav.addObject("locationUrl", "f2");
		mav.setViewName("common/message");
		
		return mav;
	
	}
	
	@RequestMapping(value = "a1", method = RequestMethod.GET)
	public ModelAndView a1(Locale locale, 
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

		mav.addObject("sessionVo", session_vo);
		mav.addObject("serverDate", date);
		mav.addObject("usrTheme", "uni/core/cf3");
		mav.setViewName("uni/core/cf3/ct/a1");
		
		return mav;
	
	}
	
	@RequestMapping(value = "a1_ok", method = RequestMethod.POST)
	public ModelAndView a1_ok(Locale locale, 
												Model model,
												String sendMessage) {
		
		int sw = 0;
		int result = 0;
		
		logger.info("Welcome home! The client locale is {}.", locale);
		logger.info("메시지출력:" + sendMessage);
		
		AiChatBotVO chatBotWordVo = null; 
		AiChatBotVO chatBotSentenceVo = null;
		AiChatBotFilterVO chatBotFilterWordVo = null;
		
		ModelAndView mav = new ModelAndView();
		Date serverDate = new Date();
		
		List<SpecificDHTVO> specificDhtVO = dhtService.selectSpecificDHT(serverDate);
		
		String message = "";	// 메세지
		String[] strArr = sendMessage.split(" ");
		
		int realCode = 0;
		
		// 단어, 문장 = 가중치 로직
		int i = 0;
		while (i < strArr.length && 
				   realCode == 0) {
			
			chatBotWordVo = aiChatBotService.selectWord(strArr[i]);
			
			//System.out.println("반환값:" + chatBotWordVo + "/현재 문자:" + strArr[i]);
			
			// 문자열 일치(참)
			if ( chatBotWordVo != null ) {
				result = 1;
			}
			
			// 클린 챗봇
			chatBotFilterWordVo = aiChatBotService.selectFilterWord(strArr[i]);
			
			// 욕설 발견
			if ( chatBotFilterWordVo != null )
			{
				result = 2;
			}
			
			// 문장 일치 여부
			if ( result == 1 && (i + 1) != strArr.length ) {
				chatBotSentenceVo = aiChatBotService.selectSentence( strArr[i] + " " + strArr[i + 1] );

				//System.out.println("메시지결과1:" + strArr[i] + " " + strArr[i + 1]);
				
				if ( chatBotSentenceVo != null ) {
					result = 1;
				}else {
					result = 0;
				}
			
			}
			
			// 챗봇 청정 결과
			if ( result == 1 ) {
				
				sw = ChatBotAnswer.MSG_POSITIVE;
				
				if (chatBotSentenceVo != null ) {
					realCode = Math.round( chatBotSentenceVo.getService() );
				}else {
					realCode = Math.round( chatBotWordVo.getService() );
				}

				//System.out.println("리얼코드:" + realCode);
				
				// 메시지 생성
				if ( realCode >= 1 ) { } 
				else {
					result = 0;
				}
				
			}
			
			// 챗봇 필터링 적발 결과
			else if ( result == 2 ) {

				sw = ChatBotAnswer.MSG_NEGATIVE;
				
				if ( chatBotFilterWordVo != null ) {
					realCode = Math.round( chatBotFilterWordVo.getBad_score() );
				}
				
			}
			
			i++;
			
		}

		//System.out.println("리얼코드1:" + realCode);
		
		// 메시지 생성 및 출력
		ChatBotAnswer chAnswer = ChatBotAnswer.getInstance();
		chAnswer.inputDate(serverDate);
		chAnswer.inputData(specificDhtVO);
		message = chAnswer.createMessage(sw, realCode);
		
		//System.out.println("메시지결과:" + message);
		
		mav.addObject("message", message);
		mav.setViewName("common/ajaxMessage");
		
		return mav;
	
	}
		
}