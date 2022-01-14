package me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Validators;

import java.util.HashMap;

import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.RollerCoaster;

public class RollerCoasterValidator extends Validator
{
    private HashMap<String, String[]> errors = new HashMap<String, String[]>();

    public boolean isValid(RollerCoaster rollerCoaster)
    {
        this.errors.clear();

        // Validate name
        if(this.stringIsEmpty(rollerCoaster.getName()))
            this.errors.put("name", new String[] {"Name is empty"});

        if(!this.stringIsNotLongerThan(60, rollerCoaster.getName()) && !this.errors.containsKey("name"))
            this.errors.put("name", new String[] {"Name is longer than 60 characters"});

        // Validate park
        if(this.stringIsEmpty(rollerCoaster.getPark()))
            this.errors.put("park", new String[] {"Park is empty"});

        if(!this.stringIsNotLongerThan(128, rollerCoaster.getPark()) && !this.errors.containsKey("park"))
            this.errors.put("park", new String[] {"Park is longer than 128 characters"});

        return this.errors.size() == 0 ? true : false;
    }

    public HashMap<String, String[]> getErrors()
    {
        return this.errors;
    }
}
