package me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Entities.RollerCoasterEntity;

public class CustomRollerCoasterRepositoryImpl implements CustomRollerCoasterRepository
{
    @Autowired
    private EntityManager entityManager;

    public List<RollerCoasterEntity> findRollerCoasterEntityByLooseName(String name)
    {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

        // Tell the criteria builder which entity is going to be used
        CriteriaQuery<RollerCoasterEntity> query = criteriaBuilder.createQuery(RollerCoasterEntity.class);
        Root<RollerCoasterEntity> rollerCoasterEntity = query.from(RollerCoasterEntity.class);

        // Create the query clauses
        Predicate nameClause = criteriaBuilder.like(criteriaBuilder.lower(rollerCoasterEntity.get("name")), "%" + name.toLowerCase() + "%");

        // Create the query
        query.select(rollerCoasterEntity).where(nameClause);
        TypedQuery<RollerCoasterEntity> builtQuery = entityManager.createQuery(query);
        
        // Execute and return the results
        return builtQuery.getResultList();
    }

    public List<RollerCoasterEntity> findRollerCoasterEntityByLoosePark(String park)
    {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

        // Tell the criteria builder which entity is going to be used
        CriteriaQuery<RollerCoasterEntity> query = criteriaBuilder.createQuery(RollerCoasterEntity.class);
        Root<RollerCoasterEntity> rollerCoasterEntity = query.from(RollerCoasterEntity.class);

        // Create the query clauses
        Predicate parkClause = criteriaBuilder.like(criteriaBuilder.lower(rollerCoasterEntity.get("park")), "%" + park.toLowerCase() + "%");

        // Create the query
        query.select(rollerCoasterEntity).where(parkClause);
        TypedQuery<RollerCoasterEntity> builtQuery = entityManager.createQuery(query);
        
        // Execute and return the results
        return builtQuery.getResultList();
    }

    public RollerCoasterEntity findRollerCoasterEntityByNameAndPark(String name, String park)
    {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

        // Tell the criteria builder which entity is going to be used
        CriteriaQuery<RollerCoasterEntity> query = criteriaBuilder.createQuery(RollerCoasterEntity.class);
        Root<RollerCoasterEntity> rollerCoasterEntity = query.from(RollerCoasterEntity.class);

        // Create the query clauses
        Predicate nameClause = criteriaBuilder.equal(criteriaBuilder.lower(rollerCoasterEntity.get("name")), name.toLowerCase());
        Predicate parkClause = criteriaBuilder.equal(criteriaBuilder.lower(rollerCoasterEntity.get("park")), park.toLowerCase());

        // Create the query
        query.select(rollerCoasterEntity).where(nameClause, parkClause);
        TypedQuery<RollerCoasterEntity> builtQuery = entityManager.createQuery(query);
        
        // Execute and return the results
        try
        {
            return builtQuery.getSingleResult();
        }
        catch(Exception e)
        {
            return null;
        }
    }
}
