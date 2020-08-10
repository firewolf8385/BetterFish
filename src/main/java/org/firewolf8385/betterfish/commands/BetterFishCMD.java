package org.firewolf8385.betterfish.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.firewolf8385.betterfish.commands.subcommands.GetItemCMD;

import java.util.Arrays;

public class BetterFishCMD implements CommandExecutor {
    private GetItemCMD getItemCMD;

    public BetterFishCMD() {
        getItemCMD = new GetItemCMD();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            //TODO: Add GUI.
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "getitem":
                getItemCMD.onCommand(sender, cmd, label, Arrays.copyOfRange(args, 1, args.length));
                break;
        }
        return true;
    }
}
