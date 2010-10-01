package com.geoffwebb.demo.data;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public abstract class GenericHibernateDAO<T, ID extends Serializable> 
{
    private Class<T> persistentClass;

    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public GenericHibernateDAO() 
    {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public T save(T entity)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        sessionFactory.getCurrentSession().flush();
        return entity;
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(T entity)
    {
        sessionFactory.getCurrentSession().delete(entity);
        sessionFactory.getCurrentSession().flush();
    }
    
    @SuppressWarnings("unchecked")
    public T findById(ID id)
    {
        return (T) sessionFactory.getCurrentSession().get(persistentClass, id);
    }

    public List<T> findAll() {
        return findByCriteria();
    }

    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion... criterion) {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(persistentClass);
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
    }
}
