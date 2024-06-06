package rw.gov.moh.rhip.v1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rw.gov.moh.rhip.v1.dto.responses.ErrorResponse;
import rw.gov.moh.rhip.v1.dto.responses.Response;
import rw.gov.moh.rhip.v1.enums.ResponseType;

import java.util.ArrayList;
import java.util.List;

public class InternalServerErrorException  extends RuntimeException{
    public InternalServerErrorException(String message){
        super(message);
    }

    public ResponseEntity<?> getResponse(){
        List<String> details = new ArrayList<>();
        ErrorResponse errorResponse = new ErrorResponse().setMessage(getMessage()).setDetails(details);
        Response<ErrorResponse> response = new Response<>();
        response.setType(ResponseType.LOGIN_FAILED);
        response.setPayload(errorResponse);
        return new ResponseEntity<Response>(response , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}