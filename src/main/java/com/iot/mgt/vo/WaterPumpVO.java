package com.iot.mgt.vo;

import java.util.Date;

public class WaterPumpVO {
	
	private int id;
	private Date createDate;
	private String location;
	private String water_state;
	private String memo;
	private String ipv4;
	private String ipv6;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getWater_state() {
		return water_state;
	}
	public void setWater_state(String water_state) {
		this.water_state = water_state;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getIpv4() {
		return ipv4;
	}
	public void setIpv4(String ipv4) {
		this.ipv4 = ipv4;
	}
	public String getIpv6() {
		return ipv6;
	}
	public void setIpv6(String ipv6) {
		this.ipv6 = ipv6;
	}
		
}
