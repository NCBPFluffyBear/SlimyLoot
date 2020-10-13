package io.ncbpfluffybear.slimyloot;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class SlimyLoot extends JavaPlugin implements SlimefunAddon {

    private static SlimyLoot instance;
    public static double killDropsChance;
    public static List<String> killDrops = new ArrayList<>();
    public static double breakDropsChance;
    public static List<String> breakDrops = new ArrayList<>();
    public static double fishDropsChance;
    public static List<String> fishDrops = new ArrayList<>();

    @Override
    public void onEnable() {
        // Read something from your config.yml
        Config cfg = new Config(this);

        if (cfg.getBoolean("options.auto-update")) {
            // You could start an Auto-Updater for example
        }

        killDropsChance = cfg.getDouble("kill-drops-chance");
        killDrops.addAll(cfg.getStringList("kill-drops"));

        breakDropsChance = cfg.getDouble("break-drops-chance");
        breakDrops.addAll(cfg.getStringList("break-drops"));

        fishDropsChance = cfg.getDouble("fish-drops-chance");
        fishDrops.addAll(cfg.getStringList("fish-drops"));


        Bukkit.getPluginManager().registerEvents(new KillEvent(), this);
        Bukkit.getPluginManager().registerEvents(new BreakEvent(), this);
        Bukkit.getPluginManager().registerEvents(new FishEvent(), this);

    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    public boolean onCommand(@Nonnull final CommandSender sender, final Command cmd, @Nonnull final String label, @Nonnull final String[] args) {
        if (cmd.getName().equalsIgnoreCase("slimyloot")) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("id")) {
                    if (sender instanceof Player && sender.hasPermission("slimyloot.admin")) {
                        Player p = (Player) sender;

                        if (SlimefunItem.getByItem(p.getInventory().getItemInMainHand()) != null) {
                            Utils.send(p, "&f" + SlimefunItem.getByItem(p.getInventory().getItemInMainHand()).getID());

                        } else {
                            Utils.send(p, "&cYou must be holding a Slimefun item!");

                        }
                        return true;
                    }
                } else {
                    sender.sendMessage("§cInvalid argument! Do §e/slimyloot §7for command" +
                        " usage.");
                }
            } else if (sender instanceof Player) {
                Utils.send((Player) sender, "&eUsage: &a/slimyloot id");

            } else {
                sender.sendMessage("§cThis can only be used in game");
            }
        }
        return true;
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    public static SlimyLoot getInstance() {
        return instance;
    }

}
