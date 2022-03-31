package com.iot.mgt.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iot.mgt.service.DHTService;
import com.iot.mgt.vo.SensorDHTVO;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/uni/core/cf3/iot/*")
public class IotController {
	
	@Inject
	private DHTService dhtService;
	
	private static final Logger logger = LoggerFactory.getLogger(IotController.class);
	
	
	@RequestMapping(value = "dht22", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, 
									Model model,
									String key,
									SensorDHTVO vo) {
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		boolean status = false;
		ModelAndView mav = new ModelAndView();
		Date serverdate = new Date();
		
		// 인증키 값 일치 여부
		if ( key.equals("hama") ) {
			status = true;
		}
		
		// 정보 등록
		if ( status == true ) {

			UUID uuid = UUID.randomUUID();
			
			vo.setLocation("재배지1");
			vo.setUuid(uuid);
			vo.setRegidate(serverdate);
			vo.setIpv4("");
			vo.setIpv6("");
			
			dhtService.insertDHT(vo);
		}
		
		mav.setViewName("common/ajaxMessage");
		
		return mav;
	
	}
	
}
