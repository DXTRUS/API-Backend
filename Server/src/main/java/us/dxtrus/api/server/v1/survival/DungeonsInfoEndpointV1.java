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
import us.dxtrus.api.models.dungeons.ActiveDungeon;
import us.dxtrus.api.models.dungeons.Dungeon;
import us.dxtrus.api.server.base.survival.DungeonsInfoEndpoint;
import us.dxtrus.api.server.errors.CommonResults;

@Tag(name = "Dungeon Information", description = "Endpoints to do with survival dungeons")
@Path("/v1/survival/dungeons")
public class DungeonsInfoEndpointV1 implements DungeonsInfoEndpoint {
    @Operation(
            summary = "Gets information about a dungeon."
    )
    @ApiResponse(
            responseCode = "200",
            description = "The dungeon info.",
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

    @Operation(
            summary = "Gets all the dungeons."
    )
    @ApiResponse(
            responseCode = "200",
            description = "The dungeon info.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(anyOf = Dungeon.class)
            )
    )
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getAllDungeons() {
        return Response.ok("{}", MediaType.APPLICATION_JSON).build();
    }

    @Operation(
            summary = "Gets all the currently running dungeons."
    )
    @ApiResponse(
            responseCode = "200",
            description = "The active dungeon info.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(anyOf = ActiveDungeon.class)
            )
    )
    @GET
    @Path("/active")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getActiveDungeons() {
        return Response.ok("{}", MediaType.APPLICATION_JSON).build();
    }
}
