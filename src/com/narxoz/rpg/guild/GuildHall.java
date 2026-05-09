package com.narxoz.rpg.guild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Topic-based mediator for the Adventurers' Guild war council.
 */
public class GuildHall implements GuildMediator {

    private final Map<String, List<GuildMember>> membersByTopic = new HashMap<>();

    public int getMessagesRouted() {
        return messagesRouted;
    }

    public int getMembersNotified() {
        return membersNotified;
    }

    private int messagesRouted = 0;
    private int membersNotified = 0;

    @Override
    public void register(GuildMember member) {
        addSubscriber("orders",member);
        addSubscriber("supplies",member);
        addSubscriber("scouting",member);
        addSubscriber("healing", member);
        addSubscriber("urgent",member);
        addSubscriber("rewards", member);
    }

    @Override
    public void dispatch(String topic, GuildMember from, String payload) {
        messagesRouted ++;
        List<GuildMember> subscribers = subscribersFor(topic);
        for (GuildMember member : subscribers) {
            if (member != from) {
                member.receive(topic,from,payload);
                membersNotified++;
            }
        }
    }

    protected void addSubscriber(String topic, GuildMember member) {
        membersByTopic.computeIfAbsent(topic, key -> new ArrayList<>()).add(member);
    }

    protected List<GuildMember> subscribersFor(String topic) {
        return membersByTopic.getOrDefault(topic, List.of());
    }
}
