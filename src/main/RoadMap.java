package main;

import entity.City;
import entity.Road;
import service.RoadMapServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoadMap {
    public static void main(String[] args) {
        RoadMapServiceImpl roadMapService = new RoadMapServiceImpl();
        City moscow = new City("Moscow", 123.4, 2345.6);
        if (roadMapService.addCity(moscow)) {
            System.out.println("City is added to storage:" + moscow.toString());
        }
        City moscowNew = new City("Moscow", 1231.4, 235.6);
        City nullCityName = new City(null, 1231.4, 235.6);
        City negativeCity = new City("A", -1231.4, 235.6);
        City piter = new City("Peterburg", 12344.45, 233245.16);
        List<City> cityList = new ArrayList<>(Arrays.asList(moscowNew, nullCityName, negativeCity, piter));
        cityList.forEach(roadMapService::addCity);
        roadMapService.getStorage().forEach((x, y) -> System.out.println(x + "   " + y.toString()));

        Road roadM = new Road("M1", 100);
        roadMapService.addRoad(roadM, "Moscow");
        roadMapService.addRoad(roadM, "Moscow");
        System.out.println(roadMapService.getRoadsByCityName("Moscow").toString());
    }
}
