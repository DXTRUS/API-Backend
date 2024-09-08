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
import us.dxtrus.api.server.base.survival.DungeonsInfoEndpoint;
import us.dxtrus.api.server.errors.CommonResults;

@Tag(name = "Dungeon Information", description = "Endpoints to do with survival dungeons")
@Path("/v1/survival/dungeons")
public class DungeonsInfoEndpointV1 implements DungeonsInfoEndpoint {
    @Operation(
            summary = "Gets information on a dungeon."
    )
    @ApiResponse(
            responseCode = "200",
            description = "The dungeon information.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Dungeon.class)
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
    @Path("/{dungeonName}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getDungeon(@PathParam("dungeonName") String dungeon) {
        return CommonResults.NOT_FOUND("Dungeon not found with the name %s.".formatted(dungeon));
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getAllDungeons() {
        return Response.ok("{}", MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/active")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getActiveDungeons() {
        return Response.ok("{}", MediaType.APPLICATION_JSON).build();
    }
}
