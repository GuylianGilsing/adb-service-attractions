package me.guyliangilsing.attractions_databasemicroserviceattraction_data.UnitTests.Logic.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Entities.TechnicalInformationEntity;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Mappers.TechnicalInformationMapper;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.TechnicalInformation;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TechnicalInformationMapperTests
{
    @Test
    public void testIfCanMapDomainModelToEntity()
    {
        // Arrange
        TechnicalInformation domainModel = this.getPreconfiguredDomainModel();
    
        // Act
        TechnicalInformationEntity entity = TechnicalInformationMapper.toEntity(domainModel);

        // Assert
        assertEquals(domainModel.getId(),       entity.getId());
        assertEquals(domainModel.getName(),     entity.getName());
        assertEquals(domainModel.getValue(),    entity.getValue());
    }

    @Test
    public void testIfCanMapEntityToDomainModel()
    {
        // Arrange
        TechnicalInformationEntity entity = this.getPreconfiguredEntity();
            
        // Act
        TechnicalInformation domainModel = TechnicalInformationMapper.toDomainModel(entity);

        // Assert
        assertEquals(entity.getId(),       domainModel.getId());
        assertEquals(entity.getName(),     domainModel.getName());
        assertEquals(entity.getValue(),    domainModel.getValue());
    }

    @Test
    public void testIfCanMapDomainListToEntityList() throws Exception
    {
        // Arrange
        List<TechnicalInformation> domainList = this.getPreconfiguredDomainModelList();
    
        // Act
        List<TechnicalInformationEntity> entityList = TechnicalInformationMapper.domainListToEntityList(domainList);

        // Assert
        if(domainList.size() != entityList.size())
            throw new Exception("List sizes differ.");

        for(int i = 0; i < domainList.size(); i += 1)
        {
            TechnicalInformation domainModel = domainList.get(i);
            TechnicalInformationEntity entity = entityList.get(i);

            assertEquals(entity.getId(),       domainModel.getId());
            assertEquals(entity.getName(),     domainModel.getName());
            assertEquals(entity.getValue(),    domainModel.getValue());
        }
    }

    @Test
    public void testIfCanMapEntityListToDomainList() throws Exception
    {
        // Arrange
        List<TechnicalInformationEntity> entityList = this.getPreconfiguredEntityList();
    
        // Act
        List<TechnicalInformation> domainList = TechnicalInformationMapper.entityListToDomainList(entityList);

        // Assert
        if(domainList.size() != entityList.size())
            throw new Exception("List sizes differ.");

        for(int i = 0; i < domainList.size(); i += 1)
        {
            TechnicalInformationEntity entity = entityList.get(i);
            TechnicalInformation domainModel = domainList.get(i);

            assertEquals(domainModel.getId(),       entity.getId());
            assertEquals(domainModel.getName(),     entity.getName());
            assertEquals(domainModel.getValue(),    entity.getValue());
        }
    }
    
    private TechnicalInformation getPreconfiguredDomainModel()
    {
        TechnicalInformation technicalInformation = new TechnicalInformation();

        technicalInformation.setId(1l);
        technicalInformation.setName("Test");
        technicalInformation.setValue("Value");

        return technicalInformation;
    }

    private List<TechnicalInformation> getPreconfiguredDomainModelList()
    {
        return List.of(
            new TechnicalInformation(1l, "Test 1", "value"),
            new TechnicalInformation(2l, "Test 2", "value"),
            new TechnicalInformation(3l, "Test 3", "value")
        );
    }

    private TechnicalInformationEntity getPreconfiguredEntity()
    {
        TechnicalInformationEntity technicalInformationEntity = new TechnicalInformationEntity();

        technicalInformationEntity.setId(1l);
        technicalInformationEntity.setName("Test");
        technicalInformationEntity.setValue("Value");
        
        return technicalInformationEntity;
    }

    private List<TechnicalInformationEntity> getPreconfiguredEntityList()
    {
        return List.of(
            new TechnicalInformationEntity(1l, "Test 1", "value"),
            new TechnicalInformationEntity(2l, "Test 2", "value"),
            new TechnicalInformationEntity(3l, "Test 3", "value")
        );
    }
}
