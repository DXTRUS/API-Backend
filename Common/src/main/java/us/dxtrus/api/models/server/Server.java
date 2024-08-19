package us.dxtrus.api.models.server;

import org.jetbrains.annotations.NotNull;
import us.dxtrus.api.models.Serializable;
import java.util.List;

public interface Server extends Serializable {
    @NotNull String getName();
    int getPlayerCount();
    List<String> getPlayers();
}
