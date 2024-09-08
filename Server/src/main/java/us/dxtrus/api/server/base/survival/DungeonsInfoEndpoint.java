package us.dxtrus.api.server.base.survival;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

public interface DungeonsInfoEndpoint {
    Response getDungeon(@PathParam("dungeon") String dungeon);
    Response getAllDungeons();
    Response getActiveDungeons();
}
