package me.guyliangilsing.attractions_databasemicroserviceattraction_data.TestConfigurations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Entities.RollerCoasterEntity;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Entities.TechnicalInformationEntity;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Repositories.RollerCoasterRepository;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.AttractionStatus;

@Configuration
public class DatabaseConfiguration {
    private final RollerCoasterRepository rollerCoasterRepository;
    
    @Autowired
    public DatabaseConfiguration(RollerCoasterRepository rollerCoasterRepository)
    {
        this.rollerCoasterRepository = rollerCoasterRepository;
    }

    @Bean
    public CommandLineRunner prepareDatabaseData()
    {
        return (args) -> {
            RollerCoasterEntity entity1 = this.getPreconfiguredEntityOne();
            RollerCoasterEntity entity2 = this.getPreconfiguredEntityTwo();

            rollerCoasterRepository.save(entity1);
            rollerCoasterRepository.save(entity2);
        };
    }

    private RollerCoasterEntity getPreconfiguredEntityOne()
    {
        RollerCoasterEntity entity = new RollerCoasterEntity();

        List<TechnicalInformationEntity> technicalInformation = List.of(
            new TechnicalInformationEntity(1l, "Manufacturer", "B&M"),
            new TechnicalInformationEntity(2l, "Coaster type", "Inverted Coaster")
        );

        entity.setId(1l);
        entity.setName("Black Mamba");
        entity.setDescription("Test description");
        entity.setAuthorId(1l);
        entity.setOpeningDate("2006-05-24");
        entity.setStatus(AttractionStatus.OPERATIONAL);
        entity.setPark("Phantasialand");
        entity.setCountry("Germany");
        entity.setHeight(26.00);
        entity.setSpeed(80.00);
        entity.setTechnicalInformation(technicalInformation);

        return entity;
    }

    private RollerCoasterEntity getPreconfiguredEntityTwo()
    {
        RollerCoasterEntity entity = new RollerCoasterEntity();

        entity.setId(2l);
        entity.setName("Taron");
        entity.setDescription("Test description");
        entity.setAuthorId(1l);
        entity.setOpeningDate("2016-06-30");
        entity.setStatus(AttractionStatus.OPERATIONAL);
        entity.setPark("Phantasialand");
        entity.setCountry("Germany");
        entity.setHeight(30.00);
        entity.setSpeed(117.00);

        return entity;
    }
}
