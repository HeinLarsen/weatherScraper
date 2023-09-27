package org.weatherScrape.dao;

import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public interface IDAOGeneric<T> {
        T save(T entity);

        T getById(int id);

        List<T> getAll();

        void saveAll(List<T> list);

        void setEntityManagerFactory(EntityManagerFactory factory);
}
