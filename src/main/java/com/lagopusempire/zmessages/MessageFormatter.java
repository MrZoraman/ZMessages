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
        char[] chars = contents.toCharArray();
        boolean inDecorators = false;
        
        for(int ii = 0; ii < chars.length; ii++)
        {
            if(chars[ii] == VARIABLE_DECORATOR)
            {
                inDecorators = !inDecorators;
                continue;
            }
            
            if(inDecorators)
            {
                chars[ii] = Character.toLowerCase(chars[ii]);
            }
        }
        
        this.contents = String.valueOf(chars);
    }
    
    public static MessageFormatter create(String contents)
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
                .append(variable.toLowerCase())
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
