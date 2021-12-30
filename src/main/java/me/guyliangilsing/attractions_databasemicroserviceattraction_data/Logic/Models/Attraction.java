package me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models;

import java.util.ArrayList;
import java.util.List;

public class Attraction extends SimpleAttraction
{
    private List<TechnicalInformation> technicalInformation = null;

    public Attraction(){}

    public Attraction(List<TechnicalInformation> technicalInformation)
    {
        this.technicalInformation = technicalInformation;
    }

    public List<TechnicalInformation> getTechnicalInformation()
    {
        return this.technicalInformation;
    }

    public void setTechnicalInformation(List<TechnicalInformation> technicalInformation)
    {
        this.technicalInformation = technicalInformation;
    }

    public void addTechnicalInformation(TechnicalInformation domainModel)
    {
        ArrayList<TechnicalInformation> technicalInformation = this.getMutableTechnicalInformationList();

        technicalInformation.add(domainModel);
        this.technicalInformation = technicalInformation;
    }

    public void removeTechnicalInformation(Long technicalInformationID)
    {
        ArrayList<TechnicalInformation> technicalInformation = this.getMutableTechnicalInformationList();

        for(int i = 0; i < technicalInformation.size(); i += 1)
        {
            TechnicalInformation techInfo = technicalInformation.get(i);

            if(techInfo.getId() == technicalInformationID)
            {
                technicalInformation.remove(i);
                break;
            }
        }

        this.technicalInformation = technicalInformation;
    }

    private ArrayList<TechnicalInformation> getMutableTechnicalInformationList()
    {
        ArrayList<TechnicalInformation> mutableTechnicalInformation = new ArrayList<TechnicalInformation>();

        if(this.technicalInformation == null)
            return mutableTechnicalInformation;

        for(int i = 0; i < this.technicalInformation.size(); i += 1)
        {
            mutableTechnicalInformation.add(this.technicalInformation.get(i));
        }

        return mutableTechnicalInformation;
    }

    @Override
    public String toString()
    {
        return "{" +
            " technicalInformation='" + getTechnicalInformation() + "'" +
            "}";
    }
}
