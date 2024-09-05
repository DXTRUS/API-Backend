package us.dxtrus.api.server.base.survival;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

public interface DungeonsInfoEndpoint {
    Response getStats(@PathParam("query") String query);
    Response getDungeon(@PathParam("dungeon") String dungeon);
    Response getAllDungeons();
    Response getActiveDungeons();
}
