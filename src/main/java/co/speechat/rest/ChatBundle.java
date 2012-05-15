package co.speechat.rest;

import co.speechat.data.Message;

import java.util.Collection;

/**
 * TODO: Document me
 * Author: Iurii Lytvynenko
 */
public class ChatBundle {
    private String member;
    private Collection<Message> messages;

    public ChatBundle(String member, Collection<Message> messages) {
        this.messages = messages;
        this.member = member;
    }

    public String getMember() {
        return member;
    }

    public Collection<Message> getMessages() {
        return messages;
    }
}
