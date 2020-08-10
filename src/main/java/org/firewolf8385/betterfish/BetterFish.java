package org.firewolf8385.betterfish;

import org.bukkit.plugin.java.JavaPlugin;

public final class BetterFish extends JavaPlugin {
    private static final ConfigManager config = ConfigManager.getInstance();

    @Override
    public void onEnable() {
        // Plugin startup logic
        config.setup(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
