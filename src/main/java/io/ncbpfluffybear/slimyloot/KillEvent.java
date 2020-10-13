package io.ncbpfluffybear.slimyloot;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class KillEvent implements Listener {

    @EventHandler
    public void playerKillMob(EntityDeathEvent e) {
        Player p = e.getEntity().getKiller();

        if (p != null && p.hasPermission("slimyloot.loot.kill")) {

            double chance = ThreadLocalRandom.current().nextDouble();

            if (chance <= SlimyLoot.killDropsChance) {

                int randomIndex = ThreadLocalRandom.current().nextInt(0, SlimyLoot.killDrops.size());
                ItemStack drop = SlimefunItem.getByID(SlimyLoot.killDrops.get(randomIndex)).getItem();

                p.getWorld().dropItemNaturally(p.getLocation(), drop);
                Utils.send(p, "&aThe mob has dropped a " + drop.getItemMeta().getDisplayName());
            }
        }
    }

}
