package org.firewolf8385.betterfish.objects;

import com.github.firewolf8385.customitemapi.utils.ChatUtils;
import com.github.firewolf8385.customitemapi.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.firewolf8385.betterfish.BetterFish;
import org.firewolf8385.betterfish.ConfigManager;
import org.firewolf8385.betterfish.enums.Rarity;
import com.github.firewolf8385.customitemapi.objects.CustomItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents a Fishing Rod not in vanilla Minecraft.
 */
public class CustomRod extends CustomItem {
    private static final ConfigManager config = ConfigManager.getInstance();
    private static Collection<CustomRod> allRods = new ArrayList<>();

    private final String name;
    private final int texture;
    private final int power;
    private final Rarity rarity;
    private final ShapedRecipe recipe;
    private final int durability;

    /**
     * Creates a Custom Fishing Rod
     * @param id Item id.
     */
    public CustomRod(String id) {
        super(id);

        String path = "Rods." + id;

        name = config.getRods().getString(path + ".Name");
        texture = config.getRods().getInt(path + ".Texture");
        power = config.getRods().getInt(path + ".Power");
        rarity = Rarity.valueOf(config.getRods().getString(path + ".Rarity"));
        durability = config.getRods().getInt(path + ".Durability");

        List<String> row1 = config.getRods().getStringList(path + ".Recipe.Row1");
        List<String> row2 = config.getRods().getStringList(path + ".Recipe.Row2");
        List<String> row3 = config.getRods().getStringList(path + ".Recipe.Row3");

        NamespacedKey key = new NamespacedKey(BetterFish.getPlugin(), id);
        recipe = new ShapedRecipe(key, getItemStack());
        recipe.shape("ABC", "DEF", "GHI");
        recipe.setIngredient('A', Material.valueOf(row1.get(0)));
        recipe.setIngredient('B', Material.valueOf(row1.get(1)));
        recipe.setIngredient('C', Material.valueOf(row1.get(2)));
        recipe.setIngredient('D', Material.valueOf(row2.get(0)));
        recipe.setIngredient('E', Material.valueOf(row2.get(1)));
        recipe.setIngredient('F', Material.valueOf(row2.get(2)));
        recipe.setIngredient('G', Material.valueOf(row3.get(0)));
        recipe.setIngredient('H', Material.valueOf(row3.get(1)));
        recipe.setIngredient('I', Material.valueOf(row3.get(2)));
        Bukkit.addRecipe(recipe);

        allRods.add(this);
    }

    /**
     * Get a collection of all CustomRods.
     * @return Collection of CustomRod.
     */
    public static Collection<CustomRod> getAllRods() {
        return allRods;
    }

    /**
     * Get the ItemStack of the fishing rod.
     * @return ItemStack of the fishing rod.
     */
    public ItemStack getItemStack() {
        ItemBuilder builder = new ItemBuilder(Material.FISHING_ROD)
                .setCustomModelData(texture)
                .setDisplayName(rarity.getColor() + name)
                .setCustomDurability(durability)
                .addLore(getDefaultLore())
                .setPersistentData("id", getId());
        return builder.build();
    }

    /**
     * Get the power of the fishing rod.
     * @return Power of the fishing rod.
     */
    public int getPower() {
        return power;
    }

    /**
     * Get the rarity of the fishing rod.
     * @return Rarity of the fishing rod.
     */
    public Rarity getRarity() {
        return rarity;
    }

    public List<String> getDefaultLore() {
        List<String> lore = new ArrayList<>();
        lore.add(ChatUtils.translate("&8Fishing Power: " + power));
        lore.add(" ");
        lore.add(ChatUtils.translate(rarity.toString()));

        return lore;
    }
}