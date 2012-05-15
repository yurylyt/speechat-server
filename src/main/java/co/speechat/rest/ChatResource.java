package co.speechat.rest;

import co.speechat.data.ChatHouse;
import co.speechat.data.ChatHouseImpl;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Author: Iurii Lytvynenko
 */
@Path("/")
public class ChatResource {
    private ChatHouse chatHouse;

    public ChatResource() {
        chatHouse = ChatHouseImpl.get();
    }

    @Path("/{chatRoom}")
    public ChatRoomResource get(@PathParam("chatRoom") String chatRoom) {
        return new ChatRoomResource(chatHouse.getRoom(chatRoom));
    }
}
