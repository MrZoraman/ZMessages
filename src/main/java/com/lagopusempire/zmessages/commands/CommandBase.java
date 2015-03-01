package com.lagopusempire.zmessages.commands;

import com.lagopusempire.zmessages.MessageSystem;
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
    
    public CommandBase(JavaPlugin plugin, MessageSystem messageSystem)
    {
        this.plugin = plugin;
        this.messageSystem = messageSystem;
    }
}
