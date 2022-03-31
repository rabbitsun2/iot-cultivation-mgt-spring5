package com.iot.mgt.vo;

import java.util.Date;

public class AiChatBotVO {
	
	private int id;
	private String word;
	private String sentence;
	private String answer;
	private float service;
	private Date regidate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getSentence() {
		return sentence;
	}
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public float getService() {
		return service;
	}
	public void setService(float service) {
		this.service = service;
	}
	public Date getRegidate() {
		return regidate;
	}
	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}
		
}