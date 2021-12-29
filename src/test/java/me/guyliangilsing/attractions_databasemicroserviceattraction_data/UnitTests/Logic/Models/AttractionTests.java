package me.guyliangilsing.attractions_databasemicroserviceattraction_data.UnitTests.Logic.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.Attraction;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.TechnicalInformation;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class AttractionTests
{
    @Test
    public void testIfCanAddTechnicalInformationField()
    {
        // Arrange
        Attraction domainModel = new Attraction();
        TechnicalInformation technicalInformationFieldToAdd = new TechnicalInformation(1l, "Manufacturer", "Mack");

        // Act
        domainModel.addTechnicalInformation(technicalInformationFieldToAdd);

        // Assert
        List<TechnicalInformation> technicalInformation = domainModel.getTechnicalInformation();
        assertEquals(technicalInformation.size(), 1);
        
        TechnicalInformation addedTechnicalInformation = technicalInformation.get(0);
        assertEquals(technicalInformationFieldToAdd.getId(), addedTechnicalInformation.getId());
        assertEquals(technicalInformationFieldToAdd.getName(), addedTechnicalInformation.getName());
        assertEquals(technicalInformationFieldToAdd.getValue(), addedTechnicalInformation.getValue());
    }

    @Test
    public void testIfCanRemoveTechnicalInformationField()
    {
        // Arrange
        Attraction domainModel = new Attraction();
        
        domainModel.setTechnicalInformation(
            List.of(new TechnicalInformation(1l, "Manufacturer", "Mack"))
        );

        // Act
        domainModel.removeTechnicalInformation(1l);

        // Assert
        List<TechnicalInformation> technicalInformation = domainModel.getTechnicalInformation();
        assertEquals(technicalInformation.size(), 0);
    }
}
