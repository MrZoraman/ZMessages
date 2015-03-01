package com.lagopusempire.zmessages;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author MrZoraman
 */
public class MessageSystem
{
    private final String format_sender, format_reciever, format_socialspy;
    private final Set<UUID> socialSpies = new HashSet<>();
    private final JavaPlugin plugin;
    
    public MessageSystem(JavaPlugin plugin)
    {
        format_sender = plugin.getConfig().getString("format.sender");
        format_reciever = plugin.getConfig().getString("format.reciever");
        format_socialspy = plugin.getConfig().getString("format.socialspy");
        
        this.plugin = plugin;
    }
    
    public void sendMessage(Player from, Player to, String message)
    {
        MessageFormatter messageForSender = format(MessageFormatter.create(format_sender), from, to, message);
        MessageFormatter messageForReciever = format(MessageFormatter.create(format_reciever), from, to, message);
        MessageFormatter messageForSocialSpy = format(MessageFormatter.create(format_socialspy), from, to, message);
        
        from.sendMessage(messageForSender.toString());
        to.sendMessage(messageForReciever.toString());
        
        broadcastToSocialSpies(messageForSocialSpy);
    }
    
    private void broadcastToSocialSpies(MessageFormatter formatter)
    {
        plugin.getLogger().info(formatter.stripColors().toString());
        
        String message = formatter.toString();
        Iterator<UUID> it = socialSpies.iterator();
        while(it.hasNext())
        {
            UUID uuid = it.next();
            Player player = Bukkit.getPlayer(uuid);
            if(player == null)
            {
                it.remove();
                continue;
            }
            
            player.sendMessage(message);
        }
    }
    
    private MessageFormatter format(MessageFormatter formatter, Player from, Player to, String message)
    {
        return formatter
                .replace("sender", from.getName())
                .replace("reciever", to.getName())
                .replace("message", message)
                .colorize();
    }
    
    public void reply(Player from)
    {
    }
}
