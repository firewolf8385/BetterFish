package org.firewolf8385.betterfish.enums;

import net.md_5.bungee.api.ChatColor;
import org.firewolf8385.betterfish.ConfigManager;

/**
 * Represents the rarity of items and
 * fish gained by fishing.
 */
public enum Rarity {
    JUNK("Junk"),
    COMMON("Common"),
    UNCOMMON("Uncommon"),
    RARE("Rare"),
    EPIC("Epic"),
    LEGENDARY("Legendary"),
    TREASURE("Treasure");

    private static final ConfigManager config = ConfigManager.getInstance();

    private String path;

    /**
     * Creates a Rarity Enum.
     * @param path Path in the config.
     */
    Rarity(String path) {
        this.path = path;
    }

    /**
     * Get the chance of the rarity being picked.
     * @return Chance of rarity being picked.
     */
    public int getChance() {
        return config.getConfig().getInt(path + ".Chance");
    }

    /**
     * Get the color of the rarity.
     * @return ChatColor of the rarity.
     */
    public ChatColor getColor() {
        return ChatColor.valueOf(config.getConfig().getString(path + ".Color"));
    }

    /**
     * Get the experience given by the rarity.
     * @return Experience.
     */
    public int getExperience() {
        return  config.getConfig().getInt(path + ".Experience");
    }

    /**
     * Convert the Rarity to a string.
     * @return The rarity in String form.
     */
    public String toString() {
        return config.getConfig().getString(path + ".DisplayText");
    }
}
