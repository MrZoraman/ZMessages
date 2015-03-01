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
        addMessage(config, "format.sender");
        addMessage(config, "format.reciever");
        addMessage(config, "format.socialspy");
        addMessage(config, "notFound.reply");
        addMessage(config, "notFound.general");
        addMessage(config, "error.noTarget");
    }
    
    private void addMessage(FileConfiguration config, String messagePath)
    {
        messages.put(messagePath, config.getString("messages." + messagePath));
    }
    
    public String get(String key)
    {
        return messages.get(key);
    }
}
