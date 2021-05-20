package com.ssafy.edu.dto;

import java.io.Serializable;

public class MemberDto implements Serializable{
	private String userid;
	private String username;
	private String userpwd;
	private String email;
	private String number;
	private String address;
	private int role;
	private String joindate;

	public MemberDto() {
	}

	public MemberDto(String userid, String username, String userpwd, String email, String number, String address,
			int role, String joindate) {
		this.userid = userid;
		this.username = username;
		this.userpwd = userpwd;
		this.email = email;
		this.number = number;
		this.address = address;
		this.role = role;
		this.joindate = joindate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

}

/*
 * userid VARCHAR(16) NOT NULL, username VARCHAR(20) NOT NULL, userpwd
 * VARCHAR(16) NOT NULL, email VARCHAR(50) NULL, number VARCHAR(13) NULL,
 * address VARCHAR(100) NULL, role int, joindate TIMESTAMP NULL DEFAULT
 * current_timestamp,
 */