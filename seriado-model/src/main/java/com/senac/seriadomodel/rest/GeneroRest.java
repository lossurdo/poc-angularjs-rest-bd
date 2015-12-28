package com.senac.seriadomodel.rest;

import com.senac.seriadomodel.bean.Genero;
import com.senac.seriadomodel.crud.CrudGenericoRest;
import com.senac.seriadomodel.crud.RNException;
import com.senac.seriadomodel.rn.GeneroRN;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response pesquisar(String json) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response listar(Integer offset, Integer limit) {
        try {
            List<Genero> ret = rn.pesquisar(null);
            return gerarResponseParaCollection(ret);
        } catch (RNException e) {
            return exceptionParaResponse(e);
        }    
    }

    @Override
    public Response excluirPK(String pk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response salvar(Genero obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Response gerarResponseParaCollection(List<Genero> obj) {
        if (obj == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        GenericEntity<List<Genero>> lista = new GenericEntity<List<Genero>>(obj) {
        };
        return Response.ok(lista).build();
    }
    
}
