package com.senac.seriadomodel.bd;

import com.senac.seriadomodel.bean.Genero;
import com.senac.seriadomodel.crud.CrudGenericoBD;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author lossurdo
 */
public class GeneroBD extends CrudGenericoBD<Genero> {

    @Override
    public List<Genero> pesquisar(String valor) {
        EntityManager em = createEntityManager();        
        try {
            Query query = em.createNamedQuery("Genero.findByGenero");
            query.setParameter("genero", "%" + valor + "%");
            return query.getResultList();
        } finally {
            em.close();        
        }
    }

    @Override
    public List<Genero> pesquisar(Genero bean) {
        return pesquisar(bean.getGenero());
    }
    
}
