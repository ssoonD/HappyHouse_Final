package com.ssafy.edu.dto;

public class AnswerDto {
	int idx;
	int qidx;
	String username;
	String content;
	String date;

	public AnswerDto() {
		super();
	}

	public AnswerDto(int idx, int qidx, String username, String content) {
		super();
		this.idx = idx;
		this.qidx = qidx;
		this.username = username;
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

	public int getQidx() {
		return qidx;
	}

	public void setQidx(int qidx) {
		this.qidx = qidx;
	}

	@Override
	public String toString() {
		return "AnswerDto [idx=" + idx + ", username=" + username + ", content=" + content + ", date=" + date + "]";
	}

}
