package org.bukkit.help;

import org.bukkit.command.CommandSender;

public abstract class HelpTopic {
    protected String name;
    protected String shortText;
    protected String fullText;

    public String getName() { return name; }
    public String getShortText() { return shortText; }
    public String getFullText(CommandSender forPlayer) { return fullText != null ? fullText : shortText; }
    public boolean canSee(CommandSender player) { return true; }
    public void amendTopic(String amendedShortText, String amendedFullText) {
        if (amendedShortText != null) shortText = amendedShortText;
        if (amendedFullText != null) fullText = amendedFullText;
    }
    public void amendCanSee(boolean amendedCanSee) {}
}
