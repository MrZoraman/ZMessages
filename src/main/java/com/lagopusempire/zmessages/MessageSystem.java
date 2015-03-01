package com.lagopusempire.zmessages;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @author MrZoraman
 */
public class MessageSystem implements Listener
{
    private final Messages messages;
    private final Set<UUID> socialSpies = new HashSet<>();
    private final Map<UUID, UUID> latestSenders = new HashMap<>();
               //recipient, the one who last messaged the recipient
    private boolean consoleIsSocialSpy;
    
    public MessageSystem(FileConfiguration config, Messages messages)
    {
        reload(config);//initializes consoleIsSocialSpy
        this.messages = messages;
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        socialSpies.remove(event.getPlayer().getUniqueId());
    }
    
    public void sendMessage(CommandSender from, CommandSender to, String message)
    {
        if(from == null) throw new IllegalArgumentException("From player cannot be null!");
        
        if(from == to)
        {
            Utils.sendMessage(from, MessageFormatter.create(messages.get("error.sentToSelf")).colorize());
            return;
        }
        
        MessageFormatter messageForSender = format(MessageFormatter.create(messages.get("format.sender")), from, to, message);
        MessageFormatter messageForReceiver = format(MessageFormatter.create(messages.get("format.receiver")), from, to, message);
        MessageFormatter messageForSocialSpy = format(MessageFormatter.create(messages.get("socialspy.prefix") + messages.get("format.socialspy")), from, to, message);
        
        Utils.sendMessage(from, messageForSender);
        Utils.sendMessage(to, messageForReceiver);
        
        broadcastToSocialSpies(Utils.toUUID(to), Utils.toUUID(from), messageForSocialSpy);
        
        latestSenders.put(Utils.toUUID(to), Utils.toUUID(from));
    }
    
    public void reply(CommandSender from, String message)
    {
        if(from == null) throw new IllegalArgumentException("From player cannot be null!");
        
        CommandSender to = getLatestSender(Utils.toUUID(from));
        
        if(to == null)
        {
            MessageFormatter notOnlineMessage = MessageFormatter.create(messages.get("notFound.reply")).colorize();
            Utils.sendMessage(from, notOnlineMessage);
        }
        else
        {
            sendMessage(from, to, message);
        }
    }
    
    private void broadcastToSocialSpies(UUID from, UUID to, MessageFormatter formatter)
    {
        if(consoleIsSocialSpy && !(from == null || to == null))
        {
            Utils.sendMessage(null, formatter);
        }
        
        Iterator<UUID> it = socialSpies.iterator();
        while(it.hasNext())
        {
            UUID uuid = it.next();
            Player player = Bukkit.getPlayer(uuid);
            if(player == null)
            {
                continue;
            }
            
            if(!(uuid.equals(from) || uuid.equals(to)))
            {
                Utils.sendMessage(player, formatter);
            }
        }
    }
    
    private MessageFormatter format(MessageFormatter formatter, CommandSender from, CommandSender to, String message)
    {
        return formatter
                .replace("sender", Utils.getName(from))
                .replace("receiver", Utils.getName(to))
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
    private CommandSender getLatestSender(UUID recipient)
    {
        if(!latestSenders.containsKey(recipient))
        {
            return null;
        }
        
        UUID theOneWhoLastSentToRecipient = latestSenders.get(recipient);
            
        if(theOneWhoLastSentToRecipient == null) 
        {
            return Bukkit.getConsoleSender();
        }
        
        return Bukkit.getPlayer(theOneWhoLastSentToRecipient);
    }
    
    public final void reload(FileConfiguration config)
    {
        consoleIsSocialSpy = config.getBoolean("Console_is_socialspy");
    }
}
