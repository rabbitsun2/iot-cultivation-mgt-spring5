package com.iot.mgt.vo;

import java.util.Date;

public class RemoteMovieVO {
	
	private int id;
	private Date createDate;
	private String location;
	private String status;
	private String uuid;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
