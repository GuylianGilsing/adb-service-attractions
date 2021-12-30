package me.guyliangilsing.attractions_databasemicroserviceattraction_data.UnitTests.Logic.Services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javassist.NotFoundException;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Repositories.RollerCoasterRepository;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Data.Repositories.TechnicalInformationRepository;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.AttractionStatus;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.RollerCoaster;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Services.RollerCoasterService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class RollerCoasterServiceTests
{
    @InjectMocks
    private RollerCoasterService rollerCoasterService;
    
    @Mock private RollerCoasterRepository rollerCoasterRepository;
    @Mock private TechnicalInformationRepository technicalInformationRepository;

    @Test
    @BeforeAll
    public void testThatServicesExist()
    {
        assertNotNull(this.rollerCoasterService);
    }

    @Test
    public void testIfNonExistingRollerCoastersThrowsNotFoundExceptionWhenGetByID()
    {
        // Arrange
        when(this.rollerCoasterRepository.findById(1l)).thenReturn(Optional.empty());

        // Act + assert
        Exception exception = assertThrows(NotFoundException.class, () -> {
            this.rollerCoasterService.getByID(1l);
        });

        assertNotNull(exception);
    }

    @Test
    public void testIfNonExistingRollerCoastersThrowsNotFoundExceptionWhenUpdated()
    {
        // Arrange
        when(this.rollerCoasterRepository.findById(1l)).thenReturn(Optional.empty());

        RollerCoaster rollerCoaster = new RollerCoaster();

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

        // Act + assert
        Exception exception = assertThrows(NotFoundException.class, () -> {
            this.rollerCoasterService.update(rollerCoaster);
        });

        assertNotNull(exception);
    }

    @Test
    public void testIfNonExistingRollerCoastersThrowsNotFoundExceptionWhenDeleted()
    {
        // Arrange
        when(this.rollerCoasterRepository.findById(1l)).thenReturn(Optional.empty());

        // Act + assert
        Exception exception = assertThrows(NotFoundException.class, () -> {
            this.rollerCoasterService.delete(1l);
        });

        assertNotNull(exception);
    }
}
