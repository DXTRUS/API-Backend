package us.dxtrus.api;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import us.dxtrus.api.models.server.BackendServer;
import us.dxtrus.api.models.user.User;

import java.util.ArrayList;
import java.util.List;

public class BackendServerImpl implements BackendServer {
    @Override
    public @NotNull String getVersion() {
        return Bukkit.getVersion();
    }

    @Override
    public double getTPS() {
        return Math.min(Bukkit.getServer().getTPS()[0], 20.0);
    }

    @Override
    public double getMSPT() {
        return Math.round(Bukkit.getServer().getAverageTickTime() * 100.0) / 100.0;
    }

    @Override
    public @NotNull String getName() {
        return "Config Value";
    }

    @Override
    public int getPlayerCount() {
        return Bukkit.getOnlinePlayers().size();
    }

    @Override
    public List<User> getPlayers() {
        List<User> online = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(player -> online.add(BackendPlayerImpl.adapt(player)));
        return online;
    }
}
