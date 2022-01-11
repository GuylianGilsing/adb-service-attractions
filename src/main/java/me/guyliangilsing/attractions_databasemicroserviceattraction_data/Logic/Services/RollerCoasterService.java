package me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Entities.RollerCoasterEntity;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Entities.TechnicalInformationEntity;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Repositories.RollerCoasterRepository;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Repositories.TechnicalInformationRepository;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Mappers.RollerCoasterMapper;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.RollerCoaster;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.SimpleRollerCoaster;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.TechnicalInformation;

@Service
public class RollerCoasterService
{
    private final RollerCoasterRepository rollerCoasterRepository;
    private final TechnicalInformationRepository technicalInformationRepository;

    @Autowired
    public RollerCoasterService(RollerCoasterRepository rollerCoasterRepository, TechnicalInformationRepository technicalInformationRepository)
    {
        this.rollerCoasterRepository = rollerCoasterRepository;
        this.technicalInformationRepository = technicalInformationRepository;
    }

    public List<SimpleRollerCoaster> getAll()
    {
        Iterable<RollerCoasterEntity> rollerCoasterEntities = this.rollerCoasterRepository.findAll();
        return RollerCoasterMapper.entityListToSimpleDomainList(rollerCoasterEntities);
    }

    public List<SimpleRollerCoaster> search(String name, String park)
    {
        ArrayList<SimpleRollerCoaster> searchResults = new ArrayList<SimpleRollerCoaster>();        

        if(name.length() > 0)
        {
            List<SimpleRollerCoaster> results = this.getRollerCoastersByName(name);
            this.combineQueryResultsWithSearchResultArray(searchResults, results);
        }

        if(park.length() > 0)
        {
            List<SimpleRollerCoaster> results = this.getRollerCoastersByPark(park);
            this.combineQueryResultsWithSearchResultArray(searchResults, results);
        }

        return searchResults;
    }

    public RollerCoaster getByID(Long id) throws NotFoundException
    {
        RollerCoasterEntity entity = this.getRollerCoasterByID(id);

        return RollerCoasterMapper.toDomainModel(entity);
    }

    public RollerCoaster create(RollerCoaster rollerCoaster)
    {
        RollerCoasterEntity entity = RollerCoasterMapper.toEntity(rollerCoaster);

        this.addNewTechnicalInformationEntitiesToRollercoasterEntity(entity);
        entity = this.rollerCoasterRepository.save(entity);

        return RollerCoasterMapper.toDomainModel(entity);
    }

    public RollerCoaster update(RollerCoaster rollerCoaster) throws NotFoundException
    {
        RollerCoasterEntity entity = this.getRollerCoasterByID(rollerCoaster.getId());
        
        entity.setName(rollerCoaster.getName());
        entity.setDescription(rollerCoaster.getDescription());
        entity.setAuthorId(rollerCoaster.getAuthorId());
        entity.setOpeningDate(rollerCoaster.getOpeningDate());
        entity.setStatus(rollerCoaster.getStatus());
        entity.setPark(rollerCoaster.getPark());
        entity.setCountry(rollerCoaster.getCountry());
        entity.setHeight(rollerCoaster.getHeight());
        entity.setSpeed(rollerCoaster.getSpeed());
        entity.setElements(rollerCoaster.getElements());
        entity.setInversions(rollerCoaster.getInversions());

        this.updateTechnicalInformation(entity, rollerCoaster);
        entity = this.rollerCoasterRepository.save(entity);

        return RollerCoasterMapper.toDomainModel(entity);
    }

    public void delete(Long id) throws NotFoundException
    {
        RollerCoasterEntity entity = this.getRollerCoasterByID(id);

        // Delete all technical information entities
        for(TechnicalInformationEntity technicalInformationEntity : entity.getTechnicalInformation())
        {
            this.technicalInformationRepository.deleteById(technicalInformationEntity.getId());
        }

        this.rollerCoasterRepository.deleteById(entity.getId());
    }

    private void addNewTechnicalInformationEntitiesToRollercoasterEntity(RollerCoasterEntity entity)
    {
        if(entity.getTechnicalInformation() == null)
            return;

        for(TechnicalInformationEntity techInfoEntity : entity.getTechnicalInformation())
        {
            this.technicalInformationRepository.save(techInfoEntity);
        }
    }

