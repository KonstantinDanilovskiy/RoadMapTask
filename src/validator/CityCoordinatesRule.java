package validator;

import entity.City;
import entity.ObjectWithName;

import java.util.List;

public class CityCoordinatesRule implements Rule<City> {
    @Override
    public void validate(City object, List<String> errors) {
        if (object.getX() < 0 || object.getY() < 0) {
            errors.add("Coordinates can not be negative.");
        }
    }
}
