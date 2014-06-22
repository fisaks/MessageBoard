package com.fpi.domain;

import java.util.ArrayList;
import java.util.List;



/**
 * A simple in memory implementation of the Message Board DAO 
 * 
 * @see MessageBoardDAO
 * 
 * @author freddi
 *
 */


public class MessageBoardDAOImpl implements MessageBoardDAO {

	private List<Message> allMessages=new ArrayList<Message>();
	
	
	/**
	 * Returns an <b>immutable</b> list of all message
	 * 
	 * @return a list of Messages. Any changes to the returned list is not reflected to the internal in memory list
	 */
	public List<Message> listMessages() {
		List<Message> localList=new ArrayList<Message>(allMessages.size());
		for(Message m: allMessages) {
			localList.add(new Message(m));
		}
		return localList;

	}	
	
	
	public void addMessage(Message message) {
		allMessages.add(message);
	
	}
	



}
