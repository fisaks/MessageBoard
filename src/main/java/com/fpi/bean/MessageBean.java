package com.fpi.bean;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fpi.domain.Message;

/**
 * Bean to represent the V1 of the external Message. 
 * 
 * @see MessageV2Bean
 * @author freddi
 */
public class MessageBean {
	protected String title;
	protected String content;
	protected String sender;


	public MessageBean() {};
		
	public MessageBean(Message m) {
		this.title=m.getTitle();
		this.sender=m.getSender();
		this.content=m.getContent();
		
	}
	
	public MessageBean(String title,String content,String sender) {
		
		this.title = title;
		this.content = content;
		this.sender = sender;
		
	}
	

	
	@Size(max = 128)
	@NotBlank
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Size(max = 64)
	@NotBlank
	public String getSender() {
		return this.sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	@Size(max = 1000)
	@NotBlank
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	


}
