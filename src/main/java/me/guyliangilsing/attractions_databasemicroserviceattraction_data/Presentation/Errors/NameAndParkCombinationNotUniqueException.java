package me.guyliangilsing.attractions_databasemicroserviceattraction_data.Presentation.Errors;

public class NameAndParkCombinationNotUniqueException extends Exception
{
    public NameAndParkCombinationNotUniqueException(String errorMessage)
    {
        super(errorMessage);
    }
}
