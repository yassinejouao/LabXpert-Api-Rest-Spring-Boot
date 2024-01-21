package yass.jouao.labx.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import yass.jouao.labx.DTOs.AnalysisDTO;
import yass.jouao.labx.entities.Analysis;
import yass.jouao.labx.entities.Patient;
import yass.jouao.labx.enums.AnalysisStatus;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IAnalysisRepository;
import yass.jouao.labx.repositories.IAnalysisTypeRepository;
import yass.jouao.labx.repositories.IPatientRepository;
import yass.jouao.labx.repositories.ISampleRepository;
import yass.jouao.labx.repositories.ITestRepository;
import yass.jouao.labx.repositories.IUserLabRepository;
import yass.jouao.labx.serviceImpl.Mappers.AnalysisMapper;
import yass.jouao.labx.serviceImpl.Mappers.PatientMapper;
import yass.jouao.labx.serviceImpl.Mappers.TestMapper;

@ExtendWith(MockitoExtension.class)
class AnalysisImplTest {

	@InjectMocks
	private AnalysisImpl analysisImpl;

	@Mock
	private AnalysisMapper analysisMapper;
	@Mock
	private PatientMapper patientMapper;
	@Mock
	private TestMapper testMapper;
	@Mock
	private TestTypeServiceImpl testTypeServiceImpl;
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

	@Test
	public void testGetAllAnalysisInProgressTest() throws NotFoundException {
		List<Analysis> analysis = Arrays.asList(
				Analysis.builder().endDate(LocalDateTime.now()).startDate(LocalDateTime.now())
						.status(AnalysisStatus.WAITING).build(),
				Analysis.builder().endDate(LocalDateTime.now()).startDate(LocalDateTime.now())
						.status(AnalysisStatus.WAITING).build());
		when(analysisRepository.findByStatusIn(AnalysisStatus.ANALYZING, AnalysisStatus.WAITING)).thenReturn(analysis);
		when(analysisMapper.fromAnalysisToAnalysisDTO(any(Analysis.class))).thenAnswer(invocation -> {
			Analysis analysisinvo = invocation.getArgument(0);
			AnalysisDTO analysisDTO = AnalysisDTO.builder().status(AnalysisStatus.WAITING)
					.startDate(analysisinvo.getStartDate()).endDate(analysisinvo.getEndDate()).build();
			return analysisDTO;
		});
		List<AnalysisDTO> result = analysisImpl.getAllAnalysisInProgress();
		assertEquals(2, result.size());

	}

	@Test
	void testGetAnalysisByIdPatientService() throws NotFoundException {
		Long patientId = 1L;
		Patient patient = Patient.builder().id(patientId).build();
		List<Analysis> analysis = Arrays.asList(
				Analysis.builder().patient(patient).endDate(LocalDateTime.now()).startDate(LocalDateTime.now())
						.status(AnalysisStatus.WAITING).build(),
				Analysis.builder().patient(patient).endDate(LocalDateTime.now()).startDate(LocalDateTime.now())
						.status(AnalysisStatus.WAITING).build());
		when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));
		when(analysisRepository.findAllByPatient(patient)).thenReturn(analysis);
		when(analysisMapper.fromAnalysisToAnalysisDTO(analysis.get(0))).thenReturn(new AnalysisDTO());
		when(analysisMapper.fromAnalysisToAnalysisDTO(analysis.get(1))).thenReturn(new AnalysisDTO());
		List<AnalysisDTO> result = analysisImpl.getAnalysisByIdPatientService(patientId);
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertEquals(2, result.size());
		verify(patientRepository, times(1)).findById(patientId);
		verify(analysisRepository, times(1)).findAllByPatient(patient);
		verify(analysisMapper, times(2)).fromAnalysisToAnalysisDTO(any(Analysis.class));
	}

}
