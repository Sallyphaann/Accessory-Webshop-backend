package fontys.sem3.school.accesoryweb.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidCategoryException extends ResponseStatusException {
    public InvalidCategoryException() {
        super(HttpStatus.BAD_REQUEST, "Category_INVALID");
    }

    public InvalidCategoryException(String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
