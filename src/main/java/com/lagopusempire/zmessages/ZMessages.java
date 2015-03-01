package com.lagopusempire.zmessages;

import com.lagopusempire.zmessages.commands.*;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author MrZoraman
 */
public class ZMessages extends JavaPlugin
{
    private MessageSystem messageSystem;
    private Messages messages;
    
    @Override
    public void onEnable()
    {
        Utils.setLogger(getLogger());
        
        getConfig().options().copyDefaults(true);
        saveConfig();
        
        messages = new Messages(getConfig());
        messageSystem = new MessageSystem(messages);
        
        registerEvents();
        
        getCommand("msg").setExecutor(new MsgCommand(this, messageSystem, messages));
        getCommand("reply").setExecutor(new ReplyCommand(this, messageSystem, messages));
        getCommand("socialspy").setExecutor(new SocialSpyCommand(this, messageSystem, messages));
        
        getCommand("zmessages").setExecutor(new ZMessagesCommand(this, messages));
    }
    
    public void reload()
    {
        reloadConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
        
        messages.reload(getConfig());
        
        HandlerList.unregisterAll(messageSystem);
        registerEvents();
    }
    
    private void registerEvents()
    {
        if(getConfig().getBoolean("Remove_social_spy_on_logout"))
        {
            getServer().getPluginManager().registerEvents(messageSystem, this);
        }
    }
}
