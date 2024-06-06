package rca.ac.supermarket.exceptions;

public class ResourceNotFoundException extends  RuntimeException{
    public String property;
    public String value;
    public String entity;

    public String message;


    public ResourceNotFoundException(String entity, String value, String property){
        super(String.format("%s with %s %s not found", entity, property, value));
        this.entity = entity;
        this.value = value;
        this.property = property;
    }

}
