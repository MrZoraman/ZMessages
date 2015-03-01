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
    
    @Override
    public void onEnable()
    {
        getConfig().options().copyDefaults(true);
        saveConfig();
        
        messageSystem = new MessageSystem(this);
        
        getCommand("msg").setExecutor(new MsgCommand(this, messageSystem));
        getCommand("reply").setExecutor(new ReplyCommand(this, messageSystem));
        getCommand("socialspy").setExecutor(new SocialSpyCommand(this, messageSystem));
        getCommand("zmessages").setExecutor(new ZMessagesCommand(this, messageSystem));
    }
}
