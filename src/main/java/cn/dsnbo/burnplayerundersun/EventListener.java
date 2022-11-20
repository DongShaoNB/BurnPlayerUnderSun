package cn.dsnbo.burnplayerundersun;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import static cn.dsnbo.burnplayerundersun.BurnPlayerUnderSun.getIns;

public class EventListener implements Listener {


    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent p){

        Player player = p.getPlayer();
        Material playerHat;
        FileConfiguration config = getIns().getConfig();


        boolean playerWorldWeather = player.getWorld().isClearWeather();
        Location playerLocate = player.getLocation();
        long playerWorldTime = player.getWorld().getTime();
        GameMode playerGamemode = player.getGameMode();
        byte blockLight = playerLocate.getBlock().getLightFromSky();
        ItemStack playerInv = player.getInventory().getHelmet();
        if (playerInv != null) {
            playerHat = playerInv.getType();
        } else {
            playerHat = Material.AIR;
        }


        if (config.getBoolean("enable")) {
            if (!player.hasPermission("bpus.pass")) {
                if (playerGamemode == GameMode.SURVIVAL) {
                    if (playerWorldWeather) {
                        if (playerWorldTime >= 6000 && playerWorldTime <= 18000) {
                            if (blockLight == 15) {
                                if (playerHat == Material.AIR) {
                                    p.getPlayer().setFireTicks(config.getInt("burntime") * 20);
                                }
                            }
                        }
                    }
                }
            }


        }
    }
}
