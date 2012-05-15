package co.speechat.rest;

import co.speechat.data.ChatRoom;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * TODO: Document me
 * Author: Iurii Lytvynenko
 */
public class ChatRoomResource {

    private ChatRoom chatRoom;

    public ChatRoomResource(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/")
    public String register() {
        return chatRoom.register();
    }
    
    @Path("/{member}")
    public ChatClientResource get(@PathParam("member") String member) {
        return new ChatClientResource(member, chatRoom);
    }
}
