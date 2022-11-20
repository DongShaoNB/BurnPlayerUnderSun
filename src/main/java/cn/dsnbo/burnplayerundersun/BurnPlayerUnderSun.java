package cn.dsnbo.burnplayerundersun;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BurnPlayerUnderSun extends JavaPlugin {

    @Override
    public void onEnable() {
        ins = this;
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);

    }

    @Override
    public void onDisable() {

    }

    private static BurnPlayerUnderSun ins;

    public static BurnPlayerUnderSun getIns() {
        return ins;
    }
}
