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
    
    private final EntityManagerFactory emf;
    private final EntityManager em;

    public CrudGenericoBD() {
        logger.debug("Estabelecendo conexão com o banco de dados via JPA");
        emf = Persistence.createEntityManagerFactory(
                Propriedades.getInstance().get("persistenceUnitName")
        );
        em = emf.createEntityManager();
        logger.debug("Conexão estabelecida com sucesso");
    }

    @Override
    public T salvar(T bean) {
        logger.debug("Salvando/Alterando " + bean);
        Object valorPK = null;
        try {
            valorPK = descobrirValorPK(bean);
        } catch (Exception e) {
            logger.error("", e);
        }

        em.getTransaction().begin();        
        if (valorPK == null) { // incluindo novo registro
            em.persist(bean);
            em.flush();
        } else { // alterando registro existente
            em.merge(bean);
        }        
        em.getTransaction().commit();        
        
        return bean;
    }

    @Override
    public boolean excluir(T bean) {
        logger.debug("Excluindo " + bean);
        T obj = consultar(bean);
        em.getTransaction().begin();
        em.remove(obj);
        em.getTransaction().commit();
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
            return (T) em.find(bean.getClass(), valorPK);
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
        return em.createNamedQuery(namedQuery).getResultList();
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

    protected EntityManager getEntityManager() {
        return em;
    }
        
}
