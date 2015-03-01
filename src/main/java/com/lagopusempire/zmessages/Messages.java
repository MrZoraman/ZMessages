package com.lagopusempire.zmessages;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 * @author MrZoraman
 */
public class Messages
{
    private final Map<String, String> messages = new HashMap<>();
    
    public Messages(FileConfiguration config)
    {
        messages.put("format_sender", config.getString("messages.format.sender"));
        messages.put("format_reciever", config.getString("messages.format.reciever"));
        messages.put("format_socialspy", config.getString("messages.format.socialspy"));
        messages.put("not_online_reply", config.getString("messages.notFound.reply"));
    }
    
    public String get(String key)
    {
        return messages.get(key);
    }
}
