package com.lagopusempire.zmessages.commands;

import com.lagopusempire.zmessages.MessageSystem;
import com.lagopusempire.zmessages.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author MrZoraman
 */
public class MsgCommand extends CommandBase
{
    public MsgCommand(JavaPlugin plugin, MessageSystem messageSystem, Messages messages)
    {
        super(plugin, messageSystem, messages);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
