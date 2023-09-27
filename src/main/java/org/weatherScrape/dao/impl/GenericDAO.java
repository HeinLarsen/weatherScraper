package org.weatherScrape.dao.impl;

import jakarta.persistence.EntityManagerFactory;
import org.weatherScrape.dao.IDAOGeneric;

import java.util.List;
@SuppressWarnings("unchecked")
public class GenericDAO<T> implements IDAOGeneric<T> {

    protected static EntityManagerFactory emf;

    private final Class<T> entityClass; // Add this field
    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T save(T entity) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public T getById(int id) {
        try (var em = emf.createEntityManager()) {
            return em.find(entityClass, id); // Use entityClass here
        }
    }

    @Override
    public List<T> getAll() {
        try (var em = emf.createEntityManager()) {
            return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                    .getResultList();
        }
    }

    @Override
    public void saveAll(List<T> list) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            for (T entity : list) {
                em.persist(entity);
            }
            em.getTransaction().commit();
        }
    }

    @Override
    public void setEntityManagerFactory(EntityManagerFactory factory) {
        emf = factory;
    }
}
