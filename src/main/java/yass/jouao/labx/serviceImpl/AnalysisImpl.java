package yass.jouao.labx.serviceImpl;

import java.lang.reflect.Field;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yass.jouao.labx.DTOs.AnalysisDTO;
import yass.jouao.labx.DTOs.AnalysisResultDTO;
import yass.jouao.labx.DTOs.TestDTO;
import yass.jouao.labx.entities.Analysis;
import yass.jouao.labx.entities.AnalysisType;
import yass.jouao.labx.entities.Patient;
import yass.jouao.labx.entities.Sample;
import yass.jouao.labx.entities.Test;
import yass.jouao.labx.entities.TestType;
import yass.jouao.labx.entities.UserLab;
import yass.jouao.labx.enums.AnalysisStatus;
import yass.jouao.labx.enums.IntervalRapport;
import yass.jouao.labx.enums.ResultTest;
import yass.jouao.labx.enums.TestStatus;
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
import yass.jouao.labx.services.IAnalysisService;

@Service
public class AnalysisImpl implements IAnalysisService {

	@Autowired
	private AnalysisMapper analysisMapper;
	@Autowired
	private PatientMapper patientMapper;
	@Autowired
	private TestMapper testMapper;
	@Autowired
	private TestTypeServiceImpl testTypeServiceImpl;
	@Autowired
	private IAnalysisRepository analysisRepository;
	@Autowired
	private IPatientRepository patientRepository;
	@Autowired
	private ISampleRepository sampleRepository;
	@Autowired
	private IUserLabRepository userLabRepository;
	@Autowired
	private IAnalysisTypeRepository analysisTypeRepository;
	@Autowired
	private ITestRepository testRepository;

	@Override
	public List<AnalysisDTO> getAllAnalysisInProgress() throws NotFoundException {
		List<Analysis> analysisInProgress = analysisRepository.findByStatusIn(AnalysisStatus.ANALYZING,
				AnalysisStatus.WAITING);
		List<AnalysisDTO> analysisDTOs = analysisInProgress.stream().map(aIProgress -> {
			Patient patient = aIProgress.getPatient();
			AnalysisDTO analysisDTO = analysisMapper.fromAnalysisToAnalysisDTO(aIProgress);
			analysisDTO.setPatientDTO(patientMapper.fromPatientToPatientDTO(patient));
			return analysisDTO;

		}).collect(Collectors.toList());
		return analysisDTOs;
	}

	@Override
	public AnalysisDTO getResultByAnalysisId(Long id) throws NotFoundException {
		Optional<Analysis> analysisOptional = analysisRepository.findById(id);
		if (analysisOptional.isPresent()) {
			if (analysisOptional.get().getStatus() == AnalysisStatus.FINISHED) {
				AnalysisDTO analysisDTO = analysisMapper.fromAnalysisToAnalysisDTO(analysisOptional.get());
				Collection<Test> tests = analysisOptional.get().getTests();
				List<TestDTO> testDTOs = tests.stream().map(test -> testMapper.fromTestToTestDTO(test))
						.collect(Collectors.toList());
				analysisDTO.setTestsDTO(testDTOs);
				return analysisDTO;
			} else {
				throw new NotFoundException("analysis not finished yet!");
			}
		} else {
			throw new NotFoundException("not found analysis");
		}

	}

	@Override
	public List<AnalysisDTO> getAnalysisByIdPatientService(Long id) throws NotFoundException {
		Optional<Patient> patient = patientRepository.findById(id);
		if (patient.isPresent()) {
			List<AnalysisDTO> analysisDTOs = analysisRepository.findAllByPatient(patient.get()).stream()
					.map(a -> analysisMapper.fromAnalysisToAnalysisDTO(a)).collect(Collectors.toList());
			return analysisDTOs;
		} else {
			throw new NotFoundException("not found patient");
		}
	}

