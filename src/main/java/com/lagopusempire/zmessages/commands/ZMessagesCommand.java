package com.lagopusempire.zmessages.commands;

import com.lagopusempire.zmessages.MessageFormatter;
import com.lagopusempire.zmessages.MessageSystem;
import com.lagopusempire.zmessages.Messages;
import com.lagopusempire.zmessages.Utils;
import com.lagopusempire.zmessages.ZMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author MrZoraman
 */
public class ZMessagesCommand implements CommandExecutor
{
    private final ZMessages plugin;
    private final Messages messages;
    
    public ZMessagesCommand(ZMessages plugin, Messages messages)
    {
        this.plugin = plugin;
        this.messages = messages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args)
    {
        plugin.reload();
        
        MessageFormatter formatter = MessageFormatter.create(messages.get("reload")).colorize(); 
        Utils.sendMessage(sender, formatter);
        
        if(!Utils.isConsole(sender))
        {
            plugin.getLogger().info(formatter.stripColors().toString());
        }
        
        return true;
    }
}