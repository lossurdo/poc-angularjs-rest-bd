package com.senac.seriadomodel.bd;

import com.senac.seriadomodel.bean.Teste;
import com.senac.seriadomodel.crud.CrudGenericoBD;
import java.util.List;
import javax.persistence.Query;

public class TesteBD extends CrudGenericoBD<Teste> {
    
    public List<Teste> pesquisar(String valor) {
        Query query = getEntityManager().createNamedQuery("findByValor");
        query.setParameter("param1", valor);
        return query.getResultList();
    }
    
    public List<Teste> pesquisar() {
        Query query = getEntityManager().createNamedQuery("findAll");
        return query.getResultList();
    }
    
}
