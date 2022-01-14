package me.guyliangilsing.attractions_databasemicroserviceattraction_data.UnitTests.Logic.Validators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.RollerCoaster;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Validators.RollerCoasterValidator;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class RollerCoasterValidatorTest
{
    private final RollerCoasterValidator rollerCoasterValidator = new RollerCoasterValidator();

    @Test
    public void testThatValidCoasterDoesNotProduceErrors()
    {
        // Arrange
        RollerCoaster rollerCoaster = new RollerCoaster();
        
        rollerCoaster.setName("Test name");
        rollerCoaster.setPark("Test park");

        // Act
        boolean isValid = this.rollerCoasterValidator.isValid(rollerCoaster);

        // Assert
        assertEquals(true, isValid);
    }

    @Test
    public void testThatNameIsEmptyErrorIsGivenWithEmptyName()
    {
        // Arrange
        RollerCoaster rollerCoaster = new RollerCoaster();
        
        rollerCoaster.setName("");
        rollerCoaster.setPark("Test park");

        // Act
        boolean isValid = this.rollerCoasterValidator.isValid(rollerCoaster);

        // Assert
        HashMap<String, String[]> errors = this.rollerCoasterValidator.getErrors();

        assertEquals(false, isValid);
        assertEquals(true, errors.containsKey("name"));
        assertEquals("Name is empty", errors.get("name")[0]);
    }

    @Test
    public void testThatNameIsEmptyErrorIsGivenWithOnlyWhitespaceName()
    {
        // Arrange
        RollerCoaster rollerCoaster = new RollerCoaster();
        
        rollerCoaster.setName("    ");
        rollerCoaster.setPark("Test park");

        // Act
        boolean isValid = this.rollerCoasterValidator.isValid(rollerCoaster);

        // Assert
        HashMap<String, String[]> errors = this.rollerCoasterValidator.getErrors();

        assertEquals(false, isValid);
        assertEquals(true, errors.containsKey("name"));
        assertEquals("Name is empty", errors.get("name")[0]);
    }

    @Test
    public void testThatNameIsToolongErrorIsGiven()
    {
        // Arrange
        RollerCoaster rollerCoaster = new RollerCoaster();
        
        rollerCoaster.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        rollerCoaster.setPark("Test park");

        // Act
        boolean isValid = this.rollerCoasterValidator.isValid(rollerCoaster);

        // Assert
        HashMap<String, String[]> errors = this.rollerCoasterValidator.getErrors();

        assertEquals(false, isValid);
        assertEquals(true, errors.containsKey("name"));
        assertEquals("Name is longer than 60 characters", errors.get("name")[0]);
    }

    @Test
    public void testThatParkIsEmptyErrorIsGivenWithEmptyName()
    {
        // Arrange
        RollerCoaster rollerCoaster = new RollerCoaster();
        
        rollerCoaster.setName("Test name");
        rollerCoaster.setPark("");

        // Act
        boolean isValid = this.rollerCoasterValidator.isValid(rollerCoaster);

        // Assert
        HashMap<String, String[]> errors = this.rollerCoasterValidator.getErrors();

        assertEquals(false, isValid);
        assertEquals(true, errors.containsKey("park"));
        assertEquals("Park is empty", errors.get("park")[0]);
    }

    @Test
    public void testThatParkIsEmptyErrorIsGivenWithOnlyWhitespaceName()
    {
        // Arrange
        RollerCoaster rollerCoaster = new RollerCoaster();
        
        rollerCoaster.setName("Test park");
        rollerCoaster.setPark("    ");

        // Act
        boolean isValid = this.rollerCoasterValidator.isValid(rollerCoaster);

        // Assert
        HashMap<String, String[]> errors = this.rollerCoasterValidator.getErrors();

        assertEquals(false, isValid);
        assertEquals(true, errors.containsKey("park"));
        assertEquals("Park is empty", errors.get("park")[0]);
    }

    @Test
    public void testThatParkIsToolongErrorIsGiven()
    {
        // Arrange
        RollerCoaster rollerCoaster = new RollerCoaster();
        
        rollerCoaster.setName("Test name");
        rollerCoaster.setPark("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        // Act
        boolean isValid = this.rollerCoasterValidator.isValid(rollerCoaster);

        // Assert
        HashMap<String, String[]> errors = this.rollerCoasterValidator.getErrors();

        assertEquals(false, isValid);
        assertEquals(true, errors.containsKey("park"));
        assertEquals("Park is longer than 128 characters", errors.get("park")[0]);
    }
}
