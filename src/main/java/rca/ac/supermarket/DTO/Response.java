package rca.ac.supermarket.DTO;

import lombok.Getter;
import lombok.Setter;
import rca.ac.supermarket.enums.ResponseType;

@Getter
@Setter
public class Response {
    private ResponseType responseType;
    private String message;
    private Object payload;

    public Response setResponseType(ResponseType responseType) {
        this.responseType = responseType;
        return this;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    public Response setPayload(Object payload) {
        this.payload = payload;
        return this;
    }
}