	@Override
	public AnalysisDTO addAnalysisService(AnalysisDTO a) throws NotFoundException {
		Analysis analysis = analysisMapper.fromAnalysisDTOToAnalysis(a);
		Optional<Patient> optionalPatient = patientRepository.findById(a.getIdPatient());
		Optional<Sample> optionalSample = sampleRepository.findById(a.getIdSample());
		Optional<UserLab> optionalUser = userLabRepository.findById(a.getIdUserLab());
		Optional<AnalysisType> optionalAnalysisType = analysisTypeRepository.findById(a.getIdAnalysisType());
		if (optionalPatient.isPresent() && optionalSample.isPresent() && optionalUser.isPresent()
				&& optionalAnalysisType.isPresent()) {
			if (!optionalPatient.get().getSamples().contains(optionalSample.get())) {
				throw new NotFoundException("Patient don't have this sample");
			}
			analysis.setPatient(optionalPatient.get());
			analysis.setSample(optionalSample.get());
			analysis.setUser(optionalUser.get());
			analysis.setAnalysisType(optionalAnalysisType.get());
			List<TestType> testTypes = testTypeServiceImpl.getAllTestTypesByAnalysis(analysis);
			Analysis analysis2 = analysisRepository.save(analysis);
			for (TestType testType : testTypes) {
				Test test = new Test();
				test.setTestType(testType);
				test.setAnalysis(analysis);
				test.setResult(null);
				test.setResultTest(null);
				test.setStatus(TestStatus.WAITING);
				test.setResult(ResultTest.WAITING);
				testRepository.save(test);
			}
			return analysisMapper.fromAnalysisToAnalysisDTO(analysis2);
		} else {
			throw new NotFoundException("Patient or sample or user not found");
		}
	}

	@Override
	public List<AnalysisResultDTO> getAnalysisRapport(Long analysisTypeId, LocalDateTime startDate,
			LocalDateTime endDate, IntervalRapport interfRapport) {
		Optional<AnalysisType> analysisType = analysisTypeRepository.findById(analysisTypeId);
		List<Analysis> analyses = analysisRepository.findByAnalysisTypeAndStartDateBetween(analysisType.get(),
				startDate, endDate);
		switch (interfRapport) {
		case DAY:
			return groupByDay(analyses);
		case WEEK:
			return groupByWeek(analyses);
		case MONTH:
			return groupByMonth(analyses);
		default:
			throw new IllegalArgumentException("Interval not found");
		}
	}

	private List<AnalysisResultDTO> groupByDay(List<Analysis> analyses) {
		Map<LocalDate, Long> groupedByDay = analyses.stream().collect(
				Collectors.groupingBy(analysis -> analysis.getStartDate().toLocalDate(), Collectors.counting()));

		return convertToResultFunction(groupedByDay);
	}

	private List<AnalysisResultDTO> groupByMonth(List<Analysis> analyses) {
		Map<LocalDate, Long> groupedByMonth = analyses.stream().collect(Collectors.groupingBy(
				analysis -> analysis.getStartDate().toLocalDate().withDayOfMonth(1), Collectors.counting()));

		return convertToResultFunction(groupedByMonth);
	}

	private List<AnalysisResultDTO> groupByWeek(List<Analysis> analyses) {
		Map<LocalDate, Long> groupedByMonth = analyses.stream().collect(Collectors.groupingBy(
				analysis -> analysis.getStartDate().toLocalDate().with(DayOfWeek.MONDAY), Collectors.counting()));

		return convertToResultFunction(groupedByMonth);
	}

	private List<AnalysisResultDTO> convertToResultFunction(Map<LocalDate, Long> groupedData) {
		return groupedData.entrySet().stream()
				.map(entry -> new AnalysisResultDTO(entry.getKey(), entry.getValue().intValue()))
				.collect(Collectors.toList());
	}

	@Override
	public AnalysisDTO updateAnalysisService(AnalysisDTO a) throws NotFoundException, IllegalAccessException {
		Optional<Analysis> analysisToUpdate = analysisRepository.findById(a.getId());
		analysisToUpdate.ifPresent(analysis -> {
			analysis.getSample();
			analysis.getPatient();
			analysis.getAnalysisType();
			analysis.getUser();
		});
		Analysis analysisNewData = analysisMapper.fromAnalysisDTOToAnalysis(a);
		updateNonNullFields(analysisToUpdate.get(), analysisNewData);
		AnalysisDTO analysisDTO = analysisMapper
				.fromAnalysisToAnalysisDTO(analysisRepository.save(analysisToUpdate.get()));
		return analysisDTO;
	}

	private void updateNonNullFields(Analysis existingEntity, Analysis updatedEntity) throws IllegalAccessException {
		Field[] fields = Analysis.class.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Object updatedValue = field.get(updatedEntity);
			if (updatedValue != null) {
				field.set(existingEntity, updatedValue);
			}
		}
	}

}
