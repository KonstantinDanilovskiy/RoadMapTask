package validator;

import entity.ObjectWithName;
import java.util.List;

public interface Rule<T extends ObjectWithName> {
    void validate(T object, List<String> errors);
}
