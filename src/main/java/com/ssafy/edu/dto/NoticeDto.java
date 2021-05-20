package com.ssafy.edu.dto;

import java.io.Serializable;

public class NoticeDto implements Serializable {
	private int idx;
	private String username;
	private String title;
	private String content;
	private String date;

	public NoticeDto() {
	}

	public NoticeDto(int idx, String username, String title, String content, String date) {
		this.idx = idx;
		this.username = username;
		this.title = title;
		this.content = content;
		this.date = date;
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

}
