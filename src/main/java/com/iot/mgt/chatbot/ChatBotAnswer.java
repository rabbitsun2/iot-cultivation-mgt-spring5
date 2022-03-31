package com.iot.mgt.chatbot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.iot.mgt.vo.SpecificDHTVO;

public class ChatBotAnswer {

	public final static int MSG_POSITIVE = 1;
	public final static int MSG_NEGATIVE = 2;
	
	private static ChatBotAnswer instance;
	private List<SpecificDHTVO> dhtVO;
	private Date curDate;
	
	private ChatBotAnswer() {}
	
	public static ChatBotAnswer getInstance() {
		
		if ( instance == null ) {
			instance = new ChatBotAnswer();
		}
		
		return instance;
		
	}
	
	public void inputDate(Date curDate) {
		this.curDate = curDate;
	}
	
	private Date getDate() {
		return this.curDate;
	}
	
	public void inputData(List<SpecificDHTVO> dhtVO) {
		this.dhtVO = dhtVO;
	}
	
	private List<SpecificDHTVO> loadData(){
		return this.dhtVO;
	}
	
	public String createMessage(int sw, int index) {
				
		String msg = "";
		
		switch(sw) {

			case MSG_POSITIVE:
				msg = positiveMsg(index);
				break;
				
			case MSG_NEGATIVE:
				msg = negativeMsg(index);
				break;
				
			default:
				
				break;
		
		}
		
		return msg;
		
	}
	
	private String positiveMsg(int index) {
		
		String message = "";
		List<SpecificDHTVO> lstData = this.loadData();
		Date curDate = this.getDate();

		String pattern = "yyyy-MM-dd HH:mm:ss";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(curDate);
		
		switch(index) {
			
			case 1:
				message = "오늘 온도는 다음과 같습니다.<br>";
				message = message + "(" + date + ")<br>"; 
				
				for (SpecificDHTVO val : lstData) {
					message += String.valueOf(val.getHh()) + "/" + val.getAvg_temperature() + "<br>";
				}
				
				break;
			
			case 2:
				message = "오늘 습도는 다음과 같습니다.<br>";
				message = message + "(" + date + ")<br>";
				
				for (SpecificDHTVO val : lstData) {
					message += String.valueOf(val.getHh()) + "/" + val.getAvg_humidity() + "<br>";
				}
				
				break;
			
			default:
				message = "학습되지 않은 데이터입니다.<br>";
				message += "공부 열심히 할게요.";
				break;
				
		}
		
		return message;	
		
	}
	

	private String negativeMsg(int index) {
		
		String message = "";

		Date curDate = this.getDate();
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		switch(index) {
			
			case 1:
				message = "욕설은 사용할 수 없습니다.";
				break;
						
			default:
				message = "고운말을 사용하세요.";
				break;
				
		}
		
		return message;	
		
	}
	
}
