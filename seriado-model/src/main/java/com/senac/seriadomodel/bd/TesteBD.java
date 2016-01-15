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
        try {
            Query query = em.createNamedQuery("findByValor");
            query.setParameter("param1", valor);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Teste> pesquisar(Teste bean) {
        return pesquisar(bean.getValor());
    }
    
}
