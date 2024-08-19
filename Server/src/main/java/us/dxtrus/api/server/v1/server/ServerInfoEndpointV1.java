package us.dxtrus.api.server.v1.server;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import us.dxtrus.api.models.server.BackendServer;
import us.dxtrus.api.models.user.User;
import us.dxtrus.api.server.base.server.ServerInfoEndpoint;
import us.dxtrus.api.server.configuration.ApplicationConfig;
import us.dxtrus.api.server.database.CacheManager;
import us.dxtrus.api.server.database.DatabaseManager;
import us.dxtrus.api.server.errors.CommonResults;

import java.util.Optional;

@Path("/v1")
public class ServerInfoEndpointV1 implements ServerInfoEndpoint {
    @GET
    @Path("/servers")
    @Override
    public Response getServers() {
        return null;
    }

    @Path("/server/{server}")
    @Override
    public Response getServer(@PathParam("server") String server) {
        if (!ApplicationConfig.getInstance().getEndpoints().getUserInfo().isEnabled()) {
            return CommonResults.ENDPOINT_DISABLED;
        }

        Optional<BackendServer> cachedGetUser = CacheManager.getInstance().getServer(server);
        if (cachedGetUser.isPresent()) {
            return Response.ok(cachedGetUser.get().toJson(), MediaType.APPLICATION_JSON).build();
        }

        Optional<BackendServer> dbUser = DatabaseManager.getInstance().search(BackendServer.class, server).join();
        if (dbUser.isEmpty()) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("{\"code\": \"404\"\"message\": \"Server not found!\"}")
                    .build();
        }
        CacheManager.getInstance().cacheServer(dbUser.get());
        return Response.ok(dbUser.get().toJson(), MediaType.APPLICATION_JSON).build();
    }
}
