package me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Entities.RollerCoasterEntity;

@Repository
public interface RollerCoasterRepository extends CrudRepository<RollerCoasterEntity, Long>{
    
}
