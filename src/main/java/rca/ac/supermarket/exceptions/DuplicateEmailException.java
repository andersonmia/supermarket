package rca.ac.supermarket.exceptions;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuplicateEmailException extends RuntimeException {
    private final String resourceName;
    private String fieldName;
    private Object fieldValue;

    public DuplicateEmailException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s with %s : '%s' already exists", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
