package com.iot.mgt.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.iot.mgt.vo.AiChatBotFilterVO;
import com.iot.mgt.vo.AiChatBotVO;

@Repository
public class AiChatBotDAOImpl implements AiChatBotDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String ns = "com.iot.mgt.mapper.AiChatBotMapper";
	
	@Override
	public AiChatBotVO selectWord(String word) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("word", word);
		
		return sqlSession.selectOne(ns + ".selectKeyword", paramMap);
	}

	@Override
	public AiChatBotVO selectSentence(String sentence) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("sentence", sentence);
		
		return sqlSession.selectOne(ns + ".selectSentence", paramMap);
		
	}

	@Override
	public AiChatBotFilterVO selectFilterWord(String word) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("word", word);
		
		return sqlSession.selectOne(ns + ".selectFilter", paramMap);
	}
	
}