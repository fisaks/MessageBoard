package com.fpi.app;

import org.glassfish.jersey.server.ResourceConfig;

public class MessageBoardApp extends ResourceConfig {

	public MessageBoardApp() {
		
		register(new MessageBoardBinder());
		packages(true, "com.fpi.resource");
		
	}
}
