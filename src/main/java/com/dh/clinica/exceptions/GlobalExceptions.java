package com.dh.clinica.exceptions;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.apache.log4j.Logger;


@ControllerAdvice
public class GlobalExceptions {

    private static final Logger logger = Logger.getLogger(GlobalExceptions.class);

    @ExceptionHandler({ResourceBadRequestException.class})
    public ResponseEntity<?> procesarErrorBadRequest(ResourceBadRequestException ex, WebRequest request) {
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error manejado por excepción Handler" + ex.getMessage());

    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<?> procesarErrorNotFound(ResourceNotFoundException ex, WebRequest request) {
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error manejado por excepción Handler" + ex.getMessage());

    }


    @ExceptionHandler
    public ResponseEntity<?> globleExceptionHandler(Exception ex, WebRequest request){
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error manejado por excepción Handler" + ex.getMessage());
    }


}
