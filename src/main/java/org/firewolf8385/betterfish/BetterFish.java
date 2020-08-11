package org.firewolf8385.betterfish;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.firewolf8385.betterfish.commands.BetterFishCMD;
import org.firewolf8385.betterfish.listeners.EntityDeath;
import org.firewolf8385.betterfish.objects.CustomFish;
import org.firewolf8385.betterfish.objects.LivingFish;

public final class BetterFish extends JavaPlugin {
    private static final ConfigManager config = ConfigManager.getInstance();

    @Override
    public void onEnable() {
        // Plugin startup logic
        config.setup(this);

        registerCommands();
        registerListeners();
        registerFish();

        // Registers metrics.
        new MetricsLite(this, 8459);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        getCommand("betterfish").setExecutor(new BetterFishCMD());
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new EntityDeath(), this);
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
}
