package us.dxtrus.api.server;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import us.dxtrus.api.server.errors.NotFoundHandler;
import us.dxtrus.api.server.v1.user.UserInfoEndpointV1;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@ApplicationPath("/")
public class APIServer extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();

        // v1
        Stream.of(
                UserInfoEndpointV1.class
        ).forEach(classes::add);

        // Handlers
        Stream.of (
                NotFoundHandler.class
        ).forEach(classes::add);

        return classes;
    }
}