package org.firewolf8385.betterfish.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BetterFishCMD implements CommandExecutor {

    public BetterFishCMD() { }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            //TODO: Add GUI.
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "info":
                //TODO: Add info page
                break;
        }
        return true;
    }
}