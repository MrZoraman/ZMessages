package com.lagopusempire.zmessages;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
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
    private final Map<UUID, UUID> latestSenders = new HashMap<>();
               //recipient, the one who last messaged the recipient
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
        
        latestSenders.put(to.getUniqueId(), from.getUniqueId());
    }
    
    public void reply(Player from, String message)
    {
        Player to = getLatestSender(from.getUniqueId());
        sendMessage(from, to, message);
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
    
    /**
     * Toggles the socialspy status of a player
     * @param uuid The player's uuid
     * @return True if the player's state is switched to being in social spy mode, false if the player is no longer a social spy
     */
    public boolean toggleSocialSpy(UUID uuid)
    {
        if(socialSpies.contains(uuid))
        {
            socialSpies.remove(uuid);
            return false;
        }
        else
        {
            socialSpies.add(uuid);
            return true;
        }
    }
    
    /**
     * Gets the latest recipient that sent a player a message
     * @param recipient The recipient
     * @return The player who last sent a message to the recipient, or null if there is none or the player is no longer online
     */
    private Player getLatestSender(UUID recipient)
    {
        UUID theOneWhoLastSentToRecipient = latestSenders.get(recipient);
        
        if(theOneWhoLastSentToRecipient == null) return null;
        
        return Bukkit.getPlayer(theOneWhoLastSentToRecipient);
    }
}
