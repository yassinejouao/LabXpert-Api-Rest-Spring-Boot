package yass.jouao.labx.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import yass.jouao.labx.entities.Patient;
import yass.jouao.labx.enums.RoleUser;
import yass.jouao.labx.enums.Sex;
import yass.jouao.labx.enums.StatusUser;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
class IPatientRepositoryTest {
    @Autowired
    private IPatientRepository patientRepository;

    @Test
    @DisplayName("Test of save")
    public void testSave(){
        //Arange
        Patient patient = Patient.builder().id(1L).firstname("patient").lastname("lastName").dateOfBirth(LocalDate.now()).sex(Sex.MAN).phone("2039374").build();
        //Act
        Patient savedPatient = patientRepository.save(patient);
        //Assert
        assertNotNull(savedPatient);
        assertEquals(savedPatient.getId(), 1L);
    }
    @Test
    @DisplayName("Test of findAll")
    public void TestFindAll(){
        //Arange
        Patient patient1 = Patient.builder().firstname("patient1").lastname("lastName").dateOfBirth(LocalDate.now()).sex(Sex.MAN).phone("2039374").build();
        Patient patient2 = Patient.builder().firstname("patient2").lastname("lastName").dateOfBirth(LocalDate.now()).sex(Sex.WOMAN).phone("43629364").build();
        patientRepository.save(patient1);
        patientRepository.save(patient2);
        //Act
        List<Patient> patients = patientRepository.findAll();
        //Assert
        assertNotNull(patients);
        assertEquals(2,patients.size());
        assertEquals("patient1",patients.get(0).getFirstname());
        assertEquals("patient2",patients.get(1).getFirstname());
    }
    @Test
    @DisplayName("Test of findById")
    public void TestFindById(){
        //Arange
        Patient patient = Patient.builder().firstname("patient").lastname("lastName").dateOfBirth(LocalDate.now()).sex(Sex.MAN).phone("2039374").build();
        patientRepository.save(patient);
        //Act
        Patient patient1 = patientRepository.findById(patient.getId()).get();
        //Assert
        assertNotNull(patient1);
        assertEquals(patient1.getFirstname(),patient.getFirstname());
        assertEquals(patient1.getSex(),Sex.MAN);
    }
    @Test
    @DisplayName("Test of Update")
    public void updateTest(){
        Patient patient = Patient.builder().firstname("patient").lastname("lastName").dateOfBirth(LocalDate.now()).sex(Sex.MAN).phone("2039374").build();
        patientRepository.save(patient);
        Patient patientSave = patientRepository.findById(patient.getId()).get();
        patientSave.setFirstname("patientUp");
        patientSave.setSex(Sex.WOMAN);
        Patient updatedPatient = patientRepository.save(patientSave);
        assertNotNull(updatedPatient.getFirstname());
        assertEquals(updatedPatient.getFirstname(),"patientUp");
        assertNotEquals(updatedPatient.getSex(),Sex.MAN);
    }
    @Test
    @DisplayName("Test of Delete")
    public void deleteTest(){
        Patient patient = Patient.builder().firstname("patient").lastname("lastName").dateOfBirth(LocalDate.now()).sex(Sex.MAN).phone("2039374").build();
        patientRepository.save(patient);
        patientRepository.deleteById(patient.getId());
        Optional<Patient> patientReturn = patientRepository.findById(patient.getId());
        assertFalse(patientReturn.isPresent());
    }
}