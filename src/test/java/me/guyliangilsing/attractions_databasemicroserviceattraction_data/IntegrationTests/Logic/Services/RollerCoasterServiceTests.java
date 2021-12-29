package me.guyliangilsing.attractions_databasemicroserviceattraction_data.IntegrationTests.Logic.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javassist.NotFoundException;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.AttractionStatus;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.RollerCoaster;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.TechnicalInformation;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Services.RollerCoasterService;

@SpringBootTest
public class RollerCoasterServiceTests
{
    @Autowired
    private RollerCoasterService rollerCoasterService;

    @Test
    public void testThatAutowiredClassesExist()
    {
        assertNotNull(this.rollerCoasterService);
    }

    @Test
    public void testIfCanUpdateTechnicalInformation() throws NotFoundException
    {
        // Arrange
        RollerCoaster rollerCoaster = this.getPreconfiguredRollerCoasterDomainModel();
        rollerCoaster = this.rollerCoasterService.create(rollerCoaster);

        // Act
        String newManufacturerName = "Intamin";
        String newCoasterTypeName = "Blitz coaster";

        List<TechnicalInformation> technicalInformation = rollerCoaster.getTechnicalInformation();

        technicalInformation.get(0).setValue(newManufacturerName);
        technicalInformation.get(1).setValue(newCoasterTypeName);

        rollerCoaster.setTechnicalInformation(technicalInformation);

        RollerCoaster updatedCoaster = this.rollerCoasterService.update(rollerCoaster);

        // Assert
        List<TechnicalInformation> editedTechnicalInformation = rollerCoaster.getTechnicalInformation();
        List<TechnicalInformation> updatedTechnicalInformation = updatedCoaster.getTechnicalInformation();

        assertEquals(editedTechnicalInformation.size(), updatedTechnicalInformation.size());

        for(int i = 0; i < editedTechnicalInformation.size(); i += 1)
        {
            TechnicalInformation editedTechInfoModel = editedTechnicalInformation.get(i);
            TechnicalInformation updatedTechInfoModel = updatedTechnicalInformation.get(i);

            assertEquals(editedTechInfoModel.getId(), updatedTechInfoModel.getId());
            assertEquals(editedTechInfoModel.getName(), updatedTechInfoModel.getName());
            assertEquals(editedTechInfoModel.getValue(), updatedTechInfoModel.getValue());
        }
    }

    @Test
    public void testIfCanRemoveOneTechnicalInformationField() throws NotFoundException
    {
        // Arrange
        RollerCoaster rollerCoaster = this.getPreconfiguredRollerCoasterDomainModel();
        rollerCoaster = this.rollerCoasterService.create(rollerCoaster);

        // Act
        rollerCoaster.removeTechnicalInformation(2l);
        RollerCoaster updatedCoaster = this.rollerCoasterService.update(rollerCoaster);

        // Assert
        List<TechnicalInformation> editedTechnicalInformation = rollerCoaster.getTechnicalInformation();
        List<TechnicalInformation> updatedTechnicalInformation = updatedCoaster.getTechnicalInformation();

        assertEquals(editedTechnicalInformation.size(), updatedTechnicalInformation.size());

        for(int i = 0; i < editedTechnicalInformation.size(); i += 1)
        {
            TechnicalInformation editedTechInfoModel = editedTechnicalInformation.get(i);
            TechnicalInformation updatedTechInfoModel = updatedTechnicalInformation.get(i);

            assertEquals(editedTechInfoModel.getId(), updatedTechInfoModel.getId());
            assertEquals(editedTechInfoModel.getName(), updatedTechInfoModel.getName());
            assertEquals(editedTechInfoModel.getValue(), updatedTechInfoModel.getValue());
        }
    }

    @Test
    public void testIfCanAddOneTechnicalInformationField() throws NotFoundException
    {
        // Arrange
        RollerCoaster rollerCoaster = this.getPreconfiguredRollerCoasterDomainModel();
        rollerCoaster = this.rollerCoasterService.create(rollerCoaster);

        TechnicalInformation techInfoToAdd = new TechnicalInformation("Capacity", "1246");

        // Act
        rollerCoaster.addTechnicalInformation(techInfoToAdd);
        RollerCoaster updatedCoaster = this.rollerCoasterService.update(rollerCoaster);

        // Assert
        List<TechnicalInformation> editedTechnicalInformation = rollerCoaster.getTechnicalInformation();
        List<TechnicalInformation> updatedTechnicalInformation = updatedCoaster.getTechnicalInformation();

        assertEquals(editedTechnicalInformation.size(), updatedTechnicalInformation.size());

        for(int i = 0; i < editedTechnicalInformation.size(); i += 1)
        {
            TechnicalInformation editedTechInfoModel = editedTechnicalInformation.get(i);
            TechnicalInformation updatedTechInfoModel = updatedTechnicalInformation.get(i);

            assertEquals(editedTechInfoModel.getName(), updatedTechInfoModel.getName());
            assertEquals(editedTechInfoModel.getValue(), updatedTechInfoModel.getValue());
        }
    }

    private RollerCoaster getPreconfiguredRollerCoasterDomainModel()
    {
        RollerCoaster rollerCoaster = new RollerCoaster();

        List<TechnicalInformation> technicalInformation = List.of(
            new TechnicalInformation(1l, "Manufacturer", "Test manufacturer"),
            new TechnicalInformation(2l, "Coaster type", "Test")
        );

        rollerCoaster.setId(1l);
        rollerCoaster.setName("Test rollercoaster");
        rollerCoaster.setDescription("Test description");
        rollerCoaster.setAuthorId(1l);
        rollerCoaster.setOpeningDate("2000-11-21");
        rollerCoaster.setStatus(AttractionStatus.OPERATIONAL);
        rollerCoaster.setPark("Test park");
        rollerCoaster.setCountry("Test country");
        rollerCoaster.setHeight(100.00);
        rollerCoaster.setSpeed(120.00);
        rollerCoaster.setElements("Overbanked turn, Airtime hill");
        rollerCoaster.setInversions("Zero-G-Roll, Immelman");
        rollerCoaster.setTechnicalInformation(technicalInformation);

        return rollerCoaster;
    }
}
