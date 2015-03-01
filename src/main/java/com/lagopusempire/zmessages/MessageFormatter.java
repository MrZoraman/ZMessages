package com.lagopusempire.zmessages;

import org.bukkit.ChatColor;

/**
 *
 * @author MrZoraman
 */
public class MessageFormatter
{
    private final char VARIABLE_DECORATOR = '%';
    
    private String contents;
    
    private MessageFormatter(String contents)
    {
        this.contents = contents;
    }
    
    public MessageFormatter create(String contents)
    {
        return new MessageFormatter(contents);
    }
    
    public MessageFormatter stripColors()
    {
        contents = ChatColor.stripColor(contents);
        return this;
    }
    
    public MessageFormatter colorize()
    {
        contents = ChatColor.translateAlternateColorCodes('&', contents);
        return this;
    }
    
    public MessageFormatter replace(String variable, String replacement)
    {
        String toReplace = (new StringBuilder(variable.length() + 2))
                .append(VARIABLE_DECORATOR)
                .append(variable)
                .append(VARIABLE_DECORATOR)
                .toString();
        contents = contents.replaceAll(toReplace, replacement);
        return this;
    }
    
    @Override
    public String toString()
    {
        return contents;
    }
}
