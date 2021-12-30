package me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Mappers;

import java.util.ArrayList;
import java.util.List;

import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Entities.RollerCoasterEntity;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.RollerCoaster;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.SimpleRollerCoaster;

public class RollerCoasterMapper
{
    public static RollerCoasterEntity toEntity(RollerCoaster domainModel)
    {
        RollerCoasterEntity entity = new RollerCoasterEntity();

        entity.setId(domainModel.getId());
        entity.setName(domainModel.getName());
        entity.setDescription(domainModel.getDescription());
        entity.setAuthorId(domainModel.getAuthorId());
        entity.setOpeningDate(domainModel.getOpeningDate());
        entity.setStatus(domainModel.getStatus());
        entity.setPark(domainModel.getPark());
        entity.setCountry(domainModel.getCountry());
        entity.setHeight(domainModel.getHeight());
        entity.setSpeed(domainModel.getSpeed());
        entity.setElements(domainModel.getElements());
        entity.setInversions(domainModel.getInversions());

        if(domainModel.getTechnicalInformation() != null)
        {
            entity.setTechnicalInformation(
                TechnicalInformationMapper.domainListToEntityList(domainModel.getTechnicalInformation())
            );
        }

        return entity;
    }   
    
    public static RollerCoaster toDomainModel(RollerCoasterEntity entity)
    {
        RollerCoaster domainModel = new RollerCoaster();

        domainModel.setId(entity.getId());
        domainModel.setName(entity.getName());
        domainModel.setDescription(entity.getDescription());
        domainModel.setAuthorId(entity.getAuthorId());
        domainModel.setOpeningDate(entity.getOpeningDate());
        domainModel.setStatus(entity.getStatus());
        domainModel.setPark(entity.getPark());
        domainModel.setCountry(entity.getCountry());
        domainModel.setHeight(entity.getHeight());
        domainModel.setSpeed(entity.getSpeed());
        domainModel.setElements(entity.getElements());
        domainModel.setInversions(entity.getInversions());

        if(entity.getTechnicalInformation() != null)
        {
            domainModel.setTechnicalInformation(
                TechnicalInformationMapper.entityListToDomainList(entity.getTechnicalInformation())
            );
        }

        return domainModel;
    }

    public static SimpleRollerCoaster toSimpleDomainModel(RollerCoasterEntity entity)
    {
        SimpleRollerCoaster domainModel = new SimpleRollerCoaster();

        domainModel.setId(entity.getId());
        domainModel.setName(entity.getName());
        domainModel.setDescription(entity.getDescription());
        domainModel.setAuthorId(entity.getAuthorId());
        domainModel.setOpeningDate(entity.getOpeningDate());
        domainModel.setStatus(entity.getStatus());
        domainModel.setPark(entity.getPark());
        domainModel.setCountry(entity.getCountry());
        domainModel.setHeight(entity.getHeight());
        domainModel.setSpeed(entity.getSpeed());
        domainModel.setElements(entity.getElements());
        domainModel.setInversions(entity.getInversions());

        return domainModel;
    }

    public static List<RollerCoaster> entityListToDomainList(Iterable<RollerCoasterEntity> entityList)
    {
        ArrayList<RollerCoaster> domainList = new ArrayList<RollerCoaster>();

        for(RollerCoasterEntity entity : entityList)
        {
            RollerCoaster domainModel = toDomainModel(entity);
            domainList.add(domainModel);
        }

        return domainList;
    }

    public static List<SimpleRollerCoaster> entityListToSimpleDomainList(Iterable<RollerCoasterEntity> entityList)
    {
        ArrayList<SimpleRollerCoaster> domainList = new ArrayList<SimpleRollerCoaster>();

        for(RollerCoasterEntity entity : entityList)
        {
            SimpleRollerCoaster domainModel = toSimpleDomainModel(entity);
            domainList.add(domainModel);
        }

        return domainList;
    }
}
