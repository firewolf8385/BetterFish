package org.firewolf8385.betterfish.listeners;

import com.github.firewolf8385.customitemapi.CustomItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.firewolf8385.betterfish.objects.CustomRod;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PrepareAnvilListener implements Listener {
    @EventHandler
    public void onAnvil(PrepareAnvilEvent e) {
        ItemStack item = e.getResult();

        if(item == null) {
            return;
        }

        ItemMeta meta = item.getItemMeta();

        if(meta == null) {
            return;
        }

        Set<Enchantment> enchants = meta.getEnchants().keySet();

        NamespacedKey id = new NamespacedKey(Bukkit.getPluginManager().getPlugin("CustomItemAPI"), "id");
        PersistentDataContainer container = meta.getPersistentDataContainer();

        if(!container.has(id, PersistentDataType.STRING)) {
            return;
        }

        CustomRod rod = (CustomRod) CustomItemAPI.getCustomItems().get(container.get(id, PersistentDataType.STRING));
        if(rod == null) {
            return;
        }

        List<String> lore = new ArrayList<>(rod.getDefaultLore());

        for(Enchantment enchant : enchants) {
            String str = enchantmentToString(enchant);
            String level = IntegerToRomanNumeral(item.getEnchantmentLevel(enchant));

            if(str.equals("Mending")) {
                level = "";
            }

            lore.add(lore.size() - 2, ChatColor.GRAY + str + " " + level);
        }

        lore.add(meta.getLore().size() - 2, " ");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        e.setResult(item);
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
