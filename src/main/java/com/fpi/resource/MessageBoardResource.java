package com.fpi.resource;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fpi.bean.MessageBean;
import com.fpi.bean.MessageV2Bean;
import com.fpi.domain.MessageBoardDAO;
import com.fpi.util.MessageConverter;

/**
 * <p>Expose the message board as an REST service (exposed at "messageboard" path)</p>
 * 
 * <p>Currently support for 2 operations</p>
 * 
 * <ul>
 * <li>listMessages</li>
 * <li>createMessage</li>
 * </ul>
 * 
 * <p>The listMessages supports two different formats</p>
 * <ul>
 * <li>/listMessages/v1</li>
 * <li>/listMessages/v2</li>
 * </ul> 
 * 
 *<p>If no version is specified part of the path version 1 is used</p>
 *
 *<p>Example usage: (using the maven jetty plugin mvn jetty:run)</p>
 * <ul>
 * <li>curl -v -X POST -H 'Content-Type: application/json' http://localhost:8080/webapi/messageboard/createMessage --data '{"title":"sample title", "content": "sample content", "sender": "sample sender", "url": "http://google.com" }'</li>
 * <li>curl -v -X GET -H 'Accept: application/json'  http://localhost:8080/webapi/messageboard/listMessages</li>
 * <li>curl -v -X GET -H 'Accept: application/xml'  http://localhost:8080/webapi/messageboard/listMessages/v2</li>
 * </ul> 
 *
 */
@Path("/messageboard")
public class MessageBoardResource {

	@Inject
	private MessageBoardDAO dao;
	

    @GET
    @Path("/listMessages{v1 : (/v1)?}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MessageBean> listMessages() {
    	
    	return MessageConverter.messagesToMessageBeans(dao.listMessages());
    	
    }

    @GET
    @Path("/listMessages/v2")
    @Produces( {MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
    
    public List<MessageV2Bean> listMessagesV2() {
    	return MessageConverter.messagesToMessageV2Beans(dao.listMessages());
    }

    @POST
    @Path("/createMessage")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMessage(@Valid MessageV2Bean messageBean) {
    	
    	dao.addMessage(MessageConverter.messageV2BeanToMessage(messageBean));
    	
    	return Response.ok().build();
    	
    	
    }

}
