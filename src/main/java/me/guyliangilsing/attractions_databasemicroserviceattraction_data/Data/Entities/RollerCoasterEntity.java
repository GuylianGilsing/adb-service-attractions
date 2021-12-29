package me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "rollercoasters")
public class RollerCoasterEntity extends AttractionEntity
{
    private Double height;
    private Double speed;
    private String elements;
    private String inversions;
    
    @OneToMany(
        cascade = CascadeType.ALL,
        targetEntity = TechnicalInformationEntity.class,
        fetch = FetchType.EAGER,
        orphanRemoval = false
    )
    @JoinTable(
        name = "rollercoaster_technical_information",
        joinColumns = {@JoinColumn(table = "rollercoasters", name = "rollercoaster_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(table = "technical_information", name = "technical_information_id", referencedColumnName = "id")}
    )
    private List<TechnicalInformationEntity> technicalInformation;

    public RollerCoasterEntity(){}

    public RollerCoasterEntity(Double height, Double speed, String elements, String inversions, List<TechnicalInformationEntity> technicalInformation)
    {
        this.height = height;
        this.speed = speed;
        this.elements = elements;
        this.inversions = inversions;
        this.technicalInformation = technicalInformation;
    }

    public RollerCoasterEntity(Double height, Double speed, String elements, String inversions)
    {
        this.height = height;
        this.speed = speed;
        this.elements = elements;
        this.inversions = inversions;
    }

    public Double getHeight()
    {
        return this.height;
    }

    public void setHeight(Double height)
    {
        this.height = height;
    }

    public Double getSpeed()
    {
        return this.speed;
    }

    public void setSpeed(Double speed)
    {
        this.speed = speed;
    }

    public String getElements()
    {
        return this.elements;
    }

    public void setElements(String elements)
    {
        this.elements = elements;
    }

    public String getInversions()
    {
        return this.inversions;
    }

    public void setInversions(String inversions)
    {
        this.inversions = inversions;
    }

    public List<TechnicalInformationEntity> getTechnicalInformation()
    {
        return this.technicalInformation;
    }

    public void setTechnicalInformation(List<TechnicalInformationEntity> technicalInformation)
    {
        this.technicalInformation = technicalInformation;
    }
}
