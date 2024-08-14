package us.dxtrus.api;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import us.dxtrus.api.models.user.User;

import java.util.Optional;
import java.util.UUID;

@Getter
public class ProxyUserImpl implements User {
    @Getter(AccessLevel.PRIVATE)
    private final Player velocityPlayer;
    private final String name;
    private final UUID uniqueId;
    private final String currentProxy;
    private final String currentServer;

    private ProxyUserImpl(Player velocityPlayer) {
        this.velocityPlayer = velocityPlayer;
        this.name = velocityPlayer.getUsername();
        this.uniqueId = velocityPlayer.getUniqueId();
        this.currentProxy = "Config Value";
        Optional<ServerConnection> conn = velocityPlayer.getCurrentServer();
        this.currentServer = conn.isEmpty() ? "Limbo" : conn.get().getServerInfo().getName();
    }

    public static ProxyUserImpl adapt(Player velocityPlayer) {
        return new ProxyUserImpl(velocityPlayer);
    }

    @Override
    public @NotNull String toJson() {
        return "";
    }
}
