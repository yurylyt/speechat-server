package co.speechat.rest;

import co.speechat.data.ChatRoom;
import co.speechat.data.Message;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

/**
 * TODO: Document me
 * Author: Iurii Lytvynenko
 */
@Produces(MediaType.APPLICATION_JSON)
public class ChatClientResource {
    public static final int MAX_MSG_LENGTH = 140;
    private ChatRoom chatRoom;
    private String member;

    public ChatClientResource(String member, ChatRoom chatRoom) {
        this.member = member;
        this.chatRoom = chatRoom;
    }

    @GET
    @Path("new")
    public ChatBundle get() {
        return new ChatBundle(member, chatRoom.get(member));
    }
    
    @GET
    public ChatBundle getAll() {
        return new ChatBundle(member, chatRoom.getAll(member));
    }
    
    @POST
    public void post(MultivaluedMap<String, String> formParams) {
        List<String> msgs = formParams.get("message");
        String message = msgs.size() > 0 ? msgs.get(0) : null;
        
        if (message == null || message.trim().isEmpty())
            return;
        if (message.length() > MAX_MSG_LENGTH)
            message = message.substring(0, MAX_MSG_LENGTH);
        chatRoom.add(new Message(message, member));
    }
}
