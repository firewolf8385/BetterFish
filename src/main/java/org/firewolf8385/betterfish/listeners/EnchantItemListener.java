package org.firewolf8385.betterfish.listeners;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.firewolf8385.betterfish.BetterFish;
import org.firewolf8385.betterfish.objects.CustomRod;

import java.util.ArrayList;
import java.util.List;

public class EnchantItemListener implements Listener {
    @EventHandler
    public void onEnchant(EnchantItemEvent e) {
        ItemStack item = e.getItem();
        ItemMeta meta = item.getItemMeta();

        if(meta == null) {
            return;
        }

        NamespacedKey id = new NamespacedKey(Bukkit.getPluginManager().getPlugin("CustomItemAPI"), "id");
        PersistentDataContainer container = meta.getPersistentDataContainer();

        if(!container.has(id, PersistentDataType.STRING)) {
            Bukkit.broadcastMessage("Missing ID");
            return;
        }

        CustomRod rod = (CustomRod) CustomItemAPI.getCustomItems().get(container.get(id, PersistentDataType.STRING));

        if(rod == null) {
            return;
        }

        List<String> lore = new ArrayList<>(rod.getDefaultLore());

        for(Enchantment enchant : e.getEnchantsToAdd().keySet()) {
            String str = enchantmentToString(enchant);
            String level = IntegerToRomanNumeral(e.getEnchantsToAdd().get(enchant));

            if(str.equals("Mending")) {
                level = "";
            }

            lore.add(lore.size() - 2, ChatColor.GRAY + str + " " + level);
        }

        lore.add(meta.getLore().size() - 2, " ");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
    }

    private static String enchantmentToString(Enchantment e) {
        switch(e.getName()) {
            case "MENDING":
                return "Mending";
            case "DURABILITY":
                return "Unbreaking";
            case "LUCK":
                return "Luck of the Sea";
            case "LURE":
                return "Lure";
        }
        return " ";
    }

    private static String IntegerToRomanNumeral(int input) {
        if (input < 1 || input > 3999)
            return "Invalid Roman Number Value";
        String s = "";
        while (input >= 1000) {
            s += "M";
            input -= 1000;        }
        while (input >= 900) {
            s += "CM";
            input -= 900;
        }
        while (input >= 500) {
            s += "D";
            input -= 500;
        }
        while (input >= 400) {
            s += "CD";
            input -= 400;
        }
        while (input >= 100) {
            s += "C";
            input -= 100;
        }
        while (input >= 90) {
            s += "XC";
            input -= 90;
        }
        while (input >= 50) {
            s += "L";
            input -= 50;
        }
        while (input >= 40) {
            s += "XL";
            input -= 40;
        }
        while (input >= 10) {
            s += "X";
            input -= 10;
        }
        while (input >= 9) {
            s += "IX";
            input -= 9;
        }
        while (input >= 5) {
            s += "V";
            input -= 5;
        }
        while (input >= 4) {
            s += "IV";
            input -= 4;
        }
        while (input >= 1) {
            s += "I";
            input -= 1;
        }
        return s;
    }
}