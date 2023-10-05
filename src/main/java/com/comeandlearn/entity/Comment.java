package com.comeandlearn.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int commentId;
	String userName;
	@Column(length = 100)
	String content;
	@Column(name = "date_time")
	String dateAndTime;
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comment(int commentId, String userName, String content, String dateAndTime) {
		super();
		this.commentId = commentId;
		this.userName = userName;
		this.content = content;
		this.dateAndTime = dateAndTime;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDateAndTime() {
		return dateAndTime;
	}
	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", userName=" + userName + ", content=" + content + ", dateAndTime="
				+ dateAndTime + "]";
	}

	
	
}
