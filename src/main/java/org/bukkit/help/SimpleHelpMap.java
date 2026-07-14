package org.bukkit.help;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleHelpMap implements HelpMap {
    private final Map<String, HelpTopic> topics = new ConcurrentHashMap<>();
    private final Map<Class<?>, HelpTopicFactory<?>> factories = new ConcurrentHashMap<>();

    @Override
    public HelpTopic getHelpTopic(String topicName) { return topics.get(topicName); }

    @Override
    public Collection<HelpTopic> getHelpTopics() { return topics.values(); }

    @Override
    public void addTopic(HelpTopic topic) {
        if (topic.getName() != null) topics.put(topic.getName(), topic);
    }

    @Override
    public void clear() { topics.clear(); }

    @Override
    public void registerHelpTopicFactory(Class<?> commandClass, HelpTopicFactory<?> factory) {
        factories.put(commandClass, factory);
    }
}
