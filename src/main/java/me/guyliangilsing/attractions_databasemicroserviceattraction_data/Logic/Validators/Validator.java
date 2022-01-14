package me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Validators;

public class Validator
{
    protected String strTargetWhitespaceRegex = "[\\n\\r\s\\t]+";

    protected boolean stringIsEmpty(String str)
    {
        if(str.length() == 0)
            return true;

        if(str.replaceAll(this.strTargetWhitespaceRegex, "").length() == 0)
            return true;

        return false;
    }

    protected boolean stringIsNotLongerThan(int characterAmount, String str)
    {
        return str.length() <= characterAmount ? true : false;
    }
}
