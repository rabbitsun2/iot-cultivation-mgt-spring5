package com.iot.mgt.dao;

import com.iot.mgt.vo.AiChatBotFilterVO;
import com.iot.mgt.vo.AiChatBotVO;

public interface AiChatBotDAO {

	public AiChatBotVO selectWord(String word);
	public AiChatBotVO selectSentence(String sentence);

	public AiChatBotFilterVO selectFilterWord(String word);														
}