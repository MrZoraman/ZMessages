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
public class ReplyCommand extends CommandBase
{
    public ReplyCommand(MessageSystem messageSystem, Messages messages)
    {
        super(messageSystem, messages);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args)
    {
        if(args.length < 1)
        {
            Utils.sendMessage(sender, MessageFormatter.create(messages.get("error.noMessage")).colorize());
            return false;
        }
        
        String message = Utils.toMessage(Arrays.copyOfRange(args, 0, args.length));
        
        messageSystem.reply(sender, message);
        
        return true;
    }
}