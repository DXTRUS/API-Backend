package us.dxtrus.api.server.v1.survival;

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
import us.dxtrus.api.models.dungeons.Dungeon;
import us.dxtrus.api.models.user.User;
import us.dxtrus.api.server.base.survival.DungeonsInfoEndpoint;

@Tag(name = "User Information", description = "Endpoints to do with user data across the network.")
@Path("/v1/user")
public class DungeonsInfoEndpointV1 implements DungeonsInfoEndpoint {
    @Operation(
            summary = "Gets information on a dungeon."
    )
    @ApiResponse(
            responseCode = "200",
            description = "The dungeon information.",
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
    public Response getStats(@PathParam("query") String query) {
        return null;
    }

    @Operation(
            summary = "Gets a users information"
    )
    @ApiResponse(
            responseCode = "200",
            description = "The users information.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Dungeon.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "The dungeon was not found."
    )
    @GET
    @Path("/{dungeon}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getDungeon(@PathParam("dungeon") String dungeon) {
        return null;
    }

    @Override
    public Response getAllDungeons() {
        return null;
    }

    @Override
    public Response getActiveDungeons() {
        return null;
    }
}
