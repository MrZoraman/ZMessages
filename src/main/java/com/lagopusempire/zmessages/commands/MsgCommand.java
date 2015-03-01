package com.lagopusempire.zmessages.commands;

import com.lagopusempire.zmessages.MessageFormatter;
import com.lagopusempire.zmessages.MessageSystem;
import com.lagopusempire.zmessages.Messages;
import com.lagopusempire.zmessages.Utils;
import java.util.Arrays;
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
        if(args.length < 1)
        {
            Utils.sendMessage(sender, MessageFormatter.create(messages.get("error.noTarget")));
            return false;
        }
        
        if(args.length < 2)
        {
            Utils.sendMessage(sender, MessageFormatter.create(messages.get("error.noMessage")));
            return false;
        }
        
        CommandSender target = Utils.matchCommandSender(args[0]);
        if(target == null)
        {
            Utils.sendMessage(sender, MessageFormatter.create(messages.get("notFound.general")));
            return true;
        }
        
        String message = Utils.toMessage(Arrays.copyOfRange(args, 1, args.length));
        
        messageSystem.sendMessage(sender, target, message);
        return true;
    }
}
