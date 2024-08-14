package us.dxtrus.api.models.server;

import org.jetbrains.annotations.NotNull;
import us.dxtrus.api.models.Serializable;
import us.dxtrus.api.models.user.User;

import java.util.List;

public interface Server extends Serializable {
    @NotNull String getName();
    int getPlayerCount();
    List<User> getPlayers();
}
