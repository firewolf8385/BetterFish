package org.firewolf8385.betterfish.commands.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.firewolf8385.betterfish.objects.CustomFish;
import org.firewolf8385.betterfish.objects.CustomItem;
import org.firewolf8385.betterfish.objects.CustomRod;
import org.firewolf8385.betterfish.utils.ChatUtils;

public class GetItemCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!sender.hasPermission("betterfish.admin")) {
            ChatUtils.chat(sender, "&c&l(&7!&c&l) &cYou do not have access to that command!");
            return true;
        }

        if(!(sender instanceof Player)) {
            ChatUtils.chat(sender, "&c&l(&7!&c&l) &cOnly players can use that command!");
            return true;
        }

        if(args.length == 0) {
            ChatUtils.chat(sender, "&c&l(&7&l!&c&l) &cYou must enter a custom item.");
            return true;
        }

        CustomItem item = CustomItem.getAllItems().get(args[0]);

        if(item == null) {
            ChatUtils.chat(sender, "&c&l(&7&l!&c&l) &cThat item does not exist.");
            return true;
        }

        Player p = (Player) sender;
        p.getInventory().addItem(item.getItem());
        ChatUtils.chat(p, "&a&l(&7&l!&a&l) &aItem has been given.");

        return true;
    }
}