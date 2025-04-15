package boostclinic;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



class PatientTest {

    @Test
    void testPatientConstructorAndGetters() {
        // Arrange
        String expectedId = "P123";
        String expectedName = "John Doe";

        // Act
        Patient patient = new Patient(expectedId, expectedName);

        // Assert
        assertEquals(expectedId, patient.getPatientId());
        assertEquals(expectedName, patient.getPatientName());
    }
}