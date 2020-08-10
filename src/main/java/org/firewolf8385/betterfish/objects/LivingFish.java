package org.firewolf8385.betterfish.objects;

import org.bukkit.DyeColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TropicalFish;

/**
 * Represents a CustomFish that also have a mob form.
 */
public class LivingFish extends CustomFish {
    private EntityType type;
    private DyeColor bodyColor;
    private DyeColor patternColor;
    private TropicalFish.Pattern pattern;

    /**
     * Creates a LivingFish.
     * @param id id of the fish.
     */
    public LivingFish(String id) {
        super(id);
    }

    /**
     * Get the body color of the fish.
     * @return Body color of the fish.
     */
    public DyeColor getBodyColor() {
        return bodyColor;
    }

    /**
     * Get the pattern of the fish.
     * @return Pattern of the fish.
     */
    public TropicalFish.Pattern getPattern() {
        return pattern;
    }

    /**
     * Get the pattern color of the fish.
     * @return Pattern color of the fish.
     */
    public DyeColor getPatternColor() {
        return patternColor;
    }

    /**
     * Get the type of entity the fish is.
     * @return Entity type of the fish.
     */
    public EntityType getType() {
        return type;
    }
}
