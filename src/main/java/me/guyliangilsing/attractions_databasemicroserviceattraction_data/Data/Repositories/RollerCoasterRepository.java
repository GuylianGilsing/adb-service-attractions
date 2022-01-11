package me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Entities.RollerCoasterEntity;

@Repository
public interface RollerCoasterRepository extends CrudRepository<RollerCoasterEntity, Long>{
    @Query(value = "SELECT * FROM `rollercoasters` WHERE name LIKE ?1", nativeQuery = true)
    public List<RollerCoasterEntity> findAllByName(String name);

    @Query(value = "SELECT * FROM `rollercoasters` WHERE park LIKE ?1", nativeQuery = true)
    public List<RollerCoasterEntity> findAllByPark(String park);
}
