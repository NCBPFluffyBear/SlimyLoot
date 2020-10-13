package io.ncbpfluffybear.slimyloot;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class FishEvent implements Listener {

    @EventHandler
    public void playerFish(PlayerFishEvent e) {
        Player p = e.getPlayer();

        if (e.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)
            && p.hasPermission("slimyloot.loot.fish")) {

            double chance = ThreadLocalRandom.current().nextDouble();

            if (chance <= SlimyLoot.fishDropsChance) {

                int randomIndex = ThreadLocalRandom.current().nextInt(0, SlimyLoot.fishDrops.size());
                ItemStack drop = SlimefunItem.getByID(SlimyLoot.fishDrops.get(randomIndex)).getItem();

                p.getWorld().dropItemNaturally(p.getLocation(), drop);
                Utils.send(p, "&aYou have fished a " + drop.getItemMeta().getDisplayName());
            }
        }
    }

}
