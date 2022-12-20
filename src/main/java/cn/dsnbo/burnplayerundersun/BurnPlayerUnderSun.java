package cn.dsnbo.burnplayerundersun;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public final class BurnPlayerUnderSun extends JavaPlugin implements Listener {

    public final Set<Player> playerHashSet = new HashSet<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        new Metrics(this, 15589);
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            if (!playerHashSet.isEmpty()) {
                for (int i = 0; i < playerHashSet.size(); i++) {

                    Player player = (Player) playerHashSet.toArray()[i];
                    Material playerHat;

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

                    if (getConfig().getBoolean("enable")) {
                        if (!player.hasPermission("bpus.pass")) {
                            if (playerGamemode == GameMode.SURVIVAL) {
                                if (playerWorldWeather) {
                                    if (playerWorldTime >= 23791 || playerWorldTime <= 12210) {
                                        if (blockLight == 15) {
                                            if (playerHat == Material.AIR) {
                                                player.setFireTicks(getConfig().getInt("burntime") * 20);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }, 0, 20);
    }


    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        playerHashSet.add(player);
    }

    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        playerHashSet.remove(player);
    }

    @Override
    public void onDisable() {

    }
}
