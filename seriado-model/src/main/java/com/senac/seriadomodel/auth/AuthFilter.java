package com.senac.seriadomodel.auth;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;

/**
 * 
 * @see https://jwt.io/ e https://github.com/auth0/java-jwt
 * @author lossurdo 
 */
//@Provider
public class AuthFilter implements ContainerRequestFilter, ContainerResponseFilter {

    public static final Logger logger = Logger.getLogger(AuthFilter.class);
    
    /**
     * Chave PRINCIPAL de criptografia
     */
    protected static final String CHAVE_HMAC = "jn2[349q3*TRdaY";
    
    @Context
    private HttpServletRequest servletRequest;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        logger.debug("Filtrando REQUEST da chamada REST");
        
        // TOKEN informado
        if(informadoHeader("token", requestContext)) {
            logger.debug("Verificando token informado");
            String token = requestContext.getHeaderString("token");
            JWTVerifier verifier = new JWTVerifier(CHAVE_HMAC);
            try {
                Map<String, Object> payload = verifier.verify(token);
                logger.debug("Token verificado");
                
                String ip = payload.get("ip").toString();
                if(!servletRequest.getRemoteAddr().equals(ip)) {
                    logger.warn("Validação de token inválida: ENDEREÇO IP INCORRETO");
                    throw new WebApplicationException(Response.Status.FORBIDDEN);
                }
                
                TokenControl.getInstance().revalidarToken(token);
                logger.debug("Revalidada data do Token");
            } catch (Exception ex) {
                logger.warn("Token inválido: " + ex.getMessage());
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            }
        }
        
        // HEADER informado incorretamente
        else if(!informadoHeader("usuario", requestContext) 
                || !informadoHeader("senha", requestContext)) {
            logger.warn("FORBIDDEN; HEADER obrigatório não informado!");
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        } 
        
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        logger.debug("Filtrando REQUEST/RESPONSE da chamada REST");

        
        // USUÁRIO e SENHA informados
        if(informadoHeader("usuario", requestContext)
                && informadoHeader("senha", requestContext)) {

            // valida usuário e senha em uma base de dados, se ok cria o token
            
            Map<String, Object> payload = new HashMap<>();
            payload.put("usuario", requestContext.getHeaderString("usuario"));
            payload.put("data", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            payload.put("ip", servletRequest.getRemoteAddr());
            logger.debug("PAYLOAD: " + payload);
            
            JWTSigner signer = new JWTSigner(CHAVE_HMAC);
            String token = signer.sign(payload);
            
            responseContext.getHeaders().add("token", token);
            TokenControl.getInstance().addToken(token);
            logger.debug("Token gerado: " + token);
        } 
                
    }

    private boolean informadoHeader(String key, ContainerRequestContext requestContext) {
        return requestContext.getHeaderString(key)!=null;
    }
}
