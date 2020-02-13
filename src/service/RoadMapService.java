package service;

import entity.City;
import entity.Road;
import java.util.Collection;

public interface RoadMapService {
    boolean addCity(City city);

    boolean addRoad(Road road, String cityName);

    boolean removeRoad(Road road);

    City getCityByName(String cityName);

    Collection<Road> getRoadsByCityName(String cityName);
}
