package org.firewolf8385.betterfish;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

/**
 * Represents the access point for all configuration files.
 * Uses a singleton pattern.
 */
public class ConfigManager {
    FileConfiguration config;
    FileConfiguration fish;
    FileConfiguration rods;
    FileConfiguration treasure;
    File configFile;
    File fishFile;
    File rodsFile;
    File treasureFile;
    static ConfigManager instance = new ConfigManager();

    private ConfigManager(){}

    /**
     * Retrieve an instance of the ConfigManager
     * @return instance of ConfigManager
     */
    public static ConfigManager getInstance() {
        return instance;
    }

    /**
     *
     * @param pl Plugin
     */
    public void setup(Plugin pl) {
        config = pl.getConfig();
        config.options().copyDefaults(true);
        configFile = new File(pl.getDataFolder(), "config.yml");
        pl.saveConfig();

        fishFile = new File(pl.getDataFolder(), "fish.yml");
        fish = YamlConfiguration.loadConfiguration(fishFile);

        rodsFile = new File(pl.getDataFolder(), "rods.yml");
        rods = YamlConfiguration.loadConfiguration(rodsFile);

        treasureFile = new File(pl.getDataFolder(), "treasure.yml");
        treasure = YamlConfiguration.loadConfiguration(treasureFile);

        if(!fishFile.exists()) {
            pl.saveResource("fish.yml", false);
        }

        if(!rodsFile.exists()) {
            pl.saveResource("rods.yml", false);
        }

        if(!treasureFile.exists()) {
            pl.saveResource("treasure.yml", false);
        }
    }

    /**
     * Get the main config file.
     * @return Main config file.
     */
    public FileConfiguration getConfig() {
        return config;
    }

    /**
     * Get the fish config file.
     * @return Fish config file.
     */
    public FileConfiguration getFish() {
        return  fish;
    }

    /**
     * Get the fishing rods config file.
     * @return Fishing rods config file.
     */
    public FileConfiguration getRods() {
        return rods;
    }

    /**
     * Get the treasure configuration file.
     * @return Treasure configuration file.
     */
    public FileConfiguration getTreasure() {
        return treasure;
    }
}