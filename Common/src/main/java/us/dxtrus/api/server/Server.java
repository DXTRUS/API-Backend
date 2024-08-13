package us.dxtrus.api.server;

import org.jetbrains.annotations.NotNull;
import us.dxtrus.api.database.Serializable;
import us.dxtrus.api.user.User;

import java.util.List;

public interface Server extends Serializable {
    @NotNull String getName();
    int getPlayerCount();
    List<User> getPlayers();
}
