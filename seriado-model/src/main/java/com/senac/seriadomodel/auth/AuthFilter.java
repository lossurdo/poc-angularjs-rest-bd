package com.senac.seriadomodel.auth;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.apache.log4j.Logger;

/**
 *
 * @author lossurdo
 */
@Provider
public class AuthFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final Logger logger = Logger.getLogger(AuthFilter.class);
    
    @Override
    public void filter(ContainerRequestContext requestContext) {
        logger.debug("Filtrando REQUEST da chamada REST");
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        logger.debug("Filtrando REQUEST/RESPONSE da chamada REST");
        
        // deve validar usuário e senha e criar token
        if (requestContext.getHeaderString("usuario") != null && requestContext.getHeaderString("usuario") != null) {

        }
        
        // deve validar token
        if (requestContext.getHeaderString("x-senac-token") == null) {
            logger.warn("FORBIDDEN; HEADER obrigatório não informado!");
            responseContext.setStatus(Response.Status.FORBIDDEN.getStatusCode());
        }
    }
    
}
