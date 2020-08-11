package org.firewolf8385.betterfish.objects;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public abstract class CustomItem {
    private String id;
    private static HashMap<String, CustomItem> allItems= new HashMap<>();

    public CustomItem(String id) {
        this.id = id;
        allItems.put(id, this);
    }

    public static HashMap<String, CustomItem> getAllItems() {
        return allItems;
    }

    public abstract ItemStack getItem();
}