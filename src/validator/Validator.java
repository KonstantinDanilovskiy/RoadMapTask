package validator;

import entity.City;
import entity.ObjectWithName;
import entity.Road;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class Validator {
    private List<Rule> rules;

    public Validator() {
    }

    public Validator(List<Rule> rules) {
        this.rules = rules;
    }

    public <T extends ObjectWithName> List<String> validate(T object) {
        List<String> errors = new ArrayList<>();
        rules.forEach(rule -> rule.validate(object, errors));
        return errors;
    }

    public <T extends ObjectWithName> boolean validateIsUnique(T object, Map<String, City> storage) {
        if (object instanceof City) {
            return !storage.containsKey(object.getName());
        }
        if (object instanceof Road) {
            return storage.values()
                       .stream()
                       .flatMap(city -> {
                           if (city.getCityRoads() != null) {
                               return city.getCityRoads().stream();
                           } else {
                               return Stream.empty();
                           }})
                       .filter(road -> road.equals(object))
                       .count() < 2;
        }
        throw new IllegalArgumentException("Received object is unsupported.");
    }

    public <T extends ObjectWithName> boolean validateIfPresent(T object, Map<String, City> storage) {
        if (object instanceof City) {
            return storage.containsKey(object.getName());
        }
        if (object instanceof Road) {
            return storage.values()
                          .stream()
                          .flatMap(city -> {
                              if (city.getCityRoads() != null) {
                                  return city.getCityRoads().stream();
                              } else {
                                  return Stream.empty();
                              }})
                          .anyMatch(road -> road.equals(object));
        }
        throw new IllegalArgumentException("Received object is unsupported.");
    }

}
