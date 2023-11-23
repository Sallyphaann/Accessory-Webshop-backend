package fontys.sem3.school.accesoryweb.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailAlreadyExisitsException extends ResponseStatusException {
    public EmailAlreadyExisitsException(){super(HttpStatus.BAD_REQUEST, "EMAIL_ALREADY_EXISTS");

    }
}
