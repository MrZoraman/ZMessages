package com.lagopusempire.zmessages;

import com.lagopusempire.zmessages.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author MrZoraman
 */
public class ZMessages extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        getCommand("msg").setExecutor(new MsgCommand(this));
        getCommand("reply").setExecutor(new ReplyCommand(this));
        getCommand("socialspy").setExecutor(new SocialSpyCommand(this));
        getCommand("zmessages").setExecutor(new ZMessagesCommand(this));
    }
}
