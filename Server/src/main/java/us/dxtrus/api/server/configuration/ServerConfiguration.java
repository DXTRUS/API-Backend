package us.dxtrus.api.server.configuration;

import io.swagger.v3.jaxrs2.SwaggerSerializers;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import us.dxtrus.api.server.database.DatabaseManager;
import us.dxtrus.api.server.errors.NotFoundHandler;
import us.dxtrus.api.server.v1.user.UserInfoEndpointV1;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@ApplicationPath("/")
public class ServerConfiguration extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        ApplicationConfig.getInstance(); // Create file
        DatabaseManager.getInstance(); // Connect

        Set<Class<?>> classes = new HashSet<>();
        Stream.of(
                UserInfoEndpointV1.class,

                // Swagger api docs
                OpenApiResource.class,
                SwaggerSerializers.class,

                NotFoundHandler.class
        ).forEach(classes::add);

        return classes;
    }
}