package com.senac.seriadomodel.rest;

import com.google.gson.Gson;
import com.senac.seriadomodel.bean.Genero;
import com.senac.seriadomodel.bean.Seriado;
import com.senac.seriadomodel.crud.CrudGenericoRest;
import com.senac.seriadomodel.crud.ErroRest;
import com.senac.seriadomodel.crud.RNException;
import com.senac.seriadomodel.rn.GeneroRN;
import java.net.URI;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author lossurdo
 */
@Path("/generos")
public class GeneroRest extends CrudGenericoRest<Genero> {

    private final GeneroRN rn;

    public GeneroRest() {
        rn = new GeneroRN();
    }

    @Override
    public Response consultarPK(String pk) {
        try {
            Genero g = rn.consultar(new Genero(Integer.parseInt(pk)));
            return Response.ok(g).build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response pesquisar(String q) {
        try {
            List<Genero> ret = rn.pesquisar(q);
            
            /*
                Remove a lista circular; seriado aponta pra gênero
                e gênero aponta pra seriado; Gson gera exceção.
            */
            for (Genero genero : ret) {
                for(Seriado seriado : genero.getSeriados()) {
                    seriado.setGeneros(null);
                }
            }            
            
            return gerarResponseParaCollection(ret);
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response excluirPK(String pk) {
        try {
            rn.excluir(new Genero(Integer.parseInt(pk)));
            return Response.ok().build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response salvar(String obj) {
        try {
            Genero o = rn.salvar(new Gson().fromJson(obj, Genero.class));
            URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(o.getId())).build();
            return Response.created(uri).build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response alterar(String obj) {
        try {
            Genero o = rn.alterar(new Gson().fromJson(obj, Genero.class));
            URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(o.getId())).build();
            return Response.created(uri).build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    protected Response gerarResponseParaCollection(List<Genero> obj) {
        if (obj == null) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(toJSON(new ErroRest("Nenhum registro disponível; lista vazia")))
                .build();
        }

        return Response.ok(new Gson().toJson(obj)).build();
    }

}
