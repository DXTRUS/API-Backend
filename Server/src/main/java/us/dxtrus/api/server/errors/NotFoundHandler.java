package us.dxtrus.api.server.errors;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundHandler  implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity("{\"code\": \"404\", \"message\": \"The requested resource was not found.\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}