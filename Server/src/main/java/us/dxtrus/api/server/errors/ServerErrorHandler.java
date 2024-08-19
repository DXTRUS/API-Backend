package us.dxtrus.api.server.errors;

import jakarta.ws.rs.ServerErrorException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ServerErrorHandler  implements ExceptionMapper<ServerErrorException> {
    @Override
    public Response toResponse(ServerErrorException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("{\"code\": \"500\"\"message\": \"There was an unexpected issue.\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}