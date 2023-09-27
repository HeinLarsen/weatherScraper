package org.weatherScrape.dao;

import org.weatherScrape.entitiy.Region;

import java.util.List;

public interface IRegionDAO extends IDAOGeneric<Region> {

     List<Region> getByCountryISO(String iso);

}
