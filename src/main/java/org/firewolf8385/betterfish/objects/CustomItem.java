package org.firewolf8385.betterfish.objects;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * Represents an item not in vanilla Minecraft.
 */
public abstract class CustomItem {
    private String id;
    private static HashMap<String, CustomItem> allItems= new HashMap<>();

    /**
     * Creates a CustomItem.
     * @param id
     */
    public CustomItem(String id) {
        this.id = id;
        allItems.put(id, this);
    }

    /**
     * Gets a map of all Custom Items.
     * @return Custom Items.
     */
    public static HashMap<String, CustomItem> getAllItems() {
        return allItems;
    }

    public abstract ItemStack getItem();
}