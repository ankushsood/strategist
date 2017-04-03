package com.chikara.strategist.dao;

import com.chikara.strategist.entity.Entity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JpaDao<T extends Entity, I> implements Dao<T, I>
{
    private EntityManager entityManager;

    protected Class<T> entityClass;

    public JpaDao(Class<T> entityClass)
    {
        this.entityClass = entityClass;
    }

    public EntityManager getEntityManager()
    {
        return this.entityManager;
    }

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    public void setEntityManager(final EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll()
    {
        final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<T> criteriaQuery = builder.createQuery(this.entityClass);

        criteriaQuery.from(this.entityClass);

        TypedQuery<T> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public T find(I id)
    {
        return this.getEntityManager().find(this.entityClass, id);
    }

    @Override
    @Transactional
    public T save(T entity)
    {
        return this.getEntityManager().merge(entity);
    }

    @Override
    @Transactional
    public void delete(I id)
    {
        if (id == null) {
            return;
        }

        T entity = this.find(id);
        if (entity == null) {
            return;
        }

        this.getEntityManager().remove(entity);
    }

    @Override
    @Transactional
    public void delete(T entity)
    {
        this.getEntityManager().remove(entity);
    }
    
    public Map<String, Object> mapDatabaseResponse(String [] header, Object[] data){
    	Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    	for (int i = 0; i < header.length; i++) {
			resultMap.put(header[i], data[i]);
		}
    	return resultMap;
    	
    }
}
