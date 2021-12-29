package me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "technical_information")
public class TechnicalInformationEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0l;
    private String name;
    private String value;

    public TechnicalInformationEntity(){}

    public TechnicalInformationEntity(Long id, String name, String value)
    {
        this.id = id;
        this.name = name;
        this.value = value;
    }
    
    public TechnicalInformationEntity(String name, String value)
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
