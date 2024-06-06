package rca.ac.supermarket.utils;


import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import rca.ac.supermarket.DTO.Response;
import rca.ac.supermarket.enums.ResponseType;
import rca.ac.supermarket.exceptions.AuthenticationFailedException;
import rca.ac.supermarket.exceptions.DuplicateEmailException;
import rca.ac.supermarket.exceptions.InternalServerErrorException;
import rca.ac.supermarket.exceptions.ResourceNotFoundException;

public class ExceptionHandlerUtil {

    public static ResponseEntity<Response> handleException(Exception e) {
        if(e instanceof ResourceNotFoundException) {
            return ResponseEntity.status(404).body(new Response().setResponseType(ResponseType.RESOURCE_NOT_FOUND).setPayload(e.getMessage()));
        } else if (e instanceof DuplicateEmailException) {
            return ResponseEntity.status(400).body(new Response().setResponseType(ResponseType.BAD_REQUEST).setPayload(e.getMessage()));
        } else if (e instanceof AuthenticationFailedException) {
            return ResponseEntity.status(401).body(new Response().setResponseType(ResponseType.UNAUTHORIZED).setPayload(e.getMessage()));
        }  else {
            return ResponseEntity.status(500).body(new Response().setResponseType(ResponseType.INTERNAL_SERVER_ERROR).setPayload(e.getMessage()));
        }

    }
}
