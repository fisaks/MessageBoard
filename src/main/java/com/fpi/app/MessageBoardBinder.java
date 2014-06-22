package com.fpi.app;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.fpi.domain.MessageBoardDAO;
import com.fpi.domain.MessageBoardDAOImpl;

public class MessageBoardBinder extends AbstractBinder {

	@Override
	protected void configure() {
		// bind the MessageBoardDAO to singletons and the implementation class to be used for CDI
    	bind(MessageBoardDAO.class).in(Singleton.class);
    	bind(new MessageBoardDAOImpl()).to(MessageBoardDAO.class);

		

	}

}
