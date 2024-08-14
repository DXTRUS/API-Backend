package us.dxtrus.api.server.base;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.concurrent.CompletableFuture;

public interface UserInfoEndpoint {
    CompletableFuture<Response> getUser(@PathParam("uuid") String uuid);
}
