package yass.jouao.labx.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import yass.jouao.labx.DTOs.PatientDTO;
import yass.jouao.labx.entities.Patient;
import yass.jouao.labx.enums.Sex;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IPatientRepository;
import yass.jouao.labx.serviceImpl.Mappers.PatientMapper;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)

class PatientServiceImplTest {

	@Mock
	private PatientMapper patientMapper;

	@Mock
	private IPatientRepository patientRepository;

	@InjectMocks
	private PatientServiceImpl patientService;

	@Test
	void testGetPatientByIdService() throws NotFoundException {
		Patient patient = Patient.builder().id(1L).firstname("yassine").lastname("jouaoudi")
				.dateOfBirth(LocalDate.now()).sex(Sex.MAN).phone("0012021").build();
		PatientDTO patientDTO = PatientDTO.builder().id(1L).firstname("yassine").lastname("jouaoudi")
				.dateOfBirth(LocalDate.now()).sex(Sex.MAN).phone("0012021").build();
		Optional<Patient> optionalPatient = Optional.of(patient);
		when(patientRepository.findById(1L)).thenReturn(optionalPatient);
		when(patientMapper.fromPatientToPatientDTO(patient)).thenReturn(patientDTO);
		PatientDTO result = patientService.getPatientByIdService(1L);
		assertEquals(patientDTO, result);
		verify(patientRepository, times(1)).findById(1L);
		verify(patientMapper, times(1)).fromPatientToPatientDTO(patient);
	}

	@Test
	void testAddPatientService() {
		Patient patient = Patient.builder().id(1L).firstname("yassine").lastname("jouaoudi")
				.dateOfBirth(LocalDate.now()).sex(Sex.MAN).phone("0012021").build();
		PatientDTO patientDTO = PatientDTO.builder().id(1L).firstname("yassine").lastname("jouaoudi")
				.dateOfBirth(LocalDate.now()).sex(Sex.MAN).phone("0012021").build();
		when(patientMapper.fromPatientDTOToPatient(patientDTO)).thenReturn(patient);
		when(patientRepository.save(patient)).thenReturn(patient);
		when(patientMapper.fromPatientToPatientDTO(patient)).thenReturn(patientDTO);
		PatientDTO result = patientService.addPatientService(patientDTO);
		assertEquals(patientDTO, result);
		verify(patientMapper, times(1)).fromPatientDTOToPatient(patientDTO);
		verify(patientRepository, times(1)).save(patient);
		verify(patientMapper, times(1)).fromPatientToPatientDTO(patient);
	}

	@Test
	public void testUpdatePatientService() throws NotFoundException, IllegalAccessException {
		Long patientId = 1L;
		PatientDTO newPatientDTO = PatientDTO.builder().id(patientId).firstname("new").lastname("Patient1")
				.dateOfBirth(LocalDate.of(1990, 1, 1)).sex(Sex.MAN).phone("123456789").build();
		Patient newPatient = Patient.builder().id(patientId).firstname("new").lastname("Patient1")
				.dateOfBirth(LocalDate.of(1990, 1, 1)).sex(Sex.MAN).phone("123456789").build();
		PatientDTO oldPatientDTO = PatientDTO.builder().id(patientId).firstname("Old").lastname("Patient2")
				.dateOfBirth(LocalDate.of(1980, 1, 1)).sex(Sex.WOMAN).phone("987654321").build();
		Patient oldPatient = Patient.builder().id(patientId).firstname("Old").lastname("Patient2")
				.dateOfBirth(LocalDate.of(1980, 1, 1)).sex(Sex.WOMAN).phone("987654321").build();

		when(patientMapper.fromPatientDTOToPatient(any())).thenReturn(oldPatient);
		when(patientMapper.fromPatientToPatientDTO(any())).thenReturn(newPatientDTO);
		when(patientRepository.save(any())).thenReturn(newPatient);
		Optional<Patient> optionalPatient = Optional.of(oldPatient);
		when(patientRepository.findById(1L)).thenReturn(optionalPatient);
		when(patientService.getPatientByIdService(patientId)).thenReturn(oldPatientDTO);

		PatientDTO result = patientService.updatePatientService(patientId, newPatientDTO);

		// Verify the interactions
		verify(patientRepository, times(1)).save(any());
		verify(patientMapper, times(1)).fromPatientDTOToPatient(newPatientDTO);
		verify(patientMapper, times(1)).fromPatientToPatientDTO(patientMapper.fromPatientDTOToPatient(oldPatientDTO));
		assertEquals(patientId, result.getId());
		assertNotEquals(oldPatientDTO.getFirstname(), result.getFirstname());
		assertNotEquals(oldPatientDTO.getLastname(), result.getLastname());
	}

//	@Test
//	public void testUpdatePatientService() throws NotFoundException, IllegalAccessException {
//		Long patientId = 1L;
//		PatientDTO newPatientDTO = PatientDTO.builder().id(patientId).firstname("new").lastname("Patient1")
//				.dateOfBirth(LocalDate.of(1990, 1, 1)).sex(Sex.MAN).phone("123456789").build();
//		Patient newPatient = Patient.builder().id(patientId).firstname("new").lastname("Patient1")
//				.dateOfBirth(LocalDate.of(1990, 1, 1)).sex(Sex.MAN).phone("123456789").build();
//		PatientDTO oldPatientDTO = PatientDTO.builder().id(patientId).firstname("Old").lastname("Patient2")
//				.dateOfBirth(LocalDate.of(1980, 1, 1)).sex(Sex.WOMAN).phone("987654321").build();
//		Patient oldPatient = Patient.builder().id(patientId).firstname("Old").lastname("Patient2")
//				.dateOfBirth(LocalDate.of(1980, 1, 1)).sex(Sex.WOMAN).phone("987654321").build();
//
//		when(patientRepository.save(any(Patient.class))).thenReturn(newPatient);
//		when(patientMapper.fromPatientDTOToPatient(any())).thenReturn(oldPatient);
//		when(patientMapper.fromPatientToPatientDTO(any())).thenReturn(newPatientDTO);
//		Optional<Patient> optionalPatient = Optional.of(oldPatient);
//		when(patientRepository.findById(1L)).thenReturn(optionalPatient);
//		when(patientService.getPatientByIdService(patientId)).thenReturn(oldPatientDTO);
//
//		PatientDTO result = patientService.updatePatientService(patientId, newPatientDTO);
//
//		// Verify the interactions
//		verify(patientRepository, times(1)).save(any());
//		verify(patientMapper, times(1)).fromPatientDTOToPatient(newPatientDTO);
//		verify(patientMapper, times(1)).fromPatientToPatientDTO(patientMapper.fromPatientDTOToPatient(oldPatientDTO));
//		assertEquals(patientId, result.getId());
//		assertNotEquals(oldPatientDTO.getFirstname(), result.getFirstname());
//		assertNotEquals(oldPatientDTO.getLastname(), result.getLastname());
//	}

}
