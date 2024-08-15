package us.dxtrus.api.server.v1.user;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jetbrains.annotations.Blocking;
import org.jetbrains.annotations.NotNull;
import us.dxtrus.api.database.DatabaseManager;
import us.dxtrus.api.models.user.DatabaseUser;
import us.dxtrus.api.models.user.User;
import us.dxtrus.api.server.base.user.UserInfoEndpoint;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Path("/v1/user")
public class UserInfoEndpointV1 implements UserInfoEndpoint {

    /**
     * Get a {@link User} from our database.
     *
     * @param query the username or UUID of the player.
     * @return json response of the user's data.
     */
    @GET
    @Path("/{query}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public CompletableFuture<Response> getUser(@PathParam("query") String query) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                UUID uuid = UUID.fromString(query);
                Optional<User> user = DatabaseManager.getInstance().get(User.class, uuid).join();
                if (user.isEmpty()) {
                    return Response
                            .status(Response.Status.NOT_FOUND)
                            .entity("User not found with UUID %s".formatted(query))
                            .build();
                }
                return Response.ok(user.get().toJson()).build();
            } catch (IllegalArgumentException e) {
                return attemptSearch(query);
            }
        });
    }

    /**
     * Attempt a search for the players name if the uuid returned negative.
     *
     * @param name the name to search.
     * @return the response.
     */
    @Blocking
    private Response attemptSearch(@NotNull String name) {
        Optional<User> user = DatabaseManager.getInstance().search(User.class, name).join();
        if (user.isEmpty()) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("User not found with Name %s".formatted(name))
                    .build();
        }
        return Response.ok(user.get().toJson()).build();
    }
}
