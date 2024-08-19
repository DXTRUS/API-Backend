package us.dxtrus.api.server.base.user;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

public interface UserInfoEndpoint {
    Response getUser(@PathParam("query") String query);
}
