package org.bukkit.help;

import java.util.Collection;

public interface HelpMap {
    HelpTopic getHelpTopic(String topicName);
    Collection<HelpTopic> getHelpTopics();
    void addTopic(HelpTopic topic);
    void clear();
    void registerHelpTopicFactory(Class<?> commandClass, HelpTopicFactory<?> factory);
}
