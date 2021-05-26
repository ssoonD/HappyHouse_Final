package com.ssafy.edu.dto;

public class QuestionDto {
	int idx;
	String username;
	String title;
	String content;
	int secret;
	String password;
	String date;
	
	public QuestionDto() {
		super();
	}

	public QuestionDto(String username, String title, String content, int secret, String password) {
		super();
		this.username = username;
		this.title = title;
		this.content = content;
		this.secret = secret;
		this.password = password;
	}
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	

	public int getSecret() {
		return secret;
	}

	public void setSecret(int secret) {
		this.secret = secret;
	}
	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "QuestionDto [idx=" + idx + ", username=" + username + ", title=" + title + ", content=" + content
				+ ", date=" + date + "]";
	}
	
	
	
}
