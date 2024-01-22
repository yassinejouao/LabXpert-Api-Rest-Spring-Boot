package yass.jouao.labx.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import yass.jouao.labx.DTOs.PatientDTO;
import yass.jouao.labx.entities.Patient;
import yass.jouao.labx.enums.Sex;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IPatientRepository;
import yass.jouao.labx.serviceImpl.Mappers.PatientMapper;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

	private PatientMapper patientMapper;
	@Mock
	private IPatientRepository patientRepository;

	@InjectMocks
	private PatientServiceImpl patientService;

	@BeforeEach
	public void setup() {
		patientMapper = new PatientMapper();
		patientRepository = Mockito.mock(IPatientRepository.class);
		patientService = new PatientServiceImpl(patientMapper, patientRepository);
	}

	@Test
	void testGetPatientByIdService() throws NotFoundException {
		Patient patient = Patient.builder().id(1L).firstname("yassine").lastname("jouaoudi")
				.dateOfBirth(LocalDate.now()).sex(Sex.MAN).phone("0012021").build();
		PatientDTO patientDTO = PatientDTO.builder().id(1L).firstname("yassine").lastname("jouaoudi")
				.dateOfBirth(LocalDate.now()).sex(Sex.MAN).phone("0012021").build();
		Optional<Patient> optionalPatient = Optional.of(patient);

		when(patientRepository.findById(1L)).thenReturn(optionalPatient);

		PatientDTO result = patientService.getPatientByIdService(1L);

		assertEquals(patientDTO, result);
		verify(patientRepository, times(1)).findById(1L);
	}

//
	@Test
	void testAddPatientService() {
		Patient patient = Patient.builder().id(1L).firstname("yassine").lastname("jouaoudi")
				.dateOfBirth(LocalDate.now()).sex(Sex.MAN).phone("0012021").build();
		PatientDTO patientDTO = PatientDTO.builder().id(1L).firstname("yassine").lastname("jouaoudi")
				.dateOfBirth(LocalDate.now()).sex(Sex.MAN).phone("0012021").build();
		when(patientRepository.save(patient)).thenReturn(patient);
		PatientDTO result = patientService.addPatientService(patientDTO);
		assertEquals(patientDTO, result);
		verify(patientRepository, times(1)).save(patient);
	}

	@Test
	public void testUpdatePatientService() throws IllegalAccessException, NotFoundException {
		Long patientId = 1L;
		PatientDTO newPatientDTO = PatientDTO.builder().id(patientId).firstname("new").lastname("Patient1")
				.dateOfBirth(LocalDate.of(1990, 1, 1)).sex(Sex.MAN).phone("123456789").build();
		Patient newPatient = Patient.builder().id(patientId).firstname("new").lastname("Patient1")
				.dateOfBirth(LocalDate.of(1990, 1, 1)).sex(Sex.MAN).phone("123456789").build();
		PatientDTO oldPatientDTO = PatientDTO.builder().id(patientId).firstname("Old").lastname("Patient2")
				.dateOfBirth(LocalDate.of(1980, 1, 1)).sex(Sex.WOMAN).phone("987654321").build();
		Patient oldPatient = Patient.builder().id(patientId).firstname("Old").lastname("Patient2")
				.dateOfBirth(LocalDate.of(1980, 1, 1)).sex(Sex.WOMAN).phone("987654321").build();
		Optional<Patient> optionalPatient = Optional.of(oldPatient);

		when(patientRepository.findById(1L)).thenReturn(optionalPatient);
		when(patientRepository.save(any())).thenReturn(newPatient);

		PatientDTO result = patientService.updatePatientService(patientId, newPatientDTO);
		verify(patientRepository, times(1)).save(any());
		assertEquals(patientId, result.getId());
		assertNotEquals(oldPatientDTO.getFirstname(), result.getFirstname());
		assertNotEquals(oldPatientDTO.getLastname(), result.getLastname());
	}

	@Test
	public void deletePatientServiceTest() throws NotFoundException {
		Long patientId = 1L;
		when(patientRepository.existsById(patientId)).thenReturn(true);
		patientService.deletePatientService(patientId);
		verify(patientRepository, times(1)).deleteById(patientId);

	}
}
