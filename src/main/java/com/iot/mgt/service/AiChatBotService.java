package com.iot.mgt.service;

import com.iot.mgt.vo.AiChatBotFilterVO;
import com.iot.mgt.vo.AiChatBotVO;

public interface AiChatBotService {

	public AiChatBotVO selectWord(String word);
	public AiChatBotVO selectSentence(String sentence);
	public AiChatBotFilterVO selectFilterWord(String word);
	
}