    private void updateTechnicalInformation(RollerCoasterEntity entity, RollerCoaster domainModel)
    {
        List<TechnicalInformation> domainListTechnicalInformation = domainModel.getTechnicalInformation();
        List<TechnicalInformationEntity> technicalInformationEntities = entity.getTechnicalInformation();

        ArrayList<TechnicalInformationEntity> updatedTechnicalInformation = new ArrayList<TechnicalInformationEntity>();

        for(int i = 0; i < domainListTechnicalInformation.size(); i += 1)
        {
            TechnicalInformation techInfo = domainListTechnicalInformation.get(i);

            // Only selects given entities by id, and updates their names and values
            if(techInfo.getId() > 0)
            {
                boolean technicalInformationEntityHasBeenUpdated = false;

                for(int x = 0; x < technicalInformationEntities.size(); x += 1)
                {
                    TechnicalInformationEntity techInfoEntity = technicalInformationEntities.get(x);

                    if(techInfo.getId() == techInfoEntity.getId())
                    {
                        techInfoEntity.setName(techInfo.getName());
                        techInfoEntity.setValue(techInfo.getValue());

                        this.technicalInformationRepository.save(techInfoEntity);

                        updatedTechnicalInformation.add(techInfoEntity);
                        technicalInformationEntityHasBeenUpdated = true;
                        break;
                    }
                }

                if(!technicalInformationEntityHasBeenUpdated)
                    this.technicalInformationRepository.deleteById(domainListTechnicalInformation.get(i).getId());
            }
            // Create a new entity, save it and add it to the rollercoaster entity
            else
            {
                TechnicalInformationEntity techInfoEntity = new TechnicalInformationEntity(
                    techInfo.getId(),
                    techInfo.getName(),
                    techInfo.getValue()
                );

                techInfoEntity = this.technicalInformationRepository.save(techInfoEntity);
                updatedTechnicalInformation.add(techInfoEntity);
            }
        }

        entity.setTechnicalInformation(updatedTechnicalInformation);
    }

    private RollerCoasterEntity getRollerCoasterByID(Long id) throws NotFoundException
    {
        return this.rollerCoasterRepository.findById(id).orElseThrow(
            () -> new NotFoundException("Rollercoaster with ID: '" + id + "' does not exist.")
        );
    }

    private List<SimpleRollerCoaster> getRollerCoastersByName(String name)
    {
        return this.convertRollerCoasterEntitiesToSimpleDomainModels(this.rollerCoasterRepository.findAllByName(name));
    }

    private List<SimpleRollerCoaster> getRollerCoastersByPark(String park)
    {
        return this.convertRollerCoasterEntitiesToSimpleDomainModels(this.rollerCoasterRepository.findAllByPark(park));
    }

    private List<SimpleRollerCoaster> convertRollerCoasterEntitiesToSimpleDomainModels(List<RollerCoasterEntity> entities)
    {
        if(entities.size() == 0)
            return List.of();

        ArrayList<SimpleRollerCoaster> domainModels = new ArrayList<SimpleRollerCoaster>();

        for(RollerCoasterEntity entity : entities)
        {
            domainModels.add(RollerCoasterMapper.toSimpleDomainModel(entity));
        }

        return domainModels;
    }

    private void combineQueryResultsWithSearchResultArray(ArrayList<SimpleRollerCoaster> searchResults, List<SimpleRollerCoaster> queryResults)
    {
        ArrayList<Integer> indicesToAddToSearchResults = new ArrayList<Integer>();

        for(Integer queryResultIndex = 0; queryResultIndex < queryResults.size(); queryResultIndex += 1)
        {
            boolean canAddQueryResultToSearchResultArray = true;
            SimpleRollerCoaster queryResult = queryResults.get(queryResultIndex);

            for(SimpleRollerCoaster searchResult : searchResults)
            {
                if(searchResult.getId().equals(queryResult.getId()))
                {
                    canAddQueryResultToSearchResultArray = false;
                    break;
                }
            }

            if(canAddQueryResultToSearchResultArray)
            {
                indicesToAddToSearchResults.add(queryResultIndex);
            }
        }

        for(Integer queryResultIndex : indicesToAddToSearchResults)
        {
            searchResults.add(queryResults.get(queryResultIndex));
        }
    }
}
