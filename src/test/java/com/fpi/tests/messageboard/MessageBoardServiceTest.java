package com.fpi.tests.messageboard;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import com.fpi.app.MessageBoardApp;
import com.fpi.bean.MessageV2Bean;
import com.fpi.domain.Message;

/**
 * Some simple test cases to test the basic function of the message board rest application
 * 
 * @author freddi
 *
 */

public class MessageBoardServiceTest extends JerseyTest {

	
	 @Override
	 protected Application configure() {
		 return new MessageBoardApp();
	    }
	 
	private final static Message SAMPLE_MESSAGE=new Message("sample title","sample content","sample sender","http://www.google.com"); 

	protected void addMessage(Message m) {

		Entity<Message> mEntity = Entity.entity(m, MediaType.APPLICATION_JSON_TYPE);
		
		Response response = target("/messageboard/createMessage").request(MediaType.APPLICATION_JSON).post(mEntity);
		// check that added ok       
		Assert.assertEquals(200, response.getStatus());

	}

	@Test
    public void testAdd2MessagesAndTestListThem()  {
		
		final String MESSAGE1_TITLE="message 1";
		final String MESSAGE2_TITLE="message 2";
		
		// create first message
		Message m=new Message(SAMPLE_MESSAGE);
		m.setTitle(MESSAGE1_TITLE);
		
		addMessage(m);		
		
		// change title for 2 message
		m.setTitle(MESSAGE2_TITLE);
		addMessage(m);
		
		Response response = target("/messageboard/listMessages/v2").request(MediaType.APPLICATION_JSON).get();
		
		List<Message> allMeassages=response.readEntity(new GenericType<List<Message>>() {});


		Assert.assertEquals(200, response.getStatus());
		Assert.assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));

		// should Be 2 messages in repository
		Assert.assertEquals(2, allMeassages.size());
	
		// check the titles
		for(Message message : allMeassages) {
			Assert.assertTrue( message.getTitle().equals(MESSAGE1_TITLE) || message.getTitle().equals(MESSAGE2_TITLE));
		}
		Assert.assertNotEquals(allMeassages.get(0).getTitle(),allMeassages.get(1).getTitle());

    }
	

	@Test
	public void testListJsonV1() {
		
		addMessage(SAMPLE_MESSAGE);
		
		Response response = target("/messageboard/listMessages/v1").request(MediaType.APPLICATION_JSON).get();
		

		List<Message> allMeassages=response.readEntity(new GenericType<List<Message>>() {});

		
		Assert.assertEquals(200, response.getStatus());
		Assert.assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
		Assert.assertEquals(1, allMeassages.size());
		Assert.assertNull("URL should be null in v1",allMeassages.get(0).getUrl());
		
	}
	@Test
	public void testListJsonV2() {

		addMessage(SAMPLE_MESSAGE);

		
		Response response = target("/messageboard/listMessages/v2").request(MediaType.APPLICATION_JSON).get();

		List<Message> allMeassages=response.readEntity(new GenericType<List<Message>>() {});
		
		Assert.assertEquals(200, response.getStatus());
		Assert.assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
		Assert.assertEquals(1, allMeassages.size());
		Assert.assertNotNull("URL should not be null in v2",allMeassages.get(0).getUrl());
	
		
		
	}

	@Test
	public void testListXmlV2() {

		addMessage(SAMPLE_MESSAGE);
		addMessage(SAMPLE_MESSAGE);
		Response response = target("/messageboard/listMessages/v2").request(MediaType.APPLICATION_XML).get();

		List<MessageV2Bean> allMeassages=response.readEntity(new GenericType<List<MessageV2Bean>>() {});
		
		Assert.assertEquals(200, response.getStatus());
		Assert.assertEquals(MediaType.APPLICATION_XML, response.getHeaderString("Content-Type"));
	
		Assert.assertEquals(2, allMeassages.size());
		Assert.assertNotNull("URL should not be null in v2",allMeassages.get(0).getUrl());
		
		
	}


	@Test
    public void testCreationBadUrl() {
		
		Message m=new	Message(SAMPLE_MESSAGE);
		// missing protocol from url
		m.setUrl("www.google.com");
		
		Entity<Message> mEntity = Entity.entity(m, MediaType.APPLICATION_JSON_TYPE);
		
		Response response = target("/messageboard/createMessage").request(MediaType.APPLICATION_JSON).post(mEntity);
		
		Assert.assertTrue(response.getStatus()!=200);
    }
	@Test
    public void testCreationMissingTitle() {

		Message m=new	Message(SAMPLE_MESSAGE);
		m.setTitle(null);
		
		Entity<Message> mEntity = Entity.entity(m, MediaType.APPLICATION_JSON_TYPE);
		
		Response response = target("/messageboard/createMessage").request(MediaType.APPLICATION_JSON).post(mEntity);
		
		Assert.assertTrue(response.getStatus()!=200);
    }

	@Test
    public void testCreationMaxLengthSender() {
		
		Message m=new	Message(SAMPLE_MESSAGE);
		
		StringBuilder sender=new StringBuilder(100);
		for(int i=0;i<100;i++)
			sender.append("a");
			
		m.setSender(sender.toString());
		
		Entity<Message> mEntity = Entity.entity(m, MediaType.APPLICATION_JSON_TYPE);
		
		Response response = target("/messageboard/createMessage").request(MediaType.APPLICATION_JSON).post(mEntity);
		
		Assert.assertTrue(response.getStatus()!=200);
    }

}
