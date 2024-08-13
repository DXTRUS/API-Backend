package us.dxtrus.api.server;

import org.jetbrains.annotations.NotNull;

public interface BackendServer extends Server {
    @NotNull String getVersion();
    double getTPS();
}
