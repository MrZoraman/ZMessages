package com.lagopusempire.zmessages.commands;

import com.lagopusempire.zmessages.MessageFormatter;
import com.lagopusempire.zmessages.MessageSystem;
import com.lagopusempire.zmessages.Messages;
import com.lagopusempire.zmessages.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author MrZoraman
 */
public class SocialSpyCommand extends CommandBase
{
    public SocialSpyCommand(JavaPlugin plugin, MessageSystem messageSystem, Messages messages)
    {
        super(messageSystem, messages);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args)
    {
        if(Utils.isConsole(sender))
        {
            sender.sendMessage("The console changes its social spy status in the config! Don't forget to reload with /zmessages!");
            return true;
        }
        
        Player p = (Player) sender;
        
        boolean result = messageSystem.toggleSocialSpy(p.getUniqueId());
        
        System.out.println(messages.get("socialspy." + (result ? "activated" : "deactivated")));
        Utils.sendMessage(sender, MessageFormatter.create(messages.get("socialspy." + (result ? "activated" : "deactivated"))).colorize());
        
        return true;
    }
}