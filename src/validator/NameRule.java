package validator;

import entity.ObjectWithName;

import java.util.List;

public class NameRule implements Rule<ObjectWithName> {
    @Override
    public void validate(ObjectWithName object, List<String> errors) {
        if (object.getName() == null) {
            errors.add("Name can not be null.");
        } else if (object.getName().isEmpty()) {
            errors.add("Name can not be empty.");
        }
    }
}
