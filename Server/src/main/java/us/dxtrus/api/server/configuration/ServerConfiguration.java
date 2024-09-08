package us.dxtrus.api.server.configuration;

import io.swagger.v3.jaxrs2.SwaggerSerializers;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import us.dxtrus.api.server.database.DatabaseManager;
import us.dxtrus.api.server.errors.NotFoundHandler;
import us.dxtrus.api.server.v1.survival.DungeonsInfoEndpointV1;
import us.dxtrus.api.server.v1.user.UserInfoEndpointV1;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@OpenAPIDefinition(
        info = @Info(
                title = "DXTRUS API",
                version = "1.0",
                description = "The public facing API for dxtrus.",
                contact = @Contact(name = "Preva1l", url = "https://preva1l.info/")
        )
)
@ApplicationPath("/")
public class ServerConfiguration extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        ApplicationConfig.getInstance(); // Create file
        DatabaseManager.getInstance(); // Connect

        Set<Class<?>> classes = new HashSet<>();
        Stream.of(
                // Version 1
                UserInfoEndpointV1.class,
                DungeonsInfoEndpointV1.class,

                // Swagger api docs
                SwaggerSerializers.class,
                OpenApiResource.class,

                NotFoundHandler.class
        ).forEach(classes::add);

        return classes;
    }
}