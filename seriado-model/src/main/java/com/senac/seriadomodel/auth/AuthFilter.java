package com.senac.seriadomodel.auth;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
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
    private static final String CHAVE_HMAC = "jn2[349q3*TRdaY";
    
    @Context
    private HttpServletRequest servletRequest;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        logger.debug("Filtrando REQUEST da chamada REST");
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        logger.debug("Filtrando REQUEST/RESPONSE da chamada REST");

        // deve validar usuário e senha e criar token
        if (requestContext.getHeaderString("usuario") != null
                && requestContext.getHeaderString("senha") != null) {
            
            // valida usuário e senha em uma base de dados, se ok cria o token
            
            Map<String, Object> payload = new HashMap<>();
            payload.put("usuario", requestContext.getHeaderString("usuario"));
            payload.put("data", new Date());
            payload.put("ip", servletRequest.getRemoteAddr());
            logger.debug("PAYLOAD: " + payload);
            
            JWTSigner signer = new JWTSigner(CHAVE_HMAC);
            String token = signer.sign(payload);
            
            responseContext.getHeaders().add("x-senac-token", token);
            logger.debug("Token gerado: " + token);
        } else { // deve validar token
            if (requestContext.getHeaderString("x-senac-token") == null) {
                logger.warn("FORBIDDEN; HEADER obrigatório não informado!");
                responseContext.setStatus(Response.Status.FORBIDDEN.getStatusCode());
            } else { // token informado
                logger.debug("Verificando token informado");
                String token = requestContext.getHeaderString("x-senac-token");
                JWTVerifier verifier = new JWTVerifier(CHAVE_HMAC);
                try {
                    verifier.verify(token);
                    logger.debug("Token válido");
                } catch (Exception ex) {
                    logger.warn("Validação de token inválida: " + ex.getMessage());
                    responseContext.setStatus(Response.Status.FORBIDDEN.getStatusCode());
                }
            }
        }

    }

}
