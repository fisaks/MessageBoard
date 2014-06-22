package com.fpi.util;

import java.util.ArrayList;
import java.util.List;

import com.fpi.bean.MessageBean;
import com.fpi.bean.MessageV2Bean;
import com.fpi.domain.Message;

/**
 * Used to convert the internal domain representation between different external message versions 
 * 
 * 
 * @author freddi
 *
 */
public class MessageConverter {


	public static List<MessageV2Bean> messagesToMessageV2Beans(List<Message> messages) {
		
		// returns an empty list in case input list was null
		if(messages==null) {
			return new ArrayList<MessageV2Bean>();
		}
		
		List<MessageV2Bean> newList=new ArrayList<MessageV2Bean>(messages.size());
		for(Message m: messages) {
			newList.add(new MessageV2Bean(m));
			
		}
		return newList;
		
		
	}

	public static List<MessageBean> messagesToMessageBeans(List<Message> messages) {

		// returns an empty list in case input list was null
		if(messages==null) {
			return new ArrayList<MessageBean>();
		}
		
		List<MessageBean> newList=new ArrayList<MessageBean>(messages.size());
		for(Message m: messages) {
			newList.add(new MessageBean(m));
			
		}
		return newList;
		
		
	}

	public static Message messageV2BeanToMessage(MessageV2Bean mV2Bean) {
		//Returns a empty Message in case input bean was null 
		if(mV2Bean==null)
			return new Message();
		
		return new Message(mV2Bean.getTitle(),mV2Bean.getContent(),mV2Bean.getSender(),mV2Bean.getUrl());
	}		


}
