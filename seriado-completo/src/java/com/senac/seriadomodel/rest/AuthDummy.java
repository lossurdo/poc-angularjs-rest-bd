package com.senac.seriadomodel.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/authdummy")
public class AuthDummy {

    @POST
    public Response dummy() {
        return Response.ok().build();
    }

}
