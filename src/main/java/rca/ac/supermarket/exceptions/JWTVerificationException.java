package rw.gov.moh.rhip.v1.exceptions;

public class JWTVerificationException extends RuntimeException {
    public JWTVerificationException(String message) {
        this(message, null);
    }

    public JWTVerificationException(String message, Throwable cause) {
            super(message, cause);
    }
}
