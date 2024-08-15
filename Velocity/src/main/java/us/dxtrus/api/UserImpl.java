package us.dxtrus.api;

import com.velocitypowered.api.proxy.Player;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import us.dxtrus.api.database.DatabaseManager;
import us.dxtrus.api.models.user.EditableUser;
import us.dxtrus.api.models.user.User;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
@Setter
@AllArgsConstructor
public class UserImpl implements EditableUser {
    @Getter(AccessLevel.PRIVATE)
    private final Player velocityPlayer;
    private final String name;
    private final UUID uniqueId;
    private final String currentProxy;
    private String currentServer;
    private boolean online = true;
}
