package org.firewolf8385.betterfish.objects;

import org.bukkit.DyeColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TropicalFish;
import org.firewolf8385.betterfish.ConfigManager;

/**
 * Represents a CustomFish that also has a mob form.
 */
public class LivingFish extends CustomFish {
    private static final ConfigManager config = ConfigManager.getInstance();

    private final EntityType type;
    private final DyeColor bodyColor;
    private final DyeColor patternColor;
    private final TropicalFish.Pattern pattern;

    /**
     * Creates a LivingFish.
     * @param id id of the fish.
     */
    public LivingFish(String id) {
        super(id);

        String path = "Fish." + id + ".Entity";
        type = EntityType.valueOf(config.getFish().getString(path + ".Type"));

        if(type == EntityType.TROPICAL_FISH) {
            pattern = TropicalFish.Pattern.valueOf(config.getFish().getString(path + ".Pattern"));
            patternColor = DyeColor.valueOf(config.getFish().getString(path + ".PatternColor"));
            bodyColor = DyeColor.valueOf(config.getFish().getString(path + ".BodyColor"));
        }
        else {
            pattern = null;
            patternColor = null;
            bodyColor = null;
        }
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
