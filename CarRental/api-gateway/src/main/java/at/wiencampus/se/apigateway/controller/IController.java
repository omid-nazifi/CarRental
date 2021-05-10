package at.wiencampus.se.apigateway.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class IController {

    @ExceptionHandler({DataAccessException.class})
    public ResponseEntity<String> handleDataException() {
        return new ResponseEntity<>("An error occurred while accessing the data!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
