package com.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BlogNotFoundException.class)
	public ResponseEntity<String> handleBlogNotFoundException(BlogNotFoundException exception) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}

//	@ExceptionHandler(InvalidDataException.class)
//	public ResponseEntity<String> handleInvalidDataException(InvalidDataException exception) {
//	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
//	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException exception) {
	    Map<String, String> validationErrors = new HashMap<>();
	    exception.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        validationErrors.put(fieldName, errorMessage);
	    });
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors);
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + exception.getMessage());
    }

}
