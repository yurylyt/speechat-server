package co.speechat.data;

import java.util.HashMap;

/**
 * TODO: Document me
 * Author: Iurii Lytvynenko
 */
public class ChatHouseImpl implements ChatHouse {
    private HashMap<String, ChatRoom> rooms = new HashMap<String, ChatRoom>();
    private static final ChatHouse instance = new ChatHouseImpl();
    
    public static ChatHouse get() {
        return instance;
    }
    
    @Override
    public ChatRoom getRoom(String room) {
        if (!rooms.containsKey(room)) {
            rooms.put(room, new ChatRoomImpl(room, new PasswordNameGenerator()));
        }
        return rooms.get(room);
    }
}
