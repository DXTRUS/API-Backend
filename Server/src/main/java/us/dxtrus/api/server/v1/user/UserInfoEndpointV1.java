package us.dxtrus.api.server.v1.user;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import us.dxtrus.api.database.DatabaseManager;
import us.dxtrus.api.models.user.User;
import us.dxtrus.api.server.base.UserInfoEndpoint;

import java.util.concurrent.CompletableFuture;

@Path("/v1/user")
public class UserInfoEndpointV1 implements UserInfoEndpoint {

    /**
     * 
     *
     * @param uuid
     * @return
     */
    @GET
    @Path("/{query}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public CompletableFuture<Response> getUser(@PathParam("query") String uuid) {
        return CompletableFuture.supplyAsync(() -> {
           User user = DatabaseManager.getInstance().s(User.class, )
        });
    }
}
