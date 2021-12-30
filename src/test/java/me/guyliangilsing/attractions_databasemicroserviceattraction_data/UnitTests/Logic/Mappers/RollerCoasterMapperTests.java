package me.guyliangilsing.attractions_databasemicroserviceattraction_data.UnitTests.Logic.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Entities.RollerCoasterEntity;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Mappers.RollerCoasterMapper;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.AttractionStatus;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.RollerCoaster;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RollerCoasterMapperTests
{
    @Test
    public void testIfCanMapDomainModelToEntity()
    {
        // Arrange
        RollerCoaster domainModel = this.getPreconfiguredDomainModel();

        // Act
        RollerCoasterEntity entity = RollerCoasterMapper.toEntity(domainModel);

        // Assert
        assertEquals(domainModel.getId(),           entity.getId());
        assertEquals(domainModel.getAuthorId(),     entity.getAuthorId());
        assertEquals(domainModel.getName(),         entity.getName());
        assertEquals(domainModel.getDescription(),  entity.getDescription());
        assertEquals(domainModel.getOpeningDate(),  entity.getOpeningDate().toString());
        assertEquals(domainModel.getStatus(),       entity.getStatus());
        assertEquals(domainModel.getPark(),         entity.getPark());
        assertEquals(domainModel.getCountry(),      entity.getCountry());
        assertEquals(domainModel.getHeight(),       entity.getHeight());
        assertEquals(domainModel.getSpeed(),        entity.getSpeed());
        assertEquals(domainModel.getElements(),     entity.getElements());
        assertEquals(domainModel.getInversions(),   entity.getInversions());
    }

    @Test
    public void testIfCanMapEntityToDomainModel()
    {
        // Arrange
        RollerCoasterEntity entity = this.getPreconfiguredEntity();

        // Act
        RollerCoaster domainModel = RollerCoasterMapper.toDomainModel(entity);

        // Assert
        assertEquals(entity.getId(),                        domainModel.getId());
        assertEquals(entity.getAuthorId(),                  domainModel.getAuthorId());
        assertEquals(entity.getName(),                      domainModel.getName());
        assertEquals(entity.getDescription(),               domainModel.getDescription());
        assertEquals(entity.getOpeningDate().toString(),    domainModel.getOpeningDate());
        assertEquals(entity.getStatus(),                    domainModel.getStatus());
        assertEquals(entity.getPark(),                      domainModel.getPark());
        assertEquals(entity.getCountry(),                   domainModel.getCountry());
        assertEquals(entity.getHeight(),                    domainModel.getHeight());
        assertEquals(entity.getSpeed(),                     domainModel.getSpeed());
        assertEquals(entity.getElements(),                  domainModel.getElements());
        assertEquals(entity.getInversions(),                domainModel.getInversions());
    }

    private RollerCoaster getPreconfiguredDomainModel()
    {
        RollerCoaster domainModel = new RollerCoaster();

        domainModel.setId(1l);
        domainModel.setAuthorId(1l);
        domainModel.setName("Test attraction");
        domainModel.setDescription("Test description.");
        domainModel.setOpeningDate("2000-11-21");
        domainModel.setStatus(AttractionStatus.DEFUNCT);
        domainModel.setPark("Test park");
        domainModel.setCountry("Test country");
        domainModel.setHeight(35.00);
        domainModel.setSpeed(90.00);
        domainModel.setElements("Overbanked turn, drop-track");
        domainModel.setInversions("Corkscrew, Zero-G-Roll");

        return domainModel;
    }

    private RollerCoasterEntity getPreconfiguredEntity()
    {
        RollerCoasterEntity entity = new RollerCoasterEntity();

        entity.setId(1l);
        entity.setAuthorId(1l);
        entity.setName("Test attraction");
        entity.setDescription("Test description.");
        entity.setOpeningDate("2000-11-21");
        entity.setStatus(AttractionStatus.DEFUNCT);
        entity.setPark("Test park");
        entity.setCountry("Test country");
        entity.setHeight(35.00);
        entity.setSpeed(90.00);
        entity.setElements("Overbanked turn, drop-track");
        entity.setInversions("Corkscrew, Zero-G-Roll");

        return entity;
    }
}
