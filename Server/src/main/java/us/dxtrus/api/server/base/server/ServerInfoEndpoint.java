package us.dxtrus.api.server.base.server;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

public interface ServerInfoEndpoint {
    Response getServers();
    Response getServer(@PathParam("server") String server);
}
