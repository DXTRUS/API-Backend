package us.dxtrus.api.models.server;

import org.jetbrains.annotations.NotNull;

public interface BackendServer extends Server {
    @NotNull String getVersion();
    double getTPS();
    double getMSPT();
}
