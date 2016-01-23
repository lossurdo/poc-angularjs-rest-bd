package com.senac.seriadomodel.rest;

import com.senac.seriadomodel.bean.Teste;
import com.senac.seriadomodel.crud.CrudGenericoRest;
import com.senac.seriadomodel.crud.RNException;
import com.senac.seriadomodel.rn.TesteRN;
import java.net.URI;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

@Path("/testes")
public class TesteRest extends CrudGenericoRest<Teste> {

    private final TesteRN rn;

    public TesteRest() {
        rn = new TesteRN();
    }

    @Override
    public Response consultarPK(String pk) {
        try {
            Teste t = rn.consultar(new Teste(Integer.parseInt(pk)));
            return Response.ok(t).build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response pesquisar(String q) {
        try {
            List<Teste> ret = rn.pesquisar(q);

            return gerarResponseParaCollection(ret);    
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response excluirPK(String pk) {
        try {
            rn.excluir(new Teste(Integer.parseInt(pk)));
            return Response.ok().build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response salvar(Teste obj) {
        try {
            Teste o = rn.salvar(obj);
            URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(o.getId())).build();
            return Response.created(uri).build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    public Response alterar(Teste obj) {
        try {
            Teste o = rn.alterar(obj);
            URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(o.getId())).build();
            return Response.created(uri).build();
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }
    }

    @Override
    protected Response gerarResponseParaCollection(List<Teste> obj) {
        if (obj == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        GenericEntity<List<Teste>> lista = new GenericEntity<List<Teste>>(obj) {
        };
        return Response.ok(lista).build();
    }

}
