package org.firewolf8385.betterfish.enums;

import org.firewolf8385.betterfish.ConfigManager;

/**
 * Represents the rarity of items and
 * fish gained by fishing.
 */
public enum Rarity {
    JUNK,
    COMMON,
    UNCOMMON,
    RARE,
    EPIC,
    LEGENDARY,
    TREASURE;

    private static final ConfigManager config = ConfigManager.getInstance();

    private String str;
    private double chance;
    private int experience;

    Rarity() {

    }

    /**
     * Convert the Rarity to a string.
     * @return
     */
    public String toString() {
        return str;
    }
}
