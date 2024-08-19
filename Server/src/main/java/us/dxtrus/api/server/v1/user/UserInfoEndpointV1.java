package us.dxtrus.api.server.v1.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jetbrains.annotations.Blocking;
import org.jetbrains.annotations.NotNull;
import us.dxtrus.api.models.user.User;
import us.dxtrus.api.server.base.user.UserInfoEndpoint;
import us.dxtrus.api.server.configuration.ApplicationConfig;
import us.dxtrus.api.server.database.CacheManager;
import us.dxtrus.api.server.database.DatabaseManager;
import us.dxtrus.api.server.errors.CommonResults;

import java.util.Optional;
import java.util.UUID;

@Tag(name = "User Information", description = "Endpoints to do with user data across the network.")
@Path("/v1/user")
public class UserInfoEndpointV1 implements UserInfoEndpoint {

    @Operation(
            summary = "Gets a users information"
    )
    @ApiResponse(
            responseCode = "200",
            description = "The users information.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = User.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "The user was not found."
    )
    @GET
    @Path("/{query}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getUser(@PathParam("query") String query) {
        if (!ApplicationConfig.getInstance().getEndpoints().getUserInfo().isEnabled()) {
            return CommonResults.ENDPOINT_DISABLED;
        }

        try {
            UUID uuid = UUID.fromString(query);
            Optional<User> cachedGetUser = CacheManager.getInstance().getUser(uuid);
            if (cachedGetUser.isEmpty()) {
                Optional<User> dbUser = DatabaseManager.getInstance().get(User.class, uuid).join();
                if (dbUser.isEmpty()) {
                    return CommonResults.NOT_FOUND("User not found with the uuid %s.".formatted(uuid));
                }
                CacheManager.getInstance().cacheUser(dbUser.get());
                return Response.ok(dbUser.get().toJson(), MediaType.APPLICATION_JSON).build();
            }
            return Response.ok(cachedGetUser.get().toJson(), MediaType.APPLICATION_JSON).build();
        } catch (IllegalArgumentException e) {
            return attemptSearch(query);
        }
    }

    /**
     * Attempt a search for the players name if the uuid returned negative.
     *
     * @param name the name to search.
     * @return the response.
     */
    @Blocking
    private Response attemptSearch(@NotNull String name) {
        Optional<User> cachedGetUser = CacheManager.getInstance().searchUser(name);
        if (cachedGetUser.isEmpty()) {
            Optional<User> dbUser = DatabaseManager.getInstance().search(User.class, name).join();
            if (dbUser.isEmpty()) {
                return CommonResults.NOT_FOUND("User not found with the username %s.".formatted(name));
            }
            CacheManager.getInstance().cacheUser(dbUser.get());
            return Response.ok(dbUser.get().toJson(), MediaType.APPLICATION_JSON).build();
        }
        return Response.ok(cachedGetUser.get().toJson(), MediaType.APPLICATION_JSON).build();
    }
}
