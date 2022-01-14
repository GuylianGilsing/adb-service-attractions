package me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Repositories;

import java.util.List;

import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Entities.RollerCoasterEntity;

public interface CustomRollerCoasterRepository
{
    public List<RollerCoasterEntity> findRollerCoasterEntityByLooseName(String name);
    public List<RollerCoasterEntity> findRollerCoasterEntityByLoosePark(String park);
    public RollerCoasterEntity findRollerCoasterEntityByNameAndPark(String name, String park);
}
