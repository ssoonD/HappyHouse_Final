package com.ssafy.edu.dto;

public class HospitalDto {
	String date;
	String specimen;
	String sido;
	String gugun;
	String name;
	String address;
	String week;
	String saturday;
	String sunday;
	String num;
	String lat;
	String lng;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSpecimen() {
		return specimen;
	}
	public void setSpecimen(String specimen) {
		this.specimen = specimen;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getGugun() {
		return gugun;
	}
	public void setGugun(String gugun) {
		this.gugun = gugun;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getSaturday() {
		return saturday;
	}
	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}
	public String getSunday() {
		return sunday;
	}
	public void setSunday(String sunday) {
		this.sunday = sunday;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public HospitalDto(String date, String specimen, String sido, String gugun, String name, String address,
			String week, String saturday, String sunday, String num, String lat, String lng) {
		super();
		this.date = date;
		this.specimen = specimen;
		this.sido = sido;
		this.gugun = gugun;
		this.name = name;
		this.address = address;
		this.week = week;
		this.saturday = saturday;
		this.sunday = sunday;
		this.num = num;
		this.lat=lat;
		this.lng=lng;
	}
	@Override
	public String toString() {
		return "hospitalDto [date=" + date + ", specimen=" + specimen + ", sido=" + sido + ", gugun=" + gugun
				+ ", name=" + name + ", address=" + address + ", week=" + week + ", saturday=" + saturday + ", sunday="
				+ sunday + ", num=" + num + "]";
	}
	
	
	
}

