package yass.jouao.labx.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import yass.jouao.labx.DTOs.AnalysisDTO;
import yass.jouao.labx.entities.Analysis;
import yass.jouao.labx.entities.AnalysisType;
import yass.jouao.labx.entities.Patient;
import yass.jouao.labx.entities.Sample;
import yass.jouao.labx.entities.UserLab;
import yass.jouao.labx.enums.AnalysisStatus;
import yass.jouao.labx.enums.RoleUser;
import yass.jouao.labx.enums.SampleStatus;
import yass.jouao.labx.enums.SampleType;
import yass.jouao.labx.enums.Sex;
import yass.jouao.labx.enums.StatusUser;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IAnalysisRepository;
import yass.jouao.labx.repositories.IAnalysisTypeRepository;
import yass.jouao.labx.repositories.IPatientRepository;
import yass.jouao.labx.repositories.ISampleRepository;
import yass.jouao.labx.repositories.ITestRepository;
import yass.jouao.labx.repositories.ITestTypeRepository;
import yass.jouao.labx.repositories.IUserLabRepository;
import yass.jouao.labx.serviceImpl.Mappers.AnalysisMapper;
import yass.jouao.labx.serviceImpl.Mappers.PatientMapper;
import yass.jouao.labx.serviceImpl.Mappers.TestMapper;

@ExtendWith(MockitoExtension.class)
class AnalysisImplTest {

	private AnalysisMapper analysisMapper;
	private PatientMapper patientMapper;
	private TestMapper testMapper;
	@Mock
	private IAnalysisRepository analysisRepository;
	@Mock
	private IPatientRepository patientRepository;
	@Mock
	private ISampleRepository sampleRepository;
	@Mock
	private IUserLabRepository userLabRepository;
	@Mock
	private IAnalysisTypeRepository analysisTypeRepository;
	@Mock
	private ITestRepository testRepository;
	@Mock
	private ITestTypeRepository testTypeRepository;
	@Mock
	private TestTypeServiceImpl testTypeServiceImpl;

	@InjectMocks
	private AnalysisImpl analysisService;

	@BeforeEach
	public void setup() {
		analysisMapper = new AnalysisMapper();
		patientMapper = new PatientMapper();
		testMapper = new TestMapper();
		analysisRepository = Mockito.mock(IAnalysisRepository.class);
		testTypeServiceImpl = Mockito.mock(TestTypeServiceImpl.class);
		analysisService = new AnalysisImpl(analysisMapper, patientMapper, testMapper, testTypeServiceImpl,
				analysisRepository, patientRepository, sampleRepository, userLabRepository, analysisTypeRepository,
				testRepository);
	}

	@Test
	public void testGetAllAnalysisInProgressTest() throws NotFoundException {
		Patient patient = Patient.builder().id(1L).firstname("yassine").lastname("jouaoudi")
				.dateOfBirth(LocalDate.now()).sex(Sex.MAN).phone("0012021").build();
		List<Analysis> analysis = Arrays.asList(
				Analysis.builder().endDate(LocalDateTime.now()).startDate(LocalDateTime.now())
						.status(AnalysisStatus.WAITING).patient(patient).build(),
				Analysis.builder().endDate(LocalDateTime.now()).startDate(LocalDateTime.now())
						.status(AnalysisStatus.WAITING).patient(patient).build());
		when(analysisRepository.findByStatusIn(any())).thenReturn(analysis);
		List<AnalysisDTO> result = analysisService.getAllAnalysisInProgress();
		assertEquals(2, result.size());

	}

	@Test
	void testGetAnalysisByIdPatientService() throws NotFoundException {
		Patient patient = Patient.builder().id(1L).firstname("yassine").lastname("jouaoudi")
				.dateOfBirth(LocalDate.now()).sex(Sex.MAN).phone("0012021").build();
		List<Analysis> analysis = Arrays.asList(
				Analysis.builder().patient(patient).endDate(LocalDateTime.now()).startDate(LocalDateTime.now())
						.status(AnalysisStatus.WAITING).build(),
				Analysis.builder().patient(patient).endDate(LocalDateTime.now()).startDate(LocalDateTime.now())
						.status(AnalysisStatus.WAITING).build());
		Optional<Patient> optionalPatient = Optional.of(patient);

		when(patientRepository.findById(anyLong())).thenReturn(optionalPatient);
		when(analysisRepository.findAllByPatient(any())).thenReturn(analysis);

		List<AnalysisDTO> result = analysisService.getAnalysisByIdPatientService(1L);
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertEquals(2, result.size());
		verify(patientRepository, times(1)).findById(1L);
		verify(analysisRepository, times(1)).findAllByPatient(patient);
	}

	@Test
	void addAnalysisServiceTest() throws NotFoundException {
		Sample sample = Sample.builder().id(1L).type(SampleType.BLOOD).status(SampleStatus.ANALYSING)
				.date(LocalDateTime.now()).build();
		Patient patient = Patient.builder().id(1L).firstname("new").lastname("Patient1")
				.dateOfBirth(LocalDate.of(1990, 1, 1)).samples(Arrays.asList(sample)).sex(Sex.MAN).phone("123456789")
				.build();

		UserLab userLab = UserLab.builder().id(1L).name("user").password("password").userRole(RoleUser.ADMIN)
				.information("info").status(StatusUser.ACTIVE).build();

		AnalysisType analysisType = AnalysisType.builder().id(1L).name("analysisType1").build();
		Optional<Patient> optionalPatient = Optional.of(patient);

		Optional<UserLab> optionalUserLab = Optional.of(userLab);
		Optional<AnalysisType> optionalAnalysisType = Optional.of(analysisType);
		Analysis analysis = Analysis.builder().id(1L).sample(sample).user(userLab).patient(patient)
				.analysisType(analysisType).startDate(LocalDateTime.now()).endDate(LocalDateTime.now())
				.resultAnalysis(true).status(AnalysisStatus.ANALYZING).build();
		AnalysisDTO analysisDTO = AnalysisDTO.builder().id(1L).startDate(LocalDateTime.now())
				.endDate(LocalDateTime.now()).resultAnalysis(true).status(AnalysisStatus.ANALYZING).build();
		Optional<Sample> optionalSample = Optional.of(sample);
		when(patientRepository.findById(any())).thenReturn(optionalPatient);
		when(sampleRepository.findById(any())).thenReturn(optionalSample);
		when(userLabRepository.findById(any())).thenReturn(optionalUserLab);
		when(analysisTypeRepository.findById(any())).thenReturn(optionalAnalysisType);
		when(analysisRepository.save(any())).thenReturn(analysis);

		AnalysisDTO result = analysisService.addAnalysisService(analysisDTO);
		assertNotNull(result);
		assertEquals(result.getId(), 1);

	}

}
