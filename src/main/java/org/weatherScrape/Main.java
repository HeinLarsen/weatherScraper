package org.weatherScrape;

import jakarta.persistence.EntityManagerFactory;
import org.weatherScrape.config.HibernateConfig;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    }
}