package com.iot.mgt.service;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.iot.mgt.dao.AiChatBotDAO;
import com.iot.mgt.vo.AiChatBotFilterVO;
import com.iot.mgt.vo.AiChatBotVO;

@Service
public class AiChatBotServiceImpl implements AiChatBotService {

	@Inject
	private AiChatBotDAO dao;
	
	@Override
	public AiChatBotVO selectWord(String word) {
		
		return dao.selectWord(word);
	}

	@Override
	public AiChatBotVO selectSentence(String sentence) {
		
		return dao.selectSentence(sentence);
	}

	@Override
	public AiChatBotFilterVO selectFilterWord(String word) {
		
		return dao.selectFilterWord(word);
	}
	
}