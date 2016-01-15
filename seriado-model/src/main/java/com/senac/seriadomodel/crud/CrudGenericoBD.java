package com.senac.seriadomodel.crud;

import com.senac.seriadomodel.infra.Propriedades;
import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import org.apache.log4j.Logger;

/**
 * Classe de persistência genérica para os beans utilizados no projeto.
 *
 * @author lossurdo
 */
public abstract class CrudGenericoBD<T> implements CrudGenerico<T> {

    private static final Logger logger = Logger.getLogger(CrudGenericoBD.class);
    
    protected EntityManager createEntityManager() {
        logger.debug("Estabelecendo conexão com o banco de dados via JPA");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                Propriedades.getInstance().get("persistenceUnitName")
        );
        EntityManager em = emf.createEntityManager();
        logger.debug("Conexão estabelecida com sucesso");
        return em;
    }
    
    @Override
    public T salvar(T bean) {
        logger.debug("Salvando " + bean);
        EntityManager em = createEntityManager();
        em.getTransaction().begin();        
        em.persist(bean);
        em.flush();
        em.getTransaction().commit();
        em.close();
        return bean;
    }

    @Override
    public T alterar(T bean) {
        logger.debug("Alterando " + bean);
        EntityManager em = createEntityManager();
        em.getTransaction().begin();        
        em.merge(bean);
        em.getTransaction().commit();        
        em.close();
        return bean;
    }

    @Override
    public boolean excluir(T bean) {
        logger.debug("Excluindo " + bean);
        T obj = consultar(bean);
        EntityManager em = createEntityManager();
        em.getTransaction().begin();
        em.remove(obj);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public T consultar(T bean) {
        logger.debug("Consultando " + bean);
        try {
            Object valorPK = descobrirValorPK(bean);
            if (valorPK == null) {
                return null;
            }
            EntityManager em = createEntityManager();
            Object obj = em.find(bean.getClass(), valorPK);
            em.close();
            return (T) obj;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Utilizado para executar qualquer NamedQuery
     * criada nos beans.
     * 
     * @param namedQuery
     * @return 
     */
    public List<T> namedQuery(String namedQuery) {
        EntityManager em = createEntityManager();        
        List lista = em.createNamedQuery(namedQuery).getResultList();
        em.close();        
        return lista;
    }
    
    /**
     * Percorre os atributos de uma classe a procura daquele anotado com @Id; Ao
     * encontrar, retorna o valor setado para este atributo.
     *
     * @param bean
     * @return Conteúdo da PK
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private Object descobrirValorPK(T bean) throws IllegalArgumentException, IllegalAccessException {
        Class<? extends Object> clazz = bean.getClass();
        for (Field f : clazz.getDeclaredFields()) {
            if (f.isAnnotationPresent(Id.class)) {
                f.setAccessible(true);
                Object valorPK = f.get(bean);
                logger.debug("Valor PK encontrado: " + valorPK);
                return valorPK;
            }
        }
        return null;
    }

}
