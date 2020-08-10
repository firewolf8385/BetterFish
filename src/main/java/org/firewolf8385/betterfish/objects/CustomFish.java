package org.firewolf8385.betterfish.objects;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;
import org.firewolf8385.betterfish.ConfigManager;
import org.firewolf8385.betterfish.enums.Rarity;
import org.firewolf8385.betterfish.utils.ItemBuilder;

import java.util.ArrayList;
import java.util.Collection;

public class CustomFish {
    private static final ConfigManager config = ConfigManager.getInstance();
    private static Collection<CustomFish> allFish = new ArrayList<>();

    private String name;
    private int texture;
    private String id;
    private Material material;
    private Rarity rarity;
    private Collection<Biome> biomes = new ArrayList<>();

    /**
     * Create a CustomFish object.
     * @param id id of the fish.
     */
    public CustomFish(String id) {
        this.id = id;

        String path = "Fish." + id;

        name = config.getFish().getString(path + "Name");
        texture = config.getFish().getInt(path + "Texture");
        material = Material.valueOf(config.getFish().getString(path + ".Material"));
        rarity = Rarity.valueOf(config.getFish().getString(path = ".Rarity"));

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
     * Get the id of the fish.
     * @return Id of the fish.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the custom fish item.
     * @return Custom fish item.
     */
    public ItemStack getItem() {
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

    /**
     * Get the texture of the fish.
     * @return Texture of the fish.
     */
    public int getTexture() {
        return texture;
    }
}
