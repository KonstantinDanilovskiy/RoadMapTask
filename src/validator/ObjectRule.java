package validator;

import entity.ObjectWithName;

import java.util.List;

public class ObjectRule implements Rule<ObjectWithName> {
    @Override
    public void validate(ObjectWithName object, List<String> errors) {
        if (object == null) {
            errors.add("Object can not be null.");
        }
    }
}
