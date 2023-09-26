package org.weatherScrape.dao;

import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public interface IDAOGeneric<T> {
        void save(T entity);

        T getById(int id);

        T update(T entity);

        List<T> getAll();

        void setEntityManagerFactory(EntityManagerFactory factory);
}
