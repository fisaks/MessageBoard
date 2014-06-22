package com.fpi.bean;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import com.fpi.domain.Message;
/**
 * 
 * Bean to represent the V2 of the external Message. 
 * 
 * @author freddi
 *
 */

@XmlRootElement

public class MessageV2Bean extends MessageBean {

	protected String url;
	
	public MessageV2Bean() {
		super();
	}
	public MessageV2Bean(String title,String content,String sender,String url) {
		super(title,content,sender);
		this.url=url;
		
	}
	public MessageV2Bean(Message m) {
		super(m);
		this.url=m.getUrl();

	}

	@NotBlank
	@URL 
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	

}
