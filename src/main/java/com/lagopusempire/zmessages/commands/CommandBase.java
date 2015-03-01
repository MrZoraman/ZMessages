package com.lagopusempire.zmessages.commands;

import com.lagopusempire.zmessages.MessageSystem;
import com.lagopusempire.zmessages.Messages;
import org.bukkit.command.CommandExecutor;

/**
 *
 * @author MrZoraman
 */
public abstract class CommandBase implements CommandExecutor
{
    protected final MessageSystem messageSystem;
    protected final Messages messages;
    
    public CommandBase(MessageSystem messageSystem, Messages messages)
    {
        this.messageSystem = messageSystem;
        this.messages = messages;
    }
}
