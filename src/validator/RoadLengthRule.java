package validator;

import entity.Road;

import java.util.List;

public class RoadLengthRule implements Rule<Road> {
    @Override
    public void validate(Road object, List<String> errors) {
        if (object.getLength() < 0) {
            errors.add("Road length can not be negative.");
        }
    }
}
