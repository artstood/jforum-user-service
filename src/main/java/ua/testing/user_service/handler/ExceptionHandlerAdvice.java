package ua.testing.user_service.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.testing.user_service.model.error.SimpleErrorResponse;
import ua.testing.user_service.service.exception.UserNotFoundException;
import ua.testing.user_service.service.exception.UsernameTakenException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<SimpleErrorResponse> handleUserNotFound(UserNotFoundException e) {
        return new ResponseEntity<>(new SimpleErrorResponse(e.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler(UsernameTakenException.class)
    public ResponseEntity<SimpleErrorResponse> handleUsernameTaken(UsernameTakenException e) {
        return new ResponseEntity<>(new SimpleErrorResponse(e.getMessage()), BAD_REQUEST);
    }
}
