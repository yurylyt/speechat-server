package co.speechat.data;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Author: Iurii Lytvynenko
 */
public class ChatRoomImpl implements ChatRoom {
    private List<Message> messages = new CopyOnWriteArrayList<Message>();
    private Map<String, Integer> reads = new HashMap<String, Integer>();
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
        if (!reads.containsKey(member)) {
            member = register(member);
        }
        int index = reads.get(member);
        dropNewEntries(member);
        if (index >= messages.size())
            return Collections.emptyList();
        return messages.subList(index, messages.size());
    }

    private void dropNewEntries(String member) {
        if (isValid(member))
            reads.put(member, messages.size());
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
        reads.put(member, 0);
        return member;
    }

    private String genMemberId() {
        return nameGenerator.generate();
    }

    @Override
    public void add(Message message) {
        messages.add(message);
    }

    @Override
    public int getUsersCount() {
        return reads.size();
    }
}
