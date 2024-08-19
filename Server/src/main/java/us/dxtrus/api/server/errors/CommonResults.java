package us.dxtrus.api.server.errors;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonResults {
    public final Response ENDPOINT_DISABLED = Response.status(Response.Status.SERVICE_UNAVAILABLE)
            .entity("{\"code\": \"503\", \"message\": \"This endpoint is currently disabled.\"}")
            .type(MediaType.APPLICATION_JSON)
            .build();

    public Response NOT_FOUND(String message) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity("{\"code\": \"404\", \"message\": \"%s\"}".formatted(message))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
