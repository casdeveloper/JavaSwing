package com.casdeveloper.model;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionDatabase {

    private static String unitPersistence = "unitHB";
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    public ConnectionDatabase(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public ConnectionDatabase(){
        entityManagerFactory = Persistence.createEntityManagerFactory(unitPersistence);
        entityManager = entityManagerFactory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void beginTransaction(){
        entityManager.getTransaction().begin();
    }

    public void commitTransaction(){
        entityManager.getTransaction().commit();
    }

    public void rollbackTransaction(){
        entityManager.getTransaction().rollback();
    }

    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }


}
