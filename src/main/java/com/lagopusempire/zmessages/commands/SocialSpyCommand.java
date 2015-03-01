package com.lagopusempire.zmessages.commands;

import com.lagopusempire.zmessages.MessageSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author MrZoraman
 */
public class SocialSpyCommand extends CommandBase
{
    public SocialSpyCommand(JavaPlugin plugin, MessageSystem messageSystem)
    {
        super(plugin, messageSystem);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}