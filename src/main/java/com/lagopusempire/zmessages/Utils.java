package com.lagopusempire.zmessages;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author MrZoraman
 */
public class Utils
{
    private Utils()
    {
    }
    
    private static Logger logger;
    
    public static void setLogger(Logger logger)
    {
        Utils.logger = logger;
    }
    
    public static String toMessage(String[] array)
    {
        StringBuilder builder = new StringBuilder();
        for(int ii = 0; ii < array.length; ii++)
        {
            builder.append(array[ii]).append(" ");
        }
        
        builder.setLength(builder.length() - 1);
        return builder.toString();
    }
    
    public static String getName(CommandSender sender)
    {
        if(isConsole(sender))
        {
            return "Console";
        }
        else
        {
            return ((Player) sender).getName();
        }
    }
    
    public static boolean isConsole(CommandSender sender)
    {
        if(sender == null) return true;
        return sender instanceof Player == false;
    }
    
    public static UUID toUUID(CommandSender sender)
    {
        if(isConsole(sender))
            return null;
        else
            return ((Player) sender).getUniqueId();
    }
    
    public static CommandSender matchCommandSender(String name)
    {
        name = name.toLowerCase();
        if(name.equals("console") || name.equals("terminal") || name.equals("server"))
        {
            return Bukkit.getConsoleSender();
        }
        
        Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
        Iterator<? extends Player> it = onlinePlayers.iterator();
        while(it.hasNext())
        {
            Player p = it.next();
            String playerName = p.getName().toLowerCase();
            if(playerName.equals(name))
            {
                return p;
            }
        }
        
        return null;
    }
    
    public static void sendMessage(CommandSender sender, MessageFormatter formatter)
    {
        if(isConsole(sender))
        {
            logger.info(formatter.stripColors().toString());
        }
        else
        {
            sender.sendMessage(formatter.toString());
        }
    }
}
