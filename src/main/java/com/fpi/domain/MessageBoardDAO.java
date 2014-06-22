package com.fpi.domain;

import java.util.List;

import javax.inject.Singleton;

/**
 * A singleton DAO 
 * @author freddi
 *
 */
@Singleton
public interface MessageBoardDAO {
	
	/**
	 * 
	 * @return A list of the messages in the message board app
	 */
	public List<Message> listMessages();
	
	/**
	 * Adds the newMessage to the list
	 * 
	 * @param message
	 */
	public void addMessage(Message message);
	

}
