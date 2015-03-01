package com.lagopusempire.zmessages;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 *
 * @author MrZoraman
 */
public class MessageSystem
{
    private final String format_sender, format_reciever, format_socialspy;
    
    public MessageSystem(FileConfiguration config)
    {
        format_sender = config.getString("format.sender");
        format_reciever = config.getString("format.reciever");
        format_socialspy = config.getString("format.socialspy");
    }
    
    public void sendMessage(Player from, Player to, String message)
    {
        
    }
    
    public void reply(Player from)
    {
    }
}
