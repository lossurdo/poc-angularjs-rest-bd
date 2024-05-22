package com.senac.seriadomodel.rest;

import java.io.IOException;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;


/**
 *
 * @author lossurdo
 */
@Provider
public class ResponseCorsFilter implements ContainerResponseFilter {

    private static final Logger logger = Logger.getLogger(ResponseCorsFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        logger.info("Ativando filtro CORS para chamadas REST");

        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        responseContext.getHeaders().add("Access-Control-Expose-Headers", "Location");

        String headers = requestContext.getHeaderString("Access-Control-Request-Headers");

        if (headers != null && !"".equals(headers)) {
            responseContext.getHeaders().add("Access-Control-Allow-Headers", headers);
        }
        
        logger.info("HEADER final configurado: " + responseContext.getHeaders());
    }

}
