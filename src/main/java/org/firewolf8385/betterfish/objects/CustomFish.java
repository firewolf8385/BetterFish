package org.firewolf8385.betterfish.objects;

import org.bukkit.Material;

public class CustomFish {
    private String name;
    private int texture;
    private String id;
    private Material material;

    public CustomFish(String id) {
        this.id = id;
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
     * @return
     */
    public int getTexture() {
        return texture;
    }
}
