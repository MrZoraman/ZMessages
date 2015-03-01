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
        getConfig().options().copyDefaults(true);
        saveConfig();
        
        Utils.setLogger(getLogger());
        
        messages = new Messages(getConfig());
        messageSystem = new MessageSystem(this, messages);
        
        getCommand("msg").setExecutor(new MsgCommand(this, messageSystem, messages));
        getCommand("reply").setExecutor(new ReplyCommand(this, messageSystem, messages));
        getCommand("socialspy").setExecutor(new SocialSpyCommand(this, messageSystem, messages));
        getCommand("zmessages").setExecutor(new ZMessagesCommand(this, messageSystem, messages));
    }
}
