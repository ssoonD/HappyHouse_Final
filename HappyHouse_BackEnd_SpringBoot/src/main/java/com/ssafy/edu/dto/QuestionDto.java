package com.ssafy.edu.dto;

public class QuestionDto {
	int idx;
	String username;
	String title;
	String content;
	String date;
	
	public QuestionDto(String username, String title, String content) {
		super();
		this.username = username;
		this.title = title;
		this.content = content;
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

	@Override
	public String toString() {
		return "QuestionDto [idx=" + idx + ", username=" + username + ", title=" + title + ", content=" + content
				+ ", date=" + date + "]";
	}
	
	
	
}
