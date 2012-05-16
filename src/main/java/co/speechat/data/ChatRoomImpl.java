package co.speechat.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Author: Iurii Lytvynenko
 */
public class ChatRoomImpl implements ChatRoom {
    private Collection<Message> messages = new LinkedList<Message>();
    private Map<String, Collection<Message>> newEntries = new HashMap<String, Collection<Message>>();
    private NameGenerator nameGenerator;
    private String name;

    public ChatRoomImpl(String name, NameGenerator nameGenerator) {
        this.name = name;
        this.nameGenerator = nameGenerator;
    }

    @Override
    public Collection<Message> getAll(String member) {
        member = register(member);
        return get(member); 
    }
    
    @Override
    public Collection<Message> get(String member) {
        if (!newEntries.containsKey(member)) {
            member = register(member);
        }
        Collection<Message> msgs = newEntries.get(member);
        dropNewEntries(member);
        return msgs;
    }

    private void dropNewEntries(String member) {
        if (isValid(member))
            newEntries.put(member, new LinkedList<Message>());
    }

    private boolean isValid(String member) {
        return member != null && !member.trim().isEmpty();
    }

    @Override
    public String register() {
        return register(null);
    }

    private String register(String member) {
        if (!isValid(member))
            member = genMemberId();
        newEntries.put(member, messages);
        return member;
    }

    private String genMemberId() {
        return nameGenerator.generate();
    }

    @Override
    public void add(Message message) {
        messages.add(message);
        for (Collection<Message> entries : newEntries.values()) {
            entries.add(message);
        }
    }

    @Override
    public int getUsersCount() {
        return newEntries.size();
    }
}
