package io.ncbpfluffybear.slimyloot;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class BreakEvent implements Listener {

    @EventHandler
    public void blockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (p.hasPermission("slimyloot.loot.break")) {

            double chance = ThreadLocalRandom.current().nextDouble();

            if (chance <= SlimyLoot.breakDropsChance) {

                int randomIndex = ThreadLocalRandom.current().nextInt(0, SlimyLoot.breakDrops.size());
                ItemStack drop = SlimefunItem.getByID(SlimyLoot.breakDrops.get(randomIndex)).getItem();

                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), drop);
                Utils.send(p, "&aYou have found a " + drop.getItemMeta().getDisplayName());
            }
        }
    }

}
