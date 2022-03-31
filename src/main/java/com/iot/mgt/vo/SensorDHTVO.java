package com.iot.mgt.vo;

import java.util.Date;
import java.util.UUID;

public class SensorDHTVO {

	private int id;
	private UUID uuid;
	private Date regidate;
	private String location;
	private float temperature;
	private float humidity;
	private String ipv4;
	private String ipv6;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public Date getRegidate() {
		return regidate;
	}
	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public float getHumidity() {
		return humidity;
	}
	public void setHumidity(float humidity) {
		this.humidity = humidity;
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
