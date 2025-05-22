package com.casdeveloper.model;

import com.casdeveloper.model.util.FileUtilities;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDao {

    public EntityManager entityManager;
    private Class entityClass;

    public void setClass(Class entityClass) {
        this.entityClass = entityClass;
    }

    public Class getEntityClass() {
        return entityClass;
    }


    protected void save(Object entity) {
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    protected void update(Object entity) {
        entityManager.getTransaction().begin();
        try {
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    protected void delete(Object entity) {
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public List findAll() throws Exception {
        return entityManager.createQuery("from " + entityClass.getSimpleName()).getResultList();
    }

    public Object findByName(String name) {
        return entityManager.createQuery("from " + entityClass.getSimpleName() + " p where p.name = : name")
                .setParameter("name", name).getSingleResult();
    }

    public Object findById(long id) {
        return entityManager.find(entityClass, id);
    }

}
