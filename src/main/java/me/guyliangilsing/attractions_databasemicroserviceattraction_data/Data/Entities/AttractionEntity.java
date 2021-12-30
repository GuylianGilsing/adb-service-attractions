package me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.lang.Nullable;

@MappedSuperclass
public class AttractionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Nullable
    private String description;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "opening_date")
    @Nullable
    private LocalDate openingDate = null;
    private short status = 0;
    private String park;
    private String country;

    public AttractionEntity(){}

    public AttractionEntity(Long id, String name, String description, Long authorId, LocalDate openingDate, short status, String park, String country)
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

    public AttractionEntity(String name, String description, Long authorId, LocalDate openingDate, short status, String park, String country)
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

    public LocalDate getOpeningDate()
    {
        return this.openingDate;
    }

    public void setOpeningDate(String openingDate)
    {
        if(openingDate.length() > 0)
        {
            this.openingDate = LocalDate.parse(openingDate);
        }
    }

    public void setOpeningDate(LocalDate openingDate)
    {
        this.openingDate = openingDate;
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
}
