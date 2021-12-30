package me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SimpleAttraction
{
    private Long id = null;
    private String name;
    private String description;
    private Long authorId;
    private String openingDate;
    private short status;
    private String park;
    private String country;

    public SimpleAttraction(){}

    public SimpleAttraction(Long id, String name, String description, Long authorId, String openingDate, short status, String park, String country)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.authorId = authorId;
        this.openingDate = openingDate;
        this.status = status;
        this.park = park;
        this.country = country;
    }
    
    public SimpleAttraction(String name, String description, Long authorId, String openingDate, short status, String park, String country)
    {
        this.name = name;
        this.description = description;
        this.authorId = authorId;
        this.openingDate = openingDate;
        this.status = status;
        this.park = park;
        this.country = country;
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

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Long getAuthorId()
    {
        return this.authorId;
    }

    public void setAuthorId(Long authorId)
    {
        this.authorId = authorId;
    }

    public String getOpeningDate()
    {
        return this.openingDate;
    }

    public void setOpeningDate(String openingDate)
    {
        this.openingDate = openingDate;
    }

    public void setOpeningDate(LocalDate openingDate)
    {
        if(openingDate != null)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.openingDate = openingDate.format(formatter);
        }
    }

    public short getStatus()
    {
        return this.status;
    }

    public void setStatus(short status)
    {
        this.status = status;
    }

    public String getPark()
    {
        return this.park;
    }

    public void setPark(String park)
    {
        this.park = park;
    }

    public String getCountry()
    {
        return this.country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    @Override
    public String toString()
    {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", authorId='" + getAuthorId() + "'" +
            ", openingDate='" + getOpeningDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", park='" + getPark() + "'" +
            ", country='" + getCountry() + "'" +
            "}";
    }
}
