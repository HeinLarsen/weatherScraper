package org.weatherScrape.dao.impl;

import jakarta.persistence.EntityManagerFactory;
import org.weatherScrape.dao.IDAOGeneric;

import java.util.List;
@SuppressWarnings("unchecked")
public class GenericDAO<T> implements IDAOGeneric<T> {

    protected static EntityManagerFactory emf;

    @Override
    public void save(T entity) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }
    }

    @Override
    public T getById(int id) {
        try (var em = emf.createEntityManager()) {
            return em.find((Class<T>) this.getClass(), id);
        }
    }

    @Override
    public T update(T entity) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public List<T> getAll() {
        try (var em = emf.createEntityManager()) {
            return em.createQuery("SELECT e FROM " + this.getClass().getSimpleName() + " e", (Class<T>) this.getClass())
                    .getResultList();
        }
    }

    @Override
    public void setEntityManagerFactory(EntityManagerFactory factory) {
        emf = factory;
    }
}
