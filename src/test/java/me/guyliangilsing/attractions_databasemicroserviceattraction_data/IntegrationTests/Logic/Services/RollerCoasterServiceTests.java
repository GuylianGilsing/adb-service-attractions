package me.guyliangilsing.attractions_databasemicroserviceattraction_data.IntegrationTests.Logic.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import javassist.NotFoundException;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.AttractionStatus;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.RollerCoaster;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.SimpleRollerCoaster;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.TechnicalInformation;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Services.RollerCoasterService;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Presentation.Errors.NameAndParkCombinationNotUniqueException;

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
    @DirtiesContext // Refresh the contenxt, and thus the database, after running this test
    public void testIfCanCreateRollerCoasterWithUniqueNameAndParkCombination() throws NameAndParkCombinationNotUniqueException
    {
        // Arrange
        RollerCoaster rollerCoaster = new RollerCoaster();

        rollerCoaster.setName("Raik");
        rollerCoaster.setDescription("Test description");
        rollerCoaster.setAuthorId(1l);
        rollerCoaster.setOpeningDate("2016-06-30");
        rollerCoaster.setStatus(AttractionStatus.OPERATIONAL);
        rollerCoaster.setPark("Phantasialand");
        rollerCoaster.setCountry("Germany");
        rollerCoaster.setHeight(25.00);
        rollerCoaster.setSpeed(62.00);

        // Act
        RollerCoaster createdRollerCoaster = this.rollerCoasterService.create(rollerCoaster);

        // Assert
        assertEquals(rollerCoaster.getName(), createdRollerCoaster.getName());
        assertEquals(rollerCoaster.getDescription(), createdRollerCoaster.getDescription());
        assertEquals(rollerCoaster.getAuthorId(), createdRollerCoaster.getAuthorId());
        assertEquals(rollerCoaster.getOpeningDate(), createdRollerCoaster.getOpeningDate());
        assertEquals(rollerCoaster.getStatus(), createdRollerCoaster.getStatus());
        assertEquals(rollerCoaster.getPark(), createdRollerCoaster.getPark());
        assertEquals(rollerCoaster.getCountry(), createdRollerCoaster.getCountry());
        assertEquals(rollerCoaster.getHeight(), createdRollerCoaster.getHeight());
        assertEquals(rollerCoaster.getSpeed(), createdRollerCoaster.getSpeed());
    }

    @Test
    public void testIfCantCreateRollerCoasterWithSameNameAndParkCombination()
    {
        // Arrange
        RollerCoaster rollerCoaster = new RollerCoaster();

        rollerCoaster.setName("Black Mamba");
        rollerCoaster.setPark("Phantasialand");

        // Act + assert
        Exception exception = assertThrows(NameAndParkCombinationNotUniqueException.class, () -> {
            this.rollerCoasterService.create(rollerCoaster); 
        });

        assertNotNull(exception);
    }

    @Test
    @DirtiesContext // Refresh the contenxt, and thus the database, after running this test
    public void testIfCanUpdateTechnicalInformation() throws NotFoundException
    {
        // Arrange
        RollerCoaster rollerCoaster = this.rollerCoasterService.getByID(1l);

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
    @DirtiesContext // Refresh the contenxt, and thus the database, after running this test
    public void testIfCanRemoveOneTechnicalInformationField() throws NotFoundException
    {
        // Arrange
        RollerCoaster rollerCoaster = this.rollerCoasterService.getByID(1l);

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
    @DirtiesContext // Refresh the contenxt, and thus the database, after running this test
    public void testIfCanAddOneTechnicalInformationField() throws NotFoundException
    {
        // Arrange
        RollerCoaster rollerCoaster = this.rollerCoasterService.getByID(1l);

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

    @Test
    public void testIfCanSearchRollerCoasterByExactName()
    {
        // Act
        List<SimpleRollerCoaster> searchResults = this.rollerCoasterService.search("Taron", "");

        // Assert
        assertEquals(1, searchResults.size());
        assertEquals("Taron", searchResults.get(0).getName());
    }

    @Test
    public void testIfCanSearchRollerCoasterByLooseName()
    {
        // Act
        List<SimpleRollerCoaster> searchResults = this.rollerCoasterService.search("tArON", "");

        // Assert
        assertEquals(1, searchResults.size());
        assertEquals("Taron", searchResults.get(0).getName());
    }

    @Test
    public void testIfCanSearchRollerCoasterByPartialName()
    {
        // Act
        List<SimpleRollerCoaster> searchResults = this.rollerCoasterService.search("aro", "");

        // Assert
        assertEquals(1, searchResults.size());
        assertEquals("Taron", searchResults.get(0).getName());
    }

    @Test
    public void testIfCanSearchRollerCoasterByPartialLooseName()
    {
        // Act
        List<SimpleRollerCoaster> searchResults = this.rollerCoasterService.search("ARo", "");

        // Assert
        assertEquals(1, searchResults.size());
        assertEquals("Taron", searchResults.get(0).getName());
    }

    @Test
    public void testIfCanSearchRollerCoasterByExactPark()
    {
        // Act
        List<SimpleRollerCoaster> searchResults = this.rollerCoasterService.search("", "Phantasialand");

        // Assert
        assertEquals(2, searchResults.size());
        assertEquals("Phantasialand", searchResults.get(0).getPark());
        assertEquals("Phantasialand", searchResults.get(1).getPark());
    }

    @Test
    public void testIfCanSearchRollerCoasterByLoosePark()
    {
        // Act
        List<SimpleRollerCoaster> searchResults = this.rollerCoasterService.search("", "PhANtaSiAlaND");

        // Assert
        assertEquals(2, searchResults.size());
        assertEquals("Phantasialand", searchResults.get(0).getPark());
        assertEquals("Phantasialand", searchResults.get(1).getPark());
    }

    @Test
    public void testIfCanSearchRollerCoasterByPartialPark()
    {
        // Act
        List<SimpleRollerCoaster> searchResults = this.rollerCoasterService.search("", "antasia");

        // Assert
        assertEquals(2, searchResults.size());
        assertEquals("Phantasialand", searchResults.get(0).getPark());
        assertEquals("Phantasialand", searchResults.get(1).getPark());
    }

    @Test
    public void testIfCanSearchRollerCoasterByPartialLoosePark()
    {
        // Act
        List<SimpleRollerCoaster> searchResults = this.rollerCoasterService.search("", "aNTasIa");

        // Assert
        assertEquals(2, searchResults.size());
        assertEquals("Phantasialand", searchResults.get(0).getPark());
        assertEquals("Phantasialand", searchResults.get(1).getPark());
    }

    @Test
    public void testThatNoDuplicateAttractionsAreBeingReturned()
    {
        // Act
        List<SimpleRollerCoaster> searchResults = this.rollerCoasterService.search("Taron", "Phantasialand");

        // Assert
        assertEquals(2, searchResults.size());
        assertEquals("Phantasialand", searchResults.get(0).getPark());
        assertEquals("Phantasialand", searchResults.get(1).getPark());
        assertNotEquals(searchResults.get(0).getId(), searchResults.get(1).getId());
    }
}
