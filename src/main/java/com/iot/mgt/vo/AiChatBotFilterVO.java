package com.iot.mgt.vo;

import java.util.Date;

public class AiChatBotFilterVO {

	private int id;
	private String word;
	private float bad_score;
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
	public float getBad_score() {
		return bad_score;
	}
	public void setBad_score(float bad_score) {
		this.bad_score = bad_score;
	}
	public Date getRegidate() {
		return regidate;
	}
	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}
		
}
