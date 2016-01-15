package com.senac.seriadomodel.bd;

import com.senac.seriadomodel.bean.Teste;
import com.senac.seriadomodel.crud.CrudGenericoBD;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TesteBD extends CrudGenericoBD<Teste> {
    
    @Override
    public List<Teste> pesquisar(String valor) {
        EntityManager em = createEntityManager();        
        Query query = em.createNamedQuery("findByValor");
        query.setParameter("param1", valor);
        List lista = query.getResultList();
        em.close();
        return lista;
    }

    @Override
    public List<Teste> pesquisar(Teste bean) {
        return pesquisar(bean.getValor());
    }
    
}
