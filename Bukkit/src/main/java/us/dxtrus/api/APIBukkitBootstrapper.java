package us.dxtrus.api;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class APIBukkitBootstrapper extends JavaPlugin {
    @Getter private static APIBukkitBootstrapper instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {

    }
}
