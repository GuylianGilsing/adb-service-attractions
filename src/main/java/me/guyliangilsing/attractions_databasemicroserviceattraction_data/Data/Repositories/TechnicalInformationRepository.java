package me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Entities.TechnicalInformationEntity;

@Repository
public interface TechnicalInformationRepository extends CrudRepository<TechnicalInformationEntity, Long>{
    
}
