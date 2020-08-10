package org.firewolf8385.betterfish;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.firewolf8385.betterfish.objects.CustomFish;
import org.firewolf8385.betterfish.objects.LivingFish;

public final class BetterFish extends JavaPlugin {
    private static final ConfigManager config = ConfigManager.getInstance();

    @Override
    public void onEnable() {
        // Plugin startup logic
        config.setup(this);

        registerFish();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerFish() {
        ConfigurationSection section = config.getFish().getConfigurationSection("Fish");

        if(section == null) {
            return;
        }

        section.getKeys(false).forEach(fish -> {
            ConfigurationSection entity = config.getFish().getConfigurationSection("Fish." + fish + ".Entity");
            if(entity != null) {
                new LivingFish(fish);
            }
            else {
                new CustomFish(fish);
            }
        });

        getLogger().info("Registered " + CustomFish.getAllFish().size() + " custom fish.");
    }
}
