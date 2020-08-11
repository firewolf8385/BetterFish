package org.firewolf8385.betterfish.listeners;

import org.bukkit.block.Biome;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.firewolf8385.betterfish.enums.Rarity;
import org.firewolf8385.betterfish.objects.CustomFish;
import org.firewolf8385.betterfish.utils.ChatUtils;

import java.util.ArrayList;
import java.util.List;

public class PlayerFish implements Listener {
    @EventHandler
    public void onFish(PlayerFishEvent e) {
        if(e.getState() != PlayerFishEvent.State.CAUGHT_FISH) {
            return;
        }

        List<CustomFish> biomeFish = new ArrayList<>();
        List<CustomFish> allowedFish = new ArrayList<>();
        Player p = e.getPlayer();
        Biome biome = e.getHook().getLocation().getBlock().getBiome();

        for(CustomFish cf : CustomFish.getAllFish()) {
            if(cf.getBiomes().contains(biome)) {
                biomeFish.add(cf);
            }
        }

        if(biomeFish.size() == 0) {
            biomeFish.add(CustomFish.fromId("salmon"));
        }

        Rarity rarity = null;

        while(allowedFish.size() == 0) {
            rarity = getRarity();

            for(CustomFish cf : biomeFish) {
                if(cf.getRarity() == rarity) {
                    allowedFish.add(cf);
                }
            }
        }

        int random = (int) (Math.random() * (allowedFish.size()));
        CustomFish cf = allowedFish.get(random);

        Item stack = (Item) e.getCaught();
        stack.setItemStack(cf.getItem());

        e.setExpToDrop(cf.getRarity().getExperience());
        ChatUtils.chat(p, rarity.toString());
    }

    /**
     * Get a random rarity using the chance of each..
     * @return Rarity.
     */
    private Rarity getRarity() {
        int common = Rarity.COMMON.getChance();
        int uncommon = Rarity.UNCOMMON.getChance();
        int rare = Rarity.RARE.getChance();
        int epic = Rarity.EPIC.getChance();
        int legendary = Rarity.LEGENDARY.getChance();
        int max = common + uncommon + rare + epic + legendary;
        int random = (int) (Math.random() * (max) + 1);

        if(random <= common) {
            return Rarity.COMMON;
        }
        else if(random <= common + uncommon) {
            return Rarity.UNCOMMON;
        }
        else if(random <= common + uncommon + rare) {
            return Rarity.RARE;
        }
        else if(random <= common + uncommon + rare + epic) {
            return Rarity.EPIC;
        }
        else {
            return Rarity.LEGENDARY;
        }
    }
}
