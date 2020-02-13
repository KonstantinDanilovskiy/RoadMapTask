package service;

import entity.City;
import entity.Road;
import validator.CityCoordinatesRule;
import validator.RoadLengthRule;
import validator.NameRule;
import validator.ObjectRule;
import validator.Validator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class RoadMapServiceImpl implements RoadMapService {
    private static ConcurrentHashMap<String, City> storage = new ConcurrentHashMap<>();
    private static final Logger logger = Logger.getLogger(RoadMapServiceImpl.class.getName());
    private static final String CITY_NAME_IS_NOT_UNIQUE = "City with such name %s already in the storage";
    private static final String CITY_NOT_FOUND = "City with such name %s is not fount in the storage";
    private static final String ROAD_NOT_FOUND = "Road with such name is not fount in the storage";
    private static final String CITY_ALREADY_HAS_THIS_ROAD = "City with name %S already has road %s";

    @Override
    public boolean addCity(City city) {
        Validator addCityValidator = new Validator(Arrays.asList(new ObjectRule(), new NameRule(), new CityCoordinatesRule()));
        List<String> errors = addCityValidator.validate(city);
        if (errors.isEmpty()) {
            if (addCityValidator.validateIsUnique(city, storage)) {
                storage.put(city.getName(), city);
                return true;
            }
            logger.warning(String.format(CITY_NAME_IS_NOT_UNIQUE, city.getName()));
            return false;
        }
        logger.warning(String.join("/n", errors));
        return false;
    }

    @Override
    public boolean addRoad(Road road, String cityName) {
        if (cityName != null && storage.containsKey(cityName)) {
            Validator addRoadValidator = new Validator(Arrays.asList(new ObjectRule(), new NameRule(), new RoadLengthRule()));
            List<String> errors = addRoadValidator.validate(road);
            if (errors.isEmpty()) {
                if (addRoadValidator.validateIsUnique(road, storage)) {
                    if (!storage.get(cityName).getCityRoads().contains(road)) {
                        storage.get(cityName).getCityRoads().add(road);
                        return true;
                    }
                    logger.warning(String.format(CITY_ALREADY_HAS_THIS_ROAD, cityName, road.getName()));
                    return false;
                }
            }
            logger.warning(String.join("/n", errors));
            return false;
        }
        logger.warning(String.format(CITY_NOT_FOUND, cityName));
        return false;
    }

    @Override
    public boolean removeRoad(Road road) {
        Validator removeRoadValidator = new Validator();
        if (removeRoadValidator.validateIfPresent(road, storage)) {
            deleteRoad(road);
            return true;
        }
        logger.warning(String.format(ROAD_NOT_FOUND));
        return false;
    }

    @Override
    public City getCityByName(String cityName) {
        if (cityName != null && storage.containsKey(cityName)) {
            return storage.get(cityName);
        }
        logger.warning(String.format(CITY_NOT_FOUND, cityName));
        return null;
    }

    @Override
    public Collection<Road> getRoadsByCityName(String cityName) {
        if (cityName != null && storage.containsKey(cityName)) {
            return storage.get(cityName).getCityRoads();
        }
        logger.warning(String.format(CITY_NOT_FOUND, cityName));
        return Collections.emptyList();
    }

    private void deleteRoad(Road road) {
        storage.values()
               .forEach(city -> {
                   city.getCityRoads().remove(road);
               });
    }

    public ConcurrentHashMap<String,City> getStorage() {
        return storage;
    }
}
