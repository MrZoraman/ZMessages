package com.lagopusempire.zmessages;

import com.lagopusempire.zmessages.commands.*;
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
        
        reload();
        
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
        
        if(messages == null)
        {
            messages = new Messages(getConfig());
        }
        else
        {
            messages.reload(getConfig());
        }
        
        if(messageSystem == null)
        {
            messageSystem = new MessageSystem(getConfig(), messages);
        }
        else
        {
            messageSystem.reload(getConfig());
        }
    }
}
