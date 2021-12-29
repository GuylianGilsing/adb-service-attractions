package me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models;

public class TechnicalInformation
{
    private Long id = 0l;
    private String name;
    private String value;

    public TechnicalInformation(){}
    
    public TechnicalInformation(Long id, String name, String value)
    {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public TechnicalInformation(String name, String value)
    {
        this.name = name;
        this.value = value;
    }

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getValue()
    {
        return this.value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }    
}
