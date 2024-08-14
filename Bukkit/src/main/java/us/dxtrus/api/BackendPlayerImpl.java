package us.dxtrus.api;

import lombok.Getter;
import org.bukkit.entity.Player;
import us.dxtrus.api.database.DatabaseManager;
import us.dxtrus.api.models.user.User;

import java.util.UUID;

@Getter
public class BackendPlayerImpl implements User {
    private final Player bukkitPlayer;

    private final String name;
    private final UUID uniqueId;
    private String currentProxy;
    private final String currentServer;

    private BackendPlayerImpl(Player bukkitPlayer) {
        this.bukkitPlayer = bukkitPlayer;
        this.name = bukkitPlayer.getName();
        this.uniqueId = bukkitPlayer.getUniqueId();
        this.currentProxy = "Unknown";
        this.currentServer = "Config Value";

        // Defer currentProxy assigning.
        DatabaseManager.getInstance().get(User.class, uniqueId)
                .thenAccept(user -> user.ifPresent(value -> this.currentProxy = value.getCurrentProxy()));
    }

    public static BackendPlayerImpl adapt(Player bukkitPlayer) {
        return new BackendPlayerImpl(bukkitPlayer);
    }
}
