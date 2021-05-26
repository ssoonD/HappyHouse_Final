package com.ssafy.edu.dto;

public class WelfareDto {
	String guname;
	int sports;
	int theater;
	int lib;
	int welfarecenter;
	public String getGuname() {
		return guname;
	}
	public void setGuname(String guname) {
		this.guname = guname;
	}
	public int getSports() {
		return sports;
	}
	public void setSports(int sports) {
		this.sports = sports;
	}
	public int getTheater() {
		return theater;
	}
	public void setTheater(int theater) {
		this.theater = theater;
	}
	public int getLib() {
		return lib;
	}
	public void setLib(int lib) {
		this.lib = lib;
	}
	public int getWelfarecenter() {
		return welfarecenter;
	}
	public void setWelfarecenter(int welfarecenter) {
		this.welfarecenter = welfarecenter;
	}
	public WelfareDto(String guname, int sports, int theater, int lib, int welfarecenter) {
		super();
		this.guname = guname;
		this.sports = sports;
		this.theater = theater;
		this.lib = lib;
		this.welfarecenter = welfarecenter;
	}
	@Override
	public String toString() {
		return "WelfareDto [guname=" + guname + ", sports=" + sports + ", theater=" + theater + ", lib=" + lib
				+ ", welfarecenter=" + welfarecenter + "]";
	}
	
	
	
}

