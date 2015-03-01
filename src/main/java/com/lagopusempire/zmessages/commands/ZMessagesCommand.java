package com.lagopusempire.zmessages.commands;

import com.lagopusempire.zmessages.MessageSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author MrZoraman
 */
public class ZMessagesCommand extends CommandBase
{
    public ZMessagesCommand(JavaPlugin plugin, MessageSystem messageSystem)
    {
        super(plugin, messageSystem);
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}