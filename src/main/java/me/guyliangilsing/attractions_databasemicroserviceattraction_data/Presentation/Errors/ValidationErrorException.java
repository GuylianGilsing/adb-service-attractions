package me.guyliangilsing.attractions_databasemicroserviceattraction_data.Presentation.Errors;

import java.util.HashMap;

public class ValidationErrorException extends Exception
{
    private final HashMap<String, String[]> errors;

    public ValidationErrorException(String errorMessage, HashMap<String, String[]> errors)
    {
        super(errorMessage);
        this.errors = errors;
    }

    public HashMap<String, String[]> getValidationErrors()
    {
        return this.errors;
    }
}
