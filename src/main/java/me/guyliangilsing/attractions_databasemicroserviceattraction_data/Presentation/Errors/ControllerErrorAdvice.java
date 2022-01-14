package me.guyliangilsing.attractions_databasemicroserviceattraction_data.Presentation.Errors;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javassist.NotFoundException;

@Component
@ControllerAdvice
public class ControllerErrorAdvice
{
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exception)
    {
        Map<String, String> responseBody = new HashMap<String, String>();

        responseBody.put("time", LocalTime.now().toString());
        responseBody.put("message", exception.getMessage());

        return new ResponseEntity<Object>(responseBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NameAndParkCombinationNotUniqueException.class)
    public ResponseEntity<Object> handleGeneralErrorExceptions(Exception exception)
    {
        Map<String, String> responseBody = new HashMap<String, String>();

        responseBody.put("time", LocalTime.now().toString());
        responseBody.put("message", exception.getMessage());

        return new ResponseEntity<Object>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationErrorException.class)
    public ResponseEntity<Object> handleValidationErrors(ValidationErrorException exception)
    {
        Map<String, Object> responseBody = new HashMap<String, Object>();

        responseBody.put("time", LocalTime.now().toString());
        responseBody.put("message", exception.getMessage());
        responseBody.put("errors", exception.getValidationErrors());

        return new ResponseEntity<Object>(responseBody, HttpStatus.BAD_REQUEST);
    }
}
