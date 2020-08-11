package org.firewolf8385.betterfish;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.firewolf8385.betterfish.commands.BetterFishCMD;
import org.firewolf8385.betterfish.listeners.EntityDeath;
import org.firewolf8385.betterfish.listeners.PlayerFish;
import org.firewolf8385.betterfish.objects.CustomFish;
import org.firewolf8385.betterfish.objects.CustomRod;
import org.firewolf8385.betterfish.objects.LivingFish;

public final class BetterFish extends JavaPlugin {
    private static final ConfigManager config = ConfigManager.getInstance();
    private static BetterFish plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        config.setup(this);

        registerCommands();
        registerListeners();
        registerFish();
        registerRods();

        // Registers metrics.
        new MetricsLite(this, 8459);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        plugin = null;
    }

    private void registerCommands() {
        getCommand("betterfish").setExecutor(new BetterFishCMD());
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new EntityDeath(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerFish(), this);
    }

    private void registerFish() {
        ConfigurationSection section = config.getFish().getConfigurationSection("Fish");

        if (section == null) {
            return;
        }

        section.getKeys(false).forEach(fish -> {
            ConfigurationSection entity = config.getFish().getConfigurationSection("Fish." + fish + ".Entity");
            if (entity != null) {
                new LivingFish(fish);
            }
            else {
                new CustomFish(fish);
            }
        });

        getLogger().info("Registered " + CustomFish.getAllFish().size() + " custom fish.");
    }

    private void registerRods() {
        ConfigurationSection section = config.getRods().getConfigurationSection("Rods");

        if (section == null) {
            return;
        }

        section.getKeys(false).forEach(CustomRod::new);
    }

    public static BetterFish getPlugin() {
        return plugin;
    }
}
