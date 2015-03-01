package com.lagopusempire.zmessages.commands;

import com.lagopusempire.zmessages.MessageSystem;
import com.lagopusempire.zmessages.Messages;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author MrZoraman
 */
public abstract class CommandBase implements CommandExecutor
{
    protected final JavaPlugin plugin;
    protected final MessageSystem messageSystem;
    protected final Messages messages;
    
    public CommandBase(JavaPlugin plugin, MessageSystem messageSystem, Messages messages)
    {
        this.plugin = plugin;
        this.messageSystem = messageSystem;
        this.messages = messages;
    }
}
