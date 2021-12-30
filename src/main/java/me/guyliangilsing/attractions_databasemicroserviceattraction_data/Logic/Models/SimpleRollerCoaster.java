package me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models;

public class SimpleRollerCoaster extends SimpleAttraction
{
    private Double height;
    private Double speed;
    private String elements;
    private String inversions;
    
    public SimpleRollerCoaster(){}

    public SimpleRollerCoaster(Double height, Double speed, String elements, String inversions)
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
}
