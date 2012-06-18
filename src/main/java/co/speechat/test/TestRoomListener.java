package co.speechat.test;

import co.speechat.data.ChatHouse;
import co.speechat.data.ChatHouseImpl;
import co.speechat.data.Message;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * TODO: Document me
 * Author: Iurii Lytvynenko
 */
public class TestRoomListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        ChatHouse chatHouse = ChatHouseImpl.get();
        chatHouse.createRoom("injection");
        chatHouse.getRoom("injection").add(new Message("Hey there", "anonymous"));
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
