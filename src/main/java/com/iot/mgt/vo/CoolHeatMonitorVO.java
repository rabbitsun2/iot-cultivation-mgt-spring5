package com.iot.mgt.vo;

import java.util.Date;

public class CoolHeatMonitorVO {

	private int id;
	private Date createDate;
	private String location;
	private String cool_state;
	private String heat_state;
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
	public String getCool_state() {
		return cool_state;
	}
	public void setCool_state(String cool_state) {
		this.cool_state = cool_state;
	}
	public String getHeat_state() {
		return heat_state;
	}
	public void setHeat_state(String heat_state) {
		this.heat_state = heat_state;
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