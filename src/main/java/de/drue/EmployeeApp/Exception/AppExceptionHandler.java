package de.drue.EmployeeApp.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Date;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ReturnError> handleResourceNotFoundException(final ResourceNotFoundException resourceNotFoundException,
                                                                       final HttpServletRequest request){
        return new ResponseEntity<>(ReturnError.builder()
                .message(resourceNotFoundException.getMessage())
                .timestamp(new Date())
                .path(request.getRequestURI())
                .status(HttpStatus.NOT_FOUND)
                .build(),
                HttpStatus.NOT_FOUND);
    }
}
