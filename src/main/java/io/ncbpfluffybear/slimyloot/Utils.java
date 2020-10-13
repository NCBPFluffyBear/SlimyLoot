package io.ncbpfluffybear.slimyloot;

import me.mrCookieSlime.Slimefun.cscorelib2.chat.ChatColors;
import org.bukkit.entity.Player;

public class Utils {

    public static void send(Player p, String message) {
        p.sendMessage(ChatColors.color("&7[&a&lSlimy&6Loot&7] " + message));
    }

}