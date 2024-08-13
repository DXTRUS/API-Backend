package us.dxtrus.api;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import us.dxtrus.api.server.BackendServer;
import us.dxtrus.api.user.User;

import java.util.List;

public class BackendServerImpl implements BackendServer {
    @Override
    public @NotNull String getVersion() {
        return Bukkit.getVersion();
    }

    @Override
    public double getTPS() {
        return 0;
    }

    @Override
    public @NotNull String getName() {
        return "";
    }

    @Override
    public int getPlayerCount() {
        return 0;
    }

    @Override
    public List<User> getPlayers() {
        return List.of();
    }

    @Override
    public @NotNull String toJson() {
        return "";
    }
}
