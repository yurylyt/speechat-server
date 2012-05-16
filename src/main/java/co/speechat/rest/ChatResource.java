package co.speechat.rest;

import co.speechat.data.ChatHouse;
import co.speechat.data.ChatHouseImpl;
import co.speechat.data.ChatRoom;
import com.sun.jersey.api.NotFoundException;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Author: Iurii Lytvynenko
 */
@Path("/chat")
public class ChatResource {
    private ChatHouse chatHouse;

    public ChatResource() {
        chatHouse = ChatHouseImpl.get();
    }

    @Path("/{chatRoom}")
    public ChatRoomResource get(@PathParam("chatRoom") String chatRoom) {
        ChatRoom room = chatHouse.getRoom(chatRoom);
        if (room == null)
            throw new NotFoundException(chatRoom);
        return new ChatRoomResource(room);
    }
}
