package com.lagopusempire.zmessages.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author MrZoraman
 */
public abstract class CommandBase implements CommandExecutor
{
    protected final JavaPlugin plugin;
    
    public CommandBase(JavaPlugin plugin)
    {
        this.plugin = plugin;
    }
}
