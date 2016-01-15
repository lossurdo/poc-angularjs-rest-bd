package com.senac.seriadomodel.rest;

import com.google.gson.Gson;
import com.senac.seriadomodel.bean.Seriado;
import com.senac.seriadomodel.crud.CrudGenericoRest;
import com.senac.seriadomodel.crud.RNException;
import com.senac.seriadomodel.rn.SeriadoRN;
import java.net.URI;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

/**
 *
 * @author lossurdo
 */
@Path("/seriados")
public class SeriadoRest extends CrudGenericoRest<Seriado> {

    private final SeriadoRN rn;

    public SeriadoRest() {
        this.rn = new SeriadoRN();
    }
    
    @Override
    public Response consultarPK(String pk) {
        try {
            Seriado s = rn.consultar(new Seriado(Integer.parseInt(pk)));
            return Response.ok(s).build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response pesquisar(String json) {
        try {
            Gson g = new Gson();
            Seriado seriado = g.fromJson(json, Seriado.class);

            List<Seriado> ret = rn.pesquisar(seriado);

            return gerarResponseParaCollection(ret);
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response listar(Integer offset, Integer limit) {
        try {
            List<Seriado> ret = rn.pesquisar("");
            return gerarResponseParaCollection(ret);
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response excluirPK(String pk) {
        try {
            rn.excluir(new Seriado(Integer.parseInt(pk)));
            return Response.ok().build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response salvar(Seriado obj) {
        try {
            Seriado o = rn.salvar(obj);
            URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(o.getId())).build();
            return Response.created(uri).build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response alterar(Seriado obj) {
        try {
            Seriado o = rn.alterar(obj);
            URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(o.getId())).build();
            return Response.created(uri).build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    protected Response gerarResponseParaCollection(List<Seriado> obj) {
        if (obj == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        GenericEntity<List<Seriado>> lista = new GenericEntity<List<Seriado>>(obj) {
        };
        return Response.ok(lista).build();
    }
    
}
