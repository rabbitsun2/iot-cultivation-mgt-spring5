package com.iot.mgt.vo;

import java.util.Date;
import java.util.UUID;

public class MemberVO {

	private int id;
	private String email;
	private UUID uuid;
	private String passwd;
	private String usrname;
	private Date birthdate;
	private int remember;
	private String usrgrant;
	private int locked;
	private Date regidate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getUsrname() {
		return usrname;
	}
	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public int getRemember() {
		return remember;
	}
	public void setRemember(int remember) {
		this.remember = remember;
	}
	public String getUsrgrant() {
		return usrgrant;
	}
	public void setUsrgrant(String usrgrant) {
		this.usrgrant = usrgrant;
	}
	public int getLocked() {
		return locked;
	}
	public void setLocked(int locked) {
		this.locked = locked;
	}
	public Date getRegidate() {
		return regidate;
	}
	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}
				
}