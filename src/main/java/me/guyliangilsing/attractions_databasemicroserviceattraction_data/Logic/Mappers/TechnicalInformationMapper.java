package me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Mappers;

import java.util.List;
import java.util.ArrayList;

import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Entities.TechnicalInformationEntity;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.TechnicalInformation;

public class TechnicalInformationMapper
{
    public static TechnicalInformationEntity toEntity(TechnicalInformation domainModel)
    {
        TechnicalInformationEntity entity = new TechnicalInformationEntity();

        entity.setId(domainModel.getId());
        entity.setName(domainModel.getName());
        entity.setValue(domainModel.getValue());

        return entity;
    }

    public static TechnicalInformation toDomainModel(TechnicalInformationEntity entity)
    {
        TechnicalInformation domainModel = new TechnicalInformation();

        domainModel.setId(entity.getId());
        domainModel.setName(entity.getName());
        domainModel.setValue(entity.getValue());

        return domainModel;
    }

    public static List<TechnicalInformationEntity> domainListToEntityList(List<TechnicalInformation> domainList)
    {
        ArrayList<TechnicalInformationEntity> entityList = new ArrayList<TechnicalInformationEntity>();

        for(TechnicalInformation domainModel : domainList)
        {
            TechnicalInformationEntity entity = toEntity(domainModel);
            entityList.add(entity);
        }

        return entityList;
    }

    public static List<TechnicalInformation> entityListToDomainList(List<TechnicalInformationEntity> entityList)
    {
        ArrayList<TechnicalInformation> domainList = new ArrayList<TechnicalInformation>();

        for(TechnicalInformationEntity entity : entityList)
        {
            TechnicalInformation domainModel = toDomainModel(entity);
            domainList.add(domainModel);
        }

        return domainList;
    }
}
