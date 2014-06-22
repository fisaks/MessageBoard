package com.fpi.domain;

public class Message {
	
	private String title;
	private String content;
	private String sender;
	private String url;

	public Message() {
		
	}
	/**
	 * Copy constructor
	 * @param m
	 */
	public Message(Message m) {
		title=m.title;
		content=m.content;
		sender=m.sender;
		url=m.url;
	}
	 public Message(String title, String content, String sender, String url) {
		this.title = title;
		this.content = content;
		this.sender = sender;
		this.url = url;
	}
	
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSender() {
		return this.sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
