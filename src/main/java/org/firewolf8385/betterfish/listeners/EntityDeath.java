package org.firewolf8385.betterfish.listeners;

import org.bukkit.entity.Fish;
import org.bukkit.entity.TropicalFish;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.firewolf8385.betterfish.objects.CustomFish;
import org.firewolf8385.betterfish.objects.LivingFish;

public class EntityDeath implements Listener {
    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if(!(e.getEntity() instanceof Fish)) {
            return;
        }

        Fish fish = (Fish) e.getEntity();

        for(CustomFish cf : CustomFish.getAllFish()) {
            if(!(cf instanceof LivingFish)) {
                continue;
            }

            LivingFish lf = (LivingFish) cf;

            if(fish.getType() != lf.getType()) {
                continue;
            }

            switch (lf.getType()) {
                case COD:
                case SALMON:
                    e.getDrops().clear();
                    e.getDrops().add(lf.getItem());
                    break;

                case TROPICAL_FISH:
                    TropicalFish tf = (TropicalFish) fish;

                    if(tf.getPattern() == lf.getPattern()) {
                        if(tf.getPatternColor() == lf.getPatternColor() && tf.getBodyColor() == lf.getBodyColor()) {
                            e.getDrops().clear();
                            e.getDrops().add(lf.getItem());
                        }
                    }
                    break;
            }
        }
    }
}