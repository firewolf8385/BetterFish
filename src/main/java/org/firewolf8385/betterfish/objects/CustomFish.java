package org.firewolf8385.betterfish.objects;


import com.github.firewolf8385.customitemapi.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;
import org.firewolf8385.betterfish.ConfigManager;
import org.firewolf8385.betterfish.enums.Rarity;
import com.github.firewolf8385.customitemapi.objects.CustomItem;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a fish item not in vanilla Minecraft.
 */
public class CustomFish extends CustomItem {
    private static final ConfigManager config = ConfigManager.getInstance();
    private static Collection<CustomFish> allFish = new ArrayList<>();

    private final String name;
    private final int texture;
    private final Material material;
    private final Rarity rarity;
    private final Collection<Biome> biomes = new ArrayList<>();

    /**
     * Create a CustomFish object.
     * @param id id of the fish.
     */
    public CustomFish(String id) {
        super(id);

        String path = "Fish." + id;

        name = config.getFish().getString(path + ".Name");
        texture = config.getFish().getInt(path + ".Id");
        material = Material.valueOf(config.getFish().getString(path + ".Material"));
        rarity = Rarity.valueOf(config.getFish().getString(path + ".Rarity"));

        config.getFish().getStringList(path + ".Biomes").forEach(biome -> {
            biomes.add(Biome.valueOf(biome));
        });

        allFish.add(this);
    }

    /**
     * Get a CustomFish from it's id.
     * @param id Id of the custom fish.
     * @return The custom fish.
     */
    public static CustomFish fromId(String id) {
        CustomFish cf = null;

        for(CustomFish fish : getAllFish()) {
            if(fish.getId().equals(id)) {
                cf = fish;
            }
        }

        return cf;
    }


    /**
     * Get a Collection of all CustomFish.
     * @return All CustomFish.
     */
    public static Collection<CustomFish> getAllFish() {
        return allFish;
    }

    /**
     * Get the biomes the fish can be found in.
     * @return Biomes it can be found in.
     */
    public Collection<Biome> getBiomes() {
        return biomes;
    }

    /**
     * Get the custom fish item.
     * @return Custom fish item.
     */
    public ItemStack getItemStack() {
        ItemBuilder builder = new ItemBuilder(material)
                .setDisplayName(rarity.getColor() + name)
                .addLore(rarity.toString())
                .setCustomModelData(texture);

        return builder.build();
    }

    /**
     * Get the material of the fish.
     * @return Material.
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Get the name of the fish.
     * @return Name of the fish.
     */
    public String getName() {
        return name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    /**
     * Get the texture of the fish.
     * @return Texture of the fish.
     */
    public int getTexture() {
        return texture;
    }
}