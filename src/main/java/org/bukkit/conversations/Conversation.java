package org.bukkit.conversations;

public class Conversation {
    public void abandon() {}
    public void outputNextPrompt() {}
    public boolean begin() { return true; }
    public void acceptInput(String input) {}
}
