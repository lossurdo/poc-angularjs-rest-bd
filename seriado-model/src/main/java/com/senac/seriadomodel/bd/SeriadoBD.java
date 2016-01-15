package com.senac.seriadomodel.bd;

import com.senac.seriadomodel.bean.Seriado;
import com.senac.seriadomodel.crud.CrudGenericoBD;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author lossurdo
 */
public class SeriadoBD extends CrudGenericoBD<Seriado> {

    @Override
    public List<Seriado> pesquisar(String valor) {
        EntityManager em = createEntityManager();        
        try {
            Query query = em.createNamedQuery("Seriado.findByTitulo");
            query.setParameter("titulo", "%" + valor + "%");
            return query.getResultList();
        } finally {
            em.close();        
        }
    }

    @Override
    public List<Seriado> pesquisar(Seriado bean) {
        return pesquisar(bean.getTitulo());
    }
    
}
